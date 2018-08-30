package com.helger.photon.bootstrap4.uictrls.datetimepicker;

import static org.junit.Assert.assertEquals;

import javax.annotation.Nonnull;

import org.junit.Test;

/**
 * Test class for class {@link Bootstrap4DateTimePickerFormatBuilder}.
 * 
 * @author Philip Helger
 */
public final class Bootstrap4DateTimePickerFormatBuilderTest
{
  @Nonnull
  private static String _getAsJS (@Nonnull final String s)
  {
    return Bootstrap4DateTimePickerFormatBuilder.fromJavaPattern (s).getJSCalendarFormatString ();
  }

  @Test
  public void testBasic ()
  {
    assertEquals ("DD.MM.YYYY", _getAsJS ("dd.MM.yyyy"));
    assertEquals ("DD.MM.YYYY", _getAsJS ("dd.MM.uuuu"));
    assertEquals ("DD/MM", _getAsJS ("dd/MM"));
  }
}
