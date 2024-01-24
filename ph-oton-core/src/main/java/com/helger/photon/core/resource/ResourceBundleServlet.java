/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.core.resource;

import com.helger.commons.http.EHttpMethod;
import com.helger.photon.app.PhotonAppSettings;
import com.helger.xservlet.AbstractXServlet;
import com.helger.xservlet.servletstatus.ServletStatusManager;

/**
 * Special servlet to stream JS and CSS bundles.<br>
 * Default usage:
 *
 * <pre>
 *  &lt;servlet&gt;
 *     &lt;servlet-name&gt;ResourceBundleServlet&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;com.helger.photon.core.resource.ResourceBundleServlet&lt;/servlet-class&gt;
 *     &lt;init-param&gt;
 *       &lt;param-name&gt;allowedExtensions&lt;/param-name&gt;
 *       &lt;param-value&gt;js,css&lt;/param-value&gt;
 *     &lt;/init-param&gt;
 *   &lt;/servlet&gt;
 *   &lt;servlet-mapping&gt;
 *     &lt;servlet-name&gt;ResourceBundleServlet&lt;/servlet-name&gt;
 *     &lt;url-pattern&gt;/resbundle/*&lt;/url-pattern&gt;
 *   &lt;/servlet-mapping&gt;
 * </pre>
 *
 * @author philip
 */
public final class ResourceBundleServlet extends AbstractXServlet
{
  public static final String SERVLET_DEFAULT_NAME = PhotonAppSettings.getResourceBundleServletName ();
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  public ResourceBundleServlet ()
  {
    handlerRegistry ().registerHandler (EHttpMethod.GET, new ResourceBundleDeliveryHttpHandler ());
  }

  public static boolean isServletRegisteredInServletContext ()
  {
    return ServletStatusManager.isServletRegistered (ResourceBundleServlet.class);
  }
}
