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
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.FileSystemResource;

/**
 * A read-only version of a path-relative IO component. For a version with
 * explicitly modifying methods see {@link IMutablePathRelativeIO}.
 *
 * @author Philip Helger
 */
public interface IPathRelativeIO extends Serializable
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
  String getBasePath ();

  /**
   * Get a {@link File} relative to the base path.
   *
   * @param sRelativePath
   *        the relative path
   * @return The "absolute" {@link File} and never <code>null</code>.
   * @see #getBasePathFile()
   */
  @Nonnull
  File getFile (@Nonnull String sRelativePath);

  /**
   * Check if a file relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a file and exists,
   *         <code>false</code> otherwise.
   * @see #getBasePathFile()
   */
  boolean existsFile (@Nonnull String sRelativePath);

  /**
   * Check if a directory relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a directory and exists,
   *         <code>false</code> otherwise.
   * @see #getBasePathFile()
   */
  boolean existsDir (@Nonnull String sRelativePath);

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
  FileSystemResource getResource (@Nonnull String sRelativePath);

  /**
   * Get the {@link InputStream} relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>null</code> if the path does not exist
   * @see #getBasePathFile()
   */
  @Nullable
  InputStream getInputStream (@Nonnull String sRelativePath);

  /**
   * Get the {@link Reader} relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @param aCharset
   *        The charset to use. May not be <code>null</code>.
   * @return <code>null</code> if the path does not exist
   * @see #getBasePathFile()
   */
  @Nullable
  Reader getReader (@Nonnull String sRelativePath, @Nonnull Charset aCharset);

  /**
   * Get the relative file name for the passed absolute file.
   *
   * @param aAbsoluteFile
   *        The non-<code>null</code> absolute file to make relative.
   * @return <code>null</code> if the passed file is not a child of this base
   *         directory.
   */
  @Nullable
  String getRelativeFilename (@Nonnull File aAbsoluteFile);
}
