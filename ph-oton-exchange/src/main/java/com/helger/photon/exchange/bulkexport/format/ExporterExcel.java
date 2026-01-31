/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.WillClose;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.io.stream.StreamHelper;
import com.helger.base.state.ESuccess;
import com.helger.photon.exchange.EExchangeFileType;
import com.helger.photon.exchange.bulkexport.EExportDataType;
import com.helger.photon.exchange.bulkexport.EExportRecordType;
import com.helger.photon.exchange.bulkexport.IExportRecord;
import com.helger.photon.exchange.bulkexport.IExportRecordField;
import com.helger.photon.exchange.bulkexport.IExportRecordProvider;
import com.helger.photon.exchange.bulkexport.IExporterFile;
import com.helger.poi.excel.EExcelVersion;
import com.helger.poi.excel.WorkbookCreationHelper;
import com.helger.poi.excel.style.ExcelStyle;
import com.helger.typeconvert.impl.TypeConverter;

/**
 * Export records to Excel workbook.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class ExporterExcel implements IExporterFile
{
  public static final boolean DEFAULT_AUTOSIZE_ALL_COLUMNS = true;
  private static final ExcelStyle DEFAULT_STYLE_DATE = new ExcelStyle ().setDataFormat ("dd.mm.yyyy");
  private static final ExcelStyle DEFAULT_STYLE_TIME = new ExcelStyle ().setDataFormat ("hh:mm:ss");
  private static final ExcelStyle DEFAULT_STYLE_DATETIME = new ExcelStyle ().setDataFormat ("dd.mm.yyyy hh:mm:ss");

  private final EExcelVersion m_eVersion;
  private ExcelStyle m_aStyleBoolean;
  private ExcelStyle m_aStyleInt;
  private ExcelStyle m_aStyleDouble;
  private ExcelStyle m_aStyleText;
  private ExcelStyle m_aStyleDate = DEFAULT_STYLE_DATE;
  private ExcelStyle m_aStyleTime = DEFAULT_STYLE_TIME;
  private ExcelStyle m_aStyleDateTime = DEFAULT_STYLE_DATETIME;
  private boolean m_bAutoSizeAllColumns = DEFAULT_AUTOSIZE_ALL_COLUMNS;

  public ExporterExcel (@NonNull final EExcelVersion eVersion)
  {
    m_eVersion = ValueEnforcer.notNull (eVersion, "Version");
  }

  @NonNull
  public final EExchangeFileType getFileType ()
  {
    return m_eVersion.equals (EExcelVersion.XLS) ? EExchangeFileType.XLS : EExchangeFileType.XLSX;
  }

  @NonNull
  public final EExcelVersion getExcelVersion ()
  {
    return m_eVersion;
  }

  @Nullable
  @ReturnsMutableCopy
  public final ExcelStyle getStyleBoolean ()
  {
    return m_aStyleBoolean == null ? null : m_aStyleBoolean.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleBoolean (@Nullable final ExcelStyle aStyle)
  {
    m_aStyleBoolean = aStyle == null ? null : aStyle.getClone ();
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public final ExcelStyle getStyleInt ()
  {
    return m_aStyleInt == null ? null : m_aStyleInt.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleInt (@Nullable final ExcelStyle aStyle)
  {
    m_aStyleInt = aStyle == null ? null : aStyle.getClone ();
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public final ExcelStyle getStyleDouble ()
  {
    return m_aStyleDouble == null ? null : m_aStyleDouble.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleDouble (@Nullable final ExcelStyle aStyle)
  {
    m_aStyleDouble = aStyle == null ? null : aStyle.getClone ();
    return this;
  }

  @Nullable
  @ReturnsMutableCopy
  public final ExcelStyle getStyleText ()
  {
    return m_aStyleText == null ? null : m_aStyleText.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleText (@Nullable final ExcelStyle aStyle)
  {
    m_aStyleText = aStyle == null ? null : aStyle.getClone ();
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public final ExcelStyle getStyleDate ()
  {
    return m_aStyleDate.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleDate (@NonNull final ExcelStyle aStyle)
  {
    m_aStyleDate = ValueEnforcer.notNull (aStyle, "Style").getClone ();
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public final ExcelStyle getStyleTime ()
  {
    return m_aStyleTime.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleTime (@NonNull final ExcelStyle aStyle)
  {
    m_aStyleTime = ValueEnforcer.notNull (aStyle, "Style").getClone ();
    return this;
  }

  @NonNull
  @ReturnsMutableCopy
  public final ExcelStyle getStyleDateTime ()
  {
    return m_aStyleDateTime.getClone ();
  }

  @NonNull
  public final ExporterExcel setStyleDateTime (@NonNull final ExcelStyle aStyle)
  {
    m_aStyleDateTime = ValueEnforcer.notNull (aStyle, "Style").getClone ();
    return this;
  }

  public final boolean isAutoSizeAllColumns ()
  {
    return m_bAutoSizeAllColumns;
  }

  @NonNull
  public final ExporterExcel setAutoSizeAllColumns (final boolean bAutoSizeAllColumns)
  {
    m_bAutoSizeAllColumns = bAutoSizeAllColumns;
    return this;
  }

  /**
   * Callback when a new row is created.
   *
   * @param aWBCH
   *        The creation helper
   * @param eRecordType
   *        The record type
   * @param aRow
   *        The created row.
   * @param nRowIndex
   *        The 0-based total index of the row (including header)
   */
  @OverrideOnDemand
  protected void onAddRow (@NonNull final WorkbookCreationHelper aWBCH,
                           @NonNull final EExportRecordType eRecordType,
                           @NonNull final Row aRow,
                           @Nonnegative final int nRowIndex)
  {}

  /**
   * Callback when a new row is created.
   *
   * @param aWBCH
   *        The creation helper
   * @param eRecordType
   *        The record type
   * @param aCell
   *        The created cell.
   * @param nCellIndex
   *        The 0-based index of the cell in the current row
   * @param eBaseType
   *        The data type of the last cells data.
   */
  @OverrideOnDemand
  protected void onAddCell (@NonNull final WorkbookCreationHelper aWBCH,
                            @NonNull final EExportRecordType eRecordType,
                            @NonNull final Cell aCell,
                            @Nonnegative final int nCellIndex,
                            @NonNull final EExportDataType eBaseType)
  {}

  private void _emitRecord (@NonNull final WorkbookCreationHelper aWBCH,
                            @NonNull final EExportRecordType eRecordType,
                            @NonNull final IExportRecord aRecord)
  {
    final int nRowIndex = aWBCH.getRowCount ();
    final Row aRow = aWBCH.addRow ();

    // Callback
    onAddRow (aWBCH, eRecordType, aRow, nRowIndex);

    for (final IExportRecordField aField : aRecord.getAllFields ())
    {
      final Object aFieldValue = aField.getFieldValue ();
      if (aFieldValue == null)
      {
        // Add an empty cell
        aWBCH.addCell ();
      }
      else
      {
        final Cell aCell;
        final int nCellIndex = aWBCH.getCellCountInRow ();
        switch (aField.getFieldType ())
        {
          case BOOLEAN:
            aCell = aWBCH.addCell (((Boolean) aFieldValue).booleanValue ());
            if (m_aStyleBoolean != null)
              aWBCH.addCellStyle (m_aStyleBoolean);
            break;
          case DOUBLE:
            aCell = aWBCH.addCell (((Number) aFieldValue).doubleValue ());
            if (m_aStyleDouble != null)
              aWBCH.addCellStyle (m_aStyleDouble);
            break;
          case INT:
            aCell = aWBCH.addCell (((Number) aFieldValue).intValue ());
            if (m_aStyleInt != null)
              aWBCH.addCellStyle (m_aStyleInt);
            break;
          case TEXT:
            aCell = aWBCH.addCell ((String) aFieldValue);
            if (m_aStyleText != null)
              aWBCH.addCellStyle (m_aStyleText);
            break;
          case DATE:
            aCell = aWBCH.addCell (TypeConverter.convert (aFieldValue, Date.class));
            aWBCH.addCellStyle (m_aStyleDate);
            break;
          case TIME:
            aCell = aWBCH.addCell (TypeConverter.convert (aFieldValue, Date.class));
            aWBCH.addCellStyle (m_aStyleTime);
            break;
          case DATETIME:
            if (aFieldValue instanceof LocalDateTime)
            {
              // No timezone
              aCell = aWBCH.addCell (TypeConverter.convert (aFieldValue, Date.class));
            }
            else
            {
              // Here we have a timezone -> use calendar
              aCell = aWBCH.addCell (TypeConverter.convert (aFieldValue, GregorianCalendar.class));
            }
            aWBCH.addCellStyle (m_aStyleDateTime);
            break;
          default:
            throw new IllegalArgumentException ("The type " + aField.getFieldType () + " cannot be written to Excel!");
        }

        // Callback
        onAddCell (aWBCH, eRecordType, aCell, nCellIndex, aField.getFieldType ());
      }
    }
  }

  @NonNull
  public final ESuccess exportRecords (@NonNull final IExportRecordProvider aProvider,
                                       @NonNull @WillClose final OutputStream aOS)
  {
    ValueEnforcer.notNull (aProvider, "Provider");
    ValueEnforcer.notNull (aOS, "OutputStream");

    try (final WorkbookCreationHelper aWBCH = new WorkbookCreationHelper (m_eVersion))
    {
      aWBCH.createNewSheet ();

      // Header
      aProvider.forEachHeaderRecord (x -> _emitRecord (aWBCH, EExportRecordType.HEADER, x));

      // Body
      aProvider.forEachBodyRecord (x -> _emitRecord (aWBCH, EExportRecordType.BODY, x));

      // Footer
      aProvider.forEachFooterRecord (x -> _emitRecord (aWBCH, EExportRecordType.FOOTER, x));

      if (aWBCH.getRowCount () == 0)
        return ESuccess.FAILURE;

      if (m_bAutoSizeAllColumns)
        aWBCH.autoSizeAllColumns ();
      return aWBCH.writeTo (aOS);
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }
}
