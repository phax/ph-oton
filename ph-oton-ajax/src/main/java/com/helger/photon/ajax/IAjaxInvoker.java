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
package com.helger.photon.ajax;

import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for an AJAX invoker. It has a set of {@link IAjaxExecutor}
 * instances that it can invoke.
 *
 * @author Philip Helger
 */
public interface IAjaxInvoker extends Serializable
{
  /**
   * Invoke the specified AJAX function.
   *
   * @param sFunctionName
   *        the alias of the AJAX function to invoke. May not be
   *        <code>null</code>.
   * @param aAjaxExecutor
   *        The executor to be invoked. May not be <code>null</code>.
   * @param aRequestScope
   *        The request scope to be used for the function. Never
   *        <code>null</code>.
   * @param aAjaxResponse
   *        The Ajax response to be filled. Never <code>null</code>.
   * @throws Exception
   *         In case something goes wrong
   */
  void invokeFunction (@Nonnull String sFunctionName,
                       @Nonnull IAjaxExecutor aAjaxExecutor,
                       @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                       @Nonnull PhotonUnifiedResponse aAjaxResponse) throws Exception;
}
