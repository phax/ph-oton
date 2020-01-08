/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.url.SimpleURL;
import com.helger.css.property.CCSSProperties;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.photon.bootstrap4.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap4.button.BootstrapSubmitButton;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.layout.BootstrapContainer;
import com.helger.photon.bootstrap4.utils.BootstrapPageHeader;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.login.CLogin;
import com.helger.photon.uicore.login.AbstractLoginHTMLProvider;
import com.helger.photon.uicore.login.SimpleLoginHTMLProvider;
import com.helger.security.authentication.credentials.ICredentialValidationResult;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A special {@link SimpleLoginHTMLProvider} with Bootstrap UI.
 *
 * @author Philip Helger
 */
public class BootstrapLoginHTMLProvider extends AbstractLoginHTMLProvider
{
  private final IHCNode m_aPageTitle;

  public BootstrapLoginHTMLProvider (final boolean bLoginError,
                                     @Nonnull final ICredentialValidationResult aLoginResult,
                                     @Nullable final IHCNode aPageTitle)
  {
    super (bLoginError, aLoginResult);
    m_aPageTitle = aPageTitle;
  }

  @Override
  @Nullable
  @OverrideOnDemand
  protected String getTextFieldUserName (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.EMAIL_ADDRESS.getDisplayText (aDisplayLocale);
  }

  /**
   * Customize the empty form
   *
   * @param aSWEC
   *        Web execution context.
   * @param aForm
   *        The empty form.
   */
  @OverrideOnDemand
  protected void onBeforeForm (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final BootstrapForm aForm)
  {}

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
   * Create the text above the login form.
   *
   * @param aSWEC
   *        Web page execution context.
   * @param aPageTitle
   *        Page title as provided in the constructor. May be <code>null</code>.
   * @return The created node or <code>null</code>.
   */
  @Nullable
  @OverrideOnDemand
  protected IHCNode createPageHeader (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                      @Nullable final IHCNode aPageTitle)
  {
    return BootstrapPageHeader.createOnDemand (aPageTitle);
  }

  /**
   * After form
   *
   * @param aSWEC
   *        Web page execution context
   * @return May be <code>null</code>
   */
  @Nullable
  protected IHCNode createFormFooter (@Nonnull final ISimpleWebExecutionContext aSWEC)
  {
    return null;
  }

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

    // Use the server-relative version without the hostname
    final BootstrapForm aForm = new BootstrapForm (aSWEC).setAction (new SimpleURL (aRequestScope.getURI ()));

    // Customize
    onBeforeForm (aSWEC, aForm);

    // The hidden field that triggers the validation
    aForm.addChild (new HCHiddenField (CLogin.REQUEST_PARAM_ACTION, CLogin.REQUEST_ACTION_VALIDATE_LOGIN_CREDENTIALS));
    aForm.addChild (getCSRFHandler ().createCSRFNonceField ());

    if (isLoginError ())
      aForm.addChild (new BootstrapErrorBox ().addChild (getTextErrorMessage (aDisplayLocale, getLoginResult ())));

    // User name and password table
    final String sUserName = getTextFieldUserName (aDisplayLocale);
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sUserName)
                                                 .setCtrl (new HCEdit (CLogin.REQUEST_ATTR_USERID).setPlaceholder (sUserName)
                                                                                                  .setAutoFocus (true)));

    final String sPassword = getTextFieldPassword (aDisplayLocale);
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sPassword)
                                                 .setCtrl (new HCEditPassword (CLogin.REQUEST_ATTR_PASSWORD).setPlaceholder (sPassword)));

    // Submit button
    aForm.addChild (new BootstrapSubmitButton ().addChild (getLoginButtonText (aDisplayLocale)));

    // Customize
    onAfterForm (aSWEC, aForm);

    final HCSpan aSpan = new HCSpan ().setID (CLogin.LAYOUT_AREAID_LOGIN);
    aSpan.addStyle (CCSSProperties.MIN_HEIGHT.newValue ("100%"));
    aSpan.addStyle (CCSSProperties.MIN_HEIGHT.newValue ("100vh"));
    aSpan.addStyle (CCSSProperties.DISPLAY.newValue ("flex"));
    aSpan.addStyle (CCSSProperties.ALIGN_ITEMS.newValue ("center"));
    onBeforeLoginContainer (aSWEC, aSpan);

    final BootstrapContainer aDiv = new BootstrapContainer ();
    aDiv.addChild (createPageHeader (aSWEC, m_aPageTitle));
    aDiv.addChild (aForm);
    aDiv.addChild (createFormFooter (aSWEC));
    aSpan.addChild (aDiv);

    // Customize
    onAfterLoginContainer (aSWEC, aSpan);

    // Build body
    final HCBody aBody = aHtml.body ();
    aBody.addChild (aSpan);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillHead (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    super.fillHead (aSWEC, aHtml);
    if (m_aPageTitle != null)
      aHtml.head ().setPageTitle (m_aPageTitle.getPlainText ());
  }
}
