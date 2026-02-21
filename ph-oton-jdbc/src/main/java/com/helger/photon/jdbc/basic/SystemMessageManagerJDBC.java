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

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.EChange;
import com.helger.base.string.StringHelper;
import com.helger.base.wrapper.Wrapper;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.db.jdbc.mgr.AbstractJDBCEnabledManager;
import com.helger.photon.audit.AuditHelper;
import com.helger.photon.mgrs.systemmsg.ESystemMessageType;
import com.helger.photon.mgrs.systemmsg.ISystemMessageManager;
import com.helger.photon.mgrs.systemmsg.SystemMessageData;

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

  @Nullable
  private DBResultRow _readRow ()
  {
    final Wrapper <DBResultRow> aDBResult = new Wrapper <> ();
    newExecutor ().querySingle ("SELECT messagetype, lastupdate, message FROM " + m_sTableName, aDBResult::set);
    return aDBResult.get ();
  }

  @Nullable
  public LocalDateTime getLastUpdateDT ()
  {
    final DBResultRow aRow = _readRow ();
    return aRow != null ? aRow.getAsLocalDateTime (1) : null;
  }

  @NonNull
  public ESystemMessageType getMessageType ()
  {
    final DBResultRow aRow = _readRow ();
    if (aRow != null)
      return ESystemMessageType.getFromIDOrDefault (aRow.getAsString (0));
    return ESystemMessageType.DEFAULT;
  }

  @Nullable
  public String getSystemMessage ()
  {
    final DBResultRow aRow = _readRow ();
    return aRow != null ? aRow.getAsString (2) : null;
  }

  public boolean hasSystemMessage ()
  {
    final DBResultRow aRow = _readRow ();
    if (aRow == null)
      return false;
    return StringHelper.isNotEmpty (aRow.getAsString (2));
  }

  @NonNull
  public EChange setSystemMessage (@NonNull final ESystemMessageType eMessageType, @Nullable final String sMessage)
  {
    ValueEnforcer.notNull (eMessageType, "MessageType");

    // Use SystemMessageData to check for actual change and compute new lastupdate
    final DBResultRow aExisting = _readRow ();
    final SystemMessageData aData;
    if (aExisting != null)
    {
      aData = new SystemMessageData (ESystemMessageType.getFromIDOrDefault (aExisting.getAsString (0)),
                                     aExisting.getAsString (2));
      aData.setLastUpdate (aExisting.getAsLocalDateTime (1));
    }
    else
    {
      aData = new SystemMessageData ();
    }

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
