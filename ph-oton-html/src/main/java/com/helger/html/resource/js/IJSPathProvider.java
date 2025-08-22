/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.html.resource.js;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.MustImplementEqualsAndHashcode;
import com.helger.html.hc.html.script.EHCScriptLoadingMode;

import jakarta.annotation.Nonnull;

/**
 * Provides a path to an external JS object.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IJSPathProvider extends IJSProvider
{
  /**
   * Get the path to this JavaScript resource. It is always classpath relative.
   *
   * @param bRegular
   *        if <code>true</code> the regular version of item should be
   *        retrieved, otherwise the minified version of the file.
   * @return The path to the external JS item.
   */
  @Nonnull
  @Nonempty
  String getJSItemPath (boolean bRegular);

  /**
   * @return The script loading mode to use. May not be <code>null</code>.
   * @since 9.3.0
   */
  @Nonnull
  default EHCScriptLoadingMode getScriptLoadingMode ()
  {
    return EHCScriptLoadingMode.DEFAULT;
  }
}
