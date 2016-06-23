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
package com.helger.photon.exchange;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.charset.CCharset;
import com.helger.commons.id.IHasID;
import com.helger.commons.lang.EnumHelper;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.text.display.IHasDisplayText;
import com.helger.photon.exchange.bulkexport.IExporter;
import com.helger.photon.exchange.bulkexport.format.ExporterCSV;
import com.helger.photon.exchange.bulkexport.format.ExporterExcel;
import com.helger.photon.exchange.bulkexport.format.ExporterJSON;
import com.helger.photon.exchange.bulkexport.format.ExporterXML;
import com.helger.poi.excel.EExcelVersion;

/**
 * Defines common file types for import/export.
 *
 * @author Philip Helger
 */
public enum EExchangeFileType implements IHasID <String>, IHasDisplayText
{
  CSV ("csv", ".csv", CMimeType.TEXT_CSV, true, EExchangeFileTypeName.CSV)
  {
    @Override
    @Nonnull
    public ExporterCSV createExporter ()
    {
      return new ExporterCSV ().setCharset (CCharset.CHARSET_ISO_8859_1_OBJ).setSeparator (';');
    }
  },
  XLS ("xls", EExcelVersion.XLS.getFileExtension (), EExcelVersion.XLS.getMimeType (), true, EExchangeFileTypeName.XLS)
  {
    @Override
    @Nonnull
    public ExporterExcel createExporter ()
    {
      return new ExporterExcel (EExcelVersion.XLS);
    }
  },
  XLSX ("xlsx",
        EExcelVersion.XLSX.getFileExtension (),
        EExcelVersion.XLSX.getMimeType (),
        true,
        EExchangeFileTypeName.XLSX)
  {
    @Override
    @Nonnull
    public ExporterExcel createExporter ()
    {
      return new ExporterExcel (EExcelVersion.XLSX);
    }
  },
  XML ("xml", ".xml", CMimeType.TEXT_XML, false, EExchangeFileTypeName.XML)
  {
    @Override
    @Nonnull
    public ExporterXML createExporter ()
    {
      return new ExporterXML ();
    }
  },
  TXT ("txt", ".txt", CMimeType.TEXT_PLAIN, true, EExchangeFileTypeName.TXT)
  {
    @Override
    @Nullable
    public IExporter createExporter ()
    {
      // No default exporter is present
      return null;
    }
  },
  JSON ("json", ".json", CMimeType.APPLICATION_JSON, false, EExchangeFileTypeName.JSON)
  {
    @Override
    @Nonnull
    public ExporterJSON createExporter ()
    {
      return new ExporterJSON ();
    }
  };

  private final String m_sID;
  private final String m_sExt;
  private final IMimeType m_aMimeType;
  private final boolean m_bLineBased;
  private final EExchangeFileTypeName m_aName;

  private EExchangeFileType (@Nonnull @Nonempty final String sID,
                             @Nonnull @Nonempty final String sExt,
                             @Nonnull final IMimeType aMimeType,
                             final boolean bLineBased,
                             @Nonnull final EExchangeFileTypeName aName)
  {
    m_sID = sID;
    m_sExt = sExt;
    m_aMimeType = aMimeType;
    m_bLineBased = bLineBased;
    m_aName = aName;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return The desired file extension including the leading dot.
   */
  @Nonnull
  @Nonempty
  public String getFileExtension ()
  {
    return m_sExt;
  }

  /**
   * @return The MIMe type for created files.
   */
  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  /**
   * @return <code>true</code> if this file type is line based. This is e.g. the
   *         case for CSV or Excel files.
   */
  public boolean isLineBased ()
  {
    return m_bLineBased;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aName.getDisplayText (aContentLocale);
  }

  @Nullable
  public String getExportAsText (@Nonnull final Locale aContentLocale)
  {
    return EExchangeFileTypeText.EXPORT_AS.getDisplayTextWithArgs (aContentLocale, getDisplayText (aContentLocale));
  }

  @Nullable
  public String getSaveAsText (@Nonnull final Locale aContentLocale)
  {
    return EExchangeFileTypeText.SAVE_AS.getDisplayTextWithArgs (aContentLocale, getDisplayText (aContentLocale));
  }

  @Nullable
  public abstract IExporter createExporter ();

  @Nullable
  public static EExchangeFileType getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (EExchangeFileType.class, sID);
  }

  @Nullable
  public static EExchangeFileType getFromIDOrDefault (@Nullable final String sID,
                                                      @Nullable final EExchangeFileType eDefault)
  {
    return EnumHelper.getFromIDOrDefault (EExchangeFileType.class, sID, eDefault);
  }
}
