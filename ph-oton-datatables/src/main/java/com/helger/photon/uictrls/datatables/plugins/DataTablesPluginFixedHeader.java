/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ETriState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.jscode.IJSExpression;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSExpr;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;

public class DataTablesPluginFixedHeader extends AbstractDataTablesPlugin
{
  public static final String PLUGIN_NAME = "fixedHeader";
  public static final boolean DEFAULT_FOOTER = false;
  public static final int DEFAULT_FOOTER_OFFSET = 0;
  public static final boolean DEFAULT_HEADER = true;
  public static final int DEFAULT_HEADER_OFFSET = 0;

  private ETriState m_eFooter = ETriState.UNDEFINED;
  private Integer m_aFooterOffset;
  private ETriState m_eHeader = ETriState.UNDEFINED;
  private Integer m_aHeaderOffset;

  public DataTablesPluginFixedHeader ()
  {
    super (PLUGIN_NAME);
  }

  @Nonnull
  public DataTablesPluginFixedHeader setFooter (final boolean bFooter)

  {
    return setFooter (ETriState.valueOf (bFooter));
  }

  @Nonnull
  public DataTablesPluginFixedHeader setFooter (@Nonnull final ETriState eFooter)
  {
    ValueEnforcer.notNull (eFooter, "Footer");
    m_eFooter = eFooter;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedHeader setFooterOffset (final int nFooterOffset)

  {
    return setFooterOffset (Integer.valueOf (nFooterOffset));
  }

  @Nonnull
  public DataTablesPluginFixedHeader setFooterOffset (@Nullable final Integer aFooterOffset)
  {
    m_aFooterOffset = aFooterOffset;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedHeader setHeader (final boolean bHeader)

  {
    return setHeader (ETriState.valueOf (bHeader));
  }

  @Nonnull
  public DataTablesPluginFixedHeader setHeader (@Nonnull final ETriState eHeader)
  {
    ValueEnforcer.notNull (eHeader, "Header");
    m_eHeader = eHeader;
    return this;
  }

  @Nonnull
  public DataTablesPluginFixedHeader setHeaderOffset (final int nHeaderOffset)

  {
    return setHeaderOffset (Integer.valueOf (nHeaderOffset));
  }

  @Nonnull
  public DataTablesPluginFixedHeader setHeaderOffset (@Nullable final Integer aHeaderOffset)
  {
    m_aHeaderOffset = aHeaderOffset;
    return this;
  }

  @Nullable
  public IJSExpression getInitParams ()
  {
    final JSAssocArray ret = new JSAssocArray ();
    if (m_eFooter.isDefined ())
      ret.add ("footer", m_eFooter.getAsBooleanValue (DEFAULT_FOOTER));
    if (m_aFooterOffset != null)
      ret.add ("footerOffset", m_aFooterOffset.intValue ());
    if (m_eHeader.isDefined ())
      ret.add ("header", m_eHeader.getAsBooleanValue (DEFAULT_HEADER));
    if (m_aHeaderOffset != null)
      ret.add ("headerOffset", m_aHeaderOffset.intValue ());
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
