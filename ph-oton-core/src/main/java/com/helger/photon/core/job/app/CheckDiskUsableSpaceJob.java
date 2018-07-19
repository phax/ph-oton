/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
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
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.io.misc.SizeHelper;
import com.helger.photon.basic.app.io.WebFileIO;
import com.helger.photon.core.app.error.InternalErrorHandler;
import com.helger.photon.core.app.error.InternalErrorSettings;
import com.helger.quartz.DisallowConcurrentExecution;
import com.helger.quartz.IJobExecutionContext;
import com.helger.quartz.IScheduleBuilder;
import com.helger.quartz.ITrigger;
import com.helger.quartz.JobDataMap;
import com.helger.quartz.JobExecutionException;
import com.helger.quartz.TriggerKey;
import com.helger.schedule.quartz.GlobalQuartzScheduler;
import com.helger.schedule.quartz.trigger.JDK8TriggerBuilder;
import com.helger.smtp.data.EEmailType;
import com.helger.smtp.data.EmailData;
import com.helger.smtp.scope.ScopedMailAPI;
import com.helger.web.scope.util.AbstractScopeAwareJob;

/**
 * Check whether at least x bytes of usable space is present on the file system
 * where the application .
 *
 * @author philip
 */
@DisallowConcurrentExecution
public final class CheckDiskUsableSpaceJob extends AbstractScopeAwareJob
{
  private static final Logger LOGGER = LoggerFactory.getLogger (CheckDiskUsableSpaceJob.class);
  private static final String JOB_DATA_ATTR_THRESHOLD_BYTES = "threshold-bytes";

  /**
   * Public no argument constructor must be available.
   */
  public CheckDiskUsableSpaceJob ()
  {}

  @Override
  protected void onExecute (@Nonnull final JobDataMap aJobDataMap,
                            @Nonnull final IJobExecutionContext aContext) throws JobExecutionException
  {
    final long nThresholdBytes = aJobDataMap.getAsLong (JOB_DATA_ATTR_THRESHOLD_BYTES);
    final File aBaseDir = WebFileIO.getDataIO ().getBasePathFile ();

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Checking for usable space on " +
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
      LOGGER.warn ("File system has less or equal than " +
                      sThresholdFormatted +
                      " of usable space: " +
                      sUsableFormatted);

      // Main error thread dump
      final String sMailBody = InternalErrorHandler.fillInternalErrorMetaData (null, "out-of-usable-disk-space", null)
                                                   .addField ("threshold space", sThresholdFormatted)
                                                   .addField ("usable space", sUsableFormatted)
                                                   .getAsString ();

      final EmailData aEmailData = new EmailData (EEmailType.TEXT);
      aEmailData.setFrom (InternalErrorSettings.getSMTPSenderAddress ());
      aEmailData.setTo (InternalErrorSettings.getSMTPReceiverAddresses ());
      aEmailData.setSubject ("[ph-oton] Usable Disk Space is low: " + sUsableFormatted);
      aEmailData.setBody (sMailBody);
      ScopedMailAPI.getInstance ().queueMail (InternalErrorSettings.getSMTPSettings (), aEmailData);
    }
  }

  /**
   * Call this method to schedule the check disk usage job to run.
   *
   * @param aScheduleBuilder
   *        The schedule builder to be used. May not be <code>null</code>.
   *        Example:
   *        <code>SimpleScheduleBuilder.repeatMinutelyForever (60)</code>
   * @param nThresholdBytes
   *        If &le; than this number of bytes are free on the drive, an internal
   *        notification email is send. Must be &ge; 0.
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @Nonnull
  public static TriggerKey schedule (@Nonnull final IScheduleBuilder <? extends ITrigger> aScheduleBuilder,
                                     @Nonnegative final long nThresholdBytes)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");
    ValueEnforcer.isGE0 (nThresholdBytes, "ThresholdBytes");

    final ICommonsMap <String, Object> aJobDataMap = new CommonsHashMap <> ();
    aJobDataMap.put (JOB_DATA_ATTR_THRESHOLD_BYTES, Long.valueOf (nThresholdBytes));

    return GlobalQuartzScheduler.getInstance ().scheduleJob (CheckDiskUsableSpaceJob.class.getName (),
                                                             JDK8TriggerBuilder.newTrigger ()
                                                                               .startNow ()
                                                                               .withSchedule (aScheduleBuilder),
                                                             CheckDiskUsableSpaceJob.class,
                                                             aJobDataMap);
  }
}
