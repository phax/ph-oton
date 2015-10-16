package com.helger.photon.security.token.accesstoken;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.random.VerySecureRandom;
import com.helger.commons.string.StringHelper;
import com.helger.datetime.PDTFactory;
import com.helger.photon.security.token.revocation.RevocationStatus;

/**
 * This class represents a single token. It uniquely belongs to an application
 * token or a user token. It consists of a random string token, a not-before
 * date time and an optional not-after date time.
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

  private AccessToken (@Nonnull @Nonempty final String sTokenString,
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

  public void setNotAfterNow ()
  {
    setNotAfter (PDTFactory.getCurrentLocalDateTime ());
  }

  public void setNotAfter (@Nonnull final LocalDateTime aNotAfter)
  {
    ValueEnforcer.notNull (aNotAfter, "NotAfter");
    if (aNotAfter.isBefore (m_aNotBefore))
      throw new IllegalArgumentException ("Not after date (" + aNotAfter + ") must be >= not before date (" + m_aNotBefore + ")");
    m_aNotAfter = aNotAfter;
  }

  public boolean isValidNow ()
  {
    return isValidAt (PDTFactory.getCurrentLocalDateTime ());
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

  public boolean isRevoked ()
  {
    return m_aRevocationStatus.isRevoked ();
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
    VerySecureRandom.getInstance ().nextBytes (aBytes);
    // Returns a 128 byte string
    return StringHelper.getHexEncoded (aBytes);
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
    return new AccessToken (createNewTokenString (64), PDTFactory.getCurrentLocalDateTime (), null, new RevocationStatus ());
  }
}
