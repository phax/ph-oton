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

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.factory.IFactory;
import com.helger.commons.name.IHasName;
import com.helger.commons.url.ISimpleURL;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * Interface for all AJAX function declarations
 *
 * @author Philip Helger
 */
public interface IAjaxFunctionDeclaration extends IHasName
{
  /**
   * @return The executor factory to be used. May not be <code>null</code>.
   */
  @Nonnull
  IFactory <? extends IAjaxExecutor> getExecutorFactory ();

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
  String getPathWithoutContext ();

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The URI where the AJAX function can be invoked. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  String getInvocationURI (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

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
  String getInvocationURI (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                           @Nullable Map <String, String> aParams);

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The URL where the AJAX function can be invoked. Never
   *         <code>null</code>.
   */
  @Nonnull
  ISimpleURL getInvocationURL (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param aParams
   *        An optional map with URL parameters to be used in the URL. May be
   *        <code>null</code> or empty.
   * @return The URL where the AJAX function can be invoked. Never
   *         <code>null</code>.
   */
  @Nonnull
  ISimpleURL getInvocationURL (@Nonnull IRequestWebScopeWithoutResponse aRequestScope,
                               @Nullable Map <String, String> aParams);
}
