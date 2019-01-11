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
package com.helger.photon.core.api.callback;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.photon.core.api.IAPIInvoker;
import com.helger.photon.core.api.IAPILongRunningExecutionCallback;
import com.helger.photon.core.api.InvokableAPIDescriptor;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

public class LoggingAPILongRunningExecutionCallback implements IAPILongRunningExecutionCallback
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingAPILongRunningExecutionCallback.class);

  public void onLongRunningExecution (@Nonnull final IAPIInvoker aInvoker,
                                      @Nonnull final InvokableAPIDescriptor aInvokableDescriptor,
                                      @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                      @Nonnegative final long nExecutionMillis)
  {
    LOGGER.warn ("Finished invoking API '" +
                    aInvokableDescriptor.getPath () +
                    "' which took " +
                    nExecutionMillis +
                    " milliseconds (which is too long)");
  }
}
