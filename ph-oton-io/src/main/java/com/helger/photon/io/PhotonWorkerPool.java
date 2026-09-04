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
package com.helger.photon.io;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonnegative;
import com.helger.annotation.style.UsedViaReflection;
import com.helger.base.concurrent.BasicThreadFactoryBuilder;
import com.helger.base.concurrent.ExecutorServiceHelper;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.functional.IThrowingSupplier;
import com.helger.base.iface.IThrowingRunnable;
import com.helger.base.log.ConditionalLogger;
import com.helger.base.timing.StopWatch;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.Telemetry;

/**
 * Asynchronous worker pool that handles stuff that runs in the background.
 *
 * @author Philip Helger
 * @since 8.2.6
 */
public class PhotonWorkerPool extends AbstractGlobalSingleton
{
  private static final Logger LOGGER = LoggerFactory.getLogger (PhotonWorkerPool.class);
  private static final ConditionalLogger CONDLOG = new ConditionalLogger (LOGGER, !GlobalDebug.DEFAULT_SILENT_MODE);

  private final ExecutorService m_aES;

  @Deprecated (forRemoval = false)
  @UsedViaReflection
  public PhotonWorkerPool ()
  {
    this (Runtime.getRuntime ().availableProcessors () * 2);
  }

  public PhotonWorkerPool (@Nonnegative final int nThreadPoolSize)
  {
    this (Executors.newFixedThreadPool (nThreadPoolSize,
                                        new BasicThreadFactoryBuilder ().daemon (true)
                                                                        .namingPattern ("ph-oton-worker-%d")
                                                                        .build ()));
  }

  public PhotonWorkerPool (@NonNull final ExecutorService aES)
  {
    ValueEnforcer.notNull (aES, "ExecutorService");
    m_aES = aES;
  }

  @NonNull
  public static PhotonWorkerPool getInstance ()
  {
    return getGlobalSingleton (PhotonWorkerPool.class);
  }

  /**
   * @return <code>true</code> if logging is disabled, <code>false</code> if it is enabled.
   * @since 9.3.0
   */
  public static boolean isSilentMode ()
  {
    return CONDLOG.isDisabled ();
  }

  /**
   * Enable or disable certain regular log messages.
   *
   * @param bSilentMode
   *        <code>true</code> to disable logging, <code>false</code> to enable logging
   * @return The previous value of the silent mode.
   * @since 9.3.0
   */
  public static boolean setSilentMode (final boolean bSilentMode)
  {
    return !CONDLOG.setEnabled (!bSilentMode);
  }

  @Override
  protected void onDestroy (@NonNull final IScope aScopeInDestruction) throws Exception
  {
    CONDLOG.debug ( () -> "ph-oton worker pool about to be closed");
    ExecutorServiceHelper.shutdownAndWaitUntilAllTasksAreFinished (m_aES);
    CONDLOG.info ("ph-oton worker pool was closed!");
  }

  /**
   * Execute the provided task body with the common logging and telemetry around it. This runs
   * completely on the worker pool thread, so the span is started and ended on the same thread.
   *
   * @param sActionName
   *        The caller supplied action name. May not be <code>null</code>.
   * @param sTaskType
   *        The task type for the log message - <code>runner</code> or <code>supplier</code>. May
   *        not be <code>null</code>.
   * @param aTask
   *        The task object for the log message. May not be <code>null</code>.
   * @param aEndEmitted
   *        Guard, so that the end-of-task metrics are emitted exactly once per task - either here
   *        or in the {@code exceptionally} handler of the caller. May not be <code>null</code>.
   * @param aBody
   *        The body to execute. May not be <code>null</code>.
   * @param <T>
   *        The body return type.
   * @return The value returned by the body, or <code>null</code> if it threw.
   */
  @Nullable
  private static <T> T _executeInstrumented (@NonNull final String sActionName,
                                             @NonNull final String sTaskType,
                                             @NonNull final Object aTask,
                                             @NonNull final AtomicBoolean aEndEmitted,
                                             @NonNull final IThrowingSupplier <T, Exception> aBody)
  {
    return Telemetry.withSpan (CIOTelemetry.SPAN_WORKER_EXECUTE, ETelemetrySpanKind.INTERNAL, aSpan -> {
      final StopWatch aSW = StopWatch.createdStarted ();
      CONDLOG.info ( () -> "Starting '" + sActionName + "'");
      PhotonWorkerPoolTelemetry.onTaskStart (aSpan, sActionName);

      boolean bSuccess = false;
      T ret = null;
      try
      {
        ret = aBody.get ();
        bSuccess = true;
        PhotonWorkerPoolTelemetry.onTaskSuccess (aSpan);
      }
      catch (final Exception ex)
      {
        CONDLOG.error ( () -> "Error running ph-oton " + sTaskType + " " + aTask, ex);
        PhotonWorkerPoolTelemetry.onTaskError (aSpan, ex);
      }
      finally
      {
        aSW.stop ();
        CONDLOG.info ( () -> "Finished '" + sActionName + "' after " + aSW.getMillis () + " milliseconds");
        if (aEndEmitted.compareAndSet (false, true))
          PhotonWorkerPoolTelemetry.onTaskEnd (bSuccess, aSW.getMillis ());
      }
      return ret;
    });
  }

