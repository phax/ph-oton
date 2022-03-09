/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.embedded;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;

/**
 * Represents an HTML &lt;area&gt; element
 *
 * @author Philip Helger
 */
public class HCArea extends AbstractHCArea <HCArea>
{
  public HCArea ()
  {}

  public HCArea (@Nonnull final ISimpleURL aHref)
  {
    super (aHref);
  }

  /**
   * @param sHref
   *        The target URL to link to. May be <code>null</code>.
   * @return A new {@link HCArea} - depending on the link.
   */
  @Nonnull
  public static HCArea of (@Nullable final String sHref)
  {
    if (StringHelper.hasNoText (sHref))
      return new HCArea ();
    return new HCArea (new SimpleURL (sHref));
  }
}
