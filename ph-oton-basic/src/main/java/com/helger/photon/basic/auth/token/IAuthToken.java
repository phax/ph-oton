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
package com.helger.photon.basic.auth.token;

import java.io.Serializable;

import javax.annotation.Nonnull;

import org.joda.time.LocalDateTime;

import com.helger.commons.id.IHasID;
import com.helger.photon.basic.auth.identify.IAuthIdentification;

/**
 * Interface for an auth token.
 *
 * @author Philip Helger
 */
public interface IAuthToken extends IHasID <String>, Serializable
{
  /**
   * @return The secret key token representing a session of a subject. Never
   *         <code>null</code>.
   */
  @Nonnull
  String getID ();

  /**
   * @return The underlying identification object. Never <code>null</code>.
   */
  @Nonnull
  IAuthIdentification getIdentification ();

  /**
   * @return The date and time when the token was created. Never
   *         <code>null</code>.
   */
  @Nonnull
  LocalDateTime getCreationDate ();

  /**
   * @return The date and time when the token was last accessed. If the token
   *         was never accessed before, the creation date time is returned.
   *         Never <code>null</code>.
   */
  @Nonnull
  LocalDateTime getLastAccessDate ();

  /**
   * Check if the token is expired. Expired tokens are considered invalid.
   *
   * @return <code>true</code> if the token is already expired.
   */
  boolean isExpired ();
}
