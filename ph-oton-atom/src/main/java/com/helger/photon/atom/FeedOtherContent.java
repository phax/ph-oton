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

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.MicroElement;

public class FeedOtherContent extends AbstractFeedElement implements IFeedContent
{
  private final ICommonsList <Object> m_aChildren = new CommonsArrayList <> ();
  private final String m_sType;

  public FeedOtherContent (@NonNull @Nonempty final String sType)
  {
    ValueEnforcer.notEmpty (sType, "Type");
    m_sType = sType;
  }

  @NonNull
  @Nonempty
  public String getType ()
  {
    return m_sType;
  }

  @NonNull
  public FeedOtherContent addChild (@NonNull final String sText)
  {
    ValueEnforcer.notNull (sText, "Text");
    m_aChildren.add (sText);
    return this;
  }

  @NonNull
  public FeedOtherContent addChild (@NonNull final IMicroNode aNode)
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
      if (aChild instanceof final String sChild)
        aElement.addText (sChild);
      else
        aElement.addChild ((IMicroNode) aChild);
    if (StringHelper.isNotEmpty (getLanguage ()))
      aElement.setAttributeNS (XMLConstants.XML_NS_URI, "lang", getLanguage ());
    return aElement;
  }

  public boolean isValid ()
  {
    return true;
  }
}
