/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.connect.sftp;

import java.util.Properties;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.EChange;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.photon.connect.connection.IBaseServerConnectionSettings;
import com.helger.photon.connect.connection.IServerConnectionSettingsKeyPair;
import com.helger.photon.connect.connection.IServerConnectionSettingsPassword;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Utility class to create JSch sessions.
 *
 * @author Philip Helger
 */
@Immutable
public final class JSchSessionFactory
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JSchSessionFactory.class);
  private static final IMutableStatisticsHandlerCounter s_aStatsCounterCreated = StatisticsManager.getCounterHandler (JSchSessionFactory.class +
                                                                                                                      "$created");
  private static final IMutableStatisticsHandlerCounter s_aStatsCounterDestroyed = StatisticsManager.getCounterHandler (JSchSessionFactory.class +
                                                                                                                        "$destroyed");

  private JSchSessionFactory ()
  {}

  @Nonnull
  private static String _debugSession (@Nonnull final Session aSession)
  {
    return "[Session@" +
           Integer.toHexString (System.identityHashCode (aSession)) +
           " - " +
           (aSession.isConnected () ? "connected" : "free") +
           "]";
  }

  @Nonnull
  private static Session _createSession (@Nonnull final JSch aJSch,
                                         @Nonnull final IBaseServerConnectionSettings aSettings) throws JSchException
  {
    final Session aSession = aJSch.getSession (aSettings.getUserName (), aSettings.getServerAddress (), aSettings.getServerPort ());
    // Set timeout in session
    if (aSettings.getConnectionTimeoutMillis () >= 0)
      aSession.setTimeout (aSettings.getConnectionTimeoutMillis ());

    // Setup Strict HostKeyChecking to no so we don't get the
    // unknown host key exception
    final Properties aConfig = new Properties ();
    aConfig.put ("StrictHostKeyChecking", "no");
    aSession.setConfig (aConfig);

    return aSession;
  }

  @Nonnull
  public static Session createSession (@Nonnull final IServerConnectionSettingsPassword aSettings) throws JSchException
  {
    ValueEnforcer.notNull (aSettings, "Settings");

    final JSch aJSch = new JSch ();

    // Create session
    final Session aSession = _createSession (aJSch, aSettings);
    aSession.setPassword (aSettings.getPassword ());

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Created new session " + _debugSession (aSession));

    // Timeout already set directly on session!
    aSession.connect ();

    s_aStatsCounterCreated.increment ();
    return aSession;
  }

  @Nonnull
  public static Session createSession (@Nonnull final IServerConnectionSettingsKeyPair aSettings) throws JSchException
  {
    ValueEnforcer.notNull (aSettings, "Settings");

    final JSch aJSch = new JSch ();

    // key pair
    aJSch.addIdentity (aSettings.getUserName (), aSettings.getPrivateKey (), aSettings.getPublicKey (), aSettings.getKeyPairPassphrase ());

    // Create session
    final Session aSession = _createSession (aJSch, aSettings);

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Created new session " + _debugSession (aSession));

    // Timeout already set directly on session!
    aSession.connect ();

    s_aStatsCounterCreated.increment ();
    return aSession;
  }

  @Nonnull
  public static EChange destroySession (@Nonnull final Session aSession)
  {
    ValueEnforcer.notNull (aSession, "Session");

    if (!aSession.isConnected ())
      return EChange.UNCHANGED;

    aSession.disconnect ();
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Closed session " + _debugSession (aSession));
    s_aStatsCounterDestroyed.increment ();
    return EChange.CHANGED;
  }
}
