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
package com.helger.photon.core.servlet;

import com.helger.http.EHttpMethod;
import com.helger.xservlet.AbstractXServlet;

/**
 * A simple default implementation of a CSP (Content Security Policy) reporting
 * servlet.
 *
 * @author Philip Helger
 */
public class CSPReportingServlet extends AbstractXServlet
{
  public static final String SERVLET_DEFAULT_NAME = "cspreporting";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public CSPReportingServlet ()
  {
    super ( () -> "none");
    handlerRegistry ().registerHandler (EHttpMethod.POST, new CSPReportingXServletHandler ());
  }
}