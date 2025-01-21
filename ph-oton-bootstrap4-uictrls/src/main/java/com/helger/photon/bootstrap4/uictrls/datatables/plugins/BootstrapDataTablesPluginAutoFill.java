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
package com.helger.photon.bootstrap4.uictrls.datatables.plugins;

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.EDataTablesB4CSSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesB4JSPathProvider;
import com.helger.photon.uictrls.datatables.EDataTablesCSSPathProvider;
import com.helger.photon.uictrls.datatables.plugins.DataTablesPluginAutoFill;

public class BootstrapDataTablesPluginAutoFill extends DataTablesPluginAutoFill
{
  @Override
  public void registerExternalResources (final IHCConversionSettingsToNode aConversionSettings)
  {
    super.registerExternalResources (aConversionSettings);
    PhotonJS.registerJSIncludeForThisRequest (EDataTablesB4JSPathProvider.DATATABLES_AUTO_FILL_BOOTSTRAP4);
    // Change CSS
    PhotonCSS.unregisterCSSIncludeFromThisRequest (EDataTablesCSSPathProvider.DATATABLES_AUTO_FILL);
    PhotonCSS.registerCSSIncludeForThisRequest (EDataTablesB4CSSPathProvider.DATATABLES_AUTO_FILL_BOOTSTRAP4);
  }
}
