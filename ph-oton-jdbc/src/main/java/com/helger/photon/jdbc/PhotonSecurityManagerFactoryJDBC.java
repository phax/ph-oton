/*
 * Copyright (C) 2021-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc;

import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.dao.DAOException;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.photon.audit.IAuditManager;
import com.helger.photon.jdbc.audit.AuditManagerJDBC;
import com.helger.photon.jdbc.security.RoleManagerJDBC;
import com.helger.photon.jdbc.security.UserGroupManagerJDBC;
import com.helger.photon.jdbc.security.UserManagerJDBC;
import com.helger.photon.jdbc.security.UserTokenManagerJDBC;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.mgr.PhotonSecurityManager.IFactory;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.usergroup.IUserGroupManager;

/**
 * An implementation of {@link IFactory} for JDBC based managers.
 *
 * @author Philip Helger
 */
public class PhotonSecurityManagerFactoryJDBC implements PhotonSecurityManager.IFactory
{
  private final Supplier <? extends DBExecutor> m_aDBExecSupplier;
  private final Function <String, String> m_aTableNameCustomizer;

  public PhotonSecurityManagerFactoryJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                                           @NonNull final Function <String, String> aTableNameCustomizer)
  {
    ValueEnforcer.notNull (aDBExecSupplier, "DBExecSupplier");
    ValueEnforcer.notNull (aTableNameCustomizer, "TableNameCustomizer");
    m_aDBExecSupplier = aDBExecSupplier;
    m_aTableNameCustomizer = aTableNameCustomizer;
  }

  @NonNull
  public IAuditManager createAuditMgr () throws Exception
  {
    return new AuditManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer);
  }

  @NonNull
  public IUserManager createUserMgr () throws DAOException
  {
    return new UserManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer);
  }

  @NonNull
  public IRoleManager createRoleMgr () throws DAOException
  {
    return new RoleManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer);
  }

  @NonNull
  public IUserGroupManager createUserGroupMgr (@NonNull final IUserManager aUserMgr,
                                               @NonNull final IRoleManager aRoleMgr) throws DAOException
  {
    return new UserGroupManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer, aUserMgr, aRoleMgr);
  }

  @NonNull
  public IUserTokenManager createUserTokenMgr (@NonNull final IUserManager aUserMgr) throws DAOException
  {
    return new UserTokenManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer, aUserMgr);
  }

  /**
   * @param aDBExecSupplier
   *        The main supplier for {@link DBExecutor} objects. This will be passed to all the main
   *        managers. May not be <code>null</code>.
   * @param aTableNameCustomizer
   *        A customizer for database table names used by this class. May not be <code>null</code>.
   */
  public static void install (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                              @NonNull final Function <String, String> aTableNameCustomizer)
  {
    ValueEnforcer.notNull (aDBExecSupplier, "DBExecSupplier");
    ValueEnforcer.notNull (aTableNameCustomizer, "TableNameCustomizer");

    // Required for unit tests, to set it again and again and again
    if (false)
      if (PhotonSecurityManager.isAlreadyInitialized ())
        throw new IllegalStateException ("PhotonSecurityManager is already initialized - call this method earlier");

    // First set the factory
    PhotonSecurityManager.setFactory (new PhotonSecurityManagerFactoryJDBC (aDBExecSupplier, aTableNameCustomizer));
    // then get it installed
    PhotonSecurityManager.getInstance ();
  }
}
