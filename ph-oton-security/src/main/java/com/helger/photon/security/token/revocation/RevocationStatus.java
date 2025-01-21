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
package com.helger.photon.security.token.revocation;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * The default implementation of {@link IRevocationStatus}.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class RevocationStatus implements IRevocationStatus
{
  private boolean m_bRevoked;
  private String m_sRevocationUserID;
  private LocalDateTime m_aRevocationDT;
  private String m_sRevocationReason;

  /**
   * Constructor for deserialization only. Use {@link #createUnrevoked()}
   * instead.
   *
   * @param bRevoked
   *        <code>true</code> if it is revoked, <code>false</code> if not
   * @param sRevocationUserID
   *        The user ID that revoked the object. May be <code>null</code>.
   * @param aRevocationDT
   *        The date time when the revocation took place. May be
   *        <code>null</code>.
   * @param sRevocationReason
   *        The reason for the revocation. May be <code>null</code>.
   */
  public RevocationStatus (final boolean bRevoked,
                           @Nullable final String sRevocationUserID,
                           @Nullable final LocalDateTime aRevocationDT,
                           @Nullable final String sRevocationReason)
  {
    if (bRevoked)
    {
      ValueEnforcer.notEmpty (sRevocationUserID, "RevocationUserID");
      ValueEnforcer.notNull (aRevocationDT, "RevocationDT");
      ValueEnforcer.notEmpty (sRevocationReason, "RevocationReason");
    }
    m_bRevoked = bRevoked;
    m_sRevocationUserID = sRevocationUserID;
    m_aRevocationDT = aRevocationDT;
    m_sRevocationReason = sRevocationReason;
  }

  public final boolean isRevoked ()
  {
    return m_bRevoked;
  }

  @Nullable
  public final String getRevocationUserID ()
  {
    return m_sRevocationUserID;
  }

  @Nullable
  public final LocalDateTime getRevocationDateTime ()
  {
    return m_aRevocationDT;
  }

  @Nullable
  public final String getRevocationReason ()
  {
    return m_sRevocationReason;
  }

  /**
   * Mark the owning item as revoked.
   *
   * @param sRevocationUserID
   *        The ID of the user who revoked it. May neither be <code>null</code>
   *        nor empty.
   * @param aRevocationDT
   *        The date and time when the revocation took place. May not be
   *        <code>null</code>.
   * @param sRevocationReason
   *        A human readable reason why revocation took place. May neither be
   *        <code>null</code> nor empty.
   * @throws IllegalStateException
   *         If this status already denotes a revoked object.
   */
  public void markRevoked (@Nonnull @Nonempty final String sRevocationUserID,
                           @Nonnull final LocalDateTime aRevocationDT,
                           @Nonnull @Nonempty final String sRevocationReason)
  {
    ValueEnforcer.notEmpty (sRevocationUserID, "RevocationUserID");
    ValueEnforcer.notNull (aRevocationDT, "RevocationDT");
    ValueEnforcer.notEmpty (sRevocationReason, "RevocationReason");
    if (m_bRevoked)
      throw new IllegalStateException ("This object is already revoked!");
    m_bRevoked = true;
    m_sRevocationUserID = sRevocationUserID;
    m_aRevocationDT = aRevocationDT;
    m_sRevocationReason = sRevocationReason;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final RevocationStatus rhs = (RevocationStatus) o;
    return m_bRevoked == rhs.m_bRevoked &&
           EqualsHelper.equals (m_sRevocationUserID, rhs.m_sRevocationUserID) &&
           EqualsHelper.equals (m_aRevocationDT, rhs.m_aRevocationDT) &&
           EqualsHelper.equals (m_sRevocationReason, rhs.m_sRevocationReason);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_bRevoked)
                                       .append (m_sRevocationUserID)
                                       .append (m_aRevocationDT)
                                       .append (m_sRevocationReason)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("revoked", m_bRevoked)
                                       .appendIfNotNull ("RevocationUserID", m_sRevocationUserID)
                                       .appendIfNotNull ("RevocationDT", m_aRevocationDT)
                                       .appendIfNotNull ("RevocationReason", m_sRevocationReason)
                                       .getToString ();
  }

  @Nonnull
  public static RevocationStatus createUnrevoked ()
  {
    return new RevocationStatus (false, null, null, null);
  }
}
