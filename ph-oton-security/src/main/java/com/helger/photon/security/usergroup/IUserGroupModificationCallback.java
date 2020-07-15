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
package com.helger.photon.security.usergroup;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.callback.ICallback;

/**
 * Callback interface when a user group is created, modified, deleted or
 * assigned to.
 *
 * @author Philip Helger
 */
public interface IUserGroupModificationCallback extends ICallback
{
  /**
   * Called after a user group was created.
   *
   * @param aUserGroup
   *        The created user group. Never <code>null</code>.
   * @param bPredefinedUserGroup
   *        <code>true</code> if it is a predefined user group,
   *        <code>false</code> if it is a regular user group
   */
  default void onUserGroupCreated (@Nonnull final IUserGroup aUserGroup, final boolean bPredefinedUserGroup)
  {}

  /**
   * Called after a user group was edited fully.
   *
   * @param aUserGroup
   *        The modified user group. Never <code>null</code>.
   */
  default void onUserGroupUpdated (@Nonnull final IUserGroup aUserGroup)
  {}

  /**
   * Called after a user group was renamed.
   *
   * @param aUserGroup
   *        The modified user group. Never <code>null</code>.
   */
  default void onUserGroupRenamed (@Nonnull final IUserGroup aUserGroup)
  {}

  /**
   * Called after a user group was deleted.
   *
   * @param aUserGroup
   *        The deleted user group. Never <code>null</code>.
   */
  default void onUserGroupDeleted (@Nonnull final IUserGroup aUserGroup)
  {}

  /**
   * Called after a user group was undeleted.
   *
   * @param aUserGroup
   *        The undeleted user group. Never <code>null</code>.
   */
  default void onUserGroupUndeleted (@Nonnull final IUserGroup aUserGroup)
  {}

  /**
   * Called after a user was assigned/unassigned to/from a user group.
   *
   * @param aUserGroup
   *        The modified user group. Never <code>null</code>.
   * @param sUserID
   *        The ID of the user that was assigned/unassigned.
   * @param bAssign
   *        <code>true</code> if the user was assigned, <code>false</code> if it
   *        was unassigned
   */
  default void onUserGroupUserAssignment (@Nonnull final IUserGroup aUserGroup,
                                          @Nonnull @Nonempty final String sUserID,
                                          final boolean bAssign)
  {}

  /**
   * Called after a role was assigned/unassigned to/from a user group.
   *
   * @param aUserGroup
   *        The modified user group. Never <code>null</code>.
   * @param sRoleID
   *        The ID of the role that was assigned/unassigned.
   * @param bAssign
   *        <code>true</code> if the role was assigned, <code>false</code> if it
   *        was unassigned
   */
  default void onUserGroupRoleAssignment (@Nonnull final IUserGroup aUserGroup,
                                          @Nonnull @Nonempty final String sRoleID,
                                          final boolean bAssign)
  {}
}
