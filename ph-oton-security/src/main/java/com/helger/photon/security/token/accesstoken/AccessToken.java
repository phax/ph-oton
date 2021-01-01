/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import java.util.Random;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.base64.Base64;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.string.StringHelper;
import com.helger.photon.security.token.revocation.RevocationStatus;

/**
 * This class represents a single token. It uniquely belongs to an application
 * token or a user token. It consists of a random string token, a not-before
 * date time and an optional not-after date time. Additionally it contains a
 * revocation status.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class AccessToken implements IAccessToken
{
  private final String m_sTokenString;
  private final LocalDateTime m_aNotBefore;
  private LocalDateTime m_aNotAfter;
  private final RevocationStatus m_aRevocationStatus;

  AccessToken (@Nonnull @Nonempty final String sTokenString,
               @Nonnull final LocalDateTime aNotBefore,
               @Nullable final LocalDateTime aNotAfter,
               @Nonnull final RevocationStatus aRevocationStatus)
  {
    m_sTokenString = ValueEnforcer.notEmpty (sTokenString, "TokenString");
    m_aNotBefore = ValueEnforcer.notNull (aNotBefore, "NotBefore");
    if (aNotAfter != null)
      setNotAfter (aNotAfter);
    m_aRevocationStatus = ValueEnforcer.notNull (aRevocationStatus, "RevocationStatus");
  }

  @Nonnull
  @Nonempty
  public String getTokenString ()
  {
    return m_sTokenString;
  }

  @Nonnull
  public LocalDateTime getNotBefore ()
  {
    return m_aNotBefore;
  }

  @Nullable
  public LocalDateTime getNotAfter ()
  {
    return m_aNotAfter;
  }

  public void setNotAfter (@Nonnull final LocalDateTime aNotAfter)
  {
    ValueEnforcer.notNull (aNotAfter, "NotAfter");
    if (aNotAfter.isBefore (m_aNotBefore))
      throw new IllegalArgumentException ("Not after date (" + aNotAfter + ") must be >= not before date (" + m_aNotBefore + ")");
    m_aNotAfter = aNotAfter;
  }

  public boolean isValidAt (@Nonnull final LocalDateTime aDT)
  {
    ValueEnforcer.notNull (aDT, "DateTime");
    if (aDT.isBefore (m_aNotBefore))
      return false;
    if (m_aNotAfter != null && aDT.isAfter (m_aNotAfter))
      return false;
    return true;
  }

  @Nonnull
  public RevocationStatus getRevocationStatus ()
  {
    return m_aRevocationStatus;
  }

  public void markRevoked (@Nonnull @Nonempty final String sRevocationUserID,
                           @Nonnull final LocalDateTime aRevocationDT,
                           @Nonnull @Nonempty final String sRevocationReason)
  {
    m_aRevocationStatus.markRevoked (sRevocationUserID, aRevocationDT, sRevocationReason);
  }

  /**
   * @param nBytes
   *        The number of bytes to be used for the token. Must be &gt; 0.
   *        Suggested value is 64.
   * @return A newly created random token of x bytes as a hex-encoded String.
   *         Never <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public static String createNewTokenString (@Nonnegative final int nBytes)
  {
    final byte [] aBytes = new byte [nBytes];
    new Random ().nextBytes (aBytes);
    // Returns a +33% longer byte string
    return Base64.encodeBytes (aBytes);
  }

  /**
   * Create a new access token that is valid from now on for an infinite amount
   * of time.
   *
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static AccessToken createNewAccessTokenValidFromNow ()
  {
    return createAccessTokenValidFromNow (null);
  }

  /**
   * Create a new access token that is valid from now on for an infinite amount
   * of time.
   *
   * @param sTokenString
   *        The existing token string. May be <code>null</code> in which case a
   *        new token string is created.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static AccessToken createAccessTokenValidFromNow (@Nullable final String sTokenString)
  {
    // Length 66 so that the Base64 encoding does not add the "==" signs
    // Length must be dividable by 3
    final String sRealTokenString = StringHelper.hasText (sTokenString) ? sTokenString : createNewTokenString (66);
    return new AccessToken (sRealTokenString, PDTFactory.getCurrentLocalDateTime (), null, new RevocationStatus ());
  }
}
