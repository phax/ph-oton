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
package com.helger.photon.security.login;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.string.StringHelper;
import com.helger.photon.app.io.WebFileIO;

/**
 * This class encapsulates the file IO base directory for the current user
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class LoggedInUserStorage
{
  /**
   * The name of the default base directory relative to the WebFileIO where the
   * data is referenced
   */
  public static final String BASE_DIRECTORY = "userdata/";

  private static final Logger LOGGER = LoggerFactory.getLogger (LoggedInUserStorage.class);

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("s_aRWLock")
  private static String s_sBaseDirectory = BASE_DIRECTORY;

  private LoggedInUserStorage ()
  {}

  /**
   * @return The base directory to be used. By default {@link #BASE_DIRECTORY}
   *         is used.
   */
  @Nonnull
  public static String getBaseDirectory ()
  {
    return s_aRWLock.readLockedGet ( () -> s_sBaseDirectory);
  }

  /**
   * Set the base directory to be used.
   *
   * @param sBaseDirectory
   *        The new base directory. May not be <code>null</code> but maybe
   *        empty.
   */
  public static void setBaseDirectory (@Nonnull final String sBaseDirectory)
  {
    ValueEnforcer.notNull (sBaseDirectory, "BaseDirectory");

    s_aRWLock.writeLockedGet ( () -> s_sBaseDirectory = sBaseDirectory);
  }

  /**
   * @return The base directory for all user-related data of the current user
   */
  @Nonnull
  public static File getUserdataDirectory ()
  {
    final String sCurrentUserID = LoggedInUserManager.getInstance ().getCurrentUserID ();
    return getUserdataDirectory (sCurrentUserID);
  }

  /**
   * @param sUserID
   *        the ID of the user for which the user data is requested. May neither
   *        be <code>null</code> nor empty.
   * @return The base directory for all user-related data
   */
  @Nonnull
  public static File getUserdataDirectory (@Nonnull @Nonempty final String sUserID)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");

    // Ensure user ID is valid as a filename!
    final String sRealUserID = FilenameHelper.getAsSecureValidASCIIFilename (sUserID);
    if (StringHelper.hasNoText (sRealUserID))
      throw new IllegalStateException ("Passed user ID '" + sUserID + "' is an empty filename!");
    if (!sRealUserID.equals (sUserID))
      LOGGER.warn ("User ID '" + sUserID + "' was modified to '" + sRealUserID + "' to be used as a file system name!");

    final File aDir = WebFileIO.getDataIO ().getFile (getBaseDirectory () + sRealUserID);
    FileOperationManager.INSTANCE.createDirRecursiveIfNotExisting (aDir);
    return aDir;
  }
}
