/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.forms.AbstractHCRadioButton;
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.xml.microdom.IMicroElement;

public class BootstrapRadioButton extends AbstractHCRadioButton <BootstrapRadioButton>
{
  private void _init ()
  {
    addClass (CBootstrapCSS.RADIO);
  }

  public BootstrapRadioButton (@Nonnull final IHCRequestFieldBoolean aRF)
  {
    super (aRF);
    _init ();
  }

  public BootstrapRadioButton (@Nonnull final IHCRequestFieldBooleanMultiValue aRF)
  {
    super (aRF);
    _init ();
  }

  @Nonnull
  public BootstrapRadioButton setInline (final boolean bInline)
  {
    if (bInline)
      addClass (CBootstrapCSS.RADIO_INLINE);
    else
      removeClass (CBootstrapCSS.RADIO_INLINE);
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
