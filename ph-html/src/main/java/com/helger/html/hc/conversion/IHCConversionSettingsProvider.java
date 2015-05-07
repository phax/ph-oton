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
package com.helger.html.hc.conversion;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.xml.serialize.IXMLWriterSettings;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.customize.IHCCustomizer;
import com.helger.html.js.writer.IJSWriterSettings;
import com.helger.html.js.writer.JSWriterSettings;

/**
 * Provider interface for {@link HCConversionSettings} objects.
 *
 * @author Philip Helger
 */
public interface IHCConversionSettingsProvider
{
  /**
   * Get the conversion settings used.
   *
   * @return The non-<code>null</code> conversion settings object.
   */
  @Nonnull
  IHCConversionSettings getConversionSettings ();

  /**
   * @return The HTML version to use. May not be <code>null</code>.
   */
  @Nonnull
  EHTMLVersion getHTMLVersion ();

  @Nonnull
  IHCConversionSettingsProvider setHTMLVersion (@Nonnull EHTMLVersion eHTMLVersion);

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  XMLWriterSettings getXMLWriterSettings ();

  @Nonnull
  IHCConversionSettingsProvider setXMLWriterSettingsOptimized (boolean bOptimized);

  @Nonnull
  IHCConversionSettingsProvider setXMLWriterSettings (@Nonnull IXMLWriterSettings aXMLWriterSettings);

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  CSSWriterSettings getCSSWriterSettings ();

  @Nonnull
  IHCConversionSettingsProvider setCSSWriterSettingsOptimized (boolean bOptimized);

  @Nonnull
  IHCConversionSettingsProvider setCSSWriterSettings (@Nonnull ICSSWriterSettings aCSSWriterSettings);

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  JSWriterSettings getJSWriterSettings ();

  @Nonnull
  IHCConversionSettingsProvider setJSWriterSettingsOptimized (boolean bOptimized);

  @Nonnull
  IHCConversionSettingsProvider setJSWriterSettings (@Nonnull IJSWriterSettings aJSWriterSettings);

  boolean areConsistencyChecksEnabled ();

  @Nonnull
  IHCConversionSettingsProvider setConsistencyChecksEnabled (boolean bConsistencyChecksEnabled);

  boolean isExtractOutOfBandNodes ();

  @Nonnull
  IHCConversionSettingsProvider setExtractOutOfBandNodes (boolean bExtractOutOfBandNodes);

  @Nonnull
  IHCCustomizer getCustomizer ();

  @Nonnull
  IHCConversionSettingsProvider setCustomizer (@Nonnull IHCCustomizer aCustomizer);

  /**
   * Set all settings to create debug output (default).
   *
   * @return this
   */
  @Nonnull
  IHCConversionSettingsProvider setToDefault ();

  /**
   * Set all settings to create optimized output.
   *
   * @return this
   */
  @Nonnull
  IHCConversionSettingsProvider setToOptimized ();
}
