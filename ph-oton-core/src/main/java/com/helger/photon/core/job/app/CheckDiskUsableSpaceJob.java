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
package com.helger.photon.core.job.app;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonnegative;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.collection.commons.CommonsHashMap;
import com.helger.collection.commons.ICommonsMap;
import com.helger.io.misc.SizeHelper;
import com.helger.photon.core.interror.InternalErrorHandler;
import com.helger.photon.core.interror.InternalErrorSettings;
import com.helger.photon.io.WebFileIO;
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
  private static final String JOB_DATA_ATTR_PATH_SUPPLIER = "path-supplier";
  private static final AtomicInteger JOB_COUNT = new AtomicInteger (0);

  /**
   * Public no argument constructor must be available.
   */
  public CheckDiskUsableSpaceJob ()
  {}

  @Override
  protected void onExecute (@NonNull final JobDataMap aJobDataMap, @NonNull final IJobExecutionContext aContext)
                                                                                                                 throws JobExecutionException
  {
    final long nThresholdBytes = aJobDataMap.getAsLong (JOB_DATA_ATTR_THRESHOLD_BYTES);
    final Supplier <File> aPathSupplier = aJobDataMap.getCastedValue (JOB_DATA_ATTR_PATH_SUPPLIER);
    final File aBasePath = aPathSupplier.get ();
    final String sBasePath = aBasePath.getAbsolutePath ();

    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Checking for usable space on '" +
                    sBasePath +
                    "' with a threshold of " +
                    nThresholdBytes +
                    " bytes");

    final long nUsableSpace = aBasePath.getUsableSpace ();
    if (nUsableSpace <= nThresholdBytes)
    {
      final SizeHelper aSH = SizeHelper.getSizeHelperOfLocale (Locale.US);
      final String sThresholdFormatted = aSH.getAsMatching (nThresholdBytes, 3);
      final String sUsableFormatted = aSH.getAsMatching (nUsableSpace, 3);

      LOGGER.warn ("File system of '" +
                   sBasePath +
                   "' has less or equal than " +
                   sThresholdFormatted +
                   " of usable space: " +
                   sUsableFormatted);

      // Main error thread dump
      final String sMailBody = InternalErrorHandler.fillInternalErrorMetaData (null, "out-of-usable-disk-space", null)
                                                   .addField ("base path", sBasePath)
                                                   .addField ("threshold space", sThresholdFormatted)
                                                   .addField ("usable space", sUsableFormatted)
                                                   .getAsString ();

      final EmailData aEmailData = new EmailData (EEmailType.TEXT);
      aEmailData.setFrom (InternalErrorSettings.getSMTPSenderAddress ());
      aEmailData.to ().addAll (InternalErrorSettings.getSMTPReceiverAddresses ());
      aEmailData.setSubject ("[ph-oton] Usable Disk Space on '" + sBasePath + "' is low: " + sUsableFormatted);
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
  @NonNull
  public static TriggerKey schedule (@NonNull final IScheduleBuilder <? extends ITrigger> aScheduleBuilder,
                                     @Nonnegative final long nThresholdBytes)
  {
    // Use indirection to ensure initialization order does not matter
    return schedule (aScheduleBuilder, nThresholdBytes, () -> WebFileIO.getDataIO ().getBasePathFile ());
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
   * @param aPathSupplier
   *        The serializable supplier for the path where the size should be
   *        checked.
   * @return The created trigger key for further usage. Never <code>null</code>.
   */
  @NonNull
  public static TriggerKey schedule (@NonNull final IScheduleBuilder <? extends ITrigger> aScheduleBuilder,
                                     @Nonnegative final long nThresholdBytes,
                                     @NonNull final Supplier <File> aPathSupplier)
  {
    ValueEnforcer.notNull (aScheduleBuilder, "ScheduleBuilder");
    ValueEnforcer.isGE0 (nThresholdBytes, "ThresholdBytes");
    ValueEnforcer.notNull (aPathSupplier, "PathSupplier");

    final ICommonsMap <String, Object> aJobDataMap = new CommonsHashMap <> ();
    aJobDataMap.put (JOB_DATA_ATTR_THRESHOLD_BYTES, Long.valueOf (nThresholdBytes));
    aJobDataMap.put (JOB_DATA_ATTR_PATH_SUPPLIER, aPathSupplier);

    // Ensure unique name in case this job is scheduled for different
    // directories
    return GlobalQuartzScheduler.getInstance ()
                                .scheduleJob (CheckDiskUsableSpaceJob.class.getName () + JOB_COUNT.incrementAndGet (),
                                              JDK8TriggerBuilder.newTrigger ()
                                                                .startNow ()
                                                                .withSchedule (aScheduleBuilder),
                                              CheckDiskUsableSpaceJob.class,
                                              aJobDataMap);
  }
}
