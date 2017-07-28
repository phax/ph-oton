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
package com.helger.photon.core.api.servlet;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.xservlet.servletstatus.ServletStatusManager;

/**
 * This class handles the API functions for the public application
 *
 * @author Philip Helger
 */
public class PublicApplicationAPIServlet extends AbstractApplicationAPIServlet
{
  public static final String SERVLET_DEFAULT_NAME = "publicapi";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public PublicApplicationAPIServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return ServletStatusManager.getInstance ().isServletRegistered (PublicApplicationAPIServlet.class);
  }

  @Override
  @Nonnull
  @Nonempty
  protected String getApplicationID ()
  {
    return CApplication.APP_ID_PUBLIC;
  }
}
