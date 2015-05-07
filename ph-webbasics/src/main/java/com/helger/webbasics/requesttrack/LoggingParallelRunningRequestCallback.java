/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.webbasics.requesttrack;

import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.error.EErrorLevel;
import com.helger.commons.log.LogUtils;
import com.helger.commons.string.ToStringGenerator;

/**
 * A simple implementation of {@link IParallelRunningRequestCallback} simply
 * logging such events.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
public class LoggingParallelRunningRequestCallback implements IParallelRunningRequestCallback
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingParallelRunningRequestCallback.class);

  private final EErrorLevel m_eErrorLevel;

  public LoggingParallelRunningRequestCallback ()
  {
    this (EErrorLevel.WARN);
  }

  public LoggingParallelRunningRequestCallback (@Nonnull final EErrorLevel eErrorLevel)
  {
    m_eErrorLevel = ValueEnforcer.notNull (eErrorLevel, "ErrorLevel");
  }

  @Nonnull
  public EErrorLevel getErrorLevel ()
  {
    return m_eErrorLevel;
  }

  public void onParallelRunningRequests (@Nonnegative final int nParallelRequests,
                                         @Nonnull @Nonempty final List <TrackedRequest> aRequests)
  {
    LogUtils.log (s_aLogger, m_eErrorLevel, "Currently " + nParallelRequests + " parallel requests are active!");
  }

  public void onParallelRunningRequestsBelowLimit ()
  {
    LogUtils.log (s_aLogger, m_eErrorLevel, "Parallel requests are back to normal!");
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("errorLevel", m_eErrorLevel).toString ();
  }
}
