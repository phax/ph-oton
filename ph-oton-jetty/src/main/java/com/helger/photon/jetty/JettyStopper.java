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
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.charset.CCharset;

public final class JettyStopper
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (JettyStopper.class);

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
        s_aLogger.info ("Sending jetty stop request");
        out.write ((m_sStopKey + "\r\nstop\r\n").getBytes (CCharset.CHARSET_UTF_8_OBJ));
        out.flush ();
      }
    }
    catch (final ConnectException ex)
    {
      s_aLogger.warn ("Jetty is not running");
    }
  }
}
