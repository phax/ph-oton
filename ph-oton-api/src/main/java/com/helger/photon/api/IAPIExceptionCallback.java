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

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Callback interface to handle thrown but unmapped exception objects from the
 * API servlet.
 *
 * @author Philip Helger
 * @see IAPIExceptionMapper for mapping exceptions to the response
 */
@FunctionalInterface
public interface IAPIExceptionCallback extends ICallback
{
  /**
   * Called when an unmapped exception occurred while executing the server side
   * code.
   *
   * @param aInvoker
   *        The {@link IAPIInvoker} object that invoked the API function
   * @param aInvokableDescriptor
   *        API invoker. Never <code>null</code>.
   * @param aRequestScope
   *        The request scope. Never <code>null</code>.
   * @param t
   *        The exception. Never <code>null</code>.
   */
  void onAPIExecutionException (@Nonnull IAPIInvoker aInvoker,
                                @Nonnull InvokableAPIDescriptor aInvokableDescriptor,
                                @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull Throwable t);
}
