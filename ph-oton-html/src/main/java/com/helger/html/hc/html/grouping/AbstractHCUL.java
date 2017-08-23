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
package com.helger.html.hc.html.grouping;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;ul&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCUL <IMPLTYPE extends AbstractHCUL <IMPLTYPE>> extends AbstractHCListLI <IMPLTYPE>
                                   implements IHCUL <IMPLTYPE>
{
  private EHCULType m_eType;

  public AbstractHCUL ()
  {
    super (EHTMLElement.UL);
  }

  @Nullable
  public final EHCULType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nullable final EHCULType eType)
  {
    m_eType = eType;
    return thisAsT ();
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).appendIfNotNull ("type", m_eType).getToString ();
  }
}
