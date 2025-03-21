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
package com.helger.photon.connect.sftp;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
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

  /**
   * Create a new runnable that first executes this action, and than the
   * provided one.
   *
   * @param aOther
   *        The other runnable to execute after this. May not be
   *        <code>null</code>.
   * @return Never <code>null</code>.
   * @since 8.3.2
   */
  @Nonnull
  @CheckReturnValue
  default IChannelSftpRunnable followedBy (@Nonnull final IChannelSftpRunnable aOther)
  {
    return and (this, aOther);
  }

  /**
   * Create a new {@link IChannelSftpRunnable} that contains both parameters in
   * a row.
   *
   * @param aLhs
   *        First runnable. May not be <code>null</code>.
   * @param aRhs
   *        Second runnable. May not be <code>null</code>.
   * @return Never <code>null</code>.
   * @since 8.3.2
   */
  @Nonnull
  @CheckReturnValue
  static IChannelSftpRunnable and (@Nonnull final IChannelSftpRunnable aLhs, @Nonnull final IChannelSftpRunnable aRhs)
  {
    ValueEnforcer.notNull (aLhs, "Lhs");
    ValueEnforcer.notNull (aRhs, "Rhs");
    return new IChannelSftpRunnable ()
    {
      @Nonnull
      @Nonempty
      public String getDisplayName ()
      {
        return aLhs.getDisplayName () + " & " + aRhs.getDisplayName ();
      }

      public void execute (@Nonnull final ChannelSftp aChannel) throws SftpException
      {
        aLhs.execute (aChannel);
        aRhs.execute (aChannel);
      }
    };
  }
}
