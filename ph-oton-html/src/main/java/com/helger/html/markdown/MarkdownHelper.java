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
package com.helger.html.markdown;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;

import com.helger.commons.collection.ArrayHelper;

/**
 * Utilities.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
final class MarkdownHelper
{
  private static final char [] ESCAPE_CHARS = new char [] { '\\',
                                                            '[',
                                                            ']',
                                                            '(',
                                                            ')',
                                                            '{',
                                                            '}',
                                                            '#',
                                                            '"',
                                                            '\'',
                                                            '.',
                                                            '>',
                                                            '<',
                                                            '*',
                                                            '+',
                                                            '-',
                                                            '_',
                                                            '!',
                                                            '`',
                                                            '^' };

  private MarkdownHelper ()
  {}

  /**
   * @return A pseudo random number between 0 and 1023
   */
  public static int rnd ()
  {
    return ThreadLocalRandom.current ().nextInt (1024);
  }

  /**
   * Skips spaces in the given String.
   *
   * @param sIn
   *        Input String.
   * @param nStart
   *        Starting position.
   * @return The new position or -1 if EOL has been reached.
   */
  public static int skipSpaces (final String sIn, final int nStart)
  {
    int pos = nStart;
    while (pos < sIn.length () && (sIn.charAt (pos) == ' ' || sIn.charAt (pos) == '\n'))
      pos++;
    return pos < sIn.length () ? pos : -1;
  }

  public static boolean isEscapeChar (final char c)
  {
    return ArrayHelper.contains (ESCAPE_CHARS, c);
  }

  /**
   * Processed the given escape sequence.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param ch
   *        The character.
   * @param pos
   *        Current parsing position.
   * @return The new position.
   */
  private static int _escape (final StringBuilder out, final char ch, final int pos)
  {
    if (isEscapeChar (ch))
    {
      out.append (ch);
      return pos + 1;
    }
    out.append ('\\');
    return pos;
  }

  /**
   * Reads characters until any 'end' character is encountered.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        The Input String.
   * @param start
   *        Starting position.
   * @param end
   *        End characters.
   * @return The new position or -1 if no 'end' char was found.
   */
  public static int readUntil (final StringBuilder out, final String in, final int start, final char... end)
  {
    int pos = start;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      if (ch == '\\' && pos + 1 < in.length ())
      {
        pos = _escape (out, in.charAt (pos + 1), pos);
      }
      else
      {
        boolean endReached = false;
        for (final char element : end)
        {
          if (ch == element)
          {
            endReached = true;
            break;
          }
        }
        if (endReached)
          break;
        out.append (ch);
      }
      pos++;
    }

    return pos == in.length () ? -1 : pos;
  }

  /**
   * Reads characters until the 'end' character is encountered.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        The Input String.
   * @param start
   *        Starting position.
   * @param end
   *        End characters.
   * @return The new position or -1 if no 'end' char was found.
   */
  public static int readUntil (final StringBuilder out, final String in, final int start, final char end)
  {
    int pos = start;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      if (ch == '\\' && pos + 1 < in.length ())
      {
        pos = _escape (out, in.charAt (pos + 1), pos);
      }
      else
      {
        if (ch == end)
          break;
        out.append (ch);
      }
      pos++;
    }

    return pos == in.length () ? -1 : pos;
  }

  /**
   * Reads a markdown link.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Starting position.
   * @return The new position or -1 if this is no valid markdown link.
   */
  public static int readMdLink (final StringBuilder out, final String in, final int start)
  {
    int pos = start;
    int counter = 1;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      if (ch == '\\' && pos + 1 < in.length ())
      {
        pos = _escape (out, in.charAt (pos + 1), pos);
      }
      else
      {
        boolean endReached = false;
        switch (ch)
        {
          case '(':
            counter++;
            break;
          case ' ':
            if (counter == 1)
              endReached = true;
            break;
          case ')':
            counter--;
            if (counter == 0)
              endReached = true;
            break;
          default:
            break;
        }
        if (endReached)
          break;
        out.append (ch);
      }
      pos++;
    }

    return pos == in.length () ? -1 : pos;
  }

  /**
   * Reads a markdown link ID.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Starting position.
   * @return The new position or -1 if this is no valid markdown link ID.
   */
  public static int readMdLinkId (final StringBuilder out, final String in, final int start)
  {
    int pos = start;
    int counter = 1;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      boolean endReached = false;
      switch (ch)
      {
        case '\n':
          out.append (' ');
          break;
        case '[':
          counter++;
          out.append (ch);
          break;
        case ']':
          counter--;
          if (counter == 0)
            endReached = true;
          else
            out.append (ch);
          break;
        default:
          out.append (ch);
          break;
      }
      if (endReached)
        break;
      pos++;
    }

    return pos == in.length () ? -1 : pos;
  }

  /**
   * Reads characters until any 'end' character is encountered, ignoring escape
   * sequences.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        The Input String.
   * @param start
   *        Starting position.
   * @param end
   *        End characters.
   * @return The new position or -1 if no 'end' char was found.
   */
  private static int _readRawUntil (final StringBuilder out, final String in, final int start, final char... end)
  {
    int pos = start;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      boolean endReached = false;
      for (final char element : end)
      {
        if (ch == element)
        {
          endReached = true;
          break;
        }
      }
      if (endReached)
        break;
      out.append (ch);
      pos++;
    }

    return pos == in.length () ? -1 : pos;
  }

  /**
   * Reads characters until the end character is encountered, ignoring escape
   * sequences.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        The Input String.
   * @param start
   *        Starting position.
   * @param end
   *        End characters.
   * @return The new position or -1 if no 'end' char was found.
   */
  public static int readRawUntil (final StringBuilder out, final String in, final int start, final char end)
  {
    int pos = start;
    while (pos < in.length ())
    {
      final char ch = in.charAt (pos);
      if (ch == end)
        break;
      out.append (ch);
      pos++;
    }

    return (pos == in.length ()) ? -1 : pos;
  }

  /**
   * Append the given char as a decimal HTML entity.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param value
   *        The character.
   */
  private static void _appendDecEntity (final StringBuilder out, final char value)
  {
    out.append ("&#").append ((int) value).append (';');
  }

  /**
   * Append the given char as a hexadecimal HTML entity.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param value
   *        The character.
   */
  private static void _appendHexEntity (final StringBuilder out, final char value)
  {
    out.append ("&#x").append (Integer.toHexString (value)).append (';');
  }

  /**
   * Appends the given mailto link using obfuscation.
   *
   * @param out
   *        The StringBuilder to write to.
   * @param in
   *        Input String.
   * @param start
   *        Input String starting position.
   * @param end
   *        Input String end position.
   */
  public static void appendMailto (final StringBuilder out, final String in, final int start, final int end)
  {
    for (int i = start; i < end; i++)
    {
      final char c = in.charAt (i);
      final int r = rnd ();
      switch (c)
      {
        case '&':
        case '<':
        case '>':
        case '"':
        case '\'':
        case '@':
          if (r < 512)
            _appendDecEntity (out, c);
          else
            _appendHexEntity (out, c);
          break;
        default:
          if (r < 32)
            out.append (c);
          else
            if (r < 520)
              _appendDecEntity (out, c);
            else
              _appendHexEntity (out, c);
          break;
      }
    }
  }

  /**
   * Extracts the tag from an XML element.
   *
   * @param in
   *        Input String.
   * @return XML tag name
   */
  @Nonnull
  public static String getXMLTag (final String in)
  {
    final StringBuilder aSB = new StringBuilder ();
    int pos = 1;
    if (in.charAt (1) == '/')
      pos++;
    while (Character.isLetterOrDigit (in.charAt (pos)))
      aSB.append (in.charAt (pos++));
    return aSB.toString ().toLowerCase (Locale.US);
  }

  /**
   * Reads an XML element.
   *
   * @param aSB
   *        The StringBuilder to write to.
   * @param sIn
   *        Input String.
   * @param nStart
   *        Starting position.
   * @param bSafeMode
   *        Whether to escape unsafe HTML tags or not
   * @return The new position or -1 if this is no valid XML element.
   */
  @CheckForSigned
  public static int readXMLElement (final StringBuilder aSB,
                                    final String sIn,
                                    final int nStart,
                                    final boolean bSafeMode)
  {
    try
    {
      if (sIn.charAt (nStart + 1) == '!')
      {
        aSB.append ("<!");
        return nStart + 1;
      }

      int pos;
      final boolean bIsCloseTag;
      if (sIn.charAt (nStart + 1) == '/')
      {
        bIsCloseTag = true;
        pos = nStart + 2;
      }
      else
      {
        bIsCloseTag = false;
        pos = nStart + 1;
      }

      if (bSafeMode)
      {
        final StringBuilder temp = new StringBuilder ();
        pos = _readRawUntil (temp, sIn, pos, ' ', '/', '>');
        if (pos == -1)
          return -1;
        final String tag = temp.toString ().trim ();
        if (MarkdownHTML.isUnsafeHtmlElement (tag))
        {
          // Use "&lt;" to not evaluate it but just print it
          aSB.append ("&lt;");
          if (bIsCloseTag)
            aSB.append ('/');
          aSB.append (tag);
        }
        else
        {
          aSB.append ('<');
          if (bIsCloseTag)
            aSB.append ('/');
          aSB.append (tag);
        }
      }
      else
      {
        aSB.append ('<');
        if (bIsCloseTag)
          aSB.append ('/');
        pos = _readRawUntil (aSB, sIn, pos, ' ', '/', '>');
      }
      if (pos == -1)
        return -1;
      pos = _readRawUntil (aSB, sIn, pos, '/', '>');
      if (sIn.charAt (pos) == '/')
      {
        aSB.append (" /");
        pos = readRawUntil (aSB, sIn, pos + 1, '>');
        if (pos == -1)
          return -1;
      }
      if (sIn.charAt (pos) == '>')
      {
        aSB.append ('>');
        return pos + 1;
      }
    }
    catch (final StringIndexOutOfBoundsException e)
    {
      return -1;
    }
    return -1;
  }

  /**
   * Removes trailing <code>`</code> and trims spaces.
   *
   * @param fenceLine
   *        Fenced code block starting line
   * @return Rest of the line after trimming and backtick removal
   * @since 0.7
   */
  @Nonnull
  public static String getMetaFromFence (final String fenceLine)
  {
    for (int i = 0; i < fenceLine.length (); i++)
    {
      final char c = fenceLine.charAt (i);
      if (!Character.isWhitespace (c) && c != '`' && c != '~' && c != '%')
      {
        return fenceLine.substring (i).trim ();
      }
    }
    return "";
  }
}
