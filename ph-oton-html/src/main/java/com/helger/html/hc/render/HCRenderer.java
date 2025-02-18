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
package com.helger.html.hc.render;

import java.io.OutputStream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.VisibleForTesting;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.lang.GenericReflection;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCIteratorNonBreakableCallback;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.root.HCHtml;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.serialize.MicroWriter;

@Immutable
public final class HCRenderer
{
  private HCRenderer ()
  {}

  /**
   * Prepare and return a single node.
   *
   * @param aNode
   *        Node to be prepared.
   * @param aTargetNode
   *        Target node for additional nodes.
   * @param aConversionSettings
   *        Conversion settings to be used.
   * @return The passed in node.
   * @param <T>
   *        Input and return node type
   */
  @Nonnull
  public static <T extends IHCNode> T getPreparedNode (@Nonnull final T aNode,
                                                       @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode,
                                                       @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Run the global customizer
    aNode.customizeNode (aConversionSettings.getCustomizer (), aConversionSettings.getHTMLVersion (), aTargetNode);

    // finalize the node
    aNode.finalizeNodeState (aConversionSettings, aTargetNode);

    // Consistency check
    aNode.consistencyCheck (aConversionSettings);

    // No forced registration here
    final boolean bForcedResourceRegistration = false;
    aNode.registerExternalResources (aConversionSettings, bForcedResourceRegistration);

    return aNode;
  }

  public static void prepareHtmlForConversion (@Nonnull final HCHtml aHtml,
                                               @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // customize, finalize and extract resources
    prepareForConversion (aHtml, aHtml.body (), aConversionSettings);

    // Extract all out-of-band nodes into the body
    if (aConversionSettings.isExtractOutOfBandNodes ())
    {
      final ICommonsList <IHCNode> aOOBNodes = aHtml.getAllOutOfBandNodesWithMergedInlineNodes ();
      aHtml.addAllOutOfBandNodesToHead (aOOBNodes);
    }

    // This would be the correct place to perform aggregation of CSS and JS
    // nodes (before they are potentially moved)

    // Move scripts to body?
    if (HCSettings.isScriptsInBody ())
      aHtml.moveScriptElementsToBody ();
  }

  /**
   * Customize the passed base node and all child nodes recursively.
   *
   * @param aStartNode
   *        Base node to start customizing (incl.). May not be <code>null</code>
   *        .
   * @param aGlobalTargetNode
   *        The target node where new nodes should be appended to in case the
   *        direct parent node is not suitable. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to use. May not be <code>null</code>.
   */
  public static void prepareForConversion (@Nonnull final IHCNode aStartNode,
                                           @Nonnull final IHCHasChildrenMutable <?, ? super IHCNode> aGlobalTargetNode,
                                           @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    ValueEnforcer.notNull (aStartNode, "NodeToBeCustomized");
    ValueEnforcer.notNull (aGlobalTargetNode, "TargetNode");
    ValueEnforcer.notNull (aConversionSettings, "ConversionSettings");

    final IHCCustomizer aCustomizer = aConversionSettings.getCustomizer ();
    final EHTMLVersion eHTMLVersion = aConversionSettings.getHTMLVersion ();

    // Customize all elements before extracting out-of-band nodes, in case the
    // customizer adds some out-of-band nodes as well
    // Than finalize and register external resources
    final IHCIteratorNonBreakableCallback aCB = (aParentNode, aChildNode) -> {
      // If the parent node is suitable, use it, else use the global target
      // node
      IHCHasChildrenMutable <?, ? super IHCNode> aRealTargetNode;
      if (aParentNode instanceof IHCHasChildrenMutable <?, ?>)
      {
        // Unchecked conversion
        aRealTargetNode = GenericReflection.uncheckedCast (aParentNode);
      }
      else
        aRealTargetNode = aGlobalTargetNode;

      final int nTargetNodeChildren = aRealTargetNode.getChildCount ();

      // Run the global customizer
      aChildNode.customizeNode (aCustomizer, eHTMLVersion, aRealTargetNode);

      // finalize the node
      aChildNode.finalizeNodeState (aConversionSettings, aRealTargetNode);

      // Consistency check
      aChildNode.consistencyCheck (aConversionSettings);

      // No forced registration here
      final boolean bForcedResourceRegistration = false;
      aChildNode.registerExternalResources (aConversionSettings, bForcedResourceRegistration);

      // Something was added?
      if (aRealTargetNode.getChildCount () > nTargetNodeChildren)
      {
        // Recursive call on the target node only.
        // It's important to scan the whole tree, as a hierarchy of nodes may
        // have been added!
        prepareForConversion (aRealTargetNode, aRealTargetNode, aConversionSettings);
      }
    };
    HCHelper.iterateTreeNonBreakable (aStartNode, aCB);
  }

