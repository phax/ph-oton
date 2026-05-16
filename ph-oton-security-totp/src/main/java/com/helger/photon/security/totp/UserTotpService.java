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

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.photon.security.login.ILoggedInUserSecondFactorPolicy;
import com.helger.photon.security.login.ILoggedInUserSecondFactorVerifier;
import com.helger.photon.security.password.GlobalPasswordSettings;
import com.helger.security.password.hash.PasswordHash;
import com.helger.security.password.salt.IPasswordSalt;
import com.helger.security.password.salt.PasswordSalt;
import com.helger.totp.CTotp;
import com.helger.totp.code.DefaultCodeGenerator;
import com.helger.totp.code.ICodeGenerator;
import com.helger.totp.exception.CodeGenerationException;
import com.helger.totp.recovery.RecoveryCodeGenerator;
import com.helger.totp.secret.DefaultSecretGenerator;
import com.helger.totp.secret.ISecretGenerator;
import com.helger.totp.time.ITimeProvider;
import com.helger.totp.time.SystemTimeProvider;

/**
 * Façade combining {@link IUserTotpManager} with the ph-totp primitives. Provides:
 * <ul>
 * <li>enrollment helpers (secret + recovery-code generation),</li>
 * <li>verification of submitted codes (TOTP or single-use recovery codes),</li>
 * <li>integration with {@link com.helger.photon.security.login.LoggedInUserManager} as both a
 * {@link ILoggedInUserSecondFactorPolicy} and a {@link ILoggedInUserSecondFactorVerifier}.</li>
 * </ul>
 *
 * @author Philip Helger
 */
@ThreadSafe
public class UserTotpService implements ILoggedInUserSecondFactorPolicy, ILoggedInUserSecondFactorVerifier
{
  public static final int DEFAULT_RECOVERY_CODE_COUNT = 10;

  private static final Logger LOGGER = LoggerFactory.getLogger (UserTotpService.class);

  private final IUserTotpManager m_aManager;
  private final ICodeGenerator m_aCodeGenerator;
  private final ITimeProvider m_aTimeProvider;
  private final ISecretGenerator m_aSecretGenerator;
  private final RecoveryCodeGenerator m_aRecoveryCodeGenerator;
  private int m_nTimePeriodSecs = CTotp.DEFAULT_TIME_PERIOD_SECS;
  private int m_nAllowedDiscrepancy = CTotp.DEFAULT_TIME_PERIOD_DISCREPANCY;
  private IUserTotpReplayCallback m_aReplayCallback;

  public UserTotpService (@NonNull final IUserTotpManager aManager)
  {
    this (aManager,
          new DefaultCodeGenerator (),
          new SystemTimeProvider (),
          new DefaultSecretGenerator (),
          new RecoveryCodeGenerator ());
  }

  public UserTotpService (@NonNull final IUserTotpManager aManager,
                          @NonNull final ICodeGenerator aCodeGenerator,
                          @NonNull final ITimeProvider aTimeProvider,
                          @NonNull final ISecretGenerator aSecretGenerator,
                          @NonNull final RecoveryCodeGenerator aRecoveryCodeGenerator)
  {
    ValueEnforcer.notNull (aManager, "Manager");
    ValueEnforcer.notNull (aCodeGenerator, "CodeGenerator");
    ValueEnforcer.notNull (aTimeProvider, "TimeProvider");
    ValueEnforcer.notNull (aSecretGenerator, "SecretGenerator");
    ValueEnforcer.notNull (aRecoveryCodeGenerator, "RecoveryCodeGenerator");
    m_aManager = aManager;
    m_aCodeGenerator = aCodeGenerator;
    m_aTimeProvider = aTimeProvider;
    m_aSecretGenerator = aSecretGenerator;
    m_aRecoveryCodeGenerator = aRecoveryCodeGenerator;
    m_aReplayCallback = new DefaultUserTotpReplayCallback (aManager);
  }

  @NonNull
  public final IUserTotpManager getManager ()
  {
    return m_aManager;
  }

  public final int getTimePeriodSecs ()
  {
    return m_nTimePeriodSecs;
  }

  @NonNull
  public final UserTotpService setTimePeriodSecs (final int nTimePeriodSecs)
  {
    ValueEnforcer.isGT0 (nTimePeriodSecs, "TimePeriodSecs");
    m_nTimePeriodSecs = nTimePeriodSecs;
    return this;
  }

  public final int getAllowedDiscrepancy ()
  {
    return m_nAllowedDiscrepancy;
  }

