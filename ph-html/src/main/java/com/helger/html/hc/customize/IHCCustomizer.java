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
package com.helger.html.hc.customize;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCBody;
import com.helger.html.hc.html.HCHead;

/**
 * A special customization interface, that lets you modify existing elements
 * before they are assembled and emitted in the HTML code.
 * 
 * @author Philip Helger
 */
public interface IHCCustomizer
{
  /**
   * Customize HC node with some predefined classes etc.
   * 
   * @param aParentElement
   *        The parent element of the element to be customized. May not be
   *        <code>null</code>.
   * @param aNode
   *        The element to be customized. Never <code>null</code>.
   * @param eHTMLVersion
   *        The HTML version to be used. Never <code>null</code>.
   */
  void customizeNode (@Nonnull IHCHasChildrenMutable <?, ? super IHCNode> aParentElement,
                      @Nonnull IHCNode aNode,
                      @Nonnull EHTMLVersion eHTMLVersion);

  /**
   * This callback is called, when the main HTML element is assembled, to move
   * the out-of-band nodes to the correct place.
   * 
   * @param aOutOfBandNodes
   *        The non-<code>null</code> list of out-of-band nodes. May be empty.
   * @param aHead
   *        The head element. Never <code>null</code>.
   * @param aBody
   *        The body element. Never <code>null</code>.
   */
  void handleOutOfBandNodes (@Nonnull List <IHCNode> aOutOfBandNodes, @Nonnull HCHead aHead, @Nonnull HCBody aBody);
}
