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
package com.helger.photon.uictrls;

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
public enum EUICtrlsJSPathProvider implements IJSPathProvider
{
  AUTONUMERIC ("autonumeric/1.9.37/autoNumeric.js"),
  AUTOSIZE ("autosize/1.18.18/jquery.autosize.js"),
  AUTOSIZE_ALL ("autosize/autosize-all.js"),
  /** BigDecimal support for JS */
  BIG_DECIMAL ("js/big.js"),
  CHART ("chart/1.0.2/Chart.js"),
  COLORBOX ("colorbox/1.6.0/jquery.colorbox.js"),
  DATATABLES_1_10 ("datatables/1.10.12/js/jquery.dataTables.js"),
  DATATABLES_1_10_BOOTSTRAP ("datatables/1.10.12/js/dataTables.bootstrap.js"),
  DATATABLES_BUTTONS ("datatables/Buttons-1.2.1/js/dataTables.buttons.js"),
  DATATABLES_BUTTONS_BOOTSTRAP ("datatables/Buttons-1.2.1/js/buttons.bootstrap.js"),
  DATATABLES_BUTTONS_COLVIS ("datatables/Buttons-1.2.1/js/buttons.colVis.js"),
  DATATABLES_BUTTONS_FLASH ("datatables/Buttons-1.2.1/js/buttons.flash.js"),
  DATATABLES_BUTTONS_HTML5 ("datatables/Buttons-1.2.1/js/buttons.html5.js"),
  DATATABLES_BUTTONS_PRINT ("datatables/Buttons-1.2.1/js/buttons.print.js"),
  DATATABLES_FIXED_COLUMNS ("datatables/FixedColumns-3.2.2/js/dataTables.fixedColumns.js"),
  DATATABLES_FIXED_HEADER ("datatables/FixedHeader-3.1.2/js/dataTables.fixedHeader.js"),
  DATATABLES_RESPONSIVE ("datatables/Responsive-2.1.0/js/dataTables.responsive.js"),
  DATATABLES_RESPONSIVE_BOOTSTRAP ("datatables/Responsive-2.1.0/js/responsive.bootstrap.js"),
  DATATABLES_ROW_REORDER ("datatables/RowReorder-1.1.2/js/dataTables.rowReorder.js"),
  DATATABLES_SCROLLER ("datatables/Scroller-1.4.2/js/dataTables.scroller.js"),
  DATATABLES_SEARCH_HIGHLIGHT ("datatables/searchHighlight/dataTables.searchHighlight.js"),
  DATATABLES_SELECT ("datatables/Select-1.2.0/js/dataTables.select.js"),
  DATATABLES_SORTING_MOMENT ("datatables/sorting/datetime-moment.js"),
  EXCANVAS ("chart/excanvas.js", "if lt IE 9"),
  FINEUPLOADER_320 ("fineupload/320/fineuploader.js"),
  FINEUPLOADER_330 ("fineupload/330/fineuploader.js"),
  HANDLEBARS_3 ("handlebars/3.0.3/handlebars-v3.0.3.js"),
  JSCOLOR ("jscolor/1.4.4ph/jscolor.js"),
  JSZIP2 ("jszip/2.5.0-6/jszip.js"),
  JSZIP3 ("jszip/3.0.0/jszip.js"),
  PDFMAKE ("pdfmake/0.1.18/pdfmake.js"),
  VFS_FONTS ("pdfmake/0.1.18/vfs_fonts.js"),
  PDFOBJECT1 ("pdfobject/1.2/pdfobject.js"),
  PDFOBJECT2 ("pdfobject/1.2/pdfobject.js"),
  PRISMJS ("prismjs/prism.js"),
  SELECT2 ("select2/4.0.2/js/select2.js"),
  SELECT2_LOCALE ("select2/4.0.2/js/i18n/{0}.js", false),
  TETHER ("tether/1.3.1/js/tether.js"),
  /** https://github.com/twitter/typeahead.js/ */
  TYPEAHEAD_0_9 ("typeahead/0.9.3/typeahead.js"),
  /** https://github.com/twitter/typeahead.js/ */
  TYPEAHEAD_0_11 ("typeahead/0.11.1/typeahead.bundle.js"),
  TYPEAHEAD_PH ("typeahead/ph-typeahead.js"),
  /** JS library to use animate.css - https://github.com/matthieua/WOW */
  WOW ("js/wow.js");

  private final ConstantJSPathProvider m_aPP;

  private EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.create (sPath);
  }

  private EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = ConstantJSPathProvider.createWithConditionalComment (sPath, sConditionalComment);
  }

  private EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath, final boolean bCanBeBundled)
  {
    m_aPP = ConstantJSPathProvider.createBundlable (sPath, bCanBeBundled);
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
