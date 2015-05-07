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
package com.helger.bootstrap3.base;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.html.CHTMLAttributes;
import com.helger.html.entities.EHTMLEntity;
import com.helger.html.hc.html.AbstractHCButton;
import com.helger.html.hc.impl.HCEntityNode;

public class BootstrapCloseIcon extends AbstractHCButton <BootstrapCloseIcon>
{
  public BootstrapCloseIcon ()
  {
    addClass (CBootstrapCSS.CLOSE);
    setCustomAttr (CHTMLAttributes.ARIA_HIDDEN, "true");
    addChild (new HCEntityNode (EHTMLEntity.times, "x"));
  }
}
