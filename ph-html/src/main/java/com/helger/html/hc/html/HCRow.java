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
package com.helger.html.hc.html;

import java.util.List;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;
import com.helger.html.hc.impl.HCNodeList;

/**
 * Represents an HTML &lt;tr&gt; element
 *
 * @author Philip Helger
 */
public class HCRow extends AbstractHCElementWithInternalChildren <HCRow, IHCCell <?>>
{
  private final boolean m_bHeader;

  @DevelopersNote ("Works only for tbody rows!")
  public HCRow ()
  {
    this (false);
  }

  public HCRow (final boolean bHeader)
  {
    super (EHTMLElement.TR);
    m_bHeader = bHeader;
  }

  public boolean isHeader ()
  {
    return m_bHeader;
  }

  /**
   * Add an empty cell.
   *
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  public IHCCell <?> addCell ()
  {
    final AbstractHCCell <?> ret = m_bHeader ? new HCTH () : new HCTD ();
    ret.internalSetParentRow (this);
    addChild (ret);
    return ret;
  }

  /**
   * Add an empty cell at the specified index.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  public IHCCell <?> addCell (@Nonnegative final int nIndex)
  {
    final AbstractHCCell <?> ret = m_bHeader ? new HCTH () : new HCTD ();
    ret.internalSetParentRow (this);
    addChild (nIndex, ret);
    return ret;
  }

  /**
   * Add a single cell with the given text element.
   *
   * @param sCellText
   *        The text to be set into the cell. May be <code>null</code>.
   * @return the created table cell
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final String sCellText)
  {
    return addCell ().addChild (sCellText);
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param aCellChild
   *        The element to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final IHCNodeBuilder aCellChild)
  {
    return addCell ().addChild (aCellChild);
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param aCellChild
   *        The element to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final IHCNode aCellChild)
  {
    return addCell ().addChild (aCellChild);
  }

  /**
   * Add a single cell with the given text elements.
   *
   * @param aCellTexts
   *        The text to be set into the cell. May be <code>null</code>.
   * @return the created table cell
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final String... aCellTexts)
  {
    return addCell ().addChildren (aCellTexts);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final IHCNodeBuilder... aCellChildren)
  {
    return addCell ().addChildren (aCellChildren);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final IHCNode... aCellChildren)
  {
    return addCell ().addChildren (aCellChildren);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    return addCell ().addChildren (aCellChildren);
  }

  /**
   * Add a single cell with the given text element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param sCellText
   *        The text to be set into the cell. May be <code>null</code>.
   * @return the created table cell
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    return addCell (nIndex).addChild (sCellText);
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChild
   *        The element to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder aCellChild)
  {
    return addCell (nIndex).addChild (aCellChild);
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChild
   *        The element to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNode aCellChild)
  {
    return addCell (nIndex).addChild (aCellChild);
  }

  /**
   * Add a single cell with the given text elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellTexts
   *        The text to be set into the cell. May be <code>null</code>.
   * @return the created table cell
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final String... aCellTexts)
  {
    return addCell (nIndex).addChildren (aCellTexts);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder... aCellChildren)
  {
    return addCell (nIndex).addChildren (aCellChildren);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    return addCell (nIndex).addChildren (aCellChildren);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex,
                                       @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    return addCell (nIndex).addChildren (aCellChildren);
  }

  /**
   * Add a single cell with the given text element.
   *
   * @param sCellText
   *        The text to be set into the cell. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final String sCellText)
  {
    addCell ().addChild (sCellText);
    return this;
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param aChild
   *        The element to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final IHCNodeBuilder aChild)
  {
    addCell ().addChild (aChild);
    return this;
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param aChild
   *        The element to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final IHCNode aChild)
  {
    addCell ().addChild (aChild);
    return this;
  }

  /**
   * Add a single new cell and add the passed texts.
   *
   * @param aCellTexts
   *        The list of texts to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final String... aCellTexts)
  {
    addCell ().addChildren (aCellTexts);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final IHCNodeBuilder... aCellChildren)
  {
    addCell ().addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final IHCNode... aCellChildren)
  {
    addCell ().addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    addCell ().addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single cell with the given text element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param sCellText
   *        The text to be set into the cell. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    addCell (nIndex).addChild (sCellText);
    return this;
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aChild
   *        The element to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder aChild)
  {
    addCell (nIndex).addChild (aChild);
    return this;
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aChild
   *        The element to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNode aChild)
  {
    addCell (nIndex).addChild (aChild);
    return this;
  }

  /**
   * Add a single new cell and add the passed texts.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of texts to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final String... aCellChildren)
  {
    addCell (nIndex).addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder... aCellChildren)
  {
    addCell (nIndex).addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    addCell (nIndex).addChildren (aCellChildren);
    return this;
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    addCell (nIndex).addChildren (aCellChildren);
    return this;
  }

  @Deprecated
  @DevelopersNote ("Use either addCell or add parameters :)")
  @Nonnull
  public HCRow addCells (@Nullable final String sCellChild)
  {
    return addCell (sCellChild);
  }

  /**
   * Add multiple cells, one for each passed string.
   *
   * @param aCellTexts
   *        The list of strings for which a table cell should be created.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCells (@Nullable final String... aCellTexts)
  {
    if (aCellTexts != null)
      for (final String sCellText : aCellTexts)
        addCell (sCellText);
    return this;
  }

  @Deprecated
  @DevelopersNote ("Use either addCell or add parameters :)")
  @Nonnull
  public HCRow addCells (@Nullable final IHCNodeBuilder aCellChild)
  {
    return addCell (aCellChild);
  }

  /**
   * Add multiple cells, one for each passed element.
   *
   * @param aCellChildren
   *        The list of elements for which new cells should be created. May be
   *        <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCells (@Nullable final IHCNodeBuilder... aCellChildren)
  {
    if (aCellChildren != null)
      for (final IHCNodeBuilder aCellChild : aCellChildren)
        addCell (aCellChild);
    return this;
  }

  @Deprecated
  @DevelopersNote ("Use either addCell or add parameters :)")
  @Nonnull
  public HCRow addCells (@Nullable final IHCNode aCellChild)
  {
    return addCell (aCellChild);
  }

  /**
   * Add multiple cells, one for each passed element.
   *
   * @param aCellChildren
   *        The list of elements for which new cells should be created. May be
   *        <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCells (@Nullable final IHCNode... aCellChildren)
  {
    if (aCellChildren != null)
      for (final IHCNode aCellChild : aCellChildren)
        addCell (aCellChild);
    return this;
  }

  /**
   * Add multiple cells, one for each passed element.
   *
   * @param aCellChildren
   *        The list of elements for which new cells should be created. May be
   *        <code>null</code>.
   * @return this (the table row)
   */
  @Nonnull
  public HCRow addCells (@Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    if (aCellChildren != null)
      for (final IHCNode aCellChild : aCellChildren)
        addCell (aCellChild);
    return this;
  }

