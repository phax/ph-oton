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
package com.helger.photon.uicore.html.tabbox;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.state.EChange;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeBuilder;

public interface ITabBox <IMPLTYPE extends ITabBox <IMPLTYPE>> extends IHCNodeBuilder
{
  /** By default a tab is not active */
  boolean DEFAULT_ACTIVE = false;
  /** By default a tab is not disabled */
  boolean DEFAULT_DISABLED = false;

  @Nullable
  String getActiveTabID ();

  @Nonnull
  IMPLTYPE setActiveTabID (@Nullable String sID);

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
  IMPLTYPE addTab (Tab aTab, boolean bActive);

  @Nonnull
  @ReturnsMutableCopy
  List <Tab> getAllTabs ();

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
