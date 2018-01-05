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
package com.helger.photon.basic.app.menu;

import java.io.Serializable;
import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.url.ConstantHasSimpleURL;
import com.helger.commons.url.IHasSimpleURL;
import com.helger.commons.url.ISimpleURL;
import com.helger.photon.basic.app.page.IPage;

/**
 * Contains all the menu-specific menu operations. This is not meant to be
 * publicly used. The {@link IMenuTree} should be used instead.
 *
 * @author philip
 */
public interface IMenuOperations extends Serializable
{
  /**
   * Append a new menu item separator at root level
   *
   * @return The created menu item separator object. Never <code>null</code>.
   */
  @Nonnull
  IMenuSeparator createRootSeparator ();

  /**
   * Append a new menu item separator as a child of the passed menu item
   *
   * @param sParentID
   *        The parent menu item ID to append the separator to. May not be
   *        <code>null</code>.
   * @return The created menu item separator object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  IMenuSeparator createSeparator (@Nonnull String sParentID);

  /**
   * Append a new menu item separator as a child of the passed menu item
   *
   * @param aParent
   *        The parent menu item to append the separator to. May not be
   *        <code>null</code>.
   * @return The created menu item separator object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuSeparator createSeparator (@Nonnull final IMenuItem aParent)
  {
    ValueEnforcer.notNull (aParent, "Parent");
    return createSeparator (aParent.getID ());
  }

  /**
   * Append a new menu item at root level.
   *
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aPage
   *        The referenced page. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   */
  @Nonnull
  IMenuItemPage createRootItem (@Nonnull String sItemID, @Nonnull IPage aPage);

  /**
   * Append a new menu item at root level.
   *
   * @param aPage
   *        The referenced page. May not be <code>null</code>.
   * @return The created menu item object. The ID of the menu item is the ID of
   *         the page. Never <code>null</code>.
   */
  @Nonnull
  default IMenuItemPage createRootItem (@Nonnull final IPage aPage)
  {
    ValueEnforcer.notNull (aPage, "Page");
    return createRootItem (aPage.getID (), aPage);
  }

  /**
   * Append a new menu item below the specified parent.
   *
   * @param sParentID
   *        The parent menu item ID to append the item to. May not be
   *        <code>null</code>.
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aPage
   *        The referenced page. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  IMenuItemPage createItem (@Nonnull final String sParentID, @Nonnull final String sItemID, @Nonnull final IPage aPage);

  /**
   * Append a new menu item below the specified parent.
   *
   * @param sParentID
   *        The parent menu item ID to append the item to. May not be
   *        <code>null</code>.
   * @param aPage
   *        The referenced page. May not be <code>null</code>.
   * @return The created menu item object. The ID of the menu item is the ID of
   *         the page. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuItemPage createItem (@Nonnull final String sParentID, @Nonnull final IPage aPage)
  {
    ValueEnforcer.notNull (aPage, "Page");
    return createItem (sParentID, aPage.getID (), aPage);
  }

  /**
   * Append a new menu item below the specified parent.
   *
   * @param aParent
   *        The parent menu item to append the item to. May not be
   *        <code>null</code>.
   * @param aPage
   *        The referenced page. May not be <code>null</code>.
   * @return The created menu item object. The ID of the menu item is the ID of
   *         the page. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuItemPage createItem (@Nonnull final IMenuItem aParent, @Nonnull final IPage aPage)
  {
    ValueEnforcer.notNull (aParent, "Parent");
    return createItem (aParent.getID (), aPage);
  }

  /**
   * Append a new menu item at root level.
   *
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   */
  @Nonnull
  default IMenuItemExternal createRootItem (@Nonnull final String sItemID,
                                            @Nonnull final ISimpleURL aURL,
                                            @Nonnull final IHasDisplayText aName)
  {
    return createRootItem (sItemID, new ConstantHasSimpleURL (aURL), aName);
  }

  /**
   * Append a new menu item at root level.
   *
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   */
  @Nonnull
  IMenuItemExternal createRootItem (@Nonnull String sItemID,
                                    @Nonnull IHasSimpleURL aURL,
                                    @Nonnull IHasDisplayText aName);

