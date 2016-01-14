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
package com.helger.photon.basic.auth.identify;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.photon.basic.auth.credentials.AuthCredentialValidatorManager;
import com.helger.photon.basic.auth.credentials.CredentialValidationResult;
import com.helger.photon.basic.auth.credentials.IAuthCredentials;
import com.helger.photon.basic.auth.subject.AuthCredentialToSubjectResolverManager;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.auth.token.AuthTokenRegistry;
import com.helger.photon.basic.auth.token.IAuthToken;

/**
 * This is the main class for creating an {@link IAuthToken} from credentials.
 *
 * @author Philip Helger
 */
@Immutable
public final class AuthIdentificationManager
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AuthIdentificationManager.class);

  private AuthIdentificationManager ()
  {}

  /**
   * Validate the login credentials, try to resolve the subject and create a
   * token upon success.
   *
   * @param aDisplayLocale
   *        Display locale.
   * @param aCredentials
   *        The credentials to validate. If <code>null</code> it is treated as
   *        error.
   * @return Never <code>null</code>.
   */
  @Nonnull
  public static AuthIdentificationResult validateLoginCredentialsAndCreateToken (@Nonnull final Locale aDisplayLocale,
                                                                                 @Nonnull final IAuthCredentials aCredentials)
  {
    ValueEnforcer.notNull (aCredentials, "Credentials");

    // validate credentials
    final CredentialValidationResult aValidationResult = AuthCredentialValidatorManager.validateCredentials (aDisplayLocale,
                                                                                                             aCredentials);
    if (aValidationResult.isFailure ())
    {
      s_aLogger.warn ("Credentials have been rejected: " + aCredentials);
      return new AuthIdentificationResult (aValidationResult);
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Credentials have been accepted: " + aCredentials);

    // try to get AuthSubject from passed credentials
    final IAuthSubject aSubject = AuthCredentialToSubjectResolverManager.getSubjectFromCredentials (aCredentials);
    if (aSubject != null)
      s_aLogger.info ("Credentials " + aCredentials + " correspond to subject " + aSubject);
    else
      s_aLogger.error ("Failed to resolve credentials " + aCredentials + " to an auth subject!");

    // Create the identification element
    final AuthIdentification aIdentification = new AuthIdentification (aSubject);

    // create the token (without expiration seconds)
    final IAuthToken aNewAuthToken = AuthTokenRegistry.getInstance ()
                                                      .createToken (aIdentification,
                                                                    IAuthToken.EXPIRATION_SECONDS_INFINITE);
    return new AuthIdentificationResult (aNewAuthToken);
  }
}
