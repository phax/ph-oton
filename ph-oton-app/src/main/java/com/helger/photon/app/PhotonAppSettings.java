/**
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
package com.helger.photon.app;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.hc.config.HCSettings;
import com.helger.html.resource.css.ICSSPathProvider;
import com.helger.html.resource.js.IJSPathProvider;
import com.helger.photon.app.url.IWebURIToURLConverter;
import com.helger.photon.app.url.StreamOrLocalURIToURLConverter;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Global photon-app settings. It contains the following settings:
 * <ul>
 * <li>The converter from URI to URL</li>
 * <li>To merge all CSS together or not to merge them</li>
 * <li>To merge all JS together or not to merge them</li>
 * <li>The name of the resource bundle servlet</li>
 * </ul>
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonAppSettings
{
  public static final String DEFAULT_RESOURCE_BUNDLE_SERVLET_NAME = "resbundle";

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonAppSettings.class);

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static IWebURIToURLConverter s_aURIToURLConverter = new StreamOrLocalURIToURLConverter ();

  /** Merge CSS resources into one (if possible) */
  @GuardedBy ("s_aRWLock")
  private static boolean s_bMergeCSSResources = GlobalDebug.isProductionMode ();

  /** Merge JS resources into one (if possible) */
  @GuardedBy ("s_aRWLock")
  private static boolean s_bMergeJSResources = GlobalDebug.isProductionMode ();

  @GuardedBy ("s_aRWLock")
  private static String s_sResourceBundleServletName = DEFAULT_RESOURCE_BUNDLE_SERVLET_NAME;

  private PhotonAppSettings ()
  {}

  @Nonnull
  public static IWebURIToURLConverter getURIToURLConverter ()
  {
    return s_aRWLock.readLockedGet ( () -> s_aURIToURLConverter);
  }

  public static void setURIToURLConverter (@Nonnull final IWebURIToURLConverter aURIToURLConverter)
  {
    ValueEnforcer.notNull (aURIToURLConverter, "URIToURLConverter");

    s_aRWLock.writeLockedGet ( () -> s_aURIToURLConverter = aURIToURLConverter);
  }

  @Nonnull
  public static ISimpleURL getCSSPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final ICSSPathProvider aCSS,
                                       final boolean bRegular)
  {
    final String sPath = aCSS.getCSSItemPath (bRegular);
    return getURIToURLConverter ().getAsURL (aRequestScope, sPath);
  }

  @Nonnull
  public static ISimpleURL getJSPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final IJSPathProvider aJS,
                                      final boolean bRegular)
  {
    final String sPath = aJS.getJSItemPath (bRegular);
    return getURIToURLConverter ().getAsURL (aRequestScope, sPath);
  }

  /**
   * @return <code>true</code> to merge CSS resources if possible or
   *         <code>false</code> to include each CSS separately.
   */
  public static boolean isMergeCSSResources ()
  {
    return s_aRWLock.readLockedBoolean ( () -> s_bMergeCSSResources);
  }

  public static void setMergeCSSResources (final boolean bMergeCSSResources)
  {
    s_aRWLock.writeLockedBoolean ( () -> s_bMergeCSSResources = bMergeCSSResources);
    if (!HCSettings.isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info (bMergeCSSResources ? "Merging CSS resources" : "Using separate CSS resources");
  }

  /**
   * @return <code>true</code> to merge JS resources if possible or
   *         <code>false</code> to include each JS separately.
   */
  public static boolean isMergeJSResources ()
  {
    return s_aRWLock.readLockedBoolean ( () -> s_bMergeJSResources);
  }

  public static void setMergeJSResources (final boolean bMergeJSResources)
  {
    s_aRWLock.writeLockedBoolean ( () -> s_bMergeJSResources = bMergeJSResources);
    if (!HCSettings.isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info (bMergeJSResources ? "Merging JS resources" : "Using separate JS resources");
  }

  /**
   * @return The name of the resource bundle servlet without a leading nor a
   *         trailing slash. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public static String getResourceBundleServletName ()
  {
    return s_aRWLock.readLockedGet ( () -> s_sResourceBundleServletName);
  }

  public static void setResourceBundleServletName (@Nonnull @Nonempty final String sResourceBundleServletName)
  {
    ValueEnforcer.notEmpty (sResourceBundleServletName, "ResourceBundleServletName");
    s_aRWLock.writeLockedGet ( () -> s_sResourceBundleServletName = sResourceBundleServletName);
    if (!HCSettings.isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Using ResourceBundleServlet name '" + sResourceBundleServletName + "'");
  }
}
