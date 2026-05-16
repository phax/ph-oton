/*
 * Copyright (C) 2021-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc.security;

import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.base.numeric.mutable.MutableLong;
import com.helger.base.state.EChange;
import com.helger.base.state.ESuccess;
import com.helger.base.string.StringHelper;
import com.helger.base.wrapper.Wrapper;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.json.IJson;
import com.helger.json.IJsonArray;
import com.helger.json.IJsonObject;
import com.helger.json.JsonArray;
import com.helger.json.JsonObject;
import com.helger.json.serialize.JsonReader;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.totp.ETotpEnrollmentState;
import com.helger.photon.security.totp.IUserTotpManager;
import com.helger.photon.security.totp.UserTotpManager;
import com.helger.photon.security.totp.UserTotpRecord;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;
import com.helger.security.password.salt.PasswordSalt;

/**
 * JDBC backend for {@link IUserTotpManager}. Stores one row per user; recovery codes are kept as a
 * JSON-encoded array in a single TEXT column. Schema:
 *
 * <pre>
 * CREATE TABLE sectotp (
 *   userid        VARCHAR(20) PRIMARY KEY,
 *   state         VARCHAR(16) NOT NULL,
 *   secret        VARCHAR(128) NOT NULL,
 *   lastcounter   BIGINT NOT NULL DEFAULT 0,
 *   recoverycodes TEXT
 * );
 * </pre>
 *
 * The {@code recoverycodes} JSON array contains objects of the form
 * {@code {"a":"algorithm","s":"salt-or-null","h":"hash"}}.
 *
 * @author Philip Helger
 */
