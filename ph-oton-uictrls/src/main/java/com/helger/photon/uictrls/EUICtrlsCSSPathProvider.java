/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls;

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
public enum EUICtrlsCSSPathProvider implements ICSSPathProvider
{
 /** https://github.com/daneden/animate.css */
  ANIMATE ("animate/animate.css"),
 AUTONUMERIC ("autonumeric/autonumeric.css"),
 CHART ("chart/Chart.css"),
 COLORBOX ("colorbox/1.6.0/colorbox.css"),
 DATATABLES_1_10 ("datatables/1.10.9/css/jquery.dataTables.css"),
 DATATABLES_1_10_THEMEROLLER ("datatables/1.10.9/css/jquery.dataTables_themeroller.css"),
 DATATABLES_1_10_BOOTSTRAP ("datatables/1.10.9/css/dataTables.bootstrap.css"),
 DATATABLES_BUTTONS ("datatables/Buttons-1.0.0/css/buttons.dataTables.css"),
 DATATABLES_BUTTONS_BOOTSTRAP ("datatables/Buttons-1.0.0/css/buttons.bootstrap.css"),
 DATATABLES_FIXED_COLUMNS ("datatables/FixedColumns-3.1.0/css/fixedColumns.dataTables.css"),
 DATATABLES_FIXED_COLUMNS_BOOTSTRAP ("datatables/FixedColumns-3.1.0/css/fixedColumns.bootstrap.css"),
 DATATABLES_FIXED_HEADER ("datatables/FixedHeader-3.0.0/css/fixedHeader.dataTables.css"),
 DATATABLES_FIXED_HEADER_BOOTSTRAP ("datatables/FixedHeader-3.0.0/css/fixedHeader.bootstrap.css"),
 DATATABLES_RESPONSIVE ("datatables/Responsive-1.0.7/css/responsive.dataTables.css"),
 DATATABLES_RESPONSIVE_BOOTSTRAP ("datatables/Responsive-1.0.7/css/responsive.bootstrap.css"),
 DATATABLES_ROW_REORDER ("datatables/RowReorder-1.0.0/css/rowReorder.dataTables.css"),
 DATATABLES_ROW_REORDER_BOOTSTRAP ("datatables/RowReorder-1.0.0/css/rowReorder.bootstrap.css"),
 DATATABLES_SCROLLER ("datatables/Scroller-1.3.0/css/scroller.dataTables.css"),
 DATATABLES_SCROLLER_BOOTSTRAP ("datatables/Scroller-1.3.0/css/scroller.bootstrap.css"),
 DATATABLES_SEARCH_HIGHLIGHT ("datatables/searchHighlight/dataTables.searchHighlight.css"),
 DATATABLES_SELECT ("datatables/Select-1.0.1/css/select.dataTables.css"),
 DATATABLES_SELECT_BOOTSTRAP ("datatables/Select-1.0.1/css/select.bootstrap.css"),
 FAMFAM_ICONS ("famfam/013/famfam.css"),
 FAMFAM_FLAGS ("famfam/flags/flags.css"),
 FINEUPLOADER_320 ("fineupload/320/fineuploader.css"),
 FINEUPLOADER_330 ("fineupload/330/fineuploader.css"),
 /** http://fontawesome.io */
  FONT_AWESOME4 ("fontawesome/4.3.0/css/font-awesome.css"),
 PRISMJS ("prismjs/prism.css"),
 PRISMJS_BOOTSTRAP ("prismjs/prism-bootstrap.css"),
 SELECT2 ("select2/4.0.0/css/select2.css"),
 TETHER ("tether/1.1.1/css/tether.css"),
 TETHER_THEME_ARROWS ("tether/1.1.1/css/tether-theme-arrows.css"),
 TETHER_THEME_ARROWS_DARK ("tether/1.1.1/css/tether-theme-arrows-dark.css"),
 TETHER_THEME_BASIC ("tether/1.1.1/css/tether-theme-basic.css"),
 TYPEAHEAD_BOOTSTRAP ("typeahead/typeahead.js-bootstrap.css");

  private final ConstantCSSPathProvider m_aPP;

  private EUICtrlsCSSPathProvider (@Nonnull @Nonempty final String sPath)
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
