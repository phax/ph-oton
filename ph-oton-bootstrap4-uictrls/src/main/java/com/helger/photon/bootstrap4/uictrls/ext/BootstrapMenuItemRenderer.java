/*
 * Copyright (C) 2018-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.ext;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.factory.FactoryNewInstance;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.IHCElement;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.textlevel.HCA;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.photon.bootstrap4.CBootstrapCSS;
import com.helger.photon.bootstrap4.card.BootstrapCard;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemDeterminatorCallback;
import com.helger.photon.core.menu.IMenuItemExternal;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuSeparator;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.menu.MenuItemDeterminatorCallback;
import com.helger.photon.core.menu.ui.AbstractMenuItemRenderer;
import com.helger.photon.core.menu.ui.IMenuItemRenderer;
import com.helger.photon.core.menu.ui.MenuRendererCallback;

/**
 * Default implementation of {@link IMenuItemRenderer}
 *
 * @author Philip Helger
 */
public class BootstrapMenuItemRenderer extends AbstractMenuItemRenderer <HCUL>
{
  private static final ICSSClassProvider CSS_CLASS_SEPARATOR = DefaultCSSClassProvider.create ("menu-separator");

  public BootstrapMenuItemRenderer (@Nonnull final Locale aContentLocale)
  {
    super (aContentLocale);
  }

  @Nonnull
  public IHCNode renderSeparator (@Nonnull final ISimpleWebExecutionContext aSWEC, @Nonnull final IMenuSeparator aSeparator)
  {
    // Add styling!
    return new HCLI ().addClass (CSS_CLASS_SEPARATOR);
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
    aLink.addClass (CBootstrapCSS.NAV_LINK);
    aLink.addChild (getMenuItemPageLabel (aMenuItem, bHasChildren, bIsSelected, bIsExpanded));
    if (bHasChildren && !bIsExpanded)
      aLink.addChild (new HCTextNode (" + "));
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
    aLink.addClass (CBootstrapCSS.NAV_LINK);
    aLink.addChild (getMenuItemExternalLabel (aMenuItem, bHasChildren, bIsSelected, bIsExpanded));
    if (bHasChildren && !bIsExpanded)
      aLink.addChild (new HCTextNode (" + "));
    return aLink;
  }

  @Override
  public void onLevelDown (@Nonnull final HCUL aNewLevel)
  {
    aNewLevel.addClass (CBootstrapCSS.NAV).addClass (CBootstrapCSS.FLEX_COLUMN);
  }

  @Override
  public void onMenuItemPageItem (@Nonnull final ISimpleWebExecutionContext aSWEC,
                                  @Nonnull final HCLI aLI,
                                  final boolean bHasChildren,
                                  final boolean bSelected,
                                  final boolean bExpanded)
  {
    aLI.addClass (CBootstrapCSS.NAV_ITEM);
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
    aLI.addClass (CBootstrapCSS.NAV_ITEM);
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
    final ICommonsMap <String, Boolean> aAllDisplayMenuItemIDs = MenuItemDeterminatorCallback.getAllDisplayMenuItemIDs (aDeterminator);
    final HCUL aUL = MenuRendererCallback.createRenderedMenu (aLEC,
                                                              FactoryNewInstance.create (HCUL.class),
                                                              aMenuTree.getRootItem (),
                                                              aRenderer,
                                                              aAllDisplayMenuItemIDs)
                                         .addClass (CBootstrapCSS.NAV)
                                         .addClass (CBootstrapCSS.FLEX_COLUMN);
    final BootstrapCard ret = new BootstrapCard ();
    ret.createAndAddBody ().addChild (aUL);
    return ret;
  }
}
