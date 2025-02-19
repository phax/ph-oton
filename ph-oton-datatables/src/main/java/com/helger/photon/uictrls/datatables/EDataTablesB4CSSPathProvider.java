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
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;

/**
 * Contains default CSS paths for this project.
 *
 * @author Philip Helger
 */
public enum EDataTablesB4CSSPathProvider implements ICSSPathProvider
{
  DATATABLES_BOOTSTRAP4 ("external/datatables/datatables-" +
                         CDataTablesComponentVersion.DT +
                         "/css/dataTables.bootstrap4.css"),
  DATATABLES_AUTO_FILL_BOOTSTRAP4 ("external/datatables/autofill-" +
                                   CDataTablesComponentVersion.AUTO_FILL +
                                   "/css/autoFill.bootstrap4.css"),
  DATATABLES_BUTTONS_BOOTSTRAP4 ("external/datatables/buttons-" +
                                 CDataTablesComponentVersion.BUTTONS +
                                 "/css/buttons.bootstrap4.css"),
  DATATABLES_COL_REORDER_BOOTSTRAP4 ("external/datatables/colreorder-" +
                                     CDataTablesComponentVersion.COL_REORDER +
                                     "/css/colReorder.bootstrap4.css"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP4 ("external/datatables/fixedcolumns-" +
                                       CDataTablesComponentVersion.FIXED_COLUMNS +
                                       "/css/fixedColumns.bootstrap4.css"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP4 ("external/datatables/fixedheader-" +
                                      CDataTablesComponentVersion.FIXED_HEADER +
                                      "/css/fixedHeader.bootstrap4.css"),
  DATATABLES_KEY_TABLE_BOOTSTRAP4 ("external/datatables/keytable-" +
                                   CDataTablesComponentVersion.KEY_TABLE +
                                   "/css/keyTable.bootstrap4.css"),
  DATATABLES_RESPONSIVE_BOOTSTRAP4 ("external/datatables/responsive-" +
                                    CDataTablesComponentVersion.RESPONSIVE +
                                    "/css/responsive.bootstrap4.css"),
  DATATABLES_ROW_GROUP_BOOTSTRAP4 ("external/datatables/rowgroup-" +
                                   CDataTablesComponentVersion.ROW_GROUP +
                                   "/css/rowGroup.bootstrap4.css"),
  DATATABLES_ROW_REORDER_BOOTSTRAP4 ("external/datatables/rowreorder-" +
                                     CDataTablesComponentVersion.ROW_REORDER +
                                     "/css/rowReorder.bootstrap4.css"),
  DATATABLES_SCROLLER_BOOTSTRAP4 ("external/datatables/scroller-" +
                                  CDataTablesComponentVersion.SCROLLER +
                                  "/css/scroller.bootstrap4.css"),
  DATATABLES_SELECT_BOOTSTRAP4 ("external/datatables/select-" +
                                CDataTablesComponentVersion.SELECT +
                                "/css/select.bootstrap4.css");

  private final ConstantCSSPathProvider m_aPP;

  EDataTablesB4CSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @Nonnull
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

  @Nonnull
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
