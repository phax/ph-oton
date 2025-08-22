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

import com.helger.annotation.style.IsSPIImplementation;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.credentials.ITokenCredentials;
import com.helger.photon.security.token.user.IUserToken;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.security.authentication.credentials.IAuthCredentialValidatorSPI;
import com.helger.security.authentication.credentials.IAuthCredentials;

import jakarta.annotation.Nonnull;

/**
 * An implementation of the {@link IAuthCredentialValidatorSPI} for
 * {@link ITokenCredentials} using the {@link IUserTokenManager} to login
 * {@link IUserToken} objects.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class UserTokenAuthCredentialValidatorSPI implements IAuthCredentialValidatorSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof ITokenCredentials;
  }

  @Nonnull
  public ELoginResult validateCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    final ITokenCredentials aATC = (ITokenCredentials) aCredentials;
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    final IUserToken aUserToken = aUserTokenMgr.getUserTokenOfTokenString (aATC.getTokenString ());
    if (aUserToken == null)
    {
      // Credential validation failed
      return ELoginResult.TOKEN_NOT_EXISTING;
    }
    if (aUserToken.isDeleted ())
    {
      // Credential validation failed
      return ELoginResult.TOKEN_NOT_EXISTING;
    }

    return ELoginResult.SUCCESS;
  }
}
