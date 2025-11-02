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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.base.callback.ICallback;

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
  default void onUserGroupCreated (@NonNull final IUserGroup aUserGroup, final boolean bPredefinedUserGroup)
  {}

  /**
   * Called after a user group was edited fully.
   *
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   */
  default void onUserGroupUpdated (@NonNull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was renamed.
   *
   * @param sUserGroupID
   *        The modified user group ID. Never <code>null</code>.
   */
  default void onUserGroupRenamed (@NonNull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was deleted.
   *
   * @param sUserGroupID
   *        The deleted user group ID. Never <code>null</code>.
   */
  default void onUserGroupDeleted (@NonNull @Nonempty final String sUserGroupID)
  {}

  /**
   * Called after a user group was undeleted.
   *
   * @param sUserGroupID
   *        The undeleted user group ID. Never <code>null</code>.
   */
  default void onUserGroupUndeleted (@NonNull @Nonempty final String sUserGroupID)
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
  default void onUserGroupUserAssignment (@NonNull @Nonempty final String sUserGroupID,
                                          @NonNull @Nonempty final String sUserID,
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
  default void onUserGroupRoleAssignment (@NonNull @Nonempty final String sUserGroupID,
                                          @NonNull @Nonempty final String sRoleID,
                                          final boolean bAssign)
  {}
}
