/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.photon.app.PhotonAppManager;
import com.helger.photon.app.resource.WebSiteResourceBundleManager;
import com.helger.photon.audit.IAuditManager;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoAPI;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoAjaxFunctions;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoConfigurationFiles;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoGlobalScope;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoGo;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoPathMapper;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoScheduler;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoServletStatus;
import com.helger.photon.bootstrap4.pages.appinfo.BasePageAppInfoWebSiteResourceBundles;
import com.helger.photon.bootstrap4.pages.data.BasePageDataCountries;
import com.helger.photon.bootstrap4.pages.data.BasePageDataCurrencies;
import com.helger.photon.bootstrap4.pages.data.BasePageDataLanguages;
import com.helger.photon.bootstrap4.pages.data.BasePageDataTimeZones;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringAudit;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringFailedMails;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringLockedObjects;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringLoginInfo;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringSessions;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringStatistics;
import com.helger.photon.bootstrap4.pages.monitoring.BasePageMonitoringSystemMigrations;
import com.helger.photon.bootstrap4.pages.security.BasePageSecurityRoleManagement;
import com.helger.photon.bootstrap4.pages.security.BasePageSecurityUserGroupManagement;
import com.helger.photon.bootstrap4.pages.security.BasePageSecurityUserManagement;
import com.helger.photon.bootstrap4.pages.security.BasePageSecurityUserTokenManagement;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsGlobal;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsHTML;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsLogLevel;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsSMTP;
import com.helger.photon.bootstrap4.pages.settings.BasePageSettingsSystemMessage;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoCACerts;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoEnvironmentVariables;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoNetwork;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoRequest;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoSecurity;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoServletContext;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoSystemProperties;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoThirdPartyLibraries;
import com.helger.photon.bootstrap4.pages.sysinfo.BasePageSysInfoThreads;
import com.helger.photon.bootstrap4.pages.utils.BasePageUtilsBase64Decode;
import com.helger.photon.bootstrap4.pages.utils.BasePageUtilsBase64Encode;
import com.helger.photon.bootstrap4.pages.utils.BasePageUtilsHttpClient;
import com.helger.photon.bootstrap4.pages.utils.BasePageUtilsPortChecker;
import com.helger.photon.core.go.GoMappingManager;
import com.helger.photon.core.menu.IMenuItem;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuObjectFilter;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.mgr.PhotonBasicManager;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.smtp.NamedSMTPSettingsManager;
import com.helger.photon.core.sysmigration.SystemMigrationManager;
import com.helger.photon.security.lock.ILockManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.system.BasePageShowChildren;
import com.helger.smtp.failed.FailedMailQueue;

@Immutable
public final class BootstrapPagesMenuConfigurator
{
  public static final String MENU_ADMIN_SECURITY = "admin_security";
  public static final String MENU_ADMIN_SECURITY_USER = "admin_security_user";
  public static final String MENU_ADMIN_SECURITY_USER_GROUP = "admin_security_usergroup";
  public static final String MENU_ADMIN_SECURITY_ROLE = "admin_security_role";
  public static final String MENU_ADMIN_SECURITY_USER_TOKEN = "admin_security_usertoken";

  public static final String MENU_ADMIN_MONITORING = "admin_monitoring";
  public static final String MENU_ADMIN_MONITORING_AUDIT = "admin_monitoring_audit";
  public static final String MENU_ADMIN_MONITORING_FAILEDMAILS = "admin_monitoring_failedmails";
  public static final String MENU_ADMIN_MONITORING_LOCKEDOBJECTS = "admin_monitoring_lockedobjects";
  public static final String MENU_ADMIN_MONITORING_LOGININFO = "admin_monitoring_logininfo";
  public static final String MENU_ADMIN_MONITORING_SESSIONS = "admin_monitoring_sessions";
  public static final String MENU_ADMIN_MONITORING_STATISTICS = "admin_monitoring_statistics";
  public static final String MENU_ADMIN_MONITORING_SYSTEMMIGRATIONS = "admin_monitoring_systemmigrations";

