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

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.CGlobal;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.log.ConditionalLogger;
import com.helger.base.spi.ServiceLoaderHelper;
import com.helger.base.system.ENewLineMode;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.IHCConversionSettings;

import jakarta.annotation.Nonnull;

/**
 * Global HTML Control settings
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class HCSettings
{
  /** Default HTML version is HTML5 */
  public static final EHTMLVersion DEFAULT_HTML_VERSION = EHTMLVersion.HTML5;

  /** Default auto-complete for password fields: only in debug mode */
  public static final boolean DEFAULT_AUTO_COMPLETE_OFF_FOR_PASSWORD_EDITS = !GlobalDebug.isDebugMode ();

  /** By default inline scripts are emitted in mode "plain text" */
  public static final EHCScriptInlineMode DEFAULT_SCRIPT_INLINE_MODE = EHCScriptInlineMode.PLAIN_TEXT_NO_ESCAPE;

  /** By default plain text without escape is used */
  public static final EHCStyleInlineMode DEFAULT_STYLE_MODE = EHCStyleInlineMode.PLAIN_TEXT_NO_ESCAPE;

  /** By default OOB node debugging is disabled */
  public static final boolean DEFAULT_OUT_OF_BAND_NODE_DEBUGGING = false;

  /** By default script tags are placed in the HTML body */
  public static final boolean DEFAULT_SCRIPTS_IN_BODY = true;

  /** By default nonce attributes in scripts are disabled */
  public static final boolean DEFAULT_USE_NONCE_ATTRIBUTES_IN_SCRIPTS = false;

  /** By default nonce attributes in styles are disabled */
  public static final boolean DEFAULT_USE_NONCE_ATTRIBUTES_IN_STYLES = false;

  /**
   * By default in debug mode, regular resources are used, and in non-debug mode minified resources
   * are used
   */
  public static final boolean DEFAULT_USE_REGULAR_RESOURCES = GlobalDebug.isDebugMode ();

  private static final Logger LOGGER = LoggerFactory.getLogger (HCSettings.class);
  private static final ConditionalLogger CONDLOG = new ConditionalLogger (LOGGER, !GlobalDebug.DEFAULT_SILENT_MODE);

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();

  /** HC to MicroNode converter settings */
  @GuardedBy ("RW_LOCK")
  private static HCConversionSettings s_aConversionSettings = new HCConversionSettings (DEFAULT_HTML_VERSION);

  /**
   * For security reasons, the password should not be auto-filled by the browser in the
   * release-version
   */
  @GuardedBy ("RW_LOCK")
  private static boolean s_bAutoCompleteOffForPasswordEdits = DEFAULT_AUTO_COMPLETE_OFF_FOR_PASSWORD_EDITS;

  @GuardedBy ("RW_LOCK")
  private static int s_nTextAreaDefaultRows = CGlobal.ILLEGAL_UINT;

  /** The "on document ready" code provider */
  @GuardedBy ("RW_LOCK")
  private static IHCOnDocumentReadyProvider s_aOnDocumentReadyProvider = new DefaultHCOnDocumentReadyProvider ();

  @GuardedBy ("RW_LOCK")
  private static EHCScriptInlineMode s_eScriptInlineMode = DEFAULT_SCRIPT_INLINE_MODE;

  @GuardedBy ("RW_LOCK")
  private static EHCStyleInlineMode s_eStyleInlineMode = DEFAULT_STYLE_MODE;

  @GuardedBy ("RW_LOCK")
  private static ENewLineMode s_eNewLineMode = ENewLineMode.DEFAULT;

  @GuardedBy ("RW_LOCK")
  private static boolean s_bOOBDebugging = DEFAULT_OUT_OF_BAND_NODE_DEBUGGING;

  @GuardedBy ("RW_LOCK")
  private static boolean s_bScriptsInBody = DEFAULT_SCRIPTS_IN_BODY;

  /** Use regular or minified resources */
  @GuardedBy ("RW_LOCK")
  private static boolean s_bUseRegularResources = DEFAULT_USE_REGULAR_RESOURCES;

  @GuardedBy ("RW_LOCK")
  private static boolean s_bUseNonceInScript = DEFAULT_USE_NONCE_ATTRIBUTES_IN_SCRIPTS;

  @GuardedBy ("RW_LOCK")
  private static boolean s_bUseNonceInStyle = DEFAULT_USE_NONCE_ATTRIBUTES_IN_STYLES;

  static
  {
    // Apply all SPI settings providers
    for (final IHCSettingsProviderSPI aSPI : ServiceLoaderHelper.getAllSPIImplementations (IHCSettingsProviderSPI.class))
      aSPI.initHCSettings ();
  }

  private HCSettings ()
  {}

  /**
   * @return <code>true</code> if logging is disabled, <code>false</code> if it is enabled.
   */
  public static boolean isSilentMode ()
  {
    return CONDLOG.isDisabled ();
  }

  /**
   * Enable or disable certain regular log messages.
   *
   * @param bSilentMode
   *        <code>true</code> to disable logging, <code>false</code> to enable logging
   * @return The previous value of the silent mode.
   */
  public static boolean setSilentMode (final boolean bSilentMode)
  {
    return !CONDLOG.setEnabled (!bSilentMode);
  }

  /**
   * Set the global conversion settings.
   *
   * @param aConversionSettings
   *        The object to be used. May not be <code>null</code>.
   */
  public static void setConversionSettings (@Nonnull final HCConversionSettings aConversionSettings)
  {
    ValueEnforcer.notNull (aConversionSettings, "ConversionSettings");

    RW_LOCK.writeLocked ( () -> s_aConversionSettings = aConversionSettings);
  }

  /**
   * @return The global mutable conversion settings. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public static HCConversionSettings getMutableConversionSettings ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aConversionSettings);
  }

  /**
   * @return The global read-only non-<code>null</code> conversion settings
   */
  @Nonnull
  public static IHCConversionSettings getConversionSettings ()
  {
    return getMutableConversionSettings ();
  }

  /**
   * @return The global read-only non-<code>null</code> conversion settings with XML namespaces
   *         disabled
   */
  @Nonnull
  public static HCConversionSettings getConversionSettingsWithoutNamespaces ()
  {
    // Create a copy!!
    final HCConversionSettings aCS = getMutableConversionSettings ().getClone ();
    // And modify the copied XML settings
    aCS.getXMLWriterSettings ().setEmitNamespaces (false);
    return aCS;
  }

  /**
   * Get the {@link Charset} that is used to create the HTML code.
   *
   * @return The non-<code>null</code> Charset object
   */
  @Nonnull
  public static Charset getHTMLCharset ()
  {
    return getConversionSettings ().getXMLWriterSettings ().getCharset ();
  }

  /**
   * Set the default HTML version to use. This sets the HTML version in the {@link HCSettings} class
   * and performs some additional modifications depending on the chosen version.
   *
   * @param eHTMLVersion
   *        The HTML version to use. May not be <code>null</code>.
   */
  public static void setDefaultHTMLVersion (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    ValueEnforcer.notNull (eHTMLVersion, "HTMLVersion");

    // Update the HCSettings
    final EHTMLVersion eOldVersion = getConversionSettings ().getHTMLVersion ();
    getMutableConversionSettings ().setHTMLVersion (eHTMLVersion);

    // Update the XMLWriterSettings
    getMutableConversionSettings ().setXMLWriterSettings (HCConversionSettings.createDefaultXMLWriterSettings (eHTMLVersion));

    if (!eHTMLVersion.equals (eOldVersion))
      CONDLOG.info ( () -> "Default HTML version changed from " + eOldVersion + " to " + eHTMLVersion);
    if (eHTMLVersion.isAtLeastHTML5 ())
    {
      // No need to put anything in a comment
      if (getScriptInlineMode () != EHCScriptInlineMode.PLAIN_TEXT_NO_ESCAPE)
        setScriptInlineMode (EHCScriptInlineMode.PLAIN_TEXT_NO_ESCAPE);
    }
    else
    {
      // Use default mode
      if (getScriptInlineMode () != DEFAULT_SCRIPT_INLINE_MODE)
        setScriptInlineMode (DEFAULT_SCRIPT_INLINE_MODE);
    }
  }

  public static boolean isAutoCompleteOffForPasswordEdits ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bAutoCompleteOffForPasswordEdits);
  }

  public static void setAutoCompleteOffForPasswordEdits (final boolean bAutoCompleteOffForPasswordEdits)
  {
    RW_LOCK.writeLocked ( () -> s_bAutoCompleteOffForPasswordEdits = bAutoCompleteOffForPasswordEdits);
    CONDLOG.info ( () -> "Default @autocomplete for <input type=password> set to " +
                         (bAutoCompleteOffForPasswordEdits ? "off" : "on"));
  }

  public static int getTextAreaDefaultRows ()
  {
    return RW_LOCK.readLockedInt ( () -> s_nTextAreaDefaultRows);
  }

  public static void setTextAreaDefaultRows (final int nTextAreaDefaultRows)
  {
    RW_LOCK.writeLocked ( () -> s_nTextAreaDefaultRows = nTextAreaDefaultRows);
    CONDLOG.info ( () -> "Default <textarea> rows set to " + nTextAreaDefaultRows);
  }

  @Nonnull
  public static IHCOnDocumentReadyProvider getOnDocumentReadyProvider ()
  {
    return RW_LOCK.readLockedGet ( () -> s_aOnDocumentReadyProvider);
  }

  public static void setOnDocumentReadyProvider (@Nonnull final IHCOnDocumentReadyProvider aOnDocumentReadyProvider)
  {
    ValueEnforcer.notNull (aOnDocumentReadyProvider, "OnDocumentReadyProvider");

    RW_LOCK.writeLocked ( () -> s_aOnDocumentReadyProvider = aOnDocumentReadyProvider);
    CONDLOG.info ( () -> "Default JS onDocumentReady provider set to " + aOnDocumentReadyProvider);
  }

  /**
   * @return The default masking mode to emit script content. Never <code>null</code>.
   */
  @Nonnull
  public static EHCScriptInlineMode getScriptInlineMode ()
  {
    return RW_LOCK.readLockedGet ( () -> s_eScriptInlineMode);
  }

  /**
   * Set how the content of script elements should be emitted. This only affects new built objects,
   * and does not alter existing objects! The default mode is {@link #DEFAULT_SCRIPT_INLINE_MODE}.
   *
   * @param eMode
   *        The new masking mode to set. May not be <code>null</code>.
   */
  public static void setScriptInlineMode (@Nonnull final EHCScriptInlineMode eMode)
  {
    ValueEnforcer.notNull (eMode, "Mode");

    final EHCScriptInlineMode eOld = getScriptInlineMode ();
    RW_LOCK.writeLocked ( () -> s_eScriptInlineMode = eMode);
    if (!eMode.equals (eOld))
      CONDLOG.info ( () -> "Default <script> mode changed from " + eOld + " to " + eMode);
  }

  /**
   * @return The default mode to emit style content. Never <code>null</code>.
   */
  @Nonnull
  public static EHCStyleInlineMode getStyleInlineMode ()
  {
    return RW_LOCK.readLockedGet ( () -> s_eStyleInlineMode);
  }

  /**
   * Set how the content of style elements should be emitted. This only affects new built objects,
   * and does not alter existing objects! The default mode is {@link #DEFAULT_STYLE_MODE}.
   *
   * @param eStyleInlineMode
   *        The new mode to set. May not be <code>null</code>.
   */
  public static void setStyleInlineMode (@Nonnull final EHCStyleInlineMode eStyleInlineMode)
  {
    ValueEnforcer.notNull (eStyleInlineMode, "mode");

    final EHCStyleInlineMode eOld = getStyleInlineMode ();
    RW_LOCK.writeLocked ( () -> s_eStyleInlineMode = eStyleInlineMode);
    if (!eStyleInlineMode.equals (eOld))
      CONDLOG.info ( () -> "Default <style> mode changed from " + eOld + " to " + eStyleInlineMode);
  }

  @Nonnull
  public static ENewLineMode getNewLineMode ()
  {
    return RW_LOCK.readLockedGet ( () -> s_eNewLineMode);
  }

  public static void setNewLineMode (@Nonnull final ENewLineMode eNewLineMode)
  {
    ValueEnforcer.notNull (eNewLineMode, "NewLineMode");

    final ENewLineMode eOld = getNewLineMode ();
    RW_LOCK.writeLocked ( () -> s_eNewLineMode = eNewLineMode);
    if (!eNewLineMode.equals (eOld))
      CONDLOG.info ( () -> "Default new line mode changed from " + eOld + " to " + eNewLineMode);
  }

  public static boolean isOutOfBandDebuggingEnabled ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bOOBDebugging);
  }

  public static void setOutOfBandDebuggingEnabled (final boolean bEnabled)
  {
    RW_LOCK.writeLocked ( () -> s_bOOBDebugging = bEnabled);
    CONDLOG.info ( () -> "Default out-of-band debugging " + (bEnabled ? "enabled" : "disabled"));
  }

  /**
   * @return <code>true</code> if &lt;script&gt; elements should be placed in the &lt;body&gt;,
   *         <code>false</code> if they should be placed in the &lt;head&gt;. Default is
   *         <code>true</code>.
   */
  public static boolean isScriptsInBody ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bScriptsInBody);
  }

  public static void setScriptsInBody (final boolean bEnabled)
  {
    RW_LOCK.writeLocked ( () -> s_bScriptsInBody = bEnabled);
    CONDLOG.info ( () -> "Default put <scripts>s in " + (bEnabled ? "<body>" : "<head>"));
  }

  /**
   * @return <code>true</code> to include regular, non-minified resources or <code>false</code> to
   *         include minified resources (JS + CSS).
   */
  public static boolean isUseRegularResources ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bUseRegularResources);
  }

  public static void setUseRegularResources (final boolean bUseRegularResources)
  {
    RW_LOCK.writeLocked ( () -> s_bUseRegularResources = bUseRegularResources);
    CONDLOG.info ( () -> "Default using " + (bUseRegularResources ? "regular" : "minified") + " resources");
  }

  /**
   * @return <code>true</code> if scripts should use the 'nonce' attribute, <code>false</code> if
   *         not.
   * @since 9.3.0
   */
  public static boolean isUseNonceInScript ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bUseNonceInScript);
  }

  public static void setUseNonceInScript (final boolean b)
  {
    RW_LOCK.writeLocked ( () -> s_bUseNonceInScript = b);
    CONDLOG.info ( () -> "The 'nonce' attribute will" + (b ? "" : " not") + " be used for <script> elements");
  }

  /**
   * @return <code>true</code> if styles (CSS) should use the 'nonce' attribute, <code>false</code>
   *         if not.
   * @since 9.3.0
   */
  public static boolean isUseNonceInStyle ()
  {
    return RW_LOCK.readLockedBoolean ( () -> s_bUseNonceInStyle);
  }

  public static void setUseNonceInStyle (final boolean b)
  {
    RW_LOCK.writeLocked ( () -> s_bUseNonceInStyle = b);
    CONDLOG.info ( () -> "The 'nonce' attribute will" + (b ? "" : " not") + " be used for <style> elements");
  }
}
