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

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.Nonempty;
import com.helger.annotation.Nonnegative;
import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.config.IConfig;

/**
 * Default implementation of {@link ISftpSettingsHost}.
 *
 * @author Philip Helger
 * @since 9.2.9
 */
@Immutable
public class SftpSettingsHost implements ISftpSettingsHost
{
  public static final String CONFIG_SUFFIX_HOST = ".host";
  public static final String CONFIG_SUFFIX_PORT = ".port";
  public static final String CONFIG_SUFFIX_CONNECTION_TIMEOUT = ".connectiontimeout";
  /**
   * @deprecated Use {@link #CONFIG_SUFFIX_CONNECTION_TIMEOUT} instead. The value is now parsed as a
   *             {@link Duration} (e.g. <code>10s</code>) rather than an integer millisecond count.
   */
  @Deprecated (forRemoval = true, since = "10.2.3")
  public static final String CONFIG_SUFFIX_CONNECTION_TIMEOUT_MS = ".connectiontimeoutms";
  public static final String CONFIG_SUFFIX_USER = ".user";
  public static final String CONFIG_SUFFIX_PASSWORD = ".password";
  public static final String CONFIG_SUFFIX_KEYPAIR_PRIVATE_KEY_PATH = ".keypair.privatekeypath";
  public static final String CONFIG_SUFFIX_KEYPAIR_PUBLIC_KEY_PATH = ".keypair.publickeypath";
  public static final String CONFIG_SUFFIX_KEYPAIR_PASSPHRASE = ".keypair.passphrase";
  public static final String CONFIG_SUFFIX_KNOWN_HOSTS_PATH = ".knownhostspath";
  public static final String CONFIG_SUFFIX_MAX_CONNECTIONS = ".maxconnections";

  private static final Logger LOGGER = LoggerFactory.getLogger (SftpSettingsHost.class);

  private final String m_sServerHost;
  private final int m_nServerPort;
  private final Duration m_aConnectionTimeout;
  private final String m_sServerUserName;
  private final String m_sServerPassword;
  private final String m_sKeyPairPrivateKeyFile;
  private final String m_sKeyPairPublicKeyFile;
  private final String m_sKeyPairPassphrase;
  private final String m_sKnownHostsPath;
  private final int m_nMaxParallelConnections;

  // Status vars
  private String m_sDisplayName;

  protected SftpSettingsHost (@NonNull final ISftpSettingsHost aHost)
  {
    this (aHost.getServerHost (),
          aHost.getServerPort (),
          aHost.getConnectionTimeout (),
          aHost.getServerUserName (),
          aHost.getServerPassword (),
          aHost.getKeyPairPrivateKeyFile (),
          aHost.getKeyPairPublicKeyFile (),
          aHost.getKeyPairPassphrase (),
          aHost.getKnownHostsPath (),
          aHost.getMaximumParallelConnections ());
  }

  @Deprecated (forRemoval = true, since = "10.2.3")
  public SftpSettingsHost (@NonNull @Nonempty final String sServerHost,
                           @Nonnegative final int nServerPort,
                           @Nonnegative final int nConnectionTimeoutMillis,
                           @Nullable final String sServerUserName,
                           @Nullable final String sServerPassword,
                           @Nullable final String sKeyPairPrivateKeyFile,
                           @Nullable final String sKeyPairPublicKeyFile,
                           @Nullable final String sKeyPairPassphrase,
                           @Nullable final String sKnownHostsPath,
                           @Nonnegative final int nMaxParallelConnections)
  {
    this (sServerHost,
          nServerPort,
          nConnectionTimeoutMillis < 0 ? null : Duration.ofMillis (nConnectionTimeoutMillis),
          sServerUserName,
          sServerPassword,
          sKeyPairPrivateKeyFile,
          sKeyPairPublicKeyFile,
          sKeyPairPassphrase,
          sKnownHostsPath,
          nMaxParallelConnections);
  }

  public SftpSettingsHost (@NonNull @Nonempty final String sServerHost,
                           @Nonnegative final int nServerPort,
                           @Nullable final Duration aConnectionTimeout,
                           @Nullable final String sServerUserName,
                           @Nullable final String sServerPassword,
                           @Nullable final String sKeyPairPrivateKeyFile,
                           @Nullable final String sKeyPairPublicKeyFile,
                           @Nullable final String sKeyPairPassphrase,
                           @Nullable final String sKnownHostsPath,
                           @Nonnegative final int nMaxParallelConnections)
  {
    ValueEnforcer.notEmpty (sServerHost, "ServerHost");
    ValueEnforcer.isGT0 (nServerPort, "ServerPort");
    ValueEnforcer.isGT0 (nMaxParallelConnections, "MaxParallelConnections");
    m_sServerHost = sServerHost;
    m_nServerPort = nServerPort;
    m_aConnectionTimeout = aConnectionTimeout;
    m_sServerUserName = sServerUserName;
    m_sServerPassword = sServerPassword;
    m_sKeyPairPrivateKeyFile = sKeyPairPrivateKeyFile;
    m_sKeyPairPublicKeyFile = sKeyPairPublicKeyFile;
    m_sKeyPairPassphrase = sKeyPairPassphrase;
    m_sKnownHostsPath = sKnownHostsPath;
    m_nMaxParallelConnections = nMaxParallelConnections;
  }

