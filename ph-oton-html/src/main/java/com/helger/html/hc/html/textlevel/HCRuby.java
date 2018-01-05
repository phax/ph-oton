/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.textlevel;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.html.EHTMLElement;
import com.helger.html.annotation.SinceHTML5;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;

@SinceHTML5
public class HCRuby extends AbstractHCElementWithInternalChildren <HCRuby, IHCRubyChild <?>>
{
  public HCRuby ()
  {
    super (EHTMLElement.RUBY);
  }

  public final boolean hasItems ()
  {
    return hasChildren ();
  }

  @Nonnull
  public final HCRuby addItem (@Nullable final IHCRubyChild <?> aChild)
  {
    if (aChild != null)
      addChild (aChild);
    return this;
  }
}
