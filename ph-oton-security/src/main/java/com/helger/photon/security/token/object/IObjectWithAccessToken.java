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
package com.helger.photon.security.token.object;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.photon.basic.auth.subject.IAuthSubject;
import com.helger.photon.basic.object.IObjectWithCustomAttrs;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * Base interface for a application and user token.
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
  List <? extends IAccessToken> getAllAccessTokens ();

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
  String getActiveTokenString ();
}
