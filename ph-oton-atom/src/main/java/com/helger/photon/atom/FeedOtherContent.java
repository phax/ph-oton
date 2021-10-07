/*
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
package com.helger.photon.atom;

import javax.annotation.Nonnull;
import javax.xml.XMLConstants;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.string.StringHelper;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroElement;

public class FeedOtherContent extends AbstractFeedElement implements IFeedContent
{
  private final ICommonsList <Object> m_aChildren = new CommonsArrayList <> ();
  private final String m_sType;

  public FeedOtherContent (@Nonnull @Nonempty final String sType)
  {
    ValueEnforcer.notEmpty (sType, "Type");
    m_sType = sType;
  }

  @Nonnull
  @Nonempty
  public String getType ()
  {
    return m_sType;
  }

  @Nonnull
  public FeedOtherContent addChild (@Nonnull final String sText)
  {
    ValueEnforcer.notNull (sText, "Text");
    m_aChildren.add (sText);
    return this;
  }

  @Nonnull
  public FeedOtherContent addChild (@Nonnull final IMicroNode aNode)
  {
    ValueEnforcer.notNull (aNode, "Node");
    m_aChildren.add (aNode);
    return this;
  }

  public IMicroElement getAsElement (final String sElementName)
  {
    final IMicroElement aElement = new MicroElement (CFeed.XMLNS_ATOM, sElementName);
    aElement.setAttribute ("type", m_sType);
    for (final Object aChild : m_aChildren)
      if (aChild instanceof String)
        aElement.appendText ((String) aChild);
      else
        aElement.appendChild ((IMicroNode) aChild);
    if (StringHelper.hasText (getLanguage ()))
      aElement.setAttribute (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return true;
  }
}
