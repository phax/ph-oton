/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
  DATATABLES ("external/datatables/datatables-" + CDataTablesComponentVersion.DT + "/js/dataTables.js"),
  DATATABLES_AUTO_FILL ("external/datatables/autofill-" +
                        CDataTablesComponentVersion.AUTO_FILL +
                        "/js/dataTables.autoFill.js"),
  DATATABLES_BUTTONS ("external/datatables/buttons-" +
                      CDataTablesComponentVersion.BUTTONS +
                      "/js/dataTables.buttons.js"),
  DATATABLES_BUTTONS_COLVIS ("external/datatables/buttons-" +
                             CDataTablesComponentVersion.BUTTONS +
                             "/js/buttons.colVis.js"),
  DATATABLES_BUTTONS_HTML5 ("external/datatables/buttons-" +
                            CDataTablesComponentVersion.BUTTONS +
                            "/js/buttons.html5.js"),
  DATATABLES_BUTTONS_PRINT ("external/datatables/buttons-" +
                            CDataTablesComponentVersion.BUTTONS +
                            "/js/buttons.print.js"),
  DATATABLES_COL_REORDER ("external/datatables/colreorder-" +
                          CDataTablesComponentVersion.COL_REORDER +
                          "/js/dataTables.colReorder.js"),
  DATATABLES_FIXED_COLUMNS ("external/datatables/fixedcolumns-" +
                            CDataTablesComponentVersion.FIXED_COLUMNS +
                            "/js/dataTables.fixedColumns.js"),
  DATATABLES_FIXED_HEADER ("external/datatables/fixedheader-" +
                           CDataTablesComponentVersion.FIXED_HEADER +
                           "/js/dataTables.fixedHeader.js"),
  DATATABLES_KEY_TABLE ("external/datatables/keytable-" +
                        CDataTablesComponentVersion.KEY_TABLE +
                        "/js/dataTables.keyTable.js"),
  DATATABLES_RESPONSIVE ("external/datatables/responsive-" +
                         CDataTablesComponentVersion.RESPONSIVE +
                         "/js/dataTables.responsive.js"),
  DATATABLES_ROW_GROUP ("external/datatables/rowgroup-" +
                        CDataTablesComponentVersion.ROW_GROUP +
                        "/js/dataTables.rowGroup.js"),
  DATATABLES_ROW_REORDER ("external/datatables/rowreorder-" +
                          CDataTablesComponentVersion.ROW_REORDER +
                          "/js/dataTables.rowReorder.js"),
  DATATABLES_SCROLLER ("external/datatables/scroller-" +
                       CDataTablesComponentVersion.SCROLLER +
                       "/js/dataTables.scroller.js"),
  DATATABLES_SELECT ("external/datatables/select-" + CDataTablesComponentVersion.SELECT + "/js/dataTables.select.js"),

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
