/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls.treeview;

import java.util.List;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.NonBlockingStack;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.state.ETriState;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.html.grouping.AbstractHCDiv;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.jquery.JQuery;
import com.helger.html.jscode.JSArray;
import com.helger.html.jscode.JSAssocArray;
import com.helger.html.jscode.JSInvocation;
import com.helger.photon.app.html.PhotonCSS;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsCSSPathProvider;
import com.helger.photon.bootstrap4.uictrls.EBootstrapUICtrlsJSPathProvider;
import com.helger.tree.BasicTree;
import com.helger.tree.DefaultTree;
import com.helger.tree.DefaultTreeItem;
import com.helger.tree.ITreeItem;
import com.helger.tree.util.TreeVisitor;

/**
 * Bootstrap Tree View from https://github.com/jonmiles/bootstrap-treeview
 *
 * @author Philip Helger
 */
public class BootstrapTreeView extends AbstractHCDiv <BootstrapTreeView>
{
  private final DefaultTree <BootstrapTreeViewItem> m_aTree;

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
    ensureID ();
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
    return JQuery.idRef (this).invoke ("treeview");
  }

  @Nonnull
  public JSInvocation getJSRemoveInvocation ()
  {
    return invoke ().arg ("remove");
  }

  @Nonnull
  public JSInvocation getJSExpandAllInvocation ()
  {
    return invoke ().arg ("expandAll");
  }

  @Nonnull
  public JSInvocation getJSExpandAllInvocation (final int nLevels)
  {
    return getJSExpandAllInvocation ().arg (new JSAssocArray ().add ("levels", nLevels));
  }

  @Nonnull
  public JSInvocation getJSExpandAllInvocation (final int nLevels, final boolean bSilent)
  {
    return getJSExpandAllInvocation ().arg (new JSAssocArray ().add ("levels", nLevels).add ("silent", bSilent));
  }

  @Nonnull
  public JSInvocation getJSCollapseAllInvocation ()
  {
    return invoke ().arg ("collapseAll");
  }

  @Nonnull
  public JSInvocation getJSCollapseAllInvocation (final int nLevels)
  {
    return getJSCollapseAllInvocation ().arg (new JSAssocArray ().add ("levels", nLevels));
  }

  @Nonnull
  public JSInvocation getJSCollapseAllInvocation (final int nLevels, final boolean bSilent)
  {
    return getJSCollapseAllInvocation ().arg (new JSAssocArray ().add ("levels", nLevels).add ("silent", bSilent));
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
        if (aChildNodes.isNotEmpty ())
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

  @Override
  protected void onFinalizeNodeState (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                      @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    // JS Code
    final JSAssocArray aJSOptions = getJSOptions ();
    aTargetNode.addChild (new HCScriptInline (invoke ().arg (aJSOptions)));
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    PhotonJS.registerJSIncludeForThisRequest (EBootstrapUICtrlsJSPathProvider.TREE_VIEW);
    PhotonCSS.registerCSSIncludeForThisRequest (EBootstrapUICtrlsCSSPathProvider.TREE_VIEW);
  }

  @Nonnull
  public static <DATATYPE, ITEMTYPE extends ITreeItem <DATATYPE, ITEMTYPE>> BootstrapTreeView create (@Nonnull final BasicTree <DATATYPE, ITEMTYPE> aTree,
                                                                                                      @Nonnull final Function <DATATYPE, BootstrapTreeViewItem> aConverter)
  {
    final DefaultTree <BootstrapTreeViewItem> aNewTree = new DefaultTree <> ();
    final NonBlockingStack <DefaultTreeItem <BootstrapTreeViewItem>> aParents = new NonBlockingStack <> ();
    aParents.push (aNewTree.getRootItem ());
    TreeVisitor.visitTree (aTree, new DefaultHierarchyVisitorCallback <ITEMTYPE> ()
    {
      @Override
      public EHierarchyVisitorReturn onItemBeforeChildren (@Nonnull final ITEMTYPE aItem)
      {
        final DefaultTreeItem <BootstrapTreeViewItem> aChildItem = aParents.peek ()
                                                                           .createChildItem (aConverter.apply (aItem.getData ()));
        aParents.push (aChildItem);
        return EHierarchyVisitorReturn.CONTINUE;
      }

      @Override
      public EHierarchyVisitorReturn onItemAfterChildren (@Nonnull final ITEMTYPE aItem)
      {
        aParents.pop ();
        return EHierarchyVisitorReturn.CONTINUE;
      }
    });
    return new BootstrapTreeView (aNewTree);
  }
}
