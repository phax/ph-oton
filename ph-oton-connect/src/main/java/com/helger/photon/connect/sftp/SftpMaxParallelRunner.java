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
package com.helger.photon.connect.sftp;

import java.util.concurrent.Semaphore;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ESuccess;
import com.helger.collection.commons.CommonsConcurrentHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.jcraft.jsch.JSchException;

/**
 * Special SFTP executor that maintains the maximum parallel connection limit.
 *
 * @author Philip Helger
 * @since 10.2.1
 */
@Immutable
public final class SftpMaxParallelRunner
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SftpMaxParallelRunner.class);
  private static final ICommonsMap <String, Semaphore> MAX_CONNECTIONS = new CommonsConcurrentHashMap <> ();

  private SftpMaxParallelRunner ()
  {}

  /**
   * Upload a file to the server. Note: must be synchronized with a semaphore, because on one server
   * only a limited number of connections can be open at the same time. Otherwise we may get
   * connection exception!
   *
   * @param aSFTPSettings
   *        Connections settings to the server.
   * @param aRunnable
   *        The callback that performs the actions via SFTP. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} if operation succeeded, {@link ESuccess#FAILURE} otherwise.
   * @throws JSchException
   *         If some general connection handling stuff goes wrong.
   */
  @NonNull
  public static ESuccess execute (@NonNull final ISftpSettings aSFTPSettings,
                                  @NonNull final IChannelSftpRunnable aRunnable) throws JSchException
  {
    ValueEnforcer.notNull (aSFTPSettings, "SFTPSettings");
    ValueEnforcer.notNull (aRunnable, "Runnable");

    // Find the Semaphore per server
    final String sSemaKey = aSFTPSettings.getDisplayName ();

    // The maximum number of parallel connections is defined by the SFTP Settings
    final Semaphore aSemaphore = MAX_CONNECTIONS.computeIfAbsent (sSemaKey,
                                                                  k -> new Semaphore (aSFTPSettings.getMaximumParallelConnections ()));

    try
    {
      aSemaphore.acquire ();
    }
    catch (final InterruptedException ex)
    {
      LOGGER.error ("Failed to acquire Semaphore for SFTP settings " + aSFTPSettings, ex);
      return ESuccess.FAILURE;
    }

    try
    {
      final JschSessionProvider aSessionProvider = new JschSessionProvider (aSFTPSettings);
      return ChannelSftpRunner.execute (aSessionProvider, aSFTPSettings.getConnectionTimeoutMillis (), aRunnable);
    }
    finally
    {
      aSemaphore.release ();
    }
  }
}
