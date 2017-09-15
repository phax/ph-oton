/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.photon.core.PhotonUnifiedResponse;
import com.helger.photon.core.ajax.decl.IAjaxFunctionDeclaration;
import com.helger.photon.core.ajax.executor.IAjaxExecutor;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for an AJAX invoker. It has a set of {@link IAjaxExecutor}
 * instances that it can invoke.
 *
 * @author Philip Helger
 */
public interface IAjaxInvoker extends Serializable
{
  @Nonnull
  @ReturnsMutableCopy
  ICommonsMap <String, IAjaxFunctionDeclaration> getAllRegisteredFunctions ();

  /**
   * Get the registered AJAX function with the specified name
   *
   * @param sFunctionName
   *        The AJAX function name to search. May be <code>null</code>.
   * @return <code>null</code> if no such AJAX function is registered.
   */
  @Nullable
  IAjaxFunctionDeclaration getRegisteredFunction (@Nullable String sFunctionName);

  /**
   * Check whether an AJAX function with the given name is present
   *
   * @param sFunctionName
   *        The name of the AJAX function to check. May be <code>null</code>.
   * @return <code>true</code> if an AJAX function with the given name is
   *         contained, <code>false</code> otherwise.
   */
  boolean isRegisteredFunction (@Nullable String sFunctionName);

  /**
   * Add a handler function that is used as a callback.
   *
   * @param aFunction
   *        The AJAX function declaration to be invoked. May not be
   *        <code>null</code>.
   */
  void registerFunction (@Nonnull IAjaxFunctionDeclaration aFunction);

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
