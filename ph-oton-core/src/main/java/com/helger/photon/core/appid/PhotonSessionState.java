/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.core.appid;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.string.ToStringGenerator;
import com.helger.web.scope.singleton.AbstractSessionWebSingleton;

/**
 * This class maintains the session state per application ID
 *
 * @author Philip Helger
 * @since 8.0.0
 */
@ThreadSafe
public final class PhotonSessionState extends AbstractSessionWebSingleton
{
  @GuardedBy ("RW_LOCK")
  private String m_sLastApplicationID;
  private final ICommonsMap <String, PhotonSessionStatePerApp> m_aStateMap = new CommonsHashMap <> ();

  @Deprecated
  @UsedViaReflection
  public PhotonSessionState ()
  {}

  /**
   * @return The one and only instance for the current session. Never
   *         <code>null</code>.
   * @see #getInstanceIfInstantiated()
   */
  @Nonnull
  public static PhotonSessionState getInstance ()
  {
    return getSessionSingleton (PhotonSessionState.class);
  }

  /**
   * @return The one and only instance for the current session. It may be
   *         <code>null</code> if this singleton was not yet instantiated.
   * @see #getInstance()
   */
  @Nullable
  public static PhotonSessionState getInstanceIfInstantiated ()
  {
    return getSessionSingletonIfInstantiated (PhotonSessionState.class);
  }

  /**
   * @return The last application ID used for the application servlet. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getLastApplicationID ()
  {
    return m_aRWLock.readLockedGet ( () -> m_sLastApplicationID);
  }

  /**
   * Set the last used application ID of an application servlet.
   *
   * @param sLastApplicationID
   *        The last application ID to be set. May be <code>null</code>.
   */
  void setLastApplicationID (@Nullable final String sLastApplicationID)
  {
    m_aRWLock.writeLocked ( () -> m_sLastApplicationID = sLastApplicationID);
  }

  /**
   * Get or create a new state for the provided app ID.
   *
   * @param sAppID
   *        The app ID to get the state for. May neither be <code>null</code>
   *        nor empty.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public PhotonSessionStatePerApp state (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    return m_aStateMap.computeIfAbsent (sAppID, k -> new PhotonSessionStatePerApp ());
  }

  @Nonnull
  public PhotonSessionStatePerApp stateLastAppID ()
  {
    return state (getLastApplicationID ());
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("LastApplicationID", m_sLastApplicationID)
                            .append ("StateMap", m_aStateMap)
                            .getToString ();
  }
}
