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
package com.helger.photon.basic.security.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.IReloadableDAO;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.security.CSecurity;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.photon.basic.security.password.GlobalPasswordSettings;
import com.helger.photon.basic.security.password.hash.PasswordHash;
import com.helger.photon.basic.security.password.salt.IPasswordSalt;
import com.helger.photon.basic.security.password.salt.PasswordSalt;
import com.helger.photon.basic.security.user.callback.IUserModificationCallback;

/**
 * This class manages the available users.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserManager extends AbstractSimpleDAO implements IUserManager, IReloadableDAO
{
  public static final boolean DEFAULT_CREATE_DEFAULTS = true;

  private static final Logger s_aLogger = LoggerFactory.getLogger (UserManager.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static boolean s_bCreateDefaults = DEFAULT_CREATE_DEFAULTS;

  @GuardedBy ("m_aRWLock")
  private final Map <String, User> m_aUsers = new HashMap <String, User> ();

  private final CallbackList <IUserModificationCallback> m_aUserCallbacks = new CallbackList <IUserModificationCallback> ();

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

  public UserManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aUsers.clear ();
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

    // Create Administrator
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
    return EChange.CHANGED;
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

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IUserModificationCallback> getUserModificationCallbacks ()
  {
    return m_aUserCallbacks;
  }

  private void _addUser (@Nonnull final User aUser)
  {
    final String sUserID = aUser.getID ();
    if (m_aUsers.containsKey (sUserID))
      throw new IllegalArgumentException ("User ID " + sUserID + " is already in use!");
    m_aUsers.put (sUserID, aUser);
  }

  @Nullable
  public IUser createNewUser (@Nonnull @Nonempty final String sLoginName,
                              @Nullable final String sEmailAddress,
                              @Nonnull final String sPlainTextPassword,
                              @Nullable final String sFirstName,
                              @Nullable final String sLastName,
                              @Nullable final String sDescription,
                              @Nullable final Locale aDesiredLocale,
                              @Nullable final Map <String, ?> aCustomAttrs,
                              final boolean bDisabled)
  {
    ValueEnforcer.notEmpty (sLoginName, "LoginName");
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    if (getUserOfLoginName (sLoginName) != null)
    {
      // Another user with this login name already exists
      AuditUtils.onAuditCreateFailure (CSecurity.TYPE_USER, "login-name-already-in-use", sLoginName);
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addUser (aUser);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditCreateSuccess (CSecurity.TYPE_USER,
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
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserCreated (aUser, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserCreated callback on " + aUser.toString (), t);
      }

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
                                     @Nullable final Map <String, ?> aCustomAttrs,
                                     final boolean bDisabled)
  {
    ValueEnforcer.notEmpty (sLoginName, "LoginName");
    ValueEnforcer.notNull (sPlainTextPassword, "PlainTextPassword");

    if (getUserOfLoginName (sLoginName) != null)
    {
      // Another user with this login name already exists
      AuditUtils.onAuditCreateFailure (CSecurity.TYPE_USER, "login-name-already-in-use", sLoginName, "predefined-user");
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addUser (aUser);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditCreateSuccess (CSecurity.TYPE_USER,
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
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserCreated (aUser, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserCreated callback on " + aUser.toString (), t);
      }

    return aUser;
  }

  public boolean containsUserWithID (@Nullable final String sUserID)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aUsers.containsKey (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public User getUserOfID (@Nullable final String sUserID)
  {
    if (StringHelper.hasNoText (sUserID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aUsers.get (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IUser getUserOfLoginName (@Nullable final String sLoginName)
  {
    if (StringHelper.hasNoText (sLoginName))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final User aUser : m_aUsers.values ())
        if (aUser.getLoginName ().equals (sLoginName))
          return aUser;
      return null;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IUser getUserOfEmailAddress (@Nullable final String sEmailAddress)
  {
    if (StringHelper.hasNoText (sEmailAddress))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final User aUser : m_aUsers.values ())
        if (sEmailAddress.equals (aUser.getEmailAddress ()))
          return aUser;
      return null;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllUsers ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aUsers.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllActiveUsers ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <User> ret = new ArrayList <User> ();
      for (final User aUser : m_aUsers.values ())
        if (!aUser.isDeleted () && aUser.isEnabled ())
          ret.add (aUser);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllDisabledUsers ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <User> ret = new ArrayList <User> ();
      for (final User aUser : m_aUsers.values ())
        if (!aUser.isDeleted () && aUser.isDisabled ())
          ret.add (aUser);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllNotDeletedUsers ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <User> ret = new ArrayList <User> ();
      for (final User aUser : m_aUsers.values ())
        if (!aUser.isDeleted ())
          ret.add (aUser);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <User> getAllDeletedUsers ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <User> ret = new ArrayList <User> ();
      for (final User aUser : m_aUsers.values ())
        if (aUser.isDeleted ())
          ret.add (aUser);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public EChange setUserData (@Nullable final String sUserID,
                              @Nonnull @Nonempty final String sNewLoginName,
                              @Nullable final String sNewEmailAddress,
                              @Nullable final String sNewFirstName,
                              @Nullable final String sNewLastName,
                              @Nullable final String sNewDescription,
                              @Nullable final Locale aNewDesiredLocale,
                              @Nullable final Map <String, ?> aNewCustomAttrs,
                              final boolean bNewDisabled)
  {
    // Resolve user
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id");
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
      eChange = eChange.or (aUser.setAttributes (aNewCustomAttrs));
      eChange = eChange.or (aUser.setDisabled (bNewDisabled));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      aUser.updateLastModified ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER,
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
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserUpdated (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserUpdated callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange setUserPassword (@Nullable final String sUserID, @Nonnull final String sNewPlainTextPassword)
  {
    // Resolve user
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id", "password");
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

      aUser.updateLastModified ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER, "password", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserPasswordChanged (aUser);
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
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id", "update-last-login");
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
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER, "update-last-login", sUserID);
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange updateUserLastFailedLogin (@Nullable final String sUserID)
  {
    // Resolve user
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id", "update-last-failed-login");
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
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER, "update-last-failed-login", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserLastFailedLoginUpdated (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserLastFailedLoginUpdated callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteUser (@Nullable final String sUserID)
  {
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditDeleteFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUser.setDeleted (true).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditDeleteSuccess (CSecurity.TYPE_USER, sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserDeleted (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserDeleted callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange undeleteUser (@Nullable final String sUserID)
  {
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditUndeleteFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUser.setDeleted (false).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditUtils.onAuditUndeleteSuccess (CSecurity.TYPE_USER, sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserUndeleted (aUser);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserUndeleted callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange disableUser (@Nullable final String sUserID)
  {
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id", "disable");
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
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER, "disable", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserEnabled (aUser, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserEnabled callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange enableUser (@Nullable final String sUserID)
  {
    final User aUser = getUserOfID (sUserID);
    if (aUser == null)
    {
      AuditUtils.onAuditModifyFailure (CSecurity.TYPE_USER, sUserID, "no-such-user-id", "enable");
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
    AuditUtils.onAuditModifySuccess (CSecurity.TYPE_USER, "enable", sUserID);

    // Execute callback as the very last action
    for (final IUserModificationCallback aUserCallback : m_aUserCallbacks.getAllCallbacks ())
      try
      {
        aUserCallback.onUserEnabled (aUser, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserEnabled callback on " + aUser.toString (), t);
      }

    return EChange.CHANGED;
  }

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
