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
package com.helger.photon.exchange.bulkexport.format;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.EUnicodeBOM;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.csv.CCSV;
import com.helger.commons.csv.CSVWriter;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.ESuccess;
import com.helger.commons.system.SystemHelper;
import com.helger.commons.typeconvert.TypeConverter;
import com.helger.photon.exchange.EExchangeFileType;
import com.helger.photon.exchange.bulkexport.IExportRecord;
import com.helger.photon.exchange.bulkexport.IExportRecordField;
import com.helger.photon.exchange.bulkexport.IExportRecordProvider;
import com.helger.photon.exchange.bulkexport.IExporterFile;

/**
 * Implementation of {@link IExporterFile} for CSV files.
 *
 * @author Philip Helger
 */
public final class ExporterCSV implements IExporterFile
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ExporterCSV.class);

  private Charset m_aCharset;
  private char m_cSeparatorChar = CCSV.DEFAULT_SEPARATOR;
  private char m_cQuoteChar = CCSV.DEFAULT_QUOTE_CHARACTER;
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

  /**
   * @param cSeparator
   *        Separator char
   * @return this for chaining
   * @deprecated Use {@link #setSeparatorChar(char)} instead
   */
  @Deprecated
  @Nonnull
  public ExporterCSV setSeparator (final char cSeparator)
  {
    return setSeparatorChar (cSeparator);
  }

  /**
   * @param cSeparator
   *        Separator char
   * @return this for chaining
   */
  @Nonnull
  public ExporterCSV setSeparatorChar (final char cSeparator)
  {
    m_cSeparatorChar = cSeparator;
    return this;
  }

  /**
   * @return Current separator char
   * @deprecated Use {@link #getSeparatorChar()} instead
   */
  @Deprecated
  public char getSeparator ()
  {
    return getSeparatorChar ();
  }

  /**
   * @return Current separator char
   */
  public char getSeparatorChar ()
  {
    return m_cSeparatorChar;
  }

  @Nonnull
  public ExporterCSV setQuoteChar (final char cQuote)
  {
    m_cQuoteChar = cQuote;
    return this;
  }

  public char getQuoteChar ()
  {
    return m_cQuoteChar;
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

  private static void _emitRecord (@Nonnull final ICommonsList <ICommonsList <String>> aRecords,
                                   @Nonnull final IExportRecord aRecord)
  {
    final ICommonsList <? extends IExportRecordField> aAllFields = aRecord.getAllFields ();
    final ICommonsList <String> aValues = new CommonsArrayList<> (aAllFields.size ());
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

      final ICommonsList <ICommonsList <String>> aRecords = new CommonsArrayList<> ();

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

      // Write BOM if necessary
      if (m_eBOM != null)
        try
        {
          aOS.write (m_eBOM.getAllBytes ());
        }
        catch (final IOException ex)
        {
          s_aLogger.error ("Failed to write BOM on stream", ex);
        }

      try (final CSVWriter aWriter = new CSVWriter (new OutputStreamWriter (aOS,
                                                                            m_aCharset)).setSeparatorChar (m_cSeparatorChar)
                                                                                        .setQuoteChar (m_cQuoteChar))
      {
        aWriter.writeAll (aRecords);
        return ESuccess.SUCCESS;
      }
      catch (final IOException ex)
      {
        return ESuccess.FAILURE;
      }
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }

  @Override
  @Nonnull
  public EExchangeFileType getFileType ()
  {
    return EExchangeFileType.CSV;
  }
}
