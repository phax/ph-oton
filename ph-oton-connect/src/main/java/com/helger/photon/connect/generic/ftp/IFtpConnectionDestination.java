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
package com.helger.photon.connect.generic.ftp;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.apache.commons.net.ftp.FTPClient;

import com.helger.network.port.DefaultNetworkPorts;
import com.helger.photon.connect.generic.IConnectionDestination;

public interface IFtpConnectionDestination extends IConnectionDestination <FTPClient>
{
  int DEFAULT_FTP_PORT = DefaultNetworkPorts.TCP_21_ftp.getPort ();

  /**
   * @return The hostname or IP address of the host.
   */
  @Nonnull
  String getHostname ();

  /**
   * @return The port to use. In you are not sure, return
   *         {@link #DEFAULT_FTP_PORT}.
   */
  @Nonnegative
  int getPort ();
}
