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

import java.util.List;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.StringParser;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ECSSUnit;
import com.helger.css.property.CCSSProperties;
import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.impl.HCEntityNode;

/**
 * This is the common base class for regular HC tables as well as for more
 * complex constructs (e.g. PUI)
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCBaseTable <IMPLTYPE extends AbstractHCBaseTable <IMPLTYPE>>
                                          extends AbstractHCElement <IMPLTYPE> implements IHCTable <IMPLTYPE>
{
  public static final ICSSClassProvider CSS_FORCE_COLSPAN = DefaultCSSClassProvider.create ("force-colspan");

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

  @Override
  public final boolean hasChildren ()
  {
    return true;
  }

  @Override
  @Nonnegative
  public final int getChildCount ()
  {
    return (m_aColGroup != null ? 1 : 0) + 3;
  }

  @Override
  @Nullable
  public final IHCNode getFirstChild ()
  {
    return m_aColGroup != null ? m_aColGroup : m_aHead;
  }

  @Override
  @Nullable
  public final IHCNode getLastChild ()
  {
    return m_aFoot;
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <IHCNode> getAllChildren ()
  {
    final ICommonsList <IHCNode> ret = new CommonsArrayList<> ();
    if (m_aColGroup != null)
      ret.add (m_aColGroup);
    ret.add (m_aHead);
    ret.add (m_aBody);
    ret.add (m_aFoot);
    return ret;
  }

  @Override
  @Nullable
  public final IHCNode getChildAtIndex (@Nonnegative final int nIndex)
  {
    final boolean bHasColGroup = m_aColGroup != null;
    if (nIndex == 0)
      return bHasColGroup ? m_aColGroup : m_aHead;
    if (nIndex == 1)
      return bHasColGroup ? m_aHead : m_aBody;
    if (nIndex == 2)
      return bHasColGroup ? m_aBody : m_aFoot;
    if (nIndex == 3)
      if (bHasColGroup)
        return m_aFoot;
    return null;
  }

  @CheckForSigned
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

  @CheckForSigned
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
  public final IMPLTYPE addColumn (@Nullable final IHCCol <?> aCol)
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
  public final IMPLTYPE addColumnAt (@Nonnegative final int nIndex, @Nullable final IHCCol <?> aCol)
  {
    if (aCol != null)
    {
      if (m_aColGroup == null)
        m_aColGroup = new HCColGroup ();
      m_aColGroup.addColumnAt (nIndex, aCol);
    }
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE removeColumnAt (@Nonnegative final int nColumnIndex)
  {
    if (m_aColGroup != null)
      m_aColGroup.removeColumnAt (nColumnIndex);
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
  // header/footer row handling
  //

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject ("For performance reasons in derived classes")
  protected final ICommonsList <HCRow> directGetHeaderRowList ()
  {
    return m_aHead.directGetRowList ();
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject ("For performance reasons in derived classes")
  protected final ICommonsList <HCRow> directGetFooterRowList ()
  {
    return m_aFoot.directGetRowList ();
  }

  /**
   * Get the contained list object that holds all the rows. Handle with care
   * because it alters the internal data structures of this table.
   *
   * @return The contained list object for external row order handling.
   */
  @Nullable
  @ReturnsMutableObject ("For performance reasons in derived classes")
  protected final ICommonsList <HCRow> directGetBodyRowList ()
  {
    return m_aBody.directGetRowList ();
  }

  //
  // code generation
  //

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    /*
     * bug fix for IE9 table layout bug
     * (http://msdn.microsoft.com/en-us/library/ms531161%28v=vs.85%29.aspx) IE9
     * only interprets column widths if the first row does not use colspan (i.e.
     * at least one row does not use colspan)
     */
    if (m_aColGroup != null && m_aColGroup.hasColumns () && hasBodyRows () && getFirstBodyRow ().isColspanUsed ())
    {
      // Create a dummy row with explicit widths
      final HCRow aRow = new HCRow (false).addClass (CSS_FORCE_COLSPAN);
      for (final IHCCol <?> aCol : m_aColGroup.getAllColumns ())
      {
        final IHCCell <?> aCell = aRow.addAndReturnCell (HCEntityNode.newNBSP ());
        final int nWidth = StringParser.parseInt (aCol.getWidth (), -1);
        if (nWidth >= 0)
          aCell.addStyle (CCSSProperties.WIDTH.newValue (ECSSUnit.px (nWidth)));
      }
      addBodyRowAt (0, aRow);
    }
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Avoid creating a table without header, body and footer
    return m_aBody.canConvertToMicroNode (aConversionSettings) ||
           m_aHead.canConvertToMicroNode (aConversionSettings) ||
           m_aFoot.canConvertToMicroNode (aConversionSettings);
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
    final ICommonsList <int []> aTotalRowSpans = new CommonsArrayList<> (aPart.getChildCount ());
    if (aPart.hasChildren ())
      for (final HCRow aBodyRow : aPart.directGetRowList ())
      {
        // Pass null if no row spans are defined!
        final int nRowCols = _getEffectiveCellCount (aBodyRow, bTotalHasRowSpans ? aTotalRowSpans : null);
        if (nRowCols != nCols)
          HCConsistencyChecker.consistencyError (sContext +
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

  public void checkInternalConsistency ()
  {
    // Determine number of columns to use
    int nCols = 0;
    if (m_aColGroup != null)
      nCols = m_aColGroup.getColumnCount ();
    if (nCols == 0 && m_aHead.hasChildren ())
      nCols = m_aHead.getFirstChild ().getEffectiveCellCount ();
    if (nCols == 0 && m_aBody.hasChildren ())
      nCols = m_aBody.getFirstChild ().getEffectiveCellCount ();
    if (nCols == 0 && m_aFoot.hasChildren ())
      nCols = m_aFoot.getFirstChild ().getEffectiveCellCount ();

    String sPrefix = "Table";
    if (StringHelper.hasText (getID ()))
      sPrefix += " with ID " + getID ();
    _checkConsistency (sPrefix + " header", m_aHead, nCols);
    _checkConsistency (sPrefix + " body", m_aBody, nCols);
    _checkConsistency (sPrefix + " footer", m_aFoot, nCols);
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    checkInternalConsistency ();
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
                            .getToString ();
  }
}
