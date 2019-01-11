/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.photon.connect.ftp;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;

public final class FTPFileFilterFileStartsWith implements FTPFileFilter
{
  private final String m_sStart;

  public FTPFileFilterFileStartsWith (@Nonnull @Nonempty final String sStart)
  {
    m_sStart = ValueEnforcer.notEmpty (sStart, "start");
  }

  public boolean accept (@Nullable final FTPFile aFile)
  {
    return aFile != null && aFile.isFile () && aFile.getName ().startsWith (m_sStart);
  }
}
