/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.collection.ext.ICommonsSet;
import com.helger.commons.state.EChange;
import com.helger.photon.basic.app.dao.impl.AbstractMapBasedWALDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
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
  public ClientManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (Client.class, sFilename);
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    internalCreateItem (new Client (CClient.GLOBAL_CLIENT, CClient.GLOBAL_CLIENT_NAME));
    return EChange.CHANGED;
  }

  @Nullable
  public IClient createClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    if (containsWithID (sClientID))
      return null;

    final Client aClient = new Client (sClientID, sDisplayName);

    m_aRWLock.writeLocked ( () -> {
      internalCreateItem (aClient);
    });
    AuditHelper.onAuditCreateSuccess (Client.OT, aClient.getID (), sDisplayName);

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
      internalUpdateItem (aClient);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Client.OT, "all", sClientID, sDisplayName);

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
      internalMarkItemDeleted (aDeletedClient);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Client.OT, sClientID);

    return EChange.CHANGED;
  }

  public boolean hasAnyClient ()
  {
    return containsAny ();
  }

  public boolean hasAnyClientExceptGlobal ()
  {
    return containsAny (x -> !x.isGlobalClient ());
  }

  @Nonnegative
  public int getClientCount ()
  {
    return getCount ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <? extends IClient> getAllClients ()
  {
    return getAll ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllClientIDs ()
  {
    return getAllIDs ();
  }

  @Nullable
  public IClient getClientOfID (@Nullable final String sID)
  {
    return getOfID (sID);
  }
}
