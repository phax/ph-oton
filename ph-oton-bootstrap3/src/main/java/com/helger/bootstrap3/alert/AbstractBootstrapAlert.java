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
package com.helger.bootstrap3.alert;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.bootstrap3.base.BootstrapCloseIcon;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCDiv;

/**
 * Bootstrap alert box
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public abstract class AbstractBootstrapAlert <THISTYPE extends AbstractBootstrapAlert <THISTYPE>> extends AbstractHCDiv <THISTYPE>
{
  /** By default the close box is not shown */
  public static final boolean DEFAULT_SHOW_CLOSE = false;

  private EBootstrapAlertType m_eType;
  private boolean m_bShowClose = DEFAULT_SHOW_CLOSE;

  public AbstractBootstrapAlert (@Nonnull final EBootstrapAlertType eType)
  {
    super ();
    setType (eType);
  }

  @Nonnull
  public EBootstrapAlertType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public THISTYPE setType (@Nonnull final EBootstrapAlertType eType)
  {
    m_eType = ValueEnforcer.notNull (eType, "Type");
    return thisAsT ();
  }

  public boolean isShowClose ()
  {
    return m_bShowClose;
  }

  @Nonnull
  public THISTYPE setShowClose (final boolean bShowClose)
  {
    m_bShowClose = bShowClose;
    return thisAsT ();
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.internalBeforeConvertToNode (aConversionSettings);
    addClasses (CBootstrapCSS.ALERT, m_eType);
    if (m_bShowClose)
    {
      addClass (CBootstrapCSS.ALERT_DISMISSABLE);
      addChild (0, new BootstrapCloseIcon ().setDataAttr ("dismiss", "alert"));
    }
  }
}
