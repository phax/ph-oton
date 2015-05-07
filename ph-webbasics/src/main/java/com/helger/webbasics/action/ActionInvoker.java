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
package com.helger.webbasics.action;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.CGlobal;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.annotations.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.stats.IStatisticsHandlerCounter;
import com.helger.commons.stats.IStatisticsHandlerKeyedCounter;
import com.helger.commons.stats.IStatisticsHandlerKeyedTimer;
import com.helger.commons.stats.StatisticsManager;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.timing.StopWatch;
import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;
import com.helger.webbasics.action.callback.LoggingActionExceptionCallback;

/**
 * This class maps action names to callback objects.
 *
 * @author Philip Helger
 */
@ThreadSafe
public class ActionInvoker implements IActionInvoker
{
  /** Default milliseconds until an implementation is considered long running. */
  public static final long DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS = CGlobal.MILLISECONDS_PER_SECOND;

  private static final Logger s_aLogger = LoggerFactory.getLogger (ActionInvoker.class);
  private static final IStatisticsHandlerCounter s_aStatsGlobalInvoke = StatisticsManager.getCounterHandler (ActionInvoker.class.getName () +
                                                                                                             "$invocations");
  private static final IStatisticsHandlerKeyedCounter s_aStatsFunctionInvoke = StatisticsManager.getKeyedCounterHandler (ActionInvoker.class.getName () +
                                                                                                                         "$func");
  private static final IStatisticsHandlerKeyedTimer s_aStatsFunctionTimer = StatisticsManager.getKeyedTimerHandler (ActionInvoker.class.getName () +
                                                                                                                    "$timer");

  private final ReadWriteLock m_aRWLock = new ReentrantReadWriteLock ();
  private final CallbackList <IActionExceptionCallback> m_aExceptionCallbacks = new CallbackList <IActionExceptionCallback> ();
  private final CallbackList <IActionBeforeExecutionCallback> m_aBeforeExecutionCallbacks = new CallbackList <IActionBeforeExecutionCallback> ();
  private final CallbackList <IActionAfterExecutionCallback> m_aAfterExecutionCallbacks = new CallbackList <IActionAfterExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private long m_nLongRunningExecutionLimitTime = DEFAULT_LONG_RUNNING_EXECUTION_LIMIT_MS;
  private final CallbackList <IActionLongRunningExecutionCallback> m_aLongRunningExecutionCallbacks = new CallbackList <IActionLongRunningExecutionCallback> ();
  @GuardedBy ("m_aRWLock")
  private final Map <String, IActionDeclaration> m_aMap = new HashMap <String, IActionDeclaration> ();

  public ActionInvoker ()
  {
    // Register default handler
    getExceptionCallbacks ().addCallback (new LoggingActionExceptionCallback ());
  }

