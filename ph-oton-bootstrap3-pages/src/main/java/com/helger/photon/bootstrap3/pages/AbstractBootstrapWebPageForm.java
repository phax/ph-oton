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
package com.helger.photon.bootstrap3.pages;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.commons.string.StringHelper;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.html.hc.html.HCH1;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.bootstrap3.pageheader.BootstrapPageHeader;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;
import com.helger.photon.uicore.page.AbstractWebPageForm;
import com.helger.photon.uicore.page.EWebPageFormAction;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.validation.error.FormErrors;

/**
 * Abstract base class for a Bootstrap based web page that has the common form
 * handling, with a list view, details view, create and edit + binding.
 *
 * @author Philip Helger
 * @param <DATATYPE>
 *        The data type of the object to be handled.
 * @param <WPECTYPE>
 *        Web page execution context type
 */
@NotThreadSafe
public abstract class AbstractBootstrapWebPageForm <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageForm <DATATYPE, WPECTYPE>
{
  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID,
                                       @Nonnull final IReadonlyMultiLingualText aName)
  {
    super (sID, aName);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID,
                                       @Nonnull final IReadonlyMultiLingualText aName,
                                       @Nullable final IReadonlyMultiLingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  @Nullable
  @OverrideOnDemand
  public IHCNode getHeaderNode (@Nonnull final WPECTYPE aWPEC)
  {
    final String sHeaderText = getHeaderText (aWPEC);
    if (StringHelper.hasNoText (sHeaderText))
      return null;
    return new BootstrapPageHeader ().addChild (new HCH1 ().addChild (sHeaderText));
  }

  @Override
  @Nonnull
  public BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapForm (EBootstrapFormType.HORIZONTAL).setAction (aLEC.getSelfHref ());
  }

  @Override
  @Nonnull
  public BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC).setEncTypeFileUpload ();
  }

  @Override
  protected final IHCNode createErrorBox (@Nonnull final WPECTYPE aWPEC, @Nullable final String sErrorMsg)
  {
    return new BootstrapErrorBox ().addChild (sErrorMsg);
  }

  @Override
  protected final IHCNode createIncorrectInputBox (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return new BootstrapErrorBox ().addChild (EPhotonCoreText.ERR_INCORRECT_INPUT.getDisplayText (aDisplayLocale));
  }

  @Override
  @Nonnull
  protected final BootstrapButtonToolbar createNewToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createNewViewToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return (BootstrapButtonToolbar) super.createNewViewToolbar (aWPEC);
  }

  /**
   * Add additional elements to the view toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aSelectedObject,
                                    @Nonnull final BootstrapButtonToolbar aToolbar)
  {}

  @Override
  protected final void modifyViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                          @Nonnull final DATATYPE aSelectedObject,
                                          @Nonnull final IButtonToolbar <?> aToolbar)
  {
    modifyViewToolbar (aWPEC, aSelectedObject, (BootstrapButtonToolbar) aToolbar);
  }

  @Override
  @Nonnull
  protected BootstrapButtonToolbar createViewToolbar (@Nonnull final WPECTYPE aWPEC,
                                                      final boolean bCanGoBack,
                                                      @Nonnull final DATATYPE aSelectedObject)
  {
    return (BootstrapButtonToolbar) super.createViewToolbar (aWPEC, bCanGoBack, aSelectedObject);
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createNewEditToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return (BootstrapButtonToolbar) super.createNewEditToolbar (aWPEC);
  }

  /**
   * Add additional elements to the edit toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aSelectedObject
   *        The selected object. Never <code>null</code>.
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                    @Nonnull final DATATYPE aSelectedObject,
                                    @Nonnull final BootstrapButtonToolbar aToolbar)
  {}

  @Override
  protected final void modifyEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                          @Nonnull final DATATYPE aSelectedObject,
                                          @Nonnull final IButtonToolbar <?> aToolbar)
  {
    modifyEditToolbar (aWPEC, aSelectedObject, (BootstrapButtonToolbar) aToolbar);
  }

  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                                      @Nonnull final BootstrapForm aForm,
                                                      @Nonnull final DATATYPE aSelectedObject)
  {
    return (BootstrapButtonToolbar) super.createEditToolbar (aWPEC, aForm, aSelectedObject);
  }

  @Override
  @Nonnull
  protected final IButtonToolbar <?> createEditToolbar (@Nonnull final WPECTYPE aWPEC,
                                                        @Nonnull final AbstractHCForm <?> aForm,
                                                        @Nonnull final DATATYPE aSelectedObject)
  {
    return createEditToolbar (aWPEC, (BootstrapForm) aForm, aSelectedObject);
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createNewCreateToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return (BootstrapButtonToolbar) super.createNewCreateToolbar (aWPEC);
  }

  /**
   * Add additional elements to the create toolbar
   *
   * @param aWPEC
   *        The web page execution context
   * @param aToolbar
   *        The toolbar to be modified
   */
  @OverrideOnDemand
  protected void modifyCreateToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final BootstrapButtonToolbar aToolbar)
  {}

  @Override
  protected final void modifyCreateToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final IButtonToolbar <?> aToolbar)
  {
    modifyCreateToolbar (aWPEC, (BootstrapButtonToolbar) aToolbar);
  }

  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createCreateToolbar (@Nonnull final WPECTYPE aWPEC,
                                                        @Nonnull final BootstrapForm aForm,
                                                        @Nullable final DATATYPE aSelectedObject)
  {
    return (BootstrapButtonToolbar) super.createCreateToolbar (aWPEC, aForm, aSelectedObject);
  }

  @Override
  @Nonnull
  protected final BootstrapButtonToolbar createCreateToolbar (@Nonnull final WPECTYPE aWPEC,
                                                              @Nonnull final AbstractHCForm <?> aForm,
                                                              @Nullable final DATATYPE aSelectedObject)
  {
    return createCreateToolbar (aWPEC, (BootstrapForm) aForm, aSelectedObject);
  }

  protected abstract void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                         @Nullable final DATATYPE aSelectedObject,
                                         @Nonnull final BootstrapForm aForm,
                                         @Nonnull final EWebPageFormAction eFormAction,
                                         @Nonnull final FormErrors aFormErrors);

  @Override
  protected final void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                      @Nullable final DATATYPE aSelectedObject,
                                      @Nonnull final AbstractHCForm <?> aForm,
                                      @Nonnull final EWebPageFormAction eFormAction,
                                      @Nonnull final FormErrors aFormErrors)
  {
    // Change type from AbstractHCForm <?> to BootstrapForm
    showInputForm (aWPEC, aSelectedObject, (BootstrapForm) aForm, eFormAction, aFormErrors);
  }

  /**
   * Add additional form IDs (e.g. client and accounting area). This method is
   * called after
   * {@link #showInputForm(IWebPageExecutionContext, IHasID, AbstractHCForm, EWebPageFormAction, FormErrors)}
   * was called but before the toolbars are added.
   *
   * @param aWPEC
   *        Web page execution context
   * @param aForm
   *        the form to add the elements to
   */
  @OverrideOnDemand
  protected void modifyFormAfterShowInputForm (@Nonnull final WPECTYPE aWPEC, @Nonnull final BootstrapForm aForm)
  {}

  @Override
  protected final void modifyFormAfterShowInputForm (@Nonnull final WPECTYPE aWPEC,
                                                     @Nonnull final AbstractHCForm <?> aForm)
  {
    modifyFormAfterShowInputForm (aWPEC, (BootstrapForm) aForm);
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
  protected void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                  @Nonnull final BootstrapForm aForm,
                                  @Nonnull final DATATYPE aSelectedObject)
  {}

  @Override
  protected final void showDeleteQuery (@Nonnull final WPECTYPE aWPEC,
                                        @Nonnull final AbstractHCForm <?> aForm,
                                        @Nonnull final DATATYPE aSelectedObject)
  {
    showDeleteQuery (aWPEC, (BootstrapForm) aForm, aSelectedObject);
  }

  @Override
  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createNewDeleteToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return (BootstrapButtonToolbar) super.createNewDeleteToolbar (aWPEC);
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
  protected void modifyDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final BootstrapButtonToolbar aToolbar)
  {}

  @Override
  protected final void modifyDeleteToolbar (@Nonnull final WPECTYPE aWPEC, @Nonnull final IButtonToolbar <?> aToolbar)
  {
    modifyDeleteToolbar (aWPEC, (BootstrapButtonToolbar) aToolbar);
  }

  @Nonnull
  @OverrideOnDemand
  protected BootstrapButtonToolbar createDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                                        @Nonnull final BootstrapForm aForm,
                                                        @Nonnull final DATATYPE aSelectedObject)
  {
    return (BootstrapButtonToolbar) super.createDeleteToolbar (aWPEC, aForm, aSelectedObject);
  }

  @Override
  @Nonnull
  protected final BootstrapButtonToolbar createDeleteToolbar (@Nonnull final WPECTYPE aWPEC,
                                                              @Nonnull final AbstractHCForm <?> aForm,
                                                              @Nonnull final DATATYPE aSelectedObject)
  {
    return createDeleteToolbar (aWPEC, (BootstrapForm) aForm, aSelectedObject);
  }
}
