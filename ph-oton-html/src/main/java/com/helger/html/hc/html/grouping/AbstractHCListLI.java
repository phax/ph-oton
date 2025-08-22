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
package com.helger.html.hc.html.grouping;

import com.helger.html.EHTMLElement;

import jakarta.annotation.Nonnull;

/**
 * Abstract HTML list element that uses {@link HCLI} as the item.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The real implementation type.
 */
public abstract class AbstractHCListLI <IMPLTYPE extends AbstractHCList <IMPLTYPE, HCLI>> extends AbstractHCList <IMPLTYPE, HCLI>
{
  public AbstractHCListLI (@Nonnull final EHTMLElement eElement)
  {
    super (eElement, HCLI.class);
  }

  @Override
  @Nonnull
  protected HCLI createEmptyItem ()
  {
    return new HCLI ();
  }
}
