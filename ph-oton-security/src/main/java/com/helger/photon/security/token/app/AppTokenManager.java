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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
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
import com.helger.photon.security.token.accesstoken.AccessToken;
import com.helger.photon.security.token.accesstoken.IAccessToken;

/**
 * A manager for {@link AppToken} objects.
 *
 * @author Philip Helger
 */
public final class AppTokenManager extends AbstractSimpleDAO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AppTokenManager.class);

  private static final String ELEMENT_ROOT = "apptokens";
  private static final String ELEMENT_ITEM = "apptoken";

  @GuardedBy ("m_aRWLock")
  private final Map <String, AppToken> m_aMap = new HashMap <> ();

  private final CallbackList <IAppTokenModificationCallback> m_aCallbacks = new CallbackList <> ();

  public AppTokenManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eAppToken : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addAppToken (MicroTypeConverter.convertToNative (eAppToken, AppToken.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final AppToken aAppToken : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aAppToken, ELEMENT_ITEM));
    return aDoc;
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

  private void _addAppToken (@Nonnull final AppToken aAppToken)
  {
    ValueEnforcer.notNull (aAppToken, "AppToken");

    final String sAppTokenID = aAppToken.getID ();
    if (m_aMap.containsKey (sAppTokenID))
      throw new IllegalArgumentException ("AppToken ID '" + sAppTokenID + "' is already in use!");
    m_aMap.put (sAppTokenID, aAppToken);
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

    m_aRWLock.writeLock ().lock ();
    try
    {
      _addAppToken (aAppToken);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
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
    final AppToken aAppToken = _getAppTokenOfID (sAppTokenID);
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
      markAsChanged ();
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
    final AppToken aAppToken = _getAppTokenOfID (sAppTokenID);
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
      markAsChanged ();
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
    final AppToken aAppToken = _getAppTokenOfID (sAppTokenID);
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
      markAsChanged ();
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
    final AppToken aAppToken = _getAppTokenOfID (sAppTokenID);
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
      markAsChanged ();
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
  public Collection <? extends AppToken> getAllAppTokens ()
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
  public Collection <? extends AppToken> getAllActiveAppTokens ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      final List <AppToken> ret = new ArrayList <> ();
      for (final AppToken aItem : m_aMap.values ())
        if (!aItem.isDeleted ())
          ret.add (aItem);
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public int getActiveAppTokenCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      int ret = 0;
      for (final AppToken aItem : m_aMap.values ())
        if (!aItem.isDeleted ())
          ret++;
      return ret;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean containsActiveAppToken ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      for (final AppToken aItem : m_aMap.values ())
        if (!aItem.isDeleted ())
          return true;
      return false;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  private AppToken _getAppTokenOfID (@Nullable final String sID)
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
  public IAppToken getAppTokenOfID (@Nullable final String sID)
  {
    return _getAppTokenOfID (sID);
  }

  @Nullable
  public IAppToken getActiveAppTokenOfID (@Nullable final String sID)
  {
    final IAppToken aAppToken = _getAppTokenOfID (sID);
    return aAppToken != null && !aAppToken.isDeleted () ? aAppToken : null;
  }

  public boolean containsAppTokenWithID (@Nullable final String sID)
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
  public IAppToken getAppTokenOfTokenString (@Nullable final String sTokenString)
  {
    if (StringHelper.hasNoText (sTokenString))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final IAppToken aAppToken : m_aMap.values ())
      {
        final IAccessToken aAccessToken = aAppToken.getActiveAccessToken ();
        if (aAccessToken != null && aAccessToken.getTokenString ().equals (sTokenString))
          return aAppToken;
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
      for (final IAppToken aAppToken : m_aMap.values ())
        for (final IAccessToken aAccessToken : aAppToken.getAllAccessTokens ())
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
