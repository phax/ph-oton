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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.state.EContinue;
import com.helger.http.EHTTPMethod;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.photon.core.servlet.AbstractObjectDeliveryServlet;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

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
public class ResourceBundleServlet extends AbstractObjectDeliveryServlet
{
  public static final String SERVLET_DEFAULT_NAME = "resbundle";
  public static final String SERVLET_DEFAULT_PATH = '/' + SERVLET_DEFAULT_NAME;

  private static final Logger s_aLogger = LoggerFactory.getLogger (ResourceBundleServlet.class);
  private static final AtomicBoolean s_bIsEnabled = new AtomicBoolean (isServletRegisteredInServletContext () &&
                                                                       GlobalDebug.isProductionMode ());

  public ResourceBundleServlet ()
  {}

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
  protected String getApplicationID ()
  {
    return CApplication.APP_ID_PUBLIC;
  }

  @Override
  @Nonnull
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return ALLOWED_METHDOS_GET;
  }

  @Nonnull
  private static String _getBundleIDFromFilename (@Nonnull final String sFilename)
  {
    // Cut leading path ("/") and file extension
    return FilenameHelper.getBaseName (sFilename);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected EContinue initRequestState (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final UnifiedResponse aUnifiedResponse)
  {
    if (super.initRequestState (aRequestScope, aUnifiedResponse).isBreak ())
      return EContinue.BREAK;

    // Allow only valid bundle IDs
    final String sBundleID = _getBundleIDFromFilename (aRequestScope.params ()
                                                                    .getAsString (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME));
    if (!PhotonCoreManager.getWebSiteResourceBundleMgr ().containsResourceBundleOfID (sBundleID))
    {
      s_aLogger.info ("Failed to resolve resource bundle with ID '" + sBundleID + "'");
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
      return EContinue.BREAK;
    }
    return EContinue.CONTINUE;
  }

  /**
   * @return The number of days to cache the result.
   */
  @Nonnegative
  protected int getCachingDays ()
  {
    return 30;
  }

  @Override
  protected void onDeliverResource (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                    @Nonnull final UnifiedResponse aUnifiedResponse,
                                    @Nonnull final String sFilename) throws IOException
  {
    final String sBundleID = _getBundleIDFromFilename (sFilename);
    final WebSiteResourceBundleSerialized aBundle = PhotonCoreManager.getWebSiteResourceBundleMgr ()
                                                                     .getResourceBundleOfID (sBundleID);

    final int nCachingDays = getCachingDays ();
    aUnifiedResponse.enableCaching (CGlobal.SECONDS_PER_DAY * nCachingDays)
                    .setMimeType (aBundle.getMimeType ())
                    .setContent (aBundle)
                    .setCharset (StandardCharsets.UTF_8);
  }
}
