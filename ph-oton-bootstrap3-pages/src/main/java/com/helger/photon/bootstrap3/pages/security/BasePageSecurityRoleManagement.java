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

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.errorlist.FormErrors;
import com.helger.commons.name.IHasName;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.ext.HCExtHelper;
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
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.BootstrapFormGroup;
import com.helger.photon.bootstrap3.form.BootstrapViewForm;
import com.helger.photon.bootstrap3.pages.BootstrapPagesMenuConfigurator;
import com.helger.photon.bootstrap3.pages.handler.AbstractBootstrapWebPageActionHandlerDelete;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDTColAction;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.role.IRole;
import com.helger.photon.security.role.RoleManager;
import com.helger.photon.security.usergroup.IUserGroup;
import com.helger.photon.security.usergroup.UserGroupManager;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.column.DTCol;

public class BasePageSecurityRoleManagement <WPECTYPE extends IWebPageExecutionContext>
                                            extends AbstractWebPageSecurityObjectWithAttributes <IRole, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText,IHasDisplayTextWithArgs
  {
    HEADER_NAME ("Name", "Name"),
    HEADER_IN_USE ("Verwendet?", "In use?"),
    HEADER_VALUE ("Wert", "Value"),
    HEADER_DETAILS ("Details von Rolle ''{0}''", "Details of role ''{0}''"),
    LABEL_NAME ("Name", "Name"),
    LABEL_DESCRIPTION ("Beschreibung", "Description"),
    LABEL_USERGROUPS_0 ("Benutzergruppen", "User groups"),
    LABEL_USERGROUPS_N ("Benutzergruppen ({0})", "User groups ({0})"),
    LABEL_ATTRIBUTES ("Attribute", "Attributes"),
    NONE_ASSIGNED ("keine zugeordnet", "none assigned"),
    DELETE_QUERY ("Soll die Rolle ''{0}'' wirklich gelöscht werden?", "Are you sure to delete the role ''{0}''?"),
    DELETE_SUCCESS ("Die Rolle ''{0}'' wurden erfolgreich gelöscht!", "The role ''{0}'' was successfully deleted!"),
    DELETE_ERROR ("Fehler beim Löschen der Rolle ''{0}''!", "Error deleting the role ''{0}''!");

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

  private void _init ()
  {
    setDeleteHandler (new AbstractBootstrapWebPageActionHandlerDelete <IRole, WPECTYPE> ()
    {
      @Override
      protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                      @Nonnull final BootstrapForm aForm,
                                      @Nonnull final IRole aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         aSelectedObject.getName ())));
      }

      @Override
      protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IRole aSelectedObject)
      {
        final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
        final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();

        if (aRoleMgr.deleteRole (aSelectedObject.getID ()).isChanged ())
        {
          aWPEC.postRedirectGet (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                                   aSelectedObject.getName ())));
        }
        else
        {
          aWPEC.postRedirectGet (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               aSelectedObject.getName ())));
        }
      }
    });
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_ROLES.getAsMLT ());
    _init ();
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
    _init ();
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final String sName,
                                         @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
    _init ();
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final IMultilingualText aName,
                                         @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
    _init ();
  }

  @Override
  @Nullable
  protected String getObjectDisplayName (@Nonnull final WPECTYPE aWPEC, @Nonnull final IRole aSelectedObject)
  {
    return aSelectedObject.getName ();
  }

  @Override
  @Nullable
  protected IRole getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();
    return aRoleMgr.getRoleOfID (sID);
  }

  @Override
  protected boolean isActionAllowed (@Nonnull final WPECTYPE aWPEC,
                                     @Nonnull final EWebPageFormAction eFormAction,
                                     @Nullable final IRole aSelectedObject)
  {
    if (eFormAction.isEdit ())
      return false;
    if (eFormAction.isDelete ())
      return canDeleteRole (aSelectedObject);
    return true;
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final IRole aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    aNodeList.addChild (getUIHandler ().createActionHeader (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                         aSelectedObject.getName ())));
    final BootstrapViewForm aForm = aNodeList.addAndReturnChild (new BootstrapViewForm ());
    onShowSelectedObjectTableStart (aWPEC, aForm, aSelectedObject);

    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_NAME.getDisplayText (aDisplayLocale))
                                                 .setCtrl (aSelectedObject.getName ()));

    if (StringHelper.hasText (aSelectedObject.getDescription ()))
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale))
                                                   .setCtrl (HCExtHelper.nl2divList (aSelectedObject.getDescription ())));

    // All user groups to which the role is assigned
    final Collection <? extends IUserGroup> aAssignedUserGroups = PhotonSecurityManager.getUserGroupMgr ()
                                                                                       .getAllUserGroupsWithAssignedRole (aSelectedObject.getID ());
    if (aAssignedUserGroups.isEmpty ())
    {
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERGROUPS_0.getDisplayText (aDisplayLocale))
                                                   .setCtrl (new HCEM ().addChild (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale))));
    }
    else
    {
      final HCNodeList aUserGroupUI = new HCNodeList ();
      CollectionHelper.getSorted (aAssignedUserGroups, IHasName.getComparatorCollating (aDisplayLocale))
                      .forEach (aUserGroup -> aUserGroupUI.addChild (new HCDiv ().addChild (new HCA (createViewURL (aWPEC,
                                                                                                                    BootstrapPagesMenuConfigurator.MENU_ADMIN_SECURITY_USER_GROUP,
                                                                                                                    aUserGroup.getID (),
                                                                                                                    null)).addChild (aUserGroup.getName ()))));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (EText.LABEL_USERGROUPS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                               Integer.toString (aAssignedUserGroups.size ())))
                                                   .setCtrl (aUserGroupUI));
    }

    // custom attributes
    final ICommonsMap <String, String> aCustomAttrs = aSelectedObject.getAllAttributes ();

    // Callback for custom attributes
    final ICommonsSet <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC,
                                                                                aSelectedObject,
                                                                                aCustomAttrs,
                                                                                aForm);

    if (aCustomAttrs.isNotEmpty ())
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
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final IRole aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {
    throw new UnsupportedOperationException ();
  }

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final IRole aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  protected static boolean canDeleteRole (@Nullable final IRole aRole)
  {
    final UserGroupManager aUserGroupMgr = PhotonSecurityManager.getUserGroupMgr ();
    return aRole != null &&
           !aRole.getID ().equals (CSecurity.ROLE_ADMINISTRATOR_ID) &&
           !aUserGroupMgr.containsUserGroupWithAssignedRole (aRole.getID ());
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final RoleManager aRoleMgr = PhotonSecurityManager.getRoleMgr ();
    final UserGroupManager aUserGroupManager = PhotonSecurityManager.getUserGroupMgr ();

    final HCTable aTable = new HCTable (new DTCol ().setVisible (false),
                                        new DTCol (EText.HEADER_NAME.getDisplayText (aDisplayLocale)).setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol (EText.HEADER_IN_USE.getDisplayText (aDisplayLocale)),
                                        new BootstrapDTColAction (aDisplayLocale)).setID (getID ());
    final Collection <? extends IRole> aRoles = aRoleMgr.getAllRoles ();
    for (final IRole aRole : aRoles)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aRole);

      final Collection <? extends IUserGroup> aAssignedUserGroups = aUserGroupManager.getAllUserGroupsWithAssignedRole (aRole.getID ());

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (aRole.getID ());
      aRow.addCell (new HCA (aViewLink).addChild (aRole.getName ()));
      aRow.addCell (EPhotonCoreText.getYesOrNo (!aAssignedUserGroups.isEmpty (), aDisplayLocale));

      final IHCCell <?> aActionCell = aRow.addCell ();
      if (canDeleteRole (aRole))
      {
        aActionCell.addChild (createDeleteLink (aWPEC,
                                                aRole,
                                                EWebPageText.OBJECT_DELETE.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                   aRole.getName ())));
      }
      else
      {
        aActionCell.addChild (createEmptyAction ());
      }
    }

    aNodeList.addChild (aTable);

    final DataTables aDataTables = BootstrapDataTables.createDefaultDataTables (aWPEC, aTable);
    aNodeList.addChild (aDataTables);
  }
}
