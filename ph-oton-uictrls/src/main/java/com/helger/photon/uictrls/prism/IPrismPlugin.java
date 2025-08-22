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
package com.helger.photon.uictrls.prism;

import com.helger.html.hc.ext.HCHasCSSClasses;
import com.helger.html.hc.ext.HCHasCSSStyles;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;

/**
 * Interface for PrismJS plugins
 *
 * @author Philip Helger
 */
public interface IPrismPlugin
{
  /**
   * Register all CSS/JS necessary for this plugin
   */
  default void registerExternalResourcesBeforePrism ()
  {}

  /**
   * Register all CSS/JS necessary for this plugin
   */
  default void registerExternalResourcesAfterPrism ()
  {}

  /**
   * Create the HTML elements for the &lt;pre&gt; element
   *
   * @param aPreElement
   *        The pre element
   * @param aPreClasses
   *        The pre element classes
   * @param aPreStyles
   *        The pre element styles
   */
  void applyOnPre (@Nonnull IMicroElement aPreElement, @Nonnull HCHasCSSClasses aPreClasses, @Nonnull HCHasCSSStyles aPreStyles);
}
