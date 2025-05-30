/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.page.system;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.NonBlockingStack;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.text.IMultilingualText;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.grouping.IHCLI;
import com.helger.photon.core.menu.IMenuItemExternal;
import com.helger.photon.core.menu.IMenuItemPage;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.core.menu.IMenuSeparator;
import com.helger.photon.core.menu.IMenuTree;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;
import com.helger.tree.util.TreeVisitor;
import com.helger.tree.withid.DefaultTreeItemWithID;

public class BasePageShowChildren <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  public static final class ShowChildrenCallback <WPECTYPE extends IWebPageExecutionContext> extends
                                                 DefaultHierarchyVisitorCallback <DefaultTreeItemWithID <String, IMenuObject>>
  {
    private final WPECTYPE m_aWPEC;
    private final NonBlockingStack <HCUL> m_aStack;
    private final BasePageShowChildrenRenderer m_aRenderer;

    public ShowChildrenCallback (@Nonnull final WPECTYPE aWPEC,
                                 @Nonnull final HCUL aUL,
                                 @Nonnull final BasePageShowChildrenRenderer aRenderer)
    {
      ValueEnforcer.notNull (aWPEC, "WPEC");
      ValueEnforcer.notNull (aUL, "UL");
      ValueEnforcer.notNull (aRenderer, "Renderer");

      m_aWPEC = aWPEC;
      m_aStack = new NonBlockingStack <> (aUL);
      m_aRenderer = aRenderer;
    }

    @Override
    public void onLevelDown ()
    {
      super.onLevelDown ();
      m_aStack.push (new HCUL ());
    }

    @Override
    public void onLevelUp ()
    {
      final HCUL aLastCreated = m_aStack.pop ();
      if (aLastCreated.hasChildren ())
      {
        // There were some LIs created
        final HCUL aParent = m_aStack.peek ();
        if (aParent.hasChildren ())
          aParent.getLastItem ().addChild (aLastCreated);
        else
        {
          // Replace top element with last created element
          m_aStack.pop ();
          m_aStack.push (aLastCreated);
        }
      }
      super.onLevelUp ();
    }

    @Override
    public EHierarchyVisitorReturn onItemBeforeChildren (@Nullable final DefaultTreeItemWithID <String, IMenuObject> aTreeItem)
    {
      final IMenuObject aMenuObj = aTreeItem == null ? null : aTreeItem.getData ();
      if (aMenuObj != null)
      {
        // Call before render callback
        {
          final IHCLI <?> aLI = m_aStack.peek ().getLastChild ();
          m_aRenderer.beforeAddRenderedMenuItem (m_aWPEC, aMenuObj, aLI);
        }

        IHCNode aNode;
        switch (aMenuObj.getMenuObjectType ())
        {
          case SEPARATOR:
            aNode = m_aRenderer.renderMenuSeparator (m_aWPEC, (IMenuSeparator) aMenuObj);
            break;
          case PAGE:
            aNode = m_aRenderer.renderMenuItemPage (m_aWPEC, (IMenuItemPage) aMenuObj);
            break;
          case EXTERNAL:
            aNode = m_aRenderer.renderMenuItemExternal (m_aWPEC, (IMenuItemExternal) aMenuObj);
            break;
          case REDIRECT_TO_PAGE:
            // Nothing to be done
            aNode = null;
            break;
          default:
            throw new IllegalStateException ("Unsupported menu object type: " + aMenuObj);
        }

        IHCLI <?> aNewLI = null;
        if (aNode != null)
          aNewLI = m_aStack.peek ().addAndReturnItem (aNode);

        // Call after render callback
        m_aRenderer.afterAddRenderedMenuItem (m_aWPEC, aMenuObj, aNewLI);
      }
      return EHierarchyVisitorReturn.CONTINUE;
    }
  }

  private final IMenuTree m_aMenuTree;
  private BasePageShowChildrenRenderer m_aRenderer = new BasePageShowChildrenRenderer ();

  public BasePageShowChildren (@Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nonnull final IMenuTree aMenuTree)
  {
    super (sID, aName, null);
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
    // Menu item cannot be resolved here, because the menu tree is not yet
    // ready!
  }

  public BasePageShowChildren (@Nonnull @Nonempty final String sID, @Nonnull final String sName, @Nonnull final IMenuTree aMenuTree)
  {
    this (sID, getAsMLT (sName), aMenuTree);
  }

  @Nonnull
  protected final IMenuTree getMenuTree ()
  {
    return m_aMenuTree;
  }

  @Nonnull
  protected final BasePageShowChildrenRenderer getRenderer ()
  {
    return m_aRenderer;
  }

  @Nonnull
  public final BasePageShowChildren <WPECTYPE> setRenderer (@Nonnull final BasePageShowChildrenRenderer aRenderer)
  {
    m_aRenderer = ValueEnforcer.notNull (aRenderer, "Renderer");
    return this;
  }

  @Nonnull
  @OverrideOnDemand
  protected HCUL createRootUL ()
  {
    return new HCUL ();
  }

  @Nullable
  protected HCUL createChildItemTree (@Nonnull final WPECTYPE aWPEC)
  {
    final DefaultTreeItemWithID <String, IMenuObject> aMenuTreeItem = m_aMenuTree.getItemWithID (getID ());
    if (aMenuTreeItem == null)
      return null;
    if (aMenuTreeItem.getData ().getMenuObjectType ().isSeparator ())
      return null;
    if (aMenuTreeItem.getData ().getMenuObjectType ().isRedirect ())
      return null;

    final HCUL aUL = createRootUL ();
    TreeVisitor.visitTreeItem (aMenuTreeItem, new ShowChildrenCallback <> (aWPEC, aUL, m_aRenderer));
    return aUL;
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    aWPEC.getNodeList ().addChild (createChildItemTree (aWPEC));
  }
}
