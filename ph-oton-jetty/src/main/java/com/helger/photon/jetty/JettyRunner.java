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
package com.helger.photon.jetty;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnull;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.exception.InitializationException;

/**
 * A simple wrapper around Jetty. It synchronously starts and stops Jetty.
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public class JettyRunner extends JettyStarter
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JettyRunner.class);

  private Thread m_aThread;
  private final Semaphore m_aServerStartedSem = new Semaphore (0);
  private final AtomicBoolean m_aServerStartupSuccess = new AtomicBoolean (true);

  public JettyRunner ()
  {
    this ("JettyRunner");
  }

  public JettyRunner (@Nonnull @Nonempty final String sAppName)
  {
    super (sAppName);
  }

  @Override
  protected void onServerStarted (@Nonnull final Server aServer)
  {
    // Notify that server started
    m_aServerStartedSem.release ();
  }

  @Override
  protected void onServerStartFailure (@Nonnull final Server aServer, @Nonnull final Throwable t)
  {
    // Server start failed - remember that
    m_aServerStartupSuccess.set (false);
    m_aServerStartedSem.release ();
  }

  public synchronized void startServer () throws Exception
  {
    if (m_aThread != null)
      throw new IllegalStateException ("Jetty is already running!");

    m_aServerStartupSuccess.set (true);
    m_aThread = new Thread ( () -> {
      try
      {
        run ();
      }
      catch (final Exception ex)
      {
        LOGGER.error ("Error running Jetty", ex);
      }
    }, "JettyRunner");
    m_aThread.setDaemon (true);
    m_aThread.start ();

    // Wait until server started
    m_aServerStartedSem.acquire ();

    if (!m_aServerStartupSuccess.get ())
      throw new InitializationException ("Failed to start Jetty:" +
                                         getPort () +
                                         ":" +
                                         getStopPort () +
                                         " - see logs for details");
  }

  public synchronized void shutDownServer () throws IOException, InterruptedException
  {
    if (m_aThread != null)
    {
      new JettyStopper ().setStopPort (getStopPort ()).setStopKey (getStopKey ()).run ();
      m_aThread.join ();
      m_aThread = null;
    }
  }
}
