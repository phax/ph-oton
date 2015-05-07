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
package com.helger.html;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.microdom.IHasElementName;
import com.helger.commons.string.StringHelper;

/**
 * Contains a list of all HTML element names.
 *
 * @author Philip Helger
 */
public enum EHTMLElement implements IHasElementName
{
  A ("a", false, EHTMLContentModelType.FLOW),
  ABBR ("abbr", false, EHTMLContentModelType.PHRASING),
  ADDRESS ("address", false, EHTMLContentModelType.FLOW),
  APPLET ("applet", false, EHTMLContentModelType.FLOW),
  AREA ("area", true, EHTMLContentModelType.PHRASING),
  ARTICLE ("article", false, EHTMLContentModelType.FLOW),
  ASIDE ("aside", false, EHTMLContentModelType.FLOW),
  AUDIO ("audio", false, EHTMLContentModelType.FLOW),
  B ("b", false, EHTMLContentModelType.PHRASING),
  BASE ("base", true, EHTMLContentModelType.EMPTY),
  BDI ("bdi", false, EHTMLContentModelType.PHRASING),
  BDO ("bdo", false, EHTMLContentModelType.PHRASING),
  BLOCKQUOTE ("blockquote", false, EHTMLContentModelType.FLOW),
  BODY ("body", false, EHTMLContentModelType.SPECIAL),
  BR ("br", true, EHTMLContentModelType.PHRASING),
  BUTTON ("button", false, EHTMLContentModelType.PHRASING),
  CANVAS ("canvas", false, EHTMLContentModelType.FLOW),
  CAPTION ("caption", false, EHTMLContentModelType.FLOW),
  CENTER ("center", false, EHTMLContentModelType.LEGACY_PHRASING),
  CITE ("cite", false, EHTMLContentModelType.PHRASING),
  CODE ("code", false, EHTMLContentModelType.PHRASING),
  COL ("col", true, EHTMLContentModelType.EMPTY),
  COLGROUP ("colgroup", false, EHTMLContentModelType.CHILD),
  COMMAND ("command", false, EHTMLContentModelType.PHRASING_METADATA),
  DATALIST ("datalist", false, EHTMLContentModelType.PHRASING),
  DD ("dd", false, EHTMLContentModelType.FLOW),
  DEL ("del", false, EHTMLContentModelType.FLOW),
  DETAILS ("details", false, EHTMLContentModelType.FLOW),
  DFN ("dfn", false, EHTMLContentModelType.PHRASING),
  DIR ("dir", false, EHTMLContentModelType.LEGACY_PHRASING),
  DIV ("div", false, EHTMLContentModelType.FLOW),
  DL ("dl", false, EHTMLContentModelType.FLOW),
  DT ("dt", false, EHTMLContentModelType.FLOW),
  EM ("em", false, EHTMLContentModelType.PHRASING),
  EMBED ("embed", true, EHTMLContentModelType.PHRASING),
  FIELDSET ("fieldset", false, EHTMLContentModelType.FLOW),
  FIGCAPTION ("figcaption", false, EHTMLContentModelType.FLOW),
  FIGURE ("figure", false, EHTMLContentModelType.FLOW),
  FONT ("font", false, EHTMLContentModelType.UNDEFINED),
  FOOTER ("footer", false, EHTMLContentModelType.FLOW),
  FORM ("form", false, EHTMLContentModelType.FLOW),
  FRAME ("frame", true, EHTMLContentModelType.UNDEFINED),
  FRAMESET ("frameset", false, EHTMLContentModelType.UNDEFINED),
  H1 ("h1", false, EHTMLContentModelType.FLOW),
  H2 ("h2", false, EHTMLContentModelType.FLOW),
  H3 ("h3", false, EHTMLContentModelType.FLOW),
  H4 ("h4", false, EHTMLContentModelType.FLOW),
  H5 ("h5", false, EHTMLContentModelType.FLOW),
  H6 ("h6", false, EHTMLContentModelType.FLOW),
  HEAD ("head", false, EHTMLContentModelType.SPECIAL),
  HEADER ("header", false, EHTMLContentModelType.FLOW),
  HGROUP ("hgroup", false, EHTMLContentModelType.FLOW),
  HR ("hr", true, EHTMLContentModelType.FLOW),
  HTML ("html", false, EHTMLContentModelType.SPECIAL),
  I ("i", false, EHTMLContentModelType.PHRASING),
  IFRAME ("iframe", false, EHTMLContentModelType.PHRASING),
  IMG ("img", true, EHTMLContentModelType.PHRASING),
  INS ("ins", false, EHTMLContentModelType.FLOW),
  INPUT ("input", true, EHTMLContentModelType.PHRASING),
  KBD ("kbd", false, EHTMLContentModelType.PHRASING),
  KEYGEN ("keygen", true, EHTMLContentModelType.PHRASING),
  LABEL ("label", false, EHTMLContentModelType.PHRASING),
  LEGEND ("legend", false, EHTMLContentModelType.CHILD),
  LI ("li", false, EHTMLContentModelType.CHILD),
  LINK ("link", true, EHTMLContentModelType.METADATA),
  MAIN ("main", false, EHTMLContentModelType.FLOW),
  MAP ("map", false, EHTMLContentModelType.FLOW),
  MARK ("mark", false, EHTMLContentModelType.PHRASING),
  MENU ("menu", false, EHTMLContentModelType.FLOW),
  META ("meta", true, EHTMLContentModelType.METADATA),
  METER ("meter", false, EHTMLContentModelType.PHRASING),
  NAV ("nav", false, EHTMLContentModelType.FLOW),
  NOBR ("nobr", false, EHTMLContentModelType.UNDEFINED),
  NOSCRIPT ("noscript", false, EHTMLContentModelType.FLOW_METADATA),
  OBJECT ("object", false, EHTMLContentModelType.FLOW),
  OL ("ol", false, EHTMLContentModelType.FLOW),
  OPTGROUP ("optgroup", false, EHTMLContentModelType.CHILD),
  OPTION ("option", false, EHTMLContentModelType.CHILD),
  OUTPUT ("output", false, EHTMLContentModelType.PHRASING),
  P ("p", false, EHTMLContentModelType.FLOW),
  PARAM ("param", false, EHTMLContentModelType.CHILD),
  PRE ("pre", false, EHTMLContentModelType.FLOW),
  PROGRESS ("progress", false, EHTMLContentModelType.PHRASING),
  RP ("rp", false, EHTMLContentModelType.CHILD),
  RT ("rt", false, EHTMLContentModelType.CHILD),
  RUBY ("ruby", false, EHTMLContentModelType.PHRASING),
  Q ("q", false, EHTMLContentModelType.PHRASING),
  S ("s", false, EHTMLContentModelType.PHRASING),
  SAMP ("samp", false, EHTMLContentModelType.PHRASING),
  SCRIPT ("script", false, EHTMLContentModelType.PHRASING_METADATA),
  SECTION ("section", false, EHTMLContentModelType.FLOW),
  SELECT ("select", false, EHTMLContentModelType.PHRASING),
  SMALL ("small", false, EHTMLContentModelType.PHRASING),
  SOURCE ("source", true, EHTMLContentModelType.CHILD),
  SPAN ("span", false, EHTMLContentModelType.PHRASING),
  STRONG ("strong", false, EHTMLContentModelType.PHRASING),
  SUB ("sub", false, EHTMLContentModelType.PHRASING),
  SUMMARY ("summary", false, EHTMLContentModelType.CHILD),
  SUP ("sup", false, EHTMLContentModelType.PHRASING),
  STYLE ("style", false, EHTMLContentModelType.METADATA),
  TABLE ("table", false, EHTMLContentModelType.FLOW),
  TBODY ("tbody", false, EHTMLContentModelType.CHILD),
  TD ("td", false, EHTMLContentModelType.CHILD),
  TEXTAREA ("textarea", false, EHTMLContentModelType.PHRASING),
  // Any, except the html element, the head element, the body element, or the
  // frameset element:
  TEMPLATE ("template", false, EHTMLContentModelType.SPECIAL),
  TFOOT ("tfoot", false, EHTMLContentModelType.CHILD),
  TH ("th", false, EHTMLContentModelType.CHILD),
  THEAD ("thead", false, EHTMLContentModelType.CHILD),
  TIME ("time", false, EHTMLContentModelType.PHRASING),
  TITLE ("title", false, EHTMLContentModelType.CHILD),
  TR ("tr", false, EHTMLContentModelType.CHILD),
  TRACK ("track", true, EHTMLContentModelType.CHILD),
  U ("u", false, EHTMLContentModelType.PHRASING),
  UL ("ul", false, EHTMLContentModelType.FLOW),
  VAR ("var", false, EHTMLContentModelType.PHRASING),
  VIDEO ("video", false, EHTMLContentModelType.FLOW),
  WBR ("wbr", true, EHTMLContentModelType.PHRASING);

