/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.debug.GlobalDebug;
import com.helger.photon.app.PhotonUnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A dummy AJAX handler that can be invoked to keep the session alive.
 *
 * @author Philip Helger
 */
public final class AjaxExecutorKeepAlive implements IAjaxExecutor
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AjaxExecutorKeepAlive.class);

  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final PhotonUnifiedResponse aAjaxResponse) throws Exception
  {
    if (GlobalDebug.isDebugMode ())
      LOGGER.info ("AJAX Keep alive!");

    // This is all we need. If a session is present, it gets accessed
    aRequestScope.getSession (false);

    // Always success
    aAjaxResponse.createNoContent ();
  }
}
