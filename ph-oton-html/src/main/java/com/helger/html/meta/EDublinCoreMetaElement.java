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
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.lang.EnumHelper;

/**
 * An enumeration with all DublinCore meta tag names.
 *
 * @author Philip Helger
 */
public enum EDublinCoreMetaElement implements IMetaElementDeclaration
{
  TITLE ("DC.title"),
  CREATOR ("DC.creator"),
  SUBJECT ("DC.subject"),
  DESCRIPTION ("DC.description"),
  PUBLISHER ("DC.publisher"),
  CONTRIBUTOR ("DC.contributor"),
  DATE ("DC.date", "DCTERMS.W3CDTF"),
  TYPE ("DC.type", "DCTERMS.DCMIType"),
  FORMAT ("DC.format", "DCTERMS.IMT"),
  IDENTIFIER ("DC.identifier", "DCTERMS.URI"),
  SOURCE ("DC.source", "DCTERMS.URI"),
  LANGUAGE ("DC.language", "DCTERMS.RFC3066"),
  RELATION ("DC.relation", "DCTERMS.URI"),
  COVERAGE ("DC.coverage", "DCTERMS.TGN"),
  RIGHTS ("DC.rights");

  /**
   * Meta tag name
   */
  private final String m_sName;

  /**
   * Meta tag scheme
   */
  private final String m_sScheme;

  EDublinCoreMetaElement (@Nonnull @Nonempty final String sName)
  {
    this (sName, null);
  }

  EDublinCoreMetaElement (@Nonnull @Nonempty final String sName, @Nullable final String sScheme)
  {
    m_sName = sName;
    m_sScheme = sScheme;
  }

  /**
   * Get the meta tag name.
   *
   * @return the meta tag name
   */
  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  /**
   * Get the corresponding scheme of the meta tag.
   *
   * @return <code>null</code> if the meta tag has no scheme
   */
  @Nullable
  public String getScheme ()
  {
    return m_sScheme;
  }

  @Nonnull
  public EMetaElementType getType ()
  {
    return EMetaElementType.DOCUMENT_LEVEL;
  }

  /**
   * Check if a tag is a DublinCore tag.
   *
   * @param sTagName
   *        the tag name to check
   * @return null if the tag name is not a DublinCore meta tag
   */
  @Nullable
  public static EDublinCoreMetaElement getDCMetaTag (@Nullable final String sTagName)
  {
    return EnumHelper.getFromNameOrNull (EDublinCoreMetaElement.class, sTagName);
  }

  public static boolean isDCMetaTag (@Nullable final String sTagName)
  {
    return getDCMetaTag (sTagName) != null;
  }
}
