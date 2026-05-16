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
 * SPI consulted during login to decide whether a user must satisfy a second factor (e.g. TOTP)
 * before being considered logged in. Register a single implementation on
 * {@link LoggedInUserManager#setSecondFactorPolicy(ILoggedInUserSecondFactorPolicy)}. When no
 * policy is registered, second-factor authentication is disabled.
 *
 * @author Philip Helger
 */
@FunctionalInterface
public interface ILoggedInUserSecondFactorPolicy
{
  /**
   * @param sUserID
   *        The ID of the user that just passed primary credential validation. Neither
   *        <code>null</code> nor empty.
   * @return <code>true</code> if a second factor is required to complete login of this user,
   *         <code>false</code> if the user may be logged in straight away.
   */
  boolean isSecondFactorRequired (@NonNull @Nonempty String sUserID);
}
