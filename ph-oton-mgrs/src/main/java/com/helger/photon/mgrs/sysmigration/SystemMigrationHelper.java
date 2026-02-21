package com.helger.photon.mgrs.sysmigration;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.SuccessWithValue;

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

    if (!aSysMigMgr.wasMigrationExecutedSuccessfully (sMigrationID))
    {
      try
      {
        LOGGER.info ("Performing migration '" + sMigrationID + "'");

        // Invoke the callback
        aMigrationAction.run ();

        LOGGER.info ("Finished performing migration '" + sMigrationID + "'");

        // Always assume success
        aSysMigMgr.addMigrationResultSuccess (sMigrationID);
      }
      catch (final RuntimeException ex)
      {
        LOGGER.error ("Error execution system migration '" + sMigrationID + "'", ex);
        aSysMigMgr.addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
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

    if (!aSysMigMgr.wasMigrationExecutedSuccessfully (sMigrationID))
    {
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
          aSysMigMgr.addMigrationResultSuccess (sMigrationID);
        else
          aSysMigMgr.addMigrationResultError (sMigrationID, ret.get ());
      }
      catch (final RuntimeException ex)
      {
        LOGGER.error ("Error execution system migration '" + sMigrationID + "'", ex);
        aSysMigMgr.addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
      }
    }
  }

}
