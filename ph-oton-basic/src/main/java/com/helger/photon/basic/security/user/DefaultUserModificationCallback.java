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
package com.helger.photon.basic.security.user;

import javax.annotation.Nonnull;

/**
 * Default empty implementation of {@link IUserModificationCallback}. Use this
 * class as the base class of custom implementations so that no change is
 * necessary when the interface gets extended.
 *
 * @author Philip Helger
 */
public class DefaultUserModificationCallback implements IUserModificationCallback
{
  public void onUserCreated (@Nonnull final IUser aUser, final boolean bPredefinedUser)
  {}

  public void onUserUpdated (@Nonnull final IUser aUser)
  {}

  public void onUserLastFailedLoginUpdated (@Nonnull final IUser aUser)
  {}

  public void onUserPasswordChanged (@Nonnull final IUser aUser)
  {}

  public void onUserDeleted (@Nonnull final IUser aUser)
  {}

  public void onUserUndeleted (@Nonnull final IUser aUser)
  {}

  public void onUserEnabled (@Nonnull final IUser aUser, final boolean bEnabled)
  {}
}
