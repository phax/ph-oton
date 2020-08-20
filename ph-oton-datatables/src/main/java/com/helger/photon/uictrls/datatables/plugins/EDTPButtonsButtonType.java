/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.datatables.plugins;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.name.IHasName;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.app.html.PhotonJS;
import com.helger.photon.uictrls.datatables.EDataTablesJSPathProvider;

/**
 * DataTables Buttons plugin, button type
 *
 * @author Philip Helger
 */
public enum EDTPButtonsButtonType implements IHasName
{
  /**
   * Copy to clipboard (uses Flash in preference to HTML5 if Flash is available)
   */
  COPY ("copy"),
  /** Save to CSV file */
  CSV ("csv"),
  /** Save to Excel XSLX file */
  EXCEL ("excel"),
  /** Save to a PDF document */
  PDF ("pdf"),
  /**
   * Displays a dialogue asking the user to use their browser's copy command
   * (HTML5 does not have a copy to clipboard API)
   */
  COPY_HTML5 ("copyHtml5"),
  /** Create and save an CSV file */
  CSV_HTML5 ("csvHtml5"),
  /**
   * Create and save an Excel XLSX file - this requires JSZip. Note - this will
   * not work in Safari.
   */
  EXCEL_HTML5 ("excelHtml5"),
  /**
   * Create and save a PDF document - this required PDFMake and a suitable font
   * file.
   */
  PDF_HTML5 ("pdfHtml5"),
  /**
   * Immediately copies the data to clipboard
   */
  COPY_FLASH ("copyFlash"),
  /** Create and save an CSV file */
  CSV_FLASH ("csvFlash"),
  /** Create and save an Excel XLSX file */
  EXCEL_FLASH ("excelFlash"),
  /**
   * Create and save a PDF document. Note - this does not support UTF8
   * characters.
   */
  PDF_FLASH ("pdfFlash"),
  PRINT ("print"),
  COLLECTION ("collection"),
  COL_VIS ("colvis", EDataTablesJSPathProvider.DATATABLES_BUTTONS_COLVIS),
  COL_VIS_GROUP ("colvisGroup", EDataTablesJSPathProvider.DATATABLES_BUTTONS_COLVIS),
  COLUMNS_TOGGLE ("columnsToggle", EDataTablesJSPathProvider.DATATABLES_BUTTONS_COLVIS);

  private final String m_sName;
  private final ICommonsList <IJSPathProvider> m_aJSIncludes;

  EDTPButtonsButtonType (@Nonnull @Nonempty final String sName, @Nullable final IJSPathProvider... aJSIncludes)
  {
    m_sName = sName;
    m_aJSIncludes = new CommonsArrayList <> (aJSIncludes);
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  public void registerExternalResources ()
  {
    for (final IJSPathProvider aJSPathProvider : m_aJSIncludes)
      PhotonJS.registerJSIncludeForThisRequest (aJSPathProvider);
  }
}
