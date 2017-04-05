/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.root;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.HCHelper;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.EHCTextDirection;
import com.helger.html.hc.html.metadata.HCCSSNodeDetector;
import com.helger.html.hc.html.metadata.HCHead;
import com.helger.html.hc.html.script.HCJSNodeDetector;
import com.helger.html.hc.html.script.IHCScript;
import com.helger.html.hc.html.script.IHCScriptInline;
import com.helger.html.hc.html.sections.HCBody;
import com.helger.html.hc.special.HCSpecialNodeHandler;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroDocument;

/**
 * The node that represents a full HTML document.
 *
 * @author Philip Helger
 */
public class HCHtml extends AbstractHCElement <HCHtml>
{
  private final HCHead m_aHead;
  private final HCBody m_aBody;

  /**
   * Create a new HTML object
   */
  public HCHtml ()
  {
    this (new HCHead (), new HCBody ());
  }

  public HCHtml (@Nonnull final HCHead aHead, @Nonnull final HCBody aBody)
  {
    super (EHTMLElement.HTML);
    m_aHead = ValueEnforcer.notNull (aHead, "Head");
    m_aBody = ValueEnforcer.notNull (aBody, "Body");

    // Set default direction
    setDirection (EHCTextDirection.LTR);
  }

  @Nonnull
  public final HCHead getHead ()
  {
    return m_aHead;
  }

  @Nonnull
  public final HCBody getBody ()
  {
    return m_aBody;
  }

  @Override
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <? extends IHCNode> getAllChildren ()
  {
    return new CommonsArrayList <> (m_aHead, m_aBody);
  }

  @Override
  @Nullable
  public IHCNode getChildAtIndex (final int nIndex)
  {
    if (nIndex == 0)
      return m_aHead;
    if (nIndex == 1)
      return m_aBody;
    return null;
  }

  @Override
  @Nonnull
  public IHCNode getFirstChild ()
  {
    return m_aHead;
  }

  @Override
  @Nonnull
  public IHCNode getLastChild ()
  {
    return m_aBody;
  }

  @Override
  public boolean hasChildren ()
  {
    return true;
  }

  @Override
  public int getChildCount ()
  {
    return 2;
  }

  @Override
  @Nonnull
  protected final IMicroDocument internalConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final EHTMLVersion eHTMLVersion = aConversionSettings.getHTMLVersion ();

    // Note: we need to clone the doctype, because otherwise the object would
    // already have a parent assigned if "getAsNode" is called more than once!
    final IMicroDocument aDoc = new MicroDocument (eHTMLVersion.getDocType ().getClone ());
    final IMicroElement aRoot = aDoc.appendElement (eHTMLVersion.getNamespaceURI (),
                                                    eHTMLVersion.getDocType ().getQualifiedName ());
    fillMicroElement (aRoot, aConversionSettings);

    // Use the getter, to ensure the elements are not null
    final IMicroNode eBody = m_aBody.convertToMicroNode (aConversionSettings);
    aRoot.appendChild (eBody);

    // Create head after body but insert it before the body
    final IMicroNode eHead = m_aHead.convertToMicroNode (aConversionSettings);
    aRoot.insertAtIndex (0, eHead);

    // Done!
    return aDoc;
  }

  /**
   * Extract all out-of-band (OOB) nodes from head and body. Afterwards merge
   * all inline CSS and JS nodes.
   *
   * @return An ordered list of all OOB nodes in the correct order.
   */
  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IHCNode> getAllOutOfBandNodesWithMergedInlineNodes ()
  {
    final ICommonsList <IHCNode> aAllOOBNodes = new CommonsArrayList <> ();
    // Add all existing JS and CSS nodes from the head, as they are known to be
    // out-of-band
    m_aHead.getAllAndRemoveAllJSNodes (aAllOOBNodes);
    m_aHead.getAllAndRemoveAllCSSNodes (aAllOOBNodes);

    // Extract all out-of-band nodes from the body
    HCSpecialNodeHandler.recursiveExtractAndRemoveOutOfBandNodes (m_aBody, aAllOOBNodes);

    // First merge all JS and CSS nodes (and keep document.ready() as it is)
    final boolean bKeepOnDocumentReady = true;
    return HCSpecialNodeHandler.getMergedInlineCSSAndJSNodes (aAllOOBNodes, bKeepOnDocumentReady);
  }

  /**
   * Add the passed OOB nodes to the head.
   *
   * @param aAllOOBNodes
   *        The out-of-band node list. Usually retrieved from
   *        {@link #getAllOutOfBandNodesWithMergedInlineNodes()}. May not be
   *        <code>null</code>.
   */
  public void addAllOutOfBandNodesToHead (@Nonnull final List <IHCNode> aAllOOBNodes)
  {
    ValueEnforcer.notNull (aAllOOBNodes, "AllOOBNodes");
    // And now add all to head in the correct order
    for (final IHCNode aNode : aAllOOBNodes)
    {
      final IHCNode aUnwrappedNode = HCHelper.getUnwrappedNode (aNode);

      // Node for the body?
      if (HCJSNodeDetector.isDirectJSNode (aUnwrappedNode))
        m_aHead.addJS (aNode);
      else
        if (HCCSSNodeDetector.isDirectCSSNode (aUnwrappedNode))
          m_aHead.addCSS (aNode);
        else
          throw new IllegalStateException ("Found illegal out-of-band head node: " + aNode);
    }
  }

  /**
   * Move all JS nodes from the head to the body.
   */
  public void moveScriptElementsToBody ()
  {
    // Move all JS from head to body
    final ICommonsList <IHCNode> aJSNodes = new CommonsArrayList <> ();
    m_aHead.getAllAndRemoveAllJSNodes (aJSNodes);

    // Find index of first script in body
    int nFirstScriptIndex = 0;
    if (m_aBody.hasChildren ())
      for (final IHCNode aChild : m_aBody.getAllChildren ())
      {
        if (aChild instanceof IHCScript <?>)
        {
          // Check if this is a special inline script to be emitted before files
          final boolean bIsInlineBeforeFiles = (aChild instanceof IHCScriptInline <?>) &&
                                               !((IHCScriptInline <?>) aChild).isEmitAfterFiles ();
          if (!bIsInlineBeforeFiles)
          {
            // Remember index to insert before
            break;
          }
        }
        nFirstScriptIndex++;
      }

    m_aBody.addChildrenAt (nFirstScriptIndex, aJSNodes);
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return StringHelper.getConcatenatedOnDemand (m_aHead.getPlainText (), " ", m_aBody.getPlainText ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("head", m_aHead)
                            .appendIfNotNull ("body", m_aBody)
                            .getToString ();
  }
}
