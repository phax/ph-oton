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
package com.helger.photon.markdown;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.ICommonsSet;
import com.helger.html.EHTMLElement;
import com.helger.url.protocol.IURLProtocol;
import com.helger.url.protocol.URLProtocolRegistry;

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
  @SuppressWarnings ("deprecation")
  private static final EHTMLElement [] UNSAFE_ELEMENTS = { EHTMLElement.APPLET,
                                                           EHTMLElement.HEAD,
                                                           EHTMLElement.HTML,
                                                           EHTMLElement.BODY,
                                                           EHTMLElement.FRAME,
                                                           EHTMLElement.FRAMESET,
                                                           EHTMLElement.IFRAME,
                                                           EHTMLElement.SCRIPT,
                                                           EHTMLElement.OBJECT,
                                                           EHTMLElement.STYLE,
                                                           EHTMLElement.LINK,
                                                           EHTMLElement.META,
                                                           EHTMLElement.BASE,
                                                           EHTMLElement.EMBED,
                                                           EHTMLElement.FORM };

  /** Unsafe elements that are not part of {@link EHTMLElement}. */
  private static final String [] UNSAFE_ELEMENT_NAMES = { "svg", "math" };

  /** Attributes that may contain a URL. */
  private static final String [] URL_ATTRIBUTES = { "action",
                                                    "background",
                                                    "cite",
                                                    "codebase",
                                                    "data",
                                                    "dynsrc",
                                                    "formaction",
                                                    "href",
                                                    "longdesc",
                                                    "lowsrc",
                                                    "poster",
                                                    "src",
                                                    "srcset",
                                                    "xlink:href" };

  /** URL schemes that are not allowed in safe mode. */
  private static final String [] UNSAFE_URL_SCHEMES = { "javascript:", "vbscript:", "data:" };

  /** Set of valid markdown link prefixes. */
  private static final ICommonsSet <String> LINK_PREFIX = new CommonsHashSet <> ();
  /** Set of HTML block level tags. */
  private static final ICommonsSet <String> HTML_BLOCK_ELEMENTS = new CommonsHashSet <> ();
  /** Set of unsafe HTML tags. */
  private static final ICommonsSet <String> HTML_UNSAFE = new CommonsHashSet <> ();
  /** Set of HTML attributes that may contain a URL. */
  private static final ICommonsSet <String> HTML_URL_ATTRIBUTES = new CommonsHashSet <> ();

  static
  {
    for (final IURLProtocol aProtocol : URLProtocolRegistry.getInstance ().getAllProtocols ())
    {
      final String sProtocol = aProtocol.getProtocol ();
      final int i = sProtocol.indexOf (':');
      LINK_PREFIX.add (i < 0 ? sProtocol : sProtocol.substring (0, i));
    }

    for (final EHTMLElement e : BLOCK_ELEMENTS)
      HTML_BLOCK_ELEMENTS.add (e.getElementName ());

    for (final EHTMLElement e : UNSAFE_ELEMENTS)
      HTML_UNSAFE.add (e.getElementName ());
    for (final String sElementName : UNSAFE_ELEMENT_NAMES)
      HTML_UNSAFE.add (sElementName);

    for (final String sAttrName : URL_ATTRIBUTES)
      HTML_URL_ATTRIBUTES.add (sAttrName);
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
   * @param sValue
   *        String to check.
   * @return Returns <code>true</code> if the given String is a HTML block level tag.
   */
  public static boolean isHtmlBlockElement (@Nullable final String sValue)
  {
    if (StringHelper.isEmpty (sValue))
      return false;
    return HTML_BLOCK_ELEMENTS.contains (EHTMLElement.getUnifiedHTMLElementName (sValue));
  }

  /**
   * @param sValue
   *        String to check.
   * @return Returns <code>true</code> if the given String is an unsafe HTML tag.
   */
  public static boolean isUnsafeHtmlElement (@Nullable final String sValue)
  {
    if (StringHelper.isEmpty (sValue))
      return false;
    return HTML_UNSAFE.contains (EHTMLElement.getUnifiedHTMLElementName (sValue));
  }

  @NonNull
  private static String _getWithoutWhitespaceAndControlChars (@NonNull final String sValue)
  {
    final StringBuilder aSB = new StringBuilder (sValue.length ());
    for (final char c : sValue.toCharArray ())
      if (c > ' ')
        aSB.append (c);
    return aSB.toString ();
  }

  /**
   * Check if an attribute is unsafe and must not be emitted in safe mode. This is the case for
   * event handler attributes (starting with <code>on</code>) and for URL attributes using the
   * <code>javascript:</code>, <code>vbscript:</code> or <code>data:</code> scheme.
   *
   * @param sAttrName
   *        Attribute name to check. May be <code>null</code>.
   * @param sAttrValue
   *        Attribute value to check. May be <code>null</code>.
   * @return Returns <code>true</code> if the given attribute is unsafe.
   */
  public static boolean isUnsafeHtmlAttribute (@Nullable final String sAttrName, @Nullable final String sAttrValue)
  {
    if (StringHelper.isEmpty (sAttrName))
      return false;

    final String sUnifiedName = sAttrName.toLowerCase (Locale.ROOT);
    if (sUnifiedName.startsWith ("on"))
    {
      // Event handler
      return true;
    }

    if (sAttrValue != null && HTML_URL_ATTRIBUTES.contains (sUnifiedName))
    {
      // Browsers ignore whitespace and control characters inside the scheme
      final String sUnifiedValue = _getWithoutWhitespaceAndControlChars (sAttrValue).toLowerCase (Locale.ROOT);
      for (final String sScheme : UNSAFE_URL_SCHEMES)
        if (sUnifiedValue.startsWith (sScheme))
          return true;
    }
    return false;
  }
}
