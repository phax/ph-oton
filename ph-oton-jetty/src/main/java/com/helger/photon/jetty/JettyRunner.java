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
package com.helger.photon.jetty;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.eclipse.jetty.server.Server;

import com.helger.commons.exception.InitializationException;

/**
 * A simple wrapper around Jetty. It synchronously starts and stops Jetty.
 *
 * @author Philip Helger
 * @since 7.0.2
 */
public class JettyRunner
{
  private final int m_nPort;
  private final int m_nStopPort;
  private Thread m_aThread;

  public JettyRunner ()
  {
    this (JettyStarter.DEFAULT_PORT, JettyStarter.DEFAULT_STOP_PORT);
  }

  public JettyRunner (@Nonnegative final int nPort, @Nonnegative final int nStopPort)
  {
    m_nPort = nPort;
    m_nStopPort = nStopPort;
  }

  @Nonnegative
  public int getPort ()
  {
    return m_nPort;
  }

  @Nonnegative
  public int getStopPort ()
  {
    return m_nStopPort;
  }

  public synchronized void startServer () throws Exception
  {
    if (m_aThread != null)
      throw new IllegalStateException ("Jetty is already running!");

    final AtomicBoolean aSuccess = new AtomicBoolean (true);
    final Semaphore s = new Semaphore (0);
    m_aThread = new Thread ( () -> {
      try
      {
        new JettyStarter ("JettyRunner:" + m_nPort + ":" + m_nStopPort)
        {
          @Override
          protected void onServerStarted (@Nonnull final Server aServer)
          {
            // Notify that server started
            s.release ();
          }

          @Override
          protected void onServerStartFailure (@Nonnull final Server aServer, @Nonnull final Throwable t)
          {
            // Server start failed - remember that
            aSuccess.set (false);
            s.release ();
          }

        }.setPort (m_nPort).setStopPort (m_nStopPort).run ();
      }
      catch (final Exception ex)
      {
        ex.printStackTrace ();
      }
    });
    m_aThread.setDaemon (true);
    m_aThread.start ();

    // Wait until server started
    s.acquire ();

    if (!aSuccess.get ())
      throw new InitializationException ("Failed to start Jetty:" + m_nPort + ":" + m_nStopPort + " - see log files");
  }

  public synchronized void shutDownServer () throws Exception
  {
    if (m_aThread != null)
    {
      new JettyStopper ().setStopPort (m_nStopPort).run ();
      m_aThread.join ();
      m_aThread = null;
    }
  }
}
