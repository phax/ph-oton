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
package com.helger.photon.basic.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.scopes.singleton.GlobalSingleton;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.security.login.DefaultUserLoginCallback;
import com.helger.photon.basic.security.login.ELoginResult;
import com.helger.photon.basic.security.login.LoggedInUserManager;
import com.helger.photon.basic.security.login.LoginInfo;
import com.helger.photon.basic.security.role.IRole;
import com.helger.photon.basic.security.role.IRoleModificationCallback;
import com.helger.photon.basic.security.role.RoleManager;
import com.helger.photon.basic.security.user.IUser;
import com.helger.photon.basic.security.user.IUserModificationCallback;
import com.helger.photon.basic.security.user.UserManager;
import com.helger.photon.basic.security.usergroup.IUserGroup;
import com.helger.photon.basic.security.usergroup.IUserGroupModificationCallback;
import com.helger.photon.basic.security.usergroup.UserGroupManager;

/**
 * This is the central manager that encapsulates all security manages. This
 * class is thread-safe under the assumption that the implementing managers are
 * thread-safe.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AccessManager extends GlobalSingleton implements IAccessManager
{
  public static final String DEFAULT_BASE_PATH = "security/";
  public static final String FILENAME_USERS_XML = "users.xml";
  public static final String FILENAME_ROLES_XML = "roles.xml";
  public static final String FILENAME_USERGROUPS_XML = "usergroups.xml";

  private static final Logger s_aLogger = LoggerFactory.getLogger (AccessManager.class);

  private final UserManager m_aUserMgr;
  private final RoleManager m_aRoleMgr;
  private final UserGroupManager m_aUserGroupMgr;

  @Deprecated
  @UsedViaReflection
  public AccessManager () throws DAOException
  {
    m_aUserMgr = new UserManager (DEFAULT_BASE_PATH + FILENAME_USERS_XML);
    m_aRoleMgr = new RoleManager (DEFAULT_BASE_PATH + FILENAME_ROLES_XML);
    m_aUserGroupMgr = new UserGroupManager (DEFAULT_BASE_PATH + FILENAME_USERGROUPS_XML, m_aUserMgr, m_aRoleMgr);

    // Remember the last login date of the user
    LoggedInUserManager.getInstance ().getUserLoginCallbacks ().addCallback (new DefaultUserLoginCallback ()
    {
      @Override
      public void onUserLogin (@Nonnull final LoginInfo aInfo)
      {
        m_aUserMgr.updateUserLastLogin (aInfo.getUserID ());
      }

      @Override
      public void onUserLoginError (@Nonnull @Nonempty final String sUserID, @Nonnull final ELoginResult eLoginResult)
      {
        if (eLoginResult == ELoginResult.INVALID_PASSWORD)
        {
          // On invalid password, update consecutive failed login count
          m_aUserMgr.updateUserLastFailedLogin (sUserID);
        }
      }
    });
  }

  @Nonnull
  public static AccessManager getInstance ()
  {
    return getGlobalSingleton (AccessManager.class);
  }

  /**
   * Reload the content of the user manager.
   *
   * @throws DAOException
   *         in case reloading fails
   */
  public void reloadUserManager () throws DAOException
  {
    m_aUserMgr.reload ();
  }

  /**
   * Reload the content of the user group manager.
   *
   * @throws DAOException
   *         in case reloading fails
   */
  public void reloadUserGroupManager () throws DAOException
  {
    m_aUserGroupMgr.reload ();
  }

  /**
   * Reload the content of the role manager.
   *
   * @throws DAOException
   *         in case reloading fails
   */
  public void reloadRoleManager () throws DAOException
  {
    m_aRoleMgr.reload ();
  }

  // User API

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IUserModificationCallback> getUserModificationCallbacks ()
  {
    return m_aUserMgr.getUserModificationCallbacks ();
  }

  @Nullable
  public IUser createNewUser (@Nonnull @Nonempty final String sLoginName,
                              @Nullable final String sEmailAddress,
                              @Nonnull @Nonempty final String sPlainTextPassword,
                              @Nullable final String sFirstName,
                              @Nullable final String sLastName,
                              @Nullable final String sDescription,
                              @Nullable final Locale aDesiredLocale,
                              @Nullable final Map <String, ?> aCustomAttrs,
                              final boolean bDisabled)
  {
    return m_aUserMgr.createNewUser (sLoginName,
                                     sEmailAddress,
                                     sPlainTextPassword,
                                     sFirstName,
                                     sLastName,
                                     sDescription,
                                     aDesiredLocale,
                                     aCustomAttrs,
                                     bDisabled);
  }

  @Nullable
  public IUser createPredefinedUser (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sLoginName,
                                     @Nullable final String sEmailAddress,
                                     @Nonnull @Nonempty final String sPlainTextPassword,
                                     @Nullable final String sFirstName,
                                     @Nullable final String sLastName,
                                     @Nullable final String sDescription,
                                     @Nullable final Locale aDesiredLocale,
                                     @Nullable final Map <String, ?> aCustomAttrs,
                                     final boolean bDisabled)
  {
    return m_aUserMgr.createPredefinedUser (sID,
                                            sLoginName,
                                            sEmailAddress,
                                            sPlainTextPassword,
                                            sFirstName,
                                            sLastName,
                                            sDescription,
                                            aDesiredLocale,
                                            aCustomAttrs,
                                            bDisabled);
  }

  @Nonnull
  public EChange deleteUser (@Nullable final String sUserID)
  {
    if (LoggedInUserManager.getInstance ().isUserLoggedIn (sUserID))
    {
      s_aLogger.warn ("Cannot delete user " + sUserID + " as the user is currently logged in!");
      return EChange.UNCHANGED;
    }
    return m_aUserMgr.deleteUser (sUserID);
  }

  @Nonnull
  public EChange undeleteUser (@Nullable final String sUserID)
  {
    return m_aUserMgr.undeleteUser (sUserID);
  }

  @Nonnull
  public EChange disableUser (@Nullable final String sUserID)
  {
    return m_aUserMgr.disableUser (sUserID);
  }

  @Nonnull
  public EChange enableUser (@Nullable final String sUserID)
  {
    return m_aUserMgr.enableUser (sUserID);
  }

  public boolean containsUserWithID (@Nullable final String sUserID)
  {
    return m_aUserMgr.containsUserWithID (sUserID);
  }

  @Nullable
  public IUser getUserOfID (@Nullable final String sUserID)
  {
    return m_aUserMgr.getUserOfID (sUserID);
  }

  @Nullable
  public IUser getUserOfLoginName (@Nullable final String sLoginName)
  {
    return m_aUserMgr.getUserOfLoginName (sLoginName);
  }

  @Nullable
  public IUser getUserOfEmailAddress (@Nullable final String sEmailAddress)
  {
    return m_aUserMgr.getUserOfEmailAddress (sEmailAddress);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUser> getAllUsers ()
  {
    return m_aUserMgr.getAllUsers ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUser> getAllActiveUsers ()
  {
    return m_aUserMgr.getAllActiveUsers ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUser> getAllDisabledUsers ()
  {
    return m_aUserMgr.getAllDisabledUsers ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUser> getAllNotDeletedUsers ()
  {
    return m_aUserMgr.getAllNotDeletedUsers ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUser> getAllDeletedUsers ()
  {
    return m_aUserMgr.getAllDeletedUsers ();
  }

  @Nonnull
  public EChange setUserData (@Nullable final String sUserID,
                              @Nonnull @Nonempty final String sNewLoginName,
                              @Nullable final String sNewEmailAddress,
                              @Nullable final String sNewFirstName,
                              @Nullable final String sNewLastName,
                              @Nullable final String sNewDescription,
                              @Nullable final Locale aNewDesiredLocale,
                              @Nullable final Map <String, ?> aCustomAttrs,
                              final boolean bDisabled)
  {
    return m_aUserMgr.setUserData (sUserID,
                                   sNewLoginName,
                                   sNewEmailAddress,
                                   sNewFirstName,
                                   sNewLastName,
                                   sNewDescription,
                                   aNewDesiredLocale,
                                   aCustomAttrs,
                                   bDisabled);
  }

  @Nonnull
  public EChange setUserPassword (@Nullable final String sUserID, @Nonnull final String sNewPlainTextPassword)
  {
    return m_aUserMgr.setUserPassword (sUserID, sNewPlainTextPassword);
  }

  public boolean areUserIDAndPasswordValid (@Nullable final String sUserID, @Nullable final String sPlainTextPassword)
  {
    return m_aUserMgr.areUserIDAndPasswordValid (sUserID, sPlainTextPassword);
  }

  /**
   * Check if the passed combination of login name and plain text password are
   * valid
   *
   * @param sLoginName
   *        The login name of the user to validate.
   * @param sPlainTextPassword
   *        The plain text password to validate
   * @return <code>true</code> if the login name matches a user, and if the hash
   *         of the plain text password matches the stored password hash
   */
  public boolean areUserLoginNameAndPasswordValid (@Nullable final String sLoginName,
                                                   @Nullable final String sPlainTextPassword)
  {
    final IUser aUser = getUserOfLoginName (sLoginName);
    return aUser == null ? false : m_aUserMgr.areUserIDAndPasswordValid (aUser.getID (), sPlainTextPassword);
  }

  // UserGroup API
  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IUserGroupModificationCallback> getUserGroupModificationCallbacks ()
  {
    return m_aUserGroupMgr.getUserGroupModificationCallbacks ();
  }

  @Nonnull
  public IUserGroup createNewUserGroup (@Nonnull @Nonempty final String sName,
                                        @Nullable final String sDescription,
                                        @Nullable final Map <String, ?> aCustomAttrs)
  {
    return m_aUserGroupMgr.createNewUserGroup (sName, sDescription, aCustomAttrs);
  }

  @Nonnull
  public IUserGroup createPredefinedUserGroup (@Nonnull @Nonempty final String sID,
                                               @Nonnull @Nonempty final String sName,
                                               @Nullable final String sDescription,
                                               @Nullable final Map <String, ?> aCustomAttrs)
  {
    return m_aUserGroupMgr.createPredefinedUserGroup (sID, sName, sDescription, aCustomAttrs);
  }

  @Nonnull
  public EChange deleteUserGroup (@Nullable final String sUserGroupID)
  {
    return m_aUserGroupMgr.deleteUserGroup (sUserGroupID);
  }

  public boolean containsUserGroupWithID (@Nullable final String sUserGroupID)
  {
    return m_aUserGroupMgr.containsUserGroupWithID (sUserGroupID);
  }

  public boolean containsAllUserGroupsWithID (@Nullable final Collection <String> aUserGroupIDs)
  {
    return m_aUserGroupMgr.containsAllUserGroupsWithID (aUserGroupIDs);
  }

  @Nullable
  public IUserGroup getUserGroupOfID (@Nullable final String sUserGroupID)
  {
    return m_aUserGroupMgr.getUserGroupOfID (sUserGroupID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IUserGroup> getAllUserGroups ()
  {
    return m_aUserGroupMgr.getAllUserGroups ();
  }

  @Nonnull
  public EChange renameUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sNewName)
  {
    return m_aUserGroupMgr.renameUserGroup (sUserGroupID, sNewName);
  }

  @Nonnull
  public EChange setUserGroupData (@Nullable final String sUserGroupID,
                                   @Nonnull @Nonempty final String sNewName,
                                   @Nullable final String sNewDescription,
                                   @Nullable final Map <String, ?> aNewCustomAttrs)
  {
    return m_aUserGroupMgr.setUserGroupData (sUserGroupID, sNewName, sNewDescription, aNewCustomAttrs);
  }

  @Nonnull
  public EChange assignUserToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sUserID)
  {
    return m_aUserGroupMgr.assignUserToUserGroup (sUserGroupID, sUserID);
  }

  @Nonnull
  public EChange unassignUserFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    return m_aUserGroupMgr.unassignUserFromUserGroup (sUserGroupID, sUserID);
  }

  @Nonnull
  public EChange unassignUserFromAllUserGroups (@Nullable final String sUserID)
  {
    return m_aUserGroupMgr.unassignUserFromAllUserGroups (sUserID);
  }

  public boolean isUserAssignedToUserGroup (@Nullable final String sUserGroupID, @Nullable final String sUserID)
  {
    return m_aUserGroupMgr.isUserAssignedToUserGroup (sUserGroupID, sUserID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <IUserGroup> getAllUserGroupsWithAssignedUser (@Nullable final String sUserID)
  {
    return m_aUserGroupMgr.getAllUserGroupsWithAssignedUser (sUserID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <String> getAllUserGroupIDsWithAssignedUser (@Nullable final String sUserID)
  {
    return m_aUserGroupMgr.getAllUserGroupIDsWithAssignedUser (sUserID);
  }

  @Nonnull
  public EChange assignRoleToUserGroup (@Nullable final String sUserGroupID, @Nonnull @Nonempty final String sRoleID)
  {
    return m_aUserGroupMgr.assignRoleToUserGroup (sUserGroupID, sRoleID);
  }

  @Nonnull
  public EChange unassignRoleFromUserGroup (@Nullable final String sUserGroupID, @Nullable final String sRoleID)
  {
    return m_aUserGroupMgr.unassignRoleFromUserGroup (sUserGroupID, sRoleID);
  }

  @Nonnull
  public EChange unassignRoleFromAllUserGroups (@Nullable final String sRoleID)
  {
    return m_aUserGroupMgr.unassignRoleFromAllUserGroups (sRoleID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <IUserGroup> getAllUserGroupsWithAssignedRole (@Nullable final String sRoleID)
  {
    return m_aUserGroupMgr.getAllUserGroupsWithAssignedRole (sRoleID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <String> getAllUserGroupIDsWithAssignedRole (@Nullable final String sRoleID)
  {
    return m_aUserGroupMgr.getAllUserGroupIDsWithAssignedRole (sRoleID);
  }

  // Role API

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IRoleModificationCallback> getRoleModificationCallbacks ()
  {
    return m_aRoleMgr.getRoleModificationCallbacks ();
  }

  @Nonnull
  public IRole createNewRole (@Nonnull @Nonempty final String sName,
                              @Nullable final String sDescription,
                              @Nullable final Map <String, ?> aCustomAttrs)
  {
    return m_aRoleMgr.createNewRole (sName, sDescription, aCustomAttrs);
  }

  @Nonnull
  public IRole createPredefinedRole (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sName,
                                     @Nullable final String sDescription,
                                     @Nullable final Map <String, ?> aCustomAttrs)
  {
    return m_aRoleMgr.createPredefinedRole (sID, sName, sDescription, aCustomAttrs);
  }

  @Nonnull
  public EChange deleteRole (@Nullable final String sRoleID)
  {
    if (m_aRoleMgr.deleteRole (sRoleID).isUnchanged ())
      return EChange.UNCHANGED;

    // Since the role does not exist any more, remove it from all user groups
    m_aUserGroupMgr.unassignRoleFromAllUserGroups (sRoleID);
    return EChange.CHANGED;
  }

  public boolean containsRoleWithID (@Nullable final String sRoleID)
  {
    return m_aRoleMgr.containsRoleWithID (sRoleID);
  }

  public boolean containsAllRolesWithID (@Nullable final Collection <String> aRoleIDs)
  {
    return m_aRoleMgr.containsAllRolesWithID (aRoleIDs);
  }

  @Nullable
  public IRole getRoleOfID (@Nullable final String sRoleID)
  {
    return m_aRoleMgr.getRoleOfID (sRoleID);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IRole> getAllRoles ()
  {
    return m_aRoleMgr.getAllRoles ();
  }

  @Nonnull
  public EChange renameRole (@Nullable final String sRoleID, @Nonnull @Nonempty final String sNewName)
  {
    return m_aRoleMgr.renameRole (sRoleID, sNewName);
  }

  @Nonnull
  public EChange setRoleData (@Nullable final String sRoleID,
                              @Nonnull @Nonempty final String sNewName,
                              @Nullable final String sNewDescription,
                              @Nullable final Map <String, ?> aNewCustomAttrs)
  {
    return m_aRoleMgr.setRoleData (sRoleID, sNewName, sNewDescription, aNewCustomAttrs);
  }

  public boolean hasUserRole (@Nullable final String sUserID, @Nullable final String sRoleID)
  {
    for (final IUserGroup aUserGroup : m_aUserGroupMgr.getAllUserGroupsWithAssignedUser (sUserID))
      if (aUserGroup.containsRoleID (sRoleID))
        return true;
    return false;
  }

  public boolean hasUserAllRoles (@Nullable final String sUserID, @Nullable final Collection <String> aRoleIDs)
  {
    if (CollectionHelper.isNotEmpty (aRoleIDs))
    {
      final Collection <IUserGroup> aUserGroups = m_aUserGroupMgr.getAllUserGroupsWithAssignedUser (sUserID);
      for (final String sRoleID : aRoleIDs)
      {
        boolean bFoundRole = false;
        for (final IUserGroup aUserGroup : aUserGroups)
          if (aUserGroup.containsRoleID (sRoleID))
          {
            bFoundRole = true;
            break;
          }
        if (!bFoundRole)
          return false;
      }
    }
    return true;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllUserRoleIDs (@Nullable final String sUserID)
  {
    final Set <String> ret = new HashSet <String> ();
    for (final IUserGroup aUserGroup : m_aUserGroupMgr.getAllUserGroupsWithAssignedUser (sUserID))
      ret.addAll (aUserGroup.getAllContainedRoleIDs ());
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <IRole> getAllUserRoles (@Nullable final String sUserID)
  {
    final Set <String> aRoleIDs = getAllUserRoleIDs (sUserID);
    final Set <IRole> ret = new HashSet <IRole> ();
    for (final String sRoleID : aRoleIDs)
    {
      final IRole aRole = getRoleOfID (sRoleID);
      if (aRole != null)
        ret.add (aRole);
      else
        s_aLogger.warn ("Failed to resolve role with ID '" + sRoleID + "'");
    }
    return ret;
  }
}
