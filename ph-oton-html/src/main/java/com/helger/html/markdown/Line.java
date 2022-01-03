/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.collection.impl.CommonsLinkedList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.EHTMLElement;

/**
 * This class represents a text line.
 * <p>
 * It also provides methods for processing and analyzing a line.
 * </p>
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt
 */
@CodingStyleguideUnaware
final class Line
{
  /** Current cursor position. */
  int m_nPos;
  /** Leading spaces. */
  int m_nLeading = 0;
  /** Trailing spaces. */
  int m_nTrailing = 0;
  /** Is this line empty? */
  boolean m_bIsEmpty = true;
  /** This line's value. */
  String m_sValue;
  /** Previous line. */
  Line m_aPrevious;
  /** Next line. */
  Line m_aNext;
  /** Is previous line empty? */
  boolean m_bPrevEmpty;
  /** Final line of a XML block. */
  Line m_aXmlEndLine;

  /** Constructor. */
  public Line ()
  {}

  /**
   * Calculates leading and trailing spaces. Also sets empty if needed.
   */
  public void init ()
  {
    m_nLeading = 0;
    while (m_nLeading < m_sValue.length () && m_sValue.charAt (m_nLeading) == ' ')
      m_nLeading++;

    if (m_nLeading == m_sValue.length ())
    {
      setEmpty ();
    }
    else
    {
      m_bIsEmpty = false;
      m_nTrailing = 0;
      while (m_sValue.charAt (m_sValue.length () - m_nTrailing - 1) == ' ')
        m_nTrailing++;
    }
  }

  /**
   * Recalculate leading spaces.
   */
  public void initLeading ()
  {
    m_nLeading = 0;
    while (m_nLeading < m_sValue.length () && m_sValue.charAt (m_nLeading) == ' ')
      m_nLeading++;

    if (m_nLeading == m_sValue.length ())
      setEmpty ();
  }

  /**
   * Skips spaces.
   *
   * @return <code>false</code> if end of line is reached
   */
  public boolean skipSpaces ()
  {
    while (m_nPos < m_sValue.length () && m_sValue.charAt (m_nPos) == ' ')
      m_nPos++;
    return m_nPos < m_sValue.length ();
  }

  /**
   * Reads chars from this line until any 'end' char is reached.
   *
   * @param aEndChars
   *        Delimiting character(s)
   * @return The read String or <code>null</code> if no 'end' char was reached.
   */
  public String readUntil (final char... aEndChars)
  {
    final StringBuilder aSB = new StringBuilder ();
    int nPos = m_nPos;
    while (nPos < m_sValue.length ())
    {
      final char ch = m_sValue.charAt (nPos);
      if (ch == '\\' && nPos + 1 < m_sValue.length ())
      {
        final char c = m_sValue.charAt (nPos + 1);
        if (MarkdownHelper.isEscapeChar (c))
        {
          aSB.append (c);
          nPos++;
        }
        else
          aSB.append (ch);
      }
      else
      {
        boolean bEndReached = false;
        for (final char cElement : aEndChars)
          if (ch == cElement)
          {
            bEndReached = true;
            break;
          }
        if (bEndReached)
          break;
        aSB.append (ch);
      }
      nPos++;
    }

    final char ch = nPos < m_sValue.length () ? m_sValue.charAt (nPos) : '\n';
    for (final char cElement : aEndChars)
      if (ch == cElement)
      {
        m_nPos = nPos;
        return aSB.toString ();
      }
    return null;
  }

  /**
   * Marks this line empty. Also sets previous/next line's empty attributes.
   */
  public void setEmpty ()
  {
    m_sValue = "";
    m_nLeading = 0;
    m_nTrailing = 0;
    m_bIsEmpty = true;
    if (m_aNext != null)
      m_aNext.m_bPrevEmpty = true;
  }

  /**
   * Counts the amount of 'ch' in this line.
   *
   * @param ch
   *        The char to count.
   * @return A value &gt; 0 if this line only consists of 'ch' end spaces.
   */
  private int _countConsecutiveChars (final char ch)
  {
    int nCount = 0;
    for (int i = 0; i < m_sValue.length (); i++)
    {
      final char c = m_sValue.charAt (i);
      if (c != ' ')
      {
        if (c != ch)
          return 0;
        nCount++;
      }
    }
    return nCount;
  }

  /**
   * Counts the amount of 'ch' at the start of this line ignoring spaces.
   *
   * @param ch
   *        The char to count.
   * @return Number of characters found.
   * @since 0.7
   */
  private int _countCharsStart (final char ch)
  {
    int nCount = 0;
    for (int i = 0; i < m_sValue.length (); i++)
    {
      final char c = m_sValue.charAt (i);
      if (c == ' ')
        continue;
      if (c != ch)
        break;
      nCount++;
    }
    return nCount;
  }

