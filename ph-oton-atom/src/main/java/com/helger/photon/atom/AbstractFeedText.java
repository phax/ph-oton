/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.xml.XMLConstants;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroElement;

public abstract class AbstractFeedText extends AbstractFeedElement
{
  public static final EFeedTextType DEFAULT_TYPE = EFeedTextType.TEXT;

  private final EFeedTextType m_eType;
  private final String m_sText;

  public AbstractFeedText (@Nullable final EFeedTextType eType, @Nullable final String sText)
  {
    m_eType = eType == null ? DEFAULT_TYPE : eType;
    m_sText = sText;
  }

  @NonNull
  @Nonempty
  public final String getType ()
  {
    return m_eType.getType ();
  }

  @Nullable
  public final String getText ()
  {
    return m_sText;
  }

  public final IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.setAttribute ("type", getType ());
    aElement.addText (m_sText == null ? "" : m_sText);
    if (StringHelper.isNotEmpty (getLanguage ()))
      aElement.setAttributeNS (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public final boolean isValid ()
  {
    return true;
  }
}
