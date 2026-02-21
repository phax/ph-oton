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

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.factory.GlobalIDFactory;
import com.helger.base.state.ESuccess;
import com.helger.collection.commons.CommonsArrayList;
import com.helger.collection.commons.ICommonsList;
import com.helger.db.api.helper.DBValueHelper;
import com.helger.db.jdbc.callback.ConstantPreparedStatementDataProvider;
import com.helger.db.jdbc.executor.DBExecutor;
import com.helger.db.jdbc.executor.DBResultRow;
import com.helger.db.jdbc.mgr.AbstractJDBCEnabledManager;
import com.helger.photon.mgrs.longrun.ILongRunningJobResultManager;
import com.helger.photon.mgrs.longrun.LongRunningJobData;
import com.helger.photon.mgrs.longrun.LongRunningJobDataMicroTypeConverter;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.serialize.MicroReader;
import com.helger.xml.microdom.serialize.MicroWriter;

/**
 * A JDBC based implementation of the {@link ILongRunningJobResultManager} interface.
 *
 * @author Philip Helger
 */
public class LongRunningJobResultManagerJDBC extends AbstractJDBCEnabledManager implements ILongRunningJobResultManager
{
  private static final String ELEMENT_JOB = "job";
  private static final LongRunningJobDataMicroTypeConverter CONVERTER = new LongRunningJobDataMicroTypeConverter ();

  private final String m_sTableName;

  /**
   * Constructor
   *
   * @param aDBExecSupplier
   *        The supplier for {@link DBExecutor} objects. May not be <code>null</code>.
   * @param aTableNameCustomizer
   *        A customizer for database table names used by this class. May not be <code>null</code>.
   */
  public LongRunningJobResultManagerJDBC (@NonNull final Supplier <? extends DBExecutor> aDBExecSupplier,
                                          @NonNull final Function <String, String> aTableNameCustomizer)
  {
    super (aDBExecSupplier);
    m_sTableName = aTableNameCustomizer.apply ("long_running_job");
  }

  @NonNull
  private static String _serialize (@NonNull final LongRunningJobData aJobData)
  {
    final IMicroElement eJob = CONVERTER.convertToMicroElement (aJobData, null, ELEMENT_JOB);
    return MicroWriter.getNodeAsString (eJob);
  }

  @Nullable
  private static LongRunningJobData _deserialize (@Nullable final String sJobData)
  {
    if (sJobData == null)
      return null;
    final IMicroElement eJob = MicroReader.readMicroXML (sJobData).getDocumentElement ();
    return CONVERTER.convertToNative (eJob);
  }

  public void addResult (@NonNull final LongRunningJobData aJobData)
  {
    ValueEnforcer.notNull (aJobData, "JobData");
    if (!aJobData.isEnded ())
      throw new IllegalArgumentException ("Passed jobData is not yet finished");

    final DBExecutor aExecutor = newExecutor ();
    final ESuccess eSuccess = aExecutor.performInTransaction ( () -> {
      final long nCreated = aExecutor.insertOrUpdateOrDelete ("INSERT INTO " +
                                                              m_sTableName +
                                                              " (id, start_dt, job_data) VALUES (?, ?, ?)",
                                                              new ConstantPreparedStatementDataProvider (DBValueHelper.getTrimmedToLength (aJobData.getID (),
                                                                                                                                           GlobalIDFactory.STRING_ID_MAX_LENGTH),
                                                                                                         DBValueHelper.toTimestamp (aJobData.getStartDateTime ()),
                                                                                                         _serialize (aJobData)));
      if (nCreated != 1)
        throw new IllegalStateException ("Failed to create new DB entry (" + nCreated + ")");
    });

    if (eSuccess.isFailure ())
      throw new IllegalStateException ("Failed to insert long running job '" +
                                       aJobData.getID () +
                                       "' into the database");
  }

  @NonNull
  @ReturnsMutableCopy
  public ICommonsList <LongRunningJobData> getAllJobResults ()
  {
    final ICommonsList <LongRunningJobData> ret = new CommonsArrayList <> ();
    final ICommonsList <DBResultRow> aDBResult = newExecutor ().queryAll ("SELECT job_data FROM " +
                                                                          m_sTableName +
                                                                          " ORDER BY start_dt ASC");
    if (aDBResult != null)
      for (final DBResultRow aRow : aDBResult)
      {
        final LongRunningJobData aJobData = _deserialize (aRow.getAsString (0));
        if (aJobData != null)
          ret.add (aJobData);
      }
    return ret;
  }

  @Nullable
  public LongRunningJobData getJobResultOfID (@Nullable final String sJobResultID)
  {
    if (sJobResultID == null)
      return null;

    final com.helger.base.wrapper.Wrapper <DBResultRow> aDBResult = new com.helger.base.wrapper.Wrapper <> ();
    newExecutor ().querySingle ("SELECT job_data FROM " + m_sTableName + " WHERE id=?",
                                new ConstantPreparedStatementDataProvider (sJobResultID),
                                aDBResult::set);
    if (aDBResult.isNotSet ())
      return null;

    return _deserialize (aDBResult.get ().getAsString (0));
  }
}
