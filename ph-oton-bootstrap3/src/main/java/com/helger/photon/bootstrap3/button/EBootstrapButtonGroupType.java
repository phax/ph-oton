/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap3.button;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsImmutableObject;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap3.CBootstrapCSS;

/**
 * Button group types
 *
 * @author Philip Helger
 */
public enum EBootstrapButtonGroupType
{
  DEFAULT (CBootstrapCSS.BTN_GROUP),
  VERTICAL (CBootstrapCSS.BTN_GROUP_VERTICAL),
  JUSTIFIED (CBootstrapCSS.BTN_GROUP, CBootstrapCSS.BTN_GROUP_JUSTIFIED);

  private final ICommonsList <ICSSClassProvider> m_aCSSClasses;

  EBootstrapButtonGroupType (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    m_aCSSClasses = new CommonsArrayList <> (aCSSClasses);
  }

  @Nonnull
  @Nonempty
  @ReturnsImmutableObject
  public ICommonsList <ICSSClassProvider> getAllCSSClasses ()
  {
    return m_aCSSClasses.getClone ();
  }
}
