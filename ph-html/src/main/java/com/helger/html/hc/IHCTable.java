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
package com.helger.html.hc;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.HCCol;
import com.helger.html.hc.html.HCColGroup;
import com.helger.html.hc.html.HCRow;

/**
 * Base interface for HTML tables
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCTable <IMPLTYPE extends IHCTable <IMPLTYPE>> extends IHCElement <IMPLTYPE>, IHCHasChildren
{
  @Nonnegative
  int getCellSpacing ();

  @Nonnull
  IMPLTYPE setCellSpacing (@Nonnegative int nCellSpacing);

  @Nonnegative
  int getCellPadding ();

  @Nonnull
  IMPLTYPE setCellPadding (@Nonnegative int nCellPadding);

  // Column handling

  @Nullable
  HCColGroup getColGroup ();

  @Nonnull
  IMPLTYPE addColumn (@Nullable HCCol aCol);

  @Nonnull
  IMPLTYPE addColumn (@Nonnegative int nIndex, @Nullable HCCol aCol);

  @Nonnull
  IMPLTYPE addColumns (@Nullable HCCol aCol);

  @Nonnull
  IMPLTYPE addColumns (@Nullable HCCol... aCols);

  @Nonnull
  IMPLTYPE addColumns (@Nullable Iterable <? extends HCCol> aCols);

  /**
   * Remove the column definition at the specified index. This does not affect
   * any row contents.
   *
   * @param nColumnIndex
   *        The index of the column to remove
   * @return this
   */
  @Nonnull
  IMPLTYPE removeColumnAtIndex (@Nonnegative int nColumnIndex);

  /**
   * Remove all column definitions. This does not affect any row contents.
   *
   * @return this
   */
  @Nonnull
  IMPLTYPE removeAllColumns ();

  /**
   * @return The number of columns as specified in the column group. If no
   *         column group is defined, 0 is returned.
   */
  @Nonnegative
  int getColumnCount ();

  // Header rows

  @Nullable
  String getHeaderID ();

  @Nonnull
  IMPLTYPE setHeaderID (@Nullable String sID);

  /**
   * @return <code>true</code> if a Header ID is present, <code>false</code>
   *         otherwise
   */
  boolean hasHeaderID ();

  @Nonnull
  @ReturnsMutableCopy
  Set <ICSSClassProvider> getAllHeaderClasses ();

  @Nonnull
  String getAllHeaderClassesAsString ();

  @Nonnull
  IMPLTYPE addHeaderClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  @Nonnull
  IMPLTYPE removeHeaderClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  boolean hasHeaderClasses ();

  boolean hasHeaderRows ();

  @Nonnegative
  int getHeaderRowCount ();

  @Nullable
  HCRow getFirstHeaderRow ();

  @Nullable
  HCRow getHeaderRowAtIndex (@Nonnegative int nIndex);

  @Nullable
  HCRow getLastHeaderRow ();

  @Nonnull
  @ReturnsMutableCopy
  List <HCRow> getAllHeaderRows ();

  @Nonnull
  HCRow addHeaderRow ();

  @Nonnull
  HCRow addHeaderRow (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE addHeaderRow (@Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE addHeaderRow (@Nonnegative int nIndex, @Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE removeHeaderRowAtIndex (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE removeAllHeaderRows ();

  @Nonnull
  IMPLTYPE sortAllHeaderRows (@Nonnull Comparator <? super HCRow> aComparator);

  // Footer rows

  @Nullable
  String getFooterID ();

  @Nonnull
  IMPLTYPE setFooterID (@Nullable String sID);

  /**
   * @return <code>true</code> if a Footer ID is present, <code>false</code>
   *         otherwise
   */
  boolean hasFooterID ();

  @Nonnull
  @ReturnsMutableCopy
  Set <ICSSClassProvider> getAllFooterClasses ();

  @Nonnull
  String getAllFooterClassesAsString ();

  @Nonnull
  IMPLTYPE addFooterClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  @Nonnull
  IMPLTYPE removeFooterClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  boolean hasFooterClasses ();

  boolean hasFooterRows ();

  @Nonnegative
  int getFooterRowCount ();

  @Nullable
  HCRow getFirstFooterRow ();

  @Nullable
  HCRow getFooterRowAtIndex (@Nonnegative int nIndex);

  @Nullable
  HCRow getLastFooterRow ();

  @Nonnull
  @ReturnsMutableCopy
  List <HCRow> getAllFooterRows ();

  @Nonnull
  HCRow addFooterRow ();

  @Nonnull
  HCRow addFooterRow (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE addFooterRow (@Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE addFooterRow (@Nonnegative int nIndex, @Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE removeFooterRowAtIndex (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE removeAllFooterRows ();

  @Nonnull
  IMPLTYPE sortAllFooterRows (@Nonnull Comparator <? super HCRow> aComparator);

  // Body rows

  @Nullable
  String getBodyID ();

  @Nonnull
  IMPLTYPE setBodyID (@Nullable String sID);

  /**
   * @return <code>true</code> if a body ID is present, <code>false</code>
   *         otherwise
   */
  boolean hasBodyID ();

  @Nonnull
  @ReturnsMutableCopy
  Set <ICSSClassProvider> getAllBodyClasses ();

  @Nonnull
  String getAllBodyClassesAsString ();

  @Nonnull
  IMPLTYPE addBodyClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  @Nonnull
  IMPLTYPE removeBodyClass (@Nonnull ICSSClassProvider aCSSClassProvider);

  boolean hasBodyClasses ();

  boolean hasBodyRows ();

  @Nonnegative
  int getBodyRowCount ();

  @Nullable
  HCRow getFirstBodyRow ();

  @Nullable
  HCRow getBodyRowAtIndex (@Nonnegative int nIndex);

  @Nullable
  HCRow getLastBodyRow ();

  @Nonnull
  @ReturnsMutableCopy
  List <HCRow> getAllBodyRows ();

  @Nonnull
  HCRow addBodyRow ();

  @Nonnull
  HCRow addBodyRow (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE addBodyRow (@Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE addBodyRow (@Nonnegative int nIndex, @Nullable HCRow aRow);

  @Nonnull
  IMPLTYPE removeBodyRowAtIndex (@Nonnegative int nIndex);

  @Nonnull
  IMPLTYPE removeAllBodyRows ();

  @Nonnull
  IMPLTYPE sortAllBodyRows (@Nonnull Comparator <? super HCRow> aComparator);

  @Nonnull
  IMPLTYPE setSpanningHeaderContent (@Nullable String sText);

  @Nonnull
  IMPLTYPE setSpanningHeaderContent (@Nullable IHCNode aNode);

  @Nonnull
  IMPLTYPE addSpanningHeaderContent (@Nullable String sText);

  @Nonnull
  IMPLTYPE addSpanningHeaderContent (@Nullable IHCNode aNode);

  @Nonnull
  IMPLTYPE addSpanningBodyContent (@Nullable String sText);

  @Nonnull
  IMPLTYPE addSpanningBodyContent (@Nullable IHCNode aNode);

  @Nonnull
  IMPLTYPE setSpanningFooterContent (@Nullable String sText);

  @Nonnull
  IMPLTYPE setSpanningFooterContent (@Nullable IHCNode aNode);

  @Nonnull
  IMPLTYPE addSpanningFooterContent (@Nullable String sText);

  @Nonnull
  IMPLTYPE addSpanningFooterContent (@Nullable IHCNode aNode);
}
