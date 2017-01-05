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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.callback.IThrowingRunnable;
import com.helger.commons.charset.CCharset;
import com.helger.commons.io.stream.StreamHelper;

final class InternalJettyStopMonitorThread extends Thread
{
  public static final int STOP_PORT = 8079;
  public static final String STOP_KEY = "secret";

  private static final Logger s_aLogger = LoggerFactory.getLogger (InternalJettyStopMonitorThread.class);
  private final int m_nPort;
  private final String m_sKey;
  private final ServerSocket m_aServerSocket;
  private final IThrowingRunnable <Exception> m_aAction;

  public InternalJettyStopMonitorThread (@Nonnegative final int nPort,
                                         @Nonnull final String sKey,
                                         @Nonnull final IThrowingRunnable <Exception> aAction) throws IOException
  {
    m_nPort = nPort;
    m_sKey = sKey;
    setDaemon (true);
    setName ("JettyStopMonitor");
    m_aServerSocket = new ServerSocket (m_nPort, 1, InetAddress.getByName (null));
    m_aAction = aAction;
  }

  @Override
  public void run ()
  {
    outer: while (true)
    {
      try (final Socket aSocket = m_aServerSocket.accept ();
           final LineNumberReader lin = new LineNumberReader (new InputStreamReader (aSocket.getInputStream (),
                                                                                     CCharset.CHARSET_UTF_8_OBJ)))
      {
        // First line: key
        final String sKey = lin.readLine ();
        if (!m_sKey.equals (sKey))
        {
          s_aLogger.warn ("Stop key mismatch. Got '" + sKey + "' but was expecting something else");
          continue;
        }

        // Second line: stop
        final String sCmd = lin.readLine ();
        if (false)
          s_aLogger.info ("Got command: " + sCmd);
        if ("stop".equals (sCmd))
        {
          StreamHelper.close (aSocket);
          StreamHelper.close (m_aServerSocket);
          m_aAction.run ();
          break outer;
        }
      }
      catch (final Exception e)
      {
        s_aLogger.error ("Error reading from socket", e);
        break;
      }
    }
  }
}
