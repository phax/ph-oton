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

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.text.IReadonlyMultiLingualText;
import com.helger.html.hc.IHCNode;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.uicore.page.AbstractWebPageExt;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public abstract class AbstractBootstrapWebPageExt <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPageExt <WPECTYPE>
{
  public AbstractBootstrapWebPageExt (@Nonnull @Nonempty final String sID, @Nonnull final String sName)
  {
    super (sID, sName);
  }

  public AbstractBootstrapWebPageExt (@Nonnull @Nonempty final String sID,
                                      @Nonnull final IReadonlyMultiLingualText aName)
  {
    super (sID, aName);
  }

  public AbstractBootstrapWebPageExt (@Nonnull @Nonempty final String sID,
                                      @Nonnull final String sName,
                                      @Nullable final String sDescription)
  {
    super (sID, sName, sDescription);
  }

  public AbstractBootstrapWebPageExt (@Nonnull @Nonempty final String sID,
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

  /**
   * Create a new toolbar.
   * 
   * @param aWPEC
   *        Current web page execution context. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected final BootstrapButtonToolbar createNewToolbar (@Nonnull final WPECTYPE aWPEC)
  {
    return new BootstrapButtonToolbar (aWPEC);
  }

  /**
   * Create a new error box
   * 
   * @param aWPEC
   *        Current web page execution context. Never <code>null</code>.
   * @param sErrorMsg
   *        The error message to emit.
   * @return Never <code>null</code>.
   */
  @Nonnull
  protected final IHCNode createErrorBox (@Nonnull final WPECTYPE aWPEC, @Nullable final String sErrorMsg)
  {
    return new BootstrapErrorBox ().addChild (sErrorMsg);
  }

  @Nonnull
  protected final IHCNode createIncorrectInputBox (@Nonnull final WPECTYPE aWPEC)
  {
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();
    return new BootstrapErrorBox ().addChild (EPhotonCoreText.ERR_INCORRECT_INPUT.getDisplayText (aDisplayLocale));
  }
}
