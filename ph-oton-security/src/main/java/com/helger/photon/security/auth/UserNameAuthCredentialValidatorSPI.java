/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.login.LoggedInUserManager;
import com.helger.photon.security.user.IUser;
import com.helger.security.authentication.credentials.IAuthCredentialValidatorSPI;
import com.helger.security.authentication.credentials.IAuthCredentials;
import com.helger.security.authentication.credentials.usernamepw.IUserNamePasswordCredentials;

/**
 * An implementation of the {@link IAuthCredentialValidatorSPI} for
 * {@link IUserNamePasswordCredentials} using the {@link LoggedInUserManager} to
 * login {@link IUser} objects.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class UserNameAuthCredentialValidatorSPI implements IAuthCredentialValidatorSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof IUserNamePasswordCredentials;
  }

  @Nonnull
  public ELoginResult validateCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    final IUserNamePasswordCredentials aUPC = (IUserNamePasswordCredentials) aCredentials;
    return LoggedInUserManager.getInstance ().loginUser (aUPC.getUserName (), aUPC.getPassword ());
  }
}
