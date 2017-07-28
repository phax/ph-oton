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
package com.helger.photon.core.api;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.concurrent.SimpleReadWriteLock;
import com.helger.photon.core.api.callback.LoggingAPIExceptionCallback;
import com.helger.photon.core.api.callback.LoggingAPILongRunningExecutionCallback;

/**
 * A central helper class that centrally manages the API callbacks.
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

  private static final SimpleReadWriteLock s_aRWLock = new SimpleReadWriteLock ();
  private static final CallbackList <IAPIExceptionCallback> s_aExceptionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAPIBeforeExecutionCallback> s_aBeforeExecutionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAPIAfterExecutionCallback> s_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("s_aRWLock")
  private static long s_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAPILongRunningExecutionCallback> s_aLongRunningExecutionCallbacks = new CallbackList <> ();

  static
  {
    // Register default handler
    getExceptionCallbacks ().add (new LoggingAPIExceptionCallback ());
    getLongRunningExecutionCallbacks ().add (new LoggingAPILongRunningExecutionCallback ());
  }

  private APISettings ()
  {}

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIExceptionCallback> getExceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return s_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIAfterExecutionCallback> getAfterExecutionCallbacks ()
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
  public static CallbackList <IAPILongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return s_aLongRunningExecutionCallbacks;
  }
}
