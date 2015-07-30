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
package com.helger.photon.bootstrap3.nav;

import javax.annotation.Nonnull;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.html.hcapi.IHCConversionSettingsToNode;
import com.helger.html.hcapi.IHCHasChildrenMutable;
import com.helger.html.hcapi.IHCNode;
import com.helger.html.hchtml.impl.HCA;
import com.helger.html.hchtml.impl.HCDiv;
import com.helger.html.hchtml.list.IHCLI;
import com.helger.photon.bootstrap3.CBootstrapCSS;
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

  public BootstrapTabBox ()
  {}

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
      sActiveTabID = CollectionHelper.getFirstKey (m_aTabs);
    }

    final BootstrapNav aNav = new BootstrapNav (EBootstrapNavType.TABS);

    // Build code for tabs and content
    final HCDiv aContent = new HCDiv ().addClass (CBootstrapCSS.TAB_CONTENT);
    for (final Tab aTab : m_aTabs.values ())
    {
      final boolean bIsActiveTab = aTab.getID ().equals (sActiveTabID);

      // header
      final IHCLI <?> aToggleLI = aNav.addItem ();
      if (bIsActiveTab)
        aToggleLI.addClass (CBootstrapCSS.ACTIVE);
      if (aTab.isDisabled ())
      {
        aToggleLI.addClass (CBootstrapCSS.DISABLED);
        aToggleLI.addChild (new HCA (aTab.getLinkURL ()).addChild (aTab.getLabel ()));
      }
      else
      {
        aToggleLI.addChild (new HCA (aTab.getLinkURL ()).setDataAttr ("toggle", "tab").addChild (aTab.getLabel ()));
      }

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
