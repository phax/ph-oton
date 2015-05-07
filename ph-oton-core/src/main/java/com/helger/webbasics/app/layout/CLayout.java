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
package com.helger.webbasics.app.layout;

import javax.annotation.concurrent.Immutable;

/**
 * Contains some predefined layout area IDs
 * 
 * @author Philip Helger
 */
@Immutable
public final class CLayout
{
  // Layout area IDs - used in CSS!!!

  /** Header layout area */
  public static final String LAYOUT_AREAID_HEADER = "header";
  /** NavBar layout area */
  public static final String LAYOUT_AREAID_NAVBAR = "navbar";
  /** Viewport/MainContent layout area */
  public static final String LAYOUT_AREAID_VIEWPORT = "viewport";
  /** Left layout area */
  public static final String LAYOUT_AREAID_LEFT = "left";
  /** Menu layout area */
  public static final String LAYOUT_AREAID_MENU = "menu";
  /** Special layout area */
  public static final String LAYOUT_AREAID_SPECIAL = "special";
  /** Content layout area */
  public static final String LAYOUT_AREAID_CONTENT = "content";
  /** Footer layout area */
  public static final String LAYOUT_AREAID_FOOTER = "footer";

  private CLayout ()
  {}
}
