/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.nav;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.string.StringHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCDiv;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.uicore.html.tabbox.AbstractTabBox;
import com.helger.photon.uicore.html.tabbox.Tab;

/**
 * Represent a single tab box
 *
 * @author Philip Helger
 */
public class BootstrapTabBox extends AbstractTabBox <BootstrapTabBox>
{
  /**
   * This event fires on tab show, but before the new tab has been shown. Use
   * event.target and event.relatedTarget to target the active tab and the
   * previous active tab (if available) respectively.
   */
  public static final String JS_EVENT_SHOW = "show.bs.tab";
  /**
   * This event fires on tab show after a tab has been shown. Use event.target
   * and event.relatedTarget to target the active tab and the previous active
   * tab (if available) respectively.
   */
  public static final String JS_EVENT_SHOWN = "shown.bs.tab";
  /**
   * This event fires when a new tab is to be shown (and thus the previous
   * active tab is to be hidden). Use event.target and event.relatedTarget to
   * target the current active tab and the new soon-to-be-active tab,
   * respectively.
   */
  public static final String JS_EVENT_HIDE = "hide.bs.tab";
  /**
   * This event fires after a new tab is shown (and thus the previous active tab
   * is hidden). Use event.target and event.relatedTarget to target the previous
   * active tab and the new active tab, respectively.
   */
  public static final String JS_EVENT_HIDDEN = "hidden.bs.tab";

  private String m_sNavID;

  public BootstrapTabBox ()
  {}

  /**
   * @return The ID of the {@link BootstrapNav} to be created. May be
   *         <code>null</code>.
   * @since 8.3.1
   */
  @Nullable
  public final String getNavID ()
  {
    return m_sNavID;
  }

  /**
   * @param sNavID
   *        The ID of the {@link BootstrapNav} to be created. May be
   *        <code>null</code>.
   * @return this for chaining
   * @since 8.3.1
   */
  @Nonnull
  public final BootstrapTabBox setNavID (@Nullable final String sNavID)
  {
    m_sNavID = sNavID;
    return this;
  }

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    return !m_aTabs.isEmpty ();
  }

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    String sActiveTabID = getActiveTabID ();
    if (StringHelper.hasNoText (sActiveTabID))
    {
      // Activate first tab by default
      sActiveTabID = m_aTabs.getFirstKey ();
    }

    final BootstrapNav aNav = new BootstrapNav (EBootstrapNavType.TABS);
    aNav.setID (m_sNavID);

    // Build code for tabs and content
    final HCDiv aContent = new HCDiv ().addClass (CBootstrapCSS.TAB_CONTENT);
    for (final Tab aTab : m_aTabs.values ())
    {
      final boolean bIsActiveTab = aTab.getID ().equals (sActiveTabID);

      // header
      final BootstrapNavItem aToggleLI = aNav.addItem ();

      final HCA aLink = new HCA (aTab.getLinkURL ()).addChild (aTab.getLabel ()).addClass (CBootstrapCSS.NAV_LINK);
      if (bIsActiveTab)
        aLink.addClass (CBootstrapCSS.ACTIVE);

      if (aTab.isDisabled ())
        aToggleLI.addClass (CBootstrapCSS.DISABLED);
      else
        aLink.customAttrs ().setDataAttr ("toggle", "tab");
      aToggleLI.addChild (aLink);

      // content
      final HCDiv aPane = aContent.addAndReturnChild (new HCDiv ().addChild (aTab.getContent ())
                                                                  .addClass (CBootstrapCSS.TAB_PANE)
                                                                  .setID (aTab.getID ()));
      if (bIsActiveTab)
        aPane.addClass (CBootstrapCSS.ACTIVE);
    }

    addChild (aNav);

    addChild (aContent);
  }
}
