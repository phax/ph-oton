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
package com.helger.photon.connect.sftp;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.state.ESuccess;
import com.helger.base.timing.StopWatch;
import com.helger.base.wrapper.Wrapper;
import com.helger.photon.connect.CConnectTelemetry;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.Telemetry;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Immutable
public final class ChannelSftpRunner
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ChannelSftpRunner.class);

  private ChannelSftpRunner ()
  {}

  /**
   * Upload a file to the server.
   *
   * @param aSessionProvider
   *        The JSch session provider. May not be <code>null</code>.
   * @param nChannelConnectTimeoutMillis
   *        The channel connection timeout in milliseconds.
   * @param aRunnable
   *        The callback that performs the actions via SFTP. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} if operation succeeded, {@link ESuccess#FAILURE} otherwise.
   * @throws JSchException
   *         If some general connection handling stuff goes wrong.
   * @deprecated Use {@link #execute(IJSchSessionProvider, Duration, IChannelSftpRunnable)} instead.
   */
  @Deprecated (forRemoval = true, since = "10.2.3")
  @NonNull
  public static ESuccess execute (@NonNull final IJSchSessionProvider aSessionProvider,
                                  final int nChannelConnectTimeoutMillis,
                                  @NonNull final IChannelSftpRunnable aRunnable) throws JSchException
  {
    return execute (aSessionProvider,
                    nChannelConnectTimeoutMillis < 0 ? null : Duration.ofMillis (nChannelConnectTimeoutMillis),
                    aRunnable);
  }

  /**
   * The actual SFTP work, executed inside the telemetry span.
   *
   * @param aSpan
   *        The span covering this operation. May not be <code>null</code>.
   * @param aSessionProvider
   *        The JSch session provider. May not be <code>null</code>.
   * @param aChannelConnectTimeout
   *        The channel connection timeout. May be <code>null</code>.
   * @param aRunnable
   *        The callback that performs the actions via SFTP. May not be <code>null</code>.
   * @param aHost
   *        Receives the remote host as soon as the session is established, so that the caller can
   *        use it as a metric attribute. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} if operation succeeded, {@link ESuccess#FAILURE} otherwise.
   * @throws JSchException
   *         If some general connection handling stuff goes wrong.
   */
  @NonNull
  private static ESuccess _executeInSpan (@NonNull final ITelemetrySpan aSpan,
                                          @NonNull final IJSchSessionProvider aSessionProvider,
                                          @Nullable final Duration aChannelConnectTimeout,
                                          @NonNull final IChannelSftpRunnable aRunnable,
                                          @NonNull final Wrapper <String> aHost) throws JSchException
  {
    SftpTelemetry.onOperationStart (aSpan, aRunnable);

    Session aSession = null;
    Channel aChannel = null;
    ChannelSftp aSFTPChannel = null;
    try
    {
      // get session from pool
      aSession = aSessionProvider.createSession ();
      if (aSession == null)
        throw new IllegalStateException ("Failed to create JSch session from provider");

      aHost.set (aSession.getHost ());
      SftpTelemetry.onSessionEstablished (aSpan, aSession);

      // Open the SFTP channel
      aChannel = aSession.openChannel ("sftp");

      // Set connection timeout
      final int nChannelConnectTimeoutMillis = aChannelConnectTimeout == null || aChannelConnectTimeout.isNegative ()
                                                                                                                      ? 0
                                                                                                                      : Math.toIntExact (aChannelConnectTimeout.toMillis ());
      aChannel.connect (nChannelConnectTimeoutMillis);
      aSFTPChannel = (ChannelSftp) aChannel;

      // call callback
      aRunnable.execute (aSFTPChannel);
      SftpTelemetry.onOperationSuccess (aSpan);
      return ESuccess.SUCCESS;
    }
    catch (final SftpException ex)
    {
      LOGGER.error ("Error peforming SFTP action: " + aRunnable.getDisplayName (), ex);
      // The exception is not re-thrown, so the span must be marked as failed here
      SftpTelemetry.onOperationError (aSpan, ex);
      return ESuccess.FAILURE;
    }
    finally
    {
      // end SFTP session
      if (aSFTPChannel != null)
        aSFTPChannel.quit ();

      // close channel
      if (aChannel != null && aChannel.isConnected ())
        aChannel.disconnect ();

      // destroy session
      if (aSession != null)
        JSchSessionFactory.destroySession (aSession);
    }
  }

  /**
   * Upload a file to the server.
   *
   * @param aSessionProvider
   *        The JSch session provider. May not be <code>null</code>.
   * @param aChannelConnectTimeout
   *        The channel connection timeout. A <code>null</code> or negative duration means "no
   *        timeout"; {@link Duration#ZERO} means infinite.
   * @param aRunnable
   *        The callback that performs the actions via SFTP. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} if operation succeeded, {@link ESuccess#FAILURE} otherwise.
   * @throws JSchException
   *         If some general connection handling stuff goes wrong.
   */
  @NonNull
  public static ESuccess execute (@NonNull final IJSchSessionProvider aSessionProvider,
                                  @Nullable final Duration aChannelConnectTimeout,
                                  @NonNull final IChannelSftpRunnable aRunnable) throws JSchException
  {
    ValueEnforcer.notNull (aSessionProvider, "SessionProvider");
    ValueEnforcer.notNull (aRunnable, "Runnable");

    final StopWatch aSW = StopWatch.createdStarted ();
    // Both need to be readable from the finally block below
    final Wrapper <String> aHost = new Wrapper <> ();
    final Wrapper <ESuccess> aResult = new Wrapper <> (ESuccess.FAILURE);
    try
    {
      // This is an outbound network call, so the span kind is CLIENT and not INTERNAL
      return Telemetry.<ESuccess, JSchException> withSpanThrowing (CConnectTelemetry.SPAN_SFTP_EXECUTE,
                                                                   ETelemetrySpanKind.CLIENT,
                                                                   aSpan -> {
                                                                     final ESuccess ret = _executeInSpan (aSpan,
                                                                                                          aSessionProvider,
                                                                                                          aChannelConnectTimeout,
                                                                                                          aRunnable,
                                                                                                          aHost);
                                                                     aResult.set (ret);
                                                                     return ret;
                                                                   });
    }
    finally
    {
      SftpTelemetry.onOperationEnd (aHost.get (), aResult.get ().isSuccess (), aSW.stopAndGetMillis ());
    }
  }
}
