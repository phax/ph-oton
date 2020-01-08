/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.Nonempty;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;

/**
 * Interface for elements having children of a certain type.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 * @param <CHILDTYPE>
 *        Contained child type
 */
public interface IHCElementWithInternalChildren <IMPLTYPE extends IHCElementWithInternalChildren <IMPLTYPE, CHILDTYPE>, CHILDTYPE extends IHCNode>
                                                extends
                                                IHCElement <IMPLTYPE>,
                                                IHCHasChildrenMutable <IMPLTYPE, CHILDTYPE>
{
  /**
   * Check if this element contains any of the specified elements.
   * 
   * @param aElements
   *        The list of HTML elements to query. May neither be <code>null</code>
   *        nor empty.
   * @return <code>true</code> if such an element is contained,
   *         <code>false</code> if not.
   */
  default boolean recursiveContainsChildWithTagName (@Nonnull @Nonempty final EHTMLElement... aElements)
  {
    return HCHTMLHelper.recursiveGetFirstChildWithTagName (this, aElements) != null;
  }
}
