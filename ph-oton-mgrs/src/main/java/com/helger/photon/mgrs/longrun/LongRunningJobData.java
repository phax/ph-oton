/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.mgrs.longrun;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.NotThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.id.IHasID;
import com.helger.base.state.ESuccess;
import com.helger.base.state.ETriState;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.datetime.helper.PDTFactory;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.text.IMultilingualText;

/**
 * This class contains the data for a single long running job.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class LongRunningJobData implements IHasID <String>, Serializable
{
  private final String m_sID;

  // Initial job data
  private final String m_sJobType;
  private final IMultilingualText m_aJobDescription;
  private final LocalDateTime m_aStartDateTime;
  private final String m_sStartingUserID;

  // Data set on job end:
  private LocalDateTime m_aEndDateTime;
  private ETriState m_eExecSuccess;
  private LongRunningJobResult m_aResult;

  // Telemetry state - only relevant as long as the job is running and therefore neither serialized
  // nor persisted
  private transient ITelemetrySpan m_aTelemetrySpan;
  private transient Thread m_aTelemetrySpanThread;

  public LongRunningJobData (@NonNull @Nonempty final String sID,
                             @Nullable final String sJobType,
                             @NonNull final IMultilingualText aJobDescription,
                             @Nullable final String sStartingUserID)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    m_sJobType = sJobType;
    m_aJobDescription = ValueEnforcer.notNull (aJobDescription, "JobDescription");
    m_aStartDateTime = PDTFactory.getCurrentLocalDateTime ();
    m_sStartingUserID = sStartingUserID;
    m_eExecSuccess = ETriState.UNDEFINED;
  }

  LongRunningJobData (@NonNull @Nonempty final String sID,
                      @Nullable final String sJobType,
                      @NonNull final LocalDateTime aStartDateTime,
                      @NonNull final LocalDateTime aEndDateTime,
                      @NonNull final ETriState eExecSuccess,
                      @Nullable final String sStartingUserID,
                      @NonNull final IMultilingualText aJobDescription,
                      @NonNull final LongRunningJobResult aResult)
  {
    m_sID = ValueEnforcer.notEmpty (sID, "ID");
    m_sJobType = sJobType;
    m_aStartDateTime = ValueEnforcer.notNull (aStartDateTime, "StartDateTime");
    m_aEndDateTime = ValueEnforcer.notNull (aEndDateTime, "EndDateTime");
    m_eExecSuccess = ValueEnforcer.notNull (eExecSuccess, "ExecSuccess");
    m_sStartingUserID = sStartingUserID;
    m_aJobDescription = ValueEnforcer.notNull (aJobDescription, "JobDescription");
    m_aResult = ValueEnforcer.notNull (aResult, "Result");
  }

  @NonNull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  /**
   * @return The type of the underlying job as returned by {@link ILongRunningJob#getJobType()}.
   *         This value is persisted, but it is <code>null</code> for job results that were written
   *         before v10.4.0 and therefore don't contain a job type at all.
   * @since 10.4.0
   */
  @Nullable
  public String getJobType ()
  {
    return m_sJobType;
  }

  /**
   * @return The description of the underlying job. Never <code>null</code>.
   */
  @NonNull
  public IMultilingualText getJobDescription ()
  {
    return m_aJobDescription;
  }

  /**
   * @return The date time when the job was started. Never <code>null</code>.
   */
  @NonNull
  public LocalDateTime getStartDateTime ()
  {
    return m_aStartDateTime;
  }

  /**
   * @return The user who started the job. May be <code>null</code>.
   */
  @Nullable
  public String getStartingUserID ()
  {
    return m_sStartingUserID;
  }

  /**
   * Remember the telemetry span that covers this job execution, together with the thread that
   * created it. The span is created in
   * {@link LongRunningJobManager#onStartJob(ILongRunningJob, String)} and must be closed again when
   * the job ends.
   *
   * @param aSpan
   *        The span to remember. May be <code>null</code>.
   */
  void setTelemetrySpan (@Nullable final ITelemetrySpan aSpan)
  {
    m_aTelemetrySpan = aSpan;
    m_aTelemetrySpanThread = aSpan == null ? null : Thread.currentThread ();
  }

  /**
   * @return The telemetry span that covers this job execution. May be <code>null</code>.
   */
  @Nullable
  ITelemetrySpan getTelemetrySpan ()
  {
    return m_aTelemetrySpan;
  }

  /**
   * @return The thread on which the telemetry span was created. May be <code>null</code>.
   */
  @Nullable
  Thread getTelemetrySpanThread ()
  {
    return m_aTelemetrySpanThread;
  }

  void onJobEnd (@NonNull final ESuccess eExecSuccess, @NonNull final LongRunningJobResult aResult)
  {
    ValueEnforcer.notNull (eExecSuccess, "ExecSuccess");
    ValueEnforcer.notNull (aResult, "Result");
    if (isEnded ())
      throw new IllegalStateException ("Job was already ended");

    // Save the date
    m_aEndDateTime = PDTFactory.getCurrentLocalDateTime ();
    m_eExecSuccess = ETriState.valueOf (eExecSuccess.isSuccess ());
    // Build the main results
    m_aResult = aResult;
    if (m_aResult == null)
      throw new IllegalStateException ("Failed to create job results object!");
  }

  /**
   * @return <code>true</code> if this job was already ended
   */
  public boolean isEnded ()
  {
    return m_aEndDateTime != null;
  }

  /**
   * @return The date and time when the job execution finished
   */
  @Nullable
  public LocalDateTime getEndDateTime ()
  {
    return m_aEndDateTime;
  }

  /**
   * @return The execution duration. Never <code>null</code>.
   */
  @NonNull
  public Duration getDuration ()
  {
    if (!isEnded ())
      throw new IllegalStateException ("Job is still running!");
    return Duration.between (m_aStartDateTime, getEndDateTime ());
  }

  /**
   * @return The technical success indicator, whether the scheduled job ran without an exception. Is
   *         {@link ETriState#UNDEFINED} if the result is not yet known. Never <code>null</code>.
   */
  @NonNull
  public ETriState getExecutionSuccess ()
  {
    return m_eExecSuccess;
  }

  /**
   * @return The semantic result of the execution.
   */
  @Nullable
  public LongRunningJobResult getResult ()
  {
    return m_aResult;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ID", m_sID)
                                       .append ("jobType", m_sJobType)
                                       .append ("jobDescription", m_aJobDescription)
                                       .append ("startDateTime", m_aStartDateTime)
                                       .append ("startingUserID", m_sStartingUserID)
                                       .append ("endDateTime", m_aEndDateTime)
                                       .append ("execSucces", m_eExecSuccess)
                                       .append ("result", m_aResult)
                                       .getToString ();
  }
}
