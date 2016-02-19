/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeList;

/**
 * Base interface for a tab box
 *
 * @author Philip Helger
 * @param <THISTYPE>
 *        Implementation type
 */
public interface ITabBox <THISTYPE extends ITabBox <THISTYPE>> extends IHCNodeList <THISTYPE>
{
  /** By default a tab is not active */
  boolean DEFAULT_ACTIVE = false;
  /** By default a tab is not disabled */
  boolean DEFAULT_DISABLED = false;

  @Nullable
  String getActiveTabID ();

  @Nonnull
  THISTYPE setActiveTabID (@Nullable String sID);

  @Nonnull
  Tab addTab (@Nullable String sLabel, @Nullable IHCNode aContent);

  @Nonnull
  Tab addTab (@Nullable String sLabel, @Nullable IHCNode aContent, boolean bActive);

  @Nonnull
  Tab addTab (@Nullable String sLabel, @Nullable IHCNode aContent, boolean bActive, boolean bDisabled);

  @Nonnull
  Tab addTab (@Nullable String sID, @Nullable String sLabel, @Nullable IHCNode aContent, boolean bActive);

  @Nonnull
  Tab addTab (@Nullable IHCNode aLabel, @Nullable IHCNode aContent);

  @Nonnull
  Tab addTab (@Nullable IHCNode aLabel, @Nullable IHCNode aContent, boolean bActive);

  @Nonnull
  Tab addTab (@Nullable IHCNode aLabel, @Nullable IHCNode aContent, boolean bActive, boolean bDisabled);

  @Nonnull
  Tab addTab (@Nullable String sID, @Nullable IHCNode aLabel, @Nullable IHCNode aContent, boolean bActive);

  @Nonnull
  Tab addTab (@Nullable String sID,
              @Nullable IHCNode aLabel,
              @Nullable IHCNode aContent,
              boolean bActive,
              boolean bDisabled);

  @Nonnull
  THISTYPE addTab (Tab aTab, boolean bActive);

  @Nonnull
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

  boolean hasNoTabs ();

  int getTabCount ();

  @Nonnull
  EChange removeTab (@Nullable String sTabID);
}
