/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.xml.XMLConstants;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

public class FeedOutOfLineContent extends AbstractFeedElement implements IFeedContent
{
  private final String m_sType;
  private final String m_sSrc;

  public FeedOutOfLineContent (@Nonnull @Nonempty final String sType, @Nonnull @Nonempty final String sSrc)
  {
    ValueEnforcer.notEmpty (sType, "Type");
    ValueEnforcer.notEmpty (sSrc, "Src");
    m_sType = sType;
    m_sSrc = sSrc;
  }

  @Nonnull
  @Nonempty
  public String getType ()
  {
    return m_sType;
  }

  @Nonnull
  @Nonempty
  public String getSrc ()
  {
    return m_sSrc;
  }

  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.setAttribute ("type", m_sType);
    aElement.setAttribute ("src", m_sSrc);
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return true;
  }
}
