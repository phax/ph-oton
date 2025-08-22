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
package com.helger.html.hc.html.metadata;

import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.PresentForCodeCoverage;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCNode;

import jakarta.annotation.Nullable;

/**
 * This class is used to determine the special nodes (JS and CSS, inline and
 * reference).
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class HCCSSNodeDetector
{
  @PresentForCodeCoverage
  private static final HCCSSNodeDetector INSTANCE = new HCCSSNodeDetector ();

  private HCCSSNodeDetector ()
  {}

  /**
   * Check if the passed node is a CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle} or
   *         {@link HCLink} (and not a special case).
   */
  public static boolean isCSSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectCSSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle} or
   *         {@link HCLink} (and not a special case).
   */
  public static boolean isDirectCSSNode (@Nullable final IHCNode aNode)
  {
    return isDirectCSSInlineNode (aNode) || isDirectCSSFileNode (aNode);
  }

  /**
   * Check if the passed node is an inline CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isCSSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectCSSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isDirectCSSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline CSS node?
    return aNode instanceof HCStyle;
  }

  /**
   * Check if the passed node is a file CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCLink}.
   */
  public static boolean isCSSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectCSSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCLink}.
   */
  public static boolean isDirectCSSFileNode (@Nullable final IHCNode aNode)
  {
    // File CSS node?
    return aNode instanceof HCLink && ((HCLink) aNode).isCSSLink ();
  }
}
