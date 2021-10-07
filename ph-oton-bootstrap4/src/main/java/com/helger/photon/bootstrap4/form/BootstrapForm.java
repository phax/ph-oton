/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.EHTMLRole;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.AbstractHCForm;
import com.helger.photon.bootstrap4.CBootstrap;
import com.helger.photon.bootstrap4.grid.BootstrapGridSpec;
import com.helger.photon.bootstrap4.grid.IBootstrapGridElement;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;

@NotThreadSafe
public class BootstrapForm extends AbstractHCForm <BootstrapForm> implements IBootstrapFormGroupContainer <BootstrapForm>
{
  public static final int DEFAULT_LEFT_PART = 2;
  public static final int DEFAULT_RIGHT_PART = CBootstrap.GRID_SYSTEM_MAX - DEFAULT_LEFT_PART;

  private final Locale m_aDisplayLocale;
  private EBootstrapFormType m_eFormType = EBootstrapFormType.DEFAULT;
  private BootstrapGridSpec m_aLeftGrid = BootstrapGridSpec.create (CBootstrap.GRID_SYSTEM_MAX,
                                                                    DEFAULT_LEFT_PART,
                                                                    DEFAULT_LEFT_PART,
                                                                    DEFAULT_LEFT_PART,
                                                                    DEFAULT_LEFT_PART);
  private BootstrapGridSpec m_aRightGrid = BootstrapGridSpec.create (CBootstrap.GRID_SYSTEM_MAX,
                                                                     DEFAULT_RIGHT_PART,
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
    m_eFormType = eFormType;
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
  public BootstrapForm setLeft (@Nonnegative final int nLeftPartsXS,
                                @Nonnegative final int nLeftPartsSM,
                                @Nonnegative final int nLeftPartsMD,
                                @Nonnegative final int nLeftPartsLG,
                                @Nonnegative final int nLeftPartsXL)
  {
    ValueEnforcer.isBetweenInclusive (nLeftPartsXS, "LeftPartsXS", IBootstrapGridElement.MIN, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsSM, "LeftPartsSM", IBootstrapGridElement.MIN, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsMD, "LeftPartsMD", IBootstrapGridElement.MIN, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsLG, "LeftPartsLG", IBootstrapGridElement.MIN, CBootstrap.GRID_SYSTEM_MAX);
    ValueEnforcer.isBetweenInclusive (nLeftPartsXL, "LeftPartsXL", IBootstrapGridElement.MIN, CBootstrap.GRID_SYSTEM_MAX);

    final BootstrapGridSpec aNewLeft = BootstrapGridSpec.create (nLeftPartsXS, nLeftPartsSM, nLeftPartsMD, nLeftPartsLG, nLeftPartsXL);
    final BootstrapGridSpec aNewRight = BootstrapGridSpec.create (IBootstrapGridElement.getRight (nLeftPartsXS),
                                                                  IBootstrapGridElement.getRight (nLeftPartsSM),
                                                                  IBootstrapGridElement.getRight (nLeftPartsMD),
                                                                  IBootstrapGridElement.getRight (nLeftPartsLG),
                                                                  IBootstrapGridElement.getRight (nLeftPartsXL));
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

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClass (m_eFormType);
  }
}
