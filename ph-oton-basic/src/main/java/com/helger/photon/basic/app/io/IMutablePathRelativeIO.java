/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import java.io.Writer;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.io.EAppend;
import com.helger.commons.io.file.FileIOError;
import com.helger.commons.state.ESuccess;

/**
 * The mutable version of the {@link IPathRelativeIO} interface. It extends the
 * base interface with writable methods relative to a certain directory.
 *
 * @author Philip Helger
 */
public interface IMutablePathRelativeIO extends IPathRelativeIO
{
  /**
   * Get the {@link OutputStream} relative to the base path. An eventually
   * existing file is truncated.
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>null</code> if the path is not writable
   * @see #getBasePathFile()
   */
  @Nullable
  OutputStream getOutputStream (@Nonnull String sRelativePath);

  /**
   * Get the {@link OutputStream} relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @param eAppend
   *        Append or truncate mode. May not be <code>null</code>.
   * @return <code>null</code> if the path is not writable
   * @see #getBasePathFile()
   */
  @Nullable
  OutputStream getOutputStream (@Nonnull String sRelativePath, @Nonnull EAppend eAppend);

  /**
   * Get the {@link Writer} relative to the base path. An eventually existing
   * file is truncated.
   *
   * @param sRelativePath
   *        the relative path
   * @param aCharset
   *        The charset to use. May not be <code>null</code>.
   * @return <code>null</code> if the path is not writable
   * @see #getBasePathFile()
   */
  @Nullable
  Writer getWriter (@Nonnull String sRelativePath, @Nonnull Charset aCharset);

  /**
   * Get the {@link Writer} relative to the base path.
   *
   * @param sRelativePath
   *        the relative path
   * @param aCharset
   *        The charset to use. May not be <code>null</code>.
   * @param eAppend
   *        Append or truncate mode. May not be <code>null</code>.
   * @return <code>null</code> if the path is not writable
   * @see #getBasePathFile()
   */
  @Nullable
  Writer getWriter (@Nonnull String sRelativePath, @Nonnull Charset aCharset, @Nonnull EAppend eAppend);

  /**
   * Create the appropriate directory if it is not existing
   *
   * @param sRelativePath
   *        the relative path
   * @param bRecursive
   *        if <code>true</code> all missing parent directories will be created
   * @return Success indicator. Never <code>null</code>.
   * @see #getBasePathFile()
   */
  @Nonnull
  FileIOError createDirectory (@Nonnull String sRelativePath, boolean bRecursive);

  @Nonnull
  FileIOError deleteFile (@Nonnull String sFilename);

  @Nonnull
  FileIOError deleteFile (@Nonnull File aFile);

  @Nonnull
  FileIOError deleteFileIfExisting (@Nonnull String sFilename);

  @Nonnull
  FileIOError deleteFileIfExisting (@Nonnull File aFile);

  @Nonnull
  FileIOError deleteDirectory (@Nonnull String sDirName, boolean bDeleteRecursively);

  @Nonnull
  FileIOError deleteDirectory (@Nonnull File fDir, boolean bDeleteRecursively);

  @Nonnull
  FileIOError deleteDirectoryIfExisting (@Nonnull String sDirName, boolean bDeleteRecursively);

  @Nonnull
  FileIOError deleteDirectoryIfExisting (@Nonnull File fDir, boolean bDeleteRecursively);

  @Nonnull
  FileIOError renameFile (@Nonnull String sOldFilename, @Nonnull String sNewFilename);

  @Nonnull
  FileIOError renameDir (@Nonnull String sOldDirName, @Nonnull String sNewDirName);

  /**
   * Helper function for saving a file with correct error handling.
   *
   * @param sFilename
   *        name of the file. May not be <code>null</code>.
   * @param sContent
   *        the content to save. May not be <code>null</code>.
   * @param aCharset
   *        The character set to use. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess saveFile (@Nonnull String sFilename, @Nonnull String sContent, @Nonnull Charset aCharset);

  /**
   * Helper function for saving a file with correct error handling.
   *
   * @param sFilename
   *        name of the file. May not be <code>null</code>.
   * @param aBytes
   *        the bytes to be written. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess saveFile (@Nonnull String sFilename, byte [] aBytes);

  /**
   * Helper function for saving a file with correct error handling.
   *
   * @param sFilename
   *        name of the file. May not be <code>null</code>.
   * @param sContent
   *        the content to save. May not be <code>null</code>.
   * @param aCharset
   *        The character set to use. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess appendFile (@Nonnull String sFilename, @Nonnull String sContent, @Nonnull Charset aCharset);

  /**
   * Helper function for saving a file with correct error handling.
   *
   * @param sFilename
   *        name of the file. May not be <code>null</code>.
   * @param aBytes
   *        the bytes to be written. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  ESuccess appendFile (@Nonnull String sFilename, @Nonnull byte [] aBytes);
}
