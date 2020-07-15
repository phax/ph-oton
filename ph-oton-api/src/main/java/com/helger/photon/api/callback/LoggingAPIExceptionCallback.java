/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.api.callback;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.io.stream.StreamHelper;
import com.helger.photon.api.IAPIExceptionCallback;
import com.helger.photon.api.IAPIInvoker;
import com.helger.photon.api.InvokableAPIDescriptor;
import com.helger.servlet.request.RequestLogger;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Implementation of {@link IAPIExceptionCallback} logging to an SLF4J logger
 *
 * @author Philip Helger
 */
public class LoggingAPIExceptionCallback implements IAPIExceptionCallback
{
  private static final Logger LOGGER = LoggerFactory.getLogger (LoggingAPIExceptionCallback.class);

  public void onAPIExecutionException (@Nonnull final IAPIInvoker aInvoker,
                                       @Nonnull final InvokableAPIDescriptor aInvokableDescriptor,
                                       @Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                       @Nonnull final Throwable t)
  {
    if (t instanceof IOException)
    {
      if (!StreamHelper.isKnownEOFException (t))
        LOGGER.error ("Error writing result of API '" + aInvokableDescriptor.getPath () + "' with " + aInvokableDescriptor, t);
    }
    else
    {
      LOGGER.error ("Error invoking API '" + aInvokableDescriptor.getPath () + "' on " + aInvokableDescriptor, t);
      if (GlobalDebug.isDebugMode ())
        RequestLogger.logRequestComplete (aRequestScope.getRequest ());
    }
  }
}
