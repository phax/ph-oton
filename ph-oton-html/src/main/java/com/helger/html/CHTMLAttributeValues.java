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
package com.helger.html;

import javax.annotation.concurrent.Immutable;

/**
 * List of constant HTML attribute values.
 *
 * @author Philip Helger
 */
@Immutable
public final class CHTMLAttributeValues
{
  // Generic values:
  public static final String CHECKED = CHTMLAttributes.CHECKED.getName ();
  public static final String DISABLED = CHTMLAttributes.DISABLED.getName ();
  public static final String MULTIPLE = CHTMLAttributes.MULTIPLE.getName ();
  public static final String READONLY = CHTMLAttributes.READONLY.getName ();
  public static final String SELECTED = CHTMLAttributes.SELECTED.getName ();
  public static final String DEFER = CHTMLAttributes.DEFER.getName ();
  public static final String DECLARE = CHTMLAttributes.DECLARE.getName ();
  public static final String STAR = "*";
  public static final String OFF = "off";
  public static final String ON = "on";
  public static final String YES = "yes";
  public static final String NO = "no";
  public static final String ANY = "any";

  // HTML5
  public static final String ASYNC = CHTMLAttributes.ASYNC.getName ();
  public static final String AUTOFOCUS = CHTMLAttributes.AUTOFOCUS.getName ();
  public static final String DEFAULT = CHTMLAttributes.DEFAULT.getName ();
  public static final String DRAGGABLE = CHTMLAttributes.DRAGGABLE.getName ();
  public static final String FORMNOVALIDATE = CHTMLAttributes.FORMNOVALIDATE.getName ();
  public static final String HIDDEN = CHTMLAttributes.HIDDEN.getName ();
  public static final String MUTED = CHTMLAttributes.MUTED.getName ();
  public static final String NORESIZE = CHTMLAttributes.NORESIZE.getName ();
  public static final String NOSHADE = CHTMLAttributes.NOSHADE.getName ();
  public static final String NOVALIDATE = CHTMLAttributes.NOVALIDATE.getName ();
  public static final String OPEN = CHTMLAttributes.OPEN.getName ();
  public static final String REQUIRED = CHTMLAttributes.REQUIRED.getName ();
  public static final String REVERSED = CHTMLAttributes.REVERSED.getName ();
  public static final String SPELLCHECK = CHTMLAttributes.SPELLCHECK.getName ();

  // HTML5 audio
  public static final String AUTOPLAY = CHTMLAttributes.AUTOPLAY.getName ();
  public static final String CONTROLS = CHTMLAttributes.CONTROLS.getName ();
  public static final String LOOP = CHTMLAttributes.LOOP.getName ();

  private CHTMLAttributeValues ()
  {}
}
