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
package com.helger.photon.bootstrap3.alert;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.base.BootstrapCloseIcon;

/**
 * Bootstrap alert box
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public abstract class AbstractBootstrapAlert <THISTYPE extends AbstractBootstrapAlert <THISTYPE>> extends AbstractHCDiv <THISTYPE>
{
  /**
   * This event fires immediately when the close instance method is called.
   */
  public static final String JS_EVENT_CLOSE = "close.bs.alert";
  /**
   * This event is fired when the alert has been closed (will wait for CSS
   * transitions to complete).
   */
  public static final String JS_EVENT_CLOSED = "closed.bs.alert";

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
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);
    addClasses (CBootstrapCSS.ALERT, m_eType);
    if (m_bShowClose)
    {
      addClass (CBootstrapCSS.ALERT_DISMISSABLE);
      addChild (0, new BootstrapCloseIcon ().setDataAttr ("dismiss", "alert"));
    }
  }
}
