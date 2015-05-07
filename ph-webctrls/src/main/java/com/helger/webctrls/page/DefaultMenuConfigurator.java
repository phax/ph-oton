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
package com.helger.webctrls.page;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.app.menu.IMenuItem;
import com.helger.appbasics.app.menu.IMenuItemPage;
import com.helger.appbasics.app.menu.IMenuObject;
import com.helger.appbasics.app.menu.IMenuTree;
import com.helger.appbasics.migration.SystemMigrationManager;
import com.helger.appbasics.security.audit.IAuditManager;
import com.helger.appbasics.security.lock.ILockManager;
import com.helger.commons.filter.IFilter;
import com.helger.smtp.failed.FailedMailQueue;
import com.helger.webbasics.app.page.IWebPageExecutionContext;
import com.helger.webbasics.app.page.system.PageShowChildren;
import com.helger.webbasics.go.GoMappingManager;
import com.helger.webbasics.mgr.MetaSystemManager;
import com.helger.webbasics.resource.WebSiteResourceBundleManager;
import com.helger.webbasics.smtp.NamedSMTPSettingsManager;
import com.helger.webctrls.page.data.BasePageDataCountries;
import com.helger.webctrls.page.data.BasePageDataCurrencies;
import com.helger.webctrls.page.data.BasePageDataLanguages;
import com.helger.webctrls.page.data.BasePageDataTimeZones;
import com.helger.webctrls.page.monitoring.BasePageMonitoringActions;
import com.helger.webctrls.page.monitoring.BasePageMonitoringAjaxFunctions;
import com.helger.webctrls.page.monitoring.BasePageMonitoringAudit;
import com.helger.webctrls.page.monitoring.BasePageMonitoringFailedMails;
import com.helger.webctrls.page.monitoring.BasePageMonitoringGo;
import com.helger.webctrls.page.monitoring.BasePageMonitoringLockedObjects;
import com.helger.webctrls.page.monitoring.BasePageMonitoringLoginInfo;
import com.helger.webctrls.page.monitoring.BasePageMonitoringScheduler;
import com.helger.webctrls.page.monitoring.BasePageMonitoringScopes;
import com.helger.webctrls.page.monitoring.BasePageMonitoringServletContext;
import com.helger.webctrls.page.monitoring.BasePageMonitoringServletStatus;
import com.helger.webctrls.page.monitoring.BasePageMonitoringSessions;
import com.helger.webctrls.page.monitoring.BasePageMonitoringStatistics;
import com.helger.webctrls.page.monitoring.BasePageMonitoringSystemMigrations;
import com.helger.webctrls.page.monitoring.BasePageMonitoringWebSiteResourceBundles;
import com.helger.webctrls.page.security.BasePageSecurityRoleManagement;
import com.helger.webctrls.page.security.BasePageSecurityUserGroupManagement;
import com.helger.webctrls.page.security.BasePageSecurityUserManagement;
import com.helger.webctrls.page.settings.BasePageSettingsGlobal;
import com.helger.webctrls.page.settings.BasePageSettingsHTML;
import com.helger.webctrls.page.settings.BasePageSettingsSMTP;
import com.helger.webctrls.page.settings.BasePageSettingsSystemMessage;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoChangeLogs;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoEnvironmentVariables;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoNetwork;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoRequest;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoSecurity;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoSystemProperties;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoThirdPartyLibraries;
import com.helger.webctrls.page.sysinfo.BasePageSysInfoThreads;

