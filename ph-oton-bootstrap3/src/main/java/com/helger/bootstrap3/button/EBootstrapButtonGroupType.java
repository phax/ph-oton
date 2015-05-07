/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.bootstrap3.button;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.bootstrap3.CBootstrapCSS;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.CollectionHelper;
import com.helger.html.css.ICSSClassProvider;

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

  private final List <ICSSClassProvider> m_aCSSClasses;

  private EBootstrapButtonGroupType (@Nullable final ICSSClassProvider... aCSSClasses)
  {
    m_aCSSClasses = CollectionHelper.newUnmodifiableList (aCSSClasses);
  }

  @Nonnull
  @Nonempty
  public List <ICSSClassProvider> getAllCSSClasses ()
  {
    return m_aCSSClasses;
  }
}
