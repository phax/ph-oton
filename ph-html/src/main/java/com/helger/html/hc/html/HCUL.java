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
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.api.EHCULType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents an HTML &lt;ul&gt; element
 *
 * @author Philip Helger
 */
public class HCUL extends AbstractHCList <HCUL>
{
  private EHCULType m_eType;

  public HCUL ()
  {
    super (EHTMLElement.UL);
  }

  @Nullable
  public final EHCULType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final HCUL setType (@Nullable final EHCULType eType)
  {
    m_eType = eType;
    return this;
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("type", m_eType).toString ();
  }
}
