/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElementWithInternalChildren;

/**
 * Interface for OLs and ULs
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 * @param <ITEMTYPE>
 *        List item type
 */
public interface IHCList <THISTYPE extends IHCList <THISTYPE, ITEMTYPE>, ITEMTYPE extends IHCLI <ITEMTYPE>>
                         extends IHCElementWithInternalChildren <THISTYPE, ITEMTYPE>
{
  @Nonnull
  ITEMTYPE addItem ();

  @Nonnull
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable String sChild);

  @Nonnull
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable String... aChildren);

  @Nonnull
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable IHCNode aChild);

  @Nonnull
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable IHCNode... aChildren);

  @Nonnull
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable Iterable <? extends IHCNode> aChildren);

  @Nullable
  @CheckReturnValue
  ITEMTYPE addAndReturnItem (@Nullable ITEMTYPE aItem);

  @Nonnull
  THISTYPE addItem (@Nullable String sChild);

  @Nonnull
  THISTYPE addItem (@Nullable String... aChildren);

  @Nonnull
  THISTYPE addItem (@Nullable IHCNode aChild);

  @Nonnull
  THISTYPE addItem (@Nullable IHCNode... aChildren);

  @Nonnull
  THISTYPE addItem (@Nullable Iterable <? extends IHCNode> aChildren);

  @Nonnull
  THISTYPE addItem (@Nullable ITEMTYPE aItem);

  @Nullable
  ITEMTYPE getFirstItem ();

  @Nullable
  ITEMTYPE getLastItem ();
}
