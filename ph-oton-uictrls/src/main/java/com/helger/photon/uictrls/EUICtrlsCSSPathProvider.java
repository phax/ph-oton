/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
  ANIMATE ("uictrls/animate/animate.css"),
  AUTONUMERIC ("uictrls/autonumeric/autonumeric.css"),
  CHART_1 ("uictrls/chart/Chart.css"),
  COLORBOX ("uictrls/colorbox/1.6.0/colorbox.css"),
  FAMFAM_ICONS ("uictrls/famfam/013/famfam.css"),
  FAMFAM_FLAGS ("uictrls/famfam/flags/flags.css"),
  FINEUPLOADER_320 ("uictrls/fineupload/320/fineuploader.css"),
  FINEUPLOADER_330 ("uictrls/fineupload/330/fineuploader.css"),
  FINEUPLOADER_5 ("uictrls/fineupload/5.11.10/fine-uploader.css"),
  JSCOLOR ("uictrls/jscolor/1.4.4ph/jscolor.css"),
  PRISMJS ("uictrls/prismjs/1.2.1/prism.css"),
  PRISMJS_BOOTSTRAP3 ("uictrls/prismjs/prism-bootstrap3.css"),
  PRISMJS_BOOTSTRAP4 ("uictrls/prismjs/prism-bootstrap4.css"),
  SELECT2 ("uictrls/select2/4.0.13/css/select2.css"),
  TYPEAHEAD_BOOTSTRAP3 ("uictrls/typeahead/typeahead.js-bootstrap3.css"),
  TYPEAHEAD_BOOTSTRAP4 ("uictrls/typeahead/typeahead.js-bootstrap4.css");

  private final ConstantCSSPathProvider m_aPP;

  EUICtrlsCSSPathProvider (@Nonnull @Nonempty final String sPath)
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
