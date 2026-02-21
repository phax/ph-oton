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
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.photon.jdbc.sysmigration.SystemMigrationManagerJDBC;
import com.helger.photon.mgrs.PhotonBasicManager;
import com.helger.photon.mgrs.longrun.ILongRunningJobResultManager;
import com.helger.photon.mgrs.longrun.LongRunningJobResultManager;
import com.helger.photon.mgrs.sysmigration.ISystemMigrationManager;
import com.helger.photon.mgrs.systemmsg.ISystemMessageManager;
import com.helger.photon.mgrs.systemmsg.SystemMessageManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.mgr.PhotonSecurityManager.IFactory;

/**
 * An implementation of {@link IFactory} for JDBC based managers.
 *
 * @author Philip Helger
 * @since 10.2.0
 */
public class PhotonBasicManagerFactoryJDBC implements PhotonBasicManager.IFactory
{
  private final Supplier <? extends DBExecutor> m_aDBExecSupplier;
  private final Function <String, String> m_aTableNameCustomizer;

  public PhotonBasicManagerFactoryJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                                        @NonNull final Function <String, String> aTableNameCustomizer)
  {
    ValueEnforcer.notNull (aDBExecSupplier, "DBExecSupplier");
    ValueEnforcer.notNull (aTableNameCustomizer, "TableNameCustomizer");
    m_aDBExecSupplier = aDBExecSupplier;
    m_aTableNameCustomizer = aTableNameCustomizer;
  }

  @NonNull
  public ISystemMigrationManager createSystemMigrationManager () throws Exception
  {
    return new SystemMigrationManagerJDBC (m_aDBExecSupplier, m_aTableNameCustomizer);
  }

  public @NonNull ISystemMessageManager createSystemMessageManager () throws Exception
  {
    return new SystemMessageManager (PhotonBasicManager.FactoryXML.SYSTEM_MESSAGE_XML);
  }

  public @NonNull ILongRunningJobResultManager createLongRunningJobResultManager () throws Exception
  {
    return new LongRunningJobResultManager (PhotonBasicManager.FactoryXML.LONG_RUNNING_JOB_RESULTS_XML);
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
        throw new IllegalStateException ("PhotonBasicManager is already initialized - call this method earlier");

    // First set the factory
    PhotonBasicManager.setFactory (new PhotonBasicManagerFactoryJDBC (aDBExecSupplier, aTableNameCustomizer));
    // then get it installed
    PhotonBasicManager.getInstance ();
  }
}
