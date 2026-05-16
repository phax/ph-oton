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
package com.helger.photon.connect.connection;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.security.authentication.credentials.IAuthCredentials;

/**
 * Interface having the basic fields required for connecting to a server via SSH.
 *
 * @author philip
 */
public interface IBaseServerConnectionSettings extends IAuthCredentials
{
  /**
   * @return The IP address of the sever to connect to.
   */
  @NonNull
  @Nonempty
  String getServerAddress ();

  /**
   * @return The network port number to use.
   */
  @Nonnegative
  int getServerPort ();

  /**
   * @return The user name for connecting to the server.
   */
  @NonNull
  @Nonempty
  String getUserName ();

  /**
   * @return The connection timeout. A <code>null</code> value or a negative duration are ignored,
   *         {@link Duration#ZERO} means infinite, and any positive duration is applied to the
   *         underlying connection.
   * @since 10.2.3
   */
  @Nullable
  Duration getConnectionTimeout ();

  /**
   * @return The connection timeout in milliseconds. Values &lt; 0 are ignored, 0 means infinite and
   *         all values &gt; 0 are the respective milli seconds.
   * @deprecated Use {@link #getConnectionTimeout()} instead.
   */
  @Deprecated (forRemoval = true, since = "10.2.3")
  default int getConnectionTimeoutMillis ()
  {
    final Duration aDuration = getConnectionTimeout ();
    return aDuration == null ? -1 : Math.toIntExact (aDuration.toMillis ());
  }
}
