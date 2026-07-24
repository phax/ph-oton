/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.login;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.cache.impl.ManualCache;

/**
 * Tracks the number of consecutive failed logins per remote IP address. This is used to throttle
 * login attempts where no user could be resolved (e.g. username enumeration or blind brute force
 * attacks), analogous to the per-user consecutive failed login counter. A per-IP counter is
 * incremented on each failed login and removed on a successful login of the same IP. Entries expire
 * after a configurable time-to-live to avoid unbounded memory growth.
 *
 * @author Philip Helger
 * @since 10.2.4
 */
@ThreadSafe
public class LoginThrottlePerIP
{
  /**
   * The default time-to-live for the per-IP failed login counters: 1 hour.
   */
  public static final Duration DEFAULT_TIME_TO_LIVE = Duration.ofHours (1);

  /**
   * The internal cache name.
   */
  public static final String CACHE_NAME = "login-failed-per-ip";

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private Duration m_aTimeToLive = DEFAULT_TIME_TO_LIVE;
  // Lazily (re)built
  @GuardedBy ("m_aRWLock")
  private ManualCache <String, Integer> m_aCache;

  public LoginThrottlePerIP ()
  {}

  /**
   * @return The time-to-live for the per-IP failed login counters. Never <code>null</code>.
   */
  @NonNull
  public final Duration getTimeToLive ()
  {
    return m_aRWLock.readLockedGet (() -> m_aTimeToLive);
  }

  /**
   * Set the time-to-live for the per-IP failed login counters. After this duration of inactivity a
   * per-IP counter is discarded. Changing this value discards all currently held per-IP counters.
   *
   * @param aTimeToLive
   *        The time-to-live to use. May not be <code>null</code>. Should be positive to have any
   *        effect - a zero or negative duration disables time-based expiration and lets the
   *        counters grow unbounded.
   */
  public final void setTimeToLive (@NonNull final Duration aTimeToLive)
  {
    ValueEnforcer.notNull (aTimeToLive, "TimeToLive");

    m_aRWLock.writeLocked (() -> {
      m_aTimeToLive = aTimeToLive;
      // Force a rebuild with the new TTL on next access
      m_aCache = null;
    });
  }

  /**
   * @return The lazily created internal cache. Must be called while holding the write lock. Never
   *         <code>null</code>.
   */
  @NonNull
  private ManualCache <String, Integer> _getOrCreateCache ()
  {
    ManualCache <String, Integer> aCache = m_aCache;
    if (aCache == null)
    {
      aCache = ManualCache.<String, Integer> builder ().name (CACHE_NAME).expireAfterWrite (m_aTimeToLive).build ();
      m_aCache = aCache;
    }
    return aCache;
  }

  /**
   * Register a failed login for the provided IP address. Increments the per-IP consecutive failed
   * login counter.
   *
   * @param sIP
   *        The remote IP address. May neither be <code>null</code> nor empty.
   * @return The new consecutive failed login count for that IP. Always &ge; 1.
   */
  public int onFailedLogin (@NonNull @Nonempty final String sIP)
  {
    ValueEnforcer.notEmpty (sIP, "IP");

    return m_aRWLock.writeLockedInt (() -> {
      final ManualCache <String, Integer> aCache = _getOrCreateCache ();
      final Integer aOld = aCache.getFromCache (sIP);
      final int nNew = (aOld == null ? 0 : aOld.intValue ()) + 1;
      aCache.putInCache (sIP, Integer.valueOf (nNew));
      return nNew;
    });
  }

  /**
   * Register a successful login for the provided IP address. Removes the per-IP consecutive failed
   * login counter.
   *
   * @param sIP
   *        The remote IP address. May be <code>null</code>, in which case nothing happens.
   * @return {@link EChange#CHANGED} if a counter was removed, {@link EChange#UNCHANGED} otherwise.
   */
  @NonNull
  public EChange onSuccessfulLogin (@Nullable final String sIP)
  {
    if (sIP == null)
      return EChange.UNCHANGED;

    return m_aRWLock.writeLockedGet (() -> {
      final ManualCache <String, Integer> aCache = m_aCache;
      return aCache == null ? EChange.UNCHANGED : aCache.removeFromCache (sIP);
    });
  }

  /**
   * @param sIP
   *        The remote IP address. May be <code>null</code>.
   * @return The current consecutive failed login count for the provided IP, or <code>0</code> if
   *         none is present or the entry already expired.
   */
  public int getFailedLoginCount (@Nullable final String sIP)
  {
    if (sIP == null)
      return 0;

    return m_aRWLock.readLockedInt (() -> {
      final ManualCache <String, Integer> aCache = m_aCache;
      if (aCache != null)
      {
        final Integer aCount = aCache.getFromCache (sIP);
        if (aCount != null)
          return aCount.intValue ();
      }
      return 0;
    });
  }

  /**
   * Remove all currently held per-IP counters.
   */
  public void clear ()
  {
    m_aRWLock.writeLocked (() -> {
      if (m_aCache != null)
        m_aCache.clearCache ();
    });
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("TimeToLive", m_aTimeToLive).getToString ();
  }
}
