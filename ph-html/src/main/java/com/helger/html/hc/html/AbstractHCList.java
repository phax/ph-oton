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

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.impl.AbstractHCElementWithInternalChildren;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Abstract base class for UL and OL elements.
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        The real implementation type.
 */
public abstract class AbstractHCList <THISTYPE extends AbstractHCList <THISTYPE>> extends AbstractHCElementWithInternalChildren <THISTYPE, HCLI>
{
  protected AbstractHCList (@Nonnull @Nonempty final EHTMLElement aElement)
  {
    super (aElement);
  }

  /**
   * Callback method to be implemented in derived classes. Called everytime
   * after an item was added.
   *
   * @param aItem
   *        The added item. Never <code>null</code>.
   */
  @OverrideOnDemand
  protected void onAddItem (@Nonnull final HCLI aItem)
  {}

  @Nullable
  private HCLI _addItem (@Nullable final HCLI aItem)
  {
    if (aItem != null)
    {
      addChild (aItem);
      onAddItem (aItem);
    }
    return aItem;
  }

  @Nonnull
  public final HCLI addItem ()
  {
    return _addItem (new HCLI ());
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final String sChild)
  {
    return addItem ().addChild (sChild);
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final String... aChildren)
  {
    return addItem ().addChildren (aChildren);
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final IHCNodeBuilder aChild)
  {
    return addAndReturnItem (aChild == null ? null : aChild.build ());
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final IHCNodeBuilder... aChildren)
  {
    return addItem ().addChildren (aChildren);
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final IHCNode aChild)
  {
    // Avoid directly nested LI elements
    if (aChild instanceof HCLI)
      return addAndReturnItem ((HCLI) aChild);

    final HCLI aItem = addItem ();
    aItem.addChild (aChild);
    return aItem;
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final IHCNode... aChildren)
  {
    final HCLI aItem = addItem ();
    aItem.addChildren (aChildren);
    return aItem;
  }

  @Nonnull
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    final HCLI aItem = addItem ();
    aItem.addChildren (aChildren);
    return aItem;
  }

  @Nullable
  @CheckReturnValue
  public final HCLI addAndReturnItem (@Nullable final HCLI aItem)
  {
    return _addItem (aItem);
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final String sChild)
  {
    addAndReturnItem (sChild);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final String... aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final IHCNodeBuilder aChild)
  {
    addAndReturnItem (aChild);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final IHCNodeBuilder... aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final IHCNode aChild)
  {
    addAndReturnItem (aChild);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final IHCNode... aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  @SuppressFBWarnings ("RV_RETURN_VALUE_IGNORED")
  public final THISTYPE addItem (@Nullable final Iterable <? extends IHCNode> aChildren)
  {
    addAndReturnItem (aChildren);
    return thisAsT ();
  }

  @Nonnull
  public final THISTYPE addItem (@Nullable final HCLI aItem)
  {
    _addItem (aItem);
    return thisAsT ();
  }

  @Nullable
  public HCLI getFirstItem ()
  {
    return getFirstChild ();
  }

  @Nullable
  public HCLI getLastItem ()
  {
    return getLastChild ();
  }
}
