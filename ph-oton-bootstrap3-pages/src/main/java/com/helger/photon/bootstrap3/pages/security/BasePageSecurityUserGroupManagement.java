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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.Translatable;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.name.ComparatorHasName;
import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.name.IHasDisplayTextWithArgs;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.commons.text.impl.TextProvider;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCEM;
import com.helger.html.hc.html.HCEdit;
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.basic.security.AccessManager;
import com.helger.photon.basic.security.CSecurity;
import com.helger.photon.basic.security.role.IRole;
import com.helger.photon.basic.security.user.IUser;
import com.helger.photon.basic.security.usergroup.IUserGroup;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.RequestField;
import com.helger.photon.uicore.html.select.HCRoleForUserGroupSelect;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.autosize.HCTextAreaAutosize;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.validation.error.FormErrors;

public class BasePageSecurityUserGroupManagement <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageSecurityObjectWithAttributes <IUserGroup, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
  {
    BUTTON_CREATE_NEW_USERGROUP ("Neue Benutzergruppe anlegen", "Create new user group"),
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
    ERROR_INVALID_ROLES ("Mindestens eine der angegebenen Rolle ist ungültig!", "At least one selected role is invalid!"),
    DELETE_QUERY ("Soll die Benutzergruppe ''{0}'' wirklich gelöscht werden?", "Are you sure to delete the user group ''{0}''?"),
    DELETE_SUCCESS ("Die Benutzergruppe ''{0}'' wurden erfolgreich gelöscht!", "The user group ''{0}'' was successfully deleted!"),
    DELETE_ERROR ("Fehler beim Löschen der Benutzergruppe ''{0}''!", "Error deleting the user group ''{0}''!"),
    SUCCESS_CREATE ("Die neue BenutzerGruppe wurde erfolgreich angelegt!", "Successfully created the new user group!"),
    SUCCESS_EDIT ("Die Benutzergruppe wurde erfolgreich bearbeitet!", "Sucessfully edited the user group!");

    private final TextProvider m_aTP;

    private EText (@Nonnull final String sDE, @Nonnull final String sEN)
    {
      m_aTP = TextProvider.create_DE_EN (sDE, sEN);
    }

    @Nullable
    public String getDisplayText (@Nonnull final Locale aContentLocale)
    {
      return DefaultTextResolver.getText (this, m_aTP, aContentLocale);
    }

    @Nullable
    public String getDisplayTextWithArgs (@Nonnull final Locale aContentLocale, @Nullable final Object... aArgs)
    {
      return DefaultTextResolver.getTextWithArgs (this, m_aTP, aContentLocale, aArgs);
    }
  }

  public static final String FIELD_NAME = "name";
  public static final String FIELD_DESCRIPTION = "description";
  public static final String FIELD_ROLES = "roles";

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_USER_GROUPS.getAsMLT ());
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final String sName,
                                              @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSecurityUserGroupManagement (@Nonnull @Nonempty final String sID,
                                              @Nonnull final IReadonlyMultiLingualText aName,
                                              @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
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
    return true;
  }

  @Override
  @Nullable
  protected IUserGroup getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return AccessManager.getInstance ().getUserGroupOfID (sID);
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (createActionHeader (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                         aSelectedObject.getName ())));
    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    onShowSelectedObjectTableStart (aWPEC, aForm, aSelectedObject);

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getName ()));

    if (StringHelper.hasText (aSelectedObject.getDescription ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCUtils.nl2divList (aSelectedObject.getDescription ())));

    // All users assigned to this user group
    final Collection <String> aAssignedUserIDs = aSelectedObject.getAllContainedUserIDs ();
    if (aAssignedUserIDs.isEmpty ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERS_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCEM.create (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      // Convert IDs to objects
      final AccessManager aMgr = AccessManager.getInstance ();
      final List <IUser> aAssignedUsers = new ArrayList <IUser> (aAssignedUserIDs.size ());
      for (final String sUserID : aAssignedUserIDs)
        aAssignedUsers.add (aMgr.getUserOfID (sUserID));

      final HCNodeList aUserUI = new HCNodeList ();
      for (final IUser aUser : CollectionHelper.getSorted (aAssignedUsers,
                                                           new ComparatorHasName <IUser> (aDisplayLocale)))
        aUserUI.addChild (HCDiv.create (aUser.getName ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                          Integer.toString (aAssignedUserIDs.size ())))
                                                   .setCtrl (aUserUI));
    }

    // All roles assigned to this user group
    final Collection <String> aAssignedRoleIDs = aSelectedObject.getAllContainedRoleIDs ();
    if (aAssignedRoleIDs.isEmpty ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCEM.create (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      // Convert IDs to objects
      final AccessManager aMgr = AccessManager.getInstance ();
      final List <IRole> aAssignedRoles = new ArrayList <IRole> (aAssignedRoleIDs.size ());
      for (final String sRoleID : aAssignedRoleIDs)
        aAssignedRoles.add (aMgr.getRoleOfID (sRoleID));

      final HCNodeList aRoleUI = new HCNodeList ();
      for (final IRole aRole : CollectionHelper.getSorted (aAssignedRoles,
                                                           new ComparatorHasName <IRole> (aDisplayLocale)))
        aRoleUI.addChild (HCDiv.create (aRole.getName ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_ROLES_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                          Integer.toString (aAssignedRoleIDs.size ())))
                                                   .setCtrl (aRoleUI));
    }

    // custom attributes
    final Map <String, Object> aCustomAttrs = aSelectedObject.getAllAttributes ();

    // Callback for custom attributes
    final Set <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC, aSelectedObject, aCustomAttrs, aForm);

    if (!aCustomAttrs.isEmpty ())
    {
      // Show remaining custom attributes
      final IHCTable <?> aAttrTable = new BootstrapTable (new HCCol (170), HCCol.star ());
      aAttrTable.addHeaderRow ().addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
                                           EText.HEADER_VALUE.getDisplayText (aDisplayLocale));
      for (final Map.Entry <String, Object> aEntry : aCustomAttrs.entrySet ())
      {
        final String sName = aEntry.getKey ();
        if (aHandledAttrs == null || !aHandledAttrs.contains (sName))
        {
          final String sValue = String.valueOf (aEntry.getValue ());
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
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IUserGroup aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final AccessManager aAccessMgr = AccessManager.getInstance ();
    final String sName = aWPEC.getAttributeAsString (FIELD_NAME);
    final String sDescription = aWPEC.getAttributeAsString (FIELD_DESCRIPTION);
    final Collection <String> aRoleIDs = aWPEC.getAttributeAsList (FIELD_ROLES);

    if (StringHelper.hasNoText (sName))
      aFormErrors.addFieldError (FIELD_NAME, EText.ERROR_NAME_REQUIRED.getDisplayText (aDisplayLocale));

    if (CollectionHelper.isEmpty (aRoleIDs))
      aFormErrors.addFieldError (FIELD_ROLES, EText.ERROR_NO_ROLE.getDisplayText (aDisplayLocale));
    else
      if (!aAccessMgr.containsAllRolesWithID (aRoleIDs))
        aFormErrors.addFieldError (FIELD_ROLES, EText.ERROR_INVALID_ROLES.getDisplayText (aDisplayLocale));

    // Call custom method
    final Map <String, String> aCustomAttrMap = validateCustomInputParameters (aWPEC,
                                                                               aSelectedObject,
                                                                               aFormErrors,
                                                                               eFormAction);

    if (aFormErrors.isEmpty ())
    {
      // All fields are valid -> save
      if (eFormAction.isEdit ())
      {
        final String sUserGroupID = aSelectedObject.getID ();

        final Map <String, Object> aAttrMap = aSelectedObject.getAllAttributes ();
        if (aCustomAttrMap != null)
          aAttrMap.putAll (aCustomAttrMap);

        // We're editing an existing object
        aAccessMgr.setUserGroupData (sUserGroupID, sName, sDescription, aAttrMap);
        aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_EDIT.getDisplayText (aDisplayLocale)));

        // assign to the matching roles
        final Collection <String> aPrevRoleIDs = aSelectedObject.getAllContainedRoleIDs ();
        // Create all missing assignments
        final Set <String> aRolesToBeAssigned = CollectionHelper.getDifference (aRoleIDs, aPrevRoleIDs);
        for (final String sRoleID : aRolesToBeAssigned)
          aAccessMgr.assignRoleToUserGroup (sUserGroupID, sRoleID);

        // Delete all old assignments
        final Set <String> aRolesToBeUnassigned = CollectionHelper.getDifference (aPrevRoleIDs, aRoleIDs);
        for (final String sRoleID : aRolesToBeUnassigned)
          aAccessMgr.unassignRoleFromUserGroup (sUserGroupID, sRoleID);
      }
      else
      {
        // We're creating a new object
        final IUserGroup aNewUserGroup = aAccessMgr.createNewUserGroup (sName, sDescription, aCustomAttrMap);
        aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.SUCCESS_CREATE.getDisplayText (aDisplayLocale)));

        // assign to the matching internal user groups
        for (final String sRoleID : aRoleIDs)
          aAccessMgr.assignRoleToUserGroup (aNewUserGroup.getID (), sRoleID);
      }
    }
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IUserGroup aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (createActionHeader (eFormAction.isEdit () ? EText.TITLE_EDIT.getDisplayTextWithArgs (aDisplayLocale,
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
      final Collection <String> aRoleIDs = aSelectedObject == null ? aWPEC.getAttributeAsList (FIELD_ROLES)
                                                                  : aSelectedObject.getAllContainedRoleIDs ();
      final HCRoleForUserGroupSelect aSelect = new HCRoleForUserGroupSelect (new RequestField (FIELD_ROLES),
                                                                             aDisplayLocale,
                                                                             aRoleIDs);
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
           !aUserGroup.hasContainedUsers () &&
           !aUserGroup.getID ().equals (CSecurity.USERGROUP_ADMINISTRATORS_ID);
  }

  @Override
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final AbstractHCForm <?> aForm,
                                  @Nonnull final IUserGroup aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                     aSelectedObject.getName ())));
  }

  @Override
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IUserGroup aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (AccessManager.getInstance ().deleteUserGroup (aSelectedObject.getID ()).isChanged ())
      aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                            aSelectedObject.getName ())));
    else
      aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                        aSelectedObject.getName ())));
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    // Toolbar on top
    final IButtonToolbar <?> aToolbar = aNodeList.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
    aToolbar.addButtonNew (EText.BUTTON_CREATE_NEW_USERGROUP.getDisplayText (aDisplayLocale), createCreateURL (aWPEC));

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), new HCCol (110), createActionCol (2)).setID (getID ());
    aTable.addHeaderRow ().addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
                                     EText.HEADER_IN_USE.getDisplayText (aDisplayLocale),
                                     EPhotonCoreText.ACTIONS.getDisplayText (aDisplayLocale));
    final Collection <? extends IUserGroup> aUserGroups = AccessManager.getInstance ().getAllUserGroups ();
    for (final IUserGroup aUserGroup : aUserGroups)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aUserGroup);

      final HCRow aRow = aTable.addBodyRow ();
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
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aDataTables.getOrCreateColumnOfTarget (2).addClass (CSS_CLASS_ACTION_COL).setSortable (false);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
