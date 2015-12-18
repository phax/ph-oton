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
package com.helger.photon.uicore.page.system;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.impl.NonBlockingStack;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.tree.util.TreeVisitor;
import com.helger.commons.tree.withid.DefaultTreeItemWithID;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.grouping.HCUL;
import com.helger.html.hc.html.grouping.IHCLI;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.photon.basic.app.menu.IMenuItemExternal;
import com.helger.photon.basic.app.menu.IMenuItemPage;
import com.helger.photon.basic.app.menu.IMenuObject;
import com.helger.photon.basic.app.menu.IMenuSeparator;
import com.helger.photon.basic.app.menu.IMenuTree;
import com.helger.photon.uicore.page.AbstractWebPage;
import com.helger.photon.uicore.page.IWebPageExecutionContext;

public class BasePageShowChildren <WPECTYPE extends IWebPageExecutionContext> extends AbstractWebPage <WPECTYPE>
{
  private static final class ShowChildrenCallback <WPECTYPE extends IWebPageExecutionContext> extends
                                                  DefaultHierarchyVisitorCallback <DefaultTreeItemWithID <String, IMenuObject>>
  {
    private final WPECTYPE m_aWPEC;
    private final NonBlockingStack <HCUL> m_aStack;
    private final BasePageShowChildrenRenderer m_aRenderer;

    ShowChildrenCallback (@Nonnull final HCUL aUL,
                          @Nonnull final WPECTYPE aWPEC,
                          @Nonnull final BasePageShowChildrenRenderer aRenderer)
    {
      ValueEnforcer.notNull (aUL, "UL");
      ValueEnforcer.notNull (aWPEC, "WPEC");
      ValueEnforcer.notNull (aRenderer, "Renderer");
      m_aWPEC = aWPEC;
      m_aStack = new NonBlockingStack <HCUL> (aUL);
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
  private final BasePageShowChildrenRenderer m_aRenderer;

  public BasePageShowChildren (@Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nonnull final IMenuTree aMenuTree)
  {
    this (sID, aName, aMenuTree, new BasePageShowChildrenRenderer ());
  }

  public BasePageShowChildren (@Nonnull @Nonempty final String sID,
                               @Nonnull final IMultilingualText aName,
                               @Nonnull final IMenuTree aMenuTree,
                               @Nonnull final BasePageShowChildrenRenderer aRenderer)
  {
    super (sID, aName);
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
    m_aRenderer = ValueEnforcer.notNull (aRenderer, "Renderer");
  }

  public BasePageShowChildren (@Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nonnull final IMenuTree aMenuTree)
  {
    this (sID, sName, aMenuTree, new BasePageShowChildrenRenderer ());
  }

  public BasePageShowChildren (@Nonnull @Nonempty final String sID,
                               @Nonnull final String sName,
                               @Nonnull final IMenuTree aMenuTree,
                               @Nonnull final BasePageShowChildrenRenderer aRenderer)
  {
    super (sID, sName);
    m_aMenuTree = ValueEnforcer.notNull (aMenuTree, "MenuTree");
    m_aRenderer = ValueEnforcer.notNull (aRenderer, "Renderer");
  }

  @Nonnull
  @OverrideOnDemand
  protected HCUL createRootUL ()
  {
    return new HCUL ();
  }

  @Override
  protected void fillContent (@Nonnull final WPECTYPE aWPEC)
  {
    final HCNodeList aNodeList = aWPEC.getNodeList ();

    final DefaultTreeItemWithID <String, IMenuObject> aMenuTreeItem = m_aMenuTree.getItemWithID (getID ());
    if (aMenuTreeItem != null && aMenuTreeItem.getData ().getMenuObjectType ().isNotSeparator ())
    {
      final HCUL aUL = createRootUL ();
      TreeVisitor.visitTreeItem (aMenuTreeItem, new ShowChildrenCallback <WPECTYPE> (aUL, aWPEC, m_aRenderer));
      aNodeList.addChild (aUL);
    }
  }
}
