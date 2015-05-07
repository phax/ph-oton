/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.GlobalDebug;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.charset.CCharset;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.state.EContinue;
import com.helger.photon.core.app.CApplication;
import com.helger.photon.core.mgr.MetaSystemManager;
import com.helger.photon.core.servlet.AbstractObjectDeliveryServlet;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Special servlet to stream JS and CSS bundles.<br>
 * Default usage:
 *
 * <pre>
 *  &lt;servlet&gt;
 *     &lt;servlet-name&gt;ResourceBundleServlet&lt;/servlet-name&gt;
 *     &lt;servlet-class&gt;com.helger.webbasics.resource.ResourceBundleServlet&lt;/servlet-class&gt;
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
  private static final boolean s_bIsRegistered = ServletStatusManager.isServletRegistered (ResourceBundleServlet.class);
  private static final AtomicBoolean s_bIsActive = new AtomicBoolean (s_bIsRegistered &&
                                                                      GlobalDebug.isProductionMode ());

  public ResourceBundleServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return s_bIsRegistered;
  }

  public static void setActive (final boolean bActive)
  {
    if (bActive && !isServletRegisteredInServletContext ())
      throw new IllegalStateException ("Cannot active the servlet, since it is not registered!");
    s_bIsActive.set (bActive);
  }

  public static boolean isActive ()
  {
    return s_bIsActive.get ();
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
  protected EnumSet <EHTTPMethod> getAllowedHTTPMethods ()
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
    final String sBundleID = _getBundleIDFromFilename (aRequestScope.getAttributeAsString (REQUEST_ATTR_OBJECT_DELIVERY_FILENAME));
    if (!MetaSystemManager.getWebSiteResourceBundleMgr ().containsResourceBundleOfID (sBundleID))
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
    final WebSiteResourceBundleSerialized aBundle = MetaSystemManager.getWebSiteResourceBundleMgr ()
                                                                     .getResourceBundleOfID (sBundleID);

    final int nCachingDays = getCachingDays ();
    aUnifiedResponse.enableCaching (CGlobal.SECONDS_PER_DAY * nCachingDays)
                    .setMimeType (aBundle.getMimeType ())
                    .setContent (aBundle)
                    .setCharset (CCharset.CHARSET_UTF_8_OBJ);
  }
}
