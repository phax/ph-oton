/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.basic.migration;

import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.collection.multimap.IMultiMapListBased;
import com.helger.collection.multimap.MultiHashMapArrayListBased;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsSet;
import com.helger.commons.state.EChange;
import com.helger.commons.state.SuccessWithValue;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.type.ObjectType;
import com.helger.photon.basic.app.dao.impl.AbstractSimpleDAO;
import com.helger.photon.basic.app.dao.impl.DAOException;
import com.helger.photon.basic.audit.AuditHelper;
import com.helger.xml.microdom.IMicroDocument;
import com.helger.xml.microdom.IMicroElement;
import com.helger.xml.microdom.MicroDocument;
import com.helger.xml.microdom.convert.MicroTypeConverter;

@ThreadSafe
public class SystemMigrationManager extends AbstractSimpleDAO
{
  public static final ObjectType OT_SYSTEM_MIGRATION_RESULT = new ObjectType ("systemmigrationresult");

  private static final Logger s_aLogger = LoggerFactory.getLogger (SystemMigrationManager.class);
  private static final String ELEMENT_SYSTEM_MIGRATION_RESULTS = "systemmigrationresults";
  private static final String ELEMENT_SYSTEM_MIGRATION_RESULT = "systemmigrationresult";

  private final IMultiMapListBased <String, SystemMigrationResult> m_aMap = new MultiHashMapArrayListBased<> ();

  public SystemMigrationManager (@Nullable final String sFilename) throws DAOException
  {
    super (sFilename);
    initialRead ();
  }

  @Override
  protected EChange onRead (final IMicroDocument aDoc)
  {
    for (final IMicroElement eMigrationResult : aDoc.getDocumentElement ()
                                                    .getAllChildElements (ELEMENT_SYSTEM_MIGRATION_RESULT))
      internalAdd (MicroTypeConverter.convertToNative (eMigrationResult, SystemMigrationResult.class));
    return EChange.UNCHANGED;
  }

  @Override
  protected IMicroDocument createWriteData ()
  {
    final IMicroDocument aDoc = new MicroDocument ();
    final IMicroElement eRoot = aDoc.appendElement (ELEMENT_SYSTEM_MIGRATION_RESULTS);
    for (final List <SystemMigrationResult> aMigrationResults : m_aMap.getSortedByKey (Comparator.naturalOrder ())
                                                                      .values ())
      for (final SystemMigrationResult aMigrationResult : aMigrationResults)
        eRoot.appendChild (MicroTypeConverter.convertToMicroElement (aMigrationResult,
                                                                     ELEMENT_SYSTEM_MIGRATION_RESULT));
    return aDoc;
  }

  void internalAdd (@Nonnull final SystemMigrationResult aMigrationResult)
  {
    ValueEnforcer.notNull (aMigrationResult, "MigrationResult");

    final String sMigrationID = aMigrationResult.getID ();
    m_aMap.putSingle (sMigrationID, aMigrationResult);
  }

  public void addMigrationResult (@Nonnull final SystemMigrationResult aMigrationResult)
  {
    ValueEnforcer.notNull (aMigrationResult, "MigrationResult");

    m_aRWLock.writeLocked ( () -> {
      internalAdd (aMigrationResult);
      markAsChanged ();
    });

    AuditHelper.onAuditCreateSuccess (OT_SYSTEM_MIGRATION_RESULT,
                                      aMigrationResult.getID (),
                                      Boolean.valueOf (aMigrationResult.isSuccess ()),
                                      aMigrationResult.getErrorMessage ());
  }

  /**
   * Mark the specified migration as success.
   *
   * @param sMigrationID
   *        The migration ID to be added. May neither be <code>null</code> nor
   *        empty.
   */
  public void addMigrationResultSuccess (@Nonnull @Nonempty final String sMigrationID)
  {
    addMigrationResult (SystemMigrationResult.createSuccess (sMigrationID));
  }

