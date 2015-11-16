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
package com.helger.photon.security.token.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.joda.time.LocalDateTime;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.security.object.ObjectHelper;
import com.helger.photon.security.token.accesstoken.IAccessToken;
import com.helger.photon.security.token.app.IAppToken;

/**
 * A manager for {@link UserToken} objects.
 *
 * @author Philip Helger
 */
public final class UserTokenManager extends AbstractSimpleDAO
{
  private static final String ELEMENT_ROOT = "usertokens";
  private static final String ELEMENT_ITEM = "usertoken";

  private final Map <String, UserToken> m_aMap = new HashMap <String, UserToken> ();

  public UserTokenManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eUserToken : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addUserToken (MicroTypeConverter.convertToNative (eUserToken, UserToken.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final UserToken aUserToken : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aUserToken, ELEMENT_ITEM));
    return aDoc;
  }

  private void _addUserToken (@Nonnull final UserToken aUserToken)
  {
    ValueEnforcer.notNull (aUserToken, "UserToken");

    final String sUserTokenID = aUserToken.getID ();
    if (m_aMap.containsKey (sUserTokenID))
      throw new IllegalArgumentException ("UserToken ID '" + sUserTokenID + "' is already in use!");
    m_aMap.put (sUserTokenID, aUserToken);
  }

  @Nonnull
  public UserToken createUserToken (@Nullable final String sTokenString,
                                    @Nullable final Map <String, String> aCustomAttrs,
                                    @Nonnull final IAppToken aAppToken,
                                    @Nonnull @Nonempty final String sUserName)
  {
    final UserToken aUserToken = new UserToken (sTokenString, aCustomAttrs, aAppToken, sUserName);

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addUserToken (aUserToken);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (UserToken.OT, aUserToken.getID (), aCustomAttrs, aAppToken.getID (), sUserName);
    return aUserToken;
  }

  @Nonnull
  public EChange updateUserToken (@Nullable final String sUserTokenID,
                                  @Nullable final Map <String, String> aCustomAttrs,
                                  @Nonnull @Nonempty final String sUserName)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      final UserToken aUserToken = m_aMap.get (sUserTokenID);
      if (aUserToken == null)
      {
        AuditHelper.onAuditModifyFailure (UserToken.OT, sUserTokenID, "no-such-id");
        return EChange.UNCHANGED;
      }

      EChange eChange = EChange.UNCHANGED;
      // client ID cannot be changed!
      eChange = eChange.or (aUserToken.getMutableAttributes ().clear ());
      eChange = eChange.or (aUserToken.getMutableAttributes ().setAttributes (aCustomAttrs));
      eChange = eChange.or (aUserToken.setUserName (sUserName));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aUserToken);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT, sUserTokenID, aCustomAttrs, sUserName);
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteUserToken (@Nullable final String sUserTokenID)
  {
    final UserToken aUserToken = _getUserTokenOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditDeleteFailure (UserToken.OT, "no-such-id", sUserTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setDeletionNow (aUserToken).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (UserToken.OT, "already-deleted", aUserToken.getID ());
        return EChange.UNCHANGED;
      }
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (UserToken.OT, aUserToken.getID ());
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange createNewAccessToken (@Nullable final String sUserTokenID,
                                       @Nonnull @Nonempty final String sRevocationUserID,
                                       @Nonnull final LocalDateTime aRevocationDT,
                                       @Nonnull @Nonempty final String sRevocationReason,
                                       @Nullable final String sTokenString)
  {
    final UserToken aUserToken = _getUserTokenOfID (sUserTokenID);
    if (aUserToken == null)
    {
      AuditHelper.onAuditModifyFailure (UserToken.OT, "no-such-id", sUserTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      aUserToken.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason);
      aUserToken.createNewAccessToken (sTokenString);
      ObjectHelper.setLastModificationNow (aUserToken);
      markAsChanged ();
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
    return EChange.CHANGED;
  }

  @Nonnull
  public EChange revokeAccessToken (@Nullable final String sUserTokenID,
                                    @Nonnull @Nonempty final String sRevocationUserID,
                                    @Nonnull final LocalDateTime aRevocationDT,
                                    @Nonnull @Nonempty final String sRevocationReason)
  {
    final UserToken aUserToken = _getUserTokenOfID (sUserTokenID);
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
      ObjectHelper.setLastModificationNow (aUserToken);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (UserToken.OT, "revoke-access-token", aUserToken.getID (), sRevocationUserID, aRevocationDT, sRevocationReason);
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends UserToken> getAllUserTokens ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aMap.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends UserToken> getAllActiveUserTokens ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <UserToken> ret = new ArrayList <> ();
      for (final UserToken aItem : m_aMap.values ())
        if (!aItem.isDeleted ())
          ret.add (aItem);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  private UserToken _getUserTokenOfID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IUserToken getUserTokenOfID (@Nullable final String sID)
  {
    return _getUserTokenOfID (sID);
  }

  public boolean containsUserTokenWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IUserToken getUserTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final IUserToken aUserToken : m_aMap.values ())
      {
        final IAccessToken aAccessToken = aUserToken.getActiveAccessToken ();
        if (aAccessToken != null && aAccessToken.getTokenString ().equals (sTokenString))
          return aUserToken;
      }
      return null;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
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

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final IUserToken aUserToken : m_aMap.values ())
        for (final IAccessToken aAccessToken : aUserToken.getAllAccessTokens ())
          if (aAccessToken.getTokenString ().equals (sTokenString))
            return true;
      return false;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }
}
