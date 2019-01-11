/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.xml.microdom.IMicroDocumentType;

/**
 * Enum of supported HTML versions.
 *
 * @author Philip Helger
 */
public enum EHTMLVersion
{
  XHTML10_STRICT (false)
  {
    @Override
    @Nonnull
    public IMicroDocumentType getDocType ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML10_STRICT;
    }

    @Override
    @Nonnull
    public String getNamespaceURI ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML_URI;
    }
  },
  XHTML10_TRANSITIONAL (false)
  {
    @Override
    @Nonnull
    public IMicroDocumentType getDocType ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML10_TRANS;
    }

    @Override
    @Nonnull
    public String getNamespaceURI ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML_URI;
    }
  },
  XHTML11 (false)
  {
    @Override
    @Nonnull
    public IMicroDocumentType getDocType ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML11;
    }

    @Override
    @Nonnull
    public String getNamespaceURI ()
    {
      return CHTMLDocTypes.DOCTYPE_XHTML_URI;
    }
  },
  HTML5 (true)
  {
    @Override
    @Nonnull
    public IMicroDocumentType getDocType ()
    {
      return CHTMLDocTypes.DOCTYPE_HTML5;
    }

    @Override
    @Nullable
    public String getNamespaceURI ()
    {
      return null;
    }
  },
  HTML51 (true)
  {
    @Override
    @Nonnull
    public IMicroDocumentType getDocType ()
    {
      return CHTMLDocTypes.DOCTYPE_HTML5;
    }

    @Override
    @Nullable
    public String getNamespaceURI ()
    {
      return null;
    }
  };

  private final boolean m_bIsAtLeastHTML5;

  private EHTMLVersion (final boolean bIsAtLeastHTML5)
  {
    m_bIsAtLeastHTML5 = bIsAtLeastHTML5;
  }

  /**
   * @return The document type matching this HTML version. Never
   *         <code>null</code>.
   */
  @Nonnull
  public abstract IMicroDocumentType getDocType ();

  /**
   * @return The XML namespace URI to use. May be <code>null</code> for HTML5 or
   *         later.
   */
  @Nullable
  public abstract String getNamespaceURI ();

  public boolean isXHTML10 ()
  {
    return this == XHTML10_STRICT || this == XHTML10_TRANSITIONAL;
  }

  public boolean isXHTML11 ()
  {
    return this == XHTML11;
  }

  public boolean isPriorToHTML5 ()
  {
    return !m_bIsAtLeastHTML5;
  }

  public boolean isAtLeastHTML5 ()
  {
    return m_bIsAtLeastHTML5;
  }
}
