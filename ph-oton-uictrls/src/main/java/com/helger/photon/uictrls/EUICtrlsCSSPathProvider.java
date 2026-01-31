/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
public enum EUICtrlsCSSPathProvider implements ICSSPathProvider
{
  /** https://github.com/daneden/animate.css */
  ANIMATE ("external/animate/animate.css"),
  AUTONUMERIC ("external/autonumeric/autonumeric.css"),
  COLORBOX ("external/colorbox/1.6.0/colorbox.css"),
  FAMFAM_ICONS ("external/famfam/013/famfam.css"),
  FAMFAM_FLAGS ("external/famfam/flags/flags.css"),
  FINEUPLOADER_320 ("external/fineupload/320/fineuploader.css"),
  FINEUPLOADER_330 ("external/fineupload/330/fineuploader.css"),
  FINEUPLOADER_5 ("external/fineupload/5.16.2/fine-uploader.css"),
  JSCOLOR ("external/jscolor/1.4.4ph/jscolor.css"),
  PRISMJS ("external/prismjs/1.3.0/prism.css"),
  PRISMJS_BOOTSTRAP3 ("external/prismjs/prism-bootstrap3.css"),
  PRISMJS_BOOTSTRAP4 ("external/prismjs/prism-bootstrap4.css"),
  PRISMJS_BOOTSTRAP5 ("external/prismjs/prism-bootstrap5.css"),
  SELECT2 ("external/select2/4.0.13/css/select2.css"),
  SELECT2_BOOTSTRAP4 ("external/select2-bootstrap4-theme/1.5.2/select2-bootstrap4.css"),
  SELECT2_BOOTSTRAP5 ("external/select2-bootstrap5-theme/1.3.0/select2-bootstrap-5-theme.css"),
  TYPEAHEAD_BOOTSTRAP3 ("external/typeahead/typeahead.js-bootstrap3.css"),
  TYPEAHEAD_BOOTSTRAP4 ("external/typeahead/typeahead.js-bootstrap4.css"),
  TYPEAHEAD_BOOTSTRAP5 ("external/typeahead/typeahead.js-bootstrap5.css");

  private final ConstantCSSPathProvider m_aPP;

  EUICtrlsCSSPathProvider (@NonNull @Nonempty final String sPath)
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
