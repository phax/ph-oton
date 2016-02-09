/**
 * Copyright (C) 2012-2016 winenet GmbH - www.winenet.at
 * All Rights Reserved
 *
 * This file is part of the winenet-Kellerbuch software.
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is
 * strictly prohibited.
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

import com.helger.commons.annotation.DevelopersNote;
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

  public InternalJettyStopMonitorThread (@Nonnegative final int nPort, @Nonnull final String sKey) throws IOException
  {
    m_nPort = nPort;
    m_sKey = sKey;
    setDaemon (true);
    setName ("JettyStopMonitor");
    m_aServerSocket = new ServerSocket (m_nPort, 1, InetAddress.getByName (null));
  }

  @Override
  @DevelopersNote ("Consider throwing a runtime exception instead of System.exit (find bugs)")
  public void run ()
  {
    while (true)
    {
      try (final Socket aSocket = m_aServerSocket.accept ();
          final LineNumberReader lin = new LineNumberReader (new InputStreamReader (aSocket.getInputStream (),
                                                                                    CCharset.CHARSET_UTF_8_OBJ)))
      {
        // First line: key
        final String sKey = lin.readLine ();
        if (!m_sKey.equals (sKey))
          continue;

        // Second line: stop
        final String sCmd = lin.readLine ();
        if ("stop".equals (sCmd))
        {
          StreamHelper.close (aSocket);
          StreamHelper.close (m_aServerSocket);
          System.exit (0);
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
