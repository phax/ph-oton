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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.app.dao.IPhotonDAO;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.user.IUserManager;

/**
 * Base interface to manages the available user groups.
 *
 * @author Philip Helger
 * @since 8.2.4
 */
@ThreadSafe
public interface IUserGroupManager extends IPhotonDAO <IUserGroup>
{
  @Nonnull
  IUserManager getUserManager ();

  @Nonnull
  IRoleManager getRoleManager ();

  void createDefaults ();

  /**
   * @return The user group callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  CallbackList <IUserGroupModificationCallback> userGroupModificationCallbacks ();

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
                                 @Nullable Map <String, String> aCustomAttrs);

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
                                        @Nullable Map <String, String> aCustomAttrs);

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
   * Undelete the user group with the specified ID.
   *
   * @param sUserGroupID
   *        The ID of the user group to undelete
   * @return {@link EChange#CHANGED} if the user group was undeleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  EChange undeleteUserGroup (@Nullable String sUserGroupID);

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
   * @return A non-<code>null</code> list of all available active (=not deleted)
   *         user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  default ICommonsList <IUserGroup> getAllActiveUserGroups ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  /**
   * @return A non-<code>null</code> list of all deleted user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  default ICommonsList <IUserGroup> getAllDeletedUserGroups ()
  {
    return getAll (IUserGroup::isDeleted);
  }

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
                            @Nullable Map <String, String> aNewCustomAttrs);

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
  default boolean isUserAssignedToUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;

    final IUserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
    return aUserGroup == null ? false : aUserGroup.containsUserID (sUserID);
  }

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
  default ICommonsList <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return new CommonsArrayList <> ();

    return getAll (aUserGroup -> aUserGroup.containsUserID (sUserID));
  }

  default boolean containsAnyUserGroupWithAssignedUserAndRole (@Nullable final String sUserID,
                                                               @Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;
    if (StringHelper.hasNoText (sRoleID))
      return false;

    return containsAny (aUserGroup -> aUserGroup.containsUserID (sUserID) && aUserGroup.containsRoleID (sRoleID));
  }

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
  default ICommonsList <String> getAllUserGroupIDsWithAssignedUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return new CommonsArrayList <> ();

    return getAllMapped (aUserGroup -> aUserGroup.containsUserID (sUserID), aUserGroup -> aUserGroup.getID ());
  }

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
  default ICommonsList <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return getNone ();

    return getAll (aUserGroup -> aUserGroup.containsRoleID (sRoleID));
  }

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
  default ICommonsList <String> getAllUserGroupIDsWithAssignedRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return getNone ();

    return getAllMapped (aUserGroup -> aUserGroup.containsRoleID (sRoleID), IUserGroup::getID);
  }

  default boolean containsUserGroupWithAssignedRole (@Nullable final String sRoleID)
  {
    return containsAny (aUserGroup -> aUserGroup.containsRoleID (sRoleID));
  }
}