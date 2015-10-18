package com.helger.photon.security.token.accesstoken;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.security.token.revocation.IRevocationStatus;

/**
 * This class represents a single token. It uniquely belongs to an application
 * token or a user token. It consists of a random string token, a not-before
 * date time, an optional not-after date time and a revocation status.
 *
 * @author Philip Helger
 */
public interface IAccessToken extends Serializable
{
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
  boolean isValidNow ();

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
  boolean isRevoked ();
}
