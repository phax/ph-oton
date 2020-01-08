/**
 * Copyright (C) 2018-2020 Philip Helger (www.helger.com)
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

import com.helger.html.css.ICSSClassProvider;
import com.helger.photon.bootstrap4.CBootstrapCSS;

/**
 * Position. See https://getbootstrap.com/docs/4.1/utilities/position/ <br>
 * IE11 and IE10 will render position: sticky as position: relative. As such, we
 * wrap the styles in a @supports query, limiting the stickiness to only
 * browsers that can render it properly.
 *
 * @author Philip Helger
 */
public enum EBootstrapPositionType implements ICSSClassProvider
{
  STATIC (CBootstrapCSS.POSITION_STATIC),
  RELATIVE (CBootstrapCSS.POSITION_RELATIVE),
  ABSOLUTE (CBootstrapCSS.POSITION_ABSOLUTE),
  FIXED (CBootstrapCSS.POSITION_FIXED),
  STICKY (CBootstrapCSS.POSITION_STICKY),
  FIXED_TOP (CBootstrapCSS.FIXED_TOP),
  FIXED_BOTTOM (CBootstrapCSS.FIXED_BOTTOM),
  STICKY_TOP (CBootstrapCSS.STICKY_TOP);

  private final ICSSClassProvider m_aCSSClass;

  private EBootstrapPositionType (@Nonnull final ICSSClassProvider aCSSClass)
  {
    m_aCSSClass = aCSSClass;
  }

  @Nonnull
  public String getCSSClass ()
  {
    return m_aCSSClass.getCSSClass ();
  }
}
