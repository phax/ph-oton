/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.hc.ext;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.impl.HCDOMWrapper;
import com.helger.html.hc.impl.HCTextNode;
import com.helger.html.parser.XHTMLParser;
import com.helger.xml.microdom.IMicroContainer;

/**
 * A special XHTML parser that can convert nodes to {@link IHCNode}s.
 *
 * @author Philip Helger
 */
public class HCParser extends XHTMLParser
{
  private static final Logger LOGGER = LoggerFactory.getLogger (HCParser.class);

  public HCParser (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    super (eHTMLVersion);
  }

  /**
   * If the passed text looks like XHTML, unescape it (using
   * {@link #unescapeXHTMLFragment(String)}) else return a simple text node.
   *
   * @param sText
   *        The text to be converted. May be <code>null</code>.
   * @return A non-<code>null</code> IHCNode with the result representation
   *         (e.g. an {@link HCTextNode} or an {@link HCDOMWrapper} with an
   *         {@link IMicroContainer} having all the body elements)
   */
  @Nonnull
  public IHCNode convertToXHTMLFragmentOnDemand (@Nullable final String sText)
  {
    if (looksLikeXHTML (sText))
    {
      final IMicroContainer aCont = unescapeXHTMLFragment (sText);
      if (aCont != null)
        return new HCDOMWrapper (aCont);
      LOGGER.error ("Failed to unescape XHTML:\n" + sText);
    }
    return new HCTextNode (sText);
  }
}
