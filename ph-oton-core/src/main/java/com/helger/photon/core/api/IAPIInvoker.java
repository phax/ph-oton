/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Base interface for an API invoker. It has a set of {@link IAPIExecutor}
 * instances that it can invoke.
 *
 * @author Philip Helger
 */
public interface IAPIInvoker
{
  /**
   * Register a new API.
   *
   * @param aDescriptor
   *        The API to be registered. May not be <code>null</code>.
   */
  void registerAPI (@Nonnull APIDescriptor aDescriptor);

  /**
   * @return A list of all registered API descriptors. Never <code>null</code>
   *         but maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsCollection <? extends IAPIDescriptor> getAllAPIDescriptors ();

  @Nullable
  InvokableAPIDescriptor getAPIByPath (@Nonnull APIPath aPath);

  /**
   * Invoke the specified API
   * 
   * @param aInvokableDescriptor
   *        The invokable descriptor. Never <code>null</code>.
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The current response object, with caching already disabled. Never
   *        <code>null</code>.
   * @throws Exception
   *         In case something goes wrong
   */
  void invoke (@Nonnull InvokableAPIDescriptor aInvokableDescriptor,
               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
               @Nonnull UnifiedResponse aUnifiedResponse) throws Exception;
}
