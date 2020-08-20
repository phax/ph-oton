/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.html.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.CodingStyleguideUnaware;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Contains some predefined entities.
 *
 * @author Philip Helger
 */
@CodingStyleguideUnaware
public enum EHTMLEntity implements IHTMLEntity
{
  // Reserved chars:
  // amp must be first!
  amp ("amp", '&', "ampersand"),
  gt ("gt", '>', "greater-than"),
  lt ("lt", '<', "less-than"),
  quot ("quot", '"', "quotation mark"),
  apos ("apos", '\'', "apostrophe"),

  // ISO-8859 symbols
  nbsp ("nbsp", '\u00a0', "non-breaking space"),
  iexcl ("iexcl", '\u00a1', "inverted exclamation mark"),
  cent ("cent", '\u00a2', "cent"),
  pound ("pound", '\u00a3', "pound"),
  curren ("curren", '\u00a4', "currency"),
  yen ("yen", '\u00a5', "yen"),
  brvbar ("brvbar", '\u00a6', "broken vertical bar"),
  sect ("sect", '\u00a7', "section"),
  uml ("uml", '\u00a8', "spacing diaeresis"),
  copy ("copy", '\u00a9', "copyright"),
  ordf ("ordf", '\u00aa', "feminine ordinal indicator"),
  laquo ("laquo", '\u00ab', "angle quotation mark (left)"),
  not ("not", '\u00ac', "negation"),
  shy ("shy", '\u00ad', "soft hyphen"),
  reg ("reg", '\u00ae', "registered trademark"),
  macr ("macr", '\u00af', "spacing macron"),
  deg ("deg", '\u00b0', "degree"),
  plusmn ("plusmn", '\u00b1', "plus-or-minus "),
  sup2 ("sup2", '\u00b2', "superscript 2"),
  sup3 ("sup3", '\u00b3', "superscript 3"),
  acute ("acute", '\u00b4', "spacing acute"),
  micro ("micro", '\u00b5', "micro"),
  para ("para", '\u00b6', "paragraph"),
  middot ("middot", '\u00b7', "middle dot"),
  cedil ("cedil", '\u00b8', "spacing cedilla"),
  sup1 ("sup1", '\u00b9', "superscript 1"),
  ordm ("ordm", '\u00ba', "masculine ordinal indicator"),
  raquo ("raquo", '\u00bb', "angle quotation mark (right)"),
  frac14 ("frac14", '\u00bc', "fraction 1/4"),
  frac12 ("frac12", '\u00bd', "fraction 1/2"),
  frac34 ("frac34", '\u00be', "fraction 3/4"),
  iquest ("iquest", '\u00bf', "inverted question mark"),
  times ("times", '\u00d7', "multiplication"),
  divide ("divide", '\u00f7', "division"),

