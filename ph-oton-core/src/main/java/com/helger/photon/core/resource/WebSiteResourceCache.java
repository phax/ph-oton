/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
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
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static boolean s_bCacheEnabled = !GlobalDebug.isDebugMode ();
  @GuardedBy ("s_aRWLock")
  private static final Map <String, WebSiteResource> s_aMap = new HashMap <> ();

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
    return s_aRWLock.readLocked ( () -> s_bCacheEnabled);
  }

  /**
   * Globally enabled or disable the cache.
   *
   * @param bCacheEnabled
   *        <code>true</code> to enable it, <code>false</code> to disable it.
   */
  public static void setCacheEnabled (final boolean bCacheEnabled)
  {
    s_aRWLock.writeLocked ( () -> s_bCacheEnabled = bCacheEnabled);
    s_aLogger.info ("WebSiteResourceCache is now: " + (bCacheEnabled ? "enabled" : "disabled"));
  }

  @Nonnull
  public static WebSiteResource getOrCreateResource (@Nonnull final EWebSiteResourceType eResourceType,
                                                     @Nonnull @Nonempty final String sPath,
                                                     @Nonnull final Charset aCharset)
  {
    ValueEnforcer.notNull (eResourceType, "ResourceType");
    ValueEnforcer.notEmpty (sPath, "Path");
    ValueEnforcer.notNull (aCharset, "Charset");

    if (!isCacheEnabled ())
    {
      // Always create a new resource to allow for modifications
      final WebSiteResource aResource = new WebSiteResource (eResourceType, sPath, aCharset);
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
    final WebSiteResource ret = s_aRWLock.readLocked ( () -> s_aMap.get (sCacheKey));
    if (ret != null)
      return ret;

    return s_aRWLock.writeLocked ( () -> {
      // Try again in write lock
      WebSiteResource ret2 = s_aMap.get (sCacheKey);
      if (ret2 == null)
      {
        // Init and put in map
        ret2 = new WebSiteResource (eResourceType, sPath, aCharset);
        s_aMap.put (sCacheKey, ret2);
      }
      return ret2;
    });
  }

  @Nonnull
  public static EChange removeFromCache (@Nonnull final EWebSiteResourceType eType,
                                         @Nonnull @Nonempty final String sPath)
  {
    ValueEnforcer.notNull (eType, "Type");
    ValueEnforcer.notEmpty (sPath, "Path");

    final String sCacheKey = eType.getID () + "-" + sPath;

    return s_aRWLock.writeLocked ( () -> EChange.valueOf (s_aMap.remove (sCacheKey) != null));
  }

  /**
   * Remove all items contained in the cache.
   *
   * @return {@link EChange}
   */
  @Nonnull
  public static EChange clearCache ()
  {
    return s_aRWLock.writeLocked ( () -> {
      if (s_aMap.isEmpty ())
        return EChange.UNCHANGED;
      s_aMap.clear ();
      return EChange.CHANGED;
    });
  }
}
