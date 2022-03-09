/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.interactive;

import javax.annotation.Nonnull;

import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.xml.microdom.IMicroElement;

public class HCDetails extends AbstractHCElementWithChildren <HCDetails>
{
  public static final boolean DEFAULT_OPEN = false;

  private boolean m_bOpen = DEFAULT_OPEN;

  public HCDetails ()
  {
    super (EHTMLElement.DETAILS);
  }

  public final boolean isOpen ()
  {
    return m_bOpen;
  }

  @Nonnull
  public final HCDetails setOpen (final boolean bOpen)
  {
    m_bOpen = bOpen;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    if (m_bOpen)
      aElement.setAttribute (CHTMLAttributes.OPEN, CHTMLAttributeValues.OPEN);
  }
}
