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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;base&gt; element
 *
 * @author Philip Helger
 */
public class HCBase extends AbstractHCElement <HCBase>
{
  private String m_sHref;
  private HC_Target m_aTarget;

  public HCBase ()
  {
    super (EHTMLElement.BASE);
  }

  @Nullable
  public String getHref ()
  {
    return m_sHref;
  }

  @Nonnull
  public HCBase setHref (@Nullable final ISimpleURL aHref)
  {
    return setHref (aHref == null ? null : aHref.getAsString ());
  }

  @Nonnull
  public HCBase setHref (@Nullable final String sHref)
  {
    m_sHref = sHref;
    return this;
  }

  @Nullable
  public HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  public HCBase setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return this;
  }

  @Override
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return StringHelper.hasText (m_sHref) || m_aTarget != null;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sHref))
      aElement.setAttribute (CHTMLAttributes.HREF, m_sHref);
    if (m_aTarget != null)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget.getAttrValue ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("href", m_sHref)
                            .append ("target", m_aTarget)
                            .toString ();
  }
}
