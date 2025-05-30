/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.tabular;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;th&gt; element
 *
 * @author Philip Helger
 */
public class HCTH extends AbstractHCCell <HCTH>
{
  private EHCTHScope m_eScope;

  public HCTH ()
  {
    super (EHTMLElement.TH);
  }

  @Nullable
  public final EHCTHScope getScope ()
  {
    return m_eScope;
  }

  @Nonnull
  public final HCTH setScope (@Nullable final EHCTHScope eScope)
  {
    m_eScope = eScope;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_eScope != null)
      aElement.setAttribute (CHTMLAttributes.SCOPE, m_eScope.getAttrValue ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("Scope", m_eScope).getToString ();
  }
}
