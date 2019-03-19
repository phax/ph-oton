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
package com.helger.photon.core.app.menu.security;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.core.EPhotonBasicText;
import com.helger.photon.core.app.menu.IMenuObject;
import com.helger.photon.core.app.menu.filter.AbstractMenuObjectFilter;
import com.helger.photon.security.util.SecurityHelper;

/**
 * This filter matches any menu item if a user is logged in and if the user is
 * assigned to the specified user group ID.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public class MenuObjectFilterUserAssignedToUserGroup extends AbstractMenuObjectFilter
{
  private final String m_sUserGroupID;

  public MenuObjectFilterUserAssignedToUserGroup (@Nonnull @Nonempty final String sUserGroupID)
  {
    m_sUserGroupID = ValueEnforcer.notEmpty (sUserGroupID, "UserGroupID");
  }

  @Nonnull
  @Nonempty
  public String getUserGroupID ()
  {
    return m_sUserGroupID;
  }

  @Override
  @Nullable
  public String getDisplayText (@Nonnull final Locale aContentLocale)
  {
    return EPhotonBasicText.MENU_OBJECT_FILTER_USER_ASSIGNED_TO_GROUP.getDisplayTextWithArgs (aContentLocale,
                                                                                              m_sUserGroupID);
  }

  public boolean test (@Nullable final IMenuObject aValue)
  {
    return SecurityHelper.isCurrentUserAssignedToUserGroup (m_sUserGroupID);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("userGroupID", m_sUserGroupID).getToString ();
  }
}
