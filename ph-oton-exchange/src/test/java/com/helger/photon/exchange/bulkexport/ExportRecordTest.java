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
package com.helger.photon.exchange.bulkexport;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.helger.commons.datetime.PDTFactory;

/**
 * Test class for class {@link ExportRecord}.
 *
 * @author Philip Helger
 */
public final class ExportRecordTest
{
  @Test
  public void testAddFieldTyped ()
  {
    final ExportRecord aRecordWithAllTypes = new ExportRecord ().addField ("Hallo")
                                                                .addField (PDTFactory.getCurrentLocalTime ())
                                                                .addField (PDTFactory.getCurrentLocalDate ())
                                                                .addField (PDTFactory.getCurrentLocalDateTime ())
                                                                .addField (PDTFactory.getCurrentZonedDateTime ())
                                                                .addField (PDTFactory.getCurrentOffsetDateTime ())
                                                                .addField (true)
                                                                .addField (Boolean.FALSE)
                                                                .addField (4711)
                                                                .addField (Integer.valueOf (-34))
                                                                .addField (-123456789012345L)
                                                                .addField (Long.valueOf (Long.MIN_VALUE))
                                                                .addField (new BigInteger ("1234512345123451234512345123451234512345123451234512345"))
                                                                .addField (3.1145)
                                                                .addField (Double.valueOf (Double.MIN_VALUE))
                                                                .addField (new BigDecimal ("12345123451234512345123451234512345123451234512345.12345"));
    assertEquals (16, aRecordWithAllTypes.getFieldCount ());
  }

  @Test
  public void testAddFieldUntyped ()
  {
    final ExportRecord aRecordWithAllTypes = new ExportRecord ().addField ((Object) "Hallo")
                                                                .addField ((Object) PDTFactory.getCurrentLocalTime ())
                                                                .addField ((Object) PDTFactory.getCurrentLocalDate ())
                                                                .addField ((Object) PDTFactory.getCurrentLocalDateTime ())
                                                                .addField ((Object) PDTFactory.getCurrentZonedDateTime ())
                                                                .addField ((Object) PDTFactory.getCurrentOffsetDateTime ())
                                                                .addField ((Object) Boolean.FALSE)
                                                                .addField ((Object) Integer.valueOf (-34))
                                                                .addField ((Object) Long.valueOf (Long.MIN_VALUE))
                                                                .addField ((Object) new BigInteger ("1234512345123451234512345123451234512345123451234512345"))
                                                                .addField ((Object) Double.valueOf (Double.MIN_VALUE))
                                                                .addField ((Object) new BigDecimal ("12345123451234512345123451234512345123451234512345.12345"));
    assertEquals (12, aRecordWithAllTypes.getFieldCount ());
  }
}
