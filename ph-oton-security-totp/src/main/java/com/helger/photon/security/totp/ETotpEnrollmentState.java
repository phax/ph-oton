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
import com.helger.base.id.IHasID;
import com.helger.base.lang.EnumHelper;

/**
 * Enrollment state of a user for TOTP-based second-factor authentication.
 *
 * @author Philip Helger
 */
public enum ETotpEnrollmentState implements IHasID <String>
{
  /** Enrollment was started (secret generated, QR shown) but not yet confirmed by a valid code. */
  PENDING ("pending"),
  /** Fully enrolled — TOTP is required at login. */
  ENROLLED ("enrolled"),
  /** Previously enrolled but currently turned off (e.g. by an admin); not required at login. */
  DISABLED ("disabled");

  private final String m_sID;

  ETotpEnrollmentState (@NonNull @Nonempty final String sID)
  {
    m_sID = sID;
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  public boolean isEnrolled ()
  {
    return this == ENROLLED;
  }

  @Nullable
  public static ETotpEnrollmentState getFromIDOrNull (@Nullable final String sID)
  {
    return EnumHelper.getFromIDOrNull (ETotpEnrollmentState.class, sID);
  }
}
