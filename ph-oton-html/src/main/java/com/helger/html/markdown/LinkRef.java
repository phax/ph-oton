/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.html.markdown;

import javax.annotation.Nonnull;

/**
 * A markdown link reference.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
final class LinkRef
{
  /** The link. */
  private final String m_sLink;
  /** The optional comment/title. */
  private String m_sTitle;
  /** Flag indicating that this is an abbreviation. */
  private final boolean m_bIsAbbrev;

  /**
   * Constructor.
   *
   * @param sLink
   *        The link.
   * @param sTitle
   *        The title (may be <code>null</code>).
   * @param bIsAbbrev
   *        Is abbreviation?
   */
  public LinkRef (@Nonnull final String sLink, final String sTitle, final boolean bIsAbbrev)
  {
    m_sLink = sLink;
    m_sTitle = sTitle;
    m_bIsAbbrev = bIsAbbrev;
  }

  @Nonnull
  public String getLink ()
  {
    return m_sLink;
  }

  public String getTitle ()
  {
    return m_sTitle;
  }

  public void setTitle (final String sTitle)
  {
    m_sTitle = sTitle;
  }

  public boolean isAbbrev ()
  {
    return m_bIsAbbrev;
  }
}