  @NonNull
  public final UserTotpService setAllowedDiscrepancy (final int nAllowedDiscrepancy)
  {
    ValueEnforcer.isGE0 (nAllowedDiscrepancy, "AllowedDiscrepancy");
    m_nAllowedDiscrepancy = nAllowedDiscrepancy;
    return this;
  }

  @NonNull
  public final IUserTotpReplayCallback getReplayCallback ()
  {
    return m_aReplayCallback;
  }

  /**
   * Replace the replay-protection callback (default keeps a per-user last-counter on the
   * {@link UserTotpRecord}).
   */
  @NonNull
  public final UserTotpService setReplayCallback (@NonNull final IUserTotpReplayCallback aReplayCallback)
  {
    ValueEnforcer.notNull (aReplayCallback, "ReplayCallback");
    m_aReplayCallback = aReplayCallback;
    return this;
  }

  /**
   * Result of enrollment containing both the new secret and the plaintext recovery codes. The
   * plaintext codes are only available at this point — they are stored hashed and cannot be
   * recovered later.
   */
  public static final class EnrollmentResult
  {
    private final UserTotpRecord m_aRecord;
    private final ICommonsList <String> m_aPlaintextRecoveryCodes;

    EnrollmentResult (@NonNull final UserTotpRecord aRecord, @NonNull final ICommonsList <String> aPlaintextCodes)
    {
      m_aRecord = aRecord;
      m_aPlaintextRecoveryCodes = aPlaintextCodes;
    }

    @NonNull
    public UserTotpRecord getRecord ()
    {
      return m_aRecord;
    }

    @NonNull
    public ICommonsList <String> getPlaintextRecoveryCodes ()
    {
      return m_aPlaintextRecoveryCodes;
    }
  }

  /**
   * Start enrollment for a user: generate a fresh secret and a fresh set of recovery codes, store
   * them in {@link ETotpEnrollmentState#PENDING} state, and return both. The plaintext recovery
   * codes are returned so the caller can display them once.
   */
  @NonNull
  public EnrollmentResult startEnrollment (@NonNull @Nonempty final String sUserID)
  {
    return startEnrollment (sUserID, DEFAULT_RECOVERY_CODE_COUNT);
  }

  @NonNull
  public EnrollmentResult startEnrollment (@NonNull @Nonempty final String sUserID, final int nRecoveryCodeCount)
  {
    ValueEnforcer.notEmpty (sUserID, "UserID");
    ValueEnforcer.isGE0 (nRecoveryCodeCount, "RecoveryCodeCount");

    final String sSecret = m_aSecretGenerator.generate ();
    final ICommonsList <String> aPlaintext = new CommonsArrayList <> ();
    final ICommonsList <PasswordHash> aHashes = new CommonsArrayList <> ();
    final String [] aCodes = m_aRecoveryCodeGenerator.generateCodes (nRecoveryCodeCount);
    for (final String sCode : aCodes)
    {
      aPlaintext.add (sCode);
      aHashes.add (_hashRecoveryCode (sCode));
    }
    final UserTotpRecord aRecord = m_aManager.createOrReplaceRecord (sUserID, sSecret, aHashes);
    return new EnrollmentResult (aRecord, aPlaintext);
  }

  /**
   * Finalize enrollment after the user has successfully entered a valid code from their
   * authenticator. Verifies the supplied code against the pending record and, on success, flips
   * the state to {@link ETotpEnrollmentState#ENROLLED}.
   *
   * @return <code>true</code> if the code matched and enrollment was activated.
   */
  public boolean confirmEnrollment (@NonNull @Nonempty final String sUserID, @NonNull @Nonempty final String sCode)
  {
    final UserTotpRecord aRecord = m_aManager.getRecordOfUserID (sUserID);
    if (aRecord == null || aRecord.getState () != ETotpEnrollmentState.PENDING)
      return false;
    final long nMatched = _verifyTotpReturningCounter (aRecord.getSecret (), sCode);
    if (nMatched < 0)
      return false;
    // Initial enrollment also seeds the replay marker so that the same code can't be reused
    if (!m_aReplayCallback.acceptAndRecord (sUserID, nMatched))
      return false;
    return m_aManager.activateEnrollment (sUserID).isChanged ();
  }

  // ---- ILoggedInUserSecondFactorPolicy ----

