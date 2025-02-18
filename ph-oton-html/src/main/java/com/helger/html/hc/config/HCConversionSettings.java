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
package com.helger.html.hc.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ECSSVersion;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCConversionSettings;
import com.helger.html.hc.IHCCustomizer;
import com.helger.html.js.IJSWriterSettings;
import com.helger.html.js.JSWriterSettings;
import com.helger.xml.serialize.write.EXMLIncorrectCharacterHandling;
import com.helger.xml.serialize.write.EXMLSerializeIndent;
import com.helger.xml.serialize.write.IXMLWriterSettings;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * The default implementation of {@link IHCConversionSettings} containing the
 * real settings for HTML output.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HCConversionSettings implements IHCConversionSettings
{
  /** Default indent and align HTML: true */
  public static final boolean DEFAULT_INDENT_AND_ALIGN_HTML = true;
  /** Default CSS version 3.0 */
  public static final ECSSVersion DEFAULT_CSS_VERSION = ECSSVersion.CSS30;
  /** Default indent and align CSS: true */
  public static final boolean DEFAULT_INDENT_AND_ALIGN_CSS = true;
  /** Default indent and align JS: true */
  public static final boolean DEFAULT_INDENT_AND_ALIGN_JS = true;
  /** Default consistency checks: true */
  public static final boolean DEFAULT_CONSISTENCY_CHECKS = true;
  /** Default extract out-of-band nodes: true */
  public static final boolean DEFAULT_EXTRACT_OUT_OF_BAND_NODES = true;

  private EHTMLVersion m_eHTMLVersion;
  private String m_sHTMLNamespaceURI;
  private XMLWriterSettings m_aXMLWriterSettings;
  private CSSWriterSettings m_aCSSWriterSettings;
  private JSWriterSettings m_aJSWriterSettings;
  private boolean m_bConsistencyChecksEnabled;
  private boolean m_bExtractOutOfBandNodes;
  private IHCCustomizer m_aCustomizer;
  private String m_sNonceInlineScript;
  private String m_sNonceInlineStyle;

  @Nonnull
  public static XMLWriterSettings createDefaultXMLWriterSettings (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    final XMLWriterSettings ret = eHTMLVersion.isAtLeastHTML5 () ? XMLWriterSettings.createForHTML5 ()
                                                                 : XMLWriterSettings.createForXHTML ();
    return ret.setIncorrectCharacterHandling (EXMLIncorrectCharacterHandling.DO_NOT_WRITE_LOG_WARNING)
              .setIndent (DEFAULT_INDENT_AND_ALIGN_HTML ? EXMLSerializeIndent.INDENT_AND_ALIGN
                                                        : EXMLSerializeIndent.NONE);
  }

  @Nonnull
  public static CSSWriterSettings createDefaultCSSWriterSettings ()
  {
    return new CSSWriterSettings (DEFAULT_CSS_VERSION).setOptimizedOutput (!DEFAULT_INDENT_AND_ALIGN_CSS);
  }

  @Nonnull
  public static JSWriterSettings createDefaultJSWriterSettings ()
  {
    return new JSWriterSettings ().setIndentAndAlign (DEFAULT_INDENT_AND_ALIGN_JS);
  }

  /**
   * @return The default customizer. Currently <code>null</code>.
   * @see #isDefaultCustomizer(IHCCustomizer)
   */
  @Nullable
  public static IHCCustomizer createDefaultCustomizer ()
  {
    return null;
  }

  /**
   * Check if the passed customizer is a default customizer.
   *
   * @param aCustomizer
   *        Customizer to check.
   * @return <code>true</code> if the passed customizer is the default
   *         customizer, <code>false</code> otherwise.
   * @see #createDefaultCustomizer()
   */
  public static boolean isDefaultCustomizer (@Nullable final IHCCustomizer aCustomizer)
  {
    return aCustomizer == null;
  }

  /**
   * Constructor
   *
   * @param eHTMLVersion
   *        The HTML version to use. May not be <code>null</code>.
   */
  public HCConversionSettings (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    setHTMLVersion (eHTMLVersion);
    setToDefault ();
  }

  /**
   * Copy constructor. Also creates a copy of the {@link XMLWriterSettings} and
   * the {@link CSSWriterSettings}.
   *
   * @param aBase
   *        Object to copy the settings from. May not be <code>null</code>.
   */
  public HCConversionSettings (@Nonnull final IHCConversionSettings aBase)
  {
    this (aBase, aBase.getHTMLVersion ());
  }

  /**
   * Kind of copy constructor. Also creates a copy of the
   * {@link XMLWriterSettings} and the {@link CSSWriterSettings}.
   *
   * @param aBase
   *        Object to copy the settings from. May not be <code>null</code>.
   * @param eHTMLVersion
   *        A different HTML version to use than the one from the base settings
   */
  public HCConversionSettings (@Nonnull final IHCConversionSettings aBase, @Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (aBase, "Base");
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");

    setHTMLVersion (eHTMLVersion);
    m_aXMLWriterSettings = aBase.getMutableXMLWriterSettings ().getClone ();
    m_aCSSWriterSettings = aBase.getMutableCSSWriterSettings ().getClone ();
    m_aJSWriterSettings = aBase.getMutableJSWriterSettings ().getClone ();
    m_bConsistencyChecksEnabled = aBase.areConsistencyChecksEnabled ();
    m_bExtractOutOfBandNodes = aBase.isExtractOutOfBandNodes ();
    m_aCustomizer = aBase.getCustomizer ();
    m_sNonceInlineScript = aBase.getNonceInlineScript ();
    m_sNonceInlineStyle = aBase.getNonceInlineStyle ();
  }

  /**
   * Change the HTML version. Note: this does NOT change the
   * {@link XMLWriterSettings}!
   *
   * @param eHTMLVersion
   *        The HTML version to use.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setHTMLVersion (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");
    m_eHTMLVersion = eHTMLVersion;
    m_sHTMLNamespaceURI = eHTMLVersion.getNamespaceURI ();
    return this;
  }

  @Nonnull
  public EHTMLVersion getHTMLVersion ()
  {
    return m_eHTMLVersion;
  }

  @Nullable
  public String getHTMLNamespaceURI ()
  {
    return m_sHTMLNamespaceURI;
  }

  @Nonnull
  public HCConversionSettings setXMLWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aXMLWriterSettings.setIndent (bOptimized ? EXMLSerializeIndent.NONE
                                               : DEFAULT_INDENT_AND_ALIGN_HTML ? EXMLSerializeIndent.INDENT_AND_ALIGN
                                                                               : EXMLSerializeIndent.NONE);
    // WRITE_TO_FILE_NO_LOG is the quickest version
    m_aXMLWriterSettings.setIncorrectCharacterHandling (bOptimized ? EXMLIncorrectCharacterHandling.WRITE_TO_FILE_NO_LOG
                                                                   : EXMLIncorrectCharacterHandling.DO_NOT_WRITE_LOG_WARNING);
    return this;
  }

  /**
   * Set the XML writer settings to be used. By default values equivalent to
   * {@link XMLWriterSettings#DEFAULT_XML_SETTINGS} are used.
   *
   * @param aXMLWriterSettings
   *        The XML writer settings to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setXMLWriterSettings (@Nonnull final IXMLWriterSettings aXMLWriterSettings)
  {
    ValueEnforcer.notNull (aXMLWriterSettings, "XMLWriterSettings");

    // The objects are cached with indent and no-indent for performance reasons
    m_aXMLWriterSettings = new XMLWriterSettings (aXMLWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject ("Design")
  public XMLWriterSettings getXMLWriterSettings ()
  {
    return m_aXMLWriterSettings;
  }

  @Nonnull
  @ReturnsMutableCopy
  public XMLWriterSettings getMutableXMLWriterSettings ()
  {
    return m_aXMLWriterSettings.getClone ();
  }

  @Nonnull
  public HCConversionSettings setCSSWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aCSSWriterSettings.setOptimizedOutput (bOptimized).setRemoveUnnecessaryCode (bOptimized);
    return this;
  }

  /**
   * Set the CSS writer settings to be used.
   *
   * @param aCSSWriterSettings
   *        The settings. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setCSSWriterSettings (@Nonnull final ICSSWriterSettings aCSSWriterSettings)
  {
    ValueEnforcer.notNull (aCSSWriterSettings, "CSSWriterSettings");

    m_aCSSWriterSettings = new CSSWriterSettings (aCSSWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject ("Design")
  public CSSWriterSettings getCSSWriterSettings ()
  {
    return m_aCSSWriterSettings;
  }

  @Nonnull
  @ReturnsMutableCopy
  public CSSWriterSettings getMutableCSSWriterSettings ()
  {
    return new CSSWriterSettings (m_aCSSWriterSettings);
  }

  @Nonnull
  public HCConversionSettings setJSWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aJSWriterSettings.setMinimumCodeSize (bOptimized);
    return this;
  }

  /**
   * Set the JS formatter settings to be used.
   *
   * @param aJSWriterSettings
   *        The settings. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setJSWriterSettings (@Nonnull final IJSWriterSettings aJSWriterSettings)
  {
    ValueEnforcer.notNull (aJSWriterSettings, "JSWriterSettings");

    m_aJSWriterSettings = new JSWriterSettings (aJSWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject ("Design")
  public JSWriterSettings getJSWriterSettings ()
  {
    return m_aJSWriterSettings;
  }

  @Nonnull
  @ReturnsMutableCopy
  public JSWriterSettings getMutableJSWriterSettings ()
  {
    return m_aJSWriterSettings.getClone ();
  }

  public boolean areConsistencyChecksEnabled ()
  {
    return m_bConsistencyChecksEnabled;
  }

  /**
   * Enable or disable the consistency checks. It is recommended that the
   * consistency checks are only run in debug mode!
   *
   * @param bConsistencyChecksEnabled
   *        The new value.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setConsistencyChecksEnabled (final boolean bConsistencyChecksEnabled)
  {
    m_bConsistencyChecksEnabled = bConsistencyChecksEnabled;
    return this;
  }

  public boolean isExtractOutOfBandNodes ()
  {
    return m_bExtractOutOfBandNodes;
  }

  /**
   * Enable or disable the extraction of out-of-band nodes.
   *
   * @param bExtractOutOfBandNodes
   *        The new value.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setExtractOutOfBandNodes (final boolean bExtractOutOfBandNodes)
  {
    m_bExtractOutOfBandNodes = bExtractOutOfBandNodes;
    return this;
  }

  @Nullable
  public IHCCustomizer getCustomizer ()
  {
    return m_aCustomizer;
  }

  /**
   * Set the global customizer to be used to globally customize created
   * elements.
   *
   * @param aCustomizer
   *        The customizer to be used. May not be <code>null</code>.
   * @return this
   */
  @Nonnull
  public HCConversionSettings setCustomizer (@Nullable final IHCCustomizer aCustomizer)
  {
    m_aCustomizer = aCustomizer;
    return this;
  }

  @Nullable
  public String getNonceInlineScript ()
  {
    return m_sNonceInlineScript;
  }

  /**
   * Set the value of the HTML 'nonce' attribute for inline script elements.
   *
   * @param sNonceInlineScript
   *        The value of the 'nonce' attribute. May be <code>null</code>.
   * @return this
   * @since 9.2.10
   */
  @Nonnull
  public HCConversionSettings setNonceInlineScript (@Nullable final String sNonceInlineScript)
  {
    m_sNonceInlineScript = sNonceInlineScript;
    return this;
  }

  @Nullable
  public String getNonceInlineStyle ()
  {
    return m_sNonceInlineStyle;
  }

  /**
   * Set the value of the HTML 'nonce' attribute for inline style elements.
   *
   * @param sNonceInlineStyle
   *        The value of the 'nonce' attribute. May be <code>null</code>.
   * @return this
   * @since 9.2.10
   */
  @Nonnull
  public HCConversionSettings setNonceInlineStyle (@Nullable final String sNonceInlineStyle)
  {
    m_sNonceInlineStyle = sNonceInlineStyle;
    return this;
  }

  @OverridingMethodsMustInvokeSuper
  @Nonnull
  public HCConversionSettings setToDefault ()
  {
    // There is no such thing as a default HTML version
    m_aXMLWriterSettings = createDefaultXMLWriterSettings (m_eHTMLVersion);
    m_aCSSWriterSettings = createDefaultCSSWriterSettings ();
    m_aJSWriterSettings = createDefaultJSWriterSettings ();
    m_bConsistencyChecksEnabled = DEFAULT_CONSISTENCY_CHECKS;
    m_bExtractOutOfBandNodes = DEFAULT_EXTRACT_OUT_OF_BAND_NODES;
    m_aCustomizer = createDefaultCustomizer ();
    m_sNonceInlineScript = null;
    m_sNonceInlineStyle = null;
    return this;
  }

  @OverridingMethodsMustInvokeSuper
  public HCConversionSettings setToOptimized ()
  {
    setXMLWriterSettingsOptimized (true);
    setCSSWriterSettingsOptimized (true);
    setJSWriterSettingsOptimized (true);
    m_bConsistencyChecksEnabled = false;
    return this;
  }

  @Nonnull
  public HCConversionSettings getClone ()
  {
    return new HCConversionSettings (this);
  }

  @Nonnull
  public HCConversionSettings getClone (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    return new HCConversionSettings (this, eHTMLVersion);
  }

  @Nonnull
  public HCConversionSettings getCloneIfNecessary (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    return m_eHTMLVersion.equals (eHTMLVersion) ? this : getClone (eHTMLVersion);
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("HTMLVersion", m_eHTMLVersion)
                                       .append ("XMLWriterSettings", m_aXMLWriterSettings)
                                       .append ("CSSWriterSettings", m_aCSSWriterSettings)
                                       .append ("JSWriterSettings", m_aJSWriterSettings)
                                       .append ("ConsistencyChecksEnabled", m_bConsistencyChecksEnabled)
                                       .append ("ExtractOutOfBandNodes", m_bExtractOutOfBandNodes)
                                       .appendIfNotNull ("Customizer", m_aCustomizer)
                                       .appendIfNotNull ("NonceInlineScript", m_sNonceInlineScript)
                                       .appendIfNotNull ("NonceInlineStyle", m_sNonceInlineStyle)
                                       .getToString ();
  }
}
