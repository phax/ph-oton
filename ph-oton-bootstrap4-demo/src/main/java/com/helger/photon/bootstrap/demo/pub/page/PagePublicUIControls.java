package com.helger.photon.bootstrap.demo.pub.page;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.compare.ESortOrder;
import com.helger.commons.datetime.PDTFactory;
import com.helger.html.hc.html.tabular.HCTable;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.uictrls.datatables.BootstrapDataTables;
import com.helger.photon.bootstrap4.uictrls.datetimepicker.BootstrapDateTimePicker;
import com.helger.photon.uicore.page.WebPageExecutionContext;
import com.helger.photon.uictrls.datatables.column.DTCol;
import com.helger.photon.uictrls.datatables.column.EDTColType;

public class PagePublicUIControls extends AbstractAppWebPage
{
  public PagePublicUIControls (final String sID)
  {
    super (sID, "UI Controls");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();
    final Locale aDisplayLocale = aWPEC.getDisplayLocale ();

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));

    // Date, time and datetime picker
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("datepicker",
                                                                              PDTFactory.getCurrentLocalDate (),
                                                                              aDisplayLocale);
      aPicker.addChildSuffix ("Date");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Date selector").setCtrl (aPicker));
    }
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("timepicker",
                                                                              PDTFactory.getCurrentLocalTime (),
                                                                              aDisplayLocale);
      aPicker.setPrependIcon (null);
      aPicker.addChildPrefix ("Pre1");
      aPicker.addChildSuffix ("Time");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Time selector").setCtrl (aPicker));
    }
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("datetimepicker",
                                                                              PDTFactory.getCurrentLocalDateTime (),
                                                                              aDisplayLocale);
      aPicker.setPrependIcon (new HCStrong ().addChild ("Bla"));
      aPicker.addChildSuffix ("Date & Time");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Date and time selector").setCtrl (aPicker));
    }

    // Data tables
    {
      final HCTable aTable = new HCTable (new DTCol ("Key").setDisplayType (EDTColType.TEXT, aDisplayLocale),
                                          new DTCol ("Value 1").setDisplayType (EDTColType.INT, aDisplayLocale)
                                                               .setInitialSorting (ESortOrder.ASCENDING),
                                          new DTCol ("Value 2").setDisplayType (EDTColType.INT, aDisplayLocale)).setID ("demo-table");
      for (int i = 0; i < 10; ++i)
        aTable.addBodyRow ().addCell ("Key " + (i + 1)).addCell (Integer.toString (Math.abs (100 - i))).addCell (Integer.toString (i * 10));
      aNodeList.addChild (aTable).addChild (BootstrapDataTables.createDefaultDataTables (aWPEC, aTable));
    }
  }
}
