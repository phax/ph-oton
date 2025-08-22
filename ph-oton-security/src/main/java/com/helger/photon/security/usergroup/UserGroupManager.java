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

import java.util.Map;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.callback.CallbackList;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.dao.DAOException;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.io.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.user.IUserManager;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This class manages the available user groups.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserGroupManager extends AbstractPhotonMapBasedWALDAO <IUserGroup, UserGroup> implements IUserGroupManager
{
  private final IUserManager m_aUserMgr;
  private final IRoleManager m_aRoleMgr;

  private final CallbackList <IUserGroupModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserGroupManager (@Nonnull @Nonempty final String sFilename,
                           @Nonnull final IUserManager aUserMgr,
                           @Nonnull final IRoleManager aRoleMgr) throws DAOException
  {
    super (UserGroup.class, sFilename);
    m_aUserMgr = ValueEnforcer.notNull (aUserMgr, "UserManager");
    m_aRoleMgr = ValueEnforcer.notNull (aRoleMgr, "RoleManager");
  }

  @Nonnull
  public final IUserManager getUserManager ()
  {
    return m_aUserMgr;
  }

  @Nonnull
  public final IRoleManager getRoleManager ()
  {
    return m_aRoleMgr;
  }

  @Nonnull
  public static UserGroup createDefaultUserGroupAdministrators ()
  {
    return new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_ADMINISTRATORS_ID),
                          CSecurity.USERGROUP_ADMINISTRATORS_NAME,
                          (String) null);
  }

  @Nonnull
  public static UserGroup createDefaultUserGroupUsers ()
  {
    return new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_USERS_ID),
                          CSecurity.USERGROUP_USERS_NAME,
                          (String) null);
  }

  @Nonnull
  public static UserGroup createDefaultUserGroupGuests ()
  {
    return new UserGroup (StubObject.createForCurrentUserAndID (CSecurity.USERGROUP_GUESTS_ID),
                          CSecurity.USERGROUP_GUESTS_NAME,
                          (String) null);
  }

  public void createDefaultsForTest ()
  {
    // Administrators user group
    UserGroup aUG = getOfID (CSecurity.USERGROUP_ADMINISTRATORS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLockedGet ( () -> internalCreateItem (createDefaultUserGroupAdministrators ()));
    if (m_aUserMgr.containsWithID (CSecurity.USER_ADMINISTRATOR_ID))
      aUG.assignUser (CSecurity.USER_ADMINISTRATOR_ID);
    if (m_aRoleMgr.containsWithID (CSecurity.ROLE_ADMINISTRATOR_ID))
      aUG.assignRole (CSecurity.ROLE_ADMINISTRATOR_ID);

    // Users user group
    aUG = getOfID (CSecurity.USERGROUP_USERS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLockedGet ( () -> internalCreateItem (createDefaultUserGroupUsers ()));
    if (m_aUserMgr.containsWithID (CSecurity.USER_USER_ID))
      aUG.assignUser (CSecurity.USER_USER_ID);
    if (m_aRoleMgr.containsWithID (CSecurity.ROLE_USER_ID))
      aUG.assignRole (CSecurity.ROLE_USER_ID);

    // Guests user group
    aUG = getOfID (CSecurity.USERGROUP_GUESTS_ID);
    if (aUG == null)
      aUG = m_aRWLock.writeLockedGet ( () -> internalCreateItem (createDefaultUserGroupGuests ()));
    if (m_aUserMgr.containsWithID (CSecurity.USER_GUEST_ID))
      aUG.assignUser (CSecurity.USER_GUEST_ID);
    // no role for this user group
  }

  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IUserGroupModificationCallback> userGroupModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  public IUserGroup createNewUserGroup (@Nonnull @Nonempty final String sName,
                                        @Nullable final String sDescription,
                                        @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create user group
    final UserGroup aUserGroup = new UserGroup (sName, sDescription, aCustomAttrs);

    // Store
    m_aRWLock.writeLocked ( () -> internalCreateItem (aUserGroup));
    AuditHelper.onAuditCreateSuccess (UserGroup.OT, aUserGroup.getID (), sName, sDescription, aCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupCreated (aUserGroup, false));

    return aUserGroup;
  }

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

    // Store
    m_aRWLock.writeLocked ( () -> internalCreateItem (aUserGroup));
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

  @Nonnull
  public EChange deleteUserGroup (@Nullable final String sUserGroupID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;

    final UserGroup aDeletedUserGroup = getOfID (sUserGroupID);
    if (aDeletedUserGroup == null)
    {
      AuditHelper.onAuditDeleteFailure (UserGroup.OT, sUserGroupID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aDeletedUserGroup).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (UserGroup.OT, sUserGroupID, "already-deleted");
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
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupDeleted (sUserGroupID));

    return EChange.CHANGED;
  }

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
      {
        AuditHelper.onAuditUndeleteFailure (UserGroup.OT, sUserGroupID, "not-deleted");
        return EChange.UNCHANGED;
      }
      internalMarkItemUndeleted (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditUndeleteSuccess (UserGroup.OT, sUserGroupID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUndeleted (sUserGroupID));

    return EChange.CHANGED;
  }

  @Nullable
  public IUserGroup getUserGroupOfID (@Nullable final String sUserGroupID)
  {
    return getOfID (sUserGroupID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllActiveUserGroups ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllDeletedUserGroups ()
  {
    return getAll (IUserGroup::isDeleted);
  }

  @Nonnull
  public EChange renameUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sNewName)
  {
    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "set-name", sUserGroupID, "no-such-id");
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
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "set-name", sUserGroupID, sNewName);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRenamed (sUserGroupID));

    return EChange.CHANGED;
  }

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
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "set-all", sUserGroupID, "no-such-id");
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
                                      "set-all",
                                      aUserGroup.getID (),
                                      sNewName,
                                      sNewDescription,
                                      aNewCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUpdated (sUserGroupID));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange assignUserToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sUserID)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;

    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "assign-user", sUserGroupID, sUserID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.assignUser (sUserID).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserGroup.OT, "assign-user", sUserGroupID, sUserID, "already-assigned");
        return EChange.UNCHANGED;
      }

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (sUserGroupID, sUserID, true));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange unassignUserFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;
    if (StringHelper.hasNoText (sUserID))
      return EChange.UNCHANGED;

    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "unassign-user", sUserGroupID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.unassignUser (sUserID).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserGroup.OT, "unassign-user", sUserGroupID, sUserID, "not-assigned");
        return EChange.UNCHANGED;
      }

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (sUserGroupID, sUserID, false));

    return EChange.CHANGED;
  }

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
      m_aCallbacks.forEach (aCB -> aCB.onUserGroupUserAssignment (aUserGroup.getID (), sUserID, false));

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return new CommonsArrayList <> ();

    return getAll (aUserGroup -> aUserGroup.containsUserID (sUserID));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllUserGroupIDsWithAssignedUser (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return new CommonsArrayList <> ();

    return getAllMapped (aUserGroup -> aUserGroup.containsUserID (sUserID), IUserGroup::getID);
  }

  @Nonnull
  public EChange assignRoleToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sRoleID)
  {
    ValueEnforcer.notEmpty (sRoleID, "RoleID");
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;

    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "assign-role", sUserGroupID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.assignRole (sRoleID).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserGroup.OT, "assign-role", sUserGroupID, sRoleID, "already-assigned");
        return EChange.UNCHANGED;
      }

      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (sUserGroupID, sRoleID, true));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange unassignRoleFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return EChange.UNCHANGED;
    if (StringHelper.hasNoText (sRoleID))
      return EChange.UNCHANGED;

    // Resolve user group
    final UserGroup aUserGroup = getOfID (sUserGroupID);
    if (aUserGroup == null)
    {
      AuditHelper.onAuditModifyFailure (UserGroup.OT, "unassign-role", sUserGroupID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserGroup.unassignRole (sRoleID).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserGroup.OT, "unassign-role", sUserGroupID, sRoleID, "not-assigned");
        return EChange.UNCHANGED;
      }
      BusinessObjectHelper.setLastModificationNow (aUserGroup);
      internalUpdateItem (aUserGroup);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (sUserGroupID, sRoleID, false));

    return EChange.CHANGED;
  }

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
      m_aCallbacks.forEach (aCB -> aCB.onUserGroupRoleAssignment (aUserGroup.getID (), sRoleID, false));

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return getNone ();

    return getAll (aUserGroup -> aUserGroup.containsRoleID (sRoleID));
  }

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

  public boolean containsAnyUserGroupWithAssignedUserAndRole (@Nullable final String sUserID,
                                                              @Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;
    if (StringHelper.hasNoText (sRoleID))
      return false;

    return containsAny (aUserGroup -> aUserGroup.containsUserID (sUserID) && aUserGroup.containsRoleID (sRoleID));
  }
}
