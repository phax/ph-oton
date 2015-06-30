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
  DATATABLES_1_10 ("datatables/1.10.7/js/jquery.dataTables.js"),
  DATATABLES_EXTRAS_COL_VIS ("datatables/ColVis-1.1.2/dataTables.colVis.js"),
  DATATABLES_EXTRAS_FIXED_HEADER ("datatables/FixedHeader-2.1.2/dataTables.fixedHeader.js"),
  DATATABLES_EXTRAS_SCROLLER ("datatables/Scroller-1.2.2/dataTables.scroller.js"),
  DATATABLES_SEARCH_HIGHLIGHT ("datatables/searchHighlight/dataTables.searchHighlight.js"),
  EXCANVAS ("chart/excanvas.js", "if lt IE 9"),
  FINEUPLOADER_320 ("fineupload/320/fineuploader.js"),
  FINEUPLOADER_330 ("fineupload/330/fineuploader.js"),
  HANDLEBARS_3 ("handlebars/3.0.3/handlebars-v3.0.3.js"),
  JSCOLOR ("jscolor/1.4.4/jscolor.js"),
  PRISMJS ("prismjs/prism.js"),
  SELECT2 ("select2/4.0.0/js/select2.js"),
  SELECT2_LOCALE ("select2/4.0.0/js/i18n/{0}.js", null, false),
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
    m_aPP = new ConstantJSPathProvider (sPath);
  }

  private EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = new ConstantJSPathProvider (sPath, sConditionalComment, true);
  }

  private EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath,
                                  @Nullable final String sConditionalComment,
                                  final boolean bCanBeBundled)
  {
    m_aPP = new ConstantJSPathProvider (sPath, sConditionalComment, bCanBeBundled);
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

  public boolean canBeBundled ()
  {
    return m_aPP.canBeBundled ();
  }

  @Nonnull
  public IJSPathProvider getInstance (@Nonnull @Nonempty final String sLanguage)
  {
    return new ConstantJSPathProvider (m_aPP.getJSItemPathRegular ().replace ("{0}", sLanguage),
                                       m_aPP.getConditionalComment (),
                                       true);
  }
}
