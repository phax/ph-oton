/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
  DATATABLES_1_11 ("datatables/DataTables-1.11.5/js/jquery.dataTables.js"),
  DATATABLES_1_11_BOOTSTRAP3 ("datatables/DataTables-1.11.5/js/dataTables.bootstrap.js"),
  DATATABLES_1_11_BOOTSTRAP4 ("datatables/DataTables-1.11.5/js/dataTables.bootstrap4.js"),
  DATATABLES_AUTO_FILL ("datatables/AutoFill-2.3.7/js/dataTables.autoFill.js"),
  DATATABLES_AUTO_FILL_BOOTSTRAP3 ("datatables/AutoFill-2.3.7/js/autoFill.bootstrap.js"),
  DATATABLES_AUTO_FILL_BOOTSTRAP4 ("datatables/AutoFill-2.3.7/js/autoFill.bootstrap4.js"),
  DATATABLES_BUTTONS ("datatables/Buttons-2.2.2/js/dataTables.buttons.js"),
  DATATABLES_BUTTONS_BOOTSTRAP3 ("datatables/Buttons-2.2.2/js/buttons.bootstrap.js"),
  DATATABLES_BUTTONS_BOOTSTRAP4 ("datatables/Buttons-2.2.2/js/buttons.bootstrap4.js"),
  DATATABLES_BUTTONS_COLVIS ("datatables/Buttons-2.2.2/js/buttons.colVis.js"),
  DATATABLES_BUTTONS_HTML5 ("datatables/Buttons-2.2.2/js/buttons.html5.js"),
  DATATABLES_BUTTONS_PRINT ("datatables/Buttons-2.2.2/js/buttons.print.js"),
  DATATABLES_COL_REORDER ("datatables/ColReorder-1.5.5/js/dataTables.colReorder.js"),
  DATATABLES_COL_REORDER_BOOTSTRAP3 ("datatables/ColReorder-1.5.5/js/colReorder.bootstrap.js"),
  DATATABLES_COL_REORDER_BOOTSTRAP4 ("datatables/ColReorder-1.5.5/js/colReorder.bootstrap4.js"),
  DATATABLES_FIXED_COLUMNS ("datatables/FixedColumns-4.0.2/js/dataTables.fixedColumns.js"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP3 ("datatables/FixedColumns-4.0.2/js/fixedColumns.bootstrap.js"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP4 ("datatables/FixedColumns-4.0.2/js/fixedColumns.bootstrap4.js"),
  DATATABLES_FIXED_HEADER ("datatables/FixedHeader-3.2.2/js/dataTables.fixedHeader.js"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP3 ("datatables/FixedHeader-3.2.2/js/fixedHeader.bootstrap.js"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP4 ("datatables/FixedHeader-3.2.2/js/fixedHeader.bootstrap4.js"),
  DATATABLES_KEY_TABLE ("datatables/KeyTable-2.6.4/js/dataTables.keyTable.js"),
  DATATABLES_KEY_TABLE_BOOTSTRAP3 ("datatables/KeyTable-2.6.4/js/keyTable.bootstrap.js"),
  DATATABLES_KEY_TABLE_BOOTSTRAP4 ("datatables/KeyTable-2.6.4/js/keyTable.bootstrap4.js"),
  DATATABLES_RESPONSIVE ("datatables/Responsive-2.2.9/js/dataTables.responsive.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP3 ("datatables/Responsive-2.2.9/js/responsive.bootstrap.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP4 ("datatables/Responsive-2.2.9/js/responsive.bootstrap4.js"),
  DATATABLES_ROW_GROUP ("datatables/RowGroup-1.1.4/js/dataTables.rowGroup.js"),
  DATATABLES_ROW_GROUP_BOOTSTRAP3 ("datatables/RowGroup-1.1.4/js/rowGroup.bootstrap.js"),
  DATATABLES_ROW_GROUP_BOOTSTRAP4 ("datatables/RowGroup-1.1.4/js/rowGroup.bootstrap4.js"),
  DATATABLES_ROW_REORDER ("datatables/RowReorder-1.2.8/js/dataTables.rowReorder.js"),
  DATATABLES_ROW_REORDER_BOOTSTRAP3 ("datatables/RowReorder-1.2.8/js/rowReorder.bootstrap.js"),
  DATATABLES_ROW_REORDER_BOOTSTRAP4 ("datatables/RowReorder-1.2.8/js/rowReorder.bootstrap4.js"),
  DATATABLES_SCROLLER ("datatables/Scroller-2.0.5/js/dataTables.scroller.js"),
  DATATABLES_SCROLLER_BOOTSTRAP3 ("datatables/Scroller-2.0.5/js/scroller.bootstrap.js"),
  DATATABLES_SCROLLER_BOOTSTRAP4 ("datatables/Scroller-2.0.5/js/scroller.bootstrap4.js"),
  DATATABLES_SELECT ("datatables/Select-1.3.4/js/dataTables.select.js"),
  DATATABLES_SELECT_BOOTSTRAP3 ("datatables/Select-1.3.4/js/select.bootstrap.js"),
  DATATABLES_SELECT_BOOTSTRAP4 ("datatables/Select-1.3.4/js/select.bootstrap4.js"),
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
    return ConstantJSPathProvider.createWithConditionalComment (StringHelper.replaceAll (m_aPP.getJSItemPathRegular (), "{0}", sLanguage),
                                                                m_aPP.getConditionalComment ());
  }
}
