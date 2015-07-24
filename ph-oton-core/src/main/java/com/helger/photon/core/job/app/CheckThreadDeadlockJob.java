/**
 * Copyright (C) 2006-2015 BRZ GmbH
 * http://www.brz.gv.at
 *
 * All rights reserved
 */
package com.helger.photon.core.job.app;

import javax.annotation.Nonnull;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.ScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.deadlock.ThreadDeadlockDetector;
import com.helger.photon.core.app.error.MailingThreadDeadlockCallback;
import com.helger.photon.core.job.AbstractPhotonJob;
import com.helger.schedule.quartz.GlobalQuartzScheduler;

/**
 * A Quartz job to be scheduled to check for thread dead locks.
 *
 * @author Philip Helger
 */
@DisallowConcurrentExecution
public class CheckThreadDeadlockJob extends AbstractPhotonJob
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CheckThreadDeadlockJob.class);

  /**
   * Public no argument constructor must be available.
   */
  public CheckThreadDeadlockJob ()
  {}

  @Override
  protected void onExecute (@Nonnull final JobExecutionContext aContext) throws JobExecutionException
  {
    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Checking for dead locks");

    final ThreadDeadlockDetector aTDD = new ThreadDeadlockDetector ();
    aTDD.addCallback (new MailingThreadDeadlockCallback ());
    aTDD.findDeadlockedThreads ();
  }

  /**
   * Call this method to schedule the deadlock detection job.
   *
   * @param aScheduleBuilder
   *        The schedule builder to be used. May not be <code>null</code>.
   *        Example:
   *        <code>SimpleScheduleBuilder.repeatMinutelyForever (2)</code>
   * @param sApplicationID
   *        The internal application ID to be used. May neither be
   *        <code>null</code> nor empty.
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @Nonnull
  public static TriggerKey schedule (@Nonnull final ScheduleBuilder <SimpleTrigger> aScheduleBuilder,
                                     @Nonnull @Nonempty final String sApplicationID)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");

    setApplicationScopeID (sApplicationID);

    return GlobalQuartzScheduler.getInstance ().scheduleJob (CheckThreadDeadlockJob.class.getName (),
                                                             TriggerBuilder.newTrigger ()
                                                                           .startNow ()
                                                                           .withSchedule (aScheduleBuilder),
                                                             CheckThreadDeadlockJob.class,
                                                             null);
  }
}
