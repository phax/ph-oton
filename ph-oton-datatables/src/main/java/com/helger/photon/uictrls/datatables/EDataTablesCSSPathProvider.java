/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
public enum EDataTablesCSSPathProvider implements ICSSPathProvider
{
  DATATABLES_1_13 ("datatables/DataTables-" + DTVersion.DT + "/css/jquery.dataTables.css"),
  DATATABLES_1_13_BOOTSTRAP3 ("datatables/DataTables-" + DTVersion.DT + "/css/dataTables.bootstrap.css"),
  DATATABLES_1_13_BOOTSTRAP4 ("datatables/DataTables-" + DTVersion.DT + "/css/dataTables.bootstrap4.css"),
  DATATABLES_AUTO_FILL ("datatables/AutoFill-" + DTVersion.AUTO_FILL + "/css/autoFill.dataTables.css"),
  DATATABLES_AUTO_FILL_BOOTSTRAP3 ("datatables/AutoFill-" + DTVersion.AUTO_FILL + "/css/autoFill.bootstrap.css"),
  DATATABLES_AUTO_FILL_BOOTSTRAP4 ("datatables/AutoFill-" + DTVersion.AUTO_FILL + "/css/autoFill.bootstrap4.css"),
  DATATABLES_BUTTONS ("datatables/Buttons-" + DTVersion.BUTTONS + "/css/buttons.dataTables.css"),
  DATATABLES_BUTTONS_BOOTSTRAP3 ("datatables/Buttons-" + DTVersion.BUTTONS + "/css/buttons.bootstrap.css"),
  DATATABLES_BUTTONS_BOOTSTRAP4 ("datatables/Buttons-" + DTVersion.BUTTONS + "/css/buttons.bootstrap4.css"),
  DATATABLES_COL_REORDER ("datatables/ColReorder-" + DTVersion.COL_REORDER + "/css/colReorder.dataTables.css"),
  DATATABLES_COL_REORDER_BOOTSTRAP3 ("datatables/ColReorder-" +
                                     DTVersion.COL_REORDER +
                                     "/css/colReorder.bootstrap.css"),
  DATATABLES_COL_REORDER_BOOTSTRAP4 ("datatables/ColReorder-" +
                                     DTVersion.COL_REORDER +
                                     "/css/colReorder.bootstrap4.css"),
  DATATABLES_FIXED_COLUMNS ("datatables/FixedColumns-" + DTVersion.FIXED_COLUMNS + "/css/fixedColumns.dataTables.css"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP3 ("datatables/FixedColumns-" +
                                       DTVersion.FIXED_COLUMNS +
                                       "/css/fixedColumns.bootstrap.css"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP4 ("datatables/FixedColumns-" +
                                       DTVersion.FIXED_COLUMNS +
                                       "/css/fixedColumns.bootstrap4.css"),
  DATATABLES_FIXED_HEADER ("datatables/FixedHeader-" + DTVersion.FIXED_HEADER + "/css/fixedHeader.dataTables.css"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP3 ("datatables/FixedHeader-" +
                                      DTVersion.FIXED_HEADER +
                                      "/css/fixedHeader.bootstrap.css"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP4 ("datatables/FixedHeader-" +
                                      DTVersion.FIXED_HEADER +
                                      "/css/fixedHeader.bootstrap4.css"),
  DATATABLES_KEY_TABLE ("datatables/KeyTable-" + DTVersion.KEY_TABLE + "/css/keyTable.dataTables.css"),
  DATATABLES_KEY_TABLE_BOOTSTRAP3 ("datatables/KeyTable-" + DTVersion.KEY_TABLE + "/css/keyTable.bootstrap.css"),
  DATATABLES_KEY_TABLE_BOOTSTRAP4 ("datatables/KeyTable-" + DTVersion.KEY_TABLE + "/css/keyTable.bootstrap4.css"),
  DATATABLES_RESPONSIVE ("datatables/Responsive-" + DTVersion.RESPONSIVE + "/css/responsive.dataTables.css"),
  DATATABLES_RESPONSIVE_BOOTSTRAP3 ("datatables/Responsive-" + DTVersion.RESPONSIVE + "/css/responsive.bootstrap.css"),
  DATATABLES_RESPONSIVE_BOOTSTRAP4 ("datatables/Responsive-" + DTVersion.RESPONSIVE + "/css/responsive.bootstrap4.css"),
  DATATABLES_ROW_GROUP ("datatables/RowGroup-" + DTVersion.ROW_GROUP + "/css/rowGroup.dataTables.css"),
  DATATABLES_ROW_GROUP_BOOTSTRAP3 ("datatables/RowGroup-" + DTVersion.ROW_GROUP + "/css/rowGroup.bootstrap.css"),
  DATATABLES_ROW_GROUP_BOOTSTRAP4 ("datatables/RowGroup-" + DTVersion.ROW_GROUP + "/css/rowGroup.bootstrap4.css"),
  DATATABLES_ROW_REORDER ("datatables/RowReorder-" + DTVersion.ROW_REORDER + "/css/rowReorder.dataTables.css"),
  DATATABLES_ROW_REORDER_BOOTSTRAP3 ("datatables/RowReorder-" +
                                     DTVersion.ROW_REORDER +
                                     "/css/rowReorder.bootstrap.css"),
  DATATABLES_ROW_REORDER_BOOTSTRAP4 ("datatables/RowReorder-" +
                                     DTVersion.ROW_REORDER +
                                     "/css/rowReorder.bootstrap4.css"),
  DATATABLES_SCROLLER ("datatables/Scroller-" + DTVersion.SCROLLER + "/css/scroller.dataTables.css"),
  DATATABLES_SCROLLER_BOOTSTRAP3 ("datatables/Scroller-" + DTVersion.SCROLLER + "/css/scroller.bootstrap.css"),
  DATATABLES_SCROLLER_BOOTSTRAP4 ("datatables/Scroller-" + DTVersion.SCROLLER + "/css/scroller.bootstrap4.css"),
  DATATABLES_SELECT ("datatables/Select-" + DTVersion.SELECT + "/css/select.dataTables.css"),
  DATATABLES_SELECT_BOOTSTRAP3 ("datatables/Select-" + DTVersion.SELECT + "/css/select.bootstrap.css"),
  DATATABLES_SELECT_BOOTSTRAP4 ("datatables/Select-" + DTVersion.SELECT + "/css/select.bootstrap4.css"),
  DATATABLES_SEARCH_HIGHLIGHT ("ph-oton/searchHighlight/dataTables.searchHighlight.css"),
  BOOTSTRAP3_DATATABLES_PH ("ph-oton/bootstrap3-datatables-ph.css"),
  BOOTSTRAP4_DATATABLES_PH ("ph-oton/bootstrap4-datatables-ph.css");

  private final ConstantCSSPathProvider m_aPP;

  EDataTablesCSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.create (sPath);
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
