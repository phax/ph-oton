/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.commons.annotation.ReturnsMutableCopy;

/**
 * Base read-only interface for objects containing roles.
 *
 * @author Philip Helger
 */
public interface IRoleContainer
{
  /**
   * @return <code>true</code> if at least one role is contained,
   *         <code>false</code> otherwise.
   */
  boolean hasContainedRoles ();

  /**
   * @return The number of contained roles. Always &ge; 0.
   */
  @Nonnegative
  int getContainedRoleCount ();

  /**
   * @return A non-<code>null</code>but maybe empty set of all assigned role
   *         IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  Set <String> getAllContainedRoleIDs ();

  /**
   * Check if the passed role is contained in this container.
   *
   * @param sRoleID
   *        The role ID to check. May be <code>null</code>.
   * @return <code>true</code> if the role is contained in this container,
   *         <code>false</code> otherwise.
   */
  boolean containsRoleID (String sRoleID);
}
