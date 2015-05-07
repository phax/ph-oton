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
package com.helger.html.hc.html5;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

/**
 * Test class for class {@link HCTime}
 *
 * @author Philip Helger
 */
public final class HCTimeTest
{
  @Test
  public void testSetDatetime ()
  {
    final HCTime t = new HCTime ();
    t.setAsDuration (new Duration (new DateTime (new GregorianCalendar (2010, 5, 6)),
                                   new DateTime (new GregorianCalendar (2010, 6, 7))));
    assertEquals ("PT744H", t.getDatetime ());
  }
}
