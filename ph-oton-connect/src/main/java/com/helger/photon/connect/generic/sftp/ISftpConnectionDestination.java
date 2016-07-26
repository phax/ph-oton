/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.photon.connect.generic.sftp;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import com.helger.network.port.DefaultNetworkPorts;
import com.helger.photon.connect.generic.IConnectionDestination;
import com.jcraft.jsch.ChannelSftp;

public interface ISftpConnectionDestination extends IConnectionDestination <ChannelSftp>
{
  int DEFAULT_SFTP_PORT = DefaultNetworkPorts.TCP_22_ssh.getPort ();

  /**
   * @return The name or IP address of the host.
   */
  @Nonnull
  String getHostname ();

  /**
   * @return The port to use. In you are not sure, return
   *         {@link #DEFAULT_SFTP_PORT}.
   */
  @Nonnegative
  int getPort ();
}
