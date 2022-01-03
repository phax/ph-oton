/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.html.hc.special;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.hc.IHCNode;

@FunctionalInterface
public interface IHCSpecialNodeListModifier
{
  /**
   * Merge certain special nodes.
   *
   * @param aNodes
   *        The source list of special nodes to be merged.
   * @return The modified list to be used for further processing.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <? extends IHCNode> modifySpecialNodes (@Nonnull ICommonsList <? extends IHCNode> aNodes);
}
