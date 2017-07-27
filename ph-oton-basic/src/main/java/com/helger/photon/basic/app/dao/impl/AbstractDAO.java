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
package com.helger.photon.basic.app.dao.impl;

import java.util.function.Supplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ELockType;
import com.helger.commons.annotation.MustBeLocked;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.callback.IThrowingRunnable;
import com.helger.commons.collection.NonBlockingStack;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.functional.IThrowingSupplier;
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

  private static CallbackList <IDAOReadExceptionCallback> s_aExceptionHandlersRead = new CallbackList<> ();
  private static CallbackList <IDAOWriteExceptionCallback> s_aExceptionHandlersWrite = new CallbackList<> ();

  protected final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();

  @GuardedBy ("m_aRWLock")
  private final NonBlockingStack <Boolean> m_aAutoSaveStack = new NonBlockingStack<> ();
  @GuardedBy ("m_aRWLock")
  private boolean m_bPendingChanges = false;
  @GuardedBy ("m_aRWLock")
  private boolean m_bAutoSaveEnabled = DEFAULT_AUTO_SAVE_ENABLED;

  protected AbstractDAO ()
  {}

  protected static final boolean isDebugLogging ()
  {
    return GlobalDebug.isDebugMode ();
  }

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
    return m_aRWLock.readLocked ( () -> m_bAutoSaveEnabled);
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
    return m_aRWLock.readLocked ( () -> m_bPendingChanges);
  }

  public final void beginWithoutAutoSave ()
  {
    m_aRWLock.writeLocked ( () -> {
      // Save old auto save state
      m_aAutoSaveStack.push (Boolean.valueOf (m_bAutoSaveEnabled));
      m_bAutoSaveEnabled = false;
    });
  }

  public final void endWithoutAutoSave ()
  {
    // Restore previous auto save state
    final boolean bPreviouslyAutoSaveEnabled = m_aRWLock.writeLocked ( () -> {
      final boolean bPreviously = m_aAutoSaveStack.pop ().booleanValue ();
      m_bAutoSaveEnabled = bPreviously;
      return bPreviously;
    });

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
  public final void performWithoutAutoSave (@Nonnull final Runnable aRunnable)
  {
    ValueEnforcer.notNull (aRunnable, "Runnable");

    beginWithoutAutoSave ();
    try
    {
      aRunnable.run ();
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
   * @param aCallable
   *        The callback to be executed
   * @return The result of the callback. May be <code>null</code>.
   */
  @Nullable
  public final <RETURNTYPE> RETURNTYPE performWithoutAutoSave (@Nonnull final Supplier <RETURNTYPE> aCallable)
  {
    ValueEnforcer.notNull (aCallable, "Callable");

    beginWithoutAutoSave ();
    try
    {
      return aCallable.get ();
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
   * @throws EXTYPE
   *         In case of an error
   * @param <EXTYPE>
   *        Exception type that may be thrown
   */
  public final <EXTYPE extends Exception> void performWithoutAutoSaveThrowing (@Nonnull final IThrowingRunnable <EXTYPE> aRunnable) throws EXTYPE
  {
    ValueEnforcer.notNull (aRunnable, "Runnable");

    beginWithoutAutoSave ();
    try
    {
      aRunnable.run ();
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
   * @param aCallable
   *        The callback to be executed
   * @return The result of the callback. May be <code>null</code>.
   * @throws EXTYPE
   *         In case of an error
   * @param <RETURNTYPE>
   *        Return type of the callable
   * @param <EXTYPE>
   *        Exception type that may be thrown
   */
  @Nullable
  public final <RETURNTYPE, EXTYPE extends Exception> RETURNTYPE performWithoutAutoSaveThrowing (@Nonnull final IThrowingSupplier <RETURNTYPE, EXTYPE> aCallable) throws EXTYPE
  {
    ValueEnforcer.notNull (aCallable, "Callable");

    beginWithoutAutoSave ();
    try
    {
      // Main call of callable
      return aCallable.get ();
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
                                       .getToString ();
  }
}
