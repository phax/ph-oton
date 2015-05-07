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
package com.helger.photon.basic.app.menu.filter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.security.login.LoggedInUserManager;

/**
 * This filter matches any menu item if no user is logged in.
 * 
 * @author Philip Helger
 */
public final class MenuItemFilterNotLoggedIn extends AbstractMenuObjectFilter
{
  private static final MenuItemFilterNotLoggedIn s_aInstance = new MenuItemFilterNotLoggedIn ();

  private MenuItemFilterNotLoggedIn ()
  {}

  @Nonnull
  public static MenuItemFilterNotLoggedIn getInstance ()
  {
    return s_aInstance;
  }

  public boolean matchesFilter (@Nullable final IMenuObject aValue)
  {
    return !LoggedInUserManager.getInstance ().isUserLoggedInInCurrentSession ();
  }
}
