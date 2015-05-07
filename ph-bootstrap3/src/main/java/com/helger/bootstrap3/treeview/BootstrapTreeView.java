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
package com.helger.bootstrap3.treeview;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.EBootstrapCSSPathProvider;
import com.helger.bootstrap3.EBootstrapJSPathProvider;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.collections.NonBlockingStack;
import com.helger.commons.convert.IUnidirectionalConverter;
import com.helger.commons.hierarchy.DefaultHierarchyWalkerCallback;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.state.ETriState;
import com.helger.commons.tree.simple.BasicTree;
import com.helger.commons.tree.simple.DefaultTree;
import com.helger.commons.tree.simple.DefaultTreeItem;
import com.helger.commons.tree.simple.ITreeItem;
import com.helger.commons.tree.utils.walk.TreeWalker;
import com.helger.html.hc.IHCNodeBuilder;
import com.helger.html.hc.html.HCDiv;
import com.helger.html.hc.html.HCScript;
import com.helger.html.hc.impl.HCHasCSSClasses;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.js.builder.JSArray;
import com.helger.html.js.builder.JSAssocArray;
import com.helger.html.js.builder.JSInvocation;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.webbasics.app.html.PerRequestCSSIncludes;
import com.helger.webbasics.app.html.PerRequestJSIncludes;

/**
 * Bootstrap Tree View from https://github.com/jonmiles/bootstrap-treeview
 *
 * @author Philip Helger
 */
public class BootstrapTreeView implements IHCNodeBuilder
{
  private final DefaultTree <BootstrapTreeViewItem> m_aTree;
  private final String m_sTreeID;

  private HCHasCSSClasses m_aCollapseIcon;
  private HCHasCSSClasses m_aEmptyIcon;
  private HCHasCSSClasses m_aExpandIcon;
  private HCHasCSSClasses m_aNodeIcon;
  // Default is false
  private ETriState m_eEnableLinks = ETriState.UNDEFINED;
  // Default is true
  private ETriState m_eHighlightSelected = ETriState.UNDEFINED;
  // Default is 2
  private Integer m_aInitialLevels;
  // Default is true
  private ETriState m_eShowBorder = ETriState.UNDEFINED;
  // Default is false
  private ETriState m_eShowTags = ETriState.UNDEFINED;

  public BootstrapTreeView (@Nonnull final DefaultTree <BootstrapTreeViewItem> aTree)
  {
    m_aTree = ValueEnforcer.notNull (aTree, "Tree");
    m_sTreeID = "tree" + GlobalIDFactory.getNewIntID ();
  }

  @Nonnull
  @Nonempty
  public String getTreeID ()
  {
    return m_sTreeID;
  }

  @Nonnull
  public BootstrapTreeView setCollapseIcon (@Nullable final HCHasCSSClasses aCollapseIcon)
  {
    m_aCollapseIcon = aCollapseIcon;
    return this;
  }

  @Nonnull
  public BootstrapTreeView setEmptyIcon (@Nullable final HCHasCSSClasses aEmptyIcon)
  {
    m_aEmptyIcon = aEmptyIcon;
    return this;
  }

  @Nonnull
  public BootstrapTreeView setExpandIcon (@Nullable final HCHasCSSClasses aExpandIcon)
  {
    m_aExpandIcon = aExpandIcon;
    return this;
  }

  @Nonnull
  public BootstrapTreeView setNodeIcon (@Nullable final HCHasCSSClasses aNodeIcon)
  {
    m_aNodeIcon = aNodeIcon;
    return this;
  }

  @Nonnull
  public BootstrapTreeView setEnableLinks (final boolean bEnableLinks)
  {
    m_eEnableLinks = ETriState.valueOf (bEnableLinks);
    return this;
  }

  @Nonnull
  public BootstrapTreeView setHighlightSelected (final boolean bHighlightSelected)
  {
    m_eHighlightSelected = ETriState.valueOf (bHighlightSelected);
    return this;
  }

  @Nonnull
  public BootstrapTreeView setInitialLevels (final int nInitialLevels)
  {
    m_aInitialLevels = Integer.valueOf (nInitialLevels);
    return this;
  }

  @Nonnull
  public BootstrapTreeView setShowBorder (final boolean bShowBorder)
  {
    m_eShowBorder = ETriState.valueOf (bShowBorder);
    return this;
  }

  @Nonnull
  public BootstrapTreeView setShowTags (final boolean bShowTags)
  {
    m_eShowTags = ETriState.valueOf (bShowTags);
    return this;
  }

  @Nonnull
  public JSInvocation invoke ()
  {
    return JQuery.idRef (m_sTreeID).invoke ("treeview");
  }

