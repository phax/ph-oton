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
package com.helger.photon.core.ajax.callback;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.photon.core.ajax.IAjaxExceptionCallback;
import com.helger.photon.core.ajax.IAjaxExecutor;
import com.helger.photon.core.ajax.IAjaxInvoker;
import com.helger.servlet.request.RequestLogger;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Implementation of {@link IAjaxExceptionCallback} logging to an SLF4J logger
 *
 * @author Philip Helger
 */
public class LoggingAjaxExceptionCallback implements IAjaxExceptionCallback
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (LoggingAjaxExceptionCallback.class);

  public void onAjaxExecutionException (@Nullable final IAjaxInvoker aAjaxInvoker,
                                        @Nullable final String sFunctionName,
                                        @Nonnull final IAjaxExecutor aAjaxExecutor,
                                        @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                        @Nonnull final Throwable t)
  {
    if (t instanceof IOException)
    {
      if (!StreamHelper.isKnownEOFException (t))
        s_aLogger.error ("Error writing result of Ajax function '" + sFunctionName + "' with " + aAjaxExecutor, t);
    }
    else
    {
      s_aLogger.error ("Error invoking Ajax function '" + sFunctionName + "' on " + aAjaxExecutor, t);
      if (GlobalDebug.isDebugMode ())
        RequestLogger.logRequestComplete (aRequestScope.getRequest ());
    }
  }
}
