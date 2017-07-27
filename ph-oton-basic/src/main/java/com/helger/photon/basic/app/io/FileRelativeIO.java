/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.app.io;

import java.io.File;
import java.io.OutputStream;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.EAppend;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.io.file.FileSystemRecursiveIterator;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;

/**
 * Default implementation of {@link IMutableFileRelativeIO}.
 *
 * @author Philip Helger
 * @since 7.1.1
 */
@Immutable
public class FileRelativeIO implements IMutableFileRelativeIO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (FileRelativeIO.class);

  private final File m_aBasePath;

  static void internalCheckAccessRights (@Nonnull final File m_aBasePath)
  {
    // Check read/write/execute
    final StopWatch aSW = StopWatch.createdStarted ();
    s_aLogger.info ("Checking file access in " + m_aBasePath);
    int nFiles = 0;
    int nDirs = 0;
    for (final File aFile : new FileSystemRecursiveIterator (m_aBasePath))
      if (aFile.isFile ())
      {
        // Check if files are read-write
        if (!aFile.canRead ())
          throw new IllegalArgumentException ("Cannot read file " + aFile);
        if (!aFile.canWrite ())
          s_aLogger.warn ("Cannot write file " + aFile);
        ++nFiles;
      }
      else
        if (aFile.isDirectory ())
        {
          if (!aFile.canRead ())
            throw new IllegalArgumentException ("Cannot read in directory " + aFile);
          if (!aFile.canWrite ())
            s_aLogger.warn ("Cannot write in directory " + aFile);
          if (!aFile.canExecute ())
            s_aLogger.warn ("Cannot execute in directory " + aFile);
          ++nDirs;
        }
        else
          s_aLogger.warn ("Neither file nor directory: " + aFile);
    s_aLogger.info ("Finished checking file access for " +
                    nFiles +
                    " files and " +
                    nDirs +
                    " directories in " +
                    aSW.stopAndGetMillis () +
                    " milliseconds");
  }

  public FileRelativeIO (@Nonnull final File aBasePath, final boolean bCheckAccessRights)
  {
    ValueEnforcer.notNull (aBasePath, "BasePath");
    if (!aBasePath.isAbsolute ())
      throw new IllegalArgumentException ("Please provide an absolute path: " + aBasePath);

    // Ensure the directory is present
    WebFileIO.getFileOpMgr ().createDirRecursiveIfNotExisting (aBasePath);

    // Must be an existing directory (and not e.g. a file)
    if (!aBasePath.isDirectory ())
      throw new InitializationException ("The passed base path " + aBasePath + " exists but is not a directory!");
    m_aBasePath = aBasePath;

    if (bCheckAccessRights)
      internalCheckAccessRights (m_aBasePath);
  }

  @Nonnull
  @OverrideOnDemand
  protected FileOperationManager getFileOperationMgr ()
  {
    return WebFileIO.getFileOpMgr ();
  }

  @Nonnull
  public File getBasePathFile ()
  {
    return m_aBasePath;
  }

  @Nonnull
  public FileIOError createDirectory (@Nonnull final String sRelativePath, final boolean bRecursive)
  {
    final File aDir = getFile (sRelativePath);
    return bRecursive ? getFileOperationMgr ().createDirRecursiveIfNotExisting (aDir)
                      : getFileOperationMgr ().createDirIfNotExisting (aDir);
  }

  @Nonnull
  public FileIOError deleteFile (@Nonnull final File aFile)
  {
    return getFileOperationMgr ().deleteFile (aFile);
  }

  @Nonnull
  public FileIOError deleteFileIfExisting (@Nonnull final File aFile)
  {
    return getFileOperationMgr ().deleteFileIfExisting (aFile);
  }

  @Nonnull
  public FileIOError deleteDirectory (@Nonnull final File fDir, final boolean bDeleteRecursively)
  {
    return bDeleteRecursively ? getFileOperationMgr ().deleteDirRecursive (fDir)
                              : getFileOperationMgr ().deleteDir (fDir);
  }

  @Nonnull
  public FileIOError deleteDirectoryIfExisting (@Nonnull final File fDir, final boolean bDeleteRecursively)
  {
    return bDeleteRecursively ? getFileOperationMgr ().deleteDirRecursiveIfExisting (fDir)
                              : getFileOperationMgr ().deleteDirIfExisting (fDir);
  }

  @Nonnull
  public FileIOError renameFile (@Nonnull final String sOldFilename, @Nonnull final String sNewFilename)
  {
    final File fOld = getFile (sOldFilename);
    final File fNew = getFile (sNewFilename);
    return getFileOperationMgr ().renameFile (fOld, fNew);
  }

  @Nonnull
  public FileIOError renameDir (@Nonnull final String sOldDirName, @Nonnull final String sNewDirName)
  {
    final File fOld = getFile (sOldDirName);
    final File fNew = getFile (sNewDirName);
    return getFileOperationMgr ().renameDir (fOld, fNew);
  }

  /**
   * Helper function for saving a file with correct error handling.
   *
   * @param sFilename
   *        name of the file. May not be <code>null</code>.
   * @param eAppend
   *        Appending mode. May not be <code>null</code>.
   * @param aBytes
   *        the bytes to be written. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  private ESuccess _writeFile (@Nonnull final String sFilename,
                               @Nonnull final EAppend eAppend,
                               @Nonnull final byte [] aBytes)
  {
    // save to file
    final OutputStream aOS = getOutputStream (sFilename, eAppend);
    if (aOS == null)
    {
      s_aLogger.error ("Failed to open output stream for file '" + sFilename + "'");
      return ESuccess.FAILURE;
    }

    // Close the OS automatically!
    return StreamHelper.writeStream (aOS, aBytes);
  }

  @Nonnull
  public ESuccess saveFile (@Nonnull final String sFilename,
                            @Nonnull final String sContent,
                            @Nonnull final Charset aCharset)
  {
    return saveFile (sFilename, sContent.getBytes (aCharset));
  }

  @Nonnull
  public ESuccess saveFile (@Nonnull final String sFilename, @Nonnull final byte [] aBytes)
  {
    return _writeFile (sFilename, EAppend.TRUNCATE, aBytes);
  }

  @Nonnull
  public ESuccess appendFile (@Nonnull final String sFilename,
                              @Nonnull final String sContent,
                              @Nonnull final Charset aCharset)
  {
    return appendFile (sFilename, sContent.getBytes (aCharset));
  }

  @Nonnull
  public ESuccess appendFile (@Nonnull final String sFilename, @Nonnull final byte [] aBytes)
  {
    return _writeFile (sFilename, EAppend.APPEND, aBytes);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final FileRelativeIO rhs = (FileRelativeIO) o;
    return m_aBasePath.equals (rhs.m_aBasePath);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aBasePath).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("BasePath", m_aBasePath).getToString ();
  }
}
