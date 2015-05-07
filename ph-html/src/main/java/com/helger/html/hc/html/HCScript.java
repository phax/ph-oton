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
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.IMicroNodeWithChildren;
import com.helger.commons.microdom.impl.MicroText;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.system.ENewLineMode;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.html.annotations.OutOfBandNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.js.IJSCodeProvider;
import com.helger.html.js.provider.IJSCodeProviderWithSettings;
import com.helger.html.js.provider.UnparsedJSCodeProvider;
import com.helger.html.js.writer.IJSWriterSettings;

/**
 * This class represents an HTML &lt;script&gt; element with inline JS content.
 *
 * @author Philip Helger
 * @see HCScriptFile
 * @see HCScriptOnDocumentReady
 */
@OutOfBandNode
public class HCScript extends AbstractHCScript <HCScript>
{
  public static enum EMode
  {
    /**
     * Emit JS code as plain text, but XML masked. The XML masking rules for
     * text nodes apply.
     *
     * <pre>
     * &lt;script&gt;my &amp;lt; script&lt;/script&gt;
     * </pre>
     */
    PLAIN_TEXT,
    /**
     * Emit JS code as plain text, but without XML masking.
     *
     * <pre>
     * &lt;script&gt;my &lt; script&lt;/script&gt;
     * </pre>
     */
    PLAIN_TEXT_NO_ESCAPE,
    /**
     * Wrap the whole JS code as plain text in XML comments.
     *
     * <pre>
     * &lt;script&gt;&lt;!--
     * my &lt; script
     * //--&gt;&lt;/script&gt;
     * </pre>
     */
    PLAIN_TEXT_WRAPPED_IN_COMMENT,
    /**
     * Wrap the whole JS code in an XML CDATA container.
     *
     * <pre>
     * &lt;script&gt;&lt;![CDATA[my &lt; script]]&gt;&lt;/script&gt;
     * </pre>
     */
    CDATA,
    /**
     * Wrap the whole JS code in an XML CDATA container inside a JS comment
     * Tested OK with FF6, Opera11, Chrome13, IE8, IE9
     *
     * <pre>
     * &lt;script&gt;//&lt;![CDATA[
     * my &lt; script
     * //]]&gt;&lt;/script&gt;
     * </pre>
     */
    CDATA_IN_COMMENT;
  }

  /** By default inline scripts are emitted in mode "wrap in comment" */
  public static final EMode DEFAULT_MODE = EMode.PLAIN_TEXT_WRAPPED_IN_COMMENT;

  /** By default place inline JS after script files */
  public static final boolean DEFAULT_EMIT_AFTER_FILES = true;

  @Deprecated
  public static final String DEFAULT_LINE_SEPARATOR = XMLWriterSettings.DEFAULT_NEWLINE_STRING;

