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
package com.helger.html.hc.html.embedded;

import com.helger.url.ISimpleURL;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;img&gt; element
 *
 * @author Philip Helger
 */
public class HCImg extends AbstractHCImg <HCImg>
{
  public HCImg ()
  {}

  @Nonnull
  public static HCImg create (@Nullable final ISimpleURL aSrc)
  {
    final HCImg ret = new HCImg ();
    if (aSrc != null)
      ret.setSrc (aSrc);
    return ret;
  }
}
