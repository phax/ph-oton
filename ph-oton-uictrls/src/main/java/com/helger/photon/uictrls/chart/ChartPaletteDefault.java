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
package com.helger.photon.uictrls.chart;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.css.decl.CSSRGB;
import com.helger.css.decl.CSSRGBA;

@Immutable
public final class ChartPaletteDefault
{
  // Source: http://download.oracle.com/tech/blaf/specs/colorpalette.html
  private static final CSSRGB [] BASE_COLORS = { new CSSRGB (51, 102, 204),
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
  @NonNull
  @Nonempty
  public static String getColorString (@Nonnegative final int nIndex)
  {
    return COLORS[nIndex % COLORS.length].getAsString ();
  }

  @NonNull
  @ReturnsMutableCopy
  private static String [] _getAllStrings (@NonNull final CSSRGBA [] aSrc, @Nonnegative final int nMaxEntries)
  {
    ValueEnforcer.notNull (aSrc, "Src");
    ValueEnforcer.isGT0 (nMaxEntries, "MaxEntries");

    final int nRetLen = Math.min (aSrc.length, nMaxEntries);
    final String [] ret = new String [nRetLen];
    for (int i = 0; i < nRetLen; ++i)
      ret[i] = aSrc[i].getAsString ();
    return ret;
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllColorStrings ()
  {
    return getAllColorStrings (COLORS.length);
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllColorStrings (@Nonnegative final int nMaxEntries)
  {
    return _getAllStrings (COLORS, nMaxEntries);
  }

  /**
   * @param nIndex
   *        Index
   * @return Color with opacity 20%
   */
  @NonNull
  @Nonempty
  public static String getFillColorString (@Nonnegative final int nIndex)
  {
    return FILL_COLORS[nIndex % FILL_COLORS.length].getAsString ();
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllFillColorStrings ()
  {
    return getAllFillColorStrings (FILL_COLORS.length);
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllFillColorStrings (@Nonnegative final int nMaxEntries)
  {
    return _getAllStrings (FILL_COLORS, nMaxEntries);
  }

  /**
   * @param nIndex
   *        Index
   * @return Color with opacity 100%
   */
  @NonNull
  @Nonempty
  public static String getHighlightColorString (@Nonnegative final int nIndex)
  {
    return HIGHLIGHT_COLORS[nIndex % HIGHLIGHT_COLORS.length].getAsString ();
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllHighlightColorStrings ()
  {
    return getAllHighlightColorStrings (HIGHLIGHT_COLORS.length);
  }

  @NonNull
  @ReturnsMutableCopy
  public static String [] getAllHighlightColorStrings (@Nonnegative final int nMaxEntries)
  {
    return _getAllStrings (HIGHLIGHT_COLORS, nMaxEntries);
  }
}
