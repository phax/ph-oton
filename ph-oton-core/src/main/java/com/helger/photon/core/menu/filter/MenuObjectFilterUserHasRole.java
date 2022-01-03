/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.core.menu.filter;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.EPhotonCoreText;
import com.helger.photon.core.menu.IMenuObject;
import com.helger.photon.security.util.SecurityHelper;

/**
 * This filter checks that a user is logged in, and that the logged in user is
 * assigned to the specified role.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuObjectFilterUserHasRole extends AbstractMenuObjectFilter
{
  private final String m_sRoleID;

  public MenuObjectFilterUserHasRole (@Nonnull @Nonempty final String sRoleID)
  {
    m_sRoleID = ValueEnforcer.notEmpty (sRoleID, "RoleID");
  }

  @Nonnull
  @Nonempty
  public String getRoleID ()
  {
    return m_sRoleID;
  }

  @Override
  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return EPhotonCoreText.MENU_OBJECT_FILTER_USER_HAS_ROLE.getDisplayTextWithArgs (aContentLocale, m_sRoleID);
  }

  public boolean test (@Nullable final IMenuObject aValue)
  {
    return SecurityHelper.hasCurrentUserRole (m_sRoleID);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("roleID", m_sRoleID).getToString ();
  }
}
