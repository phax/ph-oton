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
package com.helger.photon.uictrls.datatables.column;

import java.util.Locale;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.compare.ESortOrder;
import com.helger.commons.string.StringHelper;
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
  private ESortOrder m_eInitialSorting;
  private boolean m_bSearchable = DataTablesColumnDef.DEFAULT_SEARCHABLE;
  private boolean m_bOrderable = DataTablesColumnDef.DEFAULT_ORDERABLE;
  private boolean m_bVisible = DataTablesColumnDef.DEFAULT_VISIBLE;
  private String m_sName;
  private int [] m_aDataSort;
  private final DTOrderSpec m_aOrderSpec = new DTOrderSpec ();

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
    // WAI problem
    if (false)
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
                               @Nullable final Function <String, String> aFormatter)
  {
    ValueEnforcer.notNull (eColType, "BaseType");
    ValueEnforcer.notNull (aDisplayLocale, "DisplayLocale");
    m_eColType = eColType;
    m_aOrderSpec.setDisplayLocale (aDisplayLocale);
    switch (eColType)
    {
      case BOOLEAN:
        m_aOrderSpec.setCollating (false);
        break;
      case DATE:
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorDate (aDisplayLocale));
        m_aOrderSpec.setCollating (false);
        break;
      case DATETIME:
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorDateTime (aDisplayLocale));
        m_aOrderSpec.setCollating (false);
        break;
      case DOUBLE:
        /*
         * Ensure that columns without text are sorted consistently compared to
         * the ones with non-numeric content
         */
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorBigDecimal (aDisplayLocale));
        m_aOrderSpec.setCollating (false);
        break;
      case DURATION:
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorDuration ());
        m_aOrderSpec.setCollating (false);
        break;
      case INT:
        /*
         * Ensure that columns without text are sorted consistently compared to
         * the ones with non-numeric content
         */
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorBigInteger (aDisplayLocale));
        m_aOrderSpec.setCollating (false);
        break;
      case TEXT:
        m_aOrderSpec.setComparableExtractor (aFormatter, x -> x);
        m_aOrderSpec.setCollating (true);
        break;
      case TIME:
        addClass (CUICoreCSS.CSS_CLASS_RIGHT);
        m_aOrderSpec.setComparableExtractor (aFormatter, ComparatorDT.getExtractorTime (aDisplayLocale));
        m_aOrderSpec.setCollating (false);
        break;
      case VERSION:
        m_aOrderSpec.setComparableExtractor (aFormatter, x -> Version.parse (x));
        m_aOrderSpec.setCollating (false);
        break;
      case XML:
        setOrderable (false);
        m_aOrderSpec.setCollating (false);
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

    m_aOrderSpec.setComparableExtractor (null, ComparatorDT.getExtractorCurrencyFormat (eCurrency));
    addClass (CUICoreCSS.CSS_CLASS_RIGHT);
    return this;
  }

  @Nonnull
  public DTCol setDisplayTypePercentage (@Nonnull final Locale aDisplayLocale)
  {
    return setDisplayType (EDTColType.DOUBLE, aDisplayLocale, x -> StringHelper.trimEnd (x, "%"));
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

  @Nonnull
  public DTOrderSpec getOrderSpec ()
  {
    return m_aOrderSpec;
  }
}
