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
package com.helger.photon.core.resource;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.http.EHttpMethod;
import com.helger.photon.basic.app.CApplicationID;
import com.helger.xservlet.servletstatus.ServletStatusManager;
import com.helger.xservlet.simple.AbstractSimpleHttpServlet;

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
public class ResourceBundleServlet extends AbstractSimpleHttpServlet
{
  public static final String SERVLET_DEFAULT_NAME = "resbundle";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  static final Logger s_aLogger = LoggerFactory.getLogger (ResourceBundleServlet.class);
  private static final AtomicBoolean s_bIsEnabled = new AtomicBoolean (isServletRegisteredInServletContext () &&
                                                                       GlobalDebug.isProductionMode ());

  public ResourceBundleServlet ()
  {
    registerSyncHandler (EHttpMethod.GET, new ResourceBundleDeliveryHttpHandler ());
  }

  public static boolean isServletRegisteredInServletContext ()
  {
    return ServletStatusManager.getInstance ().isServletRegistered (ResourceBundleServlet.class);
  }

  public static void setEnabled (final boolean bEnable)
  {
    if (bEnable && !isServletRegisteredInServletContext ())
      throw new IllegalStateException ("Cannot enable the servlet, since it is not registered!");
    s_bIsEnabled.set (bEnable);
    s_aLogger.info ("ResourceBundleServlet is now: " + (bEnable ? "enabled" : "disabled"));
  }

  public static boolean isEnabled ()
  {
    return s_bIsEnabled.get ();
  }

  @Override
  @Nonnull
  @Nonempty
  protected String getInitApplicationID ()
  {
    return CApplicationID.APP_ID_PUBLIC;
  }
}
