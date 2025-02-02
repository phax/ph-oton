package com.helger.photon.connect.sftp;

import javax.annotation.Nullable;

/**
 * Base interface for SFTP settings
 *
 * @author Philip Helger
 * @since 9.2.9
 */
public interface ISftpSettings extends ISftpSettingsHost
{
  /**
   * @return The folder where the messages are to be uploaded to. Should NOT end
   *         with a slash ("/"). Relative for the selected user.
   */
  @Nullable
  String getServerDirectoryUpload ();

  /**
   * @return The folder where the messages are to be downloaded from. Should NOT
   *         end with a slash ("/"). Relative for the selected user.
   */
  @Nullable
  String getServerDirectoryDownload ();
}
