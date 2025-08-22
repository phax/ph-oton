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
package com.helger.html.hc.html.script;

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
public final class HCJSNodeDetector
{
  @PresentForCodeCoverage
  private static final HCJSNodeDetector INSTANCE = new HCJSNodeDetector ();

  private HCJSNodeDetector ()
  {}

  /**
   * Check if the passed node is a JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCScript}.
   */
  public static boolean isJSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectJSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCScript}.
   */
  public static boolean isDirectJSNode (@Nullable final IHCNode aNode)
  {
    // Direct JS node?
    return aNode instanceof IHCScript <?>;
  }

  /**
   * Check if the passed node is an inline JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptInline}.
   */
  public static boolean isJSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectJSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCScriptInline} .
   */
  public static boolean isDirectJSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline JS node?
    if (aNode instanceof IHCScriptInline <?>)
    {
      final IHCScriptInline <?> aScript = (IHCScriptInline <?>) aNode;
      if (IHCScript.TYPE_MODULE.equals (aScript.getType ()))
      {
        // Modules may not be merged
        return false;
      }
      return true;
    }
    return false;
  }

  /**
   * Check if the passed node is a file JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isJSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);
    return isDirectJSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isDirectJSFileNode (@Nullable final IHCNode aNode)
  {
    // File JS node?
    return aNode instanceof HCScriptFile;
  }
}
