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
import com.helger.commons.id.IHasID;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCForm;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
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
  @Nonnull
  public final BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapForm (EBootstrapFormType.HORIZONTAL).setAction (aLEC.getSelfHref ());
  }

  @Override
  @Nonnull
  public final BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC).setEncTypeFileUpload ();
  }

  @Override
  @Nonnull
  protected final BootstrapButtonToolbar createNewToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
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
  protected final void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                      @Nullable final DATATYPE aSelectedObject,
                                      @Nonnull final AbstractHCForm <?> aForm,
                                      @Nonnull final EWebPageFormAction eFormAction,
                                      @Nonnull final FormErrors aFormErrors)
  {
    // Change type from AbstractHCForm <?> to BootstrapForm
    final BootstrapForm aBootstrapForm = (BootstrapForm) aForm;
    showInputForm (aWPEC, aSelectedObject, aBootstrapForm, eFormAction, aFormErrors);
  }

  protected abstract void showInputForm (@Nonnull final WPECTYPE aWPEC,
                                         @Nullable final DATATYPE aSelectedObject,
                                         @Nonnull final BootstrapForm aForm,
                                         @Nonnull final EWebPageFormAction eFormAction,
                                         @Nonnull final FormErrors aFormErrors);
}
