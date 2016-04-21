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
package com.helger.photon.security.login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.Collection;

import javax.annotation.Nonnegative;
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
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.scope.IScope;
import com.helger.commons.scope.ISessionScope;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.basic.auth.ICurrentUserIDProvider;
import com.helger.photon.security.lock.ObjectLockManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.util.SecurityHelper;
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
   * This class manages the user ID of the current session. This is an internal
   * class and should not be used from the outside!
   *
   * @author Philip Helger
   */
  public static final class InternalSessionUserHolder extends AbstractSessionWebSingleton
                                                      implements ISessionWebScopeActivationHandler
  {
    private static final long serialVersionUID = 2322897734799334L;

    private transient IUser m_aUser;
    private String m_sUserID;
    private transient LoggedInUserManager m_aOwningMgr;

    @Deprecated
    @UsedViaReflection
    public InternalSessionUserHolder ()
    {}

    /**
     * @return The instance of the current session. If none exists, an instance
     *         is created. Never <code>null</code>.
     */
    @Nonnull
    static InternalSessionUserHolder getInstance ()
    {
      return getSessionSingleton (InternalSessionUserHolder.class);
    }

    /**
     * @return The instance of the current session. If none exists,
     *         <code>null</code> is returned.
     */
    @Nullable
    static InternalSessionUserHolder getInstanceIfInstantiated ()
    {
      return getSessionSingletonIfInstantiated (InternalSessionUserHolder.class);
    }

    @Nullable
    static InternalSessionUserHolder getInstanceIfInstantiatedInScope (@Nullable final ISessionScope aScope)
    {
      return getSingletonIfInstantiated (aScope, InternalSessionUserHolder.class);
    }

    private void readObject (@Nonnull final ObjectInputStream aOIS) throws IOException, ClassNotFoundException
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

    public void onSessionDidActivate (@Nonnull final ISessionWebScope aSessionScope)
    {
      // Finally remember that the user is logged in
      m_aOwningMgr.internalSessionActivateUser (m_aUser, aSessionScope);
    }

    boolean hasUser ()
    {
      return m_aUser != null;
    }

    @Nullable
    String getUserID ()
    {
      return m_sUserID;
    }

    void setUser (@Nonnull final LoggedInUserManager aOwningMgr, @Nonnull final IUser aUser)
    {
      ValueEnforcer.notNull (aOwningMgr, "OwningMgr");
      ValueEnforcer.notNull (aUser, "User");
      if (m_aUser != null)
        throw new IllegalStateException ("Session already has a user!");

      m_aOwningMgr = aOwningMgr;
      m_aUser = aUser;
      m_sUserID = aUser.getID ();
    }

    void _reset ()
    {
      // Reset to avoid access while or after logout
      m_aUser = null;
      m_sUserID = null;
      m_aOwningMgr = null;
    }

    @Override
    protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
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
      return ToStringGenerator.getDerived (super.toString ()).append ("userID", m_sUserID).toString ();
    }
  }

  /**
   * Special logout callback that is executed every time a user logs out. It
   * removes all objects from the {@link ObjectLockManager}.
   *
   * @author Philip Helger
   */
  final class UserLogoutCallbackUnlockAllObjects implements IUserLogoutCallback
  {
    @Override
    public void onUserLogout (@Nonnull final LoginInfo aInfo)
    {
      final ObjectLockManager aOLMgr = ObjectLockManager.getInstanceIfInstantiated ();
      if (aOLMgr != null)
        aOLMgr.getDefaultLockMgr ().unlockAllObjectsOfUser (aInfo.getUserID ());
    }
  }

  public static final boolean DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggedInUserManager.class);

  // Set of logged in user IDs
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, LoginInfo> m_aLoggedInUsers = new CommonsHashMap<> ();
  private final CallbackList <IUserLoginCallback> m_aUserLoginCallbacks = new CallbackList<> ();
  private final CallbackList <IUserLogoutCallback> m_aUserLogoutCallbacks = new CallbackList<> ();
  private boolean m_bLogoutAlreadyLoggedInUser = DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER;

  @Deprecated
  @UsedViaReflection
  public LoggedInUserManager ()
  {
    // Ensure that all objects of a user are unlocked upon logout
    m_aUserLogoutCallbacks.addCallback (new UserLogoutCallbackUnlockAllObjects ());
  }

  /**
   * @return The global instance of this class. Never <code>null</code>.
   */
  @Nonnull
  public static LoggedInUserManager getInstance ()
  {
    return getGlobalSingleton (LoggedInUserManager.class);
  }

  /**
   * @return The user login callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IUserLoginCallback> getUserLoginCallbacks ()
  {
    return m_aUserLoginCallbacks;
  }

  /**
   * @return The user logout callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IUserLogoutCallback> getUserLogoutCallbacks ()
  {
    return m_aUserLogoutCallbacks;
  }

  /**
   * @return <code>true</code> if a new login of a user, destroys any previously
   *         present session, <code>false</code> if a login should fail, if that
   *         user is already logged in.
   */
  public boolean isLogoutAlreadyLoggedInUser ()
  {
    return m_aRWLock.readLocked ( () -> m_bLogoutAlreadyLoggedInUser);
  }

  public void setLogoutAlreadyLoggedInUser (final boolean bLogoutAlreadyLoggedInUser)
  {
    m_aRWLock.writeLocked ( () -> m_bLogoutAlreadyLoggedInUser = bLogoutAlreadyLoggedInUser);
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
  @Nonnull
  public ELoginResult loginUser (@Nullable final String sLoginName, @Nullable final String sPlainTextPassword)
  {
    return loginUser (sLoginName, sPlainTextPassword, (Collection <String>) null);
  }

  /**
   * Login the passed user and require a set of certain roles, the used needs to
   * have to login here.
   *
   * @param sLoginName
   *        Login name of the user to log-in. May be <code>null</code>.
   * @param sPlainTextPassword
   *        Plain text password to use. May be <code>null</code>.
   * @param aRequiredRoleIDs
   *        A set of required role IDs, the user needs to have. May be
   *        <code>null</code>.
   * @return Never <code>null</code> login status.
   */
  @Nonnull
  public ELoginResult loginUser (@Nullable final String sLoginName,
                                 @Nullable final String sPlainTextPassword,
                                 @Nullable final Collection <String> aRequiredRoleIDs)
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

  @Nonnull
  private ELoginResult _onLoginError (@Nonnull @Nonempty final String sUserID, @Nonnull final ELoginResult eLoginResult)
  {
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLoginError (sUserID, eLoginResult));
    return eLoginResult;
  }

  void internalSessionActivateUser (@Nonnull final IUser aUser, @Nonnull final ISessionScope aSessionScope)
  {
    ValueEnforcer.notNull (aUser, "User");
    ValueEnforcer.notNull (aSessionScope, "SessionScope");

    final LoginInfo aInfo = new LoginInfo (aUser, aSessionScope);
    m_aRWLock.writeLocked ( () -> m_aLoggedInUsers.put (aUser.getID (), aInfo));
  }

  /**
   * Login the passed user and require a set of certain roles, the used needs to
   * have to login here.
   *
   * @param aUser
   *        The user to log-in. May be <code>null</code>. When the user is
   *        <code>null</code> the login must fail.
   * @param sPlainTextPassword
   *        Plain text password to use. May be <code>null</code>.
   * @param aRequiredRoleIDs
   *        A set of required role IDs, the user needs to have. May be
   *        <code>null</code>.
   * @return Never <code>null</code> login status.
   */
  @Nonnull
  public ELoginResult loginUser (@Nullable final IUser aUser,
                                 @Nullable final String sPlainTextPassword,
                                 @Nullable final Collection <String> aRequiredRoleIDs)
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

    // Are all roles present?
    if (!SecurityHelper.hasUserAllRoles (sUserID, aRequiredRoleIDs))
    {
      AuditHelper.onAuditExecuteFailure ("login",
                                         sUserID,
                                         "user-is-missing-required-roles",
                                         StringHelper.getToString (aRequiredRoleIDs));
      return _onLoginError (sUserID, ELoginResult.USER_IS_MISSING_ROLE);
    }

    // Check the password
    final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    if (!aUserMgr.areUserIDAndPasswordValid (sUserID, sPlainTextPassword))
    {
      AuditHelper.onAuditExecuteFailure ("login", sUserID, "invalid-password");
      return _onLoginError (sUserID, ELoginResult.INVALID_PASSWORD);
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
      s_aLogger.info ("Updated password hash of user '" +
                      sUserID +
                      "' from algorithm '" +
                      sExistingPasswordHashAlgorithmName +
                      "' to '" +
                      sDefaultPasswordHashAlgorithmName +
                      "'");
    }

    boolean bLoggedOutUser = false;
    LoginInfo aInfo;
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aLoggedInUsers.containsKey (sUserID))
      {
        // The user is already logged in
        if (isLogoutAlreadyLoggedInUser ())
        {
          // Explicitly log out
          logoutUser (sUserID);

          // Just a short check
          if (m_aLoggedInUsers.containsKey (sUserID))
            throw new IllegalStateException ("Failed to logout '" + sUserID + "'");

          AuditHelper.onAuditExecuteSuccess ("logout-in-login", sUserID);
          bLoggedOutUser = true;
        }
        else
        {
          AuditHelper.onAuditExecuteFailure ("login", sUserID, "user-already-logged-in");
          return _onLoginError (sUserID, ELoginResult.USER_ALREADY_LOGGED_IN);
        }
      }

      final InternalSessionUserHolder aSUH = InternalSessionUserHolder.getInstance ();
      if (aSUH.hasUser ())
      {
        // This session already has a user
        s_aLogger.warn ("The session user holder already has the user ID '" +
                        aSUH.getUserID () +
                        "' so the new ID '" +
                        sUserID +
                        "' will not be set!");
        AuditHelper.onAuditExecuteFailure ("login", sUserID, "session-already-has-user");
        return _onLoginError (sUserID, ELoginResult.SESSION_ALREADY_HAS_USER);
      }

      aInfo = new LoginInfo (aUser, ScopeManager.getSessionScope ());
      m_aLoggedInUsers.put (sUserID, aInfo);
      aSUH.setUser (this, aUser);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    s_aLogger.info ("Logged in user '" + sUserID + "' with login name '" + aUser.getLoginName () + "'");
    AuditHelper.onAuditExecuteSuccess ("login-user", sUserID, aUser.getLoginName ());

    // Execute callback as the very last action
    m_aUserLoginCallbacks.forEach (aCB -> aCB.onUserLogin (aInfo));

    return bLoggedOutUser ? ELoginResult.SUCCESS_WITH_LOGOUT : ELoginResult.SUCCESS;
  }

  /**
   * Manually log out the specified user
   *
   * @param sUserID
   *        The user ID to log out
   * @return {@link EChange} if something changed
   */
  @Nonnull
  public EChange logoutUser (@Nullable final String sUserID)
  {
    LoginInfo aInfo;
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
      final InternalSessionUserHolder aSUH = InternalSessionUserHolder.getInstanceIfInstantiatedInScope (aInfo.getSessionScope ());
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

    s_aLogger.info ("Logged out user '" +
                    sUserID +
                    "' after " +
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
  @Nonnull
  public EChange logoutCurrentUser ()
  {
    return logoutUser (getCurrentUserID ());
  }

  /**
   * Check if the specified user is logged in or not
   *
   * @param sUserID
   *        The user ID to check. May be <code>null</code>.
   * @return <code>true</code> if the user is logged in, <code>false</code>
   *         otherwise.
   */
  public boolean isUserLoggedIn (@Nullable final String sUserID)
  {
    return m_aRWLock.readLocked ( () -> m_aLoggedInUsers.containsKey (sUserID));
  }

  /**
   * @return A non-<code>null</code> but maybe empty set with all currently
   *         logged in user IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllLoggedInUserIDs ()
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.newSet (m_aLoggedInUsers.keySet ()));
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
    return m_aRWLock.readLocked ( () -> m_aLoggedInUsers.get (sUserID));
  }

  /**
   * @return A non-<code>null</code> but maybe empty collection with the details
   *         of all currently logged in users.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsCollection <LoginInfo> getAllLoginInfos ()
  {
    return m_aRWLock.readLocked ( () -> CollectionHelper.newList (m_aLoggedInUsers.values ()));
  }

  /**
   * @return The number of currently logged in users. Always &ge; 0.
   */
  @Nonnegative
  public int getLoggedInUserCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aLoggedInUsers.size ());
  }

  /**
   * @return The ID of the user logged in this session or <code>null</code> if
   *         no user is logged in.
   */
  @Nullable
  public String getCurrentUserID ()
  {
    final InternalSessionUserHolder aSUH = InternalSessionUserHolder.getInstanceIfInstantiated ();
    return aSUH == null ? null : aSUH.m_sUserID;
  }

  /**
   * @return <code>true</code> if a user is currently logged into this session,
   *         <code>false</code> otherwise. This is the inverse of
   *         {@link #isNoUserLoggedInInCurrentSession()}.
   */
  public boolean isUserLoggedInInCurrentSession ()
  {
    return getCurrentUserID () != null;
  }

  /**
   * @return <code>true</code> if not user is currently logged into this
   *         session, <code>false</code> if it is. This is the inverse of
   *         {@link #isUserLoggedInInCurrentSession()}.
   */
  public boolean isNoUserLoggedInInCurrentSession ()
  {
    return getCurrentUserID () == null;
  }

  /**
   * @return The user currently logged in this session or <code>null</code> if
   *         no user is logged in.
   */
  @Nullable
  public IUser getCurrentUser ()
  {
    final InternalSessionUserHolder aSUH = InternalSessionUserHolder.getInstanceIfInstantiated ();
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
                            .append ("loggedInUsers", m_aLoggedInUsers)
                            .append ("userLoginCallbacks", m_aUserLoginCallbacks)
                            .append ("userLogoutCallbacks", m_aUserLogoutCallbacks)
                            .append ("logoutAlreadyLoggedInUser", m_bLogoutAlreadyLoggedInUser)
                            .toString ();
  }
}
