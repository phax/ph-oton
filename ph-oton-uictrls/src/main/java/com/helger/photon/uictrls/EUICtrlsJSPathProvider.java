/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.base.string.StringReplace;
import com.helger.html.resource.IHTMLResourceProvider;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Contains default JS paths for this project.
 *
 * @author Philip Helger
 */
public enum EUICtrlsJSPathProvider implements IJSPathProvider
{
  AUTONUMERIC ("external/autonumeric/1.9.46/autoNumeric.js"),
  AUTOSIZE3 ("external/autosize/3.0.20/autosize.js"),
  AUTOSIZE3_ALL ("external/autosize/autosize3-all.js"),
  /** BigDecimal support for JS */
  BIG_DECIMAL ("external/js/big.js"),
  CHART_4 ("external/chart/4.4.2/chart.umd.js", IHTMLResourceProvider.DEFAULT_IS_BUNDLABLE, true),
  CLIPBOARD ("external/clipboardjs/2.0.6/clipboard.js"),
  COLORBOX ("external/colorbox/1.6.0/jquery.colorbox.js"),
  FINEUPLOADER_320 ("external/fineupload/320/fineuploader.js"),
  FINEUPLOADER_5 ("external/fineupload/5.16.2/fine-uploader.js"),
  HANDLEBARS_4 ("external/handlebars/4.1.2/handlebars.runtime-v4.1.2.js"),
  JSCOLOR ("external/jscolor/1.4.4ph/jscolor.js"),
  PDFOBJECT2 ("external/pdfobject/2.2.8/pdfobject.js"),
  PRISMJS ("external/prismjs/1.2.1/prism.js"),
  SELECT2 ("external/select2/4.0.13/js/select2.js"),
  SELECT2_LOCALE ("external/select2/4.0.13/js/i18n/{0}.js", false),
  /** https://github.com/twitter/typeahead.js/ */
  TYPEAHEAD_0_9 ("external/typeahead/0.9.3/typeahead.js"),
  TYPEAHEAD_PH ("ph-oton/ph-typeahead.js"),
  /** JS library to use animate.css - https://github.com/matthieua/WOW */
  WOW ("external/js/wow.js");

  private final ConstantJSPathProvider m_aPP;

  EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath, final boolean bCanBeBundled)
  {
    m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().bundlable (bCanBeBundled).build ();
  }

  EUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath,
                          final boolean bCanBeBundled,
                          final boolean bIsAlreadyMinified)
  {
    if (bIsAlreadyMinified)
      m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPath (sPath).bundlable (bCanBeBundled).build ();
    else
      m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().bundlable (bCanBeBundled).build ();
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
    return ConstantJSPathProvider.builder ()
                                 .path (StringReplace.replaceAll (m_aPP.getJSItemPathRegular (), "{0}", sLanguage))
                                 .minifiedPathFromPath ()
                                 .conditionalComment (m_aPP.getConditionalComment ())
                                 .build ();
  }
}
