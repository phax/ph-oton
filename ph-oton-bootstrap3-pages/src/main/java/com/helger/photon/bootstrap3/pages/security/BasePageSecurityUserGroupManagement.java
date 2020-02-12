/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.attr.IStringMap;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsCollection;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.name.IHasName;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCExtHelper;
import com.helger.html.hc.html.forms.HCEdit;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.tabular.HCCol;
import com.helger.html.hc.html.tabular.HCRow;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.html.textlevel.HCEM;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.nav.BootstrapTabBox;
import com.helger.photon.bootstrap3.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandlerDelete;
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandlerUndelete;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormErrorList;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.role.IRole;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.UserManager;
import com.helger.photon.security.usergroup.IUserGroup;
import com.helger.photon.security.usergroup.UserGroupManager;
import com.helger.photon.uicore.html.select.HCRoleForUserGroupSelect;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.autosize.HCTextAreaAutosize;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;

public class BasePageSecurityUserGroupManagement <WPECTYPE extends IWebPageExecutionContext> extends
                                                 AbstractWebPageSecurityObjectWithAttributes <IUserGroup, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayTextWithArgs
  {
    BUTTON_CREATE_NEW_USERGROUP ("Neue Benutzergruppe anlegen", "Create new user group"),
    TAB_ACTIVE ("Aktive Benutzergruppen ({0})", "Active user groups ({0})"),
    TAB_DELETED ("Gelöschte Benutzergruppen ({0})", "Deleted user groups ({0})"),
    HEADER_NAME ("Name", "Name"),
    HEADER_IN_USE ("Verwendet?", "In use?"),
    HEADER_VALUE ("Wert", "Value"),
    HEADER_DETAILS ("Details von Benutzergruppe {0}", "Details of user group {0}"),
    LABEL_NAME ("Name", "Name"),
    LABEL_DESCRIPTION ("Beschreibung", "Description"),
    LABEL_USERS_0 ("Benutzer", "Users"),
    LABEL_USERS_N ("Benutzer ({0})", "Users ({0})"),
    LABEL_ROLES_0 ("Rollen", "Roles"),
    LABEL_ROLES_N ("Rollen ({0})", "Roles ({0})"),
    LABEL_ATTRIBUTES ("Attribute", "Attributes"),
    NONE_ASSIGNED ("keine zugeordnet", "none assigned"),
    TITLE_CREATE ("Neue Benutzergruppe anlegen", "Create new user group"),
    TITLE_EDIT ("Benutzergruppe ''{0}'' bearbeiten", "Edit user group ''{0}''"),
    ERROR_NAME_REQUIRED ("Es muss ein Name angegeben werden!", "A name must be specified!"),
    ERROR_NO_ROLE ("Es muss mindestens eine Rolle ausgewählt werden!", "At least one role must be selected!"),
    ERROR_INVALID_ROLES ("Mindestens eine der angegebenen Rolle ist ungültig!",
                         "At least one selected role is invalid!"),
    DELETE_QUERY ("Soll die Benutzergruppe ''{0}'' wirklich gelöscht werden?",
                  "Are you sure to delete the user group ''{0}''?"),
    DELETE_SUCCESS ("Die Benutzergruppe ''{0}'' wurden erfolgreich gelöscht!",
                    "The user group ''{0}'' was successfully deleted!"),
    DELETE_ERROR ("Fehler beim Löschen der Benutzergruppe ''{0}''!", "Error deleting the user group ''{0}''!"),
    UNDELETE_QUERY ("Sind Sie sicher, dass Sie die Benutzergruppe ''{0}'' wiederherstellen wollen?",
                    "Are you sure you want to undelete user group ''{0}''?"),
    UNDELETE_SUCCESS ("Die Benutzergruppe ''{0}'' wurde erfolgreich wiederhergestellt!",
                      "User group ''{0}'' was successfully undeleted!"),
    UNDELETE_ERROR ("Beim Wiederherstellen der Benutzergruppe ''{0}'' ist ein Fehler aufgetreten!",
                    "An error occurred while undeleting user group ''{0}''!"),
    SUCCESS_CREATE ("Die neue BenutzerGruppe wurde erfolgreich angelegt!", "Successfully created the new user group!"),
    SUCCESS_EDIT ("Die Benutzergruppe wurde erfolgreich bearbeitet!", "Sucessfully edited the user group!");

    private final IMultilingualText m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextHelper.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
    }
  }

  public static final String FIELD_NAME = "name";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_ROLES = "roles";

  private void _init ()
  {
    setDeleteHandler (new AbstractBootstrapWebPageActionHandlerDelete <IUserGroup, WPECTYPE> ()
    {
      @Override
      protected void showQuery (@Nonnull final WPECTYPE aWPEC,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final IUserGroup aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         aSelectedObject.getName ())));
      }

      @Override
      protected void performAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

        if (aUserGroupMgr.deleteUserGroup (aSelectedObject.getID ()).isChanged ())
          aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                           aSelectedObject.getName ())));
        else
          aWPEC.postRedirectGetInternal (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                       aSelectedObject.getName ())));
      }
    });
    setUndeleteHandler (new AbstractBootstrapWebPageActionHandlerUndelete <IUserGroup, WPECTYPE> ()
    {
      @Override
      protected void showQuery (@Nonnull final WPECTYPE aWPEC,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final IUserGroup aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.UNDELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                           aSelectedObject.getName ())));
      }

      @Override
      protected void performAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

        if (aUserGroupMgr.undeleteUserGroup (aSelectedObject.getID ()).isChanged ())
          aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.UNDELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                             aSelectedObject.getName ())));
        else
          aWPEC.postRedirectGetInternal (new BootstrapErrorBox ().addChild (EText.UNDELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                         aSelectedObject.getName ())));
      }
    });
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_USER_GROUPS.getAsMLT ());
    _init ();
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _init ();
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final String sName,
                                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _init ();
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final IMultilingualText aName,
                                              @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _init ();
  }

  @Override
  @Nullable
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
  {
    return aSelectedObject.getName ();
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final IUserGroup aSelectedObject)
  {
    if (eFormAction.isDelete ())
      return canDeleteUserGroup (aSelectedObject);
    if (eFormAction.isUndelete ())
      return canUndeleteUserGroup (aSelectedObject);
    return true;
  }

  @Override
  @Nullable
  protected IUserGroup getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();
    return aUserGroupMgr.getUserGroupOfID (sID);
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         aSelectedObject.getName ())));
    final BootstrapViewForm aViewForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    aViewForm.setCondensed (true);
    onShowSelectedObjectTableStart (aWPEC, aViewForm, aSelectedObject);

    aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_NAME.getDisplayText (aDisplayLocale))
                                                     .setCtrl (aSelectedObject.getName ()));

    if (aSelectedObject.hasDescription ())
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale))
                                                       .setCtrl (HCExtHelper.nl2divList (aSelectedObject.getDescription ())));

    // All users assigned to this user group
    final ICommonsSet <String> aAssignedUserIDs = aSelectedObject.getAllContainedUserIDs ();
    if (aAssignedUserIDs.isEmpty ())
    {
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERS_0.getDisplayText (aDisplayLocale))
                                                       .setCtrl (new HCEM ().addChild (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      // Convert IDs to objects
      final UserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
      final ICommonsList <IUser> aAssignedUsers = new CommonsArrayList <> (aAssignedUserIDs,
                                                                           sUserID -> aUserMgr.getUserOfID (sUserID));

      final HCNodeList aUserUI = new HCNodeList ();
      aAssignedUsers.getSortedInline (IHasDisplayName.getComparatorCollating (aDisplayLocale))
                    .forEach (aUser -> aUserUI.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                        BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER,
                                                                                                        aUser.getID (),
                                                                                                        null)).addChild (aUser.getDisplayName ()))));

      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                              Integer.toString (aAssignedUserIDs.size ())))
                                                       .setCtrl (aUserUI));
    }

    // All roles assigned to this user group
    final ICommonsSet <String> aAssignedRoleIDs = aSelectedObject.getAllContainedRoleIDs ();
    if (aAssignedRoleIDs.isEmpty ())
    {
      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_0.getDisplayText (aDisplayLocale))
                                                       .setCtrl (new HCEM ().addChild (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      // Convert IDs to objects
      final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();
      final ICommonsList <IRole> aAssignedRoles = new CommonsArrayList <> (aAssignedRoleIDs,
                                                                           sRoleID -> aRoleMgr.getRoleOfID (sRoleID));

      final HCNodeList aRoleUI = new HCNodeList ();
      aAssignedRoles.getSortedInline (IHasName.getComparatorCollating (aDisplayLocale))
                    .forEach (aRole -> aRoleUI.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                        BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_ROLE,
                                                                                                        aRole.getID (),
                                                                                                        null)).addChild (aRole.getName ()))));

      aViewForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                              Integer.toString (aAssignedRoleIDs.size ())))
                                                       .setCtrl (aRoleUI));
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
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IUserGroup aSelectedObject,
                                                 @Nonnull final FormErrorList aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final String sName = aWPEC.params ().getAsString (FIELD_NAME);
    final String sDescription = aWPEC.params ().getAsString (FIELD_DESCRIPTION);
    final ICommonsCollection <String> aRoleIDs = aWPEC.params ().getAsStringList (FIELD_ROLES);
    final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    if (StringHelper.hasNoText (sName))
      aFormErrors.addFieldError (FIELD_NAME, EText.ERROR_NAME_REQUIRED.getDisplayText (aDisplayLocale));

    if (CollectionHelper.isEmpty (aRoleIDs))
      aFormErrors.addFieldError (FIELD_ROLES, EText.ERROR_NO_ROLE.getDisplayText (aDisplayLocale));
    else
      if (!aRoleMgr.containsAllIDs (aRoleIDs))
        aFormErrors.addFieldError (FIELD_ROLES, EText.ERROR_INVALID_ROLES.getDisplayText (aDisplayLocale));

    // Call custom method
    final ICommonsMap <String, String> aCustomAttrMap = validateCustomInputParameters (aWPEC,
                                                                                       aSelectedObject,
                                                                                       aFormErrors,
                                                                                       eFormAction);

    if (aFormErrors.isEmpty ())
    {
      // All fields are valid -> save
      if (eFormAction.isEdit ())
      {
        final String sUserGroupID = aSelectedObject.getID ();

        final IStringMap aAttrMap = aSelectedObject.attrs ();
        if (aCustomAttrMap != null)
          aAttrMap.putAll (aCustomAttrMap);

        // We're editing an existing object
        aUserGroupMgr.setUserGroupData (sUserGroupID, sName, sDescription, aAttrMap);

        // assign to the matching roles
        final ICommonsSet <String> aPrevRoleIDs = aSelectedObject.getAllContainedRoleIDs ();
        // Create all missing assignments
        final ICommonsSet <String> aRolesToBeAssigned = CollectionHelper.getDifference (aRoleIDs, aPrevRoleIDs);
        for (final String sRoleID : aRolesToBeAssigned)
          aUserGroupMgr.assignRoleToUserGroup (sUserGroupID, sRoleID);

        // Delete all old assignments
        final ICommonsSet <String> aRolesToBeUnassigned = CollectionHelper.getDifference (aPrevRoleIDs, aRoleIDs);
        for (final String sRoleID : aRolesToBeUnassigned)
          aUserGroupMgr.unassignRoleFromUserGroup (sUserGroupID, sRoleID);

        aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.SUCCESS_EDIT.getDisplayText (aDisplayLocale)));
      }
      else
      {
        // We're creating a new object
        final IUserGroup aNewUserGroup = aUserGroupMgr.createNewUserGroup (sName, sDescription, aCustomAttrMap);

        // assign to the matching internal user groups
        for (final String sRoleID : aRoleIDs)
          aUserGroupMgr.assignRoleToUserGroup (aNewUserGroup.getID (), sRoleID);

        aWPEC.postRedirectGetInternal (new BootstrapSuccessBox ().addChild (EText.SUCCESS_CREATE.getDisplayText (aDisplayLocale)));
      }
    }
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IUserGroup aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                final boolean bIsFormSubmitted,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrorList aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (getUIHandler ().createActionHeader (eFormAction.isEdit () ? EText.TITLE_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                         aSelectedObject.getName ())
                                                                              : EText.TITLE_CREATE.getDisplayText (aDisplayLocale)));

    // Name
    {
      final String sName = EText.LABEL_NAME.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sName)
                                                   .setCtrl (new HCEdit (new RequestField (FIELD_NAME,
                                                                                           aSelectedObject == null ? null
                                                                                                                   : aSelectedObject.getName ())).setPlaceholder (sName))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_NAME)));
    }

    // Description
    {
      final String sDescription = EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sDescription)
                                                   .setCtrl (new HCTextAreaAutosize (new RequestField (FIELD_DESCRIPTION,
                                                                                                       aSelectedObject == null ? null
                                                                                                                               : aSelectedObject.getDescription ())).setPlaceholder (sDescription))
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_DESCRIPTION)));
    }

    // Role assignment
    {
      final ICommonsCollection <String> aRoleIDs = aSelectedObject == null ? aWPEC.params ()
                                                                                  .getAsStringList (FIELD_ROLES)
                                                                           : aSelectedObject.getAllContainedRoleIDs ();
      final HCRoleForUserGroupSelect aSelect = new HCRoleForUserGroupSelect (new RequestField (FIELD_ROLES), aRoleIDs);
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (EText.LABEL_ROLES_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (aSelect)
                                                   .setErrorList (aFormErrors.getListOfField (FIELD_ROLES)));
    }

    // Custom overridable
    onShowInputFormEnd (aWPEC, aSelectedObject, aForm, eFormAction, aFormErrors);
  }

  protected static boolean canDeleteUserGroup (@Nullable final IUserGroup aUserGroup)
  {
    return aUserGroup != null &&
           !aUserGroup.isDeleted () &&
           !aUserGroup.hasContainedUsers () &&
           !aUserGroup.getID ().equals (CSecurity.USERGROUP_ADMINISTRATORS_ID);
  }

  protected static boolean canUndeleteUserGroup (@Nullable final IUserGroup aUserGroup)
  {
    return aUserGroup != null && aUserGroup.isDeleted ();
  }

  @Nonnull
  protected IHCNode getTabWithUserGroups (@Nonnull final WPECTYPE aWPEC,
                                          @Nonnull final ICommonsList <IUserGroup> aUserGroups,
                                          @Nonnull @Nonempty final String sTableID)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final HCTable aTable = new HCTable (new DTCol ().setVisible (false),
                                        new DTCol (EText.HEADER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_IN_USE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (sTableID);
    for (final IUserGroup aUserGroup : aUserGroups)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aUserGroup);

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aUserGroup.getID ());
      aRow.addCell (new HCA (aViewLink).addChild (aUserGroup.getName ()));
      aRow.addCell (EPhotonCoreText.getYesOrNo (aUserGroup.hasContainedUsers (), aDisplayLocale));

      final IHCCell <?> aActionCell = aRow.addCell ();
      aActionCell.addChild (createEditLink (aWPEC,
                                            aUserGroup,
                                            EWebPageText.OBJECT_EDIT.getDisplayTextWithArgs (aDisplayLocale,
                                                                                             aUserGroup.getName ())));

      aActionCell.addChild (" ");
      if (canDeleteUserGroup (aUserGroup))
      {
        aActionCell.addChild (createDeleteLink (aWPEC,
                                                aUserGroup,
                                                EWebPageText.OBJECT_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                   aUserGroup.getName ())));
      }
      else
      {
        aActionCell.addChild (createEmptyAction ());
      }
      aActionCell.addChild (" ");
      if (canUndeleteUserGroup (aUserGroup))
      {
        aActionCell.addChild (createUndeleteLink (aWPEC,
                                                  aUserGroup,
                                                  EWebPageText.OBJECT_UNDELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                       aUserGroup.getName ())));
      }
      else
      {
        aActionCell.addChild (createEmptyAction ());
      }
    }

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);

    return new HCNodeList ().addChild (aTable).addChild (aDataTables);
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();

    // Toolbar on top
    final BootstrapButtonToolbar aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW_USERGROUP.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));

    final BootstrapTabBox aTabBox = new BootstrapTabBox ();

    final ICommonsList <IUserGroup> aActiveUserGroups = aUserGroupMgr.getAllActiveUserGroups ();
    aTabBox.addTab ("active",
                    EText.TAB_ACTIVE.getDisplayTextWithArgs (aDisplayLocale,
                                                             Integer.toString (aActiveUserGroups.size ())),
                    getTabWithUserGroups (aWPEC, aActiveUserGroups, getID () + "1"));

    final ICommonsList <IUserGroup> aDeletedUserGroups = aUserGroupMgr.getAllDeletedUserGroups ();
    aTabBox.addTab ("deleted",
                    EText.TAB_DELETED.getDisplayTextWithArgs (aDisplayLocale,
                                                              Integer.toString (aDeletedUserGroups.size ())),
                    getTabWithUserGroups (aWPEC, aDeletedUserGroups, getID () + "2"));
    aNodeList.addChild (aTabBox);
  }
}