  /**
   * Get the cell at the specified index in this row. This method does NOT
   * consider colspans!!!!
   *
   * @param nIndex
   *        The index of the cell
   * @return <code>null</code> if no such cell is present
   */
  @Nullable
  public IHCCell <?> getCellAtIndex (final int nIndex)
  {
    return getChildAtIndex (nIndex);
  }

  /**
   * @return A list with all contained cells. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public List <IHCCell <?>> getAllCells ()
  {
    return getAllChildren ();
  }

  /**
   * Get the cell at the specified index in this row. This method does consider
   * colspans!!!!
   *
   * @param nIndex
   *        The index of the cell
   * @return <code>null</code> if no such cell is present
   */
  @Nullable
  public IHCCell <?> getCellAtEffectiveIndex (final int nIndex)
  {
    int i = 0;
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetChildren ())
      {
        if (i >= nIndex)
          return aCell;
        i += aCell.getColspan ();
      }
    return null;
  }

  /**
   * Note: this method only counts the effective cells and does NOT include
   * eventually contained "colspans"!
   *
   * @return The number of contained cells.
   */
  @Nonnegative
  public int getCellCount ()
  {
    return getChildCount ();
  }

  /**
   * Count the number of effective cells - including "colspans" - of this row.
   *
   * @return The number of effectively contained cells. Always &ge; 0.
   */
  @Nonnegative
  public int getEffectiveCellCount ()
  {
    int ret = 0;
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetChildren ())
        ret += aCell.getColspan ();
    return ret;
  }

  /**
   * @return All cell nodes as one big {@link HCNodeList}. Never
   *         <code>null</code>.
   */
  @Nonnull
  public HCNodeList getCellsAsNodeList ()
  {
    final HCNodeList ret = new HCNodeList ();
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetChildren ())
        ret.addChild (aCell);
    return ret;
  }

  /**
   * Remove the cell at the specified index
   *
   * @param nIndex
   *        The index to remove
   * @return this
   */
  @Nonnull
  public HCRow removeCellAtIndex (@Nonnegative final int nIndex)
  {
    removeChild (nIndex);
    return this;
  }

  /**
   * Remove all cells of this row
   *
   * @return this
   */
  @Nonnull
  public HCRow removeAllCells ()
  {
    removeAllChildren ();
    return this;
  }

  /**
   * @return <code>true</code> if at least one contained cell uses a colspan.
   */
  public boolean isColspanUsed ()
  {
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetChildren ())
        if (aCell.getColspan () > 1)
          return true;
    return false;
  }

  /**
   * @return <code>true</code> if at least one contained cell uses a rowspan.
   */
  public boolean isRowspanUsed ()
  {
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetChildren ())
        if (aCell.getRowspan () > 1)
          return true;
    return false;
  }

  @Override
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Avoid rows without cells!
    return hasChildren ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("header", m_bHeader).toString ();
  }
}
