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
package com.helger.photon.bootstrap3.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.sections.HCH2;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.base.BootstrapContainer;
import com.helger.photon.bootstrap3.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.grid.BootstrapRow;
import com.helger.photon.bootstrap3.pageheader.BootstrapPageHeader;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.login.CLogin;
import com.helger.photon.core.login.LoginHTMLProvider;
import com.helger.photon.security.login.ELoginResult;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A special {@link LoginHTMLProvider} with Bootstrap UI.
 *
 * @author Philip Helger
 */
public class BootstrapLoginHTMLProvider extends LoginHTMLProvider
{
  private final String m_sPageTitle;

  public BootstrapLoginHTMLProvider (final boolean bLoginError, @Nonnull final ELoginResult eLoginResult, @Nullable final String sPageTitle)
  {
    super (bLoginError, eLoginResult);
    m_sPageTitle = sPageTitle;
  }

  /**
   * Customize the created form
   *
   * @param aSWEC
   *        Web execution context.
   * @param aForm
   *        The pre-filled form.
   */
  @OverrideOnDemand
  protected void onAfterForm (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final BootstrapForm aForm)
  {}

  /**
   * Customize the created container, where the form resides in
   *
   * @param aSWEC
   *        Web execution context.
   * @param aContainer
   *        The pre-filled container.
   * @param aRow
   *        The pre-filled row
   * @param aContentCol
   *        The content column, where the form resides in,
   */
  @OverrideOnDemand
  protected void onAfterContainer (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                   @Nonnull final BootstrapContainer aContainer,
                                   @Nonnull final BootstrapRow aRow,
                                   @Nonnull final HCDiv aContentCol)
  {}

  /**
   * Customize the created span, where the container resides in
   *
   * @param aSWEC
   *        Web execution context.
   * @param aSpan
   *        The span where the container resides in
   */
  @OverrideOnDemand
  protected void onBeforeLoginContainer (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCSpan aSpan)
  {}

  /**
   * Customize the created span, where the container resides in
   *
   * @param aSWEC
   *        Web execution context.
   * @param aSpan
   *        The span where the container resides in
   */
  @OverrideOnDemand
  protected void onAfterLoginContainer (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCSpan aSpan)
  {}

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aSWEC.getRequestScope ();
    final Locale aDisplayLocale = aSWEC.getDisplayLocale ();

    final BootstrapForm aForm = new BootstrapForm (aRequestScope.getURL ());

    // The hidden field that triggers the validation
    aForm.addChild (new HCHiddenField (CLogin.REQUEST_PARAM_ACTION, CLogin.REQUEST_ACTION_VALIDATE_LOGIN_CREDENTIALS));

    if (showLoginError ())
      aForm.addChild (new BootstrapErrorBox ().addChild (getTextErrorMessage (aDisplayLocale, getLoginResult ())));

    // User name and password table
    final String sUserName = EPhotonCoreText.EMAIL_ADDRESS.getDisplayText (aDisplayLocale);
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sUserName)
                                                 .setCtrl (new HCEdit (CLogin.REQUEST_ATTR_USERID).setPlaceholder (sUserName).setAutoFocus (true)));

    final String sPassword = EPhotonCoreText.LOGIN_FIELD_PASSWORD.getDisplayText (aDisplayLocale);
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPassword)
                                                 .setCtrl (new HCEditPassword (CLogin.REQUEST_ATTR_PASSWORD).setPlaceholder (sPassword)));

    // Submit button
    aForm.addChild (new BootstrapSubmitButton ().addChild (EPhotonCoreText.LOGIN_BUTTON_SUBMIT.getDisplayText (aDisplayLocale)));

    // Customize
    onAfterForm (aSWEC, aForm);

    // Layout the form
    final BootstrapContainer aContentLayout = new BootstrapContainer ();
    final BootstrapRow aRow = aContentLayout.addAndReturnChild (new BootstrapRow ());
    aRow.createColumn (0, 2, 3, 3);
    final HCDiv aCol2 = aRow.createColumn (12, 8, 6, 6);
    if (StringHelper.hasText (m_sPageTitle))
      aCol2.addChild (new BootstrapPageHeader ().addChild (new HCH2 ().addChild (m_sPageTitle)));
    aCol2.addChild (aForm);
    aRow.createColumn (0, 2, 3, 3);

    onAfterContainer (aSWEC, aContentLayout, aRow, aCol2);

    final HCSpan aSpan = new HCSpan ().setID (CLogin.LAYOUT_AREAID_LOGIN);
    onBeforeLoginContainer (aSWEC, aSpan);
    aSpan.addChild (aContentLayout);
    onAfterLoginContainer (aSWEC, aSpan);

    // Build body
    final HCBody aBody = aHtml.getBody ();
    aBody.addChild (aSpan);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillHead (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    super.fillHead (aSWEC, aHtml);
    aHtml.getHead ().setPageTitle (m_sPageTitle);
  }
}
