/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import com.helger.commons.string.StringHelper;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.commons.url.URLValidator;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Represents an HTML &lt;a&gt; element
 *
 * @author Philip Helger
 */
public class HCA extends AbstractHCA <HCA>
{
  public HCA ()
  {}

  public HCA (@Nonnull final ISimpleURL aHref)
  {
    super (aHref);
  }

  @Nullable
  public static IHCNode createLinkedWebsite (@Nullable final String sWebsite)
  {
    return createLinkedWebsite (sWebsite, (HC_Target) null);
  }

  @Nullable
  public static IHCNode createLinkedWebsite (@Nullable final String sWebsite, @Nullable final HC_Target aTarget)
  {
    if (StringHelper.hasNoText (sWebsite))
      return null;

    if (!URLValidator.isValid (sWebsite))
      return new HCTextNode (sWebsite);

    return HCA.of (sWebsite).setTarget (aTarget).addChild (sWebsite);
  }

  /**
   * @param sHref
   *        The target URL to link to. May be <code>null</code>.
   * @return A new {@link HCA} - depending on the link.
   * @since 8.3.2
   */
  @Nonnull
  public static HCA of (@Nullable final String sHref)
  {
    if (StringHelper.hasNoText (sHref))
      return new HCA ();
    return new HCA (new SimpleURL (sHref));
  }
}
