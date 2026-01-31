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
package com.helger.photon.api;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.collection.commons.ICommonsOrderedSet;
import com.helger.http.EHttpMethod;
import com.helger.photon.api.pathdescriptor.PathDescriptor;

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
  @NonNull
  APIPath getAPIPath ();

  /**
   * @return The HTTP method required to call this API.
   */
  @NonNull
  default EHttpMethod getHttpMethod ()
  {
    return getAPIPath ().getHttpMethod ();
  }

  /**
   * @return The path descriptor required to call this API.
   */
  @NonNull
  PathDescriptor getPathDescriptor ();

  /**
   * @return The non-<code>null</code> factory used to create the main invoker.
   */
  @NonNull
  Supplier <? extends IAPIExecutor> getExecutorFactory ();

  /**
   * @return The names of all required HTTP headers. Never <code>null</code> but
   *         maybe empty.
   */
  @NonNull
  @ReturnsMutableObject
  ICommonsOrderedSet <String> requiredHeaders ();

  /**
   * @return The names of all required request parameters. Never
   *         <code>null</code> but maybe empty.
   */
  @NonNull
  @ReturnsMutableObject
  ICommonsOrderedSet <String> requiredParams ();

  /**
   * @return The names of all allowed MIME types for the data. Never
   *         <code>null</code> but maybe empty. Only MIME types without
   *         parameters (as in ";x=y") should be added here. Also unified casing
   *         should be considered.
   */
  @NonNull
  @ReturnsMutableObject
  ICommonsOrderedSet <String> allowedMimeTypes ();

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

  /**
   * @return The exception mapper for this descriptor. May be <code>null</code>.
   * @since 8.1.3
   */
  @Nullable
  IAPIExceptionMapper getExceptionMapper ();

  /**
   * @return <code>true</code> if an exception mapper is configured,
   *         <code>false</code> if not.
   * @since 8.1.3
   */
  default boolean hasExceptionMapper ()
  {
    return getExceptionMapper () != null;
  }
}
