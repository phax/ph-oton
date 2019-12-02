/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core.menu.ui;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.NonBlockingStack;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCLI;
import com.helger.html.hc.html.grouping.IHCList;
import com.helger.photon.core.execcontext.ILayoutExecutionContext;
import com.helger.photon.core.execcontext.ISimpleWebExecutionContext;
import com.helger.photon.core.menu.IMenuItemExternal;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuItemRedirectToPage;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuSeparator;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.core.menu.MenuItemDeterminatorCallback;
import com.helger.tree.util.TreeVisitor;
import com.helger.tree.withid.DefaultTreeItemWithID;

/**
 * Renders menu item nodes.
 *
 * @author Philip Helger
 * @param <T>
 *        Parent element type
 */
public class MenuRendererCallback <T extends IHCList <?, HCLI>> extends
                                  DefaultHierarchyVisitorCallback <DefaultTreeItemWithID <String, IMenuObject>>
{
  private final ISimpleWebExecutionContext m_aSWEC;
  private final ISupplier <T> m_aFactory;
  private final NonBlockingStack <T> m_aMenuListStack;
  private final IMenuItemRenderer <T> m_aRenderer;
  private final ICommonsMap <String, Boolean> m_aDisplayMenuItemIDs;
  private final NonBlockingStack <HCLI> m_aMenuItemStack = new NonBlockingStack <> ();
  private final NonBlockingStack <AtomicInteger> m_aChildCountStack = new NonBlockingStack <> ();
  private final NonBlockingStack <DefaultTreeItemWithID <String, IMenuObject>> m_aTreeItemStack = new NonBlockingStack <> ();
  private final String m_sSelectedItem;

  protected MenuRendererCallback (@Nonnull final ILayoutExecutionContext aLEC,
                                  @Nonnull final ISupplier <T> aFactory,
                                  @Nonnull final NonBlockingStack <T> aMenuListStack,
                                  @Nonnull final IMenuItemRenderer <T> aRenderer,
                                  @Nonnull final ICommonsMap <String, Boolean> aDisplayMenuItemIDs)
  {
    ValueEnforcer.notNull (aLEC, "LEC");
    ValueEnforcer.notNull (aMenuListStack, "MenuListStack");
    ValueEnforcer.notNull (aRenderer, "Renderer");
    ValueEnforcer.notNull (aDisplayMenuItemIDs, "DisplayMenuItemIDs");

    m_aSWEC = aLEC;
    m_aFactory = aFactory;
    m_aMenuListStack = aMenuListStack;
    m_aRenderer = aRenderer;
    m_aDisplayMenuItemIDs = aDisplayMenuItemIDs;

    m_aChildCountStack.push (new AtomicInteger (0));
    m_sSelectedItem = aLEC.getSelectedMenuItemID ();
    // The selected item may be null if an invalid menu item ID was passed
  }

  @Override
  public final void onLevelDown ()
  {
    super.onLevelDown ();

    // Check if any child is visible
    final DefaultTreeItemWithID <String, IMenuObject> aParentItem = m_aTreeItemStack.peek ();
    for (final DefaultTreeItemWithID <String, IMenuObject> aChildItem : aParentItem.getAllChildren ())
      if (m_aDisplayMenuItemIDs.containsKey (aChildItem.getID ()))
      {
        // add sub menu structure at the right place
        final T aNewLevel = m_aFactory.get ();
        m_aRenderer.onLevelDown (aNewLevel);
        m_aMenuListStack.push (m_aMenuItemStack.peek ().addAndReturnChild (aNewLevel));
        break;
      }

    m_aChildCountStack.push (new AtomicInteger (0));
  }

  @Override
  public final void onLevelUp ()
  {
    final AtomicInteger aChildCount = m_aChildCountStack.pop ();
    if (aChildCount.intValue () > 0)
    {
      final T aLastLevel = m_aMenuListStack.pop ();
      m_aRenderer.onLevelUp (aLastLevel);
    }
    super.onLevelUp ();
  }

  @Override
  public final EHierarchyVisitorReturn onItemBeforeChildren (@Nonnull final DefaultTreeItemWithID <String, IMenuObject> aItem)
  {
    m_aTreeItemStack.push (aItem);

    final Boolean aExpandedState = m_aDisplayMenuItemIDs.get (aItem.getID ());
    if (aExpandedState != null)
    {
      final T aParent = m_aMenuListStack.peek ();
      final IMenuObject aMenuObj = aItem.getData ();
      boolean bHasChildren = aItem.hasChildren ();
      if (bHasChildren)
      {
        // Check if the item has children to be displayed!
        boolean bHasDisplayChildren = false;
        for (final IMenuObject aChildMenuObj : aItem.getAllChildDatas ())
          if (m_aDisplayMenuItemIDs.containsKey (aChildMenuObj.getID ()))
          {
            bHasDisplayChildren = true;
            break;
          }
        bHasChildren = bHasDisplayChildren;
      }
      if (aMenuObj instanceof IMenuSeparator)
      {
        // separator
        final IHCNode aHCNode = m_aRenderer.renderSeparator (m_aSWEC, (IMenuSeparator) aMenuObj);
        HCLI aLI;
        if (aHCNode instanceof HCLI)
          aLI = aParent.addAndReturnItem ((HCLI) aHCNode);
        else
          aLI = aParent.addAndReturnItem (aHCNode);
        m_aRenderer.onMenuSeparatorItem (m_aSWEC, aLI);
        m_aMenuItemStack.push (aLI);
      }
      else
      {
        final boolean bExpanded = aExpandedState.booleanValue ();
        final boolean bSelected = aMenuObj.getID ().equals (m_sSelectedItem);
        if (aMenuObj instanceof IMenuItemPage)
        {
          // page item
          final IHCNode aHCNode = m_aRenderer.renderMenuItemPage (m_aSWEC,
                                                                  (IMenuItemPage) aMenuObj,
                                                                  bHasChildren,
                                                                  bSelected,
                                                                  bExpanded);
          HCLI aLI;
          if (aHCNode instanceof HCLI)
            aLI = aParent.addAndReturnItem ((HCLI) aHCNode);
          else
            aLI = aParent.addAndReturnItem (aHCNode);
          m_aRenderer.onMenuItemPageItem (m_aSWEC, aLI, bHasChildren, bSelected, bExpanded);
          m_aMenuItemStack.push (aLI);
        }
        else
          if (aMenuObj instanceof IMenuItemExternal)
          {
            // external item
            final IHCNode aHCNode = m_aRenderer.renderMenuItemExternal (m_aSWEC,
                                                                        (IMenuItemExternal) aMenuObj,
                                                                        bHasChildren,
                                                                        bSelected,
                                                                        bExpanded);
            HCLI aLI;
            if (aHCNode instanceof HCLI)
              aLI = aParent.addAndReturnItem ((HCLI) aHCNode);
            else
              aLI = aParent.addAndReturnItem (aHCNode);
            m_aRenderer.onMenuItemExternalItem (m_aSWEC, aLI, bHasChildren, bSelected, bExpanded);
            m_aMenuItemStack.push (aLI);
          }
          else
            if (aMenuObj instanceof IMenuItemRedirectToPage)
            {
              // fake
              m_aMenuItemStack.push (new HCLI ());
              return EHierarchyVisitorReturn.USE_NEXT_SIBLING;
            }
            else
              throw new IllegalStateException ("Unsupported menu object type: " + aMenuObj);
      }
      m_aChildCountStack.peek ().incrementAndGet ();
      return EHierarchyVisitorReturn.CONTINUE;
    }

    // Item should not be displayed
    // push fake item so the pop does not remove anything important!
    m_aMenuItemStack.push (new HCLI ());
    return EHierarchyVisitorReturn.USE_NEXT_SIBLING;
  }

  @Override
  @Nonnull
  public final EHierarchyVisitorReturn onItemAfterChildren (final DefaultTreeItemWithID <String, IMenuObject> aItem)
  {
    m_aTreeItemStack.pop ();
    m_aMenuItemStack.pop ();
    return EHierarchyVisitorReturn.CONTINUE;
  }

  /**
   * Render the whole menu
   *
   * @param aLEC
   *        The current layout execution context. Required for cookie-less
   *        handling. May not be <code>null</code>.
   * @param aFactory
   *        The factory to be used to create nodes of type T. May not be
   *        <code>null</code>.
   * @param aRenderer
   *        The renderer to use
   * @return Never <code>null</code>.
   * @param <T>
   *        HC list type to be instantiated
   */
  @Nonnull
  public static <T extends IHCList <T, HCLI>> T createRenderedMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                                    @Nonnull final ISupplier <T> aFactory,
                                                                    @Nonnull final IMenuItemRenderer <T> aRenderer)
  {
    final IMenuTree aMenuTree = aLEC.getMenuTree ();
    return createRenderedMenu (aLEC,
                               aFactory,
                               aMenuTree.getRootItem (),
                               aRenderer,
                               MenuItemDeterminatorCallback.getAllDisplayMenuItemIDs (aMenuTree,
                                                                                      aLEC.getSelectedMenuItemID ()));
  }

  /**
   * Render a part of the menu
   *
   * @param aLEC
   *        The current layout execution context. Required for cookie-less
   *        handling. May not be <code>null</code>.
   * @param aFactory
   *        The factory to be used to create nodes of type T. May not be
   *        <code>null</code>.
   * @param aStartTreeItem
   *        The start tree to iterate
   * @param aRenderer
   *        The renderer to use
   * @return Never <code>null</code>.
   * @param <T>
   *        HC list type to be instantiated
   */
  @Nonnull
  public static <T extends IHCList <T, HCLI>> T createRenderedMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                                    @Nonnull final ISupplier <T> aFactory,
                                                                    @Nonnull final DefaultTreeItemWithID <String, IMenuObject> aStartTreeItem,
                                                                    @Nonnull final IMenuItemRenderer <T> aRenderer)
  {
    final IMenuTree aMenuTree = aLEC.getMenuTree ();
    return createRenderedMenu (aLEC,
                               aFactory,
                               aStartTreeItem,
                               aRenderer,
                               MenuItemDeterminatorCallback.getAllDisplayMenuItemIDs (aMenuTree,
                                                                                      aLEC.getSelectedMenuItemID ()));
  }

  /**
   * Render the whole menu
   *
   * @param aLEC
   *        The current layout execution context. Required for cookie-less
   *        handling. May not be <code>null</code>.
   * @param aFactory
   *        The factory to be used to create nodes of type T. May not be
   *        <code>null</code>.
   * @param aRenderer
   *        The renderer to use
   * @param aDisplayMenuItemIDs
   *        The menu items to display as a map from menu item ID to expanded
   *        state
   * @return Never <code>null</code>.
   * @param <T>
   *        HC list type to be instantiated
   */
  @Nonnull
  public static <T extends IHCList <T, HCLI>> T createRenderedMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                                    @Nonnull final ISupplier <T> aFactory,
                                                                    @Nonnull final IMenuItemRenderer <T> aRenderer,
                                                                    @Nonnull final ICommonsMap <String, Boolean> aDisplayMenuItemIDs)
  {
    return createRenderedMenu (aLEC, aFactory, aLEC.getMenuTree ().getRootItem (), aRenderer, aDisplayMenuItemIDs);
  }

  /**
   * Render a part of the menu
   *
   * @param aLEC
   *        The current layout execution context. Required for cookie-less
   *        handling. May not be <code>null</code>.
   * @param aFactory
   *        The factory to be used to create nodes of type T. May not be
   *        <code>null</code>.
   * @param aStartTreeItem
   *        The start tree to iterate. May not be <code>null</code>.
   * @param aRenderer
   *        The renderer to use. May not be <code>null</code>.
   * @param aDisplayMenuItemIDs
   *        The menu items to display as a map from menu item ID to expanded
   *        state. May not be <code>null</code>.
   * @return Never <code>null</code>.
   * @param <T>
   *        HC list type to be instantiated
   */
  @Nonnull
  public static <T extends IHCList <T, HCLI>> T createRenderedMenu (@Nonnull final ILayoutExecutionContext aLEC,
                                                                    @Nonnull final ISupplier <T> aFactory,
                                                                    @Nonnull final DefaultTreeItemWithID <String, IMenuObject> aStartTreeItem,
                                                                    @Nonnull final IMenuItemRenderer <T> aRenderer,
                                                                    @Nonnull final ICommonsMap <String, Boolean> aDisplayMenuItemIDs)
  {
    ValueEnforcer.notNull (aFactory, "Factory");

    final NonBlockingStack <T> aNodeStack = new NonBlockingStack <> ();
    aNodeStack.push (aFactory.get ());
    TreeVisitor.visitTreeItem (aStartTreeItem,
                               new MenuRendererCallback <> (aLEC,
                                                            aFactory,
                                                            aNodeStack,
                                                            aRenderer,
                                                            aDisplayMenuItemIDs));
    if (aNodeStack.size () != 1)
      throw new IllegalStateException ("Stack is inconsistent: " + aNodeStack);

    // Return the remaining UL
    return aNodeStack.pop ();
  }
}
