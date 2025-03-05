package com.helger.photon.uictrls.chart;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.css.decl.CSSRGB;
import com.helger.css.decl.CSSRGBA;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

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
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getColorString (@Nonnegative final int nIndex)
  {
    return COLORS[nIndex % COLORS.length].getAsString ();
  }

  @Nonnull
  @ReturnsMutableCopy
  private static String [] _getAllStrings (@Nonnull final CSSRGBA [] aSrc, @Nonnegative final int nMaxEntries)
  {
    ValueEnforcer.notNull (aSrc, "Src");
    ValueEnforcer.isGT0 (nMaxEntries, "MaxEntries");

    final int nRetLen = Math.min (aSrc.length, nMaxEntries);
    final String [] ret = new String [nRetLen];
    for (int i = 0; i < nRetLen; ++i)
      ret[i] = aSrc[i].getAsString ();
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static String [] getAllColorStrings ()
  {
    return getAllColorStrings (COLORS.length);
  }

  @Nonnull
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
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getFillColorString (@Nonnegative final int nIndex)
  {
    return FILL_COLORS[nIndex % FILL_COLORS.length].getAsString ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public static String [] getAllFillColorStrings ()
  {
    return getAllFillColorStrings (FILL_COLORS.length);
  }

  @Nonnull
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
  @Nonnull
  @Nonempty
  @SuppressFBWarnings ("NP_NONNULL_PARAM_VIOLATION")
  public static String getHighlightColorString (@Nonnegative final int nIndex)
  {
    return HIGHLIGHT_COLORS[nIndex % HIGHLIGHT_COLORS.length].getAsString ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public static String [] getAllHighlightColorStrings ()
  {
    return getAllHighlightColorStrings (HIGHLIGHT_COLORS.length);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static String [] getAllHighlightColorStrings (@Nonnegative final int nMaxEntries)
  {
    return _getAllStrings (HIGHLIGHT_COLORS, nMaxEntries);
  }
}
