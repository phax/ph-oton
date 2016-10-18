/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import java.io.Serializable;
import java.util.BitSet;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.io.stream.StreamHelper;
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
public final class DataTablesServerDataCell implements Serializable
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesServerDataCell.class);

  private IHCConversionSettings m_aConversionSettings;
  private IHCNodeList <?> m_aContent;
  private IMicroNode m_aMicroNode;
  private HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();
  private String m_sHTML;
  private String m_sTextContent;

  public DataTablesServerDataCell (@Nonnull final IHCCell <?> aCell, @Nonnull final IHCConversionSettings aCS)
  {
    if (aCell.hasAnyStyle ())
      s_aLogger.warn ("Cell has styles assigned which will be lost: " + aCell.getAllStyles ());
    if (aCell.hasAnyClass ())
      s_aLogger.warn ("Cell has classes assigned which will be lost: " + aCell.getAllClasses ());

    m_aConversionSettings = aCS;
    final IHCNodeList <?> aCellContent = aCell.getAllChildrenAsNodeList ();

    // Remember cell content
    setContent (aCellContent);
  }

  private void writeObject (@Nonnull final ObjectOutputStream out) throws IOException
  {
    out.writeObject (m_aContent);
    out.writeObject (m_aSpecialNodes);
    StreamHelper.writeSafeUTF (out, m_sHTML);
    StreamHelper.writeSafeUTF (out, m_sTextContent);
  }

  private void readObject (@Nonnull final ObjectInputStream in) throws IOException, ClassNotFoundException
  {
    // Always the same CS
    m_aConversionSettings = DataTablesServerData.createConversionSettings ();
    m_aContent = (HCNodeList) in.readObject ();
    m_aSpecialNodes = (HCSpecialNodes) in.readObject ();
    m_sHTML = StreamHelper.readSafeUTF (in);
    m_sTextContent = StreamHelper.readSafeUTF (in);
  }

  public void setContent (@Nonnull final IHCNodeList <?> aCellChildren)
  {
    m_aSpecialNodes.clear ();

    // customize, finalize and extract resources
    HCRenderer.prepareForConversion (aCellChildren, aCellChildren, m_aConversionSettings);

    if (m_aConversionSettings.isExtractOutOfBandNodes ())
    {
      // Add the content without the out-of-band nodes (but no document.ready()
      // because this is invoked per AJAX)
      final IHCOnDocumentReadyProvider aOnDocumentReadyProvider = null;
      HCSpecialNodeHandler.extractSpecialContent (aCellChildren, m_aSpecialNodes, aOnDocumentReadyProvider);
    }

    m_aContent = aCellChildren;
    m_sHTML = null;
    m_sTextContent = null;

    // Convert to HC node to Micro node
    m_aMicroNode = m_aContent.convertToMicroNode (m_aConversionSettings);
    if (m_aMicroNode == null)
    {
      // Avoid later checks
      m_sHTML = "";
      m_sTextContent = "";
    }
  }

  @Nonnull
  public IHCNodeList <?> getContent ()
  {
    return m_aContent;
  }

  @Nullable
  public String getHTMLString ()
  {
    String ret = m_sHTML;
    if (ret == null)
    {
      // Create lazy
      ret = MicroWriter.getNodeAsString (m_aMicroNode, m_aConversionSettings.getXMLWriterSettings ());

      // Avoid multiple calls for non-cached version
      if (ret == null)
        ret = "";
      m_sHTML = ret;
    }
    return ret;
  }

  @Nonnull
  public String getTextContent ()
  {
    String ret = m_sTextContent;
    if (ret == null)
    {
      // Create lazy
      // e.g. for initial sorting
      final IMicroNode aMicroNode = m_aMicroNode;
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
      m_sTextContent = ret;
    }

    return m_sTextContent;
  }

  @Nonnull
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aSpecialNodes;
  }

  public void matchRegEx (@Nonnull final String [] aSearchTexts, @Nonnull final BitSet aMatchingWords)
  {
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (RegExHelper.stringMatchesPattern (sSearchText, m_sTextContent))
        aMatchingWords.set (i);
    }
  }

  public void matchPlainTextCaseSensitive (@Nonnull final String [] aSearchTexts, @Nonnull final BitSet aMatchingWords)
  {
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (StringHelper.contains (m_sTextContent, sSearchText))
        aMatchingWords.set (i);
    }
  }

  public void matchPlainTextIgnoreCase (@Nonnull final String [] aSearchTexts,
                                        @Nonnull final Locale aDisplayLocale,
                                        @Nonnull final BitSet aMatchingWords)
  {
    for (int i = 0; i < aSearchTexts.length; ++i)
    {
      final String sSearchText = aSearchTexts[i];
      if (StringHelper.containsIgnoreCase (m_sTextContent, sSearchText, aDisplayLocale))
        aMatchingWords.set (i);
    }
  }

  @Override
  @Nonnull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("html", m_sHTML).append ("textContent", m_sTextContent).toString ();
  }
}