@Immutable
public final class DefaultMenuConfigurator
{
  public static final String MENU_ADMIN_SECURITY = "admin_security";
  public static final String MENU_ADMIN_SECURITY_USER = "admin_security_user";
  public static final String MENU_ADMIN_SECURITY_USER_GROUP = "admin_security_usergroup";
  public static final String MENU_ADMIN_SECURITY_ROLE = "admin_security_role";
  public static final String MENU_ADMIN_MONITORING = "admin_monitoring";
  public static final String MENU_ADMIN_MONITORING_ACTIONS = "admin_monitoring_actions";
  public static final String MENU_ADMIN_MONITORING_AJAX_FUNCTIONS = "admin_monitoring_ajax_functions";
  public static final String MENU_ADMIN_MONITORING_AUDIT = "admin_monitoring_audit";
  public static final String MENU_ADMIN_MONITORING_FAILEDMAILS = "admin_monitoring_failedmails";
  public static final String MENU_ADMIN_MONITORING_GO = "admin_sysinfo_go";
  public static final String MENU_ADMIN_MONITORING_LOCKEDOBJECTS = "admin_monitoring_lockedobjects";
  public static final String MENU_ADMIN_MONITORING_LOGININFO = "admin_monitoring_logininfo";
  public static final String MENU_ADMIN_MONITORING_SCHEDULER = "admin_monitoring_scheduler";
  public static final String MENU_ADMIN_MONITORING_SCOPES = "admin_monitoring_scopes";
  public static final String MENU_ADMIN_MONITORING_SERVLETCONTEXT = "admin_sysinfo_servletcontext";
  public static final String MENU_ADMIN_MONITORING_SERVLETSTATUS = "admin_monitoring_servletstatus";
  public static final String MENU_ADMIN_MONITORING_SESSIONS = "admin_monitoring_sessions";
  public static final String MENU_ADMIN_MONITORING_STATISTICS = "admin_monitoring_statistics";
  public static final String MENU_ADMIN_MONITORING_SYSTEMMIGRATIONS = "admin_monitoring_systemmigrations";
  public static final String MENU_ADMIN_MONITORING_WEBRESBUNDLE = "admin_monitoring_webresbundle";
  public static final String MENU_ADMIN_SETTINGS = "admin_settings";
  public static final String MENU_ADMIN_SETTINGS_GLOBAL = "admin_settings_global";
  public static final String MENU_ADMIN_SETTINGS_HTML = "admin_settings_html";
  public static final String MENU_ADMIN_SETTINGS_SMTP = "admin_settings_smtp";
  public static final String MENU_ADMIN_SETTINGS_SYSTEMMESSAGE = "admin_settings_systemmessage";
  public static final String MENU_ADMIN_SYSINFO = "admin_sysinfo";
  public static final String MENU_ADMIN_SYSINFO_CHANGELOGS = "admin_sysinfo_changelog";
  public static final String MENU_ADMIN_SYSINFO_ENVVARS = "admin_sysinfo_envvars";
  public static final String MENU_ADMIN_SYSINFO_NETWORK = "admin_sysinfo_network";
  public static final String MENU_ADMIN_SYSINFO_REQUEST = "admin_sysinfo_request";
  public static final String MENU_ADMIN_SYSINFO_SECURITY = "admin_sysinfo_security";
  public static final String MENU_ADMIN_SYSINFO_SYSPROPS = "admin_sysinfo_sysprops";
  public static final String MENU_ADMIN_SYSINFO_THIRDPARTYLIBS = "admin_sysinfo_thirdpartylibs";
  public static final String MENU_ADMIN_SYSINFO_THREADS = "admin_sysinfo_threads";
  public static final String MENU_ADMIN_DATA = "admin_data";
  public static final String MENU_ADMIN_DATA_COUNTRIES = "admin_data_countries";
  public static final String MENU_ADMIN_DATA_CURRENCIES = "admin_data_currencies";
  public static final String MENU_ADMIN_DATA_LANGUAGES = "admin_data_languages";
  public static final String MENU_ADMIN_DATA_TIMEZONES = "admin_data_timezones";