  public static boolean isValidActionName (@Nullable final String sActionName)
  {
    // All characters allowed should be valid in URLs without masking
    return StringHelper.hasText (sActionName) && RegExHelper.stringMatchesPattern ("^[a-zA-Z0-9\\-_]+$", sActionName);
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionExceptionCallback> getExceptionCallbacks ()
  {
    return m_aExceptionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionBeforeExecutionCallback> getBeforeExecutionCallbacks ()
  {
    return m_aBeforeExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionAfterExecutionCallback> getAfterExecutionCallbacks ()
  {
    return m_aAfterExecutionCallbacks;
  }

  @CheckForSigned
  public long getLongRunningExecutionLimitTime ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_nLongRunningExecutionLimitTime;
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void setLongRunningExecutionLimitTime (final long nLongRunningExecutionLimitTime)
  {
    m_aRWLock.writeLock ().lock ();
    try
    {
      m_nLongRunningExecutionLimitTime = nLongRunningExecutionLimitTime;
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }
  }

  @Nonnull
  @ReturnsMutableObject (reason = "design")
  public CallbackList <IActionLongRunningExecutionCallback> getLongRunningExecutionCallbacks ()
  {
    return m_aLongRunningExecutionCallbacks;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Map <String, IActionDeclaration> getAllRegisteredActions ()
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return CollectionHelper.newMap (m_aMap);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IActionDeclaration getRegisteredAction (@Nullable final String sActionName)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.get (sActionName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  @Nullable
  public IActionExecutor createExecutor (@Nullable final String sActionName)
  {
    final IActionDeclaration aAction = getRegisteredAction (sActionName);
    return aAction == null ? null : aAction.getExecutorFactory ().create ();
  }

  public boolean isRegisteredAction (@Nullable final String sActionName)
  {
    m_aRWLock.readLock ().lock ();
    try
    {
      return m_aMap.containsKey (sActionName);
    }
    finally
    {
      m_aRWLock.readLock ().unlock ();
    }
  }

  public void registerAction (@Nonnull final IActionDeclaration aActionDeclaration)
  {
    ValueEnforcer.notNull (aActionDeclaration, "ActionDeclaration");

    final String sActionName = aActionDeclaration.getName ();

    m_aRWLock.writeLock ().lock ();
    try
    {
      if (m_aMap.containsKey (sActionName))
        throw new IllegalArgumentException ("Action '" + sActionName + "' is already contained!");

      m_aMap.put (sActionName, aActionDeclaration);
    }
    finally
    {
      m_aRWLock.writeLock ().unlock ();
    }

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Registered Action '" +
                       sActionName +
                       "' with executor factory " +
                       aActionDeclaration.getExecutorFactory ());
  }

  public void invokeAction (@Nonnull final String sActionName,
                            @Nonnull final IActionExecutor aActionExecutor,
                            @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                            @Nonnull final UnifiedResponse aUnifiedResponse) throws Throwable
  {
    ValueEnforcer.notNull (sActionName, "ActionName");
    ValueEnforcer.notNull (aActionExecutor, "ActionExecutor");
    ValueEnforcer.notNull (aRequestScope, "RequestScope");
    ValueEnforcer.notNull (aUnifiedResponse, "UnifiedResponse");

    if (s_aLogger.isDebugEnabled ())
      s_aLogger.debug ("Invoking Action '" + sActionName + "'");

    try
    {
      final StopWatch aSW = new StopWatch (true);

      // Global increment before invocation
      s_aStatsGlobalInvoke.increment ();

      // Invoke before handler
      for (final IActionBeforeExecutionCallback aBeforeCallback : getBeforeExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aBeforeCallback.onBeforeExecution (this, sActionName, aRequestScope, aActionExecutor);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking Action before execution callback handler " + aBeforeCallback, t);
        }

      // Main execution
      aActionExecutor.execute (aRequestScope, aUnifiedResponse);

      // Invoke after handler
      for (final IActionAfterExecutionCallback aAfterCallback : getAfterExecutionCallbacks ().getAllCallbacks ())
        try
        {
          aAfterCallback.onAfterExecution (this, sActionName, aRequestScope, aActionExecutor);
        }
        catch (final Throwable t)
        {
          s_aLogger.error ("Error invoking Action after execution callback handler " + aAfterCallback, t);
        }

      // Increment statistics after successful call
      s_aStatsFunctionInvoke.increment (sActionName);

      // Long running Action request?
      final long nExecutionMillis = aSW.stopAndGetMillis ();
      s_aStatsFunctionTimer.addTime (sActionName, nExecutionMillis);
      final long nLimitMS = getLongRunningExecutionLimitTime ();
      if (nLimitMS > 0 && nExecutionMillis > nLimitMS)
      {
        // Long running execution
        for (final IActionLongRunningExecutionCallback aLongRunningExecutionCallback : getLongRunningExecutionCallbacks ().getAllCallbacks ())
          try
          {
            aLongRunningExecutionCallback.onLongRunningExecution (this,
                                                                  sActionName,
                                                                  aRequestScope,
                                                                  aActionExecutor,
                                                                  nExecutionMillis);
          }
          catch (final Throwable t)
          {
            s_aLogger.error ("Error invoking Action long running execution callback handler " +
                             aLongRunningExecutionCallback, t);
          }
      }
    }
    catch (final Throwable t)
    {
      for (final IActionExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
        try
        {
          aExceptionCallback.onActionExecutionException (this, sActionName, aActionExecutor, aRequestScope, t);
        }
        catch (final Throwable t2)
        {
          s_aLogger.error ("Error invoking Action exception callback handler " + aExceptionCallback, t2);
        }

      // Re-throw
      throw t;
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("map", m_aMap)
                                       .append ("exceptionCallbacks", m_aExceptionCallbacks)
                                       .append ("beforeExecutionCallbacks", m_aBeforeExecutionCallbacks)
                                       .append ("afterExecutionCallbacks", m_aAfterExecutionCallbacks)
                                       .append ("longRunningExecutionLimitTime", m_nLongRunningExecutionLimitTime)
                                       .append ("longRunningExecutionCallbacks", m_aLongRunningExecutionCallbacks)
                                       .toString ();
  }
}
