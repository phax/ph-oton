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
package com.helger.photon.connect.generic.sftp;

import java.util.Properties;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.network.port.CNetworkPort;
import com.helger.security.authentication.credentials.IAuthCredentials;
import com.helger.security.authentication.credentials.usernamepw.IUserNamePasswordCredentials;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public final class SftpConnectionDestination implements ISftpConnectionDestination
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SftpConnectionDestination.class);
  private final int m_nConnectTimeoutMilliSeconds;
  private final String m_sHostname;
  private final int m_nPort;

  public SftpConnectionDestination (@Nonnull final String sHostname)
  {
    this (sHostname, DEFAULT_SFTP_PORT);
  }

  public SftpConnectionDestination (@Nonnull final String sHostname, @Nonnegative final int nPort)
  {
    this (sHostname, nPort, 2000);
  }

  public SftpConnectionDestination (@Nonnull final String sHostname,
                                    @Nonnegative final int nPort,
                                    @Nonnegative final int nConnectTimeoutMilliSeconds)
  {
    if (StringHelper.hasNoText (sHostname))
      throw new IllegalArgumentException ("hostname");
    if (nPort <= CNetworkPort.MINIMUM_PORT_NUMBER || nPort > CNetworkPort.MAXIMUM_PORT_NUMBER)
      throw new IllegalArgumentException ("port");
    if (nConnectTimeoutMilliSeconds < 1)
      throw new IllegalArgumentException ("Illegal connection timeout specified");
    m_sHostname = sHostname;
    m_nPort = nPort;
    m_nConnectTimeoutMilliSeconds = nConnectTimeoutMilliSeconds;
  }

  @Nonnull
  public String getHostname ()
  {
    return m_sHostname;
  }

  @Nonnegative
  public int getPort ()
  {
    return m_nPort;
  }

  @Nullable
  public ChannelSftp openConnection (@Nonnull final IAuthCredentials aCredentials)
  {
    if (!(aCredentials instanceof IUserNamePasswordCredentials))
      throw new IllegalArgumentException ("Needs to be username/password credentials");
    final IUserNamePasswordCredentials aUPC = (IUserNamePasswordCredentials) aCredentials;

    Session aSession = null;
    Channel aChannel = null;
    try
    {
      final JSch jsch = new JSch ();

      aSession = jsch.getSession (aUPC.getUserName (), m_sHostname, m_nPort);
      aSession.setPassword (aUPC.getPassword ());

      /*
       * Setup Strict HostKeyChecking to no so we don't get the unknown host key
       * exception
       */
      final Properties aConfig = new Properties ();
      aConfig.put ("StrictHostKeyChecking", "no");
      aSession.setConfig (aConfig);

      // do connect
      aSession.connect (m_nConnectTimeoutMilliSeconds);

      // Open the SFTP channel
      aChannel = aSession.openChannel ("sftp");
      aChannel.connect ();
      return (ChannelSftp) aChannel;
    }
    catch (final JSchException ex)
    {
      LOGGER.error ("Connection exception to " + m_sHostname + ":" + m_nPort, ex);
      if (aChannel != null && aChannel.isConnected ())
        aChannel.disconnect ();

      if (aSession != null && aSession.isConnected ())
        aSession.disconnect ();
    }
    return null;
  }

  @Nonnull
  public EChange closeConnection (@Nullable final ChannelSftp aChannel)
  {
    if (aChannel == null)
      return EChange.UNCHANGED;

    // end SFTP session
    aChannel.quit ();

    // close channel
    if (aChannel.isConnected ())
      aChannel.disconnect ();

    // close session
    try
    {
      aChannel.getSession ().disconnect ();
    }
    catch (final JSchException ex)
    {
      LOGGER.error ("Failed to retrieve session for closing", ex);
    }
    return EChange.CHANGED;
  }
}
