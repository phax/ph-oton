package com.helger.photon.bootstrap.demo.pub.page;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.compare.ESortOrder;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

public class PagePublicDataTables extends AbstractAppWebPage
{
  public PagePublicDataTables (final String sID)
  {
    super (sID, "DataTables example");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final HCTable aTable = new HCTable (new DTCol ("Key").setDisplayType (EDTColType.TEXT, aDisplayLocale),
                                        new DTCol ("Value 1").setDisplayType (EDTColType.INT, aDisplayLocale)
                                                             .setInitialSorting (ESortOrder.ASCENDING),
                                        new DTCol ("Value 2").setDisplayType (EDTColType.INT, aDisplayLocale)).setID ("demo-table");
    for (int i = 0; i < 10; ++i)
      aTable.addBodyRow ().addCell ("Key " + (i + 1)).addCell (Integer.toString (Math.abs (100 - i))).addCell (Integer.toString (i * 10));
    aNodeList.addChild (aTable).addChild (BootstrapDataTables.createDefaultDataTables (aWPEC, aTable));
  }
}
