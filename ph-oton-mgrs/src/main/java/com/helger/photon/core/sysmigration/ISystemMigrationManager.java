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
package com.helger.photon.core.sysmigration;

import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.state.SuccessWithValue;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsSet;

/**
 * Interface for system migration managers.
 *
 * @author Philip Helger
 * @since 10.1.3
 */
@ThreadSafe
public interface ISystemMigrationManager
{
  // Maximum length
  int MIGRATION_ID_MAX_LENGTH = 256;

  /**
   * Add a migration result.
   *
   * @param aMigrationResult
   *        The result to add. May not be <code>null</code>.
   */
  void addMigrationResult (@NonNull SystemMigrationResult aMigrationResult);

  /**
   * Mark the specified migration as success.
   *
   * @param sMigrationID
   *        The migration ID to be added. May neither be <code>null</code> nor empty.
   */
  default void addMigrationResultSuccess (@NonNull @Nonempty final String sMigrationID)
  {
    addMigrationResult (SystemMigrationResult.createSuccess (sMigrationID));
  }

  /**
   * Mark the specified migration as failed.
   *
   * @param sMigrationID
   *        The migration ID to be added. May neither be <code>null</code> nor empty.
   * @param sErrorMsg
   *        The error message. May not be <code>null</code>.
   */
  default void addMigrationResultError (@NonNull @Nonempty final String sMigrationID, @NonNull final String sErrorMsg)
  {
    addMigrationResult (SystemMigrationResult.createFailure (sMigrationID, sErrorMsg));
  }

  /**
   * Get all migration results for the given migration ID.
   *
   * @param sMigrationID
   *        The migration ID to query. May be <code>null</code>.
   * @return A non-<code>null</code> list of all results.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <SystemMigrationResult> getAllMigrationResults (@Nullable String sMigrationID);

  /**
   * Get all migration results flattened into a single list.
   *
   * @return A non-<code>null</code> list of all results.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <SystemMigrationResult> getAllMigrationResultsFlattened ();

  /**
   * Get all failed migration results for the given migration ID.
   *
   * @param sMigrationID
   *        The migration ID to query. May be <code>null</code>.
   * @return A non-<code>null</code> list of all failed results.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <SystemMigrationResult> getAllFailedMigrationResults (@Nullable String sMigrationID);

  /**
   * Check if the given migration was already executed successfully.
   *
   * @param sMigrationID
   *        The migration ID to check. May be <code>null</code>.
   * @return <code>true</code> if the migration was executed successfully at least once.
   */
  boolean wasMigrationExecutedSuccessfully (@Nullable String sMigrationID);

  /**
   * Get all known migration IDs.
   *
   * @return A non-<code>null</code> set of all migration IDs.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsSet <String> getAllMigrationIDs ();

  /**
   * Perform a migration if it was not performed yet. The performed callback may not throw an error
   * or return an error. All migrations executed with this method will be handled as a success only.
   *
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  void performMigrationIfNecessary (@NonNull @Nonempty String sMigrationID, @NonNull Runnable aMigrationAction);

  /**
   * Perform a migration if it was not performed yet.
   *
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  void performMigrationIfNecessary (@NonNull @Nonempty String sMigrationID,
                                    @NonNull Supplier <SuccessWithValue <String>> aMigrationAction);
}
