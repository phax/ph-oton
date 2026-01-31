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
package com.helger.photon.atom;

import java.time.LocalDateTime;

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.datetime.helper.PDTFactory;
import com.helger.datetime.web.PDTWebDateHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

/**
 * ATOM date construct.
 *
 * @author Philip Helger
 */
public class FeedDate extends AbstractFeedElement
{
  private LocalDateTime m_aDT;

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

  @NonNull
  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.addText (PDTWebDateHelper.getAsStringW3C (m_aDT));
    if (StringHelper.isNotEmpty (getLanguage ()))
      aElement.setAttributeNS (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return m_aDT != null;
  }

  @NonNull
  public static FeedDate createNow ()
  {
    return new FeedDate (PDTFactory.getCurrentLocalDateTime ());
  }
}
