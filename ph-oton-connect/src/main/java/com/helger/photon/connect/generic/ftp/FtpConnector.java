/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.collection.ArrayHelper;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.photon.connect.generic.IConnectorFileBased;
import com.helger.security.authentication.credentials.IAuthCredentials;

public class FtpConnector implements IConnectorFileBased <FTPClient, FTPFile>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (FtpConnector.class);
  private final IFtpConnectionDestination m_aDestination;
  private FTPClient m_aChannel;

  public FtpConnector (@Nonnull final String sHostname)
  {
    this (new FftpConnectionDestination (sHostname));
  }

  public FtpConnector (@Nonnull final String sHostname, final boolean bEnterLocalPassiveMode)
  {
    this (new FftpConnectionDestination (sHostname, bEnterLocalPassiveMode));
  }

  public FtpConnector (@Nonnull final String sHostname, final int nPort)
  {
    this (new FftpConnectionDestination (sHostname, nPort));
  }

  public FtpConnector (@Nonnull final String sHostname, final int nPort, final boolean bEnterLocalPassiveMode)
  {
    this (new FftpConnectionDestination (sHostname, nPort, bEnterLocalPassiveMode));
  }

  public FtpConnector (@Nonnull final IFtpConnectionDestination aDestination)
  {
    ValueEnforcer.notNull (aDestination, "Destination");
    m_aDestination = aDestination;
  }

  @Nonnull
  public IFtpConnectionDestination getDestination ()
  {
    return m_aDestination;
  }

  @Nullable
  public FTPClient getHandle ()
  {
    return m_aChannel;
  }

  @Nonnull
  public EChange openConnection (@Nonnull final IAuthCredentials aCredentials)
  {
    // already open?
    if (isConnectionOpen ())
      return EChange.UNCHANGED;

    // open connection
    m_aChannel = m_aDestination.openConnection (aCredentials);
    return EChange.valueOf (isConnectionOpen ());
  }

  @Nonnull
  public EChange closeConnection ()
  {
    if (isConnectionOpen () && m_aDestination.closeConnection (m_aChannel).isChanged ())
    {
      m_aChannel = null;
      return EChange.CHANGED;
    }

    return EChange.UNCHANGED;
  }

  public boolean isConnectionOpen ()
  {
    return m_aChannel != null;
  }

  @Nonnull
  public ESuccess getData (@Nonnull final String sFilename, @Nonnull @WillClose final OutputStream aOS)
  {
    try
    {
      if (m_aChannel != null)
        try
        {
          // Does not close the output-stream!
          if (m_aChannel.retrieveFile (sFilename, aOS))
          {
            LOGGER.info ("Successfully got data '" + sFilename + "'");
            return ESuccess.SUCCESS;
          }
          LOGGER.warn ("Failed to get data '" + sFilename + "': " + m_aChannel.getReplyString ());
        }
        catch (final IOException ex)
        {
          LOGGER.error ("Error in get data '" + sFilename + "': " + m_aChannel.getReplyString (), ex);
        }
      return ESuccess.FAILURE;
    }
    finally
    {
      StreamHelper.close (aOS);
    }
  }

  @Nonnull
  public ESuccess putData (@Nonnull final String sFilename, @Nonnull @WillClose final InputStream aIS)
  {
    try
    {
      if (m_aChannel != null)
        try
        {
          // Does not close the input-stream!
          if (m_aChannel.storeFile (sFilename, aIS))
          {
            LOGGER.info ("Successfully put data '" + sFilename + "'");
            return ESuccess.SUCCESS;
          }
          LOGGER.warn ("Failed to put data '" + sFilename + "': " + m_aChannel.getReplyString ());
        }
        catch (final IOException ex)
        {
          LOGGER.error ("Error putting data '" + sFilename + "': " + m_aChannel.getReplyString (), ex);
        }
      return ESuccess.FAILURE;
    }
    finally
    {
      StreamHelper.close (aIS);
    }
  }

  @Nonnull
  public ESuccess changeWorkingDirectory (final String sDirectory)
  {
    if (m_aChannel != null)
      try
      {
        if (m_aChannel.changeWorkingDirectory (sDirectory))
        {
          LOGGER.info ("Successfully changed directory to '" + sDirectory + "'");
          return ESuccess.SUCCESS;
        }
        LOGGER.warn ("Failed to change working directory to '" + sDirectory + "': " + m_aChannel.getReplyString ());
      }
      catch (final IOException ex)
      {
        LOGGER.error ("Error changing working directory to '" + sDirectory + "': " + m_aChannel.getReplyString (), ex);
      }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess changeToParentDirectory ()
  {
    if (m_aChannel != null)
      try
      {
        if (m_aChannel.changeToParentDirectory ())
        {
          LOGGER.info ("Successfully changed directory to parent directory");
          return ESuccess.SUCCESS;
        }
        LOGGER.warn ("Failed to change to parent directory: " + m_aChannel.getReplyString ());
      }
      catch (final IOException ex)
      {
        LOGGER.error ("Error changing to parent directory: " + m_aChannel.getReplyString (), ex);
      }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess deleteFile (@Nullable final String sFilename)
  {
    if (m_aChannel != null)
      try
      {
        // listNames may return null
        if (ArrayHelper.contains (m_aChannel.listNames (null), sFilename))
        {
          if (!m_aChannel.deleteFile (sFilename))
          {
            LOGGER.error ("Failed to delete file '" + sFilename + "': " + m_aChannel.getReplyString ());
            return ESuccess.FAILURE;
          }
          LOGGER.info ("Successfully deleted file '" + sFilename + "'");
        }

        // Return success if file is not on server
        return ESuccess.SUCCESS;
      }
      catch (final IOException ex)
      {
        LOGGER.error ("Error deleting file '" + sFilename + "': " + m_aChannel.getReplyString (), ex);
      }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess listFiles (@Nullable final Predicate <FTPFile> aFilter, @Nonnull final List <FTPFile> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");

    if (m_aChannel != null)
    {
      try
      {
        final FTPFile [] aFiles = m_aChannel.listFiles (null, FTPFileFilterFromIFilter.create (aFilter));
        Collections.addAll (aTargetList, aFiles);
        LOGGER.info ("Successfully listed " + aTargetList.size () + " files");
        return ESuccess.SUCCESS;
      }
      catch (final IOException ex)
      {
        LOGGER.error ("Error listing files: " + m_aChannel.getReplyString (), ex);
      }
    }
    return ESuccess.FAILURE;
  }
}
