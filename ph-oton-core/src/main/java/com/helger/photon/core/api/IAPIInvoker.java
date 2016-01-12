/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import java.util.Collection;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
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
  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IAPIExceptionCallback> getExceptionCallbacks ();

  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IAPIBeforeExecutionCallback> getBeforeExecutionCallbacks ();

  @Nonnull
  @ReturnsMutableObject ("design")
  CallbackList <IAPIAfterExecutionCallback> getAfterExecutionCallbacks ();

  /**
   * @return The milliseconds after which an execution is considered long
   *         running.
   */
  @CheckForSigned
  long getLongRunningExecutionLimitTime ();

  /**
   * Set the milliseconds after which an execution is considered long running.
   *
   * @param nLongRunningExecutionLimitTime
   *        The milliseconds to use. Value &le; 0 are considered "no limit"
   */
  void setLongRunningExecutionLimitTime (long nLongRunningExecutionLimitTime);

  @Nonnull
  CallbackList <IAPILongRunningExecutionCallback> getLongRunningExecutionCallbacks ();

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
  Collection <? extends IAPIDescriptor> getAllAPIDescriptors ();

  @Nullable
  InvokableAPIDescriptor getAPIByPath (@Nonnull APIPath aPath);

  void invoke (@Nonnull InvokableAPIDescriptor aInvokableDescriptor,
               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
               @Nonnull UnifiedResponse aUnifiedResponse) throws Exception;
}