/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.SftpProgressMonitor;

public final class LoggingSftpProgressMonitor implements SftpProgressMonitor
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingSftpProgressMonitor.class);

  private String m_sOperation;
  private long m_nCount = 0;
  private long m_nMax = 0;
  private long m_nPercent = -1;

  public LoggingSftpProgressMonitor ()
  {}

  public void init (final int nOperation, final String sSrc, final String sDest, final long nMax)
  {
    m_sOperation = (nOperation == SftpProgressMonitor.PUT ? "put" : "get") + ": " + sSrc;
    m_nMax = nMax;
    m_nCount = 0;
    m_nPercent = -1;
  }

  public boolean count (final long nCount)
  {
    m_nCount += nCount;

    // Avoid division by zero
    if (m_nMax != 0)
    {
      final long nPerc = m_nCount * 100 / m_nMax;
      if (m_nPercent < nPerc)
      {
        m_nPercent = nPerc;
        s_aLogger.info (m_sOperation + " Completed " + m_nCount + " out of " + m_nMax + " (" + m_nPercent + "%).");
      }
    }
    return true;
  }

  public void end ()
  {}
}
