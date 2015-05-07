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
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.xml.serialize.IXMLWriterSettings;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.customize.IHCCustomizer;
import com.helger.html.js.writer.IJSWriterSettings;
import com.helger.html.js.writer.JSWriterSettings;

/**
 * Default implementation of {@link IHCConversionSettingsProvider} using a
 * provided {@link EHTMLVersion}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class HCConversionSettingsProvider implements IHCConversionSettingsProvider
{
  private final HCConversionSettings m_aConversionSettings;

  public HCConversionSettingsProvider (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    this (new HCConversionSettings (eHTMLVersion));
  }

  public HCConversionSettingsProvider (@Nonnull final HCConversionSettings aConversionSettings)
  {
    ValueEnforcer.notNull (aConversionSettings, "ConversionSettings");
    m_aConversionSettings = aConversionSettings;
  }

  @Nonnull
  public HCConversionSettings getConversionSettings ()
  {
    return m_aConversionSettings;
  }

  @Nonnull
  public EHTMLVersion getHTMLVersion ()
  {
    return m_aConversionSettings.getHTMLVersion ();
  }

  @Nonnull
  public IHCConversionSettingsProvider setHTMLVersion (@Nonnull final EHTMLVersion eHTMLVersion)
  {
    m_aConversionSettings.setHTMLVersion (eHTMLVersion);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public XMLWriterSettings getXMLWriterSettings ()
  {
    return m_aConversionSettings.getXMLWriterSettings ();
  }

  @Nonnull
  public IHCConversionSettingsProvider setXMLWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aConversionSettings.setXMLWriterSettingsOptimized (bOptimized);
    return this;
  }

  @Nonnull
  public HCConversionSettingsProvider setXMLWriterSettings (@Nonnull final IXMLWriterSettings aXMLWriterSettings)
  {
    m_aConversionSettings.setXMLWriterSettings (aXMLWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public CSSWriterSettings getCSSWriterSettings ()
  {
    return m_aConversionSettings.getCSSWriterSettings ();
  }

  @Nonnull
  public IHCConversionSettingsProvider setCSSWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aConversionSettings.setCSSWriterSettingsOptimized (bOptimized);
    return this;
  }

  @Nonnull
  public HCConversionSettingsProvider setCSSWriterSettings (@Nonnull final ICSSWriterSettings aCSSWriterSettings)
  {
    m_aConversionSettings.setCSSWriterSettings (aCSSWriterSettings);
    return this;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "Design")
  public JSWriterSettings getJSWriterSettings ()
  {
    return m_aConversionSettings.getJSWriterSettings ();
  }

  @Nonnull
  public IHCConversionSettingsProvider setJSWriterSettingsOptimized (final boolean bOptimized)
  {
    m_aConversionSettings.setJSWriterSettingsOptimized (bOptimized);
    return this;
  }

  @Nonnull
  public HCConversionSettingsProvider setJSWriterSettings (@Nonnull final IJSWriterSettings aJSWriterSettings)
  {
    m_aConversionSettings.setJSWriterSettings (aJSWriterSettings);
    return this;
  }

  public boolean areConsistencyChecksEnabled ()
  {
    return m_aConversionSettings.areConsistencyChecksEnabled ();
  }

  @Nonnull
  public HCConversionSettingsProvider setConsistencyChecksEnabled (final boolean bConsistencyChecksEnabled)
  {
    m_aConversionSettings.setConsistencyChecksEnabled (bConsistencyChecksEnabled);
    return this;
  }

  public boolean isExtractOutOfBandNodes ()
  {
    return m_aConversionSettings.isExtractOutOfBandNodes ();
  }

  @Nonnull
  public HCConversionSettingsProvider setExtractOutOfBandNodes (final boolean bExtractOutOfBandNodes)
  {
    m_aConversionSettings.setExtractOutOfBandNodes (bExtractOutOfBandNodes);
    return this;
  }

  @Nonnull
  public IHCCustomizer getCustomizer ()
  {
    return m_aConversionSettings.getCustomizer ();
  }

  @Nonnull
  public HCConversionSettingsProvider setCustomizer (@Nonnull final IHCCustomizer aCustomizer)
  {
    m_aConversionSettings.setCustomizer (aCustomizer);
    return this;
  }

  @Nonnull
  public HCConversionSettingsProvider setToDefault ()
  {
    m_aConversionSettings.setToDefault ();
    return this;
  }

  @Nonnull
  public HCConversionSettingsProvider setToOptimized ()
  {
    m_aConversionSettings.setToOptimized ();
    return this;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("conversionSettings", m_aConversionSettings).toString ();
  }
}
