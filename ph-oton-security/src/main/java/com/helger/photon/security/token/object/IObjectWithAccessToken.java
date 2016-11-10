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
package com.helger.photon.security.token.object;

import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.object.IObjectWithCustomAttrs;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Base interface for all kind of access tokens.
 *
 * @author Philip Helger
 */
public interface IObjectWithAccessToken extends IObjectWithCustomAttrs, IAuthSubject
{
  /**
   * @return A list of all tokens used by this user. The latest, active token is
   *         always the last one. Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  @ReturnsMutableCopy
  ICommonsList <? extends IAccessToken> getAllAccessTokens ();

  /**
   * Find the first access token matching the supplied filter.
   *
   * @param aFilter
   *        The filter to use. May be <code>null</code>.
   * @return The first access token (if filter is <code>null</code>) or the
   *         first matching filter or <code>null</code> if no token is present,
   *         or if no token matches the supplied filter.
   */
  @Nullable
  IAccessToken findFirstAccessToken (@Nullable Predicate <? super IAccessToken> aFilter);

  /**
   * @return The main token to access this application from the outside. May be
   *         <code>null</code> if all are revoked.
   */
  @Nullable
  IAccessToken getActiveAccessToken ();

  /**
   * @return The token string of the active access token. May be
   *         <code>null</code> if no active access token is present (which can
   *         be the case if all access tokens were revoked or if no access token
   *         is present).
   * @see #getActiveAccessToken()
   */
  @Nullable
  default String getActiveTokenString ()
  {
    final IAccessToken aActiveAccessToken = getActiveAccessToken ();
    return aActiveAccessToken == null ? null : aActiveAccessToken.getTokenString ();
  }
}