  /**
   * Gets this line's type.
   *
   * @param bExtendedMode
   *        Whether extended profile is enabled or not
   * @return The LineType.
   */
  @Nonnull
  public ELineType getLineType (final boolean bExtendedMode)
  {
    if (m_bIsEmpty)
      return ELineType.EMPTY;

    if (m_nLeading > 3)
      return ELineType.CODE;

    if (m_sValue.charAt (m_nLeading) == '#')
      return ELineType.HEADLINE;

    if (m_sValue.charAt (m_nLeading) == '>')
      return ELineType.BQUOTE;

    if (bExtendedMode)
    {
      if (m_sValue.length () - m_nLeading - m_nTrailing > 2 &&
          (m_sValue.charAt (m_nLeading) == '`' || m_sValue.charAt (m_nLeading) == '~' || m_sValue.charAt (m_nLeading) == '%'))
      {
        if (_countCharsStart ('`') >= 3)
          return ELineType.FENCED_CODE;

        if (_countCharsStart ('~') >= 3)
          return ELineType.FENCED_CODE;

        if (_countCharsStart ('%') >= 3)
          return ELineType.PLUGIN;
      }
    }

    if (m_sValue.length () - m_nLeading - m_nTrailing > 2 &&
        (m_sValue.charAt (m_nLeading) == '*' || m_sValue.charAt (m_nLeading) == '-' || m_sValue.charAt (m_nLeading) == '_'))
    {
      if (_countConsecutiveChars (m_sValue.charAt (m_nLeading)) >= 3)
        return ELineType.HR;
    }

    if (m_sValue.length () - m_nLeading >= 2 && m_sValue.charAt (m_nLeading + 1) == ' ')
    {
      switch (m_sValue.charAt (m_nLeading))
      {
        case '*':
        case '-':
        case '+':
          return ELineType.ULIST;
      }
    }

    if (m_sValue.length () - m_nLeading >= 3 && Character.isDigit (m_sValue.charAt (m_nLeading)))
    {
      int i = m_nLeading + 1;
      while (i < m_sValue.length () && Character.isDigit (m_sValue.charAt (i)))
        i++;
      if (i + 1 < m_sValue.length () && m_sValue.charAt (i) == '.' && m_sValue.charAt (i + 1) == ' ')
        return ELineType.OLIST;
    }

    if (m_sValue.charAt (m_nLeading) == '<')
    {
      final EHTMLElementType eType = _checkHTML ();
      if (eType == EHTMLElementType.TAG)
        return ELineType.XML;
      if (eType == EHTMLElementType.COMMENT)
        return ELineType.XML_COMMENT;
    }

    if (m_aNext != null && !m_aNext.m_bIsEmpty)
    {
      if (m_aNext.m_sValue.charAt (0) == '-' && m_aNext._countConsecutiveChars ('-') > 0)
        return ELineType.HEADLINE2;
      if (m_aNext.m_sValue.charAt (0) == '=' && m_aNext._countConsecutiveChars ('=') > 0)
        return ELineType.HEADLINE1;
    }

    return ELineType.OTHER;
  }

  /**
   * Reads an XML comment. Sets <code>xmlEndLine</code>.
   *
   * @param firstLine
   *        The Line to start reading from.
   * @param start
   *        The starting position.
   * @return The new position or -1 if it is no valid comment.
   */
  private int _readXMLComment (final Line firstLine, final int start)
  {
    Line line = firstLine;
    if (start + 3 < line.m_sValue.length ())
    {
      if (line.m_sValue.charAt (2) == '-' && line.m_sValue.charAt (3) == '-')
      {
        int pos = start + 4;
        while (line != null)
        {
          while (pos < line.m_sValue.length () && line.m_sValue.charAt (pos) != '-')
          {
            pos++;
          }
          if (pos == line.m_sValue.length ())
          {
            line = line.m_aNext;
            pos = 0;
          }
          else
          {
            if (pos + 2 < line.m_sValue.length ())
            {
              if (line.m_sValue.charAt (pos + 1) == '-' && line.m_sValue.charAt (pos + 2) == '>')
              {
                m_aXmlEndLine = line;
                return pos + 3;
              }
            }
            pos++;
          }
        }
      }
    }
    return -1;
  }

