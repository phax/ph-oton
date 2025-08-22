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
package com.helger.photon.security.usergroup;

import com.helger.annotation.Nonempty;
import com.helger.base.callback.ICallback;

import jakarta.annotation.Nonnull;

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
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   */
  default void onUserGroupUpdated (@Nonnull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was renamed.
   *
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   */
  default void onUserGroupRenamed (@Nonnull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was deleted.
   *
   * @param sUserGroupID
   *        The deleted user group ID. Never <code>null</code>.
   */
  default void onUserGroupDeleted (@Nonnull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was undeleted.
   *
   * @param sUserGroupID
   *        The undeleted user group ID. Never <code>null</code>.
   */
  default void onUserGroupUndeleted (@Nonnull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user was assigned/unassigned to/from a user group.
   *
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   * @param sUserID
   *        The ID of the user that was assigned/unassigned.
   * @param bAssign
   *        <code>true</code> if the user was assigned, <code>false</code> if it
   *        was unassigned
   */
  default void onUserGroupUserAssignment (@Nonnull @Nonempty final String sUserGroupID,
                                          @Nonnull @Nonempty final String sUserID,
                                          final boolean bAssign)
  {}

  /**
   * Called after a role was assigned/unassigned to/from a user group.
   *
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   * @param sRoleID
   *        The ID of the role that was assigned/unassigned.
   * @param bAssign
   *        <code>true</code> if the role was assigned, <code>false</code> if it
   *        was unassigned
   */
  default void onUserGroupRoleAssignment (@Nonnull @Nonempty final String sUserGroupID,
                                          @Nonnull @Nonempty final String sRoleID,
                                          final boolean bAssign)
  {}
}
