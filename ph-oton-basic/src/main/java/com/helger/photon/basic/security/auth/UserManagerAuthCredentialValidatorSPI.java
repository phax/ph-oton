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
package com.helger.photon.basic.security.auth;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.annotations.IsSPIImplementation;
import com.helger.photon.basic.auth.credentials.CredentialValidationResult;
import com.helger.photon.basic.auth.credentials.IAuthCredentialValidatorSPI;
import com.helger.photon.basic.auth.credentials.IAuthCredentials;
import com.helger.photon.basic.auth.credentials.userpw.UserNamePasswordCredentials;
import com.helger.photon.basic.security.login.ELoginResult;
import com.helger.photon.basic.security.login.LoggedInUserManager;

@IsSPIImplementation
public final class UserManagerAuthCredentialValidatorSPI implements IAuthCredentialValidatorSPI
{
  public boolean supportsCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    return aCredentials instanceof UserNamePasswordCredentials;
  }

  @Nonnull
  public CredentialValidationResult validateCredentials (@Nonnull final Locale aDisplayLocale,
                                                         @Nonnull final IAuthCredentials aCredentials)
  {
    final UserNamePasswordCredentials aUPC = (UserNamePasswordCredentials) aCredentials;
    final ELoginResult eLoginResult = LoggedInUserManager.getInstance ().loginUser (aUPC.getUserName (),
                                                                                    aUPC.getPassword ());
    if (eLoginResult.isSuccess ())
      return CredentialValidationResult.SUCCESS;

    // Credential validation failed
    return new CredentialValidationResult (eLoginResult.getDisplayText (aDisplayLocale));
  }
}
