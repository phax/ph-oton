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
package com.helger.photon.uictrls.datatables.plugins;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.name.IHasName;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.app.html.PhotonJS;

/**
 * DataTables Buttons plugin, button type
 *
 * @author Philip Helger
 */
public enum EDTPButtonsButtonType implements IHasName
{
  /** Copy to clipboard - resolves to {@link #COPY_HTML5} */
  COPY ("copy"),
  /** Save to CSV file - resolves to {@link #CSV_HTML5} */
  CSV ("csv"),
  /** Save to Excel XSLX file - resolves to {@link #EXCEL_HTML5} */
  EXCEL ("excel"),
  /** Save to a PDF document - resolves to {@link #PDF_HTML5} */
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
  PRINT ("print"),
  COLLECTION ("collection"),
  COL_VIS ("colvis"),
  COL_VIS_GROUP ("colvisGroup"),
  COLUMNS_TOGGLE ("columnsToggle");

  private final String m_sName;
  private final ICommonsList <IJSPathProvider> m_aJSIncludes;

  EDTPButtonsButtonType (@NonNull @Nonempty final String sName, @Nullable final IJSPathProvider... aJSIncludes)
  {
    m_sName = sName;
    m_aJSIncludes = new CommonsArrayList <> (aJSIncludes);
  }

  @NonNull
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
