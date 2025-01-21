/*
 * Copyright (C) 2021-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc.security;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedSet;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.mutable.MutableLong;
import com.helger.commons.state.EChange;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.wrapper.Wrapper;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.security.CSecurity;
import com.helger.photon.security.object.BusinessObjectHelper;
import com.helger.photon.security.object.StubObject;
import com.helger.photon.security.role.IRole;
import com.helger.photon.security.role.IRoleManager;
import com.helger.photon.security.role.IRoleModificationCallback;
import com.helger.photon.security.role.Role;
import com.helger.photon.security.role.RoleManager;

/**
 * Implementation of {@link IRoleManager} for JDBC backends.
 *
 * @author Philip Helger
 */
public class RoleManagerJDBC extends AbstractJDBCEnabledSecurityManager implements IRoleManager
{
  private final String m_sTableName;
  private final CallbackList <IRoleModificationCallback> m_aCallbacks = new CallbackList <> ();

  public RoleManagerJDBC (@Nonnull final Supplier <? extends DBExecutor> aDBExecSupplier,
                          @Nonnull final Function <String, String> aTableNameCustomizer)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("secrole");
  }

  /**
   * @return The name of the database table this class is operating on. Neither
   *         <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public final String getTableName ()
  {
    return m_sTableName;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IRole> getAll ()
  {
    final ICommonsList <IRole> ret = new CommonsArrayList <> ();
    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT id, creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                                                                          " name, description" +
                                                                          " FROM " +
                                                                          m_sTableName);
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
      {
        final StubObject aStub = new StubObject (aRow.getAsString (0),
                                                 aRow.getAsLocalDateTime (1),
                                                 aRow.getAsString (2),
                                                 aRow.getAsLocalDateTime (3),
                                                 aRow.getAsString (4),
                                                 aRow.getAsLocalDateTime (5),
                                                 aRow.getAsString (6),
                                                 attrsToMap (aRow.getAsString (7)));
        ret.add (new Role (aStub, aRow.getAsString (8), aRow.getAsString (9)));
      }
    return ret;
  }

  public boolean containsWithID (@Nullable final String sID)
  {
    if (StringHelper.hasNoText (sID))
      return false;

    final long nCount = newExecutor ().queryCount ("SELECT COUNT(*) FROM " + m_sTableName + " WHERE id=?",
                                                   new ConstantPreparedStatementDataProvider (sID));
    return nCount > 0;
  }

  public boolean containsAllIDs (@Nullable final Iterable <String> aIDs)
  {
    if (aIDs != null)
    {
      // Make unique, maintain order
      final ICommonsOrderedSet <String> aUniqueIDs = new CommonsLinkedHashSet <> (aIDs);
      final int nIDCount = aUniqueIDs.size ();
      if (nIDCount == 1)
        return containsWithID (aUniqueIDs.getFirst ());

      if (nIDCount > 0)
      {
        final StringBuilder aCond = new StringBuilder (nIDCount * 2);
        for (int i = 0; i < nIDCount; ++i)
        {
          if (i > 0)
            aCond.append (',');
          aCond.append ('?');
        }

        final long nCount = newExecutor ().queryCount ("SELECT COUNT(*) FROM " +
                                                       m_sTableName +
                                                       " WHERE id IN (" +
                                                       aCond.toString () +
                                                       ")",
                                                       new ConstantPreparedStatementDataProvider (aIDs));
        return nCount == nIDCount;
      }
    }
    return true;
  }

  public void createDefaultsForTest ()
  {
    if (!containsWithID (CSecurity.ROLE_ADMINISTRATOR_ID))
      _internalCreateItem (RoleManager.createDefaultRoleAdministrator ());
    if (!containsWithID (CSecurity.ROLE_USER_ID))
      _internalCreateItem (RoleManager.createDefaultRoleUser ());
  }

  @Nonnull
  @ReturnsMutableObject
  public CallbackList <IRoleModificationCallback> roleModificationCallbacks ()
  {
    return m_aCallbacks;
  }

  @Nonnull
  private ESuccess _internalCreateItem (@Nonnull final Role aRole)
  {
    final DBExecutor aExecutor = newExecutor ();
    return aExecutor.performInTransaction ( () -> {
      // Create new
      final long nCreated = aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                                              m_sTableName +
                                                              " (id, creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                                                              " name, description)" +
                                                              " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (aRole.getID (),
                                                                                                                                           IRole.ROLE_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aRole.getCreationDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aRole.getCreationUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aRole.getLastModificationDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aRole.getLastModificationUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aRole.getDeletionDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aRole.getDeletionUserID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         attrsToString (aRole.attrs ()),
                                                                                                         DBValueHelper.getTrimmedToLength (aRole.getName (),
                                                                                                                                           IRole.ROLE_NAME_MAX_LENGTH),
                                                                                                         aRole.getDescription ()));
      if (nCreated != 1)
        throw new IllegalStateException ("Failed to create new DB entry (" + nCreated + ")");
    });
  }

  @Nullable
  public Role internalCreateNewRole (@Nonnull final Role aRole, final boolean bPredefined, final boolean bRunCallback)
  {
    // Store
    if (_internalCreateItem (aRole).isFailure ())
    {
      AuditHelper.onAuditCreateFailure (Role.OT,
                                        aRole.getID (),
                                        aRole.getName (),
                                        aRole.getDescription (),
                                        bPredefined ? "predefined" : "custom",
                                        "database-error");
      return null;
    }

    AuditHelper.onAuditCreateSuccess (Role.OT,
                                      aRole.getID (),
                                      aRole.getName (),
                                      aRole.getDescription (),
                                      bPredefined ? "predefined" : "custom");

    if (bRunCallback)
    {
      // Execute callback as the very last action
      m_aCallbacks.forEach (aCB -> aCB.onRoleCreated (aRole, bPredefined));
    }

    return aRole;
  }

  @Nullable
  public IRole createNewRole (@Nonnull @Nonempty final String sName,
                              @Nullable final String sDescription,
                              @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (sName, sDescription, aCustomAttrs);
    return internalCreateNewRole (aRole, false, true);
  }

  @Nullable
  public IRole createPredefinedRole (@Nonnull @Nonempty final String sID,
                                     @Nonnull @Nonempty final String sName,
                                     @Nullable final String sDescription,
                                     @Nullable final Map <String, String> aCustomAttrs)
  {
    // Create role
    final Role aRole = new Role (StubObject.createForCurrentUserAndID (sID, aCustomAttrs), sName, sDescription);
    return internalCreateNewRole (aRole, true, true);
  }

  @Nonnull
  public EChange deleteRole (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return EChange.UNCHANGED;

    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET deletedt=?, deleteuserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sRoleID,
                                                                                                                                           IRole.ROLE_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditDeleteFailure (Role.OT, sRoleID, "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such user ID
      AuditHelper.onAuditDeleteFailure (Role.OT, sRoleID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditDeleteSuccess (Role.OT, sRoleID);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleDeleted (sRoleID));

    return EChange.CHANGED;
  }

  @Nullable
  public IRole getRoleOfID (@Nullable final String sRoleID)
  {
    if (StringHelper.hasNoText (sRoleID))
      return null;

    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT creationdt, creationuserid, lastmoddt, lastmoduserid, deletedt, deleteuserid, attrs," +
                                " name, description" +
                                " FROM " +
                                m_sTableName +
                                " WHERE id=?",
                                new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (sRoleID,
                                                                                                             IRole.ROLE_ID_MAX_LENGTH)),
                                aDBResult::set);
    if (aDBResult.isNotSet ())
      return null;

    final DBResultRow aRow = aDBResult.get ();
    final StubObject aStub = new StubObject (sRoleID,
                                             aRow.getAsLocalDateTime (0),
                                             aRow.getAsString (1),
                                             aRow.getAsLocalDateTime (2),
                                             aRow.getAsString (3),
                                             aRow.getAsLocalDateTime (4),
                                             aRow.getAsString (5),
                                             attrsToMap (aRow.getAsString (6)));
    return new Role (aStub, aRow.getAsString (7), aRow.getAsString (8));
  }

  @Nonnull
  public EChange renameRole (@Nullable final String sRoleID, @Nonnull @Nonempty final String sNewName)
  {
    if (StringHelper.hasNoText (sRoleID))
      return EChange.UNCHANGED;

    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET name=?, lastmoddt=?, lastmoduserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (sNewName,
                                                                                                         DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sRoleID,
                                                                                                                                           IRole.ROLE_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditModifyFailure (Role.OT, "set-name", sRoleID, sNewName, "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such role ID
      AuditHelper.onAuditModifyFailure (Role.OT, "set-name", sRoleID, "no-such-id");
      return EChange.UNCHANGED;
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
    if (StringHelper.hasNoText (sRoleID))
      return EChange.UNCHANGED;

    final MutableLong aUpdated = new MutableLong (-1);
    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      // Update existing
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET name=?, description=?, attrs=?, lastmoddt=?, lastmoduserid=? WHERE id=?",
                                                              new ConstantPreparedStatementDataProvider (sNewName,
                                                                                                         sNewDescription,
                                                                                                         attrsToString (aNewCustomAttrs),
                                                                                                         DBValueHelper.toTimestamp (PDTFactory.getCurrentLocalDateTime ()),
                                                                                                         DBValueHelper.getTrimmedToLength (BusinessObjectHelper.getUserIDOrFallback (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.getTrimmedToLength (sRoleID,
                                                                                                                                           IRole.ROLE_ID_MAX_LENGTH)));
      aUpdated.set (nUpdated);
    });

    if (eSuccess.isFailure ())
    {
      // DB error
      AuditHelper.onAuditModifyFailure (Role.OT,
                                        "set-all",
                                        sRoleID,
                                        sNewName,
                                        sNewDescription,
                                        aNewCustomAttrs,
                                        "database-error");
      return EChange.UNCHANGED;
    }

    if (aUpdated.is0 ())
    {
      // No such role ID
      AuditHelper.onAuditModifyFailure (Role.OT, "set-all", sRoleID, "no-such-id");
      return EChange.UNCHANGED;
    }

    AuditHelper.onAuditModifySuccess (Role.OT, "set-all", sRoleID, sNewName, sNewDescription, aNewCustomAttrs);

    // Execute callback as the very last action
    m_aCallbacks.forEach (aCB -> aCB.onRoleUpdated (sRoleID));

    return EChange.CHANGED;
  }
}
