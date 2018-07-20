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
package com.helger.photon.bootstrap4;

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
  BOOTSTRAP ("bootstrap/4.1.2/css/bootstrap.css"),
  BOOTSTRAP_GRID ("bootstrap/4.1.2/css/bootstrap-grid.css"),
  BOOTSTRAP_REBOOT ("bootstrap/4.1.2/css/bootstrap-reboot.css");

  private final ConstantCSSPathProvider m_aPP;

  private EBootstrapCSSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantCSSPathProvider.create (sPath);
  }

  private EBootstrapCSSPathProvider (@Nonnull @Nonempty final String sPath, @Nullable final String sConditionalComment)
  {
    m_aPP = ConstantCSSPathProvider.createWithConditionalComment (sPath, sConditionalComment);
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
