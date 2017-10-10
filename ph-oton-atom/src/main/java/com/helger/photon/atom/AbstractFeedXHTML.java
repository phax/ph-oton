/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.atom;

import javax.annotation.Nonnull;
import javax.xml.XMLConstants;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.CHTMLDocTypes;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.config.HCConversionSettings;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.render.HCRenderer;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroElement;

public abstract class AbstractFeedXHTML extends AbstractFeedElement
{
  // Feed XML always as XHTML 1.1
  private static final HCConversionSettings HCCONV_SETTINGS = new HCConversionSettings (HCSettings.getConversionSettings (),
                                                                                        EHTMLVersion.XHTML11).setToOptimized ();

  private final HCDiv m_aDiv;

  public AbstractFeedXHTML (@Nonnull final HCDiv aDiv)
  {
    ValueEnforcer.notNull (aDiv, "Div");
    m_aDiv = aDiv;
  }

  @Nonnull
  @Nonempty
  public final String getType ()
  {
    return EFeedTextType.XHTML.getType ();
  }

  @Nonnull
  public final HCDiv getDIV ()
  {
    return m_aDiv;
  }

  public final IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.setAttribute ("type", getType ());
    {
      final IMicroNode aDivNode = HCRenderer.getAsNode (m_aDiv, HCCONV_SETTINGS);
      ((IMicroElement) aDivNode).setNamespaceURI (CHTMLDocTypes.DOCTYPE_XHTML_URI);
      aElement.appendChild (aDivNode);
    }
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public final boolean isValid ()
  {
    return true;
  }
}
