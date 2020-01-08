/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.state.EHandled;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * API exception mapper to convert exceptions into reasonable HTTP responses. It
 * is called from inside {@link APIInvoker} in case an exception is thrown. See
 * {@link IAPIDescriptor} for the assignment.
 *
 * @author Philip Helger
 * @since 8.1.3
 */
public interface IAPIExceptionMapper extends Serializable
{
  /**
   * @param aInvokableDescriptor
   *        The current invokable descriptor. Never <code>null</code>.
   * @param aRequestScope
   *        The current request scope. Never <code>null</code>.
   * @param aUnifiedResponse
   *        The current response. Never <code>null</code>.
   * @param aThrowable
   *        The thrown Exception. Never <code>null</code>.
   * @return {@link EHandled#HANDLED} to indicate that the exception was handled
   *         and should NOT be re-thrown.
   */
  @Nonnull
  EHandled applyExceptionOnResponse (@Nonnull InvokableAPIDescriptor aInvokableDescriptor,
                                     @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                     @Nonnull UnifiedResponse aUnifiedResponse,
                                     @Nonnull Throwable aThrowable);
}
