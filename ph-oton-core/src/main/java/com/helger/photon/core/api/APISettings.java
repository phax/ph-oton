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

  private static final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private static final CallbackList <IAPIExceptionCallback> m_aExceptionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAPIBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAPIAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("m_aRWLock")
  private static long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAPILongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <> ();

  static
  {
    // Register default handler
    getExceptionCallbacks ().addCallback (new LoggingAPIExceptionCallback ());
    getLongRunningExecutionCallbacks ().addCallback (new LoggingAPILongRunningExecutionCallback ());
  }

  private APISettings ()
  {}

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIExceptionCallback> getExceptionCallbacks ()
  {
    return m_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return m_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public static long getLongRunningExecutionLimitTime ()
  {
    return m_aRWLock.readLocked ( () -> m_nLongRunningExecutionLimitTime);
  }

  /**
   * Set the milliseconds after which an execution is considered long running.
   *
   * @param nLongRunningExecutionLimitTime
   *        The milliseconds to use. Value &le; 0 are considered "no limit"
   */
  public static void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aRWLock.writeLocked ( () -> m_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPILongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }
}