  private DefaultMenuConfigurator ()
  {}

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addMonitoringItems (@Nonnull final IMenuTree aMenuTree,
                                                                                              @Nonnull final IMenuItem aParent,
                                                                                              @Nullable final IFilter <IMenuObject> aDisplayFilter)
  {
    return DefaultMenuConfigurator.<WPECTYPE> addMonitoringItems (aMenuTree,
                                                                  aParent,
                                                                  aDisplayFilter,
                                                                  MetaSystemManager.getAuditMgr (),
                                                                  MetaSystemManager.getFailedMailQueue (),
                                                                  MetaSystemManager.getLockMgr (),
                                                                  MetaSystemManager.getGoMappingMgr (),
                                                                  MetaSystemManager.getSystemMigrationMgr (),
                                                                  MetaSystemManager.getWebSiteResourceBundleMgr ());
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addMonitoringItems (@Nonnull final IMenuTree aMenuTree,
                                                                                              @Nonnull final IMenuItem aParent,
                                                                                              @Nullable final IFilter <IMenuObject> aDisplayFilter,
                                                                                              @Nullable final IAuditManager aAuditMgr,
                                                                                              @Nullable final FailedMailQueue aFailedMailQueue,
                                                                                              @Nullable final ILockManager <String> aLockManager,
                                                                                              @Nullable final GoMappingManager aGoMappingMgr,
                                                                                              @Nullable final SystemMigrationManager aSystemMigrationMgr,
                                                                                              @Nullable final WebSiteResourceBundleManager aResBundleMgr)
  {
    final IMenuItemPage aAdminMonitoring = aMenuTree.createItem (aParent,
                                                                 new PageShowChildren <WPECTYPE> (MENU_ADMIN_MONITORING,
                                                                                                  EWebPageText.PAGE_NAME_MONITORING.getAsMLT (),
                                                                                                  aMenuTree))
                                                    .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringActions <WPECTYPE> (MENU_ADMIN_MONITORING_ACTIONS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringAjaxFunctions <WPECTYPE> (MENU_ADMIN_MONITORING_AJAX_FUNCTIONS))
             .setDisplayFilter (aDisplayFilter);
    if (aAuditMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringAudit <WPECTYPE> (MENU_ADMIN_MONITORING_AUDIT, aAuditMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aFailedMailQueue != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringFailedMails <WPECTYPE> (MENU_ADMIN_MONITORING_FAILEDMAILS,
                                                                          aFailedMailQueue))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aGoMappingMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringGo <WPECTYPE> (MENU_ADMIN_MONITORING_GO, aGoMappingMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aLockManager != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringLockedObjects <WPECTYPE> (MENU_ADMIN_MONITORING_LOCKEDOBJECTS,
                                                                            aLockManager))
               .setDisplayFilter (aDisplayFilter);
    }
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringLoginInfo <WPECTYPE> (MENU_ADMIN_MONITORING_LOGININFO))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringScheduler <WPECTYPE> (MENU_ADMIN_MONITORING_SCHEDULER))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringScopes <WPECTYPE> (MENU_ADMIN_MONITORING_SCOPES))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringServletContext <WPECTYPE> (MENU_ADMIN_MONITORING_SERVLETCONTEXT))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringServletStatus <WPECTYPE> (MENU_ADMIN_MONITORING_SERVLETSTATUS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring, new BasePageMonitoringSessions <WPECTYPE> (MENU_ADMIN_MONITORING_SESSIONS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminMonitoring,
                          new BasePageMonitoringStatistics <WPECTYPE> (MENU_ADMIN_MONITORING_STATISTICS))
             .setDisplayFilter (aDisplayFilter);
    if (aSystemMigrationMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringSystemMigrations <WPECTYPE> (MENU_ADMIN_MONITORING_SYSTEMMIGRATIONS,
                                                                               aSystemMigrationMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    if (aResBundleMgr != null)
    {
      aMenuTree.createItem (aAdminMonitoring,
                            new BasePageMonitoringWebSiteResourceBundles <WPECTYPE> (MENU_ADMIN_MONITORING_WEBRESBUNDLE,
                                                                                     aResBundleMgr))
               .setDisplayFilter (aDisplayFilter);
    }
    return aAdminMonitoring;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSecurityItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IFilter <IMenuObject> aDisplayFilter,
                                                                                            @Nonnull final Locale aDefaultLocale)
  {
    final IMenuItemPage aAdminSecurity = aMenuTree.createItem (aParent,
                                                               new PageShowChildren <WPECTYPE> (MENU_ADMIN_SECURITY,
                                                                                                EWebPageText.PAGE_NAME_SECURITY.getAsMLT (),
                                                                                                aMenuTree))
                                                  .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity,
                          new BasePageSecurityUserManagement <WPECTYPE> (MENU_ADMIN_SECURITY_USER).setDefaultUserLocale (aDefaultLocale))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity,
                          new BasePageSecurityUserGroupManagement <WPECTYPE> (MENU_ADMIN_SECURITY_USER_GROUP))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSecurity, new BasePageSecurityRoleManagement <WPECTYPE> (MENU_ADMIN_SECURITY_ROLE))
             .setDisplayFilter (aDisplayFilter);
    return aAdminSecurity;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSettingsItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IFilter <IMenuObject> aDisplayFilter)
  {
    return DefaultMenuConfigurator.<WPECTYPE> addSettingsItems (aMenuTree,
                                                                aParent,
                                                                aDisplayFilter,
                                                                MetaSystemManager.getSMTPSettingsMgr ());
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSettingsItems (@Nonnull final IMenuTree aMenuTree,
                                                                                            @Nonnull final IMenuItem aParent,
                                                                                            @Nullable final IFilter <IMenuObject> aDisplayFilter,
                                                                                            @Nullable final NamedSMTPSettingsManager aNamedSMTPSettingsMgr)
  {
    final IMenuItemPage aAdminSettings = aMenuTree.createItem (aParent,
                                                               new PageShowChildren <WPECTYPE> (MENU_ADMIN_SETTINGS,
                                                                                                EWebPageText.PAGE_NAME_SETTINGS.getAsMLT (),
                                                                                                aMenuTree))
                                                  .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSettings, new BasePageSettingsGlobal <WPECTYPE> (MENU_ADMIN_SETTINGS_GLOBAL))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSettings, new BasePageSettingsHTML <WPECTYPE> (MENU_ADMIN_SETTINGS_HTML))
             .setDisplayFilter (aDisplayFilter);

    if (aNamedSMTPSettingsMgr != null)
    {
      aMenuTree.createItem (aAdminSettings,
                            new BasePageSettingsSMTP <WPECTYPE> (aNamedSMTPSettingsMgr, MENU_ADMIN_SETTINGS_SMTP))
               .setDisplayFilter (aDisplayFilter);
    }

    aMenuTree.createItem (aAdminSettings,
                          new BasePageSettingsSystemMessage <WPECTYPE> (MENU_ADMIN_SETTINGS_SYSTEMMESSAGE))
             .setDisplayFilter (aDisplayFilter);

    return aAdminSettings;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addSysInfoItems (@Nonnull final IMenuTree aMenuTree,
                                                                                           @Nonnull final IMenuItem aParent,
                                                                                           @Nullable final IFilter <IMenuObject> aDisplayFilter)
  {
    final IMenuItemPage aAdminSysInfo = aMenuTree.createItem (aParent,
                                                              new PageShowChildren <WPECTYPE> (MENU_ADMIN_SYSINFO,
                                                                                               EWebPageText.PAGE_NAME_SYSINFO.getAsMLT (),
                                                                                               aMenuTree))
                                                 .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoChangeLogs <WPECTYPE> (MENU_ADMIN_SYSINFO_CHANGELOGS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo,
                          new BasePageSysInfoEnvironmentVariables <WPECTYPE> (MENU_ADMIN_SYSINFO_ENVVARS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoNetwork <WPECTYPE> (MENU_ADMIN_SYSINFO_NETWORK))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoRequest <WPECTYPE> (MENU_ADMIN_SYSINFO_REQUEST))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoSecurity <WPECTYPE> (MENU_ADMIN_SYSINFO_SECURITY))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoSystemProperties <WPECTYPE> (MENU_ADMIN_SYSINFO_SYSPROPS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo,
                          new BasePageSysInfoThirdPartyLibraries <WPECTYPE> (MENU_ADMIN_SYSINFO_THIRDPARTYLIBS))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminSysInfo, new BasePageSysInfoThreads <WPECTYPE> (MENU_ADMIN_SYSINFO_THREADS))
             .setDisplayFilter (aDisplayFilter);
    return aAdminSysInfo;
  }

  @Nonnull
  public static <WPECTYPE extends IWebPageExecutionContext> IMenuItemPage addDataItems (@Nonnull final IMenuTree aMenuTree,
                                                                                        @Nonnull final IMenuItem aParent,
                                                                                        @Nullable final IFilter <IMenuObject> aDisplayFilter)
  {
    final IMenuItemPage aAdminData = aMenuTree.createItem (aParent,
                                                           new PageShowChildren <WPECTYPE> (MENU_ADMIN_DATA,
                                                                                            EWebPageText.PAGE_NAME_DATA.getAsMLT (),
                                                                                            aMenuTree))
                                              .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataCountries <WPECTYPE> (MENU_ADMIN_DATA_COUNTRIES))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataCurrencies <WPECTYPE> (MENU_ADMIN_DATA_CURRENCIES))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataLanguages <WPECTYPE> (MENU_ADMIN_DATA_LANGUAGES))
             .setDisplayFilter (aDisplayFilter);
    aMenuTree.createItem (aAdminData, new BasePageDataTimeZones <WPECTYPE> (MENU_ADMIN_DATA_TIMEZONES))
             .setDisplayFilter (aDisplayFilter);
    return aAdminData;
  }
}
