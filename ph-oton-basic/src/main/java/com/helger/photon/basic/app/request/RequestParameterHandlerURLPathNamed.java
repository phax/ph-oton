/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.request;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.locale.GlobalLocaleManager;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL path. It build URLs in the form
 * <code>basePath[/<i>paramName</i><b>separator</b><i>paramValue</i>]*</code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLPathNamed extends AbstractRequestParameterHandlerNamed
{
  /**
   * The separator char to be used if path based handling is enabled, to
   * separate name and value
   */
  public static final char DEFAULT_SEPARATOR_CHAR = '-';

  private static final Logger s_aLogger = LoggerFactory.getLogger (RequestParameterHandlerURLPathNamed.class);

  private final String m_sSeparator;

  /**
   * Default constructor using {@link #DEFAULT_SEPARATOR_CHAR} as the separator.
   */
  public RequestParameterHandlerURLPathNamed ()
  {
    this (Character.toString (DEFAULT_SEPARATOR_CHAR));
  }

  /**
   * Constructor with a custom separator.
   *
   * @param sSeparator
   *        The separator to use. May neither be <code>null</code> nor empty.
   *        May not contain the "/" character!
   */
  public RequestParameterHandlerURLPathNamed (@Nonnull @Nonempty final String sSeparator)
  {
    ValueEnforcer.notEmpty (sSeparator, "Separator");
    ValueEnforcer.isTrue (sSeparator.indexOf ('/') < 0, "The separator may not contain a path delimiter '/'!");
    m_sSeparator = sSeparator;
  }

  /**
   * @return The separator as passed in the constructor. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getSeparator ()
  {
    return m_sSeparator;
  }

  @Nonnull
  protected PhotonRequestParameters getParametersFromPath (@Nonnull final String sPath,
                                                           @Nonnull final IMenuTree aMenuTree)
  {
    // Use paths for standard menu items
    final PhotonRequestParameters ret = new PhotonRequestParameters ();
    for (final String sPair : StringHelper.getExploded ('/', StringHelper.trimStartAndEnd (sPath, "/")))
    {
      final ICommonsList <String> aElements = StringHelper.getExploded (m_sSeparator, sPair, 2);
      if (aElements.size () == 2)
      {
        final String sParamName = aElements.get (0);
        final String sParamValue = aElements.get (1);
        if (sParamName.equals (getRequestParamNameLocale ()))
          ret.setLocaleFromString (GlobalLocaleManager.getInstance (), sParamValue);
        else
          if (sParamName.equals (getRequestParamNameMenuItem ()))
            ret.setMenuItemFromString (aMenuTree, sParamValue);
          else
            if (s_aLogger.isDebugEnabled ())
              s_aLogger.debug ("Ignoring superfluous parameter '" + sParamName + "' with value '" + sParamValue + "'");
      }
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public PhotonRequestParameters getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                           @Nonnull final IMenuTree aMenuTree)
  {
    return getParametersFromPath (aRequestScope.getPathInfo (), aMenuTree);
  }

  @Nonnull
  @ReturnsMutableCopy
  public PhotonRequestParameters getParametersFromURL (@Nonnull final ISimpleURL aURL,
                                                       @Nonnull final IMenuTree aMenuTree)
  {
    return getParametersFromPath (aURL.getPath (), aMenuTree);
  }

  @Nonnull
  public SimpleURL buildURL (@Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sBasePath,
                             @Nullable final Locale aDisplayLocale,
                             @Nullable final String sMenuItemID)
  {
    final StringBuilder aFullPath = new StringBuilder (sBasePath);

    // Encode into path
    if (aDisplayLocale != null)
      aFullPath.append ('/')
               .append (getRequestParamNameLocale ())
               .append (m_sSeparator)
               .append (aDisplayLocale.toString ());
    if (StringHelper.hasText (sMenuItemID))
      aFullPath.append ('/').append (getRequestParamNameMenuItem ()).append (m_sSeparator).append (sMenuItemID);

    final String sFullPath = aFullPath.toString ();
    return new SimpleURL (aRequestScope != null ? aRequestScope.encodeURL (sFullPath) : sFullPath);
  }

  @Override
  public boolean isValidParameterName (@Nonnull final String sParamName)
  {
    // Separator may not be part of the parameter name
    // Path separator may not be part of the parameter name
    // Question mark (separator between path and params) may not be part
    return sParamName.indexOf (m_sSeparator) < 0 && sParamName.indexOf ('/') < 0 && sParamName.indexOf ('?') < 0;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Separator", m_sSeparator).getToString ();
  }
}
