/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages.security;

import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.base.string.StringHelper;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.textlevel.HCCode;
import com.helger.photon.security.user.IUser;
import com.helger.photon.uicore.css.CUICoreCSS;

import jakarta.annotation.Nullable;

public final class SecurityUIHelper
{
  @PresentForCodeCoverage
  private static final SecurityUIHelper INSTANCE = new SecurityUIHelper ();

  private SecurityUIHelper ()
  {}

  /**
   * Check if a user can be edited or not. Currently all not deleted users can
   * be edited.
   *
   * @param aUser
   *        The user to check. May be <code>null</code>.
   * @return <code>true</code> if the user can be edited, <code>false</code> if
   *         not.
   */
  public static boolean canBeEdited (@Nullable final IUser aUser)
  {
    return aUser != null && !aUser.isDeleted ();
  }

  /**
   * Check if a user can be deleted or not. Currently all not deleted users can
   * be deleted except for the administrator special user.
   *
   * @param aUser
   *        The user to check. May be <code>null</code>.
   * @return <code>true</code> if the user can be deleted, <code>false</code> if
   *         not.
   */
  public static boolean canBeDeleted (@Nullable final IUser aUser)
  {
    return aUser != null && !aUser.isDeleted () && !aUser.isAdministrator ();
  }

  /**
   * Check if a user can be undeleted or not. Currently all deleted users can be
   * undeleted.
   *
   * @param aUser
   *        The user to check. May be <code>null</code>.
   * @return <code>true</code> if the user can be deleted, <code>false</code> if
   *         not.
   */
  public static boolean canBeUndeleted (@Nullable final IUser aUser)
  {
    return aUser != null && aUser.isDeleted ();
  }

  /**
   * Check if the password of a user can be reset or not. Currently the
   * passwords of all not deleted users can be reset.
   *
   * @param aUser
   *        The user to check. May be <code>null</code>.
   * @return <code>true</code> if the password can be reset, <code>false</code>
   *         if not.
   */
  public static boolean canResetPassword (@Nullable final IUser aUser)
  {
    return aUser != null && !aUser.isDeleted ();
  }

  @Nullable
  public static IHCNode createAccessTokenNode (@Nullable final String sTokenString)
  {
    if (StringHelper.isEmpty (sTokenString))
      return null;
    return new HCCode ().addClass (CUICoreCSS.CSS_CLASS_NOWRAP).addChild (sTokenString);
  }
}
