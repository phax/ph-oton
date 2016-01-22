/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.IsLocked;
import com.helger.commons.annotation.MustBeLocked;
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
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.password.hash.PasswordHash;
import com.helger.photon.security.password.salt.IPasswordSalt;
import com.helger.photon.security.password.salt.PasswordSalt;

/**
 * This class manages the available users.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserManager extends AbstractSimpleDAO implements IReloadableDAO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (UserManager.class);

  @GuardedBy ("m_aRWLock")
  private final Map <String, User> m_aUsers = new HashMap <> ();

  private final CallbackList <IUserModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLockedThrowing ( () -> {
      m_aUsers.clear ();
      initialRead ();
    });
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eUser : aDoc.getDocumentElement ().getAllChildElements ())
      _addUser (MicroTypeConverter.convertToNative (eUser, User.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("users");
    for (final User aUser : CollectionHelper.getSortedByKey (m_aUsers).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aUser, "user"));
    return aDoc;
  }

  public void createDefaults ()
  {
    // Create Administrator
    if (!containsUserWithID (CSecurity.USER_ADMINISTRATOR_ID))
      _addUser (new User (CSecurity.USER_ADMINISTRATOR_ID,
                          CSecurity.USER_ADMINISTRATOR_LOGIN,
                          CSecurity.USER_ADMINISTRATOR_EMAIL,
                          GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                CSecurity.USER_ADMINISTRATOR_PASSWORD),
                          CSecurity.USER_ADMINISTRATOR_NAME,
                          (String) null,
                          (String) null,
                          (Locale) null,
                          (Map <String, String>) null,
                          false));

    // Create regular user
    if (!containsUserWithID (CSecurity.USER_USER_ID))
      _addUser (new User (CSecurity.USER_USER_ID,
                          CSecurity.USER_USER_LOGIN,
                          CSecurity.USER_USER_EMAIL,
                          GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                CSecurity.USER_USER_PASSWORD),
                          CSecurity.USER_USER_NAME,
                          (String) null,
                          (String) null,
                          (Locale) null,
                          (Map <String, String>) null,
                          false));

    // Create guest user
    if (!containsUserWithID (CSecurity.USER_GUEST_ID))
      _addUser (new User (CSecurity.USER_GUEST_ID,
                          CSecurity.USER_GUEST_LOGIN,
                          CSecurity.USER_GUEST_EMAIL,
                          GlobalPasswordSettings.createUserDefaultPasswordHash (new PasswordSalt (),
                                                                                CSecurity.USER_GUEST_PASSWORD),
                          CSecurity.USER_GUEST_NAME,
                          (String) null,
                          (String) null,
                          (Locale) null,
                          (Map <String, String>) null,
                          false));
  }

  /**
   * @return The user callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IUserModificationCallback> getUserModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @MustBeLocked (ELockType.WRITE)
  private void _addUser (@Nonnull final User aUser)
  {
    final String sUserID = aUser.getID ();
    if (m_aUsers.containsKey (sUserID))
      throw new IllegalArgumentException ("User ID " + sUserID + " is already in use!");
    m_aUsers.put (sUserID, aUser);
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
      _addUser (aUser);
      markAsChanged ();
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
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserCreated (aUser, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserCreated callback on " + aUser.toString (), t);
      }

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
      _addUser (aUser);
      markAsChanged ();
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
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserCreated (aUser, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserCreated callback on " + aUser.toString (), t);
      }

    return aUser;
  }

  /**
   * Check if a user with the specified ID is present.
   *
   * @param sUserID
   *        The user ID to resolve. May be <code>null</code>.
   * @return <code>true</code> if such user exists, <code>false</code>
   *         otherwise.
   */
  public boolean containsUserWithID (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return false;

    return m_aRWLock.readLocked ( () -> m_aUsers.containsKey (sUserID));
  }

  @Nullable
  @IsLocked (ELockType.READ)
  private User _getUserOfID (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return null;

    return m_aRWLock.readLocked ( () -> m_aUsers.get (sUserID));
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
    // Change return type
    return _getUserOfID (sUserID);
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

    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirst (m_aUsers.values (),
                                                                    aUser -> aUser.getLoginName ()
                                                                                  .equals (sLoginName)));
  }

  /**
   * Get the user with the specified email address
   *
   * @param sEmailAddress
   *        The email address to be checked. May be <code>null</code>.
   * @return <code>null</code> if no such user exists
   */
  @Nullable
  public IUser getUserOfEmailAddress (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    return m_aRWLock.readLocked ( () -> CollectionHelper.findFirst (m_aUsers.values (),
                                                                    aUser -> sEmailAddress.equals (aUser.getEmailAddress ())));
  }

  /**
   * @return A non-<code>null</code> collection of all contained users
   *         (enabled+disabled and deleted+not-deleted)
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllUsers ()
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.newList (m_aUsers.values ()));
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllUsers (@Nullable final Predicate <IUser> aFilter)
  {
    if (aFilter == null)
      return getAllUsers ();

    return m_aRWLock.readLocked ( () -> CollectionHelper.getAll (m_aUsers.values (), aFilter));
  }

  @Nonnegative
  public int getUserCount (@Nullable final Predicate <IUser> aFilter)
  {
    if (aFilter == null)
      return m_aRWLock.readLocked ( () -> m_aUsers.size ());

    return m_aRWLock.readLocked ( () -> CollectionHelper.getCount (m_aUsers.values (), aFilter));
  }

  /**
   * @return A non-<code>null</code> collection of all contained enabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllActiveUsers ()
  {
    return getAllUsers (aUser -> !aUser.isDeleted () && aUser.isEnabled ());
  }

  /**
   * @return The number of all contained enabled and not-deleted users
   */
  @Nonnegative
  public int getActiveUserCount ()
  {
    return getUserCount (aUser -> !aUser.isDeleted () && aUser.isEnabled ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained disabled and
   *         not-deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllDisabledUsers ()
  {
    return getAllUsers (aUser -> !aUser.isDeleted () && aUser.isDisabled ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained not deleted
   *         users
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllNotDeletedUsers ()
  {
    return getAllUsers (aUser -> !aUser.isDeleted ());
  }

  /**
   * @return A non-<code>null</code> collection of all contained deleted users
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllDeletedUsers ()
  {
    return getAllUsers (aUser -> aUser.isDeleted ());
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
    final User aUser = _getUserOfID (sUserID);
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
      eChange = eChange.or (aUser.getMutableAttributes ().clear ());
      eChange = eChange.or (aUser.getMutableAttributes ().setAttributes (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aUser);
      markAsChanged ();
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
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserUpdated (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserUpdated callback on " + aUser.toString (), t);
      }

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
    final User aUser = _getUserOfID (sUserID);
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

      ObjectHelper.setLastModificationNow (aUser);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "password", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserPasswordChanged (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserPasswordChanged callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange updateUserLastLogin (@Nullable final String sUserID)
  {
    // Resolve user
    final User aUser = _getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "update-last-login");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aUser.onSuccessfulLogin ();
      markAsChanged ();
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
    final User aUser = _getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditModifyFailure (User.OT, sUserID, "no-such-user-id", "update-last-failed-login");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aUser.onFailedLogin ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "update-last-failed-login", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserLastFailedLoginUpdated (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserLastFailedLoginUpdated callback on " + aUser.toString (), t);
      }

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
    final User aUser = _getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditDeleteFailure (User.OT, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setDeletionNow (aUser).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (User.OT, sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserDeleted (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserDeleted callback on " + aUser.toString (), t);
      }

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
    final User aUser = _getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditHelper.onAuditUndeleteFailure (User.OT, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setUndeletionNow (aUser).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditUndeleteSuccess (User.OT, sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserUndeleted (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserUndeleted callback on " + aUser.toString (), t);
      }

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
    final User aUser = _getUserOfID (sUserID);
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
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "disable", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserEnabled (aUser, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserEnabled callback on " + aUser.toString (), t);
      }

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
    final User aUser = _getUserOfID (sUserID);
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
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (User.OT, "enable", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onUserEnabled (aUser, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserEnabled callback on " + aUser.toString (), t);
      }

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
    final IUser aUser = getUserOfID (sUserID);
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
