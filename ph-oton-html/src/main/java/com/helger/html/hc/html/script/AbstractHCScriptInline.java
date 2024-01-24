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
package com.helger.html.hc.html.script;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.EHCScriptInlineMode;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.js.IHasJSCode;
import com.helger.html.js.IHasJSCodeWithSettings;
import com.helger.html.js.IJSWriterSettings;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.IMicroNodeWithChildren;
import com.helger.xml.microdom.MicroText;

/**
 * This class represents an HTML &lt;script&gt; element with inline JS content.
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCScriptInline <IMPLTYPE extends AbstractHCScriptInline <IMPLTYPE>> extends
                                             AbstractHCScript <IMPLTYPE> implements
                                             IHCScriptInline <IMPLTYPE>
{
  /** By default place inline JS after script files */
  public static final boolean DEFAULT_EMIT_AFTER_FILES = true;

  private IHasJSCode m_aJSProvider;
  private EHCScriptInlineMode m_eScriptMode = HCSettings.getScriptInlineMode ();
  private boolean m_bEmitAfterFiles = DEFAULT_EMIT_AFTER_FILES;

  private transient String m_sCachedJSCode;

  protected AbstractHCScriptInline ()
  {
    super ();
  }

  protected AbstractHCScriptInline (@Nonnull final IHasJSCode aProvider)
  {
    this ();
    setJSCodeProvider (aProvider);
  }

  /**
   * @return The JS code passed in the constructor. Never <code>null</code>.
   */
  @Nonnull
  public final IHasJSCode getJSCodeProvider ()
  {
    return m_aJSProvider;
  }

  @Nonnull
  public final IMPLTYPE setJSCodeProvider (@Nonnull final IHasJSCode aProvider)
  {
    m_aJSProvider = ValueEnforcer.notNull (aProvider, "Provider");
    return thisAsT ();
  }

  /**
   * @param aSettings
   *        The settings to be used. May be <code>null</code> to use the
   *        default.
   * @return The text representation of the JS code passed in the constructor.
   *         May be <code>null</code>.
   */
  @Nullable
  public final String getJSCode (@Nonnull final IJSWriterSettings aSettings)
  {
    if (m_aJSProvider == null)
      return null;

    if (m_aJSProvider instanceof IHasJSCodeWithSettings)
      return ((IHasJSCodeWithSettings) m_aJSProvider).getJSCode (aSettings);
    return m_aJSProvider.getJSCode ();
  }

  /**
   * @return The masking mode. Never <code>null</code>.
   */
  @Nonnull
  public final EHCScriptInlineMode getMode ()
  {
    return m_eScriptMode;
  }

  /**
   * Set the masking mode.
   *
   * @param eMode
   *        The mode to use. MAy not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public final IMPLTYPE setMode (@Nonnull final EHCScriptInlineMode eMode)
  {
    m_eScriptMode = ValueEnforcer.notNull (eMode, "Mode");
    return thisAsT ();
  }

  public final boolean isEmitAfterFiles ()
  {
    return m_bEmitAfterFiles;
  }

  @Nonnull
  public final IMPLTYPE setEmitAfterFiles (final boolean bEmitAfterFiles)
  {
    m_bEmitAfterFiles = bEmitAfterFiles;
    return thisAsT ();
  }

  public static void setInlineScript (@Nonnull final IMicroNodeWithChildren aElement,
                                      @Nullable final String sContent,
                                      @Nonnull final EHCScriptInlineMode eMode,
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
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    if (m_sCachedJSCode == null)
      m_sCachedJSCode = StringHelper.trim (getJSCode (aConversionSettings.getJSWriterSettings ()));
    // Don't create script elements with empty content....
    return StringHelper.hasText (m_sCachedJSCode);
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);

    // m_sJSCode is set in canConvertToNode which is called before this method!
    setInlineScript (aElement,
                     m_sCachedJSCode,
                     m_eScriptMode,
                     aConversionSettings.getXMLWriterSettings ().getNewLineString ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("JSProvider", m_aJSProvider)
                            .append ("ScriptMode", m_eScriptMode)
                            .append ("EmitAfterFiles", m_bEmitAfterFiles)
                            .append ("CachedJSCode", m_sCachedJSCode)
                            .getToString ();
  }
}
