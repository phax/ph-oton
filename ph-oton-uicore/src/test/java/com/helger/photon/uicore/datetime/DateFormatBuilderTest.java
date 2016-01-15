/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Locale;

import org.junit.Test;

import com.helger.commons.locale.LocaleCache;
import com.helger.commons.mock.AbstractCommonsTestCase;
import com.helger.datetime.format.PDTFormatPatterns;

/**
 * Test class for class {@link DateFormatBuilder}.
 *
 * @author Philip Helger
 */
public final class DateFormatBuilderTest extends AbstractCommonsTestCase
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
    assertEquals ("uuuu/MM/dd HH:mm:ss", aDFB.getJavaFormatString ());

    final LocalDateTime aDT = aDFB.getLocalDateTimeFormatted ("2010/08/08 12:45:13");
    assertNotNull (aDT);
    assertEquals (2010, aDT.getYear ());
    assertEquals (Month.AUGUST, aDT.getMonth ());
    assertEquals (8, aDT.getDayOfMonth ());
    assertEquals (12, aDT.getHour ());
    assertEquals (45, aDT.getMinute ());
    assertEquals (13, aDT.getSecond ());

    final LocalDate aLD = aDFB.getDateFormatted ("2010/08/08 12:45:13");
    assertNotNull (aLD);
    assertEquals (2010, aLD.getYear ());
    assertEquals (Month.AUGUST, aLD.getMonth ());
    assertEquals (8, aLD.getDayOfMonth ());

    final LocalTime aLT = aDFB.getTimeFormatted ("2010/08/08 12:45:13");
    assertNotNull (aLT);
    assertEquals (12, aLT.getHour ());
    assertEquals (45, aLT.getMinute ());
    assertEquals (13, aLT.getSecond ());
  }

  @Test
  public void getFromPattern ()
  {
    String sPattern;
    IDateFormatBuilder aDFB;

    for (final Locale aLocale : LocaleCache.getInstance ().getAllLocales ())
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
