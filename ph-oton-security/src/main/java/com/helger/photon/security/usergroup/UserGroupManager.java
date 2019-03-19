/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.dao.DAOException;
import com.helger.photon.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.user.UserManager;

/**
 * This class manages the available user groups.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserGroupManager extends AbstractPhotonMapBasedWALDAO <IUserGroup, UserGroup>
{
  private final UserManager m_aUserMgr;
  private final RoleManager m_aRoleMgr;

  private final CallbackList <IUserGroupModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserGroupManager (@Nonnull @Nonempty final String sFilename,
                           @Nonnull final UserManager aUserMgr,
                           @Nonnull final RoleManager aRoleMgr) throws DAOException
  {
    super (UserGroup.class, sFilename);
    m_aUserMgr = ValueEnforcer.notNull (aUserMgr, "UserManager");
    m_aRoleMgr = ValueEnforcer.notNull (aRoleMgr, "RoleManager");
  }

  @Nonnull
  public final UserManager getUserManager ()
  {
    return m_aUserMgr;
  }

  @Nonnull
  public final RoleManager getRoleManager ()
  {
    return m_aRoleMgr;
  }

  public void createDefaults ()
  {
    // Administrators user group
    UserGroup aUG = getOfID (CSecurity.USERGROUP_ADMINISTRATORS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLocked ( () -> internalCreateItem (new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_ADMINISTRATORS_ID),
                                                                             CSecurity.USERGROUP_ADMINISTRATORS_NAME,
                                                                             (String) null)));
    if (m_aUserMgr.containsWithID (CSecurity.USER_ADMINISTRATOR_ID))
      aUG.assignUser (CSecurity.USER_ADMINISTRATOR_ID);
    if (m_aRoleMgr.containsWithID (CSecurity.ROLE_ADMINISTRATOR_ID))
      aUG.assignRole (CSecurity.ROLE_ADMINISTRATOR_ID);

    // Users user group
    aUG = getOfID (CSecurity.USERGROUP_USERS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLocked ( () -> internalCreateItem (new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_USERS_ID),
                                                                             CSecurity.USERGROUP_USERS_NAME,
                                                                             (String) null)));
    if (m_aUserMgr.containsWithID (CSecurity.USER_USER_ID))
      aUG.assignUser (CSecurity.USER_USER_ID);
    if (m_aRoleMgr.containsWithID (CSecurity.ROLE_USER_ID))
      aUG.assignRole (CSecurity.ROLE_USER_ID);

    // Guests user group
    aUG = getOfID (CSecurity.USERGROUP_GUESTS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLocked ( () -> internalCreateItem (new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_GUESTS_ID),
                                                                             CSecurity.USERGROUP_GUESTS_NAME,
                                                                             (String) null)));
    if (m_aUserMgr.containsWithID (CSecurity.USER_GUEST_ID))
      aUG.assignUser (CSecurity.USER_GUEST_ID);
    // no role for this user group
  }

  /**
   * @return The user group callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IUserGroupModificationCallback> userGroupModificationCallbacks ()
  {
    return m_aCallbacks;
  }

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
  public IUserGroup createNewUserGroup (@Nonnull @Nonempty final String sName,
                                        @Nullable final String sDescription,
                                        @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create user group
    final UserGroup aUserGroup = new UserGroup (sName, sDescription, aCustomAttrs);

    m_aRWLock.writeLocked ( () -> {
      // Store
      internalCreateItem (aUserGroup);
    });
    AuditHelper.onAuditCreateSuccess (UserGroup.OT, aUserGroup.getID (), sName, sDescription, aCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupCreated (aUserGroup, false));

    return aUserGroup;

  }

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
  public IUserGroup createPredefinedUserGroup (@Nonnull @Nonempty final String sID,
                                               @Nonnull @Nonempty final String sName,
                                               @Nullable final String sDescription,
                                               @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create user group
    final UserGroup aUserGroup = new UserGroup (StubObject.createForCurrentUserAndID (sID, aCustomAttrs),
                                                sName,
                                                sDescription);

    m_aRWLock.writeLocked ( () -> {
      // Store
      internalCreateItem (aUserGroup);
    });
    AuditHelper.onAuditCreateSuccess (UserGroup.OT,
                                      aUserGroup.getID (),
                                      "predefined-usergroup",
                                      sName,
                                      sDescription,
                                      aCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupCreated (aUserGroup, true));

    return aUserGroup;
  }

  /**
   * Delete the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group to be deleted. May be <code>null</code>.
   * @return {@link EChange#CHANGED} if the user group was deleted,
   *         {@link EChange#UNCHANGED} otherwise
   */
  @Nonnull
  public EChange deleteUserGroup (@Nullable final String sUserGroupID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;

    final UserGroup aDeletedUserGroup = getOfID (sUserGroupID);
    if (aDeletedUserGroup == null)
    {
      AuditHelper.onAuditDeleteFailure (UserGroup.OT, "no-such-usergroup-id", sUserGroupID);
      return EChange.UNCHANGED;
    }
    if (aDeletedUserGroup.isDeleted ())
    {
      AuditHelper.onAuditDeleteFailure (UserGroup.OT, "already-deleted", sUserGroupID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aDeletedUserGroup).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (UserGroup.OT, "already-deleted", sUserGroupID);
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aDeletedUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (UserGroup.OT, sUserGroupID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupDeleted (aDeletedUserGroup));

    return EChange.CHANGED;
  }

  /**
   * Undelete the user group with the specified ID.
   *
   * @param sUserGroupID
   *        The ID of the user group to undelete
   * @return {@link EChange#CHANGED} if the user group was undeleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public EChange undeleteUserGroup (@Nullable final String sUserGroupID)
  {
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditUndeleteFailure (UserGroup.OT, sUserGroupID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setUndeletionNow (aUserGroup).isUnchanged ())
        return EChange.UNCHANGED;
      internalMarkItemUndeleted (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditUndeleteSuccess (UserGroup.OT, sUserGroupID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUndeleted (aUserGroup));

    return EChange.CHANGED;
  }

  /**
   * Get the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group to search
   * @return <code>null</code> if no such user group exists
   */
  @Nullable
  public IUserGroup getUserGroupOfID (@Nullable final String sUserGroupID)
  {
    return getOfID (sUserGroupID);
  }

  /**
   * @return A non-<code>null</code> list of all available active (=not deleted)
   *         user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllActiveUserGroups ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  /**
   * @return A non-<code>null</code> list of all deleted user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllDeletedUserGroups ()
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
  public EChange renameUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sNewName)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id", "name");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.setName (sNewName).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "name", sUserGroupID, sNewName);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRenamed (aUserGroup));

    return EChange.CHANGED;
  }

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
  public EChange setUserGroupData (@Nullable final String sUserGroupID,
                                   @Nonnull @Nonempty final String sNewName,
                                   @Nullable final String sNewDescription,
                                   @Nullable final Map <String, String> aNewCustomAttrs)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = aUserGroup.setName (sNewName);
      eChange = eChange.or (aUserGroup.setDescription (sNewDescription));
      eChange = eChange.or (aUserGroup.attrs ().setAll (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT,
                                      "all",
                                      aUserGroup.getID (),
                                      sNewName,
                                      sNewDescription,
                                      aNewCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUpdated (aUserGroup));

    return EChange.CHANGED;
  }

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
  public EChange assignUserToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sUserID)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id", "assign-user");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.assignUser (sUserID).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (aUserGroup, sUserID, true));

    return EChange.CHANGED;
  }

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
  public EChange unassignUserFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id", "unassign-user");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.unassignUser (sUserID).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (aUserGroup, sUserID, false));

    return EChange.CHANGED;
  }

  /**
   * Unassign the passed user ID from all user groups.
   *
   * @param sUserID
   *        ID of the user to be unassigned.
   * @return {@link EChange#CHANGED} if the passed user ID was at least assigned
   *         to one user group.
   */
  @Nonnull
  public EChange unassignUserFromAllUserGroups (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return EChange.UNCHANGED;

    final ICommonsList <IUserGroup> aAffectedUserGroups = new CommonsArrayList <> ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      for (final UserGroup aUserGroup : internalDirectGetAll ())
        if (aUserGroup.unassignUser (sUserID).isChanged ())
        {
          aAffectedUserGroups.add (aUserGroup);
          BusinessObjectHelper.setLastModificationNow (aUserGroup);
          internalUpdateItem (aUserGroup);
          eChange = EChange.CHANGED;
        }
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-user-from-all-usergroups", sUserID);

    // Execute callback as the very last action
    for (final IUserGroup aUserGroup : aAffectedUserGroups)
      m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (aUserGroup, sUserID, false));

    return EChange.CHANGED;
  }

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
  public boolean isUserAssignedToUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;

    final IUserGroup aUserGroup = getOfID (sUserGroupID);
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
  public ICommonsList <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return new CommonsArrayList <> ();

    return getAll (aUserGroup -> aUserGroup.containsUserID (sUserID));
  }

  public boolean containsAnyUserGroupWithAssignedUserAndRole (@Nullable final String sUserID,
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
  public ICommonsList <String> getAllUserGroupIDsWithAssignedUser (@Nullable final String sUserID)
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
  public EChange assignRoleToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sRoleID)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id", "assign-role");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.assignRole (sRoleID).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (aUserGroup, sRoleID, true));

    return EChange.CHANGED;
  }

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
  public EChange unassignRoleFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sRoleID)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, sUserGroupID, "no-such-usergroup-id", "unassign-role");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.unassignRole (sRoleID).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (aUserGroup, sRoleID, false));

    return EChange.CHANGED;
  }

  /**
   * Unassign the passed role ID from existing user groups.
   *
   * @param sRoleID
   *        The role ID to be unassigned
   * @return {@link EChange#CHANGED} if the passed role ID was contained in at
   *         least one user group
   */
  @Nonnull
  public EChange unassignRoleFromAllUserGroups (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return EChange.UNCHANGED;

    final ICommonsList <IUserGroup> aAffectedUserGroups = new CommonsArrayList <> ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      for (final UserGroup aUserGroup : internalDirectGetAll ())
        if (aUserGroup.unassignRole (sRoleID).isChanged ())
        {
          aAffectedUserGroups.add (aUserGroup);
          BusinessObjectHelper.setLastModificationNow (aUserGroup);
          internalUpdateItem (aUserGroup);
          eChange = EChange.CHANGED;
        }
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-role-from-all-usergroups", sRoleID);

    // Execute callback as the very last action
    for (final IUserGroup aUserGroup : aAffectedUserGroups)
      m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (aUserGroup, sRoleID, false));

    return EChange.CHANGED;
  }

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
  public ICommonsList <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable final String sRoleID)
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
  public ICommonsList <String> getAllUserGroupIDsWithAssignedRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return getNone ();

    return getAllMapped (aUserGroup -> aUserGroup.containsRoleID (sRoleID), IUserGroup::getID);
  }

  public boolean containsUserGroupWithAssignedRole (@Nullable final String sRoleID)
  {
    return containsAny (aUserGroup -> aUserGroup.containsRoleID (sRoleID));
  }
}