  /**
   * Checks if this line contains an ID at it's end and removes it from the
   * line.
   *
   * @return The ID or <code>null</code> if no valid ID exists.
   */
  // FIXME ... hack
  public String stripID ()
  {
    if (m_bIsEmpty || m_sValue.charAt (m_sValue.length () - m_nTrailing - 1) != '}')
      return null;
    int nPos = m_nLeading;
    boolean bFound = false;
    while (nPos < m_sValue.length () && !bFound)
    {
      switch (m_sValue.charAt (nPos))
      {
        case '\\':
          if (nPos + 1 < m_sValue.length ())
          {
            if (m_sValue.charAt (nPos + 1) == '{')
              nPos++;
          }
          nPos++;
          break;
        case '{':
          bFound = true;
          break;
        default:
          nPos++;
          break;
      }
    }

    if (bFound)
    {
      if (nPos + 1 < m_sValue.length () && m_sValue.charAt (nPos + 1) == '#')
      {
        final int nStart = nPos + 2;
        nPos = nStart;
        bFound = false;
        while (nPos < m_sValue.length () && !bFound)
        {
          switch (m_sValue.charAt (nPos))
          {
            case '\\':
              if (nPos + 1 < m_sValue.length ())
              {
                if (m_sValue.charAt (nPos + 1) == '}')
                  nPos++;
              }
              nPos++;
              break;
            case '}':
              bFound = true;
              break;
            default:
              nPos++;
              break;
          }
        }
        if (bFound)
        {
          final String sID = m_sValue.substring (nStart, nPos).trim ();
          if (m_nLeading != 0)
          {
            m_sValue = m_sValue.substring (0, m_nLeading) + m_sValue.substring (m_nLeading, nStart - 2).trim ();
          }
          else
          {
            m_sValue = m_sValue.substring (m_nLeading, nStart - 2).trim ();
          }
          m_nTrailing = 0;
          return sID.length () > 0 ? sID : null;
        }
      }
    }
    return null;
  }

  private enum EHTMLElementType
  {
    NONE,
    TAG,
    COMMENT;
  }

  /**
   * Checks for a valid HTML block. Sets <code>xmlEndLine</code>.
   *
   * @return <code>EHTMLType.TAG</code> or <code>EHTMLType.COMMENT</code> if it
   *         is a valid block.
   */
  @Nonnull
  private EHTMLElementType _checkHTML ()
  {
    final ICommonsList <String> aTags = new CommonsLinkedList <> ();
    final StringBuilder aSB = new StringBuilder ();
    if (m_sValue.charAt (m_nLeading + 1) == '!')
    {
      if (_readXMLComment (this, m_nLeading) > 0)
        return EHTMLElementType.COMMENT;
    }
    int nPos = MarkdownHelper.readXMLElement (aSB, m_sValue, m_nLeading, false);
    if (nPos > -1)
    {
      String sElement = aSB.toString ();
      String sTag = MarkdownHelper.getXMLTag (sElement);
      if (!MarkdownHTML.isHtmlBlockElement (sTag))
        return EHTMLElementType.NONE;
      if (EHTMLElement.getFromTagNameOrNull (sTag).mayBeSelfClosed ())
      {
        m_aXmlEndLine = this;
        return EHTMLElementType.TAG;
      }
      aTags.add (sTag);
      Line aLine = this;
      while (aLine != null)
      {
        while (nPos < aLine.m_sValue.length () && aLine.m_sValue.charAt (nPos) != '<')
          nPos++;

        if (nPos >= aLine.m_sValue.length ())
        {
          aLine = aLine.m_aNext;
          nPos = 0;
        }
        else
        {
          aSB.setLength (0);
          final int nNewPos = MarkdownHelper.readXMLElement (aSB, aLine.m_sValue, nPos, false);
          if (nNewPos > 0)
          {
            sElement = aSB.toString ();
            sTag = MarkdownHelper.getXMLTag (sElement);
            if (MarkdownHTML.isHtmlBlockElement (sTag) && !EHTMLElement.getFromTagNameOrNull (sTag).mayBeSelfClosed ())
            {
              if (sElement.charAt (1) == '/')
              {
                if (!aTags.getLast ().equals (sTag))
                  return EHTMLElementType.NONE;
                aTags.removeLast ();
              }
              else
              {
                aTags.add (sTag);
              }
            }
            if (aTags.isEmpty ())
            {
              m_aXmlEndLine = aLine;
              break;
            }
            nPos = nNewPos;
          }
          else
          {
            nPos++;
          }
        }
      }
      if (aTags.isEmpty ())
        return EHTMLElementType.TAG;
    }
    return EHTMLElementType.NONE;
  }
}
