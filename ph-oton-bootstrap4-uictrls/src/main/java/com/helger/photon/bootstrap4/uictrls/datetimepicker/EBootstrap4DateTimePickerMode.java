package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.datetime.PDTFormatPatterns;
import com.helger.commons.functional.IFunction;

public enum EBootstrap4DateTimePickerMode
{
  DATE (d -> PDTFormatPatterns.getDefaultPatternDate (d)),
  DATE_TIME (d -> PDTFormatPatterns.getDefaultPatternDateTime (d)),
  TIME (d -> PDTFormatPatterns.getDefaultPatternTime (d));

  public static final EBootstrap4DateTimePickerMode DEFAULT = DATE;

  private final IFunction <Locale, String> m_aJavaFormatSupplier;

  private EBootstrap4DateTimePickerMode (@Nonnull final IFunction <Locale, String> aFormatSupplier)
  {
    m_aJavaFormatSupplier = aFormatSupplier;
  }

  @Nonnull
  public String getJSFormat (@Nonnull final Locale aDisplayLocale)
  {
    final String s = m_aJavaFormatSupplier.apply (aDisplayLocale);
    return Bootstrap4DateTimePickerFormatBuilder.fromJavaPattern (s).getJSCalendarFormatString ();
  }
}
