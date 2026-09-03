/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.callback.CallbackList;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.equals.EqualsHelper;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsCollection;
import com.helger.collection.commons.ICommonsMap;
import com.helger.collection.commons.ICommonsSet;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.lock.ObjectLockManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.scope.IScope;
import com.helger.scope.ISessionScope;
import com.helger.scope.mgr.ScopeManager;
import com.helger.scope.singleton.AbstractGlobalSingleton;
import com.helger.security.authentication.subject.user.ICurrentUserIDProvider;
import com.helger.security.password.salt.PasswordSalt;
import com.helger.web.scope.ISessionWebScope;
import com.helger.web.scope.session.ISessionWebScopeActivationHandler;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * This class manages all logged-in users.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class LoggedInUserManager extends AbstractGlobalSingleton implements ICurrentUserIDProvider
{
  /**
   * This class manages the user ID of the current session. This is an internal class and should not
   * be used from the outside!
   *
   * @author Philip Helger
   */
  public static final class InternalSessionUserHolder extends AbstractSessionWebSingleton implements
                                                      ISessionWebScopeActivationHandler
  {
    private IUser m_aUser;
    private String m_sUserID;
    private LoggedInUserManager m_aOwningMgr;

    @Deprecated (forRemoval = false)
    @UsedViaReflection
    public InternalSessionUserHolder ()
    {}

    /**
     * @return The instance of the current session. If none exists, an instance is created. Never
     *         <code>null</code>.
     */
    @NonNull
    private static InternalSessionUserHolder _getInstance ()
    {
      return getSessionSingleton (InternalSessionUserHolder.class);
    }

    /**
     * @return The instance of the current session. If none exists, <code>null</code> is returned.
     */
    @Nullable
    private static InternalSessionUserHolder _getInstanceIfInstantiated ()
    {
      return getSessionSingletonIfInstantiated (InternalSessionUserHolder.class);
    }

    @Nullable
    private static InternalSessionUserHolder _getInstanceIfInstantiatedInScope (@Nullable final ISessionScope aScope)
    {
      return getSingletonIfInstantiated (aScope, InternalSessionUserHolder.class);
    }

    private void readObject (@NonNull final ObjectInputStream aOIS) throws IOException, ClassNotFoundException
    {
      aOIS.defaultReadObject ();

      // Resolve user ID
      if (m_sUserID != null)
      {
        m_aUser = PhotonSecurityManager.getUserMgr ().getUserOfID (m_sUserID);
        if (m_aUser == null)
        {
          // The user was removed while the session was passivated. Don't fail
          // the whole session deserialization - simply don't log him in again.
          LOGGER.warn ("Failed to resolve user with ID '" + m_sUserID + "' - not logging him in again");
          m_sUserID = null;
        }
      }
      // Resolve manager
      m_aOwningMgr = LoggedInUserManager.getInstance ();
    }

    public void onSessionDidActivate (@NonNull final ISessionWebScope aSessionScope)
    {
      // Finally remember that the user is logged in.
      // Note: the user state may have changed while the session was passivated,
      // so this may reject the user and reset this holder
      if (m_aUser != null)
        if (m_aOwningMgr.internalSessionActivateUser (m_aUser, aSessionScope).isFailure ())
          _reset ();
    }

    private boolean _hasUser ()
    {
      return m_aUser != null;
    }

    @Nullable
    private String _getUserID ()
    {
      return m_sUserID;
    }

    private void _setUser (@NonNull final LoggedInUserManager aOwningMgr, @NonNull final IUser aUser)
    {
      ValueEnforcer.notNull (aOwningMgr, "OwningMgr");
      ValueEnforcer.notNull (aUser, "User");
      if (m_aUser != null)
        throw new IllegalStateException ("Session already has a user!");

      m_aOwningMgr = aOwningMgr;
      m_aUser = aUser;
      m_sUserID = aUser.getID ();
    }

    private void _reset ()
    {
      // Reset to avoid access while or after logout
      m_aUser = null;
      m_sUserID = null;
      m_aOwningMgr = null;
    }

    @Override
    protected void onDestroy (@NonNull final IScope aScopeInDestruction)
    {
      // Called when the session is destroyed
      // -> Ensure the user is logged out!

      // Remember stuff
      final LoggedInUserManager aOwningMgr = m_aOwningMgr;
      final String sUserID = m_sUserID;

      _reset ();

      // Finally logout the user
      if (aOwningMgr != null)
        aOwningMgr.logoutUser (sUserID);
    }

    @Override
    public String toString ()
    {
      return ToStringGenerator.getDerived (super.toString ()).append ("UserID", m_sUserID).getToString ();
    }
  }

  /**
   * Special logout callback that is executed every time a user logs out. It removes all objects
   * from the {@link ObjectLockManager}.
   *
   * @author Philip Helger
   */
  static final class InternalUserLogoutCallbackUnlockAllObjects implements IUserLogoutCallback
  {
    @Override
    public void onUserLogout (@NonNull final LoginInfo aInfo)
    {
      final ObjectLockManager aOLMgr = ObjectLockManager.getInstanceIfInstantiated ();
      if (aOLMgr != null)
        aOLMgr.getDefaultLockMgr ().unlockAllObjectsOfUser (aInfo.getUserID ());
    }
  }

  public static final boolean DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER = false;
  public static final boolean DEFAULT_ANONYMOUS_LOGGING = false;

  private static final Logger LOGGER = LoggerFactory.getLogger (LoggedInUserManager.class);

  /**
   * Contains the ID of the user for which the password hash is currently upgraded to the default
   * algorithm as part of {@link #loginUser(IUser, String, Iterable)}. Used to distinguish an
   * internal re-hashing from a real password change.
   */
  private static final ThreadLocal <String> PASSWORD_HASH_UPGRADE = new ThreadLocal <> ();

  // Set of logged in user IDs
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, LoginInfo> m_aLoggedInUsers = new CommonsHashMap <> ();
  private final CallbackList <IUserLoginCallback> m_aUserLoginCallbacks = new CallbackList <> ();
  private final CallbackList <IUserLogoutCallback> m_aUserLogoutCallbacks = new CallbackList <> ();
  private boolean m_bLogoutAlreadyLoggedInUser = DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER;
  private boolean m_bAnonymousLogging = DEFAULT_ANONYMOUS_LOGGING;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public LoggedInUserManager ()
  {
    // Ensure that all objects of a user are unlocked upon logout
    m_aUserLogoutCallbacks.add (new InternalUserLogoutCallbackUnlockAllObjects ());
  }

  /**
   * @return The global instance of this class. Never <code>null</code>.
   */
  @NonNull
  public static LoggedInUserManager getInstance ()
  {
    return getGlobalSingleton (LoggedInUserManager.class);
  }

  /**
   * @return The global instance of this class, but only if it was already instantiated.
   *         <code>null</code> if it was not yet instantiated or if no global scope is present (e.g.
   *         while the global scope is being destroyed).
   * @since 10.5.0
   */
  @Nullable
  public static LoggedInUserManager getInstanceIfInstantiated ()
  {
    return getGlobalSingletonIfInstantiated (LoggedInUserManager.class);
  }

  /**
   * Check if the password of the provided user is currently only re-hashed with the default
   * password hash algorithm as part of a login. This is an internal method for
   * {@link UserModificationLogoutCallback} only.
   *
   * @param sUserID
   *        The user ID to check. May be <code>null</code>.
   * @return <code>true</code> if the password hash of that user is currently upgraded in this
   *         thread, <code>false</code> otherwise.
   */
  static boolean internalIsPasswordHashUpgradeInProgress (@Nullable final String sUserID)
  {
    return sUserID != null && sUserID.equals (PASSWORD_HASH_UPGRADE.get ());
  }

  /**
   * @return The user login callback list. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public CallbackList <IUserLoginCallback> userLoginCallbacks ()
  {
    return m_aUserLoginCallbacks;
  }

  /**
   * @return The user logout callback list. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableObject
  public CallbackList <IUserLogoutCallback> userLogoutCallbacks ()
  {
    return m_aUserLogoutCallbacks;
  }

  /**
   * @return <code>true</code> if a new login of a user, destroys any previously present session,
   *         <code>false</code> if a login should fail, if that user is already logged in.
   */
  public boolean isLogoutAlreadyLoggedInUser ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_bLogoutAlreadyLoggedInUser);
  }

  public void setLogoutAlreadyLoggedInUser (final boolean bLogoutAlreadyLoggedInUser)
  {
    m_aRWLock.writeLocked ( () -> m_bLogoutAlreadyLoggedInUser = bLogoutAlreadyLoggedInUser);
  }

  public boolean isAnonymousLogging ()
  {
    return m_aRWLock.readLockedBoolean ( () -> m_bAnonymousLogging);
  }

  public void setAnonymousLogging (final boolean bAnonymousLogging)
  {
    m_aRWLock.writeLocked ( () -> m_bAnonymousLogging = bAnonymousLogging);
  }

  @NonNull
  private String _getUserIDLogText (@Nullable final String sUserID)
  {
    if (isAnonymousLogging ())
      return "a user";
    return "user '" + sUserID + "'";
  }

  @NonNull
  private ELoginResult _onLoginError (@NonNull @Nonempty final String sUserID, @NonNull final ELoginResult eLoginResult)
  {
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLoginError (sUserID, eLoginResult));
    return eLoginResult;
  }

  /**
   * Create a throw-away password hash, to consume roughly the same amount of CPU time as a real
   * password check would. This is used if no user could be resolved, so that the response time does
   * not disclose whether a login name exists or not (user enumeration).
   *
   * @param sPlainTextPassword
   *        The plain text password provided by the caller. May be <code>null</code>.
   */
  private static void _consumePasswordHashingTime (@Nullable final String sPlainTextPassword)
  {
    try
    {
      GlobalPasswordSettings.createUserDefaultPasswordHash (PasswordSalt.createRandom (),
                                                            StringHelper.getNotNull (sPlainTextPassword));
    }
    catch (final RuntimeException ex)
    {
      // Never let this influence the outcome of the login
      LOGGER.warn ("Failed to create the dummy password hash", ex);
    }
  }

  /**
   * Re-establish the login of a user, after the session containing him was activated (e.g. after an
   * application server restart with session persistence, or after a fail over in a cluster). The
   * state of the user may have changed while the session was passivated, so the same basic checks
   * as in {@link #loginUser(IUser, String, Iterable)} are performed - except for the password check,
   * because no credentials are available at this point in time.
   *
   * @param aUser
   *        The user to be logged in again. May not be <code>null</code>.
   * @param aSessionScope
   *        The activated session scope. May not be <code>null</code>.
   * @return {@link ELoginResult#SUCCESS} if the user was logged in again, the respective error code
   *         otherwise. Never <code>null</code>.
   */
  @NonNull
  ELoginResult internalSessionActivateUser (@NonNull final IUser aUser, @NonNull final ISessionScope aSessionScope)
  {
    ValueEnforcer.notNull (aUser, "User");
    ValueEnforcer.notNull (aSessionScope, "SessionScope");

    final String sUserID = aUser.getID ();

    // Deleted user? (may have been deleted while the session was passivated)
    if (aUser.isDeleted ())
    {
      LOGGER.warn ("Not re-activating " + _getUserIDLogText (sUserID) + " because the user is deleted");
      AuditHelper.onAuditExecuteFailure ("session-activate-login", sUserID, "user-is-deleted");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DELETED);
    }
    // Disabled user? (may have been disabled while the session was passivated)
    if (aUser.isDisabled ())
    {
      LOGGER.warn ("Not re-activating " + _getUserIDLogText (sUserID) + " because the user is disabled");
      AuditHelper.onAuditExecuteFailure ("session-activate-login", sUserID, "user-is-disabled");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DISABLED);
    }

    final LoginInfo aInfo = new LoginInfo (aUser, aSessionScope);
    final boolean bAdded = m_aRWLock.writeLockedBoolean ( () -> {
      if (m_aLoggedInUsers.containsKey (sUserID))
      {
        // The user is already logged in somewhere else
        return false;
      }
      m_aLoggedInUsers.put (sUserID, aInfo);
      return true;
    });
    if (!bAdded)
    {
      LOGGER.warn ("Not re-activating " + _getUserIDLogText (sUserID) + " because the user is already logged in");
      AuditHelper.onAuditExecuteFailure ("session-activate-login", sUserID, "user-already-logged-in");
      return _onLoginError (sUserID, ELoginResult.USER_ALREADY_LOGGED_IN);
    }

    AuditHelper.onAuditExecuteSuccess ("session-activate-login", sUserID);

    // Execute callback as the very last action
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLogin (aInfo));

    return ELoginResult.SUCCESS;
  }

  /**
   * Login the passed user without much ado.
   *
   * @param sLoginName
   *        Login name of the user to log-in. May be <code>null</code>.
   * @param sPlainTextPassword
   *        Plain text password to use. May be <code>null</code>.
   * @return Never <code>null</code> login status.
   */
  @NonNull
  public ELoginResult loginUser (@Nullable final String sLoginName, @Nullable final String sPlainTextPassword)
  {
    return loginUser (sLoginName, sPlainTextPassword, (Iterable <String>) null);
  }

  /**
   * Login the passed user and require a set of certain roles, the used needs to have to login here.
   *
   * @param sLoginName
   *        Login name of the user to log-in. May be <code>null</code>.
   * @param sPlainTextPassword
   *        Plain text password to use. May be <code>null</code>.
   * @param aRequiredRoleIDs
   *        A set of required role IDs, the user needs to have. May be <code>null</code>.
   * @return Never <code>null</code> login status.
   */
  @NonNull
  public ELoginResult loginUser (@Nullable final String sLoginName,
                                 @Nullable final String sPlainTextPassword,
                                 @Nullable final Iterable <String> aRequiredRoleIDs)
  {
    // Try to resolve the user
    final IUser aUser = PhotonSecurityManager.getUserMgr ().getUserOfLoginName (sLoginName);
    if (aUser == null)
    {
      AuditHelper.onAuditExecuteFailure ("login", sLoginName, "no-such-loginname");
      return ELoginResult.USER_NOT_EXISTING;
    }
    return loginUser (aUser, sPlainTextPassword, aRequiredRoleIDs);
  }

  /**
   * Login the passed user and require a set of certain roles, the used needs to have to login here.
   *
   * @param aUser
   *        The user to log-in. May be <code>null</code>. When the user is <code>null</code> the
   *        login must fail.
   * @param sPlainTextPassword
   *        Plain text password to use. May be <code>null</code>.
   * @param aRequiredRoleIDs
   *        A set of required role IDs, the user needs to have. May be <code>null</code>.
   * @return Never <code>null</code> login status.
   */
  @NonNull
  public ELoginResult loginUser (@Nullable final IUser aUser,
                                 @Nullable final String sPlainTextPassword,
                                 @Nullable final Iterable <String> aRequiredRoleIDs)
  {
    if (aUser == null)
    {
      // Spend roughly the same amount of time as for an existing user, so that
      // the response time does not disclose whether a user exists or not
      _consumePasswordHashingTime (sPlainTextPassword);
      AuditHelper.onAuditExecuteFailure ("login", "null", "no-such-user");
      return ELoginResult.USER_NOT_EXISTING;
    }

    final String sUserID = aUser.getID ();

    // Deleted user?
    if (aUser.isDeleted ())
    {
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-is-deleted");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DELETED);
    }
    // Disabled user?
    if (aUser.isDisabled ())
    {
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-is-disabled");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DISABLED);
    }
    // Check the password
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    if (!aUserMgr.areUserIDAndPasswordValid (sUserID, sPlainTextPassword))
    {
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "invalid-password");
      return _onLoginError (sUserID, ELoginResult.INVALID_PASSWORD);
    }
    assert sPlainTextPassword != null;

    // Are all roles present?
    if (!SecurityHelper.hasUserAllRoles (sUserID, aRequiredRoleIDs))
    {
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-is-missing-required-roles", aRequiredRoleIDs);
      return _onLoginError (sUserID, ELoginResult.USER_IS_MISSING_ROLE);
    }
    // Check if the password hash needs to be updated
    final String sExistingPasswordHashAlgorithmName = aUser.getPasswordHash ().getAlgorithmName ();
    final String sDefaultPasswordHashAlgorithmName = GlobalPasswordSettings.getPasswordHashCreatorManager ()
                                                                           .getDefaultPasswordHashCreatorAlgorithmName ();
    if (!sExistingPasswordHashAlgorithmName.equals (sDefaultPasswordHashAlgorithmName))
    {
      // This implicitly implies using the default hash creator algorithm
      // This automatically saves the file
      // Note: this is a pure re-hashing and not a real password change, so any
      // registered IUserModificationCallback must not log the user out
      PASSWORD_HASH_UPGRADE.set (sUserID);
      try
      {
        aUserMgr.setUserPassword (sUserID, sPlainTextPassword);
      }
      finally
      {
        PASSWORD_HASH_UPGRADE.remove ();
      }

      LOGGER.info ("Updated password hash of " +
                   _getUserIDLogText (sUserID) +
                   " from algorithm '" +
                   sExistingPasswordHashAlgorithmName +
                   "' to '" +
                   sDefaultPasswordHashAlgorithmName +
                   "'");
    }

    // Check if this session can take a user at all, BEFORE any existing login of
    // that user is destroyed. Use the "if instantiated" version, so that no
    // session is created for a login that is going to fail anyway.
    final InternalSessionUserHolder aExistingSUH = InternalSessionUserHolder._getInstanceIfInstantiated ();
    if (aExistingSUH != null && aExistingSUH._hasUser ())
    {
      // This session already has a user
      LOGGER.warn ("The session user holder already has the user ID '" +
                   aExistingSUH._getUserID () +
                   "' so the new ID '" +
                   sUserID +
                   "' will not be set!");
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "session-already-has-user");
      return _onLoginError (sUserID, ELoginResult.SESSION_ALREADY_HAS_USER);
    }

    // Handle an already logged in user outside of the write lock, so that the
    // logout callbacks are not executed while holding it
    boolean bLoggedOutUser = false;
    if (isUserLoggedIn (sUserID))
    {
      // The user is already logged in
      if (!isLogoutAlreadyLoggedInUser ())
      {
        // Error: user already logged in
        AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-already-logged-in");
        return _onLoginError (sUserID, ELoginResult.USER_ALREADY_LOGGED_IN);
      }
      // Explicitly log out
      logoutUser (sUserID);

      AuditHelper.onAuditExecuteSuccess ("logout-in-login", sUserID);
      bLoggedOutUser = true;
    }

    final LoginInfo aInfo;
    m_aRWLock.writeLock ().lock ();
    try
    {
      // Re-check inside the lock, in case a concurrent login won the race
      if (m_aLoggedInUsers.containsKey (sUserID))
      {
        AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-already-logged-in");
        return _onLoginError (sUserID, ELoginResult.USER_ALREADY_LOGGED_IN);
      }
      // Update user in session
      final InternalSessionUserHolder aSUH = InternalSessionUserHolder._getInstance ();
      if (aSUH._hasUser ())
      {
        // This session already has a user
        LOGGER.warn ("The session user holder already has the user ID '" +
                     aSUH._getUserID () +
                     "' so the new ID '" +
                     sUserID +
                     "' will not be set!");
        AuditHelper.onAuditExecuteFailure ("login", sUserID, "session-already-has-user");
        return _onLoginError (sUserID, ELoginResult.SESSION_ALREADY_HAS_USER);
      }
      aInfo = new LoginInfo (aUser, ScopeManager.getSessionScope ());
      m_aLoggedInUsers.put (sUserID, aInfo);
      aSUH._setUser (this, aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    LOGGER.info ("Logged in " +
                 _getUserIDLogText (sUserID) +
                 (isAnonymousLogging () ? "" : " with login name '" + aUser.getLoginName () + "'"));
    AuditHelper.onAuditExecuteSuccess ("login-user", sUserID, aUser.getLoginName ());

    // Execute callback as the very last action
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLogin (aInfo));

    return bLoggedOutUser ? ELoginResult.SUCCESS_WITH_LOGOUT : ELoginResult.SUCCESS;
  }

  /**
   * Invoked from the login flow after the HTTP session ID has been regenerated to mitigate session
   * fixation. Any {@link LoginInfo} that still references the previous session scope is updated to
   * point at the new one, so that subsequent {@link #logoutUser(String)} calls find the
   * {@link InternalSessionUserHolder} in the correct scope.
   *
   * @param aOldSession
   *        The previous session scope. Never <code>null</code>.
   * @param aNewSession
   *        The new session scope that replaced the old one. Never <code>null</code>.
   */
  public void onSessionChangeAfterLogin (@NonNull final ISessionWebScope aOldSession,
                                         @NonNull final ISessionWebScope aNewSession)
  {
    ValueEnforcer.notNull (aOldSession, "OldSession");
    ValueEnforcer.notNull (aNewSession, "NewSession");

    final String sOldID = aOldSession.getID ();
    m_aRWLock.writeLocked ( () -> {
      for (final LoginInfo aInfo : m_aLoggedInUsers.values ())
        if (aInfo.getSessionScope ().getID ().equals (sOldID))
          aInfo.internalSetSessionScope (aNewSession);
    });
  }

  /**
   * Manually log out the specified user
   *
   * @param sUserID
   *        The user ID to log out
   * @return {@link EChange} if something changed
   */
  @NonNull
  public EChange logoutUser (@Nullable final String sUserID)
  {
    final LoginInfo aInfo;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aInfo = m_aLoggedInUsers.remove (sUserID);
      if (aInfo == null)
      {
        AuditHelper.onAuditExecuteSuccess ("logout", sUserID, "user-not-logged-in");
        return EChange.UNCHANGED;
      }
      // Ensure that the SessionUser is empty. This is only relevant if user is
      // manually logged out without destructing the underlying session
      final InternalSessionUserHolder aSUH = InternalSessionUserHolder._getInstanceIfInstantiatedInScope (aInfo.getSessionScope ());
      if (aSUH != null)
        aSUH._reset ();

      // The session scope referenced by the LoginInfo may be stale (e.g. if the
      // session ID was regenerated without calling onSessionChangeAfterLogin).
      // Additionally reset the holder of the current session, if it refers to
      // the very same user, to avoid a half logged out state
      final InternalSessionUserHolder aCurrentSUH = InternalSessionUserHolder._getInstanceIfInstantiated ();
      if (aCurrentSUH != null && aCurrentSUH != aSUH && EqualsHelper.equals (aCurrentSUH._getUserID (), sUserID))
        aCurrentSUH._reset ();

      // Set logout time - in case somebody has a strong reference to the
      // LoginInfo object
      aInfo.setLogoutDTNow ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    LOGGER.info ("Logged out " +
                 _getUserIDLogText (sUserID) +
                 " after " +
                 Duration.between (aInfo.getLoginDT (), aInfo.getLogoutDT ()).toString ());
    AuditHelper.onAuditExecuteSuccess ("logout", sUserID);

    // Execute callback as the very last action
    m_aUserLogoutCallbacks.forEach (aCB -> aCB.onUserLogout (aInfo));

    return EChange.CHANGED;
  }

  /**
   * Manually log out the current user
   *
   * @return {@link EChange} if something changed
   */
  @NonNull
  public EChange logoutCurrentUser ()
  {
    return logoutUser (getCurrentUserID ());
  }

  /**
   * Check if the specified user is logged in or not
   *
   * @param sUserID
   *        The user ID to check. May be <code>null</code>.
   * @return <code>true</code> if the user is logged in, <code>false</code> otherwise.
   */
  public boolean isUserLoggedIn (@Nullable final String sUserID)
  {
    return m_aRWLock.readLockedBoolean ( () -> m_aLoggedInUsers.containsKey (sUserID));
  }

  /**
   * @return A non-<code>null</code> but maybe empty set with all currently logged in user IDs.
   */
  @NonNull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllLoggedInUserIDs ()
  {
    return m_aRWLock.readLockedGet (m_aLoggedInUsers::copyOfKeySet);
  }

  /**
   * Get the login details of the specified user.
   *
   * @param sUserID
   *        The user ID to check. May be <code>null</code>.
   * @return <code>null</code> if the passed user is not logged in.
   */
  @Nullable
  public LoginInfo getLoginInfo (@Nullable final String sUserID)
  {
    return m_aRWLock.readLockedGet ( () -> m_aLoggedInUsers.get (sUserID));
  }

  /**
   * @return A non-<code>null</code> but maybe empty collection with the details of all currently
   *         logged in users.
   */
  @NonNull
  @ReturnsMutableCopy
  public ICommonsCollection <LoginInfo> getAllLoginInfos ()
  {
    return m_aRWLock.readLockedGet (m_aLoggedInUsers::copyOfValues);
  }

  /**
   * @return The number of currently logged in users. Always &ge; 0.
   */
  @Nonnegative
  public int getLoggedInUserCount ()
  {
    return m_aRWLock.readLockedInt (m_aLoggedInUsers::size);
  }

  /**
   * @return The ID of the user logged in this session or <code>null</code> if no user is logged in.
   */
  @Nullable
  public String getCurrentUserID ()
  {
    final InternalSessionUserHolder aSUH = InternalSessionUserHolder._getInstanceIfInstantiated ();
    return aSUH == null ? null : aSUH.m_sUserID;
  }

  /**
   * @return <code>true</code> if a user is currently logged into this session, <code>false</code>
   *         otherwise. This is the inverse of {@link #isNoUserLoggedInInCurrentSession()}.
   */
  public boolean isUserLoggedInInCurrentSession ()
  {
    return getCurrentUserID () != null;
  }

  /**
   * @return <code>true</code> if not user is currently logged into this session, <code>false</code>
   *         if it is. This is the inverse of {@link #isUserLoggedInInCurrentSession()}.
   */
  public boolean isNoUserLoggedInInCurrentSession ()
  {
    return getCurrentUserID () == null;
  }

  /**
   * @return The user currently logged in this session or <code>null</code> if no user is logged in.
   */
  @Nullable
  public IUser getCurrentUser ()
  {
    final InternalSessionUserHolder aSUH = InternalSessionUserHolder._getInstanceIfInstantiated ();
    return aSUH == null ? null : aSUH.m_aUser;
  }

  /**
   * @return <code>true</code> if a user is logged in and is administrator
   */
  public boolean isCurrentUserAdministrator ()
  {
    final IUser aUser = getCurrentUser ();
    return aUser != null && aUser.isAdministrator ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("LoggedInUsers", m_aLoggedInUsers)
                            .append ("UserLoginCallbacks", m_aUserLoginCallbacks)
                            .append ("UserLogoutCallbacks", m_aUserLogoutCallbacks)
                            .append ("LogoutAlreadyLoggedInUser", m_bLogoutAlreadyLoggedInUser)
                            .append ("AnonymousLogging", m_bAnonymousLogging)
                            .getToString ();
  }
}
