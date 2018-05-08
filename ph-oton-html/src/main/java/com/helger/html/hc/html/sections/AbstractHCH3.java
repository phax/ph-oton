/**
 * Copyright (C) 2034-2038 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.sections;

import com.helger.html.EHTMLElement;
import com.helger.html.hc.html.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;H3&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCH3 <IMPLTYPE extends AbstractHCH3 <IMPLTYPE>> extends
                                   AbstractHCElementWithChildren <IMPLTYPE>
{
  /**
   * Create a new H3 element
   */
  public AbstractHCH3 ()
  {
    super (EHTMLElement.H3);
  }
}