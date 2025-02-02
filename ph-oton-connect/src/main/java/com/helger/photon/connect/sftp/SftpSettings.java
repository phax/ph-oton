package com.helger.photon.connect.sftp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;
import com.helger.config.IConfig;

/**
 * Default implementation of {@link ISftpSettings}.
 *
 * @author Philip Helger
 * @since 9.2.9
 */
public class SftpSettings extends SftpSettingsHost implements ISftpSettings
{
  private final String m_sServerDirUpload;
  private final String m_sServerDirDownload;

  protected SftpSettings (@Nonnull final ISftpSettingsHost aHost,
                          @Nullable final String sServerDirUpload,
                          @Nullable final String sServerDirDownload)
  {
    super (aHost);
    m_sServerDirUpload = sServerDirUpload;
    m_sServerDirDownload = sServerDirDownload;
  }

  @Nullable
  public String getServerDirectoryUpload ()
  {
    return m_sServerDirUpload;
  }

  @Nullable
  public String getServerDirectoryDownload ()
  {
    return m_sServerDirDownload;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (super.toString ()).append ("ServerDirUpload", m_sServerDirUpload)
                                                    .append ("ServerDirDownload", m_sServerDirDownload)
                                                    .getToString ();
  }

  @Nullable
  public static SftpSettings createFromConfig (@Nonnull final IConfig aConfig,
                                               @Nonnull @Nonempty final String sConfigPrefix)
  {
    ValueEnforcer.notNull (aConfig, "Config");
    ValueEnforcer.notEmpty (sConfigPrefix, "ConfigPrefix");
    ValueEnforcer.isFalse (sConfigPrefix.endsWith ("."), "ConfigPrefix must end with a dot");

    final ISftpSettingsHost aHost = SftpSettingsHost.createFromConfig (aConfig, sConfigPrefix);
    if (aHost == null)
      return null;

    final String sServerDirUpload = aConfig.getAsString (sConfigPrefix + ".uploaddir");
    final String sServerDirDownload = aConfig.getAsString (sConfigPrefix + ".downloaddir");

    return new SftpSettings (aHost, sServerDirUpload, sServerDirDownload);
  }
}
