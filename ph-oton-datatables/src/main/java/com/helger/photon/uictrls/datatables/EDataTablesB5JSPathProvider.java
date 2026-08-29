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
package com.helger.photon.uictrls.datatables;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.string.StringReplace;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * Contains default JS paths for this project.
 *
 * @author Philip Helger
 */
public enum EDataTablesB5JSPathProvider implements IJSPathProvider
{
  DATATABLES_BOOTSTRAP5 ("external/datatables/datatables-" +
                         CDataTablesComponentVersion.DT +
                         "/js/dataTables.bootstrap5.js"),
  DATATABLES_AUTO_FILL_BOOTSTRAP5 ("external/datatables/autofill-" +
                                   CDataTablesComponentVersion.AUTO_FILL +
                                   "/js/autoFill.bootstrap5.js"),
  DATATABLES_BUTTONS_BOOTSTRAP5 ("external/datatables/buttons-" +
                                 CDataTablesComponentVersion.BUTTONS +
                                 "/js/buttons.bootstrap5.js"),
  DATATABLES_COL_REORDER_BOOTSTRAP5 ("external/datatables/colreorder-" +
                                     CDataTablesComponentVersion.COL_REORDER +
                                     "/js/colReorder.bootstrap5.js"),
  DATATABLES_COLUMN_CONTROL_BOOTSTRAP5 ("external/datatables/columncontrol-" +
                                        CDataTablesComponentVersion.COLUMN_CONTROL +
                                        "/js/columnControl.bootstrap5.js"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP5 ("external/datatables/fixedcolumns-" +
                                       CDataTablesComponentVersion.FIXED_COLUMNS +
                                       "/js/fixedColumns.bootstrap5.js"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP5 ("external/datatables/fixedheader-" +
                                      CDataTablesComponentVersion.FIXED_HEADER +
                                      "/js/fixedHeader.bootstrap5.js"),
  DATATABLES_KEY_TABLE_BOOTSTRAP5 ("external/datatables/keytable-" +
                                   CDataTablesComponentVersion.KEY_TABLE +
                                   "/js/keyTable.bootstrap5.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP5 ("external/datatables/responsive-" +
                                    CDataTablesComponentVersion.RESPONSIVE +
                                    "/js/responsive.bootstrap5.js"),
  DATATABLES_ROW_GROUP_BOOTSTRAP5 ("external/datatables/rowgroup-" +
                                   CDataTablesComponentVersion.ROW_GROUP +
                                   "/js/rowGroup.bootstrap5.js"),
  DATATABLES_ROW_REORDER_BOOTSTRAP5 ("external/datatables/rowreorder-" +
                                     CDataTablesComponentVersion.ROW_REORDER +
                                     "/js/rowReorder.bootstrap5.js"),
  DATATABLES_SCROLLER_BOOTSTRAP5 ("external/datatables/scroller-" +
                                  CDataTablesComponentVersion.SCROLLER +
                                  "/js/scroller.bootstrap5.js"),
  DATATABLES_SEARCH_BUILDER_BOOTSTRAP5 ("external/datatables/searchbuilder-" +
                                        CDataTablesComponentVersion.SEARCH_BUILDER +
                                        "/js/searchBuilder.bootstrap5.js"),
  DATATABLES_SELECT_BOOTSTRAP5 ("external/datatables/select-" +
                                CDataTablesComponentVersion.SELECT +
                                "/js/select.bootstrap5.js");

  private final ConstantJSPathProvider m_aPP;

  EDataTablesB5JSPathProvider (@NonNull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @NonNull
  @Nonempty
  public String getJSItemPath (final boolean bRegular)
  {
    return m_aPP.getJSItemPath (bRegular);
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_aPP.getConditionalComment ();
  }

  public boolean isBundlable ()
  {
    return m_aPP.isBundlable ();
  }

  @NonNull
  public IJSPathProvider getInstance (@NonNull @Nonempty final String sLanguage)
  {
    return ConstantJSPathProvider.builder ()
                                 .path (StringReplace.replaceAll (m_aPP.getJSItemPathRegular (), "{0}", sLanguage))
                                 .minifiedPathFromPath ()
                                 .conditionalComment (m_aPP.getConditionalComment ())
                                 .build ();
  }
}
