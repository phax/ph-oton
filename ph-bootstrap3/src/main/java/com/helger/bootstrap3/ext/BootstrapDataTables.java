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
package com.helger.bootstrap3.ext;

import javax.annotation.Nonnull;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.bootstrap3.EBootstrapCSSPathProvider;
import com.helger.bootstrap3.EBootstrapJSPathProvider;
import com.helger.bootstrap3.table.AbstractBootstrapTable;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.hc.IHCTable;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webbasics.app.html.PerRequestJSIncludes;
import com.helger.webctrls.datatables.DataTables;
import com.helger.webctrls.datatables.DataTablesDom;

public class BootstrapDataTables extends DataTables
{
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
  protected void onRegisterExternalResources ()
  {
    registerExternalResources ();
  }

  public static void registerExternalResources ()
  {
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EBootstrapJSPathProvider.BOOTSTRAP_DATATABLES);
    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EBootstrapCSSPathProvider.BOOTSTRAP_DATATABLES);
  }
}
