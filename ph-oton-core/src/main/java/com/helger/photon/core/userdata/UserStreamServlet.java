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
package com.helger.photon.core.userdata;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.app.CApplicationID;
import com.helger.xservlet.servletstatus.ServletStatusManager;

/**
 * Stream user provided resources available on disk via HTTP to a client.
 *
 * @author Philip Helger
 */
public class UserStreamServlet extends AbstractUserStreamServlet
{
  public static final String SERVLET_DEFAULT_NAME = "user";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public UserStreamServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return ServletStatusManager.getInstance ().isServletRegistered (UserStreamServlet.class);
  }

  @Override
  @Nonnull
  @Nonempty
  protected String getApplicationID ()
  {
    return CApplicationID.APP_ID_PUBLIC;
  }
}
