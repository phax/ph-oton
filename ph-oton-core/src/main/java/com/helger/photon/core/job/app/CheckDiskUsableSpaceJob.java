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
package com.helger.photon.core.job.app;

import java.io.File;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.ext.CommonsHashMap;
import com.helger.commons.collection.ext.ICommonsMap;
import com.helger.commons.io.misc.SizeHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.core.app.error.InternalErrorHandler;
import com.helger.photon.core.job.AbstractPhotonJob;
import com.helger.quartz.DisallowConcurrentExecution;
import com.helger.quartz.IJobExecutionContext;
import com.helger.quartz.JobExecutionException;
import com.helger.quartz.ScheduleBuilder;
import com.helger.quartz.SimpleScheduleBuilder;
import com.helger.quartz.ISimpleTrigger;
import com.helger.quartz.TriggerKey;
import com.helger.schedule.quartz.GlobalQuartzScheduler;
import com.helger.schedule.quartz.trigger.JDK8TriggerBuilder;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;

/**
 * Check whether at least x bytes of usable space is present on the file system
 * where the application .
 *
 * @author philip
 */
@DisallowConcurrentExecution
public class CheckDiskUsableSpaceJob extends AbstractPhotonJob
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (CheckDiskUsableSpaceJob.class);
  private static final String JOB_DATA_ATTR_THRESHOLD_BYTES = "threshold-bytes";

  /**
   * Public no argument constructor must be available.
   */
  public CheckDiskUsableSpaceJob ()
  {}

  @Override
  protected void onExecute (@Nonnull final IJobExecutionContext aContext) throws JobExecutionException
  {
    final long nThresholdBytes = aContext.getJobDetail ().getJobDataMap ().getLong (JOB_DATA_ATTR_THRESHOLD_BYTES);
    final File aBaseDir = WebFileIO.getDataIO ().getBasePathFile ();

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Checking for usable space on " +
                       aBaseDir.getAbsolutePath () +
                       " with a threshold of " +
                       nThresholdBytes +
                       " bytes");

    final long nUsableSpace = aBaseDir.getUsableSpace ();
    if (nUsableSpace <= nThresholdBytes)
    {
      final SizeHelper aSH = SizeHelper.getSizeHelperOfLocale (CGlobal.LOCALE_FIXED_NUMBER_FORMAT);
      final String sThresholdFormatted = aSH.getAsMatching (nThresholdBytes, 3);
      final String sUsableFormatted = aSH.getAsMatching (nUsableSpace, 3);
      s_aLogger.warn ("File system has less or equal than " +
                      sThresholdFormatted +
                      " of usable space: " +
                      sUsableFormatted);

      // Main error thread dump
      final String sMailBody = InternalErrorHandler.fillInternalErrorMetaData (null, "out-of-usable-disk-space", null)
                                                   .addField ("threshold space", sThresholdFormatted)
                                                   .addField ("usable space", sUsableFormatted)
                                                   .getAsString ();

      final EmailData aEmailData = new EmailData (EEmailType.TEXT);
      aEmailData.setFrom (InternalErrorHandler.getSMTPSenderAddress ());
      aEmailData.setTo (InternalErrorHandler.getSMTPReceiverAddresses ());
      aEmailData.setSubject ("[system-monitor] Usable Disk Space is low: " + sUsableFormatted);
      aEmailData.setBody (sMailBody);
      ScopedMailAPI.getInstance ().queueMail (InternalErrorHandler.getSMTPSettings (), aEmailData);
    }
  }

  /**
   * Call this method to schedule the check disk usage job to run.
   *
   * @param aScheduleBuilder
   *        The schedule builder to be used. May not be <code>null</code>.
   *        Example:
   *        <code>SimpleScheduleBuilder.repeatMinutelyForever (60)</code>
   * @param sApplicationID
   *        The internal application ID to be used. May neither be
   *        <code>null</code> nor empty.
   * @param nThresholdBytes
   *        If &le; than this number of bytes are free on the drive, an internal
   *        notification email is send. Must be &ge; 0.
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @Nonnull
  public static TriggerKey schedule (@Nonnull final ScheduleBuilder <ISimpleTrigger> aScheduleBuilder,
                                     @Nonnull @Nonempty final String sApplicationID,
                                     @Nonnegative final long nThresholdBytes)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");
    ValueEnforcer.isGE0 (nThresholdBytes, "ThresholdBytes");

    setApplicationScopeID (sApplicationID);

    final ICommonsMap <String, Object> aJobDataMap = new CommonsHashMap<> ();
    aJobDataMap.put (JOB_DATA_ATTR_THRESHOLD_BYTES, Long.valueOf (nThresholdBytes));

    return GlobalQuartzScheduler.getInstance ().scheduleJob (CheckDiskUsableSpaceJob.class.getName (),
                                                             JDK8TriggerBuilder.newTrigger ()
                                                                               .startNow ()
                                                                               .withSchedule (SimpleScheduleBuilder.repeatMinutelyForever (60)),
                                                             CheckDiskUsableSpaceJob.class,
                                                             aJobDataMap);
  }
}
