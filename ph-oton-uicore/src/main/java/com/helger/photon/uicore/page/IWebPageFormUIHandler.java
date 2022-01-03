/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.forms.IHCForm;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.uicore.html.toolbar.IButtonToolbar;

public interface IWebPageFormUIHandler <FORM_TYPE extends IHCForm <FORM_TYPE>, TOOLBAR_TYPE extends IButtonToolbar <TOOLBAR_TYPE>> extends
                                       IWebPageUIHandler
{
  /**
   * Create a new in-page header
   *
   * @param sHeaderText
   *        The header text to be displayed. May be <code>null</code> or empty.
   * @return <code>null</code> if the passed header text is <code>null</code> or
   *         empty, a non-<code>null</code> node otherwise.
   */
  @Nullable
  IHCNode createPageHeader (@Nullable String sHeaderText);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A form that links to the current page.
   */
  @Nonnull
  default FORM_TYPE createFormSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormSelf (aLEC, false);
  }

  /**
   * @param aLEC
   *        Layout execution context
   * @param bIsFormSubmitted
   *        <code>true</code> if the form was submitted for validation,
   *        <code>false</code> otherwise
   * @return A form that links to the current page.
   */
  @Nonnull
  FORM_TYPE createFormSelf (@Nonnull ILayoutExecutionContext aLEC, boolean bIsFormSubmitted);

  /**
   * @param aLEC
   *        Layout execution context
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  default FORM_TYPE createFormFileUploadSelf (@Nonnull final ILayoutExecutionContext aLEC)
  {
    return createFormFileUploadSelf (aLEC, false);
  }

  /**
   * @param aLEC
   *        Layout execution context
   * @param bIsFormSubmitted
   *        <code>true</code> if the form was submitted for validation,
   *        <code>false</code> otherwise
   * @return A file upload form that links to the current page.
   */
  @Nonnull
  FORM_TYPE createFormFileUploadSelf (@Nonnull ILayoutExecutionContext aLEC, boolean bIsFormSubmitted);

  @Nonnull
  TOOLBAR_TYPE createToolbar (@Nonnull ILayoutExecutionContext aWPEC);

  @Nullable
  IHCNode createErrorBox (@Nonnull ILayoutExecutionContext aWPEC, @Nullable String sErrorMsg);

  @Nullable
  IHCNode createIncorrectInputBox (@Nonnull ILayoutExecutionContext aWPEC);
}
