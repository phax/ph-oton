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
package com.helger.photon.core.systemmsg;

import java.util.Locale;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.id.IHasID;
import com.helger.base.lang.EnumHelper;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.text.display.IHasDisplayText;

/**
 * This enumeration defines the different system message types.
 *
 * @author Philip Helger
 */
public enum ESystemMessageType implements IHasID <String>, IHasDisplayText
{
  INFO ("i", EPhotonCoreText.SYSTEM_MESSAGE_TYPE_INFO),
  WARNING ("w", EPhotonCoreText.SYSTEM_MESSAGE_TYPE_WARNING),
  ERROR ("e", EPhotonCoreText.SYSTEM_MESSAGE_TYPE_ERROR),
  SUCCESS ("s", EPhotonCoreText.SYSTEM_MESSAGE_TYPE_SUCCESS);

  public static final ESystemMessageType DEFAULT = INFO;

  private final String m_sID;
  private final EPhotonCoreText m_eText;

  ESystemMessageType (@NonNull @Nonempty final String sID, @NonNull final EPhotonCoreText aText)
  {
    m_sID = sID;
    m_eText = aText;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nullable
  public String getDisplayText (@NonNull final Locale aContentLocale)
  {
    return m_eText.getDisplayText (aContentLocale);
  }

  @NonNull
  public static ESystemMessageType getFromIDOrDefault (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrDefault (ESystemMessageType.class, sID, DEFAULT);
  }
}
