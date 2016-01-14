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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
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
public class ClientManager extends AbstractSimpleDAO implements IClientResolver
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ClientManager.class);

  private static final String ELEMENT_ROOT = "clients";
  private static final String ELEMENT_ITEM = "client";

  private final Map <String, Client> m_aMap = new HashMap <String, Client> ();

  private final CallbackList <IClientModificationCallback> m_aCallbacks = new CallbackList <IClientModificationCallback> ();

  public ClientManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    createClient (CClient.GLOBAL_CLIENT, CClient.GLOBAL_CLIENT_NAME);
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eClient : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_ITEM))
      _addClient (MicroTypeConverter.convertToNative (eClient, Client.class));
    return EChange.UNCHANGED;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IClientModificationCallback> getClientModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_ROOT);
    for (final Client aClient : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aClient, ELEMENT_ITEM));
    return aDoc;
  }

  private void _addClient (@Nonnull final Client aClient)
  {
    ValueEnforcer.notNull (aClient, "Client");

    final String sClientID = aClient.getID ();
    if (m_aMap.containsKey (sClientID))
      throw new IllegalArgumentException ("Client ID '" + sClientID + "' is already in use!");
    m_aMap.put (aClient.getID (), aClient);
  }

  @Nullable
  public IClient createClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    final Client aClient = new Client (sClientID, sDisplayName);

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.containsKey (sClientID))
        return null;

      _addClient (aClient);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (Client.OT, aClient.getID (), sDisplayName);

    // Execute callback as the very last action
    for (final IClientModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onClientCreated (aClient);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onClientCreated callback on " + aClient.toString (), t);
      }

    return aClient;
  }

  @Nonnull
  public EChange updateClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    final Client aClient = _getClientOfID (sClientID);
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
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Client.OT, "all", sClientID, sDisplayName);

    // Execute callback as the very last action
    for (final IClientModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onClientUpdated (aClient);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onClientUpdated callback on " + aClient.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange deleteClient (@Nullable final String sClientID)
  {
    final Client aDeletedClient = _getClientOfID (sClientID);
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
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Client.OT, sClientID);

    // Execute callback as the very last action
    for (final IClientModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onClientDeleted (aDeletedClient);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onClientDeleted callback on " + aDeletedClient.toString (), t);
      }

    return EChange.CHANGED;
  }

  public boolean hasAnyClient ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return !m_aMap.isEmpty ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean hasAnyClientExceptGlobal ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      for (final IClient aClient : m_aMap.values ())
        if (!aClient.isGlobalClient ())
          return true;
      return false;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public int getClientCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IClient> getAllClients ()
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
  public Set <String> getAllClientIDs ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newSet (m_aMap.keySet ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public Client _getClientOfID (@Nullable final String sID)
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
  public IClient getClientOfID (@Nullable final String sID)
  {
    // Change return type
    return _getClientOfID (sID);
  }

  public boolean containsClientWithID (@Nullable final String sID)
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
    m_aRWLock.readLock ().lock ();
    try
    {
      if (aIDs != null)
        for (final String sClientID : aIDs)
          if (!m_aMap.containsKey (sClientID))
            return false;
      return true;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).toString ();
  }
}
