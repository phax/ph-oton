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
package com.helger.photon.ajax;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.CGlobal;
import com.helger.base.callback.CallbackList;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.photon.ajax.callback.IAjaxAfterExecutionCallback;
import com.helger.photon.ajax.callback.IAjaxBeforeExecutionCallback;
import com.helger.photon.ajax.callback.IAjaxExceptionCallback;
import com.helger.photon.ajax.callback.IAjaxLongRunningExecutionCallback;
import com.helger.photon.ajax.callback.LoggingAjaxExceptionCallback;
import com.helger.photon.ajax.callback.LoggingAjaxLongRunningExecutionCallback;

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

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  private static final CallbackList <IAjaxExceptionCallback> s_aExceptionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxBeforeExecutionCallback> s_aBeforeExecutionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxAfterExecutionCallback> s_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("RW_LOCK")
  private static long s_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAjaxLongRunningExecutionCallback> s_aLongRunningExecutionCallbacks = new CallbackList <> ();

  static
  {
    // Register default handler
    exceptionCallbacks ().add (new LoggingAjaxExceptionCallback ());
    longRunningExecutionCallbacks ().add (new LoggingAjaxLongRunningExecutionCallback ());
  }

  private AjaxSettings ()
  {}

  @NonNull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxExceptionCallback> exceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  @NonNull
  @ReturnsMutableObject
  public static CallbackList <IAjaxBeforeExecutionCallback> beforeExecutionCallbacks ()
  {
    return s_aBeforeExecutionCallbacks;
  }

  @NonNull
  @ReturnsMutableObject
  public static CallbackList <IAjaxAfterExecutionCallback> afterExecutionCallbacks ()
  {
    return s_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public static long getLongRunningExecutionLimitTime ()
  {
    return RW_LOCK.readLockedLong ( () -> s_nLongRunningExecutionLimitTime);
  }

  /**
   * Set the milliseconds after which an execution is considered long running.
   *
   * @param nLongRunningExecutionLimitTime
   *        The milliseconds to use. Value &le; 0 are considered "no limit"
   */
  public static void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    RW_LOCK.writeLocked ( () -> s_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime);
  }

  @NonNull
  @ReturnsMutableObject
  public static CallbackList <IAjaxLongRunningExecutionCallback> longRunningExecutionCallbacks ()
  {
    return s_aLongRunningExecutionCallbacks;
  }
}
