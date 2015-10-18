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
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
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
   BUTTON_CREATE_NEW ("Neues App Token anlegen", "Create new app token"),
   FIELD_OWNER_NAME ("Eigentümer", "Owner"),
   FIELD_OWNER_URL ("URL", "URL"),
   FIELD_OWNER_CONTACT ("Kontaktperson", "Contact person"),
   FIELD_OWNER_CONTACT_EMAIL ("E-Mail Adresse", "Email address"),
   HEADER_OWNER_NAME ("Eigentümer", "Owner"),
   HEADER_OWNER_URL ("URL", "URL"),
   HEADER_OWNER_TOKEN ("Token", "Token"),
   ACTION_EDIT ("Token für ''{0}'' bearbeiten", "Edit token of ''{0}''"),
   ACTION_COPY ("Token für ''{0}'' kopieren", "Copy token of ''{0}''"),
   ACTION_DELETE ("Token für ''{0}'' löschen", "Delete token of ''{0}''"),

   DEL_QUERY ("Sind Sie sicher, dass Sie das App Token für ''{0}'' löschen wollen?", "Are you sure you want to delete the app token of ''{0}''?"),
   DEL_SUCCESS ("Das App Token für ''{0}'' wurde erfolgreich gelöscht!", "App token of ''{0}'' was successfully deleted!"),
   DEL_ERROR ("Beim Löschen des App Tokens für ''{0}'' ist ein Fehler aufgetreten!", "An error occurred while deleting app token of ''{0}''!");

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
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IAppToken aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.FIELD_OWNER_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_OWNER_NAME,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getOwnerName ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_OWNER_NAME)));
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IAppToken aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    final boolean bEdit = eFormAction.isEdit ();

    final String sOwnerName = aWPEC.getAttributeAsString (FIELD_OWNER_NAME);
    final String sOwnerURL = aWPEC.getAttributeAsString (FIELD_OWNER_URL);
    final String sOwnerContact = aWPEC.getAttributeAsString (FIELD_OWNER_CONTACT);
    final String sOwnerContactEmail = aWPEC.getAttributeAsString (FIELD_OWNER_CONTACT_EMAIL);

    if (aFormErrors.isEmpty ())
    {}
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

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();

    // Toolbar on top
    final BootstrapButtonToolbar aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));

    final HCTable aTable = new HCTable (new DTCol (EText.HEADER_OWNER_NAME.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_OWNER_URL.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_OWNER_TOKEN.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID ());
    for (final IAppToken aCurObject : aAppTokenMgr.getAllAppTokens ())
      if (!aCurObject.isDeleted ())
      {
        final HCRow aBodyRow = aTable.addBodyRow ();
        aBodyRow.addCell (aCurObject.getOwnerName ());
        aBodyRow.addCell (aCurObject.getOwnerURL ());
        aBodyRow.addCell (aCurObject.getActiveTokenString ());

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
    aNodeList.addChildren (aTable, aDT);
  }
}
