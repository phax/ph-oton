/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.accesstoken;

import java.time.LocalDateTime;

import com.helger.annotation.Nonempty;
import com.helger.datetime.helper.PDTFactory;
import com.helger.photon.security.token.revocation.IRevocationStatus;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * This class represents a single token. It uniquely belongs to a user token but
 * may also be used in other tokens. It consists of a random string token, a
 * not-before date time, an optional not-after date time and a revocation
 * status.
 *
 * @author Philip Helger
 */
public interface IAccessToken
{
  /**
   * The maximum string length of the token string.
   *
   * @since 8.3.7
   */
  int TOKEN_STRING_MAX_LENGTH = 200;

  /**
   * @return The hex-encoded string with the random data.
   */
  @Nonnull
  @Nonempty
  String getTokenString ();

  /**
   * @return The date time before this token is not valid.
   */
  @Nonnull
  LocalDateTime getNotBefore ();

  /**
   * @return The date time after which this token is not valid. May be
   *         <code>null</code> to indicate infinity. If it is not
   *         <code>null</code> it must be &ge; than the not-before date time.
   */
  @Nullable
  LocalDateTime getNotAfter ();

  /**
   * Check if this token is valid now. This method does not consider the
   * revocation status!
   *
   * @return <code>true</code> if the token is valid now. This method does not
   *         consider the revocation status!
   */
  default boolean isValidNow ()
  {
    return isValidAt (PDTFactory.getCurrentLocalDateTime ());
  }

  /**
   * Check if the token is valid at the specified date and time. This method
   * does not consider the revocation status!
   *
   * @param aDT
   *        The date time to check. May not be <code>null</code>.
   * @return <code>true</code> if the token was valid at that point in time,
   *         <code>false</code> otherwise.
   */
  boolean isValidAt (@Nonnull LocalDateTime aDT);

  /**
   * @return The current revocation status of this token. Never
   *         <code>null</code>.
   */
  @Nonnull
  IRevocationStatus getRevocationStatus ();

  /**
   * A short cut for <code>getRevocationStatus ().isRevoked ()</code>.
   *
   * @return <code>true</code> if this access token was revoked,
   *         <code>false</code> otherwise.
   * @see #getRevocationStatus()
   */
  default boolean isRevoked ()
  {
    return getRevocationStatus ().isRevoked ();
  }
}
