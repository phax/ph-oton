/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.forms;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.hc.IHCNode;

@Immutable
public final class HCCtrlHelper
{
  private HCCtrlHelper ()
  {}

  /**
   * Find the first instance of {@link IHCControl} that is either the passed
   * element or a child of the passed element.
   *
   * @param aNode
   *        The source node to start searching. May be <code>null</code>.
   * @return <code>null</code> if no {@link IHCControl} can be found below the
   *         passed node.
   */
  @Nullable
  public static IHCControl <?> getFirstHCControl (@Nullable final IHCNode aNode)
  {
    if (aNode != null)
    {
      if (aNode instanceof IHCControl <?>)
        return (IHCControl <?>) aNode;

      if (aNode.hasChildren ())
        for (final IHCNode aChild : aNode.getAllChildren ())
        {
          final IHCControl <?> aNestedCtrl = getFirstHCControl (aChild);
          if (aNestedCtrl != null)
            return aNestedCtrl;
        }
    }

    return null;
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed node
   *
   * @param aNode
   *        The start node. May be <code>null</code>.
   * @param aConsumer
   *        The consumer to be invoked. May not be <code>null</code>.
   */
  public static void getAllHCControls (@Nullable final IHCNode aNode,
                                       @Nonnull final Consumer <? super IHCControl <?>> aConsumer)
  {
    ValueEnforcer.notNull (aConsumer, "Consumer");

    if (aNode != null)
    {
      if (aNode instanceof IHCControl <?>)
        aConsumer.accept ((IHCControl <?>) aNode);

      // E.g. HCNodeList
      aNode.forAllChildren (aChild -> getAllHCControls (aChild, aConsumer));
    }
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed node
   *
   * @param aNode
   *        The start node. May be <code>null</code>.
   * @return The filled list with all controls. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCControl <?>> getAllHCControls (@Nullable final IHCNode aNode)
  {
    final ICommonsList <IHCControl <?>> ret = new CommonsArrayList<> ();
    getAllHCControls (aNode, ret::add);
    return ret;
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed nodes
   *
   * @param aNodes
   *        The start nodes. May be <code>null</code>.
   * @param aConsumer
   *        The consumer to be invoked. May not be <code>null</code>.
   */
  public static void getAllHCControls (@Nullable final Iterable <? extends IHCNode> aNodes,
                                       @Nonnull final Consumer <? super IHCControl <?>> aConsumer)
  {
    ValueEnforcer.notNull (aConsumer, "TargetList");

    if (aNodes != null)
      for (final IHCNode aNode : aNodes)
        getAllHCControls (aNode, aConsumer);
  }

  /**
   * Recursively determine all {@link IHCControl} elements from and incl. the
   * passed nodes
   *
   * @param aNodes
   *        The start nodes. May be <code>null</code>.
   * @return The filled list with all controls. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCControl <?>> getAllHCControls (@Nullable final Iterable <? extends IHCNode> aNodes)
  {
    final ICommonsList <IHCControl <?>> ret = new CommonsArrayList<> ();
    getAllHCControls (aNodes, ret::add);
    return ret;
  }
}
