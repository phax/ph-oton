/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import javax.annotation.Nullable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.photon.ajax.decl.IAjaxFunctionDeclaration;
import com.helger.photon.ajax.executor.IAjaxExecutor;

/**
 * Base interface for an AJAX registry. It has a set of {@link IAjaxExecutor}
 * instances. See {@link IAjaxInvoker} for the interface to implement the
 * execution.<br>
 * Note: This interface was extracted from {@link IAjaxInvoker} in v8.1.4.
 *
 * @author Philip Helger
 */
public interface IAjaxRegistry extends Serializable
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
}
