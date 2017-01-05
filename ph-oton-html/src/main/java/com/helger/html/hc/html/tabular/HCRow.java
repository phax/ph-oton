/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.tabular;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.DevelopersNote;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;
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
   * @deprecated Use {@link #addCellAt(int)} instead
   */
  @Deprecated
  @Nonnull
  public IHCCell <?> addCell (@Nonnegative final int nIndex)
  {
    return addCellAt (nIndex);
  }

  /**
   * Add an empty cell at the specified index.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @return The created cell. Never <code>null</code>.
   */
  @Nonnull
  public IHCCell <?> addCellAt (@Nonnegative final int nIndex)
  {
    final AbstractHCCell <?> ret = m_bHeader ? new HCTH () : new HCTD ();
    ret.internalSetParentRow (this);
    addChildAt (nIndex, ret);
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
   * @deprecated Use {@link #addAndReturnCellAt(int,String)} instead
   */
  @Deprecated
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    return addAndReturnCellAt (nIndex, sCellText);
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
  public IHCCell <?> addAndReturnCellAt (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    return addCellAt (nIndex).addChild (sCellText);
  }

  /**
   * Add a single new cell and add the passed element.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChild
   *        The element to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   * @deprecated Use {@link #addAndReturnCellAt(int,IHCNode)} instead
   */
  @Deprecated
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNode aCellChild)
  {
    return addAndReturnCellAt (nIndex, aCellChild);
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
  public IHCCell <?> addAndReturnCellAt (@Nonnegative final int nIndex, @Nullable final IHCNode aCellChild)
  {
    return addCellAt (nIndex).addChild (aCellChild);
  }

  /**
   * Add a single cell with the given text elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellTexts
   *        The text to be set into the cell. May be <code>null</code>.
   * @return the created table cell
   * @deprecated Use {@link #addAndReturnCellAt(int,String...)} instead
   */
  @Deprecated
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final String... aCellTexts)
  {
    return addAndReturnCellAt (nIndex, aCellTexts);
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
  public IHCCell <?> addAndReturnCellAt (@Nonnegative final int nIndex, @Nullable final String... aCellTexts)
  {
    return addCellAt (nIndex).addChildren (aCellTexts);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   * @deprecated Use {@link #addAndReturnCellAt(int,IHCNode...)} instead
   */
  @Deprecated
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    return addAndReturnCellAt (nIndex, aCellChildren);
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
  public IHCCell <?> addAndReturnCellAt (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    return addCellAt (nIndex).addChildren (aCellChildren);
  }

  /**
   * Add a single new cell and add the passed elements.
   *
   * @param nIndex
   *        The index where the cell should be added
   * @param aCellChildren
   *        The list of elements to add. May be <code>null</code>.
   * @return The created cell. Never <code>null</code>.
   * @deprecated Use {@link #addAndReturnCellAt(int,Iterable)} instead
   */
  @Deprecated
  @Nonnull
  @CheckReturnValue
  public IHCCell <?> addAndReturnCell (@Nonnegative final int nIndex,
                                       @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    return addAndReturnCellAt (nIndex, aCellChildren);
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
  public IHCCell <?> addAndReturnCellAt (@Nonnegative final int nIndex,
                                         @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    return addCellAt (nIndex).addChildren (aCellChildren);
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
   * @deprecated Use {@link #addCellAt(int,String)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    return addCellAt (nIndex, sCellText);
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
  public HCRow addCellAt (@Nonnegative final int nIndex, @Nullable final String sCellText)
  {
    addCellAt (nIndex).addChild (sCellText);
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
   * @deprecated Use {@link #addCellAt(int,IHCNode)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNode aChild)
  {
    return addCellAt (nIndex, aChild);
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
  public HCRow addCellAt (@Nonnegative final int nIndex, @Nullable final IHCNode aChild)
  {
    addCellAt (nIndex).addChild (aChild);
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
   * @deprecated Use {@link #addCellAt(int,String...)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final String... aCellChildren)
  {
    return addCellAt (nIndex, aCellChildren);
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
  public HCRow addCellAt (@Nonnegative final int nIndex, @Nullable final String... aCellChildren)
  {
    addCellAt (nIndex).addChildren (aCellChildren);
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
   * @deprecated Use {@link #addCellAt(int,IHCNode...)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    return addCellAt (nIndex, aCellChildren);
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
  public HCRow addCellAt (@Nonnegative final int nIndex, @Nullable final IHCNode... aCellChildren)
  {
    addCellAt (nIndex).addChildren (aCellChildren);
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
   * @deprecated Use {@link #addCellAt(int,Iterable)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow addCell (@Nonnegative final int nIndex, @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    return addCellAt (nIndex, aCellChildren);
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
  public HCRow addCellAt (@Nonnegative final int nIndex, @Nullable final Iterable <? extends IHCNode> aCellChildren)
  {
    addCellAt (nIndex).addChildren (aCellChildren);
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
  public ICommonsList <IHCCell <?>> getAllCells ()
  {
    return getAllChildren ();
  }

  /**
   * @return All iterable cells. Avoids copying the list.
   */
  @Nonnull
  public Iterable <IHCCell <?>> getAllCellsIterable ()
  {
    return directGetAllChildren ();
  }

  /**
   * Get the cell at the specified index in this row. This method does consider
   * colspans!!!!
   *
   * @param nIndex
   *        The index of the cell. Should be &ge; 0.
   * @return <code>null</code> if no such cell is present
   */
  @Nullable
  public IHCCell <?> getCellAtEffectiveIndex (final int nIndex)
  {
    int i = 0;
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetAllChildren ())
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
      for (final IHCCell <?> aCell : directGetAllChildren ())
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
      for (final IHCCell <?> aCell : directGetAllChildren ())
        ret.addChild (aCell);
    return ret;
  }

  /**
   * Remove the cell at the specified index
   *
   * @param nIndex
   *        The index to remove
   * @return this
   * @deprecated Use {@link #removeCellAt(int)} instead
   */
  @Deprecated
  @Nonnull
  public HCRow removeCellAtIndex (@Nonnegative final int nIndex)
  {
    return removeCellAt (nIndex);
  }

  /**
   * Remove the cell at the specified index
   *
   * @param nIndex
   *        The index to remove
   * @return this
   */
  @Nonnull
  public HCRow removeCellAt (@Nonnegative final int nIndex)
  {
    removeChildAt (nIndex);
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
    return removeAllChildren ();
  }

  /**
   * @return <code>true</code> if at least one contained cell uses a colspan.
   */
  public boolean isColspanUsed ()
  {
    if (hasChildren ())
      for (final IHCCell <?> aCell : directGetAllChildren ())
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
      for (final IHCCell <?> aCell : directGetAllChildren ())
        if (aCell.getRowspan () > 1)
          return true;
    return false;
  }

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
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
