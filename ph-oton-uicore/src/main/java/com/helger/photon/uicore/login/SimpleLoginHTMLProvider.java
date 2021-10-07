/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.login;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.deprecated.HCCenter;
import com.helger.html.hc.html.forms.HCButton_Submit;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.html.forms.HCForm;
import com.helger.html.hc.html.forms.HCHiddenField;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.login.CLogin;
import com.helger.security.authentication.credentials.ICredentialValidationResult;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Provide a login screen
 *
 * @author Philip Helger
 */
public class SimpleLoginHTMLProvider extends AbstractLoginHTMLProvider
{
  public SimpleLoginHTMLProvider (final boolean bLoginError, @Nonnull final ICredentialValidationResult aLoginResult)
  {
    super (bLoginError, aLoginResult);
  }

  /**
   * @return <code>true</code> if the header text should be shown,
   *         <code>false</code> to not show the header text
   */
  @OverrideOnDemand
  protected boolean showHeaderText ()
  {
    return false;
  }

  /**
   * Define how label elements should look like
   *
   * @param sText
   *        Text to use
   * @return The label node
   */
  @OverrideOnDemand
  @Nullable
  protected IHCNode createLabelNode (@Nullable final String sText)
  {
    return new HCTextNode (sText);
  }

  /**
   * Add additional rows. Called after username and password row are added but
   * before the submit button is added.
   *
   * @param aTable
   *        The table to be modified.
   * @param aDisplayLocale
   *        Display locale to use
   */
  @OverrideOnDemand
  protected void customizeLoginFields (@Nonnull final HCTable aTable, @Nonnull final Locale aDisplayLocale)
  {}

  @Override
  protected void fillBody (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCHtml aHtml)
  {
    final IRequestWebScopeWithoutResponse aRequestScope = aSWEC.getRequestScope ();
    final Locale aDisplayLocale = aSWEC.getDisplayLocale ();

    final HCBody aBody = aHtml.body ();
    final HCSpan aSpan = aBody.addAndReturnChild (new HCSpan ().setID (CLogin.LAYOUT_AREAID_LOGIN));
    final HCCenter aCenter = aSpan.addAndReturnChild (new HCCenter ());
    final HCForm aForm = aCenter.addAndReturnChild (new HCForm (new SimpleURL (aRequestScope.getURLDecoded ())));
    aForm.setSubmitPressingEnter (true);

    // The hidden field that triggers the validation
    aForm.addChild (new HCHiddenField (CLogin.REQUEST_PARAM_ACTION, CLogin.REQUEST_ACTION_VALIDATE_LOGIN_CREDENTIALS));
    aForm.addChild (getCSRFHandler ().createCSRFNonceField ());

    aForm.addChild (new HCDiv ().addClass (CLogin.CSS_CLASS_LOGIN_APPLOGO));
    if (showHeaderText ())
      aForm.addChild (new HCDiv ().addChild (getTextHeader (aDisplayLocale)).addClass (CLogin.CSS_CLASS_LOGIN_HEADER));
    if (isLoginError ())
      aForm.addChild (new HCDiv ().addChild (getTextErrorMessage (aDisplayLocale, getLoginResult ()))
                                  .addClass (CLogin.CSS_CLASS_LOGIN_ERRORMSG));

    // User name and password table
    final HCTable aTable = aForm.addAndReturnChild (new HCTable (new HCCol (200), HCCol.star ()));
    HCRow aRow = aTable.addBodyRow ();
    aRow.addCell (createLabelNode (getTextFieldUserName (aDisplayLocale)));
    aRow.addCell (new HCEdit (CLogin.REQUEST_ATTR_USERID));

    aRow = aTable.addBodyRow ();
    aRow.addCell (createLabelNode (getTextFieldPassword (aDisplayLocale)));
    aRow.addCell (new HCEditPassword (CLogin.REQUEST_ATTR_PASSWORD));

    // Customize
    customizeLoginFields (aTable, aDisplayLocale);

    // Submit button
    final IHCCell <?> aCell = aTable.addBodyRow ().addCell ().setColspan (aTable.getColumnCount ());
    aCell.addChild (new HCButton_Submit (getLoginButtonText (aDisplayLocale)));
  }
}
