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
package com.helger.webbasics.userdata;

import java.io.File;
import java.io.Serializable;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.url.SimpleURL;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;

/**
 * Represents a single web accessible object, that was provided by the user.
 * Think of this as a file descriptor. A {@link IUserDataObject} lies directly
 * within a web application and can be accessed by regular file IO.
 *
 * @author Philip Helger
 */
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
  String getAsURLPath (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

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
  SimpleURL getAsURL (@Nonnull IRequestWebScopeWithoutResponse aRequestScope);

  /**
   * @return The file system resource underlying this object. Never
   *         <code>null</code> but potentially not existing.
   */
  @Nonnull
  FileSystemResource getAsResource ();

  /**
   * Get the File of this UDO object.
   *
   * @return The matching File. No check is performed, whether the file exists
   *         or not!
   */
  @Nonnull
  File getAsFile ();

  /**
   * @return <code>true</code> if this user data object is temporary,
   *         <code>false</code> if it is used in production.
   */
  boolean isTemporary ();
}
