/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

/**
 * Contains default JS paths for this project.
 *
 * @author Philip Helger
 */
public enum EDataTablesJSPathProvider implements IJSPathProvider
{
  DATATABLES_1_13 ("external/datatables/DataTables-" + CDataTablesComponentVersion.DT + "/js/jquery.dataTables.js"),
  DATATABLES_1_13_BOOTSTRAP3 ("external/datatables/DataTables-" + CDataTablesComponentVersion.DT + "/js/dataTables.bootstrap.js"),
  DATATABLES_1_13_BOOTSTRAP4 ("external/datatables/DataTables-" + CDataTablesComponentVersion.DT + "/js/dataTables.bootstrap4.js"),
  DATATABLES_AUTO_FILL ("external/datatables/AutoFill-" + CDataTablesComponentVersion.AUTO_FILL + "/js/dataTables.autoFill.js"),
  DATATABLES_AUTO_FILL_BOOTSTRAP3 ("external/datatables/AutoFill-" + CDataTablesComponentVersion.AUTO_FILL + "/js/autoFill.bootstrap.js"),
  DATATABLES_AUTO_FILL_BOOTSTRAP4 ("external/datatables/AutoFill-" +
                                   CDataTablesComponentVersion.AUTO_FILL +
                                   "/js/autoFill.bootstrap4.js"),
  DATATABLES_BUTTONS ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/dataTables.buttons.js"),
  DATATABLES_BUTTONS_BOOTSTRAP3 ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/buttons.bootstrap.js"),
  DATATABLES_BUTTONS_BOOTSTRAP4 ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/buttons.bootstrap4.js"),
  DATATABLES_BUTTONS_COLVIS ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/buttons.colVis.js"),
  DATATABLES_BUTTONS_HTML5 ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/buttons.html5.js"),
  DATATABLES_BUTTONS_PRINT ("external/datatables/Buttons-" + CDataTablesComponentVersion.BUTTONS + "/js/buttons.print.js"),
  DATATABLES_COL_REORDER ("external/datatables/ColReorder-" + CDataTablesComponentVersion.COL_REORDER + "/js/dataTables.colReorder.js"),
  DATATABLES_COL_REORDER_BOOTSTRAP3 ("external/datatables/ColReorder-" +
                                     CDataTablesComponentVersion.COL_REORDER +
                                     "/js/colReorder.bootstrap.js"),
  DATATABLES_COL_REORDER_BOOTSTRAP4 ("external/datatables/ColReorder-" +
                                     CDataTablesComponentVersion.COL_REORDER +
                                     "/js/colReorder.bootstrap4.js"),
  DATATABLES_FIXED_COLUMNS ("external/datatables/FixedColumns-" +
                            CDataTablesComponentVersion.FIXED_COLUMNS +
                            "/js/dataTables.fixedColumns.js"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP3 ("external/datatables/FixedColumns-" +
                                       CDataTablesComponentVersion.FIXED_COLUMNS +
                                       "/js/fixedColumns.bootstrap.js"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP4 ("external/datatables/FixedColumns-" +
                                       CDataTablesComponentVersion.FIXED_COLUMNS +
                                       "/js/fixedColumns.bootstrap4.js"),
  DATATABLES_FIXED_HEADER ("external/datatables/FixedHeader-" +
                           CDataTablesComponentVersion.FIXED_HEADER +
                           "/js/dataTables.fixedHeader.js"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP3 ("external/datatables/FixedHeader-" +
                                      CDataTablesComponentVersion.FIXED_HEADER +
                                      "/js/fixedHeader.bootstrap.js"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP4 ("external/datatables/FixedHeader-" +
                                      CDataTablesComponentVersion.FIXED_HEADER +
                                      "/js/fixedHeader.bootstrap4.js"),
  DATATABLES_KEY_TABLE ("external/datatables/KeyTable-" + CDataTablesComponentVersion.KEY_TABLE + "/js/dataTables.keyTable.js"),
  DATATABLES_KEY_TABLE_BOOTSTRAP3 ("external/datatables/KeyTable-" + CDataTablesComponentVersion.KEY_TABLE + "/js/keyTable.bootstrap.js"),
  DATATABLES_KEY_TABLE_BOOTSTRAP4 ("external/datatables/KeyTable-" +
                                   CDataTablesComponentVersion.KEY_TABLE +
                                   "/js/keyTable.bootstrap4.js"),
  DATATABLES_RESPONSIVE ("external/datatables/Responsive-" + CDataTablesComponentVersion.RESPONSIVE + "/js/dataTables.responsive.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP3 ("external/datatables/Responsive-" +
                                    CDataTablesComponentVersion.RESPONSIVE +
                                    "/js/responsive.bootstrap.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP4 ("external/datatables/Responsive-" +
                                    CDataTablesComponentVersion.RESPONSIVE +
                                    "/js/responsive.bootstrap4.js"),
  DATATABLES_ROW_GROUP ("external/datatables/RowGroup-" + CDataTablesComponentVersion.ROW_GROUP + "/js/dataTables.rowGroup.js"),
  DATATABLES_ROW_GROUP_BOOTSTRAP3 ("external/datatables/RowGroup-" + CDataTablesComponentVersion.ROW_GROUP + "/js/rowGroup.bootstrap.js"),
  DATATABLES_ROW_GROUP_BOOTSTRAP4 ("external/datatables/RowGroup-" +
                                   CDataTablesComponentVersion.ROW_GROUP +
                                   "/js/rowGroup.bootstrap4.js"),
  DATATABLES_ROW_REORDER ("external/datatables/RowReorder-" + CDataTablesComponentVersion.ROW_REORDER + "/js/dataTables.rowReorder.js"),
  DATATABLES_ROW_REORDER_BOOTSTRAP3 ("external/datatables/RowReorder-" +
                                     CDataTablesComponentVersion.ROW_REORDER +
                                     "/js/rowReorder.bootstrap.js"),
  DATATABLES_ROW_REORDER_BOOTSTRAP4 ("external/datatables/RowReorder-" +
                                     CDataTablesComponentVersion.ROW_REORDER +
                                     "/js/rowReorder.bootstrap4.js"),
  DATATABLES_SCROLLER ("external/datatables/Scroller-" + CDataTablesComponentVersion.SCROLLER + "/js/dataTables.scroller.js"),
  DATATABLES_SCROLLER_BOOTSTRAP3 ("external/datatables/Scroller-" + CDataTablesComponentVersion.SCROLLER + "/js/scroller.bootstrap.js"),
  DATATABLES_SCROLLER_BOOTSTRAP4 ("external/datatables/Scroller-" + CDataTablesComponentVersion.SCROLLER + "/js/scroller.bootstrap4.js"),
  DATATABLES_SELECT ("external/datatables/Select-" + CDataTablesComponentVersion.SELECT + "/js/dataTables.select.js"),
  DATATABLES_SELECT_BOOTSTRAP3 ("external/datatables/Select-" + CDataTablesComponentVersion.SELECT + "/js/select.bootstrap.js"),
  DATATABLES_SELECT_BOOTSTRAP4 ("external/datatables/Select-" + CDataTablesComponentVersion.SELECT + "/js/select.bootstrap4.js"),
  DATATABLES_SEARCH_HIGHLIGHT ("ph-oton/searchHighlight/dataTables.searchHighlight.js"),
  DATATABLES_SORTING_MOMENT ("ph-oton/sorting/datetime-moment.js");

  private final ConstantJSPathProvider m_aPP;

  EDataTablesJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.create (sPath);
  }

  @Nonnull
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

  @Nonnull
  public IJSPathProvider getInstance (@Nonnull @Nonempty final String sLanguage)
  {
    return ConstantJSPathProvider.createWithConditionalComment (StringHelper.replaceAll (m_aPP.getJSItemPathRegular (),
                                                                                         "{0}",
                                                                                         sLanguage),
                                                                m_aPP.getConditionalComment ());
  }
}