  public boolean isSecondFactorRequired (@NonNull @Nonempty final String sUserID)
  {
    final UserTotpRecord aRecord = m_aManager.getRecordOfUserID (sUserID);
    return aRecord != null && aRecord.getState () == ETotpEnrollmentState.ENROLLED;
  }

  // ---- ILoggedInUserSecondFactorVerifier ----

  public boolean verify (@NonNull @Nonempty final String sUserID, @NonNull @Nonempty final String sCode)
  {
    final UserTotpRecord aRecord = m_aManager.getRecordOfUserID (sUserID);
    if (aRecord == null || aRecord.getState () != ETotpEnrollmentState.ENROLLED)
      return false;

    final String sNormalized = _normalize (sCode);
    if (sNormalized.isEmpty ())
      return false;

    // Heuristic: TOTP codes are digits-only. Try TOTP path when the normalized input is numeric.
    if (_isDigitsOnly (sNormalized))
    {
      final long nMatched = _verifyTotpReturningCounter (aRecord.getSecret (), sNormalized);
      if (nMatched >= 0)
        return m_aReplayCallback.acceptAndRecord (sUserID, nMatched);
      return false;
    }

    // Recovery code path
    final ICommonsList <PasswordHash> aCodes = aRecord.recoveryCodes ();
    for (int i = 0; i < aCodes.size (); i++)
    {
      final PasswordHash aStored = aCodes.get (i);
      final PasswordHash aProbe = GlobalPasswordSettings.createUserPasswordHash (aStored.getAlgorithmName (),
                                                                                 aStored.getSalt (),
                                                                                 sNormalized);
      if (aStored.equals (aProbe))
      {
        m_aManager.consumeRecoveryCode (sUserID, i);
        LOGGER.info ("Recovery code consumed for user '" + sUserID + "'");
        return true;
      }
    }
    return false;
  }

  // ---- internals ----

  /**
   * Mirror of {@code DefaultCodeVerifier.isValidCode} but returns the matched counter rather than
   * a boolean. Iterates the full window even after a match for timing-safety.
   */
  private long _verifyTotpReturningCounter (@NonNull final String sSecret, @NonNull final String sCode)
  {
    final long nCurrentBucket = Math.floorDiv (m_aTimeProvider.getTime (), m_nTimePeriodSecs);
    long nMatched = -1;
    for (int i = -m_nAllowedDiscrepancy; i <= m_nAllowedDiscrepancy; i++)
    {
      final long nCounter = nCurrentBucket + i;
      try
      {
        final String sActual = m_aCodeGenerator.generate (sSecret, nCounter);
        if (_timeSafeEquals (sActual, sCode))
          nMatched = nCounter;
      }
      catch (final CodeGenerationException ex)
      {
        // intentional fall-through: keep iterating to avoid timing leak
      }
    }
    return nMatched;
  }

  private static boolean _timeSafeEquals (@NonNull final String sA, @NonNull final String sB)
  {
    final byte [] aA = sA.getBytes (StandardCharsets.UTF_8);
    final byte [] aB = sB.getBytes (StandardCharsets.UTF_8);
    if (aA.length != aB.length)
      return false;
    int nResult = 0;
    for (int i = 0; i < aA.length; i++)
      nResult |= aA[i] ^ aB[i];
    return nResult == 0;
  }

  @NonNull
  private static String _normalize (@Nullable final String sCode)
  {
    if (sCode == null)
      return "";
    final StringBuilder aSB = new StringBuilder (sCode.length ());
    for (int i = 0; i < sCode.length (); i++)
    {
      final char c = sCode.charAt (i);
      if (c == ' ' || c == '-' || c == '_' || c == '\t')
        continue;
      aSB.append (Character.toLowerCase (c));
    }
    return aSB.toString ();
  }

  private static boolean _isDigitsOnly (@NonNull final String sCode)
  {
    for (int i = 0; i < sCode.length (); i++)
      if (!Character.isDigit (sCode.charAt (i)))
        return false;
    return !sCode.isEmpty ();
  }

  @NonNull
  private static PasswordHash _hashRecoveryCode (@NonNull final String sCode)
  {
    final IPasswordSalt aSalt = PasswordSalt.createRandom ();
    return GlobalPasswordSettings.createUserDefaultPasswordHash (aSalt, sCode);
  }

  /**
   * Locale-safe lowercasing used in {@link #_normalize(String)}.
   */
  @SuppressWarnings ("unused")
  private static String _toLower (@NonNull final String s)
  {
    return s.toLowerCase (Locale.ROOT);
  }
}
