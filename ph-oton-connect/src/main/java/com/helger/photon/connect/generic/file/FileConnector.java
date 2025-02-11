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
package com.helger.photon.connect.generic.file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.io.file.FileHelper;
import com.helger.commons.io.file.FileOperations;
import com.helger.commons.io.file.FileSystemIterator;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.photon.connect.generic.IConnectorFileBased;
import com.helger.security.authentication.credentials.IAuthCredentials;

public class FileConnector implements IConnectorFileBased <File, File>
{
  private final IFileConnectionDestination m_aDestination;
  private File m_aChannel;

  public FileConnector (@Nonnull final IFileConnectionDestination aDestination)
  {
    ValueEnforcer.notNull (aDestination, "Destination");
    m_aDestination = aDestination;
  }

  @Nonnull
  public IFileConnectionDestination getDestination ()
  {
    return m_aDestination;
  }

  @Nullable
  public File getHandle ()
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
      {
        final File aFile = new File (m_aChannel, sFilename);
        return StreamHelper.copyInputStreamToOutputStream (FileHelper.getInputStream (aFile), aOS);
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
      {
        final File aFile = new File (m_aChannel, sFilename);
        return StreamHelper.copyInputStreamToOutputStream (aIS, FileHelper.getOutputStream (aFile));
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
    {
      m_aChannel = new File (m_aChannel, sDirectory);
      return ESuccess.SUCCESS;
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
    {
      final File aFile = new File (m_aChannel, sFilename);
      return ESuccess.valueOf (FileOperations.deleteFile (aFile).isSuccess ());
    }
    return ESuccess.FAILURE;
  }

  @Nonnull
  public ESuccess listFiles (@Nullable final Predicate <File> aFilter, @Nonnull final List <File> aTargetList)
  {
    if (m_aChannel != null)
    {
      for (final File aFile : new FileSystemIterator (m_aChannel))
        if (aFilter == null || aFilter.test (aFile))
          aTargetList.add (aFile);
      return ESuccess.SUCCESS;
    }
    return ESuccess.FAILURE;
  }
}
