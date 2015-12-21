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
import com.helger.photon.security.object.ObjectHelper;
import com.helger.photon.security.object.StubObjectWithCustomAttrs;

/**
 * This class manages the available roles.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class RoleManager extends AbstractSimpleDAO implements IReloadableDAO
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (RoleManager.class);

  @GuardedBy ("m_aRWLock")
  private final Map <String, Role> m_aRoles = new HashMap <String, Role> ();

  private final CallbackList <IRoleModificationCallback> m_aCallbacks = new CallbackList <IRoleModificationCallback> ();

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
    return EChange.UNCHANGED;
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

  public void createDefaults ()
  {
    // Default should be created
    _addRole (new Role (StubObjectWithCustomAttrs.createForCurrentUserAndID (CSecurity.ROLE_ADMINISTRATOR_ID),
                        CSecurity.ROLE_ADMINISTRATOR_NAME,
                        (String) null));
    _addRole (new Role (StubObjectWithCustomAttrs.createForCurrentUserAndID (CSecurity.ROLE_USER_ID),
                        CSecurity.ROLE_USER_NAME,
                        (String) null));
  }

  /**
   * @return The role callback list. Never <code>null</code>.
   */
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

  /**
   * Create a new role.
   *
   * @param sName
   *        The name of the new role. May neither be <code>null</code> nor
   *        empty.
   * @param sDescription
   *        Optional description text. May be <code>null</code>.
   * @param aCustomAttrs
   *        A set of custom attributes. May be <code>null</code>.
   * @return The created role and never <code>null</code>.
   */
  @Nonnull
  public IRole createNewRole (@Nonnull @Nonempty final String sName,
                              @Nullable final String sDescription,
                              @Nullable final Map <String, String> aCustomAttrs)
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

  /**
   * Create a predefined role.
   *
   * @param sID
   *        The ID of the new role
   * @param sName
   *        The name of the new role
   * @param sDescription
   *        Optional description text. May be <code>null</code>.
   * @param aCustomAttrs
   *        A set of custom attributes. May be <code>null</code>.
   * @return The created role and never <code>null</code>.
   */
  @Nonnull
  public IRole createPredefinedRole (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sName,
                                     @Nullable final String sDescription,
                                     @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (StubObjectWithCustomAttrs.createForCurrentUserAndID (sID, aCustomAttrs),
                                 sName,
                                 sDescription);

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

  /**
   * Delete the role with the passed ID
   *
   * @param sRoleID
   *        The role ID to be deleted
   * @return {@link EChange#CHANGED} if the passed role ID was found and deleted
   */
  @Nonnull
  public EChange deleteRole (@Nullable final String sRoleID)
  {
    Role aDeletedRole;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aDeletedRole = m_aRoles.remove (sRoleID);
      if (aDeletedRole == null)
      {
        AuditHelper.onAuditDeleteFailure (Role.OT, "no-such-role-id", sRoleID);
        return EChange.UNCHANGED;
      }

      ObjectHelper.setDeletionNow (aDeletedRole);
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

  /**
   * Check if the role with the specified ID is contained
   *
   * @param sRoleID
   *        The role ID to be check
   * @return <code>true</code> if such role exists, <code>false</code> otherwise
   */
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

  /**
   * Check if all passed role IDs are contained
   *
   * @param aRoleIDs
   *        The role IDs to be checked. May be <code>null</code>.
   * @return <code>true</code> if the collection is empty or if all contained
   *         role IDs are contained
   */
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

  /**
   * Get the role with the specified ID
   *
   * @param sRoleID
   *        The role ID to be resolved
   * @return <code>null</code> if no such role exists.
   */
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

  /**
   * @return A non-<code>null</code> collection of all available roles
   */
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

  /**
   * Rename the role with the passed ID
   *
   * @param sRoleID
   *        The ID of the role to be renamed. May be <code>null</code>.
   * @param sNewName
   *        The new name of the role. May neither be <code>null</code> nor
   *        empty.
   * @return {@link EChange#CHANGED} if the passed role ID was found, and the
   *         new name is different from the old name of he role
   */
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

      ObjectHelper.setLastModificationNow (aRole);
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

  /**
   * Change the modifiable data of a user group
   *
   * @param sRoleID
   *        The ID of the role to be renamed. May be <code>null</code>.
   * @param sNewName
   *        The new name of the role. May neither be <code>null</code> nor
   *        empty.
   * @param sNewDescription
   *        The new description text. May be <code>null</code>.
   * @param aNewCustomAttrs
   *        Custom attributes. May be <code>null</code>.
   * @return {@link EChange}
   */
  @Nonnull
  public EChange setRoleData (@Nullable final String sRoleID,
                              @Nonnull @Nonempty final String sNewName,
                              @Nullable final String sNewDescription,
                              @Nullable final Map <String, String> aNewCustomAttrs)
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
      eChange = eChange.or (aRole.getMutableAttributes ().clear ());
      eChange = eChange.or (aRole.getMutableAttributes ().setAttributes (aNewCustomAttrs));
      if (eChange.isUnchanged ())
        return EChange.UNCHANGED;

      ObjectHelper.setLastModificationNow (aRole);
      markAsChanged ();
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    AuditHelper.onAuditModifySuccess (Role.OT, "all", aRole.getID (), sNewName, sNewDescription, aNewCustomAttrs);

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
