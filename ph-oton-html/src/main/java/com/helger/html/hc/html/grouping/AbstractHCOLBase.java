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
package com.helger.html.hc.html.grouping;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;ol&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 * @param <ITEMTYPE>
 *        Item type
 */
public abstract class AbstractHCOLBase <IMPLTYPE extends AbstractHCOLBase <IMPLTYPE, ITEMTYPE>, ITEMTYPE extends IHCLI <ITEMTYPE>> extends
                                       AbstractHCList <IMPLTYPE, ITEMTYPE> implements
                                       IHCOLBase <IMPLTYPE, ITEMTYPE>
{
  private Integer m_aStart;
  private boolean m_bReversed = false;
  private EHCOLType m_eType;

  public AbstractHCOLBase (@Nonnull final Class <ITEMTYPE> aItemClass)
  {
    super (EHTMLElement.OL, aItemClass);
  }

  @Nullable
  public final Integer getStart ()
  {
    return m_aStart;
  }

  @Nonnull
  public final IMPLTYPE setStart (@Nullable final Integer aStart)
  {
    m_aStart = aStart;
    return thisAsT ();
  }

  public final boolean isReversed ()
  {
    return m_bReversed;
  }

  @Nonnull
  public final IMPLTYPE setReversed (final boolean bReversed)
  {
    m_bReversed = bReversed;
    return thisAsT ();
  }

  @Nullable
  public final EHCOLType getType ()
  {
    return m_eType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nullable final EHCOLType eType)
  {
    m_eType = eType;
    return thisAsT ();
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    if (m_aStart != null)
      aElement.setAttribute (CHTMLAttributes.START, m_aStart.toString ());
    if (m_bReversed)
      aElement.setAttribute (CHTMLAttributes.REVERSED, CHTMLAttributeValues.REVERSED);
    if (m_eType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_eType);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("start", m_aStart)
                            .append ("reversed", m_bReversed)
                            .appendIfNotNull ("type", m_eType)
                            .getToString ();
  }
}
