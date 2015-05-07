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
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.xml.serialize.IXMLWriterSettings;
import com.helger.commons.xml.serialize.XMLWriterSettings;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.writer.CSSWriterSettings;
import com.helger.html.EHTMLVersion;
import com.helger.html.hc.customize.IHCCustomizer;
import com.helger.html.js.writer.IJSWriterSettings;
import com.helger.html.js.writer.JSWriterSettings;

/**
 * Settings interface that is used to convert HC* nodes to micro nodes.
 *
 * @author Philip Helger
 */
public interface IHCConversionSettingsToNode
{
  /**
   * @return The HTML version to be used to transform HC nodes into micro nodes.
   *         Never <code>null</code>.
   */
  @Nonnull
  EHTMLVersion getHTMLVersion ();

  /**
   * @return The namespace URI of the HTML version. This should result in the
   *         same as calling <code>getHTMLVersion().getNamespaceURI()</code>
   */
  @Nullable
  String getHTMLNamespaceURI ();

  /**
   * @return The XML writer settings to be used. Never <code>null</code>.
   */
  @Nonnull
  IXMLWriterSettings getXMLWriterSettings ();

  /**
   * @return A mutable copy of the XML writer settings to be used. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  XMLWriterSettings getMutableXMLWriterSettings ();

  /**
   * @return The CSS writer settings to be used. Never <code>null</code>.
   */
  @Nonnull
  ICSSWriterSettings getCSSWriterSettings ();

  /**
   * @return A mutable copy of the CSS writer settings to be used. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  CSSWriterSettings getMutableCSSWriterSettings ();

  /**
   * @return The JS formatter settings to be used. Never <code>null</code>.
   */
  @Nonnull
  IJSWriterSettings getJSWriterSettings ();

  /**
   * @return A mutable copy of the JS formatter settings to be used. Never
   *         <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  JSWriterSettings getMutableJSWriterSettings ();

  /**
   * @return <code>true</code> if the consistency checks are enabled,
   *         <code>false</code> otherwise.
   */
  boolean areConsistencyChecksEnabled ();

  /**
   * @return <code>true</code> if out-of-band nodes should be extracted,
   *         <code>false</code> if not. By default <code>true</code> is
   *         returned.
   */
  boolean isExtractOutOfBandNodes ();

  /**
   * @return The current customizer to be used. Never <code>null</code>. By
   *         default a {@link com.helger.html.hc.customize.HCDefaultCustomizer}
   *         object is returned.
   */
  @Nonnull
  IHCCustomizer getCustomizer ();
}
