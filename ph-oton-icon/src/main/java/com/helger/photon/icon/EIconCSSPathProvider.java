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
package com.helger.photon.icon;

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
@Deprecated (forRemoval = true, since = "12.3.0")
public enum EIconCSSPathProvider implements ICSSPathProvider
{
  /** http://fontawesome.io */
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME4("external/fontawesome/4.7.0/css/font-awesome.css"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  FONT_AWESOME5("external/fontawesome/5.15.4/css/all.css"),
  /** https://material.io/icons/ */
  @Deprecated (forRemoval = true, since = "12.3.0")
  MATERIAL_ICONS("external/materialdesign/3.0.1/material-icons.css"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  MATERIAL_ICONS_LIST("external/materialdesign/3.0.1/material-icons-list.css"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PH_OTON_MATERIAL_DESIGN("ph-oton/ph-oton-material-icons.css"),
  /** https://icons.getbootstrap.com/ */
  @Deprecated (forRemoval = true, since = "12.3.0")
  BOOTSTRAP_ICONS("external/bootstrap-icons/1.11.3/font/bootstrap-icons.css"),
  @Deprecated (forRemoval = true, since = "12.3.0")
  PH_OTON_BOOTSTRAP_ICONS("ph-oton/ph-oton-bootstrap-icons.css");

  private final ConstantCSSPathProvider m_aPP;

  EIconCSSPathProvider (@NonNull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  @Nonempty
  public String getCSSItemPath (final boolean bRegular)
  {
    return m_aPP.getCSSItemPath (bRegular);
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @Nullable
  public String getConditionalComment ()
  {
    return m_aPP.getConditionalComment ();
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  @NonNull
  @ReturnsMutableCopy
  public ICSSMediaList getMediaList ()
  {
    return m_aPP.getMediaList ();
  }

  @Deprecated (forRemoval = true, since = "12.3.0")
  public boolean isBundlable ()
  {
    return m_aPP.isBundlable ();
  }
}
