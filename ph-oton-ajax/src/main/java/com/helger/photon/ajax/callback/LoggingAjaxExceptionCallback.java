/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.ajax.callback;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.photon.ajax.IAjaxInvoker;
import com.helger.photon.ajax.executor.IAjaxExecutor;
import com.helger.servlet.request.RequestLogger;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Implementation of {@link IAjaxExceptionCallback} logging to an SLF4J logger
 *
 * @author Philip Helger
 */
public class LoggingAjaxExceptionCallback implements IAjaxExceptionCallback
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingAjaxExceptionCallback.class);

  public void onAjaxExecutionException (@Nullable final IAjaxInvoker aAjaxInvoker,
                                        @Nullable final String sFunctionName,
                                        @Nonnull final IAjaxExecutor aAjaxExecutor,
                                        @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final Exception ex)
  {
    if (ex instanceof IOException)
    {
      if (!StreamHelper.isKnownEOFException (ex))
        LOGGER.error ("Error writing result of Ajax function '" + sFunctionName + "' with " + aAjaxExecutor, ex);
    }
    else
    {
      LOGGER.error ("Error invoking Ajax function '" + sFunctionName + "' on " + aAjaxExecutor, ex);
      if (GlobalDebug.isDebugMode ())
        RequestLogger.logRequestComplete (aRequestScope.getRequest ());
    }
  }
}
