/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.bootstrap.demo.servlet;

import com.helger.commons.http.EHttpMethod;
import com.helger.photon.core.servlet.AbstractPublicApplicationServlet;
import com.helger.photon.core.servlet.ErrorXServletHandler;
import com.helger.xservlet.AbstractXServlet;

public class AppErrorServlet extends AbstractXServlet
{
  public AppErrorServlet ()
  {
    handlerRegistry ().registerHandler (EHttpMethod.GET, new ErrorXServletHandler (AbstractPublicApplicationServlet.SERVLET_DEFAULT_PATH));
    handlerRegistry ().copyHandlerToAll (EHttpMethod.GET);
  }
}
