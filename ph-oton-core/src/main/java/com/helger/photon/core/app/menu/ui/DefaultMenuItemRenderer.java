/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.core.app.menu.ui;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.IHCList;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCEntityNode;
import com.helger.photon.basic.app.menu.IMenuItemExternal;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuSeparator;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;

/**
 * Default implementation of {@link IMenuItemRenderer}
 *
 * @author Philip Helger
 * @param <T>
 *        Parent element type
 */
public class DefaultMenuItemRenderer <T extends IHCList <?, HCLI>> extends AbstractMenuItemRenderer <T>
{
  /** CSS class for a menu separator */
  public static final ICSSClassProvider CSS_CLASS_MENU_SEPARATOR = DefaultCSSClassProvider.create ("menu_separator");
  /** CSS class for a menu item to an internal page */
  public static final ICSSClassProvider CSS_CLASS_MENU_ITEM = DefaultCSSClassProvider.create ("menu_item");
  /** CSS class for a menu item to an external page */
  public static final ICSSClassProvider CSS_CLASS_MENU_ITEM_EXTERNAL = DefaultCSSClassProvider.create ("menu_item_external");
  /** CSS class for the selected menu item */
  public static final ICSSClassProvider CSS_CLASS_SELECTED_MENU_ITEM = DefaultCSSClassProvider.create ("selected_menu_item");
  /** HTML ID prefix for menu items */
  public static final String CSS_ID_PREFIX_MENU_ITEM = "menu_item_";

  public DefaultMenuItemRenderer (@Nonnull final Locale aContentLocale)
  {
    super (aContentLocale);
  }

  @Nonnull
  public IHCNode renderSeparator (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                  @Nonnull final IMenuSeparator aSeparator)
  {
    return HCEntityNode.newNBSP ();
  }

  @Nonnull
  public IHCNode renderMenuItemPage (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                     @Nonnull final IMenuItemPage aMenuItem,
                                     final boolean bHasChildren,
                                     final boolean bIsSelected,
                                     final boolean bIsExpanded)
  {
    final String sMenuItemID = aMenuItem.getID ();
    final HCA aLink = new HCA (aSWEC.getLinkToMenuItem (sMenuItemID));
    aLink.addChild (aMenuItem.getDisplayText (getContentLocale ()) + (bHasChildren && !bIsExpanded ? " [+]" : ""));
    aLink.setID (CSS_ID_PREFIX_MENU_ITEM + sMenuItemID);
    if (bIsSelected)
      aLink.addClass (CSS_CLASS_SELECTED_MENU_ITEM);
    return aLink;
  }

  @Nonnull
  public IHCNode renderMenuItemExternal (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                         @Nonnull final IMenuItemExternal aMenuItem,
                                         final boolean bHasChildren,
                                         final boolean bIsSelected,
                                         final boolean bIsExpanded)
  {
    final String sMenuItemID = aMenuItem.getID ();
    final HCA aLink = new HCA (aMenuItem.getURL ());
    aLink.setTarget (HC_Target.BLANK);
    aLink.addChild (aMenuItem.getDisplayText (getContentLocale ()) + (bHasChildren && !bIsExpanded ? " [+]" : ""));
    aLink.setID (CSS_ID_PREFIX_MENU_ITEM + sMenuItemID);
    if (bIsSelected)
      aLink.addClass (CSS_CLASS_SELECTED_MENU_ITEM);
    return aLink;
  }

  @Override
  public void onMenuSeparatorItem (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final HCLI aLI)
  {
    aLI.addClass (CSS_CLASS_MENU_SEPARATOR);
  }

  @Override
  public void onMenuItemPageItem (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                  @Nonnull final HCLI aLI,
                                  final boolean bHasChildren,
                                  final boolean bIsSelected,
                                  final boolean bIsExpanded)
  {
    aLI.addClass (CSS_CLASS_MENU_ITEM);
  }

  @Override
  public void onMenuItemExternalItem (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                      @Nonnull final HCLI aLI,
                                      final boolean bHasChildren,
                                      final boolean bIsSelected,
                                      final boolean bIsExpanded)
  {
    aLI.addClass (CSS_CLASS_MENU_ITEM_EXTERNAL);
  }
}
