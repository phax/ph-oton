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
package com.helger.appbasics.app.menu.filter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.appbasics.app.menu.IMenuObject;
import com.helger.appbasics.security.util.SecurityUtils;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.string.ToStringGenerator;

public final class MenuItemFilterUserHasRole extends AbstractMenuObjectFilter
{
  private final String m_sRoleID;

  public MenuItemFilterUserHasRole (@Nonnull @Nonempty final String sRoleID)
  {
    m_sRoleID = ValueEnforcer.notEmpty (sRoleID, "RoleID");
  }

  @Nonnull
  @Nonempty
  public String getRoleID ()
  {
    return m_sRoleID;
  }

  public boolean matchesFilter (@Nullable final IMenuObject aValue)
  {
    return SecurityUtils.hasCurrentUserRole (m_sRoleID);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("roleID", m_sRoleID).toString ();
  }
}
