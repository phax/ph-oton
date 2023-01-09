/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.css;

import javax.annotation.concurrent.Immutable;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;

/**
 * WebCtrls predefined CSS classes
 *
 * @author Philip Helger
 */
@Immutable
public final class CUICoreCSS
{
  /** Align text center */
  public static final ICSSClassProvider CSS_CLASS_LEFT = DefaultCSSClassProvider.create ("left");

  /** Align text center */
  public static final ICSSClassProvider CSS_CLASS_CENTER = DefaultCSSClassProvider.create ("center");

  /** Align text right */
  public static final ICSSClassProvider CSS_CLASS_RIGHT = DefaultCSSClassProvider.create ("right");

  /** white-space:nowrap */
  public static final ICSSClassProvider CSS_CLASS_NOWRAP = DefaultCSSClassProvider.create ("nowrap");

  /** Action column in a table */
  public static final ICSSClassProvider CSS_CLASS_ACTION_COL = DefaultCSSClassProvider.create ("actioncol");

  /** Empty action in the action column of a table */
  public static final ICSSClassProvider CSS_CLASS_EMPTY_ACTION = DefaultCSSClassProvider.create ("empty-action");

  /** Action header in a web page */
  public static final ICSSClassProvider CSS_CLASS_ACTION_HEADER = DefaultCSSClassProvider.create ("action-header");

  /** Data group header in a web page */
  public static final ICSSClassProvider CSS_CLASS_DATAGROUP_HEADER = DefaultCSSClassProvider.create ("datagroup-header");

  private CUICoreCSS ()
  {}
}
