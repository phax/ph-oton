/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
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

  protected final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final int m_nMaxWarnings;
  private boolean m_bSuccess = DEFAULT_SUCCESS;
  private final Map <String, ITypedObject <String>> m_aAdded = new LinkedHashMap <String, ITypedObject <String>> ();
  private final Map <String, ITypedObject <String>> m_aChanged = new LinkedHashMap <String, ITypedObject <String>> ();
  private final List <String> m_aFailed = new ArrayList <String> ();
  private final List <String> m_aWarnings = new ArrayList <String> ();
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
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aAdded.put (aObj.getID (), aObj);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return all added objects
   * @deprecated Use {@link #getAllAdded()} instead
   */
  @Deprecated
  @Nonnull
  @ReturnsMutableCopy
  public final List <ITypedObject <String>> getAdded ()
  {
    return getAllAdded ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <ITypedObject <String>> getAllAdded ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aAdded.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public final int getAddedCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aAdded.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final boolean containsAdded (@Nonnull final ITypedObject <String> aObj)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      // linear scanning :(
      return m_aAdded.containsKey (aObj.getID ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final void registerChanged (@Nonnull final ITypedObject <String> aObj)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aChanged.put (aObj.getID (), aObj);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return all changed objects
   * @deprecated Use {@link #getAllChanged()} instead
   */
  @Deprecated
  @Nonnull
  @ReturnsMutableCopy
  public final List <ITypedObject <String>> getChanged ()
  {
    return getAllChanged ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <ITypedObject <String>> getAllChanged ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aChanged.values ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public final int getChangedCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aChanged.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final boolean containsChanged (@Nonnull final ITypedObject <String> aObj)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      // linear scanning :(
      return m_aChanged.containsKey (aObj.getID ());
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final void registerFailed (final String sID)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_aFailed.add (sID);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return All failed IDs.
   */
  @Nonnull
  @ReturnsMutableCopy
  public final List <String> getAllFailed ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aFailed);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nonnegative
  public final int getFailedCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aFailed.size ();
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final boolean containsFailed (@Nullable final String sID)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      // linear scanning :(
      return m_aFailed.contains (sID);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final void addWarning (final String sWarningMsg)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aWarnings.size () < m_nMaxWarnings)
      {
        if (false)
          s_aLogger.warn (sWarningMsg);
        m_aWarnings.add (sWarningMsg);
      }
      else
        m_nAdditionalWarnings++;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  /**
   * @return All warnings
   * @deprecated Use {@link #getAllWarnings()} instead
   */
  @Deprecated
  @Nonnull
  @ReturnsMutableCopy
  public final List <String> getWarnings ()
  {
    return getAllWarnings ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public final List <String> getAllWarnings ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newList (m_aWarnings);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * @return The number of all warnings. Always &ge; 0.
   */
  @Nonnegative
  public final int getWarningsCount ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aWarnings.size () + m_nAdditionalWarnings;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final void setSuccess (final boolean bSuccess)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_bSuccess = bSuccess;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
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
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bSuccess;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  /**
   * Indicates, that the overall import failed.
   *
   * @return Inverse of {@link #isSuccess()}
   */
  @Override
  public final boolean isFailure ()
  {
    return !isSuccess ();
  }
}
