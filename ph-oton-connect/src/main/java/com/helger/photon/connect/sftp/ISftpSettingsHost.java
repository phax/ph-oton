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

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.base.CGlobal;
import com.helger.base.name.IHasDisplayName;
import com.helger.base.string.StringHelper;
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
  @NonNull
  @Nonempty
  String getServerHost ();

  /**
   * @return The network port number to use. Must be &gt; 0. The default value
   *         should be {@link #DEFAULT_PORT}.
   */
  @Nonnegative
  int getServerPort ();

  /**
   * @return Connection timeout milliseconds. All values &le; 0 means no timeout
   */
  @Nonnegative
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
    return StringHelper.isNotEmpty (getServerUserName ());
  }

  /**
   * @return The plain password used to connect to the server. May be
   *         <code>null</code>.
   */
  @Nullable
  String getServerPassword ();

  /**
   * @return <code>true</code> if a password is used, <code>false</code> if
   *         key-pairs are used
   */
  default boolean hasServerPassword ()
  {
    return StringHelper.isNotEmpty (getServerPassword ());
  }

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

  @NonNull
  @Nonempty
  default String getLogPrefix ()
  {
    return "SFTP[" + getDisplayName () + "]: ";
  }
}
