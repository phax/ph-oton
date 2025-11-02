/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.core.job.app;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.commons.deadlock.ThreadDeadlockDetector;
import com.helger.photon.core.interror.callback.MailingThreadDeadlockCallback;
import com.helger.quartz.DisallowConcurrentExecution;
import com.helger.quartz.IJobExecutionContext;
import com.helger.quartz.JobDataMap;
import com.helger.quartz.JobExecutionException;
import com.helger.quartz.SimpleScheduleBuilder;
import com.helger.quartz.TriggerKey;
import com.helger.schedule.quartz.GlobalQuartzScheduler;
import com.helger.schedule.quartz.trigger.JDK8TriggerBuilder;
import com.helger.web.scope.util.AbstractScopeAwareJob;

/**
 * A Quartz job to be scheduled to check for thread dead locks.
 *
 * @author Philip Helger
 */
@DisallowConcurrentExecution
public final class CheckThreadDeadlockJob extends AbstractScopeAwareJob
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CheckThreadDeadlockJob.class);

  /**
   * Public no argument constructor must be available.
   */
  public CheckThreadDeadlockJob ()
  {}

  @Override
  protected void onExecute (@NonNull final JobDataMap aJobDataMap,
                            @NonNull final IJobExecutionContext aContext) throws JobExecutionException
  {
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Checking for dead locks");

    final ThreadDeadlockDetector aTDD = new ThreadDeadlockDetector ();
    aTDD.callbacks ().add (new MailingThreadDeadlockCallback ());
    aTDD.findDeadlockedThreads ();
  }

  /**
   * Call this method to schedule the deadlock detection job.
   *
   * @param aScheduleBuilder
   *        The schedule builder to be used. May not be <code>null</code>.
   *        Example:
   *        <code>SimpleScheduleBuilder.repeatMinutelyForever (2)</code>
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @NonNull
  public static TriggerKey schedule (@NonNull final SimpleScheduleBuilder aScheduleBuilder)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");

    final ICommonsMap <String, Object> aJobDataMap = new CommonsHashMap <> ();

    return GlobalQuartzScheduler.getInstance ()
                                .scheduleJob (CheckThreadDeadlockJob.class.getName (),
                                              JDK8TriggerBuilder.newTrigger ().startNow ().withSchedule (aScheduleBuilder),
                                              CheckThreadDeadlockJob.class,
                                              aJobDataMap);
  }
}
