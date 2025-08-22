/*
 * Copyright (C) 2018-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Contains default JS paths within this library.
 *
 * @author Philip Helger
 */
public enum EBootstrapJSPathProvider implements IJSPathProvider
{
  /** Default complete Bootstrap JS */
  BOOTSTRAP ("external/bootstrap/4.6.2/js/bootstrap.js"),
  BOOTSTRAP_BUNDLE ("external/bootstrap/4.6.2/js/bootstrap.bundle.js"),
  /** Some Bootstrap JS extensions */
  BOOTSTRAP_PH ("ph-oton/bootstrap4-ph.js");

  private final ConstantJSPathProvider m_aPP;

  EBootstrapJSPathProvider (@Nonnull @Nonempty final String sPath)
  {
    m_aPP = ConstantJSPathProvider.builder ().path (sPath).minifiedPathFromPath ().build ();
  }

  @Nonnull
  @Nonempty
  public String getJSItemPath (final boolean bRegular)
  {
    return m_aPP.getJSItemPath (bRegular);
  }

  @Nullable
  public String getConditionalComment ()
  {
    return m_aPP.getConditionalComment ();
  }

  public boolean isBundlable ()
  {
    return m_aPP.isBundlable ();
  }
}
