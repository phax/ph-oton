/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.impl;

import org.jspecify.annotations.NonNull;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.entity.EHTMLEntity;
import com.helger.html.entity.IHTMLEntity;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroEntityReference;
import com.helger.xml.microdom.MicroEntityReference;

/**
 * Represents an entity reference as HC node.
 *
 * @author Philip Helger
 */
public class HCEntityNode extends AbstractHCNode
{
  private final IHTMLEntity m_aEntity;
  private final String m_sPlainText;

  public HCEntityNode (@NonNull final EHTMLEntity eEntity)
  {
    this (eEntity, eEntity.getCharString ());
  }

  public HCEntityNode (@NonNull final IHTMLEntity aEntity, @NonNull final String sPlainText)
  {
    ValueEnforcer.notNull (aEntity, "Entity");
    ValueEnforcer.notNull (sPlainText, "PlainText");
    m_aEntity = aEntity;
    m_sPlainText = sPlainText;
  }

  @NonNull
  public IHTMLEntity getEntity ()
  {
    return m_aEntity;
  }

  @Override
  @NonNull
  protected IMicroEntityReference internalConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroEntityReference (m_aEntity.getEntityName ());
  }

  @Override
  @NonNull
  public String getPlainText ()
  {
    return m_sPlainText;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("entity", m_aEntity).append ("plainText", m_sPlainText).getToString ();
  }

  @NonNull
  public static HCEntityNode newNBSP ()
  {
    return new HCEntityNode (EHTMLEntity.nbsp, " ");
  }

  @NonNull
  public static HCEntityNode newCopy ()
  {
    return new HCEntityNode (EHTMLEntity.copy, "©");
  }

  @NonNull
  public static HCEntityNode newEuro ()
  {
    return new HCEntityNode (EHTMLEntity.euro, "€");
  }

  @NonNull
  public static HCEntityNode newLeftArrow ()
  {
    return new HCEntityNode (EHTMLEntity.larr, "<-");
  }

  @NonNull
  public static HCEntityNode newRightArrow ()
  {
    return new HCEntityNode (EHTMLEntity.rarr, "->");
  }

  @NonNull
  public static HCEntityNode newUpArrow ()
  {
    return new HCEntityNode (EHTMLEntity.uarr, "^");
  }

  @NonNull
  public static HCEntityNode newDownArrow ()
  {
    return new HCEntityNode (EHTMLEntity.darr, "v");
  }

  /**
   * @return dash of length "n"
   */
  @NonNull
  public static HCEntityNode newNDash ()
  {
    return new HCEntityNode (EHTMLEntity.ndash, "-");
  }

  /**
   * @return soft hyphen
   */
  @NonNull
  public static HCEntityNode newShy ()
  {
    return new HCEntityNode (EHTMLEntity.shy, "");
  }

  /**
   * @return per mille sign
   */
  @NonNull
  public static HCEntityNode newPerMille ()
  {
    return new HCEntityNode (EHTMLEntity.permil, "‰");
  }

  /**
   * @return "times" sign
   */
  @NonNull
  public static HCEntityNode times ()
  {
    return new HCEntityNode (EHTMLEntity.times, "x");
  }
}
