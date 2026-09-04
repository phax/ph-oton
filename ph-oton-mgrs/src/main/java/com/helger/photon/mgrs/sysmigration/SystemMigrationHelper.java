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
package com.helger.photon.mgrs.sysmigration;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.SuccessWithValue;
import com.helger.base.timing.StopWatch;
import com.helger.base.wrapper.Wrapper;
import com.helger.photon.mgrs.CMgrsTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.Telemetry;

/**
 * Helper class to perform default actions with {@link ISystemMigrationManager}.
 *
 * @author Philip Helger
 * @since 10.1.3
 */
@Immutable
public final class SystemMigrationHelper
{
  private static final Logger LOGGER = LoggerFactory.getLogger (SystemMigrationHelper.class);

  private SystemMigrationHelper ()
  {}

  /**
   * Perform a migration if it was not performed yet. The performed callback may not throw an error
   * or return an error. All migrations executed with this method will be handled as a success only.
   *
   * @param aSysMigMgr
   *        The system migration manager to use. May not be null.
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  public static void performMigrationIfNecessary (@NonNull final ISystemMigrationManager aSysMigMgr,
                                                  @NonNull @Nonempty final String sMigrationID,
                                                  @NonNull final Runnable aMigrationAction)
  {
    ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    ValueEnforcer.notNull (aMigrationAction, "MigrationAction");

    // Nothing is emitted for a migration that was already performed
    if (!aSysMigMgr.wasMigrationExecutedSuccessfully (sMigrationID))
    {
      final StopWatch aSW = StopWatch.createdStarted ();
      // Needs to be readable from the finally block below
      final Wrapper <String> aFailureKind = new Wrapper <> (CMgrsTelemetry.VALUE_FAILURE_NONE);
      try
      {
        Telemetry.withSpanVoid (CMgrsTelemetry.SPAN_MIGRATION_EXECUTE, ETelemetrySpanKind.INTERNAL, aSpan -> {
          SystemMigrationTelemetry.onMigrationStart (aSpan, sMigrationID);
          try
          {
            LOGGER.info ("Performing migration '" + sMigrationID + "'");

            // Invoke the callback
            aMigrationAction.run ();

            LOGGER.info ("Finished performing migration '" + sMigrationID + "'");

            // Always assume success
            aSysMigMgr.addMigrationResultSuccess (sMigrationID);
            SystemMigrationTelemetry.onMigrationSuccess (aSpan);
          }
          catch (final RuntimeException ex)
          {
            LOGGER.error ("Error execution system migration '" + sMigrationID + "'", ex);
            aSysMigMgr.addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
            aFailureKind.set (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL);
            SystemMigrationTelemetry.onMigrationTechnicalFailure (aSpan, sMigrationID, ex);
          }
        });
      }
      finally
      {
        SystemMigrationTelemetry.onMigrationEnd (sMigrationID, aFailureKind.get (), aSW.stopAndGetMillis ());
      }
    }
  }

  /**
   * Perform a migration if it was not performed yet.
   *
   * @param aSysMigMgr
   *        The system migration manager to use. May not be null.
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  public static void performMigrationIfNecessary (@NonNull final ISystemMigrationManager aSysMigMgr,
                                                  @NonNull @Nonempty final String sMigrationID,
                                                  @NonNull final Supplier <SuccessWithValue <String>> aMigrationAction)
  {
    ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    ValueEnforcer.notNull (aMigrationAction, "MigrationAction");

    // Nothing is emitted for a migration that was already performed
    if (!aSysMigMgr.wasMigrationExecutedSuccessfully (sMigrationID))
    {
      final StopWatch aSW = StopWatch.createdStarted ();
      // Needs to be readable from the finally block below
      final Wrapper <String> aFailureKind = new Wrapper <> (CMgrsTelemetry.VALUE_FAILURE_NONE);
      try
      {
        Telemetry.withSpanVoid (CMgrsTelemetry.SPAN_MIGRATION_EXECUTE, ETelemetrySpanKind.INTERNAL, aSpan -> {
          SystemMigrationTelemetry.onMigrationStart (aSpan, sMigrationID);
          try
          {
            LOGGER.info ("Performing migration '" + sMigrationID + "'");

            // Invoke the callback
            final SuccessWithValue <String> ret = aMigrationAction.get ();

            LOGGER.info ("Finished performing migration '" +
                         sMigrationID +
                         "' with status " +
                         (ret.isSuccess () ? "success" : "error"));

            // Success or error
            if (ret.isSuccess ())
            {
              aSysMigMgr.addMigrationResultSuccess (sMigrationID);
              SystemMigrationTelemetry.onMigrationSuccess (aSpan);
            }
            else
            {
              aSysMigMgr.addMigrationResultError (sMigrationID, ret.get ());
              aFailureKind.set (CMgrsTelemetry.VALUE_FAILURE_BUSINESS);
              SystemMigrationTelemetry.onMigrationBusinessFailure (aSpan, sMigrationID, ret.get ());
            }
          }
          catch (final RuntimeException ex)
          {
            LOGGER.error ("Error execution system migration '" + sMigrationID + "'", ex);
            aSysMigMgr.addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
            aFailureKind.set (CMgrsTelemetry.VALUE_FAILURE_TECHNICAL);
            SystemMigrationTelemetry.onMigrationTechnicalFailure (aSpan, sMigrationID, ex);
          }
        });
      }
      finally
      {
        SystemMigrationTelemetry.onMigrationEnd (sMigrationID, aFailureKind.get (), aSW.stopAndGetMillis ());
      }
    }
  }

}
