/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.photon.core.servlet;

import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.annotation.style.OverrideOnDemand;
import com.helger.base.debug.GlobalDebug;
import com.helger.base.state.EContinue;
import com.helger.base.timing.StopWatch;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.app.html.PhotonHTMLHelper;
import com.helger.photon.core.CCoreTelemetry;
import com.helger.photon.core.interror.InternalErrorBuilder;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.telemetry.ETelemetrySpanKind;
import com.helger.telemetry.ITelemetrySpan;
import com.helger.telemetry.Telemetry;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.forcedredirect.ForcedRedirectException;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

/**
 * Base XServlet handler for the main application.
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationXServletHandler implements IXServletSimpleHandler
{
  private static final Logger LOGGER = LoggerFactory.getLogger (AbstractApplicationXServletHandler.class);

  protected AbstractApplicationXServletHandler ()
  {}

  @OverrideOnDemand
  protected void invokeInternalErrorHandler (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                             @NonNull final Throwable t)
  {
    // Send internal error mail if needed
    new InternalErrorBuilder ().setThrowable (t)
                               .setRequestScope (aRequestScope)
                               .addErrorMessage ("Error running application servlet " + getClass ().getSimpleName ())
                               .handle ();
  }

  @NonNull
  @Override
  public EContinue onException (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                                @NonNull final UnifiedResponse aUnifiedResponse,
                                @NonNull final Throwable t)
  {
    if (!GlobalDebug.isDebugMode ())
    {
      // Send internal error mail if needed
      invokeInternalErrorHandler (aRequestScope, t);

      // Do not propagate
      return EContinue.BREAK;
    }

    // Propagate only in debug mode
    return EContinue.CONTINUE;
  }

  /**
   * @param aRequestScope
   *        The request scope
   * @return The HTML provider that creates the content. May not be <code>null</code>.
   */
  @NonNull
  protected abstract IHTMLProvider createHTMLProvider (@NonNull final IRequestWebScopeWithoutResponse aRequestScope);

  @OverridingMethodsMustInvokeSuper
  public void handleRequest (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                             @NonNull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Start handleRequest");

    final StopWatch aSW = StopWatch.createdStarted ();
    boolean bSuccess = false;
    // A ForcedRedirectException must not mark the span as failed, so the span is managed manually
    // instead of using one of the Telemetry.withSpan* helpers
    try (final ITelemetrySpan aSpan = Telemetry.startSpan (CCoreTelemetry.SPAN_PAGE_REQUEST,
                                                           ETelemetrySpanKind.SERVER))
    {
      try
      {
        // Who is responsible for creating the HTML?
        final IHTMLProvider aHTMLProvider = createHTMLProvider (aRequestScope);

        // Create the HTML and put it into the response
        PhotonHTMLHelper.createHTMLResponse (aRequestScope, aUnifiedResponse, aHTMLProvider);
        bSuccess = true;
        PageRequestTelemetry.onPageRequestSuccess (aSpan);
      }
      catch (final ForcedRedirectException ex)
      {
        // Post-Redirect-Get is a regular control flow and no error
        bSuccess = true;
        PageRequestTelemetry.onPageRequestRedirect (aSpan);

        // Re-throw
        throw ex;
      }
      catch (final Exception ex)
      {
        PageRequestTelemetry.onPageRequestError (aSpan, ex);

        // Re-throw
        throw ex;
      }
    }
    finally
    {
      PageRequestTelemetry.onPageRequestEnd (bSuccess, aSW.stopAndGetMillis ());

      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("End handleRequest");
    }
  }
}
