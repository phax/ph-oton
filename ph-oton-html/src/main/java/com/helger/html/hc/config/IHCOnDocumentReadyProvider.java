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

import com.helger.html.js.IHasJSCode;

/**
 * An interface to create the "on document ready" deferred code.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IHCOnDocumentReadyProvider
{
  /**
   * Create "on document ready" JS code. When e.g. using JQuery this could
   * return <code>$(document).ready (function() {<i>JSCode</i>});</code>
   *
   * @param aJSCodeProvider
   *        The code to be executed when the HTML document is ready.
   * @return The created code.
   */
  @Nonnull
  IHasJSCode createOnDocumentReady (@Nonnull final IHasJSCode aJSCodeProvider);
}
