/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.html.meta;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.name.IHasName;

/**
 * Defines the properties for a meta tag declaration.
 *
 * @author Philip Helger
 */
public interface IMetaElementDeclaration extends IHasName, Serializable
{
  /**
   * Get the optional meta element scheme.
   *
   * @return the optional meta element scheme - can be <code>null</code>
   */
  @Nullable
  String getScheme ();

  /**
   * @return The meta element type. Never <code>null</code>.
   * @since 8.0.2
   */
  @Nonnull
  EMetaElementType getType ();

  /**
   * @return <code>true</code> if it is a "http-equiv" tag and not a named tag.
   */
  default boolean isHttpEquiv ()
  {
    return getType ().equals (EMetaElementType.PRAGMA_DIRECTIVE);
  }
}
