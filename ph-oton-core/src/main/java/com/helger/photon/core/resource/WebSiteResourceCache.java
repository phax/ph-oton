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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.GlobalDebug;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.state.EChange;

/**
 * A global cache for {@link WebSiteResource} objects, to avoid the hash
 * calculation over and over again.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class WebSiteResourceCache
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (WebSiteResourceCache.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();
  private static boolean s_bCacheEnabled = !GlobalDebug.isDebugMode ();
  private static final Map <String, WebSiteResource> s_aMap = new HashMap <String, WebSiteResource> ();

  @PresentForCodeCoverage
  private static final WebSiteResourceCache s_aInstance = new WebSiteResourceCache ();

  private WebSiteResourceCache ()
  {}

  /**
   * @return <code>true</code> if the cache is globally enabled,
   *         <code>false</code> if the cache is disabled.
   */
  public static boolean isCacheEnabled ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bCacheEnabled;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Globally enabled or disable the cache.
   *
   * @param bCacheEnabled
   *        <code>true</code> to enable it, <code>false</code> to disable it.
   */
  public static void setCacheEnabled (final boolean bCacheEnabled)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_bCacheEnabled = bCacheEnabled;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
    s_aLogger.info ("WebSiteResourceCache is now: " + (bCacheEnabled ? "enabled" : "disabled"));
  }

  @Nonnull
  public static WebSiteResource getOrCreateResource (@Nonnull final EWebSiteResourceType eResourceType,
                                                     @Nonnull @Nonempty final String sPath)
  {
    ValueEnforcer.notNull (eResourceType, "ResourceType");
    ValueEnforcer.notEmpty (sPath, "Path");

    if (!isCacheEnabled ())
    {
      // Always create a new resource to allow for modifications
      final WebSiteResource aResource = new WebSiteResource (eResourceType, sPath);
      if (!aResource.isExisting ())
        throw new IllegalArgumentException ("WebSiteResource '" +
                                            sPath +
                                            "' of type " +
                                            eResourceType +
                                            " does not exist");
      return aResource;
    }

    final String sCacheKey = eResourceType.getID () + "-" + sPath;

    // Entry already existing?
    WebSiteResource ret;
    s_aRWLock.readLock ().lock ();
    try
    {
      ret = s_aMap.get (sCacheKey);
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }

    if (ret == null)
    {
      s_aRWLock.writeLock ().lock ();
      try
      {
        // Try again in write lock
        ret = s_aMap.get (sCacheKey);
        if (ret == null)
        {
          ret = new WebSiteResource (eResourceType, sPath);
          s_aMap.put (sCacheKey, ret);
        }
      }
      finally
      {
        s_aRWLock.writeLock ().unlock ();
      }
    }
    return ret;
  }

  @Nonnull
  public static EChange removeFromCache (@Nonnull final EWebSiteResourceType eType,
                                         @Nonnull @Nonempty final String sPath)
  {
    ValueEnforcer.notNull (eType, "Type");
    ValueEnforcer.notEmpty (sPath, "Path");

    final String sCacheKey = eType.getID () + "-" + sPath;

    s_aRWLock.writeLock ().lock ();
    try
    {
      return EChange.valueOf (s_aMap.remove (sCacheKey) != null);
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Remove all items contained in the cache.
   *
   * @return {@link EChange}
   */
  @Nonnull
  public static EChange clearCache ()
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      if (s_aMap.isEmpty ())
        return EChange.UNCHANGED;
      s_aMap.clear ();
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
    return EChange.CHANGED;
  }
}