  // ISO-8859 characters
  Agrave ("Agrave", '\u00c0', "capital a, grave accent"),
  Aacute ("Aacute", '\u00c1', "capital a, acute accent"),
  Acirc ("Acirc", '\u00c2', "capital a, circumflex accent"),
  Atilde ("Atilde", '\u00c3', "capital a, tilde"),
  Auml ("Auml", '\u00c4', "capital a, umlaut mark"),
  Aring ("Aring", '\u00c5', "capital a, ring"),
  AElig ("AElig", '\u00c6', "capital ae"),
  Ccedil ("Ccedil", '\u00c7', "capital c, cedilla"),
  Egrave ("Egrave", '\u00c8', "capital e, grave accent"),
  Eacute ("Eacute", '\u00c9', "capital e, acute accent"),
  Ecirc ("Ecirc", '\u00ca', "capital e, circumflex accent"),
  Euml ("Euml", '\u00cb', "capital e, umlaut mark"),
  Igrave ("Igrave", '\u00cc', "capital i, grave accent"),
  Iacute ("Iacute", '\u00cd', "capital i, acute accent"),
  Icirc ("Icirc", '\u00ce', "capital i, circumflex accent"),
  Iuml ("Iuml", '\u00cf', "capital i, umlaut mark"),
  ETH ("ETH", '\u00d0', "capital eth, Icelandic"),
  Ntilde ("Ntilde", '\u00d1', "capital n, tilde"),
  Ograve ("Ograve", '\u00d2', "capital o, grave accent"),
  Oacute ("Oacute", '\u00d3', "capital o, acute accent"),
  Ocirc ("Ocirc", '\u00d4', "capital o, circumflex accent"),
  Otilde ("Otilde", '\u00d5', "capital o, tilde"),
  Ouml ("Ouml", '\u00d6', "capital o, umlaut mark"),
  Oslash ("Oslash", '\u00d8', "capital o, slash"),
  Ugrave ("Ugrave", '\u00d9', "capital u, grave accent"),
  Uacute ("Uacute", '\u00da', "capital u, acute accent"),
  Ucirc ("Ucirc", '\u00db', "capital u, circumflex accent"),
  Uuml ("Uuml", '\u00dc', "capital u, umlaut mark"),
  Yacute ("Yacute", '\u00dd', "capital y, acute accent"),
  THORN ("THORN", '\u00de', "capital THORN, Icelandic"),
  szlig ("szlig", '\u00df', "small sharp s, German"),
  agrave ("agrave", '\u00e0', "small a, grave accent"),
  aacute ("aacute", '\u00e1', "small a, acute accent"),
  acirc ("acirc", '\u00e2', "small a, circumflex accent"),
  atilde ("atilde", '\u00e3', "small a, tilde"),
  auml ("auml", '\u00e4', "small a, umlaut mark"),
  aring ("aring", '\u00e5', "small a, ring"),
  aelig ("aelig", '\u00e6', "small ae"),
  ccedil ("ccedil", '\u00e7', "small c, cedilla"),
  egrave ("egrave", '\u00e8', "small e, grave accent"),
  eacute ("eacute", '\u00e9', "small e, acute accent"),
  ecirc ("ecirc", '\u00ea', "small e, circumflex accent"),
  euml ("euml", '\u00eb', "small e, umlaut mark"),
  igrave ("igrave", '\u00ec', "small i, grave accent"),
  iacute ("iacute", '\u00ed', "small i, acute accent"),
  icirc ("icirc", '\u00ee', "small i, circumflex accent"),
  iuml ("iuml", '\u00ef', "small i, umlaut mark"),
  eth ("eth", '\u00f0', "small eth, Icelandic"),
  ntilde ("ntilde", '\u00f1', "small n, tilde"),
  ograve ("ograve", '\u00f2', "small o, grave accent"),
  oacute ("oacute", '\u00f3', "small o, acute accent"),
  ocirc ("ocirc", '\u00f4', "small o, circumflex accent"),
  otilde ("otilde", '\u00f5', "small o, tilde"),
  ouml ("ouml", '\u00f6', "small o, umlaut mark"),
  oslash ("oslash", '\u00f8', "small o, slash"),
  ugrave ("ugrave", '\u00f9', "small u, grave accent"),
  uacute ("uacute", '\u00fa', "small u, acute accent"),
  ucirc ("ucirc", '\u00fb', "small u, circumflex accent"),
  uuml ("uuml", '\u00fc', "small u, umlaut mark"),
  yacute ("yacute", '\u00fd', "small y, acute accent"),
  thorn ("thorn", '\u00fe', "small thorn, Icelandic"),
  yuml ("yuml", '\u00ff', "small y, umlaut mark"),

