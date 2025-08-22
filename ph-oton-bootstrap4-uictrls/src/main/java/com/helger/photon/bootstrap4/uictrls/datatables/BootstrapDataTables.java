/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datatables;

import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.tabular.AbstractHCTable;
import com.helger.html.hc.html.tabular.IHCTable;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.EDataTablesB4CSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesB4JSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

@OutOfBandNode
public class BootstrapDataTables extends DataTables
{
  private static IBootstrapDataTablesConfigurator s_aConfigurator;

  public BootstrapDataTables (@Nonnull final IHCTable <?> aTable)
  {
    super (aTable);

    // Currently use DOM as default
    // See https://datatables.net/forums/discussion/comment/231103
    if (true)
      setDom (new BootstrapDataTablesDom ());
    else
      setLayout (new BootstrapDataTablesLayout ());
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesB4JSPathProvider.DATATABLES_BOOTSTRAP4);
    // Original CSS not needed, when Bootstrap is used
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EDataTablesCSSPathProvider.DATATABLES);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesB4CSSPathProvider.DATATABLES_BOOTSTRAP4);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.BOOTSTRAP4_DATATABLES_PH);
  }

  @Nullable
  public static IBootstrapDataTablesConfigurator getConfigurator ()
  {
    return s_aConfigurator;
  }

  public static void setConfigurator (@Nullable final IBootstrapDataTablesConfigurator aConfigurator)
  {
    s_aConfigurator = aConfigurator;
  }

  @Nonnull
  public static BootstrapDataTables createDefaultDataTables (@Nonnull final ILayoutExecutionContext aLEC,
                                                             @Nonnull final IHCTable <?> aTable)
  {
    // Assign special table class for Bootstrap look and feel
    aTable.addClass (CBootstrapCSS.TABLE).addClass (CBootstrapCSS.TABLE_STRIPED).addClass (CBootstrapCSS.TABLE_SM);

    final BootstrapDataTables ret = new BootstrapDataTables (aTable);
    ret.setDisplayLocale (aLEC.getDisplayLocale ());

    // Explicitly add all columns and apply the initial sorting
    ret.addAllColumns (aTable);

    if (s_aConfigurator != null)
      s_aConfigurator.configure (aLEC, aTable, ret);

    // Because DataTables renders its own "colgroup" element, we don't need ours
    if (aTable instanceof AbstractHCTable <?>)
      ((AbstractHCTable <?>) aTable).setRenderColGroupIfPresent (false);

    return ret;
  }
}
