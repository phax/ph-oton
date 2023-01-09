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
package com.helger.photon.connect.generic.ftp;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.security.authentication.credentials.IAuthCredentials;
import com.helger.security.authentication.credentials.usernamepw.IUserNamePasswordCredentials;

public final class FftpConnectionDestination implements IFtpConnectionDestination
{
  private static final boolean DEFAULT_ENTER_LOCAL_PASSIVE_MODE = false;
  private static final Logger LOGGER = LoggerFactory.getLogger (FftpConnectionDestination.class);

  private final int m_nConnectTimeoutMilliSeconds;
  private final String m_sHostname;
  private final int m_nPort;
  private final boolean m_bEnterLocalPassiveMode;

  public FftpConnectionDestination (@Nonnull final String sHostname)
  {
    this (sHostname, DEFAULT_ENTER_LOCAL_PASSIVE_MODE);
  }

  public FftpConnectionDestination (@Nonnull final String sHostname, final boolean bEnterLocalPassiveMode)
  {
    this (sHostname, DEFAULT_FTP_PORT, bEnterLocalPassiveMode);
  }

  public FftpConnectionDestination (@Nonnull final String sHostname, @Nonnegative final int nPort)
  {
    this (sHostname, nPort, DEFAULT_ENTER_LOCAL_PASSIVE_MODE);
  }

  public FftpConnectionDestination (@Nonnull final String sHostname, @Nonnegative final int nPort, final boolean bEnterLocalPassiveMode)
  {
    this (sHostname, nPort, 2000, bEnterLocalPassiveMode);
  }

  public FftpConnectionDestination (@Nonnull final String sHostname,
                                    @Nonnegative final int nPort,
                                    @Nonnegative final int nConnectTimeoutMilliSeconds,
                                    final boolean bEnterLocalPassiveMode)
  {
    if (StringHelper.hasNoText (sHostname))
      throw new IllegalArgumentException ("hostname");
    if (nPort <= 0 || nPort > 65535)
      throw new IllegalArgumentException ("port");
    if (nConnectTimeoutMilliSeconds < 1)
      throw new IllegalArgumentException ("Illegal connection timeout specified");
    m_sHostname = sHostname;
    m_nPort = nPort;
    m_nConnectTimeoutMilliSeconds = nConnectTimeoutMilliSeconds;
    m_bEnterLocalPassiveMode = bEnterLocalPassiveMode;
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
  public FTPClient openConnection (@Nonnull final IAuthCredentials aCredentials)
  {
    if (!(aCredentials instanceof IUserNamePasswordCredentials))
      throw new IllegalArgumentException ("Needs to be username/password credentials");
    final IUserNamePasswordCredentials aUPC = (IUserNamePasswordCredentials) aCredentials;

    final FTPClient aFtpClient = new FTPClient ();
    try
    {
      aFtpClient.setConnectTimeout (m_nConnectTimeoutMilliSeconds);
      aFtpClient.connect (m_sHostname, m_nPort);
      if (m_bEnterLocalPassiveMode)
        aFtpClient.enterLocalPassiveMode ();
      if (!aFtpClient.login (aUPC.getUserName (), aUPC.getPassword ()))
        throw new IOException ("Failed to log into FTP server " + m_sHostname + ":" + m_nPort + ": " + aFtpClient.getReplyString ());
      aFtpClient.setFileType (FTP.BINARY_FILE_TYPE);
      return aFtpClient;
    }
    catch (final SocketTimeoutException ex)
    {
      LOGGER.error ("Failed to connect to " + m_sHostname + ":" + m_nPort + ": " + ex.getMessage ());
    }
    catch (final IOException ex)
    {
      LOGGER.error ("Connection exception to " + m_sHostname + ":" + m_nPort + ": " + aFtpClient.getReplyString (), ex);
    }

    // In case of an error, disconnect (if connected)
    if (aFtpClient.isConnected ())
      try
      {
        aFtpClient.disconnect ();
      }
      catch (final IOException ex2)
      {
        LOGGER.error ("Error disconnecting from FTP while in connection phase: " + aFtpClient.getReplyString (), ex2);
      }
    return null;
  }

  @Nonnull
  public EChange closeConnection (@Nullable final FTPClient aFtpClient)
  {
    if (aFtpClient == null)
      return EChange.UNCHANGED;

    try
    {
      // end FTP session
      if (!aFtpClient.logout ())
        LOGGER.warn ("Failed to log out from FTP connection: " + aFtpClient.getReplyString ());

      // close channel
      if (aFtpClient.isConnected ())
        aFtpClient.disconnect ();
    }
    catch (final IOException ex)
    {
      LOGGER.error ("Error closing FTP connection: " + aFtpClient.getReplyString (), ex);
    }
    return EChange.CHANGED;
  }
}
