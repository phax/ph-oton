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
import com.helger.base.state.EChange;
import com.helger.collection.commons.ICommonsList;
import com.helger.security.password.hash.PasswordHash;

/**
 * Persistence-agnostic interface for the TOTP user-credential store.
 *
 * @author Philip Helger
 */
public interface IUserTotpManager
{
  /**
   * @param sUserID
   *        User ID. May be <code>null</code>.
   * @return The record for that user, or <code>null</code> if none exists.
   */
  @Nullable
  UserTotpRecord getRecordOfUserID (@Nullable String sUserID);

  /**
   * Create or replace a record. Used during enrollment.
   *
   * @param sUserID
   *        User ID. Neither <code>null</code> nor empty.
   * @param sSecret
   *        Base32-encoded shared secret. Neither <code>null</code> nor empty.
   * @param aRecoveryCodeHashes
   *        Hashed recovery codes. May be <code>null</code> or empty.
   * @return The persisted record. Never <code>null</code>.
   */
  @NonNull
  UserTotpRecord createOrReplaceRecord (@NonNull @Nonempty String sUserID,
                                        @NonNull @Nonempty String sSecret,
                                        @Nullable ICommonsList <PasswordHash> aRecoveryCodeHashes);

  /**
   * Transition the record from {@link ETotpEnrollmentState#PENDING} to
   * {@link ETotpEnrollmentState#ENROLLED}. No-op if there is no record or it is not in
   * {@code PENDING}.
   *
   * @param sUserID
   *        The user ID.
   * @return {@link EChange#CHANGED} if the transition happened.
   */
  @NonNull
  EChange activateEnrollment (@Nullable String sUserID);

  /**
   * Set the record to {@link ETotpEnrollmentState#DISABLED}. The record (including the secret) is
   * kept so that re-enabling does not require re-enrollment.
   *
   * @param sUserID
   *        The user ID.
   * @return {@link EChange#CHANGED} if the state changed.
   */
  @NonNull
  EChange disableEnrollment (@Nullable String sUserID);

  /**
   * Delete the entire TOTP record for a user.
   *
   * @param sUserID
   *        The user ID.
   * @return {@link EChange#CHANGED} if a record was removed.
   */
  @NonNull
  EChange removeRecord (@Nullable String sUserID);

  /**
   * Record that a TOTP counter was accepted, for the default replay-protection scheme.
   *
   * @param sUserID
   *        The user ID.
   * @param nCounter
   *        The counter to record.
   * @return {@link EChange#CHANGED} if the persisted counter changed.
   */
  @NonNull
  EChange setLastAcceptedCounter (@Nullable String sUserID, long nCounter);

  /**
   * Consume a recovery code at the given index (removing it from the list).
   *
   * @param sUserID
   *        The user ID.
   * @param nIndex
   *        The index of the code to remove.
   * @return {@link EChange#CHANGED} if a code was removed.
   */
  @NonNull
  EChange consumeRecoveryCode (@Nullable String sUserID, int nIndex);
}
