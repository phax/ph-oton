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
package com.helger.photon.basic.security.usergroup;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.security.usergroup.callback.IUserGroupModificationCallback;

/**
 * Interface for a user group manager
 *
 * @author Philip Helger
 */
public interface IUserGroupManager
{
  /**
   * @return The user group callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IUserGroupModificationCallback> getUserGroupModificationCallbacks ();

  /**
   * Create a new user group.
   *
   * @param sName
   *        The name of the user group to create. May neither be
   *        <code>null</code> nor empty.
   * @param sDescription
   *        The optional description of the user group. May be <code>null</code>
   *        .
   * @param aCustomAttrs
   *        A set of custom attributes. May be <code>null</code>.
   * @return The created user group.
   */
  @Nonnull
  IUserGroup createNewUserGroup (@Nonnull @Nonempty String sName,
                                 @Nullable String sDescription,
                                 @Nullable Map <String, ?> aCustomAttrs);

  /**
   * Create a predefined user group.
   *
   * @param sID
   *        The ID to use
   * @param sName
   *        The name of the user group to create. May neither be
   *        <code>null</code> nor empty.
   * @param sDescription
   *        The optional description of the user group. May be <code>null</code>
   *        .
   * @param aCustomAttrs
   *        A set of custom attributes. May be <code>null</code>.
   * @return The created user group.
   */
  @Nonnull
  IUserGroup createPredefinedUserGroup (@Nonnull @Nonempty String sID,
                                        @Nonnull @Nonempty String sName,
                                        @Nullable String sDescription,
                                        @Nullable Map <String, ?> aCustomAttrs);

  /**
   * Delete the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group to be deleted. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the user group was deleted,
   *         {@link EChange#UNCHANGED} otherwise
   */
  @Nonnull
  EChange deleteUserGroup (@Nullable String sUserGroupID);

  /**
   * Check if a user group with the specified ID is contained
   *
   * @param sUserGroupID
   *        The ID of the user group to check
   * @return <code>true</code> if no user group exists, <code>false</code>
   *         otherwise.
   */
  boolean containsUserGroupWithID (@Nullable String sUserGroupID);

  /**
   * Check if all passed user group IDs are contained
   *
   * @param aUserGroupIDs
   *        The user group IDs to be checked. May be <code>null</code>.
   * @return <code>true</code> if the collection is empty or if all contained
   *         user group IDs are contained
   */
  boolean containsAllUserGroupsWithID (@Nullable Collection <String> aUserGroupIDs);

  /**
   * Get the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group to search
   * @return <code>null</code> if no such user group exists
   */
  @Nullable
  IUserGroup getUserGroupOfID (@Nullable String sUserGroupID);

  /**
   * @return A non-<code>null</code> list of all user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  Collection <? extends IUserGroup> getAllUserGroups ();

  /**
   * Rename the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group. May be <code>null</code>.
   * @param sNewName
   *        The new name of the user group. May neither be <code>null</code> nor
   *        empty.
   * @return {@link EChange#CHANGED} if the user group ID was valid, and the new
   *         name was different from the old name
   */
  @Nonnull
  EChange renameUserGroup (@Nullable String sUserGroupID, @Nonnull @Nonempty String sNewName);

  /**
   * Change the modifiable data of a user group
   *
   * @param sUserGroupID
   *        The ID of the user group. May be <code>null</code>.
   * @param sNewName
   *        The new name of the user group. May neither be <code>null</code> nor
   *        empty.
   * @param sNewDescription
   *        The optional new description of the user group. May be
   *        <code>null</code> .
   * @param aNewCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange setUserGroupData (@Nullable String sUserGroupID,
                            @Nonnull @Nonempty String sNewName,
                            @Nullable String sNewDescription,
                            @Nullable Map <String, ?> aNewCustomAttrs);

  /**
   * Assign the passed user ID to the passed user group.<br>
   * Note: no validity check must be performed for the user ID
   *
   * @param sUserGroupID
   *        ID of the user group to assign to
   * @param sUserID
   *        ID of the user to be assigned.
   * @return {@link EChange#CHANGED} if the user group ID was valid, and the
   *         specified user ID was not already contained.
   */
  @Nonnull
  EChange assignUserToUserGroup (@Nullable String sUserGroupID, @Nonnull @Nonempty String sUserID);

