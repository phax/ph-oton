package com.helger.photon.bootstrap4.form;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.forms.HCCheckBox;
import com.helger.html.hc.html.forms.HCRadioButton;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapCheckInline extends AbstractHCDiv <BootstrapCheckInline>
{
  private void _init ()
  {
    addClass (CBootstrapCSS.FORM_CHECK);
    addClass (CBootstrapCSS.FORM_CHECK_INLINE);
  }

  public BootstrapCheckInline (@Nonnull final HCCheckBox aCheckBox)
  {
    _init ();
    addChild (aCheckBox);
  }

  public BootstrapCheckInline (@Nonnull final HCRadioButton aRadioButton)
  {
    _init ();
    addChild (aRadioButton);
  }
}
