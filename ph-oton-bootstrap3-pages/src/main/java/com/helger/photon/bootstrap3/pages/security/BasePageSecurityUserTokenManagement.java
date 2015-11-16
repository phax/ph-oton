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
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.errorlist.FormErrors;
import com.helger.commons.filter.IFilter;
import com.helger.commons.state.EValidity;
import com.helger.commons.state.IValidityIndicator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.datetime.PDTFactory;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.alert.BootstrapWarnBox;
import com.helger.photon.bootstrap3.button.BootstrapButton;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.user.IUserToken;
import com.helger.photon.security.token.user.UserTokenManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCAppTokenSelect;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;

public class BasePageSecurityUserTokenManagement <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageSecurityToken <IUserToken, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
   VALIDITY_APP_TOKEN_MSG ("Es ist noch kein App-Token vorhanden. Es muss mindestens ein App-Token vorhanden sein um ein Benutzer-Token anlegen zu können.", "No app token is present! At least one app token must be present to create a user token for it."),
   VALIDITY_APP_TOKEN_BUTTON ("Neues App-Token anlegen", "Create new app token"),
   BUTTON_CREATE_NEW ("Neues Benutzer-Token anlegen", "Create new user token"),
   HEADER_EDIT ("Benutzer-Token von ''{0}'' bearbeiten", "Edit user token of ''{0}''"),
   HEADER_CREATE ("Neues Benutzer-Token anlegen", "Create a new user token"),
   HEADER_SHOW ("Details von Benutzer-Token für {0}", "Details of user token for {0}"),
   HEADER_NAME ("Name", "Name"),
   HEADER_VALUE ("Wert", "Value"),
   LABEL_ATTRIBUTES ("Attribute", "Attributes"),
   LABEL_APP_TOKEN ("App Token", "App token"),
   LABEL_USER_NAME ("Benutzername", "User name"),
   ERR_APP_TOKEN_EMPTY ("Das App Token muss angegeben werden!", "The app token must be specified!"),
   ERR_USER_NAME_EMPTY ("Der Benutzername darf nicht leer sein!", "The user name may not be empty!"),
   CREATE_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich erstellt.", "The user token for ''{0}'' was successfully created."),
   EDIT_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich bearbeitet.", "The user token for ''{0}'' was successfully edited."),
   DELETE_QUERY ("Sind Sie sicher, dass Sie das Benutzer-Token für ''{0}'' löschen wollen?", "Are you sure you want to delete the user token of ''{0}''?"),
   DELETE_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich gelöscht!", "User token of ''{0}'' was successfully deleted!"),
   DELETE_ERROR ("Beim Löschen des Benutzer-Token für ''{0}'' ist ein Fehler aufgetreten!", "An error occurred while deleting user token of ''{0}''!"),
   TAB_LABEL_ACTIVE ("Aktiv", "Active"),
   TAB_LABEL_DELETED ("Gelöscht", "Deleted"),
   HEADER_APP_TOKEN ("App-Token", "App token"),
   HEADER_USER_NAME ("Eigentümer", "Owner"),
   HEADER_USABLE ("Verwendbar?", "Usable?"),
   ACTION_EDIT ("Benutzer-Token für ''{0}'' bearbeiten", "Edit user token of ''{0}''"),
   ACTION_COPY ("Benutzer-Token für ''{0}'' kopieren", "Copy user token of ''{0}''"),
   ACTION_DELETE ("Benutzer-Token für ''{0}'' löschen", "Delete user token of ''{0}''");

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

  public static final String FIELD_APP_TOKEN = "apptoken";
  public static final String FIELD_USER_NAME = "username";
  public static final String FIELD_TOKEN_STRING = "tokenstring";
  public static final String FIELD_REVOCATION_REASON = "revocationreason";

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_USER_TOKENS.getAsMLT ());
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final String sName,
                                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final IMultilingualText aName,
                                              @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  protected IValidityIndicator isValidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    if (!aAppTokenMgr.containsActiveAppToken ())
    {
      aNodeList.addChild (new BootstrapWarnBox ().addChild (EText.VALIDITY_APP_TOKEN_MSG.getDisplayText (aDisplayLocale)));
      aNodeList.addChild (new BootstrapButton ().addChild (EText.VALIDITY_APP_TOKEN_BUTTON.getDisplayText (aDisplayLocale))
                                                .setOnClick (createCreateURL (aWPEC,
                                                                              BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_APP_TOKEN))
                                                .setIcon (EDefaultIcon.YES));
      return EValidity.INVALID;
    }

    return super.isValidToDisplayPage (aWPEC);
  }

  @Override
  @Nullable
  protected IUserToken getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    return aUserTokenMgr.getUserTokenOfID (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final IUserToken aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return !aSelectedObject.isDeleted ();
    if (eFormAction.isDelete ())
      return !aSelectedObject.isDeleted ();
    return true;
  }

  @Nonnull
  public static HCA createLink (@Nonnull final IWebPageExecutionContext aWPEC, @Nonnull final IAppToken aAppToken)
  {
    return new HCA (createViewURL (aWPEC,
                                   BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_APP_TOKEN,
                                   aAppToken)).addChild (aAppToken.getDisplayName ());
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserToken aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (createActionHeader (EText.HEADER_SHOW.getDisplayTextWithArgs (aDisplayLocale,
                                                                                      aSelectedObject.getUserName ())));

    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    onShowSelectedObjectTableStart (aWPEC, aForm, aSelectedObject);

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_APP_TOKEN.getDisplayText (aDisplayLocale))
                                                 .setCtrl (createLink (aWPEC, aSelectedObject.getAppToken ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USER_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getUserName ()));

    {
      final IHCNode aAT = createAccessTokenListUI (aSelectedObject.getAllAccessTokens (), aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EBaseText.LABEL_ACCESS_TOKENS.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aAT));
    }

    // custom attributes
    final Map <String, String> aCustomAttrs = aSelectedObject.getAllAttributes ();

    // Callback for custom attributes
    final Set <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC, aSelectedObject, aCustomAttrs, aForm);

    if (!aCustomAttrs.isEmpty ())
    {
      // Show remaining custom attributes
      final BootstrapTable aAttrTable = new BootstrapTable (new HCCol (170), HCCol.star ());
      aAttrTable.addHeaderRow ().addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
                                           EText.HEADER_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, String> aEntry : aCustomAttrs.entrySet ())
      {
        final String sName = aEntry.getKey ();
        if (aHandledAttrs == null || !aHandledAttrs.contains (sName))
        {
          final String sValue = aEntry.getValue ();
          aAttrTable.addBodyRow ().addCells (sName, sValue);
        }
      }

      // Maybe all custom attributes where handled in
      // showCustomAttrsOfSelectedObject
      if (aAttrTable.hasBodyRows ())
        aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ATTRIBUTES.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aAttrTable));
    }

    // Callback
    onShowSelectedObjectTableEnd (aWPEC, aForm, aSelectedObject);
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IUserToken aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final boolean bEdit = eFormAction.isEdit ();

    aForm.addChild (createActionHeader (bEdit ? EText.HEADER_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                          aSelectedObject.getDisplayName ())
                                              : EText.HEADER_CREATE.getDisplayText (aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_APP_TOKEN.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCAppTokenSelect (new RequestField (FIELD_APP_TOKEN,
                                                                                                   aSelectedObject == null ? null
                                                                                                                           : aSelectedObject.getAppToken ()),
                                                                                 aDisplayLocale,
                                                                                 new IFilter <IAppToken> ()
                                                                                 {
                                                                                   public boolean matchesFilter (final IAppToken aValue)
                                                                                   {
                                                                                     return !aValue.isDeleted ();
                                                                                   }
                                                                                 }).setReadOnly (bEdit))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_APP_TOKEN)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_USER_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_USER_NAME,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getUserName ())))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_USER_NAME)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EBaseText.LABEL_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_TOKEN_STRING,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getActiveTokenString ())).setReadOnly (bEdit))
                                                 .setHelpText (EBaseText.HELPTEXT_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_TOKEN_STRING)));

    // Custom overridable
    onShowInputFormEnd (aWPEC, aSelectedObject, aForm, eFormAction, aFormErrors);
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IUserToken aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    final boolean bEdit = eFormAction.isEdit ();

    final String sAppTokenID = aWPEC.getAttributeAsString (FIELD_APP_TOKEN);
    final IAppToken aAppToken = bEdit ? aSelectedObject.getAppToken ()
                                      : aAppTokenMgr.getActiveAppTokenOfID (sAppTokenID);
    final String sUserName = aWPEC.getAttributeAsString (FIELD_USER_NAME);
    // Token string cannot be edited
    final String sTokenString = bEdit ? null : aWPEC.getAttributeAsString (FIELD_TOKEN_STRING);

    if (aAppToken == null)
      aFormErrors.addFieldError (FIELD_APP_TOKEN, EText.ERR_APP_TOKEN_EMPTY.getDisplayText (aDisplayLocale));

    if (StringHelper.hasNoText (sUserName))
      aFormErrors.addFieldError (FIELD_USER_NAME, EText.ERR_USER_NAME_EMPTY.getDisplayText (aDisplayLocale));

    if (StringHelper.hasText (sTokenString))
    {
      // Check uniqueness
      if (sTokenString.length () < TOKEN_STRING_MIN_LENGTH)
        aFormErrors.addFieldError (FIELD_TOKEN_STRING,
                                   EBaseText.ERR_TOKEN_STRING_TOO_SHORT.getDisplayText (aDisplayLocale));
      else
        if (aUserTokenMgr.isAccessTokenUsed (sTokenString))
          aFormErrors.addFieldError (FIELD_TOKEN_STRING,
                                     EBaseText.ERR_TOKEN_STRING_IN_USE.getDisplayText (aDisplayLocale));
    }

    // Call custom method
    final Map <String, String> aCustomAttrMap = validateCustomInputParameters (aWPEC,
                                                                               aSelectedObject,
                                                                               aFormErrors,
                                                                               eFormAction);

    if (aFormErrors.isEmpty ())
    {
      if (bEdit)
      {
        aUserTokenMgr.updateUserToken (aSelectedObject.getID (), aCustomAttrMap, sUserName);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.EDIT_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               sUserName)));
      }
      else
      {
        aUserTokenMgr.createUserToken (sTokenString, aCustomAttrMap, aAppToken, sUserName);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.CREATE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                 sUserName)));
      }
    }
  }

  @Override
  @OverrideOnDemand
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final IUserToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     aSelectedObject.getDisplayName ())));
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
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();

    if (aUserTokenMgr.deleteUserToken (aSelectedObject.getID ()).isChanged ())
      aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               aSelectedObject.getDisplayName ())));
    else
      aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                           aSelectedObject.getDisplayName ())));
  }

  public static boolean canRevokeAccessToken (@Nullable final IUserToken aUserToken)
  {
    return aUserToken != null && !aUserToken.isDeleted () && aUserToken.getActiveAccessToken () != null;
  }

  public static boolean canCreateNewAccessToken (@Nullable final IUserToken aUserToken)
  {
    return aUserToken != null && !aUserToken.isDeleted ();
  }

  private boolean _customCreateNewAccessToken (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final boolean bRevokedOld = aSelectedObject.getActiveAccessToken () != null;

    final FormErrors aFormErrors = new FormErrors ();
    if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
    {
      final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
      final String sRevocationReason = aWPEC.getAttributeAsString (FIELD_REVOCATION_REASON);
      final String sTokenString = aWPEC.getAttributeAsString (FIELD_TOKEN_STRING);

      if (bRevokedOld)
      {
        // Check only if something can be revoked...
        if (StringHelper.hasNoText (sRevocationReason))
          aFormErrors.addFieldError (FIELD_REVOCATION_REASON,
                                     EBaseText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));
      }

      if (StringHelper.hasText (sTokenString))
      {
        // Check uniqueness
        if (sTokenString.length () < TOKEN_STRING_MIN_LENGTH)
          aFormErrors.addFieldError (FIELD_TOKEN_STRING,
                                     EBaseText.ERR_TOKEN_STRING_TOO_SHORT.getDisplayText (aDisplayLocale));
        else
          if (aUserTokenMgr.isAccessTokenUsed (sTokenString))
            aFormErrors.addFieldError (FIELD_TOKEN_STRING,
                                       EBaseText.ERR_TOKEN_STRING_IN_USE.getDisplayText (aDisplayLocale));
      }

      if (aFormErrors.isEmpty ())
      {
        aUserTokenMgr.createNewAccessToken (aSelectedObject.getID (),
                                            LoggedInUserManager.getInstance ().getCurrentUserID (),
                                            PDTFactory.getCurrentLocalDateTime (),
                                            sRevocationReason,
                                            sTokenString);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (bRevokedOld ? EBaseText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                               aSelectedObject.getDisplayName ())
                                                                                : EBaseText.CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                    aSelectedObject.getDisplayName ())));
        return false;
      }
      aNodeList.addChild (createIncorrectInputBox (aWPEC));
    }

    final BootstrapForm aForm = new BootstrapForm (aWPEC.getSelfHref ());
    if (bRevokedOld)
    {
      // Show only if something can be revoked...
      aForm.addChild (createActionHeader (EBaseText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                      aSelectedObject.getDisplayName ())));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EBaseText.LABEL_REASON.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCTextArea (new RequestField (FIELD_REVOCATION_REASON)))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_REVOCATION_REASON)));
    }
    else
    {
      aForm.addChild (createActionHeader (EBaseText.CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                           aSelectedObject.getDisplayName ())));
    }

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EBaseText.LABEL_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_TOKEN_STRING)))
                                                 .setHelpText (EBaseText.HELPTEXT_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_TOKEN_STRING)));

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

  private boolean _customRevokeAccessToken (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserToken aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final FormErrors aFormErrors = new FormErrors ();
    if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
    {
      final String sRevocationReason = aWPEC.getAttributeAsString (FIELD_REVOCATION_REASON);
      if (StringHelper.hasNoText (sRevocationReason))
        aFormErrors.addFieldError (FIELD_REVOCATION_REASON, EBaseText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));

      if (aFormErrors.isEmpty ())
      {
        final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
        aUserTokenMgr.revokeAccessToken (aSelectedObject.getID (),
                                         LoggedInUserManager.getInstance ().getCurrentUserID (),
                                         PDTFactory.getCurrentLocalDateTime (),
                                         sRevocationReason);
        aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EBaseText.REVOKE_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                  aSelectedObject.getDisplayName ())));
        return false;
      }
      aNodeList.addChild (createIncorrectInputBox (aWPEC));
    }

    final BootstrapForm aForm = new BootstrapForm (aWPEC.getSelfHref ());
    aForm.addChild (createActionHeader (EBaseText.REVOKE_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     aSelectedObject.getDisplayName ())));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EBaseText.LABEL_REASON.getDisplayText (aDisplayLocale))
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
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final IUserToken aSelectedObject)
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
                               @Nullable final IFilter <IUserToken> aFilter)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final UserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();

    final HCTable aTable = new HCTable (new DTCol (EText.HEADER_APP_TOKEN.getDisplayText (aDisplayLocale)),
                                        new DTCol (EText.HEADER_USER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_USABLE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID () + sIDSuffix);
    for (final IUserToken aCurObject : aUserTokenMgr.getAllUserTokens ())
      if (aFilter == null || aFilter.matchesFilter (aCurObject))
      {
        final ISimpleURL aViewURL = createViewURL (aWPEC, aCurObject);
        final String sDisplayName = aCurObject.getDisplayName ();
        final boolean bUsableNow = !aCurObject.isDeleted () &&
                                   aCurObject.getActiveAccessToken () != null &&
                                   aCurObject.getActiveAccessToken ().isValidNow ();

        final HCRow aBodyRow = aTable.addBodyRow ();
        aBodyRow.addCell (createLink (aWPEC, aCurObject.getAppToken ()));
        aBodyRow.addCell (new HCA (aViewURL).addChild (sDisplayName));
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
                                                                         .setTitle (EBaseText.TITLE_ACTION_CREATE_NEW_ACCESS_TOKEN.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                           sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());
        aActionCell.addChild (" ");
        if (canRevokeAccessToken (aCurObject))
          aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                              .add (CPageParam.PARAM_ACTION, ACTION_REVOKE_ACCESS_TOKEN)
                                              .add (CPageParam.PARAM_OBJECT,
                                                    aCurObject.getID ())).addChild (EDefaultIcon.CANCEL.getAsNode ())
                                                                         .setTitle (EBaseText.TITLE_ACTION_REVOKE_ACCESS_TOKEN.getDisplayTextWithArgs (aDisplayLocale,
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
                    _createList (aWPEC, "active", new IFilter <IUserToken> ()
                    {
                      public boolean matchesFilter (@Nonnull final IUserToken aValue)
                      {
                        return !aValue.isDeleted ();
                      }
                    }));
    aTabBox.addTab (EText.TAB_LABEL_DELETED.getDisplayText (aDisplayLocale),
                    _createList (aWPEC, "deleted", new IFilter <IUserToken> ()
                    {
                      public boolean matchesFilter (@Nonnull final IUserToken aValue)
                      {
                        return aValue.isDeleted ();
                      }
                    }));
    aNodeList.addChild (aTabBox);
  }
}
