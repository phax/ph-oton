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
package com.helger.webctrls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
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
  ANIMATE ("css/animate.css"),
  AUTONUMERIC ("autonumeric/autonumeric.css"),
  CHART ("chart/Chart.css"),
  COLORBOX ("colorbox/1.6.0/colorbox.css"),
  DATATABLES_1_10 ("datatables/1.10.7/css/jquery.dataTables.css"),
  DATATABLES_THEMEROLLER_1_10 ("datatables/1.10.7/css/jquery.dataTables_themeroller.css"),
  DATATABLES_EXTRAS_COL_VIS ("datatables/ColVis-1.1.2/dataTables.colVis.css"),
  DATATABLES_EXTRAS_FIXED_HEADER ("datatables/FixedHeader-2.1.2/dataTables.fixedHeader.css"),
  DATATABLES_EXTRAS_SCROLLER ("datatables/Scroller-1.2.2/dataTables.scroller.css"),
  DATATABLES_SEARCH_HIGHLIGHT ("datatables/searchHighlight/dataTables.searchHighlight.css"),
  FAMFAM_ICONS ("famfam/013/famfam.css"),
  FAMFAM_FLAGS ("famfam/flags/flags.css"),
  FINEUPLOADER_320 ("fineupload/320/fineuploader.css"),
  FINEUPLOADER_330 ("fineupload/330/fineuploader.css"),
  /** http://fontawesome.io */
  FONT_AWESOME4 ("fontawesome/4.3.0/css/font-awesome.css"),
  SELECT2 ("select2/4.0.0/css/select2.css"),
  TYPEAHEAD_BOOTSTRAP ("typeahead/typeahead.js-bootstrap.css");

  private final ConstantCSSPathProvider m_aPP;

  private EUICtrlsCSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    this (sPath, null);
  }

  private EUICtrlsCSSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = new ConstantCSSPathProvider (sPath, sConditionalComment, (ICSSMediaList) null);
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
}
