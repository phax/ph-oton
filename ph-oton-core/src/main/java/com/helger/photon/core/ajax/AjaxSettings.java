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

  private static final SimpleReadWriteLock m_aRWLock = new SimpleReadWriteLock ();
  private static final CallbackList <IAjaxExceptionCallback> m_aExceptionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <> ();
  private static final CallbackList <IAjaxAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <> ();
  @GuardedBy ("m_aRWLock")
  private static long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private static final CallbackList <IAjaxLongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <> ();

  static
  {
    // Register default handler
    getExceptionCallbacks ().addCallback (new LoggingAjaxExceptionCallback ());
  }

  private AjaxSettings ()
  {}

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxExceptionCallback> getExceptionCallbacks ()
  {
    return m_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return m_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public static long getLongRunningExecutionLimitTime ()
  {
    return m_aRWLock.readLocked ( () -> m_nLongRunningExecutionLimitTime);
  }

  public static void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aRWLock.writeLocked ( () -> m_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime);
  }

  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAjaxLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }
}
