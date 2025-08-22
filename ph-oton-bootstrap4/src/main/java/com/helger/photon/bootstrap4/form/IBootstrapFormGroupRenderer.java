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
package com.helger.photon.bootstrap4.form;

import java.io.Serializable;
import java.util.Locale;

import com.helger.html.hc.html.IHCElementWithChildren;

import jakarta.annotation.Nonnull;

/**
 * Interface for rendering a form group based on the form style.
 *
 * @author Philip Helger
 */
public interface IBootstrapFormGroupRenderer extends Serializable
{
  boolean isUseIcons ();

  @Nonnull
  IBootstrapFormGroupRenderer setUseIcons (boolean bUseIcons);

  /**
   * @param aForm
   *        Form the form group belongs to. May not be <code>null</code>.
   * @param aFormGroup
   *        The form group to be rendered. May not be <code>null</code>.
   * @param aDisplayLocale
   *        Display locale to be used. May not be <code>null</code>.
   * @return Never <code>null</code>.
   */
  @Nonnull
  IHCElementWithChildren <?> renderFormGroup (@Nonnull IBootstrapFormGroupContainer <?> aForm,
                                              @Nonnull BootstrapFormGroup aFormGroup,
                                              @Nonnull Locale aDisplayLocale);
}
