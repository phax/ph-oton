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
package com.helger.photon.security.auth;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.user.IUser;
import com.helger.photon.security.user.IUserManager;
import com.helger.photon.security.user.UserManager;
import com.helger.security.authentication.credentials.IAuthCredentialToSubjectResolverSPI;
import com.helger.security.authentication.credentials.IAuthCredentials;
import com.helger.security.authentication.credentials.usernamepw.IUserNamePasswordCredentials;

/**
 * Implementation of {@link IAuthCredentialToSubjectResolverSPI} supporting
 * {@link IUserNamePasswordCredentials} and the resolution via the global
 * {@link UserManager}.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public class UserNameAuthCredentialToSubjectResolverSPI implements IAuthCredentialToSubjectResolverSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof IUserNamePasswordCredentials;
  }

  @Nullable
  public IUser getSubjectFromCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    final IUserNamePasswordCredentials aUPC = (IUserNamePasswordCredentials) aCredentials;
    final IUserManager aUserMgr = PhotonSecurityManager.getUserMgr ();
    return aUserMgr.getUserOfLoginName (aUPC.getUserName ());
  }
}
