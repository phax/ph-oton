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
package com.helger.photon.core.requestparam;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.core.appid.RequestSettings;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Predefined request parameter manager
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public interface IRequestParameterManager
{
  @Nonnull
  IRequestParameterHandler getParameterHandler ();

  void setParameterHandler (@Nonnull IRequestParameterHandler aRequestParameterHdl);

  @Nonnull
  default SimpleURL getLinkToMenuItem (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Locale aDisplayLocale,
                                       @Nonnull final String sMenuItemID)
  {
    return getLinkToMenuItem (RequestSettings.getApplicationID (aRequestScope),
                              aRequestScope,
                              aDisplayLocale,
                              sMenuItemID);
  }

  @Nonnull
  SimpleURL getLinkToMenuItem (@Nonnull @Nonempty String sAppID,
                               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                               @Nonnull Locale aDisplayLocale,
                               @Nonnull String sMenuItemID);

  @Nullable
  default String getMenuItemFromURL (@Nullable final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
  {
    if (aURL == null)
      return null;
    return getParameterHandler ().getParametersFromURL (aURL, aMenuTree).getMenuItemAsString ();
  }

  @Nullable
  default String getLocaleFromURL (@Nullable final ISimpleURL aURL, @Nonnull final IMenuTree aMenuTree)
  {
    if (aURL == null)
      return null;

    return getParameterHandler ().getParametersFromURL (aURL, aMenuTree).getLocaleAsString ();
  }
}
