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
package com.helger.photon.uicore.page.external;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.XMLConstants;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLProtocolRegistry;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.servlet.ServletContextPathHolder;
import com.helger.xml.microdom.IMicroComment;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroQName;
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

  private String m_sServerPrefixPath;
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
    m_sServerPrefixPath = ServletContextPathHolder.getContextPath () + "/";
    m_eHTMLVersion = eHTMLVersion;
    m_sNamespaceURI = eHTMLVersion == null ? null : eHTMLVersion.getNamespaceURI ();
  }

  /**
   * @return The server prefix path to be added to read links. May neither be
   *         <code>null</code> nor empty.
   * @since 7.0.2
   */
  @Nonnull
  @Nonempty
  public String getServerPrefixPath ()
  {
    return m_sServerPrefixPath;
  }

  /**
   * Set the link prefix that will be added to links missing the prefix. If set,
   * must end with a slash ("/")
   *
   * @param sServerPrefixPath
   *        The link prefix to use. May neither be <code>null</code> nor empty
   * @return this for chaining
   * @since 7.0.2
   */
  @Nonnull
  public PageViewExternalHTMLCleanser setServerPrefixPath (@Nonnull @Nonempty final String sServerPrefixPath)
  {
    ValueEnforcer.notEmpty (sServerPrefixPath, "ServerPrefixPath");
    ValueEnforcer.isTrue (sServerPrefixPath.endsWith ("/"), "ServerPrefixPath must end with a slash");
    m_sServerPrefixPath = sServerPrefixPath;
    return this;
  }

  /**
   * @return The HTML version as specified in the constructor. May be
   *         <code>null</code>.
   */
  @Nullable
  public EHTMLVersion getHTMLVersion ()
  {
    return m_eHTMLVersion;
  }

  /**
   * @return The HTML namespace URI from the HTML version specified in the
   *         constructor. May be <code>null</code> if no HTML version was
   *         specified or if the HTML version has no namespace URI.
   */
  @Nullable
  public String getHTMLNamespaceURI ()
  {
    return m_sNamespaceURI;
  }

  /**
   * @return <code>true</code> if all comments will be removed,
   *         <code>false</code> if not. Default is
   *         {@link #DEFAULT_REMOVE_COMMENTS}.
   */
  public boolean isRemoveComments ()
  {
    return m_bRemoveComments;
  }

  /**
   * Change if comments should be removed. By default comments are removed.
   *
   * @param bRemoveComments
   *        <code>true</code> to remove comments, <code>false</code> to leave
   *        them in.
   * @return this for chaining
   */
  @Nonnull
  public PageViewExternalHTMLCleanser setRemoveComments (final boolean bRemoveComments)
  {
    m_bRemoveComments = bRemoveComments;
    return this;
  }

  /**
   * @return <code>true</code> if text content should be cleaned on whitespace
   *         usage, <code>false</code> to not do it. Default is
   *         {@link #DEFAULT_CLEAN_TEXTS}.
   * @since 7.0.2
   */
  public boolean isCleanTexts ()
  {
    return m_bCleanTexts;
  }

  /**
   * Change if texts should be cleaned. By default this happens.
   *
   * @param bCleanTexts
   *        <code>true</code> to clean texts, <code>false</code> to leave them
   *        untouched.
   * @return this for chaining
   * @since 7.0.2
   */
  @Nonnull
  public PageViewExternalHTMLCleanser setCleanTexts (final boolean bCleanTexts)
  {
    m_bCleanTexts = bCleanTexts;
    return this;
  }

  /**
   * Protected method to determine if a link (e.g. from an 'a' or 'img'
   * elements) needs a context path prefix
   *
   * @param sHref
   *        The link HREF to check
   * @return <code>true</code> if the link needs a context path,
   *         <code>false</code> if not.
   */
  @OverrideOnDemand
  protected boolean linkNeedsContextPath (@Nullable final String sHref)
  {
    if (sHref == null)
      return false;

    // If prefix is defined and already present no need to change something
    // Or if the server prefix path is just "/" and the links starts with "/"
    // it's also fine
    if (sHref.startsWith (m_sServerPrefixPath))
      return false;

    // javascript: and mailto: are handled by URLProtocolRegistry
    if (URLProtocolRegistry.getInstance ().hasKnownProtocol (sHref) || sHref.startsWith ("#"))
      return false;

    // Prefix is needed
    return true;
  }

  private static final IMicroQName [] LINK_ATTR_NAMES = new IMicroQName [] { CHTMLAttributes.SRC, CHTMLAttributes.HREF };

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

        // Remove attribute xml:space
        aElement.removeAttribute (XMLConstants.XML_NS_URI, "space");

        // Remove unnecessary attributes
        final String sRowSpan = aElement.getAttributeValue (CHTMLAttributes.ROWSPAN);
        if ("1".equals (sRowSpan))
          aElement.removeAttribute (CHTMLAttributes.ROWSPAN);
        final String sColSpan = aElement.getAttributeValue (CHTMLAttributes.COLSPAN);
        if ("1".equals (sColSpan))
          aElement.removeAttribute (CHTMLAttributes.COLSPAN);

        // Ensure all link targets are server absolute
        for (final IMicroQName aLinkAttrName : LINK_ATTR_NAMES)
        {
          String sPath = aElement.getAttributeValue (aLinkAttrName);
          if (sPath != null)
          {
            if (linkNeedsContextPath (sPath))
            {
              sPath = m_sServerPrefixPath + sPath;
              aElement.setAttribute (aLinkAttrName, sPath);
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
              // Merge 2 blanks to 1
              sTextNodeNew = StringHelper.replaceAllRepeatedly (sTextNodeNew, "  ", " ");
              if (!sTextNode.equals (sTextNodeNew))
                aText.setData (sTextNodeNew);
            }
          }
        }

    return EHierarchyVisitorReturn.CONTINUE;
  }
}
