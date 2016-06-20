/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.pages.security;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.errorlist.FormErrors;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap3.uictrls.ext.BootstrapSecurityUI;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.formlabel.ELabelType;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

/**
 * Base page for changing the password of the currently logged in use.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSecurityChangePassword <WPECTYPE extends IWebPageExecutionContext>
                                            extends AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
    ERROR_NO_USER_PRESENT ("Es ist kein Benutzer angemeldet, daher kann auch das Passwort nicht geändert werden.",
                           "Since no user is logged in no password change is possible."),
    TITLE ("Passwort von ''{0}'' ändern", "Change password of ''{0}''"),
    LABEL_OLD_PASSWORD ("Altes Passwort", "Old password"),
    LABEL_PASSWORD ("Neues Passwort", "New password"),
    LABEL_PASSWORD_CONFIRM ("Neues Passwort (Bestätigung)", "New password (confirmation)"),
    ERROR_OLD_PASSWORD_INVALID ("Das alte Passwort ist ungültig!", "The old password is invalid!"),
    ERROR_PASSWORDS_DONT_MATCH ("Die neuen Passwörter stimmen nicht überein!", "The new passwords don't match"),
    SUCCESS_CHANGE_PW ("Das Passwort wurde erfolgreich geändert!", "Sucessfully changed the password!");

    private final IMultilingualText m_aTP;

    private EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgsStatic (this, m_aTP, aContentLocale, aArgs);
    }
  }

  public static final String FIELD_OLD_PASSWORD = "oldpassword";
  public static final String FIELD_NEW_PASSWORD = "newpassword";
  public static final String FIELD_NEW_PASSWORD_CONFIRM = "newpasswordconf";

  public BasePageSecurityChangePassword (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_CHANGE_PASSWORD.getAsMLT ());
  }

  public BasePageSecurityChangePassword (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSecurityChangePassword (@Nonnull @Nonempty final String sID,
                                         @Nonnull final String sName,
                                         @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSecurityChangePassword (@Nonnull @Nonempty final String sID,
                                         @Nonnull final IMultilingualText aName,
                                         @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final IUser aCurrentUser = LoggedInUserManager.getInstance ().getCurrentUser ();
    if (aCurrentUser == null)
    {
      aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.ERROR_NO_USER_PRESENT.getDisplayText (aDisplayLocale)));
    }
    else
    {
      final boolean bShowForm = true;
      final FormErrors aFormErrors = new FormErrors ();

      if (aWPEC.hasAction (CPageParam.ACTION_PERFORM))
      {
        // Check if the CSRF nonce matches
        if (getCSRFHandler ().checkCSRFNonce (aWPEC).isContinue ())
        {
          final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
          final String sOldPlainTextPassword = aWPEC.getAttributeAsString (FIELD_OLD_PASSWORD);
          final String sNewPlainTextPassword = aWPEC.getAttributeAsString (FIELD_NEW_PASSWORD);
          final String sNewPlainTextPasswordConfirm = aWPEC.getAttributeAsString (FIELD_NEW_PASSWORD_CONFIRM);

          if (!aUserMgr.areUserIDAndPasswordValid (aCurrentUser.getID (), sOldPlainTextPassword))
            aFormErrors.addFieldError (FIELD_OLD_PASSWORD,
                                       EText.ERROR_OLD_PASSWORD_INVALID.getDisplayText (aDisplayLocale));

          final List <String> aPasswordErrors = GlobalPasswordSettings.getPasswordConstraintList ()
                                                                      .getInvalidPasswordDescriptions (sNewPlainTextPassword,
                                                                                                       aDisplayLocale);
          for (final String sPasswordError : aPasswordErrors)
            aFormErrors.addFieldError (FIELD_NEW_PASSWORD, sPasswordError);
          if (!EqualsHelper.equals (sNewPlainTextPassword, sNewPlainTextPasswordConfirm))
            aFormErrors.addFieldError (FIELD_NEW_PASSWORD_CONFIRM,
                                       EText.ERROR_PASSWORDS_DONT_MATCH.getDisplayText (aDisplayLocale));

          if (aFormErrors.isEmpty ())
          {
            aUserMgr.setUserPassword (aCurrentUser.getID (), sNewPlainTextPassword);
            aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_CHANGE_PW.getDisplayText (aDisplayLocale)));
            // Always show form
          }
        }
      }
      if (bShowForm)
      {
        // Show input form
        final boolean bHasAnyPasswordConstraint = GlobalPasswordSettings.getPasswordConstraintList ().hasConstraints ();
        final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));
        aForm.addChild (getUIHandler ().createActionHeader (EText.TITLE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                SecurityHelper.getUserDisplayName (aCurrentUser,
                                                                                                                                   aDisplayLocale))));

        final String sLabelOldPassword = EText.LABEL_OLD_PASSWORD.getDisplayText (aDisplayLocale);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sLabelOldPassword, ELabelType.MANDATORY)
                                                     .setCtrl (new HCEditPassword (FIELD_OLD_PASSWORD).setPlaceholder (sLabelOldPassword))
                                                     .setErrorList (aFormErrors.getListOfField (FIELD_OLD_PASSWORD)));

        final String sLabelNewPassword = EText.LABEL_PASSWORD.getDisplayText (aDisplayLocale);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sLabelNewPassword,
                                                                bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                          : ELabelType.OPTIONAL)
                                                     .setCtrl (new HCEditPassword (FIELD_NEW_PASSWORD).setPlaceholder (sLabelNewPassword))
                                                     .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                     .setErrorList (aFormErrors.getListOfField (FIELD_NEW_PASSWORD)));

        final String sLabelNewPasswordConfirm = EText.LABEL_PASSWORD_CONFIRM.getDisplayText (aDisplayLocale);
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sLabelNewPasswordConfirm,
                                                                bHasAnyPasswordConstraint ? ELabelType.MANDATORY
                                                                                          : ELabelType.OPTIONAL)
                                                     .setCtrl (new HCEditPassword (FIELD_NEW_PASSWORD_CONFIRM).setPlaceholder (sLabelNewPasswordConfirm))
                                                     .setHelpText (BootstrapSecurityUI.createPasswordConstraintTip (aDisplayLocale))
                                                     .setErrorList (aFormErrors.getListOfField (FIELD_NEW_PASSWORD_CONFIRM)));

        final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
        aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_PERFORM);
        // Add the nonce for CSRF check
        aToolbar.addChild (getCSRFHandler ().createCSRFNonceField ());
        aToolbar.addSubmitButtonSave (aDisplayLocale);
      }
    }
  }
}
