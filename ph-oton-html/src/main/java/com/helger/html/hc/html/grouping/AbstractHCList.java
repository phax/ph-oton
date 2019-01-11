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
package com.helger.html.hc.html.grouping;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElementWithInternalChildren;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract base class for UL and OL elements.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The real implementation type.
 * @param <ITEMTYPE>
 *        The item type
 */
public abstract class AbstractHCList <IMPLTYPE extends AbstractHCList <IMPLTYPE, ITEMTYPE>, ITEMTYPE extends IHCLI <ITEMTYPE>>
                                     extends
                                     AbstractHCElementWithInternalChildren <IMPLTYPE, ITEMTYPE> implements
                                     IHCList <IMPLTYPE, ITEMTYPE>
{
  private final Class <ITEMTYPE> m_aItemClass;

  protected AbstractHCList (@Nonnull final EHTMLElement eElement, @Nonnull final Class <ITEMTYPE> aItemClass)
  {
    super (eElement);
    m_aItemClass = ValueEnforcer.notNull (aItemClass, "ItemClass");
  }

  /**
   * Callback method to be implemented in derived classes. Called every time
   * after an item was added.
   *
   * @param aItem
   *        The added item. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onAddItem (@Nonnull final ITEMTYPE aItem)
  {}

  @Nullable
  private ITEMTYPE _addItem (@Nullable final ITEMTYPE aItem)
  {
    if (aItem != null)
    {
      addChild (aItem);
      onAddItem (aItem);
    }
    return aItem;
  }

  @Nonnull
  protected abstract ITEMTYPE createEmptyItem ();

  @Nonnull
  public final ITEMTYPE addItem ()
  {
    return _addItem (createEmptyItem ());
  }

  @Nonnull
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final String sChild)
  {
    return addItem ().addChild (sChild);
  }

  @Nonnull
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final String... aChildren)
  {
    return addItem ().addChildren (aChildren);
  }

  @Nonnull
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final IHCNode aChild)
  {
    // Avoid directly nested LI elements
    if (m_aItemClass.isInstance (aChild))
      return _addItem (m_aItemClass.cast (aChild));

    final ITEMTYPE aItem = addItem ();
    aItem.addChild (aChild);
    return aItem;
  }

  @Nonnull
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final IHCNode... aChildren)
  {
    final ITEMTYPE aItem = addItem ();
    aItem.addChildren (aChildren);
    return aItem;
  }

  @Nonnull
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    final ITEMTYPE aItem = addItem ();
    aItem.addChildren (aChildren);
    return aItem;
  }

  @Nullable
  @CheckReturnValue
  public final ITEMTYPE addAndReturnItem (@Nullable final ITEMTYPE aItem)
  {
    return _addItem (aItem);
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final IMPLTYPE addItem (@Nullable final String sChild)
  {
    addAndReturnItem (sChild);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final IMPLTYPE addItem (@Nullable final String... aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final IMPLTYPE addItem (@Nullable final IHCNode aChild)
  {
    addAndReturnItem (aChild);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final IMPLTYPE addItem (@Nullable final IHCNode... aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final IMPLTYPE addItem (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE addItem (@Nullable final ITEMTYPE aItem)
  {
    _addItem (aItem);
    return thisAsT ();
  }

  @Nullable
  public final ITEMTYPE getFirstItem ()
  {
    return getFirstChild ();
  }

  @Nullable
  public final ITEMTYPE getLastItem ()
  {
    return getLastChild ();
  }
}
