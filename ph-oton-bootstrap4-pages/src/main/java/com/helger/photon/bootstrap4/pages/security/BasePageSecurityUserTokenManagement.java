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
import java.util.Map;
import java.util.function.Predicate;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.Translatable;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.compare.ESortOrder;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EValidity;
import com.helger.base.state.IValidityIndicator;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsMap;
import com.helger.collection.commons.ICommonsSet;
import com.helger.datetime.helper.PDTFactory;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.forms.HCTextArea;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap4.button.BootstrapButton;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.form.BootstrapViewForm;
import com.helger.photon.bootstrap4.nav.BootstrapTabBox;
import com.helger.photon.bootstrap4.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap4.pages.handler.AbstractBootstrapWebPageActionHandler;
import com.helger.photon.bootstrap4.pages.handler.AbstractBootstrapWebPageActionHandlerDelete;
import com.helger.photon.bootstrap4.table.BootstrapTable;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.user.IUserToken;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.select.HCUserSelect;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EShowList;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayTextWithArgs;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;
import com.helger.url.ISimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

public class BasePageSecurityUserTokenManagement <WPECTYPE extends IWebPageExecutionContext> extends
                                                 AbstractWebPageSecurityToken <IUserToken, WPECTYPE>
{
  @Translatable
  protected enum EText implements IHasDisplayTextWithArgs
  {
    ERROR_NO_USER ("Es existiert kein Benutzer daher kann auch kein Benutzer-Token angelegt werden",
                   "Since no user exists, no user token can be created"),
    BUTTON_CREATE_NEW_USER ("Neuen Benutzer anlegen", "Create new user"),
    BUTTON_CREATE_NEW ("Neues Benutzer-Token anlegen", "Create new user token"),
    HEADER_EDIT ("Benutzer-Token von ''{0}'' bearbeiten", "Edit user token of ''{0}''"),
    HEADER_CREATE ("Neues Benutzer-Token anlegen", "Create a new user token"),
    HEADER_SHOW ("Details von Benutzer-Token für {0}", "Details of user token for {0}"),
    HEADER_NAME ("Name", "Name"),
    HEADER_VALUE ("Wert", "Value"),
    LABEL_ATTRIBUTES ("Attribute", "Attributes"),
    LABEL_USER ("Benutzer", "User"),
    ERR_USER_EMPTY ("Es muss ein Benutzer ausgewählt werden!", "A user must be selected!"),
    CREATE_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich erstellt.",
                    "The user token for ''{0}'' was successfully created."),
    CREATE_ERROR ("Das Benutzer-Token für ''{0}'' konnte nicht erstellt werden.",
                  "Error creating user token for ''{0}''."),
    EDIT_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich bearbeitet.",
                  "The user token for ''{0}'' was successfully edited."),
    DELETE_QUERY ("Sind Sie sicher, dass Sie das Benutzer-Token für ''{0}'' löschen wollen?",
                  "Are you sure you want to delete the user token of ''{0}''?"),
    DELETE_SUCCESS ("Das Benutzer-Token für ''{0}'' wurde erfolgreich gelöscht!",
                    "User token of ''{0}'' was successfully deleted!"),
    DELETE_ERROR ("Beim Löschen des Benutzer-Token für ''{0}'' ist ein Fehler aufgetreten!",
                  "An error occurred while deleting user token of ''{0}''!"),
    TAB_LABEL_ACTIVE ("Aktiv", "Active"),
    TAB_LABEL_DELETED ("Gelöscht", "Deleted"),
    HEADER_USER ("Benutzer", "User"),
    HEADER_USABLE ("Verwendbar?", "Usable?"),
    ACTION_EDIT ("Benutzer-Token für ''{0}'' bearbeiten", "Edit user token of ''{0}''"),
    ACTION_COPY ("Benutzer-Token für ''{0}'' kopieren", "Copy user token of ''{0}''"),
    ACTION_DELETE ("Benutzer-Token für ''{0}'' löschen", "Delete user token of ''{0}''");

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

  public static final String ACTION_REVOKE_ACCESS_TOKEN = "revokeaccesstoken";
  public static final String ACTION_CREATE_NEW_ACCESS_TOKEN = "createnewaccesstoken";

  public static final String FIELD_USER = "user";
  public static final String FIELD_TOKEN_STRING = "tokenstring";
  public static final String FIELD_REVOCATION_REASON = "revocationreason";

  public static boolean canCreateNewAccessToken (@Nullable final IUserToken aUserToken)
  {
    return aUserToken != null && !aUserToken.isDeleted ();
  }

  public static boolean canRevokeAccessToken (@Nullable final IUserToken aUserToken)
  {
    return aUserToken != null && !aUserToken.isDeleted () && aUserToken.getAccessTokenList ().hasActiveAccessToken ();
  }

  private void _init ()
  {
    setDeleteHandler (new AbstractBootstrapWebPageActionHandlerDelete <IUserToken, WPECTYPE> ()
    {
      @Override
      @OverrideOnDemand
      protected void showQuery (@Nonnull final WPECTYPE aWPEC,
                                @Nonnull final BootstrapForm aForm,
                                @Nullable final IUserToken aSelectedObject)
      {
        ValueEnforcer.notNull (aSelectedObject, "SelectedObject");
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

        aForm.addChild (question (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                             aSelectedObject.getDisplayName ())));
      }

      @Override
      @OverrideOnDemand
      protected void performAction (@Nonnull final WPECTYPE aWPEC, @Nullable final IUserToken aSelectedObject)
      {
        ValueEnforcer.notNull (aSelectedObject, "SelectedObject");
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();

        if (aUserTokenMgr.deleteUserToken (aSelectedObject.getID ()).isChanged ())
          aWPEC.postRedirectGetInternal (success (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               aSelectedObject.getDisplayName ())));
        else
          aWPEC.postRedirectGetInternal (error (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                           aSelectedObject.getDisplayName ())));
      }
    });
    addCustomHandler (ACTION_CREATE_NEW_ACCESS_TOKEN,
                      new AbstractBootstrapWebPageActionHandler <IUserToken, WPECTYPE> (true)
                      {
                        @Override
                        public boolean canHandleAction (@Nonnull final WPECTYPE aWPEC,
                                                        @Nullable final IUserToken aSelectedObject)
                        {
                          assert aSelectedObject != null;

                          return canCreateNewAccessToken (aSelectedObject);
                        }

                        @Nonnull
                        public EShowList handleAction (@Nonnull final WPECTYPE aWPEC,
                                                       @Nullable final IUserToken aSelectedObject)
                        {
                          assert aSelectedObject != null;
                          final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
                          final HCNodeList aNodeList = aWPEC.getNodeList ();
                          final boolean bRevokedOld = aSelectedObject.getAccessTokenList ().hasActiveAccessToken ();

                          final FormErrorList aFormErrors = new FormErrorList ();
                          if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
                          {
                            final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
                            final String sRevocationReason = aWPEC.params ().getAsString (FIELD_REVOCATION_REASON);
                            final String sTokenString = aWPEC.params ().getAsString (FIELD_TOKEN_STRING);

                            if (bRevokedOld)
                            {
                              // Check only if something can be revoked...
                              if (StringHelper.isEmpty (sRevocationReason))
                                aFormErrors.addFieldError (FIELD_REVOCATION_REASON,
                                                           EBaseText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));
                            }

                            if (StringHelper.isNotEmpty (sTokenString))
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
                                                                  LoggedInUserManager.getInstance ()
                                                                                     .getCurrentUserID (),
                                                                  PDTFactory.getCurrentLocalDateTime (),
                                                                  sRevocationReason,
                                                                  sTokenString);
                              aWPEC.postRedirectGetInternal (success (bRevokedOld ? EBaseText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                                 aSelectedObject.getDisplayName ())
                                                                                  : EBaseText.CREATE_NEW_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                      aSelectedObject.getDisplayName ())));
                              return EShowList.SHOW_LIST;
                            }
                            aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));
                          }

                          final BootstrapForm aForm = getUIHandler ().createFormSelf (aWPEC);
                          if (bRevokedOld)
                          {
                            // Show only if something can be revoked...
                            aForm.addChild (getUIHandler ().createActionHeader (EBaseText.REVOKE_AND_CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                            aSelectedObject.getDisplayName ())));
                            aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EBaseText.LABEL_REASON.getDisplayText (aDisplayLocale))
                                                                         .setCtrl (new HCTextArea (new RequestField (FIELD_REVOCATION_REASON)))
                                                                         .setErrorList (aFormErrors.getListOfField (FIELD_REVOCATION_REASON)));
                          }
                          else
                          {
                            aForm.addChild (getUIHandler ().createActionHeader (EBaseText.CREATE_NEW_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
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
                            aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale),
                                                      EDefaultIcon.SAVE);
                            aToolbar.addButtonCancel (aDisplayLocale);
                          }
                          else
                          {
                            aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale),
                                                      EDefaultIcon.YES);
                            aToolbar.addButtonNo (aDisplayLocale);
                          }
                          aNodeList.addChild (aForm);
                          return EShowList.DONT_SHOW_LIST;
                        }
                      });
    addCustomHandler (ACTION_REVOKE_ACCESS_TOKEN,
                      new AbstractBootstrapWebPageActionHandler <IUserToken, WPECTYPE> (true)
                      {
                        @Override
                        public boolean canHandleAction (@Nonnull final WPECTYPE aWPEC,
                                                        @Nonnull final IUserToken aSelectedObject)
                        {
                          return canRevokeAccessToken (aSelectedObject);
                        }

                        @Nonnull
                        public EShowList handleAction (@Nonnull final WPECTYPE aWPEC,
                                                       @Nonnull final IUserToken aSelectedObject)
                        {
                          final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
                          final HCNodeList aNodeList = aWPEC.getNodeList ();

                          final FormErrorList aFormErrors = new FormErrorList ();
                          if (aWPEC.hasSubAction (CPageParam.ACTION_PERFORM))
                          {
                            final String sRevocationReason = aWPEC.params ().getAsString (FIELD_REVOCATION_REASON);
                            if (StringHelper.isEmpty (sRevocationReason))
                              aFormErrors.addFieldError (FIELD_REVOCATION_REASON,
                                                         EBaseText.ERR_REASON_EMPTY.getDisplayText (aDisplayLocale));

                            if (aFormErrors.isEmpty ())
                            {
                              final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
                              aUserTokenMgr.revokeAccessToken (aSelectedObject.getID (),
                                                               LoggedInUserManager.getInstance ().getCurrentUserID (),
                                                               PDTFactory.getCurrentLocalDateTime (),
                                                               sRevocationReason);
                              aWPEC.postRedirectGetInternal (success (EBaseText.REVOKE_ACCESS_TOKEN_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                    aSelectedObject.getDisplayName ())));
                              return EShowList.DONT_SHOW_LIST;
                            }
                            aNodeList.addChild (getUIHandler ().createIncorrectInputBox (aWPEC));
                          }

                          final BootstrapForm aForm = getUIHandler ().createFormSelf (aWPEC);
                          aForm.addChild (getUIHandler ().createActionHeader (EBaseText.REVOKE_ACCESS_TOKEN_HEADER.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                           aSelectedObject.getDisplayName ())));
                          aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EBaseText.LABEL_REASON.getDisplayText (aDisplayLocale))
                                                                       .setCtrl (new HCTextArea (new RequestField (FIELD_REVOCATION_REASON)))
                                                                       .setErrorList (aFormErrors.getListOfField (FIELD_REVOCATION_REASON)));

                          final BootstrapButtonToolbar aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
                          aToolbar.addHiddenField (CPageParam.PARAM_ACTION, ACTION_REVOKE_ACCESS_TOKEN);
                          aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_PERFORM);
                          aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
                          aToolbar.addSubmitButton (EPhotonCoreText.BUTTON_SAVE.getDisplayText (aDisplayLocale),
                                                    EDefaultIcon.SAVE);
                          aToolbar.addButtonCancel (aDisplayLocale);
                          aNodeList.addChild (aForm);
                          return EShowList.DONT_SHOW_LIST;
                        }
                      });
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_USER_TOKENS.getAsMLT ());
    _init ();
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _init ();
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final String sName,
                                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _init ();
  }

  public BasePageSecurityUserTokenManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final IMultilingualText aName,
                                              @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _init ();
  }

  @Override
  protected IValidityIndicator isValidToDisplayPage (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    if (!aUserMgr.containsAnyActiveUser ())
    {
      aNodeList.addChild (error (EText.ERROR_NO_USER.getDisplayText (aDisplayLocale)));
      aNodeList.addChild (div (new BootstrapButton ().addChild (EText.BUTTON_CREATE_NEW_USER.getDisplayText (aDisplayLocale))
                                                     .setOnClick (aWPEC.getLinkToMenuItem (BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER))));
      return EValidity.INVALID;
    }

    return super.isValidToDisplayPage (aWPEC);
  }

  @Override
  @Nullable
  protected IUserToken getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
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
  public static IHCNode createUserLink (@Nonnull final IWebPageExecutionContext aWPEC, @Nonnull final IUser aUser)
  {
    if (aWPEC.getMenuTree ().containsItemWithID (BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER))
      return new HCA (createViewURL (aWPEC,
                                     BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER,
                                     aUser)).addChild (aUser.getDisplayName ());
    return new HCTextNode (aUser.getDisplayName ());
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserToken aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.HEADER_SHOW.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                      aSelectedObject.getDisplayName ())));

    final BootstrapViewForm aViewForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());

    onShowSelectedObjectTableStart (aWPEC, aViewForm, aSelectedObject);

    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USER.getDisplayText (aDisplayLocale))
                                                     .setCtrl (createUserLink (aWPEC, aSelectedObject.getUser ())));

    {
      final IHCNode aAT = createAccessTokenListUI (aSelectedObject.getAccessTokenList ().getAllAccessTokens (),
                                                   aDisplayLocale);
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EBaseText.LABEL_ACCESS_TOKENS.getDisplayText (aDisplayLocale))
                                                       .setCtrl (aAT));
    }

    // custom attributes
    final ICommonsMap <String, String> aCustomAttrs = aSelectedObject.attrs ();

    // Callback for custom attributes
    final ICommonsSet <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC,
                                                                                aSelectedObject,
                                                                                aCustomAttrs,
                                                                                aViewForm);

    if (aCustomAttrs.isNotEmpty ())
    {
      // Show remaining custom attributes
      final BootstrapTable aAttrTable = new BootstrapTable (new HCCol (170), HCCol.star ());
      aAttrTable.addHeaderRow ()
                .addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
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
        aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ATTRIBUTES.getDisplayText (aDisplayLocale))
                                                         .setCtrl (aAttrTable));
    }

    // Callback
    onShowSelectedObjectTableEnd (aWPEC, aViewForm, aSelectedObject);
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IUserToken aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                final boolean bIsFormSubmitted,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrorList aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final boolean bEdit = eFormAction.isEdit ();

    aForm.addChild (getUIHandler ().createActionHeader (bEdit ? EText.HEADER_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                          aSelectedObject.getDisplayName ())
                                                              : EText.HEADER_CREATE.getDisplayText (aDisplayLocale)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_USER.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCUserSelect (new RequestField (FIELD_USER,
                                                                                               aSelectedObject == null ? null
                                                                                                                       : aSelectedObject.getUserID ()),
                                                                             aDisplayLocale,
                                                                             x -> x.isNotDeleted () && x.isEnabled ())
                                                                                                                      .setReadOnly (bEdit))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_USER)));

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EBaseText.LABEL_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setCtrl (new HCEdit (new RequestField (FIELD_TOKEN_STRING,
                                                                                         aSelectedObject == null ? null
                                                                                                                 : aSelectedObject.getAccessTokenList ()
                                                                                                                                  .getActiveTokenString ())).setReadOnly (bEdit))
                                                 .setHelpText (EBaseText.HELPTEXT_TOKEN_STRING.getDisplayText (aDisplayLocale))
                                                 .setErrorList (aFormErrors.getListOfField (FIELD_TOKEN_STRING)));

    // Custom overridable
    onShowInputFormEnd (aWPEC, aSelectedObject, aForm, eFormAction, aFormErrors);
  }

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IUserToken aSelectedObject,
                                                 @Nonnull final FormErrorList aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    final boolean bEdit = eFormAction.isEdit ();

    final String sUserID = aWPEC.params ().getAsString (FIELD_USER);
    final IUser aUser = bEdit ? aSelectedObject.getUser () : aUserMgr.getActiveUserOfID (sUserID);
    // Token string cannot be edited
    final String sTokenString = bEdit ? null : aWPEC.params ().getAsString (FIELD_TOKEN_STRING);

    if (aUser == null)
      aFormErrors.addFieldError (FIELD_USER, EText.ERR_USER_EMPTY.getDisplayText (aDisplayLocale));

    if (StringHelper.isNotEmpty (sTokenString))
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
    final ICommonsMap <String, String> aCustomAttrMap = validateCustomInputParameters (aWPEC,
                                                                                       aSelectedObject,
                                                                                       aFormErrors,
                                                                                       eFormAction);

    if (aFormErrors.isEmpty ())
    {
      // TODO make editable
      final String sDescription = null;
      if (bEdit)
      {
        aUserTokenMgr.updateUserToken (aSelectedObject.getID (), aCustomAttrMap, sDescription);
        aWPEC.postRedirectGetInternal (success (EText.EDIT_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                           aUser.getDisplayName ())));
      }
      else
      {
        if (aUserTokenMgr.createUserToken (sTokenString, aCustomAttrMap, aUser, sDescription) != null)
        {
          aWPEC.postRedirectGetInternal (success (EText.CREATE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               aUser.getDisplayName ())));
        }
        else
        {
          aWPEC.postRedirectGetInternal (error (EText.CREATE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                           aUser.getDisplayName ())));
        }
      }
    }
  }

  @Nonnull
  private IHCNode _createList (@Nonnull final WPECTYPE aWPEC,
                               @Nonnull final String sIDSuffix,
                               @Nonnull final ICommonsList <IUserToken> aUserTokens,
                               @Nullable final Predicate <? super IUserToken> aFilter)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final HCTable aTable = new HCTable (new DTCol (EText.HEADER_USER.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_USABLE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID () + sIDSuffix);
    for (final IUserToken aCurObject : aUserTokens)
      if (aFilter == null || aFilter.test (aCurObject))
      {
        final ISimpleURL aViewURL = createViewURL (aWPEC, aCurObject);
        final String sDisplayName = aCurObject.getDisplayName ();
        final boolean bUsableNow = !aCurObject.isDeleted () &&
                                   aCurObject.getAccessTokenList ().hasActiveAccessToken () &&
                                   aCurObject.getAccessTokenList ().getActiveAccessToken ().isValidNow ();

        final HCRow aBodyRow = aTable.addBodyRow ();
        aBodyRow.addCell (new HCA (aViewURL).addChild (sDisplayName));
        aBodyRow.addCell (EPhotonCoreText.getYesOrNo (bUsableNow, aDisplayLocale));

        final IHCCell <?> aActionCell = aBodyRow.addCell ();

        // Edit
        if (isActionAllowed (aWPEC, EWebPageFormAction.EDIT, aCurObject))
          aActionCell.addChild (createEditLink (aWPEC,
                                                aCurObject,
                                                EText.ACTION_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                          sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());

        // Copy
        aActionCell.addChild (" ");
        aActionCell.addChild (createCopyLink (aWPEC,
                                              aCurObject,
                                              EText.ACTION_COPY.getDisplayTextWithArgs (aDisplayLocale, sDisplayName)));

        // Delete
        aActionCell.addChild (" ");
        if (isActionAllowed (aWPEC, EWebPageFormAction.DELETE, aCurObject))
          aActionCell.addChild (createDeleteLink (aWPEC,
                                                  aCurObject,
                                                  EText.ACTION_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                              sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());

        // Renew
        aActionCell.addChild (" ");
        if (canCreateNewAccessToken (aCurObject))
          aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                              .add (CPageParam.PARAM_ACTION, ACTION_CREATE_NEW_ACCESS_TOKEN)
                                              .add (CPageParam.PARAM_OBJECT, aCurObject.getID ()))
                                                                                                  .addChild (EDefaultIcon.REFRESH.getAsNode ())
                                                                                                  .setTitle (EBaseText.TITLE_ACTION_CREATE_NEW_ACCESS_TOKEN.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                                                                                    sDisplayName)));
        else
          aActionCell.addChild (createEmptyAction ());

        // Revoke
        aActionCell.addChild (" ");
        if (canRevokeAccessToken (aCurObject))
          aActionCell.addChild (new HCA (aWPEC.getSelfHref ()
                                              .add (CPageParam.PARAM_ACTION, ACTION_REVOKE_ACCESS_TOKEN)
                                              .add (CPageParam.PARAM_OBJECT, aCurObject.getID ()))
                                                                                                  .addChild (EDefaultIcon.CANCEL.getAsNode ())
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

    // Query only once
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    final ICommonsList <IUserToken> aUserTokens = aUserTokenMgr.getAll ();

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();
    aTabBox.addTab ("active",
                    EText.TAB_LABEL_ACTIVE.getDisplayText (aDisplayLocale),
                    _createList (aWPEC, "active", aUserTokens, IUserToken::isNotDeleted));
    aTabBox.addTab ("deleted",
                    EText.TAB_LABEL_DELETED.getDisplayText (aDisplayLocale),
                    _createList (aWPEC, "deleted", aUserTokens, IUserToken::isDeleted));
    aNodeList.addChild (aTabBox);
  }
}