  /**
   * Convert the passed HC node to a micro node using the default conversion
   * settings.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNode aHCNode)
  {
    return getAsNode (aHCNode, HCSettings.getConversionSettings ());
  }

  /**
   * Convert the passed HC node to a micro node using the provided conversion
   * settings.
   *
   * @param aSrcNode
   *        The node to be converted. May not be <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The fully created HTML node
   */
  @SuppressWarnings ("unchecked")
  @Nullable
  public static IMicroNode getAsNode (@Nonnull final IHCNode aSrcNode,
                                      @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    IHCNode aConvertNode = aSrcNode;

    // Special case for HCHtml - must have been done separately because the
    // extraction of the OOB nodes must happen before the HTML HEAD is filled
    if (!(aSrcNode instanceof HCHtml))
    {
      // Determine the target node to use
      final boolean bSrcNodeCanHaveChildren = aSrcNode instanceof IHCHasChildrenMutable <?, ?>;
      final IHCHasChildrenMutable <?, IHCNode> aTempNode;
      if (bSrcNodeCanHaveChildren)
      {
        // Passed node can handle it
        aTempNode = (IHCHasChildrenMutable <?, IHCNode>) aSrcNode;
      }
      else
      {
        aTempNode = new HCNodeList ();
        aTempNode.addChild (aSrcNode);
      }

      // customize, finalize and extract resources
      prepareForConversion (aTempNode, aTempNode, aConversionSettings);

      // NOTE: no OOB extraction here, because it is unclear what would happen
      // to the nodes.

      // Select node to convert to MicroDOM - if something was extracted, use
      // the temp node
      if (!bSrcNodeCanHaveChildren && aTempNode.getChildCount () > 1)
        aConvertNode = aTempNode;
    }

    return aConvertNode.convertToMicroNode (aConversionSettings);
  }

  /**
   * Convert the passed HC node to an HTML string using the default conversion
   * settings.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The node as XML with or without indentation.
   */
  @Nonnull
  @VisibleForTesting
  public static String getAsHTMLString (@Nonnull final IHCNode aHCNode)
  {
    return getAsHTMLString (aHCNode, HCSettings.getConversionSettings ());
  }

  /**
   * Convert the passed node to it's HTML representation. First this HC-node is
   * converted to a micro node, which is than
   *
   * @param aHCNode
   *        The HC node to be converted to an HTML string. May not be
   *        <code>null</code>.
   * @param aConversionSettings
   *        The conversion settings to be used. May not be <code>null</code>.
   * @return The node as HTML string. Never <code>null</code>.
   */
  @Nonnull
  public static String getAsHTMLString (@Nonnull final IHCNode aHCNode,
                                        @Nonnull final IHCConversionSettings aConversionSettings)
  {
    final IMicroNode aMicroNode = getAsNode (aHCNode, aConversionSettings);
    if (aMicroNode == null)
      return "";

    return MicroWriter.getNodeAsString (aMicroNode, aConversionSettings.getXMLWriterSettings ());
  }

  /**
   * Convert the passed HC node to an HTML string without namespaces.
   *
   * @param aHCNode
   *        The node to be converted. May not be <code>null</code>.
   * @return The node as XML with or without indentation.
   */
  @Nonnull
  public static String getAsHTMLStringWithoutNamespaces (@Nonnull final IHCNode aHCNode)
  {
    // Only works, if no nonce is needed
    return getAsHTMLString (aHCNode, HCSettings.getConversionSettingsWithoutNamespaces ());
  }

  @Deprecated (forRemoval = true, since = "9.2.10")
  public static void writeHtmlTo (@Nonnull final IHCNode aHCNode, @Nonnull final OutputStream aOS)
  {
    writeHtmlTo (aHCNode, HCSettings.getConversionSettings (), aOS);
  }

  public static void writeHtmlTo (@Nonnull final IHCNode aHCNode,
                                  @Nonnull final IHCConversionSettings aConversionSettings,
                                  @Nonnull @WillClose final OutputStream aOS)
  {
    try
    {
      final IMicroNode aMicroNode = getAsNode (aHCNode, aConversionSettings);
      if (aMicroNode != null)
        MicroWriter.writeToStream (aMicroNode, aOS, aConversionSettings.getXMLWriterSettings ());
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }
}
