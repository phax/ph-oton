/*
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
package com.helger.photon.icon.materialdesign;

import javax.annotation.concurrent.Immutable;

import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;

/**
 * CSS Constants for the Material Design icon library
 *
 * @author Philip Helger
 */
@Immutable
public final class CMaterialDesignCSS
{
  public static final ICSSClassProvider MATERIAL_ICONS = DefaultCSSClassProvider.create ("material-icons");
  public static final ICSSClassProvider MD_18 = DefaultCSSClassProvider.create ("md-18");
  public static final ICSSClassProvider MD_24 = DefaultCSSClassProvider.create ("md-24");
  public static final ICSSClassProvider MD_36 = DefaultCSSClassProvider.create ("md-36");
  public static final ICSSClassProvider MD_48 = DefaultCSSClassProvider.create ("md-48");
  public static final ICSSClassProvider MD_DARK = DefaultCSSClassProvider.create ("md-dark");
  public static final ICSSClassProvider MD_LIGHT = DefaultCSSClassProvider.create ("md-light");
  public static final ICSSClassProvider MD_INACTIVE = DefaultCSSClassProvider.create ("md-inactive");

  private CMaterialDesignCSS ()
  {}
}
