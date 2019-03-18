/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
import com.helger.html.hc.config.HCSettings;

/**
 * Global photon-app settings
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class PhotonAppSettings
{
  public static final String DEFAULT_RESOURCE_BUNDLE_SERVLET_NAME = "resbundle";

  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonAppSettings.class);

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

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

  /**
   * @return <code>true</code> to merge CSS resources if possible or
   *         <code>false</code> to include each CSS separately.
   */
  public static boolean isMergeCSSResources ()
  {
    return s_aRWLock.readLocked ( () -> s_bMergeCSSResources);
  }

  public static void setMergeCSSResources (final boolean bMergeCSSResources)
  {
    s_aRWLock.writeLocked ( () -> s_bMergeCSSResources = bMergeCSSResources);
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
    return s_aRWLock.readLocked ( () -> s_bMergeJSResources);
  }

  public static void setMergeJSResources (final boolean bMergeJSResources)
  {
    s_aRWLock.writeLocked ( () -> s_bMergeJSResources = bMergeJSResources);
    if (!HCSettings.isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info (bMergeJSResources ? "Merging JS resources" : "Using separate JS resources");
  }

  @Nonnull
  @Nonempty
  public static String getResourceBundleServletName ()
  {
    return s_aRWLock.readLocked ( () -> s_sResourceBundleServletName);
  }

  public static void setResourceBundleServletName (@Nonnull @Nonempty final String sResourceBundleServletName)
  {
    ValueEnforcer.notEmpty (sResourceBundleServletName, "ResourceBundleServletName");
    s_aRWLock.writeLocked ( () -> s_sResourceBundleServletName = sResourceBundleServletName);
    if (!HCSettings.isSilentMode ())
      if (LOGGER.isInfoEnabled ())
        LOGGER.info ("Using ResourceBundleServlet name '" + sResourceBundleServletName + "'");
  }
}
