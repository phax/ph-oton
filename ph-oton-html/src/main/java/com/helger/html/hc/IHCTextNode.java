/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.hc;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.traits.IGenericImplTrait;

/**
 * This class represents a text node.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCTextNode <IMPLTYPE extends IHCTextNode <IMPLTYPE>> extends IHCNode, IGenericImplTrait <IMPLTYPE>
{
  /**
   * @return The unescaped text. Never <code>null</code>.
   */
  @Nonnull
  String getText ();

  @Nonnull
  IMPLTYPE setText (@Nullable String sText);

  @Nonnull
  default IMPLTYPE setText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars));
  }

  @Nonnull
  default IMPLTYPE setText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars, nOfs, nLen));
  }

  @Nonnull
  IMPLTYPE prependText (@Nullable String sText);

  @Nonnull
  default IMPLTYPE prependText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars));
  }

  @Nonnull
  default IMPLTYPE prependText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars, nOfs, nLen));
  }

  @Nonnull
  IMPLTYPE appendText (@Nullable String sText);

  @Nonnull
  default IMPLTYPE appendText (@Nonnull final char [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return appendText (new String (aChars));
  }

  @Nonnull
  default IMPLTYPE appendText (@Nonnull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return appendText (new String (aChars, nOfs, nLen));
  }

  /**
   * Enable or disable XML escaping in the final document. By default all text
   * is escaped ({@link com.helger.xml.microdom.MicroText#DEFAULT_ESCAPE}), but
   * for certain special cases (like script elements in HTML), XML escaping must
   * be disabled.
   *
   * @param bEscape
   *        <code>true</code> to enable escaping (default), <code>false</code>
   *        to disable it
   * @return this
   */
  @Nonnull
  IMPLTYPE setEscape (boolean bEscape);

  /**
   * @return <code>true</code> if XML escaping is enabled, <code>false</code> if
   *         it is disabled
   */
  boolean isEscape ();
}
