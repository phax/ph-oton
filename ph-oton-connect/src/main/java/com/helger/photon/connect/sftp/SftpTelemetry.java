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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.concurrent.Immutable;
import com.helger.photon.connect.CConnectTelemetry;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.TelemetryAttributes;
import com.jcraft.jsch.Session;

/**
 * Emits the ph-telemetry span attributes and metrics for the SFTP operations performed by
 * {@link ChannelSftpRunner} and the JSch sessions of {@link JSchSessionFactory}. All emission
 * happens through the vendor neutral ph-telemetry facades, so without a registered SPI everything
 * degrades to cheap no-ops.<br>
 * Neither credentials nor remote paths, file names or user names ever reach any attribute; the host
 * is the only dimension, because it is bounded per deployment.
 *
 * @author Philip Helger
 * @since 10.6.0
 */
@Immutable
final class SftpTelemetry
{
  private SftpTelemetry ()
  {}

  /**
   * @param sHost
   *        The remote host. May be <code>null</code>, in which case the attribute is skipped.
   * @param bSuccess
   *        Whether the operation succeeded.
   * @return The bounded attributes shared by the SFTP operation instruments. Never
   *         <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getOperationAttrs (@Nullable final String sHost, final boolean bSuccess)
  {
    return TelemetryAttributes.builder ()
                              .put (CConnectTelemetry.ATTR_SFTP_HOST, sHost)
                              .put (CConnectTelemetry.ATTR_SFTP_SUCCESS, bSuccess)
                              .build ();
  }

  /**
   * Set the operation name on the span. The host and port are only known once the session was
   * created - see {@link #onSessionEstablished(ITelemetrySpan, Session)}.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param aRunnable
   *        The SFTP action about to be executed. May not be <code>null</code>.
   */
  static void onOperationStart (@NonNull final ITelemetrySpan aSpan, @NonNull final IChannelSftpRunnable aRunnable)
  {
    aSpan.setAttribute (CConnectTelemetry.ATTR_SFTP_OPERATION, aRunnable.getDisplayName ());
  }

  /**
   * Set the remote host and port on the span, as soon as the session is known.
   *
   * @param aSpan
   *        The span to fill. May not be <code>null</code>.
   * @param aSession
   *        The established JSch session. May not be <code>null</code>.
   */
  static void onSessionEstablished (@NonNull final ITelemetrySpan aSpan, @NonNull final Session aSession)
  {
    aSpan.setAttribute (CConnectTelemetry.ATTR_SFTP_HOST, aSession.getHost ());
    aSpan.setAttribute (CConnectTelemetry.ATTR_SFTP_PORT, aSession.getPort ());
  }

  /**
   * Mark the span of an SFTP operation that failed without throwing - the runner catches the
   * {@code SftpException} and returns {@code ESuccess.FAILURE}, so the surrounding span helper
   * never sees it.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   * @param aException
   *        The exception that occurred. May not be <code>null</code>.
   */
  static void onOperationError (@NonNull final ITelemetrySpan aSpan, @NonNull final Exception aException)
  {
    aSpan.setAttribute (CConnectTelemetry.ATTR_SFTP_SUCCESS, false);
    aSpan.recordException (aException);
    aSpan.setStatusError (aException.getMessage ());
  }

  /**
   * Mark the span of a successful SFTP operation.
   *
   * @param aSpan
   *        The span to mark. May not be <code>null</code>.
   */
  static void onOperationSuccess (@NonNull final ITelemetrySpan aSpan)
  {
    aSpan.setAttribute (CConnectTelemetry.ATTR_SFTP_SUCCESS, true);
    aSpan.setStatusOk ();
  }

  /**
   * Emit the end-of-operation metrics. Called for every code path through the runner.
   *
   * @param sHost
   *        The remote host, or <code>null</code> if the session could not be created at all.
   * @param bSuccess
   *        Whether the operation succeeded.
   * @param nDurationMillis
   *        The wall-clock duration of the operation in milliseconds.
   */
  static void onOperationEnd (@Nullable final String sHost, final boolean bSuccess, final long nDurationMillis)
  {
    SftpMetrics.OPERATIONS.add (1, _getOperationAttrs (sHost, bSuccess));
    SftpMetrics.DURATION.record (nDurationMillis, _getOperationAttrs (sHost, bSuccess));
  }

  /**
   * @param aSession
   *        The session to build the attributes from. May not be <code>null</code>.
   * @return The attributes of the session up-down counter. Both the increment and the decrement
   *         must use these, so that the counter nets out to zero. Never <code>null</code>.
   */
  @NonNull
  private static TelemetryAttributes _getSessionAttrs (@NonNull final Session aSession)
  {
    return TelemetryAttributes.builder ().put (CConnectTelemetry.ATTR_SFTP_HOST, aSession.getHost ()).build ();
  }

  /**
   * Count a newly opened JSch session.
   *
   * @param aSession
   *        The created session. May not be <code>null</code>.
   */
  static void onSessionCreated (@NonNull final Session aSession)
  {
    SftpMetrics.SESSIONS_OPEN.add (1, _getSessionAttrs (aSession));
  }

  /**
   * Count a closed JSch session.
   *
   * @param aSession
   *        The destroyed session. May not be <code>null</code>.
   */
  static void onSessionDestroyed (@NonNull final Session aSession)
  {
    SftpMetrics.SESSIONS_OPEN.add (-1, _getSessionAttrs (aSession));
  }
}
