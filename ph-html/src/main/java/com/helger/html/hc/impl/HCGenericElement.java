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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLElement;

/**
 * Fallback implementation of {@link AbstractHCElement} for {@link EHTMLElement}
 * items that are not explicitly wrapped in its own class.
 * 
 * @author Philip Helger
 */
public class HCGenericElement extends AbstractHCElement <HCGenericElement>
{
  public HCGenericElement (@Nonnull final EHTMLElement eElement)
  {
    super (eElement);
  }
}
