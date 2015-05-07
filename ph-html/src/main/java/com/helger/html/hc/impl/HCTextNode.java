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
package com.helger.html.hc.impl;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroText;
import com.helger.commons.microdom.impl.MicroText;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Represents a single text node as HC node.
 *
 * @author Philip Helger
 */
public class HCTextNode extends AbstractHCNode
{
  private String m_sText;
  private boolean m_bEscape = MicroText.DEFAULT_ESCAPE;

  public HCTextNode (@Nullable final String sText)
  {
    setText (sText);
  }

  public HCTextNode (@Nonnull final char [] aChars)
  {
    setText (aChars);
  }

  public HCTextNode (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    setText (aChars, nOfs, nLen);
  }

  public HCTextNode (final char cChar)
  {
    this (Character.toString (cChar));
  }

  public HCTextNode (final int nText)
  {
    this (Integer.toString (nText));
  }

  public HCTextNode (final long nText)
  {
    this (Long.toString (nText));
  }

  /**
   * @return The unescaped text. Never <code>null</code>.
   */
  @Nonnull
  public String getText ()
  {
    return m_sText;
  }

  @Nonnull
  public HCTextNode setText (@Nullable final String sText)
  {
    m_sText = StringHelper.getNotNull (sText);
    return this;
  }

  @Nonnull
  public HCTextNode setText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars));
  }

  @Nonnull
  public HCTextNode setText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars, nOfs, nLen));
  }

  @Nonnull
  public HCTextNode prependText (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      m_sText = sText + m_sText;
    return this;
  }

  @Nonnull
  public HCTextNode prependText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars));
  }

  @Nonnull
  public HCTextNode prependText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars, nOfs, nLen));
  }

  @Nonnull
  public HCTextNode appendText (@Nullable final String sText)
  {
    if (StringHelper.hasText (sText))
      m_sText = m_sText + sText;
    return this;
  }

  @Nonnull
  public HCTextNode appendText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return appendText (new String (aChars));
  }

  @Nonnull
  public HCTextNode appendText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return appendText (new String (aChars, nOfs, nLen));
  }

  /**
   * Enable or disable XML escaping in the final document. By default all text
   * is escaped ({@link MicroText#DEFAULT_ESCAPE}), but for certain special
   * cases (like script elements in HTML), XML escaping must be disabled.
   *
   * @param bEscape
   *        <code>true</code> to enable escaping (default), <code>false</code>
   *        to disable it
   * @return this
   */
  @Nonnull
  public HCTextNode setEscape (final boolean bEscape)
  {
    m_bEscape = bEscape;
    return this;
  }

  /**
   * @return <code>true</code> if XML escaping is enabled, <code>false</code> if
   *         it is disabled
   */
  public boolean isEscape ()
  {
    return m_bEscape;
  }

  @Override
  @Nonnull
  protected IMicroText internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return new MicroText (m_sText).setEscape (m_bEscape);
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return m_sText;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("text", m_sText)
                            .append ("escape", m_bEscape)
                            .toString ();
  }

  @Nullable
  public static HCTextNode createOnDemand (@Nullable final String sText)
  {
    return sText == null ? null : new HCTextNode (sText);
  }
}
