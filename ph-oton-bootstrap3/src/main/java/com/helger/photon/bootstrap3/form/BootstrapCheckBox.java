/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.form;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.microdom.IMicroElement;
import com.helger.html.hcapi.IHCConversionSettingsToNode;
import com.helger.html.hchtml.forms.HCCheckBox;
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;
import com.helger.photon.bootstrap3.CBootstrapCSS;

public class BootstrapCheckBox extends HCCheckBox
{
  private void _init ()
  {
    addClass (CBootstrapCSS.CHECKBOX);
  }

  public BootstrapCheckBox ()
  {
    _init ();
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

  @Nonnull
  public BootstrapCheckBox setInline (final boolean bInline)
  {
    if (bInline)
      addClass (CBootstrapCSS.CHECKBOX_INLINE);
    else
      removeClass (CBootstrapCSS.CHECKBOX_INLINE);
    return this;
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    // Before calling super
    if (isDisabled ())
      addClass (CBootstrapCSS.DISABLED);

    super.fillMicroElement (aElement, aConversionSettings);
  }
}
