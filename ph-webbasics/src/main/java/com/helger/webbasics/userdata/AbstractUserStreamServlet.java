/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webbasics.userdata;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.io.IReadableResource;
import com.helger.commons.url.URLUtils;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.webbasics.servlet.AbstractStreamServlet;

public abstract class AbstractUserStreamServlet extends AbstractStreamServlet
{
  protected AbstractUserStreamServlet ()
  {}

  /**
   * Get the user data object matching the passed request and filename
   *
   * @param aRequestScope
   *        HTTP request
   * @param sFilename
   *        Filename as extracted from the URL
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected UserDataObject getUserDataObject (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                              @Nonnull final String sFilename)
  {
    // URL decode is required because requests contain e.g. "%20"
    final String sFilename1 = URLUtils.urlDecode (sFilename);

    return new UserDataObject (sFilename1);
  }

  @Override
  @Nonnull
  protected IReadableResource getResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                           @Nonnull final String sFilename)
  {
    return UserDataManager.getResource (getUserDataObject (aRequestScope, sFilename));
  }
}
