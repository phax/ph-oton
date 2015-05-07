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
package com.helger.html.hc.html5;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLElement;
import com.helger.html.annotations.SinceHTML5;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Base class for &lt;source&gt; and &lt;track
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
@SinceHTML5
public abstract class AbstractHCMediaElementChild <THISTYPE extends AbstractHCMediaElementChild <THISTYPE>> extends AbstractHCElement <THISTYPE>
{
  public AbstractHCMediaElementChild (@Nonnull final EHTMLElement eElement)
  {
    super (eElement);
  }
}
