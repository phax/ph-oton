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

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;

/**
 * This class is an abstract HC node that represents a list of nodes without
 * creating an HTML element by itself.
 *
 * @author Philip Helger
 */
public class HCNodeList extends AbstractHCNodeList <HCNodeList>
{
  public HCNodeList ()
  {}

  @Nonnull
  public static HCNodeList create (@Nullable final String sChild)
  {
    return new HCNodeList ().addChild (sChild);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final String... aChildren)
  {
    return new HCNodeList ().addChildren (aChildren);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final IHCNodeBuilder aChild)
  {
    return new HCNodeList ().addChild (aChild);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final IHCNodeBuilder... aChildren)
  {
    return new HCNodeList ().addChildren (aChildren);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final IHCNode aChild)
  {
    return new HCNodeList ().addChild (aChild);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final IHCNode... aChildren)
  {
    return new HCNodeList ().addChildren (aChildren);
  }

  @Nonnull
  public static HCNodeList create (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    return new HCNodeList ().addChildren (aChildren);
  }
}
