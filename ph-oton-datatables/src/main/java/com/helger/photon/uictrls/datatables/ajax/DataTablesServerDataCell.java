/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.util.BitSet;
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.cache.regex.RegExHelper;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCNodeList;
import com.helger.html.hc.config.IHCOnDocumentReadyProvider;
import com.helger.html.hc.html.tabular.IHCCell;
import com.helger.html.hc.render.HCRenderer;
import com.helger.html.hc.special.HCSpecialNodeHandler;
import com.helger.html.hc.special.HCSpecialNodes;
import com.helger.html.hc.special.IHCSpecialNodes;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroNodeWithChildren;
import com.helger.xml.microdom.IMicroText;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * This class holds table cells to be used by the DataTables server side handling.
 *
 * @author Philip Helger
 */
public final class DataTablesServerDataCell
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DataTablesServerDataCell.class);

  // Cells are read concurrently under the shared read lock of DataTablesServerData, while the lazy
  // values are created on first access
  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private IHCNodeList <?> m_aContent;
  // Lazy stuff
  private HCSpecialNodes m_aLazySpecialNodes;
  @GuardedBy ("m_aRWLock")
  private String m_sLazyHTML;
  @GuardedBy ("m_aRWLock")
  private String m_sLazyTextContent;

  public DataTablesServerDataCell (@NonNull final IHCCell <?> aCell)
  {
    if (aCell.hasAnyStyle ())
      LOGGER.warn ("Cell has styles assigned which will be lost: " + aCell.getAllStyles ());
    if (aCell.hasAnyClass ())
      LOGGER.warn ("Cell has classes assigned which will be lost: " + aCell.getAllClasses ());

    final IHCNodeList <?> aCellContent = aCell.getAllChildrenAsNodeList ();

    // Remember cell content
    _setContent (aCellContent);
  }

  private void _setContent (@NonNull final IHCNodeList <?> aCellChildren)
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
    m_sLazyHTML = null;
    m_sLazyTextContent = null;
  }

  @Nullable
  private IMicroNode _createMicroNode ()
  {
    // The micro node is deliberately not cached. It is only an intermediate representation to
    // derive the HTML and the text content from, and keeping one per cell would multiply the
    // memory consumption of the whole table
    final IHCNodeList <?> aContent = m_aContent;
    if (aContent == null)
    {
      // The content was already released, because both derived values are present
      return null;
    }
    return aContent.convertToMicroNode (DataTablesServerData.DEFAULT_CONVERSION_SETTINGS);
  }

  private void _releaseContentIfPossible ()
  {
    // Once both derived values are present, the HC node tree is not needed any more
    if (m_sLazyHTML != null && m_sLazyTextContent != null)
      m_aContent = null;
  }

  @NonNull
  private String _createHTMLString ()
  {
    String ret = null;
    final IMicroNode aMicroNode = _createMicroNode ();
    if (aMicroNode != null)
      ret = MicroWriter.getNodeAsString (aMicroNode,
                                         DataTablesServerData.DEFAULT_CONVERSION_SETTINGS.getXMLWriterSettings ());

    // Avoid multiple calls for non-cached version
    return ret == null ? "" : ret;
  }

  @NonNull
  private String _createTextContent ()
  {
    String ret = null;
    final IMicroNode aMicroNode = _createMicroNode ();
    if (aMicroNode instanceof final IMicroNodeWithChildren aNodeWithChildren)
      ret = aNodeWithChildren.getTextContent ();
    else
      if (aMicroNode != null && aMicroNode.isText ())
      {
        // ignore whitespace-only content
        if (!((IMicroText) aMicroNode).isElementContentWhitespace ())
          ret = aMicroNode.getNodeValue ();
      }
      else
        if (aMicroNode != null && aMicroNode.isCDATA ())
          ret = aMicroNode.getNodeValue ();

    // Avoid multiple calls for non-cached version
    return ret == null ? "" : ret;
  }

  /**
   * @return The cell content as an HTML string. Never <code>null</code> but maybe empty. The value
   *         is created on first access and cached afterwards.
   */
  @NonNull
  public String getHTMLString ()
  {
    final String ret = m_aRWLock.readLockedGet (() -> m_sLazyHTML);
    if (ret != null)
      return ret;

    return m_aRWLock.writeLockedGet (() -> {
      // Check again, because another thread may have created it in the meantime
      if (m_sLazyHTML == null)
      {
        m_sLazyHTML = _createHTMLString ();
        _releaseContentIfPossible ();
      }
      return m_sLazyHTML;
    });
  }

  /**
   * @return The plain text content of the cell, e.g. for sorting and searching. Never
   *         <code>null</code> but maybe empty. The value is created on first access and cached
   *         afterwards.
   */
  @NonNull
  public String getTextContent ()
  {
    final String ret = m_aRWLock.readLockedGet (() -> m_sLazyTextContent);
    if (ret != null)
      return ret;

    return m_aRWLock.writeLockedGet (() -> {
      // Check again, because another thread may have created it in the meantime
      if (m_sLazyTextContent == null)
      {
        m_sLazyTextContent = _createTextContent ();
        _releaseContentIfPossible ();
      }
      return m_sLazyTextContent;
    });
  }

  @Nullable
  public IHCSpecialNodes getSpecialNodes ()
  {
    return m_aLazySpecialNodes;
  }

  public void matchRegEx (@NonNull final String [] aSearchTexts, @NonNull final BitSet aMatchingWords)
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

  public void matchPlainTextCaseSensitive (@NonNull final String [] aSearchTexts, @NonNull final BitSet aMatchingWords)
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

  public void matchPlainTextIgnoreCase (@NonNull final String [] aSearchTexts,
                                        @NonNull final Locale aDisplayLocale,
                                        @NonNull final BitSet aMatchingWords)
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
  @NonNull
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Content", m_aRWLock.readLockedGet (() -> m_aContent)).getToString ();
  }
}
