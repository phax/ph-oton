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
package com.helger.photon.uictrls.chart.v1;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.Nonempty;
import com.helger.css.decl.CSSRGB;
import com.helger.css.decl.CSSRGBA;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Immutable
public final class ChartPaletteDefault
{
  // Source: http://download.oracle.com/tech/blaf/specs/colorpalette.html
  private static final CSSRGB [] BASE_COLORS = new CSSRGB [] { new CSSRGB (51, 102, 204),
                                                               new CSSRGB (153, 204, 255),
                                                               new CSSRGB (153, 153, 51),
                                                               new CSSRGB (102, 102, 153),
                                                               new CSSRGB (204, 153, 51),
                                                               new CSSRGB (0, 102, 102),
                                                               new CSSRGB (51, 153, 255),
                                                               new CSSRGB (153, 51, 0),
                                                               new CSSRGB (204, 204, 153),
                                                               new CSSRGB (102, 102, 102),
                                                               new CSSRGB (255, 204, 102),
                                                               new CSSRGB (102, 153, 204),
                                                               new CSSRGB (102, 51, 102),
                                                               new CSSRGB (153, 153, 204),
                                                               new CSSRGB (204, 204, 204),
                                                               new CSSRGB (102, 153, 153),
                                                               new CSSRGB (204, 204, 102),
                                                               new CSSRGB (204, 102, 0),
                                                               new CSSRGB (153, 153, 255),
                                                               new CSSRGB (0, 102, 204),
                                                               new CSSRGB (153, 204, 204),
                                                               new CSSRGB (153, 153, 153),
                                                               new CSSRGB (255, 204, 0),
                                                               new CSSRGB (0, 153, 153),
                                                               new CSSRGB (153, 204, 51),
                                                               new CSSRGB (255, 153, 0),
                                                               new CSSRGB (153, 153, 102),
                                                               new CSSRGB (102, 204, 204),
                                                               new CSSRGB (51, 153, 102),
                                                               new CSSRGB (204, 204, 51) };

  private static final CSSRGBA [] COLORS = new CSSRGBA [BASE_COLORS.length];
  private static final CSSRGBA [] FILL_COLORS = new CSSRGBA [BASE_COLORS.length];
  private static final CSSRGBA [] HIGHLIGHT_COLORS = new CSSRGBA [BASE_COLORS.length];

  static
  {
    for (int i = 0; i < COLORS.length; ++i)
      COLORS[i] = new CSSRGBA (BASE_COLORS[i], "0.8");
    for (int i = 0; i < FILL_COLORS.length; ++i)
      FILL_COLORS[i] = new CSSRGBA (BASE_COLORS[i], "0.2");
    for (int i = 0; i < HIGHLIGHT_COLORS.length; ++i)
      HIGHLIGHT_COLORS[i] = new CSSRGBA (BASE_COLORS[i], "1");
  }

  private ChartPaletteDefault ()
  {}

  /**
   * @param nIndex
   *        Index
   * @return Color with opacity 80%
   */
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getColorString (final int nIndex)
  {
    return COLORS[nIndex % COLORS.length].getAsString ();
  }

  /**
   * @param nIndex
   *        Index
   * @return Color with opacity 20%
   */
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getFillColorString (final int nIndex)
  {
    return FILL_COLORS[nIndex % FILL_COLORS.length].getAsString ();
  }

  /**
   * @param nIndex
   *        Index
   * @return Color with opacity 100%
   */
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getHighlightColorString (final int nIndex)
  {
    return HIGHLIGHT_COLORS[nIndex % HIGHLIGHT_COLORS.length].getAsString ();
  }
}
