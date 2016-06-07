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

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hierarchy.visit.DefaultHierarchyVisitorCallback;
import com.helger.commons.hierarchy.visit.EHierarchyVisitorReturn;
import com.helger.html.EHTMLElement;
import com.helger.html.EHTMLVersion;
import com.helger.xml.CXML;
import com.helger.xml.microdom.IMicroComment;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;

/**
 * Perform a standard cleansing on externally parsed HTML content. This
 * includes:
 * <ul>
 * <li>Setting the correct namespace as provided in the constructor</li>
 * <li>Removing any read <code>xml:space</code> attributes</li>
 * <li>Ensure that only valid elements are self-closed (
 * <code>&lt;.../&gt;</code>)</li>
 * </ul>
 *
 * @author Philip Helger
 */
public class PageViewExternalHTMLCleanser extends DefaultHierarchyVisitorCallback <IMicroNode>
{
  private final EHTMLVersion m_eHTMLVersion;
  private final String m_sNamespaceURI;
  private boolean m_bRemoveComments = true;

  public PageViewExternalHTMLCleanser (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    m_eHTMLVersion = ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");
    m_sNamespaceURI = eHTMLVersion.getNamespaceURI ();
  }

  @Nonnull
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

  @Override
  public EHierarchyVisitorReturn onItemBeforeChildren (final IMicroNode aItem)
  {
    if (aItem instanceof IMicroComment)
    {
      if (isRemoveComments ())
      {
        // Remove all comments
        final IMicroComment e = (IMicroComment) aItem;
        e.getParent ().removeChild (e);
      }
    }
    else
      if (aItem instanceof IMicroElement)
      {
        final IMicroElement e = (IMicroElement) aItem;

        // Ensure the correct namespace is present
        e.setNamespaceURI (m_sNamespaceURI);

        // Remove this attribute
        e.removeAttribute (CXML.XML_NS_XML, "space");

        if (EHTMLElement.isTagThatMayNotBeSelfClosed (e.getTagName ()) && e.getChildCount () == 0)
        {
          // Avoid self-closed tag (e.g. <a> or <span>)
          e.appendText ("");
        }
      }
    return EHierarchyVisitorReturn.CONTINUE;
  }
}
