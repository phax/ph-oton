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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.photon.connect.generic.IConnectorFileBased;
import com.helger.security.authentication.credentials.IAuthCredentials;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

public class SftpConnector implements IConnectorFileBased <ChannelSftp, ChannelSftp.LsEntry>
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SftpConnector.class);
  private final ISftpConnectionDestination m_aDestination;
  private ChannelSftp m_aChannel;

  public SftpConnector (@Nonnull final String sHostname)
  {
    this (new SftpConnectionDestination (sHostname));
  }

  public SftpConnector (@Nonnull final String sHostname, final int nPort)
  {
    this (new SftpConnectionDestination (sHostname, nPort));
  }

  public SftpConnector (@Nonnull final ISftpConnectionDestination aDestination)
  {
    ValueEnforcer.notNull (aDestination, "Destination");
    m_aDestination = aDestination;
  }

  @Nonnull
  public ISftpConnectionDestination getDestination ()
  {
    return m_aDestination;
  }

  @Nullable
  public ChannelSftp getHandle ()
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
          m_aChannel.get (sFilename, aOS);
          LOGGER.info ("Successfully got data '" + sFilename + "'");
          return ESuccess.SUCCESS;
        }
        catch (final SftpException ex)
        {
          LOGGER.error ("Error in get data '" + sFilename + "'", ex);
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
          m_aChannel.put (aIS, sFilename);
          LOGGER.info ("Successfully put data '" + sFilename + "'");
          return ESuccess.SUCCESS;
        }
        catch (final SftpException ex)
        {
          LOGGER.error ("Error putting data '" + sFilename + "'", ex);
        }
      return ESuccess.FAILURE;
    }
    finally
    {
      StreamHelper.close (aIS);
    }
  }

  @Nonnull
  public ESuccess changeWorkingDirectory (@Nonnull final String sDirectory)
  {
    if (m_aChannel != null)
      try
      {
        m_aChannel.cd (sDirectory);
        LOGGER.info ("Successfully changed directory to '" + sDirectory + "'");
        return ESuccess.SUCCESS;
      }
      catch (final SftpException ex)
      {
        LOGGER.error ("Error changing directory to '" + sDirectory + "'", ex);
      }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess changeToParentDirectory ()
  {
    return changeWorkingDirectory ("..");
  }

  @Nonnull
  public ESuccess deleteFile (@Nonnull final String sFilename)
  {
    if (m_aChannel != null)
      try
      {
        m_aChannel.rm (sFilename);
        LOGGER.info ("Successfully deleted file '" + sFilename + "'");
        return ESuccess.SUCCESS;
      }
      catch (final SftpException ex)
      {
        LOGGER.error ("Error deleting file '" + sFilename + "'", ex);
      }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess listFiles (@Nullable final Predicate <ChannelSftp.LsEntry> aFilter, @Nonnull final List <ChannelSftp.LsEntry> aTargetList)
  {
    ValueEnforcer.notNull (aTargetList, "TargetList");

    if (m_aChannel != null)
    {
      try
      {
        final List <?> aFiles = m_aChannel.ls (".");
        for (final Object aFile : aFiles)
        {
          final ChannelSftp.LsEntry aEntry = (ChannelSftp.LsEntry) aFile;
          if (aFilter == null || aFilter.test (aEntry))
            aTargetList.add (aEntry);
        }
        LOGGER.info ("Successfully listed " + aTargetList.size () + " files");
        return ESuccess.SUCCESS;
      }
      catch (final SftpException ex)
      {
        LOGGER.error ("Error listing files", ex);
      }
    }
    return ESuccess.FAILURE;
  }
}
