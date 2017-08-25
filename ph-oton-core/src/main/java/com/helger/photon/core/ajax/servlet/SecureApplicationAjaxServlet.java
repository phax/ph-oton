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
package com.helger.photon.core.ajax.servlet;

import com.helger.commons.http.EHttpMethod;
import com.helger.photon.basic.app.CApplicationID;
import com.helger.xservlet.AbstractXServlet;

/**
 * This class handles the AJAX functions for the secure application
 *
 * @author Philip Helger
 */
public final class SecureApplicationAjaxServlet extends AbstractXServlet
{
  public static final String SERVLET_DEFAULT_NAME = "secureajax";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public SecureApplicationAjaxServlet ()
  {
    super ( () -> CApplicationID.APP_ID_SECURE);
    handlerRegistry ().registerHandler (EHttpMethod.GET, new AjaxXServletHandler ());
    handlerRegistry ().copyHandler (EHttpMethod.GET,
                                    EHttpMethod.POST,
                                    EHttpMethod.PUT,
                                    EHttpMethod.DELETE,
                                    EHttpMethod.PATCH);
  }
}
