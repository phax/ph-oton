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

import com.helger.html.hc.api.EHCInputType;
import com.helger.html.hc.impl.AbstractHCInput;

/**
 * Base class for an HTML &lt;input&gt; element to be used as an edit
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementing type
 */
@Deprecated
public abstract class AbstractHCEdit <IMPLTYPE extends AbstractHCEdit <IMPLTYPE>> extends AbstractHCInput <IMPLTYPE>
{
  protected AbstractHCEdit (@Nonnull final EHCInputType eType)
  {
    super (eType);
  }

  protected AbstractHCEdit (@Nonnull final EHCInputType eType, @Nullable final String sName)
  {
    super (eType);
    setName (sName);
  }
}
