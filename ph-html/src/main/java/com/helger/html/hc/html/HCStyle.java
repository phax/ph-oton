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

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNodeWithChildren;
import com.helger.commons.microdom.impl.MicroText;
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
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.IHCCSSNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;style&gt; element
 *
 * @author Philip Helger
 */
@OutOfBandNode
public class HCStyle extends AbstractHCElement <HCStyle> implements IHCCSSNode
{
  public static enum EMode
  {
    /**
     * Emit JS code as plain text, but XML masked
     */
    PLAIN_TEXT,
    /**
     * Emit JS code as plain text, but without XML masking
     */
    PLAIN_TEXT_NO_ESCAPE;
  }

  /** By default plain text without escape is used */
  public static final EMode DEFAULT_MODE = EMode.PLAIN_TEXT_NO_ESCAPE;
  /** The default MIME type is text/css */
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_CSS;

  /** By default place inline CSS after script files */
  public static final boolean DEFAULT_EMIT_AFTER_FILES = true;

  private static final Logger s_aLogger = LoggerFactory.getLogger (HCStyle.class);

  private static EMode s_eDefaultMode = DEFAULT_MODE;

  private IMimeType m_aType = DEFAULT_TYPE;
  private CSSMediaList m_aMediaList;
  private String m_sContent;
  private EMode m_eMode = s_eDefaultMode;
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

  public boolean isInlineCSS ()
  {
    return true;
  }

  @Nonnull
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public HCStyle setType (@Nonnull final IMimeType aType)
  {
    m_aType = ValueEnforcer.notNull (aType, "Type");
    return this;
  }

  @Nullable
  public ICSSMediaList getMedia ()
  {
    return m_aMediaList;
  }

  @Nonnull
  public HCStyle setMedia (@Nullable final ICSSMediaList aMediaList)
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
  public HCStyle addMedium (@Nonnull final ECSSMedium eMedium)
  {
    _ensureMediaListPresent ().addMedium (eMedium);
    return this;
  }

  @Nonnull
  public HCStyle removeAllMedia ()
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
  public boolean containsMedium (@Nullable final ECSSMedium eMedium)
  {
    return m_aMediaList != null && m_aMediaList.containsMedium (eMedium);
  }

  @Nonnegative
  public int getMediaCount ()
  {
    return m_aMediaList == null ? 0 : m_aMediaList.size ();
  }

  public boolean hasAnyMedia ()
  {
    return m_aMediaList != null && m_aMediaList.hasAnyMedia ();
  }

  public boolean hasNoMedia ()
  {
    return m_aMediaList == null || !m_aMediaList.hasAnyMedia ();
  }

  /**
   * @return <code>true</code> if no explicit media is defined or if
   *         {@link ECSSMedium#ALL} is contained.
   */
  public boolean hasNoMediaOrAll ()
  {
    return hasNoMedia () || containsMedium (ECSSMedium.ALL);
  }

  @Nonnull
  public HCStyle setStyleContent (@Nullable final String sContent)
  {
    m_sContent = sContent;
    return this;
  }

  @Nonnull
  public HCStyle setStyleContent (@Nonnull final CascadingStyleSheet aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    return setStyleContent (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  @Nonnull
  public HCStyle setStyleContent (@Nonnull final CSSDeclarationList aCSS, @Nonnull final CSSWriterSettings aSettings)
  {
    return setStyleContent (new CSSWriter (aSettings).getCSSAsString (aCSS));
  }

  /**
   * @return The CSS content. May be <code>null</code>.
   */
  @Nullable
  public String getStyleContent ()
  {
    return m_sContent;
  }

  @Nonnull
  public EMode getMode ()
  {
    return m_eMode;
  }

  @Nonnull
  public HCStyle setMode (@Nonnull final EMode eMode)
  {
    m_eMode = ValueEnforcer.notNull (eMode, "Mode");
    return this;
  }

  public boolean isEmitAfterFiles ()
  {
    return m_bEmitAfterFiles;
  }

  @Nonnull
  public HCStyle setEmitAfterFiles (final boolean bEmitAfterFiles)
  {
    m_bEmitAfterFiles = bEmitAfterFiles;
    return this;
  }

  @Override
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Don't create style elements with empty content....
    return StringHelper.hasText (m_sContent);
  }

  public static void setInlineStyle (@Nonnull final IMicroNodeWithChildren aElement,
                                     @Nullable final String sContent,
                                     @Nonnull final EMode eMode)
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
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
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
                            .toString ();
  }

  /**
   * Set how the content of style elements should be emitted. This only affects
   * new built objects, and does not alter existing objects! The default mode is
   * {@link #DEFAULT_MODE}.
   *
   * @param eMode
   *        The new mode to set. May not be <code>null</code>.
   */
  public static void setDefaultMode (@Nonnull final EMode eMode)
  {
    ValueEnforcer.notNull (eMode, "mode");
    s_eDefaultMode = eMode;
    s_aLogger.info ("Default <style> mode set to " + eMode);
  }

  /**
   * @return The default mode to emit style content. Never <code>null</code>.
   */
  @Nonnull
  public static EMode getDefaultMode ()
  {
    return s_eDefaultMode;
  }
}
