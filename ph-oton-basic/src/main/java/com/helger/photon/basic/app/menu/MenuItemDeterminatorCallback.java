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
package com.helger.photon.basic.app.menu;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.hierarchy.DefaultHierarchyWalkerCallback;
import com.helger.commons.tree.utils.walk.TreeWalker;
import com.helger.commons.tree.withid.DefaultTreeItemWithID;

/**
 * Determine all menu items to show, depending on the currently selected menu
 * item.
 *
 * @author Philip Helger
 */
public class MenuItemDeterminatorCallback extends AbstractMenuItemDeterminatorCallback
{
  private final Map <String, Boolean> m_aItems = new HashMap <String, Boolean> ();
  private final String m_sSelectedItemID;
  private final DefaultTreeItemWithID <String, IMenuObject> m_aSelectedItem;

  public MenuItemDeterminatorCallback (@Nonnull final IMenuTree aMenuTree, @Nullable final String sSelectedMenuItemID)
  {
    super (aMenuTree);
    m_sSelectedItemID = sSelectedMenuItemID;
    m_aSelectedItem = aMenuTree.getItemWithID (m_sSelectedItemID);
    // The selected item may be null if an invalid menu item ID was passed
  }

  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected boolean isMenuItemValidToBeDisplayed (@Nonnull final IMenuObject aMenuObj)
  {
    return aMenuObj.matchesDisplayFilter ();
  }

  @OverrideOnDemand
  protected void rememberMenuItemForDisplay (@Nonnull @Nonempty final String sMenuItemID, final boolean bExpanded)
  {
    final Boolean aExpanded = m_aItems.get (sMenuItemID);
    if (aExpanded == null || !aExpanded.booleanValue ())
      m_aItems.put (sMenuItemID, Boolean.valueOf (bExpanded));
  }

  @Override
  public final void onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, IMenuObject> aItem)
  {
    boolean bShow;
    boolean bAddAllChildrenOnThisLevel = false;
    boolean bExpanded = false;
    final boolean bIsTopLevel = getLevel () == 0;
    if (m_aSelectedItem == null)
    {
      // Show only top level entries
      bShow = bIsTopLevel;
    }
    else
    {
      // 1. Top level entries are always shown
      // 2. Show this item, if it is a parent of the selected item (sub menu)
      // 3. Show this item, if it is a direct child of the selected item
      // 4. Show this item, if if is a direct child of the selected item's
      // parent
      if (bIsTopLevel)
      {
        // 1.
        bShow = true;
        bExpanded = m_aSelectedItem.isSameOrChildOf (aItem);
      }
      else
      {
        // 2.
        if (m_aSelectedItem.isSameOrChildOf (aItem))
        {
          bShow = true;
          bExpanded = true;
        }
        else
        {
          final DefaultTreeItemWithID <String, IMenuObject> aItemParent = aItem.getParent ();
          // 3.
          if (aItemParent.equals (m_aSelectedItem))
          {
            bShow = true;
          }
          else
            // 4.
            if (aItemParent.getChildItemOfDataID (m_sSelectedItemID) != null)
              bShow = true;
            else
              bShow = false;
        }
        bAddAllChildrenOnThisLevel = bShow;
      }
    }

    if (bShow || bAddAllChildrenOnThisLevel)
    {
      // Check display filter
      if (!isMenuItemValidToBeDisplayed (aItem.getData ()))
      {
        bShow = false;
        bAddAllChildrenOnThisLevel = false;
        bExpanded = false;
      }
    }

    if (bShow)
      rememberMenuItemForDisplay (aItem.getID (), bExpanded);
    if (bAddAllChildrenOnThisLevel)
      for (final DefaultTreeItemWithID <String, IMenuObject> aSibling : aItem.getParent ().getAllChildren ())
        if (isMenuItemValidToBeDisplayed (aSibling.getData ()))
          rememberMenuItemForDisplay (aSibling.getID (), false);
  }

  /**
   * @return A map with all items to be displayed, where the key is the menu
   *         item ID and the value is the expansion state of the item.
   */
  @Nonnull
  @ReturnsMutableCopy
  public Map <String, Boolean> getAllItemIDs ()
  {
    return CollectionHelper.newMap (m_aItems);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, Boolean> getAllDisplayMenuItemIDs (@Nonnull final IMenuTree aMenuTree,
                                                                @Nullable final String sSelectedMenuItemID)
  {
    return getAllDisplayMenuItemIDs (new MenuItemDeterminatorCallback (aMenuTree, sSelectedMenuItemID));
  }

  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, Boolean> getAllDisplayMenuItemIDs (@Nonnull final IMenuItemDeterminatorCallback aDeterminator)
  {
    ValueEnforcer.notNull (aDeterminator, "Determinator");

    TreeWalker.walkTree (aDeterminator.getMenuTree (), aDeterminator);
    return aDeterminator.getAllItemIDs ();
  }

  /**
   * Get all menu items without usage a separate
   * {@link MenuItemDeterminatorCallback} instance.
   *
   * @param aMenuTree
   *        The menu tree to get all items from. May not be <code>null</code>.
   * @return A non-<code>null</code> map with all menu item IDs as keys and the
   *         "expansion state" as the value.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static Map <String, Boolean> getAllMenuItemIDs (@Nonnull final IMenuTree aMenuTree)
  {
    ValueEnforcer.notNull (aMenuTree, "MenuTree");

    final Map <String, Boolean> ret = new HashMap <String, Boolean> ();
    TreeWalker.walkTree (aMenuTree, new DefaultHierarchyWalkerCallback <DefaultTreeItemWithID <String, IMenuObject>> ()
    {
      @Override
      public void onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, IMenuObject> aItem)
      {
        ret.put (aItem.getID (), Boolean.valueOf (aItem.hasChildren ()));
      }
    });
    return ret;
  }
}
