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
package com.helger.photon.basic.longrun;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.idfactory.GlobalIDFactory;
import com.helger.commons.state.ESuccess;

@ThreadSafe
public final class LongRunningJobManager
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LongRunningJobManager.class);

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, LongRunningJobData> m_aRunningJobs = new HashMap <String, LongRunningJobData> ();
  private final LongRunningJobResultManager m_aResultMgr;

  public LongRunningJobManager (@Nonnull final LongRunningJobResultManager aResultMgr)
  {
    m_aResultMgr = ValueEnforcer.notNull (aResultMgr, "ResultMgr");
  }

  /**
   * Start a long running job
   *
   * @param aJob
   *        The job that is to be started
   * @param sStartingUserID
   *        The ID of the user who started the job
   * @return The internal long running job ID
   */
  @Nonnull
  @Nonempty
  public String onStartJob (@Nonnull final ILongRunningJob aJob, @Nullable final String sStartingUserID)
  {
    ValueEnforcer.notNull (aJob, "Job");

    // Create a new unique in-memory ID
    final String sJobID = GlobalIDFactory.getNewStringID ();
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aRunningJobs.put (sJobID, new LongRunningJobData (sJobID, aJob.getJobDescription (), sStartingUserID));
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
    return sJobID;
  }

  /**
   * End a job.
   *
   * @param sJobID
   *        The internal long running job ID created from
   *        {@link #onStartJob(ILongRunningJob,String)}.
   * @param eExecSucess
   *        Was the job execution successful or not from a technical point of
   *        view? May not be <code>null</code>. If a JobExecutionException was
   *        thrown, this should be {@link ESuccess#FAILURE}.
   * @param aResult
   *        The main job results.
   */
  public void onEndJob (@Nullable final String sJobID,
                        @Nonnull final ESuccess eExecSucess,
                        @Nonnull final LongRunningJobResult aResult)
  {
    ValueEnforcer.notNull (eExecSucess, "ExecSuccess");
    ValueEnforcer.notNull (aResult, "Result");

    // Remove from running job list
    LongRunningJobData aJobData;
    m_aRWLock.writeLock ().lock ();
    try
    {
      aJobData = m_aRunningJobs.remove (sJobID);
      if (aJobData == null)
        throw new IllegalArgumentException ("Illegal job ID '" + sJobID + "' passed!");

      // End the job - inside the writeLock
      aJobData.onJobEnd (eExecSucess, aResult);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    // Remember it
    m_aResultMgr.addResult (aJobData);
  }

  @Nonnegative
  public int getRunningJobCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aRunningJobs.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnull
  @Nonempty
  public Collection <LongRunningJobData> getAllRunningJobs ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aRunningJobs.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void waitUntilAllJobsAreFinished () throws InterruptedException
  {
    // Wait until all long running jobs are finished
    int nWaitSeconds = 1;
    while (true)
    {
      final int nRunningJobCount = getRunningJobCount ();
      if (nRunningJobCount == 0)
        break;

      s_aLogger.error ("There are still " +
                       nRunningJobCount +
                       " long running jobs in the background! Waiting for them to finish...");

      // Wait some time
      Thread.sleep (nWaitSeconds * CGlobal.MILLISECONDS_PER_SECOND);

      if (nWaitSeconds < 10)
        nWaitSeconds++;
    }
  }
}
