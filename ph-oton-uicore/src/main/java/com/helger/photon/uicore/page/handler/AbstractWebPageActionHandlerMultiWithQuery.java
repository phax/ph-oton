/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.id.IHasID;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.uicore.css.CPageParam;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.icon.EDefaultIcon;
import com.helger.photon.uicore.icon.IIcon;
import com.helger.photon.uicore.page.EShowList;
import com.helger.photon.uicore.page.IWebPageCSRFHandler;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uicore.page.IWebPageFormUIHandler;

public abstract class AbstractWebPageActionHandlerMultiWithQuery <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext, FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>>
                                                                 extends
                                                                 AbstractWebPageActionHandlerMulti <DATATYPE, WPECTYPE, FORM_TYPE, TOOLBAR_TYPE>
{
  private final String m_sAction;
  private final String m_sFormID;

  public AbstractWebPageActionHandlerMultiWithQuery (@Nonnull final IWebPageFormUIHandler <FORM_TYPE, TOOLBAR_TYPE> aUIHandler,
                                                     @Nonnull @Nonempty final String sFieldName,
                                                     @Nonnull final BiFunction <WPECTYPE, String, DATATYPE> aResolver,
                                                     @Nonnull @Nonempty final String sAction,
                                                     @Nonnull @Nonempty final String sFormID)
  {
    super (aUIHandler, sFieldName, aResolver);
    ValueEnforcer.notEmpty (sAction, "Action");
    ValueEnforcer.notEmpty (sFormID, "FormID");
    m_sAction = sAction;
    m_sFormID = sFormID;
  }

  /**
   * Show the query.
   *
   * @param aWPEC
   *        The web page execution context
   * @param aForm
   *        The handled form. Never <code>null</code>.
   * @param aSelectedObjects
   *        The objects to be handled. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void showQuery (@Nonnull WPECTYPE aWPEC, @Nonnull FORM_TYPE aForm, @Nonnull ICommonsList <DATATYPE> aSelectedObjects);

  /**
   * Perform object action
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObjects
   *        The objects to be handled. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected abstract void performAction (@Nonnull WPECTYPE aWPEC, @Nonnull ICommonsList <DATATYPE> aSelectedObjects);

  /**
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObjects
   *        The selected objects. Never <code>null</code>.
   * @return <code>true</code> to show the toolbar, <code>false</code> to draw
   *         your own toolbar
   */
  @OverrideOnDemand
  protected boolean showToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
  {
    return true;
  }

  @Nullable
  @OverrideOnDemand
  protected String getToolbarSubmitButtonText (@Nonnull final Locale aDisplayLocale)
  {
    return EPhotonCoreText.BUTTON_YES.getDisplayText (aDisplayLocale);
  }

  @Nullable
  @OverrideOnDemand
  protected IIcon getToolbarSubmitButtonIcon ()
  {
    return EDefaultIcon.YES;
  }

  /**
   * Add additional elements to the toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final TOOLBAR_TYPE aToolbar)
  {}

  /**
   * Create toolbar for handling an existing object
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
  protected TOOLBAR_TYPE createToolbar (@Nonnull final WPECTYPE aWPEC,
                                        @Nonnull final FORM_TYPE aForm,
                                        @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final TOOLBAR_TYPE aToolbar = getUIHandler ().createToolbar (aWPEC);
    aToolbar.addHiddenField (CPageParam.PARAM_ACTION, m_sAction);
    aToolbar.addHiddenField (CPageParam.PARAM_SUBACTION, CPageParam.ACTION_SAVE);
    for (final DATATYPE aItem : aSelectedObjects)
      aToolbar.addHiddenField (getFieldName (), aItem.getID ());
    // Yes button
    aToolbar.addSubmitButton (getToolbarSubmitButtonText (aDisplayLocale), getToolbarSubmitButtonIcon ());
    // No button
    aToolbar.addButtonNo (aDisplayLocale);

    // Callback
    modifyToolbar (aWPEC, aToolbar);
    return aToolbar;
  }

  @OverrideOnDemand
  protected boolean isFormSubmitted (@Nonnull final WPECTYPE aWPEC)
  {
    return aWPEC.hasAction (m_sAction) && aWPEC.hasSubAction (CPageParam.ACTION_SAVE);
  }

  @Nonnull
  public EShowList handleMultiAction (@Nonnull final WPECTYPE aWPEC, @Nonnull final ICommonsList <DATATYPE> aSelectedObjects)
  {
    final boolean bIsFormSubmitted = isFormSubmitted (aWPEC);
    final IWebPageCSRFHandler aCSRFHandler = aWPEC.getWebPage ().getCSRFHandler ();

    final EShowList eShowList;
    if (bIsFormSubmitted)
    {
      // Check if the nonce matches
      if (aCSRFHandler.checkCSRFNonce (aWPEC).isContinue ())
      {
        // Main delete
        performAction (aWPEC, aSelectedObjects);
      }

      eShowList = EShowList.SHOW_LIST;
    }
    else
    {
      final FORM_TYPE aForm = getUIHandler ().createFormSelf (aWPEC);
      aWPEC.getNodeList ().addChild (aForm);

      // Set unique ID
      aForm.setID (m_sFormID);

      // Add the nonce for CSRF check
      aForm.addChild (aCSRFHandler.createCSRFNonceField ());

      // Show the main query
      showQuery (aWPEC, aForm, aSelectedObjects);

      // Show the toolbar?
      if (showToolbar (aWPEC, aSelectedObjects))
        aForm.addChild (createToolbar (aWPEC, aForm, aSelectedObjects));
      eShowList = EShowList.DONT_SHOW_LIST;
    }

    return eShowList;
  }
}
