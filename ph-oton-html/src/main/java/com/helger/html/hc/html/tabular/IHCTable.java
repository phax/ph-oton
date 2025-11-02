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
package com.helger.html.hc.html.tabular;

import java.util.Comparator;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.misc.DevelopersNote;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsSet;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Base interface for HTML tables
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCTable <IMPLTYPE extends IHCTable <IMPLTYPE>> extends IHCElement <IMPLTYPE>
{
  /**
   * @return The table header. Never <code>null</code>.
   */
  @NonNull
  HCTHead getHead ();

  @NonNull
  IMPLTYPE setHead (@NonNull HCTHead aHead);

  /**
   * @return The table body. Never <code>null</code>.
   */
  @NonNull
  HCTBody getBody ();

  @NonNull
  IMPLTYPE setBody (@NonNull HCTBody aBody);

  /**
   * @return The table footer. Never <code>null</code>.
   */
  @NonNull
  HCTFoot getFoot ();

  @NonNull
  IMPLTYPE setFoot (@NonNull HCTFoot aFoot);

  /**
   * @return The cell spacing. Values &le; 0 mean undefined.
   */
  @CheckForSigned
  int getCellSpacing ();

  /**
   * Set the cell spacing.
   *
   * @param nCellSpacing
   *        New value. Values &le; 0 mean undefined.
   * @return this
   */
  @NonNull
  IMPLTYPE setCellSpacing (int nCellSpacing);

  /**
   * @return The cell padding. Values &le; 0 mean undefined.
   */
  @CheckForSigned
  int getCellPadding ();

  /**
   * Set the cell padding.
   *
   * @param nCellPadding
   *        New value. Values &le; 0 mean undefined.
   * @return this
   */
  @NonNull
  IMPLTYPE setCellPadding (int nCellPadding);

  // Column handling

  /**
   * @return The column group used for this table. May be <code>null</code> if
   *         undefined.
   */
  @Nullable
  HCColGroup getColGroup ();

  @NonNull
  @ReturnsMutableCopy
  default ICommonsList <? extends IHCCol <?>> getAllColumns ()
  {
    final HCColGroup aColGroup = getColGroup ();
    return aColGroup != null ? aColGroup.getAllColumns () : new CommonsArrayList <> ();
  }

  /**
   * Add the specified column.
   *
   * @param aCol
   *        The column to be added. May be <code>null</code>.
   * @return this
   */
  @NonNull
  IMPLTYPE addColumn (@Nullable IHCCol <?> aCol);

  /**
   * Add the specified column at the specified index.
   *
   * @param nIndex
   *        The index where the column should be added. Must be &ge; 0.
   * @param aCol
   *        The column to be added. May be <code>null</code>.
   * @return this
   */
  @NonNull
  IMPLTYPE addColumnAt (@Nonnegative int nIndex, @Nullable IHCCol <?> aCol);

  /**
   * Add a single column.
   *
   * @param aCol
   *        The column to be added. May be <code>null</code>.
   * @return this
   * @deprecated Use addColumn instead.
   */
  @NonNull
  @Deprecated (forRemoval = false)
  @DevelopersNote ("Use addColumn")
  default IMPLTYPE addColumns (@Nullable final IHCCol <?> aCol)
  {
    return addColumn (aCol);
  }

  /**
   * Add multiple columns at once.
   *
   * @param aCols
   *        The columns to be added. May be <code>null</code> and may contain
   *        <code>null</code> elements.
   * @return this
   */
  @NonNull
  default IMPLTYPE addColumns (@Nullable final IHCCol <?>... aCols)
  {
    if (aCols != null)
      for (final IHCCol <?> aCol : aCols)
        addColumn (aCol);
    return thisAsT ();
  }

  /**
   * Add multiple columns at once.
   *
   * @param aCols
   *        The columns to be added. May be <code>null</code> and may contain
   *        <code>null</code> elements.
   * @return this
   */
  @NonNull
  default IMPLTYPE addColumns (@Nullable final Iterable <? extends IHCCol <?>> aCols)
  {
    if (aCols != null)
      for (final IHCCol <?> aCol : aCols)
        addColumn (aCol);
    return thisAsT ();
  }

  /**
   * Remove the column definition at the specified index. This does not affect
   * any row contents.
   *
   * @param nColumnIndex
   *        The index of the column to remove
   * @return this
   */
  @NonNull
  IMPLTYPE removeColumnAt (@Nonnegative int nColumnIndex);

  /**
   * Remove all column definitions. This does not affect any row contents.
   *
   * @return this
   */
  @NonNull
  IMPLTYPE removeAllColumns ();

  /**
   * @return The number of columns as specified in the column group. If no
   *         column group is defined, 0 is returned.
   */
  @Nonnegative
  int getColumnCount ();

  // Header rows

  /**
   * @return The ID of the table head. May be <code>null</code>.
   */
  @Nullable
  default String getHeaderID ()
  {
    return getHead ().getID ();
  }

  /**
   * Set the ID of the table head.
   *
   * @param sID
   *        The table head ID. May be <code>null</code>-
   * @return this
   */
  @NonNull
  default IMPLTYPE setHeaderID (@Nullable final String sID)
  {
    getHead ().setID (sID);
    return thisAsT ();
  }

  /**
   * @return <code>true</code> if a table header ID is present,
   *         <code>false</code> otherwise
   */
  default boolean hasHeaderID ()
  {
    return getHead ().hasID ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsSet <ICSSClassProvider> getAllHeaderClasses ()
  {
    return getHead ().getAllClasses ();
  }

  @NonNull
  default String getAllHeaderClassesAsString ()
  {
    return getHead ().getAllClassesAsString ();
  }

  @NonNull
  default IMPLTYPE addHeaderClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getHead ().addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeHeaderClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getHead ().removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  default boolean hasHeaderClasses ()
  {
    return getHead ().hasAnyClass ();
  }

  default boolean hasHeaderRows ()
  {
    return getHead ().hasChildren ();
  }

  @Nonnegative
  default int getHeaderRowCount ()
  {
    return getHead ().getChildCount ();
  }

  @Nullable
  default HCRow getFirstHeaderRow ()
  {
    return getHead ().getFirstChild ();
  }

  @Nullable
  default HCRow getHeaderRowAtIndex (@Nonnegative final int nIndex)
  {
    return getHead ().getChildAtIndex (nIndex);
  }

  @Nullable
  default HCRow getLastHeaderRow ()
  {
    return getHead ().getLastChild ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsList <HCRow> getAllHeaderRows ()
  {
    return getHead ().getAllChildren ();
  }

  @NonNull
  default HCRow addHeaderRow ()
  {
    return getHead ().addRow ();
  }

  /**
   * Add a header row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default HCRow addHeaderRowAt (@Nonnegative final int nIndex)
  {
    return getHead ().addRowAt (nIndex);
  }

  @NonNull
  default IMPLTYPE addHeaderRow (@Nullable final HCRow aRow)
  {
    getHead ().addChild (aRow);
    return thisAsT ();
  }

  /**
   * Add a header row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @param aRow
   *        The row to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE addHeaderRowAt (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    getHead ().addChildAt (nIndex, aRow);
    return thisAsT ();
  }

  /**
   * Remove the header row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE removeHeaderRowAt (@Nonnegative final int nIndex)
  {
    getHead ().removeChildAt (nIndex);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeAllHeaderRows ()
  {
    getHead ().removeAllChildren ();
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE sortAllHeaderRows (@NonNull final Comparator <? super HCRow> aComparator)
  {
    getHead ().sortAllChildren (aComparator);
    return thisAsT ();
  }

  // Footer rows

  @Nullable
  default String getFooterID ()
  {
    return getFoot ().getID ();
  }

  @NonNull
  default IMPLTYPE setFooterID (@Nullable final String sID)
  {
    getFoot ().setID (sID);
    return thisAsT ();
  }

  /**
   * @return <code>true</code> if a Footer ID is present, <code>false</code>
   *         otherwise
   */
  default boolean hasFooterID ()
  {
    return getFoot ().hasID ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsSet <ICSSClassProvider> getAllFooterClasses ()
  {
    return getFoot ().getAllClasses ();
  }

  @NonNull
  default String getAllFooterClassesAsString ()
  {
    return getFoot ().getAllClassesAsString ();
  }

  @NonNull
  default IMPLTYPE addFooterClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getFoot ().addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeFooterClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getFoot ().removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  default boolean hasFooterClasses ()
  {
    return getFoot ().hasAnyClass ();
  }

  default boolean hasFooterRows ()
  {
    return getFoot ().hasChildren ();
  }

  @Nonnegative
  default int getFooterRowCount ()
  {
    return getFoot ().getChildCount ();
  }

  @Nullable
  default HCRow getFirstFooterRow ()
  {
    return getFoot ().getFirstChild ();
  }

  @Nullable
  default HCRow getFooterRowAtIndex (@Nonnegative final int nIndex)
  {
    return getFoot ().getChildAtIndex (nIndex);
  }

  @Nullable
  default HCRow getLastFooterRow ()
  {
    return getFoot ().getLastChild ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsList <HCRow> getAllFooterRows ()
  {
    return getFoot ().getAllChildren ();
  }

  @NonNull
  default HCRow addFooterRow ()
  {
    return getFoot ().addRow ();
  }

  /**
   * Add a footer row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default HCRow addFooterRowAt (@Nonnegative final int nIndex)
  {
    return getFoot ().addRowAt (nIndex);
  }

  @NonNull
  default IMPLTYPE addFooterRow (@Nullable final HCRow aRow)
  {
    getFoot ().addChild (aRow);
    return thisAsT ();
  }

  /**
   * Add a footer row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @param aRow
   *        The row to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE addFooterRowAt (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    getFoot ().addChildAt (nIndex, aRow);
    return thisAsT ();
  }

  /**
   * Remove a footer row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE removeFooterRowAt (@Nonnegative final int nIndex)
  {
    getFoot ().removeChildAt (nIndex);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeAllFooterRows ()
  {
    getFoot ().removeAllChildren ();
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE sortAllFooterRows (@NonNull final Comparator <? super HCRow> aComparator)
  {
    getFoot ().sortAllChildren (aComparator);
    return thisAsT ();
  }

  // Body rows

  @Nullable
  default String getBodyID ()
  {
    return getBody ().getID ();
  }

  @NonNull
  default IMPLTYPE setBodyID (@Nullable final String sID)
  {
    getBody ().setID (sID);
    return thisAsT ();
  }

  /**
   * @return <code>true</code> if a body ID is present, <code>false</code>
   *         otherwise
   */
  default boolean hasBodyID ()
  {
    return getBody ().hasID ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsSet <ICSSClassProvider> getAllBodyClasses ()
  {
    return getBody ().getAllClasses ();
  }

  @NonNull
  default String getAllBodyClassesAsString ()
  {
    return getBody ().getAllClassesAsString ();
  }

  @NonNull
  default IMPLTYPE addBodyClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getBody ().addClass (aCSSClassProvider);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeBodyClass (@NonNull final ICSSClassProvider aCSSClassProvider)
  {
    getBody ().removeClass (aCSSClassProvider);
    return thisAsT ();
  }

  default boolean hasBodyClasses ()
  {
    return getBody ().hasAnyClass ();
  }

  default boolean hasBodyRows ()
  {
    return getBody ().hasChildren ();
  }

  @Nonnegative
  default int getBodyRowCount ()
  {
    return getBody ().getChildCount ();
  }

  @Nullable
  default HCRow getFirstBodyRow ()
  {
    return getBody ().getFirstChild ();
  }

  @Nullable
  default HCRow getBodyRowAtIndex (@Nonnegative final int nIndex)
  {
    return getBody ().getChildAtIndex (nIndex);
  }

  @Nullable
  default HCRow getLastBodyRow ()
  {
    return getBody ().getLastChild ();
  }

  @NonNull
  @ReturnsMutableCopy
  default ICommonsList <HCRow> getAllBodyRows ()
  {
    return getBody ().getAllChildren ();
  }

  @NonNull
  default HCRow addBodyRow ()
  {
    return getBody ().addRow ();
  }

  /**
   * Add a body row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default HCRow addBodyRowAt (@Nonnegative final int nIndex)
  {
    return getBody ().addRowAt (nIndex);
  }

  @NonNull
  default IMPLTYPE addBodyRow (@Nullable final HCRow aRow)
  {
    getBody ().addChild (aRow);
    return thisAsT ();
  }

  /**
   * Add a body row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @param aRow
   *        The row to be added. May be <code>null</code>.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE addBodyRowAt (@Nonnegative final int nIndex, @Nullable final HCRow aRow)
  {
    getBody ().addChildAt (nIndex, aRow);
    return thisAsT ();
  }

  /**
   * Remove the body row at the specified index.
   *
   * @param nIndex
   *        The index to be used. Should be &ge; 0.
   * @return this for chaining
   */
  @NonNull
  default IMPLTYPE removeBodyRowAt (@Nonnegative final int nIndex)
  {
    getBody ().removeChildAt (nIndex);
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE removeAllBodyRows ()
  {
    getBody ().removeAllChildren ();
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE sortAllBodyRows (@NonNull final Comparator <? super HCRow> aComparator)
  {
    getBody ().sortAllChildren (aComparator);
    return thisAsT ();
  }

  // Special layout stuff

  @NonNull
  default IMPLTYPE setSpanningHeaderContent (@Nullable final String sText)
  {
    return setSpanningHeaderContent (HCTextNode.createOnDemand (sText));
  }

  @NonNull
  default IMPLTYPE setSpanningHeaderContent (@Nullable final IHCNode aNode)
  {
    removeAllHeaderRows ();
    return addSpanningHeaderContent (aNode);
  }

  @NonNull
  default IMPLTYPE addSpanningHeaderContent (@Nullable final String sText)
  {
    return addSpanningHeaderContent (HCTextNode.createOnDemand (sText));
  }

  @NonNull
  default IMPLTYPE addSpanningHeaderContent (@Nullable final IHCNode aNode)
  {
    addHeaderRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE addSpanningBodyContent (@Nullable final String sText)
  {
    return addSpanningBodyContent (HCTextNode.createOnDemand (sText));
  }

  @NonNull
  default IMPLTYPE addSpanningBodyContent (@Nullable final IHCNode aNode)
  {
    addBodyRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  @NonNull
  default IMPLTYPE setSpanningFooterContent (@Nullable final String sText)
  {
    return setSpanningFooterContent (HCTextNode.createOnDemand (sText));
  }

  @NonNull
  default IMPLTYPE setSpanningFooterContent (@Nullable final IHCNode aNode)
  {
    removeAllFooterRows ();
    return addSpanningFooterContent (aNode);
  }

  @NonNull
  default IMPLTYPE addSpanningFooterContent (@Nullable final String sText)
  {
    return addSpanningFooterContent (HCTextNode.createOnDemand (sText));
  }

  @NonNull
  default IMPLTYPE addSpanningFooterContent (@Nullable final IHCNode aNode)
  {
    addFooterRow ().addAndReturnCell (aNode).setColspan (getColumnCount ());
    return thisAsT ();
  }

  void checkInternalConsistency ();
}
