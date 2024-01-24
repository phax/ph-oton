/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.io.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.object.AccessTokenList;
import com.helger.photon.security.user.IUser;

/**
 * A manager for {@link UserToken} objects.
 *
 * @author Philip Helger
 */
public class UserTokenManager extends AbstractPhotonMapBasedWALDAO <IUserToken, UserToken> implements IUserTokenManager
{
  private final CallbackList <IUserTokenModificationCallback> m_aCallbacks = new CallbackList <> ();

  public UserTokenManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (UserToken.class, sFilename);
  }

  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IUserTokenModificationCallback> userTokenModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  public UserToken createUserToken (@Nullable final String sTokenString,
                                    @Nullable final Map <String, String> aCustomAttrs,
                                    @Nonnull final IUser aUser,
                                    @Nullable final String sDescription)
  {
    final UserToken aUserToken = new UserToken (sTokenString, aCustomAttrs, aUser, sDescription);

    m_aRWLock.writeLocked ( () -> internalCreateItem (aUserToken));
    AuditHelper.onAuditCreateSuccess (UserToken.OT, aUserToken.getID (), aCustomAttrs, aUser.getID (), sDescription);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreated (aUserToken));

    return aUserToken;
  }

  @Nonnull
  public EChange updateUserToken (@Nullable final String sUserTokenID,
                                  @Nullable final Map <String, String> aNewCustomAttrs,
                                  @Nullable final String sNewDescription)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, "set-all", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      // tenant ID cannot be changed!
      eChange = eChange.or (aUserToken.attrs ().setAll (aNewCustomAttrs));
      eChange = eChange.or (aUserToken.setDescription (sNewDescription));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aUserToken);
      internalUpdateItem (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT, "set-all", sUserTokenID, aNewCustomAttrs, sNewDescription);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenUpdated (sUserTokenID));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteUserToken (@Nullable final String sUserTokenID)
  {
    final UserToken aUserToken = getOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditDeleteFailure (UserToken.OT, sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (BusinessObjectHelper.setDeletionNow (aUserToken).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (UserToken.OT, sUserTokenID, "already-deleted");
        return EChange.UNCHANGED;
      }
      internalMarkItemDeleted (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (UserToken.OT, sUserTokenID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenDeleted (sUserTokenID));

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
      AuditHelper.onAuditModifyFailure (UserToken.OT, "create-new-access-token", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    final AccessToken aAccessToken;
    m_aRWLock.writeLock ().lock ();
    try
    {
      final AccessTokenList aAccessTokenList = aUserToken.getAccessTokenList ();
      aAccessTokenList.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason);
      aAccessToken = aAccessTokenList.createNewAccessToken (sTokenString);
      BusinessObjectHelper.setLastModificationNow (aUserToken);
      internalUpdateItem (aUserToken);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT,
                                      "create-new-access-token",
                                      sUserTokenID,
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason,
                                      sTokenString);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenCreateAccessToken (sUserTokenID, aAccessToken));

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
      AuditHelper.onAuditModifyFailure (UserToken.OT, "revoke-access-token", sUserTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aUserToken.getAccessTokenList ()
                    .revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason)
                    .isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (UserToken.OT, "revoke-access-token", sUserTokenID, "already-revoked");
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
                                      sUserTokenID,
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onUserTokenRevokeAccessToken (sUserTokenID));

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IUserToken> getAllActiveUserTokens ()
  {
    return getAll (x -> !x.isDeleted ());
  }

  @Nullable
  public IUserToken getUserTokenOfID (@Nullable final String sUserTokenID)
  {
    return getOfID (sUserTokenID);
  }

  @Nullable
  public IUserToken getUserTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    return findFirst (x -> sTokenString.equals (x.getAccessTokenList ().getActiveTokenString ()));
  }

  public boolean isAccessTokenUsed (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return false;

    return containsAny (x -> x.getAccessTokenList ()
                              .findFirstAccessToken (y -> y.getTokenString ().equals (sTokenString)) != null);
  }
}