  private static final Set <String> s_aSelfClosedElements = new HashSet <String> ();

  private final String m_sElementNameLC;
  private final String m_sElementNameUC;
  private final boolean m_bMayBeSelfClosed;
  private final EHTMLContentModelType m_eType;

  private EHTMLElement (@Nonnull @Nonempty final String sElementName,
                        final boolean bMayBeSelfClosed,
                        @Nonnull final EHTMLContentModelType eType)
  {
    m_sElementNameLC = sElementName.toLowerCase (CHTMLCharset.LOCALE);
    m_sElementNameUC = sElementName.toUpperCase (CHTMLCharset.LOCALE);
    m_bMayBeSelfClosed = bMayBeSelfClosed;
    m_eType = eType;
  }

  /**
   * @return The defined element name in any case. Neither <code>null</code> nor
   *         empty.
   */
  @Nonnull
  @Nonempty
  public String getElementName ()
  {
    return m_sElementNameLC;
  }

  /**
   * @return The defined element name in lowercase characters. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getElementNameLowerCase ()
  {
    return m_sElementNameLC;
  }

  /**
   * @return The defined element name in uppercase characters. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public String getElementNameUpperCase ()
  {
    return m_sElementNameUC;
  }

  /**
   * @return <code>true</code> if this element may be self-closed (e.g. &lt;br
   *         /&gt;), and <code>false</code> if not (e.g.
   *         &lt;link...&gt;&lt;/link&gt;)
   */
  public boolean mayBeSelfClosed ()
  {
    return m_bMayBeSelfClosed;
  }

