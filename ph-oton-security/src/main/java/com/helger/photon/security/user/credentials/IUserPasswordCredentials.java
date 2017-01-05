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
package com.helger.photon.security.user.credentials;

import javax.annotation.Nullable;

import com.helger.commons.collection.ext.ICommonsCollection;
import com.helger.photon.basic.auth.credentials.IAuthCredentials;
import com.helger.photon.security.user.IUser;

/**
 * This interface represents {@link IUser} and password credentials passed from
 * a login page.
 *
 * @author Philip Helger
 */
public interface IUserPasswordCredentials extends IAuthCredentials
{
  /**
   * @return The user to check. May be <code>null</code>.
   */
  @Nullable
  IUser getUser ();

  /**
   * @return The password to check. May be <code>null</code>.
   */
  @Nullable
  String getPassword ();

  /**
   * @return A collection of required rules for login. May be <code>null</code>
   *         or empty.
   */
  @Nullable
  ICommonsCollection <String> getAllRequiredRoles ();
}
