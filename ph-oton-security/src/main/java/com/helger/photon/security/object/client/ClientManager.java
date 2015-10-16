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
package com.helger.photon.security.object.client;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.microdom.IMicroDocument;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.microdom.MicroDocument;
import com.helger.commons.microdom.convert.MicroTypeConverter;
import com.helger.commons.state.EChange;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.basic.object.client.CClient;
import com.helger.photon.basic.object.client.IClient;
import com.helger.photon.basic.object.client.IClientResolver;

/**
 * Manages all available clients.
 *
 * @author Philip Helger
 */
public class ClientManager extends AbstractSimpleDAO implements IClientResolver
{
  private static final String ELEMENT_CLIENTS = "clients";
  private static final String ELEMENT_CLIENT = "client";

  private final Map <String, Client> m_aMap = new HashMap <String, Client> ();

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
    for (final IMicroElement eClient : aDoc.getDocumentElement ().getAllChildElements (ELEMENT_CLIENT))
      _addClient (MicroTypeConverter.convertToNative (eClient, Client.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_CLIENTS);
    for (final Client aClient : CollectionHelper.getSortedByKey (m_aMap).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aClient, ELEMENT_CLIENT));
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
    return aClient;
  }

  @Nonnull
  public EChange updateClient (@Nonnull @Nonempty final String sClientID, @Nonnull @Nonempty final String sDisplayName)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      final Client aClient = m_aMap.get (sClientID);
      if (aClient == null)
      {
        AuditHelper.onAuditModifyFailure (Client.OT, sClientID, "no-such-id");
        return EChange.UNCHANGED;
      }

      EChange eChange = EChange.UNCHANGED;
      eChange = eChange.or (aClient.setDisplayName (sDisplayName));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      aClient.setLastModificationNow ();
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Client.OT, "all", sClientID, sDisplayName);
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
  public IClient getClientOfID (@Nullable final String sID)
  {
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

  public boolean containsClientWithID (@Nullable final String sID)
  {
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