  /**
   * @return <code>true</code> if this element may not be self-closed (e.g.
   *         &lt;link...&gt;&lt;/link&gt;), <code>false</code> if it may be
   *         self-closed (e.g. &lt;br /&gt;)
   */
  public boolean mayNotBeSelfClosed ()
  {
    return !m_bMayBeSelfClosed;
  }

  public boolean isFlowElement ()
  {
    return m_eType.isFlowElement ();
  }

  public boolean isMetadataElement ()
  {
    return m_eType.isMetadataElement ();
  }

  public boolean isPhrasingElement ()
  {
    return m_eType.isPhrasingElement ();
  }

  @Nonnull
  private static Set <String> _getSelfClosedSet ()
  {
    if (s_aSelfClosedElements.isEmpty ())
    {
      // Lazy init, because it cannot be done in the constructor!
      for (final EHTMLElement e : values ())
        if (e.mayBeSelfClosed ())
        {
          // Always use lower cased value
          s_aSelfClosedElements.add (e.m_sElementNameLC);
        }
    }
    return s_aSelfClosedElements;
  }

  /**
   * Check if the passed element may be self closed when creating HTML.
   *
   * @param sElementName
   *        The name of the tag to validate.
   * @return <code>true</code> if the tag may not be self closed.
   */
  public static boolean isTagThatMayBeSelfClosed (@Nullable final String sElementName)
  {
    if (StringHelper.hasNoText (sElementName))
      return false;

    // Always check lower cased
    return _getSelfClosedSet ().contains (sElementName.toLowerCase (CHTMLCharset.LOCALE));
  }

  /**
   * Check if the passed element may not be self closed when creating HTML.
   *
   * @param sElementName
   *        The name of the tag to validate.
   * @return <code>true</code> if the tag may not be self closed.
   */
  public static boolean isTagThatMayNotBeSelfClosed (@Nullable final String sElementName)
  {
    if (StringHelper.hasNoText (sElementName))
      return false;

    // Always check lower cased
    return !_getSelfClosedSet ().contains (sElementName.toLowerCase (CHTMLCharset.LOCALE));
  }

  /**
   * Check if the passed tag is an HTML tag name.
   *
   * @param sTagName
   *        The case sensitive tag name to check.
   * @return <code>true</code> if it is a known HTML tag, <code>false</code>
   *         otherwise.
   */
  public static boolean isHTMLTagName (@Nullable final String sTagName)
  {
    return getFromTagNameOrNull (sTagName) != null;
  }

  /**
   * Get the {@link EHTMLElement} for the passed tag name using case insensitive
   * compare
   *
   * @param sTagName
   *        The case sensitive tag name to check.
   * @return The matching {@link EHTMLElement} or <code>null</code> if no such
   *         element is present.
   */
  @Nullable
  public static EHTMLElement getFromTagNameOrNull (@Nullable final String sTagName)
  {
    if (StringHelper.hasText (sTagName))
      for (final EHTMLElement eElement : values ())
        if (eElement.m_sElementNameLC.equalsIgnoreCase (sTagName))
          return eElement;
    return null;
  }
}
