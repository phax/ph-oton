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
package com.helger.webctrls.page.security;

import javax.annotation.Nullable;

import com.helger.appbasics.security.user.IUser;
import com.helger.commons.annotations.PresentForCodeCoverage;

public final class SecurityUIHelper
{
  @PresentForCodeCoverage
  private static final SecurityUIHelper s_aInstance = new SecurityUIHelper ();

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
}
