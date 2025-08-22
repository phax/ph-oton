/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.security.authentication.credentials.IAuthCredentials;

import jakarta.annotation.Nonnull;

/**
 * Interface having the basic fields required for connecting to a server via
 * SSH.
 *
 * @author philip
 */
public interface IBaseServerConnectionSettings extends IAuthCredentials
{
  /**
   * @return The IP address of the sever to connect to.
   */
  @Nonnull
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
  @Nonnull
  @Nonempty
  String getUserName ();

  /**
   * @return The connection timeout in milliseconds. Values &lt; 0 are ignored,
   *         0 means infinite and all values &gt; 0 are the respective milli
   *         seconds.
   */
  int getConnectionTimeoutMillis ();
}
