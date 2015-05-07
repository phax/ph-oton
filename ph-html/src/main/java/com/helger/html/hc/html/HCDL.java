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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.EHTMLElement;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;

/**
 * Represents an HTML &lt;dl&gt; element
 *
 * @author Philip Helger
 */
public class HCDL extends AbstractHCElementWithInternalChildren <HCDL, AbstractHCDefinitionItem <?>>
{
  public HCDL ()
  {
    super (EHTMLElement.DL);
  }

  public final boolean hasItems ()
  {
    return hasChildren ();
  }

  @Nonnull
  public final HCDL addItem (@Nullable final AbstractHCDefinitionItem <?> aItem)
  {
    if (aItem != null)
      addChild (aItem);
    return this;
  }
}
