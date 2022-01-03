/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.url.SimpleURL;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Represents a single web accessible object, that was provided by the user.
 * Think of this as a file descriptor. A {@link IUserDataObject} lies directly
 * within a web application and can be accessed by regular file IO.
 *
 * @author Philip Helger
 */
@MustImplementEqualsAndHashcode
public interface IUserDataObject extends Serializable
{
  /**
   * @return The path of the object, relative to the user data directory. Always
   *         starting with a "/". This method does not contain any server
   *         specific context path!
   */
  @Nonnull
  @Nonempty
  String getPath ();

  /**
   * @return <code>true</code> if this user data object is temporary,
   *         <code>false</code> if it is used in production.
   */
  boolean isTemporary ();

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The path to the user data object as an URL, including the context
   *         path. Always starting with a "/". E.g.
   *         <code>/context/user/file.txt</code> if this object points to
   *         <code>/file.txt</code>.
   */
  @Nonnull
  @Nonempty
  default String getAsURLPath (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return UserDataManager.getURLPath (aRequestScope, this);
  }

  /**
   * @param aRequestScope
   *        The request web scope to be used. Required for cookie-less handling.
   *        May not be <code>null</code>.
   * @return The path to the user data object as an URL, including the context
   *         path. Always starting with a "/". E.g.
   *         <code>/context/user/file.txt</code> if this object points to
   *         <code>/file.txt</code>.
   */
  @Nonnull
  default SimpleURL getAsURL (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    return UserDataManager.getURL (aRequestScope, this);
  }

  /**
   * @return The file system resource underlying this object. Never
   *         <code>null</code> but potentially not existing.
   */
  @Nonnull
  default FileSystemResource getAsResource ()
  {
    return UserDataManager.getResource (this);
  }

  /**
   * Get the File of this UDO object.
   *
   * @return The matching File. No check is performed, whether the file exists
   *         or not!
   */
  @Nonnull
  default File getAsFile ()
  {
    return UserDataManager.getFile (this);
  }

  /**
   * Create a clone of this object but for a different path. This is a utility
   * method to easily "change" the path of a UDO independent of the
   * implementation.
   *
   * @param sPath
   *        The new path to use. May neither be <code>null</code> nor empty.
   * @return A new user data object which has the same temporary state as the
   *         original. An implementation may chose to return <code>this</code>
   *         if the path is identical to the path of this UDO.
   */
  @Nonnull
  IUserDataObject getCloneWithDifferentPath (@Nonnull @Nonempty String sPath);
}
