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
package com.helger.photon.security.auth;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.basic.auth.credentials.IAuthCredentials;
import com.helger.photon.basic.auth.subject.IAuthCredentialToSubjectResolverSPI;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.credentials.ITokenCredentials;

/**
 * Implementation of {@link IAuthCredentialToSubjectResolverSPI} supporting
 * {@link ITokenCredentials} and the resolution via the global
 * {@link AppTokenManager}.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public class AppTokenAuthCredentialToSubjectResolverSPI implements IAuthCredentialToSubjectResolverSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof ITokenCredentials;
  }

  @Nullable
  public IAppToken getSubjectFromCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    final ITokenCredentials aATC = (ITokenCredentials) aCredentials;
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    return aAppTokenMgr.getAppTokenOfTokenString (aATC.getTokenString ());
  }
}
