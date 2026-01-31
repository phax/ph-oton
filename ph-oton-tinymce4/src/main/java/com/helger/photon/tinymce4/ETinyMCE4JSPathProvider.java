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
package com.helger.photon.tinymce4;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;

public enum ETinyMCE4JSPathProvider implements IJSPathProvider
{
  TINYMCE_4 ("external/tinymce-dev/tinymce.js", "external/tinymce-min/tinymce.min.js", false);

  private final ConstantJSPathProvider m_aPP;

  ETinyMCE4JSPathProvider (@NonNull @Nonempty final String sRegularPath,
                           @NonNull @Nonempty final String sMinPath,
                           final boolean bCanBeBundled)
  {
    m_aPP = ConstantJSPathProvider.builder ()
                                  .path (sRegularPath)
                                  .minifiedPath (sMinPath)
                                  .bundlable (bCanBeBundled)
                                  .build ();
  }

  @NonNull
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
