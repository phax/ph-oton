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
package com.helger.photon.uicore;

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
public enum EUICoreCSSPathProvider implements ICSSPathProvider
{
  COOKIE_CONSENT_ANIMATION ("external/cookieconsent/3.1.1/styles/animation.css"),
  COOKIE_CONSENT_BASE ("external/cookieconsent/3.1.1/styles/base.css"),
  COOKIE_CONSENT_LAYOUT ("external/cookieconsent/3.1.1/styles/layout.css"),
  COOKIE_CONSENT_MEDIA ("external/cookieconsent/3.1.1/styles/media.css"),
  COOKIE_CONSENT_THEMES_CLASSIC ("external/cookieconsent/3.1.1/styles/themes/classic.css"),
  COOKIE_CONSENT_THEMES_EDGELESS ("external/cookieconsent/3.1.1/styles/themes/edgeless.css"),
  /** Custom development */
  UICORE ("ph-oton/uicore.css");

  private final ConstantCSSPathProvider m_aPP;

  EUICoreCSSPathProvider (@NonNull @Nonempty final String sPath)
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
