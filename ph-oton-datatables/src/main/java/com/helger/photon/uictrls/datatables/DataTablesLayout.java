/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.lang.ICloneable;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;

/**
 * Replacement of DT DOM element in v2. See
 * https://datatables.net/reference/option/layout
 *
 * @author Philip Helger
 */
public class DataTablesLayout implements ICloneable <DataTablesLayout>
{
  public enum EPlace
  {
    TOP ("top{}"),
    TOP_START ("top{}Start"),
    TOP_END ("top{}End"),
    BOTTOM ("bottom{}"),
    BOTTOM_START ("bottom{}Start"),
    BOTTOM_END ("bottom{}End");

    private final String m_sPlaceholder;

    EPlace (@Nonnull @Nonempty final String sPlaceholder)
    {
      m_sPlaceholder = sPlaceholder;
    }

    @Nonnull
    @Nonempty
    public String getKey (@Nonnegative final int nIndex)
    {
      if (nIndex == 0)
        return m_sPlaceholder.replace ("{}", "");
      return m_sPlaceholder.replace ("{}", Integer.toString (nIndex));
    }
  }

  public static final String AREA_INFO = "info";
  public static final String AREA_PAGE_LENGTH = "pageLength";
  public static final String AREA_PAGING = "paging";
  public static final String AREA_SEARCH = "search";

  private final ICommonsMap <String, IJSExpression> m_aMap;

  public DataTablesLayout ()
  {
    m_aMap = new CommonsHashMap <> ();
  }

  public DataTablesLayout (@Nonnull final DataTablesLayout rhs)
  {
    ValueEnforcer.notNull (rhs, "rhs");
    m_aMap = rhs.m_aMap.getClone ();
  }

  public final class Adder
  {
    private EPlace m_ePlace = EPlace.TOP;
    private int m_nIndex = 0;

    public Adder ()
    {}

    @Nonnull
    public Adder at (final EPlace ePlace)
    {
      ValueEnforcer.notNull (ePlace, "Place");
      m_ePlace = ePlace;
      return this;
    }

    @Nonnull
    public Adder at (final int nIndex)
    {
      ValueEnforcer.isGE0 (nIndex, "Index");
      m_nIndex = nIndex;
      return this;
    }

    public void set (@Nullable final String sText)
    {
      set (sText == null ? null : JSExpr.lit (sText));
    }

    public void set (@Nullable final IJSExpression aExpr)
    {
      DataTablesLayout.this.set (m_ePlace, m_nIndex, aExpr);
    }
  }

  @Nonnull
  public Adder adder ()
  {
    return new Adder ();
  }

  public void set (@Nonnull final EPlace ePlace, @Nonnegative final int nIndex, @Nullable final IJSExpression aExpr)
  {
    ValueEnforcer.notNull (ePlace, "Place");
    ValueEnforcer.isGE0 (nIndex, "Index");

    final String sKey = ePlace.getKey (nIndex);
    if (aExpr == null)
      m_aMap.remove (sKey);
    else
      m_aMap.put (sKey, aExpr);
  }

  @Nullable
  public JSAssocArray getAsJSAssocArray ()
  {
    if (m_aMap.isEmpty ())
      return null;

    final JSAssocArray ret = new JSAssocArray ();
    ret.addAll (m_aMap);
    return ret;
  }

  @Nonnull
  public DataTablesLayout getClone ()
  {
    return new DataTablesLayout (this);
  }
}
