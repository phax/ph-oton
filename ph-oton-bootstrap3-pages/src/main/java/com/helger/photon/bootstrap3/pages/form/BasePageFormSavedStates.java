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
package com.helger.photon.bootstrap3.pages.form;

import java.util.Collection;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;
import com.helger.datetime.format.PDTToString;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hchtml.tabular.HCCol;
import com.helger.html.hchtml.tabular.HCRow;
import com.helger.html.hchtml.tabular.IHCCell;
import com.helger.html.hchtml.textlevel.HCA;
import com.helger.photon.basic.app.menu.IMenuItem;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.alert.BootstrapInfoBox;
import com.helger.photon.bootstrap3.alert.BootstrapQuestionBox;
import com.helger.photon.bootstrap3.alert.BootstrapSuccessBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.pages.AbstractBootstrapWebPageForm;
import com.helger.photon.bootstrap3.table.BootstrapTable;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.form.FormState;
import com.helger.photon.core.form.FormStateManager;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.validation.error.FormErrors;

/**
 * Saved states base page
 *
 * @author Philip Helger
 * @param <WPECTYPE>
 *        Web page execution context type
 */
public class BasePageFormSavedStates <WPECTYPE extends IWebPageExecutionContext> extends AbstractBootstrapWebPageForm <FormState, WPECTYPE>
{
  @Translatable
  protected static enum EText implements IHasDisplayText
  {
   DELETE_QUERY ("Sollen diese gemerkten Daten wirklich gelöscht werden?", "Are you sure to delete this saved data?"),
   DELETE_SUCCESS ("Die gemerkten Daten wurden erfolgreich gelöscht!", "The saved data was successfully deleted!"),
   DELETE_ERROR ("Fehler beim Löschen der gemerkten Daten!", "Error deleting the saved data!"),
   DELETE_ALL_QUERY ("Sollen alle gemerkten Daten wirklich gelöscht werden?", "Are you sure to delete all saved data?"),
   DELETE_ALL_SUCCESS ("Alle gemerkten Daten wurden erfolgreich gelöscht!", "All saved data was successfully deleted!"),
   DELETE_ALL_ERROR ("Fehler beim Löschen der gemerkten Daten!", "Error deleting the saved data!"),
   NONE_PRESENT ("Es sind keine gemerkten Daten vorhanden!", "No saved data is available"),
   BUTTON_DELETE ("Alle löschen", "Delete all"),
   SAVED_STATE_EDIT ("Daten weiter bearbeiten", "Continue editing this data"),
   SAVED_STATE_DELETE ("Lösche diese gemerkten Daten", "Delete this saved state"),
   HEADER_PAGE ("Seite", "Page"),
   HEADER_REMEMBERED_AT ("Gemerkt am", "Remebered at");

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

