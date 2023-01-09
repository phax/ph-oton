/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.html.hc.impl;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.xml.microdom.EMicroNodeType;
import com.helger.xml.microdom.IMicroCDATA;
import com.helger.xml.microdom.IMicroNode;
import com.helger.xml.microdom.IMicroText;
import com.helger.xml.microdom.util.MicroRecursiveIterator;

/**
 * This is a simple wrapper around any {@link IMicroNode} so it can easily be
 * used in a HC* construction!
 *
 * @author Philip Helger
 */
public class HCDOMWrapper extends AbstractHCNode
{
  private final IMicroNode m_aNode;

  /**
   * Constructor.
   *
   * @param aNode
   *        The node to be wrapped. May not be <code>null</code>.
   */
  public HCDOMWrapper (@Nonnull final IMicroNode aNode)
  {
    ValueEnforcer.notNull (aNode, "Node");
    ValueEnforcer.isTrue (!aNode.hasParent (), "Passed MicroNode may not have a parent!");
    m_aNode = aNode;
  }

  /**
   * @return The source micro node. Never <code>null</code>.
   */
  @Nonnull
  public IMicroNode getNode ()
  {
    return m_aNode;
  }

  @Override
  @Nonnull
  protected IMicroNode internalConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Always return a clone, because otherwise upon first generation the node
    // will be assigned a parent, and upon second generation an exception is
    // thrown, because a parent is already present!
    return m_aNode.getClone ();
  }

  @Override
  @Nonnull
  public String getPlainText ()
  {
    final StringBuilder ret = new StringBuilder ();
    for (final IMicroNode aNode : new MicroRecursiveIterator (m_aNode))
    {
      CharSequence sPlainText = null;
      final EMicroNodeType eType = aNode.getType ();
      if (eType == EMicroNodeType.TEXT)
      {
        final IMicroText aTextNode = (IMicroText) aNode;
        if (!aTextNode.isElementContentWhitespace ())
          sPlainText = aTextNode.getData ();
      }
      else
        if (eType == EMicroNodeType.CDATA)
        {
          final IMicroCDATA aCDATANode = (IMicroCDATA) aNode;
          sPlainText = aCDATANode.getData ();
        }
      if (StringHelper.hasText (sPlainText))
      {
        if (ret.length () > 0)
          ret.append (' ');
        ret.append (sPlainText);
      }
    }
    return ret.toString ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("node", m_aNode).getToString ();
  }
}
