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
package com.helger.photon.basic.security.usergroup.callback;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.photon.basic.security.usergroup.IUserGroup;

/**
 * Default empty implementation of {@link IUserGroupModificationCallback}. Use
 * this class as the base class of custom implementations so that no change is
 * necessary when the interface gets extended.
 *
 * @author Philip Helger
 */
public class DefaultUserGroupModificationCallback implements IUserGroupModificationCallback
{
  public void onUserGroupCreated (@Nonnull final IUserGroup aUserGroup, final boolean bPredefinedUserGroup)
  {}

  public void onUserGroupUpdated (@Nonnull final IUserGroup aUserGroup)
  {}

  public void onUserGroupRenamed (@Nonnull final IUserGroup aUserGroup)
  {}

  public void onUserGroupDeleted (@Nonnull final IUserGroup aUserGroup)
  {}

  public void onUserGroupUserAssignment (@Nonnull final IUserGroup aUserGroup,
                                         @Nonnull @Nonempty final String sUserID,
                                         final boolean bAssign)
  {}

  public void onUserGroupRoleAssignment (@Nonnull final IUserGroup aUserGroup,
                                         @Nonnull @Nonempty final String sRoleID,
                                         final boolean bAssign)
  {}
}
