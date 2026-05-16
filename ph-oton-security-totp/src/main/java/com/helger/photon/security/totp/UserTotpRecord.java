/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.security.totp;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.security.password.hash.PasswordHash;

/**
 * Per-user TOTP credential record. Persisted by {@link IUserTotpManager}. Mutable — guarded by
 * the manager.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class UserTotpRecord implements IHasID <String>
{
  private final String m_sUserID;
  private ETotpEnrollmentState m_eState;
  private String m_sSecret;
  private final ICommonsList <PasswordHash> m_aRecoveryCodes;
  private long m_nLastAcceptedCounter;

  public UserTotpRecord (@NonNull @Nonempty final String sUserID,
                         @NonNull final ETotpEnrollmentState eState,
                         @NonNull @Nonempty final String sSecret,
                         @Nullable final ICommonsList <PasswordHash> aRecoveryCodes,
                         final long nLastAcceptedCounter)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");
    ValueEnforcer.notNull (eState, "State");
    ValueEnforcer.notEmpty (sSecret, "Secret");
    m_sUserID = sUserID;
    m_eState = eState;
    m_sSecret = sSecret;
    m_aRecoveryCodes = aRecoveryCodes != null ? new CommonsArrayList <> (aRecoveryCodes) : new CommonsArrayList <> ();
    m_nLastAcceptedCounter = nLastAcceptedCounter;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sUserID;
  }

  @NonNull
  @Nonempty
  public String getUserID ()
  {
    return m_sUserID;
  }

  @NonNull
  public ETotpEnrollmentState getState ()
  {
    return m_eState;
  }

  public void setState (@NonNull final ETotpEnrollmentState eState)
  {
    ValueEnforcer.notNull (eState, "State");
    m_eState = eState;
  }

  /**
   * @return The Base32-encoded shared secret. Never <code>null</code>.
   */
  @NonNull
  @Nonempty
  public String getSecret ()
  {
    return m_sSecret;
  }

  public void setSecret (@NonNull @Nonempty final String sSecret)
  {
    ValueEnforcer.notEmpty (sSecret, "Secret");
    m_sSecret = sSecret;
  }

  /**
   * @return Live list of hashed recovery codes. Mutating it changes the record. Each entry is a
   *         {@link PasswordHash} of a single-use plaintext code.
   */
  @NonNull
  @ReturnsMutableObject
  public ICommonsList <PasswordHash> recoveryCodes ()
  {
    return m_aRecoveryCodes;
  }

  /**
   * @return The highest TOTP counter (time bucket) accepted so far. <code>0</code> if no code has
   *         been accepted yet. Replay protection compares against this.
   */
  public long getLastAcceptedCounter ()
  {
    return m_nLastAcceptedCounter;
  }

  public void setLastAcceptedCounter (final long nLastAcceptedCounter)
  {
    m_nLastAcceptedCounter = nLastAcceptedCounter;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("userID", m_sUserID)
                                       .append ("state", m_eState)
                                       .append ("recoveryCodeCount", m_aRecoveryCodes.size ())
                                       .append ("lastAcceptedCounter", m_nLastAcceptedCounter)
                                       .getToString ();
  }
}