  // Math symbols
  forall ("forall", '\u2200', "for all"),
  part ("part", '\u2202', "part"),
  exist ("exist", '\u2203', "exists"),
  empty ("empty", '\u2205', "empty"),
  nabla ("nabla", '\u2207', "nabla"),
  isin ("isin", '\u2208', "isin"),
  notin ("notin", '\u2209', "notin"),
  ni ("ni", '\u220b', "ni"),
  prod ("prod", '\u220f', "prod"),
  sum ("sum", '\u2211', "sum"),
  minus ("minus", '\u2212', "minus"),
  lowast ("lowast", '\u2217', "lowast"),
  radic ("radic", '\u221a', "square root"),
  prop ("prop", '\u221d', "proportional to"),
  infin ("infin", '\u221e', "infinity"),
  ang ("ang", '\u2220', "angle"),
  and ("and", '\u2227', "and"),
  or ("or", '\u2228', "or"),
  cap ("cap", '\u2229', "cap"),
  cup ("cup", '\u222a', "cup"),
  int_ ("int", '\u222b', "integral"),
  there4 ("there4", '\u2234', "therefore"),
  sim ("sim", '\u223c', "similar to"),
  cong ("cong", '\u2245', "congruent to"),
  asymp ("asymp", '\u2248', "almost equal"),
  ne ("ne", '\u2260', "not equal"),
  equiv ("equiv", '\u2261', "equivalent"),
  le ("le", '\u2264', "less or equal"),
  ge ("ge", '\u2265', "greater or equal"),
  sub ("sub", '\u2282', "subset of"),
  sup ("sup", '\u2283', "superset of"),
  nsub ("nsub", '\u2284', "not subset of"),
  sube ("sube", '\u2286', "subset or equal"),
  supe ("supe", '\u2287', "superset or equal"),
  oplus ("oplus", '\u2295', "circled plus"),
  otimes ("otimes", '\u2297', "circled times"),
  perp ("perp", '\u22a5', "perpendicular"),
  sdot ("sdot", '\u22c5', "dot operator"),

  // Greek letters
  Alpha ("Alpha", '\u0391', "Alpha"),
  Beta ("Beta", '\u0392', "Beta"),
  Gamma ("Gamma", '\u0393', "Gamma"),
  Delta ("Delta", '\u0394', "Delta"),
  Epsilon ("Epsilon", '\u0395', "Epsilon"),
  Zeta ("Zeta", '\u0396', "Zeta"),
  Eta ("Eta", '\u0397', "Eta"),
  Theta ("Theta", '\u0398', "Theta"),
  Iota ("Iota", '\u0399', "Iota"),
  Kappa ("Kappa", '\u039a', "Kappa"),
  Lambda ("Lambda", '\u039b', "Lambda"),
  Mu ("Mu", '\u039c', "Mu"),
  Nu ("Nu", '\u039d', "Nu"),
  Xi ("Xi", '\u039e', "Xi"),
  Omicron ("Omicron", '\u039f', "Omicron"),
  Pi ("Pi", '\u03a0', "Pi"),
  Rho ("Rho", '\u03a1', "Rho"),
  // Sigmaf is undefined!
  Sigma ("Sigma", '\u03a3', "Sigma"),
  Tau ("Tau", '\u03a4', "Tau"),
  Upsilon ("Upsilon", '\u03a5', "Upsilon"),
  Phi ("Phi", '\u03a6', "Phi"),
  Chi ("Chi", '\u03a7', "Chi"),
  Psi ("Psi", '\u03a8', "Psi"),
  Omega ("Omega", '\u03a9', "Omega"),

  alpha ("alpha", '\u03b1', "alpha"),
  beta ("beta", '\u03b2', "beta"),
  gamma ("gamma", '\u03b3', "gamma"),
  delta ("delta", '\u03b4', "delta"),
  epsilon ("epsilon", '\u03b5', "epsilon"),
  zeta ("zeta", '\u03b6', "zeta"),
  eta ("eta", '\u03b7', "eta"),
  theta ("theta", '\u03b8', "theta"),
  iota ("iota", '\u03b9', "iota"),
  kappa ("kappa", '\u03ba', "kappa"),
  lambda ("lambda", '\u03bb', "lambda"),
  mu ("mu", '\u03bc', "mu"),
  nu ("nu", '\u03bd', "nu"),
  xi ("xi", '\u03be', "xi"),
  omicron ("omicron", '\u03bf', "omicron"),
  pi ("pi", '\u03c0', "pi"),
  rho ("rho", '\u03c1', "rho"),
  sigmaf ("sigmaf", '\u03c2', "sigmaf"),
  sigma ("sigma", '\u03c3', "sigma"),
  tau ("tau", '\u03c4', "tau"),
  upsilon ("upsilon", '\u03c5', "upsilon"),
  phi ("phi", '\u03c6', "phi"),
  chi ("chi", '\u03c7', "chi"),
  psi ("psi", '\u03c8', "psi"),
  omega ("omega", '\u03c9', "omega"),

