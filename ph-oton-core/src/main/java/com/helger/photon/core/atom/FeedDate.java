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
package com.helger.photon.core.atom;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.XMLConstants;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.impl.MicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.PDTFactory;
import com.helger.web.datetime.PDTWebDateUtils;

/**
 * ATOM date construct.
 *
 * @author Philip Helger
 */
public final class FeedDate extends AbstractFeedElement
{
  private LocalDateTime m_aDT;

  public FeedDate (@Nonnull final DateTime aDT)
  {
    this (aDT.toLocalDateTime ());
  }

  public FeedDate (@Nullable final LocalDateTime aDT)
  {
    setDateTime (aDT);
  }

  public void setDateTime (@Nullable final LocalDateTime aDT)
  {
    m_aDT = aDT;
  }

  @Nullable
  public LocalDateTime getDateTime ()
  {
    return m_aDT;
  }

  @Nonnull
  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.appendText (PDTWebDateUtils.getAsStringW3C (m_aDT));
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return m_aDT != null;
  }

  @Nonnull
  public static FeedDate createNow ()
  {
    return new FeedDate (PDTFactory.getCurrentLocalDateTime ());
  }
}
