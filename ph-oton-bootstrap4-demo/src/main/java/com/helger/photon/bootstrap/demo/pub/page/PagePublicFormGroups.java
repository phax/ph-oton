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

import java.util.function.Function;

import javax.annotation.Nonnull;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.html.IHCElementWithChildren;
import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCRadioButton;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.html.textlevel.HCSup;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.bootstrap.demo.app.ui.AbstractAppWebPage;
import com.helger.photon.bootstrap4.form.BootstrapForm;
import com.helger.photon.bootstrap4.form.BootstrapFormGroup;
import com.helger.photon.uicore.html.formlabel.ELabelType;
import com.helger.photon.uicore.html.formlabel.HCFormLabelHelper;
import com.helger.photon.uicore.page.WebPageExecutionContext;

public class PagePublicFormGroups extends AbstractAppWebPage
{
  private static final ICSSClassProvider CSS_CLASS_RED = DefaultCSSClassProvider.create ("red");

  public PagePublicFormGroups (final String sID)
  {
    super (sID, "Form Groups");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));

    // Playground :)
    // Attention - affects all forms!
    HCFormLabelHelper.setDefaultLabelEnd (":");
    HCFormLabelHelper.setDefaultSuffixString (ELabelType.OPTIONAL, ")");
    HCFormLabelHelper.setDefaultSuffixString (ELabelType.ALTERNATIVE, "]");
    HCFormLabelHelper.setDefaultSuffixString (ELabelType.MANDATORY, "}");

    try
    {
      // Specific form labels
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Optional").setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel ("Optional?").setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelAlternative ("Alternative").setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("Mandatory").setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For CheckBox").setCtrl (new HCCheckBox ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory ("For CheckBox").setCtrl (new HCCheckBox ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For CheckBox?").setCtrl (new HCCheckBox ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For CheckBox)").setCtrl (new HCCheckBox ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For CheckBox:").setCtrl (new HCCheckBox ()));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For RadioButton:")
                                                   .setCtrl (new HCRadioButton ()));
      final Function <String, IHCElementWithChildren <?>> sup = s -> new HCSpan ().addChild (s)
                                                                                  .addChild (new HCSup ().addChild (")")
                                                                                                         .addClass (CSS_CLASS_RED));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sup.apply ("optional2")).setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelAlternative (sup.apply ("alternative2")).setCtrl ("foo"));
      aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sup.apply ("mandatory2")).setCtrl ("foo"));
    }
    finally
    {
      HCFormLabelHelper.resetToDefault (true);
    }
  }
}
