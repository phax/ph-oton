package com.helger.photon.connect.sftp;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
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
  private final String m_sServerHost;
  private final int m_nServerPort;
  private final int m_nConnectionTimeoutMillis;
  private final String m_sServerUserName;
  private final String m_sServerPassword;
  private final int m_nMaxParallelConnections;
  private final String m_sKeyPairPrivateKeyFile;
  private final String m_sKeyPairPublicKeyFile;
  private final String m_sKeyPairPassphrase;

  // Status vars
  private String m_sDisplayName;

  protected SftpSettingsHost (@Nonnull final ISftpSettingsHost aHost)
  {
    this (aHost.getServerHost (),
          aHost.getServerPort (),
          aHost.getConnectionTimeoutMillis (),
          aHost.getServerUserName (),
          aHost.getServerPassword (),
          aHost.getMaximumParallelConnections (),
          aHost.getKeyPairPrivateKeyFile (),
          aHost.getKeyPairPublicKeyFile (),
          aHost.getKeyPairPassphrase ());
  }

  public SftpSettingsHost (@Nonnull @Nonempty final String sServerHost,
                           @Nonnegative final int nServerPort,
                           @Nonnegative final int nConnectionTimeoutMillis,
                           @Nullable final String sServerUserName,
                           @Nullable final String sServerPassword,
                           @Nonnegative final int nMaxParallelConnections,
                           @Nullable final String sKeyPairPrivateKeyFile,
                           @Nullable final String sKeyPairPublicKeyFile,
                           @Nullable final String sKeyPairPassphrase)
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

  @Nonnegative
  public int getMaximumParallelConnections ()
  {
    return m_nMaxParallelConnections;
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
                                       .append ("MaxParallelConnections", m_nMaxParallelConnections)
                                       .appendIfNotNull ("KeyPairPrivateKeyFile", m_sKeyPairPrivateKeyFile)
                                       .appendIfNotNull ("KeyPairPublicKeyFile", m_sKeyPairPublicKeyFile)
                                       .appendPassword ("KeyPairPassphrase")
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
    if (StringHelper.hasNoText (sHost))
      return null;

    final int nPort = aConfig.getAsInt (sConfigPrefix + ".port", -1);
    final int nConnectionTimeoutMillis = aConfig.getAsInt (sConfigPrefix + ".connectiontimeoutms",
                                                           ISftpSettingsHost.DEFAULT_CONNECTION_TIMEOUT_MS);
    final String sUserName = aConfig.getAsString (sConfigPrefix + ".user");
    final String sPassword = aConfig.getAsString (sConfigPrefix + ".password");
    final int nMaxParallelConnections = aConfig.getAsInt (sConfigPrefix + ".maxparallelconnections",
                                                          ISftpSettingsHost.DEFAULT_MAX_CONNECTIONS);

    final String sKeyPairPrivateKeyFile = aConfig.getAsString (sConfigPrefix + ".keypair.privatekeyfile");
    final String sKeyPairPublicKeyFile = aConfig.getAsString (sConfigPrefix + ".keypair.publickeyfile");
    final String sKeyPairPassphrase = aConfig.getAsString (sConfigPrefix + ".keypair.passphrase");

    return new SftpSettingsHost (sHost,
                                 nPort,
                                 nConnectionTimeoutMillis,
                                 sUserName,
                                 sPassword,
                                 nMaxParallelConnections,
                                 sKeyPairPrivateKeyFile,
                                 sKeyPairPublicKeyFile,
                                 sKeyPairPassphrase);
  }
}
