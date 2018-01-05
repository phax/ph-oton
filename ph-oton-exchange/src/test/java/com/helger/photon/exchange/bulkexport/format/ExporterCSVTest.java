/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.exchange.bulkexport.format;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.stream.NonBlockingByteArrayOutputStream;
import com.helger.photon.exchange.bulkexport.ConstantExportRecordProvider;
import com.helger.photon.exchange.bulkexport.EmptyExportRecordProvider;
import com.helger.photon.exchange.bulkexport.ExportRecord;

/**
 * Test class for class {@link ExporterCSV}.
 *
 * @author Philip Helger
 */
public final class ExporterCSVTest
{
  @Test
  public void testBasicExport ()
  {
    final ExportRecord aRecordWithAllTypes = new ExportRecord ().addField ("Hallo")
                                                                .addField (PDTFactory.getCurrentLocalTime ())
                                                                .addField (PDTFactory.getCurrentLocalDate ())
                                                                .addField (PDTFactory.getCurrentLocalDateTime ())
                                                                .addField (PDTFactory.getCurrentZonedDateTime ())
                                                                .addField (PDTFactory.getCurrentOffsetDateTime ())
                                                                .addField (true)
                                                                .addField (4711)
                                                                .addField (-123456789012345L)
                                                                .addField (new BigInteger ("1234512345123451234512345123451234512345123451234512345"))
                                                                .addField (3.1145)
                                                                .addField (new BigDecimal ("12345123451234512345123451234512345123451234512345.12345"));
    final ExportRecord aEmptyRecord = new ExportRecord ();
    final ExporterCSV aExporter = new ExporterCSV (StandardCharsets.ISO_8859_1);
    // Fails because no record is present
    assertTrue (aExporter.setAvoidWriteEmpty (true)
                         .exportRecords (new EmptyExportRecordProvider (), new NonBlockingByteArrayOutputStream ())
                         .isFailure ());
    assertTrue (aExporter.setAvoidWriteEmpty (false)
                         .exportRecords (new EmptyExportRecordProvider (), new NonBlockingByteArrayOutputStream ())
                         .isSuccess ());
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
