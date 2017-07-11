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
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.io.file.FileOperationManager;
import com.helger.commons.io.file.LoggingFileOperationCallback;

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
  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static FileOperationManager s_aFileOpMgr = new FileOperationManager (new LoggingFileOperationCallback ());
  @GuardedBy ("s_aRWLock")
  private static IMutableFileRelativeIO s_aDataPath;
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

    s_aRWLock.writeLocked ( () -> s_aFileOpMgr = aFileOpMgr);
  }

  /**
   * @return The global file operation manager. Never <code>null</code>.
   */
  @Nonnull
  public static FileOperationManager getFileOpMgr ()
  {
    return s_aRWLock.readLocked ( () -> s_aFileOpMgr);
  }

  public static void initPaths (@Nonnull final File aDataPath,
                                @Nonnull @Nonempty final String sServletContextPath,
                                final boolean bCheckFileAccess)
  {
    ValueEnforcer.notNull (aDataPath, "DataPath");
    ValueEnforcer.notEmpty (sServletContextPath, "ServletContextPath");

    s_aRWLock.writeLocked ( () -> {
      if (s_aDataPath != null)
        throw new IllegalStateException ("Another data path is already present: " + s_aDataPath);
      if (s_aServletContextPath != null)
        throw new IllegalStateException ("Another servlet context path is already present: " + s_aServletContextPath);

      s_aLogger.info ("Using '" + aDataPath + "' as the data path");
      s_aDataPath = new FileRelativeIO (aDataPath, bCheckFileAccess);

      s_aLogger.info ("Using '" + sServletContextPath + "' as the servlet context path");
      s_aServletContextPath = new PathRelativeIO (sServletContextPath);
    });
  }

  /**
   * Reset the base paths - no matter if they were initialized or not.
   */
  public static void resetPaths ()
  {
    s_aRWLock.writeLocked ( () -> {
      s_aDataPath = null;
      s_aServletContextPath = null;
    });
  }

  /**
   * @return <code>true</code> if the base path was initialized,
   *         <code>false</code> otherwise
   */
  public static boolean isInited ()
  {
    return s_aRWLock.readLocked ( () -> s_aDataPath != null);
  }

  /**
   * @return data IO provider.
   * @throws IllegalStateException
   *         if no data path was provided. Call
   *         {@link #initPaths(File, String, boolean)} first.
   */
  @Nonnull
  public static IMutableFileRelativeIO getDataIO ()
  {
    final IMutableFileRelativeIO ret = s_aRWLock.readLocked ( () -> s_aDataPath);
    if (ret == null)
      throw new IllegalStateException ("Data path was not initialized!");
    return ret;
  }

  /**
   * @return The servlet context IO provider. This is read-only because it makes
   *         no sense to modify the contents of the servlet context directory.
   * @throws IllegalStateException
   *         if no servlet context path was provided. Call
   *         {@link #initPaths(File, String, boolean)} first.
   */
  @Nonnull
  public static IPathRelativeIO getServletContextIO ()
  {
    final IPathRelativeIO ret = s_aRWLock.readLocked ( () -> s_aServletContextPath);
    if (ret == null)
      throw new IllegalStateException ("Servlet context path was not initialized!");
    return ret;
  }
}
