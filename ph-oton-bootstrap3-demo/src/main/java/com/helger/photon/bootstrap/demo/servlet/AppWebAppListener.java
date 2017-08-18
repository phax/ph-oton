/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import javax.servlet.ServletContext;

import org.slf4j.bridge.SLF4JBridgeHandler;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jscode.JSAssocArray;
import com.helger.photon.basic.app.CApplicationID;
import com.helger.photon.bootstrap.demo.app.AppSettings;
import com.helger.photon.bootstrap.demo.app.CApp;
import com.helger.photon.bootstrap.demo.pub.InitializerPublic;
import com.helger.photon.bootstrap.demo.pub.ajax.CAjaxPublic;
import com.helger.photon.bootstrap.demo.secure.InitializerSecure;
import com.helger.photon.bootstrap3.servlet.AbstractWebAppListenerMultiAppBootstrap;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.core.app.init.IApplicationInitializer;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.usergroup.UserGroupManager;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenu;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.photon.uictrls.datatables.ajax.AjaxExecutorDataTables;
import com.helger.photon.uictrls.datatables.ajax.AjaxExecutorDataTablesI18N;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginSearchHighlight;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * This listener is invoked during the servlet initiailization. This is
 * basically a ServletContextListener.
 *
 * @author Philip Helger
 */
public final class AppWebAppListener extends AbstractWebAppListenerMultiAppBootstrap <LayoutExecutionContext>
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
  @Nonnull
  @Nonempty
  protected ICommonsMap <String, IApplicationInitializer <LayoutExecutionContext>> getAllInitializers ()
  {
    final ICommonsMap <String, IApplicationInitializer <LayoutExecutionContext>> ret = new CommonsHashMap <> ();
    ret.put (CApplicationID.APP_ID_SECURE, new InitializerSecure ());
    ret.put (CApplicationID.APP_ID_PUBLIC, new InitializerPublic ());
    return ret;
  }

  private static void _initSecurity ()
  {
    final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();
    final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();

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

  private static void _initUI ()
  {
    final DataTablesLengthMenu LENGTH_MENU = new DataTablesLengthMenu ().addItem (25)
                                                                        .addItem (50)
                                                                        .addItem (100)
                                                                        .addItemAll ();

    BootstrapDataTables.setConfigurator ( (aLEC, aTable, aDataTables) -> {
      final IRequestWebScopeWithoutResponse aRequestScope = aLEC.getRequestScope ();
      aDataTables.setAutoWidth (false)
                 .setLengthMenu (LENGTH_MENU)
                 .setAjaxBuilder (new JQueryAjaxBuilder ().url (CAjaxPublic.DATATABLES.getInvocationURL (aRequestScope))
                                                          .data (new JSAssocArray ().add (AjaxExecutorDataTables.OBJECT_ID,
                                                                                          aTable.getID ())))
                 .setServerFilterType (EDataTablesFilterType.ALL_TERMS_PER_ROW)
                 .setTextLoadingURL (CAjaxPublic.DATATABLES_I18N.getInvocationURL (aRequestScope),
                                     AjaxExecutorDataTablesI18N.LANGUAGE_ID)
                 .addPlugin (new DataTablesPluginSearchHighlight ());
    });
  }

  @Override
  protected void initGlobals ()
  {
    // Internal stuff:

    // JUL to SLF4J
    SLF4JBridgeHandler.removeHandlersForRootLogger ();
    SLF4JBridgeHandler.install ();

    super.initGlobals ();

    // UI stuff
    _initUI ();

    // Set all security related stuff
    _initSecurity ();
  }
}