  private static void _recursiveFillJSTree (@Nullable final List <DefaultTreeItem <BootstrapTreeViewItem>> aTreeItems,
                                            @Nonnull final JSArray aTargetArray)
  {
    if (CollectionHelper.isNotEmpty (aTreeItems))
      for (final DefaultTreeItem <BootstrapTreeViewItem> aTreeItem : aTreeItems)
      {
        // Main tree view item
        final JSAssocArray aJSNode = aTreeItem.getData ().getAsJSAssocArray ();

        // Child nodes
        final JSArray aChildNodes = new JSArray ();
        _recursiveFillJSTree (aTreeItem.getAllChildren (), aChildNodes);
        if (!aChildNodes.isEmpty ())
          aJSNode.add ("nodes", aChildNodes);

        // Append to result list
        aTargetArray.add (aJSNode);
      }
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSArray getJSDataArray ()
  {
    final JSArray aTreeArray = new JSArray ();
    _recursiveFillJSTree (m_aTree.getRootItem ().getAllChildren (), aTreeArray);
    return aTreeArray;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSAssocArray getJSOptions ()
  {
    // JS Code
    final JSAssocArray aJSOptions = new JSAssocArray ();
    aJSOptions.add ("data", getJSDataArray ());

    if (m_aCollapseIcon != null)
      aJSOptions.add ("collapseIcon", m_aCollapseIcon.getAllClassesAsString ());
    if (m_aEmptyIcon != null)
      aJSOptions.add ("emptyIcon", m_aEmptyIcon.getAllClassesAsString ());
    if (m_aExpandIcon != null)
      aJSOptions.add ("expandIcon", m_aExpandIcon.getAllClassesAsString ());
    if (m_aNodeIcon != null)
      aJSOptions.add ("nodeIcon", m_aNodeIcon.getAllClassesAsString ());

    if (m_eEnableLinks.isDefined ())
      aJSOptions.add ("enableLinks", m_eEnableLinks.getAsBooleanValue (false));
    if (m_eHighlightSelected.isDefined ())
      aJSOptions.add ("highlightSelected", m_eHighlightSelected.getAsBooleanValue (true));
    if (m_aInitialLevels != null)
      aJSOptions.add ("levels", m_aInitialLevels.intValue ());
    if (m_eShowBorder.isDefined ())
      aJSOptions.add ("showBorder", m_eShowBorder.getAsBooleanValue (true));
    if (m_eShowTags.isDefined ())
      aJSOptions.add ("showTags", m_eShowTags.getAsBooleanValue (false));

    return aJSOptions;
  }

  @Nullable
  public HCNodeList build ()
  {
    registerExternalResources ();

    final HCNodeList ret = new HCNodeList ();

    // Placeholder
    ret.addChild (new HCDiv ().setID (m_sTreeID));

    // JS Code
    final JSAssocArray aJSOptions = getJSOptions ();
    ret.addChild (new HCScript (invoke ().arg (aJSOptions)));

    return ret;
  }

  public static void registerExternalResources ()
  {
    PerRequestJSIncludes.registerJSIncludeForThisRequest (EBootstrapJSPathProvider.TREE_VIEW);
    PerRequestCSSIncludes.registerCSSIncludeForThisRequest (EBootstrapCSSPathProvider.TREE_VIEW);
  }

  @Nonnull
  public JSInvocation getJSRemoveInvocation ()
  {
    return invoke ().arg ("remove");
  }

  @Nonnull
  public static <DATATYPE, ITEMTYPE extends ITreeItem <DATATYPE, ITEMTYPE>> BootstrapTreeView create (@Nonnull final BasicTree <DATATYPE, ITEMTYPE> aTree,
                                                                                                      @Nonnull final IUnidirectionalConverter <DATATYPE, BootstrapTreeViewItem> aConverter)
  {
    final DefaultTree <BootstrapTreeViewItem> aNewTree = new DefaultTree <BootstrapTreeViewItem> ();
    final NonBlockingStack <DefaultTreeItem <BootstrapTreeViewItem>> aParents = new NonBlockingStack <DefaultTreeItem <BootstrapTreeViewItem>> ();
    aParents.push (aNewTree.getRootItem ());
    TreeWalker.walkTree (aTree, new DefaultHierarchyWalkerCallback <ITEMTYPE> ()
    {
      @Override
      public void onItemBeforeChildren (@Nonnull final ITEMTYPE aItem)
      {
        final DefaultTreeItem <BootstrapTreeViewItem> aChildItem = aParents.peek ()
                                                                           .createChildItem (aConverter.convert (aItem.getData ()));
        aParents.push (aChildItem);
      }

      @Override
      public void onItemAfterChildren (@Nonnull final ITEMTYPE aItem)
      {
        aParents.pop ();
      }
    });
    return new BootstrapTreeView (aNewTree);
  }
}
