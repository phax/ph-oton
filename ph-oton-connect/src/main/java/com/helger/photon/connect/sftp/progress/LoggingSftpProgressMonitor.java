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
package com.helger.photon.connect.sftp.progress;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.OverrideOnDemand;
import com.jcraft.jsch.SftpProgressMonitor;

/**
 * An implementation of {@link SftpProgressMonitor} that logs progress.
 *
 * @author Philip Helger
 */
public class LoggingSftpProgressMonitor implements SftpProgressMonitor
{
  public static final String OPERATION_PUT = "put";
  public static final String OPERATION_GET = "get";

  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingSftpProgressMonitor.class);

  private String m_sOperation;
  private long m_nCount = 0;
  private long m_nMax = 0;
  private long m_nLastPercent = -1;

  public LoggingSftpProgressMonitor ()
  {}

  public void init (final int nOperation, final String sSrc, final String sDest, final long nMax)
  {
    m_sOperation = (nOperation == SftpProgressMonitor.PUT ? OPERATION_PUT : OPERATION_GET) + ": " + sSrc;
    m_nMax = nMax;
    m_nCount = 0;
    m_nLastPercent = -1;
  }

  /**
   * Get the message to be logged. Overwrite this method to change the text.
   *
   * @param sOperation
   *        Operation to use ("put" or "get")
   * @param nCount
   *        Number of bytes handled so far
   * @param nMax
   *        Maximum number of bytes to be handled
   * @param nPerc
   *        Current percentage (count * 100 / max)
   * @return The string to be logged. Should neither be <code>null</code> nor
   *         empty.
   */
  @OverrideOnDemand
  @Nonnull
  @Nonempty
  protected String getLogMessage (@Nonnull final String sOperation, final long nCount, final long nMax, final long nPerc)
  {
    return sOperation + " Completed " + nCount + " out of " + nMax + " (" + nPerc + "%).";
  }

  public boolean count (final long nCount)
  {
    m_nCount += nCount;

    // Avoid division by zero
    if (m_nMax != 0)
    {
      final long nPerc = m_nCount * 100 / m_nMax;
      if (nPerc > m_nLastPercent)
      {
        // Percentage has changed - update
        m_nLastPercent = nPerc;
        LOGGER.info (getLogMessage (m_sOperation, m_nCount, m_nMax, m_nLastPercent));
      }
    }
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

  /**
   * @return The maximum number of bytes to be handled. May have weird values
   *         but should be &ge; 0.
   */
  @CheckForSigned
  public long getMaximumNumberOfBytes ()
  {
    return m_nMax;
  }
}
