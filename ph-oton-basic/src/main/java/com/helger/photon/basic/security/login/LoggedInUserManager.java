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
package com.helger.photon.basic.security.login;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.scope.IScope;
import com.helger.commons.scope.ISessionScope;
import com.helger.commons.scope.mgr.ScopeManager;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.security.AccessManager;
import com.helger.photon.basic.security.audit.AuditUtils;
import com.helger.photon.basic.security.lock.ObjectLockManager;
import com.helger.photon.basic.security.login.callback.DefaultUserLogoutCallback;
import com.helger.photon.basic.security.login.callback.IUserLoginCallback;
import com.helger.photon.basic.security.login.callback.IUserLogoutCallback;
import com.helger.photon.basic.security.password.GlobalPasswordSettings;
import com.helger.photon.basic.security.user.IUser;
import com.helger.web.scopes.domain.ISessionWebScope;
import com.helger.web.scopes.session.ISessionWebScopeActivationHandler;
import com.helger.web.scopes.singleton.AbstractSessionWebSingleton;

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
  public static final class SessionUserHolder extends AbstractSessionWebSingleton implements ISessionWebScopeActivationHandler
  {
    private static final long serialVersionUID = 2322897734799334L;

    private transient IUser m_aUser;
    private String m_sUserID;
    private transient LoggedInUserManager m_aOwningMgr;

    @Deprecated
    @UsedViaReflection
    public SessionUserHolder ()
    {}

    /**
     * @return The instance of the current session. If none exists, an instance
     *         is created. Never <code>null</code>.
     */
    @Nonnull
    static SessionUserHolder getInstance ()
    {
      return getSessionSingleton (SessionUserHolder.class);
    }

    /**
     * @return The instance of the current session. If none exists,
     *         <code>null</code> is returned.
     */
    @Nullable
    static SessionUserHolder getInstanceIfInstantiated ()
    {
      return getSessionSingletonIfInstantiated (SessionUserHolder.class);
    }

    @Nullable
    static SessionUserHolder getInstanceIfInstantiatedInScope (@Nullable final ISessionScope aScope)
    {
      return getSingletonIfInstantiated (aScope, SessionUserHolder.class);
    }

    private void readObject (@Nonnull final ObjectInputStream aOIS) throws IOException, ClassNotFoundException
    {
      aOIS.defaultReadObject ();

      // Resolve user ID
      if (m_sUserID != null)
      {
        m_aUser = AccessManager.getInstance ().getUserOfID (m_sUserID);
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
  final class UserLogoutCallbackUnlockAllObjects extends DefaultUserLogoutCallback
  {
    @Override
    public void onUserLogout (@Nonnull final LoginInfo aInfo)
    {
      final ObjectLockManager aOLMgr = ObjectLockManager.getInstanceIfInstantiated ();
      if (aOLMgr != null)
        aOLMgr.unlockAllObjectsOfUser (aInfo.getUserID ());
    }
  }

  public static final boolean DEFAULT_LOGOUT_ALREADY_LOGGED_IN_USER = false;

  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggedInUserManager.class);

  // Set of logged in user IDs
  @GuardedBy ("m_aRWLock")
  private final Map <String, LoginInfo> m_aLoggedInUsers = new HashMap <String, LoginInfo> ();
  private final CallbackList <IUserLoginCallback> m_aUserLoginCallbacks = new CallbackList <IUserLoginCallback> ();
  private final CallbackList <IUserLogoutCallback> m_aUserLogoutCallbacks = new CallbackList <IUserLogoutCallback> ();
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bLogoutAlreadyLoggedInUser;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void setLogoutAlreadyLoggedInUser (final boolean bLogoutAlreadyLoggedInUser)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_bLogoutAlreadyLoggedInUser = bLogoutAlreadyLoggedInUser;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
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
    final IUser aUser = AccessManager.getInstance ().getUserOfLoginName (sLoginName);
    if (aUser == null)
    {
      AuditUtils.onAuditExecuteFailure ("login", sLoginName, "no-such-loginname");
      return ELoginResult.USER_NOT_EXISTING;
    }
    return loginUser (aUser, sPlainTextPassword, aRequiredRoleIDs);
  }

  @Nonnull
  private ELoginResult _onLoginError (@Nonnull @Nonempty final String sUserID, @Nonnull final ELoginResult eLoginResult)
  {
    for (final IUserLoginCallback aUserLoginCallback : m_aUserLoginCallbacks.getAllCallbacks ())
      try
      {
        aUserLoginCallback.onUserLoginError (sUserID, eLoginResult);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserLoginError callback on " +
                         aUserLoginCallback +
                         "(" +
                         sUserID +
                         "," +
                         eLoginResult.toString () +
                         ")",
                         t);
      }
    return eLoginResult;
  }

  void internalSessionActivateUser (@Nonnull final IUser aUser, @Nonnull final ISessionScope aSessionScope)
  {
    ValueEnforcer.notNull (aUser, "User");
    ValueEnforcer.notNull (aSessionScope, "SessionScope");

    m_aRWLock.writeLock ().lock ();
    try
    {
      final LoginInfo aInfo = new LoginInfo (aUser, aSessionScope);
      m_aLoggedInUsers.put (aUser.getID (), aInfo);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
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
      AuditUtils.onAuditExecuteFailure ("login", sUserID, "user-is-deleted");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DELETED);
    }

    // Disabled user?
    if (aUser.isDisabled ())
    {
      AuditUtils.onAuditExecuteFailure ("login", sUserID, "user-is-disabled");
      return _onLoginError (sUserID, ELoginResult.USER_IS_DISABLED);
    }

    final AccessManager aAccessMgr = AccessManager.getInstance ();

    // Are all roles present?
    if (!aAccessMgr.hasUserAllRoles (sUserID, aRequiredRoleIDs))
    {
      AuditUtils.onAuditExecuteFailure ("login",
                                        sUserID,
                                        "user-is-missing-required-roles",
                                        StringHelper.getToString (aRequiredRoleIDs));
      return _onLoginError (sUserID, ELoginResult.USER_IS_MISSING_ROLE);
    }

    // Check the password
    if (!aAccessMgr.areUserIDAndPasswordValid (sUserID, sPlainTextPassword))
    {
      AuditUtils.onAuditExecuteFailure ("login", sUserID, "invalid-password");
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
      aAccessMgr.setUserPassword (sUserID, sPlainTextPassword);
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

          AuditUtils.onAuditExecuteSuccess ("logout-in-login", sUserID);
          bLoggedOutUser = true;
        }
        else
        {
          AuditUtils.onAuditExecuteFailure ("login", sUserID, "user-already-logged-in");
          return _onLoginError (sUserID, ELoginResult.USER_ALREADY_LOGGED_IN);
        }
      }

      final SessionUserHolder aSUH = SessionUserHolder.getInstance ();
      if (aSUH.hasUser ())
      {
        // This session already has a user
        s_aLogger.warn ("The session user holder already has the user ID '" +
                        aSUH.getUserID () +
                        "' so the new ID '" +
                        sUserID +
                        "' will not be set!");
        AuditUtils.onAuditExecuteFailure ("login", sUserID, "session-already-has-user");
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
    AuditUtils.onAuditExecuteSuccess ("login", sUserID, aUser.getLoginName ());

    // Execute callback as the very last action
    for (final IUserLoginCallback aUserLoginCallback : m_aUserLoginCallbacks.getAllCallbacks ())
      try
      {
        aUserLoginCallback.onUserLogin (aInfo);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserLogin callback on " +
                         aUserLoginCallback.toString () +
                         "(" +
                         aInfo.toString () +
                         ")",
                         t);
      }

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
    m_aRWLock.writeLock ().lock ();
    LoginInfo aInfo;
    try
    {
      aInfo = m_aLoggedInUsers.remove (sUserID);
      if (aInfo == null)
      {
        AuditUtils.onAuditExecuteSuccess ("logout", sUserID, "user-not-logged-in");
        return EChange.UNCHANGED;
      }

      // Ensure that the SessionUser is empty. This is only relevant if user is
      // manually logged out without destructing the underlying session
      final SessionUserHolder aSUH = SessionUserHolder.getInstanceIfInstantiatedInScope (aInfo.getSessionScope ());
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
                    new Period (aInfo.getLoginDT (), aInfo.getLogoutDT ()).toString ());
    AuditUtils.onAuditExecuteSuccess ("logout", sUserID);

    // Execute callback as the very last action
    for (final IUserLogoutCallback aUserLogoutCallback : m_aUserLogoutCallbacks.getAllCallbacks ())
      try
      {
        aUserLogoutCallback.onUserLogout (aInfo);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onUserLogout callback on " +
                         aUserLogoutCallback.toString () +
                         "(" +
                         aInfo.toString () +
                         ")",
                         t);
      }

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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aLoggedInUsers.containsKey (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return A non-<code>null</code> but maybe empty set with all currently
   *         logged in user IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllLoggedInUserIDs ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newSet (m_aLoggedInUsers.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aLoggedInUsers.get (sUserID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return A non-<code>null</code> but maybe empty collection with the details
   *         of all currently logged in users.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Collection <LoginInfo> getAllLoginInfos ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aLoggedInUsers.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The number of currently logged in users. Always &ge; 0.
   */
  @Nonnegative
  public int getLoggedInUserCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aLoggedInUsers.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The ID of the user logged in this session or <code>null</code> if
   *         no user is logged in.
   */
  @Nullable
  public String getCurrentUserID ()
  {
    final SessionUserHolder aSUH = SessionUserHolder.getInstanceIfInstantiated ();
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
    final SessionUserHolder aSUH = SessionUserHolder.getInstanceIfInstantiated ();
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
