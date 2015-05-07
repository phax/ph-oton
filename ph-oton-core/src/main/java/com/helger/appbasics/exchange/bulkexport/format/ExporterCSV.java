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
package com.helger.appbasics.exchange.bulkexport.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.appbasics.exchange.EExchangeFileType;
import com.helger.appbasics.exchange.bulkexport.IExportRecord;
import com.helger.appbasics.exchange.bulkexport.IExportRecordField;
import com.helger.appbasics.exchange.bulkexport.IExportRecordProvider;
import com.helger.appbasics.exchange.bulkexport.IExporterFile;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.EUnicodeBOM;
import com.helger.commons.csv.CCSV;
import com.helger.commons.csv.CSVWriter;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.state.ESuccess;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.typeconvert.TypeConverter;

/**
 * Implementation of {@link IExporterFile} for CSV files.
 *
 * @author Philip Helger
 */
public final class ExporterCSV implements IExporterFile
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ExporterCSV.class);

  private Charset m_aCharset;
  private char m_cSeparator = CCSV.DEFAULT_SEPARATOR;
  private EUnicodeBOM m_eBOM;

  public ExporterCSV ()
  {
    this (SystemHelper.getSystemCharset ());
  }

  public ExporterCSV (@Nonnull final Charset aCharset)
  {
    setCharset (aCharset);
  }

  @Nonnull
  public ExporterCSV setCharset (@Nonnull final Charset aCharset)
  {
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");
    return this;
  }

  @Nonnull
  public Charset getCharset ()
  {
    return m_aCharset;
  }

  @Nonnull
  public ExporterCSV setSeparator (final char cSeparator)
  {
    m_cSeparator = cSeparator;
    return this;
  }

  public char getSeparator ()
  {
    return m_cSeparator;
  }

  @Nonnull
  public ExporterCSV setUnicodeBOM (@Nullable final EUnicodeBOM eBOM)
  {
    m_eBOM = eBOM;
    return this;
  }

  @Nullable
  public EUnicodeBOM getUnicodeBOM ()
  {
    return m_eBOM;
  }

  private static void _emitRecord (final List <List <String>> aRecords, final IExportRecord aRecord)
  {
    final List <? extends IExportRecordField> aAllFields = aRecord.getAllFields ();
    final List <String> aValues = new ArrayList <String> (aAllFields.size ());
    for (final IExportRecordField aField : aAllFields)
    {
      final Object aFieldValue = aField.getFieldValue ();
      if (aFieldValue != null)
        aValues.add (TypeConverter.convertIfNecessary (aFieldValue, String.class));
    }
    aRecords.add (aValues);
  }

  @Override
  @Nonnull
  public ESuccess exportRecords (@Nonnull final IExportRecordProvider aProvider,
                                 @Nonnull @WillClose final OutputStream aOS)
  {
    try
    {
      ValueEnforcer.notNull (aProvider, "Provider");
      ValueEnforcer.notNull (aOS, "OutputStream");

      final List <List <String>> aRecords = new ArrayList <List <String>> ();

      // Header
      for (final IExportRecord aHeaderRecord : aProvider.getHeaderRecords ())
        _emitRecord (aRecords, aHeaderRecord);

      // Body
      for (final IExportRecord aBodyRecord : aProvider.getBodyRecords ())
        _emitRecord (aRecords, aBodyRecord);

      // Footer
      for (final IExportRecord aFooterRecord : aProvider.getFooterRecords ())
        _emitRecord (aRecords, aFooterRecord);

      // The body element is always present
      if (aRecords.isEmpty ())
        return ESuccess.FAILURE;

      CSVWriter aWriter = null;
      try
      {
        // Write BOM if necessary
        if (m_eBOM != null)
          try
          {
            aOS.write (m_eBOM.getBytes ());
          }
          catch (final IOException ex)
          {
            s_aLogger.error ("Failed to write BOM on stream", ex);
          }

        aWriter = new CSVWriter (new OutputStreamWriter (aOS, m_aCharset)).setSeparator (m_cSeparator);
        aWriter.writeAll (aRecords);
        return ESuccess.SUCCESS;
      }
      finally
      {
        StreamUtils.close (aWriter);
      }
    }
    finally
    {
      StreamUtils.close (aOS);
    }
  }

  @Override
  @Nonnull
  public EExchangeFileType getFileType ()
  {
    return EExchangeFileType.CSV;
  }
}
