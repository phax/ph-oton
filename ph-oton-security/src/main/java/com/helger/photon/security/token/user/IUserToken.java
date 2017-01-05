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
package com.helger.photon.security.token.user;

import javax.annotation.Nonnull;

import com.helger.photon.security.token.object.IObjectWithAccessToken;
import com.helger.photon.security.user.IUser;

/**
 * Base interface for a user token. A user token is always subordinated to an
 * application token and can have roles and additional properties with the
 * derived custom attributes.
 *
 * @author Philip Helger
 */
public interface IUserToken extends IObjectWithAccessToken
{
  /**
   * @return Theuser to which this user token belongs. Never <code>null</code>.
   */
  @Nonnull
  IUser getUser ();
}
