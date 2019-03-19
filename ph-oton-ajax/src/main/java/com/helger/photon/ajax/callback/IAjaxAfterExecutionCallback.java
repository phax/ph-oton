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
package com.helger.photon.ajax.callback;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.ajax.IAjaxInvoker;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Callback interface to be used with the {@link IAjaxInvoker} to get notified
 * after an {@link IAjaxExecutor} was invoked.
 *
 * @author Philip Helger
 */
public interface IAjaxAfterExecutionCallback extends ICallback
{
  /**
   * Callback method
   *
   * @param aInvoker
   *        The {@link IAjaxInvoker} object that invoked the AJAX function
   * @param sFunctionName
   *        The name of the invoked function
   * @param aRequestScope
   *        The request scope of the current invocation
   * @param aExecutor
   *        The executor that will be used
   * @param aAjaxResponse
   *        The created Ajax response
   */
  void onAfterExecution (@Nonnull IAjaxInvoker aInvoker,
                         @Nonnull String sFunctionName,
                         @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                         @Nonnull IAjaxExecutor aExecutor,
                         @Nonnull PhotonUnifiedResponse aAjaxResponse);
}
