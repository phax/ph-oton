/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.url.URLHelper;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.photon.core.url.LinkHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Stream arbitrary resources available in JAR files via HTTP to a client.
 *
 * @author Philip Helger
 */
public class StreamServlet extends AbstractStreamServlet
{
  public static final String SERVLET_DEFAULT_NAME = LinkHelper.DEFAULT_STREAM_SERVLET_NAME;
  public static final String SERVLET_DEFAULT_PATH = "/" + SERVLET_DEFAULT_NAME;

  private static final boolean s_bIsRegistered = ServletStatusManager.isServletRegistered (StreamServlet.class);

  public StreamServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return s_bIsRegistered;
  }

  @Override
  @Nonnull
  @Nonempty
  protected String getApplicationID ()
  {
    return CApplication.APP_ID_PUBLIC;
  }

  @Override
  @Nonnull
  protected IReadableResource getResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                           @Nonnull final String sFilename)
  {
    // URL decode is required because requests contain e.g. "%20"
    final String sFilename1 = URLHelper.urlDecode (sFilename);

    return new ClassPathResource (sFilename1);
  }
}
