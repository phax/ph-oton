/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.ajax;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCNodeList;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.hc.special.HCSpecialNodeHandler;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroNodeWithChildren;
import com.helger.xml.microdom.IMicroText;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * This class holds table cells to be used by the DataTables server side
 * handling.
 *
 * @author Philip Helger
 */
public final class DataTablesServerDataCell
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesServerDataCell.class);

  private IHCNodeList <?> m_aContent;
  // Lazy stuff
  private HCSpecialNodes m_aLazySpecialNodes;
  private IMicroNode m_aLazyMicroNode;
  private String m_sLazyHTML;
  private String m_sLazyTextContent;

  public DataTablesServerDataCell (@Nonnull final IHCCell <?> aCell)
  {
    if (aCell.hasAnyStyle ())
      LOGGER.warn ("Cell has styles assigned which will be lost: " + aCell.getAllStyles ());
    if (aCell.hasAnyClass ())
      LOGGER.warn ("Cell has classes assigned which will be lost: " + aCell.getAllClasses ());

    final IHCNodeList <?> aCellContent = aCell.getAllChildrenAsNodeList ();

    // Remember cell content
    setContent (aCellContent);
  }

  private void writeObject (@Nonnull final ObjectOutputStream out) throws IOException
  {
    out.writeObject (m_aContent);
    out.writeObject (m_aLazySpecialNodes);
  }

  private void readObject (@Nonnull final ObjectInputStream in) throws IOException, ClassNotFoundException
  {
    m_aContent = (HCNodeList) in.readObject ();
    m_aLazySpecialNodes = (HCSpecialNodes) in.readObject ();
  }

  public void setContent (@Nonnull final IHCNodeList <?> aCellChildren)
  {
    if (m_aLazySpecialNodes != null)
      m_aLazySpecialNodes.clear ();

    final IHCConversionSettings aCS = DataTablesServerData.DEFAULT_CONVERSION_SETTINGS;

    // customize, finalize and extract resources
    HCRenderer.prepareForConversion (aCellChildren, aCellChildren, aCS);

    if (aCS.isExtractOutOfBandNodes ())
    {
      if (m_aLazySpecialNodes == null)
        m_aLazySpecialNodes = new HCSpecialNodes ();

      // Add the content without the out-of-band nodes (but no document.ready()
      // because this is invoked per AJAX)
      final IHCOnDocumentReadyProvider aOnDocumentReadyProvider = null;
      HCSpecialNodeHandler.extractSpecialContent (aCellChildren, m_aLazySpecialNodes, aOnDocumentReadyProvider);

      // Free memory if nothing is contained
      if (m_aLazySpecialNodes.isEmpty ())
        m_aLazySpecialNodes = null;
    }

    m_aContent = aCellChildren;
    m_aLazyMicroNode = null;
    m_sLazyHTML = null;
    m_sLazyTextContent = null;
  }

  @Nonnull
  public IHCNodeList <?> getContent ()
  {
    return m_aContent;
  }

  @Nullable
  private IMicroNode _getOrCreateMicroNode ()
  {
    IMicroNode ret = m_aLazyMicroNode;
    if (ret == null)
    {
      // Convert to HC node to Micro node
      ret = m_aContent.convertToMicroNode (DataTablesServerData.DEFAULT_CONVERSION_SETTINGS);
      m_aLazyMicroNode = ret;

      if (ret == null)
      {
        // Avoid later checks
        m_sLazyHTML = "";
        m_sLazyTextContent = "";
      }
    }
    return ret;
  }

  @Nullable
  public String getHTMLString ()
  {
    String ret = m_sLazyHTML;
    if (ret == null)
    {
      // _getMicroNode may change lazy variable
      final IMicroNode aMicroNode = _getOrCreateMicroNode ();
      ret = m_sLazyHTML;

      // Re-check
      if (ret == null)
      {
        assert aMicroNode != null;

        // Create lazy
        ret = MicroWriter.getNodeAsString (aMicroNode,
                                           DataTablesServerData.DEFAULT_CONVERSION_SETTINGS.getXMLWriterSettings ());

        // Avoid multiple calls for non-cached version
        if (ret == null)
          ret = "";
        m_sLazyHTML = ret;
      }
    }
    return ret;
  }

  @Nonnull
  public String getTextContent ()
  {
    String ret = m_sLazyTextContent;
    if (ret == null)
    {
      // _getMicroNode may change lazy variable
      final IMicroNode aMicroNode = _getOrCreateMicroNode ();
      ret = m_sLazyTextContent;

      // Re-check
      if (ret == null)
      {
        assert aMicroNode != null;

        // Create lazy
        // e.g. for initial sorting
        if (aMicroNode instanceof IMicroNodeWithChildren)
          ret = ((IMicroNodeWithChildren) aMicroNode).getTextContent ();
        else
          if (aMicroNode.isText ())
          {
            // ignore whitespace-only content
            if (!((IMicroText) aMicroNode).isElementContentWhitespace ())
              ret = aMicroNode.getNodeValue ();
          }
          else
            if (aMicroNode.isCDATA ())
            {
              ret = aMicroNode.getNodeValue ();
            }

        // Avoid multiple calls for non-cached version
        if (ret == null)
          ret = "";
        m_sLazyTextContent = ret;
      }
    }

    return m_sLazyTextContent;
  }

  @Nullable
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aLazySpecialNodes;
  }

  public void matchRegEx (@Nonnull final String [] aSearchTexts, @Nonnull final BitSet aMatchingWords)
  {
    // Ensure text is resolved
    final String sTextContent = getTextContent ();
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (RegExHelper.stringMatchesPattern (sSearchText, sTextContent))
        aMatchingWords.set (i);
    }
  }

  public void matchPlainTextCaseSensitive (@Nonnull final String [] aSearchTexts, @Nonnull final BitSet aMatchingWords)
  {
    // Ensure text is resolved
    final String sTextContent = getTextContent ();
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (StringHelper.contains (sTextContent, sSearchText))
        aMatchingWords.set (i);
    }
  }

  public void matchPlainTextIgnoreCase (@Nonnull final String [] aSearchTexts,
                                        @Nonnull final Locale aDisplayLocale,
                                        @Nonnull final BitSet aMatchingWords)
  {
    // Ensure text is resolved
    final String sTextContent = getTextContent ();
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (StringHelper.containsIgnoreCase (sTextContent, sSearchText, aDisplayLocale))
        aMatchingWords.set (i);
    }
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Content", m_aContent).getToString ();
  }
}
