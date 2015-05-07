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
package com.helger.appbasics.security;

import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.security.role.IRole;
import com.helger.appbasics.security.role.IRoleManager;
import com.helger.appbasics.security.user.IUserManager;
import com.helger.appbasics.security.usergroup.IUserGroupManager;
import com.helger.commons.annotations.ReturnsMutableCopy;

public interface IAccessManager extends IUserManager, IUserGroupManager, IRoleManager
{
  /**
   * Check if the passed user ID has the passed role by checking all user group
   * role assignments of the user.
   * 
   * @param sUserID
   *        User ID to check
   * @param sRoleID
   *        Role ID to check
   * @return <code>true</code> if the user is in at least one user group that
   *         has the assigned role, <code>false</code> otherwise
   */
  boolean hasUserRole (@Nullable String sUserID, @Nullable String sRoleID);

  /**
   * Get all role IDs the current user has
   * 
   * @param sUserID
   *        User ID to check
   * @return Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <String> getAllUserRoleIDs (@Nullable String sUserID);

  /**
   * Get all roles the current user has
   * 
   * @param sUserID
   *        User ID to check
   * @return Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <IRole> getAllUserRoles (@Nullable String sUserID);
}
