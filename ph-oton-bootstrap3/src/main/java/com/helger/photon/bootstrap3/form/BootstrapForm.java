/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.form;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.AbstractHCForm;
import com.helger.photon.bootstrap3.CBootstrap;
import com.helger.photon.bootstrap3.grid.BootstrapGridSpec;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;

@NotThreadSafe
public class BootstrapForm extends AbstractHCForm <BootstrapForm> implements IMutableBootstrapFormGroupContainer
{
  public static final int DEFAULT_LEFT_PART = 3;
  public static final int DEFAULT_RIGHT_PART = CBootstrap.GRID_SYSTEM_MAX - DEFAULT_LEFT_PART;

  private final Locale m_aDisplayLocale;
  private EBootstrapFormType m_eFormType = EBootstrapFormType.DEFAULT;
  private BootstrapGridSpec m_aLeftGrid = BootstrapGridSpec.create (CBootstrap.GRID_SYSTEM_MAX,
                                                                    DEFAULT_LEFT_PART,
                                                                    DEFAULT_LEFT_PART,
                                                                    DEFAULT_LEFT_PART);
  private BootstrapGridSpec m_aRightGrid = BootstrapGridSpec.create (CBootstrap.GRID_SYSTEM_MAX,
                                                                     DEFAULT_RIGHT_PART,
                                                                     DEFAULT_RIGHT_PART,
                                                                     DEFAULT_RIGHT_PART);
  private IBootstrapFormGroupRenderer m_aFormGroupRenderer = new DefaultBootstrapFormGroupRenderer ();

  public BootstrapForm (@Nonnull final ISimpleWebExecutionContext aLEC)
  {
    // Not needed, as the role is identical to the HTML semantics
    if (false)
      setRole (EHTMLRole.FORM);
    m_aDisplayLocale = aLEC.getDisplayLocale ();
  }

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

  @Nonnull
  public final BootstrapGridSpec getLeft ()
  {
    return m_aLeftGrid;
  }

  @Nonnull
  public final BootstrapGridSpec getRight ()
  {
    return m_aRightGrid;
  }

  @Nonnull
  @OverridingMethodsMustInvokeSuper
  public BootstrapForm setLeft (@Nonnegative final int nLeftParts)
  {
    return setLeft (nLeftParts, nLeftParts, nLeftParts, nLeftParts);
  }

  private static int _getRight (final int nLeft)
  {
    if (nLeft == CBootstrap.GRID_SYSTEM_MAX)
      return CBootstrap.GRID_SYSTEM_MAX;
    return CBootstrap.GRID_SYSTEM_MAX - nLeft;
  }

  @Nonnull
  @OverridingMethodsMustInvokeSuper
  public BootstrapForm setLeft (@Nonnegative final int nLeftPartsXS,
                                @Nonnegative final int nLeftPartsSM,
                                @Nonnegative final int nLeftPartsMD,
                                @Nonnegative final int nLeftPartsLG)
  {
    ValueEnforcer.isBetweenInclusive (nLeftPartsXS, "LeftPartsXS", 0, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsSM, "LeftPartsSM", 0, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsMD, "LeftPartsMD", 0, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsLG, "LeftPartsLG", 0, CBootstrap.GRID_SYSTEM_MAX);

    final BootstrapGridSpec aNewLeft = BootstrapGridSpec.create (nLeftPartsXS,
                                                                 nLeftPartsSM,
                                                                 nLeftPartsMD,
                                                                 nLeftPartsLG);
    final BootstrapGridSpec aNewRight = BootstrapGridSpec.create (_getRight (nLeftPartsXS),
                                                                  _getRight (nLeftPartsSM),
                                                                  _getRight (nLeftPartsMD),
                                                                  _getRight (nLeftPartsLG));
    return setSplitting (aNewLeft, aNewRight);
  }

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
  public IHCElementWithChildren <?> getRenderedFormGroup (@Nonnull final BootstrapFormGroup aFormGroup)
  {
    return m_aFormGroupRenderer.renderFormGroup (this, aFormGroup, m_aDisplayLocale);
  }

  @Nonnull
  public BootstrapForm addFormGroup (@Nonnull final BootstrapFormGroup aFormGroup)
  {
    // Must be added directly and cannot be added via a proxy, because
    // otherwise, the adding may happen after the out of band nodes were
    // extracted!
    return addChild (getRenderedFormGroup (aFormGroup));
  }
}
