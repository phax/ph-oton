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
package com.helger.html.markdown;

import java.util.HashSet;
import java.util.Set;

import com.helger.commons.url.IURLProtocol;
import com.helger.commons.url.URLProtocolRegistry;
import com.helger.html.EHTMLElement;

/**
 * HTML utility class.
 *
 * @author René Jeschke &lt;rene_jeschke@yahoo.de&gt;
 */
final class MarkdownHTML
{
  /** HTML block level elements. */
  private static final EHTMLElement [] BLOCK_ELEMENTS = { EHTMLElement.ADDRESS,
                                                         EHTMLElement.BLOCKQUOTE,
                                                         EHTMLElement.DEL,
                                                         EHTMLElement.DIV,
                                                         EHTMLElement.DL,
                                                         EHTMLElement.FIELDSET,
                                                         EHTMLElement.FORM,
                                                         EHTMLElement.H1,
                                                         EHTMLElement.H2,
                                                         EHTMLElement.H3,
                                                         EHTMLElement.H4,
                                                         EHTMLElement.H5,
                                                         EHTMLElement.H6,
                                                         EHTMLElement.HR,
                                                         EHTMLElement.INS,
                                                         EHTMLElement.NOSCRIPT,
                                                         EHTMLElement.OL,
                                                         EHTMLElement.P,
                                                         EHTMLElement.PRE,
                                                         EHTMLElement.TABLE,
                                                         EHTMLElement.UL };

  /** HTML unsafe elements. */
  private static final EHTMLElement [] UNSAFE_ELEMENTS = { EHTMLElement.APPLET,
                                                          EHTMLElement.HEAD,
                                                          EHTMLElement.HTML,
                                                          EHTMLElement.BODY,
                                                          EHTMLElement.FRAME,
                                                          EHTMLElement.FRAMESET,
                                                          EHTMLElement.IFRAME,
                                                          EHTMLElement.SCRIPT,
                                                          EHTMLElement.OBJECT, };

  /** Set of valid markdown link prefixes. */
  private static final Set <String> LINK_PREFIX = new HashSet <String> ();
  /** Set of HTML block level tags. */
  private static final Set <String> HTML_BLOCK_ELEMENTS = new HashSet <String> ();
  /** Set of unsafe HTML tags. */
  private static final Set <String> HTML_UNSAFE = new HashSet <String> ();

  static
  {
    for (final IURLProtocol element : URLProtocolRegistry.getInstance ().getAllProtocols ())
    {
      final String sProtocol = element.getProtocol ();
      final int i = sProtocol.indexOf (':');
      LINK_PREFIX.add (i < 0 ? sProtocol : sProtocol.substring (0, i));
    }

    for (final EHTMLElement h : BLOCK_ELEMENTS)
      HTML_BLOCK_ELEMENTS.add (h.getElementNameLowerCase ());

    for (final EHTMLElement h : UNSAFE_ELEMENTS)
      HTML_UNSAFE.add (h.getElementNameLowerCase ());
  }

  private MarkdownHTML ()
  {}

  /**
   * @param value
   *        String to check.
   * @return Returns <code>true</code> if the given String is a link prefix.
   */
  public static boolean isLinkPrefix (final String value)
  {
    return LINK_PREFIX.contains (value);
  }

  /**
   * @param value
   *        String to check.
   * @return Returns <code>true</code> if the given String is a HTML block level
   *         tag.
   */
  public static boolean isHtmlBlockElement (final String value)
  {
    return HTML_BLOCK_ELEMENTS.contains (value);
  }

  /**
   * @param value
   *        String to check.
   * @return Returns <code>true</code> if the given String is an unsafe HTML
   *         tag.
   */
  public static boolean isUnsafeHtmlElement (final String value)
  {
    return HTML_UNSAFE.contains (value);
  }
}