  @NonNull
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

  @Nullable
  public Duration getConnectionTimeout ()
  {
    return m_aConnectionTimeout;
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

  @Nullable
  public String getKnownHostsPath ()
  {
    return m_sKnownHostsPath;
  }

  @Nonnegative
  public int getMaximumParallelConnections ()
  {
    return m_nMaxParallelConnections;
  }

  @NonNull
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
                                       .appendIfNotNull ("ConnectionTimeout", m_aConnectionTimeout)
                                       .appendIfNotNull ("ServerUserName", m_sServerUserName)
                                       .appendPassword ("ServerPassword")
                                       .appendIfNotNull ("KeyPairPrivateKeyFile", m_sKeyPairPrivateKeyFile)
                                       .appendIfNotNull ("KeyPairPublicKeyFile", m_sKeyPairPublicKeyFile)
                                       .appendPassword ("KeyPairPassphrase")
                                       .appendIfNotNull ("KnownHostsPath", m_sKnownHostsPath)
                                       .append ("MaxParallelConnections", m_nMaxParallelConnections)
                                       .getToString ();
  }

  @Nullable
  public static SftpSettingsHost createFromConfig (@NonNull final IConfig aConfig,
                                                   @NonNull @Nonempty final String sConfigPrefix)
  {
    ValueEnforcer.notNull (aConfig, "Config");
    ValueEnforcer.notEmpty (sConfigPrefix, "ConfigPrefix");
    ValueEnforcer.isFalse (sConfigPrefix.endsWith ("."), "ConfigPrefix must end with a dot");

    final String sHost = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_HOST);
    if (StringHelper.isEmpty (sHost))
      return null;

    final int nPort = aConfig.getAsInt (sConfigPrefix + CONFIG_SUFFIX_PORT, ISftpSettingsHost.DEFAULT_PORT);
    final String sConnectionTimeoutKey = sConfigPrefix + CONFIG_SUFFIX_CONNECTION_TIMEOUT;
    Duration aConnectionTimeout = aConfig.getAsConfigDuration (sConnectionTimeoutKey,
                                                               sErr -> LOGGER.warn ("Invalid SFTP connection timeout configured at '" +
                                                                                    sConnectionTimeoutKey +
                                                                                    "': " +
                                                                                    sErr));
    if (aConnectionTimeout == null)
    {
      // Backwards-compatibility: fall back to the legacy integer-millisecond key
      final String sLegacyKey = sConfigPrefix + CONFIG_SUFFIX_CONNECTION_TIMEOUT_MS;
      final String sLegacyValue = aConfig.getAsString (sLegacyKey);
      if (StringHelper.isNotEmpty (sLegacyValue))
      {
        LOGGER.warn ("Configuration key '" +
                     sLegacyKey +
                     "' is deprecated and marked for removal; please switch to '" +
                     sConnectionTimeoutKey +
                     "' which accepts a duration string like '10s' or '1m 30s'");
        aConnectionTimeout = Duration.ofMillis (aConfig.getAsInt (sLegacyKey,
                                                                  (int) ISftpSettingsHost.DEFAULT_CONNECTION_TIMEOUT.toMillis ()));
      }
      else
        aConnectionTimeout = ISftpSettingsHost.DEFAULT_CONNECTION_TIMEOUT;
    }
    final String sUserName = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_USER);
    final String sPassword = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_PASSWORD);

    final String sKeyPairPrivateKeyFile = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_KEYPAIR_PRIVATE_KEY_PATH);
    final String sKeyPairPublicKeyFile = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_KEYPAIR_PUBLIC_KEY_PATH);
    final String sKeyPairPassphrase = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_KEYPAIR_PASSPHRASE);

    final String sKnownHostsPath = aConfig.getAsString (sConfigPrefix + CONFIG_SUFFIX_KNOWN_HOSTS_PATH);

    final int nMaxParallelConnections = aConfig.getAsInt (sConfigPrefix + CONFIG_SUFFIX_MAX_CONNECTIONS,
                                                          ISftpSettingsHost.DEFAULT_MAX_CONNECTIONS);

    return new SftpSettingsHost (sHost,
                                 nPort,
                                 aConnectionTimeout,
                                 sUserName,
                                 sPassword,
                                 sKeyPairPrivateKeyFile,
                                 sKeyPairPublicKeyFile,
                                 sKeyPairPassphrase,
                                 sKnownHostsPath,
                                 nMaxParallelConnections);
  }
}
