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

import com.helger.photon.core.ajax.response.IAjaxResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for a single AJAX function handler.
 *
 * @author Philip Helger
 */
public interface IAjaxExecutor
{
  /**
   * Initialization method that is called before the main execution is called.
   * This can e.g be used to determine the last modification date time.
   *
   * @param aRequestScope
   *        The request scope to be used, to extract parameters. Never
   *        <code>null</code>.
   */
  void initExecution (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * Registers all external resources (CSS or JS files) needed by controls
   * potentially spawned by an AJAX request of this handler. This method is
   * called BEFORE {@link #handleRequest(IRequestWebScopeWithoutResponse)} is
   * invoked!
   */
  void registerExternalResources ();

  /**
   * Called to handle a specific request.
   *
   * @param aRequestScope
   *        the request scope values to be used
   * @return the result object. May never be <code>null</code>
   * @throws Exception
   *         Any exception if an error occurs.
   */
  @Nonnull
  IAjaxResponse handleRequest (@Nonnull IRequestWebScopeWithoutResponse aRequestScope) throws Exception;
}
