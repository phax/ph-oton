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
package com.helger.photon.basic.mgr;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.scope.IScope;
import com.helger.commons.scope.singleton.AbstractGlobalSingleton;
import com.helger.photon.basic.app.systemmsg.SystemMessageManager;
import com.helger.photon.basic.favorites.FavoriteManager;
import com.helger.photon.basic.longrun.LongRunningJobManager;
import com.helger.photon.basic.longrun.LongRunningJobResultManager;
import com.helger.photon.basic.migration.SystemMigrationManager;
import com.helger.photon.basic.security.audit.AuditManager;
import com.helger.photon.basic.security.audit.AuditHelper;
import com.helger.photon.basic.security.lock.ObjectLockManager;
import com.helger.photon.basic.security.login.LoggedInUserManager;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link AuditManager}</li>
 * <li>{@link FavoriteManager}</li>
 * <li>{@link LongRunningJobManager}</li>
 * <li>{@link LongRunningJobResultManager}</li>
 * <li>{@link SystemMessageManager}</li>
 * <li>{@link SystemMigrationManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonBasicManager extends AbstractGlobalSingleton
{
  public static final String DIRECTORY_AUDITS = "audits/";
  public static final String DIRECTORY_HTML = "html/";
  public static final String FAVORITES_XML = "favorites.xml";
  public static final String LONG_RUNNING_JOB_RESULTS_XML = "long-running-job-results.xml";
  public static final String SYSTEM_MIGRATIONS_XML = "systemmigrations.xml";
  public static final String SYSTEM_MESSAGE_XML = "systemmessage.xml";

  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonBasicManager.class);

  private AuditManager m_aAuditMgr;
  private FavoriteManager m_aFavoriteManager;
  private LongRunningJobManager m_aLongRunningJobMgr;
  private LongRunningJobResultManager m_aLongRunningJobResultMgr;
  private SystemMessageManager m_aSystemMessageMgr;
  private SystemMigrationManager m_aSystemMigrationMgr;

  @Deprecated
  @UsedViaReflection
  public PhotonBasicManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aAuditMgr = new AuditManager (DIRECTORY_AUDITS, LoggedInUserManager.getInstance ());
      AuditHelper.setAuditor (m_aAuditMgr.getAuditor ());
      AuditHelper.onAuditExecuteSuccess ("audit-initialized");

      m_aFavoriteManager = new FavoriteManager (FAVORITES_XML);

      m_aSystemMigrationMgr = new SystemMigrationManager (SYSTEM_MIGRATIONS_XML);

      m_aSystemMessageMgr = new SystemMessageManager (SYSTEM_MESSAGE_XML);

      m_aLongRunningJobResultMgr = new LongRunningJobResultManager (LONG_RUNNING_JOB_RESULTS_XML);
      m_aLongRunningJobMgr = new LongRunningJobManager (m_aLongRunningJobResultMgr);

      s_aLogger.info (ClassHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final Throwable t)
    {
      throw new InitializationException ("Failed to init " + ClassHelper.getClassLocalName (this), t);
    }
  }

  @Override
  protected void onBeforeDestroy (@Nonnull final IScope aScopeToBeDestroyed)
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
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    if (m_aAuditMgr != null)
    {
      AuditHelper.setDefaultAuditor ();
      m_aAuditMgr.stop ();
    }
  }

  @Nonnull
  public static PhotonBasicManager getInstance ()
  {
    return getGlobalSingleton (PhotonBasicManager.class);
  }

  @Nonnull
  public static AuditManager getAuditMgr ()
  {
    return getInstance ().m_aAuditMgr;
  }

  @Nonnull
  public static FavoriteManager getFavoriteManager ()
  {
    return getInstance ().m_aFavoriteManager;
  }

  @Nonnull
  public static ObjectLockManager getLockMgr ()
  {
    return ObjectLockManager.getInstance ();
  }

  @Nonnull
  public static SystemMigrationManager getSystemMigrationMgr ()
  {
    return getInstance ().m_aSystemMigrationMgr;
  }

  @Nonnull
  public static SystemMessageManager getSystemMessageMgr ()
  {
    return getInstance ().m_aSystemMessageMgr;
  }

  @Nonnull
  public static LongRunningJobResultManager getLongRunningJobResultMgr ()
  {
    return getInstance ().m_aLongRunningJobResultMgr;
  }

  @Nonnull
  public static LongRunningJobManager getLongRunningJobMgr ()
  {
    return getInstance ().m_aLongRunningJobMgr;
  }
}
