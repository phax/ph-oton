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
package com.helger.photon.core.app.redirect;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.IHCNode;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

@ThreadSafe
public final class ForcedRedirectManager extends AbstractSessionWebSingleton
{
  public static final String REQUEST_PARAMETER_PRG_ACTIVE = "phprga";

  private static final Logger s_aLogger = LoggerFactory.getLogger (ForcedRedirectManager.class);

  @GuardedBy ("m_aRWLock")
  private final Map <String, IHCNode> m_aMap = new HashMap <String, IHCNode> ();

  @Deprecated
  @UsedViaReflection
  public ForcedRedirectManager ()
  {}

  /**
   * @return The instance to use. Never <code>null</code>.
   */
  @Nonnull
  public static ForcedRedirectManager getInstance ()
  {
    return getSessionSingleton (ForcedRedirectManager.class);
  }

  /**
   * @return The instance to use if already instantiated. Maybe
   *         <code>null</code>.
   */
  @Nullable
  public static ForcedRedirectManager getInstanceIfInstantiated ()
  {
    return getSessionSingletonIfInstantiated (ForcedRedirectManager.class);
  }

  public void createForcedRedirect (@Nonnull final ForcedRedirectException ex)
  {
    ValueEnforcer.notNull (ex, "Exception");
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aMap.put (ex.getSourceMenuItemID (), ex.getContent ());
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Creating forced redirect from '" +
                       ex.getSourceMenuItemID () +
                       "' to URL " +
                       ex.getRedirectTargetURL ());
  }

  @Nullable
  public IHCNode getContent (@Nullable final String sMenuItemID)
  {
    if (StringHelper.hasNoText (sMenuItemID))
      return null;

    // Get only
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sMenuItemID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean hasContent (@Nullable final String sMenuItemID)
  {
    if (StringHelper.hasNoText (sMenuItemID))
      return false;

    // Get only
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sMenuItemID) != null;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public static IHCNode getLastForcedRedirectContent (@Nullable final String sMenuItemID)
  {
    if (StringHelper.hasNoText (sMenuItemID))
      return null;

    final ForcedRedirectManager aMgr = getInstanceIfInstantiated ();
    if (aMgr == null)
      return null;

    // Get only
    return aMgr.getContent (sMenuItemID);
  }

  @Nullable
  public IHCNode getAndRemoveContent (@Nullable final String sMenuItemID)
  {
    if (StringHelper.hasNoText (sMenuItemID))
      return null;

    IHCNode ret;

    // Get only
    m_aRWLock.writeLock ().lock ();
    try
    {
      // Get again in write lock
      ret = m_aMap.remove (sMenuItemID);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (ret != null)
      if (s_aLogger.isDebugEnabled ())
        s_aLogger.debug ("Removed content of last forced redirect from '" + sMenuItemID + "'");
    return ret;
  }

  @Nullable
  public static IHCNode getAndRemoveLastForcedRedirectContent (@Nullable final String sMenuItemID)
  {
    if (StringHelper.hasNoText (sMenuItemID))
      return null;

    final ForcedRedirectManager aMgr = getInstanceIfInstantiated ();
    if (aMgr == null)
      return null;

    // test in read-lock
    if (aMgr.hasContent (sMenuItemID))
    {
      // get and remove in write lock
      return aMgr.getAndRemoveContent (sMenuItemID);
    }

    // No content
    return null;
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("map", m_aMap).toString ();
  }
}
