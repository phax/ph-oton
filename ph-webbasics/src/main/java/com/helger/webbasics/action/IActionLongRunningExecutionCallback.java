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
package com.helger.webbasics.action;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * Callback interface to be used with the {@link IActionInvoker} to get notified
 * on long running executions.
 *
 * @author Philip Helger
 */
public interface IActionLongRunningExecutionCallback extends ICallback
{
  /**
   * Callback method
   *
   * @param aInvoker
   *        The {@link IActionInvoker} object that invoked the AJAX function
   * @param sActionName
   *        The name of the invoked action
   * @param aRequestScope
   *        The request scope of the current invocation
   * @param aExecutor
   *        The executor that was used
   * @param nExecutionMillis
   *        The execution time in milliseconds
   */
  void onLongRunningExecution (@Nonnull IActionInvoker aInvoker,
                               @Nonnull String sActionName,
                               @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                               @Nonnull IActionExecutor aExecutor,
                               @Nonnegative long nExecutionMillis);
}
