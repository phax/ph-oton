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
package com.helger.photon.basic.app.dao.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.callback.INonThrowingCallable;
import com.helger.commons.callback.INonThrowingRunnable;
import com.helger.commons.callback.IThrowingCallable;
import com.helger.commons.callback.IThrowingRunnable;
import com.helger.commons.callback.adapter.AdapterRunnableToCallable;
import com.helger.commons.callback.adapter.AdapterThrowingRunnableToCallable;
import com.helger.commons.collection.impl.NonBlockingStack;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.string.ToStringGenerator;
import com.helger.photon.basic.app.dao.IDAO;
import com.helger.photon.basic.app.dao.IDAOReadExceptionCallback;
import com.helger.photon.basic.app.dao.IDAOWriteExceptionCallback;

/**
 * Base implementation of {@link IDAO}
 *
 * @author Philip Helger
 */
@ThreadSafe
public abstract class AbstractDAO implements IDAO
{
  /** By default auto-save is enabled */
  public static final boolean DEFAULT_AUTO_SAVE_ENABLED = true;

  private static CallbackList <IDAOReadExceptionCallback> s_aExceptionHandlersRead = new CallbackList <IDAOReadExceptionCallback> ();
  private static CallbackList <IDAOWriteExceptionCallback> s_aExceptionHandlersWrite = new CallbackList <IDAOWriteExceptionCallback> ();

  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("m_aRWLock")
  private final NonBlockingStack <Boolean> m_aAutoSaveStack = new NonBlockingStack <Boolean> ();
  @GuardedBy ("m_aRWLock")
  private boolean m_bPendingChanges = false;
  @GuardedBy ("m_aRWLock")
  private boolean m_bAutoSaveEnabled = DEFAULT_AUTO_SAVE_ENABLED;

  protected AbstractDAO ()
  {}

  @Nonnull
  @ReturnsMutableObject ("design")
  public static final CallbackList <IDAOReadExceptionCallback> getExceptionHandlersRead ()
  {
    return s_aExceptionHandlersRead;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static final CallbackList <IDAOWriteExceptionCallback> getExceptionHandlersWrite ()
  {
    return s_aExceptionHandlersWrite;
  }

  /**
   * @return <code>true</code> if auto save is enabled, <code>false</code>
   *         otherwise.
   */
  @MustBeLocked (ELockType.READ)
  protected final boolean internalIsAutoSaveEnabled ()
  {
    return m_bAutoSaveEnabled;
  }

  /**
   * @return <code>true</code> if auto save is enabled, <code>false</code>
   *         otherwise.
   */
  public final boolean isAutoSaveEnabled ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bAutoSaveEnabled;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @MustBeLocked (ELockType.WRITE)
  public final void internalSetPendingChanges (final boolean bPendingChanges)
  {
    m_bPendingChanges = bPendingChanges;
  }

  /**
   * @return <code>true</code> if unsaved changes are present
   */
  @MustBeLocked (ELockType.READ)
  public final boolean internalHasPendingChanges ()
  {
    return m_bPendingChanges;
  }

  /**
   * @return <code>true</code> if unsaved changes are present
   */
  public final boolean hasPendingChanges ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_bPendingChanges;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public final void beginWithoutAutoSave ()
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      // Save old auto save state
      m_aAutoSaveStack.push (Boolean.valueOf (m_bAutoSaveEnabled));
      m_bAutoSaveEnabled = false;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  public final void endWithoutAutoSave ()
  {
    // Restore previous auto save state
    boolean bPreviouslyAutoSaveEnabled;
    m_aRWLock.writeLock ().lock ();
    try
    {
      bPreviouslyAutoSaveEnabled = m_aAutoSaveStack.pop ().booleanValue ();
      m_bAutoSaveEnabled = bPreviouslyAutoSaveEnabled;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (bPreviouslyAutoSaveEnabled)
    {
      // And in case something was changed - writeLocked itself
      writeToFileOnPendingChanges ();
    }
  }

  /**
   * Execute a callback with autosave being disabled. Must be called outside a
   * writeLock, as this method locks itself!
   *
   * @param aRunnable
   *        The callback to be executed
   */
  public final void performWithoutAutoSave (@Nonnull final INonThrowingRunnable aRunnable)
  {
    performWithoutAutoSave (AdapterRunnableToCallable.createAdapter (aRunnable));
  }

  /**
   * Execute a callback with autosave being disabled. Must be called outside a
   * writeLock, as this method locks itself!
   *
   * @param aCallable
   *        The callback to be executed
   * @return The result of the callback. May be <code>null</code>.
   */
  @Nullable
  public final <RETURNTYPE> RETURNTYPE performWithoutAutoSave (@Nonnull final INonThrowingCallable <RETURNTYPE> aCallable)
  {
    ValueEnforcer.notNull (aCallable, "Callable");

    beginWithoutAutoSave ();
    try
    {
      // Main call of callable
      return aCallable.call ();
    }
    finally
    {
      endWithoutAutoSave ();
    }
  }

  /**
   * Execute a callback with autosave being disabled. Must be called outside a
   * writeLock, as this method locks itself!
   *
   * @param aRunnable
   *        The callback to be executed
   * @throws Exception
   *         In case of an error
   */
  public final <EXTYPE extends Exception> void performWithoutAutoSave (@Nonnull final IThrowingRunnable <EXTYPE> aRunnable) throws Exception
  {
    performWithoutAutoSave (AdapterThrowingRunnableToCallable.createAdapter (aRunnable));
  }

  /**
   * Execute a callback with autosave being disabled. Must be called outside a
   * writeLock, as this method locks itself!
   *
   * @param aCallable
   *        The callback to be executed
   * @return The result of the callback. May be <code>null</code>.
   * @throws Exception
   *         In case of an error
   */
  @Nullable
  public final <RETURNTYPE, EXTYPE extends Exception> RETURNTYPE performWithoutAutoSave (@Nonnull final IThrowingCallable <RETURNTYPE, EXTYPE> aCallable) throws EXTYPE
  {
    ValueEnforcer.notNull (aCallable, "Callable");

    beginWithoutAutoSave ();
    try
    {
      // Main call of callable
      return aCallable.call ();
    }
    finally
    {
      endWithoutAutoSave ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("autoSaveStack", m_aAutoSaveStack)
                                       .append ("pendingChanges", m_bPendingChanges)
                                       .append ("autoSaveEnabled", m_bAutoSaveEnabled)
                                       .toString ();
  }
}
