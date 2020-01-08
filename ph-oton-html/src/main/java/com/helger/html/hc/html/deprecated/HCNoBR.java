/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.deprecated;

import com.helger.html.EHTMLElement;
import com.helger.html.annotation.DeprecatedInHTML5;
import com.helger.html.hc.html.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;NOBR&gt; element
 *
 * @author Philip Helger
 */
@DeprecatedInHTML5
public class HCNoBR extends AbstractHCElementWithChildren <HCNoBR>
{
  /**
   * Create a new NOBR element
   */
  public HCNoBR ()
  {
    super (EHTMLElement.NOBR);
  }
}
