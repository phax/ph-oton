/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.api;

import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * An {@link InvokableAPIDescriptor} contains an {@link IAPIDescriptor} as well
 * the original path by which it was called as well as any resolved path
 * variables.
 *
 * @author Philip Helger
 */
public final class InvokableAPIDescriptor
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (InvokableAPIDescriptor.class);

  private final String m_sPath;
  private final IAPIDescriptor m_aDescriptor;
  private final Map <String, String> m_aPathVariables;

  /**
   * Constructor
   *
   * @param sPath
   *        The URL path requested by the user, relative to the servlet.
   * @param aDescriptor
   *        The matching {@link IAPIDescriptor}. Never <code>null</code>.
   * @param aPathVariables
   *        All resolved path variables, if the path descriptor contains
   *        variable elements.
   */
  public InvokableAPIDescriptor (@Nonnull @Nonempty final String sPath,
                                 @Nonnull final IAPIDescriptor aDescriptor,
                                 @Nonnull final Map <String, String> aPathVariables)
  {
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.notNull (aDescriptor, "Descriptor");
    ValueEnforcer.notNull (aPathVariables, "PathVariables");

    m_sPath = sPath;
    m_aDescriptor = aDescriptor;
    m_aPathVariables = CollectionHelper.newOrderedMap (aPathVariables);
  }

  /**
   * @return The URL path requested by the user, relative to the servlet. Never
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getPath ()
  {
    return m_sPath;
  }

  /**
   * @return The original API descriptor. Never <code>null</code>.
   */
  @Nonnull
  public IAPIDescriptor getAPIDescriptor ()
  {
    return m_aDescriptor;
  }

  /**
   * @return All resolved path variables, if the path descriptor contains
   *         variable elements. Never <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Map <String, String> getAllPathVariables ()
  {
    return CollectionHelper.newOrderedMap (m_aPathVariables);
  }

  /**
   * Check if all pre-requisites are handled correctly. This checks if all
   * required headers and params are present.
   *
   * @param aRequestScope
   *        The request scope to validate.
   * @return <code>true</code> if all prerequisites are fulfilled.
   */
  public boolean canExecute (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    if (aRequestScope == null)
      return false;

    // Note: HTTP method was already checked in APIDescriptorList

    // Check required headers
    if (m_aDescriptor.hasRequiredHeaders ())
      for (final String sRequiredHeader : m_aDescriptor.getAllRequiredHeaders ())
        if (aRequestScope.getRequestHeader (sRequiredHeader) == null)
        {
          s_aLogger.warn ("Request '" + m_sPath + "' is missing required HTTP header '" + sRequiredHeader + "'");
          return false;
        }

    // Check required parameters
    if (m_aDescriptor.hasRequiredParams ())
      for (final String sRequiredParam : m_aDescriptor.getAllRequiredParams ())
        if (!aRequestScope.containsAttribute (sRequiredParam))
        {
          s_aLogger.warn ("Request '" + m_sPath + "' is missing required HTTP parameter '" + sRequiredParam + "'");
          return false;
        }

    return true;
  }

  /**
   * Invoke the Java callback underlying this API descriptor. Note: this method
   * may only be invoked after
   * {@link #canExecute(IRequestWebScopeWithoutResponse)} returned
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
    final IAPIExecutor aExecutor = m_aDescriptor.getExecutorFactory ().create ();
    if (aExecutor == null)
      throw new IllegalStateException ("Failed to created API executor for: " + toString ());

    // Go go go
    aExecutor.invokeAPI (m_sPath, m_aPathVariables, aRequestScope, aUnifiedResponse);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Path", m_sPath)
                                       .append ("APIDescriptor", m_aDescriptor)
                                       .append ("PathVariables", m_aPathVariables)
                                       .toString ();
  }
}
