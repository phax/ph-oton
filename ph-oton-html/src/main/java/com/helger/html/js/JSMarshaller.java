/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.string.StringHelper;

/**
 * JavaScript String helper
 *
 * @author Philip Helger
 */
@Immutable
public final class JSMarshaller
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JSMarshaller.class);

  private static final char [] CHARS_TO_MASK = new char [] { '"', '\'', '\\', '/', '\t', '\r', '\n', '\f' };
  private static final char [] CHARS_TO_MASK_REGEX = new char [] { '\\',
                                                                   '^',
                                                                   '$',
                                                                   '*',
                                                                   '+',
                                                                   '?',
                                                                   '|',
                                                                   '.',
                                                                   '-',
                                                                   '[',
                                                                   ']',
                                                                   '(',
                                                                   ')',
                                                                   '{',
                                                                   '}' };
  private static final char MASK_CHAR = '\\';
  private static final char MASK_CHAR_REGEX = '\\';

  /**
   * All reserved keywords of JS. see <a href=
   * "https://developer.mozilla.org/en-US/docs/JavaScript/Reference/Reserved_Words"
   * >here</a><br>
   * technically the last few are not reserved words but they cannot be used as
   * identifiers.
   */
  private static final ICommonsSet <String> RESERVED_KEYWORDS = new CommonsHashSet <> ("break",
                                                                                       "case",
                                                                                       "catch",
                                                                                       "continue",
                                                                                       "debugger",
                                                                                       "default",
                                                                                       "delete",
                                                                                       "do",
                                                                                       "else",
                                                                                       "finally",
                                                                                       "for",
                                                                                       "function",
                                                                                       "if",
                                                                                       "in",
                                                                                       "instanceof",
                                                                                       "new",
                                                                                       "return",
                                                                                       "switch",
                                                                                       "this",
                                                                                       "throw",
                                                                                       "try",
                                                                                       "typeof",
                                                                                       "var",
                                                                                       "void",
                                                                                       "while",
                                                                                       "with",
                                                                                       "class",
                                                                                       "enum",
                                                                                       "export",
                                                                                       "extends",
                                                                                       "import",
                                                                                       "super",
                                                                                       // non-reserved
                                                                                       "true",
                                                                                       "false",
                                                                                       "null",
                                                                                       "undefined");

  private JSMarshaller ()
  {}

  /**
   * Turn special characters into escaped characters conforming to JavaScript.
   * Handles complete character set defined in HTML 4.01 recommendation.<br>
   * Reference: <a href=
   * "http://developer.mozilla.org/en/docs/Core_JavaScript_1.5_Guide:Literals#String_Literals"
   * > Core JavaScript 1.5 Guide </a>
   *
   * @param sInput
   *        the input string
   * @return the escaped string
   */
  @Nullable
  public static String javaScriptEscape (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!StringHelper.containsAny (aInput, CHARS_TO_MASK))
      return sInput;

    final char [] ret = new char [aInput.length * 2];
    int nIndex = 0;
    char cPrevChar = '\u0000';
    for (final char cCurrent : aInput)
    {
      switch (cCurrent)
      {
        case '"':
        case '\'':
        case '\\':
        case '/':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = cCurrent;
          break;
        case '\t':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 't';
          break;
        case '\n':
          if (cPrevChar != '\r')
          {
            ret[nIndex++] = MASK_CHAR;
            ret[nIndex++] = 'n';
          }
          break;
        case '\r':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 'n';
          break;
        case '\f':
          ret[nIndex++] = MASK_CHAR;
          ret[nIndex++] = 'f';
          break;
        default:
          ret[nIndex++] = cCurrent;
          break;
      }
      cPrevChar = cCurrent;
    }

    return new String (ret, 0, nIndex);
  }

  @Nonnull
  public static String javaScriptEscapeForRegEx (final char cInput)
  {
    if (ArrayHelper.contains (CHARS_TO_MASK_REGEX, cInput))
      return new StringBuilder (2).append (MASK_CHAR_REGEX).append (cInput).toString ();
    return Character.toString (cInput);
  }

  /**
   * Turn special regular expression characters into escaped characters
   * conforming to JavaScript.<br>
   * Reference: <a href=
   * "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions"
   * >MDN Regular Expressions</a>
   *
   * @param sInput
   *        the input string
   * @return the escaped string
   */
  @Nullable
  public static String javaScriptEscapeForRegEx (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!StringHelper.containsAny (aInput, CHARS_TO_MASK_REGEX))
      return sInput;

    // At last each character has one masking character
    final char [] ret = new char [aInput.length * 2];
    int nIndex = 0;
    for (final char cCurrent : aInput)
      if (ArrayHelper.contains (CHARS_TO_MASK_REGEX, cCurrent))
      {
        ret[nIndex++] = MASK_CHAR_REGEX;
        ret[nIndex++] = cCurrent;
      }
      else
        ret[nIndex++] = cCurrent;

    return new String (ret, 0, nIndex);
  }

  /**
   * Unescape a previously escaped string.<br>
   * Important: this is not a 100% reversion of
   * {@link #javaScriptEscape(String)} since the escaping method drops any '\r'
   * character and it will therefore not be unescaped!
   *
   * @param sInput
   *        The string to be unescaped. May be <code>null</code>.
   * @return The unescaped string.
   * @see #javaScriptEscape(String)
   */
  @Nullable
  public static String javaScriptUnescape (@Nullable final String sInput)
  {
    if (StringHelper.hasNoText (sInput))
      return sInput;

    final char [] aInput = sInput.toCharArray ();
    if (!ArrayHelper.contains (aInput, MASK_CHAR))
      return sInput;

    // Result is at last as long as the input as e.g. "\x3a" is squeezed to ":"
    // so 1 char instead of 4
    final char [] ret = new char [aInput.length];
    int nRetIndex = 0;
    char cPrevChar = '\u0000';
    for (int i = 0; i < aInput.length; i++)
    {
      final char cCurrent = aInput[i];
      if (cPrevChar == MASK_CHAR)
      {
        switch (cCurrent)
        {
          case '"':
          case '\'':
          case '\\':
          case '/':
            ret[nRetIndex++] = cCurrent;
            break;
          case 't':
            ret[nRetIndex++] = '\t';
            break;
          case 'n':
            ret[nRetIndex++] = '\n';
            break;
          case 'f':
            ret[nRetIndex++] = '\f';
            break;
          case 'x':
            if (i + 2 >= aInput.length)
            {
              LOGGER.warn ("Failed to unescape '" + sInput + "' - EOF in hex values");
              return sInput;
            }
            final char cHex1 = aInput[++i];
            final char cHex2 = aInput[++i];
            final int nHexByte = StringHelper.getHexByte (cHex1, cHex2);
            if (nHexByte < 0)
            {
              LOGGER.warn ("Failed to unescape '" + sInput + "' - invalid hex values");
              return sInput;
            }
            ret[nRetIndex++] = (char) nHexByte;
            break;
          default:
            ret[nRetIndex++] = MASK_CHAR;
            ret[nRetIndex++] = cCurrent;
            break;
        }
        cPrevChar = 0;
      }
      else
      {
        if (cCurrent != MASK_CHAR)
          ret[nRetIndex++] = cCurrent;
        cPrevChar = cCurrent;
      }
    }

    // Last char is a mask char? append!
    if (cPrevChar == MASK_CHAR)
      ret[nRetIndex++] = MASK_CHAR;

    return new String (ret, 0, nRetIndex);
  }

  public static boolean isJSIdentifier (@Nullable final String s)
  {
    if (StringHelper.hasNoText (s))
      return false;

    // Reserved word?
    if (RESERVED_KEYWORDS.contains (s))
      return false;

    final char [] aChars = s.toCharArray ();
    for (int i = 0; i < aChars.length; ++i)
    {
      if (i == 0)
      {
        if (!Character.isJavaIdentifierStart (aChars[i]))
          return false;
      }
      else
      {
        if (!Character.isJavaIdentifierPart (aChars[i]))
          return false;
      }
    }
    return true;
  }
}
