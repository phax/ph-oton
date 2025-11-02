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
package com.helger.photon.api;

import java.io.Serializable;

import org.jspecify.annotations.NonNull;

import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for an API invoker. It has a set of {@link IAPIExecutor}
 * instances that it can invoke.
 *
 * @author Philip Helger
 */
public interface IAPIInvoker extends Serializable
{
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
  void invoke (@NonNull InvokableAPIDescriptor aInvokableDescriptor,
               @NonNull IRequestWebScopeWithoutResponse aRequestScope,
               @NonNull UnifiedResponse aUnifiedResponse) throws Exception;
}
