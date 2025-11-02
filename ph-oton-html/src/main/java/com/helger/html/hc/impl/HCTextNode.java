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
package com.helger.html.hc.impl;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;

/**
 * Represents a single text node as HC node.
 *
 * @author Philip Helger
 */
public class HCTextNode extends AbstractHCTextNode <HCTextNode>
{
  public HCTextNode (@Nullable final String sText)
  {
    setText (sText);
  }

  public HCTextNode (@NonNull final char [] aChars)
  {
    setText (aChars);
  }

  public HCTextNode (@NonNull final char [] aChars, @Nonnegative final int nOfs, @Nonnegative final int nLen)
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

  @Nullable
  public static HCTextNode createOnDemand (@Nullable final String sText)
  {
    return sText == null ? null : new HCTextNode (sText);
  }

  @Nullable
  public static HCTextNode createOnDemand (@Nullable final char [] aText)
  {
    return aText == null ? null : new HCTextNode (aText);
  }
}
