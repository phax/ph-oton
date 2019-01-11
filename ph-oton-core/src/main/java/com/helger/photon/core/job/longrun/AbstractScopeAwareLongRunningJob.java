/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.core.job.longrun;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.state.ESuccess;
import com.helger.photon.basic.longrun.ILongRunningJob;
import com.helger.photon.basic.longrun.LongRunningJobManager;
import com.helger.photon.basic.longrun.LongRunningJobResult;
import com.helger.photon.basic.mgr.PhotonBasicManager;
import com.helger.quartz.IJobExecutionContext;
import com.helger.quartz.JobDataMap;
import com.helger.scope.mgr.ScopeManager;
import com.helger.web.scope.util.AbstractScopeAwareJob;

/**
 * Abstract scope aware long running job.
 *
 * @author Philip Helger
 */
public abstract class AbstractScopeAwareLongRunningJob extends AbstractScopeAwareJob implements ILongRunningJob
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractScopeAwareLongRunningJob.class);

  /** Predefined key into the job data map */
  private static final String KEY_LONG_RUNNING_JOB_ID = ScopeManager.SCOPE_ATTRIBUTE_PREFIX_INTERNAL +
                                                        "scope.longrunning.job.id";

  /**
   * Get the ID of the current user who executes the job.
   *
   * @param aJobDataMap
   *        The current job data map. Never <code>null</code>.
   * @return The user ID to be used. May be <code>null</code> but it is
   *         recommended to deliver "guest" or the like instead.
   */
  @Nullable
  protected abstract String getCurrentUserID (@Nonnull JobDataMap aJobDataMap);

  /**
   * @return The {@link LongRunningJobManager} to be used. May not return
   *         <code>null</code>.
   */
  @Nonnull
  protected LongRunningJobManager getLongRunningJobManager ()
  {
    return PhotonBasicManager.getLongRunningJobMgr ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void beforeExecuteInScope (@Nonnull final JobDataMap aJobDataMap,
                                       @Nonnull final IJobExecutionContext aContext)
  {
    final String sUserID = getCurrentUserID (aJobDataMap);

    // Remember that a long running job is starting
    final String sLongRunningJobID = getLongRunningJobManager ().onStartJob (this, sUserID);

    // Store in JobDataMap
    aJobDataMap.put (KEY_LONG_RUNNING_JOB_ID, sLongRunningJobID);
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void afterExecuteInScope (@Nonnull final JobDataMap aJobDataMap,
                                      @Nonnull final IJobExecutionContext aContext,
                                      @Nonnull final ESuccess eExecSuccess)
  {
    // End long running job before the request scope is closed
    try
    {
      // Get long running job ID from JobDataMap
      final String sLongRunningJobID = aJobDataMap.getAsString (KEY_LONG_RUNNING_JOB_ID);
      if (sLongRunningJobID != null)
      {
        // Create the main result
        final LongRunningJobResult aJobResult = createLongRunningJobResult ();

        // Mark the long running job as finished
        getLongRunningJobManager ().onEndJob (sLongRunningJobID, eExecSuccess, aJobResult);
      }
      else
        LOGGER.error ("Failed to retrieve long running job ID from JobDataMap " + aJobDataMap);
    }
    catch (final Throwable t)
    {
      LOGGER.error ("Failed to end long running job", t);

      // Notify custom exception handler
      triggerCustomExceptionHandler (t, getClass ().getName (), this);
    }
  }
}
