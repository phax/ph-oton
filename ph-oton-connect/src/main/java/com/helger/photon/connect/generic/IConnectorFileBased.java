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
package com.helger.photon.connect.generic;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.WillClose;

import com.helger.commons.state.ESuccess;

public interface IConnectorFileBased <HANDLETYPE, REMOTEITEMTYPE> extends IConnector <HANDLETYPE>
{
  /**
   * Receive data from somewhere.
   *
   * @param sID
   *        ID of the data to be retrieved. E.g. a file name. May not be
   *        <code>null</code>.
   * @param aOS
   *        The output stream to write to. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess getData (@Nonnull String sID, @Nonnull @WillClose OutputStream aOS);

  /**
   * Write data to somewhere.
   *
   * @param sID
   *        The ID of the artifact to write to. This may e.g. be a file name.
   *        May not be <code>null</code>.
   * @param aIS
   *        The input stream to read from. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess putData (@Nonnull String sID, @Nonnull @WillClose InputStream aIS);

  /**
   * Change the working directory to the specified directory.
   *
   * @param sDirectory
   *        The directory to use.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess changeWorkingDirectory (@Nonnull String sDirectory);

  /**
   * Go one directory level up
   *
   * @return {@link ESuccess}.
   * @see #changeWorkingDirectory(String)
   */
  @Nonnull
  ESuccess changeToParentDirectory ();

  /**
   * Delete a file in the current directory
   *
   * @param sFilename
   *        The filename to be deleted.
   * @return {@link ESuccess}.
   */
  @Nonnull
  ESuccess deleteFile (String sFilename);

  /**
   * List all files in the current directory
   *
   * @param aFilter
   *        The filter to be used. Maybe <code>null</code>.
   * @param aTargetList
   *        The list where the result files should be added. May not be
   *        <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess listFiles (@Nullable Predicate <REMOTEITEMTYPE> aFilter, @Nonnull List <REMOTEITEMTYPE> aTargetList);
}
