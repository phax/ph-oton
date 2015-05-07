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
package com.helger.html.hc.utils;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.html.js.IJSCodeProvider;

/**
 * This interface represents all special nodes that can occur in an HTML
 * document: CSS files (<code>&lt;link rel="stylesheet" href="..."/&gt;</code>),
 * JS files (<code>&lt;script
 * href="..."&gt;</code>) and inline JS code (
 * <code>&lt;script&gt;...&lt;/script&gt;</code>).
 *
 * @author Philip Helger
 */
public interface IHCSpecialNodes extends Serializable
{
  /**
   * @return <code>true</code> if CSS files are present, <code>false</code> if
   *         not
   */
  boolean hasExternalCSSs ();

  /**
   * @return All CSS files. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <String> getAllExternalCSSs ();

  /**
   * @return <code>true</code> if inline CSS is present, <code>false</code> if
   *         not
   */
  boolean hasInlineCSS ();

  /**
   * @return The inline CSS. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  StringBuilder getInlineCSS ();

  /**
   * @return <code>true</code> if JS files are present, <code>false</code> if
   *         not
   */
  boolean hasExternalJSs ();

  /**
   * @return All JS files. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  List <String> getAllExternalJSs ();

  /**
   * @return <code>true</code> if inline JS is present, <code>false</code> if
   *         not
   */
  boolean hasInlineJS ();

  /**
   * @return The inline JS. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  IJSCodeProvider getInlineJS ();
}
