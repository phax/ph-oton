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
 * @param <IMPLTYPE>
 *        Implementation type
 * @param <ITEMTYPE>
 *        List item type
 */
public interface IHCList <IMPLTYPE extends IHCList <IMPLTYPE, ITEMTYPE>, ITEMTYPE extends IHCLI <ITEMTYPE>> extends
                         IHCElementWithInternalChildren <IMPLTYPE, ITEMTYPE>
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
  IMPLTYPE addItem (@Nullable String sChild);

  @Nonnull
  IMPLTYPE addItem (@Nullable String... aChildren);

  @Nonnull
  IMPLTYPE addItem (@Nullable IHCNode aChild);

  @Nonnull
  IMPLTYPE addItem (@Nullable IHCNode... aChildren);

  @Nonnull
  IMPLTYPE addItem (@Nullable Iterable <? extends IHCNode> aChildren);

  @Nonnull
  IMPLTYPE addItem (@Nullable ITEMTYPE aItem);

  @Nullable
  ITEMTYPE getFirstItem ();

  @Nullable
  ITEMTYPE getLastItem ();
}
