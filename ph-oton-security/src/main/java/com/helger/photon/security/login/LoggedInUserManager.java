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
import com.helger.annotation.concurrent.ELockType;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.MustBeLocked;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.callback.CallbackList;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
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
          throw new IllegalStateException ("Failed to resolve user with ID '" + m_sUserID + "'");
      }
      // Resolve manager
      m_aOwningMgr = LoggedInUserManager.getInstance ();
    }

    public void onSessionDidActivate (@NonNull final ISessionWebScope aSessionScope)
    {
      // Finally remember that the user is logged in
      m_aOwningMgr.internalSessionActivateUser (m_aUser, aSessionScope);
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
   * Per-session holder that remembers a login which passed primary credential validation but has
   * not yet completed second-factor verification. This is an internal class and should not be used
   * from the outside.
   *
   * @author Philip Helger
   */
  public static final class InternalSessionPendingLoginHolder extends AbstractSessionWebSingleton
  {
    private String m_sUserID;
    private boolean m_bLoggedOutPriorSession;
    private long m_nValidUntilMillis;

    @Deprecated (forRemoval = false)
    @UsedViaReflection
    public InternalSessionPendingLoginHolder ()
    {}

    @NonNull
    private static InternalSessionPendingLoginHolder _getInstance ()
    {
      return getSessionSingleton (InternalSessionPendingLoginHolder.class);
    }

    @Nullable
    private static InternalSessionPendingLoginHolder _getInstanceIfInstantiated ()
    {
      return getSessionSingletonIfInstantiated (InternalSessionPendingLoginHolder.class);
    }

    private void _setPending (@NonNull @Nonempty final String sUserID,
                              final boolean bLoggedOutPriorSession,
                              final long nValidUntilMillis)
    {
      m_sUserID = sUserID;
      m_bLoggedOutPriorSession = bLoggedOutPriorSession;
      m_nValidUntilMillis = nValidUntilMillis;
    }

    private void _reset ()
    {
      m_sUserID = null;
      m_bLoggedOutPriorSession = false;
      m_nValidUntilMillis = 0;
    }

    @Nullable
    private String _getUserID ()
    {
      return m_sUserID;
    }

    private boolean _isLoggedOutPriorSession ()
    {
      return m_bLoggedOutPriorSession;
    }

    private boolean _isStillValid ()
    {
      return m_sUserID != null && System.currentTimeMillis () <= m_nValidUntilMillis;
    }

    @Override
    public String toString ()
    {
      return ToStringGenerator.getDerived (super.toString ())
                              .appendIfNotNull ("userID", m_sUserID)
                              .append ("validUntilMillis", m_nValidUntilMillis)
                              .getToString ();
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
  /** Default lifetime of a pending second-factor login: 5 minutes. */
  public static final Duration DEFAULT_PENDING_SECOND_FACTOR_TTL = Duration.ofMinutes (5);

  private static final Logger LOGGER = LoggerFactory.getLogger (LoggedInUserManager.class);

  // Set of logged in user IDs
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, LoginInfo> m_aLoggedInUsers = new CommonsHashMap <> ();
  private final CallbackList <IUserLoginCallback> m_aUserLoginCallbacks = new CallbackList <> ();
  private final CallbackList <IUserLogoutCallback> m_aUserLogoutCallbacks = new CallbackList <> ();
  private boolean m_bLogoutAlreadyLoggedInUser = DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER;
  private boolean m_bAnonymousLogging = DEFAULT_ANONYMOUS_LOGGING;
  private ILoggedInUserSecondFactorPolicy m_aSecondFactorPolicy;
  private ILoggedInUserSecondFactorVerifier m_aSecondFactorVerifier;
  private Duration m_aPendingSecondFactorTTL = DEFAULT_PENDING_SECOND_FACTOR_TTL;

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

  /**
   * @return The currently registered second-factor policy or <code>null</code> if none is
   *         registered. When <code>null</code>, second-factor authentication is disabled.
   * @since 10.2.3
   */
  @Nullable
  public ILoggedInUserSecondFactorPolicy getSecondFactorPolicy ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aSecondFactorPolicy);
  }

  /**
   * Register the policy that decides whether a given user requires a second factor.
   *
   * @param aPolicy
   *        The policy. May be <code>null</code> to disable second-factor authentication.
   * @since 10.2.3
   */
  public void setSecondFactorPolicy (@Nullable final ILoggedInUserSecondFactorPolicy aPolicy)
  {
    m_aRWLock.writeLocked ( () -> m_aSecondFactorPolicy = aPolicy);
  }

  /**
   * @return The currently registered second-factor verifier or <code>null</code> if none is
   *         registered.
   * @since 10.2.3
   */
  @Nullable
  public ILoggedInUserSecondFactorVerifier getSecondFactorVerifier ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aSecondFactorVerifier);
  }

  /**
   * Register the verifier used by {@link #loginUserSecondFactor(String)} to validate submitted
   * second-factor codes.
   *
   * @param aVerifier
   *        The verifier. May be <code>null</code>.
   * @since 10.2.3
   */
  public void setSecondFactorVerifier (@Nullable final ILoggedInUserSecondFactorVerifier aVerifier)
  {
    m_aRWLock.writeLocked ( () -> m_aSecondFactorVerifier = aVerifier);
  }

  /**
   * @return The maximum lifetime of a pending second-factor login. Never <code>null</code>.
   * @since 10.2.3
   */
  @NonNull
  public Duration getPendingSecondFactorTTL ()
  {
    return m_aRWLock.readLockedGet ( () -> m_aPendingSecondFactorTTL);
  }

  /**
   * Configure how long a pending second-factor login remains valid after the password step.
   *
   * @param aTTL
   *        The TTL. Neither <code>null</code> nor negative.
   * @since 10.2.3
   */
  public void setPendingSecondFactorTTL (@NonNull final Duration aTTL)
  {
    ValueEnforcer.notNull (aTTL, "TTL");
    ValueEnforcer.isFalse (aTTL.isNegative (), "TTL must not be negative");
    m_aRWLock.writeLocked ( () -> m_aPendingSecondFactorTTL = aTTL);
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

  void internalSessionActivateUser (@NonNull final IUser aUser, @NonNull final ISessionScope aSessionScope)
  {
    ValueEnforcer.notNull (aUser, "User");
    ValueEnforcer.notNull (aSessionScope, "SessionScope");

    final LoginInfo aInfo = new LoginInfo (aUser, aSessionScope);
    m_aRWLock.writeLocked ( () -> m_aLoggedInUsers.put (aUser.getID (), aInfo));
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
   * Install the user in the current session as fully logged in. Must be called while holding the
   * write lock.
   *
   * @return A non-<code>null</code> {@link ELoginResult} if installation failed (the caller must
   *         return that result), or <code>null</code> on success — in which case logging and
   *         callback have already been performed.
   */
  @Nullable
  @MustBeLocked (ELockType.WRITE)
  private ELoginResult _installLoggedInUserLocked (@NonNull final IUser aUser)
  {
    final String sUserID = aUser.getID ();
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
    final LoginInfo aInfo = new LoginInfo (aUser, ScopeManager.getSessionScope ());
    m_aLoggedInUsers.put (sUserID, aInfo);
    aSUH._setUser (this, aUser);

    LOGGER.info ("Logged in " +
                 _getUserIDLogText (sUserID) +
                 (isAnonymousLogging () ? "" : " with login name '" + aUser.getLoginName () + "'"));
    AuditHelper.onAuditExecuteSuccess ("login-user", sUserID, aUser.getLoginName ());

    // Execute callback as the very last action
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLogin (aInfo));
    return null;
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
      return ELoginResult.USER_NOT_EXISTING;

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
      aUserMgr.setUserPassword (sUserID, sPlainTextPassword);

      LOGGER.info ("Updated password hash of " +
                   _getUserIDLogText (sUserID) +
                   " from algorithm '" +
                   sExistingPasswordHashAlgorithmName +
                   "' to '" +
                   sDefaultPasswordHashAlgorithmName +
                   "'");
    }

    boolean bLoggedOutUser = false;
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aLoggedInUsers.containsKey (sUserID))
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

        // Just a short check
        if (m_aLoggedInUsers.containsKey (sUserID))
          throw new IllegalStateException ("Failed to logout '" + sUserID + "'");

        AuditHelper.onAuditExecuteSuccess ("logout-in-login", sUserID);
        bLoggedOutUser = true;
      }

      // Second-factor gate (if a policy is registered)
      if (m_aSecondFactorPolicy != null && m_aSecondFactorPolicy.isSecondFactorRequired (sUserID))
      {
        // Park as pending; the session user holder stays empty until 2FA succeeds
        final InternalSessionPendingLoginHolder aPending = InternalSessionPendingLoginHolder._getInstance ();
        final long nValidUntil = System.currentTimeMillis () + m_aPendingSecondFactorTTL.toMillis ();
        aPending._setPending (sUserID, bLoggedOutUser, nValidUntil);
        AuditHelper.onAuditExecuteSuccess ("login-2fa-required", sUserID);
        LOGGER.info ("Primary credentials valid for " + _getUserIDLogText (sUserID) + "; awaiting second factor");
        m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserSecondFactorRequired (sUserID));
        return ELoginResult.SECOND_FACTOR_REQUIRED;
      }

      final ELoginResult eInstall = _installLoggedInUserLocked (aUser);
      if (eInstall != null)
        return eInstall;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
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
   * Complete a previously parked pending login by verifying the user-supplied second-factor code.
   * The pending state is read from the current session.
   *
   * @param sCode
   *        The submitted code (TOTP or single-use recovery code). May be <code>null</code>.
   * @return The final login result. {@link ELoginResult#NO_PENDING_SECOND_FACTOR} if no pending
   *         login exists or it expired; {@link ELoginResult#INVALID_SECOND_FACTOR} if the code was
   *         rejected; {@link ELoginResult#SUCCESS} / {@link ELoginResult#SUCCESS_WITH_LOGOUT} on
   *         success.
   * @since 10.2.3
   */
  @NonNull
  public ELoginResult loginUserSecondFactor (@Nullable final String sCode)
  {
    final InternalSessionPendingLoginHolder aPending = InternalSessionPendingLoginHolder._getInstanceIfInstantiated ();
    if (aPending == null || !aPending._isStillValid ())
    {
      if (aPending != null)
        aPending._reset ();
      AuditHelper.onAuditExecuteFailure ("login-2fa", "no-pending-or-expired");
      return ELoginResult.NO_PENDING_SECOND_FACTOR;
    }

    final String sUserID = aPending._getUserID ();
    final boolean bLoggedOutPrior = aPending._isLoggedOutPriorSession ();

    if (sCode == null || sCode.isEmpty () || m_aSecondFactorVerifier == null)
    {
      AuditHelper.onAuditExecuteFailure ("login-2fa",
                                         sUserID,
                                         sCode == null || sCode.isEmpty () ? "empty-code" : "no-verifier");
      m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserSecondFactorFailed (sUserID));
      return _onLoginError (sUserID, ELoginResult.INVALID_SECOND_FACTOR);
    }

    if (!m_aSecondFactorVerifier.verify (sUserID, sCode))
    {
      AuditHelper.onAuditExecuteFailure ("login-2fa", sUserID, "invalid-code");
      m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserSecondFactorFailed (sUserID));
      return _onLoginError (sUserID, ELoginResult.INVALID_SECOND_FACTOR);
    }

    // Code accepted — resolve user and finalize login
    final IUser aUser = PhotonSecurityManager.getUserMgr ().getUserOfID (sUserID);
    if (aUser == null || aUser.isDeleted () || aUser.isDisabled ())
    {
      aPending._reset ();
      AuditHelper.onAuditExecuteFailure ("login-2fa", sUserID, "user-not-active-anymore");
      return _onLoginError (sUserID,
                            aUser == null ? ELoginResult.USER_NOT_EXISTING : aUser.isDeleted ()
                                                                                                ? ELoginResult.USER_IS_DELETED
                                                                                                : ELoginResult.USER_IS_DISABLED);
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aPending._reset ();
      final ELoginResult eInstall = _installLoggedInUserLocked (aUser);
      if (eInstall != null)
        return eInstall;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditExecuteSuccess ("login-2fa-success", sUserID);
    return bLoggedOutPrior ? ELoginResult.SUCCESS_WITH_LOGOUT : ELoginResult.SUCCESS;
  }

  /**
   * @return The ID of the user currently awaiting second-factor verification in this session, or
   *         <code>null</code> if there is no such pending login.
   * @since 10.2.3
   */
  @Nullable
  public String getCurrentPendingSecondFactorUserID ()
  {
    final InternalSessionPendingLoginHolder aPending = InternalSessionPendingLoginHolder._getInstanceIfInstantiated ();
    return aPending != null && aPending._isStillValid () ? aPending._getUserID () : null;
  }

  /**
   * Discard any pending second-factor login state in the current session.
   *
   * @return {@link EChange#CHANGED} if a pending state was discarded, {@link EChange#UNCHANGED}
   *         otherwise.
   * @since 10.2.3
   */
  @NonNull
  public EChange cancelPendingSecondFactor ()
  {
    final InternalSessionPendingLoginHolder aPending = InternalSessionPendingLoginHolder._getInstanceIfInstantiated ();
    if (aPending == null || aPending._getUserID () == null)
      return EChange.UNCHANGED;
    final String sUserID = aPending._getUserID ();
    aPending._reset ();
    AuditHelper.onAuditExecuteSuccess ("login-2fa-cancel", sUserID);
    return EChange.CHANGED;
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
                            .appendIfNotNull ("SecondFactorPolicy", m_aSecondFactorPolicy)
                            .appendIfNotNull ("SecondFactorVerifier", m_aSecondFactorVerifier)
                            .append ("PendingSecondFactorTTL", m_aPendingSecondFactorTTL)
                            .getToString ();
  }
}
