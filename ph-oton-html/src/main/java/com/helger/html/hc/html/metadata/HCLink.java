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
package com.helger.html.hc.html.metadata;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.DeprecatedInHTML5;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.embedded.EHCCORSSettings;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;link&gt; element
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCLink extends AbstractHCElement <HCLink>
{
  private IHCLinkType m_aRel;
  private IHCLinkType m_aRev;
  private IMimeType m_aType;
  private ISimpleURL m_aHref;
  private String m_sHrefLang;
  private HC_Target m_aTarget;
  private String m_sCharset;
  private CSSMediaList m_aMediaList;
  private String m_sSizes;
  private ICSSPathProvider m_aCSSPathProvider;
  private EHCCORSSettings m_eCrossOrigin;
  private String m_sIntegrity;

  public HCLink ()
  {
    super (EHTMLElement.LINK);
  }

  /**
   * @return <code>true</code> if this &lt;link&gt;-element is a CSS Stylesheet,
   *         <code>false</code> if not
   */
  public final boolean isCSSLink ()
  {
    return EHCLinkType.STYLESHEET.equals (m_aRel);
  }

  @Nullable
  public final IHCLinkType getRel ()
  {
    return m_aRel;
  }

  @Nonnull
  public final HCLink setRel (@Nullable final IHCLinkType aRel)
  {
    m_aRel = aRel;
    return this;
  }

  @Nullable
  public final IHCLinkType getRev ()
  {
    return m_aRev;
  }

  @Nonnull
  public final HCLink setRev (@Nullable final IHCLinkType aRev)
  {
    m_aRev = aRev;
    return this;
  }

  @Nullable
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public final HCLink setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Nullable
  public final ISimpleURL getHref ()
  {
    return m_aHref;
  }

  @Nonnull
  public final HCLink setHref (@Nullable final ISimpleURL aHref)
  {
    m_aHref = aHref;
    return this;
  }

  @Nullable
  public final String getHrefLang ()
  {
    return m_sHrefLang;
  }

  @Nonnull
  public final HCLink setHrefLang (@Nullable final String sHrefLang)
  {
    m_sHrefLang = sHrefLang;
    return this;
  }

  @Nullable
  @DeprecatedInHTML5
  public final HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  @DeprecatedInHTML5
  public final HCLink setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return this;
  }

  @Nullable
  public final String getCharset ()
  {
    return m_sCharset;
  }

  @Nonnull
  public final HCLink setCharset (@Nullable final String sCharset)
  {
    m_sCharset = sCharset;
    return this;
  }

  @Nullable
  public final ICSSMediaList getMedia ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public final HCLink setMedia (@Nullable final ICSSMediaList aMediaList)
  {
    m_aMediaList = aMediaList == null ? null : new CSSMediaList (aMediaList);
    return this;
  }

  @Nonnull
  private CSSMediaList _ensureMediaListPresent ()
  {
    if (m_aMediaList == null)
      m_aMediaList = new CSSMediaList ();
    return m_aMediaList;
  }

  @Nonnull
  public final HCLink addMedium (@Nonnull final ECSSMedium eMedium)
  {
    _ensureMediaListPresent ().addMedium (eMedium);
    return this;
  }

  @Nonnull
  public final HCLink addMedia (@Nonnull final ICSSMediaList aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList.getAllMedia ())
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public final HCLink addMedia (@Nonnull final Iterable <ECSSMedium> aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList)
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public final HCLink addMedia (@Nonnull final ECSSMedium... aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList)
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public final HCLink removeAllMedia ()
  {
    m_aMediaList = null;
    return this;
  }

  @Nullable
  public final String getSizes ()
  {
    return m_sSizes;
  }

  @Nonnull
  public final HCLink setSizes (@Nullable final String sSizes)
  {
    m_sSizes = sSizes;
    return this;
  }

  @Nullable
  public final ICSSPathProvider getPathProvider ()
  {
    return m_aCSSPathProvider;
  }

  @Nonnull
  public final HCLink setPathProvider (@Nullable final ICSSPathProvider aCSSPathProvider)
  {
    m_aCSSPathProvider = aCSSPathProvider;
    return this;
  }

  @Nullable
  public final EHCCORSSettings getCrossOrigin ()
  {
    return m_eCrossOrigin;
  }

  @Nonnull
  public final HCLink setCrossOrigin (@Nullable final EHCCORSSettings eCrossOrigin)
  {
    m_eCrossOrigin = eCrossOrigin;
    return this;
  }

  @Nullable
  public final String getIntegrity ()
  {
    return m_sIntegrity;
  }

  @Nonnull
  public final HCLink setIntegrity (@Nullable final String sIntegrity)
  {
    m_sIntegrity = sIntegrity;
    return this;
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_aRel != null)
      aElement.setAttribute (CHTMLAttributes.REL, m_aRel);
    if (m_aRev != null)
      aElement.setAttribute (CHTMLAttributes.REV, m_aRev);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_aHref != null)
      aElement.setAttribute (CHTMLAttributes.HREF, m_aHref.getAsStringWithEncodedParameters (aConversionSettings.getCharset ()));
    if (StringHelper.hasText (m_sHrefLang))
      aElement.setAttribute (CHTMLAttributes.HREFLANG, m_sHrefLang);
    if (m_aTarget != null)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget);
    if (StringHelper.hasText (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
    if (m_aMediaList != null && m_aMediaList.hasAnyMedia ())
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_aMediaList.getMediaString ());
    if (StringHelper.hasText (m_sSizes))
      aElement.setAttribute (CHTMLAttributes.SIZES, m_sSizes);
    if (m_eCrossOrigin != null)
      aElement.setAttribute (CHTMLAttributes.CROSSORIGIN, m_eCrossOrigin);
    if (StringHelper.hasText (m_sIntegrity))
      aElement.setAttribute (CHTMLAttributes.INTEGRITY, m_sIntegrity);

    if (aConversionSettings.getHTMLVersion ().isPriorToHTML5 ())
    {
      // May not be self-closed for IE
      aElement.appendText ("");
    }
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Rel", m_aRel)
                            .appendIfNotNull ("Rev", m_aRev)
                            .appendIfNotNull ("Type", m_aType)
                            .appendIfNotNull ("Href", m_aHref)
                            .appendIfNotNull ("HrefLang", m_sHrefLang)
                            .appendIfNotNull ("Target", m_aTarget)
                            .appendIfNotNull ("Charset", m_sCharset)
                            .appendIfNotNull ("MediaList", m_aMediaList)
                            .appendIfNotNull ("Sizes", m_sSizes)
                            .appendIfNotNull ("CSSPathProvider", m_aCSSPathProvider)
                            .appendIfNotNull ("CrossOrigin", m_eCrossOrigin)
                            .appendIfNotNull ("Integrity", m_sIntegrity)
                            .getToString ();
  }

  /**
   * Shortcut to create a &lt;link&gt; element specific to CSS
   *
   * @param aCSSURL
   *        The CSS URL to be referenced
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static HCLink createCSSLink (@Nonnull final ISimpleURL aCSSURL)
  {
    return new HCLink ().setRel (EHCLinkType.STYLESHEET).setType (CMimeType.TEXT_CSS).setHref (aCSSURL);
  }
}
