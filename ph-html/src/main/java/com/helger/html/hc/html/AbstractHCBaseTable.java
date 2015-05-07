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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCTable;
import com.helger.html.hc.conversion.HCConsistencyChecker;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;
import com.helger.html.hc.impl.HCTextNode;

/**
 * This is the common base class for regular HC tables as well as for more
 * complex constructs (e.g. PUI)
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCBaseTable <IMPLTYPE extends AbstractHCBaseTable <IMPLTYPE>> extends AbstractHCElement <IMPLTYPE> implements IHCTable <IMPLTYPE>
{
  private HCColGroup m_aColGroup;
  private int m_nCellSpacing = CGlobal.ILLEGAL_UINT;
  private int m_nCellPadding = CGlobal.ILLEGAL_UINT;

  private HCTHead m_aHead = new HCTHead ();
  private HCTBody m_aBody = new HCTBody ();
  private HCTFoot m_aFoot = new HCTFoot ();

  /**
   * This constructor is used to create elements with logic like a table but
   * actually not having a top 'table' element
   *
   * @param aElement
   *        The HTML element to be used to create the table
   */
  protected AbstractHCBaseTable (@Nonnull @Nonempty final EHTMLElement aElement)
  {
    super (aElement);
  }

  /**
   * @return The table header. Never <code>null</code>.
   */
  @Nonnull
  public final HCTHead getHead ()
  {
    return m_aHead;
  }

  @Nonnull
  public final IMPLTYPE setHead (@Nonnull final HCTHead aHead)
  {
    m_aHead = ValueEnforcer.notNull (aHead, "Head");
    return thisAsT ();
  }

  /**
   * @return The table body. Never <code>null</code>.
   */
  @Nonnull
  public final HCTBody getBody ()
  {
    return m_aBody;
  }

  @Nonnull
  public final IMPLTYPE setBody (@Nonnull final HCTBody aBody)
  {
    m_aBody = ValueEnforcer.notNull (aBody, "Body");
    return thisAsT ();
  }

  /**
   * @return The table footer. Never <code>null</code>.
   */
  @Nonnull
  public final HCTFoot getFoot ()
  {
    return m_aFoot;
  }

  @Nonnull
  public final IMPLTYPE setFoot (@Nonnull final HCTFoot aFoot)
  {
    m_aFoot = ValueEnforcer.notNull (aFoot, "Foot");
    return thisAsT ();
  }

  public final boolean hasChildren ()
  {
    return m_aHead.hasChildren () || m_aBody.hasChildren () || m_aFoot.hasChildren ();
  }

  @Nonnegative
  public final int getChildCount ()
  {
    return m_aHead.getChildCount () + m_aBody.getChildCount () + m_aFoot.getChildCount ();
  }

  @Nullable
  public final HCRow getFirstChild ()
  {
    HCRow ret = getFirstHeaderRow ();
    if (ret == null)
    {
      ret = getFirstBodyRow ();
      if (ret == null)
        ret = getFirstFooterRow ();
    }
    return ret;
  }

  @Nullable
  public final HCRow getLastChild ()
  {
    HCRow ret = getLastFooterRow ();
    if (ret == null)
    {
      ret = getLastBodyRow ();
      if (ret == null)
        ret = getLastHeaderRow ();
    }
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  @Deprecated
  public final List <IHCNode> getChildren ()
  {
    return getAllChildren ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <IHCNode> getAllChildren ()
  {
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    ret.addAll (m_aHead.getAllChildren ());
    ret.addAll (m_aBody.getAllChildren ());
    ret.addAll (m_aFoot.getAllChildren ());
    return ret;
  }

  @Nullable
  public final HCRow getChildAtIndex (@Nonnegative final int nIndex)
  {
    int nRealIndex = nIndex;
    if (nRealIndex < getHeaderRowCount ())
      return getHeaderRowAtIndex (nRealIndex);
    nRealIndex -= getHeaderRowCount ();
    if (nRealIndex < getBodyRowCount ())
      return getBodyRowAtIndex (nRealIndex);
    nRealIndex -= getBodyRowCount ();
    if (nRealIndex < getFooterRowCount ())
      return getFooterRowAtIndex (nRealIndex);
    return null;
  }

  public final int getCellSpacing ()
  {
    return m_nCellSpacing;
  }

  @Nonnull
  public final IMPLTYPE setCellSpacing (final int nCellSpacing)
  {
    m_nCellSpacing = nCellSpacing;
    return thisAsT ();
  }

  public final int getCellPadding ()
  {
    return m_nCellPadding;
  }

  @Nonnull
  public final IMPLTYPE setCellPadding (final int nCellPadding)
  {
    m_nCellPadding = nCellPadding;
    return thisAsT ();
  }

  //
  // column handling
  //

  @Nullable
  public final HCColGroup getColGroup ()
  {
    return m_aColGroup;
  }

  @Nonnull
  public final IMPLTYPE addColumn (@Nullable final HCCol aCol)
  {
    if (aCol != null)
    {
      if (m_aColGroup == null)
        m_aColGroup = new HCColGroup ();
      m_aColGroup.addColumn (aCol);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addColumn (@Nonnegative final int nIndex, @Nullable final HCCol aCol)
  {
    if (aCol != null)
    {
      if (m_aColGroup == null)
        m_aColGroup = new HCColGroup ();
      m_aColGroup.addColumn (nIndex, aCol);
    }
    return thisAsT ();
  }

  @Nonnull
  @Deprecated
  public final IMPLTYPE addColumns (@Nullable final HCCol aCol)
  {
    return addColumn (aCol);
  }

  @Nonnull
  public final IMPLTYPE addColumns (@Nullable final HCCol... aCols)
  {
    if (aCols != null)
      for (final HCCol aCol : aCols)
        addColumn (aCol);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addColumns (@Nullable final Iterable <? extends HCCol> aCols)
  {
    if (aCols != null)
      for (final HCCol aCol : aCols)
        addColumn (aCol);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeColumnAtIndex (@Nonnegative final int nColumnIndex)
  {
    if (m_aColGroup != null)
      m_aColGroup.removeColumnAtIndex (nColumnIndex);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllColumns ()
  {
    m_aColGroup = null;
    return thisAsT ();
  }

  @Nonnegative
  public final int getColumnCount ()
  {
    return m_aColGroup == null ? 0 : m_aColGroup.getColumnCount ();
  }

  //
  // header row handling
  //

  @Nullable
  public final String getHeaderID ()
  {
    return m_aHead.getID ();
  }

  @Nonnull
  public final IMPLTYPE setHeaderID (@Nullable final String sID)
  {
    m_aHead.setID (sID);
    return thisAsT ();
  }

  public final boolean hasHeaderID ()
  {
    return m_aHead.hasID ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <ICSSClassProvider> getAllHeaderClasses ()
  {
    return m_aHead.getAllClasses ();
  }

  @Nonnull
  public final String getAllHeaderClassesAsString ()
  {
    return m_aHead.getAllClassesAsString ();
  }

  @Nonnull
  public final IMPLTYPE addHeaderClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aHead.addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeHeaderClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aHead.removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  public final boolean hasHeaderClasses ()
  {
    return m_aHead.hasAnyClass ();
  }

  public final boolean hasHeaderRows ()
  {
    return m_aHead.hasChildren ();
  }

  @Nonnegative
  public final int getHeaderRowCount ()
  {
    return m_aHead.getChildCount ();
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject (reason = "For performance reasons in derived classes")
  protected final List <HCRow> directGetHeaderRowList ()
  {
    return m_aHead.directGetRowList ();
  }

  @Nullable
  public final HCRow getFirstHeaderRow ()
  {
    return m_aHead.getFirstChild ();
  }

  @Nullable
  public final HCRow getHeaderRowAtIndex (@Nonnegative final int nIndex)
  {
    return m_aHead.getChildAtIndex (nIndex);
  }

  @Nullable
  public final HCRow getLastHeaderRow ()
  {
    return m_aHead.getLastChild ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <HCRow> getAllHeaderRows ()
  {
    return m_aHead.getAllChildren ();
  }

  @Nonnull
  public final HCRow addHeaderRow ()
  {
    return m_aHead.addRow ();
  }

  @Nonnull
  public final HCRow addHeaderRow (@Nonnegative final int nIndex)
  {
    return m_aHead.addRow (nIndex);
  }

  @Nonnull
  public final IMPLTYPE addHeaderRow (@Nullable final HCRow aRow)
  {
    m_aHead.addChild (aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addHeaderRow (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    m_aHead.addChild (nIndex, aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeHeaderRowAtIndex (@Nonnegative final int nIndex)
  {
    m_aHead.removeChild (nIndex);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllHeaderRows ()
  {
    m_aHead.removeAllChildren ();
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE sortAllHeaderRows (@Nonnull final Comparator <? super HCRow> aComparator)
  {
    m_aHead.sortAllChildren (aComparator);
    return thisAsT ();
  }

  //
  // footer row handling
  //
  @Nullable
  public final String getFooterID ()
  {
    return m_aFoot.getID ();
  }

  @Nonnull
  public final IMPLTYPE setFooterID (@Nullable final String sID)
  {
    m_aFoot.setID (sID);
    return thisAsT ();
  }

  public final boolean hasFooterID ()
  {
    return m_aFoot.hasID ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <ICSSClassProvider> getAllFooterClasses ()
  {
    return m_aFoot.getAllClasses ();
  }

  @Nonnull
  public final String getAllFooterClassesAsString ()
  {
    return m_aFoot.getAllClassesAsString ();
  }

  @Nonnull
  public final IMPLTYPE addFooterClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aFoot.addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeFooterClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aFoot.removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  public final boolean hasFooterClasses ()
  {
    return m_aFoot.hasAnyClass ();
  }

  public final boolean hasFooterRows ()
  {
    return m_aFoot.hasChildren ();
  }

  @Nonnegative
  public final int getFooterRowCount ()
  {
    return m_aFoot.getChildCount ();
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject (reason = "For performance reasons in derived classes")
  protected final List <HCRow> directGetFooterRowList ()
  {
    return m_aFoot.directGetRowList ();
  }

  @Nullable
  public final HCRow getFirstFooterRow ()
  {
    return m_aFoot.getFirstChild ();
  }

  @Nullable
  public final HCRow getFooterRowAtIndex (@Nonnegative final int nIndex)
  {
    return m_aFoot.getChildAtIndex (nIndex);
  }

  @Nullable
  public final HCRow getLastFooterRow ()
  {
    return m_aFoot.getLastChild ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <HCRow> getAllFooterRows ()
  {
    return m_aFoot.getAllChildren ();
  }

  @Nonnull
  public final HCRow addFooterRow ()
  {
    return m_aFoot.addRow ();
  }

  @Nonnull
  public final HCRow addFooterRow (@Nonnegative final int nIndex)
  {
    return m_aFoot.addRow (nIndex);
  }

  @Nonnull
  public final IMPLTYPE addFooterRow (@Nullable final HCRow aRow)
  {
    m_aFoot.addChild (aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addFooterRow (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    m_aFoot.addChild (nIndex, aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeFooterRowAtIndex (@Nonnegative final int nIndex)
  {
    m_aFoot.removeChild (nIndex);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllFooterRows ()
  {
    m_aFoot.removeAllChildren ();
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE sortAllFooterRows (@Nonnull final Comparator <? super HCRow> aComparator)
  {
    m_aFoot.sortAllChildren (aComparator);
    return thisAsT ();
  }

  //
  // body row handling
  //

  @Nullable
  public final String getBodyID ()
  {
    return m_aBody.getID ();
  }

  @Nonnull
  public final IMPLTYPE setBodyID (@Nullable final String sID)
  {
    m_aBody.setID (sID);
    return thisAsT ();
  }

  public final boolean hasBodyID ()
  {
    return m_aBody.hasID ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Set <ICSSClassProvider> getAllBodyClasses ()
  {
    return m_aBody.getAllClasses ();
  }

  @Nonnull
  public final String getAllBodyClassesAsString ()
  {
    return m_aBody.getAllClassesAsString ();
  }

  @Nonnull
  public final IMPLTYPE addBodyClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aBody.addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeBodyClass (@Nonnull final ICSSClassProvider aCSSClassProvider)
  {
    m_aBody.removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  public final boolean hasBodyClasses ()
  {
    return m_aBody.hasAnyClass ();
  }

  public final boolean hasBodyRows ()
  {
    return m_aBody.hasChildren ();
  }

  @Nonnegative
  public final int getBodyRowCount ()
  {
    return m_aBody.getChildCount ();
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject (reason = "For performance reasons in derived classes")
  protected final List <HCRow> directGetBodyRowList ()
  {
    return m_aBody.directGetRowList ();
  }

  @Nullable
  public final HCRow getFirstBodyRow ()
  {
    return m_aBody.getFirstChild ();
  }

  @Nullable
  public final HCRow getBodyRowAtIndex (@Nonnegative final int nIndex)
  {
    return m_aBody.getChildAtIndex (nIndex);
  }

  @Nullable
  public final HCRow getLastBodyRow ()
  {
    return m_aBody.getLastChild ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <HCRow> getAllBodyRows ()
  {
    return m_aBody.getAllChildren ();
  }

  @Nonnull
  public final HCRow addBodyRow ()
  {
    return m_aBody.addRow ();
  }

  @Nonnull
  public final HCRow addBodyRow (@Nonnegative final int nIndex)
  {
    return m_aBody.addRow (nIndex);
  }

  @Nonnull
  public final IMPLTYPE addBodyRow (@Nullable final HCRow aRow)
  {
    m_aBody.addChild (aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addBodyRow (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    m_aBody.addChild (nIndex, aRow);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeBodyRowAtIndex (@Nonnegative final int nIndex)
  {
    m_aBody.removeChild (nIndex);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeAllBodyRows ()
  {
    m_aBody.removeAllChildren ();
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE sortAllBodyRows (@Nonnull final Comparator <? super HCRow> aComparator)
  {
    m_aBody.sortAllChildren (aComparator);
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setSpanningHeaderContent (@Nullable final String sText)
  {
    return setSpanningHeaderContent (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public IMPLTYPE setSpanningHeaderContent (@Nullable final IHCNode aNode)
  {
    removeAllHeaderRows ();
    return addSpanningHeaderContent (aNode);
  }

  @Nonnull
  public IMPLTYPE addSpanningHeaderContent (@Nullable final String sText)
  {
    return addSpanningHeaderContent (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public IMPLTYPE addSpanningHeaderContent (@Nullable final IHCNode aNode)
  {
    addHeaderRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE addSpanningBodyContent (@Nullable final String sText)
  {
    return addSpanningBodyContent (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public IMPLTYPE addSpanningBodyContent (@Nullable final IHCNode aNode)
  {
    addBodyRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  @Nonnull
  public IMPLTYPE setSpanningFooterContent (@Nullable final String sText)
  {
    return setSpanningFooterContent (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public IMPLTYPE setSpanningFooterContent (@Nullable final IHCNode aNode)
  {
    removeAllFooterRows ();
    return addSpanningFooterContent (aNode);
  }

  @Nonnull
  public IMPLTYPE addSpanningFooterContent (@Nullable final String sText)
  {
    return addSpanningFooterContent (HCTextNode.createOnDemand (sText));
  }

  @Nonnull
  public IMPLTYPE addSpanningFooterContent (@Nullable final IHCNode aNode)
  {
    addFooterRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  //
  // code generation
  //

  @Override
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Avoid creating a table without header, body and footer
    return m_aBody.canConvertToNode (aConversionSettings) ||
           m_aHead.canConvertToNode (aConversionSettings) ||
           m_aFoot.canConvertToNode (aConversionSettings);
  }

  private static int _getApplicableRowspan (final int nCellIndex, @Nullable final List <int []> aRowSpans)
  {
    int nApplicableRowspan = 0;
    if (aRowSpans != null)
    {
      // The table has at least one rowspan defined
      int nDecrease = 1;
      for (final int [] aPrevRowRowspans : aRowSpans)
      {
        if (aPrevRowRowspans != null)
          if (nCellIndex < aPrevRowRowspans.length)
          {
            final int nEffectiveRowspan = aPrevRowRowspans[nCellIndex] - nDecrease;
            if (nEffectiveRowspan > 0)
              nApplicableRowspan++;
          }
        ++nDecrease;
      }
    }
    return nApplicableRowspan;
  }

  @Nonnegative
  private static int _getEffectiveCellCount (@Nonnull final HCRow aRow, @Nullable final List <int []> aRowSpans)
  {
    int nCellIndex = 0;
    for (final IHCCell <?> aCell : aRow.getAllChildren ())
      nCellIndex += _getApplicableRowspan (nCellIndex, aRowSpans) + aCell.getColspan ();
    // Any rowspan after the last cell?
    nCellIndex += _getApplicableRowspan (nCellIndex, aRowSpans);
    return nCellIndex;
  }

  private static void _checkConsistency (@Nonnull final String sContext,
                                         @Nonnull final AbstractHCTablePart <?> aPart,
                                         @Nonnegative final int nCols)
  {
    int nRowIndex = 0;
    boolean bTotalHasRowSpans = false;
    final List <int []> aTotalRowSpans = new ArrayList <int []> (aPart.getChildCount ());
    if (aPart.hasChildren ())
      for (final HCRow aBodyRow : aPart.directGetRowList ())
      {
        // Pass null if no row spans are defined!
        final int nRowCols = _getEffectiveCellCount (aBodyRow, bTotalHasRowSpans ? aTotalRowSpans : null);
        if (nRowCols != nCols)
          HCConsistencyChecker.consistencyWarning (sContext +
                                                   " row #" +
                                                   (nRowIndex + 1) +
                                                   " has " +
                                                   nRowCols +
                                                   " cells but was expecting " +
                                                   nCols +
                                                   " cells");
        // Add row span at the end of the row so that it affects following rows
        {
          final int [] aRowRowSpans = new int [aBodyRow.getCellCount ()];
          int nCellIndex = 0;
          boolean bRowHasRowSpans = false;
          for (final IHCCell <?> aCell : aBodyRow.getAllChildren ())
          {
            final int nRowSpan = aCell.getRowspan ();
            aRowRowSpans[nCellIndex++] = nRowSpan;
            if (nRowSpan != 1)
              bRowHasRowSpans = true;
          }
          if (bRowHasRowSpans)
          {
            // This row has at least one row span!
            aTotalRowSpans.add (0, aRowRowSpans);
            bTotalHasRowSpans = true;
          }
          else
          {
            // There are no row spans defined in this row
            aTotalRowSpans.add (0, null);
          }
        }
        nRowIndex++;
      }
  }

  public static void checkInternalConsistency (@Nonnull final AbstractHCBaseTable <?> aBaseTable)
  {
    // Determine number of columns to use
    int nCols = 0;
    if (aBaseTable.m_aColGroup != null)
      nCols = aBaseTable.m_aColGroup.getColumnCount ();
    if (nCols == 0 && aBaseTable.m_aHead.hasChildren ())
      nCols = aBaseTable.m_aHead.getFirstChild ().getEffectiveCellCount ();
    if (nCols == 0 && aBaseTable.m_aBody.hasChildren ())
      nCols = aBaseTable.m_aBody.getFirstChild ().getEffectiveCellCount ();
    if (nCols == 0 && aBaseTable.m_aFoot.hasChildren ())
      nCols = aBaseTable.m_aFoot.getFirstChild ().getEffectiveCellCount ();

    String sPrefix = "Table";
    if (StringHelper.hasText (aBaseTable.getID ()))
      sPrefix += " with ID " + aBaseTable.getID ();
    _checkConsistency (sPrefix + " header", aBaseTable.m_aHead, nCols);
    _checkConsistency (sPrefix + " body", aBaseTable.m_aBody, nCols);
    _checkConsistency (sPrefix + " footer", aBaseTable.m_aFoot, nCols);
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Propagate to children
    if (m_aHead.hasChildren ())
      for (final HCRow aHeaderRow : m_aHead.directGetRowList ())
        aHeaderRow.beforeConvertToNode (aConversionSettings);
    if (m_aBody.hasChildren ())
      for (final HCRow aBodyRow : m_aBody.directGetRowList ())
        aBodyRow.beforeConvertToNode (aConversionSettings);
    if (m_aFoot.hasChildren ())
      for (final HCRow aFooterRow : m_aFoot.directGetRowList ())
        aFooterRow.beforeConvertToNode (aConversionSettings);
  }

  @Override
  @Nonnull
  public final String getPlainText ()
  {
    final StringBuilder ret = new StringBuilder ();
    ret.append (m_aHead.getPlainText ());
    ret.append (m_aBody.getPlainText ());
    ret.append (m_aFoot.getPlainText ());
    return ret.toString ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("colGroup", m_aColGroup)
                            .append ("cellSpacing", m_nCellSpacing)
                            .append ("cellPadding", m_nCellPadding)
                            .append ("header", m_aHead)
                            .append ("body", m_aBody)
                            .append ("footer", m_aFoot)
                            .toString ();
  }
}
