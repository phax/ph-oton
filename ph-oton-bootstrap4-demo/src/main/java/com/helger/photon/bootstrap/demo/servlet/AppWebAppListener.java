/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.servlet;

import javax.annotation.Nonnull;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.commons.io.resource.ClassPathResource;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.ajax.IAjaxRegistry;
import com.helger.photon.bootstrap.demo.ajax.CAjax;
import com.helger.photon.bootstrap.demo.app.AppSettings;
import com.helger.photon.bootstrap.demo.app.CApp;
import com.helger.photon.bootstrap.demo.pub.menu.MenuPublic;
import com.helger.photon.bootstrap.demo.secure.menu.MenuSecure;
import com.helger.photon.bootstrap4.servlet.WebAppListenerBootstrap;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.appid.CApplicationID;
import com.helger.photon.core.appid.PhotonGlobalState;
import com.helger.photon.core.configfile.ConfigurationFile;
import com.helger.photon.core.configfile.ConfigurationFileManager;
import com.helger.photon.core.configfile.EConfigurationFileSyntax;
import com.helger.photon.core.locale.ILocaleManager;
import com.helger.photon.core.menu.MenuTree;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.usergroup.IUserGroupManager;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenu;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.photon.uictrls.datatables.ajax.AjaxExecutorDataTables;
import com.helger.photon.uictrls.datatables.ajax.AjaxExecutorDataTablesI18N;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginSearchHighlight;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

import jakarta.servlet.ServletContext;

/**
 * This listener is invoked during the servlet initialization. This is basically a
 * ServletContextListener.
 *
 * @author Philip Helger
 */
public final class AppWebAppListener extends WebAppListenerBootstrap
{
  @Override
  protected String getInitParameterDebug (@Nonnull final ServletContext aSC)
  {
    return AppSettings.getGlobalDebug ();
  }

  @Override
  protected String getInitParameterProduction (@Nonnull final ServletContext aSC)
  {
    return AppSettings.getGlobalProduction ();
  }

  @Override
  protected String getDataPath (@Nonnull final ServletContext aSC)
  {
    return AppSettings.getDataPath ();
  }

  @Override
  protected boolean shouldCheckFileAccess (@Nonnull final ServletContext aSC)
  {
    return AppSettings.isCheckFileAccess ();
  }

  @Override
  protected void initLocales (@Nonnull final ILocaleManager aLocaleMgr)
  {
    aLocaleMgr.registerLocale (CApp.DEFAULT_LOCALE);
    aLocaleMgr.registerLocale (CApp.LOCALE_DE_AT);
    aLocaleMgr.setDefaultLocale (CApp.DEFAULT_LOCALE);
  }

  @Override
  protected void initMenu ()
  {
    // Create all menu items
    {
      final MenuTree aMenuTree = new MenuTree ();
      MenuPublic.init (aMenuTree);
      PhotonGlobalState.state (CApplicationID.APP_ID_PUBLIC).setMenuTree (aMenuTree);
    }
    {
      final MenuTree aMenuTree = new MenuTree ();
      MenuSecure.init (aMenuTree);
      PhotonGlobalState.state (CApplicationID.APP_ID_SECURE).setMenuTree (aMenuTree);
    }
  }

