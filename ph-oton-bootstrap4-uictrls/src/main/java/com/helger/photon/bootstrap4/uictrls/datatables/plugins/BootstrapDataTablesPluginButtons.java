/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.datatables.plugins;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTablesDom;
import com.helger.photon.uictrls.datatables.DataTablesDom;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginButtons;

public class BootstrapDataTablesPluginButtons extends DataTablesPluginButtons
{
  @Override
  @Nonnull
  @ReturnsMutableCopy
  @OverrideOnDemand
  protected DataTablesDom createDom ()
  {
    return new BootstrapDataTablesDom ();
  }

  @Override
  @OverrideOnDemand
  protected void weaveIntoDom (@Nonnull final DataTablesDom aDom)
  {
    // Check if it is a Bootstrap datatables DOM
    final int i = aDom.indexOf (DataTablesDom.getDivString (CBootstrapCSS.ROW, CBootstrapCSS.D_PRINT_NONE));
    if (i >= 0)
    {
      aDom.setPosition (i + 1);
      aDom.remove ();
      aDom.openDiv (CBootstrapCSS.COL_SM_4);
      aDom.setPosition (i + 4).openDiv (CBootstrapCSS.COL_SM_4).addCustom ("B").closeDiv ();
      aDom.setPosition (i + 7);
      aDom.remove ();
      aDom.openDiv (CBootstrapCSS.COL_SM_4);
    }
    else
      super.weaveIntoDom (aDom);
  }

  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesJSPathProvider.DATATABLES_BUTTONS_BOOTSTRAP4);
    // Change CSS
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EDataTablesCSSPathProvider.DATATABLES_BUTTONS);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesCSSPathProvider.DATATABLES_BUTTONS_BOOTSTRAP4);
  }
}
