/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.column;

import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.version.Version;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.tabular.AbstractHCCol;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.masterdata.currency.ECurrency;
import com.helger.photon.uicore.css.CUICoreCSS;
import com.helger.photon.uictrls.datatables.DataTables;

/**
 * Specialized column for DataTables to be used in IHCTable implementation
 * constructors. Only when used with {@link DataTables} it will customize the
 * column design.
 *
 * @author Philip Helger
 */
public class DTCol extends AbstractHCCol <DTCol>
{
  private IHCNode m_aHeaderNode;
  private EDTColType m_eColType;
  private ESortOrder m_eInitialSorting = null;
  private boolean m_bSearchable = DataTablesColumnDef.DEFAULT_SEARCHABLE;
  private boolean m_bOrderable = DataTablesColumnDef.DEFAULT_ORDERABLE;
  private boolean m_bVisible = DataTablesColumnDef.DEFAULT_VISIBLE;
  private String m_sName;
  private int [] m_aDataSort;
  private Comparator <String> m_aComparator;

  public DTCol ()
  {
    this ((IHCNode) null);
  }

  public DTCol (@Nullable final String sHeaderText)
  {
    this (HCTextNode.createOnDemand (sHeaderText));
  }

  public DTCol (@Nullable final IHCNode aHeaderNode)
  {
    setWidth (CHTMLAttributeValues.STAR);
    setHeaderNode (aHeaderNode);
  }

  @Nullable
  public EDTColType getColType ()
  {
    return m_eColType;
  }

  @Nonnull
  public DTCol setDisplayType (@Nonnull final EDTColType eColType, @Nonnull final Locale aDisplayLocale)
  {
    return setDisplayType (eColType, aDisplayLocale, null);
  }

  @Nonnull
  public DTCol setDisplayType (@Nonnull final EDTColType eColType,
                               @Nonnull final Locale aDisplayLocale,
                               @Nullable final Function <? super String, String> aFormatter)
  {
    ValueEnforcer.notNull (eColType, "BaseType");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_eColType = eColType;
    switch (eColType)
    {
      case BOOLEAN:
        // Nothing special
        break;
      case DATE:
        setComparator (ComparatorDT.getComparatorDate (aFormatter, aDisplayLocale));
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        break;
      case DATETIME:
        setComparator (ComparatorDT.getComparatorDateTime (aFormatter, aDisplayLocale));
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        break;
      case DOUBLE:
        /*
         * Ensure that columns without text are sorted consistently compared to
         * the ones with non-numeric content
         */
        setComparator (ComparatorDT.getComparatorBigDecimal (aFormatter, aDisplayLocale));
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        break;
      case DURATION:
        setComparator (ComparatorDT.getComparatorDuration (aFormatter));
        break;
      case INT:
        /*
         * Ensure that columns without text are sorted consistently compared to
         * the ones with non-numeric content
         */
        setComparator (ComparatorDT.getComparatorBigInteger (aFormatter, aDisplayLocale));
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        break;
      case TEXT:
        setComparator (ComparatorDT.getComparatorString (aFormatter, aDisplayLocale));
        break;
      case TIME:
        setComparator (ComparatorDT.getComparatorTime (aFormatter, aDisplayLocale));
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        break;
      case VERSION:
        setComparator (ComparatorDT.getComparator (aFormatter, Version::parse));
        break;
      case XML:
        setOrderable (false);
        break;
      default:
        throw new IllegalArgumentException ("Unsupported base type provided: " + eColType);
    }
    return this;
  }

  @Nonnull
  public DTCol setDisplayTypeCurrency (@Nonnull final ECurrency eCurrency)
  {
    ValueEnforcer.notNull (eCurrency, "Currency");

    setComparator (ComparatorDT.getComparatorCurrencyFormat (eCurrency));
    addClass (CUICoreCSS.CSS_CLASS_RIGHT);
    return this;
  }

  @Nonnull
  public IHCNode getHeaderNode ()
  {
    return m_aHeaderNode;
  }

  @Nonnull
  public DTCol setHeaderNode (@Nullable final IHCNode aHeaderNode)
  {
    m_aHeaderNode = aHeaderNode;
    return this;
  }

  @Nonnull
  public ESortOrder getInitialSorting ()
  {
    return m_eInitialSorting;
  }

  public boolean hasInitialSorting ()
  {
    return m_eInitialSorting != null;
  }

  @Nonnull
  public DTCol setInitialSorting (@Nullable final ESortOrder eInitialSorting)
  {
    m_eInitialSorting = eInitialSorting;
    return this;
  }

  public boolean isSearchable ()
  {
    return m_bSearchable;
  }

  @Nonnull
  public DTCol setSearchable (final boolean bSearchable)
  {
    m_bSearchable = bSearchable;
    return this;
  }

  public boolean isOrderable ()
  {
    return m_bOrderable;
  }

  @Nonnull
  public DTCol setOrderable (final boolean bOrderable)
  {
    m_bOrderable = bOrderable;
    return this;
  }

  public boolean isVisible ()
  {
    return m_bVisible;
  }

  @Nonnull
  public DTCol setVisible (final boolean bVisible)
  {
    m_bVisible = bVisible;
    return this;
  }

  @Nullable
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public DTCol setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public int [] getDataSort ()
  {
    return ArrayHelper.getCopy (m_aDataSort);
  }

  /**
   * Set the column indices to sort, when this column is sorted
   *
   * @param aDataSort
   *        The sorting column (incl. this column!)
   * @return this
   */
  @Nonnull
  public DTCol setDataSort (@Nullable final int... aDataSort)
  {
    m_aDataSort = ArrayHelper.getCopy (aDataSort);
    return this;
  }

  @Nullable
  public Comparator <String> getComparator ()
  {
    return m_aComparator;
  }

  @Nonnull
  public DTCol setComparator (@Nullable final Comparator <String> aComparator)
  {
    m_aComparator = aComparator;
    return this;
  }
}
