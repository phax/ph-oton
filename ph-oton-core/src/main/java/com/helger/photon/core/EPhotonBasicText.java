/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.Translatable;
import com.helger.commons.text.IMultilingualText;
import com.helger.commons.text.display.IHasDisplayTextWithArgs;
import com.helger.commons.text.resolve.DefaultTextResolver;
import com.helger.commons.text.util.TextHelper;

/**
 * This enumeration defines the texts for this project.
 *
 * @author Philip Helger
 */
@Translatable
public enum EPhotonBasicText implements IHasDisplayTextWithArgs
{
  // Menu object filters
  MENU_OBJECT_FILTER_USER_LOGGED_IN ("ein Benutzer ist angemeldet", "a user is logged in"),
  MENU_OBJECT_FILTER_NO_USER_LOGGED_IN ("es ist kein Benutzer angemeldet", "no user is logged in"),
  MENU_OBJECT_FILTER_USER_ASSIGNED_TO_GROUP ("ein Benutzer ist der Benutzergruppe {0} zugeordnet",
                                             "a user is assigned to user group {0}"),
  MENU_OBJECT_FILTER_USER_HAS_ROLE ("ein Benutzer hat die Rolle {0}", "a user has role {0}"),

  // System messages
  SYSTEM_MESSAGE_TYPE_INFO ("Information", "Information"),
  SYSTEM_MESSAGE_TYPE_WARNING ("Warnung", "Warning"),
  SYSTEM_MESSAGE_TYPE_ERROR ("Fehler", "Error"),
  SYSTEM_MESSAGE_TYPE_SUCCESS ("Erfolg", "Success");

  private final IMultilingualText m_aTP;

  private EPhotonBasicText (@Nonnull @Nonempty final String sDE, @Nonnull @Nonempty final String sEN)
  {
    m_aTP = TextHelper.create_DE_EN (sDE, sEN);
  }

  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return DefaultTextResolver.getTextStatic (this, m_aTP, aContentLocale);
  }

  @Nonnull
  public IMultilingualText getMultilingualText ()
  {
    return m_aTP;
  }
}
