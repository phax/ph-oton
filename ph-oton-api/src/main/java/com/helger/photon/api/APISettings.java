/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
package com.helger.photon.api;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.CheckForSigned;
import com.helger.annotation.concurrent.GuardedBy;
import com.helger.annotation.concurrent.ThreadSafe;
import com.helger.annotation.style.ReturnsMutableObject;
import com.helger.base.CGlobal;
import com.helger.base.callback.CallbackList;
import com.helger.base.concurrent.SimpleReadWriteLock;
import com.helger.photon.api.callback.LoggingAPIExceptionCallback;
import com.helger.photon.api.callback.LoggingAPILongRunningExecutionCallback;

/**
 * A central helper class that centrally configures all API callbacks.
 *
 * @author Philip Helger
 */
@ThreadSafe
public final class APISettings
{
  /**
   * Default milliseconds until an implementation is considered long running.
   */
  public static final long DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS = CGlobal.MILLISECONDS_PER_SECOND;

  private static final SimpleReadWriteLock RW_LOCK = new SimpleReadWriteLock ();
  private static final CallbackList <IAPIExceptionCallback> EXCEPTION_CALLBACKS = new CallbackList <> ();
  private static final CallbackList <IAPIBeforeExecutionCallback> BEFORE_EXECUTION_CALLBACKS = new CallbackList <> ();
  private static final CallbackList <IAPIAfterExecutionCallback> AFTER_EXECUTION_CALLBACKS = new CallbackList <> ();
  @GuardedBy ("RW_LOCK")
  private static long s_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAPILongRunningExecutionCallback> LONG_RUNNING_EXECUTION_CALLBACKS = new CallbackList <> ();

  static
  {
    // Register default handler
    exceptionCallbacks ().add (new LoggingAPIExceptionCallback ());
    longRunningExecutionCallbacks ().add (new LoggingAPILongRunningExecutionCallback ());
  }

  private APISettings ()
  {}

  @NonNull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIExceptionCallback> exceptionCallbacks ()
  {
    return EXCEPTION_CALLBACKS;
  }

  @NonNull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIBeforeExecutionCallback> beforeExecutionCallbacks ()
  {
    return BEFORE_EXECUTION_CALLBACKS;
  }

  @NonNull
  @ReturnsMutableObject
  public static CallbackList <IAPIAfterExecutionCallback> afterExecutionCallbacks ()
  {
    return AFTER_EXECUTION_CALLBACKS;
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
  public static CallbackList <IAPILongRunningExecutionCallback> longRunningExecutionCallbacks ()
  {
    return LONG_RUNNING_EXECUTION_CALLBACKS;
  }
}
