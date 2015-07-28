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
package com.helger.photon.bootstrap3.uictrls.datatables;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.base.IHCTable;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.table.AbstractBootstrapTable;
import com.helger.photon.bootstrap3.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.bootstrap3.uictrls.EBootstrapUICtrlsJSPathProvider;
import com.helger.photon.core.app.html.PhotonCSS;
import com.helger.photon.core.app.html.PhotonJS;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.photon.uictrls.datatables.DataTables;
import com.helger.photon.uictrls.datatables.DataTablesDom;

@OutOfBandNode
public class BootstrapDataTables extends DataTables
{
  private static IBootstrapDataTablesConfigurator s_aConfigurator;

  public BootstrapDataTables (@Nonnull final IHCTable <?> aTable)
  {
    super (aTable);
    if (aTable instanceof AbstractBootstrapTable <?>)
    {
      ((AbstractBootstrapTable <?>) aTable).setStriped (true);
      ((AbstractBootstrapTable <?>) aTable).setHover (true);
    }
    setDom (new BootstrapDataTablesDom ());
  }

  @Override
  @OverrideOnDemand
  protected void weaveColVisIntoDom (@Nonnull final DataTablesDom aDom)
  {
    // Check if it is a Bootstrap datatables DOM
    final int i = aDom.indexOf (DataTablesDom.getDivString (CBootstrapCSS.ROW, CBootstrapCSS.HIDDEN_PRINT));
    if (i >= 0)
    {
      if (true)
        aDom.setPosition (i + 5).addColVis ();
      else
        aDom.setPosition (i + 1).openDiv (CBootstrapCSS.COL_XS_12).addColVis ().closeDiv ();
    }
    else
      super.weaveColVisIntoDom (aDom);
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.BOOTSTRAP_DATATABLES);
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.BOOTSTRAP_DATATABLES);
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.BOOTSTRAP_DATATABLES_PH);
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
  public static BootstrapDataTables createDefaultDataTables (@Nonnull final IWebPageExecutionContext aWPEC,
                                                             @Nonnull final IHCTable <?> aTable)
  {
    // Assign special table class for Bootstrap look and feel
    aTable.addClass (CBootstrapCSS.TABLE).addClass (CBootstrapCSS.TABLE_STRIPED);

    final BootstrapDataTables ret = new BootstrapDataTables (aTable);
    ret.setDisplayLocale (aWPEC.getDisplayLocale ());
    ret.addAllColumns (aTable);
    if (s_aConfigurator != null)
      s_aConfigurator.configure (aWPEC, aTable, ret);
    return ret;
  }
}
