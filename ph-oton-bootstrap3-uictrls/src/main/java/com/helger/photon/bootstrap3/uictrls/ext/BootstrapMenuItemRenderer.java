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
package com.helger.photon.bootstrap3.uictrls.ext;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.factory.FactoryNewInstance;
import com.helger.html.hc.IHCElement;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.HCA;
import com.helger.html.hc.html.HCLI;
import com.helger.html.hc.html.HCUL;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.basic.app.menu.IMenuItemDeterminatorCallback;
import com.helger.photon.basic.app.menu.IMenuItemExternal;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuSeparator;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.basic.app.menu.MenuItemDeterminatorCallback;
import com.helger.photon.bootstrap3.CBootstrapCSS;
import com.helger.photon.bootstrap3.EBootstrapIcon;
import com.helger.photon.bootstrap3.well.BootstrapWell;
import com.helger.photon.bootstrap3.well.EBootstrapWellType;
import com.helger.photon.core.app.context.ILayoutExecutionContext;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;
import com.helger.photon.core.app.menu.ui.AbstractMenuItemRenderer;
import com.helger.photon.core.app.menu.ui.IMenuItemRenderer;
import com.helger.photon.core.app.menu.ui.MenuRendererCallback;

/**
 * Default implementation of {@link IMenuItemRenderer}
 *
 * @author Philip Helger
 */
public class BootstrapMenuItemRenderer extends AbstractMenuItemRenderer <HCUL>
{
  public BootstrapMenuItemRenderer (@Nonnull final Locale aContentLocale)
  {
    super (aContentLocale);
  }

  @Nonnull
  public IHCNode renderSeparator (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                  @Nonnull final IMenuSeparator aSeparator)
  {
    return new HCLI ().addClass (CBootstrapCSS.DIVIDER);
  }

  /**
   * Get the label to display.
   *
   * @param aMenuItem
   *        Menu item. Never <code>null</code>.
   * @param bHasChildren
   *        <code>true</code> if the item has children
   * @param bIsSelected
   *        <code>true</code> if it is selected
   * @param bIsExpanded
   *        <code>true</code> if it is expanded.
   * @return The label text. Should not be <code>null</code>.
   * @see #getContentLocale()
   */
  @Nonnull
  @OverrideOnDemand
  protected String getMenuItemPageLabel (@Nonnull final IMenuItemPage aMenuItem,
                                         final boolean bHasChildren,
                                         final boolean bIsSelected,
                                         final boolean bIsExpanded)
  {
    return aMenuItem.getDisplayText (getContentLocale ());
  }

  @Nonnull
  public IHCNode renderMenuItemPage (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                     @Nonnull final IMenuItemPage aMenuItem,
                                     final boolean bHasChildren,
                                     final boolean bIsSelected,
                                     final boolean bIsExpanded)
  {
    final HCA aLink = new HCA (aSWEC.getLinkToMenuItem (aMenuItem.getID ()));
    aLink.addChild (getMenuItemPageLabel (aMenuItem, bHasChildren, bIsSelected, bIsExpanded));
    if (bHasChildren && !bIsExpanded)
      aLink.addChildren (new HCTextNode (" "), EBootstrapIcon.CHEVRON_RIGHT.getAsNode ());
    return aLink;
  }

  /**
   * Get the label to display.
   *
   * @param aMenuItem
   *        Menu item. Never <code>null</code>.
   * @param bHasChildren
   *        <code>true</code> if the item has children
   * @param bIsSelected
   *        <code>true</code> if it is selected
   * @param bIsExpanded
   *        <code>true</code> if it is expanded.
   * @return The label text. Should not be <code>null</code>.
   * @see #getContentLocale()
   */
  @Nonnull
  @OverrideOnDemand
  protected String getMenuItemExternalLabel (@Nonnull final IMenuItemExternal aMenuItem,
                                             final boolean bHasChildren,
                                             final boolean bIsSelected,
                                             final boolean bIsExpanded)
  {
    return aMenuItem.getDisplayText (getContentLocale ());
  }

  @Nonnull
  public IHCNode renderMenuItemExternal (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                         @Nonnull final IMenuItemExternal aMenuItem,
                                         final boolean bHasChildren,
                                         final boolean bIsSelected,
                                         final boolean bIsExpanded)
  {
    final HCA aLink = new HCA (aMenuItem.getURL ());
    aLink.setTargetBlank ();
    aLink.addChild (getMenuItemExternalLabel (aMenuItem, bHasChildren, bIsSelected, bIsExpanded));
    if (bHasChildren && !bIsExpanded)
      aLink.addChildren (new HCTextNode (" "), EBootstrapIcon.CHEVRON_RIGHT.getAsNode ());
    return aLink;
  }

  @Override
  public void onLevelDown (@Nonnull final HCUL aNewLevel)
  {
    aNewLevel.addClass (CBootstrapCSS.NAV);
  }

  @Override
  public void onMenuItemPageItem (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                  @Nonnull final HCLI aLI,
                                  final boolean bHasChildren,
                                  final boolean bSelected,
                                  final boolean bExpanded)
  {
    if (bSelected || bExpanded)
      aLI.addClass (CBootstrapCSS.ACTIVE);
  }

  @Override
  public void onMenuItemExternalItem (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                      @Nonnull final HCLI aLI,
                                      final boolean bHasChildren,
                                      final boolean bSelected,
                                      final boolean bExpanded)
  {
    if (bSelected || bExpanded)
      aLI.addClass (CBootstrapCSS.ACTIVE);
  }

  @Nonnull
  public static IHCElement <?> createSideBarMenu (@Nonnull final ILayoutExecutionContext aLEC)
  {
    final IMenuTree aMenuTree = aLEC.getMenuTree ();
    return createSideBarMenu (aLEC, new MenuItemDeterminatorCallback (aMenuTree, aLEC.getSelectedMenuItemID ()));
  }

  @Nonnull
  public static IHCElement <?> createSideBarMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                  @Nonnull final MenuItemDeterminatorCallback aDeterminator)
  {
    return createSideBarMenu (aLEC, aLEC.getMenuTree (), aDeterminator);
  }

  @Nonnull
  public static IHCElement <?> createSideBarMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                  @Nonnull final IMenuTree aMenuTree,
                                                  @Nonnull final MenuItemDeterminatorCallback aDeterminator)
  {
    return createSideBarMenu (aLEC, aMenuTree, aDeterminator, new BootstrapMenuItemRenderer (aLEC.getDisplayLocale ()));
  }

  @Nonnull
  public static IHCElement <?> createSideBarMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                  @Nonnull final IMenuTree aMenuTree,
                                                  @Nonnull final IMenuItemDeterminatorCallback aDeterminator,
                                                  @Nonnull final BootstrapMenuItemRenderer aRenderer)
  {
    final Map <String, Boolean> aAllDisplayMenuItemIDs = MenuItemDeterminatorCallback.getAllDisplayMenuItemIDs (aDeterminator);
    final HCUL aUL = MenuRendererCallback.createRenderedMenu (aLEC,
                                                              FactoryNewInstance.create (HCUL.class),
                                                              aMenuTree.getRootItem (),
                                                              aRenderer,
                                                              aAllDisplayMenuItemIDs).addClass (CBootstrapCSS.NAV);
    final BootstrapWell ret = new BootstrapWell (EBootstrapWellType.SMALL);
    ret.addChild (aUL);
    return ret;
  }
}
