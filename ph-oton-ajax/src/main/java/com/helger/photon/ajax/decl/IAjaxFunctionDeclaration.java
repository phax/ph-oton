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
package com.helger.photon.ajax.decl;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.functional.IPredicate;
import com.helger.commons.functional.ISupplier;
import com.helger.commons.name.IHasName;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.photon.app.url.LinkHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Interface for all AJAX function declarations
 *
 * @author Philip Helger
 */
public interface IAjaxFunctionDeclaration extends IHasName, Serializable
{
  /**
   * @return The executor factory to be used. May not be <code>null</code>.
   */
  @Nonnull
  ISupplier <? extends IAjaxExecutor> getExecutorFactory ();

  /**
   * @return An Ajax executor from the executor factory.
   * @see #getExecutorFactory()
   */
  @Nullable
  default IAjaxExecutor getExecutor ()
  {
    return getExecutorFactory ().get ();
  }

  /**
   * @return The optional filter to be invoked before the main AJAX invocation.
   *         May be <code>null</code>.
   */
  @Nullable
  IPredicate <? super IRequestWebScopeWithoutResponse> getExecutionFilter ();

  /**
   * @return The path to the AJAX servlet. Must start with a slash and end with
   *         a slash!
   */
  @Nonnull
  @Nonempty
  String getAjaxServletPath ();

  /**
   * @return The path to execute this AJAX function but without a context path.
   *         Neither <code>null</code> nor empty. This is a shortcut for
   *         <code>getAjaxServletPath () + getName ()</code>
   */
  @Nonnull
  @Nonempty
  default String getPathWithoutContext ()
  {
    return getAjaxServletPath () + getName ();
  }

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The URI where the AJAX function can be invoked. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  default String getInvocationURI (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return LinkHelper.getURIWithContext (aRequestScope, getPathWithoutContext ());
  }

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param aParams
   *        An optional map with URL parameters to be used in the URL. May be
   *        <code>null</code> or empty.
   * @return The URI where the AJAX function can be invoked. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  default String getInvocationURI (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nullable final Map <String, String> aParams)
  {
    if (aParams == null || aParams.isEmpty ())
    {
      // No need to convert to SimpleURL and back
      return getInvocationURI (aRequestScope);
    }

    return getInvocationURL (aRequestScope).addAll (aParams).getAsStringWithEncodedParameters ();
  }

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The URL where the AJAX function can be invoked. Never
   *         <code>null</code>.
   */
  @Nonnull
  default SimpleURL getInvocationURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return LinkHelper.getURLWithContext (aRequestScope, getPathWithoutContext ());
  }

  /**
   * Check if this AJAX function can be executed for the passed request.
   *
   * @param aRequestScope
   *        The request scope to be used for evaluation. Never <code>null</code>
   *        .
   * @return <code>true</code> if this AJAX function can be executed,
   *         <code>false</code> otherwise.
   */
  boolean canExecute (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);
}
