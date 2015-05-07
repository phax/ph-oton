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

import com.helger.commons.microdom.IMicroNode;
import com.helger.commons.microdom.IMicroNodeWithChildren;
import com.helger.commons.microdom.IMicroText;
import com.helger.commons.microdom.serialize.MicroWriter;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCCell;
import com.helger.html.hc.conversion.HCSettings;
import com.helger.html.hc.conversion.IHCConversionSettings;
import com.helger.html.hc.htmlext.HCUtils;
import com.helger.html.hc.impl.HCNodeList;
import com.helger.html.hc.utils.HCSpecialNodeHandler;
import com.helger.html.hc.utils.HCSpecialNodes;
import com.helger.html.hc.utils.IHCSpecialNodes;

/**
 * This class holds table cells to be used by the DataTables server side
 * handling.
 *
 * @author Philip Helger
 */
public final class DataTablesServerDataCell implements Serializable
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DataTablesServerDataCell.class);

  private IHCConversionSettings m_aCS;
  private HCNodeList m_aContent;
  private HCSpecialNodes m_aSpecialNodes = new HCSpecialNodes ();
  private String m_sHTML;
  private String m_sTextContent;

  public DataTablesServerDataCell (@Nonnull final IHCCell <?> aCell, @Nonnull final IHCConversionSettings aCS)
  {
    if (aCell.hasAnyStyle ())
      s_aLogger.warn ("Cell has styles assigned which will be lost: " + aCell.getAllStyles ());
    if (aCell.hasAnyClass ())
      s_aLogger.warn ("Cell has classes assigned which will be lost: " + aCell.getAllClasses ());

    m_aCS = aCS;
    final HCNodeList aCellContent = aCell.getAllChildrenAsNodeList ();

    // Remember cell content
    setContent (aCellContent);
  }

  private void writeObject (@Nonnull final ObjectOutputStream out) throws IOException
  {
    out.writeObject (m_aContent);
    out.writeObject (m_aSpecialNodes);
    out.writeUTF (m_sHTML);
    out.writeUTF (m_sTextContent);
  }

  private void readObject (@Nonnull final ObjectInputStream in) throws IOException, ClassNotFoundException
  {
    // Always the same CS
    m_aCS = DataTablesServerData.createConversionSettings ();
    m_aContent = (HCNodeList) in.readObject ();
    m_aSpecialNodes = (HCSpecialNodes) in.readObject ();
    m_sHTML = in.readUTF ();
    m_sTextContent = in.readUTF ();
  }

  public void setContent (@Nonnull final HCNodeList aCellChildren)
  {
    m_aSpecialNodes.clear ();

    // Add the content without the out-of-band nodes (but no document.ready()
    // because this is invoked per AJAX)
    m_aContent = HCSpecialNodeHandler.extractSpecialContent (aCellChildren, m_aSpecialNodes, false);

    // Finally customize all nodes
    HCUtils.customizeNodes (m_aContent, HCSettings.getConversionSettings ());

    // Convert to IMicroNode and to String
    final IMicroNode aNode = m_aContent.convertToNode (m_aCS);
    if (aNode == null)
    {
      m_sHTML = "";
      m_sTextContent = null;
    }
    else
    {
      m_sHTML = MicroWriter.getNodeAsString (aNode, m_aCS.getXMLWriterSettings ());

      if (aNode instanceof IMicroNodeWithChildren)
        m_sTextContent = ((IMicroNodeWithChildren) aNode).getTextContent ();
      else
        if (aNode.isText ())
        {
          // ignore whitespace-only content
          if (!((IMicroText) aNode).isElementContentWhitespace ())
            m_sTextContent = aNode.getNodeValue ();
          else
            m_sTextContent = null;
        }
        else
          if (aNode.isCDATA ())
          {
            m_sTextContent = aNode.getNodeValue ();
          }
          else
            m_sTextContent = null;
    }
  }

  @Nonnull
  public HCNodeList getContent ()
  {
    return m_aContent;
  }

  @Nullable
  public String getHTML ()
  {
    return m_sHTML;
  }

  @Nonnull
  public String getTextContent ()
  {
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
