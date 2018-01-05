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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCNode;

/**
 * Implementation of a node that is ONLY an out-of-band node!
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCOutOfBandNode extends AbstractHCWrappingNode
{
  private final IHCNode m_aOutOfBandNode;

  public HCOutOfBandNode (@Nonnull final IHCNode aOutOfBandNode)
  {
    m_aOutOfBandNode = ValueEnforcer.notNull (aOutOfBandNode, "OutOfBandNode");
  }

  @Nonnull
  public IHCNode getWrappedNode ()
  {
    return m_aOutOfBandNode;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("outOfBandNode", m_aOutOfBandNode).getToString ();
  }
}
