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
package com.helger.photon.uicore.page.external;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLProtocolRegistry;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.servlet.ServletContextPathHolder;
import com.helger.xml.CXML;
import com.helger.xml.microdom.IMicroComment;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroText;

/**
 * Perform a standard cleansing on externally parsed HTML content. This
 * includes:
 * <ul>
 * <li>Setting the correct namespace as provided in the constructor</li>
 * <li>Removing any read <code>xml:space</code> attributes</li>
 * <li>Ensure that only valid elements are self-closed
 * (<code>&lt;tag .../&gt;</code>) - all other documents will get an empty text
 * node so that they are rendered as
 * <code>&lt;tag ...&gt;&lt;/tag&gt;</code></li>
 * </ul>
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class PageViewExternalHTMLCleanser extends DefaultHierarchyVisitorCallback <IMicroNode>
{
  public static final boolean DEFAULT_REMOVE_COMMENTS = true;
  public static final boolean DEFAULT_CLEAN_TEXTS = true;

  private final String m_sServerPrefix;
  private final EHTMLVersion m_eHTMLVersion;
  private final String m_sNamespaceURI;
  private boolean m_bRemoveComments = DEFAULT_REMOVE_COMMENTS;
  private boolean m_bCleanTexts = DEFAULT_CLEAN_TEXTS;

  public PageViewExternalHTMLCleanser ()
  {
    this (null);
  }

  public PageViewExternalHTMLCleanser (@Nullable final EHTMLVersion eHTMLVersion)
  {
    // Cache once
    m_sServerPrefix = ServletContextPathHolder.getContextPath () + "/";
    m_eHTMLVersion = eHTMLVersion;
    m_sNamespaceURI = eHTMLVersion == null ? null : eHTMLVersion.getNamespaceURI ();
  }

  @Nullable
  public EHTMLVersion getHTMLVersion ()
  {
    return m_eHTMLVersion;
  }

  @Nullable
  public String getHTMLNamespaceURI ()
  {
    return m_sNamespaceURI;
  }

  public boolean isRemoveComments ()
  {
    return m_bRemoveComments;
  }

  @Nonnull
  public PageViewExternalHTMLCleanser setRemoveComments (final boolean bRemoveComments)
  {
    m_bRemoveComments = bRemoveComments;
    return this;
  }

  public boolean isCleanTexts ()
  {
    return m_bCleanTexts;
  }

  @Nonnull
  public PageViewExternalHTMLCleanser setCleanTexts (final boolean bCleanTexts)
  {
    m_bCleanTexts = bCleanTexts;
    return this;
  }

  @OverrideOnDemand
  protected boolean linkNeedsContextPath (@Nullable final String sHref)
  {
    // javascript: and mailto: are handled by URLProtocolRegistry
    return sHref != null && !URLProtocolRegistry.getInstance ().hasKnownProtocol (sHref) && !sHref.startsWith ("#");
  }

  @Override
  public EHierarchyVisitorReturn onItemBeforeChildren (final IMicroNode aItem)
  {
    if (aItem instanceof IMicroComment)
    {
      if (m_bRemoveComments)
      {
        // Remove all comments
        aItem.getParent ().removeChild (aItem);
      }
    }
    else
      if (aItem instanceof IMicroElement)
      {
        final IMicroElement aElement = (IMicroElement) aItem;

        // Ensure the correct namespace is present
        // Remove namespace URI for HTML5 usage (set to null)
        aElement.setNamespaceURI (m_sNamespaceURI);

        // Remove this attribute
        aElement.removeAttribute (CXML.XML_NS_XML, "space");

        // Remove unnecessary attributes
        final String sRowSpan = aElement.getAttributeValue (CHTMLAttributes.ROWSPAN);
        if ("1".equals (sRowSpan))
          aElement.removeAttribute (CHTMLAttributes.ROWSPAN);
        final String sColSpan = aElement.getAttributeValue (CHTMLAttributes.COLSPAN);
        if ("1".equals (sColSpan))
          aElement.removeAttribute (CHTMLAttributes.COLSPAN);

        // Ensure all link targets are server absolute
        for (final String sLinkAttrName : new String [] { CHTMLAttributes.SRC, CHTMLAttributes.HREF })
        {
          String sPath = aElement.getAttributeValue (sLinkAttrName);
          if (sPath != null)
          {
            if (linkNeedsContextPath (sPath))
            {
              sPath = m_sServerPrefix + sPath;
              aElement.setAttribute (sLinkAttrName, sPath);
            }
          }
        }

        // Remove deprecated shape attribute
        if (EHTMLElement.A.getElementName ().equalsIgnoreCase (aElement.getTagName ()))
          aElement.removeAttribute (CHTMLAttributes.SHAPE);

        if (EHTMLElement.isTagThatMayNotBeSelfClosed (aElement.getTagName ()) && aElement.getChildCount () == 0)
        {
          // Avoid self-closed tag (e.g. <a> or <span>)
          aElement.appendText ("");
        }
      }
      else
        if (aItem instanceof IMicroText)
        {
          if (m_bCleanTexts)
          {
            final IMicroText aText = (IMicroText) aItem;
            final String sTextNode = aText.getNodeValue ();
            if (StringHelper.hasText (sTextNode))
            {
              // Remove unnecessary whitespaces
              String sTextNodeNew = sTextNode;
              sTextNodeNew = StringHelper.replaceAll (sTextNodeNew, "\r\n", " ");
              sTextNodeNew = StringHelper.replaceAll (sTextNodeNew, "\r", "");
              sTextNodeNew = StringHelper.replaceAll (sTextNodeNew, '\n', ' ');
              sTextNodeNew = StringHelper.replaceAll (sTextNodeNew, '\t', ' ');
              sTextNodeNew = StringHelper.replaceAllRepeatedly (sTextNodeNew, "  ", " ");
              if (!sTextNode.equals (sTextNodeNew))
                aText.setData (sTextNodeNew);
            }
          }
        }

    return EHierarchyVisitorReturn.CONTINUE;
  }
}
