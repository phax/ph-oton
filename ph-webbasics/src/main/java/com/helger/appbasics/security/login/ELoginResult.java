/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.appbasics.security.login;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.name.IHasDisplayText;
import com.helger.commons.state.ISuccessIndicator;

/**
 * Represents the different login results.
 *
 * @author Philip Helger
 */
public enum ELoginResult implements ISuccessIndicator, IHasDisplayText
{
  /** Login was successfully */
  SUCCESS (ELoginResultText.SUCCESS),
  /** Login was successfully but logged out previous session */
  SUCCESS_WITH_LOGOUT (ELoginResultText.SUCCESS_WITH_LOGOUT),
  /** No such user */
  USER_NOT_EXISTING (ELoginResultText.USER_NOT_EXISTING),
  /** User is marked as deleted */
  USER_IS_DELETED (ELoginResultText.USER_IS_DELETED),
  /** User is disabled */
  USER_IS_DISABLED (ELoginResultText.USER_IS_DISABLED),
  /** User does not have the correct role */
  USER_IS_MISSING_ROLE (ELoginResultText.USER_IS_MISSING_ROLE),
  /** The provided password is invalid */
  INVALID_PASSWORD (ELoginResultText.INVALID_PASSWORD),
  /** The user was already logged in */
  USER_ALREADY_LOGGED_IN (ELoginResultText.USER_ALREADY_LOGGED_IN),
  /** Another user is already logged in this session */
  SESSION_ALREADY_HAS_USER (ELoginResultText.SESSION_ALREADY_HAS_USER);

  private final IHasDisplayText m_aErrorMsg;

  private ELoginResult (@Nonnull final IHasDisplayText aErrorMsg)
  {
    m_aErrorMsg = aErrorMsg;
  }

  public boolean isSuccess ()
  {
    return this == SUCCESS || this == SUCCESS_WITH_LOGOUT;
  }

  public boolean isFailure ()
  {
    return !isSuccess ();
  }

  public boolean hasMessage ()
  {
    // SUCCESS_WITH_LOGOUT is worth displaying
    return this != SUCCESS;
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return m_aErrorMsg.getDisplayText (aContentLocale);
  }
}
