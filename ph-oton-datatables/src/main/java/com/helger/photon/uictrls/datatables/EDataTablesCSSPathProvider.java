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
  DATATABLES_1_10 ("datatables/1.10.12/css/jquery.dataTables.css"),
  DATATABLES_1_10_THEMEROLLER ("datatables/1.10.12/css/jquery.dataTables_themeroller.css"),
  DATATABLES_1_10_BOOTSTRAP ("datatables/1.10.12/css/dataTables.bootstrap.css"),
  DATATABLES_BUTTONS ("datatables/Buttons-1.2.1/css/buttons.dataTables.css"),
  DATATABLES_BUTTONS_BOOTSTRAP ("datatables/Buttons-1.2.1/css/buttons.bootstrap.css"),
  DATATABLES_FIXED_COLUMNS ("datatables/FixedColumns-3.2.2/css/fixedColumns.dataTables.css"),
  DATATABLES_FIXED_COLUMNS_BOOTSTRAP ("datatables/FixedColumns-3.2.2/css/fixedColumns.bootstrap.css"),
  DATATABLES_FIXED_HEADER ("datatables/FixedHeader-3.1.2/css/fixedHeader.dataTables.css"),
  DATATABLES_FIXED_HEADER_BOOTSTRAP ("datatables/FixedHeader-3.1.2/css/fixedHeader.bootstrap.css"),
  DATATABLES_RESPONSIVE ("datatables/Responsive-2.1.0/css/responsive.dataTables.css"),
  DATATABLES_RESPONSIVE_BOOTSTRAP ("datatables/Responsive-2.1.0/css/responsive.bootstrap.css"),
  DATATABLES_ROW_REORDER ("datatables/RowReorder-1.1.2/css/rowReorder.dataTables.css"),
  DATATABLES_ROW_REORDER_BOOTSTRAP ("datatables/RowReorder-1.1.2/css/rowReorder.bootstrap.css"),
  DATATABLES_SCROLLER ("datatables/Scroller-1.4.2/css/scroller.dataTables.css"),
  DATATABLES_SCROLLER_BOOTSTRAP ("datatables/Scroller-1.4.2/css/scroller.bootstrap.css"),
  DATATABLES_SEARCH_HIGHLIGHT ("datatables/searchHighlight/dataTables.searchHighlight.css"),
  DATATABLES_SELECT ("datatables/Select-1.2.0/css/select.dataTables.css"),
  DATATABLES_SELECT_BOOTSTRAP ("datatables/Select-1.2.0/css/select.bootstrap.css");

  private final ConstantCSSPathProvider m_aPP;

  private EDataTablesCSSPathProvider (@Nonnull @Nonempty final String sPath)
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
