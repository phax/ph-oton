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
package com.helger.webbasics.mgr;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.app.dao.impl.DAOException;
import com.helger.appbasics.app.systemmsg.SystemMessageManager;
import com.helger.appbasics.longrun.LongRunningJobManager;
import com.helger.appbasics.longrun.LongRunningJobResultManager;
import com.helger.appbasics.migration.SystemMigrationManager;
import com.helger.appbasics.security.audit.AuditManager;
import com.helger.appbasics.security.audit.AuditUtils;
import com.helger.appbasics.security.lock.ObjectLockManager;
import com.helger.appbasics.security.login.LoggedInUserManager;
import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.exceptions.InitializationException;
import com.helger.commons.scopes.IScope;
import com.helger.commons.scopes.singleton.GlobalSingleton;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.webbasics.app.html.HTMLConfigManager;
import com.helger.webbasics.favorites.FavoriteManager;
import com.helger.webbasics.go.GoMappingManager;
import com.helger.webbasics.resource.WebSiteResourceBundleManager;
import com.helger.webbasics.smtp.FailedMailQueueWithDAO;
import com.helger.webbasics.smtp.NamedSMTPSettingsManager;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link AuditManager}</li>
 * <li>{@link FailedMailQueueWithDAO}</li>
 * <li>{@link FavoriteManager}</li>
 * <li>{@link GoMappingManager}</li>
 * <li>{@link HTMLConfigManager}</li>
 * <li>{@link LongRunningJobManager}</li>
 * <li>{@link LongRunningJobResultManager}</li>
 * <li>{@link NamedSMTPSettingsManager}</li>
 * <li>{@link SystemMessageManager}</li>
 * <li>{@link SystemMigrationManager}</li>
 * <li>{@link WebSiteResourceBundleManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class MetaSystemManager extends GlobalSingleton
{
  public static final String DIRECTORY_AUDITS = "audits/";
  public static final String DIRECTORY_HTML = "html/";
  public static final String SMTP_SETTINGS_XML = "smtpsettings.xml";
  public static final String FAILED_MAILS_XML = "failedmails.xml";
  public static final String FAVORITES_XML = "favorites.xml";
  public static final String SYSTEM_MIGRATIONS_XML = "systemmigrations.xml";
  public static final String SYSTEM_MESSAGE_XML = "systemmessage.xml";
  public static final String GO_XML = "go.xml";
  public static final String RESOURCE_BUNDLES_XML = "resource-bundles.xml";
  public static final String LONG_RUNNING_JOB_RESULTS_XML = "long-running-job-results.xml";

  private static final Logger s_aLogger = LoggerFactory.getLogger (MetaSystemManager.class);

  private AuditManager m_aAuditMgr;
  private FailedMailQueueWithDAO m_aFailedMailQueue;
  private FavoriteManager m_aFavoriteManager;
  private GoMappingManager m_aGoMappingMgr;
  private HTMLConfigManager m_aHTMLConfigMgr;
  private LongRunningJobManager m_aLongRunningJobMgr;
  private LongRunningJobResultManager m_aLongRunningJobResultMgr;
  private NamedSMTPSettingsManager m_aSMTPSettingsMgr;
  private SystemMessageManager m_aSystemMessageMgr;
  private SystemMigrationManager m_aSystemMigrationMgr;
  private WebSiteResourceBundleManager m_aWebSiteResourceBundleMgr;

  @Deprecated
  @UsedViaReflection
  public MetaSystemManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aAuditMgr = new AuditManager (DIRECTORY_AUDITS, LoggedInUserManager.getInstance ());
      AuditUtils.setAuditor (m_aAuditMgr.getAuditor ());
      AuditUtils.onAuditExecuteSuccess ("audit-initialized");

      m_aHTMLConfigMgr = new HTMLConfigManager (DIRECTORY_HTML);

      m_aSMTPSettingsMgr = new NamedSMTPSettingsManager (SMTP_SETTINGS_XML);

      m_aFailedMailQueue = new FailedMailQueueWithDAO (FAILED_MAILS_XML);
      ScopedMailAPI.getInstance ().setFailedMailQueue (m_aFailedMailQueue);

      m_aFavoriteManager = new FavoriteManager (FAVORITES_XML);

      m_aSystemMigrationMgr = new SystemMigrationManager (SYSTEM_MIGRATIONS_XML);

      m_aSystemMessageMgr = new SystemMessageManager (SYSTEM_MESSAGE_XML);

      m_aGoMappingMgr = new GoMappingManager (GO_XML);

      m_aWebSiteResourceBundleMgr = new WebSiteResourceBundleManager (RESOURCE_BUNDLES_XML);

      m_aLongRunningJobResultMgr = new LongRunningJobResultManager (LONG_RUNNING_JOB_RESULTS_XML);
      m_aLongRunningJobMgr = new LongRunningJobManager (m_aLongRunningJobResultMgr);

      s_aLogger.info ("MetaSystemManager was initialized");
    }
    catch (final DAOException ex)
    {
      throw new InitializationException ("Failed to init managers", ex);
    }
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction)
  {
    // Don't reset the FailedMailQueue, as no global scope is available anymore!

    if (m_aAuditMgr != null)
    {
      AuditUtils.onAuditExecuteSuccess ("audit-shutdown");
      AuditUtils.setDefaultAuditor ();
      m_aAuditMgr.stop ();
    }
  }

  @Nonnull
  public static MetaSystemManager getInstance ()
  {
    return getGlobalSingleton (MetaSystemManager.class);
  }

  @Nonnull
  public static AuditManager getAuditMgr ()
  {
    return getInstance ().m_aAuditMgr;
  }

  @Nonnull
  public static HTMLConfigManager getHTMLConfigMgr ()
  {
    return getInstance ().m_aHTMLConfigMgr;
  }

  @Nonnull
  public static NamedSMTPSettingsManager getSMTPSettingsMgr ()
  {
    return getInstance ().m_aSMTPSettingsMgr;
  }

  @Nonnull
  public static FailedMailQueueWithDAO getFailedMailQueue ()
  {
    return getInstance ().m_aFailedMailQueue;
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
  public static GoMappingManager getGoMappingMgr ()
  {
    return getInstance ().m_aGoMappingMgr;
  }

  @Nonnull
  public static WebSiteResourceBundleManager getWebSiteResourceBundleMgr ()
  {
    return getInstance ().m_aWebSiteResourceBundleMgr;
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
