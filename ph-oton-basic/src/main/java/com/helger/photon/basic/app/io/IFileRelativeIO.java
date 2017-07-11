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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.file.FileHelper;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.io.resource.FileSystemResource;

/**
 * An extended {@link IPathRelativeIO} version that uses files as the basis.
 *
 * @author Philip Helger
 * @since 7.1.1
 */
public interface IFileRelativeIO extends IPathRelativeIO
{
  /**
   * @return The base path. Never <code>null</code>.
   */
  @Nonnull
  File getBasePathFile ();

  /**
   * @return The absolute base path that is used. Neither <code>null</code> nor
   *         empty.
   */
  @Nonnull
  @Nonempty
  default String getBasePath ()
  {
    return getBasePathFile ().getAbsolutePath ();
  }

  /**
   * Get a {@link File} relative to the base path.
   *
   * @param sRelativePath
   *        the relative path
   * @return The "absolute" {@link File} and never <code>null</code>.
   * @see #getBasePathFile()
   */
  @Nonnull
  default File getFile (@Nonnull final String sRelativePath)
  {
    return new File (getBasePathFile (), sRelativePath);
  }

  /**
   * Check if a file relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a file and exists,
   *         <code>false</code> otherwise.
   * @see #getBasePathFile()
   */
  default boolean existsFile (@Nonnull final String sRelativePath)
  {
    return FileHelper.existsFile (getFile (sRelativePath));
  }

  /**
   * Check if a directory relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a directory and exists,
   *         <code>false</code> otherwise.
   * @see #getBasePathFile()
   */
  default boolean existsDir (@Nonnull final String sRelativePath)
  {
    return FileHelper.existsDir (getFile (sRelativePath));
  }

  /**
   * Get the file system resource relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @return The "absolute" {@link FileSystemResource} and never
   *         <code>null</code>.
   * @see #getBasePathFile()
   */
  @Nonnull
  default FileSystemResource getResource (@Nonnull final String sRelativePath)
  {
    return new FileSystemResource (getFile (sRelativePath));
  }

  /**
   * Get the relative file name for the passed absolute file.
   *
   * @param aAbsoluteFile
   *        The non-<code>null</code> absolute file to make relative.
   * @return <code>null</code> if the passed file is not a child of this base
   *         directory.
   */
  @Nullable
  default String getRelativeFilename (@Nonnull final File aAbsoluteFile)
  {
    return FilenameHelper.getRelativeToParentDirectory (aAbsoluteFile, getBasePathFile ());
  }
}
