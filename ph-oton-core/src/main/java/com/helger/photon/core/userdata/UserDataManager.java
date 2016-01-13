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
package com.helger.photon.core.userdata;

import java.io.File;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.photon.basic.app.io.IPathRelativeIO;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.core.url.LinkHelper;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Manager for {@link IUserDataObject} objects.
 *
 * @author Philip Helger
 */
public final class UserDataManager
{
  /**
   * The default user data path, relative to a URL context and the servlet
   * context directory.
   */
  public static final String DEFAULT_USER_DATA_PATH = "/user";
  /** By default the user data is accessed via the servletContext IO */
  public static final boolean DEFAULT_SERVLET_CONTEXT_IO = true;

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  private static String s_sUserDataPath = DEFAULT_USER_DATA_PATH;
  private static boolean s_bServletContextIO = DEFAULT_SERVLET_CONTEXT_IO;

  private UserDataManager ()
  {}

  /**
   * Set the user data path, relative to the URL context and relative to the
   * servlet context directory.
   *
   * @param sUserDataPath
   *        The path to be set. May neither be <code>null</code> nor empty and
   *        must start with a "/" character.
   */
  public static void setUserDataPath (@Nonnull @Nonempty final String sUserDataPath)
  {
    if (StringHelper.getLength (sUserDataPath) < 2)
      throw new IllegalArgumentException ("userDataPath is too short");
    if (!sUserDataPath.startsWith ("/"))
      throw new IllegalArgumentException ("userDataPath must start with a slash");

    s_aRWLock.writeLocked ( () -> {
      s_sUserDataPath = sUserDataPath;
    });
  }

  /**
   * Get the base path, where all user objects reside. It is relative to the URL
   * context and relative to the servlet context directory.
   *
   * @return The current user data path. Always starting with a "/", but without
   *         any context information. By default the return value is
   *         {@value #DEFAULT_USER_DATA_PATH}.
   */
  @Nonnull
  @Nonempty
  public static String getUserDataPath ()
  {
    return s_aRWLock.readLocked ( () -> s_sUserDataPath);
  }

  /**
   * Determine whether file resources are located relative to the servlet
   * context (inside the web application) or inside the data directory (outside
   * the web application). By default the files reside inside the web
   * application.
   *
   * @param bServletContextIO
   *        <code>true</code> to use servlet context IO, <code>false</code> to
   *        use data IO.
   */
  public static void setServletContextIO (final boolean bServletContextIO)
  {
    s_aRWLock.writeLocked ( () -> {
      s_bServletContextIO = bServletContextIO;
    });
  }

  public static boolean isServletContextIO ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bServletContextIO;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return Context and user data path. Always starting with a "/". E.g.
   *         <code>/user</code> or <code>/context/user</code>
   */
  @Nonnull
  @Nonempty
  public static String getContextAndUserDataPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return LinkHelper.getURIWithContext (aRequestScope, getUserDataPath ());
  }

  /**
   * Get the URL to the passed UDO object.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param aUDO
   *        The UDO object to get the URL from.
   * @return The path to the user data object as an URL, including the context
   *         path. Always starting with a "/". E.g.
   *         <code>/context/user/file.txt</code> if this object points to
   *         <code>/file.txt</code> and the user data path is <code>/user</code>
   *         .
   */
  @Nonnull
  @Nonempty
  public static String getURLPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                   @Nonnull final IUserDataObject aUDO)
  {
    ValueEnforcer.notNull (aUDO, "UDO");

    return LinkHelper.getURIWithContext (aRequestScope, getUserDataPath () + aUDO.getPath ());
  }

  /**
   * Get the URL to the passed UDO object.
   *
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @param aUDO
   *        The UDO object to get the URL from.
   * @return The URL to the user data object, including the context path. Always
   *         starting with a "/". E.g. <code>/context/user/file.txt</code> if
   *         this object points to <code>/file.txt</code> and the user data path
   *         is <code>/user</code> .
   */
  @Nonnull
  @Nonempty
  public static SimpleURL getURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                  @Nonnull final IUserDataObject aUDO)
  {
    return new SimpleURL (getURLPath (aRequestScope, aUDO));
  }

  @Nonnull
  private static IPathRelativeIO _getFileIO ()
  {
    return isServletContextIO () ? WebFileIO.getServletContextIO () : WebFileIO.getDataIO ();
  }

  /**
   * @return The file system base path for UDOs.
   */
  @Nonnull
  public static File getBasePathFile ()
  {
    return _getFileIO ().getBasePathFile ();
  }

  /**
   * Get the file system resource of the passed UDO object.
   *
   * @param aUDO
   *        The UDO object to get the resource from.
   * @return The matching file system resource. No check is performed, whether
   *         the resource exists or not!
   */
  @Nonnull
  public static FileSystemResource getResource (@Nonnull final IUserDataObject aUDO)
  {
    ValueEnforcer.notNull (aUDO, "UDO");

    return _getFileIO ().getResource (getUserDataPath () + aUDO.getPath ());
  }

  /**
   * Get the File of the passed UDO object.
   *
   * @param aUDO
   *        The UDO object to get the resource from.
   * @return The matching File. No check is performed, whether the file exists
   *         or not!
   */
  @Nonnull
  public static File getFile (@Nonnull final IUserDataObject aUDO)
  {
    ValueEnforcer.notNull (aUDO, "UDO");

    return _getFileIO ().getFile (getUserDataPath () + aUDO.getPath ());
  }
}
