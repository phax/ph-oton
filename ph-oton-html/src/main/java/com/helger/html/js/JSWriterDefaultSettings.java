/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.js;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.system.ENewLineMode;

/**
 * Default settings for the textual representation of JSDOM objects
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class JSWriterDefaultSettings
{
  public static final boolean DEFAULT_INDENT_AND_ALIGN = true;
  public static final boolean DEFAULT_GENERATE_TYPE_NAMES = false;
  public static final boolean DEFAULT_GENERATE_COMMENTS = false;
  public static final String DEFAULT_INDENT = "  ";

  private static boolean s_bIndentAndAlign = DEFAULT_INDENT_AND_ALIGN;
  private static boolean s_bGenerateTypeNames = DEFAULT_GENERATE_TYPE_NAMES;
  private static boolean s_bGenerateComments = DEFAULT_GENERATE_COMMENTS;
  private static String s_sIndent = DEFAULT_INDENT;
  private static ENewLineMode s_eNewLineMode = ENewLineMode.DEFAULT;

  private JSWriterDefaultSettings ()
  {}

  public static void setToDefault ()
  {
    s_bIndentAndAlign = DEFAULT_INDENT_AND_ALIGN;
    s_bGenerateTypeNames = DEFAULT_GENERATE_TYPE_NAMES;
    s_bGenerateComments = DEFAULT_GENERATE_COMMENTS;
    s_sIndent = DEFAULT_INDENT;
    s_eNewLineMode = ENewLineMode.DEFAULT;
  }

  public static void setIndentAndAlign (final boolean bIndentAndAlign)
  {
    s_bIndentAndAlign = bIndentAndAlign;
  }

  public static boolean isIndentAndAlign ()
  {
    return s_bIndentAndAlign;
  }

  public static void setGenerateTypeNames (final boolean bGenerateTypeNames)
  {
    s_bGenerateTypeNames = bGenerateTypeNames;
  }

  public static boolean isGenerateTypeNames ()
  {
    return s_bGenerateTypeNames;
  }

  public static void setGenerateComments (final boolean bGenerateComments)
  {
    s_bGenerateComments = bGenerateComments;
  }

  public static boolean isGenerateComments ()
  {
    return s_bGenerateComments;
  }

  /**
   * This is a wrapper around {@link #setIndentAndAlign(boolean)},
   * {@link #setGenerateTypeNames(boolean)} and
   * {@link #setGenerateComments(boolean)}
   *
   * @param bMinimumCodeSize
   *        true for minimum code size
   */
  public static void setMinimumCodeSize (final boolean bMinimumCodeSize)
  {
    setIndentAndAlign (!bMinimumCodeSize);
    setGenerateTypeNames (!bMinimumCodeSize);
    setGenerateComments (!bMinimumCodeSize);
  }

  public static void setIndent (@Nonnull @Nonempty final String sIndent)
  {
    s_sIndent = ValueEnforcer.notEmpty (sIndent, "Indent");
  }

  @Nonnull
  @Nonempty
  public static String getIndent ()
  {
    return s_sIndent;
  }

  public static void setNewLineMode (@Nonnull final ENewLineMode eNewLineMode)
  {
    s_eNewLineMode = ValueEnforcer.notNull (eNewLineMode, "NewLineMode");
  }

  @Nonnull
  public static ENewLineMode getNewLineMode ()
  {
    return s_eNewLineMode;
  }
}
