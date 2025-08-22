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
package com.helger.photon.bootstrap4.pages.security;

import java.util.Locale;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.base.equals.EqualsHelper;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.html.forms.HCEditPassword;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.pages.AbstractBootstrapWebPage;
import com.helger.photon.bootstrap4.uictrls.ext.BootstrapSecurityUI;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.formlabel.ELabelType;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Base page for changing the password of the currently logged in use.
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web Page Execution Context type
 */
public class BasePageSecurityChangePassword <WPECTYPE extends IWebPageExecutionContext> extends
                                            AbstractBootstrapWebPage <WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    ERROR_NO_USER_PRESENT ("Es ist kein Benutzer angemeldet, daher kann auch das Passwort nicht geändert werden.",
                           "Since no user is logged in no password change is possible."),
    TITLE ("Passwort von ''{0}'' ändern", "Change password of ''{0}''"),
    LABEL_OLD_PASSWORD ("Altes Passwort", "Old password"),
    LABEL_PASSWORD ("Neues Passwort", "New password"),
    LABEL_PASSWORD_CONFIRM ("Neues Passwort (Bestätigung)", "New password (confirmation)"),
    ERROR_OLD_PASSWORD_INVALID ("Das alte Passwort ist ungültig!", "The old password is invalid!"),
    ERROR_PASSWORDS_DONT_MATCH ("Die neuen Passwörter stimmen nicht überein!", "The new passwords don't match"),
    SUCCESS_CHANGE_PW ("Das Passwort wurde erfolgreich geändert!", "Successfully changed the password!");

    private final IMultilingualText m_aTP;

    EText (final String sDE, final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
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

    final IUser aCurrentUser = aWPEC.getLoggedInUser ();
    if (aCurrentUser == null)
    {
      aNodeList.addChild (error (EText.ERROR_NO_USER_PRESENT.getDisplayText (aDisplayLocale)));
    }
    else
    {
      final boolean bShowForm = true;
      final FormErrorList aFormErrors = new FormErrorList ();

      final boolean bFormWasSubmitted = aWPEC.hasAction (CPageParam.ACTION_PERFORM);
      if (bFormWasSubmitted)
      {
        // Check if the CSRF nonce matches
        if (getCSRFHandler ().checkCSRFNonce (aWPEC).isContinue ())
        {
          final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
          final String sOldPlainTextPassword = aWPEC.params ().getAsString (FIELD_OLD_PASSWORD);
          final String sNewPlainTextPassword = aWPEC.params ().getAsString (FIELD_NEW_PASSWORD);
          final String sNewPlainTextPasswordConfirm = aWPEC.params ().getAsString (FIELD_NEW_PASSWORD_CONFIRM);

          if (!aUserMgr.areUserIDAndPasswordValid (aCurrentUser.getID (), sOldPlainTextPassword))
            aFormErrors.addFieldError (FIELD_OLD_PASSWORD,
                                       EText.ERROR_OLD_PASSWORD_INVALID.getDisplayText (aDisplayLocale));

          final ICommonsList <String> aPasswordErrors = GlobalPasswordSettings.getPasswordConstraintList ()
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
            aNodeList.addChild (success (EText.SUCCESS_CHANGE_PW.getDisplayText (aDisplayLocale)));
            // Always show form
          }
          else
            aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));
        }
      }
      if (bShowForm)
      {
        // Show input form
        final boolean bHasAnyPasswordConstraint = GlobalPasswordSettings.getPasswordConstraintList ().hasConstraints ();
        final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC,
                                                                                                 bFormWasSubmitted));
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
