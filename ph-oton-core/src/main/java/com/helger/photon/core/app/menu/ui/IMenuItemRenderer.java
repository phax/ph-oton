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
package com.helger.photon.core.app.menu.ui;

import javax.annotation.Nonnull;

import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCList;
import com.helger.html.hc.html.HCLI;
import com.helger.photon.basic.app.menu.IMenuItemExternal;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuSeparator;
import com.helger.photon.core.app.context.ISimpleWebExecutionContext;

/**
 * Interface for rendering menu objects
 *
 * @author Philip Helger
 * @param <T>
 *        Parent element type
 */
public interface IMenuItemRenderer <T extends AbstractHCList <?>>
{
  /**
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aSeparator
   *        The separator to be rendered.
   * @return The rendered menu separator. May not be <code>null</code>.
   */
  @Nonnull
  IHCNode renderSeparator (@Nonnull ISimpleWebExecutionContext aSWEC, @Nonnull IMenuSeparator aSeparator);

  /**
   * Render a menu item on a page
   *
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aMenuItem
   *        The menu item to be rendered.
   * @param bHasChildren
   *        <code>true</code> if the menu item has children
   * @param bIsSelected
   *        <code>true</code> if the menu item is a selected menu item
   * @param bIsExpanded
   *        <code>true</code> if the menu item is expanded
   * @return The rendered menu item. May not be <code>null</code>.
   */
  @Nonnull
  IHCNode renderMenuItemPage (@Nonnull ISimpleWebExecutionContext aSWEC,
                              @Nonnull IMenuItemPage aMenuItem,
                              boolean bHasChildren,
                              boolean bIsSelected,
                              boolean bIsExpanded);

  /**
   * Render a menu item with an external link
   *
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aMenuItem
   *        The menu item to be rendered.
   * @param bHasChildren
   *        <code>true</code> if the menu item has children
   * @param bIsSelected
   *        <code>true</code> if the menu item is a selected menu item
   * @param bIsExpanded
   *        <code>true</code> if the menu item is expanded
   * @return The rendered menu item. May not be <code>null</code>.
   */
  @Nonnull
  IHCNode renderMenuItemExternal (@Nonnull ISimpleWebExecutionContext aSWEC,
                                  @Nonnull IMenuItemExternal aMenuItem,
                                  boolean bHasChildren,
                                  boolean bIsSelected,
                                  boolean bIsExpanded);

  /**
   * Called when a new sub-level is entered
   *
   * @param aNewLevel
   *        The new UL to be modified
   */
  void onLevelDown (@Nonnull T aNewLevel);

  /**
   * Called when a sub-level is left
   *
   * @param aLastLevel
   *        The last UL that was used
   */
  void onLevelUp (@Nonnull T aLastLevel);

  /**
   * Callback invoked on the created node.
   *
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aLI
   *        HCNode
   */
  void onMenuSeparatorItem (@Nonnull ISimpleWebExecutionContext aSWEC, @Nonnull HCLI aLI);

  /**
   * Callback invoked on the created node.
   *
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aLI
   *        HCNode
   * @param bHasChildren
   *        <code>true</code> if the menu item has children
   * @param bIsSelected
   *        <code>true</code> if the menu item is a selected menu item
   * @param bIsExpanded
   *        <code>true</code> if the menu item is expanded
   */
  void onMenuItemPageItem (@Nonnull ISimpleWebExecutionContext aSWEC,
                           @Nonnull HCLI aLI,
                           boolean bHasChildren,
                           boolean bIsSelected,
                           boolean bIsExpanded);

  /**
   * Callback invoked on the created node.
   *
   * @param aSWEC
   *        Web execution context. May not be <code>null</code>.
   * @param aLI
   *        HCNode
   * @param bHasChildren
   *        <code>true</code> if the menu item has children
   * @param bIsSelected
   *        <code>true</code> if the menu item is a selected menu item
   * @param bIsExpanded
   *        <code>true</code> if the menu item is expanded
   */
  void onMenuItemExternalItem (@Nonnull ISimpleWebExecutionContext aSWEC,
                               @Nonnull HCLI aLI,
                               boolean bHasChildren,
                               boolean bIsSelected,
                               boolean bIsExpanded);
}
