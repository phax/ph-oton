/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.string.StringHelper;
import com.helger.web.scope.singleton.AbstractGlobalWebSingleton;

/**
 * This class contains all the objects that are hold "per application".
 *
 * @author Philip Helger
 * @since 8.0.0
 */
public final class PhotonGlobalState extends AbstractGlobalWebSingleton
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonGlobalState.class);

  private String m_sDefaultApplicationID;
  private final ICommonsMap <String, PhotonGlobalStatePerApp> m_aStateMap = new CommonsHashMap <> ();

  @Deprecated
  @UsedViaReflection
  public PhotonGlobalState ()
  {}

  @Nonnull
  public static PhotonGlobalState getInstance ()
  {
    return getGlobalSingleton (PhotonGlobalState.class);
  }

  /**
   * @return The default application ID. May be <code>null</code>.
   */
  @Nullable
  public String getDefaultApplicationID ()
  {
    return m_aRWLock.readLockedGet ( () -> m_sDefaultApplicationID);
  }

  /**
   * @return <code>true</code> if a default application ID is present,
   *         <code>false</code> otherwise.
   * @since 8.0.3
   */
  public boolean hasDefaultApplicationID ()
  {
    return StringHelper.hasText (getDefaultApplicationID ());
  }

  /**
   * Set the default application ID of an application servlet.
   *
   * @param sDefaultApplicationID
   *        The last application ID to be set. May be <code>null</code>.
   * @return this for chaining
   */
  @Nonnull
  public PhotonGlobalState setDefaultApplicationID (@Nullable final String sDefaultApplicationID)
  {
    m_aRWLock.writeLocked ( () -> {
      if (!EqualsHelper.equals (m_sDefaultApplicationID, sDefaultApplicationID))
      {
        m_sDefaultApplicationID = sDefaultApplicationID;
        if (LOGGER.isInfoEnabled ())
          LOGGER.info ("Default application ID set to '" + sDefaultApplicationID + "'");
      }
    });
    return this;
  }

  @Nonnull
  public static PhotonGlobalStatePerApp state (@Nonnull @Nonempty final String sAppID)
  {
    ValueEnforcer.notEmpty (sAppID, "AppID");
    final PhotonGlobalState aGlobalState = getInstance ();
    return aGlobalState.m_aStateMap.computeIfAbsent (sAppID, k -> {
      // By default set first app ID as default
      if (!aGlobalState.hasDefaultApplicationID ())
        aGlobalState.setDefaultApplicationID (sAppID);
      return new PhotonGlobalStatePerApp ();
    });
  }

  public static void clear ()
  {
    final PhotonGlobalState aGlobalState = getInstance ();
    aGlobalState.m_aStateMap.clear ();
    aGlobalState.setDefaultApplicationID (null);
  }

  public static void removeAllApplicationServletPathMappings ()
  {
    getInstance ().m_aStateMap.forEachValue (PhotonGlobalStatePerApp::removeServletPath);
  }

  public static boolean containsNoState ()
  {
    return getInstance ().m_aStateMap.isEmpty ();
  }

  public static boolean containsAnyApplicationServletPathMapping ()
  {
    return getInstance ().m_aStateMap.containsAnyValue (x -> x.internalGetServletPath () != null);
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsMap <String, String> getAppIDToServletPathMap ()
  {
    final ICommonsMap <String, String> ret = new CommonsHashMap <> ();
    getInstance ().m_aStateMap.forEach ( (sAppID, aState) -> ret.put (sAppID, aState.getServletPath ()));
    return ret;
  }
}
