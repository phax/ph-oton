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
package com.helger.photon.core.mgr;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.UsedViaReflection;
import com.helger.commons.exceptions.InitializationException;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.scopes.IScope;
import com.helger.commons.scopes.singleton.GlobalSingleton;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.security.lock.ObjectLockManager;
import com.helger.photon.core.app.html.HTMLConfigManager;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.core.go.GoMappingManager;
import com.helger.photon.core.resource.WebSiteResourceBundleManager;
import com.helger.photon.core.smtp.FailedMailQueueWithDAO;
import com.helger.photon.core.smtp.NamedSMTPSettingsManager;
import com.helger.smtp.scope.ScopedMailAPI;

/**
 * The meta system manager encapsulates all managers that are located in this
 * project. Currently the contained managers are:
 * <ul>
 * <li>{@link FailedMailQueueWithDAO}</li>
 * <li>{@link GoMappingManager}</li>
 * <li>{@link HTMLConfigManager}</li>
 * <li>{@link NamedSMTPSettingsManager}</li>
 * <li>{@link WebSiteResourceBundleManager}</li>
 * </ul>
 *
 * @author Philip Helger
 */
public final class PhotonCoreManager extends GlobalSingleton
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

  private static final Logger s_aLogger = LoggerFactory.getLogger (PhotonCoreManager.class);

  private FailedMailQueueWithDAO m_aFailedMailQueue;
  private GoMappingManager m_aGoMappingMgr;
  private HTMLConfigManager m_aHTMLConfigMgr;
  private NamedSMTPSettingsManager m_aSMTPSettingsMgr;
  private WebSiteResourceBundleManager m_aWebSiteResourceBundleMgr;

  @Deprecated
  @UsedViaReflection
  public PhotonCoreManager ()
  {}

  @Override
  protected void onAfterInstantiation (@Nonnull final IScope aScope)
  {
    try
    {
      m_aHTMLConfigMgr = new HTMLConfigManager ();
      PhotonCSS.readCSSIncludesForGlobal (new ClassPathResource (DIRECTORY_HTML + "css.xml"));
      PhotonJS.readJSIncludesForGlobal (new ClassPathResource (DIRECTORY_HTML + "js.xml"));
      m_aHTMLConfigMgr.readAllFiles (DIRECTORY_HTML);

      m_aSMTPSettingsMgr = new NamedSMTPSettingsManager (SMTP_SETTINGS_XML);

      m_aFailedMailQueue = new FailedMailQueueWithDAO (FAILED_MAILS_XML);
      ScopedMailAPI.getInstance ().setFailedMailQueue (m_aFailedMailQueue);

      m_aGoMappingMgr = new GoMappingManager (GO_XML);

      m_aWebSiteResourceBundleMgr = new WebSiteResourceBundleManager (RESOURCE_BUNDLES_XML);

      s_aLogger.info (CGStringHelper.getClassLocalName (this) + " was initialized");
    }
    catch (final DAOException ex)
    {
      throw new InitializationException ("Failed to init managers", ex);
    }
  }

  @Nonnull
  public static PhotonCoreManager getInstance ()
  {
    return getGlobalSingleton (PhotonCoreManager.class);
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
  public static ObjectLockManager getLockMgr ()
  {
    return ObjectLockManager.getInstance ();
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
}
