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
package com.helger.photon.security.user;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.dao.DAOException;
import com.helger.photon.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;
import com.helger.security.password.salt.PasswordSalt;

/**
 * This class manages the available users.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserManager extends AbstractPhotonMapBasedWALDAO <IUser, User>
{
  private final CallbackList <IUserModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (User.class, sFilename);
  }

  public void createDefaults ()
  {
    // Create Administrator
    if (!containsWithID (CSecurity.USER_ADMINISTRATOR_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (new User (CSecurity.USER_ADMINISTRATOR_ID,
                                                                  CSecurity.USER_ADMINISTRATOR_LOGIN,
                                                                  CSecurity.USER_ADMINISTRATOR_EMAIL,
                                                                  GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                                                        CSecurity.USER_ADMINISTRATOR_PASSWORD),
                                                                  CSecurity.USER_ADMINISTRATOR_NAME,
                                                                  (String) null,
                                                                  (String) null,
                                                                  (Locale) null,
                                                                  (Map <String, String>) null,
                                                                  false)));

    // Create regular user
    if (!containsWithID (CSecurity.USER_USER_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (new User (CSecurity.USER_USER_ID,
                                                                  CSecurity.USER_USER_LOGIN,
                                                                  CSecurity.USER_USER_EMAIL,
                                                                  GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                                                        CSecurity.USER_USER_PASSWORD),
                                                                  CSecurity.USER_USER_NAME,
                                                                  (String) null,
                                                                  (String) null,
                                                                  (Locale) null,
                                                                  (Map <String, String>) null,
                                                                  false)));

    // Create guest user
    if (!containsWithID (CSecurity.USER_GUEST_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (new User (CSecurity.USER_GUEST_ID,
                                                                  CSecurity.USER_GUEST_LOGIN,
                                                                  CSecurity.USER_GUEST_EMAIL,
                                                                  GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                                                        CSecurity.USER_GUEST_PASSWORD),
                                                                  CSecurity.USER_GUEST_NAME,
                                                                  (String) null,
                                                                  (String) null,
                                                                  (Locale) null,
                                                                  (Map <String, String>) null,
                                                                  false)));
  }

  /**
   * @return The user callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IUserModificationCallback> userModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  /**
   * Create a new user.
   *
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor
   *        empty.This login name must be unique over all existing users.
   * @param sEmailAddress
   *        The email address. May be <code>null</code>.
   * @param sPlainTextPassword
   *        The plain text password to be used. May neither be <code>null</code>
   *        nor empty.
   * @param sFirstName
   *        The users first name. May be <code>null</code>.
   * @param sLastName
   *        The users last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description for the user. May be <code>null</code>.
   * @param aDesiredLocale
   *        The users default locale. May be <code>null</code>.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return The created user or <code>null</code> if another user with the same
   *         email address is already present.
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   */
  @Nullable
  public IUser createNewUser (@Nonnull @Nonempty final String sLoginName,
                              @Nullable final String sEmailAddress,
                              @Nonnull final String sPlainTextPassword,
                              @Nullable final String sFirstName,
                              @Nullable final String sLastName,
                              @Nullable final String sDescription,
                              @Nullable final Locale aDesiredLocale,
                              @Nullable final Map <String, String> aCustomAttrs,
                              final boolean bDisabled)
  {
    ValueEnforcer.notEmpty (sLoginName, "LoginName");
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    if (getUserOfLoginName (sLoginName) != null)
    {
      // Another user with this login name already exists
      AuditHelper.onAuditCreateFailure (User.OT, "login-name-already-in-use", sLoginName);
      return null;
    }

    // Create user
    final User aUser = new User (sLoginName,
                                 sEmailAddress,
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                       sPlainTextPassword),
                                 sFirstName,
                                 sLastName,
                                 sDescription,
                                 aDesiredLocale,
                                 aCustomAttrs,
                                 bDisabled);

    m_aRWLock.writeLocked ( () -> {
      internalCreateItem (aUser);
    });
    AuditHelper.onAuditCreateSuccess (User.OT,
                                      aUser.getID (),
                                      sLoginName,
                                      sEmailAddress,
                                      sFirstName,
                                      sLastName,
                                      sDescription,
                                      aDesiredLocale,
                                      aCustomAttrs,
                                      Boolean.valueOf (bDisabled));

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserCreated (aUser, false));

    return aUser;
  }

  /**
   * Create a predefined user.
   *
   * @param sID
   *        The ID to use
   * @param sLoginName
   *        Login name of the user. May neither be <code>null</code> nor empty.
   *        This login name must be unique over all existing users.
   * @param sEmailAddress
   *        The email address. May be <code>null</code>.
   * @param sPlainTextPassword
   *        The plain text password to be used. May neither be <code>null</code>
   *        nor empty.
   * @param sFirstName
   *        The users first name. May be <code>null</code>.
   * @param sLastName
   *        The users last name. May be <code>null</code>.
   * @param sDescription
   *        Optional description for the user. May be <code>null</code>.
   * @param aDesiredLocale
   *        The users default locale. May be <code>null</code>.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return The created user or <code>null</code> if another user with the same
   *         email address is already present.
   * @param bDisabled
   *        <code>true</code> if the user is disabled
   */
  @Nullable
  public IUser createPredefinedUser (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sLoginName,
                                     @Nullable final String sEmailAddress,
                                     @Nonnull final String sPlainTextPassword,
                                     @Nullable final String sFirstName,
                                     @Nullable final String sLastName,
                                     @Nullable final String sDescription,
                                     @Nullable final Locale aDesiredLocale,
                                     @Nullable final Map <String, String> aCustomAttrs,
                                     final boolean bDisabled)
  {
    ValueEnforcer.notEmpty (sLoginName, "LoginName");
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    if (getUserOfLoginName (sLoginName) != null)
    {
      // Another user with this login name already exists
      AuditHelper.onAuditCreateFailure (User.OT, "login-name-already-in-use", sLoginName, "predefined-user");
      return null;
    }

    // Create user
    final User aUser = new User (sID,
                                 sLoginName,
                                 sEmailAddress,
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                       sPlainTextPassword),
                                 sFirstName,
                                 sLastName,
                                 sDescription,
                                 aDesiredLocale,
                                 aCustomAttrs,
                                 bDisabled);

    m_aRWLock.writeLocked ( () -> {
      internalCreateItem (aUser);
    });
    AuditHelper.onAuditCreateSuccess (User.OT,
                                      aUser.getID (),
                                      "predefined-user",
                                      sLoginName,
                                      sEmailAddress,
                                      sFirstName,
                                      sLastName,
                                      sDescription,
                                      aDesiredLocale,
                                      aCustomAttrs,
                                      Boolean.valueOf (bDisabled));

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserCreated (aUser, true));

    return aUser;
  }

  /**
   * Get the user with the specified ID.
   *
   * @param sUserID
   *        The user ID to resolve. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   */
  @Nullable
  public IUser getUserOfID (@Nullable final String sUserID)
  {
    return getOfID (sUserID);
  }

  @Nullable
  public IUser getActiveUserOfID (@Nullable final String sUserID)
  {
    final IUser aUser = getOfID (sUserID);
    return aUser == null || aUser.isDeleted () || aUser.isDisabled () ? null : aUser;
  }

  /**
   * Get the user with the specified login name
   *
   * @param sLoginName
   *        The login name to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   */
  @Nullable
  public IUser getUserOfLoginName (@Nullable final String sLoginName)
  {
    if (StringHelper.hasNoText (sLoginName))
      return null;

    return findFirst (x -> x.getLoginName ().equals (sLoginName));
  }

  /**
   * Get the user with the specified email address
   *
   * @param sEmailAddress
   *        The email address to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   * @see #getUserOfEmailAddressIgnoreCase(String)
   */
  @Nullable
  public IUser getUserOfEmailAddress (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    return findFirst (x -> sEmailAddress.equals (x.getEmailAddress ()));
  }

  /**
   * Get the user with the specified email address
   *
   * @param sEmailAddress
   *        The email address to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   * @see #getUserOfEmailAddress(String)
   * @since 8.1.3
   */
  @Nullable
  public IUser getUserOfEmailAddressIgnoreCase (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    return findFirst (x -> sEmailAddress.equalsIgnoreCase (x.getEmailAddress ()));
  }

  /**
   * @return A non-<code>null</code> collection of all contained enabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllActiveUsers ()
  {
    return getAll (x -> !x.isDeleted () && x.isEnabled ());
  }

  /**
   * @return The number of all contained enabled and not-deleted users
   */
  @Nonnegative
  public int getActiveUserCount ()
  {
    return getCount (x -> !x.isDeleted () && x.isEnabled ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained disabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllDisabledUsers ()
  {
    return getAll (x -> !x.isDeleted () && x.isDisabled ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained not deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllNotDeletedUsers ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllDeletedUsers ()
  {
    return getAll (x -> x.isDeleted ());
  }

  /**
   * @return <code>true</code> if any non-deleted, enabled user exists.
   */
  public boolean containsAnyActiveUser ()
  {
    return containsAny (x -> !x.isDeleted () && x.isEnabled ());
  }

  /**
   * Change the modifiable data of a user
   *
   * @param sUserID
   *        The ID of the user to be modified. May be <code>null</code>.
   * @param sNewLoginName
   *        The new login name. May not be <code>null</code>.
   * @param sNewEmailAddress
   *        The new email address. May be <code>null</code>.
   * @param sNewFirstName
   *        The new first name. May be <code>null</code>.
   * @param sNewLastName
   *        The new last name. May be <code>null</code>.
   * @param sNewDescription
   *        Optional new description for the user. May be <code>null</code>.
   * @param aNewDesiredLocale
   *        The new desired locale. May be <code>null</code>.
   * @param aNewCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param bNewDisabled
   *        <code>true</code> if the user is disabled
   * @return {@link EChange}
   */
  @Nonnull
  public EChange setUserData (@Nullable final String sUserID,
                              @Nonnull @Nonempty final String sNewLoginName,
                              @Nullable final String sNewEmailAddress,
                              @Nullable final String sNewFirstName,
                              @Nullable final String sNewLastName,
                              @Nullable final String sNewDescription,
                              @Nullable final Locale aNewDesiredLocale,
                              @Nullable final Map <String, String> aNewCustomAttrs,
                              final boolean bNewDisabled)
  {
    // Resolve user
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = aUser.setLoginName (sNewLoginName);
      eChange = eChange.or (aUser.setEmailAddress (sNewEmailAddress));
      eChange = eChange.or (aUser.setFirstName (sNewFirstName));
      eChange = eChange.or (aUser.setLastName (sNewLastName));
      eChange = eChange.or (aUser.setDescription (sNewDescription));
      eChange = eChange.or (aUser.setDesiredLocale (aNewDesiredLocale));
      eChange = eChange.or (aUser.setDisabled (bNewDisabled));
      eChange = eChange.or (aUser.attrs ().setAll (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUser);
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT,
                                      "all",
                                      aUser.getID (),
                                      sNewLoginName,
                                      sNewEmailAddress,
                                      sNewFirstName,
                                      sNewLastName,
                                      sNewDescription,
                                      aNewDesiredLocale,
                                      aNewCustomAttrs,
                                      Boolean.valueOf (bNewDisabled));

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserUpdated (aUser));

    return EChange.CHANGED;
  }

  /**
   * Change the modifiable data of a user
   *
   * @param sUserID
   *        The ID of the user to be modified. May be <code>null</code>.
   * @param sNewPlainTextPassword
   *        The new password in plain text. May not be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  public EChange setUserPassword (@Nullable final String sUserID, @Nonnull final String sNewPlainTextPassword)
  {
    // Resolve user
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "password");
      return EChange.UNCHANGED;
    }

    // Create a new password salt upon password change
    final PasswordHash aPasswordHash = GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                             sNewPlainTextPassword);
    m_aRWLock.writeLock ().lock ();
    try
    {
      final EChange eChange = aUser.setPasswordHash (aPasswordHash);
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUser);
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "password", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserPasswordChanged (aUser));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange updateUserLastLogin (@Nullable final String sUserID)
  {
    // Resolve user
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "update-last-login");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aUser.onSuccessfulLogin ();
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "update-last-login", sUserID);
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange updateUserLastFailedLogin (@Nullable final String sUserID)
  {
    // Resolve user
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "update-last-failed-login");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aUser.onFailedLogin ();
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "update-last-failed-login", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserLastFailedLoginUpdated (aUser));

    return EChange.CHANGED;
  }

  /**
   * Delete the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to delete
   * @return {@link EChange#CHANGED} if the user was deleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public EChange deleteUser (@Nullable final String sUserID)
  {
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditDeleteFailure (User.OT, "no-such-user-id", sUserID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aUser).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (User.OT, "already-deleted", sUserID);
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (User.OT, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserDeleted (aUser));

    return EChange.CHANGED;
  }

  /**
   * Undelete the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to undelete
   * @return {@link EChange#CHANGED} if the user was undeleted,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public EChange undeleteUser (@Nullable final String sUserID)
  {
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditUndeleteFailure (User.OT, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setUndeletionNow (aUser).isUnchanged ())
        return EChange.UNCHANGED;
      internalMarkItemUndeleted (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditUndeleteSuccess (User.OT, sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserUndeleted (aUser));

    return EChange.CHANGED;
  }

  /**
   * disable the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to disable
   * @return {@link EChange#CHANGED} if the user was disabled,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public EChange disableUser (@Nullable final String sUserID)
  {
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "disable");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUser.setDisabled (true).isUnchanged ())
        return EChange.UNCHANGED;
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "disable", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserEnabled (aUser, false));

    return EChange.CHANGED;
  }

  /**
   * Enable the user with the specified ID.
   *
   * @param sUserID
   *        The ID of the user to enable
   * @return {@link EChange#CHANGED} if the user was enabled,
   *         {@link EChange#UNCHANGED} otherwise.
   */
  @Nonnull
  public EChange enableUser (@Nullable final String sUserID)
  {
    final User aUser = getOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "enable");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUser.setDisabled (false).isUnchanged ())
        return EChange.UNCHANGED;
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "enable", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserEnabled (aUser, true));

    return EChange.CHANGED;
  }

  /**
   * Check if the passed combination of user ID and password matches.
   *
   * @param sUserID
   *        The ID of the user
   * @param sPlainTextPassword
   *        The plan text password to validate.
   * @return <code>true</code> if the password hash matches the stored hash for
   *         the specified user, <code>false</code> otherwise.
   */
  public boolean areUserIDAndPasswordValid (@Nullable final String sUserID, @Nullable final String sPlainTextPassword)
  {
    // No password is not allowed
    if (sPlainTextPassword == null)
      return false;

    // Is there such a user?
    final IUser aUser = getOfID (sUserID);
    if (aUser == null)
      return false;

    // Now compare the hashes
    final String sPasswordHashAlgorithm = aUser.getPasswordHash ().getAlgorithmName ();
    final IPasswordSalt aSalt = aUser.getPasswordHash ().getSalt ();
    final PasswordHash aPasswordHash = GlobalPasswordSettings.createUserPasswordHash (sPasswordHashAlgorithm,
                                                                                      aSalt,
                                                                                      sPlainTextPassword);
    return aUser.getPasswordHash ().equals (aPasswordHash);
  }
}
