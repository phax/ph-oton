/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.requestparam;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.locale.GlobalLocaleManager;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL path in the fixed order locale than menu item. It
 * build URLs in the form <code>basePath/<i>locale</i>/<i>menuItem</i></code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLPathOrdered implements IRequestParameterHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (RequestParameterHandlerURLPathOrdered.class);

  /**
   * Default constructor
   */
  public RequestParameterHandlerURLPathOrdered ()
  {}

  @Nonnull
  protected PhotonRequestParameters getParametersFromPath (@Nonnull final String sPath, @Nonnull final IMenuTree aMenuTree)
  {
    // Use paths for standard menu items
    final PhotonRequestParameters ret = new PhotonRequestParameters ();

    for (final String sParamValue : StringHelper.getExploded ('/', StringHelper.trimStartAndEnd (sPath, "/")))
    {
      if (!ret.hasLocale ())
        if (ret.setLocaleFromString (GlobalLocaleManager.getInstance (), sParamValue) != null)
          continue;

      if (!ret.hasMenuItem ())
        if (ret.setMenuItemFromString (aMenuTree, sParamValue) != null)
          continue;

      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("Ignoring superfluous parameter '" + sParamValue + "'");
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
  public PhotonRequestParameters getParametersFromURL (@Nonnull final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
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
    if (aDisplayLocale != null)
      aFullPath.append ('/').append (aDisplayLocale.toString ());
    if (StringHelper.hasText (sMenuItemID))
      aFullPath.append ('/').append (sMenuItemID);
    final String sFullPath = aFullPath.toString ();

    return new SimpleURL (aRequestScope != null ? aRequestScope.encodeURL (sFullPath) : sFullPath);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).getToString ();
  }
}
