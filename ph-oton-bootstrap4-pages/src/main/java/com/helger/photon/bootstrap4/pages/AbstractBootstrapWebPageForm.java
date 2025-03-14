/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.pages;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.id.IHasID;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.buttongroup.BootstrapButtonToolbar;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.traits.IHCBootstrap4Trait;
import com.helger.photon.uicore.page.AbstractWebPageForm;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

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
public abstract class AbstractBootstrapWebPageForm <DATATYPE extends IHasID <String>, WPECTYPE extends IWebPageExecutionContext> extends
                                                   AbstractWebPageForm <DATATYPE, WPECTYPE, BootstrapForm, BootstrapButtonToolbar>
                                                   implements
                                                   IHCBootstrap4Trait
{
  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, getAsMLT (sName), null, BootstrapWebPageUIHandler.INSTANCE);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName, null, BootstrapWebPageUIHandler.INSTANCE);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID,
                                       @Nonnull final String sName,
                                       @Nullable final String sDescription)
  {
    super (sID, getAsMLT (sName), getAsMLT (sDescription), BootstrapWebPageUIHandler.INSTANCE);
  }

  public AbstractBootstrapWebPageForm (@Nonnull @Nonempty final String sID,
                                       @Nonnull final IMultilingualText aName,
                                       @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription, BootstrapWebPageUIHandler.INSTANCE);
  }

  @Override
  @Nullable
  @OverrideOnDemand
  public IHCNode getHeaderNode (@Nonnull final WPECTYPE aWPEC)
  {
    final String sHeaderText = getHeaderText (aWPEC);
    return getUIHandler ().createPageHeader (sHeaderText);
  }

  @Override
  protected void modifyFormBeforeShowInputForm (@Nonnull final WPECTYPE aWPEC,
                                                @Nonnull final BootstrapForm aForm,
                                                final boolean bIsFormSubmitted)
  {
    // That class is only suitable when using JS only validation, because it
    // uses the ":valid" and ":invalid" CSS pseudoclasses - as in
    // https://css-tricks.com/almanac/selectors/v/valid
    if (false)
      if (bIsFormSubmitted)
        aForm.addClass (CBootstrapCSS.WAS_VALIDATED);
  }
}
