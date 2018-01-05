/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.dao.DAOException;
import com.helger.photon.basic.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.user.IUser;

/**
 * A manager for {@link UserToken} objects.
 *
 * @author Philip Helger
 */
public class UserTokenManager extends AbstractPhotonMapBasedWALDAO <IUserToken, UserToken>
{
  private final CallbackList <IUserTokenModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserTokenManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (UserToken.class, sFilename);
  }

  /**
   * @return The user token callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IUserTokenModificationCallback> userTokenModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  public UserToken createUserToken (@Nullable final String sTokenString,
                                    @Nullable final Map <String, String> aCustomAttrs,
                                    @Nonnull final IUser aUser)
  {
    final UserToken aUserToken = new UserToken (sTokenString, aCustomAttrs, aUser);

    m_aRWLock.writeLocked ( () -> {
      internalCreateItem (aUserToken);
    });
    AuditHelper.onAuditCreateSuccess (UserToken.OT, aUserToken.getID (), aCustomAttrs, aUser.getID ());

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreated (aUserToken));

    return aUserToken;
  }

  @Nonnull
  public EChange updateUserToken (@Nullable final String sUserTokenID,
                                  @Nullable final Map <String, String> aCustomAttrs)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      // tenant ID cannot be changed!
      eChange = eChange.or (aUserToken.attrs ().setAll (aCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserToken);
      internalUpdateItem (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT, sUserTokenID, aCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenUpdated (aUserToken));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteUserToken (@Nullable final String sUserTokenID)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditDeleteFailure (UserToken.OT, "no-such-id", sUserTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aUserToken).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (UserToken.OT, "already-deleted", aUserToken.getID ());
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (UserToken.OT, aUserToken.getID ());

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenDeleted (aUserToken));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange createNewAccessToken (@Nullable final String sUserTokenID,
                                       @Nonnull @Nonempty final String sRevocationUserID,
                                       @Nonnull final LocalDateTime aRevocationDT,
                                       @Nonnull @Nonempty final String sRevocationReason,
                                       @Nullable final String sTokenString)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, "no-such-id", sUserTokenID);
      return EChange.UNCHANGED;
    }

    AccessToken aAccessToken;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aUserToken.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason);
      aAccessToken = aUserToken.createNewAccessToken (sTokenString);
      BusinessObjectHelper.setLastModificationNow (aUserToken);
      internalUpdateItem (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT,
                                      "create-new-access-token",
                                      aUserToken.getID (),
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason,
                                      sTokenString);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreateAccessToken (aUserToken, aAccessToken));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange revokeAccessToken (@Nullable final String sUserTokenID,
                                    @Nonnull @Nonempty final String sRevocationUserID,
                                    @Nonnull final LocalDateTime aRevocationDT,
                                    @Nonnull @Nonempty final String sRevocationReason)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, "no-such-id", sUserTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserToken.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserToken.OT, "already-revoked", sUserTokenID);
        return EChange.UNCHANGED;
      }
      BusinessObjectHelper.setLastModificationNow (aUserToken);
      internalUpdateItem (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT,
                                      "revoke-access-token",
                                      aUserToken.getID (),
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenRevokeAccessToken (aUserToken));

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserToken> getAllUserTokens ()
  {
    return getAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserToken> getAllActiveUserTokens ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  @Nullable
  public IUserToken getUserTokenOfID (@Nullable final String sID)
  {
    return getOfID (sID);
  }

  @Nullable
  public IUserToken getUserTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    return findFirst (x -> sTokenString.equals (x.getActiveTokenString ()));
  }

  /**
   * Check if the passed token string was already used in this application. This
   * method considers all access token - revoked, expired or active.
   *
   * @param sTokenString
   *        The token string to check. May be <code>null</code>.
   * @return <code>true</code> if the token string is already used.
   */
  public boolean isAccessTokenUsed (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return false;

    return containsAny (x -> x.findFirstAccessToken (y -> y.getTokenString ().equals (sTokenString)) != null);
  }
}