  public static final String MENU_ADMIN_SYSINFO = "admin_sysinfo";
  public static final String MENU_ADMIN_SYSINFO_CACERTS = "admin_sysinfo_cacerts";
  public static final String MENU_ADMIN_SYSINFO_ENVVARS = "admin_sysinfo_envvars";
  public static final String MENU_ADMIN_SYSINFO_NETWORK = "admin_sysinfo_network";
  public static final String MENU_ADMIN_SYSINFO_REQUEST = "admin_sysinfo_request";
  public static final String MENU_ADMIN_SYSINFO_SECURITY = "admin_sysinfo_security";
  public static final String MENU_ADMIN_SYSINFO_SERVLETCONTEXT = "admin_sysinfo_servletcontext";
  public static final String MENU_ADMIN_SYSINFO_SYSPROPS = "admin_sysinfo_sysprops";
  public static final String MENU_ADMIN_SYSINFO_THIRDPARTYLIBS = "admin_sysinfo_thirdpartylibs";
  public static final String MENU_ADMIN_SYSINFO_THREADS = "admin_sysinfo_threads";

  public static final String MENU_ADMIN_APPINFO = "admin_appinfo";
  public static final String MENU_ADMIN_APPINFO_AJAX_FUNCTIONS = "admin_appinfo_ajax_functions";
  public static final String MENU_ADMIN_APPINFO_API = "admin_appinfo_api";
  public static final String MENU_ADMIN_APPINFO_CONFIGFILES = "admin_appinfo_configfiles";
  public static final String MENU_ADMIN_APPINFO_GO = "admin_appinfo_go";
  public static final String MENU_ADMIN_APPINFO_PATH_MAPPER = "admin_appinfo_pathmapper";
  public static final String MENU_ADMIN_APPINFO_SCHEDULER = "admin_appinfo_scheduler";
  public static final String MENU_ADMIN_APPINFO_GLOBAL_SCOPE = "admin_appinfo_scopes";
  public static final String MENU_ADMIN_APPINFO_SERVLETSTATUS = "admin_appinfo_servletstatus";
  public static final String MENU_ADMIN_APPINFO_WEBRESBUNDLE = "admin_appinfo_webresbundle";

  public static final String MENU_ADMIN_DATA = "admin_data";
  public static final String MENU_ADMIN_DATA_COUNTRIES = "admin_data_countries";
  public static final String MENU_ADMIN_DATA_CURRENCIES = "admin_data_currencies";
  public static final String MENU_ADMIN_DATA_LANGUAGES = "admin_data_languages";
  public static final String MENU_ADMIN_DATA_TIMEZONES = "admin_data_timezones";

  public static final String MENU_ADMIN_UTILS = "admin_utils";
  public static final String MENU_ADMIN_UTILS_BASE64_DECODE = "admin_utils_base64decode";
  public static final String MENU_ADMIN_UTILS_BASE64_ENCODE = "admin_utils_base64encode";
  public static final String MENU_ADMIN_UTILS_HTTP_CLIENT = "admin_utils_httpclient";
  public static final String MENU_ADMIN_UTILS_PORT_CHECKER = "admin_utils_portchecker";

  public static final String MENU_ADMIN_SETTINGS = "admin_settings";
  public static final String MENU_ADMIN_SETTINGS_GLOBAL = "admin_settings_global";
  public static final String MENU_ADMIN_SETTINGS_HTML = "admin_settings_html";
  public static final String MENU_ADMIN_SETTINGS_LOG_LEVEL = "admin_settings_log_level";
  public static final String MENU_ADMIN_SETTINGS_SMTP = "admin_settings_smtp";
  public static final String MENU_ADMIN_SETTINGS_SYSTEMMESSAGE = "admin_settings_systemmessage";

