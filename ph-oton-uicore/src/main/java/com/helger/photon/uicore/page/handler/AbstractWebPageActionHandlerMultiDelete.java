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
package com.helger.photon.uicore.page.handler;

import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;
import com.helger.photon.uicore.page.IWebPageCSRFHandler;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public abstract class AbstractWebPageActionHandlerMultiDelete <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                              extends
                                                              AbstractWebPageActionHandlerMulti <DATATYPE, WPECTYPE, FORM_TYPE, TOOLBAR_TYPE>
{
  public static final String FORM_ID_DELETE = "deleteform";

  public AbstractWebPageActionHandlerMultiDelete (@Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler,
                                                  @Nonnull @Nonempty final String sFieldName,
                                                  @Nonnull final Function <String, DATATYPE> aResolver)
  {
    super (aUIHandler, sFieldName, aResolver);
  }

  /**
   * Show the delete query.
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObjects
   *        The objects to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                           @Nonnull final FORM_TYPE aForm,
                                           @Nonnull final ICommonsList <DATATYPE> aSelectedObjects);

  /**
   * Perform object delete
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObjects
   *        The objects to be deleted. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void performDelete (@Nonnull final WPECTYPE aWPEC,
                                         @Nonnull final ICommonsList <DATATYPE> aSelectedObjects);

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObjects
   *        The selected objects. Never <code>null</code>.
   * @return <code>true</code> to show the delete toolbar, <code>false</code> to
   *         draw your own toolbar
   */
  @OverrideOnDemand
  protected boolean showDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                       @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
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
   * @param aSelectedObjects
   *        Selected objects. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected TOOLBAR_TYPE createDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                              @Nonnull final FORM_TYPE aForm,
                                              @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = getUIHandler ().createToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, CPageParam.ACTION_DELETE);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    // Yes button
    aToolbar.addSubmitButton (getDeleteToolbarSubmitButtonText (aDisplayLocale), getDeleteToolbarSubmitButtonIcon ());
    // No button
    aToolbar.addButtonNo (aDisplayLocale);

    // Callback
    modifyDeleteToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  public final boolean handleMultiAction (@Nonnull final WPECTYPE aWPEC,
                                          @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
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
        performDelete (aWPEC, aSelectedObjects);
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
      showDeleteQuery (aWPEC, aForm, aSelectedObjects);

      // Show the toolbar?
      if (showDeleteToolbar (aWPEC, aSelectedObjects))
        aForm.addChild (createDeleteToolbar (aWPEC, aForm, aSelectedObjects));
      bShowList = false;
    }

    return bShowList;
  }
}
