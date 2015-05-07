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
package com.helger.photon.uicore.datetime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.mock.AbstractPHTestCase;
import com.helger.datetime.format.PDTFormatPatterns;

/**
 * Test class for class {@link DateFormatBuilder}.
 *
 * @author Philip Helger
 */
public final class DateFormatBuilderTest extends AbstractPHTestCase
{
  @Test
  public void testAll ()
  {
    final DateFormatBuilder aDFB = new DateFormatBuilder ();
    aDFB.append (EDateTimeFormatToken.YEAR_WITH_CENTURY)
        .append ("/")
        .append (EDateTimeFormatToken.MONTH_LZ)
        .append ("/")
        .append (EDateTimeFormatToken.DAY_OF_MONTH_LZ)
        .append (" ")
        .append (EDateTimeFormatToken.HOUR23_LZ)
        .append (":")
        .append (EDateTimeFormatToken.MINUTE_LZ)
        .append (":")
        .append (EDateTimeFormatToken.SECONDS_LZ);
    assertEquals ("%Y/%m/%d %H:%M:%S", aDFB.getJSCalendarFormatString ());
    assertEquals ("yyyy/MM/dd HH:mm:ss", aDFB.getJavaFormatString ());

    final DateTime aDT = aDFB.getDateTimeFormatted ("2010/08/08 12:45:13");
    assertNotNull (aDT);
    assertEquals (2010, aDT.getYear ());
    assertEquals (DateTimeConstants.AUGUST, aDT.getMonthOfYear ());
    assertEquals (8, aDT.getDayOfMonth ());
    assertEquals (12, aDT.getHourOfDay ());
    assertEquals (45, aDT.getMinuteOfHour ());
    assertEquals (13, aDT.getSecondOfMinute ());

    final LocalDate aLD = aDFB.getDateFormatted ("2010/08/08 12:45:13");
    assertNotNull (aLD);
    assertEquals (2010, aLD.getYear ());
    assertEquals (DateTimeConstants.AUGUST, aLD.getMonthOfYear ());
    assertEquals (8, aLD.getDayOfMonth ());

    final LocalTime aLT = aDFB.getTimeFormatted ("2010/08/08 12:45:13");
    assertNotNull (aLT);
    assertEquals (12, aLT.getHourOfDay ());
    assertEquals (45, aLT.getMinuteOfHour ());
    assertEquals (13, aLT.getSecondOfMinute ());
  }

  @Test
  public void getFromPattern ()
  {
    String sPattern;
    IDateFormatBuilder aDFB;

    for (final Locale aLocale : LocaleCache.getAllLocales ())
    {
      sPattern = PDTFormatPatterns.getDefaultPatternDateTime (aLocale);
      aDFB = DateFormatBuilder.fromJavaPattern (sPattern);
      assertNotNull (aDFB);
      assertEquals (sPattern, aDFB.getJavaFormatString ());
      assertNotNull (aDFB.getJSCalendarFormatString ());

      sPattern = PDTFormatPatterns.getDefaultPatternDate (aLocale);
      aDFB = DateFormatBuilder.fromJavaPattern (sPattern);
      assertNotNull (aDFB);
      assertEquals (sPattern, aDFB.getJavaFormatString ());
      assertNotNull (aDFB.getJSCalendarFormatString ());

      sPattern = PDTFormatPatterns.getDefaultPatternTime (aLocale);
      aDFB = DateFormatBuilder.fromJavaPattern (sPattern);
      assertNotNull (aDFB);
      assertEquals (sPattern, aDFB.getJavaFormatString ());
      assertNotNull (aDFB.getJSCalendarFormatString ());
    }

    sPattern = "dd.MM.yy HH:mm:ss";
    aDFB = DateFormatBuilder.fromJavaPattern (sPattern);
    assertNotNull (aDFB);
    assertEquals (sPattern, aDFB.getJavaFormatString ());
    assertNotNull (aDFB.getJSCalendarFormatString ());
  }
}
