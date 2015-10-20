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
package com.helger.photon.security.role;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

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
import com.helger.photon.basic.app.dao.IReloadableDAO;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.photon.security.CSecurity;

/**
 * This class manages the available roles.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class RoleManager extends AbstractSimpleDAO implements IRoleManager, IReloadableDAO
{
  public static final boolean DEFAULT_CREATE_DEFAULTS = true;

  private static final Logger s_aLogger = LoggerFactory.getLogger (RoleManager.class);
  private static final ReadWriteLock s_aRWLock = new ReentrantReadWriteLock ();

  @GuardedBy ("s_aRWLock")
  private static boolean s_bCreateDefaults = DEFAULT_CREATE_DEFAULTS;

  @GuardedBy ("m_aRWLock")
  private final Map <String, Role> m_aRoles = new HashMap <String, Role> ();

  private final CallbackList <IRoleModificationCallback> m_aCallbacks = new CallbackList <IRoleModificationCallback> ();

  /**
   * @return <code>true</code> if the default built-in roles should be created
   *         if no roles are present, <code>false</code> if not.
   */
  public static boolean isCreateDefaults ()
  {
    s_aRWLock.readLock ().lock ();
    try
    {
      return s_bCreateDefaults;
    }
    finally
    {
      s_aRWLock.readLock ().unlock ();
    }
  }

  public static void setCreateDefaults (final boolean bCreateDefaults)
  {
    s_aRWLock.writeLock ().lock ();
    try
    {
      s_bCreateDefaults = bCreateDefaults;
    }
    finally
    {
      s_aRWLock.writeLock ().unlock ();
    }
  }

  public RoleManager (@Nonnull @Nonempty final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  public void reload () throws DAOException
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aRoles.clear ();
      initialRead ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Override
  @Nonnull
  protected EChange onInit ()
  {
    if (!isCreateDefaults ())
      return EChange.UNCHANGED;

    // Default should be created
    _addRole (new Role (CSecurity.ROLE_ADMINISTRATOR_ID,
                        CSecurity.ROLE_ADMINISTRATOR_NAME,
                        (String) null,
                        (Map <String, ?>) null));
    _addRole (new Role (CSecurity.ROLE_USER_ID, CSecurity.ROLE_USER_NAME, (String) null, (Map <String, ?>) null));
    return EChange.CHANGED;
  }

  @Override
  @Nonnull
  protected EChange onRead (@Nonnull final IMicroDocument aDoc)
  {
    for (final IMicroElement eRole : aDoc.getDocumentElement ().getAllChildElements ())
      _addRole (MicroTypeConverter.convertToNative (eRole, Role.class));
    return EChange.UNCHANGED;
  }

  @Override
  @Nonnull
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement ("roles");
    for (final Role aRole : CollectionHelper.getSortedByKey (m_aRoles).values ())
      eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aRole, "role"));
    return aDoc;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public CallbackList <IRoleModificationCallback> getRoleModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  private void _addRole (@Nonnull final Role aRole)
  {
    ValueEnforcer.notNull (aRole, "Role");

    final String sRoleID = aRole.getID ();
    if (m_aRoles.containsKey (sRoleID))
      throw new IllegalArgumentException ("Role ID " + sRoleID + " is already in use!");
    m_aRoles.put (sRoleID, aRole);
  }

  @Nonnull
  public IRole createNewRole (@Nonnull @Nonempty final String sName,
                              @Nullable final String sDescription,
                              @Nullable final Map <String, ?> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (sName, sDescription, aCustomAttrs);

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Store
      _addRole (aRole);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (Role.OT, aRole.getID (), sName);

    // Execute callback as the very last action
    for (final IRoleModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onRoleCreated (aRole, false);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRoleCreated callback on " + aRole.toString (), t);
      }

    return aRole;
  }

  @Nonnull
  public IRole createPredefinedRole (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sName,
                                     @Nullable final String sDescription,
                                     @Nullable final Map <String, ?> aCustomAttrs)
  {

    // Create role
    final Role aRole = new Role (sID, sName, sDescription, aCustomAttrs);

    m_aRWLock.writeLock ().lock ();
    try
    {
      // Store
      _addRole (aRole);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditCreateSuccess (Role.OT, aRole.getID (), "predefind-role", sName);

    // Execute callback as the very last action
    for (final IRoleModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onRoleCreated (aRole, true);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRoleCreated callback on " + aRole.toString (), t);
      }

    return aRole;
  }

  public boolean containsRoleWithID (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return false;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aRoles.containsKey (sRoleID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public boolean containsAllRolesWithID (@Nullable final Collection <String> aRoleIDs)
  {
    if (CollectionHelper.isEmpty (aRoleIDs))
      return true;

    m_aRWLock.readLock ().lock ();
    try
    {
      for (final String sRoleID : aRoleIDs)
        if (!m_aRoles.containsKey (sRoleID))
          return false;
      return true;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public Role getRoleOfID (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return null;

    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aRoles.get (sRoleID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableCopy
  public Collection <? extends IRole> getAllRoles ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aRoles.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  public EChange deleteRole (@Nullable final String sRoleID)
  {
    IRole aDeletedRole;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aDeletedRole = m_aRoles.remove (sRoleID);
      if (aDeletedRole == null)
      {
        AuditHelper.onAuditDeleteFailure (Role.OT, "no-such-role-id", sRoleID);
        return EChange.UNCHANGED;
      }
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditDeleteSuccess (Role.OT, sRoleID);

    // Execute callback as the very last action
    for (final IRoleModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onRoleDeleted (aDeletedRole);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRoleDeleted callback on " + aDeletedRole.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange renameRole (@Nullable final String sRoleID, @Nonnull @Nonempty final String sNewName)
  {
    // Resolve user group
    final Role aRole = getRoleOfID (sRoleID);
    if (aRole == null)
    {
      AuditHelper.onAuditModifyFailure (Role.OT, sRoleID, "no-such-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (aRole.setName (sNewName).isUnchanged ())
        return EChange.UNCHANGED;
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Role.OT, "name", sRoleID, sNewName);

    // Execute callback as the very last action
    for (final IRoleModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onRoleRenamed (aRole);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRoleRenamed callback on " + aRole.toString (), t);
      }

    return EChange.CHANGED;
  }

  @Nonnull
  public EChange setRoleData (@Nullable final String sRoleID,
                              @Nonnull @Nonempty final String sNewName,
                              @Nullable final String sNewDescription,
                              @Nullable final Map <String, ?> aNewCustomAttrs)
  {
    // Resolve role
    final Role aRole = getRoleOfID (sRoleID);
    if (aRole == null)
    {
      AuditHelper.onAuditModifyFailure (Role.OT, sRoleID, "no-such-role-id");
      return EChange.UNCHANGED;
    }

    m_aRWLock.writeLock ().lock ();
    try
    {
      EChange eChange = aRole.setName (sNewName);
      eChange = eChange.or (aRole.setDescription (sNewDescription));
      eChange = eChange.or (aRole.setAttributes (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Role.OT,
                                      "all",
                                      aRole.getID (),
                                      sNewName,
                                      sNewDescription,
                                      aNewCustomAttrs);

    // Execute callback as the very last action
    for (final IRoleModificationCallback aCallback : m_aCallbacks.getAllCallbacks ())
      try
      {
        aCallback.onRoleUpdated (aRole);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRoleUpdated callback on " + aRole.toString (), t);
      }

    return EChange.CHANGED;
  }
}
