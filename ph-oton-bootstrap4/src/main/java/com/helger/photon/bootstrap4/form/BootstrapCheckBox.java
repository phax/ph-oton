/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapCheckBox extends AbstractHCCheckBox <BootstrapCheckBox>
{
  private void _init ()
  {
    addClass (CBootstrapCSS.FORM_CHECK_INPUT);
  }

  public BootstrapCheckBox (@Nonnull final IHCRequestFieldBoolean aRF)
  {
    super (aRF);
    _init ();
  }

  public BootstrapCheckBox (@Nonnull final IHCRequestFieldBooleanMultiValue aRF)
  {
    super (aRF);
    _init ();
  }
}
