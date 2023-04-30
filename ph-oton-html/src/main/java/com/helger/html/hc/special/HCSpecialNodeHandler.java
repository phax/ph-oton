/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.html.hc.special;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.cache.AnnotationUsageCache;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.lang.ClassHelper;
import com.helger.commons.lang.GenericReflection;
import com.helger.commons.string.StringHelper;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.EHCNodeState;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.hc.html.IHCConditionalCommentNode;
import com.helger.html.hc.html.metadata.HCCSSNodeDetector;
import com.helger.html.hc.html.metadata.HCLink;
import com.helger.html.hc.html.metadata.HCStyle;
import com.helger.html.hc.html.script.HCJSNodeDetector;
import com.helger.html.hc.html.script.HCScriptFile;
import com.helger.html.hc.html.script.HCScriptInline;
import com.helger.html.hc.html.script.HCScriptInlineOnDocumentReady;
import com.helger.html.hc.html.script.IHCScriptInline;
import com.helger.html.js.CollectingJSCodeProvider;
import com.helger.html.resource.css.ICSSCodeProvider;

/**
 * This class is used to handle the special nodes (JS and CSS, inline and
 * reference).
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class HCSpecialNodeHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (HCSpecialNodeHandler.class);
  private static final AnnotationUsageCache CACHE_OOBN = new AnnotationUsageCache (OutOfBandNode.class);
  private static final AnnotationUsageCache CACHE_SNLM = new AnnotationUsageCache (SpecialNodeListModifier.class);
  private static final ICommonsMap <String, IHCSpecialNodeListModifier> s_aModifiers = new CommonsHashMap <> ();

  @PresentForCodeCoverage
  private static final HCSpecialNodeHandler INSTANCE = new HCSpecialNodeHandler ();

  private HCSpecialNodeHandler ()
  {}

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
    if (CACHE_OOBN.hasAnnotation (aHCNode))
      return true;

    // If it is a wrapped node, look into it
    if (HCHelper.isWrappedNode (aHCNode))
      return isOutOfBandNode (HCHelper.getUnwrappedNode (aHCNode));

    // Not an out of band node
    return false;
  }

  private static void _recursiveExtractAndRemoveOutOfBandNodes (@Nonnull final IHCNode aParentElement,
                                                                @Nonnull final List <IHCNode> aTargetList,
                                                                @Nonnegative final int nLevel)
  {
    ValueEnforcer.notNull (aParentElement, "ParentElement");

    if (aParentElement.hasChildren ())
    {
      final boolean bDebug = HCSettings.isOutOfBandDebuggingEnabled ();
      int nNodeIndex = 0;

      for (final IHCNode aChild : aParentElement.getAllChildren ())
      {
        if (bDebug)
          LOGGER.info (StringHelper.getRepeated ("  ", nLevel) + ClassHelper.getClassLocalName (aChild.getClass ()));

        if (isOutOfBandNode (aChild))
        {
          if (bDebug)
            LOGGER.info (StringHelper.getRepeated ("  ", nLevel) + "=> is an OOB node!");

          aTargetList.add (aChild);
          if (aParentElement instanceof IHCHasChildrenMutable <?, ?>)
            ((IHCHasChildrenMutable <?, ?>) aParentElement).removeChildAt (nNodeIndex);
          else
            throw new IllegalStateException ("Cannot remove out-of-band node from " + aParentElement + " at index " + nNodeIndex);
        }
        else
        {
          ++nNodeIndex;
        }

        // Recurse deeper?
        _recursiveExtractAndRemoveOutOfBandNodes (aChild, aTargetList, nLevel + 1);
      }
    }
  }

  /**
   * Extract all out-of-band child nodes for the passed element. Must be called
   * after the element is finished! All out-of-band nodes are detached from
   * their parent so that the original node can be reused. Wrapped nodes where
   * the inner node is an out of band node are also considered and removed.
   *
   * @param aParentElement
   *        The parent element to extract the nodes from. May not be
   *        <code>null</code>.
   * @param aTargetList
   *        The target list to be filled. May not be <code>null</code>.
   */
  public static void recursiveExtractAndRemoveOutOfBandNodes (@Nonnull final IHCNode aParentElement,
                                                              @Nonnull final List <IHCNode> aTargetList)
  {
    ValueEnforcer.notNull (aParentElement, "ParentElement");
    ValueEnforcer.notNull (aTargetList, "TargetList");

    // Using HCUtils.iterateTree would be too tedious here
    _recursiveExtractAndRemoveOutOfBandNodes (aParentElement, aTargetList, 0);
  }

  @Nonnull
  public static Iterable <? extends IHCNode> applyModifiers (@Nonnull final Iterable <? extends IHCNode> aNodes)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");

    final ICommonsOrderedSet <Class <? extends IHCSpecialNodeListModifier>> aModifiersToApply = new CommonsLinkedHashSet <> ();
    for (final IHCNode aNode : aNodes)
      if (CACHE_SNLM.hasAnnotation (aNode))
        aModifiersToApply.add (aNode.getClass ().getAnnotation (SpecialNodeListModifier.class).value ());

    if (aModifiersToApply.isEmpty ())
    {
      // No modifiers present - return as is
      return aNodes;
    }

    // Ensure all modifiers are instantiated exactly once
    for (final Class <? extends IHCSpecialNodeListModifier> aModifierClass : aModifiersToApply)
    {
      final String sClassName = aModifierClass.getName ();
      if (!s_aModifiers.containsKey (sClassName))
      {
        final IHCSpecialNodeListModifier aModifier = GenericReflection.newInstance (aModifierClass);
        if (aModifier == null)
          LOGGER.error ("Failed to instantiate IHCSpecialNodeListModifier implementation " + aModifierClass);
        s_aModifiers.put (sClassName, aModifier);
      }
    }

    // Apply all modifiers
    ICommonsList <? extends IHCNode> ret = new CommonsArrayList <> (aNodes);
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
   * @return Target list. JS and CSS and other nodes are mixed. Inline JS and
   *         CSS that comes before files, is first. Than come the CSS and JS
   *         external as well as other elements. Finally the inline JS and CSS
   *         nodes to be emitted after the files are contained. So the resulting
   *         order is at it should be except that JS and CSS and other nodes are
   *         mixed.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCNode> getMergedInlineCSSAndJSNodes (@Nonnull final Iterable <? extends IHCNode> aNodes,
                                                                     final boolean bKeepOnDocumentReady)
  {
    // Default to the global "on document ready" provider
    return getMergedInlineCSSAndJSNodes (aNodes, bKeepOnDocumentReady ? HCSettings.getOnDocumentReadyProvider () : null);
  }

  /**
   * Merge all inline CSS and JS elements contained in the source nodes into one
   * script elements
   *
   * @param aNodes
   *        Source list of nodes. May not be <code>null</code>.
   * @param aOnDocumentReadyProvider
   *        if not <code>null</code> than all combined document.ready() scripts
   *        are kept as document.ready() scripts using this provider. If
   *        <code>null</code> than all document.ready() scripts are converted to
   *        regular scripts and are executed after all other scripts. For AJAX
   *        calls, this should be <code>null</code> as there is no "document
   *        ready" callback - alternatively you can provide a custom "on
   *        document ready" provider.
   * @return Target list. JS and CSS and other nodes are mixed. Inline JS and
   *         CSS that comes before files, is first. Than come the CSS and JS
   *         external as well as other elements. Finally the inline JS and CSS
   *         nodes to be emitted after the files are contained. So the resulting
   *         order is at it should be except that JS and CSS and other nodes are
   *         mixed.
   */
  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCNode> getMergedInlineCSSAndJSNodes (@Nonnull final Iterable <? extends IHCNode> aNodes,
                                                                     @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");

    // Apply all modifiers
    final Iterable <? extends IHCNode> aRealSpecialNodes = applyModifiers (aNodes);

    // Do standard aggregations of CSS and JS
    final ICommonsList <IHCNode> ret = new CommonsArrayList <> ();
    final CollectingJSCodeProvider aJSOnDocumentReadyBefore = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSOnDocumentReadyAfter = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSInlineBefore = new CollectingJSCodeProvider ();
    final CollectingJSCodeProvider aJSInlineAfter = new CollectingJSCodeProvider ();
    final InlineCSSList aCSSInlineBefore = new InlineCSSList ();
    final InlineCSSList aCSSInlineAfter = new InlineCSSList ();
    for (final IHCNode aNode : aRealSpecialNodes)
    {
      // Note: do not unwrap the node, because it is not allowed to merge JS/CSS
      // with a conditional comment with JS/CSS without a conditional comment!

      if (HCJSNodeDetector.isDirectJSInlineNode (aNode))
      {
        // Check HCScriptInlineOnDocumentReady first, because it is a subclass
        // of IHCScriptInline
        if (aNode instanceof HCScriptInlineOnDocumentReady)
        {
          // Inline JS
          final HCScriptInlineOnDocumentReady aScript = (HCScriptInlineOnDocumentReady) aNode;
          (aScript.isEmitAfterFiles () ? aJSOnDocumentReadyAfter
                                       : aJSOnDocumentReadyBefore).appendFlattened (aScript.getOnDocumentReadyCode ());
        }
        else
        {
          // Inline JS
          final IHCScriptInline <?> aScript = (IHCScriptInline <?>) aNode;
          (aScript.isEmitAfterFiles () ? aJSInlineAfter : aJSInlineBefore).appendFlattened (aScript.getJSCodeProvider ());
        }
      }
      else
        if (HCCSSNodeDetector.isDirectCSSInlineNode (aNode))
        {
          // Inline CSS
          final HCStyle aStyle = (HCStyle) aNode;
          (aStyle.isEmitAfterFiles () ? aCSSInlineAfter : aCSSInlineBefore).addInlineCSS (aStyle.getMedia (), aStyle.getStyleContent ());
        }
        else
        {
          // HCLink
          // HCScriptFile
          // HCConditionalCommentNode
          if (!(aNode instanceof HCLink) && !(aNode instanceof HCScriptFile) && !(aNode instanceof IHCConditionalCommentNode))
            LOGGER.warn ("Found unexpected node to merge inline CSS/JS: " + aNode);

          // Add always!
          // These nodes are either file based nodes ot conditional comment
          // nodes
          ret.add (aNode);
        }
    }

    // on-document-ready JS always as last inline JS!
    if (!aJSOnDocumentReadyBefore.isEmpty ())
      if (aOnDocumentReadyProvider != null)
        aJSInlineBefore.append (aOnDocumentReadyProvider.createOnDocumentReady (aJSOnDocumentReadyBefore));
      else
        aJSInlineBefore.append (aJSOnDocumentReadyBefore);

    if (!aJSOnDocumentReadyAfter.isEmpty ())
      if (aOnDocumentReadyProvider != null)
        aJSInlineAfter.append (aOnDocumentReadyProvider.createOnDocumentReady (aJSOnDocumentReadyAfter));
      else
        aJSInlineAfter.append (aJSOnDocumentReadyAfter);

    // Finally add the inline JS
    if (!aJSInlineBefore.isEmpty ())
    {
      // Add at the beginning
      final HCScriptInline aScript = new HCScriptInline (aJSInlineBefore).setEmitAfterFiles (false);
      aScript.internalSetNodeState (EHCNodeState.RESOURCES_REGISTERED);
      ret.add (0, aScript);
    }

    if (!aJSInlineAfter.isEmpty ())
    {
      // Add at the end
      final HCScriptInline aScript = new HCScriptInline (aJSInlineAfter).setEmitAfterFiles (true);
      aScript.internalSetNodeState (EHCNodeState.RESOURCES_REGISTERED);
      ret.add (aScript);
    }

    // Add all merged inline CSSs grouped by their media list
    if (aCSSInlineBefore.isNotEmpty ())
    {
      // Add at the beginning
      int nIndex = 0;
      for (final ICSSCodeProvider aEntry : aCSSInlineBefore.getAll ())
      {
        final HCStyle aStyle = new HCStyle (aEntry.getCSSCode ()).setMedia (aEntry.getMediaList ()).setEmitAfterFiles (false);
        aStyle.internalSetNodeState (EHCNodeState.RESOURCES_REGISTERED);
        ret.add (nIndex, aStyle);
        ++nIndex;
      }
    }

    if (aCSSInlineAfter.isNotEmpty ())
    {
      // Add at the end
      for (final ICSSCodeProvider aEntry : aCSSInlineAfter.getAll ())
      {
        final HCStyle aStyle = new HCStyle (aEntry.getCSSCode ()).setMedia (aEntry.getMediaList ()).setEmitAfterFiles (true);
        aStyle.internalSetNodeState (EHCNodeState.RESOURCES_REGISTERED);
        ret.add (aStyle);
      }
    }

    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <IHCNode> extractSpecialNodes (@Nonnull final Iterable <? extends IHCNode> aNodes,
                                                            @Nonnull final AbstractHCSpecialNodes <?> aSpecialNodes)
  {
    ValueEnforcer.notNull (aNodes, "Nodes");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    final ICommonsList <IHCNode> ret = new CommonsArrayList <> ();

    for (final IHCNode aNode : aNodes)
    {
      if (HCCSSNodeDetector.isDirectCSSFileNode (aNode))
      {
        final HCLink aLink = (HCLink) aNode;
        if (aLink.getHref () != null)
        {
          // Use the default charset here :|
          aSpecialNodes.addExternalCSS (aLink.getMedia (), aLink.getHref ().getAsStringWithEncodedParameters ());
        }
      }
      else
        if (HCCSSNodeDetector.isDirectCSSInlineNode (aNode))
        {
          final HCStyle aStyle = (HCStyle) aNode;
          if (aStyle.isEmitAfterFiles ())
            aSpecialNodes.addInlineCSSAfterExternal (aStyle.getMedia (), aStyle.getStyleContent ());
          else
            aSpecialNodes.addInlineCSSBeforeExternal (aStyle.getMedia (), aStyle.getStyleContent ());
        }
        else
          if (HCJSNodeDetector.isDirectJSFileNode (aNode))
          {
            final HCScriptFile aScriptFile = (HCScriptFile) aNode;
            if (aScriptFile.getSrc () != null)
            {
              // Use the default charset here :|
              aSpecialNodes.addExternalJS (aScriptFile.getSrc ().getAsStringWithEncodedParameters ());
            }
          }
          else
            if (HCJSNodeDetector.isDirectJSInlineNode (aNode))
            {
              final IHCScriptInline <?> aScript = (IHCScriptInline <?>) aNode;
              if (aScript.isEmitAfterFiles ())
                aSpecialNodes.addInlineJSAfterExternal (aScript.getJSCodeProvider ());
              else
                aSpecialNodes.addInlineJSBeforeExternal (aScript.getJSCodeProvider ());
            }
            else
            {
              // Neither JS nor CSS node - so maybe a conditional comment node
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
   */
  public static void extractSpecialContent (@Nonnull final IHCNode aNode,
                                            @Nonnull final AbstractHCSpecialNodes <?> aSpecialNodes,
                                            final boolean bKeepOnDocumentReady)
  {
    extractSpecialContent (aNode, aSpecialNodes, bKeepOnDocumentReady ? HCSettings.getOnDocumentReadyProvider () : null);
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
   * @param aOnDocumentReadyProvider
   *        if not <code>null</code> than all combined document.ready() scripts
   *        are kept as document.ready() scripts using this provider. If
   *        <code>null</code> than all document.ready() scripts are converted to
   *        regular scripts and are executed after all other scripts. For AJAX
   *        calls, this should be <code>null</code> as there is no "document
   *        ready" callback - alternatively you can provide a custom "on
   *        document ready" provider.
   */
  public static void extractSpecialContent (@Nonnull final IHCNode aNode,
                                            @Nonnull final AbstractHCSpecialNodes <?> aSpecialNodes,
                                            @Nullable final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.notNull (aSpecialNodes, "SpecialNodes");

    // Extract all out of band nodes from the passed node
    ICommonsList <IHCNode> aExtractedOutOfBandNodes = new CommonsArrayList <> ();
    recursiveExtractAndRemoveOutOfBandNodes (aNode, aExtractedOutOfBandNodes);

    // Merge JS/CSS nodes - replace list content
    aExtractedOutOfBandNodes = getMergedInlineCSSAndJSNodes (aExtractedOutOfBandNodes, aOnDocumentReadyProvider);

    // Extract the special nodes into the provided object
    aExtractedOutOfBandNodes = extractSpecialNodes (aExtractedOutOfBandNodes, aSpecialNodes);

    // Now the aExtractedOutOfBandNodes list must be empty - otherwise we have
    // an internal inconsistency
    if (aExtractedOutOfBandNodes.isNotEmpty ())
      throw new IllegalStateException ("Out-of-band nodes are left after merging and extraction: " + aExtractedOutOfBandNodes);
  }
}
