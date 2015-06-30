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
package com.helger.photon.basic.auth.credentials;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.exception.InitializationException;
import com.helger.commons.lang.ServiceLoaderHelper;
import com.helger.commons.string.StringHelper;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Immutable
public final class AuthCredentialValidatorManager
{
  private static List <IAuthCredentialValidatorSPI> s_aHdlList;

  static
  {
    s_aHdlList = ServiceLoaderHelper.getAllSPIImplementations (IAuthCredentialValidatorSPI.class);
    if (s_aHdlList.isEmpty ())
      throw new InitializationException ("No class implementing " + IAuthCredentialValidatorSPI.class + " was found!");
  }

  private AuthCredentialValidatorManager ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static List <IAuthCredentialValidatorSPI> getAllAuthCredentialValidators ()
  {
    return CollectionHelper.newList (s_aHdlList);
  }

  @Nonnull
  @SuppressFBWarnings ("RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE")
  public static CredentialValidationResult validateCredentials (@Nonnull final Locale aDisplayLocale,
                                                                @Nonnull final IAuthCredentials aCredentials)
  {
    // Collect all strings of all supporting credential validators
    final List <String> aFailedMessages = new ArrayList <String> ();

    // Check all credential handlers if the can handle the passed credentials
    for (final IAuthCredentialValidatorSPI aHdl : s_aHdlList)
      if (aHdl.supportsCredentials (aCredentials))
      {
        final CredentialValidationResult aResult = aHdl.validateCredentials (aDisplayLocale, aCredentials);
        if (aResult == null)
          throw new IllegalStateException ("validateCredentials returned a null object from " +
                                           aHdl +
                                           " for credentials " +
                                           aCredentials);
        if (aResult.isSuccess ())
        {
          // This validator successfully validated the passed credentials
          return aResult;
        }
        aFailedMessages.add (aResult.getErrorMessage ());
      }

    if (aFailedMessages.isEmpty ())
      aFailedMessages.add ("No credential validator supported the provided credentials: " + aCredentials);

    return new CredentialValidationResult (StringHelper.getImplodedNonEmpty ('\n', aFailedMessages));
  }
}