  thetasym ("thetasym", '\u03d1', "theta symbol"),
  upsih ("upsih", '\u03d2', "upsilon symbol"),
  piv ("piv", '\u03d6', "pi symbol"),

  // Letterlike Symbols
  weierp ("weierp", '\u2118', "script capital P = power set = Weierstrass p"),
  image ("image", '\u2111', "blackletter capital I = imaginary part"),
  real ("real", '\u211c', "blackletter capital R = real part symbol"),
  trade ("trade", '\u2122', "trade mark sign"),
  alefsym ("alefsym", '\u2135', "alef symbol = first transfinite cardinal"),

  // Arrows
  larr ("larr", '\u2190', "left arrow"),
  uarr ("uarr", '\u2191', "up arrow"),
  rarr ("rarr", '\u2192', "right arrow"),
  darr ("darr", '\u2193', "down arrow"),
  harr ("harr", '\u2194', "left right arrow"),
  crarr ("crarr", '\u21b5', "carriage return arrow"),
  lArr ("lArr", '\u21d0', "leftwards double arrow"),
  uArr ("uArr", '\u21d1', "upwards double arrow"),
  rArr ("rArr", '\u21d2', "rightwards double arrow"),
  dArr ("dArr", '\u21d3', "downwards double arrow"),
  hArr ("hArr", '\u21d4', "left right double arrow"),

  // Other entities
  OElig ("OElig", '\u0152', "capital ligature OE"),
  oelig ("oelig", '\u0153', "small ligature oe"),
  Scaron ("Scaron", '\u0160', "capital S with caron"),
  scaron ("scaron", '\u0161', "small S with caron"),
  Yuml ("Yuml", '\u0178', "capital Y with diaeres"),
  fnof ("fnof", '\u0192', "f with hook"),
  circ ("circ", '\u02c6', "modifier letter circumflex accent"),
  tilde ("tilde", '\u02dc', "small tilde"),
  ensp ("ensp", '\u2002', "en space"),
  emsp ("emsp", '\u2003', "em space"),
  thinsp ("thinsp", '\u2009', "thin space"),
  zwnj ("zwnj", '\u200c', "zero width non-joiner"),
  zwj ("zwj", '\u200d', "zero width joiner"),
  lrm ("lrm", '\u200e', "left-to-right mark"),
  rlm ("rlm", '\u200f', "right-to-left mark"),
  ndash ("ndash", '\u2013', "en dash"),
  mdash ("mdash", '\u2014', "em dash"),
  lsquo ("lsquo", '\u2018', "left single quotation mark"),
  rsquo ("rsquo", '\u2019', "right single quotation mark"),
  sbquo ("sbquo", '\u201a', "single low-9 quotation mark"),
  ldquo ("ldquo", '\u201c', "left double quotation mark"),
  rdquo ("rdquo", '\u201d', "right double quotation mark"),
  bdquo ("bdquo", '\u201e', "double low-9 quotation mark"),
  dagger ("dagger", '\u2020', "dagger"),
  Dagger ("Dagger", '\u2021', "double dagger"),
  bull ("bull", '\u2022', "bullet"),
  hellip ("hellip", '\u2026', "horizontal ellipsis"),
  permil ("permil", '\u2030', "per mille"),
  prime ("prime", '\u2032', "minutes"),
  Prime ("Prime", '\u2033', "seconds"),
  lsaquo ("lsaquo", '\u2039', "single left angle quotation"),
  rsaquo ("rsaquo", '\u203a', "single right angle quotation"),
  oline ("oline", '\u203e', "overline"),
  euro ("euro", '\u20ac', "euro"),
  lceil ("lceil", '\u2308', "left ceiling"),
  rceil ("rceil", '\u2309', "right ceiling"),
  lfloor ("lfloor", '\u230a', "left floor"),
  rfloor ("rfloor", '\u230b', "right floor"),
  loz ("loz", '\u25ca', "lozenge"),
  spades ("spades", '\u2660', "spade"),
  clubs ("clubs", '\u2663', "club"),
  hearts ("hearts", '\u2665', "heart"),
  diams ("diams", '\u2666', "diamond"),
  frasl ("frasl", '\u2044', "fraction slash"),
  lang ("lang", '\u2329', "left-pointing angle bracket = bra"),
  rang ("rang", '\u232a', "right-pointing angle bracket = ket");

