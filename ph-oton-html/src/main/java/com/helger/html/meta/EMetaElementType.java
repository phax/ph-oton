/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import javax.annotation.Nonnull;

import com.helger.html.CHTMLAttributes;
import com.helger.xml.microdom.IMicroQName;

/**
 * An enumeration with all meta element types. The type defines the layout of
 * the meta element.
 *
 * @author Philip Helger
 */
public enum EMetaElementType
{
  /** Document-level metadata */
  DOCUMENT_LEVEL (CHTMLAttributes.NAME),
  /** Pragma directive - HTTP equivalent */
  PRAGMA_DIRECTIVE (CHTMLAttributes.HTTP_EQUIV),
  /** HTML5 charset directive */
  CHARSET (CHTMLAttributes.CHARSET);

  /**
   * HTML attribute name to use.
   */
  private final IMicroQName m_aAttrName;

  EMetaElementType (@Nonnull final IMicroQName aAttrName)
  {
    m_aAttrName = aAttrName;
  }

  /**
   * @return the HTML attribute name to use. Never <code>null</code>.
   */
  @Nonnull
  public IMicroQName getAttrName ()
  {
    return m_aAttrName;
  }

  public boolean isMultilingual ()
  {
    return this == DOCUMENT_LEVEL;
  }
}
