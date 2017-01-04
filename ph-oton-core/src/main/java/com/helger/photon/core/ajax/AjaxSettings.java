/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.core.ajax;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.photon.core.ajax.callback.LoggingAjaxExceptionCallback;
import com.helger.photon.core.ajax.callback.LoggingAjaxLongRunningExecutionCallback;

/**
 * A central helper class that centrally manages the AJAX callbacks.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class AjaxSettings
{
  /**
   * Default milliseconds until an implementation is considered long running.
   */
  public static final long DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS = CGlobal.MILLISECONDS_PER_SECOND;

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  private static final CallbackList <IAjaxExceptionCallback> s_aExceptionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxBeforeExecutionCallback> s_aBeforeExecutionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxAfterExecutionCallback> s_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("s_aRWLock")
  private static long s_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAjaxLongRunningExecutionCallback> s_aLongRunningExecutionCallbacks = new CallbackList <> ();

  static
  {
    // Register default handler
    getExceptionCallbacks ().addCallback (new LoggingAjaxExceptionCallback ());
    getLongRunningExecutionCallbacks ().addCallback (new LoggingAjaxLongRunningExecutionCallback ());
  }

  private AjaxSettings ()
  {}

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxExceptionCallback> getExceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return s_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return s_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public static long getLongRunningExecutionLimitTime ()
  {
    return s_aRWLock.readLocked ( () -> s_nLongRunningExecutionLimitTime);
  }

  /**
   * Set the milliseconds after which an execution is considered long running.
   *
   * @param nLongRunningExecutionLimitTime
   *        The milliseconds to use. Value &le; 0 are considered "no limit"
   */
  public static void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    s_aRWLock.writeLocked ( () -> s_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return s_aLongRunningExecutionCallbacks;
  }
}
