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

import com.helger.annotation.Nonempty;

/**
 * Replay protection hook invoked when a submitted TOTP code matches one of the counters in the
 * verification window. RFC 6238 §5.2 mandates that an OTP value cannot be accepted more than
 * once.
 * <p>
 * The default implementation ({@link DefaultUserTotpReplayCallback}) records the last accepted
 * counter on the {@link UserTotpRecord} and rejects any counter less-than-or-equal to it. Replace
 * via {@link UserTotpService#setReplayCallback(IUserTotpReplayCallback)} for stricter or
 * distributed schemes (e.g. shared cache across nodes).
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface IUserTotpReplayCallback
{
  /**
   * Decide whether a counter that just matched the verification window should be accepted.
   * Implementations MAY record state so that future calls reject the same counter.
   *
   * @param sUserID
   *        The user whose code is being verified. Neither <code>null</code> nor empty.
   * @param nMatchedCounter
   *        The TOTP counter (time bucket) that matched. Always &ge; 0 for valid TOTP times.
   * @return <code>true</code> to accept the counter, <code>false</code> to reject it as a replay.
   */
  boolean acceptAndRecord (@NonNull @Nonempty String sUserID, long nMatchedCounter);
}
