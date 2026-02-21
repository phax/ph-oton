/*
 * Copyright (C) 2021-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.jdbc.sysmigration;

import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ESuccess;
import com.helger.base.state.SuccessWithValue;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.CommonsHashSet;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsSet;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.db.jdbc.mgr.AbstractJDBCEnabledManager;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.mgrs.sysmigration.ISystemMigrationManager;
import com.helger.photon.mgrs.sysmigration.SystemMigrationHelper;
import com.helger.photon.mgrs.sysmigration.SystemMigrationResult;

/**
 * A JDBC based implementation of the {@link ISystemMigrationManager} interface.
 *
 * @author Philip Helger
 * @since 10.2.0
 */
public class SystemMigrationManagerJDBC extends AbstractJDBCEnabledManager implements ISystemMigrationManager
{
  private final String m_sTableName;

  /**
   * Constructor
   *
   * @param aDBExecSupplier
   *        The supplier for {@link DBExecutor} objects. May not be <code>null</code>.
   * @param aTableNameCustomizer
   *        A customizer for database table names used by this class. May not be <code>null</code>.
   */
  public SystemMigrationManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                                     @NonNull final Function <String, String> aTableNameCustomizer)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("sys_migration");
  }

  public void addMigrationResult (@NonNull final SystemMigrationResult aMigrationResult)
  {
    ValueEnforcer.notNull (aMigrationResult, "MigrationResult");

    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long nCreated = aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                                              m_sTableName +
                                                              " (migration_id, execution_dt, success, error_msg)" +
                                                              " VALUES (?, ?, ?, ?, ?)",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (aMigrationResult.getID (),
                                                                                                                                           ISystemMigrationManager.MIGRATION_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aMigrationResult.getExecutionDateTime ()),
                                                                                                         Boolean.valueOf (aMigrationResult.isSuccess ()),
                                                                                                         aMigrationResult.getErrorMessage ()));
      if (nCreated != 1)
        throw new IllegalStateException ("Failed to create new DB entry (" + nCreated + ")");
    });

    if (eSuccess.isFailure ())
      throw new IllegalStateException ("Failed to insert migration result for '" +
                                       aMigrationResult.getID () +
                                       "' into the database");

    AuditHelper.onAuditCreateSuccess (SystemMigrationResult.OT,
                                      aMigrationResult.getID (),
                                      Boolean.valueOf (aMigrationResult.isSuccess ()),
                                      aMigrationResult.getErrorMessage ());
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllMigrationResults (@Nullable final String sMigrationID)
  {
    final ICommonsList <SystemMigrationResult> ret = new CommonsArrayList <> ();
    if (sMigrationID == null)
      return ret;

    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT migration_id, execution_dt, success, error_msg" +
                                                                          " FROM " +
                                                                          m_sTableName +
                                                                          " WHERE migration_id=?",
                                                                          new ConstantPreparedStatementDataProvider (sMigrationID));
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
        ret.add (new SystemMigrationResult (aRow.getAsString (0),
                                            aRow.getAsLocalDateTime (1),
                                            aRow.getAsBoolean (2),
                                            aRow.getAsString (3)));
    return ret;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllMigrationResultsFlattened ()
  {
    final ICommonsList <SystemMigrationResult> ret = new CommonsArrayList <> ();
    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT migration_id, execution_dt, success, error_msg FROM " +
                                                                          m_sTableName);
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
        ret.add (new SystemMigrationResult (aRow.getAsString (0),
                                            aRow.getAsLocalDateTime (1),
                                            aRow.getAsBoolean (2),
                                            aRow.getAsString (3)));
    return ret;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllFailedMigrationResults (@Nullable final String sMigrationID)
  {
    final ICommonsList <SystemMigrationResult> ret = new CommonsArrayList <> ();
    if (sMigrationID == null)
      return ret;

    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT migration_id, execution_dt, success, error_msg" +
                                                                          " FROM " +
                                                                          m_sTableName +
                                                                          " WHERE migration_id=? AND success=?",
                                                                          new ConstantPreparedStatementDataProvider (sMigrationID,
                                                                                                                     Boolean.FALSE));
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
        ret.add (new SystemMigrationResult (aRow.getAsString (0),
                                            aRow.getAsLocalDateTime (1),
                                            aRow.getAsBoolean (2),
                                            aRow.getAsString (3)));
    return ret;
  }

  public boolean wasMigrationExecutedSuccessfully (@Nullable final String sMigrationID)
  {
    if (sMigrationID == null)
      return false;

    return newExecutor ().queryCount ("SELECT COUNT(*) FROM " + m_sTableName + " WHERE migration_id=? AND success=?",
                                      new ConstantPreparedStatementDataProvider (sMigrationID, Boolean.TRUE)) > 0;
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllMigrationIDs ()
  {
    final ICommonsSet <String> ret = new CommonsHashSet <> ();
    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT DISTINCT migration_id FROM " +
                                                                          m_sTableName);
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
        ret.add (aRow.getAsString (0));
    return ret;
  }

  public void performMigrationIfNecessary (@NonNull @Nonempty final String sMigrationID,
                                           @NonNull final Runnable aMigrationAction)
  {
    SystemMigrationHelper.performMigrationIfNecessary (this, sMigrationID, aMigrationAction);
  }

  public void performMigrationIfNecessary (@NonNull @Nonempty final String sMigrationID,
                                           @NonNull final Supplier <SuccessWithValue <String>> aMigrationAction)
  {
    SystemMigrationHelper.performMigrationIfNecessary (this, sMigrationID, aMigrationAction);
  }
}
