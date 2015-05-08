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
package com.helger.photon.basic.security.role.callback;

import javax.annotation.Nonnull;

import com.helger.commons.callback.ICallback;
import com.helger.photon.basic.security.role.IRole;

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
  void onRoleCreated (@Nonnull IRole aRole, boolean bPredefinedRole);

  /**
   * Called after a role was edited fully.
   *
   * @param aRole
   *        The modified role. Never <code>null</code>.
   */
  void onRoleUpdated (@Nonnull IRole aRole);

  /**
   * Called after a role was renamed.
   *
   * @param aRole
   *        The modified role. Never <code>null</code>.
   */
  void onRoleRenamed (@Nonnull IRole aRole);

  /**
   * Called after a role was deleted.
   *
   * @param aRole
   *        The deleted role. Never <code>null</code>.
   */
  void onRoleDeleted (@Nonnull IRole aRole);
}
