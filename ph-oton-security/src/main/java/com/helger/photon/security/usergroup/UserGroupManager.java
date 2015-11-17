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
package com.helger.photon.security.usergroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.IReloadableDAO;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.ObjectHelper;
import com.helger.photon.security.object.StubObjectWithCustomAttrs;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.user.UserManager;

/**
 * This class manages the available user groups.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserGroupManager extends AbstractSimpleDAO implements IReloadableDAO
{
  public static final boolean DEFAULT_CREATE_DEFAULTS = true;

  private static final Logger s_aLogger = LoggerFactory.getLogger (UserGroupManager.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static boolean s_bCreateDefaults = DEFAULT_CREATE_DEFAULTS;

  private final UserManager m_aUserMgr;
  private final RoleManager m_aRoleMgr;
  @GuardedBy ("m_aRWLock")
  private final Map <String, UserGroup> m_aUserGroups = new HashMap <String, UserGroup> ();

  private final CallbackList <IUserGroupModificationCallback> m_aCallbacks = new CallbackList <IUserGroupModificationCallback> ();

  public static boolean isCreateDefaults ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bCreateDefaults;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setCreateDefaults (final boolean bCreateDefaults)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_bCreateDefaults = bCreateDefaults;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public UserGroupManager (@Nonnull @Nonempty final String sFilename,
                           @Nonnull final UserManager aUserMgr,
                           @Nonnull final RoleManager aRoleMgr) throws DAOException
  {
    super (sFilename);
    m_aUserMgr = ValueEnforcer.notNull (aUserMgr, "UserManager");
    m_aRoleMgr = ValueEnforcer.notNull (aRoleMgr, "RoleManager");
    initialRead ();
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

  public void reload () throws DAOException
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aUserGroups.clear ();
      initialRead ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    if (!isCreateDefaults ())
      return EChange.UNCHANGED;

    // Administrators user group
    UserGroup aUG = _addUserGroup (new UserGroup (StubObjectWithCustomAttrs.createForCurrentUserAndID (CSecurity.USERGROUP_ADMINISTRATORS_ID),
                                                  CSecurity.USERGROUP_ADMINISTRATORS_NAME,
                                                  (String) null));
    if (m_aUserMgr.containsUserWithID (CSecurity.USER_ADMINISTRATOR_ID))
      aUG.assignUser (CSecurity.USER_ADMINISTRATOR_ID);
    if (m_aRoleMgr.containsRoleWithID (CSecurity.ROLE_ADMINISTRATOR_ID))
      aUG.assignRole (CSecurity.ROLE_ADMINISTRATOR_ID);

    // Users user group
    aUG = _addUserGroup (new UserGroup (StubObjectWithCustomAttrs.createForCurrentUserAndID (CSecurity.USERGROUP_USERS_ID),
                                        CSecurity.USERGROUP_USERS_NAME,
                                        (String) null));
    if (m_aUserMgr.containsUserWithID (CSecurity.USER_USER_ID))
      aUG.assignUser (CSecurity.USER_USER_ID);
    if (m_aRoleMgr.containsRoleWithID (CSecurity.ROLE_USER_ID))
      aUG.assignRole (CSecurity.ROLE_USER_ID);

    // Guests user group
    aUG = _addUserGroup (new UserGroup (StubObjectWithCustomAttrs.createForCurrentUserAndID (CSecurity.USERGROUP_GUESTS_ID),
                                        CSecurity.USERGROUP_GUESTS_NAME,
                                        (String) null));
    if (m_aUserMgr.containsUserWithID (CSecurity.USER_GUEST_ID))
      aUG.assignUser (CSecurity.USER_GUEST_ID);
    // no role for this user group

    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eUserGroup : aDoc.getDocumentElement ().getAllChildElements ())
      _addUserGroup (MicroTypeConverter.convertToNative (eUserGroup, UserGroup.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("usergroups");
    for (final UserGroup aUserGroup : CollectionHelper.getSortedByKey (m_aUserGroups).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aUserGroup, "usergroup"));
    return aDoc;
  }

  /**
   * @return The user group callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IUserGroupModificationCallback> getUserGroupModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  private UserGroup _addUserGroup (@Nonnull final UserGroup aUserGroup)
  {
    final String sUserGroupID = aUserGroup.getID ();
    if (m_aUserGroups.containsKey (sUserGroupID))
      throw new IllegalArgumentException ("User group ID " + sUserGroupID + " is already in use!");
    m_aUserGroups.put (sUserGroupID, aUserGroup);
    return aUserGroup;
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Store
      _addUserGroup (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (UserGroup.OT, aUserGroup.getID (), sName, sDescription, aCustomAttrs);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupCreated (aUserGroup, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupCreated callback on " + aUserGroup.toString (), t);
      }

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
    final UserGroup aUserGroup = new UserGroup (StubObjectWithCustomAttrs.createForCurrentUserAndID (sID, aCustomAttrs), sName, sDescription);

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Store
      _addUserGroup (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (UserGroup.OT, aUserGroup.getID (), "predefined-usergroup", sName, sDescription, aCustomAttrs);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupCreated (aUserGroup, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupCreated callback on " + aUserGroup.toString (), t);
      }

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

    UserGroup aDeletedUserGroup;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aDeletedUserGroup = m_aUserGroups.remove (sUserGroupID);
      if (aDeletedUserGroup == null)
      {
        AuditHelper.onAuditDeleteFailure (UserGroup.OT, "no-such-usergroup-id", sUserGroupID);
        return EChange.UNCHANGED;
      }

      ObjectHelper.setDeletionNow (aDeletedUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (UserGroup.OT, sUserGroupID);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupDeleted (aDeletedUserGroup);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupDeleted callback on " + aDeletedUserGroup.toString (), t);
      }

    return EChange.CHANGED;
  }

  /**
   * Check if a user group with the specified ID is contained
   *
   * @param sUserGroupID
   *        The ID of the user group to check
   * @return <code>true</code> if no user group exists, <code>false</code>
   *         otherwise.
   */
  public boolean containsUserGroupWithID (@Nullable final String sUserGroupID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aUserGroups.containsKey (sUserGroupID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Check if all passed user group IDs are contained
   *
   * @param aUserGroupIDs
   *        The user group IDs to be checked. May be <code>null</code>.
   * @return <code>true</code> if the collection is empty or if all contained
   *         user group IDs are contained
   */
  public boolean containsAllUserGroupsWithID (@Nullable final Collection <String> aUserGroupIDs)
  {
    if (CollectionHelper.isEmpty (aUserGroupIDs))
      return true;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final String sUserGroupID : aUserGroupIDs)
        if (!m_aUserGroups.containsKey (sUserGroupID))
          return false;
      return true;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Get the user group with the specified ID
   *
   * @param sUserGroupID
   *        The ID of the user group to search
   * @return <code>null</code> if no such user group exists
   */
  @Nullable
  public UserGroup getUserGroupOfID (@Nullable final String sUserGroupID)
  {
    if (StringHelper.hasNoText (sUserGroupID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aUserGroups.get (sUserGroupID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return A non-<code>null</code> list of all user groups
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <? extends IUserGroup> getAllUserGroups ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aUserGroups.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "name", sUserGroupID, sNewName);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupRenamed (aUserGroup);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupRenamed callback on " + aUserGroup.toString (), t);
      }

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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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
      eChange = eChange.or (aUserGroup.getMutableAttributes ().clear ());
      eChange = eChange.or (aUserGroup.getMutableAttributes ().setAttributes (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "all", aUserGroup.getID (), sNewName, sNewDescription, aNewCustomAttrs);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupUpdated (aUserGroup);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupUpdated callback on " + aUserGroup.toString (), t);
      }

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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupUserAssignment (aUserGroup, sUserID, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupUserAssignment callback on " + aUserGroup.toString (), t);
      }

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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-user", sUserGroupID, sUserID);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupUserAssignment (aUserGroup, sUserID, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupUserAssignment callback on " + aUserGroup.toString (), t);
      }

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

    final List <IUserGroup> aAffectedUserGroups = new ArrayList <> ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      for (final UserGroup aUserGroup : m_aUserGroups.values ())
        if (aUserGroup.unassignUser (sUserID).isChanged ())
        {
          aAffectedUserGroups.add (aUserGroup);
          ObjectHelper.setLastModificationNow (aUserGroup);
          eChange = EChange.CHANGED;
        }
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-user-from-all-usergroups", sUserID);

    // Execute callback as the very last action
    for (final IUserGroup aUserGroup : aAffectedUserGroups)
      for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
        try
        {
          aCallback.onUserGroupUserAssignment (aUserGroup, sUserID, false);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Failed to invoke onUserGroupUserAssignment callback on " + aUserGroup.toString (), t);
        }

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
  public List <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable final String sUserID)
  {
    final List <IUserGroup> ret = new ArrayList <> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IUserGroup aUserGroup : m_aUserGroups.values ())
          if (aUserGroup.containsUserID (sUserID))
            ret.add (aUserGroup);
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
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
  public List <String> getAllUserGroupIDsWithAssignedUser (@Nullable final String sUserID)
  {
    final List <String> ret = new ArrayList <String> ();
    if (StringHelper.hasText (sUserID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IUserGroup aUserGroup : m_aUserGroups.values ())
          if (aUserGroup.containsUserID (sUserID))
            ret.add (aUserGroup.getID ());
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "assign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupRoleAssignment (aUserGroup, sRoleID, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupRoleAssignment callback on " + aUserGroup.toString (), t);
      }

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
    final UserGroup aUserGroup = getUserGroupOfID (sUserGroupID);
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

      ObjectHelper.setLastModificationNow (aUserGroup);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-role", sUserGroupID, sRoleID);

    // Execute callback as the very last action
    for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserGroupRoleAssignment (aUserGroup, sRoleID, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserGroupRoleAssignment callback on " + aUserGroup.toString (), t);
      }

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

    final List <IUserGroup> aAffectedUserGroups = new ArrayList <IUserGroup> ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      for (final UserGroup aUserGroup : m_aUserGroups.values ())
        if (aUserGroup.unassignRole (sRoleID).isChanged ())
        {
          aAffectedUserGroups.add (aUserGroup);
          ObjectHelper.setLastModificationNow (aUserGroup);
          eChange = EChange.CHANGED;
        }
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserGroup.OT, "unassign-role-from-all-usergroups", sRoleID);

    // Execute callback as the very last action
    for (final IUserGroup aUserGroup : aAffectedUserGroups)
      for (final IUserGroupModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
        try
        {
          aCallback.onUserGroupRoleAssignment (aUserGroup, sRoleID, false);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Failed to invoke onUserGroupRoleAssignment callback on " + aUserGroup.toString (), t);
        }

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
  public List <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable final String sRoleID)
  {
    final List <IUserGroup> ret = new ArrayList <IUserGroup> ();
    if (StringHelper.hasText (sRoleID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IUserGroup aUserGroup : m_aUserGroups.values ())
          if (aUserGroup.containsRoleID (sRoleID))
            ret.add (aUserGroup);
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
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
  public List <String> getAllUserGroupIDsWithAssignedRole (@Nullable final String sRoleID)
  {
    final List <String> ret = new ArrayList <> ();
    if (StringHelper.hasText (sRoleID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IUserGroup aUserGroup : m_aUserGroups.values ())
          if (aUserGroup.containsRoleID (sRoleID))
            ret.add (aUserGroup.getID ());
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return ret;
  }

  public boolean containsUserGroupWithAssignedRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasText (sRoleID))
    {
      m_aRWLock.readLock ().lock ();
      try
      {
        for (final IUserGroup aUserGroup : m_aUserGroups.values ())
          if (aUserGroup.containsRoleID (sRoleID))
            return true;
      }
      finally
      {
        m_aRWLock.readLock ().unlock ();
      }
    }
    return false;
  }
}
