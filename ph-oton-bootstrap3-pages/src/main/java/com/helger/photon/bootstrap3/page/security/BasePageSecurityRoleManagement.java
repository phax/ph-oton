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
package com.helger.photon.bootstrap3.page.security;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.security.AccessManager;
import com.helger.appbasics.security.CSecurity;
import com.helger.appbasics.security.role.IRole;
import com.helger.appbasics.security.usergroup.IUserGroup;
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
import com.helger.html.hc.html.HCRow;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.bootstrap3.table.BootstrapTableFormView;
import com.helger.photon.bootstrap3.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.html.table.IHCTableFormView;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.EWebPageText;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.validation.error.FormErrors;
import com.helger.webbasics.EWebBasicsText;
import com.helger.webctrls.datatables.DataTables;

public class BasePageSecurityRoleManagement <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageSecurityObjectWithAttributes <IRole, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText, IHasDisplayTextWithArgs
  {
    HEADER_NAME ("Name", "Name"),
    HEADER_IN_USE ("Verwendet?", "In use?"),
    HEADER_VALUE ("Wert", "Value"),
    HEADER_DETAILS ("Details von Rolle {0}", "Details of role {0}"),
    LABEL_NAME ("Name", "Name"),
    LABEL_DESCRIPTION ("Beschreibung", "Description"),
    LABEL_USERGROUPS_0 ("Benutzergruppen", "User groups"),
    LABEL_USERGROUPS_N ("Benutzergruppen ({0})", "User groups ({0})"),
    LABEL_ATTRIBUTES ("Attribute", "Attributes"),
    NONE_ASSIGNED ("keine zugeordnet", "none assigned"),
    DELETE_QUERY ("Soll die Rolle ''{0}'' wirklich gelöscht werden?", "Are you sure to delete the role ''{0}''?"),
    DELETE_SUCCESS ("Die Rolle ''{0}'' wurden erfolgreich gelöscht!", "The role ''{0}'' was successfully deleted!"),
    DELETE_ERROR ("Fehler beim Löschen der Rolle ''{0}''!", "Error deleting the role ''{0}''!");

    private final TextProvider m_aTP;

    private EText (final String sDE, final String sEN)
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

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID)
  {
    super (sID, EWebPageText.PAGE_NAME_SECURITY_ROLES.getAsMLT ());
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID, @Nonnull @Nonempty final String sName)
  {
    super (sID, sName);
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final String sName,
                                         @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageSecurityRoleManagement (@Nonnull @Nonempty final String sID,
                                         @Nonnull final IReadonlyMultiLingualText aName,
                                         @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
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
    return AccessManager.getInstance ().getRoleOfID (sID);
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

    final IHCTableFormView <?> aTable = aNodeList.addAndReturnChild (new BootstrapTableFormView (new HCCol (170),
                                                                                                       HCCol.star ()));
    aTable.setSpanningHeaderContent (EText.HEADER_DETAILS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                  aSelectedObject.getName ()));

    onShowSelectedObjectTableStart (aWPEC, aTable, aSelectedObject);

    aTable.createItemRow ()
          .setLabel (EText.LABEL_NAME.getDisplayText (aDisplayLocale))
          .setCtrl (aSelectedObject.getName ());

    if (StringHelper.hasText (aSelectedObject.getDescription ()))
      aTable.createItemRow ()
            .setLabel (EText.LABEL_DESCRIPTION.getDisplayText (aDisplayLocale))
            .setCtrl (HCUtils.nl2divList (aSelectedObject.getDescription ()));

    // All user groups to which the role is assigned
    final Collection <IUserGroup> aAssignedUserGroups = AccessManager.getInstance ()
                                                                     .getAllUserGroupsWithAssignedRole (aSelectedObject.getID ());
    if (aAssignedUserGroups.isEmpty ())
    {
      aTable.createItemRow ()
            .setLabel (EText.LABEL_USERGROUPS_0.getDisplayText (aDisplayLocale))
            .setCtrl (HCEM.create (EText.NONE_ASSIGNED.getDisplayText (aDisplayLocale)));
    }
    else
    {
      final HCNodeList aUserGroupUI = new HCNodeList ();
      for (final IUserGroup aUserGroup : CollectionHelper.getSorted (aAssignedUserGroups,
                                                                    new ComparatorHasName <IUserGroup> (aDisplayLocale)))
        aUserGroupUI.addChild (HCDiv.create (aUserGroup.getName ()));
      aTable.createItemRow ()
            .setLabel (EText.LABEL_USERGROUPS_N.getDisplayTextWithArgs (aDisplayLocale,
                                                                        Integer.toString (aAssignedUserGroups.size ())))
            .setCtrl (aUserGroupUI);
    }

    // custom attributes
    final Map <String, Object> aCustomAttrs = aSelectedObject.getAllAttributes ();

    // Callback for custom attributes
    final Set <String> aHandledAttrs = onShowSelectedObjectCustomAttrs (aWPEC, aSelectedObject, aCustomAttrs, aTable);

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
        aTable.createItemRow ().setLabel (EText.LABEL_ATTRIBUTES.getDisplayText (aDisplayLocale)).setCtrl (aAttrTable);
    }

    // Callback
    onShowSelectedObjectTableEnd (aWPEC, aTable, aSelectedObject);
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
                                @Nonnull final AbstractHCForm <?> aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {
    throw new UnsupportedOperationException ();
  }

  protected static boolean canDeleteRole (@Nullable final IRole aRole)
  {
    return aRole != null &&
           !aRole.getID ().equals (CSecurity.ROLE_ADMINISTRATOR_ID) &&
           AccessManager.getInstance ().getAllUserGroupIDsWithAssignedRole (aRole.getID ()).isEmpty ();
  }

  @Override
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final AbstractHCForm <?> aForm,
                                  @Nonnull final IRole aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (
                                                    EText.DELETE_QUERY.getDisplayTextWithArgs (aDisplayLocale,
                                                                                               aSelectedObject.getName ())));
  }

  @Override
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final IRole aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (AccessManager.getInstance ().deleteRole (aSelectedObject.getID ()).isChanged ())
    {
      aNodeList.addChild (new BootstrapSuccessBox ().addChild (
                                                         EText.DELETE_SUCCESS.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                      aSelectedObject.getName ())));
    }
    else
    {
      aNodeList.addChild (new BootstrapErrorBox ().addChild (
                                                       EText.DELETE_ERROR.getDisplayTextWithArgs (aDisplayLocale,
                                                                                                  aSelectedObject.getName ())));
    }
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final AccessManager aAccessMgr = AccessManager.getInstance ();

    final IHCTable <?> aTable = new BootstrapTable (HCCol.star (), new HCCol (110), createActionCol (1))
                                            .setID (getID ());
    aTable.addHeaderRow ().addCells (EText.HEADER_NAME.getDisplayText (aDisplayLocale),
                                     EText.HEADER_IN_USE.getDisplayText (aDisplayLocale),
                                     EWebBasicsText.MSG_ACTIONS.getDisplayText (aDisplayLocale));
    final Collection <? extends IRole> aRoles = aAccessMgr.getAllRoles ();
    for (final IRole aRole : aRoles)
    {
      final ISimpleURL aViewLink = createViewURL (aWPEC, aRole);

      final Collection <IUserGroup> aAssignedUserGroups = aAccessMgr.getAllUserGroupsWithAssignedRole (aRole.getID ());

      final HCRow aRow = aTable.addBodyRow ();
      aRow.addCell (new HCA (aViewLink).addChild (aRole.getName ()));
      aRow.addCell (EWebBasicsText.getYesOrNo (!aAssignedUserGroups.isEmpty (), aDisplayLocale));

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
    aDataTables.getOrCreateColumnOfTarget (2).addClass (CSS_CLASS_ACTION_COL).setSortable (false);
    aDataTables.setInitialSorting (0, ESortOrder.ASCENDING);
    aNodeList.addChild (aDataTables);
  }
}
