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
package com.helger.html.resource.js;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.MustImplementEqualsAndHashcode;
import com.helger.commons.annotations.Nonempty;

/**
 * Provides a path to an external JS object.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IJSPathProvider extends Serializable
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
   * @return The conditional comment required for this JS item or
   *         <code>null</code> if it applies to all browsers.
   */
  @Nullable
  String getConditionalComment ();

  /**
   * @return Whether or not this script can be bundled to a big JS profile. For
   *         some files this is not possible (e.g. tinyMCE)
   */
  boolean canBeBundled ();
}
