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
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.state.EChange;
import com.helger.base.type.ObjectType;
import com.helger.collection.commons.ICommonsList;
import com.helger.dao.DAOException;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.io.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.security.password.hash.PasswordHash;

/**
 * Default XML/WAL-backed implementation of {@link IUserTotpManager}.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserTotpManager extends AbstractPhotonMapBasedWALDAO <UserTotpRecord, UserTotpRecord> implements
                              IUserTotpManager
{
  public static final ObjectType OT = new ObjectType ("usertotp");

  public UserTotpManager (@NonNull @Nonempty final String sFilename) throws DAOException
  {
    super (UserTotpRecord.class, sFilename);
  }

  @Nullable
  public UserTotpRecord getRecordOfUserID (@Nullable final String sUserID)
  {
    return getOfID (sUserID);
  }

  @NonNull
  public UserTotpRecord createOrReplaceRecord (@NonNull @Nonempty final String sUserID,
                                               @NonNull @Nonempty final String sSecret,
                                               @Nullable final ICommonsList <PasswordHash> aRecoveryCodeHashes)
  {
    final UserTotpRecord aRecord = new UserTotpRecord (sUserID,
                                                       ETotpEnrollmentState.PENDING,
                                                       sSecret,
                                                       aRecoveryCodeHashes,
                                                       0L);
    m_aRWLock.writeLocked ( () -> {
      // Replace any prior record (key by user ID)
      if (containsWithID (sUserID))
        internalDeleteItem (sUserID);
      internalCreateItem (aRecord);
    });
    AuditHelper.onAuditCreateSuccess (OT, sUserID, "totp-enrollment-started");
    return aRecord;
  }

  @NonNull
  public EChange activateEnrollment (@Nullable final String sUserID)
  {
    final UserTotpRecord aRecord = getOfID (sUserID);
    if (aRecord == null || aRecord.getState () != ETotpEnrollmentState.PENDING)
      return EChange.UNCHANGED;
    m_aRWLock.writeLocked ( () -> {
      aRecord.setState (ETotpEnrollmentState.ENROLLED);
      internalUpdateItem (aRecord);
    });
    AuditHelper.onAuditModifySuccess (OT, "activate", sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange disableEnrollment (@Nullable final String sUserID)
  {
    final UserTotpRecord aRecord = getOfID (sUserID);
    if (aRecord == null || aRecord.getState () == ETotpEnrollmentState.DISABLED)
      return EChange.UNCHANGED;
    m_aRWLock.writeLocked ( () -> {
      aRecord.setState (ETotpEnrollmentState.DISABLED);
      internalUpdateItem (aRecord);
    });
    AuditHelper.onAuditModifySuccess (OT, "disable", sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange removeRecord (@Nullable final String sUserID)
  {
    if (sUserID == null)
      return EChange.UNCHANGED;
    final UserTotpRecord aRecord = m_aRWLock.writeLockedGet ( () -> internalDeleteItem (sUserID));
    if (aRecord == null)
      return EChange.UNCHANGED;
    AuditHelper.onAuditDeleteSuccess (OT, sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange setLastAcceptedCounter (@Nullable final String sUserID, final long nCounter)
  {
    final UserTotpRecord aRecord = getOfID (sUserID);
    if (aRecord == null || aRecord.getLastAcceptedCounter () == nCounter)
      return EChange.UNCHANGED;
    m_aRWLock.writeLocked ( () -> {
      aRecord.setLastAcceptedCounter (nCounter);
      internalUpdateItem (aRecord);
    });
    return EChange.CHANGED;
  }

  @NonNull
  public EChange consumeRecoveryCode (@Nullable final String sUserID, final int nIndex)
  {
    final UserTotpRecord aRecord = getOfID (sUserID);
    if (aRecord == null || nIndex < 0 || nIndex >= aRecord.recoveryCodes ().size ())
      return EChange.UNCHANGED;
    m_aRWLock.writeLocked ( () -> {
      aRecord.recoveryCodes ().removeAtIndex (nIndex);
      internalUpdateItem (aRecord);
    });
    AuditHelper.onAuditExecuteSuccess ("totp-recovery-code-consumed", sUserID);
    return EChange.CHANGED;
  }
}
