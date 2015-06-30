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
package com.helger.photon.bootstrap3;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.css.media.ICSSMediaList;
import com.helger.html.resource.css.ConstantCSSPathProvider;
import com.helger.html.resource.css.ICSSPathProvider;

/**
 * Contains default CSS paths within this library.
 *
 * @author Philip Helger
 */
public enum EBootstrapCSSPathProvider implements ICSSPathProvider
{
  /** Main Bootstrap CSS */
  BOOTSTRAP_335 ("bootstrap/3.3.5/css/bootstrap.css"),
  /** Bootstrap theme CSS */
  BOOTSTRAP_THEME_335 ("bootstrap/3.3.5/css/bootstrap-theme.css"),
  /** Special Bootstrap CSS for IE < 10 */
  BOOTSTRAP_IE9 ("bootstrap/bootstrap3-ie9.css", "if lt IE 10"),
  /** A some of default Bootstrap CSS adoptions etc. */
  BOOTSTRAP_PH ("bootstrap/bootstrap3-ph.css");

  private final ConstantCSSPathProvider m_aPP;

  private EBootstrapCSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    this (sPath, null);
  }

  private EBootstrapCSSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = new ConstantCSSPathProvider (sPath, sConditionalComment, (ICSSMediaList) null);
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
}
