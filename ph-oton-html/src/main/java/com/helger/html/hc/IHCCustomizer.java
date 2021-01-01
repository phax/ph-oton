/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.hc;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLVersion;

/**
 * A special customization interface, that lets you modify existing elements
 * before they are assembled and emitted in the HTML code.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IHCCustomizer
{
  /**
   * Customize HC node with some predefined classes etc.
   *
   * @param aNode
   *        The element to be customized. Never <code>null</code>.
   * @param eHTMLVersion
   *        The HTML version to be used. Never <code>null</code>.
   * @param aTargetNode
   *        The node where additional elements should be appended to. May not be
   *        <code>null</code>.
   */
  void customizeNode (@Nonnull IHCNode aNode,
                      @Nonnull EHTMLVersion eHTMLVersion,
                      @Nonnull IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode);
}
