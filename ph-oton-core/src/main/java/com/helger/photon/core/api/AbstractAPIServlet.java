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
package com.helger.photon.core.api;

import java.io.IOException;
import java.util.EnumSet;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.ReturnsMutableObject;
import com.helger.commons.callback.CallbackList;
import com.helger.commons.io.file.FilenameHelper;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedCounter;
import com.helger.commons.statistics.IMutableStatisticsHandlerKeyedTimer;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.commons.timing.StopWatch;
import com.helger.photon.core.servlet.AbstractUnifiedResponseServlet;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * Abstract API servlet. Use {@link ApplicationAPIManager} to register API
 * functions dynamically.
 *
 * @author Philip Helger
 */
public abstract class AbstractAPIServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractAPIServlet.class);
  private static final EnumSet <EHTTPMethod> ALL = EnumSet.allOf (EHTTPMethod.class);
  private static final IMutableStatisticsHandlerKeyedTimer s_aStatsTimer = StatisticsManager.getKeyedTimerHandler (AbstractAPIServlet.class);
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterSuccess = StatisticsManager.getKeyedCounterHandler (AbstractAPIServlet.class +
                                                                                                                                "$success");
  private static final IMutableStatisticsHandlerKeyedCounter s_aStatsCounterError = StatisticsManager.getKeyedCounterHandler (AbstractAPIServlet.class +
                                                                                                                              "$error");

  private static final CallbackList <IAPIExceptionCallback> s_aExceptionCallbacks = new CallbackList <> ();

  /**
   * @return The callback list with the exception handlers
   */
  @Nonnull
  @ReturnsMutableObject ("design")
  public static CallbackList <IAPIExceptionCallback> getExceptionCallbacks ()
  {
    return s_aExceptionCallbacks;
  }

  @Override
  @Nonnull
  protected EnumSet <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return ALL;
  }

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    // ensure leading "/"
    final String sAPIPath = FilenameHelper.ensurePathStartingWithSeparator (aRequestScope.getPathWithinServlet ());
    final EHTTPMethod eHTTPMethod = aRequestScope.getHttpMethod ();
    final InvokableAPIDescriptor aInvokableDescriptor = ApplicationAPIManager.getInstance ().getAPIByPath (sAPIPath,
                                                                                                           eHTTPMethod);
    if (aInvokableDescriptor == null)
    {
      s_aLogger.warn ("Unknown API " + eHTTPMethod + " '" + sAPIPath + "' requested!");

      // No such action
      aUnifiedResponse.setStatus (HttpServletResponse.SC_NOT_FOUND);
    }
    else
    {
      // Is the declaration applicable for the current scope?
      // Check for required headers and parameters
      if (!aInvokableDescriptor.canExecute (aRequestScope))
      {
        s_aLogger.warn ("API " + eHTTPMethod + " '" + sAPIPath + "' cannot be executed for the current request.");
        aUnifiedResponse.setStatus (HttpServletResponse.SC_UNAUTHORIZED);
      }
      else
      {
        // Don't cache the result. The executor may overwrite it
        aUnifiedResponse.disableCaching ();

        try
        {
          // Start the timing
          final StopWatch aSW = StopWatch.createdStarted ();

          // Main API invocation
          aInvokableDescriptor.invokeAPI (aRequestScope, aUnifiedResponse);

          // Remember the time
          s_aStatsTimer.addTime (sAPIPath, aSW.stopAndGetMillis ());
          s_aStatsCounterSuccess.increment (sAPIPath);
        }
        catch (final Throwable t)
        {
          s_aStatsCounterError.increment (sAPIPath);

          // Notify custom exception handler
          for (final IAPIExceptionCallback aExceptionCallback : getExceptionCallbacks ().getAllCallbacks ())
            try
            {
              aExceptionCallback.onAPIExecutionException (aInvokableDescriptor, aRequestScope, t);
            }
            catch (final Throwable t2)
            {
              s_aLogger.error ("Exception in custom API exception handler of API " +
                               eHTTPMethod +
                               " '" +
                               sAPIPath +
                               "'",
                               t2);
            }

          // Re-throw
          if (t instanceof IOException)
            throw (IOException) t;
          if (t instanceof ServletException)
            throw (ServletException) t;
          throw new ServletException ("Error invoking API " + eHTTPMethod + " '" + sAPIPath + "'", t);
        }
      }
    }
  }
}
