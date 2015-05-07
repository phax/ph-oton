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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.microdom.IMicroNode;
import com.helger.html.hc.IHCWrappingNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;

/**
 * Abstract implementation of {@link IHCWrappingNode}
 * 
 * @author Philip Helger
 */
@NotThreadSafe
public abstract class AbstractHCWrappingNode extends AbstractHCNode implements IHCWrappingNode
{
  @Override
  @Nonnull
  public String getPlainText ()
  {
    return getWrappedNode ().getPlainText ();
  }

  @Override
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return getWrappedNode ().canConvertToNode (aConversionSettings);
  }

  @Override
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Propagate to wrapped node
    getWrappedNode ().beforeConvertToNode (aConversionSettings);
  }

  @Override
  @Nullable
  protected IMicroNode internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return getWrappedNode ().convertToNode (aConversionSettings);
  }
}
