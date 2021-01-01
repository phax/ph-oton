/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.html.hc.special;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.js.IHasJSCode;
import com.helger.html.resource.css.ICSSCodeProvider;

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
  boolean isEmpty ();

  /**
   * @return <code>true</code> if CSS files are present, <code>false</code> if
   *         not
   */
  boolean hasExternalCSSs ();

  /**
   * @return All CSS files as a map from media list to the list of matching
   *         files. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedMap <ICSSMediaList, ICommonsList <String>> getAllExternalCSSs ();

  /**
   * @return <code>true</code> if inline CSS to be included <b>before</b> the
   *         CSS files is present, <code>false</code> if not
   */
  boolean hasInlineCSSBeforeExternal ();

  /**
   * @return The inline CSS to be included <b>before</b> the files as a map from
   *         media list to the CSS code. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <ICSSCodeProvider> getAllInlineCSSBeforeExternal ();

  /**
   * @return <code>true</code> if inline CSS to be included <b>after</b> the CSS
   *         files is present, <code>false</code> if not
   */
  boolean hasInlineCSSAfterExternal ();

  /**
   * @return The inline CSS to be included <b>after</b> the files as a map from
   *         media list to the CSS code. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <ICSSCodeProvider> getAllInlineCSSAfterExternal ();

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
  ICommonsList <String> getAllExternalJSs ();

  /**
   * @return <code>true</code> if inline JS is present, <code>false</code> if
   *         not
   */
  boolean hasInlineJSBeforeExternal ();

  /**
   * @return The inline JS. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  IHasJSCode getInlineJSBeforeExternal ();

  /**
   * @return <code>true</code> if inline JS is present, <code>false</code> if
   *         not
   */
  boolean hasInlineJSAfterExternal ();

  /**
   * @return The inline JS. May not be <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableCopy
  IHasJSCode getInlineJSAfterExternal ();
}
