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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.tostring.ToStringGenerator;
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

  protected SftpSettings (@NonNull final ISftpSettingsHost aHost,
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
  public static SftpSettings createFromConfig (@NonNull final IConfig aConfig,
                                               @NonNull @Nonempty final String sConfigPrefix)
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
