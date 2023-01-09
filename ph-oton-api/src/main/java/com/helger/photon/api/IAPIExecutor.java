/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Interface for API executors. Implementations of this class contain the
 * business logic that is mapped to a certain URL path.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IAPIExecutor extends Serializable
{
  /**
   * @param aAPIDescriptor
   *        The base API descriptor which is invoked. Never <code>null</code>.
   * @param sPath
   *        The invoked path by the user. Neither <code>null</code> nor empty.
   *        All potential variable elements were already resolved. The mapping
   *        of the source path variables is contained in the path variable
   *        parameter.
   * @param aPathVariables
   *        The resolved variable path parts according to the underlying
   *        {@link IAPIDescriptor}. Never <code>null</code>.
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The unified response to be filled. Never <code>null</code>.
   * @throws Exception
   *         In case of an error
   */
  void invokeAPI (@Nonnull IAPIDescriptor aAPIDescriptor,
                  @Nonnull @Nonempty String sPath,
                  @Nonnull Map <String, String> aPathVariables,
                  @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                  @Nonnull UnifiedResponse aUnifiedResponse) throws Exception;
}
