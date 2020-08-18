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
  AUTONUMERIC ("uictrls/autonumeric/1.9.46/autoNumeric.js"),
  AUTOSIZE3 ("uictrls/autosize/3.0.20/autosize.js"),
  AUTOSIZE3_ALL ("uictrls/autosize/autosize3-all.js"),
  /** BigDecimal support for JS */
  BIG_DECIMAL ("uictrls/js/big.js"),
  CHART_1 ("uictrls/chart/1.0.2/Chart.js"),
  CHART_2 ("uictrls/chart/2.2.1/Chart.js"),
  CLIPBOARD ("uictrls/clipboardjs/2.0.6/clipboard.js"),
  COLORBOX ("uictrls/colorbox/1.6.0/jquery.colorbox.js"),
  EXCANVAS ("uictrls/chart/excanvas.js", "if lt IE 9"),
  FINEUPLOADER_320 ("uictrls/fineupload/320/fineuploader.js"),
  FINEUPLOADER_330 ("uictrls/fineupload/330/fineuploader.js"),
  FINEUPLOADER_5 ("uictrls/fineupload/5.11.10/fine-uploader.js"),
  HANDLEBARS_4 ("uictrls/handlebars/4.1.2/handlebars.runtime-v4.1.2.js"),
  JSCOLOR ("uictrls/jscolor/1.4.4ph/jscolor.js"),
  PDFOBJECT1 ("uictrls/pdfobject/1.2/pdfobject.js"),
  PDFOBJECT2 ("uictrls/pdfobject/2.0/pdfobject.js"),
  PRISMJS ("uictrls/prismjs/1.2.1/prism.js"),
  SELECT2 ("uictrls/select2/4.0.13/js/select2.js"),
  SELECT2_LOCALE ("uictrls/select2/4.0.13/js/i18n/{0}.js", false),
  /** https://github.com/twitter/typeahead.js/ */
  TYPEAHEAD_0_9 ("uictrls/typeahead/0.9.3/typeahead.js"),
  TYPEAHEAD_PH ("ph-oton/ph-typeahead.js"),
  /** JS library to use animate.css - https://github.com/matthieua/WOW */
  WOW ("uictrls/js/wow.js");

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
    return ConstantJSPathProvider.createWithConditionalComment (StringHelper.replaceAll (m_aPP.getJSItemPathRegular (), "{0}", sLanguage),
                                                                m_aPP.getConditionalComment ());
  }
}
