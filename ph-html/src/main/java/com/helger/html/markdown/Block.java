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
package com.helger.html.markdown;

import com.helger.commons.annotations.CodingStyleguideUnaware;

/**
 * This class represents a block of lines.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt
 */
@CodingStyleguideUnaware
final class Block
{
  /** This block's type. */
  public EBlockType m_eType = EBlockType.NONE;
  /** Head of linked lines. */
  public Line m_aLines = null;
  /** Tail of linked lines. */
  public Line m_aLineTail = null;
  /** Head of child blocks. */
  public Block m_aBlocks = null;
  /** Tail of child blocks. */
  public Block m_aBlockTail = null;
  /** Next block. */
  public Block m_aNext = null;
  /** Depth of headline BlockType. */
  public int m_nHeadlineDepth = 0;
  /** ID for headlines and list items */
  public String m_sId = null;
  /** Block meta information */
  public String m_sMeta = "";

  /** Constructor. */
  public Block ()
  {}

  /**
   * @return <code>true</code> if this block contains lines.
   */
  public boolean hasLines ()
  {
    return m_aLines != null;
  }

  /**
   * Removes leading and trailing empty lines.
   */
  public void removeSurroundingEmptyLines ()
  {
    if (m_aLines != null)
    {
      removeTrailingEmptyLines ();
      removeLeadingEmptyLines ();
    }
  }

  /**
   * Sets <code>hlDepth</code> and takes care of '#' chars.
   */
  public void transfromHeadline ()
  {
    if (m_nHeadlineDepth > 0)
      return;
    int nLevel = 0;
    final Line aLine = m_aLines;
    if (aLine.m_bIsEmpty)
      return;
    int nStart = aLine.m_nLeading;
    while (nStart < aLine.m_sValue.length () && aLine.m_sValue.charAt (nStart) == '#')
    {
      nLevel++;
      nStart++;
    }
    while (nStart < aLine.m_sValue.length () && aLine.m_sValue.charAt (nStart) == ' ')
      nStart++;
    if (nStart >= aLine.m_sValue.length ())
    {
      aLine.setEmpty ();
    }
    else
    {
      int nEnd = aLine.m_sValue.length () - aLine.m_nTrailing - 1;
      while (aLine.m_sValue.charAt (nEnd) == '#')
        nEnd--;
      while (aLine.m_sValue.charAt (nEnd) == ' ')
        nEnd--;
      aLine.m_sValue = aLine.m_sValue.substring (nStart, nEnd + 1);
      aLine.m_nLeading = aLine.m_nTrailing = 0;
    }
    m_nHeadlineDepth = Math.min (nLevel, 6);
  }

  /**
   * Used for nested lists. Removes list markers and up to 4 leading spaces.
   *
   * @param bExtendedMode
   *        Whether extended profile is activated or not
   */
  public void removeListIndent (final boolean bExtendedMode)
  {
    Line line = m_aLines;
    while (line != null)
    {
      if (!line.m_bIsEmpty)
      {
        switch (line.getLineType (bExtendedMode))
        {
          case ULIST:
            line.m_sValue = line.m_sValue.substring (line.m_nLeading + 2);
            break;
          case OLIST:
            line.m_sValue = line.m_sValue.substring (line.m_sValue.indexOf ('.') + 2);
            break;
          default:
            line.m_sValue = line.m_sValue.substring (Math.min (line.m_nLeading, 4));
            break;
        }
        line.initLeading ();
      }
      line = line.m_aNext;
    }
  }

  /**
   * Used for nested block quotes. Removes '>' char.
   */
  public void removeBlockQuotePrefix ()
  {
    Line line = m_aLines;
    while (line != null)
    {
      if (!line.m_bIsEmpty)
      {
        if (line.m_sValue.charAt (line.m_nLeading) == '>')
        {
          int rem = line.m_nLeading + 1;
          if (line.m_nLeading + 1 < line.m_sValue.length () && line.m_sValue.charAt (line.m_nLeading + 1) == ' ')
            rem++;
          line.m_sValue = line.m_sValue.substring (rem);
          line.initLeading ();
        }
      }
      line = line.m_aNext;
    }
  }

