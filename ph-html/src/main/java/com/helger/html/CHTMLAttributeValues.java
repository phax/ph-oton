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
  public static final String CHECKED = CHTMLAttributes.CHECKED;
  public static final String DISABLED = CHTMLAttributes.DISABLED;
  public static final String MULTIPLE = CHTMLAttributes.MULTIPLE;
  public static final String READONLY = CHTMLAttributes.READONLY;
  public static final String SELECTED = CHTMLAttributes.SELECTED;
  public static final String DEFER = CHTMLAttributes.DEFER;
  public static final String DECLARE = CHTMLAttributes.DECLARE;
  public static final String STAR = "*";
  public static final String OFF = "off";
  public static final String ON = "on";
  public static final String YES = "yes";
  public static final String NO = "no";

  // HTML5
  public static final String ASYNC = CHTMLAttributes.ASYNC;
  public static final String AUTOFOCUS = CHTMLAttributes.AUTOFOCUS;
  public static final String DEFAULT = CHTMLAttributes.DEFAULT;
  public static final String DRAGGABLE = CHTMLAttributes.DRAGGABLE;
  public static final String FORMNOVALIDATE = CHTMLAttributes.FORMNOVALIDATE;
  public static final String HIDDEN = CHTMLAttributes.HIDDEN;
  public static final String MUTED = CHTMLAttributes.MUTED;
  public static final String NOVALIDATE = CHTMLAttributes.NOVALIDATE;
  public static final String OPEN = CHTMLAttributes.OPEN;
  public static final String REQUIRED = CHTMLAttributes.REQUIRED;
  public static final String REVERSED = CHTMLAttributes.REVERSED;
  public static final String SPELLCHECK = CHTMLAttributes.SPELLCHECK;

  // HTML5 audio
  public static final String AUTOPLAY = CHTMLAttributes.AUTOPLAY;
  public static final String CONTROLS = CHTMLAttributes.CONTROLS;
  public static final String LOOP = CHTMLAttributes.LOOP;

  private CHTMLAttributeValues ()
  {}
}
