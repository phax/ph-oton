/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.uictrls;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

public enum EBootstrapUICtrlsJSPathProvider implements IJSPathProvider
{
  DATETIMEPICKER ("external/bootstrap/datetimepicker/5.39.0/js/tempusdominus-bootstrap-4.js"),
  DATETIMEPICKER_FONTAWESOME5 ("ph-oton/datetimepicker-fa5.js"),
  TREE_VIEW ("external/bootstrap/treeview/1.2.0/bootstrap-treeview.js");

  private final ConstantJSPathProvider m_aPP;

  EBootstrapUICtrlsJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
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
    return ConstantJSPathProvider.builder ().path (StringHelper.replaceAll (m_aPP.getJSItemPathRegular (),
     "{0}",
     sLanguage)).minifiedPathFromPath ().conditionalComment (m_aPP.getConditionalComment ()).build ();
  }
}
