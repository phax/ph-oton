/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.nav;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Type of Nav
 *
 * @author Philip Helger
 */
public enum EBootstrapNavType
{
  DEFAULT,
  TABS (CBootstrapCSS.NAV_TABS),
  TABS_JUSTIFIED (CBootstrapCSS.NAV_TABS, CBootstrapCSS.NAV_JUSTIFIED),
  PILLS (CBootstrapCSS.NAV_PILLS),
  PILLS_STACKED (CBootstrapCSS.NAV_PILLS, CBootstrapCSS.NAV_STACKED),
  PILLS_JUSTIFIED (CBootstrapCSS.NAV_PILLS, CBootstrapCSS.NAV_JUSTIFIED);

  private final ICommonsList <ICSSClassProvider> m_aCSSClasses;

  private EBootstrapNavType (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    m_aCSSClasses = CollectionHelper.newList (aCSSClasses);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <ICSSClassProvider> getAllCSSClasses ()
  {
    return CollectionHelper.newList (m_aCSSClasses);
  }
}
