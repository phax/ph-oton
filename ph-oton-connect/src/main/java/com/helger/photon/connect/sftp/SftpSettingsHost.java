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

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.config.IConfig;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Default implementation of {@link ISftpSettingsHost}.
 *
 * @author Philip Helger
 * @since 9.2.9
 */
@Immutable
public class SftpSettingsHost implements ISftpSettingsHost
{
  private final String m_sServerHost;
  private final int m_nServerPort;
  private final int m_nConnectionTimeoutMillis;
  private final String m_sServerUserName;
  private final String m_sServerPassword;
  private final String m_sKeyPairPrivateKeyFile;
  private final String m_sKeyPairPublicKeyFile;
  private final String m_sKeyPairPassphrase;
  private final int m_nMaxParallelConnections;

  // Status vars
  private String m_sDisplayName;

  protected SftpSettingsHost (@Nonnull final ISftpSettingsHost aHost)
  {
    this (aHost.getServerHost (),
          aHost.getServerPort (),
          aHost.getConnectionTimeoutMillis (),
          aHost.getServerUserName (),
          aHost.getServerPassword (),
          aHost.getKeyPairPrivateKeyFile (),
          aHost.getKeyPairPublicKeyFile (),
          aHost.getKeyPairPassphrase (),
          aHost.getMaximumParallelConnections ());
  }

  public SftpSettingsHost (@Nonnull @Nonempty final String sServerHost,
                           @Nonnegative final int nServerPort,
                           @Nonnegative final int nConnectionTimeoutMillis,
                           @Nullable final String sServerUserName,
                           @Nullable final String sServerPassword,
                           @Nullable final String sKeyPairPrivateKeyFile,
                           @Nullable final String sKeyPairPublicKeyFile,
                           @Nullable final String sKeyPairPassphrase,
                           @Nonnegative final int nMaxParallelConnections)
  {
    ValueEnforcer.notEmpty (sServerHost, "ServerHost");
    ValueEnforcer.isGT0 (nServerPort, "ServerPort");
    ValueEnforcer.isGE0 (nConnectionTimeoutMillis, "ConnectionTimeoutMillis");
    ValueEnforcer.isGT0 (nMaxParallelConnections, "MaxParallelConnections");
    m_sServerHost = sServerHost;
    m_nServerPort = nServerPort;
    m_nConnectionTimeoutMillis = nConnectionTimeoutMillis;
    m_sServerUserName = sServerUserName;
    m_sServerPassword = sServerPassword;
    m_nMaxParallelConnections = nMaxParallelConnections;
    m_sKeyPairPrivateKeyFile = sKeyPairPrivateKeyFile;
    m_sKeyPairPublicKeyFile = sKeyPairPublicKeyFile;
    m_sKeyPairPassphrase = sKeyPairPassphrase;
  }

  @Nonnull
  @Nonempty
  public String getServerHost ()
  {
    return m_sServerHost;
  }

  @Nonnegative
  public int getServerPort ()
  {
    return m_nServerPort;
  }

  @Nonnegative
  public int getConnectionTimeoutMillis ()
  {
    return m_nConnectionTimeoutMillis;
  }

  @Nullable
  public String getServerUserName ()
  {
    return m_sServerUserName;
  }

  @Nullable
  public String getServerPassword ()
  {
    return m_sServerPassword;
  }

  @Nullable
  public String getKeyPairPrivateKeyFile ()
  {
    return m_sKeyPairPrivateKeyFile;
  }

  @Nullable
  public String getKeyPairPublicKeyFile ()
  {
    return m_sKeyPairPublicKeyFile;
  }

  @Nullable
  public String getKeyPairPassphrase ()
  {
    return m_sKeyPairPassphrase;
  }

  @Nonnegative
  public int getMaximumParallelConnections ()
  {
    return m_nMaxParallelConnections;
  }

  @Nonnull
  @Nonempty
  public String getDisplayName ()
  {
    // Caching
    String ret = m_sDisplayName;
    if (ret == null)
    {
      ret = getServerHost () + ":" + getServerPort ();
      if (hasServerUserName ())
        ret = getServerUserName () + "@" + ret;

      m_sDisplayName = ret;
    }
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ServerHost", m_sServerHost)
                                       .append ("ServerPort", m_nServerPort)
                                       .append ("ConnectionTimeoutMillis", m_nConnectionTimeoutMillis)
                                       .appendIfNotNull ("ServerUserName", m_sServerUserName)
                                       .appendPassword ("ServerPassword")
                                       .appendIfNotNull ("KeyPairPrivateKeyFile", m_sKeyPairPrivateKeyFile)
                                       .appendIfNotNull ("KeyPairPublicKeyFile", m_sKeyPairPublicKeyFile)
                                       .appendPassword ("KeyPairPassphrase")
                                       .append ("MaxParallelConnections", m_nMaxParallelConnections)
                                       .getToString ();
  }

  @Nullable
  public static SftpSettingsHost createFromConfig (@Nonnull final IConfig aConfig,
                                                   @Nonnull @Nonempty final String sConfigPrefix)
  {
    ValueEnforcer.notNull (aConfig, "Config");
    ValueEnforcer.notEmpty (sConfigPrefix, "ConfigPrefix");
    ValueEnforcer.isFalse (sConfigPrefix.endsWith ("."), "ConfigPrefix must end with a dot");

    final String sHost = aConfig.getAsString (sConfigPrefix + ".host");
    if (StringHelper.isEmpty (sHost))
      return null;

    final int nPort = aConfig.getAsInt (sConfigPrefix + ".port", ISftpSettingsHost.DEFAULT_PORT);
    final int nConnectionTimeoutMillis = aConfig.getAsInt (sConfigPrefix + ".connectiontimeoutms",
                                                           ISftpSettingsHost.DEFAULT_CONNECTION_TIMEOUT_MS);
    final String sUserName = aConfig.getAsString (sConfigPrefix + ".user");
    final String sPassword = aConfig.getAsString (sConfigPrefix + ".password");

    final String sKeyPairPrivateKeyFile = aConfig.getAsString (sConfigPrefix + ".keypair.privatekeypath");
    final String sKeyPairPublicKeyFile = aConfig.getAsString (sConfigPrefix + ".keypair.publickeypath");
    final String sKeyPairPassphrase = aConfig.getAsString (sConfigPrefix + ".keypair.passphrase");

    final int nMaxParallelConnections = aConfig.getAsInt (sConfigPrefix + ".maxconnections",
                                                          ISftpSettingsHost.DEFAULT_MAX_CONNECTIONS);

    return new SftpSettingsHost (sHost,
                                 nPort,
                                 nConnectionTimeoutMillis,
                                 sUserName,
                                 sPassword,
                                 sKeyPairPrivateKeyFile,
                                 sKeyPairPublicKeyFile,
                                 sKeyPairPassphrase,
                                 nMaxParallelConnections);
  }
}
