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

import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;legend&gt; element
 *
 * @author Philip Helger
 */
public class HCLegend extends AbstractHCElementWithChildren <HCLegend>
{
  public HCLegend ()
  {
    super (EHTMLElement.LEGEND);
  }

  /**
   * Create a new LEGEND element with the passed child text
   *
   * @param sChild
   *        The child to be appended. May be <code>null</code>
   * @return The created HCLegend element and never <code>null</code>
   */
  @Nonnull
  public static HCLegend create (@Nullable final String sChild)
  {
    return new HCLegend ().addChild (sChild);
  }

  /**
   * Create a new LEGEND element with the passed child texts
   *
   * @param aChildren
   *        The child texts to be appended. May be <code>null</code>
   * @return The created HCLegend element and never <code>null</code>
   */
  @Nonnull
  public static HCLegend create (@Nullable final String... aChildren)
  {
    return new HCLegend ().addChildren (aChildren);
  }

  /**
   * Create a new LEGEND element with the passed child node
   *
   * @param aChild
   *        The child node to be appended. May be <code>null</code>
   * @return The created HCLegend element and never <code>null</code>
   */
  @Nonnull
  public static HCLegend create (@Nullable final IHCNode aChild)
  {
    return new HCLegend ().addChild (aChild);
  }

  /**
   * Create a new LEGEND element with the passed child nodes
   *
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCLegend element and never <code>null</code>
   */
  @Nonnull
  public static HCLegend create (@Nullable final IHCNode... aChildren)
  {
    return new HCLegend ().addChildren (aChildren);
  }

  /**
   * Create a new LEGEND element with the passed child nodes
   *
   * @param aChildren
   *        The child nodes to be appended. May be <code>null</code>
   * @return The created HCLegend element and never <code>null</code>
   */
  @Nonnull
  public static HCLegend create (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    return new HCLegend ().addChildren (aChildren);
  }
}
