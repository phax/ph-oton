/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;

/**
 * Stop a Jetty that was started with {@link JettyStarter}.
 *
 * @author Philip Helger
 */
public final class JettyStopper
{
  private static final Logger LOGGER = LoggerFactory.getLogger (JettyStopper.class);

  private String m_sStopKey = InternalJettyStopMonitorThread.STOP_KEY;
  private int m_nStopPort = InternalJettyStopMonitorThread.STOP_PORT;

  public JettyStopper ()
  {}

  @Nonnull
  public JettyStopper setStopKey (@Nonnull final String sStopKey)
  {
    ValueEnforcer.notNull (sStopKey, "StopKey");
    m_sStopKey = sStopKey;
    return this;
  }

  @Nonnull
  public JettyStopper setStopPort (@Nonnegative final int nStopPort)
  {
    ValueEnforcer.isGT0 (nStopPort, "StopPort");
    m_nStopPort = nStopPort;
    return this;
  }

  public void run () throws IOException
  {
    try (final Socket s = new Socket (InetAddress.getByName (null), m_nStopPort))
    {
      s.setSoLinger (false, 0);

      try (final OutputStream out = s.getOutputStream ())
      {
        LOGGER.info ("Sending Jetty stop request to port " + m_nStopPort);
        out.write ((m_sStopKey + "\r\nstop\r\n").getBytes (StandardCharsets.UTF_8));
        out.flush ();
      }
      LOGGER.info ("Done");
    }
    catch (final ConnectException ex)
    {
      LOGGER.warn ("Jetty is not running");
    }
  }
}
