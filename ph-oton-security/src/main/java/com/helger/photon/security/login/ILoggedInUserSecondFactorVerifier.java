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
package com.helger.photon.security.login;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;

/**
 * SPI invoked by {@link LoggedInUserManager#loginUserSecondFactor(String)} to verify a
 * second-factor code (e.g. a TOTP token or a single-use recovery code). The implementation is
 * responsible for replay protection and for marking single-use codes as consumed.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface ILoggedInUserSecondFactorVerifier
{
  /**
   * @param sUserID
   *        The ID of the user whose pending login is being completed. Neither <code>null</code>
   *        nor empty.
   * @param sCode
   *        The code submitted by the user. Neither <code>null</code> nor empty.
   * @return <code>true</code> if the code is valid and should grant access, <code>false</code>
   *         otherwise.
   */
  boolean verify (@NonNull @Nonempty String sUserID, @NonNull @Nonempty String sCode);
}
