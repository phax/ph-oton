/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.css.property.CCSSProperties;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.jquery.JQuery;
import com.helger.html.jquery.JQueryAjaxBuilder;
import com.helger.html.jscode.JSAnonymousFunction;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSPackage;
import com.helger.html.jscode.JSVar;
import com.helger.html.jscode.html.JSHtml;
import com.helger.photon.bootstrap.demo.ajax.AjaxExecutorLogin;
import com.helger.photon.bootstrap.demo.ajax.CAjax;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.core.login.CLogin;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

@Immutable
public final class AppCommonUI
{
  // Logo parts
  public static final ICSSClassProvider CSS_CLASS_LOGO1 = DefaultCSSClassProvider.create ("logo1");
  public static final ICSSClassProvider CSS_CLASS_LOGO2 = DefaultCSSClassProvider.create ("logo2");

  private AppCommonUI ()
  {}

  @Nonnull
  public static BootstrapForm createViewLoginForm (@Nonnull final ILayoutExecutionContext aLEC,
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

    final BootstrapForm aForm = new BootstrapForm (aLEC).setAction (aLEC.getSelfHref ())
                                                        .setFormType (bFullUI ? EBootstrapFormType.HORIZONTAL
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
    {
      final JSAnonymousFunction aJSSuccess = new JSAnonymousFunction ();
      final JSVar aJSData = aJSSuccess.param ("data");
      aJSSuccess.body ()
                ._if (aJSData.ref (AjaxExecutorLogin.JSON_LOGGEDIN),
                      JSHtml.windowLocationReload (),
                      JQuery.idRef (sIDErrorField).empty ().append (aJSData.ref (AjaxExecutorLogin.JSON_HTML)));
      aOnClick.add (new JQueryAjaxBuilder ().url (CAjax.LOGIN.getInvocationURI (aRequestScope))
                                            .data (new JSAssocArray ().add (CLogin.REQUEST_ATTR_USERID,
                                                                            JQuery.idRef (sIDUserName).val ())
                                                                      .add (CLogin.REQUEST_ATTR_PASSWORD,
                                                                            JQuery.idRef (sIDPassword).val ()))
                                            .success (aJSSuccess)
                                            .build ());
      aOnClick._return (false);
    }
    aToolbar.addSubmitButton (EPhotonCoreText.LOGIN_BUTTON_SUBMIT.getDisplayText (aDisplayLocale), aOnClick);
    return aForm;
  }
}