  /**
   * @param sActionName
   *        The caller supplied action name. May not be <code>null</code>.
   * @param sTaskType
   *        The task type for the log message. May not be <code>null</code>.
   * @param aEndEmitted
   *        The guard shared with {@link #_executeInstrumented(String, String, Object, AtomicBoolean, IThrowingSupplier)}.
   *        May not be <code>null</code>.
   * @return The handler for failures that did not surface inside the task body. Never
   *         <code>null</code>.
   */
  @NonNull
  private static <T> Function <Throwable, T> _onUnexpectedException (@NonNull final String sActionName,
                                                                     @NonNull final String sTaskType,
                                                                     @NonNull final AtomicBoolean aEndEmitted)
  {
    return ex -> {
      LOGGER.error ("Unexpected exception in ph-oton " + sTaskType + " '" + sActionName + "'", ex);
      if (aEndEmitted.compareAndSet (false, true))
        PhotonWorkerPoolTelemetry.onTaskDropped ();
      return null;
    };
  }

  @NonNull
  public CompletableFuture <Void> run (@NonNull final String sActionName, @NonNull final Runnable aRunnable)
  {
    final AtomicBoolean aEndEmitted = new AtomicBoolean (false);
    return CompletableFuture.runAsync ( () -> _executeInstrumented (sActionName, "runner", aRunnable, aEndEmitted, () -> {
      aRunnable.run ();
      return null;
    }), m_aES).exceptionally (_onUnexpectedException (sActionName, "runner", aEndEmitted));
  }

  @NonNull
  public CompletableFuture <Void> runThrowing (@NonNull final String sActionName,
                                               @NonNull final IThrowingRunnable <? extends Exception> aRunnable)
  {
    final AtomicBoolean aEndEmitted = new AtomicBoolean (false);
    return CompletableFuture.runAsync ( () -> _executeInstrumented (sActionName, "runner", aRunnable, aEndEmitted, () -> {
      aRunnable.run ();
      return null;
    }), m_aES).exceptionally (_onUnexpectedException (sActionName, "runner", aEndEmitted));
  }

  @NonNull
  public <T> CompletableFuture <T> supply (@NonNull final String sActionName, @NonNull final Supplier <T> aSupplier)
  {
    final AtomicBoolean aEndEmitted = new AtomicBoolean (false);
    return CompletableFuture.supplyAsync ( () -> _executeInstrumented (sActionName,
                                                                      "supplier",
                                                                      aSupplier,
                                                                      aEndEmitted,
                                                                      aSupplier::get), m_aES)
                            .exceptionally (_onUnexpectedException (sActionName, "supplier", aEndEmitted));
  }

  @NonNull
  public <T> CompletableFuture <T> supplyThrowing (@NonNull final String sActionName,
                                                   @NonNull final IThrowingSupplier <T, ? extends Exception> aSupplier)
  {
    final AtomicBoolean aEndEmitted = new AtomicBoolean (false);
    return CompletableFuture.supplyAsync ( () -> _executeInstrumented (sActionName,
                                                                      "supplier",
                                                                      aSupplier,
                                                                      aEndEmitted,
                                                                      aSupplier::get), m_aES)
                            .exceptionally (_onUnexpectedException (sActionName, "supplier", aEndEmitted));
  }
}
