/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.helger.security.password.salt.PasswordSalt;

/**
 * This class manages the available users.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserManager extends AbstractPhotonMapBasedWALDAO <IUser, User> implements IUserManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (UserManager.class);

  private final CallbackList <IUserModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (User.class, sFilename);
  }

  @Nonnull
  public static User createDefaultUserAdministrator ()
  {
    return User.createdPredefinedUser (CSecurity.USER_ADMINISTRATOR_ID,
                                       CSecurity.USER_ADMINISTRATOR_LOGIN,
                                       CSecurity.USER_ADMINISTRATOR_EMAIL,
                                       GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                             CSecurity.USER_ADMINISTRATOR_PASSWORD),
                                       CSecurity.USER_ADMINISTRATOR_NAME,
                                       (String) null,
                                       (String) null,
                                       (Locale) null,
                                       (Map <String, String>) null,
                                       false);
  }

  @Nonnull
  public static User createDefaultUserUser ()
  {
    return User.createdPredefinedUser (CSecurity.USER_USER_ID,
                                       CSecurity.USER_USER_LOGIN,
                                       CSecurity.USER_USER_EMAIL,
                                       GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                             CSecurity.USER_USER_PASSWORD),
                                       CSecurity.USER_USER_NAME,
                                       (String) null,
                                       (String) null,
                                       (Locale) null,
                                       (Map <String, String>) null,
                                       false);
  }

  @Nonnull
  public static User createDefaultUserGuest ()
  {
    return User.createdPredefinedUser (CSecurity.USER_GUEST_ID,
                                       CSecurity.USER_GUEST_LOGIN,
                                       CSecurity.USER_GUEST_EMAIL,
                                       GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                             CSecurity.USER_GUEST_PASSWORD),
                                       CSecurity.USER_GUEST_NAME,
                                       (String) null,
                                       (String) null,
                                       (Locale) null,
                                       (Map <String, String>) null,
                                       false);
  }

  public void createDefaultsForTest ()
  {
    // Create Administrator
    if (!containsWithID (CSecurity.USER_ADMINISTRATOR_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (createDefaultUserAdministrator ()));

    // Create regular user
    if (!containsWithID (CSecurity.USER_USER_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (createDefaultUserUser ()));

    // Create guest user
    if (!containsWithID (CSecurity.USER_GUEST_ID))
      m_aRWLock.writeLocked ( () -> internalCreateItem (createDefaultUserGuest ()));
  }

  @Nonnull
  @ReturnsMutableObject
  public final CallbackList <IUserModificationCallback> userModificationCallbacks ()
  {
    return m_aCallbacks;
  }

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
                                 GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (), sPlainTextPassword),
                                 sFirstName,
                                 sLastName,
                                 sDescription,
                                 aDesiredLocale,
                                 aCustomAttrs,
                                 bDisabled);

    // Store
    m_aRWLock.writeLocked ( () -> internalCreateItem (aUser));
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
    final User aUser = User.createdPredefinedUser (sID,
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

    // Store
    m_aRWLock.writeLocked ( () -> internalCreateItem (aUser));

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

  @Nullable
  public IUser getUserOfID (@Nullable final String sUserID)
  {
    return getOfID (sUserID);
  }

  @Nullable
  public IUser getUserOfLoginName (@Nullable final String sLoginName)
  {
    if (StringHelper.hasNoText (sLoginName))
      return null;

    return findFirst (x -> x.getLoginName ().equals (sLoginName));
  }

  @Nullable
  public IUser getUserOfEmailAddress (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    return findFirst (x -> sEmailAddress.equals (x.getEmailAddress ()));
  }

  @Nullable
  public IUser getUserOfEmailAddressIgnoreCase (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    return findFirst (x -> sEmailAddress.equalsIgnoreCase (x.getEmailAddress ()));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllActiveUsers ()
  {
    return getAll (x -> x.isNotDeleted () && x.isEnabled ());
  }

  @Nonnegative
  public long getActiveUserCount ()
  {
    return getCount (x -> x.isNotDeleted () && x.isEnabled ());
  }

  public boolean containsAnyActiveUser ()
  {
    return containsAny (x -> x.isNotDeleted () && x.isEnabled ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllDisabledUsers ()
  {
    return getAll (x -> x.isNotDeleted () && x.isDisabled ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllNotDeletedUsers ()
  {
    return getAll (IUser::isNotDeleted);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUser> getAllDeletedUsers ()
  {
    return getAll (IUser::isDeleted);
  }

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
                                      sUserID,
                                      sNewLoginName,
                                      sNewEmailAddress,
                                      sNewFirstName,
                                      sNewLastName,
                                      sNewDescription,
                                      aNewDesiredLocale,
                                      aNewCustomAttrs,
                                      Boolean.valueOf (bNewDisabled));

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserUpdated (sUserID));

    return EChange.CHANGED;
  }

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
    final PasswordHash aPasswordHash = GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (), sNewPlainTextPassword);
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

    if (LOGGER.isInfoEnabled ())
      LOGGER.info ("The password of user '" + sUserID + "' was changed");

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserPasswordChanged (sUserID));

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
    m_aCallbacks.forEach (aCB -> aCB.onUserLastFailedLoginUpdated (sUserID));

    return EChange.CHANGED;
  }

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
    m_aCallbacks.forEach (aCB -> aCB.onUserDeleted (sUserID));

    return EChange.CHANGED;
  }

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
    m_aCallbacks.forEach (aCB -> aCB.onUserUndeleted (sUserID));

    return EChange.CHANGED;
  }

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

      BusinessObjectHelper.setLastModificationNow (aUser);
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "disable", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserEnabled (sUserID, false));

    return EChange.CHANGED;
  }

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

      BusinessObjectHelper.setLastModificationNow (aUser);
      internalUpdateItem (aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "enable", sUserID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserEnabled (sUserID, true));

    return EChange.CHANGED;
  }
}
