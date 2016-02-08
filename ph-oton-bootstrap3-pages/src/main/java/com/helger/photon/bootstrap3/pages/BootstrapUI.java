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
package com.helger.photon.bootstrap3.pages;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.html.sections.HCH1;
import com.helger.photon.bootstrap3.alert.BootstrapErrorBox;
import com.helger.photon.bootstrap3.button.BootstrapButtonToolbar;
import com.helger.photon.bootstrap3.form.BootstrapForm;
import com.helger.photon.bootstrap3.form.EBootstrapFormType;
import com.helger.photon.bootstrap3.pageheader.BootstrapPageHeader;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;

/**
 * This class contains static methods to create common UIs.
 *
 * @author Philip Helger
 */
@Immutable
public final class BootstrapUI
{
  private BootstrapUI ()
  {}

  @Nullable
  public static BootstrapPageHeader createPageHeader (@Nullable final String sHeaderText)
  {
    if (StringHelper.hasNoText (sHeaderText))
      return null;
    return new BootstrapPageHeader ().addChild (new HCH1 ().addChild (sHeaderText));
  }

  @Nonnull
  public static BootstrapForm createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapForm (EBootstrapFormType.HORIZONTAL).setAction (aLEC.getSelfHref ());
  }

  @Nonnull
  public static BootstrapForm createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC).setEncTypeFileUpload ();
  }

  /**
   * Create a new toolbar.
   *
   * @param aLEC
   *        Current layout execution context. Never <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static BootstrapButtonToolbar createToolbar (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return new BootstrapButtonToolbar (aLEC);
  }

  /**
   * Create a new error box
   *
   * @param sErrorMsg
   *        The error message to emit.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static BootstrapErrorBox createErrorBox (@Nullable final String sErrorMsg)
  {
    return new BootstrapErrorBox ().addChild (sErrorMsg);
  }

  @Nonnull
  public static BootstrapErrorBox createIncorrectInputBox (@Nonnull final ISimpleWebExecutionContext aSWEC)
  {
    final Locale aDisplayLocale = aSWEC.getDisplayLocale ();
    return new BootstrapErrorBox ().addChild (EPhotonCoreText.ERR_INCORRECT_INPUT.getDisplayText (aDisplayLocale));
  }
}