  private static final Logger s_aLogger = LoggerFactory.getLogger (HCScript.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static EMode s_eDefaultMode = DEFAULT_MODE;
  @GuardedBy ("s_aRWLock")
  private static ENewLineMode s_eDefaultNewLineMode = ENewLineMode.DEFAULT;

  private IJSCodeProvider m_aProvider;
  private String m_sJSCode;
  private EMode m_eMode = getDefaultMode ();
  private boolean m_bEmitAfterFiles = DEFAULT_EMIT_AFTER_FILES;
  private ENewLineMode m_eNewLineMode = getDefaultNewLineMode ();

  public HCScript ()
  {
    super ();
  }

  public HCScript (@Nonnull final IJSCodeProvider aProvider)
  {
    this ();
    setJSCodeProvider (aProvider);
  }

  @DevelopersNote ("Handle with care!")
  public HCScript (@Nonnull final String sJSCode)
  {
    this ();
    setJSCode (sJSCode);
  }

  public boolean isInlineJS ()
  {
    return true;
  }

  @Nonnull
  public HCScript setJSCodeProvider (@Nonnull final IJSCodeProvider aProvider)
  {
    m_aProvider = ValueEnforcer.notNull (aProvider, "Provider");
    return this;
  }

  @Nonnull
  @DevelopersNote ("Handle with care!")
  public HCScript setJSCode (@Nonnull final String sJSCode)
  {
    return setJSCodeProvider (new UnparsedJSCodeProvider (sJSCode));
  }

  /**
   * @return The JS code passed in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public IJSCodeProvider getJSCodeProvider ()
  {
    return m_aProvider;
  }

  /**
   * @param aSettings
   *        The settings to be used. May be <code>null</code> to use the
   *        default.
   * @return The text representation of the JS code passed in the constructor.
   *         May be <code>null</code>.
   */
  @Nullable
  public String getJSCode (@Nonnull final IJSWriterSettings aSettings)
  {
    if (m_aProvider instanceof IJSCodeProviderWithSettings)
      return ((IJSCodeProviderWithSettings) m_aProvider).getJSCode (aSettings);
    return m_aProvider.getJSCode ();
  }

  /**
   * @return The masking mode. Never <code>null</code>.
   */
  @Nonnull
  public EMode getMode ()
  {
    return m_eMode;
  }

  /**
   * Set the masking mode.
   *
   * @param eMode
   *        The mode to use. MAy not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCScript setMode (@Nonnull final EMode eMode)
  {
    m_eMode = ValueEnforcer.notNull (eMode, "Mode");
    return this;
  }

  public boolean isEmitAfterFiles ()
  {
    return m_bEmitAfterFiles;
  }

  @Nonnull
  public HCScript setEmitAfterFiles (final boolean bEmitAfterFiles)
  {
    m_bEmitAfterFiles = bEmitAfterFiles;
    return this;
  }

  @Nonnull
  public ENewLineMode getNewLineMode ()
  {
    return m_eNewLineMode;
  }

  @Nonnull
  @Nonempty
  public String getNewLineString ()
  {
    return m_eNewLineMode.getText ();
  }

  @Nonnull
  public HCScript setNewLineMode (@Nonnull final ENewLineMode eNewLineMode)
  {
    m_eNewLineMode = ValueEnforcer.notNull (eNewLineMode, "NewLineMode");
    return this;
  }

  @Nonnull
  @Nonempty
  @Deprecated
  public String getLineSeparator ()
  {
    return getNewLineString ();
  }

  @Nonnull
  @Deprecated
  public HCScript setLineSeparator (@Nonnull @Nonempty final String sLineSeparator)
  {
    return setNewLineMode (ENewLineMode.getFromTextOrDefault (sLineSeparator, ENewLineMode.DEFAULT));
  }

  public static void setInlineScript (@Nonnull final IMicroNodeWithChildren aElement,
                                      @Nullable final String sContent,
                                      @Nonnull final EMode eMode,
                                      @Nonnull final String sLineSeparator)
  {
    if (StringHelper.hasText (sContent))
      switch (eMode)
      {
        case PLAIN_TEXT:
          aElement.appendText (sContent);
          break;
        case PLAIN_TEXT_NO_ESCAPE:
          if (StringHelper.containsIgnoreCase (sContent, "</script>", Locale.US))
            throw new IllegalArgumentException ("The script text contains a closing script tag: " + sContent);
          aElement.appendChild (new MicroText (sContent).setEscape (false));
          break;
        case PLAIN_TEXT_WRAPPED_IN_COMMENT:
          if (StringHelper.getLastChar (sContent) == '\n')
            aElement.appendComment (sLineSeparator + sContent + "//");
          else
            aElement.appendComment (sLineSeparator + sContent + sLineSeparator + "//");
          break;
        case CDATA:
          aElement.appendCDATA (sContent);
          break;
        case CDATA_IN_COMMENT:
          aElement.appendText ("//");
          if (StringHelper.getLastChar (sContent) == '\n')
            aElement.appendCDATA (sLineSeparator + sContent + "//");
          else
            aElement.appendCDATA (sLineSeparator + sContent + sLineSeparator + "//");
          break;
        default:
          throw new IllegalArgumentException ("Illegal mode: " + eMode);
      }
  }

  @Override
  public boolean canConvertToNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    m_sJSCode = StringHelper.trim (getJSCode (aConversionSettings.getJSWriterSettings ()));
    // Don't create script elements with empty content....
    return StringHelper.hasText (m_sJSCode);
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);

    // m_sJSCode is set in canConvertToNode which is called before this method!
    setInlineScript (aElement, m_sJSCode, m_eMode, getNewLineString ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("provider", m_aProvider)
                            .append ("jsCode", m_sJSCode)
                            .append ("mode", m_eMode)
                            .append ("emitAfterFiles", m_bEmitAfterFiles)
                            .append ("NewLineMode", m_eNewLineMode)
                            .toString ();
  }

  /**
   * @return The default masking mode to emit script content. Never
   *         <code>null</code>.
   */
  @Nonnull
  public static EMode getDefaultMode ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_eDefaultMode;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Set how the content of script elements should be emitted. This only affects
   * new built objects, and does not alter existing objects! The default mode is
   * {@link #DEFAULT_MODE}.
   *
   * @param eMode
   *        The new masking mode to set. May not be <code>null</code>.
   */
  public static void setDefaultMode (@Nonnull final EMode eMode)
  {
    ValueEnforcer.notNull (eMode, "Mode");

    s_aRWLock.writeLock ().lock ();
    try
    {
      if (s_eDefaultMode != eMode)
      {
        s_eDefaultMode = eMode;
        s_aLogger.info ("Default <script> mode set to " + eMode);
      }
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  public static ENewLineMode getDefaultNewLineMode ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_eDefaultNewLineMode;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setDefaultNewLineMode (@Nonnull final ENewLineMode eNewLineMode)
  {
    ValueEnforcer.notNull (eNewLineMode, "NewLineMode");

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_eDefaultNewLineMode = eNewLineMode;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @Nonempty
  @Deprecated
  public static String getDefaultLineSeparator ()
  {
    return getDefaultNewLineMode ().getText ();
  }

  @Deprecated
  public static void setDefaultLineSeparator (@Nonnull @Nonempty final String sDefaultLineSeparator)
  {
    setDefaultNewLineMode (ENewLineMode.getFromTextOrDefault (sDefaultLineSeparator, ENewLineMode.DEFAULT));
  }
}
