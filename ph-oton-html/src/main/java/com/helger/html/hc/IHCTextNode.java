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
package com.helger.html.hc;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.trait.IGenericImplTrait;

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
  @NonNull
  String getText ();

  @NonNull
  IMPLTYPE setText (@Nullable String sText);

  @NonNull
  default IMPLTYPE setText (final char @NonNull [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars));
  }

  @NonNull
  default IMPLTYPE setText (final char @NonNull [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return setText (new String (aChars, nOfs, nLen));
  }

  @NonNull
  IMPLTYPE prependText (@Nullable String sText);

  @NonNull
  default IMPLTYPE prependText (final char @NonNull [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars));
  }

  @NonNull
  default IMPLTYPE prependText (final char @NonNull [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return prependText (new String (aChars, nOfs, nLen));
  }

  @NonNull
  IMPLTYPE addText (@Nullable String sText);

  @NonNull
  default IMPLTYPE addText (final char @NonNull [] aChars)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return addText (new String (aChars));
  }

  @NonNull
  default IMPLTYPE addText (final char @NonNull [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
  {
    ValueEnforcer.notNull (aChars, "Chars");
    return addText (new String (aChars, nOfs, nLen));
  }

  /**
   * Enable or disable XML escaping in the final document. By default all text is escaped
   * ({@link com.helger.xml.microdom.MicroText#DEFAULT_ESCAPE}), but for certain special cases (like
   * script elements in HTML), XML escaping must be disabled.
   *
   * @param bEscape
   *        <code>true</code> to enable escaping (default), <code>false</code> to disable it
   * @return this
   */
  @NonNull
  IMPLTYPE setEscape (boolean bEscape);

  /**
   * @return <code>true</code> if XML escaping is enabled, <code>false</code> if it is disabled
   */
  boolean isEscape ();
}