  /**
   * Removes leading empty lines.
   *
   * @return <code>true</code> if an empty line was removed.
   */
  public boolean removeLeadingEmptyLines ()
  {
    boolean wasEmpty = false;
    Line line = m_aLines;
    while (line != null && line.m_bIsEmpty)
    {
      removeLine (line);
      line = m_aLines;
      wasEmpty = true;
    }
    return wasEmpty;
  }

  /**
   * Removes trailing empty lines.
   */
  public void removeTrailingEmptyLines ()
  {
    Line line = m_aLineTail;
    while (line != null && line.m_bIsEmpty)
    {
      removeLine (line);
      line = m_aLineTail;
    }
  }

  /**
   * Splits this block's lines, creating a new child block having 'line' as it's
   * lineTail.
   *
   * @param line
   *        The line to split from.
   * @return The newly created Block.
   */
  public Block split (final Line line)
  {
    final Block block = new Block ();

    block.m_aLines = m_aLines;
    block.m_aLineTail = line;
    m_aLines = line.m_aNext;
    line.m_aNext = null;
    if (m_aLines == null)
      m_aLineTail = null;
    else
      m_aLines.m_aPrevious = null;

    if (m_aBlocks == null)
      m_aBlocks = m_aBlockTail = block;
    else
    {
      m_aBlockTail.m_aNext = block;
      m_aBlockTail = block;
    }

    return block;
  }

  /**
   * Removes the given line from this block.
   *
   * @param line
   *        Line to remove.
   */
  public void removeLine (final Line line)
  {
    if (line.m_aPrevious == null)
      m_aLines = line.m_aNext;
    else
      line.m_aPrevious.m_aNext = line.m_aNext;
    if (line.m_aNext == null)
      m_aLineTail = line.m_aPrevious;
    else
      line.m_aNext.m_aPrevious = line.m_aPrevious;
    line.m_aPrevious = line.m_aNext = null;
  }

  /**
   * Appends the given line to this block.
   *
   * @param line
   *        Line to append.
   */
  public void appendLine (final Line line)
  {
    if (m_aLineTail == null)
      m_aLines = m_aLineTail = line;
    else
    {
      line.m_bPrevEmpty = m_aLineTail.m_bIsEmpty;
      line.m_aPrevious = m_aLineTail;
      m_aLineTail.m_aNext = line;
      m_aLineTail = line;
    }
  }

  /**
   * Changes all Blocks of type <code>NONE</code> to <code>PARAGRAPH</code> if
   * this Block is a List and any of the ListItems contains a paragraph.
   */
  public void expandListParagraphs ()
  {
    if (m_eType != EBlockType.ORDERED_LIST && m_eType != EBlockType.UNORDERED_LIST)
    {
      return;
    }
    Block outer = m_aBlocks, inner;
    boolean hasParagraph = false;
    while (outer != null && !hasParagraph)
    {
      if (outer.m_eType == EBlockType.LIST_ITEM)
      {
        inner = outer.m_aBlocks;
        while (inner != null && !hasParagraph)
        {
          if (inner.m_eType == EBlockType.PARAGRAPH)
            hasParagraph = true;
          inner = inner.m_aNext;
        }
      }
      outer = outer.m_aNext;
    }
    if (hasParagraph)
    {
      outer = m_aBlocks;
      while (outer != null)
      {
        if (outer.m_eType == EBlockType.LIST_ITEM)
        {
          inner = outer.m_aBlocks;
          while (inner != null)
          {
            if (inner.m_eType == EBlockType.NONE)
              inner.m_eType = EBlockType.PARAGRAPH;
            inner = inner.m_aNext;
          }
        }
        outer = outer.m_aNext;
      }
    }
  }
}
