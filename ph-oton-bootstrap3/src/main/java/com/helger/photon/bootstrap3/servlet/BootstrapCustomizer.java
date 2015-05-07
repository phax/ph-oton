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
package com.helger.photon.bootstrap3.servlet;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.gfx.ScalableSize;
import com.helger.commons.version.Version;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCControl;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.customize.HCEmptyCustomizer;
import com.helger.html.hc.html.HCImg;
import com.helger.photon.bootstrap3.BootstrapHelper;
import com.helger.photon.bootstrap3.CBootstrap;

public class BootstrapCustomizer extends HCEmptyCustomizer
{
  private final Version m_aBootstrapVersion;

  public BootstrapCustomizer ()
  {
    this (CBootstrap.BOOTSTRAP_VERSION_334);
  }

  public BootstrapCustomizer (@Nonnull final Version aBootstrapVersion)
  {
    m_aBootstrapVersion = ValueEnforcer.notNull (aBootstrapVersion, "BootstrapVersion");
  }

  @Nonnull
  public final Version getBootstrapVersion ()
  {
    return m_aBootstrapVersion;
  }

  @Override
  public void customizeNode (@Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aParentElement,
                             @Nonnull final IHCNode aNode,
                             @Nonnull final EHTMLVersion eHTMLVersion)
  {
    if (aNode instanceof HCImg)
    {
      final HCImg aImg = (HCImg) aNode;
      final ScalableSize aExtent = aImg.getExtent ();
      // Workaround for IE if a CSS contains "width:auto" and/or "height:auto"
      // See e.g. https://github.com/twitter/bootstrap/issues/1899
      if (aExtent != null)
      {
        aImg.addStyles (CCSSProperties.WIDTH.newValue (ECSSUnit.px (aExtent.getWidth ())),
                        CCSSProperties.HEIGHT.newValue (ECSSUnit.px (aExtent.getHeight ())));
      }
    }
    else
      if (aNode instanceof IHCControl <?>)
      {
        BootstrapHelper.markAsFormControl ((IHCControl <?>) aNode);
      }
  }
}
