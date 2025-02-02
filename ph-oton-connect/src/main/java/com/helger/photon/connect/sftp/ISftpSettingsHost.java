package com.helger.photon.connect.sftp;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.name.IHasDisplayName;
import com.helger.commons.string.StringHelper;
import com.helger.network.port.DefaultNetworkPorts;

/**
 * Base interface for SFTP settings per host. These settings
 *
 * @author Philip Helger
 * @since 9.2.9
 */
public interface ISftpSettingsHost extends IHasDisplayName
{
  int DEFAULT_PORT = DefaultNetworkPorts.TCP_22_ssh.getPort ();

  int DEFAULT_CONNECTION_TIMEOUT_MS = 10 * (int) CGlobal.MILLISECONDS_PER_SECOND;

  int DEFAULT_MAX_CONNECTIONS = 4;

  /**
   * @return The IP address of the sever to connect to.
   */
  @Nonnull
  @Nonnegative
  String getServerHost ();

  /**
   * @return The network port number to use. Must be &gt; 0. The default value
   *         should be {@link #DEFAULT_PORT}.
   */
  int getServerPort ();

  /**
   * @return Connection timeout milliseconds. All values &le; 0 means no timeout
   */
  int getConnectionTimeoutMillis ();

  /**
   * @return The user name for connecting to the server. May be
   *         <code>null</code>.
   */
  @Nullable
  String getServerUserName ();

  /**
   * @return <code>true</code> if a server user name is present.
   */
  default boolean hasServerUserName ()
  {
    return StringHelper.hasText (getServerUserName ());
  }

  /**
   * @return The plain password used to connect to the server. May be
   *         <code>null</code>.
   */
  @Nullable
  String getServerPassword ();

  /**
   * @return The path to a PuttyGen PPK file (classpath relative)
   */
  @Nullable
  String getKeyPairPrivateKeyFile ();

  /**
   * @return The path to a PuttyGen public key file (classpath relative)
   */
  @Nullable
  String getKeyPairPublicKeyFile ();

  /**
   * @return The pass phrase used to encode the PPK key. You should only use
   *         ASCII characters at the moment because the encoding of the pass
   *         phrase is unclear
   */
  @Nullable
  String getKeyPairPassphrase ();

  /**
   * @return The maximum number of parallel connections to this server. Must be
   *         &gt; 0.
   */
  @Nonnegative
  int getMaximumParallelConnections ();

  @Nonnull
  @Nonempty
  default String getLogPrefix ()
  {
    return "SFTP[" + getDisplayName () + "]: ";
  }
}
