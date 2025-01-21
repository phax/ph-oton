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
package com.helger.html.hc.html.metadata;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.decl.CSSDeclarationList;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.media.CSSMediaList;
import com.helger.css.media.ECSSMedium;
import com.helger.css.media.ICSSMediaList;
import com.helger.css.writer.CSSWriter;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.annotation.OutOfBandNode;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.EHCStyleInlineMode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNodeWithChildren;
import com.helger.xml.microdom.MicroText;

/**
 * Represents an HTML &lt;style&gt; element
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCStyle extends AbstractHCElement <HCStyle>
{
  /** The default MIME type is text/css */
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_CSS;

  /** By default place inline CSS after script files */
  public static final boolean DEFAULT_EMIT_AFTER_FILES = true;

  private IMimeType m_aType = DEFAULT_TYPE;
  private CSSMediaList m_aMediaList;
  private String m_sContent;
  private EHCStyleInlineMode m_eMode = HCSettings.getStyleInlineMode ();
  private boolean m_bEmitAfterFiles = DEFAULT_EMIT_AFTER_FILES;

  public HCStyle ()
  {
    super (EHTMLElement.STYLE);
  }

  public HCStyle (@Nullable final String sContent)
  {
    this ();
    setStyleContent (sContent);
  }

  public HCStyle (@Nonnull final CascadingStyleSheet aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    this ();
    setStyleContent (aCSS, aSettings);
  }

  public HCStyle (@Nonnull final CSSDeclarationList aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    this ();
    setStyleContent (aCSS, aSettings);
  }

  @Nonnull
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public final HCStyle setType (@Nonnull final IMimeType aType)
  {
    m_aType = ValueEnforcer.notNull (aType, "Type");
    return this;
  }

  @Nullable
  public final ICSSMediaList getMedia ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public final HCStyle setMedia (@Nullable final ICSSMediaList aMediaList)
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
  public final HCStyle addMedium (@Nonnull final ECSSMedium eMedium)
  {
    _ensureMediaListPresent ().addMedium (eMedium);
    return this;
  }

  @Nonnull
  public final HCStyle removeAllMedia ()
  {
    m_aMediaList = null;
    return this;
  }

  /**
   * Check if the passed medium is explicitly specified
   *
   * @param eMedium
   *        The medium to be checked. May be <code>null</code>.
   * @return <code>true</code> if it is contained, <code>false</code> otherwise
   */
  public final boolean containsMedium (@Nullable final ECSSMedium eMedium)
  {
    return m_aMediaList != null && m_aMediaList.containsMedium (eMedium);
  }

  @Nonnegative
  public final int getMediaCount ()
  {
    return m_aMediaList == null ? 0 : m_aMediaList.getMediaCount ();
  }

  public final boolean hasAnyMedia ()
  {
    return m_aMediaList != null && m_aMediaList.hasAnyMedia ();
  }

  public final boolean hasNoMedia ()
  {
    return m_aMediaList == null || !m_aMediaList.hasAnyMedia ();
  }

  /**
   * @return <code>true</code> if no explicit media is defined or if
   *         {@link ECSSMedium#ALL} is contained.
   */
  public final boolean hasNoMediaOrAll ()
  {
    return hasNoMedia () || containsMedium (ECSSMedium.ALL);
  }

  @Nonnull
  public final HCStyle setStyleContent (@Nullable final String sContent)
  {
    m_sContent = sContent;
    return this;
  }

  @Nonnull
  public final HCStyle setStyleContent (@Nonnull final CascadingStyleSheet aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    return setStyleContent (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  @Nonnull
  public final HCStyle setStyleContent (@Nonnull final CSSDeclarationList aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    return setStyleContent (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  /**
   * @return The CSS content. May be <code>null</code>.
   */
  @Nullable
  public final String getStyleContent ()
  {
    return m_sContent;
  }

  @Nonnull
  public final EHCStyleInlineMode getMode ()
  {
    return m_eMode;
  }

  @Nonnull
  public final HCStyle setMode (@Nonnull final EHCStyleInlineMode eMode)
  {
    m_eMode = ValueEnforcer.notNull (eMode, "Mode");
    return this;
  }

  public final boolean isEmitAfterFiles ()
  {
    return m_bEmitAfterFiles;
  }

  @Nonnull
  public final HCStyle setEmitAfterFiles (final boolean bEmitAfterFiles)
  {
    m_bEmitAfterFiles = bEmitAfterFiles;
    return this;
  }

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Don't create style elements with empty content....
    return StringHelper.hasText (m_sContent);
  }

  public static void setInlineStyle (@Nonnull final IMicroNodeWithChildren aElement,
                                     @Nullable final String sContent,
                                     @Nonnull final EHCStyleInlineMode eMode)
  {
    if (StringHelper.hasText (sContent))
      switch (eMode)
      {
        case PLAIN_TEXT:
          aElement.appendText (sContent);
          break;
        case PLAIN_TEXT_NO_ESCAPE:
          if (StringHelper.containsIgnoreCase (sContent, "</script>", Locale.US))
            throw new IllegalArgumentException ("The script text contains a closing script tag!");
          aElement.appendChild (new MicroText (sContent).setEscape (false));
          break;
      }
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (hasAnyMedia ())
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_aMediaList.getMediaString ());
    setInlineStyle (aElement, m_sContent, m_eMode);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("type", m_aType)
                            .appendIfNotNull ("mediaList", m_aMediaList)
                            .append ("content", m_sContent)
                            .append ("mode", m_eMode)
                            .append ("emitAfterFiles", m_bEmitAfterFiles)
                            .getToString ();
  }
}
