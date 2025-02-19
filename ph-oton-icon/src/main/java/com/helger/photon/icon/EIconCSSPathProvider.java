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
package com.helger.photon.icon;

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
public enum EIconCSSPathProvider implements ICSSPathProvider
{
  /** http://fontawesome.io */
  FONT_AWESOME4 ("external/fontawesome/4.7.0/css/font-awesome.css"),
  FONT_AWESOME5 ("external/fontawesome/5.15.4/css/all.css"),
  /** https://material.io/icons/ */
  MATERIAL_ICONS ("external/materialdesign/3.0.1/material-icons.css"),
  MATERIAL_ICONS_LIST ("external/materialdesign/3.0.1/material-icons-list.css"),
  /** https://icons.getbootstrap.com/ */
  BOOTSTRAP_ICONS ("external/bootstrap-icons/1.8.1/bootstrap-icons.css"),
  PH_OTON_MATERIAL_DESIGN ("ph-oton/ph-oton-material-icons.css");

  private final ConstantCSSPathProvider m_aPP;

  EIconCSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
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