  private BootstrapPagesMenuConfigurator ()
  {}

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSecurityItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IMenuObjectFilter aDisplayFilter,
                                                                                            @Nonnull final Locale aDefaultLocale)
  {
    final IMenuItemPage aAdminSecurity = aMenuTree.createItem (aParent,
                                                               new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_SECURITY,
                                                                                                    EWebPageText.PAGE_NAME_SECURITY.getAsMLT (),
                                                                                                    aMenuTree))
                                                  .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity,
                          new BasePageSecurityUserManagement <WPECTYPE> (MENU_ADMIN_SECURITY_USER).setDefaultUserLocale (aDefaultLocale))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity, new BasePageSecurityUserGroupManagement <WPECTYPE> (MENU_ADMIN_SECURITY_USER_GROUP))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity, new BasePageSecurityRoleManagement <WPECTYPE> (MENU_ADMIN_SECURITY_ROLE))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity, new BasePageSecurityUserTokenManagement <WPECTYPE> (MENU_ADMIN_SECURITY_USER_TOKEN))
             .setDisplayFilter (aDisplayFilter);
    return aAdminSecurity;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addMonitoringItems (@Nonnull final IMenuTree aMenuTree,
                                                                                              @Nonnull final IMenuItem aParent,
                                                                                              @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    return BootstrapPagesMenuConfigurator.<WPECTYPE> addMonitoringItems (aMenuTree,
                                                                         aParent,
                                                                         aDisplayFilter,
                                                                         PhotonSecurityManager.getAuditMgr (),
                                                                         PhotonCoreManager.getFailedMailQueue (),
                                                                         PhotonSecurityManager.getLockMgr (),
                                                                         PhotonBasicManager.getSystemMigrationMgr ());
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addMonitoringItems (@Nonnull final IMenuTree aMenuTree,
                                                                                              @Nonnull final IMenuItem aParent,
                                                                                              @Nullable final IMenuObjectFilter aDisplayFilter,
                                                                                              @Nullable final IAuditManager aAuditMgr,
                                                                                              @Nullable final FailedMailQueue aFailedMailQueue,
                                                                                              @Nullable final ILockManager <String> aLockManager,
                                                                                              @Nullable final SystemMigrationManager aSystemMigrationMgr)
  {
    final IMenuItemPage aAdminMonitoring = aMenuTree.createItem (aParent,
                                                                 new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_MONITORING,
                                                                                                      EWebPageText.PAGE_NAME_MONITORING.getAsMLT (),
                                                                                                      aMenuTree))
                                                    .setDisplayFilter (aDisplayFilter);
    if (aAuditMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringAudit <WPECTYPE> (MENU_ADMIN_MONITORING_AUDIT, aAuditMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aFailedMailQueue != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringFailedMails <WPECTYPE> (MENU_ADMIN_MONITORING_FAILEDMAILS, aFailedMailQueue))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aLockManager != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringLockedObjects <WPECTYPE> (MENU_ADMIN_MONITORING_LOCKEDOBJECTS, aLockManager))
               .setDisplayFilter (aDisplayFilter);
    }
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringLoginInfo <WPECTYPE> (MENU_ADMIN_MONITORING_LOGININFO))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringSessions <WPECTYPE> (MENU_ADMIN_MONITORING_SESSIONS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringStatistics <WPECTYPE> (MENU_ADMIN_MONITORING_STATISTICS))
             .setDisplayFilter (aDisplayFilter);
    if (aSystemMigrationMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringSystemMigrations <WPECTYPE> (MENU_ADMIN_MONITORING_SYSTEMMIGRATIONS, aSystemMigrationMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    return aAdminMonitoring;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSysInfoItems (@Nonnull final IMenuTree aMenuTree,
                                                                                           @Nonnull final IMenuItem aParent,
                                                                                           @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    final IMenuItemPage aAdminSysInfo = aMenuTree.createItem (aParent,
                                                              new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_SYSINFO,
                                                                                                   EWebPageText.PAGE_NAME_SYSINFO.getAsMLT (),
                                                                                                   aMenuTree))
                                                 .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoCACerts <WPECTYPE> (MENU_ADMIN_SYSINFO_CACERTS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoEnvironmentVariables <WPECTYPE> (MENU_ADMIN_SYSINFO_ENVVARS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoNetwork <WPECTYPE> (MENU_ADMIN_SYSINFO_NETWORK))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoRequest <WPECTYPE> (MENU_ADMIN_SYSINFO_REQUEST))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoSecurity <WPECTYPE> (MENU_ADMIN_SYSINFO_SECURITY))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoServletContext <WPECTYPE> (MENU_ADMIN_SYSINFO_SERVLETCONTEXT))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoSystemProperties <WPECTYPE> (MENU_ADMIN_SYSINFO_SYSPROPS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoThirdPartyLibraries <WPECTYPE> (MENU_ADMIN_SYSINFO_THIRDPARTYLIBS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoThreads <WPECTYPE> (MENU_ADMIN_SYSINFO_THREADS))
             .setDisplayFilter (aDisplayFilter);
    return aAdminSysInfo;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addAppInfoItems (@Nonnull final IMenuTree aMenuTree,
                                                                                           @Nonnull final IMenuItem aParent,
                                                                                           @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    return BootstrapPagesMenuConfigurator.<WPECTYPE> addAppInfoItems (aMenuTree,
                                                                      aParent,
                                                                      aDisplayFilter,
                                                                      PhotonCoreManager.getGoMappingMgr (),
                                                                      PhotonAppManager.getWebSiteResourceBundleMgr ());
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addAppInfoItems (@Nonnull final IMenuTree aMenuTree,
                                                                                           @Nonnull final IMenuItem aParent,
                                                                                           @Nullable final IMenuObjectFilter aDisplayFilter,
                                                                                           @Nullable final GoMappingManager aGoMappingMgr,
                                                                                           @Nullable final WebSiteResourceBundleManager aResBundleMgr)
  {
    final IMenuItemPage aAdminAppInfo = aMenuTree.createItem (aParent,
                                                              new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_APPINFO,
                                                                                                   EWebPageText.PAGE_NAME_APPINFO.getAsMLT (),
                                                                                                   aMenuTree))
                                                 .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoAjaxFunctions <WPECTYPE> (MENU_ADMIN_APPINFO_AJAX_FUNCTIONS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoAPI <WPECTYPE> (MENU_ADMIN_APPINFO_API)).setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoConfigurationFiles <WPECTYPE> (MENU_ADMIN_APPINFO_CONFIGFILES))
             .setDisplayFilter (aDisplayFilter);
    if (aGoMappingMgr != null)
    {
      aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoGo <WPECTYPE> (MENU_ADMIN_APPINFO_GO, aGoMappingMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoPathMapper <WPECTYPE> (MENU_ADMIN_APPINFO_PATH_MAPPER))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoScheduler <WPECTYPE> (MENU_ADMIN_APPINFO_SCHEDULER))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoGlobalScope <WPECTYPE> (MENU_ADMIN_APPINFO_GLOBAL_SCOPE))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminAppInfo, new BasePageAppInfoServletStatus <WPECTYPE> (MENU_ADMIN_APPINFO_SERVLETSTATUS))
             .setDisplayFilter (aDisplayFilter);
    if (aResBundleMgr != null)
    {
      aMenuTree.createItem (aAdminAppInfo,
                            new BasePageAppInfoWebSiteResourceBundles <WPECTYPE> (MENU_ADMIN_APPINFO_WEBRESBUNDLE, aResBundleMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    return aAdminAppInfo;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addDataItems (@Nonnull final IMenuTree aMenuTree,
                                                                                        @Nonnull final IMenuItem aParent,
                                                                                        @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    final IMenuItemPage aAdminData = aMenuTree.createItem (aParent,
                                                           new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_DATA,
                                                                                                EWebPageText.PAGE_NAME_DATA.getAsMLT (),
                                                                                                aMenuTree))
                                              .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataCountries <WPECTYPE> (MENU_ADMIN_DATA_COUNTRIES)).setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataCurrencies <WPECTYPE> (MENU_ADMIN_DATA_CURRENCIES)).setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataLanguages <WPECTYPE> (MENU_ADMIN_DATA_LANGUAGES)).setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataTimeZones <WPECTYPE> (MENU_ADMIN_DATA_TIMEZONES)).setDisplayFilter (aDisplayFilter);
    return aAdminData;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addUtilsItems (@Nonnull final IMenuTree aMenuTree,
                                                                                         @Nonnull final IMenuItem aParent,
                                                                                         @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    final IMenuItemPage aAdminUtils = aMenuTree.createItem (aParent,
                                                            new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_UTILS,
                                                                                                 EWebPageText.PAGE_NAME_UTILS.getAsMLT (),
                                                                                                 aMenuTree))
                                               .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminUtils, new BasePageUtilsBase64Decode <WPECTYPE> (MENU_ADMIN_UTILS_BASE64_DECODE))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminUtils, new BasePageUtilsBase64Encode <WPECTYPE> (MENU_ADMIN_UTILS_BASE64_ENCODE))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminUtils, new BasePageUtilsHttpClient <WPECTYPE> (MENU_ADMIN_UTILS_HTTP_CLIENT))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminUtils, new BasePageUtilsPortChecker <WPECTYPE> (MENU_ADMIN_UTILS_PORT_CHECKER))
             .setDisplayFilter (aDisplayFilter);
    return aAdminUtils;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSettingsItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IMenuObjectFilter aDisplayFilter)
  {
    return BootstrapPagesMenuConfigurator.<WPECTYPE> addSettingsItems (aMenuTree,
                                                                       aParent,
                                                                       aDisplayFilter,
                                                                       PhotonCoreManager.getSMTPSettingsMgr ());
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSettingsItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IMenuObjectFilter aDisplayFilter,
                                                                                            @Nullable final NamedSMTPSettingsManager aNamedSMTPSettingsMgr)
  {
    final IMenuItemPage aAdminSettings = aMenuTree.createItem (aParent,
                                                               new BasePageShowChildren <WPECTYPE> (MENU_ADMIN_SETTINGS,
                                                                                                    EWebPageText.PAGE_NAME_SETTINGS.getAsMLT (),
                                                                                                    aMenuTree))
                                                  .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSettings, new BasePageSettingsGlobal <WPECTYPE> (MENU_ADMIN_SETTINGS_GLOBAL))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSettings, new BasePageSettingsHTML <WPECTYPE> (MENU_ADMIN_SETTINGS_HTML)).setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSettings, new BasePageSettingsLogLevel <WPECTYPE> (MENU_ADMIN_SETTINGS_LOG_LEVEL))
             .setDisplayFilter (aDisplayFilter);

    if (aNamedSMTPSettingsMgr != null)
    {
      aMenuTree.createItem (aAdminSettings, new BasePageSettingsSMTP <WPECTYPE> (aNamedSMTPSettingsMgr, MENU_ADMIN_SETTINGS_SMTP))
               .setDisplayFilter (aDisplayFilter);
    }

    aMenuTree.createItem (aAdminSettings, new BasePageSettingsSystemMessage <WPECTYPE> (MENU_ADMIN_SETTINGS_SYSTEMMESSAGE))
             .setDisplayFilter (aDisplayFilter);

    return aAdminSettings;
  }

  public static void addAllItems (@Nonnull final IMenuTree aMenuTree,
                                  @Nonnull final IMenuItem aParent,
                                  @Nullable final IMenuObjectFilter aDisplayFilter,
                                  @Nonnull final Locale aDefaultLocale)
  {
    BootstrapPagesMenuConfigurator.addSecurityItems (aMenuTree, aParent, aDisplayFilter, aDefaultLocale);
    BootstrapPagesMenuConfigurator.addMonitoringItems (aMenuTree, aParent, aDisplayFilter);
    BootstrapPagesMenuConfigurator.addSysInfoItems (aMenuTree, aParent, aDisplayFilter);
    BootstrapPagesMenuConfigurator.addAppInfoItems (aMenuTree, aParent, aDisplayFilter);
    BootstrapPagesMenuConfigurator.addDataItems (aMenuTree, aParent, aDisplayFilter);
    BootstrapPagesMenuConfigurator.addUtilsItems (aMenuTree, aParent, aDisplayFilter);
    BootstrapPagesMenuConfigurator.addSettingsItems (aMenuTree, aParent, aDisplayFilter);
  }
}
