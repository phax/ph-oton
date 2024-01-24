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
package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;

public class DataTablesPluginFixedColumns extends AbstractDataTablesPlugin
{
  public static final String PLUGIN_NAME = "fixedColumns";
  public static final EDTPFixedColumnsHeightMatch DEFAULT_HEIGHT_MATCH = EDTPFixedColumnsHeightMatch.SEMI_AUTO;
  public static final int DEFAULT_LEFT_COLUMNS = 1;
  public static final int DEFAULT_RIGHT_COLUMNS = 0;

  private EDTPFixedColumnsHeightMatch m_eHeightMatch;
  private Integer m_aLeftColumns;
  private Integer m_aRightColumns;

  public DataTablesPluginFixedColumns ()
  {
    super (PLUGIN_NAME);
  }

  @Nonnull
  public DataTablesPluginFixedColumns setHeightMatch (@Nullable final EDTPFixedColumnsHeightMatch eHeightMatch)
  {
    m_eHeightMatch = eHeightMatch;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedColumns setLeftColumns (final int nLeftColumns)
  {
    return setLeftColumns (Integer.valueOf (nLeftColumns));
  }

  @Nonnull
  public DataTablesPluginFixedColumns setLeftColumns (@Nullable final Integer aLeftColumns)
  {
    m_aLeftColumns = aLeftColumns;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedColumns setRightColumns (final int nRightColumns)
  {
    return setRightColumns (Integer.valueOf (nRightColumns));
  }

  @Nonnull
  public DataTablesPluginFixedColumns setRightColumns (@Nullable final Integer aRightColumns)
  {
    m_aRightColumns = aRightColumns;
    return this;
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eHeightMatch != null)
      ret.add ("heightMatch", m_eHeightMatch.getName ());
    if (m_aLeftColumns != null)
      ret.add ("leftColumns", m_aLeftColumns.intValue ());
    if (m_aRightColumns != null)
      ret.add ("rightColumns", m_aRightColumns.intValue ());
    if (ret.isEmpty ())
    {
      // No properties present
      return JSExpr.TRUE;
    }
    return ret;
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_FIXED_HEADER);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_FIXED_HEADER);
  }
}