  /**
   * Append a new menu item below the specified parent.
   *
   * @param aParent
   *        The parent menu item to append the item to. May not be
   *        <code>null</code>.
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuItemExternal createItem (@Nonnull final IMenuItem aParent,
                                        @Nonnull final String sItemID,
                                        @Nonnull final ISimpleURL aURL,
                                        @Nonnull final IHasDisplayText aName)
  {
    return createItem (aParent, sItemID, new ConstantHasSimpleURL (aURL), aName);
  }

  /**
   * Append a new menu item below the specified parent.
   *
   * @param aParent
   *        The parent menu item to append the item to. May not be
   *        <code>null</code>.
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuItemExternal createItem (@Nonnull final IMenuItem aParent,
                                        @Nonnull final String sItemID,
                                        @Nonnull final IHasSimpleURL aURL,
                                        @Nonnull final IHasDisplayText aName)
  {
    ValueEnforcer.notNull (aParent, "Parent");
    return createItem (aParent.getID (), sItemID, aURL, aName);
  }

  /**
   * Append a new menu item below the specified parent.
   *
   * @param sParentID
   *        The parent menu item ID to append the item to. May not be
   *        <code>null</code>.
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  default IMenuItemExternal createItem (@Nonnull final String sParentID,
                                        @Nonnull final String sItemID,
                                        @Nonnull final ISimpleURL aURL,
                                        @Nonnull final IHasDisplayText aName)
  {
    return createItem (sParentID, sItemID, new ConstantHasSimpleURL (aURL), aName);
  }

  /**
   * Append a new menu item below the specified parent.
   *
   * @param sParentID
   *        The parent menu item ID to append the item to. May not be
   *        <code>null</code>.
   * @param sItemID
   *        The new menu item ID. May not be <code>null</code>.
   * @param aURL
   *        The referenced URL. May not be <code>null</code>.
   * @param aName
   *        The name of the menu item. May not be <code>null</code>.
   * @return The created menu item object. Never <code>null</code>.
   * @throws IllegalArgumentException
   *         If the passed parent menu item could not be resolved
   */
  @Nonnull
  IMenuItemExternal createItem (@Nonnull String sParentID,
                                @Nonnull String sItemID,
                                @Nonnull IHasSimpleURL aURL,
                                @Nonnull IHasDisplayText aName);

  /**
   * Set the default menu item. This is a shortcut for
   * <code>setDefaultMenuItemIDs (new String[]{sDefaultMenuItemID});</code> .
   * The passed menu item ID must resolve to an {@link IMenuItemPage} object.
   *
   * @param sDefaultMenuItemID
   *        The default menu item to be set. May be <code>null</code>.
   */
  void setDefaultMenuItemID (@Nullable String sDefaultMenuItemID);

  /**
   * Set the default menu items in the priority order. The passed menu item IDs
   * must resolve to {@link IMenuItemPage} objects.
   *
   * @param aDefaultMenuItemIDs
   *        The default menu items to be set. May be <code>null</code>. This
   *        array may not contain any <code>null</code> entries.
   */
  void setDefaultMenuItemIDs (@Nullable String... aDefaultMenuItemIDs);

  /**
   * Set the default menu items in the priority order. The passed menu item IDs
   * must resolve to {@link IMenuItemPage} objects.
   *
   * @param aDefaultMenuItemIDs
   *        The default menu items to be set. May be <code>null</code>. This
   *        list may not contain any <code>null</code> entries.
   */
  void setDefaultMenuItemIDs (@Nullable Iterable <String> aDefaultMenuItemIDs);

  /**
   * Get the default menu item with the highest priority. Similar to
   * <code>getAllDefaultMenuItemIDs().get(0)</code>.
   *
   * @return The default menu item ID. May be <code>null</code>.
   */
  @Nullable
  String getDefaultMenuItemID ();

  /**
   * @return The default menu item IDs. May not be <code>null</code> but may be
   *         empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <String> getAllDefaultMenuItemIDs ();

  /**
   * Get the default menu item object. Similar to
   * <code>getAllDefaultMenuItems().get(0)</code>.
   *
   * @return <code>null</code> if either no default menu item is present, or the
   *         default menu item ID could not be resolved to a menu item
   */
  @Nullable
  IMenuItemPage getDefaultMenuItem ();

  /**
   * Get the default menu item objects in the correct order.
   *
   * @return The list of all default menu items, in the order as specified by
   *         {@link #setDefaultMenuItemIDs(Iterable)}. Never <code>null</code>
   *         but may be empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IMenuItemPage> getAllDefaultMenuItems ();

  /**
   * Get the menu object with the specified ID
   *
   * @param sID
   *        The ID to be resolved. May be <code>null</code>.
   * @return <code>null</code> if the menu item could not be resolved
   */
  @Nullable
  IMenuObject getMenuObjectOfID (@Nullable String sID);

  /**
   * Iterate all menu objects and invoke the supplied callback.
   *
   * @param aCallback
   *        The callback to be supplied for each menu object. May not be
   *        <code>null</code>.
   */
  void iterateAllMenuObjects (@Nonnull Consumer <IMenuObject> aCallback);

  /**
   * Replace an eventually existing menu item with the new one. The ID of the
   * passed page is used to determine the ID of the menu item to be replaced.
   * The new menu item will be a {@link MenuItemPage} object.
   *
   * @param aNewPage
   *        The page to be used instead of the existing menu item
   * @return The create menu item or <code>null</code> if no such menu item
   *         exists.
   */
  @Nullable
  IMenuItemPage replaceMenuItem (@Nonnull IPage aNewPage);
}
