/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.ajax.executor;

import java.io.Serializable;

import org.jspecify.annotations.NonNull;

import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base interface for a single AJAX function handler.
 *
 * @author Philip Helger
 */
public interface IAjaxExecutor extends Serializable
{
  /**
   * Initialization method that is called before the main execution is called.
   * This can e.g be used to determine the last modification date time.
   *
   * @param aRequestScope
   *        The request scope to be used, to extract parameters. Never
   *        <code>null</code>.
   */
  default void initExecution (@NonNull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // empty default implementation
  }

  /**
   * Registers all external resources (CSS or JS files) needed by controls
   * potentially spawned by an AJAX request of this handler. This method is
   * called BEFORE
   * {@link #handleRequest(IRequestWebScopeWithoutResponse, PhotonUnifiedResponse)}
   * is invoked!
   */
  default void registerExternalResources ()
  {
    // empty default implementation
  }

  /**
   * Called to handle a specific request. The implementation of this method
   * usually performs a server side task and fills the provided response object
   * with e.g. binary data to download or HTML content to be evaluated by the
   * calling HTML page. If this Ajax executor only executes something but
   * delivers no result, at least the HTTP status 204 (No content) should be
   * returned.
   *
   * @param aRequestScope
   *        the request scope values to be used. Never <code>null</code>.
   * @param aAjaxResponse
   *        The response object to be filled. Never <code>null</code>.
   * @throws Exception
   *         Any exception if an error occurs.
   */
  void handleRequest (@NonNull IRequestWebScopeWithoutResponse aRequestScope,
                      @NonNull PhotonUnifiedResponse aAjaxResponse) throws Exception;
}
