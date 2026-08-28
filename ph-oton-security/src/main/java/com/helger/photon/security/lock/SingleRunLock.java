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
package com.helger.photon.security.lock;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.name.IHasName;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.datetime.helper.PDTFactory;

/**
 * A process wide lock that ensures, that a certain expensive activity only runs once at a time. It
 * additionally remembers since when and by whom the running activity was started, so that a
 * rejected caller can be told what is going on.<br>
 * This lock is deliberately not reentrant and it is not bound to the acquiring thread - a
 * background job may release what an HTTP request thread acquired.<br>
 * Note: this lock only works within a single JVM. It does not synchronize multiple instances
 * running against the same database.
 *
 * @author Philip Helger
 * @since 10.4.0
 */
@ThreadSafe
public class SingleRunLock implements IHasName
{
  private final String m_sName;
  private final AtomicBoolean m_aRunning = new AtomicBoolean (false);
  private final AtomicReference <LocalDateTime> m_aStartDT = new AtomicReference <> ();
  private final AtomicReference <String> m_aUserID = new AtomicReference <> ();

  /**
   * Constructor
   *
   * @param sName
   *        The name of the activity guarded by this lock. Used for debugging only. May neither be
   *        <code>null</code> nor empty.
   */
  public SingleRunLock (@NonNull @Nonempty final String sName)
  {
    m_sName = ValueEnforcer.notEmpty (sName, "Name");
  }

  /**
   * @return The name of the activity guarded by this lock, as provided in the constructor. Neither
   *         <code>null</code> nor empty.
   */
  @NonNull
  @Nonempty
  public final String getName ()
  {
    return m_sName;
  }

  /**
   * Try to acquire the lock. If this method returns <code>true</code>, {@link #release()} must be
   * called afterwards - preferably in a <code>finally</code> block.
   *
   * @param sUserID
   *        The ID of the user starting the activity. May be <code>null</code>.
   * @return <code>true</code> if the lock was acquired, <code>false</code> if the activity is
   *         already running.
   */
  public boolean tryAcquire (@Nullable final String sUserID)
  {
    if (!m_aRunning.compareAndSet (false, true))
      return false;

    m_aStartDT.set (PDTFactory.getCurrentLocalDateTime ());
    m_aUserID.set (sUserID);
    return true;
  }

  /**
   * Release the lock previously acquired via {@link #tryAcquire(String)}.
   */
  public void release ()
  {
    m_aUserID.set (null);
    m_aStartDT.set (null);
    m_aRunning.set (false);
  }

  /**
   * @return <code>true</code> if the guarded activity is currently running, <code>false</code> if
   *         not.
   */
  public boolean isRunning ()
  {
    return m_aRunning.get ();
  }

  /**
   * @return The date and time at which the currently running activity was started. May be
   *         <code>null</code> if nothing is running.
   */
  @Nullable
  public LocalDateTime getStartDateTime ()
  {
    return m_aStartDT.get ();
  }

  /**
   * @return The ID of the user that started the currently running activity. May be
   *         <code>null</code>.
   */
  @Nullable
  public String getUserID ()
  {
    return m_aUserID.get ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Name", m_sName)
                                       .append ("Running", m_aRunning.get ())
                                       .append ("StartDT", m_aStartDT.get ())
                                       .append ("UserID", m_aUserID.get ())
                                       .getToString ();
  }
}
