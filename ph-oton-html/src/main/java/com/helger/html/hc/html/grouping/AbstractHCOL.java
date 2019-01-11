/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

/**
 * Represents an HTML &lt;ol&gt; element using {@link HCLI} as children.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCOL <IMPLTYPE extends AbstractHCOL <IMPLTYPE>> extends AbstractHCOLBase <IMPLTYPE, HCLI>
                                   implements
                                   IHCOL <IMPLTYPE>
{
  public AbstractHCOL ()
  {
    super (HCLI.class);
  }

  @Override
  @Nonnull
  protected HCLI createEmptyItem ()
  {
    return new HCLI ();
  }
}
