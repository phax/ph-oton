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
package com.helger.photon.basic.app.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.io.EAppend;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.io.file.LoggingFileOperationCallback;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.state.ISuccessIndicator;

/**
 * Abstract for accessing files inside the web application. It differentiates
 * between the data path (where the runtime data is stored) and the servlet
 * context path (for accessing files inside the web application). The default
 * operations in this class work on the data path!
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class WebFileIO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (WebFileIO.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static FileOperationManager s_aFileOpMgr = new FileOperationManager (new LoggingFileOperationCallback ());
  @GuardedBy ("s_aRWLock")
  private static IPathRelativeIO s_aDataPath;
  @GuardedBy ("s_aRWLock")
  private static IPathRelativeIO s_aServletContextPath;

  private WebFileIO ()
  {}

  /**
   * Set the global file operation manager to be used.
   *
   * @param aFileOpMgr
   *        The file operation manager. May not be <code>null</code>.
   */
  public static void setFileOpMgr (@Nonnull final FileOperationManager aFileOpMgr)
  {
    ValueEnforcer.notNull (aFileOpMgr, "FileOpMgr");

    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aFileOpMgr = aFileOpMgr;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return The global file operation manager. Never <code>null</code>.
   */
  @Nonnull
  public static FileOperationManager getFileOpMgr ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aFileOpMgr;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void initPaths (@Nonnull final File aDataPath,
                                @Nonnull final File aServletContextPath,
                                final boolean bCheckFileAccess)
  {
    ValueEnforcer.notNull (aDataPath, "DataPath");
    ValueEnforcer.notNull (aServletContextPath, "ServletContextPath");

    s_aRWLock.writeLock ().lock ();
    try
    {
      if (s_aDataPath != null)
        throw new IllegalStateException ("Another data path is already present: " + s_aDataPath);
      if (s_aServletContextPath != null)
        throw new IllegalStateException ("Another servlet context path is already present: " + s_aServletContextPath);

      s_aLogger.info ("Using '" + aDataPath + "' as the data path");
      s_aDataPath = new PathRelativeFileIO (aDataPath, bCheckFileAccess);

      s_aLogger.info ("Using '" + aServletContextPath + "' as the servlet context path");
      // Don't check access rights again, if it equals the data path
      s_aServletContextPath = new PathRelativeFileIO (aServletContextPath, bCheckFileAccess &&
                                                                           !aServletContextPath.equals (aDataPath));
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * Reset the base paths - no matter if they were initialized or not.
   */
  public static void resetPaths ()
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_aDataPath = null;
      s_aServletContextPath = null;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return <code>true</code> if the base path was initialized,
   *         <code>false</code> otherwise
   */
  public static boolean isInited ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_aDataPath != null;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return data IO provider.
   * @throws IllegalStateException
   *         if no servlet context path was provided
   */
  @Nonnull
  public static IPathRelativeIO getDataIO ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      if (s_aDataPath == null)
        throw new IllegalStateException ("Data path was not initialized!");
      return s_aDataPath;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The servlet context IO provider.
   * @throws IllegalStateException
   *         if no servlet context path was provided
   */
  @Nonnull
  public static IPathRelativeIO getServletContextIO ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      if (s_aServletContextPath == null)
        throw new IllegalStateException ("Servlet context path was not initialized!");
      return s_aServletContextPath;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The storage base path.
   * @throws IllegalStateException
   *         if no base path was provided
   */
  @Nonnull
  public static File getBasePathFile ()
  {
    return getDataIO ().getBasePathFile ();
  }

  /**
   * @return The absolute base path that is used
   * @throws IllegalStateException
   *         if no base path was provided
   */
  @Nonnull
  @Nonempty
  public static String getBasePath ()
  {
    return getDataIO ().getBasePath ();
  }

  /**
   * Get a {@link File} relative to the base path.
   *
   * @param sRelativePath
   *        the relative path
   * @return The "absolute" {@link File} and never <code>null</code>.
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  @Nonnull
  public static File getFile (@Nonnull final String sRelativePath)
  {
    return getDataIO ().getFile (sRelativePath);
  }

  /**
   * Check if a file relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a file and exists,
   *         <code>false</code> otherwise.
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  public static boolean existsFile (@Nonnull final String sRelativePath)
  {
    return getDataIO ().existsFile (sRelativePath);
  }

  /**
   * Check if a directory relative to the base path exists
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>true</code> if the {@link File} is a directory and exists,
   *         <code>false</code> otherwise.
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  public static boolean existsDir (@Nonnull final String sRelativePath)
  {
    return getDataIO ().existsDir (sRelativePath);
  }

  /**
   * Get the file system resource relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @return The "absolute" {@link FileSystemResource} and never
   *         <code>null</code>.
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  @Nonnull
  public static FileSystemResource getResource (@Nonnull final String sRelativePath)
  {
    return getDataIO ().getResource (sRelativePath);
  }

  /**
   * Get the {@link InputStream} relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @return <code>null</code> if the path does not exist
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  @Nullable
  public static InputStream getInputStream (@Nonnull final String sRelativePath)
  {
    return getDataIO ().getInputStream (sRelativePath);
  }

  /**
   * Get the {@link Reader} relative to the base path
   *
   * @param sRelativePath
   *        the relative path
   * @param aCharset
   *        The charset to use. May not be <code>null</code>.
   * @return <code>null</code> if the path does not exist
   * @throws IllegalStateException
   *         if no base path was provided
   * @see #getBasePathFile()
   */
  @Nullable
  public static Reader getReader (@Nonnull final String sRelativePath, @Nonnull final Charset aCharset)
  {
    return getDataIO ().getReader (sRelativePath, aCharset);
  }

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
  public static OutputStream getOutputStream (@Nonnull final String sRelativePath, @Nonnull final EAppend eAppend)
  {
    return getDataIO ().getOutputStream (sRelativePath, eAppend);
  }

  /**
   * Get the {@link Writer} relative to the base path
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
  public static Writer getWriter (@Nonnull final String sRelativePath,
                                  @Nonnull final Charset aCharset,
                                  @Nonnull final EAppend eAppend)
  {
    return getDataIO ().getWriter (sRelativePath, aCharset, eAppend);
  }

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
  public static ISuccessIndicator createDirectory (@Nonnull final String sRelativePath, final boolean bRecursive)
  {
    return getDataIO ().createDirectory (sRelativePath, bRecursive);
  }

  /**
   * Get the relative file name for the passed absolute file.
   *
   * @param aAbsoluteFile
   *        The non-<code>null</code> absolute file to make relative.
   * @return <code>null</code> if the passed file is not a child of the data
   *         directory.
   */
  @Nullable
  public static String getRelativeFilename (@Nonnull final File aAbsoluteFile)
  {
    return getDataIO ().getRelativeFilename (aAbsoluteFile);
  }
}
