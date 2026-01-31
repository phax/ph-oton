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
import org.jspecify.annotations.Nullable;

import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroCDATA;
import com.helger.xml.microdom.MicroCDATA;

/**
 * Represents a single CDATA node as HC node.
 *
 * @author Philip Helger
 */
public class HCCDATANode extends AbstractHCNode
{
  private final String m_sText;

  public HCCDATANode (@Nullable final String sText)
  {
    m_sText = sText == null ? "" : sText;
  }

  /**
   * @return The unescaped CDATA content text.
   */
  @NonNull
  public String getText ()
  {
    return m_sText;
  }

  @Override
  @NonNull
  protected IMicroCDATA internalConvertToMicroNode (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroCDATA (m_sText);
  }

  @Override
  @NonNull
  public String getPlainText ()
  {
    return getText ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("text", m_sText).getToString ();
  }
}