  @Override
  protected void initSecurity ()
  {
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final IUserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();
    final IRoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();

    // Standard users
    if (!aUserMgr.containsWithID (CApp.USER_ADMINISTRATOR_ID))
    {
      final boolean bDisabled = false;
      aUserMgr.createPredefinedUser (CApp.USER_ADMINISTRATOR_ID,
                                     CApp.USER_ADMINISTRATOR_LOGINNAME,
                                     CApp.USER_ADMINISTRATOR_EMAIL,
                                     CApp.USER_ADMINISTRATOR_PASSWORD,
                                     CApp.USER_ADMINISTRATOR_FIRSTNAME,
                                     CApp.USER_ADMINISTRATOR_LASTNAME,
                                     CApp.USER_ADMINISTRATOR_DESCRIPTION,
                                     CApp.USER_ADMINISTRATOR_LOCALE,
                                     CApp.USER_ADMINISTRATOR_CUSTOMATTRS,
                                     bDisabled);
    }

    // Create all roles
    if (!aRoleMgr.containsWithID (CApp.ROLE_CONFIG_ID))
      aRoleMgr.createPredefinedRole (CApp.ROLE_CONFIG_ID,
                                     CApp.ROLE_CONFIG_NAME,
                                     CApp.ROLE_CONFIG_DESCRIPTION,
                                     CApp.ROLE_CONFIG_CUSTOMATTRS);
    if (!aRoleMgr.containsWithID (CApp.ROLE_VIEW_ID))
      aRoleMgr.createPredefinedRole (CApp.ROLE_VIEW_ID,
                                     CApp.ROLE_VIEW_NAME,
                                     CApp.ROLE_VIEW_DESCRIPTION,
                                     CApp.ROLE_VIEW_CUSTOMATTRS);

    // User group Administrators
    if (!aUserGroupMgr.containsWithID (CApp.USERGROUP_ADMINISTRATORS_ID))
    {
      aUserGroupMgr.createPredefinedUserGroup (CApp.USERGROUP_ADMINISTRATORS_ID,
                                               CApp.USERGROUP_ADMINISTRATORS_NAME,
                                               CApp.USERGROUP_ADMINISTRATORS_DESCRIPTION,
                                               CApp.USERGROUP_ADMINISTRATORS_CUSTOMATTRS);
      // Assign administrator user to administrators user group
      aUserGroupMgr.assignUserToUserGroup (CApp.USERGROUP_ADMINISTRATORS_ID, CApp.USER_ADMINISTRATOR_ID);
    }
    aUserGroupMgr.assignRoleToUserGroup (CApp.USERGROUP_ADMINISTRATORS_ID, CApp.ROLE_CONFIG_ID);
    aUserGroupMgr.assignRoleToUserGroup (CApp.USERGROUP_ADMINISTRATORS_ID, CApp.ROLE_VIEW_ID);

    // User group for Config users
    if (!aUserGroupMgr.containsWithID (CApp.USERGROUP_CONFIG_ID))
      aUserGroupMgr.createPredefinedUserGroup (CApp.USERGROUP_CONFIG_ID,
                                               CApp.USERGROUP_CONFIG_NAME,
                                               CApp.USERGROUP_CONFIG_DESCRIPTION,
                                               CApp.USERGROUP_CONFIG_CUSTOMATTRS);
    aUserGroupMgr.assignRoleToUserGroup (CApp.USERGROUP_CONFIG_ID, CApp.ROLE_CONFIG_ID);

    // User group for View users
    if (!aUserGroupMgr.containsWithID (CApp.USERGROUP_VIEW_ID))
      aUserGroupMgr.createPredefinedUserGroup (CApp.USERGROUP_VIEW_ID,
                                               CApp.USERGROUP_VIEW_NAME,
                                               CApp.USERGROUP_VIEW_DESCRIPTION,
                                               CApp.USERGROUP_VIEW_CUSTOMATTRS);
    aUserGroupMgr.assignRoleToUserGroup (CApp.USERGROUP_VIEW_ID, CApp.ROLE_VIEW_ID);
  }

  @Override
  protected void initUI ()
  {
    BootstrapDataTables.setConfigurator ( (aLEC, aTable, aDataTables) -> {
      final IRequestWebScopeWithoutResponse aRequestScope = aLEC.getRequestScope ();
      aDataTables.setAutoWidth (false)
                 .setLengthMenu (DataTablesLengthMenu.INSTANCE_25_50_100_ALL)
                 .setAjaxBuilder (new JQueryAjaxBuilder ().url (CAjax.DATATABLES.getInvocationURL (aRequestScope))
                                                          .data (new JSAssocArray ().add (AjaxExecutorDataTables.OBJECT_ID,
                                                                                          aTable.getID ())))
                 .setServerFilterType (EDataTablesFilterType.ALL_TERMS_PER_ROW)
                 .setTextLoadingURL (CAjax.DATATABLES_I18N.getInvocationURL (aRequestScope),
                                     AjaxExecutorDataTablesI18N.REQUEST_PARAM_LANGUAGE_ID)
                 .addPlugin (new DataTablesPluginSearchHighlight ());
    });
  }

  @Override
  protected void initGlobalSettings ()
  {
    // JUL to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger ();
    SLF4JBridgeHandler.install ();

    // This is required to match the CSP declarations
    HCSettings.setUseNonceInScript (true);
    HCSettings.setUseNonceInStyle (true);
  }

  @Override
  protected void initAjax (@Nonnull final IAjaxRegistry aAjaxRegistry)
  {
    aAjaxRegistry.registerFunction (CAjax.DATATABLES);
    aAjaxRegistry.registerFunction (CAjax.DATATABLES_I18N);
    aAjaxRegistry.registerFunction (CAjax.LOGIN);
    aAjaxRegistry.registerFunction (CAjax.UPDATE_MENU_VIEW_PUB);
    aAjaxRegistry.registerFunction (CAjax.UPDATE_MENU_VIEW_SEC);
  }

  @Override
  protected void initManagers ()
  {
    final ConfigurationFileManager aCfgMgr = ConfigurationFileManager.getInstance ();
    aCfgMgr.registerConfigurationFile (new ConfigurationFile (new ClassPathResource ("log4j2.xml")).setDescription ("log4j configuration file")
                                                                                                   .setSyntaxHighlightLanguage (EConfigurationFileSyntax.XML));
    aCfgMgr.registerConfigurationFile (new ConfigurationFile (new ClassPathResource ("application.properties")).setDescription ("Web application properties")
                                                                                                               .setSyntaxHighlightLanguage (EConfigurationFileSyntax.PROPERTIES));
  }
}
