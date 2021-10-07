/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.locale.GlobalLocaleManager;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * An implementation of {@link IRequestParameterHandler} that takes the request
 * parameters from the URL parameters. It build URLs in the form
 * <code>basePath[[?&amp;]<i>paramName</i>=<i>paramValue</i>]*</code>
 *
 * @author Philip Helger
 * @since 7.0.2
 */
@Immutable
public class RequestParameterHandlerURLParameter extends AbstractRequestParameterHandlerNamed
{
  public RequestParameterHandlerURLParameter ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public PhotonRequestParameters getParametersFromRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                                           @Nonnull final IMenuTree aMenuTree)
  {
    final PhotonRequestParameters ret = new PhotonRequestParameters ();
    ret.setLocaleFromString (GlobalLocaleManager.getInstance (), aRequestScope.params ().getAsString (getRequestParamNameLocale ()));
    ret.setMenuItemFromString (aMenuTree, aRequestScope.params ().getAsString (getRequestParamNameMenuItem ()));
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public PhotonRequestParameters getParametersFromURL (@Nonnull final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
  {
    final PhotonRequestParameters ret = new PhotonRequestParameters ();
    ret.setLocaleFromString (GlobalLocaleManager.getInstance (), aURL.params ().getFirstParamValue (getRequestParamNameLocale ()));
    ret.setMenuItemFromString (aMenuTree, aURL.params ().getFirstParamValue (getRequestParamNameMenuItem ()));
    return ret;
  }

  @Nonnull
  public SimpleURL buildURL (@Nullable final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull @Nonempty final String sBasePath,
                             @Nullable final Locale aDisplayLocale,
                             @Nullable final String sMenuItemID)
  {
    // Add menu item parameter as URL parameter
    final SimpleURL ret = new SimpleURL (aRequestScope != null ? aRequestScope.encodeURL (sBasePath) : sBasePath);
    if (aDisplayLocale != null)
      ret.add (getRequestParamNameLocale (), aDisplayLocale.toString ());
    if (StringHelper.hasText (sMenuItemID))
      ret.add (getRequestParamNameMenuItem (), sMenuItemID);
    return ret;
  }

  @Override
  public boolean isValidParameterName (@Nonnull final String sParamName)
  {
    // Question mark (separator between path and params) may not be part
    // Ampersand may not be part of the name
    return sParamName.indexOf ('?') < 0 && sParamName.indexOf ('&') < 0;
  }
}
