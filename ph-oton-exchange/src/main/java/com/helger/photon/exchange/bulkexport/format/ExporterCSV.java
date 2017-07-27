/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
import javax.annotation.concurrent.NotThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.charset.EUnicodeBOM;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
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
@NotThreadSafe
public class ExporterCSV implements IExporterFile
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ExporterCSV.class);

  private Charset m_aCharset;
  private char m_cSeparatorChar = CCSV.DEFAULT_SEPARATOR;
  private char m_cQuoteChar = CCSV.DEFAULT_QUOTE_CHARACTER;
  private char m_cEscapeChar = CCSV.DEFAULT_ESCAPE_CHARACTER;
  private String m_sLineEnd = CSVWriter.DEFAULT_LINE_END;
  private EUnicodeBOM m_eBOM;
  private boolean m_bAvoidWriteEmpty = false;
  private boolean m_bAvoidFinalLineEnd = CSVWriter.DEFAULT_AVOID_FINAL_LINE_END;

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
   */
  @Nonnull
  public ExporterCSV setSeparatorChar (final char cSeparator)
  {
    m_cSeparatorChar = cSeparator;
    return this;
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
  public ExporterCSV setEscapeChar (final char cEscape)
  {
    m_cEscapeChar = cEscape;
    return this;
  }

  public char getEscapeChar ()
  {
    return m_cEscapeChar;
  }

  @Nonnull
  public ExporterCSV setLineEnd (@Nonnull @Nonempty final String sLineEnd)
  {
    ValueEnforcer.notEmpty (sLineEnd, "LineEnd");
    m_sLineEnd = sLineEnd;
    return this;
  }

  @Nonnull
  @Nonempty
  public String getLineEnd ()
  {
    return m_sLineEnd;
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

  /**
   * Enable or disable that empty files are written. By enabling this, all
   * records are collected which results in higher memory consumption but safer
   * output. If this is disabled (the default) than it my happen that nothing is
   * written on the output stream.
   *
   * @param bAvoidWriteEmpty
   *        <code>true</code> to collect before write, <code>false</code> to
   *        write directly
   * @return this for chaining
   * @since 7.0.4
   */
  @Nonnull
  public ExporterCSV setAvoidWriteEmpty (final boolean bAvoidWriteEmpty)
  {
    m_bAvoidWriteEmpty = bAvoidWriteEmpty;
    return this;
  }

  /**
   * @return <code>true</code> if empty files are not written,
   *         <code>false</code> otherwise (by default).
   */
  public boolean isAvoidWriteEmpty ()
  {
    return m_bAvoidWriteEmpty;
  }

  /**
   * Set whether the CSV file should end with a new line or not.
   *
   * @param bAvoidFinalLineEnd
   *        <code>true</code> to avoid the CSV file ending with a new line.
   * @return this for chaining
   * @since 7.1.1
   */
  @Nonnull
  public ExporterCSV setAvoidFinalLineEnd (final boolean bAvoidFinalLineEnd)
  {
    m_bAvoidFinalLineEnd = bAvoidFinalLineEnd;
    return this;
  }

  /**
   * @return <code>true</code> if the written data should end with a line end,
   *         <code>false</code> otherwise (by default).
   * @since 7.1.1
   */
  public boolean isAvoidFinalLineEnd ()
  {
    return m_bAvoidFinalLineEnd;
  }

  @Nonnull
  private static ICommonsList <String> _getAsCSVRecord (@Nonnull final IExportRecord aRecord)
  {
    final ICommonsList <? extends IExportRecordField> aAllFields = aRecord.getAllFields ();
    final ICommonsList <String> aCSVValues = new CommonsArrayList <> (aAllFields.size ());
    for (final IExportRecordField aField : aAllFields)
    {
      final Object aFieldValue = aField.getFieldValue ();
      if (aFieldValue != null)
        aCSVValues.add (TypeConverter.convertIfNecessary (aFieldValue, String.class));
    }
    return aCSVValues;
  }

  /**
   * Create a new CSV writer.
   *
   * @param aOS
   *        The output stream to write to. May not be <code>null</code>.
   * @return The {@link CSVWriter} to used. Never <code>null</code>.
   */
  @Nonnull
  @OverrideOnDemand
  protected CSVWriter createCSVWriter (@Nonnull final OutputStream aOS)
  {
    return new CSVWriter (new OutputStreamWriter (aOS, m_aCharset)).setSeparatorChar (m_cSeparatorChar)
                                                                   .setQuoteChar (m_cQuoteChar)
                                                                   .setEscapeChar (m_cEscapeChar)
                                                                   .setLineEnd (m_sLineEnd)
                                                                   .setAvoidFinalLineEnd (m_bAvoidFinalLineEnd);
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

      if (m_bAvoidWriteEmpty)
      {
        // Collect all records in the correct order
        final ICommonsList <ICommonsList <String>> aCSVRecords = new CommonsArrayList <> ();
        aProvider.forEachHeaderRecord (x -> aCSVRecords.add (_getAsCSVRecord (x)));
        aProvider.forEachBodyRecord (x -> aCSVRecords.add (_getAsCSVRecord (x)));
        aProvider.forEachFooterRecord (x -> aCSVRecords.add (_getAsCSVRecord (x)));

        if (aCSVRecords.isEmpty ())
        {
          // No records to handle
          return ESuccess.FAILURE;
        }

        // Write BOM if necessary
        if (m_eBOM != null)
          aOS.write (m_eBOM.getAllBytes ());

        try (final CSVWriter aWriter = createCSVWriter (aOS))
        {
          aWriter.writeAll (aCSVRecords);
        }
      }
      else
      {
        // Write directly

        // Write BOM if necessary
        if (m_eBOM != null)
          aOS.write (m_eBOM.getAllBytes ());

        try (final CSVWriter aWriter = createCSVWriter (aOS))
        {
          aProvider.forEachHeaderRecord (x -> aWriter.writeNext (_getAsCSVRecord (x)));
          aProvider.forEachBodyRecord (x -> aWriter.writeNext (_getAsCSVRecord (x)));
          aProvider.forEachFooterRecord (x -> aWriter.writeNext (_getAsCSVRecord (x)));
        }
      }
      return ESuccess.SUCCESS;
    }
    catch (final IOException ex)
    {
      s_aLogger.error ("Error exporting to CSV", ex);
      return ESuccess.FAILURE;
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
