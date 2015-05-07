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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.IHCNodeWithChildren;

/**
 * This class is an abstract HC node that represents a list of nodes without
 * creating an HTML element by itself.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
@NotThreadSafe
public abstract class AbstractHCNodeList <THISTYPE extends AbstractHCNodeList <THISTYPE>> extends AbstractHCHasChildrenMutable <THISTYPE, IHCNode> implements IHCNodeWithChildren <THISTYPE>
{
  public AbstractHCNodeList ()
  {}

  @Nonnull
  public THISTYPE addChild (@Nullable final String sText)
  {
    // Empty text is OK!
    if (sText != null)
      addChild (new HCTextNode (sText));
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE addChild (@Nullable final IHCNodeBuilder aNodeBuilder)
  {
    if (aNodeBuilder != null)
      addChild (aNodeBuilder.build ());
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final String sText)
  {
    // Empty text is OK!
    if (sText != null)
      addChild (nIndex, new HCTextNode (sText));
    return thisAsT ();
  }

  @Nonnull
  public THISTYPE addChild (@Nonnegative final int nIndex, @Nullable final IHCNodeBuilder aNodeBuilder)
  {
    if (aNodeBuilder != null)
      addChild (nIndex, aNodeBuilder.build ());
    return thisAsT ();
  }

  @Deprecated
  @DevelopersNote ("Use addChild instead")
  @Nonnull
  public THISTYPE addChildren (@Nullable final String sChild)
  {
    return addChild (sChild);
  }

  @Nonnull
  public THISTYPE addChildren (@Nullable final String... aChildren)
  {
    if (aChildren != null)
      for (final String sChild : aChildren)
        addChild (sChild);
    return thisAsT ();
  }

  @Deprecated
  @DevelopersNote ("Use addChild instead")
  @Nonnull
  public THISTYPE addChildren (@Nullable final IHCNodeBuilder aChild)
  {
    return addChild (aChild);
  }

  @Nonnull
  public THISTYPE addChildren (@Nullable final IHCNodeBuilder... aChildren)
  {
    if (aChildren != null)
      for (final IHCNodeBuilder aChild : aChildren)
        addChild (aChild);
    return thisAsT ();
  }
}