  /**
   * Mark the specified migration as failed.
   *
   * @param sMigrationID
   *        The migration ID to be added. May neither be <code>null</code> nor
   *        empty.
   * @param sErrorMsg
   *        The error message. May not be <code>null</code>.
   */
  public void addMigrationResultError (@Nonnull @Nonempty final String sMigrationID, @Nonnull final String sErrorMsg)
  {
    addMigrationResult (SystemMigrationResult.createFailure (sMigrationID, sErrorMsg));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllMigrationResults (@Nullable final String sMigrationID)
  {
    return m_aRWLock.readLocked ( () -> new CommonsArrayList<> (m_aMap.get (sMigrationID)));
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllMigrationResultsFlattened ()
  {
    final ICommonsList <SystemMigrationResult> ret = new CommonsArrayList<> ();
    m_aRWLock.readLocked ( () -> {
      for (final ICommonsList <SystemMigrationResult> aResults : m_aMap.values ())
        ret.addAll (aResults);
    });
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <SystemMigrationResult> getAllFailedMigrationResults (@Nullable final String sMigrationID)
  {
    return getAllMigrationResults (sMigrationID).getAll (SystemMigrationResult::isFailure);
  }

  public boolean wasMigrationExecutedSuccessfully (@Nullable final String sMigrationID)
  {
    return getAllMigrationResults (sMigrationID).containsAny (SystemMigrationResult::isSuccess);
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsSet <String> getAllMigrationIDs ()
  {
    return m_aRWLock.readLocked ( () -> m_aMap.copyOfKeySet ());
  }

  /**
   * Perform a migration if it was not performed yet. The performed callback may
   * not throw an error or return an error. All migrations executed with this
   * method will be handled as a success only.
   *
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor
   *        empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  public void performMigrationIfNecessary (@Nonnull @Nonempty final String sMigrationID,
                                           @Nonnull final Runnable aMigrationAction)
  {
    ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    ValueEnforcer.notNull (aMigrationAction, "MigrationAction");

    if (!wasMigrationExecutedSuccessfully (sMigrationID))
    {
      try
      {
        s_aLogger.info ("Performing migration '" + sMigrationID + "'");

        // Invoke the callback
        aMigrationAction.run ();

        s_aLogger.info ("Finished performing migration '" + sMigrationID + "'");

        // Always assume success
        addMigrationResultSuccess (sMigrationID);
      }
      catch (final RuntimeException ex)
      {
        s_aLogger.error ("Error execution system migration '" + sMigrationID + "'", ex);
        addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
      }
    }
  }

  /**
   * Perform a migration if it was not performed yet.
   *
   * @param sMigrationID
   *        The migration ID to handle. May neither be <code>null</code> nor
   *        empty.
   * @param aMigrationAction
   *        The action to be performed. May not be <code>null</code>.
   */
  public void performMigrationIfNecessary (@Nonnull @Nonempty final String sMigrationID,
                                           @Nonnull final Supplier <SuccessWithValue <String>> aMigrationAction)
  {
    ValueEnforcer.notEmpty (sMigrationID, "MigrationID");
    ValueEnforcer.notNull (aMigrationAction, "MigrationAction");

    if (!wasMigrationExecutedSuccessfully (sMigrationID))
    {
      try
      {
        s_aLogger.info ("Performing migration '" + sMigrationID + "'");

        // Invoke the callback
        final SuccessWithValue <String> ret = aMigrationAction.get ();

        s_aLogger.info ("Finished performing migration '" +
                        sMigrationID +
                        "' with status " +
                        (ret.isSuccess () ? "success" : "error"));

        // Success or error
        if (ret.isSuccess ())
          addMigrationResultSuccess (sMigrationID);
        else
          addMigrationResultError (sMigrationID, ret.get ());
      }
      catch (final RuntimeException ex)
      {
        s_aLogger.error ("Error execution system migration '" + sMigrationID + "'", ex);
        addMigrationResultError (sMigrationID, ex.getClass () + ": " + ex.getMessage ());
      }
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap).getToString ();
  }
}
