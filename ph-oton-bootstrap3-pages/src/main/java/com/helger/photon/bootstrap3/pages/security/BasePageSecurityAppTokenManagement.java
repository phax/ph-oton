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
package com.helger.photon.bootstrap3.pages.security;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.filter.IFilter;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.URLValidator;
import com.helger.datetime.PDTFactory;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCA_MailTo;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.alert.BootstrapWarnBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.label.BootstrapLabel;
import com.helger.photon.bootstrap3.label.EBootstrapLabelType;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.revocation.IRevocationStatus;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.smtp.util.EmailAddressValidator;
import com.helger.validation.error.FormErrors;

public class BasePageSecurityAppTokenManagement <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageForm <IAppToken, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
   BUTTON_CREATE_NEW ("Neues App Token anlegen", "Create new app token"),
   HEADER_EDIT ("App Token von ''{0}'' bearbeiten", "Edit app token of ''{0}''"),
   HEADER_CREATE ("Neues App Token anlegen", "Create a new app token"),
   HEADER_SHOW ("Details von App Token für {0}", "Details of app token for {0}"),
   LABEL_OWNER_NAME ("Eigentümer", "Owner"),
   LABEL_OWNER_URL ("URL", "URL"),
   LABEL_OWNER_CONTACT ("Kontaktperson", "Contact person"),
   LABEL_OWNER_CONTACT_EMAIL ("E-Mail Adresse", "Email address"),
   LABEL_ACCESS_TOKENS ("Zugriffstoken", "Access tokens"),
   SHOW_REVOKED ("Zurückgezogen von {0} um {1}; Begründung: {2}", "Revoked by {0} on {1}; reason: {2}"),
   SHOW_INVALID_NOW ("Jetzt nicht mehr gültig", "Not valid now"),
   SHOW_VALID_NOW ("Jetzt gültig", "Valid now"),
   SHOW_ACCESS_TOKEN ("Zugriffstoken: ", "Access token: "),
   SHOW_NOT_BEFORE ("Gültig ab: {0}", "Not before: {0}"),
   SHOW_NOT_AFTER ("Gültig bis: {0}", "Not after: {0}"),
   NOTE_CURRENT_ACCESS_TOKEN ("Aktuelles Zugriffstoken: ", "Current access token: "),
   NOTE_NO_ACCESS_TOKEN ("Diesem App Token ist kein Zugriffstoken zugeordnet", "This app token currently has no access token"),
   NOTE_ACCESS_TOKEN_GENERATED ("Hinweis: das Zugriffstoken wird automatisch generiert.", "Note: the access token is generated automatically."),
   ERR_OWNER_NAME_EMPTY ("Der Eigentümer muss angegeben werden!", "The owner must be specified!"),
   ERR_OWNER_URL_INVALID ("Die URL ist ungültig!", "The URL is invalid!"),
   ERR_OWNER_EMAIL_INVALID ("Die E-Mail-Adresse ist ungültig!", "The email address is invalid!"),
   CREATE_SUCCESS ("Das App Token für ''{0}'' wurde erfolgreich erstellt.", "The app token for ''{0}'' was successfully created."),
   EDIT_SUCCESS ("Das App Token für ''{0}'' wurde erfolgreich bearbeitet.", "The app token for ''{0}'' was successfully edited."),
   DELETE_QUERY ("Sind Sie sicher, dass Sie das App Token für ''{0}'' löschen wollen?", "Are you sure you want to delete the app token of ''{0}''?"),
   DELETE_SUCCESS ("Das App Token für ''{0}'' wurde erfolgreich gelöscht!", "App token of ''{0}'' was successfully deleted!"),
   DELETE_ERROR ("Beim Löschen des App Token für ''{0}'' ist ein Fehler aufgetreten!", "An error occurred while deleting app token of ''{0}''!"),
   LABEL_REASON ("Begründung", "Reason"),
   ERR_REASON_EMPTY ("Es muss eine Bgründung angegeben werden!", "A reason must be provided!"),
   REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_SUCCESS ("Das alte Zugriffstoken von ''{0}'' wurde widerrufen und ein Neues wurde erfolgreich erstellt.", "The old access token of ''{0}'' was revoked and a new access token was successfully created."),
   REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_HEADER ("Das alte Zugriffstoken von ''{0}'' widerrufen und ein Neues erstellen", "Revoke the old access token of ''{0}'' and create a new access token"),
   CREATE_NEW_ACCESS_TOKEN_SUCCESS ("Das neue Zugriffstoken für ''{0}'' wurde erfolgreich erstellt.", "A new access token for ''{0}'' was successfully created."),
   CREATE_NEW_ACCESS_TOKEN_HEADER ("Ein neues Zugriffstoken für ''{0}'' erstellen", "Create a new access token for ''{0}''"),
   REVOKE_ACCESS_TOKEN_SUCCESS ("Das alte Zugriffstoken von ''{0}'' wurde erfolgreich widerrufen.", "The old access token of ''{0}'' was successfully revoked."),
   REVOKE_ACCESS_TOKEN_HEADER ("Das alte Zugriffstoken von ''{0}'' widerrufen", "Revoke the old access token of ''{0}''"),
   TAB_LABEL_ACTIVE ("Aktiv", "Active"),
   TAB_LABEL_DELETED ("Gelöscht", "Deleted"),
   HEADER_OWNER_NAME ("Eigentümer", "Owner"),
   HEADER_OWNER_URL ("URL", "URL"),
   HEADER_OWNER_TOKEN ("Token", "Token"),
   HEADER_USABLE ("Verwendbar?", "Usable?"),
   ACTION_EDIT ("App Token für ''{0}'' bearbeiten", "Edit app token of ''{0}''"),
   ACTION_COPY ("App Token für ''{0}'' kopieren", "Copy app token of ''{0}''"),
   ACTION_DELETE ("App Token für ''{0}'' löschen", "Delete app token of ''{0}''"),
   TITLE_ACTION_CREATE_NEW_ACCESS_TOKEN ("Neuen Zugriffstoken für ''{0}'' erzeugen", "Create new access token for ''{0}''"),
   TITLE_ACTION_REVOKE_ACCESS_TOKEN ("Zugriffstoken für ''{0}'' zurückziehen", "Revoke access token for ''{0}''");

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

  public static final String ACTION_REVOKE_ACCESS_TOKEN = "revokeaccesstoken";
  public static final String ACTION_CREATE_NEW_ACCESS_TOKEN = "createnewaccesstoken";

  public static final String FIELD_OWNER_NAME = "ownername";
  public static final String FIELD_OWNER_URL = "ownerurl";
  public static final String FIELD_OWNER_CONTACT = "ownercontact";
  public static final String FIELD_OWNER_CONTACT_EMAIL = "ownercontactemail";
  public static final String FIELD_REVOCATION_REASON = "revocationreason";

  public BasePageSecurityAppTokenManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_APP_TOKENS.getAsMLT ());
  }

  public BasePageSecurityAppTokenManagement (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSecurityAppTokenManagement (@Nonnull @Nonempty final String sID,
                                             @Nonnull final String sName,
                                             @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSecurityAppTokenManagement (@Nonnull @Nonempty final String sID,
                                             @Nonnull final IMultilingualText aName,
                                             @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected boolean isObjectLockingEnabled ()
  {
    // Enable object locking
    return true;
  }

  @Override
  @Nullable
  protected IAppToken getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    return aAppTokenMgr.getAppTokenOfID (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final IAppToken aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return !aSelectedObject.isDeleted ();
    if (eFormAction.isDelete ())
      return !aSelectedObject.isDeleted ();
    return true;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IAppToken aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (createActionHeader (EText.HEADER_SHOW.getDisplayTextWithArgs (aDisplayLocale,
                                                                                      aSelectedObject.getOwnerName ())));

    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getOwnerName ()));

    if (StringHelper.hasText (aSelectedObject.getOwnerURL ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_URL.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCA.createLinkedWebsite (aSelectedObject.getOwnerURL (),
                                                                                      HC_Target.BLANK)));

    if (StringHelper.hasText (aSelectedObject.getOwnerContact ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelectedObject.getOwnerContact ()));

    if (StringHelper.hasText (aSelectedObject.getOwnerContactEmail ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT_EMAIL.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCA_MailTo.createLinkedEmail (aSelectedObject.getOwnerContactEmail ())));

    {
      final HCUL aAT = new HCUL ();
      // Reverse so that the newest token is on top
      for (final IAccessToken aToken : CollectionHelper.getReverseList (aSelectedObject.getAllAccessTokens ()))
      {
        final IRevocationStatus aRevocationStatus = aToken.getRevocationStatus ();

        final HCNodeList aItem = new HCNodeList ();
        if (aRevocationStatus.isRevoked ())
        {
          // Revoked
          final String sUserName = SecurityHelper.getUserDisplayName (aRevocationStatus.getRevocationUserID (),
                                                                      aDisplayLocale);
          aItem.addChild (new BootstrapLabel (EBootstrapLabelType.DANGER).addChild (EText.SHOW_REVOKED.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                               sUserName,
                                                                                                                               PDTToString.getAsString (aRevocationStatus.getRevocationDateTime (),
                                                                                                                                                        aDisplayLocale),
                                                                                                                               aRevocationStatus.getRevocationReason ())));
        }
        else
        {
          // Not revoked
          if (!aToken.isValidNow ())
            aItem.addChild (new BootstrapLabel (EBootstrapLabelType.DANGER).addChild (EText.SHOW_INVALID_NOW.getDisplayText (aDisplayLocale)));
          else
            aItem.addChild (new BootstrapLabel (EBootstrapLabelType.SUCCESS).addChild (EText.SHOW_VALID_NOW.getDisplayText (aDisplayLocale)));
        }
        aItem.addChild (new HCDiv ().addChild (EText.SHOW_ACCESS_TOKEN.getDisplayText (aDisplayLocale))
                                    .addChild (SecurityUIHelper.createAccessTokenNode (aToken.getTokenString ())));
        aItem.addChild (new HCDiv ().addChild (EText.SHOW_NOT_BEFORE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                             PDTToString.getAsString (aToken.getNotBefore (),
                                                                                                                      aDisplayLocale))));
        if (aToken.getNotAfter () != null)
          aItem.addChild (new HCDiv ().addChild (EText.SHOW_NOT_BEFORE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               PDTToString.getAsString (aToken.getNotAfter (),
                                                                                                                        aDisplayLocale))));
        aAT.addItem (aItem);
      }
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ACCESS_TOKENS.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aAT));
    }
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IAppToken aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final boolean bEdit = eFormAction.isEdit ();

    aForm.addChild (createActionHeader (bEdit ? EText.HEADER_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                          aSelectedObject.getOwnerName ())
                                              : EText.HEADER_CREATE.getDisplayText (aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_OWNER_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_OWNER_NAME,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getOwnerName ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_OWNER_NAME)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_URL.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_OWNER_URL,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getOwnerURL ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_OWNER_URL)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_OWNER_CONTACT,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getOwnerContact ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_OWNER_CONTACT)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT_EMAIL.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_OWNER_CONTACT_EMAIL,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getOwnerContactEmail ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_OWNER_CONTACT_EMAIL)));

    if (bEdit)
    {
      final String sTokenString = aSelectedObject.getActiveTokenString ();
      if (StringHelper.hasText (sTokenString))
        aForm.addChild (new BootstrapInfoBox ().addChild (EText.NOTE_CURRENT_ACCESS_TOKEN.getDisplayText (aDisplayLocale))
                                               .addChild (SecurityUIHelper.createAccessTokenNode (sTokenString)));
      else
        aForm.addChild (new BootstrapWarnBox ().addChild (EText.NOTE_NO_ACCESS_TOKEN.getDisplayText (aDisplayLocale)));
    }
    else
      aForm.addChild (new BootstrapInfoBox ().addChild (EText.NOTE_ACCESS_TOKEN_GENERATED.getDisplayText (aDisplayLocale)));
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IAppToken aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    final boolean bEdit = eFormAction.isEdit ();

    final String sOwnerName = aWPEC.getAttributeAsString (FIELD_OWNER_NAME);
    final String sOwnerURL = aWPEC.getAttributeAsString (FIELD_OWNER_URL);
    final String sOwnerContact = aWPEC.getAttributeAsString (FIELD_OWNER_CONTACT);
    final String sOwnerContactEmail = aWPEC.getAttributeAsString (FIELD_OWNER_CONTACT_EMAIL);

    if (StringHelper.hasNoText (sOwnerName))
      aFormErrors.addFieldError (FIELD_OWNER_NAME, EText.ERR_OWNER_NAME_EMPTY.getDisplayText (aDisplayLocale));

    if (StringHelper.hasText (sOwnerURL))
    {
      if (!URLValidator.isValid (sOwnerURL))
        aFormErrors.addFieldError (FIELD_OWNER_URL, EText.ERR_OWNER_URL_INVALID.getDisplayText (aDisplayLocale));
    }

    if (StringHelper.hasText (sOwnerContactEmail))
    {
      if (!EmailAddressValidator.isValid (sOwnerContactEmail))
        aFormErrors.addFieldError (FIELD_OWNER_CONTACT_EMAIL,
                                   EText.ERR_OWNER_EMAIL_INVALID.getDisplayText (aDisplayLocale));
    }

    if (aFormErrors.isEmpty ())
    {
      if (bEdit)
      {
        aAppTokenMgr.updateAppToken (aSelectedObject.getID (),
                                     sOwnerName,
                                     sOwnerURL,
                                     sOwnerContact,
                                     sOwnerContactEmail);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.EDIT_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               sOwnerName)));
      }
      else
      {
        aAppTokenMgr.createAppToken (sOwnerName, sOwnerURL, sOwnerContact, sOwnerContactEmail);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.CREATE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                 sOwnerName)));
      }
    }
  }

  @Override
  @OverrideOnDemand
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final IAppToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     aSelectedObject.getOwnerName ())));
  }

  /**
   * Perform object delete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @Override
  @OverrideOnDemand
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IAppToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

    if (aAppTokenMgr.deleteAppToken (aSelectedObject.getID ()).isChanged ())
      aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               aSelectedObject.getOwnerName ())));
    else
      aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                           aSelectedObject.getOwnerName ())));
  }

  public static boolean canRevokeAccessToken (@Nullable final IAppToken aAppToken)
  {
    return aAppToken != null && !aAppToken.isDeleted () && aAppToken.getActiveAccessToken () != null;
  }

  public static boolean canCreateNewAccessToken (@Nullable final IAppToken aAppToken)
  {
    return aAppToken != null && !aAppToken.isDeleted ();
  }

  private boolean _customCreateNewAccessToken (@Nonnull final WPECTYPE aWPEC, @Nonnull final IAppToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final boolean bRevokedOld = aSelectedObject.getActiveAccessToken () != null;

    final FormErrors aFormErrors = new FormErrors ();
    if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
    {
      final String sRevocationReason = aWPEC.getAttributeAsString (FIELD_REVOCATION_REASON);
      if (bRevokedOld)
      {
        // Check only if something can be revoked...
        if (StringHelper.hasNoText (sRevocationReason))
          aFormErrors.addFieldError (FIELD_REVOCATION_REASON, EText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));
      }

      if (aFormErrors.isEmpty ())
      {
        final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
        aAppTokenMgr.createNewAccessToken (aSelectedObject.getID (),
                                           LoggedInUserManager.getInstance ().getCurrentUserID (),
                                           PDTFactory.getCurrentLocalDateTime (),
                                           sRevocationReason);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (bRevokedOld ? EText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                           aSelectedObject.getOwnerName ())
                                                                                : EText.CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                aSelectedObject.getOwnerName ())));
        return false;
      }
      aNodeList.addChild (createIncorrectInputBox (aWPEC));
    }

    final BootstrapForm aForm = new BootstrapForm (aWPEC.getSelfHref ());
    if (bRevokedOld)
    {
      // Show only if something can be revoked...
      aForm.addChild (createActionHeader (EText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                  aSelectedObject.getOwnerName ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_REASON.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCTextArea (new RequestField (FIELD_REVOCATION_REASON)))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_REVOCATION_REASON)));
    }
    else
    {
      aForm.addChild (createActionHeader (EText.CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                       aSelectedObject.getOwnerName ())));
    }

    final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_CREATE_NEW_ACCESS_TOKEN);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    if (bRevokedOld)
    {
      aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), EDefaultIcon.SAVE);
      aToolbar.addButtonCancel (aDisplayLocale);
    }
    else
    {
      aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale), EDefaultIcon.YES);
      aToolbar.addButtonNo (aDisplayLocale);
    }
    aNodeList.addChild (aForm);
    return false;
  }

  private boolean _customRevokeAccessToken (@Nonnull final WPECTYPE aWPEC, @Nonnull final IAppToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final FormErrors aFormErrors = new FormErrors ();
    if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
    {
      final String sRevocationReason = aWPEC.getAttributeAsString (FIELD_REVOCATION_REASON);
      if (StringHelper.hasNoText (sRevocationReason))
        aFormErrors.addFieldError (FIELD_REVOCATION_REASON, EText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));

      if (aFormErrors.isEmpty ())
      {
        final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
        aAppTokenMgr.revokeAccessToken (aSelectedObject.getID (),
                                        LoggedInUserManager.getInstance ().getCurrentUserID (),
                                        PDTFactory.getCurrentLocalDateTime (),
                                        sRevocationReason);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.REVOKE_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                              aSelectedObject.getOwnerName ())));
        return false;
      }
      aNodeList.addChild (createIncorrectInputBox (aWPEC));
    }

    final BootstrapForm aForm = new BootstrapForm (aWPEC.getSelfHref ());
    aForm.addChild (createActionHeader (EText.REVOKE_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                 aSelectedObject.getOwnerName ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_REASON.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCTextArea (new RequestField (FIELD_REVOCATION_REASON)))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_REVOCATION_REASON)));

    final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_REVOKE_ACCESS_TOKEN);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale), EDefaultIcon.SAVE);
    aToolbar.addButtonCancel (aDisplayLocale);
    aNodeList.addChild (aForm);
    return false;
  }

  @Override
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final IAppToken aSelectedObject)
  {
    if (aWPEC.hasAction (ACTION_CREATE_NEW_ACCESS_TOKEN) && canCreateNewAccessToken (aSelectedObject))
      return _customCreateNewAccessToken (aWPEC, aSelectedObject);
    if (aWPEC.hasAction (ACTION_REVOKE_ACCESS_TOKEN) && canRevokeAccessToken (aSelectedObject))
      return _customRevokeAccessToken (aWPEC, aSelectedObject);

    return true;
  }

  @Nonnull
  private IHCNode _createList (@Nonnull final WPECTYPE aWPEC,
                               @Nonnull final String sIDSuffix,
                               @Nullable final IFilter <IAppToken> aFilter)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

    final HCTable aTable = new HCTable (new DTCol (EText.HEADER_OWNER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_OWNER_URL.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_OWNER_TOKEN.getDisplayText (aDisplayLocale)).setVisible (false),
                                        new DTCol (EText.HEADER_USABLE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID () + sIDSuffix);
    for (final IAppToken aCurObject : aAppTokenMgr.getAllAppTokens ())
      if (aFilter == null || aFilter.matchesFilter (aCurObject))
      {
        final ISimpleURL aViewURL = createViewURL (aWPEC, aCurObject);
        final String sDisplayName = aCurObject.getOwnerName ();
        final boolean bUsableNow = !aCurObject.isDeleted () &&
                                   aCurObject.getActiveAccessToken () != null &&
                                   aCurObject.getActiveAccessToken ().isValidNow ();

        final HCRow aBodyRow = aTable.addBodyRow ();
        aBodyRow.addCell (new HCA (aViewURL).addChild (sDisplayName));
        aBodyRow.addCell (HCA.createLinkedWebsite (aCurObject.getOwnerURL (), HC_Target.BLANK));
        aBodyRow.addCell (SecurityUIHelper.createAccessTokenNode (aCurObject.getActiveTokenString ()));
        aBodyRow.addCell (EPhotonCoreText.getYesOrNo (bUsableNow, aDisplayLocale));

        final IHCCell <?> aActionCell = aBodyRow.addCell ();
        if (isActionAllowed (aWPEC, EWebPageFormAction.EDIT, aCurObject))
          aActionCell.addChild (createEditLink (aWPEC,
                                                aCurObject,
                                                EText.ACTION_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                          sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());
        aActionCell.addChild (" ");
        aActionCell.addChild (createCopyLink (aWPEC,
                                              aCurObject,
                                              EText.ACTION_COPY.getDisplayTextWithArgs (aDisplayLocale, sDisplayName)));
        aActionCell.addChild (" ");
        if (isActionAllowed (aWPEC, EWebPageFormAction.DELETE, aCurObject))
          aActionCell.addChild (createDeleteLink (aWPEC,
                                                  aCurObject,
                                                  EText.ACTION_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                              sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());
        aActionCell.addChild (" ");
        if (canCreateNewAccessToken (aCurObject))
          aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                              .add (CPageParam.PARAM_ACTION, ACTION_CREATE_NEW_ACCESS_TOKEN)
                                              .add (CPageParam.PARAM_OBJECT,
                                                    aCurObject.getID ())).addChild (EDefaultIcon.REFRESH.getAsNode ())
                                                                         .setTitle (EText.TITLE_ACTION_CREATE_NEW_ACCESS_TOKEN.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                       sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());
        aActionCell.addChild (" ");
        if (canRevokeAccessToken (aCurObject))
          aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                              .add (CPageParam.PARAM_ACTION, ACTION_REVOKE_ACCESS_TOKEN)
                                              .add (CPageParam.PARAM_OBJECT,
                                                    aCurObject.getID ())).addChild (EDefaultIcon.CANCEL.getAsNode ())
                                                                         .setTitle (EText.TITLE_ACTION_REVOKE_ACCESS_TOKEN.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                   sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());
      }
    final BootstrapDataTables aDT = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    return new HCNodeList ().addChildren (aTable, aDT);
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Toolbar on top
    final BootstrapButtonToolbar aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();
    aTabBox.addTab (EText.TAB_LABEL_ACTIVE.getDisplayText (aDisplayLocale),
                    _createList (aWPEC, "active", new IFilter <IAppToken> ()
                    {
                      public boolean matchesFilter (@Nonnull final IAppToken aValue)
                      {
                        return !aValue.isDeleted ();
                      }
                    }));
    aTabBox.addTab (EText.TAB_LABEL_DELETED.getDisplayText (aDisplayLocale),
                    _createList (aWPEC, "deleted", new IFilter <IAppToken> ()
                    {
                      public boolean matchesFilter (@Nonnull final IAppToken aValue)
                      {
                        return aValue.isDeleted ();
                      }
                    }));
    aNodeList.addChild (aTabBox);
  }
}
