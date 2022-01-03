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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.state.ESuccess;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Immutable
public final class ChannelSftpRunner
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ChannelSftpRunner.class);

  private ChannelSftpRunner ()
  {}

  /**
   * Upload a file to the server.
   *
   * @param aSessionProvider
   *        The JSch session provider. May not be <code>null</code>.
   * @param nChannelConnectTimeoutMillis
   *        The channel connection timeout in milliseconds. 0 means infinite.
   * @param aRunnable
   *        The callback that performs the actions via SFTP. May not be
   *        <code>null</code>.
   * @return {@link ESuccess#SUCCESS} if operation succeeded,
   *         {@link ESuccess#FAILURE} otherwise.
   * @throws JSchException
   *         If some general connection handling stuff goes wrong.
   */
  @Nonnull
  public static ESuccess execute (@Nonnull final IJSchSessionProvider aSessionProvider,
                                  final int nChannelConnectTimeoutMillis,
                                  @Nonnull final IChannelSftpRunnable aRunnable) throws JSchException
  {
    ValueEnforcer.notNull (aSessionProvider, "SessionProvider");
    ValueEnforcer.notNull (aRunnable, "Runnable");

    Session aSession = null;
    Channel aChannel = null;
    ChannelSftp aSFTPChannel = null;
    try
    {
      // get session from pool
      aSession = aSessionProvider.createSession ();
      if (aSession == null)
        throw new IllegalStateException ("Failed to create JSch session from provider");

      // Open the SFTP channel
      aChannel = aSession.openChannel ("sftp");

      // Set connection timeout
      aChannel.connect (nChannelConnectTimeoutMillis);
      aSFTPChannel = (ChannelSftp) aChannel;

      // call callback
      aRunnable.execute (aSFTPChannel);
      return ESuccess.SUCCESS;
    }
    catch (final SftpException ex)
    {
      LOGGER.error ("Error peforming SFTP action: " + aRunnable.getDisplayName (), ex);
      return ESuccess.FAILURE;
    }
    finally
    {
      // end SFTP session
      if (aSFTPChannel != null)
        aSFTPChannel.quit ();

      // close channel
      if (aChannel != null && aChannel.isConnected ())
        aChannel.disconnect ();

      // destroy session
      if (aSession != null)
        JSchSessionFactory.destroySession (aSession);
    }
  }
}
