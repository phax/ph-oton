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

import java.util.Locale;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.photon.bootstrap4.CBootstrap;
import com.helger.photon.bootstrap4.base.AbstractBootstrapDiv;
import com.helger.photon.bootstrap4.grid.BootstrapGridSpec;
import com.helger.photon.bootstrap4.grid.IBootstrapGridElement;

import jakarta.annotation.Nonnull;

@NotThreadSafe
public class BootstrapViewForm extends AbstractBootstrapDiv <BootstrapViewForm> implements IBootstrapFormGroupContainer <BootstrapViewForm>
{
  public static final ICSSClassProvider CSS_CLASS_VIEW_FORM = DefaultCSSClassProvider.create ("view-form");
  public static final int DEFAULT_LEFT_PART = 3;
  public static final int DEFAULT_RIGHT_PART = CBootstrap.GRID_SYSTEM_MAX - DEFAULT_LEFT_PART;

  private EBootstrapFormType m_eFormType;
  private BootstrapGridSpec m_aLeftGrid = BootstrapGridSpec.create (DEFAULT_LEFT_PART);
  private BootstrapGridSpec m_aRightGrid = BootstrapGridSpec.create (DEFAULT_RIGHT_PART);
  private IBootstrapFormGroupRenderer m_aFormGroupRenderer = new DefaultBootstrapFormGroupRenderer ();

  public BootstrapViewForm ()
  {
    addClass (CSS_CLASS_VIEW_FORM);
    // Must call the setter to ensure the class is present
    setFormType (EBootstrapFormType.DEFAULT);
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
  public final BootstrapViewForm setFormType (@Nonnull final EBootstrapFormType eFormType)
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
  public final BootstrapViewForm setLeft (@Nonnegative final int nLeftPartsXS,
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
  public final BootstrapViewForm setSplitting (@Nonnull final BootstrapGridSpec aLeft, @Nonnull final BootstrapGridSpec aRight)
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
  public final BootstrapViewForm setFormGroupRenderer (@Nonnull final IBootstrapFormGroupRenderer aFormGroupRenderer)
  {
    m_aFormGroupRenderer = ValueEnforcer.notNull (aFormGroupRenderer, "FormGroupRenderer");
    return this;
  }

  @Nonnull
  public IHCElementWithChildren <?> getRenderedFormGroup (@Nonnull final BootstrapFormGroup aFormGroup)
  {
    // TODO find Locale for rendering
    // Usually no error texts are used; so this is not sooo important
    return m_aFormGroupRenderer.renderFormGroup (this, aFormGroup, Locale.US);
  }

  @Nonnull
  public final BootstrapViewForm addFormGroup (@Nonnull final BootstrapFormGroup aFormGroup)
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
