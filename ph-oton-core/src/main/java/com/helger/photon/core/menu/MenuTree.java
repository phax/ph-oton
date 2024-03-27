/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.core.menu;

import java.util.function.Consumer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.commons.url.IHasSimpleURL;
import com.helger.photon.core.page.IPage;
import com.helger.tree.util.TreeVisitor;
import com.helger.tree.withid.DefaultTreeItemWithID;
import com.helger.tree.withid.unique.DefaultTreeWithGlobalUniqueID;

/**
 * Represents a single menu tree
 *
 * @author Philip Helger
 */
public class MenuTree extends DefaultTreeWithGlobalUniqueID <String, IMenuObject> implements IMenuTree
{
  private static final Logger LOGGER = LoggerFactory.getLogger (MenuTree.class);

  private final ICommonsList <String> m_aDefaultMenuItemIDs = new CommonsArrayList <> ();

  public MenuTree ()
  {}

  @Nonnull
  protected static <T extends IMenuObject> T internalCreateChildItem (@Nonnull final DefaultTreeItemWithID <String, IMenuObject> aParentItem,
                                                                      @Nonnull final T aMenuObject)
  {
    if (aParentItem.createChildItem (aMenuObject.getID (), aMenuObject, false) == null)
      throw new IllegalArgumentException ("Failed to add the menu object " +
                                          aMenuObject +
                                          " probably the ID is already contained!");
    return aMenuObject;
  }

  @Nonnull
  public IMenuSeparator createRootSeparator (@Nonnull @Nonempty final String sID)
  {
    return internalCreateChildItem (getRootItem (), new MenuSeparator (sID));
  }

  @Nonnull
  public IMenuSeparator createSeparator (@Nonnull final String sParentID, @Nonnull @Nonempty final String sID)
  {
    final DefaultTreeItemWithID <String, IMenuObject> aParentItem = getItemWithID (sParentID);
    if (aParentItem == null)
      throw new IllegalArgumentException ("No such parent menu item '" + sParentID + "'");
    return internalCreateChildItem (aParentItem, new MenuSeparator (sID));
  }

  @Nonnull
  public IMenuItemPage createRootItem (@Nonnull final String sItemID, @Nonnull final IPage aPage)
  {
    return internalCreateChildItem (getRootItem (), new MenuItemPage (sItemID, aPage));
  }

  @Nonnull
  public IMenuItemPage createItem (@Nonnull final String sParentID,
                                   @Nonnull final String sItemID,
                                   @Nonnull final IPage aPage)
  {
    final DefaultTreeItemWithID <String, IMenuObject> aParentItem = getItemWithID (sParentID);
    if (aParentItem == null)
      throw new IllegalArgumentException ("No such parent menu item '" + sParentID + "'");
    return internalCreateChildItem (aParentItem, new MenuItemPage (sItemID, aPage));
  }

  @Nonnull
  public IMenuItemExternal createRootItem (@Nonnull final String sItemID,
                                           @Nonnull final IHasSimpleURL aURL,
                                           @Nonnull final IHasDisplayText aName)
  {
    return internalCreateChildItem (getRootItem (), new MenuItemExternal (sItemID, aURL, aName));
  }

  @Nonnull
  public IMenuItemExternal createItem (@Nonnull final String sParentID,
                                       @Nonnull final String sItemID,
                                       @Nonnull final IHasSimpleURL aURL,
                                       @Nonnull final IHasDisplayText aName)
  {
    final DefaultTreeItemWithID <String, IMenuObject> aParentItem = getItemWithID (sParentID);
    if (aParentItem == null)
      throw new IllegalArgumentException ("No such parent menu item '" + sParentID + "'");
    return internalCreateChildItem (aParentItem, new MenuItemExternal (sItemID, aURL, aName));
  }

  @Nonnull
  public IMenuItemRedirectToPage createRedirect (@Nonnull final String sItemID, @Nonnull final IMenuItemPage aPage)
  {
    return internalCreateChildItem (getRootItem (), new MenuItemRedirectToPage (sItemID, aPage));
  }

  public void setDefaultMenuItemID (@Nullable final String sDefaultMenuItemID)
  {
    if (sDefaultMenuItemID == null)
      m_aDefaultMenuItemIDs.clear ();
    else
      m_aDefaultMenuItemIDs.set (sDefaultMenuItemID);
  }

  public void setDefaultMenuItemIDs (@Nullable final String... aDefaultMenuItemIDs)
  {
    m_aDefaultMenuItemIDs.setAll (aDefaultMenuItemIDs);
  }

