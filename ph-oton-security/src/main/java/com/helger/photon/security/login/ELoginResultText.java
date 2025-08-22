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
package com.helger.photon.security.login;

import java.util.Locale;

import com.helger.annotation.misc.Translatable;
import com.helger.text.IMultilingualText;
import com.helger.text.display.IHasDisplayText;
import com.helger.text.resolve.DefaultTextResolver;
import com.helger.text.util.TextHelper;

import jakarta.annotation.Nonnull;

/**
 * Login result explanations
 *
 * @author Philip Helger
 */
@Translatable
public enum ELoginResultText implements IHasDisplayText
{
  SUCCESS ("Die Anmeldung war erfolgreich.", "User logged in successfully."),
  SUCCESS_WITH_LOGOUT ("Die Anmeldung war erfolgreich. Eine andere Sitzung wurde automatisch beendet.",
                       "User logged in successfully. Another session was automatically closed."),
  USER_NOT_EXISTING ("Der Benutzername ist ungültig.", "No such user exists."),
  USER_IS_DELETED ("Der Benutzer existiert nicht mehr.", "The user no longer exists."),
  USER_IS_DISABLED ("Der Benutzer ist deaktiviert.", "The user is disabled."),
  USER_IS_MISSING_ROLE ("Der Benutzer hat keine Berechtigung sich anzumelden.", "The user has no rights to login."),
  INVALID_PASSWORD ("Das Passwort ist ungültig.", "Invalid password provided."),
  USER_ALREADY_LOGGED_IN ("Der Benutzer ist bereits angemeldet.", "The user is already logged in."),
  SESSION_ALREADY_HAS_USER ("Es ist bereits ein anderer Benutzer angemeldet.", "Another user is already logged in."),
  TOKEN_NOT_EXISTING ("Der Zugriffstoken ist ungültig.", "The access token does not exist.");

  private final IMultilingualText m_aTP;

  ELoginResultText (@Nonnull final String sDE, @Nonnull final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }
}
