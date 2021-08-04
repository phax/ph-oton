package com.helger.photon.bootstrap.demo.pub.page;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.datetime.PDTFactory;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.uictrls.datetimepicker.BootstrapDateTimePicker;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.page.WebPageExecutionContext;

public class PagePublicDateTimePicker extends AbstractAppWebPage
{
  public PagePublicDateTimePicker (final String sID)
  {
    super (sID, "DateTimePicker");
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
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Date selector")).setCtrl (aPicker));
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

    // Enforce German UI
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("datepicker",
                                                                              PDTFactory.getCurrentLocalDate (),
                                                                              Locale.GERMANY);
      aPicker.addChildSuffix ("Datum");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Date selector in German")).setCtrl (aPicker));
    }
  }
}
