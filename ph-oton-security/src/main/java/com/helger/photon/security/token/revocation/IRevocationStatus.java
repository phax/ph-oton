package com.helger.photon.security.token.revocation;

import java.io.Serializable;

import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

/**
 * Read-only interface for the revocation status.
 *
 * @author Philip Helger
 */
public interface IRevocationStatus extends Serializable
{
  /**
   * @return <code>true</code> if this object is revoked, <code>false</code>
   *         otherwise.
   */
  boolean isRevoked ();

  /**
   * @return The ID of the user who revoked the owning object. Must be non-
   *         <code>null</code> and non-empty if this object is revoked.
   */
  @Nullable
  String getRevocationUserID ();

  /**
   * @return The date time when the owning object was revoked. Must be non-
   *         <code>null</code> if this object is revoked.
   */
  @Nullable
  LocalDateTime getRevocationDateTime ();

  /**
   * @return The reason why the owning object was revoked. Must be non-
   *         <code>null</code> and non-empty if this object is revoked.
   */
  @Nullable
  String getRevocationReason ();
}
