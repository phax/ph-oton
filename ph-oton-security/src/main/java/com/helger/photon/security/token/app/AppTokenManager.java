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
package com.helger.photon.security.token.app;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.state.EChange;
import com.helger.commons.string.StringHelper;
import com.helger.photon.basic.app.dao.impl.AbstractMapBasedWALDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.app.dao.impl.EDAOActionType;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.security.object.ObjectHelper;
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * A manager for {@link AppToken} objects.
 *
 * @author Philip Helger
 */
public final class AppTokenManager extends AbstractMapBasedWALDAO <IAppToken, AppToken>
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AppTokenManager.class);

  private static final String ELEMENT_ITEM = "apptoken";

  private final CallbackList <IAppTokenModificationCallback> m_aCallbacks = new CallbackList <> ();

  public AppTokenManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (AppToken.class, sFilename, ELEMENT_ITEM);
  }

  /**
   * @return The app token callback list. Never <code>null</code>.
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IAppTokenModificationCallback> getAppTokenModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  public AppToken createAppToken (@Nullable final String sTokenString,
                                  @Nullable final Map <String, String> aCustomAttrs,
                                  @Nonnull @Nonempty final String sOwnerName,
                                  @Nullable final String sOwnerURL,
                                  @Nullable final String sOwnerContact,
                                  @Nullable final String sOwnerContactEmail)
  {
    final AppToken aAppToken = new AppToken (sTokenString,
                                             aCustomAttrs,
                                             sOwnerName,
                                             sOwnerURL,
                                             sOwnerContact,
                                             sOwnerContactEmail);

    m_aRWLock.writeLocked ( () -> {
      internalAddItem (aAppToken, EDAOActionType.CREATE);
      markAsChanged (aAppToken, EDAOActionType.CREATE);
    });
    AuditHelper.onAuditCreateSuccess (AppToken.OT,
                                      aAppToken.getID (),
                                      sTokenString,
                                      aCustomAttrs,
                                      sOwnerName,
                                      sOwnerURL,
                                      sOwnerContact,
                                      sOwnerContactEmail);

    // Execute callback as the very last action
    for (final IAppTokenModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAppTokenCreated (aAppToken);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAppTokenCreated callback on " + aAppToken.toString (), t);
      }

    return aAppToken;
  }

  @Nonnull
  public EChange updateAppToken (@Nullable final String sAppTokenID,
                                 @Nullable final Map <String, String> aCustomAttrs,
                                 @Nonnull @Nonempty final String sOwnerName,
                                 @Nullable final String sOwnerURL,
                                 @Nullable final String sOwnerContact,
                                 @Nullable final String sOwnerContactEmail)
  {
    final AppToken aAppToken = getOfID (sAppTokenID);
    if (aAppToken == null)
    {
      AuditHelper.onAuditModifyFailure (AppToken.OT, sAppTokenID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      // client ID cannot be changed!
      eChange = eChange.or (aAppToken.setOwnerName (sOwnerName));
      eChange = eChange.or (aAppToken.setOwnerURL (sOwnerURL));
      eChange = eChange.or (aAppToken.setOwnerContact (sOwnerContact));
      eChange = eChange.or (aAppToken.setOwnerContactEmail (sOwnerContactEmail));
      eChange = eChange.or (aAppToken.getMutableAttributes ().clear ());
      eChange = eChange.or (aAppToken.getMutableAttributes ().setAttributes (aCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aAppToken);
      markAsChanged (aAppToken, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (AppToken.OT,
                                      sAppTokenID,
                                      aCustomAttrs,
                                      sOwnerName,
                                      sOwnerURL,
                                      sOwnerContact,
                                      sOwnerContactEmail);

    // Execute callback as the very last action
    for (final IAppTokenModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAppTokenUpdated (aAppToken);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAppTokenUpdated callback on " + aAppToken.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteAppToken (@Nullable final String sAppTokenID)
  {
    final AppToken aAppToken = getOfID (sAppTokenID);
    if (aAppToken == null)
    {
      AuditHelper.onAuditDeleteFailure (AppToken.OT, "no-such-id", sAppTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setDeletionNow (aAppToken).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (AppToken.OT, "already-deleted", aAppToken.getID ());
        return EChange.UNCHANGED;
      }
      markAsChanged (aAppToken, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (AppToken.OT, aAppToken.getID ());

    // Execute callback as the very last action
    for (final IAppTokenModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAppTokenDeleted (aAppToken);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAppTokenDeleted callback on " + aAppToken.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange createNewAccessToken (@Nullable final String sAppTokenID,
                                       @Nonnull @Nonempty final String sRevocationUserID,
                                       @Nonnull final LocalDateTime aRevocationDT,
                                       @Nonnull @Nonempty final String sRevocationReason,
                                       @Nullable final String sTokenString)
  {
    final AppToken aAppToken = getOfID (sAppTokenID);
    if (aAppToken == null)
    {
      AuditHelper.onAuditModifyFailure (AppToken.OT, "no-such-id", sAppTokenID);
      return EChange.UNCHANGED;
    }

    AccessToken aAccessToken;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aAppToken.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason);
      aAccessToken = aAppToken.createNewAccessToken (sTokenString);
      ObjectHelper.setLastModificationNow (aAppToken);
      markAsChanged (aAppToken, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (AppToken.OT,
                                      "create-new-access-token",
                                      aAppToken.getID (),
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason,
                                      sTokenString);

    // Execute callback as the very last action
    for (final IAppTokenModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAppTokenCreateAccessToken (aAppToken, aAccessToken);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAppTokenCreateAccessToken callback on " +
                         aAppToken.toString () +
                         " and " +
                         aAccessToken.toString (),
                         t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange revokeAccessToken (@Nullable final String sAppTokenID,
                                    @Nonnull @Nonempty final String sRevocationUserID,
                                    @Nonnull final LocalDateTime aRevocationDT,
                                    @Nonnull @Nonempty final String sRevocationReason)
  {
    final AppToken aAppToken = getOfID (sAppTokenID);
    if (aAppToken == null)
    {
      AuditHelper.onAuditModifyFailure (AppToken.OT, "no-such-id", sAppTokenID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aAppToken.revokeActiveAccessToken (sRevocationUserID, aRevocationDT, sRevocationReason).isUnchanged ())
      {
        AuditHelper.onAuditModifyFailure (AppToken.OT, "already-revoked", sAppTokenID);
        return EChange.UNCHANGED;
      }
      ObjectHelper.setLastModificationNow (aAppToken);
      markAsChanged (aAppToken, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (AppToken.OT,
                                      "revoke-access-token",
                                      aAppToken.getID (),
                                      sRevocationUserID,
                                      aRevocationDT,
                                      sRevocationReason);

    // Execute callback as the very last action
    for (final IAppTokenModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onAppTokenRevokeAccessToken (aAppToken);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onAppTokenRevokeAccessToken callback on " + aAppToken.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IAppToken> getAllAppTokens ()
  {
    return getAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IAppToken> getAllActiveAppTokens ()
  {
    return getAll (aItem -> !aItem.isDeleted ());
  }

  @Nonnegative
  public int getActiveAppTokenCount ()
  {
    return getCount (aItem -> !aItem.isDeleted ());
  }

  public boolean containsActiveAppToken ()
  {
    return containsAny (aItem -> !aItem.isDeleted ());
  }

  @Nullable
  public IAppToken getAppTokenOfID (@Nullable final String sID)
  {
    return getOfID (sID);
  }

  @Nullable
  public IAppToken getActiveAppTokenOfID (@Nullable final String sID)
  {
    final IAppToken aAppToken = getOfID (sID);
    return aAppToken != null && !aAppToken.isDeleted () ? aAppToken : null;
  }

  public boolean containsAppTokenWithID (@Nullable final String sID)
  {
    return containsWithID (sID);
  }

  @Nullable
  public IAppToken getAppTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    return getFirst (aAppToken -> {
      final IAccessToken aAccessToken = aAppToken.getActiveAccessToken ();
      return aAccessToken != null && aAccessToken.getTokenString ().equals (sTokenString);
    });
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

    return containsAny (aAppToken -> {
      for (final IAccessToken aAccessToken : aAppToken.getAllAccessTokens ())
        if (aAccessToken.getTokenString ().equals (sTokenString))
          return true;
      return false;
    });
  }
}
