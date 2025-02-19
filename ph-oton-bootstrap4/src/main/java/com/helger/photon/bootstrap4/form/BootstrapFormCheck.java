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
package com.helger.photon.bootstrap4.form;

import javax.annotation.Nonnull;

import com.helger.html.hc.html.forms.AbstractHCCheckBox;
import com.helger.html.hc.html.forms.AbstractHCRadioButton;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Special wrapper for a DIV with class "form-check" to handle check boxes and
 * radio buttons. An optional label can be added as a child.
 *
 * @author Philip Helger
 */
public class BootstrapFormCheck extends AbstractHCDiv <BootstrapFormCheck>
{
  private void _init ()
  {
    addClass (CBootstrapCSS.FORM_CHECK);
  }

  public BootstrapFormCheck (@Nonnull final AbstractHCCheckBox <?> aCheckBox)
  {
    _init ();
    addChild (aCheckBox);
    aCheckBox.addClass (CBootstrapCSS.FORM_CHECK_INPUT);
  }

  public BootstrapFormCheck (@Nonnull final AbstractHCRadioButton <?> aRadioButton)
  {
    _init ();
    addChild (aRadioButton);
    aRadioButton.addClass (CBootstrapCSS.FORM_CHECK_INPUT);
  }
}
