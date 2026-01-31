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
package com.helger.photon.security.mgr;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.exception.InitializationException;
import com.helger.base.lang.clazz.ClassHelper;
import com.helger.dao.DAOException;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.audit.AuditManager;
import com.helger.photon.audit.IAuditManager;
import com.helger.photon.security.lock.DefaultLockManager;
import com.helger.photon.security.lock.ObjectLockManager;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.login.IUserLoginCallback;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.login.LoginInfo;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.photon.security.token.user.UserTokenManager;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.usergroup.IUserGroupManager;
import com.helger.photon.security.usergroup.UserGroupManager;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link AuditManager}</li>
 * <li>{@link UserManager}</li>
 * <li>{@link RoleManager}</li>
 * <li>{@link UserGroupManager}</li>
 * <li>{@link UserTokenManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonSecurityManager extends AbstractGlobalSingleton
{
  /**
   * Manager factory interface.
   *
   * @author Philip Helger
   * @since 8.2.4
   */
  public interface IFactory
  {
    /**
     * @return A new instance of {@link IAuditManager}.
     * @throws Exception
     *         In case of error
     * @since 8.3.2
     */
    @NonNull
    IAuditManager createAuditManager () throws Exception;

    /**
     * @return A new instance of {@link IUserManager}
     * @throws Exception
     *         In case of error
     */
    @NonNull
    IUserManager createUserMgr () throws Exception;

    /**
     * @return A new instance of {@link IRoleManager}
     * @throws Exception
     *         In case of error
     */
    @NonNull
    IRoleManager createRoleMgr () throws Exception;

    /**
     * @param aUserMgr
     *        The user manager. Never <code>null</code>.
     * @param aRoleMgr
     *        The role manager. Never <code>null</code>.
     * @return A new instance of {@link IUserGroupManager}
     * @throws Exception
     *         In case of error
     */
    @NonNull
    IUserGroupManager createUserGroupMgr (@NonNull IUserManager aUserMgr,
                                          @NonNull IRoleManager aRoleMgr) throws Exception;

    /**
     * @param aUserMgr
     *        The user manager. Never <code>null</code>.
     * @return A new instance of {@link IUserTokenManager}
     * @throws Exception
     *         In case of error
     */
    @NonNull
    IUserTokenManager createUserTokenMgr (@NonNull final IUserManager aUserMgr) throws Exception;
  }

  /**
   * Default {@link IFactory} implementation using XML backend.
   *
   * @author Philip Helger
   * @since 8.2.4
   */
  @Immutable
  public static class FactoryXML implements IFactory
  {
    public static final String DIRECTORY_AUDITS = "audits/";
    public static final String DIRECTORY_SECURITY = "security/";
    public static final String FILENAME_USERS_XML = "users.xml";
    public static final String FILENAME_ROLES_XML = "roles.xml";
    public static final String FILENAME_USERGROUPS_XML = "usergroups.xml";
    public static final String FILENAME_USERTOKENS_XML = "usertokens.xml";

    @NonNull
    public IAuditManager createAuditManager () throws DAOException
    {
      return new AuditManager (DIRECTORY_AUDITS, LoggedInUserManager.getInstance ());
    }

    @NonNull
    public IUserManager createUserMgr () throws DAOException
    {
      return new UserManager (DIRECTORY_SECURITY + FILENAME_USERS_XML);
    }

    @NonNull
    public IRoleManager createRoleMgr () throws DAOException
    {
      return new RoleManager (DIRECTORY_SECURITY + FILENAME_ROLES_XML);
    }

    @NonNull
    public IUserGroupManager createUserGroupMgr (@NonNull final IUserManager aUserMgr,
                                                 @NonNull final IRoleManager aRoleMgr) throws DAOException
    {
      return new UserGroupManager (DIRECTORY_SECURITY + FILENAME_USERGROUPS_XML, aUserMgr, aRoleMgr);
    }

    @NonNull
    public IUserTokenManager createUserTokenMgr (@NonNull final IUserManager aUserMgr) throws DAOException
    {
      return new UserTokenManager (DIRECTORY_SECURITY + FILENAME_USERTOKENS_XML);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonSecurityManager.class);
  private static final AtomicBoolean INITED = new AtomicBoolean (false);

  /** The global factory to be used. */
  private static IFactory s_aFactory = new FactoryXML ();

  /**
   * @return <code>true</code> if the {@link PhotonSecurityManager} was already
   *         initialized, <code>false</code> if not.
   * @since 8.3.2
   */
  public static boolean isAlreadyInitialized ()
  {
    return INITED.get ();
  }

  /**
   * @return The currently installed factory for security managers. By default
   *         an instance of {@link FactoryXML} is returned.
   * @see #setFactory(IFactory)
   */
  @NonNull
  public static IFactory getFactory ()
  {
    return s_aFactory;
  }

  /**
   * Set a new global factory for security managers.
   *
   * @param aFactory
   *        The new factory to be set. May not be <code>null</code>.
   */
  public static void setFactory (@NonNull final IFactory aFactory)
  {
    ValueEnforcer.notNull (aFactory, "Factory");
    if (isAlreadyInitialized ())
    {
      LOGGER.error ("Setting the PhotonSecurityManager factory after initialization has no effect and is ignored!");
    }
    else
    {
      s_aFactory = aFactory;
      LOGGER.info ("Setting the PhotonSecurityManager to " + aFactory.toString ());
    }
  }

  private IAuditManager m_aAuditMgr;
  private IUserManager m_aUserMgr;
  private IRoleManager m_aRoleMgr;
  private IUserGroupManager m_aUserGroupMgr;
  private IUserTokenManager m_aUserTokenMgr;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonSecurityManager ()
  {}

  private void _initCallbacks ()
  {
    // Remember the last login date of the user
    LoggedInUserManager.getInstance ().userLoginCallbacks ().add (new IUserLoginCallback ()
    {
      @Override
      public void onUserLogin (@NonNull final LoginInfo aInfo)
      {
        m_aUserMgr.updateUserLastLogin (aInfo.getUserID ());
      }

      @Override
      public void onUserLoginError (@NonNull @Nonempty final String sUserID, @NonNull final ELoginResult eLoginResult)
      {
        if (eLoginResult == ELoginResult.INVALID_PASSWORD)
        {
          // On invalid password, update consecutive failed login count
          m_aUserMgr.updateUserLastFailedLogin (sUserID);
        }
      }
    });
  }

  @Override
  protected void onAfterInstantiation (@NonNull final IScope aScope)
  {
    try
    {
      m_aAuditMgr = s_aFactory.createAuditManager ();
      AuditHelper.setAuditor (m_aAuditMgr.getAuditor ());
      AuditHelper.onAuditExecuteSuccess ("audit-initialized");

      m_aUserMgr = s_aFactory.createUserMgr ();
      m_aRoleMgr = s_aFactory.createRoleMgr ();
      m_aUserGroupMgr = s_aFactory.createUserGroupMgr (m_aUserMgr, m_aRoleMgr);
      m_aUserTokenMgr = s_aFactory.createUserTokenMgr (m_aUserMgr);
      INITED.set (true);

      // Init callbacks after all managers
      _initCallbacks ();

      LOGGER.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Exception ex)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), ex);
    }
  }

  @Override
  protected void onBeforeDestroy (@NonNull final IScope aScopeToBeDestroyed)
  {
    if (m_aAuditMgr != null)
    {
      /*
       * Call here to ensure that the AuditManager is still present! Otherwise
       * the destruction order of the singletons is relevant!
       */
      AuditHelper.onAuditExecuteSuccess ("audit-shutdown");
    }
  }

  @Override
  protected void onDestroy (@NonNull final IScope aScopeInDestruction)
  {
    if (m_aAuditMgr != null)
    {
      AuditHelper.setDefaultAuditor ();
      m_aAuditMgr.stop ();
    }
  }

  @NonNull
  public static PhotonSecurityManager getInstance ()
  {
    return getGlobalSingleton (PhotonSecurityManager.class);
  }

  @NonNull
  public static IAuditManager getAuditMgr ()
  {
    return getInstance ().m_aAuditMgr;
  }

  @NonNull
  public static DefaultLockManager <String> getLockMgr ()
  {
    return ObjectLockManager.getInstance ().getDefaultLockMgr ();
  }

  @NonNull
  public static IRoleManager getRoleMgr ()
  {
    return getInstance ().m_aRoleMgr;
  }

  @NonNull
  public static IUserManager getUserMgr ()
  {
    return getInstance ().m_aUserMgr;
  }

  @NonNull
  public static IUserGroupManager getUserGroupMgr ()
  {
    return getInstance ().m_aUserGroupMgr;
  }

  @NonNull
  public static IUserTokenManager getUserTokenMgr ()
  {
    return getInstance ().m_aUserTokenMgr;
  }
}