  private static final ICommonsOrderedMap <String, EHTMLEntity> s_aEntityRefToEntityMap = new CommonsLinkedHashMap <> ();
  private static final ICommonsOrderedMap <Character, EHTMLEntity> s_aCharToEntityMap = new CommonsLinkedHashMap <> ();
  private static final ICommonsOrderedMap <String, Character> s_aEntityRefToCharMap = new CommonsLinkedHashMap <> ();
  private static final ICommonsOrderedMap <String, String> s_aEntityRefToCharStringMap = new CommonsLinkedHashMap <> ();
  private static final ICommonsOrderedMap <Character, String> s_aCharToEntityRefMap = new CommonsLinkedHashMap <> ();
  private static final ICommonsOrderedMap <String, String> s_aCharStringToEntityRefMap = new CommonsLinkedHashMap <> ();

  static
  {
    for (final EHTMLEntity e : values ())
    {
      final String sEntityRef = e.m_sEntityReference;
      final Character aChar = e.getCharObj ();

      if (s_aEntityRefToEntityMap.put (sEntityRef, e) != null)
        throw new IllegalStateException ("Another entity reference '" + sEntityRef + "' is already contained!");

      if (s_aCharToEntityMap.put (aChar, e) != null)
        throw new IllegalStateException ("Another entity reference for '" +
                                         "0x" +
                                         StringHelper.getHexStringLeadingZero (e.m_cChar, 4) +
                                         "' is already contained!");

      if (s_aEntityRefToCharMap.put (sEntityRef, aChar) != null)
        throw new IllegalStateException ("Another char for '" + sEntityRef + "' is already contained!");

      if (s_aEntityRefToCharStringMap.put (sEntityRef, aChar.toString ()) != null)
        throw new IllegalStateException ("Another char for '" + sEntityRef + "' is already contained!");

      if (s_aCharToEntityRefMap.put (aChar, sEntityRef) != null)
        throw new IllegalStateException ("Another entity reference for '" +
                                         "0x" +
                                         StringHelper.getHexStringLeadingZero (e.m_cChar, 4) +
                                         "' is already contained!");

      if (s_aCharStringToEntityRefMap.put (aChar.toString (), sEntityRef) != null)
        throw new IllegalStateException ("Another entity reference for '" +
                                         "0x" +
                                         StringHelper.getHexStringLeadingZero (e.m_cChar, 4) +
                                         "' is already contained!");
    }
  }

  private final String m_sEntityName;
  private final String m_sEntityReference;
  private char m_cChar;
  private final String m_sDescription;

  EHTMLEntity (@Nonnull @Nonempty final String sName, final char c, @Nonnull @Nonempty final String sDescription)
  {
    m_sEntityName = sName;
    m_sEntityReference = '&' + sName + ';';
    m_cChar = c;
    m_sDescription = sDescription;
  }

  @Nonnull
  @Nonempty
  public String getEntityName ()
  {
    return m_sEntityName;
  }

  @Nonnull
  @Nonempty
  public String getEntityReference ()
  {
    return m_sEntityReference;
  }

  /**
   * @return The source character matching the entity.
   */
  public char getChar ()
  {
    return m_cChar;
  }

  /**
   * @return The source character object matching the entity. Never
   *         <code>null</code>.
   */
  @Nonnull
  public Character getCharObj ()
  {
    return Character.valueOf (m_cChar);
  }

  /**
   * @return The source character string matching the entity.
   */
  @Nonnull
  public String getCharString ()
  {
    return Character.toString (m_cChar);
  }

