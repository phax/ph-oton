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
package com.helger.html.hc.html;

import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.impl.MicroDocument;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.IHCNodeWithChildren;
import com.helger.html.hc.api.EHCTextDirection;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.AbstractHCElement;
import com.helger.html.hc.utils.HCSpecialNodeHandler;

/**
 * The node that represents a full HTML document.
 *
 * @author Philip Helger
 */
public class HCHtml extends AbstractHCElement <HCHtml>
{
  private HCHead m_aHead;
  private HCBody m_aBody;

  /**
   * Create a new HTML object
   */
  public HCHtml ()
  {
    super (EHTMLElement.HTML);

    // Set default direction
    setDirection (EHCTextDirection.LTR);
  }

  /**
   * Overwrite this method to create a custom {@link HCHead} implementation
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected HCHead createHead ()
  {
    return new HCHead ();
  }

  /**
   * Overwrite this method to create a custom {@link HCBody} implementation
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected HCBody createBody ()
  {
    return new HCBody ();
  }

  @Nonnull
  public final HCHead getHead ()
  {
    if (m_aHead == null)
    {
      m_aHead = createHead ();
      if (m_aHead == null)
        throw new IllegalStateException ("Created HCHead is null!");
    }
    return m_aHead;
  }

  @Nonnull
  public final HCBody getBody ()
  {
    if (m_aBody == null)
    {
      m_aBody = createBody ();
      if (m_aBody == null)
        throw new IllegalStateException ("Created HCBody is null!");
    }
    return m_aBody;
  }

  public static void extractAndHandleOutOfBandNodes (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                                     @Nonnull final IHCNodeWithChildren <?> aBaseNode,
                                                     @Nonnull final HCHead aHead,
                                                     @Nonnull final HCBody aBody)
  {
    ValueEnforcer.notNull (aConversionSettings, "ConversionSettings");

    if (aConversionSettings.isExtractOutOfBandNodes ())
    {
      // Extract all out-of-band nodes
      final List <IHCNode> aExtractedOutOfBandNodes = HCSpecialNodeHandler.recursiveExtractAndRemoveOutOfBandNodes (aBaseNode);

      // Call out-of-band node handler
      aConversionSettings.getCustomizer ().handleOutOfBandNodes (aExtractedOutOfBandNodes, aHead, aBody);
    }
  }

  @Override
  protected void internalBeforeConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final HCHead aHead = getHead ();
    final HCBody aBody = getBody ();

    // Customize element, before extracting out-of-band nodes, in case the
    // customizer adds some out-of-band nodes as well
    HCUtils.customizeNodes (aBody, aConversionSettings);

    // Prepare head and body before performing the OOB extraction
    aHead.beforeConvertToNode (aConversionSettings);
    aBody.beforeConvertToNode (aConversionSettings);

    // Extract all out-of-band nodes
    extractAndHandleOutOfBandNodes (aConversionSettings, aBody, aHead, aBody);
  }

  @Override
  @Nonnull
  protected final IMicroDocument internalConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    final EHTMLVersion eHTMLVersion = aConversionSettings.getHTMLVersion ();

    // Note: we need to clone the doctype, because otherwise the object would
    // already have a parent assigned if "getAsNode" is called more than once!
    final IMicroDocument aDoc = new MicroDocument (eHTMLVersion.getDocType ().getClone ());
    final IMicroElement aRoot = aDoc.appendElement (eHTMLVersion.getNamespaceURI (), eHTMLVersion.getDocType ()
                                                                                                 .getQualifiedName ());
    applyProperties (aRoot, aConversionSettings);

    // Use the getter, to ensure the elements are not null
    final IMicroNode eBody = getBody ().convertToNode (aConversionSettings);
    aRoot.appendChild (eBody);

    // Create head after body but insert it before the body
    final IMicroNode eHead = getHead ().convertToNode (aConversionSettings);
    aRoot.insertAtIndex (0, eHead);

    // Done!
    return aDoc;
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    return StringHelper.getConcatenatedOnDemand (getHead ().getPlainText (), " ", getBody ().getPlainText ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("head", m_aHead)
                            .appendIfNotNull ("body", m_aBody)
                            .toString ();
  }
}
