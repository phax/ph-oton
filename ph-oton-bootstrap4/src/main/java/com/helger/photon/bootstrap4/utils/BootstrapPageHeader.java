/**
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

import javax.annotation.Nullable;

import com.helger.html.EHTMLElement;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Bootstrap 4 page header.
 *
 * @author Philip Helger
 */
public class BootstrapPageHeader extends AbstractHCElementWithChildren <BootstrapPageHeader>
{
  public static final ICSSClassProvider CSS_CLASS_PAGE_HEADER = DefaultCSSClassProvider.create ("pageheader");

  public BootstrapPageHeader ()
  {
    super (EHTMLElement.H1);
    addClass (CBootstrapCSS.MT_1);
    addClass (CBootstrapCSS.MB_4);
    // Special class to easily identify respective elements
    addClass (CSS_CLASS_PAGE_HEADER);
  }

  @Nullable
  public static BootstrapPageHeader createOnDemand (@Nullable final IHCNode aNode)
  {
    return aNode == null ? null : new BootstrapPageHeader ().addChild (aNode);
  }
}
