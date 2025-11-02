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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;

/**
 * Contains default CSS paths for this project.
 *
 * @author Philip Helger
 */
public enum EDataTablesCSSPathProvider implements ICSSPathProvider
{
  DATATABLES ("external/datatables/datatables-" + CDataTablesComponentVersion.DT + "/css/dataTables.dataTables.css"),
  DATATABLES_AUTO_FILL ("external/datatables/autofill-" +
                        CDataTablesComponentVersion.AUTO_FILL +
                        "/css/autoFill.dataTables.css"),
  DATATABLES_BUTTONS ("external/datatables/buttons-" +
                      CDataTablesComponentVersion.BUTTONS +
                      "/css/buttons.dataTables.css"),
  DATATABLES_COL_REORDER ("external/datatables/colreorder-" +
                          CDataTablesComponentVersion.COL_REORDER +
                          "/css/colReorder.dataTables.css"),
  DATATABLES_FIXED_COLUMNS ("external/datatables/fixedcolumns-" +
                            CDataTablesComponentVersion.FIXED_COLUMNS +
                            "/css/fixedColumns.dataTables.css"),
  DATATABLES_FIXED_HEADER ("external/datatables/fixedheader-" +
                           CDataTablesComponentVersion.FIXED_HEADER +
                           "/css/fixedHeader.dataTables.css"),
  DATATABLES_KEY_TABLE ("external/datatables/keytable-" +
                        CDataTablesComponentVersion.KEY_TABLE +
                        "/css/keyTable.dataTables.css"),
  DATATABLES_RESPONSIVE ("external/datatables/responsive-" +
                         CDataTablesComponentVersion.RESPONSIVE +
                         "/css/responsive.dataTables.css"),
  DATATABLES_ROW_GROUP ("external/datatables/rowgroup-" +
                        CDataTablesComponentVersion.ROW_GROUP +
                        "/css/rowGroup.dataTables.css"),
  DATATABLES_ROW_REORDER ("external/datatables/rowreorder-" +
                          CDataTablesComponentVersion.ROW_REORDER +
                          "/css/rowReorder.dataTables.css"),
  DATATABLES_SCROLLER ("external/datatables/scroller-" +
                       CDataTablesComponentVersion.SCROLLER +
                       "/css/scroller.dataTables.css"),
  DATATABLES_SELECT ("external/datatables/select-" + CDataTablesComponentVersion.SELECT + "/css/select.dataTables.css"),

  DATATABLES_SEARCH_HIGHLIGHT ("ph-oton/searchHighlight/dataTables.searchHighlight.css"),
  BOOTSTRAP4_DATATABLES_PH ("ph-oton/bootstrap4-datatables-ph.css");

  private final ConstantCSSPathProvider m_aPP;

  EDataTablesCSSPathProvider (@NonNull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @NonNull
  @Nonempty
  public String getCSSItemPath (final boolean bRegular)
  {
    return m_aPP.getCSSItemPath (bRegular);
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_aPP.getConditionalComment ();
  }

  @NonNull
  @ReturnsMutableCopy
  public ICSSMediaList getMediaList ()
  {
    return m_aPP.getMediaList ();
  }

  public boolean isBundlable ()
  {
    return m_aPP.isBundlable ();
  }
}
