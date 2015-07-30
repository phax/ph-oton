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
package com.helger.photon.bootstrap3.panel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.html.hchtml.base.AbstractHCDiv;
import com.helger.html.hchtml.impl.HCDiv;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Wrapper for a Bootstrap3 panel.
 *
 * @author Philip Helger
 */
public class BootstrapPanel extends AbstractHCDiv <BootstrapPanel>
{
  private final EBootstrapPanelType m_eType;
  private HCDiv m_aHeader = null;
  private final HCDiv m_aBody;
  private HCDiv m_aFooter = null;

  public BootstrapPanel ()
  {
    this (EBootstrapPanelType.DEFAULT);
  }

  /**
   * Create a new Panel element
   * 
   * @param eType
   *        Panel type. May not be <code>null</code>.
   */
  public BootstrapPanel (@Nonnull final EBootstrapPanelType eType)
  {
    ValueEnforcer.notNull (eType, "Type");

    addClasses (CBootstrapCSS.PANEL, eType);
    m_eType = eType;
    m_aBody = addAndReturnChild (new HCDiv ().addClass (CBootstrapCSS.PANEL_BODY));
  }

  @Nonnull
  public EBootstrapPanelType getType ()
  {
    return m_eType;
  }

  public boolean hasHeader ()
  {
    return m_aHeader != null;
  }

  @Nonnull
  public HCDiv getOrCreateHeader ()
  {
    if (m_aHeader == null)
    {
      m_aHeader = new HCDiv ().addClass (CBootstrapCSS.PANEL_HEADING);
      addChild (0, m_aHeader);
    }
    return m_aHeader;
  }

  @Nullable
  public HCDiv getHeader ()
  {
    return m_aHeader;
  }

  @Nonnull
  public HCDiv getBody ()
  {
    return m_aBody;
  }

  public boolean hasFooter ()
  {
    return m_aFooter != null;
  }

  @Nonnull
  public HCDiv getOrCreateFooter ()
  {
    if (m_aFooter == null)
    {
      m_aFooter = new HCDiv ().addClass (CBootstrapCSS.PANEL_FOOTER);
      addChild (m_aFooter);
    }
    return m_aFooter;
  }

  @Nullable
  public HCDiv getFooter ()
  {
    return m_aFooter;
  }
}