  public void setDefaultMenuItemIDs (@Nullable final Iterable <String> aDefaultMenuItemIDs)
  {
    m_aDefaultMenuItemIDs.setAll (aDefaultMenuItemIDs);
  }

  @Nullable
  public String getDefaultMenuItemID ()
  {
    return m_aDefaultMenuItemIDs.getFirstOrNull ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <String> getAllDefaultMenuItemIDs ()
  {
    return m_aDefaultMenuItemIDs.getClone ();
  }

  @Nullable
  private IMenuItemPage _getDefaultMenuItem (@Nullable final String sMenuItemID)
  {
    if (sMenuItemID != null)
    {
      // Resolve default menu item ID
      final DefaultTreeItemWithID <String, IMenuObject> aTreeItem = getItemWithID (sMenuItemID);
      if (aTreeItem != null)
      {
        final IMenuObject aMenuItem = aTreeItem.getData ();
        if (aMenuItem instanceof IMenuItemPage)
          return (IMenuItemPage) aMenuItem;
        LOGGER.warn ("The default menu object ID '" +
                     sMenuItemID +
                     "' does not resolve to an IMenuItemPage but to " +
                     ClassHelper.getSafeClassName (aMenuItem));
      }
      else
        LOGGER.warn ("Failed to resolve the default menu item ID '" + sMenuItemID + "'");
    }
    return null;
  }

  @Nullable
  public IMenuItemPage getDefaultMenuItem ()
  {
    return _getDefaultMenuItem (getDefaultMenuItemID ());
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IMenuItemPage> getAllDefaultMenuItems ()
  {
    final ICommonsList <IMenuItemPage> ret = new CommonsArrayList <> ();
    if (m_aDefaultMenuItemIDs != null)
      for (final String sDefaultMenuItemID : m_aDefaultMenuItemIDs)
      {
        final IMenuItemPage aDefaultMenuItem = _getDefaultMenuItem (sDefaultMenuItemID);
        if (aDefaultMenuItem != null)
          ret.add (aDefaultMenuItem);
      }
    return ret;
  }

  @Nullable
  public IMenuObject getMenuObjectOfID (@Nullable final String sID)
  {
    return getItemDataWithID (sID);
  }

  public void iterateAllMenuObjects (@Nonnull final Consumer <? super IMenuObject> aCallback)
  {
    ValueEnforcer.notNull (aCallback, "Callback");

    TreeVisitor.visitTree (this, new DefaultHierarchyVisitorCallback <DefaultTreeItemWithID <String, IMenuObject>> ()
    {
      @Override
      public EHierarchyVisitorReturn onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, IMenuObject> aItem)
      {
        aCallback.accept (aItem.getData ());
        return EHierarchyVisitorReturn.CONTINUE;
      }
    });
  }

  @Nullable
  public IMenuItemPage replaceMenuItem (@Nonnull final IPage aNewPage)
  {
    ValueEnforcer.notNull (aNewPage, "NewPage");

    final String sID = aNewPage.getID ();
    final DefaultTreeItemWithID <String, IMenuObject> aItem = getItemWithID (sID);
    if (aItem == null)
      return null;

    final IMenuItemPage ret = new MenuItemPage (sID, aNewPage);
    aItem.setData (ret);
    return ret;
  }

  @Nullable
  public DefaultTreeItemWithID <String, IMenuObject> getRootItemOfItemWithID (@Nullable final String sMenuItemID)
  {
    // Resolve tree item
    final DefaultTreeItemWithID <String, IMenuObject> aItem = getItemWithID (sMenuItemID);
    if (aItem == null || aItem.isRootItem ())
      return null;

    // Find root item of selected item
    DefaultTreeItemWithID <String, IMenuObject> aCurRootItem = aItem;
    while (!aCurRootItem.getParent ().isRootItem ())
      aCurRootItem = aCurRootItem.getParent ();
    return aCurRootItem;
  }

  @Nullable
  public IMenuObject getRootItemDataOfItemWithID (@Nullable final String sMenuItemID)
  {
    final DefaultTreeItemWithID <String, IMenuObject> aTreeItem = getRootItemOfItemWithID (sMenuItemID);
    return aTreeItem == null ? null : aTreeItem.getData ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final MenuTree rhs = (MenuTree) o;
    return EqualsHelper.equals (m_aDefaultMenuItemIDs, rhs.m_aDefaultMenuItemIDs);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aDefaultMenuItemIDs).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("DefaultMenuItemIDs", m_aDefaultMenuItemIDs).getToString ();
  }
}
