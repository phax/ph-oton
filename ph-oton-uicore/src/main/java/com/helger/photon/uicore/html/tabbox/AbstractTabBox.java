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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsLinkedHashMap;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.AbstractHCNodeList;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represent a single tab box
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractTabBox <IMPLTYPE extends AbstractTabBox <IMPLTYPE>> extends AbstractHCNodeList <IMPLTYPE> implements
                                     ITabBox <IMPLTYPE>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractTabBox.class);

  protected final ICommonsOrderedMap <String, Tab> m_aTabs = new CommonsLinkedHashMap <> ();
  private String m_sActiveTabID;

  public AbstractTabBox ()
  {}

  public String getActiveTabID ()
  {
    return m_sActiveTabID;
  }

  @Nonnull
  public IMPLTYPE setActiveTabID (@Nullable final String sID)
  {
    m_sActiveTabID = sID;
    if (StringHelper.hasText (sID) && !m_aTabs.containsKey (sID))
      LOGGER.warn ("No tab with ID '" + sID + "' to be set active!");
    return thisAsT ();
  }

  @Nonnull
  public Tab addTab (@Nullable final String sID,
                     @Nullable final IHCNode aLabel,
                     @Nullable final IHCNode aContent,
                     final boolean bActive,
                     final boolean bDisabled)
  {
    final Tab aTab = new Tab (sID, aLabel, aContent, bDisabled);
    addTab (aTab, bActive);
    return aTab;
  }

  @Nonnull
  public IMPLTYPE addTab (@Nonnull final Tab aTab, final boolean bActive)
  {
    ValueEnforcer.notNull (aTab, "Tab");

    // Tab ID may be generated, if null was provided
    final String sTabID = aTab.getID ();
    m_aTabs.put (sTabID, aTab);
    if (bActive)
      m_sActiveTabID = sTabID;
    return thisAsT ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <Tab> getAllTabs ()
  {
    return m_aTabs.copyOfValues ();
  }

  @Nullable
  public Tab getTabOfID (@Nullable final String sID)
  {
    return m_aTabs.get (sID);
  }

  /**
   * @return The tab marked as active or <code>null</code> if no tab is marked
   *         as active.
   */
  @Nullable
  public Tab getActiveTab ()
  {
    Tab aTab = null;
    // Any active tab set?
    if (m_sActiveTabID != null)
      aTab = getTabOfID (m_sActiveTabID);
    return aTab;
  }

  /**
   * @return The tab marked as active, or the first tab which will be active by
   *         default. May be <code>null</code> if no tab is contained
   */
  @Nullable
  public Tab getActiveTabOrDefault ()
  {
    Tab aTab = getActiveTab ();
    // Invalid or no active tab -> use first tab (as done below in build)
    if (aTab == null)
      aTab = m_aTabs.getFirstValue ();
    return aTab;
  }

  public boolean hasTabs ()
  {
    return m_aTabs.isNotEmpty ();
  }

  public boolean hasNoTabs ()
  {
    return m_aTabs.isEmpty ();
  }

  @Nonnegative
  public int getTabCount ()
  {
    return m_aTabs.size ();
  }

  @Nonnull
  public EChange removeTab (@Nullable final String sTabID)
  {
    if (m_aTabs.remove (sTabID) == null)
      return EChange.UNCHANGED;

    // Was it the active tab?
    if (m_sActiveTabID != null && m_sActiveTabID.equals (sTabID))
      m_sActiveTabID = null;
    return EChange.CHANGED;
  }

  public void sortTabs (@Nonnull final Comparator <? super Tab> aComparator)
  {
    ValueEnforcer.notNull (aComparator, "Comparator");
    m_aTabs.setAll (m_aTabs.getSortedByValue (aComparator));
  }
}
