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

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;

/**
 * Represents an HTML &lt;label&gt; element
 *
 * @author Philip Helger
 */
public class HCLabel extends AbstractHCLabel <HCLabel>
{
  public HCLabel ()
  {}

  /**
   * Create a new LABEL element with the passed child text
   *
   * @param sChild
   *        The child to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final String sChild)
  {
    return new HCLabel ().addChild (sChild);
  }

  /**
   * Create a new LABEL element with the passed child texts
   *
   * @param aChildren
   *        The child texts to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final String... aChildren)
  {
    return new HCLabel ().addChildren (aChildren);
  }

  /**
   * Create a new LABEL element with the passed child node
   *
   * @param aChild
   *        The child node to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final IHCNodeBuilder aChild)
  {
    return new HCLabel ().addChild (aChild);
  }

  /**
   * Create a new LABEL element with the passed child nodes
   *
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final IHCNodeBuilder... aChildren)
  {
    return new HCLabel ().addChildren (aChildren);
  }

  /**
   * Create a new LABEL element with the passed child node
   *
   * @param aChild
   *        The child node to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final IHCNode aChild)
  {
    return new HCLabel ().addChild (aChild);
  }

  /**
   * Create a new LABEL element with the passed child nodes
   *
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final IHCNode... aChildren)
  {
    return new HCLabel ().addChildren (aChildren);
  }

  /**
   * Create a new LABEL element with the passed child nodes
   *
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCLabel element and never <code>null</code>
   */
  @Nonnull
  public static HCLabel create (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    return new HCLabel ().addChildren (aChildren);
  }
}
