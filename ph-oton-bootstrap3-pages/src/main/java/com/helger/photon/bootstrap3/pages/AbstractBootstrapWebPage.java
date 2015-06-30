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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPage <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  public AbstractBootstrapWebPage (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractBootstrapWebPage (@Nonnull @Nonempty final String sID, @Nonnull final IMultilingualText aName)
  {
    super (sID, aName);
  }

  public AbstractBootstrapWebPage (@Nonnull @Nonempty final String sID,
                                   @Nonnull final String sName,
                                   @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractBootstrapWebPage (@Nonnull @Nonempty final String sID,
                                   @Nonnull final IMultilingualText aName,
                                   @Nullable final IMultilingualText aDescription)
  {
    super (sID, aName, aDescription);
  }

  @Override
  @Nullable
  @OverrideOnDemand
  public IHCNode getHeaderNode (@Nonnull final WPECTYPE aWPEC)
  {
    final String sHeaderText = getHeaderText (aWPEC);
    return BootstrapUI.createPageHeader (sHeaderText);
  }

  @Nonnull
  protected BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return BootstrapUI.createFormSelf (aLEC);
  }

  @Nonnull
  protected BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return BootstrapUI.createFormFileUploadSelf (aLEC);
  }

  @Nonnull
  protected final IHCNode createErrorBox (@Nullable final String sErrorMsg)
  {
    return BootstrapUI.createErrorBox (sErrorMsg);
  }

  @Nonnull
  protected final IHCNode createIncorrectInputBox (@Nonnull final WPECTYPE aWPEC)
  {
    return BootstrapUI.createIncorrectInputBox (aWPEC);
  }
}
