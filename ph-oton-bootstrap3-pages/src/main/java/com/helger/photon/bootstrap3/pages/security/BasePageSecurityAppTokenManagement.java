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
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.filter.IFilter;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
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
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.label.BootstrapLabel;
import com.helger.photon.bootstrap3.label.EBootstrapLabelType;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.revocation.IRevocationStatus;
import com.helger.photon.security.util.SecurityHelper;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.validation.error.FormErrors;

public class BasePageSecurityAppTokenManagement <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageSecurityObjectWithAttributes <IAppToken, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
   BUTTON_CREATE_NEW ("Neues Zugriffstoken anlegen", "Create new app token"),
   HEADER_EDIT ("Zugriffstoken von ''{0}'' bearbeiten", "Edit app token of ''{0}''"),
   HEADER_CREATE ("Neues Zugriffstoken anlegen", "Create a new app token"),
   HEADER_SHOW ("Details von Zugriffstoken für {0}", "Details of app token for {0}"),
   LABEL_OWNER_NAME ("Eigentümer", "Owner"),
   LABEL_OWNER_URL ("URL", "URL"),
   LABEL_OWNER_CONTACT ("Kontaktperson", "Contact person"),
   LABEL_OWNER_CONTACT_EMAIL ("E-Mail Adresse", "Email address"),
   LABEL_ACCESS_TOKENS ("Zugriffstoken", "Access tokens"),
   NOTE_CURRENT_TOKEN_STRING ("Aktueller Zugriffstoken: {0}", "Current access token: {0}"),
   NOTE_TOKEN_STRING_GENERATED ("Hinweis: das Zugriffstoken wird automatisch generiert.", "Note: the access token is generated automatically."),
   ERR_OWNER_NAME_EMPTY ("Der Eigentümer muss angegeben werden.", "The owner must be specified."),
   CREATE_SUCCESS ("Das Zugriffstoken für ''{0}'' wurde erfolgreich erstellt.", "The app token for ''{0}'' was successfully created."),
   EDIT_SUCCESS ("Das Zugriffstoken für ''{0}'' wurde erfolgreich bearbeitet.", "The app token for ''{0}'' was successfully edited."),
   TAB_LABEL_ACTIVE ("Aktiv", "Active"),
   TAB_LABEL_DELETED ("Gelöscht", "Deleted"),
   HEADER_OWNER_NAME ("Eigentümer", "Owner"),
   HEADER_OWNER_URL ("URL", "URL"),
   HEADER_OWNER_TOKEN ("Token", "Token"),
   HEADER_USABLE ("Verwendbar?", "Usable?"),
   ACTION_EDIT ("Token für ''{0}'' bearbeiten", "Edit token of ''{0}''"),
   ACTION_COPY ("Token für ''{0}'' kopieren", "Copy token of ''{0}''"),
   ACTION_DELETE ("Token für ''{0}'' löschen", "Delete token of ''{0}''"),

   DEL_QUERY ("Sind Sie sicher, dass Sie das Zugriffstoken für ''{0}'' löschen wollen?", "Are you sure you want to delete the app token of ''{0}''?"),
   DEL_SUCCESS ("Das Zugriffstoken für ''{0}'' wurde erfolgreich gelöscht!", "App token of ''{0}'' was successfully deleted!"),
   DEL_ERROR ("Beim Löschen des Zugriffstoken für ''{0}'' ist ein Fehler aufgetreten!", "An error occurred while deleting app token of ''{0}''!");

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

  public static final String FIELD_OWNER_NAME = "ownername";
  public static final String FIELD_OWNER_URL = "ownerurl";
  public static final String FIELD_OWNER_CONTACT = "ownercontact";
  public static final String FIELD_OWNER_CONTACT_EMAIL = "ownercontactemail";

  public static final String ACTION_CREATE_ACCESS_TOKEN = "createaccesstoken";

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
                                                   .setCtrl (aSelectedObject.getOwnerURL ()));

    if (StringHelper.hasText (aSelectedObject.getOwnerContact ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelectedObject.getOwnerContact ()));

    if (StringHelper.hasText (aSelectedObject.getOwnerContactEmail ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_OWNER_CONTACT_EMAIL.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelectedObject.getOwnerContactEmail ()));

    {
      final HCUL aAT = new HCUL ();
      for (final IAccessToken aToken : aSelectedObject.getAllAccessTokens ())
      {
        final IRevocationStatus aRevocationStatus = aToken.getRevocationStatus ();

        final HCNodeList aItem = new HCNodeList ();
        if (!aToken.isValidNow ())
          aItem.addChild (new HCDiv ().addChild (new BootstrapLabel (EBootstrapLabelType.DANGER).addChild ("Not valid")));
        else
          aItem.addChild (new HCDiv ().addChild (new BootstrapLabel (EBootstrapLabelType.SUCCESS).addChild ("Valid now")));
        if (aRevocationStatus.isRevoked ())
        {
          final String sUserName = SecurityHelper.getUserDisplayName (aRevocationStatus.getRevocationUserID (),
                                                                      aDisplayLocale);
          aItem.addChild (new HCDiv ().addChild (new BootstrapLabel (EBootstrapLabelType.DANGER).addChild ("Revoked by " +
                                                                                                           sUserName +
                                                                                                           " on " +
                                                                                                           PDTToString.getAsString (aRevocationStatus.getRevocationDateTime (),
                                                                                                                                    aDisplayLocale) +
                                                                                                           "; reason: " +
                                                                                                           aRevocationStatus.getRevocationReason ())));
        }
        aItem.addChild (new HCDiv ().addChild ("Token: ")
                                    .addChild (SecurityUIHelper.createAppTokenNode (aToken.getTokenString ())));
        aItem.addChild (new HCDiv ().addChild ("Not before: ")
                                    .addChild (PDTToString.getAsString (aToken.getNotBefore (), aDisplayLocale)));
        if (aToken.getNotAfter () != null)
          aItem.addChild (new HCDiv ().addChild ("Not after: ")
                                      .addChild (PDTToString.getAsString (aToken.getNotAfter (), aDisplayLocale)));
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
      final String sAppToken = aSelectedObject.getActiveTokenString ();
      if (StringHelper.hasText (sAppToken))
        aForm.addChild (new BootstrapInfoBox ().addChild (EText.NOTE_CURRENT_TOKEN_STRING.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                  sAppToken)));
    }
    else
      aForm.addChild (new BootstrapInfoBox ().addChild (EText.NOTE_TOKEN_STRING_GENERATED.getDisplayText (aDisplayLocale)));
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
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final IAppToken aSelectedObject)
  {
    if (aWPEC.hasAction (ACTION_CREATE_ACCESS_TOKEN) && aSelectedObject != null)
    {
      return false;
    }
    return true;
  }

  @Override
  @OverrideOnDemand
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final IAppToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DEL_QUERY.getDisplayTextWithArgs (aDisplayLocale,
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
      aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.DEL_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            aSelectedObject.getOwnerName ())));
    else
      aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.DEL_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                        aSelectedObject.getOwnerName ())));
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
                                        new DTCol (EText.HEADER_OWNER_TOKEN.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_USABLE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID () + sIDSuffix);
    for (final IAppToken aCurObject : aAppTokenMgr.getAllAppTokens ())
      if (aFilter == null || aFilter.matchesFilter (aCurObject))
      {
        final ISimpleURL aViewURL = createViewURL (aWPEC, aCurObject);
        final boolean bUsableNow = !aCurObject.isDeleted () &&
                                   aCurObject.getActiveAccessToken () != null &&
                                   aCurObject.getActiveAccessToken ().isValidNow ();

        final HCRow aBodyRow = aTable.addBodyRow ();
        aBodyRow.addCell (new HCA (aViewURL).addChild (aCurObject.getOwnerName ()));
        aBodyRow.addCell (aCurObject.getOwnerURL ());
        aBodyRow.addCell (SecurityUIHelper.createAppTokenNode (aCurObject.getActiveTokenString ()));
        aBodyRow.addCell (EPhotonCoreText.getYesOrNo (bUsableNow, aDisplayLocale));

        final IHCCell <?> aActionCell = aBodyRow.addCell ();
        if (isActionAllowed (aWPEC, EWebPageFormAction.EDIT, aCurObject))
          aActionCell.addChild (createEditLink (aWPEC,
                                                aCurObject,
                                                EText.ACTION_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                          aCurObject.getOwnerName ())));
        else
          aActionCell.addChild (createEmptyAction ());
        aActionCell.addChild (" ");
        aActionCell.addChild (createCopyLink (aWPEC,
                                              aCurObject,
                                              EText.ACTION_COPY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                        aCurObject.getOwnerName ())));
        aActionCell.addChild (" ");
        if (isActionAllowed (aWPEC, EWebPageFormAction.DELETE, aCurObject))
          aActionCell.addChild (createDeleteLink (aWPEC,
                                                  aCurObject,
                                                  EText.ACTION_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                              aCurObject.getOwnerName ())));
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
