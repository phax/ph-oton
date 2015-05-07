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
package com.helger.appbasics.exchange.format;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

import com.helger.appbasics.exchange.bulkexport.ConstantExportRecordProvider;
import com.helger.appbasics.exchange.bulkexport.EmptyExportRecordProvider;
import com.helger.appbasics.exchange.bulkexport.ExportRecord;
import com.helger.appbasics.exchange.bulkexport.format.ExporterJSON;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.io.streams.NonBlockingByteArrayOutputStream;
import com.helger.datetime.PDTFactory;

/**
 * Test class for class {@link ExporterJSON}.
 *
 * @author Philip Helger
 */
public final class ExporterJSONTest
{
  @Test
  public void testBasicExport ()
  {
    final ExportRecord aRecordWithAllTypes = new ExportRecord ().addField ("Hallo")
                                                                .addField (PDTFactory.getCurrentLocalTime ())
                                                                .addField (PDTFactory.getCurrentLocalDate ())
                                                                .addField (PDTFactory.getCurrentLocalDateTime ())
                                                                .addField (PDTFactory.getCurrentDateTime ())
                                                                .addField (true)
                                                                .addField (4711)
                                                                .addField (-123456789012345L)
                                                                .addField (new BigInteger ("1234512345123451234512345123451234512345123451234512345"))
                                                                .addField (3.1415)
                                                                .addField (new BigDecimal ("12345123451234512345123451234512345123451234512345.12345"));
    final ExportRecord aEmptyRecord = new ExportRecord ();
    final ExporterJSON aExporter = new ExporterJSON ();
    // Fails because no record is present
    assertTrue (aExporter.exportRecords (new EmptyExportRecordProvider (), new NonBlockingByteArrayOutputStream ())
                         .isFailure ());
    assertTrue (aExporter.exportRecords (new ConstantExportRecordProvider (CollectionHelper.newList (aRecordWithAllTypes)),
                                         new NonBlockingByteArrayOutputStream ())
                         .isSuccess ());
    assertTrue (aExporter.exportRecords (new ConstantExportRecordProvider (CollectionHelper.newList (aRecordWithAllTypes,
                                                                                                    aRecordWithAllTypes,
                                                                                                    aEmptyRecord)),
                                         new NonBlockingByteArrayOutputStream ())
                         .isSuccess ());
    assertTrue (aExporter.exportRecords (new ConstantExportRecordProvider (null,
                                                                           CollectionHelper.newList (aRecordWithAllTypes,
                                                                                                    aRecordWithAllTypes,
                                                                                                    aEmptyRecord),
                                                                           aRecordWithAllTypes),
                                         new NonBlockingByteArrayOutputStream ())
                         .isSuccess ());
    assertTrue (aExporter.exportRecords (new ConstantExportRecordProvider (aRecordWithAllTypes,
                                                                           CollectionHelper.newList (aRecordWithAllTypes,
                                                                                                    aRecordWithAllTypes,
                                                                                                    aEmptyRecord),
                                                                           aRecordWithAllTypes),
                                         new NonBlockingByteArrayOutputStream ())
                         .isSuccess ());
  }
}
