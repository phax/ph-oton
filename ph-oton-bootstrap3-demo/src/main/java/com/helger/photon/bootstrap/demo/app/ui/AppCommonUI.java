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
package com.helger.photon.bootstrap.demo.app.ui;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCEdit;
import com.helger.html.hc.html.HCEditPassword;
import com.helger.html.jquery.JQuery;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSPackage;
import com.helger.photon.bootstrap.demo.app.action.pub.CActionPublic;
import com.helger.photon.bootstrap.demo.app.ajax.pub.CAjaxPublic;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.bootstrap3.uictrls.datatables.IBootstrapDataTablesConfigurator;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.LayoutExecutionContext;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.login.CLogin;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTablesLengthMenuList;
import com.helger.photon.uictrls.datatables.EDataTablesFilterType;
import com.helger.photon.uictrls.datatables.ajax.ActionExecutorDataTablesI18N;
import com.helger.photon.uictrls.datatables.ajax.AjaxExecutorDataTables;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

@Immutable
public final class AppCommonUI
{
  private static final DataTablesLengthMenuList LENGTH_MENU = new DataTablesLengthMenuList ().addItem (25)
                                                                                             .addItem (50)
                                                                                             .addItem (100)
                                                                                             .addItemAll ();

  private AppCommonUI ()
  {}

  public static void init ()
  {
    BootstrapDataTables.setConfigurator (new IBootstrapDataTablesConfigurator ()
    {
      public void configure (@Nonnull final IWebPageExecutionContext aWPEC,
                             @Nonnull final IHCTable <?> aTable,
                             @Nonnull final BootstrapDataTables aDataTables)
      {
        final IRequestWebScopeWithoutResponse aRequestScope = aWPEC.getRequestScope ();
        aDataTables.setAutoWidth (false)
                   .setLengthMenu (LENGTH_MENU)
                   .setUseJQueryAjax (true)
                   .setAjaxSource (CAjaxPublic.DATATABLES.getInvocationURL (aRequestScope))
                   .setServerParams (CollectionHelper.newMap (AjaxExecutorDataTables.OBJECT_ID, aTable.getID ()))
                   .setServerFilterType (EDataTablesFilterType.ALL_TERMS_PER_ROW)
                   .setTextLoadingURL (CActionPublic.DATATABLES_I18N.getInvocationURL (aRequestScope),
                                       ActionExecutorDataTablesI18N.LANGUAGE_ID)
                   .setUseSearchHighlight (true);
      }
    });
  }

  @Nonnull
  public static BootstrapForm createViewLoginForm (@Nonnull final LayoutExecutionContext aLEC,
                                                   @Nullable final String sPreselectedUserName,
                                                   final boolean bFullUI)
  {
    final Locale aDisplayLocale = aLEC.getDisplayLocale ();
    final IRequestWebScopeWithoutResponse aRequestScope = aLEC.getRequestScope ();

    // Use new IDs for both fields, in case the login stuff is displayed more
    // than once!
    final String sIDUserName = GlobalIDFactory.getNewStringID ();
    final String sIDPassword = GlobalIDFactory.getNewStringID ();
    final String sIDErrorField = GlobalIDFactory.getNewStringID ();

    final BootstrapForm aForm = new BootstrapForm (aLEC.getSelfHref (), bFullUI ? EBootstrapFormType.HORIZONTAL
                                                                               : EBootstrapFormType.DEFAULT);
    aForm.setLeft (3);

    // User name field
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EPhotonCoreText.EMAIL_ADDRESS.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (CLogin.REQUEST_ATTR_USERID,
                                                                                         sPreselectedUserName)).setID (sIDUserName)));

    // Password field
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EPhotonCoreText.LOGIN_FIELD_PASSWORD.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEditPassword (CLogin.REQUEST_ATTR_PASSWORD).setID (sIDPassword)));

    // Placeholder for error message
    aForm.addChild (new HCDiv ().setID (sIDErrorField).addStyle (CCSSProperties.MARGIN.newValue ("4px 0")));

    // Login button
    final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aLEC));
    final JSPackage aOnClick = new JSPackage ();
    aOnClick.add (CAppJS.viewLogin ()
                        .arg (CAjaxPublic.LOGIN.getInvocationURI (aRequestScope))
                        .arg (new JSAssocArray ().add (CLogin.REQUEST_ATTR_USERID, JQuery.idRef (sIDUserName).val ())
                                                 .add (CLogin.REQUEST_ATTR_PASSWORD, JQuery.idRef (sIDPassword).val ()))
                        .arg (sIDErrorField));
    aOnClick._return (false);
    aToolbar.addSubmitButton (EPhotonCoreText.LOGIN_BUTTON_SUBMIT.getDisplayText (aDisplayLocale), aOnClick);
    return aForm;
  }
}
