/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLElement;

/**
 * Base class for &lt;source&gt; and &lt;track
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCMediaElementChild <IMPLTYPE extends AbstractHCMediaElementChild <IMPLTYPE>> extends
                                                  AbstractHCElement <IMPLTYPE> implements
                                                  IHCMediaElementChild <IMPLTYPE>
{
  public AbstractHCMediaElementChild (@Nonnull final EHTMLElement eElement)
  {
    super (eElement);
  }
}
