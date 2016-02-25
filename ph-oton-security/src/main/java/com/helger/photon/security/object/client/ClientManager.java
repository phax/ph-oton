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
package com.helger.photon.security.object.client;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.app.dao.impl.AbstractMapBasedWALDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.app.dao.impl.EDAOActionType;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.basic.object.client.CClient;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientResolver;
import com.helger.photon.security.object.ObjectHelper;

/**
 * Manages all available clients.
 *
 * @author Philip Helger
 */
public class ClientManager extends AbstractMapBasedWALDAO <IClient, Client> implements IClientResolver
{
  private static final String ELEMENT_ITEM = "client";

  private final CallbackList <IClientModificationCallback> m_aCallbacks = new CallbackList <> ();

  public ClientManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (Client.class, sFilename, ELEMENT_ITEM);
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    internalAddItem (new Client (CClient.GLOBAL_CLIENT, CClient.GLOBAL_CLIENT_NAME), EDAOActionType.CREATE);
    return EChange.CHANGED;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IClientModificationCallback> getClientModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nullable
  public IClient createClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    final Client aClient = new Client (sClientID, sDisplayName);

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (containsWithID (sClientID))
        return null;

      internalAddItem (aClient, EDAOActionType.CREATE);
      markAsChanged (aClient, EDAOActionType.CREATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (Client.OT, aClient.getID (), sDisplayName);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onClientCreated (aClient));

    return aClient;
  }

  @Nonnull
  public EChange updateClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    final Client aClient = getOfID (sClientID);
    if (aClient == null)
    {
      AuditHelper.onAuditModifyFailure (Client.OT, sClientID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aClient.setDisplayName (sDisplayName));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aClient);
      markAsChanged (aClient, EDAOActionType.UPDATE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Client.OT, "all", sClientID, sDisplayName);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onClientUpdated (aClient));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteClient (@Nullable final String sClientID)
  {
    final Client aDeletedClient = getOfID (sClientID);
    if (aDeletedClient == null)
    {
      AuditHelper.onAuditDeleteFailure (Client.OT, "no-such-object-id", sClientID);
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (ObjectHelper.setDeletionNow (aDeletedClient).isUnchanged ())
      {
        AuditHelper.onAuditDeleteFailure (Client.OT, "already-deleted", sClientID);
        return EChange.UNCHANGED;
      }
      markAsChanged (aDeletedClient, EDAOActionType.DELETE);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Client.OT, sClientID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onClientDeleted (aDeletedClient));

    return EChange.CHANGED;
  }

  public boolean hasAnyClient ()
  {
    return containsAny ();
  }

  public boolean hasAnyClientExceptGlobal ()
  {
    return containsAny (c -> !c.isGlobalClient ());
  }

  @Nonnegative
  public int getClientCount ()
  {
    return getCount ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IClient> getAllClients ()
  {
    return getAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllClientIDs ()
  {
    return getAllIDs ();
  }

  @Nullable
  public IClient getClientOfID (@Nullable final String sID)
  {
    // Change return type
    return getOfID (sID);
  }

  public boolean containsClientWithID (@Nullable final String sID)
  {
    return containsWithID (sID);
  }

  /**
   * Check if all passed client IDs are contained
   *
   * @param aIDs
   *        The IDs to be checked. May be <code>null</code>.
   * @return <code>true</code> if either an empty collection was passed or if
   *         really all passed client IDs are contained!
   */
  public boolean containsAllClientsWithID (@Nullable final Collection <String> aIDs)
  {
    if (aIDs != null)
      for (final String sClientID : aIDs)
        if (!containsWithID (sClientID))
          return false;
    return true;
  }
}
