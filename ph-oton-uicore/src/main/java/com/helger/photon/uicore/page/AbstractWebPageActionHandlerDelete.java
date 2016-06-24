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
package com.helger.photon.uicore.page;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;

public abstract class AbstractWebPageActionHandlerDelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                         extends
                                                         AbstractWebPageActionHandler <DATATYPE, WPECTYPE, FORM_TYPE, TOOLBAR_TYPE>
{
  public static final String FORM_ID_DELETE = "deleteform";

  public AbstractWebPageActionHandlerDelete (@Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler)
  {
    super (true, aUIHandler);
  }

  /**
   * Show the delete query.
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                           @Nonnull final FORM_TYPE aForm,
                                           @Nonnull final DATATYPE aSelectedObject);

  /**
   * Perform object delete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The object to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void performDelete (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject);

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @return <code>true</code> to show the delete toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    return true;
  }

  @Nullable
  @OverrideOnDemand
  protected String getDeleteToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getDeleteToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.YES;
  }

  /**
   * Add additional elements to the delete toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  /**
   * Create toolbar for deleting an existing object
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObject
   *        Selected object. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                              @Nonnull final FORM_TYPE aForm,
                                              @Nonnull final DATATYPE aSelectedObject)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = getUIHandler ().createToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    aToolbar.addHiddenField (CPageParam.PARAM_OBJECT, aSelectedObject.getID ());
    // Yes button
    aToolbar.addSubmitButton (getDeleteToolbarSubmitButtonText (aDisplayLocale), getDeleteToolbarSubmitButtonIcon ());
    // No button
    aToolbar.addButtonNo (aDisplayLocale);

    // Callback
    modifyDeleteToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  public final boolean handleAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final DATATYPE aSelectedObject)
  {
    final boolean bIsFormSubmitted = aWPEC.hasSubAction (CPageParam.ACTION_SAVE);
    final IWebPageCSRFHandler aCSRFHandler = aWPEC.getWebPage ().getCSRFHandler ();
    boolean bShowList;

    if (bIsFormSubmitted)
    {
      // Check if the nonce matches
      if (aCSRFHandler.checkCSRFNonce (aWPEC).isContinue ())
      {
        // Main delete
        performDelete (aWPEC, aSelectedObject);
      }

      bShowList = true;
    }
    else
    {
      final FORM_TYPE aForm = getUIHandler ().createFormSelf (aWPEC);
      aWPEC.getNodeList ().addChild (aForm);

      // Set unique ID
      aForm.setID (FORM_ID_DELETE);

      // Add the nonce for CSRF check
      aForm.addChild (aCSRFHandler.createCSRFNonceField ());

      // Show the main query
      showDeleteQuery (aWPEC, aForm, aSelectedObject);

      // Show the toolbar?
      if (showDeleteToolbar (aWPEC, aSelectedObject))
        aForm.addChild (createDeleteToolbar (aWPEC, aForm, aSelectedObject));
      bShowList = false;
    }

    return bShowList;
  }
}
