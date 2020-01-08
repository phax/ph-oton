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
package com.helger.html.markdown;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.CodingStyleguideUnaware;

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
  public Line m_aLines;
  /** Tail of linked lines. */
  public Line m_aLineTail;
  /** Head of child blocks. */
  public Block m_aBlocks;
  /** Tail of child blocks. */
  public Block m_aBlockTail;
  /** Next block. */
  public Block m_aNext;
  /** Depth of headline BlockType. */
  public int m_nHeadlineDepth = 0;
  /** ID for headlines and list items */
  public String m_sID;
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
    Line aLine = m_aLines;
    while (aLine != null)
    {
      if (!aLine.m_bIsEmpty)
      {
        switch (aLine.getLineType (bExtendedMode))
        {
          case ULIST:
            aLine.m_sValue = aLine.m_sValue.substring (aLine.m_nLeading + 2);
            break;
          case OLIST:
            aLine.m_sValue = aLine.m_sValue.substring (aLine.m_sValue.indexOf ('.') + 2);
            break;
          default:
            aLine.m_sValue = aLine.m_sValue.substring (Math.min (aLine.m_nLeading, 4));
            break;
        }
        aLine.initLeading ();
      }
      aLine = aLine.m_aNext;
    }
  }

  /**
   * Used for nested block quotes. Removes '>' char.
   */
  public void removeBlockQuotePrefix ()
  {
    Line aLine = m_aLines;
    while (aLine != null)
    {
      if (!aLine.m_bIsEmpty)
      {
        if (aLine.m_sValue.charAt (aLine.m_nLeading) == '>')
        {
          int rem = aLine.m_nLeading + 1;
          if (aLine.m_nLeading + 1 < aLine.m_sValue.length () && aLine.m_sValue.charAt (aLine.m_nLeading + 1) == ' ')
            rem++;
          aLine.m_sValue = aLine.m_sValue.substring (rem);
          aLine.initLeading ();
        }
      }
      aLine = aLine.m_aNext;
    }
  }

  /**
   * Removes leading empty lines.
   *
   * @return <code>true</code> if an empty line was removed.
   */
  public boolean removeLeadingEmptyLines ()
  {
    boolean bWasEmpty = false;
    Line aLine = m_aLines;
    while (aLine != null && aLine.m_bIsEmpty)
    {
      removeLine (aLine);
      aLine = m_aLines;
      bWasEmpty = true;
    }
    return bWasEmpty;
  }

  /**
   * Removes trailing empty lines.
   */
  public void removeTrailingEmptyLines ()
  {
    Line aLine = m_aLineTail;
    while (aLine != null && aLine.m_bIsEmpty)
    {
      removeLine (aLine);
      aLine = m_aLineTail;
    }
  }

  /**
   * Splits this block's lines, creating a new child block having 'line' as it's
   * lineTail.
   *
   * @param aLine
   *        The line to split from.
   * @return The newly created Block.
   */
  public Block split (final Line aLine)
  {
    final Block aBlock = new Block ();

    aBlock.m_aLines = m_aLines;
    aBlock.m_aLineTail = aLine;
    m_aLines = aLine.m_aNext;
    aLine.m_aNext = null;
    if (m_aLines == null)
      m_aLineTail = null;
    else
      m_aLines.m_aPrevious = null;

    if (m_aBlocks == null)
      m_aBlocks = m_aBlockTail = aBlock;
    else
    {
      m_aBlockTail.m_aNext = aBlock;
      m_aBlockTail = aBlock;
    }

    return aBlock;
  }

  /**
   * Removes the given line from this block.
   *
   * @param aLine
   *        Line to remove.
   */
  public void removeLine (final Line aLine)
  {
    if (aLine.m_aPrevious == null)
      m_aLines = aLine.m_aNext;
    else
      aLine.m_aPrevious.m_aNext = aLine.m_aNext;
    if (aLine.m_aNext == null)
      m_aLineTail = aLine.m_aPrevious;
    else
      aLine.m_aNext.m_aPrevious = aLine.m_aPrevious;
    aLine.m_aPrevious = null;
    aLine.m_aNext = null;
  }

  /**
   * Appends the given line to this block.
   *
   * @param aLine
   *        Line to append.
   */
  public void appendLine (@Nonnull final Line aLine)
  {
    if (m_aLineTail == null)
    {
      m_aLines = aLine;
      m_aLineTail = aLine;
    }
    else
    {
      aLine.m_bPrevEmpty = m_aLineTail.m_bIsEmpty;
      aLine.m_aPrevious = m_aLineTail;
      m_aLineTail.m_aNext = aLine;
      m_aLineTail = aLine;
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
    Block aOuter = m_aBlocks;
    Block aInner;
    boolean bHasParagraph = false;
    while (aOuter != null && !bHasParagraph)
    {
      if (aOuter.m_eType == EBlockType.LIST_ITEM)
      {
        aInner = aOuter.m_aBlocks;
        while (aInner != null && !bHasParagraph)
        {
          if (aInner.m_eType == EBlockType.PARAGRAPH)
            bHasParagraph = true;
          aInner = aInner.m_aNext;
        }
      }
      aOuter = aOuter.m_aNext;
    }
    if (bHasParagraph)
    {
      aOuter = m_aBlocks;
      while (aOuter != null)
      {
        if (aOuter.m_eType == EBlockType.LIST_ITEM)
        {
          aInner = aOuter.m_aBlocks;
          while (aInner != null)
          {
            if (aInner.m_eType == EBlockType.NONE)
              aInner.m_eType = EBlockType.PARAGRAPH;
            aInner = aInner.m_aNext;
          }
        }
        aOuter = aOuter.m_aNext;
      }
    }
  }
}
