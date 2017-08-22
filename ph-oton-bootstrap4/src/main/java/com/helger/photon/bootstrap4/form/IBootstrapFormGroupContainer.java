/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.bootstrap4.grid.BootstrapGridSpec;

/**
 * Base interface for a form group container.
 *
 * @author Philip Helger
 */
public interface IBootstrapFormGroupContainer
{
  /**
   * @return The form type for aligning the form groups. Never <code>null</code>
   *         .
   */
  @Nonnull
  EBootstrapFormType getFormType ();

  /**
   * @return The left parts. Always &ge; 1 and &le; CBootstrap.GRID_SYSTEM_MAX.
   *         Never <code>null</code>.
   */
  @Nonnull
  BootstrapGridSpec getLeft ();

  /**
   * @return The right parts. Always
   *         <code>CBootstrap.GRID_SYSTEM_MAX - getLeft ()</code> except left is
   *         <code>CBootstrap.GRID_SYSTEM_MAX</code> than right is also
   *         <code>CBootstrap.GRID_SYSTEM_MAX</code>. Never <code>null</code>.
   */
  @Nonnull
  BootstrapGridSpec getRight ();

  /**
   * @return The renderer used to convert form groups into HC nodes. Never
   *         <code>null</code>.
   */
  @Nonnull
  IBootstrapFormGroupRenderer getFormGroupRenderer ();

  /**
   * Get the rendered form group based on the contained form group renderer. The
   * form group is NOT added to this container!
   *
   * @param aFormGroup
   *        The form group to be rendered. May not be <code>null</code>.
   * @return The rendered form group and never <code>null</code>.
   */
  @Nonnull
  IHCElementWithChildren <?> getRenderedFormGroup (@Nonnull BootstrapFormGroup aFormGroup);
}
