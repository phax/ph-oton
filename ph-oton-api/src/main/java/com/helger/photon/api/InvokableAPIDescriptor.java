/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.photon.api;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.http.CHttp;
import com.helger.commons.mime.MimeType;
import com.helger.commons.mime.MimeTypeParser;
import com.helger.commons.mutable.MutableInt;
import com.helger.commons.string.ToStringGenerator;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An {@link InvokableAPIDescriptor} contains an {@link IAPIDescriptor} as well
 * the original path by which it was called as well as any resolved path
 * variables.
 *
 * @author Philip Helger
 */
public class InvokableAPIDescriptor implements Serializable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (InvokableAPIDescriptor.class);

  private final IAPIDescriptor m_aDescriptor;
  private final String m_sPath;
  private final ICommonsOrderedMap <String, String> m_aPathVariables;

  /**
   * Constructor
   *
   * @param aDescriptor
   *        The matching {@link IAPIDescriptor}. Never <code>null</code>.
   * @param sPath
   *        The URL path requested by the user, relative to the servlet.
   * @param aPathVariables
   *        All resolved path variables, if the path descriptor contains
   *        variable elements.
   */
  public InvokableAPIDescriptor (@Nonnull final IAPIDescriptor aDescriptor,
                                 @Nonnull @Nonempty final String sPath,
                                 @Nonnull final ICommonsOrderedMap <String, String> aPathVariables)
  {
    ValueEnforcer.notNull (aDescriptor, "Descriptor");
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.notNull (aPathVariables, "PathVariables");

    m_aDescriptor = aDescriptor;
    m_sPath = sPath;
    m_aPathVariables = aPathVariables.getClone ();
  }

  /**
   * @return The original API descriptor. Never <code>null</code>.
   */
  @Nonnull
  public final IAPIDescriptor getAPIDescriptor ()
  {
    return m_aDescriptor;
  }

  /**
   * @return The URL path requested by the user, relative to the servlet. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public final String getPath ()
  {
    return m_sPath;
  }

  /**
   * @return All resolved path variables, if the path descriptor contains
   *         variable elements. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsOrderedMap <String, String> getAllPathVariables ()
  {
    return m_aPathVariables.getClone ();
  }

  /**
   * Check if all pre-requisites are handled correctly. This checks if all
   * required headers and params are present.
   *
   * @param aRequestScope
   *        The request scope to validate.
   * @param aStatusCode
   *        The mutable int to hold the status code to be returned in case of
   *        error. Default is HTTP 400, bad request.
   * @return <code>true</code> if all prerequisites are fulfilled.
   */
  public boolean canExecute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final MutableInt aStatusCode)
  {
    if (aRequestScope == null)
      return false;

    // Note: HTTP method was already checked in APIDescriptorList

    // Check required headers
    for (final String sRequiredHeader : m_aDescriptor.requiredHeaders ())
      if (aRequestScope.headers ().getFirstHeaderValue (sRequiredHeader) == null)
      {
        LOGGER.warn ("Request '" + m_sPath + "' is missing required HTTP header '" + sRequiredHeader + "'");
        return false;
      }

    // Check required parameters
    for (final String sRequiredParam : m_aDescriptor.requiredParams ())
      if (!aRequestScope.params ().containsKey (sRequiredParam))
      {
        LOGGER.warn ("Request '" + m_sPath + "' is missing required HTTP parameter '" + sRequiredParam + "'");
        return false;
      }

    // Check explicit filter
    if (m_aDescriptor.hasExecutionFilter ())
      if (!m_aDescriptor.getExecutionFilter ().canExecute (aRequestScope))
      {
        LOGGER.warn ("Request '" + m_sPath + "' cannot be executed because of ExecutionFilter");
        return false;
      }

    if (m_aDescriptor.allowedMimeTypes ().isNotEmpty ())
    {
      final String sContentType = aRequestScope.getContentType ();
      // Parse to extract any parameters etc.
      final MimeType aMT = MimeTypeParser.safeParseMimeType (sContentType);
      // If parsing fails, use the provided String
      final String sMimeTypeToCheck = aMT == null ? sContentType : aMT.getAsStringWithoutParameters ();

      if (!m_aDescriptor.allowedMimeTypes ().contains (sMimeTypeToCheck) &&
          !m_aDescriptor.allowedMimeTypes ().contains (sMimeTypeToCheck.toLowerCase (CGlobal.DEFAULT_LOCALE)))
      {
        LOGGER.warn ("Request '" +
                     m_sPath +
                     "' contains the Content-Type '" +
                     sContentType +
                     "' which is not in the allowed list of " +
                     m_aDescriptor.allowedMimeTypes ());
        // Special error code HTTP 415
        aStatusCode.set (CHttp.HTTP_UNSUPPORTED_MEDIA_TYPE);
        return false;
      }
    }

    return true;
  }

  /**
   * Invoke the Java callback underlying this API descriptor. Note: this method
   * may only be invoked after
   * {@link #canExecute(IRequestWebScopeWithoutResponse,MutableInt)} returned
   * <code>true</code>!
   *
   * @param aRequestScope
   *        Current request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        Current response. Never <code>null</code>.
   * @throws Exception
   *         In case the Java callback throws one
   * @throws IllegalStateException
   *         In case the executor factory creates a <code>null</code> executor.
   */
  public void invokeAPI (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                         @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    final IAPIExecutor aExecutor = m_aDescriptor.getExecutorFactory ().get ();
    if (aExecutor == null)
      throw new IllegalStateException ("Failed to created API executor for: " + toString ());

    // Go go go
    aExecutor.invokeAPI (m_aDescriptor, m_sPath, m_aPathVariables, aRequestScope, aUnifiedResponse);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("APIDescriptor", m_aDescriptor)
                                       .append ("Path", m_sPath)
                                       .append ("PathVariables", m_aPathVariables)
                                       .getToString ();
  }
}
