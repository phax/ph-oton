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
package com.helger.bootstrap3.form;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.bootstrap3.CBootstrap;
import com.helger.bootstrap3.grid.BootstrapGridSpec;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.html.AbstractHCForm;

@NotThreadSafe
public class BootstrapForm extends AbstractHCForm <BootstrapForm>
{
  public static final int DEFAULT_LEFT_PART = 3;
  public static final int DEFAULT_RIGHT_PART = CBootstrap.GRID_SYSTEM_MAX - DEFAULT_LEFT_PART;

  private EBootstrapFormType m_eFormType;
  private BootstrapGridSpec m_aLeftGrid = BootstrapGridSpec.create (DEFAULT_LEFT_PART);
  private BootstrapGridSpec m_aRightGrid = BootstrapGridSpec.create (DEFAULT_RIGHT_PART);
  private IBootstrapFormGroupRenderer m_aFormGroupRenderer = new BootstrapFormGroupRendererDefault ();

  public BootstrapForm ()
  {
    this ((ISimpleURL) null);
  }

  public BootstrapForm (@Nullable final ISimpleURL aAction)
  {
    this (aAction, EBootstrapFormType.DEFAULT);
  }

  public BootstrapForm (@Nonnull final EBootstrapFormType eFormType)
  {
    this ((ISimpleURL) null, eFormType);
  }

  public BootstrapForm (@Nullable final String sAction)
  {
    this (sAction, EBootstrapFormType.DEFAULT);
  }

  public BootstrapForm (@Nullable final ISimpleURL aAction, @Nonnull final EBootstrapFormType eFormType)
  {
    this (aAction == null ? null : aAction.getAsString (), eFormType);
  }

  public BootstrapForm (@Nullable final String sAction, @Nonnull final EBootstrapFormType eFormType)
  {
    super ();
    setRole (EHTMLRole.FORM);
    if (sAction != null)
      setAction (sAction);
    setFormType (eFormType);
  }

  /**
   * @return The form type as specified in the constructor. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final EBootstrapFormType getFormType ()
  {
    return m_eFormType;
  }

  /**
   * @param eFormType
   *        The form type to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public final BootstrapForm setFormType (@Nonnull final EBootstrapFormType eFormType)
  {
    ValueEnforcer.notNull (eFormType, "FormType");

    if (!eFormType.equals (m_eFormType))
    {
      removeClass (m_eFormType);
      addClass (eFormType);
      m_eFormType = eFormType;
    }
    return this;
  }

  /**
   * @return The left parts. Always &ge; 1 and &lt; CBootstrap.GRID_SYSTEM_MAX.
   *         Never <code>null</code>.
   */
  @Nonnull
  public final BootstrapGridSpec getLeft ()
  {
    return m_aLeftGrid;
  }

  /**
   * @return The right parts. Always
   *         <code>CBootstrap.GRID_SYSTEM_MAX - getLeft ()</code>. Never
   *         <code>null</code>.
   */
  @Nonnull
  public final BootstrapGridSpec getRight ()
  {
    return m_aRightGrid;
  }

  /**
   * Set the left part of a horizontal form. This implies setting the correct
   * right parts (= CBootstrap.GRID_SYSTEM_MAX - left).
   *
   * @param nLeftParts
   *        The left parts. Must be &ge; 1 and &lt; 12!
   * @return this
   */
  @Nonnull
  @OverridingMethodsMustInvokeSuper
  public BootstrapForm setLeft (@Nonnegative final int nLeftParts)
  {
    return setLeft (nLeftParts, nLeftParts, nLeftParts, nLeftParts);
  }

  /**
   * Set the left part of a horizontal form. This implies setting the correct
   * right parts (= CBootstrap.GRID_SYSTEM_MAX - left).
   *
   * @param nLeftPartsXS
   *        The left parts XS. Must be &ge; 1 and &lt; 12!
   * @param nLeftPartsSM
   *        The left parts SM. Must be &ge; 1 and &lt; 12!
   * @param nLeftPartsMD
   *        The left parts MD. Must be &ge; 1 and &lt; 12!
   * @param nLeftPartsLG
   *        The left parts LG. Must be &ge; 1 and &lt; 12!
   * @return this
   */
  @Nonnull
  @OverridingMethodsMustInvokeSuper
  public BootstrapForm setLeft (@Nonnegative final int nLeftPartsXS,
                                @Nonnegative final int nLeftPartsSM,
                                @Nonnegative final int nLeftPartsMD,
                                @Nonnegative final int nLeftPartsLG)
  {
    ValueEnforcer.isBetweenInclusive (nLeftPartsXS, "LeftPartsXS", 1, CBootstrap.GRID_SYSTEM_MAX - 1);
    ValueEnforcer.isBetweenInclusive (nLeftPartsSM, "LeftPartsSM", 1, CBootstrap.GRID_SYSTEM_MAX - 1);
    ValueEnforcer.isBetweenInclusive (nLeftPartsMD, "LeftPartsMD", 1, CBootstrap.GRID_SYSTEM_MAX - 1);
    ValueEnforcer.isBetweenInclusive (nLeftPartsLG, "LeftPartsLG", 1, CBootstrap.GRID_SYSTEM_MAX - 1);

    final BootstrapGridSpec aNewLeft = BootstrapGridSpec.create (nLeftPartsXS, nLeftPartsSM, nLeftPartsMD, nLeftPartsLG);
    final BootstrapGridSpec aNewRight = BootstrapGridSpec.create (CBootstrap.GRID_SYSTEM_MAX - nLeftPartsXS,
                                                                  CBootstrap.GRID_SYSTEM_MAX - nLeftPartsSM,
                                                                  CBootstrap.GRID_SYSTEM_MAX - nLeftPartsMD,
                                                                  CBootstrap.GRID_SYSTEM_MAX - nLeftPartsLG);
    return setSplitting (aNewLeft, aNewRight);
  }

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
  @OverridingMethodsMustInvokeSuper
  public BootstrapForm setSplitting (@Nonnull final BootstrapGridSpec aLeft, @Nonnull final BootstrapGridSpec aRight)
  {
    ValueEnforcer.notNull (aLeft, "Left");
    ValueEnforcer.notNull (aRight, "Right");
    m_aLeftGrid = aLeft;
    m_aRightGrid = aRight;
    return this;
  }

  @Nonnull
  public IBootstrapFormGroupRenderer getFormGroupRenderer ()
  {
    return m_aFormGroupRenderer;
  }

  @Nonnull
  public BootstrapForm setFormGroupRenderer (@Nonnull final IBootstrapFormGroupRenderer aFormGroupRenderer)
  {
    m_aFormGroupRenderer = ValueEnforcer.notNull (aFormGroupRenderer, "FormGroupRenderer");
    return this;
  }

  @Nonnull
  public BootstrapForm addFormGroup (@Nonnull final BootstrapFormGroup aFormGroup)
  {
    // Must be added directly and cannot be added via a proxy, because
    // otherwise, the adding may happen after the out of band nodes were
    // extracted!
    final IHCElement <?> aRendererNode = m_aFormGroupRenderer.renderFormGroup (this, aFormGroup);
    return addChild (aRendererNode);
  }
}
