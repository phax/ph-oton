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
package com.helger.photon.exchange.bulkimport;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsLinkedHashMap;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.collection.impl.ICommonsOrderedMap;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.state.ISuccessIndicator;
import com.helger.commons.type.ITypedObject;

/**
 * This class represents the results of a bulk import.
 *
 * @author boris, philip
 */
@ThreadSafe
public class BulkImportResult implements ISuccessIndicator
{
  /**
   * Default success value
   */
  public static final boolean DEFAULT_SUCCESS = true;
  /**
   * Default value for maximum number of warnings to maintain in a list.
   */
  public static final int DEFAULT_MAX_WARNINGS = 1000;
  private static final Logger s_aLogger = LoggerFactory.getLogger (BulkImportResult.class);

  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private final int m_nMaxWarnings;
  private boolean m_bSuccess = DEFAULT_SUCCESS;
  private final ICommonsOrderedMap <String, ITypedObject <String>> m_aAdded = new CommonsLinkedHashMap <> ();
  private final ICommonsOrderedMap <String, ITypedObject <String>> m_aChanged = new CommonsLinkedHashMap <> ();
  private final ICommonsList <String> m_aFailed = new CommonsArrayList <> ();
  private final ICommonsList <String> m_aWarnings = new CommonsArrayList <> ();
  private int m_nAdditionalWarnings = 0;

  public BulkImportResult ()
  {
    this (DEFAULT_MAX_WARNINGS);
  }

  public BulkImportResult (@Nonnegative final int nMaxWarnings)
  {
    m_nMaxWarnings = nMaxWarnings;
  }

  public final void registerAdded (@Nonnull final ITypedObject <String> aObj)
  {
    m_aRWLock.writeLocked ( () -> m_aAdded.put (aObj.getID (), aObj));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <ITypedObject <String>> getAllAdded ()
  {
    return m_aRWLock.readLocked ( () -> m_aAdded.copyOfValues ());
  }

  @Nonnegative
  public final int getAddedCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aAdded.size ());
  }

  public final boolean containsAdded (@Nonnull final ITypedObject <String> aObj)
  {
    // linear scanning :(
    return m_aRWLock.readLocked ( () -> m_aAdded.containsKey (aObj.getID ()));
  }

  public final void registerChanged (@Nonnull final ITypedObject <String> aObj)
  {
    m_aRWLock.writeLocked ( () -> m_aChanged.put (aObj.getID (), aObj));
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <ITypedObject <String>> getAllChanged ()
  {
    return m_aRWLock.readLocked ( () -> m_aChanged.copyOfValues ());
  }

  @Nonnegative
  public final int getChangedCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aChanged.size ());
  }

  public final boolean containsChanged (@Nonnull final ITypedObject <String> aObj)
  {
    // linear scanning :(
    return m_aRWLock.readLocked ( () -> m_aChanged.containsKey (aObj.getID ()));
  }

  public final void registerFailed (final String sID)
  {
    m_aRWLock.writeLocked ( () -> m_aFailed.add (sID));
  }

  /**
   * @return All failed IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <String> getAllFailed ()
  {
    return m_aRWLock.readLocked ( () -> m_aFailed.getClone ());
  }

  @Nonnegative
  public final int getFailedCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aFailed.size ());
  }

  public final boolean containsFailed (@Nullable final String sID)
  {
    // linear scanning :(
    return m_aRWLock.readLocked ( () -> m_aFailed.contains (sID));
  }

  public final void addWarning (final String sWarningMsg)
  {
    m_aRWLock.writeLocked ( () -> {
      if (m_aWarnings.size () < m_nMaxWarnings)
      {
        if (false)
          s_aLogger.warn (sWarningMsg);
        m_aWarnings.add (sWarningMsg);
      }
      else
        m_nAdditionalWarnings++;
    });
  }

  @Nonnull
  @ReturnsMutableCopy
  public final ICommonsList <String> getAllWarnings ()
  {
    return m_aRWLock.readLocked ( () -> m_aWarnings.getClone ());
  }

  /**
   * @return The number of all warnings. Always &ge; 0.
   */
  @Nonnegative
  public final int getWarningsCount ()
  {
    return m_aRWLock.readLocked ( () -> m_aWarnings.size () + m_nAdditionalWarnings);
  }

  public final void setSuccess (final boolean bSuccess)
  {
    m_aRWLock.writeLocked ( () -> m_bSuccess = bSuccess);
  }

  /**
   * Indicates, that the overall import succeeded. Default is <code>true</code>.
   *
   * @return <code>true</code> for import success, <code>false</code> for import
   *         failure.
   */
  @Override
  public final boolean isSuccess ()
  {
    return m_aRWLock.readLocked ( () -> m_bSuccess);
  }
}
