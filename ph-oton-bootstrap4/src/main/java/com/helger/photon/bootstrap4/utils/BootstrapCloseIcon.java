/*
 * Copyright (C) 2018-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.utils;

import com.helger.html.hc.html.forms.AbstractHCButton;
import com.helger.html.hc.html.textlevel.HCSpan;
import com.helger.html.hc.impl.HCEntityNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;

public class BootstrapCloseIcon extends AbstractHCButton <BootstrapCloseIcon>
{
  public BootstrapCloseIcon ()
  {
    addClass (CBootstrapCSS.CLOSE);
    // TODO translate
    customAttrs ().setAriaLabel ("Close");

    final HCSpan aTimes = new HCSpan ().addChild (HCEntityNode.times ());
    aTimes.customAttrs ().setAriaHidden (true);
    addChild (aTimes);
  }
}
