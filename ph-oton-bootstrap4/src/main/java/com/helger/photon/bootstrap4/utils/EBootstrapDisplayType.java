/**
 * Copyright (C) 2015-2018 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap4.utils;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.html.css.ICSSClassProvider;

/**
 * Display type. See https://getbootstrap.com/docs/4.1/utilities/display/
 *
 * @author Philip Helger
 * @See {@link BootstrapDisplayBuilder}
 */
public enum EBootstrapDisplayType implements ICSSClassProvider
{
  NONE ("none"),
  INLINE ("inline"),
  INLINE_BLOCK ("inline-block"),
  BLOCK ("block"),
  TABLE ("table"),
  TABLE_CELL ("table-cell"),
  TABLE_ROW ("table-row"),
  FLEX ("flex"),
  INLINE_FLEX ("inline-flex");

  private final String m_sCSSClassNamePart;

  private EBootstrapDisplayType (@Nonnull @Nonempty final String sCSSClassNamePart)
  {
    m_sCSSClassNamePart = sCSSClassNamePart;
  }

  @Nonnull
  @Nonempty
  public String getCSSClassNamePart ()
  {
    return m_sCSSClassNamePart;
  }

  @Nonnull
  @Nonempty
  public String getCSSClass ()
  {
    return "d-" + m_sCSSClassNamePart;
  }
}
