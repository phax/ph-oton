/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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
package com.helger.photon.connect.sftp.progress;

import javax.annotation.Nonnegative;

import com.jcraft.jsch.SftpProgressMonitor;

/**
 * An implementation of {@link SftpProgressMonitor} that just counts the written
 * bytes.
 *
 * @author Philip Helger
 * @since 8.3.2
 */
public class CountingSftpProgressMonitor implements SftpProgressMonitor
{
  private long m_nCount;

  public void init (final int nOperation, final String sSrc, final String sDest, final long nMax)
  {
    m_nCount = 0;
  }

  public boolean count (final long nCount)
  {
    m_nCount += nCount;
    // Continue processing - important!
    return true;
  }

  public void end ()
  {}

  /**
   * @return The number of bytes handles so far. Should always be &ge; 0.
   */
  @Nonnegative
  public long getNumberOfBytes ()
  {
    return m_nCount;
  }
}
