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
package com.helger.appbasics.auth.subject;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.appbasics.auth.credentials.IAuthCredentials;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.lang.ServiceLoaderUtils;

/**
 * This handler is used to resolve a subject from validated credentials. This is
 * necessary because the {@link IAuthCredentials} interface does not state
 * anything about the subject by default.
 *
 * @author Philip Helger
 */
@Immutable
public final class AuthCredentialToSubjectResolverManager
{
  private static volatile List <IAuthCredentialToSubjectResolverSPI> s_aHdlList;

  static
  {
    s_aHdlList = ServiceLoaderUtils.getAllSPIImplementations (IAuthCredentialToSubjectResolverSPI.class);
    // list may be empty...
  }

  private AuthCredentialToSubjectResolverManager ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static List <IAuthCredentialToSubjectResolverSPI> getAllAuthCredentialToSubjectResolvers ()
  {
    return CollectionHelper.newList (s_aHdlList);
  }

  /**
   * Resolve the {@link IAuthSubject} from the specified credentials.
   *
   * @param aCredentials
   *        The credentials to be transformed.
   * @return <code>null</code> if no subject matches the specified credentials.
   */
  @Nullable
  public static IAuthSubject getSubjectFromCredentials (@Nonnull final IAuthCredentials aCredentials)
  {
    for (final IAuthCredentialToSubjectResolverSPI aHdl : s_aHdlList)
      if (aHdl.supportsCredentials (aCredentials))
      {
        final IAuthSubject aSubject = aHdl.getSubjectFromCredentials (aCredentials);
        if (aSubject != null)
          return aSubject;
      }
    return null;
  }
}
