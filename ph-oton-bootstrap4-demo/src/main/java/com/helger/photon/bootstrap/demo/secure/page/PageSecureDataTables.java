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
package com.helger.photon.bootstrap.demo.secure.page;

import java.util.Locale;

import com.helger.base.compare.ESortOrder;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

import jakarta.annotation.Nonnull;

public class PageSecureDataTables extends AbstractAppWebPage
{
  public PageSecureDataTables (final String sID)
  {
    super (sID, "Admin DataTables example");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final HCTable aTable = new HCTable (new DTCol ("Name").setDisplayType (EDTColType.TEXT, aDisplayLocale),
                                        new DTCol ("ID").setDisplayType (EDTColType.TEXT, aDisplayLocale)
                                                        .setInitialSorting (ESortOrder.ASCENDING)).setID ("demo-table-sec");
    aTable.addBodyRow ().addCell (aWPEC.getLoggedInUser ().getDisplayName ()).addCell (aWPEC.getLoggedInUserID ());
    aNodeList.addChild (aTable).addChild (BootstrapDataTables.createDefaultDataTables (aWPEC, aTable));
  }
}
