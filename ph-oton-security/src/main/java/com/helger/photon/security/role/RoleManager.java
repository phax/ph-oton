/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.security.role;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.state.EChange;
import com.helger.dao.DAOException;
import com.helger.photon.app.dao.AbstractPhotonMapBasedWALDAO;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.object.StubObject;

/**
 * This class manages the available roles.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class RoleManager extends AbstractPhotonMapBasedWALDAO <IRole, Role> implements IRoleManager
{
  private final CallbackList <IRoleModificationCallback> m_aCallbacks = new CallbackList <> ();

  public RoleManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (Role.class, sFilename);
  }

  @Nonnull
  public static Role createDefaultRoleAdministrator ()
  {
    return new Role (StubObject.createForCurrentUserAndID (CSecurity.ROLE_ADMINISTRATOR_ID),
                     CSecurity.ROLE_ADMINISTRATOR_NAME,
                     (String) null);
  }

  @Nonnull
  public static Role createDefaultRoleUser ()
  {
    return new Role (StubObject.createForCurrentUserAndID (CSecurity.ROLE_USER_ID), CSecurity.ROLE_USER_NAME, (String) null);
  }

  public void createDefaultsForTest ()
  {
    if (!containsWithID (CSecurity.ROLE_ADMINISTRATOR_ID))
      internalCreateItem (createDefaultRoleAdministrator ());
    if (!containsWithID (CSecurity.ROLE_USER_ID))
      internalCreateItem (createDefaultRoleUser ());
  }

  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IRoleModificationCallback> roleModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  private void _createNewRole (@Nonnull final Role aRole, final boolean bPredefined)
  {
    // Store
    m_aRWLock.writeLocked ( () -> internalCreateItem (aRole));
    AuditHelper.onAuditCreateSuccess (Role.OT,
                                      aRole.getID (),
                                      aRole.getName (),
                                      aRole.getDescription (),
                                      bPredefined ? "predefined" : "custom");

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleCreated (aRole, bPredefined));
  }

  @Nonnull
  public IRole createNewRole (@Nonnull @Nonempty final String sName,
                              @Nullable final String sDescription,
                              @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (sName, sDescription, aCustomAttrs);
    _createNewRole (aRole, false);
    return aRole;
  }

  @Nonnull
  public IRole createPredefinedRole (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sName,
                                     @Nullable final String sDescription,
                                     @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (StubObject.createForCurrentUserAndID (sID, aCustomAttrs), sName, sDescription);
    _createNewRole (aRole, true);
    return aRole;
  }

  @Nonnull
  public EChange deleteRole (@Nullable final String sRoleID)
  {
    Role aDeletedRole;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aDeletedRole = internalDeleteItem (sRoleID);
      if (aDeletedRole == null)
      {
        AuditHelper.onAuditDeleteFailure (Role.OT, sRoleID, "no-such-id");
        return EChange.UNCHANGED;
      }
      BusinessObjectHelper.setDeletionNow (aDeletedRole);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Role.OT, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleDeleted (sRoleID));

    return EChange.CHANGED;
  }

  @Nullable
  public IRole getRoleOfID (@Nullable final String sRoleID)
  {
    return getOfID (sRoleID);
  }

  @Nonnull
  public EChange renameRole (@Nullable final String sRoleID, @Nonnull @Nonempty final String sNewName)
  {
    // Resolve user group
    final Role aRole = getOfID (sRoleID);
    if (aRole == null)
    {
      AuditHelper.onAuditModifyFailure (Role.OT, "set-name", sRoleID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aRole.setName (sNewName).isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aRole);
      internalUpdateItem (aRole);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Role.OT, "set-name", sRoleID, sNewName);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleRenamed (sRoleID));

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange setRoleData (@Nullable final String sRoleID,
                              @Nonnull @Nonempty final String sNewName,
                              @Nullable final String sNewDescription,
                              @Nullable final Map <String, String> aNewCustomAttrs)
  {
    // Resolve role
    final Role aRole = getOfID (sRoleID);
    if (aRole == null)
    {
      AuditHelper.onAuditModifyFailure (Role.OT, "set-all", sRoleID, "no-such-role-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = aRole.setName (sNewName);
      eChange = eChange.or (aRole.setDescription (sNewDescription));
      eChange = eChange.or (aRole.attrs ().setAll (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      BusinessObjectHelper.setLastModificationNow (aRole);
      internalUpdateItem (aRole);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Role.OT, "set-all", aRole.getID (), sNewName, sNewDescription, aNewCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleUpdated (sRoleID));

    return EChange.CHANGED;
  }
}
