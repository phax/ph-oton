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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.IMicroComment;
import com.helger.xml.microdom.MicroComment;
import com.helger.xml.serialize.write.XMLEmitter;

/**
 * Represents a single CDATA node as HC node.
 *
 * @author Philip Helger
 */
public class HCCommentNode extends AbstractHCNode
{
  private final String m_sText;

  public HCCommentNode (@Nullable final String sText)
  {
    m_sText = sText == null ? "" : sText;
    if (m_sText.contains (XMLEmitter.COMMENT_START) || m_sText.contains (XMLEmitter.COMMENT_END))
      throw new IllegalArgumentException ("XML comment contains nested XML comment: " + m_sText);
  }

  /**
   * @return The unescaped comment text. Never <code>null</code>.
   */
  @Nonnull
  public String getText ()
  {
    return m_sText;
  }

  @Override
  @Nonnull
  protected IMicroComment internalConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroComment (m_sText);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("text", m_sText).getToString ();
  }
}
