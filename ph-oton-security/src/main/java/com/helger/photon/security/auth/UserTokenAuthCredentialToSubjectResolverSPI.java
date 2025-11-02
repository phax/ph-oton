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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.style.IsSPIImplementation;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.credentials.ITokenCredentials;
import com.helger.photon.security.token.user.IUserToken;
import com.helger.photon.security.token.user.IUserTokenManager;
import com.helger.security.authentication.credentials.IAuthCredentialToSubjectResolverSPI;
import com.helger.security.authentication.credentials.IAuthCredentials;

/**
 * Implementation of {@link IAuthCredentialToSubjectResolverSPI} supporting
 * {@link ITokenCredentials} and the resolution via the global
 * {@link IUserTokenManager}.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public class UserTokenAuthCredentialToSubjectResolverSPI implements IAuthCredentialToSubjectResolverSPI
{
  public boolean supportsCredentials (@NonNull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof ITokenCredentials;
  }

  @Nullable
  public IUserToken getSubjectFromCredentials (@NonNull final IAuthCredentials aCredentials)
  {
    final ITokenCredentials aATC = (ITokenCredentials) aCredentials;
    final IUserTokenManager aUserTokenMgr = PhotonSecurityManager.getUserTokenMgr ();
    return aUserTokenMgr.getUserTokenOfTokenString (aATC.getTokenString ());
  }
}
