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
package com.helger.html.hc;

import java.util.Comparator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.Nonempty;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.impl.HCNodeList;

/**
 * Base interface for HC nodes that have mutable children.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 * @param <CHILDTYPE>
 *        Desired child type
 */
public interface IHCHasChildrenMutable <THISTYPE extends IHCHasChildrenMutable <THISTYPE, CHILDTYPE>, CHILDTYPE extends IHCNode> extends IHCHasChildren
{
  /**
   * @param aNode
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nullable CHILDTYPE aNode);

  /**
   * @param nIndex
   *        The index to where the element should be inserted.
   * @param aNode
   *        Child to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChild (@Nonnegative int nIndex, @Nullable CHILDTYPE aNode);

  /**
   * Use {@link #addChild(IHCNode)} instead.
   *
   * @param aChild
   *        The child to add. May be <code>null</code>.
   * @return this
   */
  @Deprecated
  @DevelopersNote ("Use addChild instead")
  THISTYPE addChildren (@Nullable CHILDTYPE aChild);

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChildren (@Nullable CHILDTYPE... aChildren);

  /**
   * @param aChildren
   *        Children to add. May be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE addChildren (@Nullable Iterable <? extends CHILDTYPE> aChildren);

  /**
   * @param aChild
   *        Child to add. May be <code>null</code>.
   * @param <V>
   *        The type to add. Needs to be a IHCNode or a sub class.
   * @return the added child
   */
  @Nullable
  <V extends CHILDTYPE> V addAndReturnChild (@Nullable V aChild);

  /**
   * @param nIndex
   *        The index where the element should be added. Always &ge; 0.
   * @param aChild
   *        Child to add. May be <code>null</code>.
   * @param <V>
   *        The type to add. Needs to be a IHCNode or a sub class.
   * @return the added child
   */
  @Nullable
  <V extends CHILDTYPE> V addAndReturnChild (@Nonnegative int nIndex, @Nullable V aChild);

  /**
   * Remove the child at the specified index.
   *
   * @param nIndex
   *        The index to use. Must be &ge; 0.
   * @return this
   */
  @Nonnull
  THISTYPE removeChild (@Nonnegative int nIndex);

  /**
   * Remove the passed direct child object.
   *
   * @param aNode
   *        The node to be removed. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE removeChild (@Nullable CHILDTYPE aNode);

  /**
   * Remove all children of this object.
   *
   * @return this
   */
  @Nonnull
  THISTYPE removeAllChildren ();

  /**
   * Check if any of the specified elements is contained as a child of this.
   *
   * @param aElements
   *        The elements to check. May neither be <code>null</code> nor empty.
   * @return <code>true</code> if at least one of the specified elements is
   *         contained, <code>false</code> otherwise.
   */
  boolean recursiveContainsChildWithTagName (@Nonnull @Nonempty EHTMLElement... aElements);

  /**
   * Sort all children with the passed comparator
   *
   * @param aComparator
   *        The comparator to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  THISTYPE sortAllChildren (@Nonnull Comparator <? super CHILDTYPE> aComparator);

  /**
   * @return A new node list with all contained children. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  HCNodeList getAllChildrenAsNodeList ();
}
