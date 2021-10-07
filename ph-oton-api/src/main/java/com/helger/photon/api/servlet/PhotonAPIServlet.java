/*
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
package com.helger.photon.api.servlet;

import com.helger.commons.http.EHttpMethod;
import com.helger.xservlet.AbstractXServlet;

/**
 * This class handles the API functions for the public application.<br>
 * The default registration for web.xml looks like this:
 *
 * <pre>
  &lt;servlet&gt;
    &lt;servlet-name&gt;PhotonAPIServlet&lt;/servlet-name&gt;
    &lt;servlet-class&gt;com.helger.photon.api.servlet.PhotonAPIServlet&lt;/servlet-class&gt;
  &lt;/servlet&gt;
  &lt;servlet-mapping&gt;
    &lt;servlet-name&gt;PhotonAPIServlet&lt;/servlet-name&gt;
    &lt;url-pattern&gt;/api/*&lt;/url-pattern&gt;
  &lt;/servlet-mapping&gt;
 * </pre>
 *
 * @author Philip Helger
 */
public final class PhotonAPIServlet extends AbstractXServlet
{
  public static final String SERVLET_DEFAULT_NAME = "api";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public PhotonAPIServlet ()
  {
    handlerRegistry ().registerHandler (EHttpMethod.GET, new APIXServletHandler ());
    handlerRegistry ().copyHandlerToAll (EHttpMethod.GET);
  }
}
