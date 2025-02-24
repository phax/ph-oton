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
package com.helger.photon.io;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.UsedViaReflection;
import com.helger.commons.callback.IThrowingRunnable;
import com.helger.commons.concurrent.BasicThreadFactory;
import com.helger.commons.concurrent.ExecutorServiceHelper;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.functional.IThrowingSupplier;
import com.helger.commons.log.ConditionalLogger;
import com.helger.commons.timing.StopWatch;
import com.helger.scope.IScope;
import com.helger.scope.singleton.AbstractGlobalSingleton;

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
                                        new BasicThreadFactory.Builder ().daemon (true)
                                                                         .namingPattern ("ph-oton-worker-%d")
                                                                         .build ()));
  }

  public PhotonWorkerPool (@Nonnull final ExecutorService aES)
  {
    ValueEnforcer.notNull (aES, "ExecutorService");
    m_aES = aES;
  }

  @Nonnull
  public static PhotonWorkerPool getInstance ()
  {
    return getGlobalSingleton (PhotonWorkerPool.class);
  }

  /**
   * @return <code>true</code> if logging is disabled, <code>false</code> if it
   *         is enabled.
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
   *        <code>true</code> to disable logging, <code>false</code> to enable
   *        logging
   * @return The previous value of the silent mode.
   * @since 9.3.0
   */
  public static boolean setSilentMode (final boolean bSilentMode)
  {
    return !CONDLOG.setEnabled (!bSilentMode);
  }

  @Override
  protected void onDestroy (@Nonnull final IScope aScopeInDestruction) throws Exception
  {
    CONDLOG.debug ( () -> "ph-oton worker pool about to be closed");
    ExecutorServiceHelper.shutdownAndWaitUntilAllTasksAreFinished (m_aES);
    CONDLOG.info ("ph-oton worker pool was closed!");
  }

  @Nonnull
  public CompletableFuture <Void> run (@Nonnull final String sActionName, @Nonnull final Runnable aRunnable)
  {
    return CompletableFuture.runAsync ( () -> {
      final StopWatch aSW = StopWatch.createdStarted ();
      CONDLOG.info ( () -> "Starting '" + sActionName + "'");
      try
      {
        aRunnable.run ();
      }
      catch (final RuntimeException ex)
      {
        CONDLOG.error ( () -> "Error running ph-oton runner " + aRunnable, ex);
      }
      finally
      {
        aSW.stop ();
        CONDLOG.info ( () -> "Finished '" + sActionName + "' after " + aSW.getMillis () + " milliseconds");
      }
    }, m_aES);
  }

  @Nonnull
  public CompletableFuture <Void> runThrowing (@Nonnull final String sActionName,
                                               @Nonnull final IThrowingRunnable <? extends Exception> aRunnable)
  {
    return CompletableFuture.runAsync ( () -> {
      final StopWatch aSW = StopWatch.createdStarted ();
      CONDLOG.info ( () -> "Starting '" + sActionName + "'");
      try
      {
        aRunnable.run ();
      }
      catch (final Exception ex)
      {
        CONDLOG.error ( () -> "Error running ph-oton runner " + aRunnable, ex);
      }
      finally
      {
        aSW.stop ();
        CONDLOG.info ( () -> "Finished '" + sActionName + "' after " + aSW.getMillis () + " milliseconds");
      }
    }, m_aES);
  }

  @Nonnull
  public <T> CompletableFuture <T> supply (@Nonnull final String sActionName, @Nonnull final Supplier <T> aSupplier)
  {
    return CompletableFuture.supplyAsync ( () -> {
      final StopWatch aSW = StopWatch.createdStarted ();
      CONDLOG.info ( () -> "Starting '" + sActionName + "'");
      try
      {
        return aSupplier.get ();
      }
      catch (final RuntimeException ex)
      {
        CONDLOG.error ( () -> "Error running ph-oton supplier " + aSupplier, ex);
        return null;
      }
      finally
      {
        aSW.stop ();
        CONDLOG.info ( () -> "Finished '" + sActionName + "' after " + aSW.getMillis () + " milliseconds");
      }
    }, m_aES);
  }

  @Nonnull
  public <T> CompletableFuture <T> supplyThrowing (@Nonnull final String sActionName,
                                                   @Nonnull final IThrowingSupplier <T, ? extends Exception> aSupplier)
  {
    return CompletableFuture.supplyAsync ( () -> {
      final StopWatch aSW = StopWatch.createdStarted ();
      CONDLOG.info ( () -> "Starting '" + sActionName + "'");
      try
      {
        return aSupplier.get ();
      }
      catch (final Exception ex)
      {
        CONDLOG.error ( () -> "Error running ph-oton supplier " + aSupplier, ex);
        return null;
      }
      finally
      {
        aSW.stop ();
        CONDLOG.info ( () -> "Finished '" + sActionName + "' after " + aSW.getMillis () + " milliseconds");
      }
    }, m_aES);
  }
}
