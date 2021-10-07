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
package com.helger.photon.core.menu.filter;

import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.security.login.LoggedInUserManager;

/**
 * This filter matches any menu item if a user is logged in.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuObjectFilterUserLoggedIn extends AbstractMenuObjectFilter
{
  public MenuObjectFilterUserLoggedIn ()
  {
    setDescription (EPhotonCoreText.MENU_OBJECT_FILTER_USER_LOGGED_IN.getMultilingualText ());
  }

  public boolean test (@Nullable final IMenuObject aValue)
  {
    return LoggedInUserManager.getInstance ().isUserLoggedInInCurrentSession ();
  }
}
