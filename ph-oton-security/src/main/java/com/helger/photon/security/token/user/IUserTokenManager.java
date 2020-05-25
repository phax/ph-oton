/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.security.token.user;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.photon.app.mgr.IPhotonManager;
import com.helger.photon.security.user.IUser;

/**
 * Base interface for a manager for {@link UserToken} objects.
 *
 * @author Philip Helger
 * @since 8.2.2
 */
public interface IUserTokenManager extends IPhotonManager <IUserToken>
{
  /**
   * Create a new user token.
   *
   * @param sTokenString
   *        The existing token string. May be <code>null</code> in which case a
   *        new token string is created.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @param aUser
   *        The user it belongs to. May not be <code>null</code>.
   * @return The created user token. Never <code>null</code>.
   */
  @Nonnull
  UserToken createUserToken (@Nullable String sTokenString, @Nullable Map <String, String> aCustomAttrs, @Nonnull IUser aUser);

  /**
   * Update an existing token.
   *
   * @param sUserTokenID
   *        The ID of the token to be updated.
   * @param aCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  EChange updateUserToken (@Nullable String sUserTokenID, @Nullable Map <String, String> aCustomAttrs);

  /**
   * Delete an existing token.
   *
   * @param sUserTokenID
   *        The ID of the token to be deleted.
   * @return {@link EChange}
   */
  @Nonnull
  EChange deleteUserToken (@Nullable String sUserTokenID);

  /**
   * Create a new access token for an existing user token
   *
   * @param sUserTokenID
   *        The ID of the token to be modified.
   * @param sRevocationUserID
   *        Who revoked the old access token?
   * @param aRevocationDT
   *        When was it revoked.
   * @param sRevocationReason
   *        Why was it revoked.
   * @param sTokenString
   *        The new token string. May be <code>null</code> in which case a new
   *        token string is created.
   * @return {@link EChange}
   */
  @Nonnull
  EChange createNewAccessToken (@Nullable String sUserTokenID,
                                @Nonnull @Nonempty String sRevocationUserID,
                                @Nonnull LocalDateTime aRevocationDT,
                                @Nonnull @Nonempty String sRevocationReason,
                                @Nullable String sTokenString);

  /**
   * Revoke the latest access token of an existing user token. Does not create a
   * new access token.
   *
   * @param sUserTokenID
   *        The ID of the token to be modified.
   * @param sRevocationUserID
   *        Who revoked the old access token?
   * @param aRevocationDT
   *        When was it revoked.
   * @param sRevocationReason
   *        Why was it revoked.
   * @return {@link EChange}
   */
  @Nonnull
  EChange revokeAccessToken (@Nullable String sUserTokenID,
                             @Nonnull @Nonempty String sRevocationUserID,
                             @Nonnull LocalDateTime aRevocationDT,
                             @Nonnull @Nonempty String sRevocationReason);

  /**
   * @return All contained, non-deleted user token. Never <code>null</code> but
   *         maybe empty.
   */
  @Nonnull
  @ReturnsMutableCopy
  ICommonsList <IUserToken> getAllActiveUserTokens ();

  /**
   * Get the user token with the passed ID
   *
   * @param sID
   *        The ID to search. May be <code>null</code>.
   * @return <code>null</code> if no such user token exists.
   */
  @Nullable
  IUserToken getUserTokenOfID (@Nullable String sID);

  /**
   * Find the user token that has the provided access token string.
   *
   * @param sTokenString
   *        The token string to search.
   * @return <code>null</code> if no user token uses this access token string.
   */
  @Nullable
  IUserToken getUserTokenOfTokenString (@Nullable String sTokenString);

  /**
   * Check if the passed token string was already used in this application. This
   * method considers all access token - revoked, expired or active.
   *
   * @param sTokenString
   *        The token string to check. May be <code>null</code>.
   * @return <code>true</code> if the token string is already used.
   */
  boolean isAccessTokenUsed (@Nullable final String sTokenString);
}
