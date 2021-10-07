/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.security.role;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.callback.ICallback;

/**
 * Callback interface when a role is created, modified, deleted or renamed.
 *
 * @author Philip Helger
 */
public interface IRoleModificationCallback extends ICallback
{
  /**
   * Called after a role was created.
   *
   * @param aRole
   *        The created role. Never <code>null</code>.
   * @param bPredefinedRole
   *        <code>true</code> if it is a predefined role, <code>false</code> if
   *        it is a regular role
   */
  default void onRoleCreated (@Nonnull final IRole aRole, final boolean bPredefinedRole)
  {}

  /**
   * Called after a role was edited fully.
   *
   * @param sRoleID
   *        The modified role ID. Never <code>null</code>.
   */
  default void onRoleUpdated (@Nonnull @Nonempty final String sRoleID)
  {}

  /**
   * Called after a role was renamed.
   *
   * @param sRoleID
   *        The modified role ID. Never <code>null</code>.
   */
  default void onRoleRenamed (@Nonnull @Nonempty final String sRoleID)
  {}

  /**
   * Called after a role was deleted.
   *
   * @param sRoleID
   *        The deleted role ID. Never <code>null</code>.
   */
  default void onRoleDeleted (@Nonnull @Nonempty final String sRoleID)
  {}
}