public class UserTotpManagerJDBC extends AbstractJDBCEnabledSecurityManager implements IUserTotpManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (UserTotpManagerJDBC.class);

  private static final String KEY_ALGO = "a";
  private static final String KEY_SALT = "s";
  private static final String KEY_HASH = "h";

  private final String m_sTableName;

  public UserTotpManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                              @NonNull final Function <String, String> aTableNameCustomizer)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("sectotp");
  }

  @NonNull
  @Nonempty
  public final String getTableName ()
  {
    return m_sTableName;
  }

  @Nullable
  private static String _codesToJson (@Nullable final ICommonsList <PasswordHash> aCodes)
  {
    if (aCodes == null || aCodes.isEmpty ())
      return null;
    final IJsonArray aArr = new JsonArray ();
    for (final PasswordHash aHash : aCodes)
    {
      final IJsonObject aObj = new JsonObject ().add (KEY_ALGO, aHash.getAlgorithmName ())
                                                .add (KEY_HASH, aHash.getPasswordHashValue ());
      if (aHash.hasSalt ())
        aObj.add (KEY_SALT, aHash.getSaltAsString ());
      aArr.add (aObj);
    }
    return aArr.getAsJsonString ();
  }

  @NonNull
  private static ICommonsList <PasswordHash> _codesFromJson (@Nullable final String sJson)
  {
    final ICommonsList <PasswordHash> ret = new CommonsArrayList <> ();
    if (StringHelper.isEmpty (sJson))
      return ret;
    final IJsonArray aArr = JsonReader.builder ().source (sJson).readAsArray ();
    if (aArr == null)
      return ret;
    for (final IJson aEntry : aArr)
    {
      final IJsonObject aObj = aEntry.getAsObject ();
      if (aObj == null)
        continue;
      final String sAlgo = aObj.getAsString (KEY_ALGO);
      final String sHash = aObj.getAsString (KEY_HASH);
      final String sSalt = aObj.getAsString (KEY_SALT);
      final IPasswordSalt aSalt = PasswordSalt.createFromStringMaybe (sSalt);
      if (StringHelper.isNotEmpty (sAlgo) && StringHelper.isNotEmpty (sHash))
        ret.add (new PasswordHash (sAlgo, aSalt, sHash));
    }
    return ret;
  }

  @Nullable
  private UserTotpRecord _readRow (@NonNull final DBResultRow aRow)
  {
    final String sUserID = aRow.getAsString (0);
    final ETotpEnrollmentState eState = ETotpEnrollmentState.getFromIDOrNull (aRow.getAsString (1));
    final String sSecret = aRow.getAsString (2);
    final long nLastCounter = aRow.getAsLong (3, 0L);
    final String sCodesJson = aRow.getAsString (4);
    if (sUserID == null || eState == null || sSecret == null)
      return null;
    return new UserTotpRecord (sUserID, eState, sSecret, _codesFromJson (sCodesJson), nLastCounter);
  }

  @Nullable
  public UserTotpRecord getRecordOfUserID (@Nullable final String sUserID)
  {
    if (StringHelper.isEmpty (sUserID))
      return null;
    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT userid, state, secret, lastcounter, recoverycodes FROM " +
                                m_sTableName +
                                " WHERE userid=?",
                                new ConstantPreparedStatementDataProvider (sUserID),
                                aDBResult::set);
    return aDBResult.isSet () ? _readRow (aDBResult.get ()) : null;
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
    final String sCodesJson = _codesToJson (aRecoveryCodeHashes);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      aExecutor.insertOrUpdateOrDelete ("DELETE FROM " + m_sTableName + " WHERE userid=?",
                                        new ConstantPreparedStatementDataProvider (sUserID));
      aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                        m_sTableName +
                                        " (userid, state, secret, lastcounter, recoverycodes) VALUES (?, ?, ?, ?, ?)",
                                        new ConstantPreparedStatementDataProvider (sUserID,
                                                                                   ETotpEnrollmentState.PENDING.getID (),
                                                                                   sSecret,
                                                                                   Long.valueOf (0L),
                                                                                   sCodesJson));
    });
    if (eSuccess.isFailure ())
    {
      AuditHelper.onAuditCreateFailure (UserTotpManager.OT, sUserID, "database-error");
      LOGGER.warn ("Failed to persist TOTP enrollment for user '" + sUserID + "'");
    }
    else
      AuditHelper.onAuditCreateSuccess (UserTotpManager.OT, sUserID, "totp-enrollment-started");
    return aRecord;
  }

  @NonNull
  private EChange _setState (@Nullable final String sUserID,
                             @NonNull final ETotpEnrollmentState eRequiredCurrent,
                             @NonNull final ETotpEnrollmentState eNew,
                             @NonNull final String sAuditWhat)
  {
    if (StringHelper.isEmpty (sUserID))
      return EChange.UNCHANGED;
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long n = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                       m_sTableName +
                                                       " SET state=? WHERE userid=? AND state=?",
                                                       new ConstantPreparedStatementDataProvider (eNew.getID (),
                                                                                                  sUserID,
                                                                                                  eRequiredCurrent.getID ()));
      aUpdated.set (n);
    });
    if (eSuccess.isFailure () || aUpdated.is0 ())
      return EChange.UNCHANGED;
    AuditHelper.onAuditModifySuccess (UserTotpManager.OT, sAuditWhat, sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange activateEnrollment (@Nullable final String sUserID)
  {
    return _setState (sUserID, ETotpEnrollmentState.PENDING, ETotpEnrollmentState.ENROLLED, "activate");
  }

  @NonNull
  public EChange disableEnrollment (@Nullable final String sUserID)
  {
    if (StringHelper.isEmpty (sUserID))
      return EChange.UNCHANGED;
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long n = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                       m_sTableName +
                                                       " SET state=? WHERE userid=? AND state<>?",
                                                       new ConstantPreparedStatementDataProvider (ETotpEnrollmentState.DISABLED.getID (),
                                                                                                  sUserID,
                                                                                                  ETotpEnrollmentState.DISABLED.getID ()));
      aUpdated.set (n);
    });
    if (eSuccess.isFailure () || aUpdated.is0 ())
      return EChange.UNCHANGED;
    AuditHelper.onAuditModifySuccess (UserTotpManager.OT, "disable", sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange removeRecord (@Nullable final String sUserID)
  {
    if (StringHelper.isEmpty (sUserID))
      return EChange.UNCHANGED;
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long n = aExecutor.insertOrUpdateOrDelete ("DELETE FROM " + m_sTableName + " WHERE userid=?",
                                                       new ConstantPreparedStatementDataProvider (sUserID));
      aUpdated.set (n);
    });
    if (eSuccess.isFailure () || aUpdated.is0 ())
      return EChange.UNCHANGED;
    AuditHelper.onAuditDeleteSuccess (UserTotpManager.OT, sUserID);
    return EChange.CHANGED;
  }

  @NonNull
  public EChange setLastAcceptedCounter (@Nullable final String sUserID, final long nCounter)
  {
    if (StringHelper.isEmpty (sUserID))
      return EChange.UNCHANGED;
    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long n = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                       m_sTableName +
                                                       " SET lastcounter=? WHERE userid=? AND lastcounter<>?",
                                                       new ConstantPreparedStatementDataProvider (Long.valueOf (nCounter),
                                                                                                  sUserID,
                                                                                                  Long.valueOf (nCounter)));
      aUpdated.set (n);
    });
    if (eSuccess.isFailure () || aUpdated.is0 ())
      return EChange.UNCHANGED;
    return EChange.CHANGED;
  }

  @NonNull
  public EChange consumeRecoveryCode (@Nullable final String sUserID, final int nIndex)
  {
    final UserTotpRecord aRecord = getRecordOfUserID (sUserID);
    if (aRecord == null || nIndex < 0 || nIndex >= aRecord.recoveryCodes ().size ())
      return EChange.UNCHANGED;
    aRecord.recoveryCodes ().removeAtIndex (nIndex);
    final String sCodesJson = _codesToJson (aRecord.recoveryCodes ());
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                        m_sTableName +
                                        " SET recoverycodes=? WHERE userid=?",
                                        new ConstantPreparedStatementDataProvider (sCodesJson, sUserID));
    });
    if (eSuccess.isFailure ())
      return EChange.UNCHANGED;
    AuditHelper.onAuditExecuteSuccess ("totp-recovery-code-consumed", sUserID);
    return EChange.CHANGED;
  }

}
