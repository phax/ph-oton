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
package com.helger.html.hc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.cache.AnnotationUsageCache;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.lang.CGStringHelper;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.string.StringHelper;
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.IHCCSSNode;
import com.helger.html.hc.IHCHasChildren;
import com.helger.html.hc.IHCJSNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.html.HCLink;
import com.helger.html.hc.html.HCScript;
import com.helger.html.hc.html.HCScriptFile;
import com.helger.html.hc.html.HCScriptOnDocumentReady;
import com.helger.html.hc.html.HCStyle;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCConditionalCommentNode;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.js.builder.jquery.JQuery;
import com.helger.html.js.provider.CollectingJSCodeProvider;

/**
 * This class is used to handle the special nodes (JS and CSS, inline and
 * reference).
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class HCSpecialNodeHandler
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (HCSpecialNodeHandler.class);
  private static final AnnotationUsageCache s_aOOBNAnnotationCache = new AnnotationUsageCache (OutOfBandNode.class);
  private static final AnnotationUsageCache s_aSNLMAnnotationCache = new AnnotationUsageCache (SpecialNodeListModifier.class);
  private static final AtomicBoolean s_aOOBDebugging = new AtomicBoolean (false);
  private static final Map <String, IHCSpecialNodeListModifier> s_aModifiers = new HashMap <String, IHCSpecialNodeListModifier> ();

  @PresentForCodeCoverage
  private static final HCSpecialNodeHandler s_aInstance = new HCSpecialNodeHandler ();

  private HCSpecialNodeHandler ()
  {}

  public static boolean isOutOfBandDebuggingEnabled ()
  {
    return s_aOOBDebugging.get ();
  }

  public static void setOutOfBandDebuggingEnabled (final boolean bEnabled)
  {
    s_aOOBDebugging.set (bEnabled);
  }

  /**
   * Check if the passed node is a CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCCSSNode} (and
   *         not a special case).
   */
  public static boolean isCSSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCCSSNode} (and
   *         not a special case).
   */
  public static boolean isDirectCSSNode (@Nullable final IHCNode aNode)
  {
    // Direct CSS node?
    if (aNode instanceof IHCCSSNode)
    {
      // Special case
      if (aNode instanceof HCLink && !((HCLink) aNode).isCSSLink ())
        return false;
      return true;
    }

    return false;
  }

  /**
   * Check if the passed node is an inline CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isCSSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isDirectCSSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline CSS node?
    return aNode instanceof HCStyle;
  }

  /**
   * Check if the passed node is a file CSS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isCSSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectCSSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file CSS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCStyle}.
   */
  public static boolean isDirectCSSFileNode (@Nullable final IHCNode aNode)
  {
    // File CSS node?
    return aNode instanceof HCLink && ((HCLink) aNode).isCSSLink ();
  }

  /**
   * Check if the passed node is a JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCJSNode}.
   */
  public static boolean isJSNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link IHCJSNode}.
   */
  public static boolean isDirectJSNode (@Nullable final IHCNode aNode)
  {
    // Direct JS node?
    return aNode instanceof IHCJSNode;
  }

  /**
   * Check if the passed node is an inline JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScript}.
   */
  public static boolean isJSInlineNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSInlineNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is an inline JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScript}.
   */
  public static boolean isDirectJSInlineNode (@Nullable final IHCNode aNode)
  {
    // Inline JS node?
    return aNode instanceof HCScript;
  }

  /**
   * Check if the passed node is a file JS node after unwrapping.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isJSFileNode (@Nullable final IHCNode aNode)
  {
    final IHCNode aUnwrappedNode = HCUtils.getUnwrappedNode (aNode);
    return isDirectJSFileNode (aUnwrappedNode);
  }

  /**
   * Check if the passed node is a file JS node.
   *
   * @param aNode
   *        The node to be checked - may be <code>null</code>.
   * @return <code>true</code> if the node implements {@link HCScriptFile}.
   */
  public static boolean isDirectJSFileNode (@Nullable final IHCNode aNode)
  {
    // File JS node?
    return aNode instanceof HCScriptFile;
  }

  /**
   * Check if the passed node is an out-of-band node.
   *
   * @param aHCNode
   *        The node to be checked. May not be <code>null</code>.
   * @return <code>true</code> if it is an out-of-band node, <code>false</code>
   *         if not.
   */
  public static boolean isOutOfBandNode (@Nonnull final IHCNode aHCNode)
  {
    ValueEnforcer.notNull (aHCNode, "HCNode");

    // Is the @OutOfBandNode annotation present?
    if (s_aOOBNAnnotationCache.hasAnnotation (aHCNode))
      return true;

    // If it is a wrapped node, look into it
    if (HCUtils.isWrappedNode (aHCNode))
      return isOutOfBandNode (HCUtils.getUnwrappedNode (aHCNode));

    // Not an out of band node
    return false;
  }

  private static void _recursiveExtractAndRemoveOutOfBandNodes (@Nonnull final IHCHasChildren aParentElement,
                                                                @Nonnull final List <IHCNode> aTargetList,
                                                                @Nonnegative final int nLevel)
  {
    ValueEnforcer.notNull (aParentElement, "ParentElement");

    if (aParentElement.hasChildren ())
    {
      final boolean bDebug = isOutOfBandDebuggingEnabled ();
      int nNodeIndex = 0;

      for (final IHCNode aChild : aParentElement.getAllChildren ())
      {
        if (bDebug)
          s_aLogger.info (StringHelper.getRepeated ("  ", nLevel) +
                          CGStringHelper.getClassLocalName (aChild.getClass ()));

        if (isOutOfBandNode (aChild))
        {
          if (bDebug)
            s_aLogger.info (StringHelper.getRepeated ("  ", nLevel) + "=> is an OOB node!");

          aTargetList.add (aChild);
          if (aParentElement instanceof IHCNodeWithChildren <?>)
            ((IHCNodeWithChildren <?>) aParentElement).removeChild (nNodeIndex);
          else
            throw new IllegalStateException ("Cannot have out-of-band nodes at " + aParentElement);
        }
        else
        {
          ++nNodeIndex;
        }

        // Recurse deeper?
        if (aChild instanceof IHCHasChildren)
          _recursiveExtractAndRemoveOutOfBandNodes ((IHCHasChildren) aChild, aTargetList, nLevel + 1);
      }
    }
  }

  /**
   * Extract all out-of-band child nodes for the passed element. Ensure to call
   * {@link com.helger.html.hc.IHCNode#beforeConvertToNode(com.helger.html.hc.conversion.IHCConversionSettingsToNode)}
   * before calling this method! All out-of-band nodes are detached from their
   * parent so that the original node can be reused.
   *
   * @param aParentElement
   *        The parent element to extract the nodes from. May not be
   *        <code>null</code>.
   * @return The list with out-of-band nodes. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> recursiveExtractAndRemoveOutOfBandNodes (@Nonnull final IHCHasChildren aParentElement)
  {
    ValueEnforcer.notNull (aParentElement, "ParentElement");

    final List <IHCNode> aTargetList = new ArrayList <IHCNode> ();

    // Using HCUtils.iterateTree would be too tedious here
    _recursiveExtractAndRemoveOutOfBandNodes (aParentElement, aTargetList, 0);

    return aTargetList;
  }

  @Nonnull
  public static Iterable <? extends IHCNode> applyModifiers (@Nonnull final Iterable <? extends IHCNode> aNodes)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");

    final Set <Class <? extends IHCSpecialNodeListModifier>> aModifiersToApply = new LinkedHashSet <Class <? extends IHCSpecialNodeListModifier>> ();
    for (final IHCNode aNode : aNodes)
      if (s_aSNLMAnnotationCache.hasAnnotation (aNode))
        aModifiersToApply.add (aNode.getClass ().getAnnotation (SpecialNodeListModifier.class).value ());

    if (aModifiersToApply.isEmpty ())
    {
      // No modifiers present - return as is
      return aNodes;
    }

    // Ensure all modifiers are instantiated
    for (final Class <? extends IHCSpecialNodeListModifier> aModifierClass : aModifiersToApply)
    {
      final String sClassName = aModifierClass.getName ();
      if (!s_aModifiers.containsKey (sClassName))
        s_aModifiers.put (sClassName, GenericReflection.newInstance (aModifierClass));
    }

    // Apply all modifiers
    List <? extends IHCNode> ret = CollectionHelper.newList (aNodes);
    for (final Class <? extends IHCSpecialNodeListModifier> aModifierClass : aModifiersToApply)
    {
      final IHCSpecialNodeListModifier aModifier = s_aModifiers.get (aModifierClass.getName ());
      if (aModifier != null)
      {
        // Invocation successful
        ret = aModifier.modifySpecialNodes (ret);
      }
    }
    return ret;
  }

  /**
   * Merge all inline CSS and JS elements contained in the source nodes into one
   * script elements
   *
   * @param aNodes
   *        Source list of nodes. May not be <code>null</code>.
   * @param bKeepOnDocumentReady
   *        if <code>true</code> than all combined document.ready() scripts are
   *        kept as document.ready() scripts. If <code>false</code> than all
   *        document.ready() scripts are converted to regular scripts and are
   *        executed after all other scripts. For AJAX calls, this should be
   *        <code>false</code>.
   * @return Target list. It contains all non-script nodes and at last one JS
   *         inline node (HCScript).
   */
  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getMergedInlineCSSAndJSNodes (@Nonnull final Iterable <? extends IHCNode> aNodes,
                                                             final boolean bKeepOnDocumentReady)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");

    // Apply all modifiers
    final Iterable <? extends IHCNode> aRealSpecialNodes = applyModifiers (aNodes);

    // Do standard aggregations of CSS and JS
    final List <IHCNode> ret = new ArrayList <IHCNode> ();
    final CollectingJSCodeProvider aJSOnDocumentReadyBefore = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSOnDocumentReadyAfter = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSInlineBefore = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSInlineAfter = new CollectingJSCodeProvider ();
    final StringBuilder aCSSInlineBefore = new StringBuilder ();
    final StringBuilder aCSSInlineAfter = new StringBuilder ();
    for (final IHCNode aNode : aRealSpecialNodes)
    {
      // Note: do not unwrap the node, because it is not allowed to merge JS/CSS
      // with a conditional comment with JS/CSS without a conditional comment!

      // Check HCScriptOnDocumentReady first, because it is a subclass of
      // HCScript
      if (aNode instanceof HCScriptOnDocumentReady)
      {
        final HCScriptOnDocumentReady aScript = (HCScriptOnDocumentReady) aNode;
        (aScript.isEmitAfterFiles () ? aJSOnDocumentReadyAfter : aJSOnDocumentReadyBefore).appendFlattened (aScript.getOnDocumentReadyCode ());
      }
      else
        if (aNode instanceof HCScript)
        {
          final HCScript aScript = (HCScript) aNode;
          (aScript.isEmitAfterFiles () ? aJSInlineAfter : aJSInlineBefore).appendFlattened (aScript.getJSCodeProvider ());
        }
        else
          if (aNode instanceof HCStyle && ((HCStyle) aNode).hasNoMediaOrAll ())
          {
            // Merge only inline CSS nodes, that are media-independent
            final HCStyle aStyle = (HCStyle) aNode;
            (aStyle.isEmitAfterFiles () ? aCSSInlineAfter : aCSSInlineBefore).append (aStyle.getStyleContent ());
          }
          else
          {
            // HCLink
            // HCScriptFile
            // HCConditionalCommentNode
            if (!(aNode instanceof HCLink) &&
                !(aNode instanceof HCScriptFile) &&
                !(aNode instanceof HCConditionalCommentNode))
              s_aLogger.warn ("Found unexpected node to merge inline CSS/JS: " + aNode);

            // Add always!
            ret.add (aNode);
          }
    }

    // on-document-ready JS always as last inline JS!
    if (!aJSOnDocumentReadyBefore.isEmpty ())
      if (bKeepOnDocumentReady)
        aJSInlineBefore.append (JQuery.onDocumentReady (aJSOnDocumentReadyBefore));
      else
        aJSInlineBefore.append (aJSOnDocumentReadyBefore);

    if (!aJSOnDocumentReadyAfter.isEmpty ())
      if (bKeepOnDocumentReady)
        aJSInlineAfter.append (JQuery.onDocumentReady (aJSOnDocumentReadyAfter));
      else
        aJSInlineAfter.append (aJSOnDocumentReadyAfter);

    // Finally add the inline JS
    if (!aJSInlineBefore.isEmpty ())
      ret.add (new HCScript (aJSInlineBefore).setEmitAfterFiles (false));

    if (!aJSInlineAfter.isEmpty ())
      ret.add (new HCScript (aJSInlineAfter).setEmitAfterFiles (true));

    // Add all merged inline CSSs
    if (aCSSInlineBefore.length () > 0)
      ret.add (new HCStyle (aCSSInlineBefore.toString ()).setEmitAfterFiles (false));

    if (aCSSInlineAfter.length () > 0)
      ret.add (new HCStyle (aCSSInlineAfter.toString ()).setEmitAfterFiles (true));

    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static List <IHCNode> getWithoutSpecialNodes (@Nonnull final Iterable <? extends IHCNode> aNodes,
                                                       @Nonnull final AbstractHCSpecialNodes <?> aSpecialNodes)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    final List <IHCNode> ret = new ArrayList <IHCNode> ();

    for (final IHCNode aNode : aNodes)
    {
      if (isDirectCSSFileNode (aNode))
      {
        aSpecialNodes.addExternalCSS (((HCLink) aNode).getHrefString ());
      }
      else
        if (isDirectCSSInlineNode (aNode))
        {
          aSpecialNodes.addInlineCSS (((HCStyle) aNode).getStyleContent ());
        }
        else
          if (isDirectJSFileNode (aNode))
          {
            aSpecialNodes.addExternalJS (((HCScriptFile) aNode).getSrcString ());
          }
          else
            if (isDirectJSInlineNode (aNode))
            {
              aSpecialNodes.addInlineJS (((HCScript) aNode).getJSCodeProvider ());
            }
            else
            {
              ret.add (aNode);
            }
    }

    return ret;
  }

  /**
   * Extract all out-of-band nodes of the source node, merge JS and CSS and
   * finally extract all special nodes into the passed object.
   *
   * @param aNode
   *        Source node. May not be <code>null</code>.
   * @param aSpecialNodes
   *        Target special node object to be filled. May not be
   *        <code>null</code>.
   * @param bKeepOnDocumentReady
   *        if <code>true</code> than all combined document.ready() scripts are
   *        kept as document.ready() scripts. If <code>false</code> than all
   *        document.ready() scripts are converted to regular scripts and are
   *        executed after all other scripts. For AJAX calls, this should be
   *        <code>false</code>.
   * @return A node list with all remaining (non-special) nodes. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static HCNodeList extractSpecialContent (@Nonnull final IHCHasChildren aNode,
                                                  @Nonnull final AbstractHCSpecialNodes <?> aSpecialNodes,
                                                  final boolean bKeepOnDocumentReady)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    // Extract all out of band nodes from the passed node
    List <IHCNode> aExtractedOutOfBandNodes = recursiveExtractAndRemoveOutOfBandNodes (aNode);

    // Merge JS/CSS nodes
    aExtractedOutOfBandNodes = getMergedInlineCSSAndJSNodes (aExtractedOutOfBandNodes, bKeepOnDocumentReady);

    // Extract the special nodes into the provided object
    aExtractedOutOfBandNodes = getWithoutSpecialNodes (aExtractedOutOfBandNodes, aSpecialNodes);

    // Now the aExtractedOutOfBandNodes list should be empty - otherwise we have
    // an internal inconsistency (see the warning below)
    if (!aExtractedOutOfBandNodes.isEmpty ())
      s_aLogger.warn ("Out-of-band nodes are left after merging and extraction: " + aExtractedOutOfBandNodes);

    // Add the content without the out-of-band nodes
    final HCNodeList ret = HCNodeList.create (aNode);
    // And to be sure, add all remaining out-of-band nodes at the end so that no
    // node will get lost!
    ret.addChildren (aExtractedOutOfBandNodes);
    return ret;
  }
}
