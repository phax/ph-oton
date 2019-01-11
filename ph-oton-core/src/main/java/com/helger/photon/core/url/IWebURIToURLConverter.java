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
package com.helger.photon.core.url;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A generic converted from String URI to {@link ISimpleURL}.
 *
 * @author Philip Helger
 */
public interface IWebURIToURLConverter
{
  /**
   * Convert the passed URI to a resource.
   *
   * @param sURI
   *        The URI to be converted. May neither be <code>null</code> nor empty.
   * @return The created resource and never null.
   */
  @Nonnull
  IReadableResource getAsResource (@Nonnull @Nonempty String sURI);

  /**
   * Convert the passed URI to a URL.
   *
   * @param sURI
   *        The URI to be converted. May neither be <code>null</code> nor empty.
   * @return The created URL.
   */
  @Nonnull
  SimpleURL getAsURL (@Nonnull @Nonempty String sURI);

  /**
   * Convert the passed URI to a URL.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param sURI
   *        The URI to be converted.
   * @return The created URL.
   */
  @Nonnull
  SimpleURL getAsURL (@Nonnull IRequestWebScopeWithoutResponse aRequestScope, @Nonnull @Nonempty String sURI);
}
