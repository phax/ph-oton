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
package com.helger.webbasics.ajax.callback;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.web.scopes.domain.IRequestWebScopeWithoutResponse;
import com.helger.webbasics.ajax.IAjaxExecutor;
import com.helger.webbasics.ajax.IAjaxInvoker;
import com.helger.webbasics.ajax.IAjaxLongRunningExecutionCallback;

public class LoggingAjaxLongRunningExecutionCallback implements IAjaxLongRunningExecutionCallback
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingAjaxLongRunningExecutionCallback.class);

  public void onLongRunningExecution (@Nonnull final IAjaxInvoker aInvoker,
                                      @Nonnull final String sFunctionName,
                                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnull final IAjaxExecutor aExecutor,
                                      @Nonnegative final long nExecutionMillis)
  {
    s_aLogger.warn ("Finished invoking Ajax function '" +
                    sFunctionName +
                    "' which took " +
                    nExecutionMillis +
                    " milliseconds (which is too long)");
  }
}
