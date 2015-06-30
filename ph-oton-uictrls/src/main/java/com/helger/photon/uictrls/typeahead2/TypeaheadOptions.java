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
package com.helger.photon.uictrls.typeahead2;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.lang.ICloneable;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.js.builder.JSAssocArray;

@NotThreadSafe
public class TypeaheadOptions implements ICloneable <TypeaheadOptions>
{
  public static final String JSON_HIGHLIHT = "highlight";
  public static final String JSON_HINT = "hint";
  public static final String JSON_MIN_LENGTH = "minLength";

  public static final boolean DEFAULT_HIGHLIGHT = false;
  public static final boolean DEFAULT_HINT = true;
  public static final int DEFAULT_MIN_LENGTH = 1;

  private boolean m_bHighlight = DEFAULT_HIGHLIGHT;
  private boolean m_bHint = DEFAULT_HINT;
  private int m_nMinLength = DEFAULT_MIN_LENGTH;

  public TypeaheadOptions ()
  {}

  public TypeaheadOptions (@Nonnull final TypeaheadOptions aOther)
  {
    ValueEnforcer.notNull (aOther, "Other");
    m_bHighlight = aOther.m_bHighlight;
    m_bHint = aOther.m_bHint;
    m_nMinLength = aOther.m_nMinLength;
  }

  /**
   * @return If <code>true</code>, when suggestions are rendered, pattern
   *         matches for the current query in text nodes will be wrapped in a
   *         strong element. Defaults to <code>false</code>.
   */
  public boolean isHighlight ()
  {
    return m_bHighlight;
  }

  @Nonnull
  public TypeaheadOptions setHighlight (final boolean bHighlight)
  {
    m_bHighlight = bHighlight;
    return this;
  }

  /**
   * @return If <code>false</code>, the typeahead will not show a hint. Defaults
   *         to <code>true</code>.
   */
  public boolean isHint ()
  {
    return m_bHint;
  }

  @Nonnull
  public TypeaheadOptions setHint (final boolean bHint)
  {
    m_bHint = bHint;
    return this;
  }

  /**
   * @return The minimum character length needed before suggestions start
   *         getting rendered. Defaults to {@link #DEFAULT_MIN_LENGTH}.
   */
  @Nonnegative
  public int getMinLength ()
  {
    return m_nMinLength;
  }

  @Nonnull
  public TypeaheadOptions setMinLength (@Nonnegative final int nMinLength)
  {
    m_nMinLength = ValueEnforcer.isGT0 (nMinLength, "MinLength");
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getAsJSObject ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_bHighlight != DEFAULT_HIGHLIGHT)
      ret.add (JSON_HIGHLIHT, m_bHighlight);
    if (m_bHint != DEFAULT_HINT)
      ret.add (JSON_HINT, m_bHint);
    if (m_nMinLength != DEFAULT_MIN_LENGTH)
      ret.add (JSON_MIN_LENGTH, m_nMinLength);
    return ret;
  }

  @Nonnull
  public TypeaheadOptions getClone ()
  {
    return new TypeaheadOptions (this);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("highlight", m_bHighlight)
                                       .append ("hint", m_bHint)
                                       .append ("minLength", m_nMinLength)
                                       .toString ();
  }
}
