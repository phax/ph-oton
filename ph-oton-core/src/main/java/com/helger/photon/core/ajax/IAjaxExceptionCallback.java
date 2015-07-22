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
package com.helger.photon.core.ajax;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.core.ajax.servlet.AbstractAjaxServlet;
import com.helger.web.scope.domain.IRequestWebScopeWithoutResponse;

/**
 * Callback interface to handle thrown exception objects from the
 * {@link AbstractAjaxServlet}.
 *
 * @author Philip Helger
 */
public interface IAjaxExceptionCallback extends ICallback
{
  /**
   * Called when an exception of the specified type occurred
   *
   * @param aAjaxInvoker
   *        Source AJAX invoker. Never <code>null</code>.
   * @param sFunctionName
   *        The AJAX function that should have been involved. Never
   *        <code>null</code>.
   * @param aAjaxExecutor
   *        The running AJAX executor. Never <code>null</code>.
   * @param aRequestScope
   *        The request scope. Never <code>null</code>.
   * @param t
   *        The exception. Never <code>null</code>.
   */
  void onAjaxExecutionException (@Nonnull IAjaxInvoker aAjaxInvoker,
                                 @Nonnull String sFunctionName,
                                 @Nonnull IAjaxExecutor aAjaxExecutor,
                                 @Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                                 @Nonnull Throwable t);
}