  public BasePageFormSavedStates (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public BasePageFormSavedStates (@Nonnull @Nonempty final String sID,
                                  @Nonnull final String sName,
                                  @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public BasePageFormSavedStates (@Nonnull @Nonempty final String sID,
                                  @Nonnull final IMultilingualText aName,
                                  @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  @Nullable
  protected FormState getSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nullable final String sID)
  {
    return FormStateManager.getInstance ().getFormStateOfID (sID);
  }

  @Override
  protected void showSelectedObject (@Nonnull final WPECTYPE aWPEC, @Nonnull final FormState aSelectedObject)
  {}

  @Override
  protected void validateAndSaveInputParameters (@Nonnull final WPECTYPE aWPEC,
                                                 @Nullable final FormState aSelectedObject,
                                                 @Nonnull final FormErrors aFormErrors,
                                                 @Nonnull final EWebPageFormAction eFormAction)
  {}

  @Override
  protected void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                @Nullable final FormState aSelectedObject,
                                @Nonnull final BootstrapForm aForm,
                                @Nonnull final EWebPageFormAction eFormAction,
                                @Nonnull final FormErrors aFormErrors)
  {}

  @Override
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final FormState aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_QUERY.getDisplayText (aDisplayLocale)));
  }

  @Override
  protected void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final FormState aSelectedObject)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    if (FormStateManager.getInstance ().deleteFormState (aSelectedObject.getID ()).isChanged ())
      aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.DELETE_SUCCESS.getDisplayText (aDisplayLocale)));
    else
      aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.DELETE_ERROR.getDisplayText (aDisplayLocale)));
  }

  @Override
  protected boolean handleCustomActions (@Nonnull final WPECTYPE aWPEC, @Nullable final FormState aSelectedObject)
  {
    if (aWPEC.hasAction (CPageParam.ACTION_DELETE_ALL))
    {
      final HCNodeList aNodeList = aWPEC.getNodeList ();
      final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

      if (aWPEC.hasSubAction (CPageParam.ACTION_SAVE))
      {
        if (FormStateManager.getInstance ().deleteAllFormStates ().isChanged ())
          aNodeList.addChild (new BootstrapSuccessBox ().addChild (EText.DELETE_ALL_SUCCESS.getDisplayText (aDisplayLocale)));
        else
          aNodeList.addChild (new BootstrapErrorBox ().addChild (EText.DELETE_ALL_ERROR.getDisplayText (aDisplayLocale)));
        return true;
      }

      final BootstrapForm aForm = aNodeList.addAndReturnChild (createFormSelf (aWPEC));
      aForm.addChild (new BootstrapQuestionBox ().addChild (EText.DELETE_ALL_QUERY.getDisplayText (aDisplayLocale)));
      final IButtonToolbar <?> aToolbar = aForm.addAndReturnChild (new BootstrapButtonToolbar (aWPEC));
      aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE_ALL);
      aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
      aToolbar.addSubmitButtonYes (aDisplayLocale);
      aToolbar.addButtonNo (aDisplayLocale);
      return false;
    }

    return true;
  }

  @Override
  protected void showListOfExistingObjects (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    final FormStateManager aFSM = FormStateManager.getInstance ();

    final Collection <FormState> aAllFormStates = aFSM.getAllFormStates ();
    if (aAllFormStates.isEmpty ())
    {
      aNodeList.addChild (new BootstrapInfoBox ().addChild (EText.NONE_PRESENT.getDisplayText (aDisplayLocale)));
    }
    else
    {
      final IButtonToolbar <?> aToolbar = new BootstrapButtonToolbar (aWPEC);
      aToolbar.addButton (EText.BUTTON_DELETE.getDisplayText (aDisplayLocale),
                          aWPEC.getSelfHref ().add (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE_ALL),
                          EDefaultIcon.DELETE);
      aNodeList.addChild (aToolbar);

      // Start emitting saved states
      final BootstrapTable aPerPage = aNodeList.addAndReturnChild (new BootstrapTable (HCCol.star (),
                                                                                       HCCol.star (),
                                                                                       HCCol.star ()));
      aPerPage.addHeaderRow ().addCells (EText.HEADER_PAGE.getDisplayText (aDisplayLocale),
                                         EText.HEADER_REMEMBERED_AT.getDisplayText (aDisplayLocale),
                                         EPhotonCoreText.ACTIONS.getDisplayText (aDisplayLocale));
      for (final FormState aFormState : aAllFormStates)
      {
        final HCRow aRow = aPerPage.addBodyRow ();

        final String sPageID = aFormState.getPageID ();
        final IMenuItem aMenuItem = (IMenuItem) aWPEC.getMenuTree ().getMenuObjectOfID (sPageID);
        aRow.addCell (aMenuItem.getDisplayText (aDisplayLocale));

        aRow.addCell (PDTToString.getAsString (aFormState.getDateTime (), aDisplayLocale));

        final IHCCell <?> aActionCell = aRow.addCell ();
        // Original action (currently always create even for copy)
        final String sAction = aFormState.getAllAttributes ().getAttributeAsString (CPageParam.PARAM_ACTION,
                                                                                    CPageParam.ACTION_CREATE);
        // Original object ID
        final String sObjectID = aFormState.getAllAttributes ().getAttributeAsString (CPageParam.PARAM_OBJECT);
        aActionCell.addChild (new HCA (aWPEC.getLinkToMenuItem (aFormState.getPageID ())
                                            .add (CPageParam.PARAM_ACTION, sAction)
                                            .addIfNonNull (CPageParam.PARAM_OBJECT, sObjectID)
                                            .add (FIELD_FLOW_ID, aFormState.getFlowID ())
                                            .add (FIELD_RESTORE_FLOW_ID,
                                                  aFormState.getFlowID ())).setTitle (EText.SAVED_STATE_EDIT.getDisplayText (aDisplayLocale))
                                                                           .addChild (EDefaultIcon.NEW.getAsNode ()));
        aActionCell.addChild (createDeleteLink (aWPEC,
                                                aFormState,
                                                EText.SAVED_STATE_DELETE.getDisplayText (aDisplayLocale)));
      }
    }
  }
}
