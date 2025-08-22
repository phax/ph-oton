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
package com.helger.photon.bootstrap.demo.pub.page;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.helger.datetime.helper.PDTFactory;
import com.helger.html.hc.html.forms.EHCInputType;
import com.helger.html.hc.html.forms.HCInput;
import com.helger.html.hc.html.textlevel.HCStrong;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.bootstrap4.inputgroup.BootstrapInputGroup;
import com.helger.photon.bootstrap4.uictrls.datetimepicker.BootstrapDateTimePicker;
import com.helger.photon.uicore.html.formlabel.HCFormLabel;
import com.helger.photon.uicore.page.WebPageExecutionContext;

import jakarta.annotation.Nonnull;

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
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Date selector [" +
                                                                                          aDisplayLocale +
                                                                                          "]")).setCtrl (aPicker));
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

    // Enforce US UI
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("datepicker",
                                                                              PDTFactory.getCurrentLocalDate (),
                                                                              Locale.US);
      aPicker.addChildSuffix ("Datum");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Date selector [" +
                                                                                          Locale.US +
                                                                                          "]")).setCtrl (aPicker));
    }

    // Enforce German UI
    {
      final BootstrapDateTimePicker aPicker = BootstrapDateTimePicker.create ("datepicker",
                                                                              PDTFactory.getCurrentLocalDate (),
                                                                              Locale.GERMANY);
      aPicker.addChildSuffix ("Datum");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Date selector [" +
                                                                                          Locale.GERMANY +
                                                                                          "]")).setCtrl (aPicker));
    }

    // Browser Default
    {
      final BootstrapInputGroup aBIG = new BootstrapInputGroup ();
      aBIG.addChild (new HCInput (EHCInputType.DATE).setName ("bla")
                                                    .setValue (DateTimeFormatter.ISO_DATE.format (PDTFactory.getCurrentLocalDate ())));
      aBIG.addChildSuffix ("Datum");
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (HCFormLabel.createOptional ("Browser default"))
                                                   .setCtrl (aBIG));
    }
  }
}
