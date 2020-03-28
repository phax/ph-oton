/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.core.longrun;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsCollection;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.state.ESuccess;

@ThreadSafe
public final class LongRunningJobManager
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LongRunningJobManager.class);

  private final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  @GuardedBy ("m_aRWLock")
  private final ICommonsMap <String, LongRunningJobData> m_aRunningJobs = new CommonsHashMap <> ();
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
    final LongRunningJobData aJobData = new LongRunningJobData (sJobID, aJob.getJobDescription (), sStartingUserID);
    m_aRWLock.writeLockedGet ( () -> m_aRunningJobs.put (sJobID, aJobData));
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
    final LongRunningJobData aJobData = m_aRWLock.writeLockedGet ( () -> {
      final LongRunningJobData ret = m_aRunningJobs.remove (sJobID);
      if (ret == null)
        throw new IllegalArgumentException ("Illegal job ID '" + sJobID + "' passed!");

      // End the job - inside the writeLock
      ret.onJobEnd (eExecSucess, aResult);
      return ret;
    });

    // Remember it
    m_aResultMgr.addResult (aJobData);
  }

  @Nonnegative
  public int getRunningJobCount ()
  {
    return m_aRWLock.readLockedInt (m_aRunningJobs::size);
  }

  @Nonnull
  @Nonempty
  public ICommonsCollection <LongRunningJobData> getAllRunningJobs ()
  {
    return m_aRWLock.readLockedGet (m_aRunningJobs::copyOfValues);
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

      LOGGER.error ("There are still " +
                    nRunningJobCount +
                    " long running jobs in the background! Waiting for them to finish...");

      // Wait some time
      Thread.sleep (nWaitSeconds * CGlobal.MILLISECONDS_PER_SECOND);

      if (nWaitSeconds < 10)
        nWaitSeconds++;
    }
  }
}
