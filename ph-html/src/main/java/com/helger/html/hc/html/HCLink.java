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
package com.helger.html.hc.html;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.IHCCSSNode;
import com.helger.html.hc.api.EHCLinkType;
import com.helger.html.hc.api.IHCLinkType;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;link&gt; element
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCLink extends AbstractHCElement <HCLink> implements IHCCSSNode
{
  private IHCLinkType m_aRel;
  private IHCLinkType m_aRev;
  private IMimeType m_aType;
  private ISimpleURL m_aHref;
  private String m_sHrefLang;
  private HC_Target m_aTarget;
  private String m_sCharset;
  private CSSMediaList m_aMediaList;

  public HCLink ()
  {
    super (EHTMLElement.LINK);
  }

  public boolean isInlineCSS ()
  {
    return false;
  }

  /**
   * @return <code>true</code> if this &lt;link&gt;-element is a CSS Stylesheet,
   *         <code>false</code> if not
   */
  public boolean isCSSLink ()
  {
    return EHCLinkType.STYLESHEET.equals (m_aRel);
  }

  @Nullable
  public IHCLinkType getRel ()
  {
    return m_aRel;
  }

  @Nonnull
  public HCLink setRel (@Nullable final IHCLinkType aRel)
  {
    m_aRel = aRel;
    return this;
  }

  @Nullable
  public IHCLinkType getRev ()
  {
    return m_aRev;
  }

  @Nonnull
  public HCLink setRev (@Nullable final IHCLinkType aRev)
  {
    m_aRev = aRev;
    return this;
  }

  @Nullable
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCLink setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Nullable
  public ISimpleURL getHref ()
  {
    return m_aHref;
  }

  @Nullable
  public String getHrefString ()
  {
    return m_aHref == null ? null : m_aHref.getAsString ();
  }

  @Nonnull
  public HCLink setHref (@Nullable final ISimpleURL aHref)
  {
    m_aHref = aHref;
    return this;
  }

  @Nonnull
  public HCLink setHref (@Nullable final String sHref)
  {
    return setHref (sHref == null ? null : new SimpleURL (sHref));
  }

  @Nullable
  public String getHrefLang ()
  {
    return m_sHrefLang;
  }

  @Nonnull
  public HCLink setHrefLang (@Nullable final String sHrefLang)
  {
    m_sHrefLang = sHrefLang;
    return this;
  }

  @Nullable
  public HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  public HCLink setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return this;
  }

  @Nullable
  public String getCharset ()
  {
    return m_sCharset;
  }

  @Nonnull
  public HCLink setCharset (@Nullable final String sCharset)
  {
    m_sCharset = sCharset;
    return this;
  }

  @Nullable
  public ICSSMediaList getMedia ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public HCLink setMedia (@Nullable final ICSSMediaList aMediaList)
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
  public HCLink addMedium (@Nonnull final ECSSMedium eMedium)
  {
    _ensureMediaListPresent ().addMedium (eMedium);
    return this;
  }

  @Nonnull
  public HCLink addMedia (@Nonnull final ICSSMediaList aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList.getAllMedia ())
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public HCLink addMedia (@Nonnull final Iterable <ECSSMedium> aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList)
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public HCLink addMedia (@Nonnull final ECSSMedium... aMediaList)
  {
    ValueEnforcer.notNull (aMediaList, "MediaList");
    _ensureMediaListPresent ();
    for (final ECSSMedium eMedium : aMediaList)
      m_aMediaList.addMedium (eMedium);
    return this;
  }

  @Nonnull
  public HCLink removeAllMedia ()
  {
    m_aMediaList = null;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_aRel != null)
      aElement.setAttribute (CHTMLAttributes.REL, m_aRel);
    if (m_aRev != null)
      aElement.setAttribute (CHTMLAttributes.REV, m_aRev);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_aHref != null)
      aElement.setAttribute (CHTMLAttributes.HREF, m_aHref.getAsString ());
    if (StringHelper.hasText (m_sHrefLang))
      aElement.setAttribute (CHTMLAttributes.HREFLANG, m_sHrefLang);
    if (m_aTarget != null)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget);
    if (StringHelper.hasText (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
    if (m_aMediaList != null && m_aMediaList.hasAnyMedia ())
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_aMediaList.getMediaString ());

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
                            .appendIfNotNull ("rel", m_aRel)
                            .appendIfNotNull ("rev", m_aRev)
                            .appendIfNotNull ("type", m_aType)
                            .appendIfNotNull ("href", m_aHref)
                            .appendIfNotNull ("hrefLang", m_sHrefLang)
                            .appendIfNotNull ("target", m_aTarget)
                            .appendIfNotNull ("charset", m_sCharset)
                            .appendIfNotNull ("mediaList", m_aMediaList)
                            .toString ();
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

  /**
   * Shortcut to create a &lt;link&gt; element specific to CSS
   *
   * @param sCSSURL
   *        The CSS URL to be referenced
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static HCLink createCSSLink (@Nonnull final String sCSSURL)
  {
    return createCSSLink (new SimpleURL (sCSSURL));
  }
}
