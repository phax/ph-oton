/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.appbasics.app.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.OverrideOnDemand;
import com.helger.commons.charset.CharsetManager;
import com.helger.commons.exceptions.InitializationException;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.io.EAppend;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.io.file.FileUtils;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.file.iterate.FileSystemRecursiveIterator;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.streams.StreamUtils;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;

/**
 * Default implementation of {@link IPathRelativeIO}.
 *
 * @author Philip Helger
 */
@Immutable
public class PathRelativeFileIO implements IPathRelativeIO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (PathRelativeFileIO.class);

  private final File m_aBasePath;

  private void _checkAccessRights ()
  {
    // Check read/write/execute
    final StopWatch aSW = new StopWatch (true);
    s_aLogger.info ("Checking file access in " + m_aBasePath);
    int nFiles = 0;
    int nDirs = 0;
    for (final File aFile : new FileSystemRecursiveIterator (m_aBasePath))
      if (aFile.isFile ())
      {
        // Check if files are read-write
        if (!FileUtils.canRead (aFile))
          throw new IllegalArgumentException ("Cannot read file " + aFile);
        if (!FileUtils.canWrite (aFile))
          s_aLogger.warn ("Cannot write file " + aFile);
        ++nFiles;
      }
      else
        if (aFile.isDirectory ())
        {
          if (!FileUtils.canRead (aFile))
            throw new IllegalArgumentException ("Cannot read in directory " + aFile);
          if (!FileUtils.canWrite (aFile))
            s_aLogger.warn ("Cannot write in directory " + aFile);
          if (!FileUtils.canExecute (aFile))
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

  public PathRelativeFileIO (@Nonnull final File aBasePath, final boolean bCheckAccessRights)
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
      _checkAccessRights ();
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
  @Nonempty
  public String getBasePath ()
  {
    return getBasePathFile ().getAbsolutePath ();
  }

  @Nonnull
  public File getFile (@Nonnull final String sRelativePath)
  {
    return new File (getBasePathFile (), sRelativePath);
  }

  public boolean existsFile (@Nonnull final String sRelativePath)
  {
    return FileUtils.existsFile (getFile (sRelativePath));
  }

  public boolean existsDir (@Nonnull final String sRelativePath)
  {
    return FileUtils.existsDir (getFile (sRelativePath));
  }

  @Nonnull
  public FileSystemResource getResource (@Nonnull final String sRelativePath)
  {
    return new FileSystemResource (getFile (sRelativePath));
  }

  @Nullable
  public InputStream getInputStream (@Nonnull final String sRelativePath)
  {
    return getResource (sRelativePath).getInputStream ();
  }

  @Nullable
  public Reader getReader (@Nonnull final String sRelativePath, @Nonnull final Charset aCharset)
  {
    return getResource (sRelativePath).getReader (aCharset);
  }

  @Nullable
  public OutputStream getOutputStream (@Nonnull final String sRelativePath)
  {
    return getOutputStream (sRelativePath, EAppend.TRUNCATE);
  }

  @Nullable
  public OutputStream getOutputStream (@Nonnull final String sRelativePath, @Nonnull final EAppend eAppend)
  {
    return getResource (sRelativePath).getOutputStream (eAppend);
  }

  @Nullable
  public Writer getWriter (@Nonnull final String sRelativePath, @Nonnull final Charset aCharset)
  {
    return getWriter (sRelativePath, aCharset, EAppend.TRUNCATE);
  }

  @Nullable
  public Writer getWriter (@Nonnull final String sRelativePath,
                           @Nonnull final Charset aCharset,
                           @Nonnull final EAppend eAppend)
  {
    return getResource (sRelativePath).getWriter (aCharset, eAppend);
  }

  @Nonnull
  public FileIOError createDirectory (@Nonnull final String sRelativePath, final boolean bRecursive)
  {
    final File aDir = getFile (sRelativePath);
    return bRecursive ? getFileOperationMgr ().createDirRecursiveIfNotExisting (aDir)
                     : getFileOperationMgr ().createDirIfNotExisting (aDir);
  }

  @Nonnull
  public FileIOError deleteFile (@Nonnull final String sFilename)
  {
    return deleteFile (getFile (sFilename));
  }

  @Nonnull
  public FileIOError deleteFile (@Nonnull final File aFile)
  {
    return getFileOperationMgr ().deleteFile (aFile);
  }

  @Nonnull
  public FileIOError deleteFileIfExisting (@Nonnull final String sFilename)
  {
    return deleteFileIfExisting (getFile (sFilename));
  }

  @Nonnull
  public FileIOError deleteFileIfExisting (@Nonnull final File aFile)
  {
    return getFileOperationMgr ().deleteFileIfExisting (aFile);
  }

  @Nonnull
  public FileIOError deleteDirectory (@Nonnull final String sDirName, final boolean bDeleteRecursively)
  {
    return deleteDirectory (getFile (sDirName), bDeleteRecursively);
  }

  @Nonnull
  public FileIOError deleteDirectory (@Nonnull final File fDir, final boolean bDeleteRecursively)
  {
    return bDeleteRecursively ? getFileOperationMgr ().deleteDirRecursive (fDir)
                             : getFileOperationMgr ().deleteDir (fDir);
  }

  @Nonnull
  public FileIOError deleteDirectoryIfExisting (@Nonnull final String sDirName, final boolean bDeleteRecursively)
  {
    return deleteDirectoryIfExisting (getFile (sDirName), bDeleteRecursively);
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
    return StreamUtils.writeStream (aOS, aBytes);
  }

  @Nonnull
  public ESuccess saveFile (@Nonnull final String sFilename,
                            @Nonnull final String sContent,
                            @Nonnull final Charset aCharset)
  {
    return saveFile (sFilename, CharsetManager.getAsBytes (sContent, aCharset));
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
    return appendFile (sFilename, CharsetManager.getAsBytes (sContent, aCharset));
  }

  @Nonnull
  public ESuccess appendFile (@Nonnull final String sFilename, @Nonnull final byte [] aBytes)
  {
    return _writeFile (sFilename, EAppend.APPEND, aBytes);
  }

  @Nullable
  public String getRelativeFilename (@Nonnull final File aAbsoluteFile)
  {
    return FilenameHelper.getRelativeToParentDirectory (aAbsoluteFile, m_aBasePath);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PathRelativeFileIO rhs = (PathRelativeFileIO) o;
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
    return new ToStringGenerator (this).append ("basePath", m_aBasePath).toString ();
  }
}
