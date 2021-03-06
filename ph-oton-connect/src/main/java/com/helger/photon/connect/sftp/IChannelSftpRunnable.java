/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;

import com.helger.commons.name.IHasDisplayName;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;

/**
 * Callback interface for executing SFTP actions. The {@link IHasDisplayName}
 * interface is only used for better messages in case of an error.
 *
 * @author philip
 */
public interface IChannelSftpRunnable extends IHasDisplayName
{
  /**
   * Execute your stuff within a safe SFTP channel.
   *
   * @param aChannel
   *        The channel to use. Never <code>null</code>.
   * @throws SftpException
   *         In case anything goes wrong.
   */
  void execute (@Nonnull ChannelSftp aChannel) throws SftpException;
}
