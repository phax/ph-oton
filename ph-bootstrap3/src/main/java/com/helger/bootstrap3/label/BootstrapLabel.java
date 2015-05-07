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
package com.helger.bootstrap3.label;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCDiv;

/**
 * Bootstrap label
 *
 * @author Philip Helger
 */
public class BootstrapLabel extends AbstractHCDiv <BootstrapLabel>
{
  private EBootstrapLabelType m_eType;

  public BootstrapLabel ()
  {
    this (EBootstrapLabelType.DEFAULT);
  }

  public BootstrapLabel (@Nonnull final EBootstrapLabelType eType)
  {
    super ();
    setType (eType);
  }

  @Nonnull
  public EBootstrapLabelType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public BootstrapLabel setType (@Nonnull final EBootstrapLabelType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.internalBeforeConvertToNode (aConversionSettings);
    addClasses (CBootstrapCSS.LABEL, m_eType);
  }
}
