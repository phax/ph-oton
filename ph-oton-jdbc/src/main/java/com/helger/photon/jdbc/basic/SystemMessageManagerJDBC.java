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
package com.helger.photon.jdbc.basic;

import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.wrapper.Wrapper;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.db.jdbc.mgr.AbstractJDBCEnabledManager;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.mgrs.sysmsg.ESystemMessageType;
import com.helger.photon.mgrs.sysmsg.ISystemMessageData;
import com.helger.photon.mgrs.sysmsg.ISystemMessageManager;
import com.helger.photon.mgrs.sysmsg.SystemMessageData;

/**
 * A JDBC based implementation of the {@link ISystemMessageManager} interface.
 *
 * @author Philip Helger
 * @since 10.2.0
 */
public class SystemMessageManagerJDBC extends AbstractJDBCEnabledManager implements ISystemMessageManager
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
  public SystemMessageManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                                   @NonNull final Function <String, String> aTableNameCustomizer)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("sys_message");
  }

  /**
   * Read all system message fields with a single SQL statement.
   *
   * @return Never <code>null</code>. The default data, if no row is present.
   */
  @NonNull
  private SystemMessageData _readData ()
  {
    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT messagetype, lastupdate, message FROM " + m_sTableName, aDBResult::set);

    final DBResultRow aRow = aDBResult.get ();
    if (aRow == null)
      return new SystemMessageData ();

    final SystemMessageData ret = new SystemMessageData (ESystemMessageType.getFromIDOrDefault (aRow.getAsString (0)),
                                                         aRow.getAsString (2));
    ret.setLastUpdate (aRow.getAsLocalDateTime (1));
    return ret;
  }

  @NonNull
  public ISystemMessageData getSystemMessageData ()
  {
    return _readData ();
  }

  @NonNull
  public EChange setSystemMessage (@NonNull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    ValueEnforcer.notNull (eMessageType, "MessageType");

    // Use SystemMessageData to check for actual change and compute new lastupdate
    final SystemMessageData aData = _readData ();
    if (aData.setSystemMessage (eMessageType, sMessage).isUnchanged ())
      return EChange.UNCHANGED;

    final DBExecutor aExecutor = newExecutor ();
    aExecutor.performInTransaction ( () -> {
      final long nUpdated = aExecutor.insertOrUpdateOrDelete ("UPDATE " +
                                                              m_sTableName +
                                                              " SET messagetype=?, lastupdate=?, message=?",
                                                              new ConstantPreparedStatementDataProvider (eMessageType.getID (),
                                                                                                         DBValueHelper.toTimestamp (aData.getLastUpdateDT ()),
                                                                                                         sMessage));
      if (nUpdated == 0)
      {
        aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                          m_sTableName +
                                          " (messagetype, lastupdate, message) VALUES (?, ?, ?)",
                                          new ConstantPreparedStatementDataProvider (eMessageType.getID (),
                                                                                     DBValueHelper.toTimestamp (aData.getLastUpdateDT ()),
                                                                                     sMessage));
      }
    });

    AuditHelper.onAuditExecuteSuccess ("update-system-message", eMessageType, sMessage);
    return EChange.CHANGED;
  }
}
