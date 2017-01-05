/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.api;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsOrderedSet;
import com.helger.commons.factory.IFactory;
import com.helger.http.EHTTPMethod;
import com.helger.photon.core.api.pathdescriptor.PathDescriptor;

/**
 * The read-only part of {@link APIDescriptor}.
 *
 * @author Philip Helger
 */
public interface IAPIDescriptor
{
  /**
   * @return The API path used. Never <code>null</code>.
   */
  @Nonnull
  APIPath getAPIPath ();

  /**
   * @return The HTTP method required to call this API.
   */
  @Nonnull
  default EHTTPMethod getHTTPMethod ()
  {
    return getAPIPath ().getHTTPMethod ();
  }

  /**
   * @return The path descriptor required to call this API.
   */
  @Nonnull
  PathDescriptor getPathDescriptor ();

  /**
   * @return The non-<code>null</code> factory used to create the main invoker.
   */
  @Nonnull
  IFactory <? extends IAPIExecutor> getExecutorFactory ();

  /**
   * @return <code>true</code> if this API has required HTTP headers.
   */
  boolean hasRequiredHeaders ();

  /**
   * @return The names of all required HTTP headers. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedSet <String> getAllRequiredHeaders ();

  /**
   * @return <code>true</code> if this API has required request parameters.
   */
  boolean hasRequiredParams ();

  /**
   * @return The names of all required request parameters. Never
   *         <code>null</code> but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsOrderedSet <String> getAllRequiredParams ();

  /**
   * @return The current execution filter. May be <code>null</code>.
   */
  @Nullable
  IAPIExecutionFilter getExecutionFilter ();

  /**
   * @return <code>true</code> if an execution filter is present,
   *         <code>false</code> otherwise.
   */
  default boolean hasExecutionFilter ()
  {
    return getExecutionFilter () != null;
  }
}
