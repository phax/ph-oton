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
package com.helger.photon.security.auth;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.IsSPIImplementation;
import com.helger.photon.basic.auth.credentials.CredentialValidationResult;
import com.helger.photon.basic.auth.credentials.IAuthCredentialValidatorSPI;
import com.helger.photon.basic.auth.credentials.IAuthCredentials;
import com.helger.photon.security.login.ELoginResult;
import com.helger.photon.security.mgr.PhotonSecurityManager;
import com.helger.photon.security.token.app.AppTokenManager;
import com.helger.photon.security.token.app.IAppToken;
import com.helger.photon.security.token.credentials.IAppTokenCredentials;

/**
 * An implementation of the {@link IAuthCredentialValidatorSPI} for
 * {@link IAppTokenCredentials} using the {@link AppTokenManager} to login
 * {@link IAppToken} objects.
 *
 * @author Philip Helger
 */
@IsSPIImplementation
public final class AppTokenAuthCredentialValidatorSPI implements IAuthCredentialValidatorSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof IAppTokenCredentials;
  }

  @Nonnull
  public CredentialValidationResult validateCredentials (@Nonnull final Locale aDisplayLocale, @Nonnull final IAuthCredentials aCredentials)
  {
    final IAppTokenCredentials aATC = (IAppTokenCredentials) aCredentials;
    final AppTokenManager aAppTokenMgr = PhotonSecurityManager.getAppTokenMgr ();
    if (aAppTokenMgr.getAppTokenOfTokenString (aATC.getTokenString ()) != null)
      return CredentialValidationResult.SUCCESS;

    // Credential validation failed
    return new CredentialValidationResult (ELoginResult.TOKEN_NOT_EXISTING.getDisplayText (aDisplayLocale));
  }
}