  /**
   * Unassign the specified user ID from the passed user group ID.
   *
   * @param sUserGroupID
   *        The ID of the user group to unassign the user ID from
   * @param sUserID
   *        The user ID to be unassigned
   * @return {@link EChange#CHANGED} if the user group ID was resolved, and the
   *         passed user ID was assigned to the user group
   */
  @Nonnull
  EChange unassignUserFromUserGroup (@Nullable String sUserGroupID, @Nullable String sUserID);

  /**
   * Unassign the passed user ID from all user groups.
   *
   * @param sUserID
   *        ID of the user to be unassigned.
   * @return {@link EChange#CHANGED} if the passed user ID was at least assigned
   *         to one user group.
   */
  @Nonnull
  EChange unassignUserFromAllUserGroups (@Nullable String sUserID);

  /**
   * Check if the passed user is assigned to the specified user group
   *
   * @param sUserGroupID
   *        ID of the user group to check
   * @param sUserID
   *        ID of the user to be checked.
   * @return <code>true</code> if the specified user is assigned to the
   *         specified user group.
   */
  boolean isUserAssignedToUserGroup (@Nullable String sUserGroupID, @Nullable String sUserID);

  /**
   * Get a collection of all user groups to which a certain user is assigned to.
   *
   * @param sUserID
   *        The user ID to search
   * @return A non-<code>null</code>but may be empty collection with all
   *         matching user groups.
   */
  @Nonnull
  @ReturnsMutableCopy
  Collection <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable String sUserID);

  /**
   * Get a collection of all user group IDs to which a certain user is assigned
   * to.
   *
   * @param sUserID
   *        The user ID to search
   * @return A non-<code>null</code>but may be empty collection with all
   *         matching user group IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  Collection <String> getAllUserGroupIDsWithAssignedUser (@Nullable String sUserID);

  /**
   * Assign the passed role ID to the user group with the passed ID.<br>
   * Note: the role ID must not be checked for consistency
   *
   * @param sUserGroupID
   *        The ID of the user group to assign the role to.
   * @param sRoleID
   *        The ID of the role to be assigned
   * @return {@link EChange#CHANGED} if the passed user group ID was resolved,
   *         and the role ID was not already previously contained
   */
  @Nonnull
  EChange assignRoleToUserGroup (@Nullable String sUserGroupID, @Nonnull @Nonempty String sRoleID);

  /**
   * Unassign the passed role ID from the passed user group ID
   *
   * @param sUserGroupID
   *        The ID of the user group to unassign the role ID from
   * @param sRoleID
   *        The role ID to be unassigned.
   * @return {@link EChange#CHANGED} if the user group ID was resolved and the
   *         passed role ID was contained
   */
  @Nonnull
  EChange unassignRoleFromUserGroup (@Nullable String sUserGroupID, @Nullable String sRoleID);

  /**
   * Unassign the passed role ID from existing user groups.
   *
   * @param sRoleID
   *        The role ID to be unassigned
   * @return {@link EChange#CHANGED} if the passed role ID was contained in at
   *         least one user group
   */
  @Nonnull
  EChange unassignRoleFromAllUserGroups (@Nullable String sRoleID);

  /**
   * Get a collection of all user groups to which a certain role is assigned to.
   *
   * @param sRoleID
   *        The role ID to search
   * @return A non-<code>null</code>but may be empty collection with all
   *         matching user groups.
   */
  @Nonnull
  @ReturnsMutableCopy
  Collection <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable String sRoleID);

  /**
   * Get a collection of all user group IDs to which a certain role is assigned
   * to.
   *
   * @param sRoleID
   *        The role ID to search
   * @return A non-<code>null</code>but may be empty collection with all
   *         matching user group IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  Collection <String> getAllUserGroupIDsWithAssignedRole (@Nullable String sRoleID);
}
