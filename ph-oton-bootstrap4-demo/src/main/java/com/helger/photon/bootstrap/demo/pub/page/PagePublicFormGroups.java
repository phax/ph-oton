package com.helger.photon.bootstrap.demo.pub.page;

import java.util.function.Function;

import javax.annotation.Nonnull;

import com.helger.css.property.CCSSProperties;
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
  public PagePublicFormGroups (final String sID)
  {
    super (sID, "Form Groups");
  }

  @Override
  protected void fillContent (@Nonnull final WebPageExecutionContext aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final BootstrapForm aForm = aNodeList.addAndReturnChild (getUIHandler ().createFormSelf (aWPEC));

    if (true)
    {
      // Playground :)
      // Attention - affects all forms!
      HCFormLabelHelper.setDefaultLabelEnd (":");
      HCFormLabelHelper.setDefaultSuffixString (ELabelType.OPTIONAL, ")");
      HCFormLabelHelper.setDefaultSuffixString (ELabelType.ALTERNATIVE, "]");
      HCFormLabelHelper.setDefaultSuffixString (ELabelType.MANDATORY, "}");
    }

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
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelForCheckBox ("For RadioButton:").setCtrl (new HCRadioButton ()));
    final Function <String, IHCElementWithChildren <?>> sup = s -> new HCSpan ().addChild (s)
                                                                                .addChild (new HCSup ().addChild (")")
                                                                                                       .addStyle (CCSSProperties.COLOR.newValue ("red")));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabel (sup.apply ("optional2")).setCtrl ("foo"));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelAlternative (sup.apply ("alternative2")).setCtrl ("foo"));
    aForm.addFormGroup (new BootstrapFormGroup ().setLabelMandatory (sup.apply ("mandatory2")).setCtrl ("foo"));
  }
}