  @Nonnull
  @Nonempty
  public String getDescription ()
  {
    return m_sDescription;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sEntityName)
                                       .append ("char", "0x" + StringHelper.getHexStringLeadingZero (m_cChar, 4))
                                       .append ("description", m_sDescription)
                                       .getToString ();
  }

  /**
   * Check if the passed entity reference string is valid.
   *
   * @param sEntityReference
   *        The string to be checked (e.g. <code>"&amp;ndash;"</code>)
   * @return <code>true</code> if it is valid, <code>false</code> if not
   */
  public static boolean isValidEntityReference (@Nullable final String sEntityReference)
  {
    return s_aEntityRefToEntityMap.containsKey (sEntityReference);
  }

  /**
   * Get the predefined HTML entity for the specified entity reference string is
   * valid.
   *
   * @param sEntityReference
   *        The string to be checked (e.g. <code>"&amp;ndash;"</code>)
   * @return <code>null</code> if no such HTML entity is present
   */
  @Nullable
  public static EHTMLEntity getFromEntityReferenceOrNull (@Nullable final String sEntityReference)
  {
    return s_aEntityRefToEntityMap.get (sEntityReference);
  }

  /**
   * Check if the passed character can be presented by an entity reference
   * string.
   *
   * @param c
   *        The char to be checked (e.g. <code>'–'</code>)
   * @return <code>true</code> if an entity representation is present,
   *         <code>false</code> if not
   */
  public static boolean isValidEntityChar (final char c)
  {
    return s_aCharToEntityMap.containsKey (Character.valueOf (c));
  }

  /**
   * Get the predefined HTML entity to be used to represent the passed
   * character.
   *
   * @param c
   *        The char to be checked (e.g. <code>'–'</code>)
   * @return <code>null</code> if no such HTML entity is present
   */
  @Nullable
  public static EHTMLEntity getFromCharOrNull (final char c)
  {
    return s_aCharToEntityMap.get (Character.valueOf (c));
  }

  /**
   * @return The global map from entity reference string to the according entity
   *         (e.g. from <code>"&amp;ndash;"</code> to
   *         <code>EHTMLEntity.ndash</code>). Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <String, EHTMLEntity> getEntityRefToEntityMap ()
  {
    return s_aEntityRefToEntityMap.getClone ();
  }

  /**
   * @return The global map from entity reference string to the according entity
   *         (e.g. from <code>'–'</code> to <code>EHTMLEntity.ndash</code>).
   *         Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <Character, EHTMLEntity> getCharToEntityMap ()
  {
    return s_aCharToEntityMap.getClone ();
  }

  /**
   * @return The global map from entity reference string to the according
   *         character (e.g. from <code>"&amp;ndash;"</code> to <code>'–'</code>
   *         ). Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <String, Character> getEntityRefToCharMap ()
  {
    return s_aEntityRefToCharMap.getClone ();
  }

  /**
   * @return The global map from entity reference string to the according
   *         character as a String (e.g. from <code>"&amp;ndash;"</code> to
   *         <code>"–"</code>). Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <String, String> getEntityRefToCharStringMap ()
  {
    return s_aEntityRefToCharStringMap.getClone ();
  }

  /**
   * @return The global map from character to the according entity reference
   *         string (e.g. from <code>'–'</code> to <code>"&amp;ndash;"</code> ).
   *         Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <Character, String> getCharToEntityRefMap ()
  {
    return s_aCharToEntityRefMap.getClone ();
  }

  /**
   * @return The global map from character string to the according entity
   *         reference string (e.g. from <code>"–"</code> to
   *         <code>"&amp;ndash;"</code> ). Never <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  public static final ICommonsOrderedMap <String, String> getCharStringToEntityRefMap ()
  {
    return s_aCharStringToEntityRefMap.getClone ();
  }

  /**
   * Perform an HTML escape on the passed string. For example the string
   * "abcäöü" is translated to "abc&amp;auml;&amp;ouml;&amp;uuml;"
   *
   * @param sSource
   *        The source string. May be <code>null</code>.
   * @return <code>null</code> if the source string is <code>null</code>.
   */
  @Nullable
  public static String htmlEscape (@Nullable final String sSource)
  {
    if (StringHelper.hasNoText (sSource))
      return sSource;

    return StringHelper.replaceMultiple (sSource, s_aCharStringToEntityRefMap);
  }
}
