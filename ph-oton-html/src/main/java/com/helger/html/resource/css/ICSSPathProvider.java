/*
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
package com.helger.html.resource.css;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;

/**
 * Provides a path to an external CSS object.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface ICSSPathProvider extends ICSSProvider
{
  /**
   * @param bRegular
   *        if <code>true</code> the regular version of item should be
   *        retrieved, otherwise the minified version of the file.
   * @return The path to the external CSS item.
   */
  @Nonnull
  @Nonempty
  String getCSSItemPath (boolean bRegular);
}
