/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.job.smtp;

import javax.annotation.Nonnull;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.photon.core.job.AbstractPhotonJob;
import com.helger.photon.core.mgr.PhotonCoreManager;
import com.helger.schedule.quartz.GlobalQuartzScheduler;
import com.helger.schedule.quartz.trigger.JDK8TriggerBuilder;
import com.helger.smtp.failed.FailedMailData;
import com.helger.smtp.scope.ScopedMailAPI;

/**
 * A Quartz job, that tries to re-send failed mails.
 *
 * @author Philip Helger
 */
@DisallowConcurrentExecution
public class FailedMailResendJob extends AbstractPhotonJob
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (FailedMailResendJob.class);

  /**
   * Public no argument constructor must be available.
   */
  public FailedMailResendJob ()
  {}

  @Override
  protected void onExecute (final JobExecutionContext aContext) throws JobExecutionException
  {
    final ICommonsList <FailedMailData> aFailedMails = PhotonCoreManager.getFailedMailQueue ().removeAll ();
    if (!aFailedMails.isEmpty ())
    {
      s_aLogger.info ("Resending " + aFailedMails.size () + " failed mails!");
      for (final FailedMailData aFailedMailData : aFailedMails)
        ScopedMailAPI.getInstance ().queueMail (aFailedMailData.getSMTPSettings (), aFailedMailData.getEmailData ());
    }
  }

  /**
   * @param aScheduleBuilder
   *        The schedule builder to be used. May not be <code>null</code>.
   *        Example:
   *        <code>SimpleScheduleBuilder.repeatMinutelyForever (60)</code>
   * @param sApplicationID
   *        The internal application ID to be used. May neither be
   *        <code>null</code> nor empty.
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @Nonnull
  public static TriggerKey schedule (@Nonnull final SimpleScheduleBuilder aScheduleBuilder,
                                     @Nonnull @Nonempty final String sApplicationID)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");

    setApplicationScopeID (sApplicationID);
    return GlobalQuartzScheduler.getInstance ().scheduleJob (FailedMailResendJob.class.getName (),
                                                             JDK8TriggerBuilder.newTrigger ()
                                                                               .startNow ()
                                                                               .withSchedule (aScheduleBuilder),
                                                             FailedMailResendJob.class,
                                                             null);
  }
}
