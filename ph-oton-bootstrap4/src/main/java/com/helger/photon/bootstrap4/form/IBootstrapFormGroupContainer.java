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

import com.helger.annotation.Nonnegative;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.bootstrap4.grid.BootstrapGridSpec;

import jakarta.annotation.Nonnull;

/**
 * Base interface for a form group container.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IBootstrapFormGroupContainer <IMPLTYPE extends IBootstrapFormGroupContainer <IMPLTYPE>> extends IHCElement <IMPLTYPE>
{
  /**
   * @return The form type for aligning the form groups. Never <code>null</code>
   *         .
   */
  @Nonnull
  EBootstrapFormType getFormType ();

  /**
   * Set the left part of a horizontal form. This implicitly sets the correct
   * right parts (= CBootstrap.GRID_SYSTEM_MAX - left).
   *
   * @param nLeftParts
   *        The left parts. Must be &ge; 1 and &le; 12!
   * @return this
   */
  @Nonnull
  default IMPLTYPE setLeft (@Nonnegative final int nLeftParts)
  {
    return setLeft (nLeftParts, nLeftParts, nLeftParts, nLeftParts, nLeftParts);
  }

  /**
   * Set the left part of a horizontal form. This implicitly sets the correct
   * right parts (= CBootstrap.GRID_SYSTEM_MAX - left).
   *
   * @param nLeftPartsXS
   *        The left parts XS. Must be &ge; 1 and &le; 12!
   * @param nLeftPartsSM
   *        The left parts SM. Must be &ge; 1 and &le; 12!
   * @param nLeftPartsMD
   *        The left parts MD. Must be &ge; 1 and &le; 12!
   * @param nLeftPartsLG
   *        The left parts LG. Must be &ge; 1 and &le; 12!
   * @param nLeftPartsXL
   *        The left parts XL. Must be &ge; 1 and &le; 12!
   * @return this
   */
  @Nonnull
  IMPLTYPE setLeft (@Nonnegative int nLeftPartsXS,
                    @Nonnegative int nLeftPartsSM,
                    @Nonnegative int nLeftPartsMD,
                    @Nonnegative int nLeftPartsLG,
                    @Nonnegative int nLeftPartsXL);

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
   * Set the left part of a horizontal form.
   *
   * @param aLeft
   *        The left parts. Must not be <code>null</code>.
   * @param aRight
   *        The right parts. Must not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setSplitting (@Nonnull BootstrapGridSpec aLeft, @Nonnull BootstrapGridSpec aRight);

  /**
   * @return The renderer used to convert form groups into HC nodes. Never
   *         <code>null</code>.
   */
  @Nonnull
  IBootstrapFormGroupRenderer getFormGroupRenderer ();

  /**
   * Set the form group renderer to be used.
   *
   * @param aFormGroupRenderer
   *        The from group renderer. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE setFormGroupRenderer (@Nonnull IBootstrapFormGroupRenderer aFormGroupRenderer);

  /**
   * Add a new form group at the end.
   *
   * @param aFormGroup
   *        The form group to be added. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  IMPLTYPE addFormGroup (@Nonnull BootstrapFormGroup aFormGroup);

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
