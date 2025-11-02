/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.tabbox;

import java.util.Comparator;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeList;
import com.helger.html.hc.impl.HCTextNode;

/**
 * Base interface for a tab box
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface ITabBox <IMPLTYPE extends ITabBox <IMPLTYPE>> extends IHCNodeList <IMPLTYPE>
{
  /** By default a tab is not active */
  boolean DEFAULT_ACTIVE = false;
  /** By default a tab is not disabled */
  boolean DEFAULT_DISABLED = false;

  @Nullable
  String getActiveTabID ();

  @NonNull
  IMPLTYPE setActiveTabID (@Nullable String sID);

  @NonNull
  default Tab addTab (@Nullable final String sID, @Nullable final String sLabel, @Nullable final IHCNode aContent)
  {
    return addTab (sID, new HCTextNode (sLabel), aContent, DEFAULT_ACTIVE);
  }

  @NonNull
  default Tab addTab (@Nullable final String sID, @Nullable final IHCNode aLabel, @Nullable final IHCNode aContent)
  {
    return addTab (sID, aLabel, aContent, DEFAULT_ACTIVE);
  }

  @NonNull
  default Tab addTab (@Nullable final String sID, @Nullable final String sLabel, @Nullable final IHCNode aContent, final boolean bActive)
  {
    return addTab (sID, new HCTextNode (sLabel), aContent, bActive);
  }

  @NonNull
  default Tab addTab (@Nullable final String sID, @Nullable final IHCNode aLabel, @Nullable final IHCNode aContent, final boolean bActive)
  {
    return addTab (sID, aLabel, aContent, bActive, DEFAULT_DISABLED);
  }

  @NonNull
  Tab addTab (@Nullable String sID, @Nullable IHCNode aLabel, @Nullable IHCNode aContent, boolean bActive, boolean bDisabled);

  @NonNull
  IMPLTYPE addTab (Tab aTab, boolean bActive);

  @NonNull
  @ReturnsMutableCopy
  ICommonsList <Tab> getAllTabs ();

  @Nullable
  Tab getTabOfID (@Nullable String sID);

  /**
   * @return The tab marked as active or <code>null</code> if no tab is marked
   *         as active.
   */
  @Nullable
  Tab getActiveTab ();

  /**
   * @return The tab marked as active, or the first tab which will be active by
   *         default. May be <code>null</code> if no tab is contained
   */
  @Nullable
  Tab getActiveTabOrDefault ();

  boolean hasTabs ();

  boolean hasNoTabs ();

  int getTabCount ();

  @NonNull
  EChange removeTab (@Nullable String sTabID);

  void sortTabs (@NonNull Comparator <? super Tab> aComparator);
}
