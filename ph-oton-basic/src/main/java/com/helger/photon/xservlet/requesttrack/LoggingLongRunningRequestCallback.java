/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.xservlet.requesttrack;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.error.level.EErrorLevel;
import com.helger.commons.error.level.IErrorLevel;
import com.helger.commons.log.LogHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.servlet.request.RequestHelper;
import com.helger.web.scope.IRequestWebScope;

/**
 * A simple implementation of {@link ILongRunningRequestCallback} simply logging
 * such events.
 *
 * @author Philip Helger
 * @since 4.0.0
 */
public class LoggingLongRunningRequestCallback implements ILongRunningRequestCallback
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingLongRunningRequestCallback.class);

  private IErrorLevel m_aErrorLevel;

  public LoggingLongRunningRequestCallback ()
  {
    this (EErrorLevel.WARN);
  }

  public LoggingLongRunningRequestCallback (@Nonnull final IErrorLevel aErrorLevel)
  {
    setErrorLevel (aErrorLevel);
  }

  @Nonnull
  public IErrorLevel getErrorLevel ()
  {
    return m_aErrorLevel;
  }

  @Nonnull
  public LoggingLongRunningRequestCallback setErrorLevel (@Nonnull final IErrorLevel aErrorLevel)
  {
    m_aErrorLevel = ValueEnforcer.notNull (aErrorLevel, "ErrorLevel");
    return this;
  }

  public void onLongRunningRequest (@Nonnull @Nonempty final String sUniqueRequestID,
                                    @Nonnull final IRequestWebScope aRequestScope,
                                    @Nonnegative final long nRunningMilliseconds)
  {
    LogHelper.log (s_aLogger,
                   m_aErrorLevel,
                   "Long running request. ID=" +
                                  sUniqueRequestID +
                                  "; millisecs=" +
                                  nRunningMilliseconds +
                                  "; URL=" +
                                  RequestHelper.getURL (aRequestScope.getRequest ()));
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("errorLevel", m_aErrorLevel).getToString ();
  }
}
