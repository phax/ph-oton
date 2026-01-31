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

import org.jspecify.annotations.Nullable;

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
