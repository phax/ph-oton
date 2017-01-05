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
package com.helger.photon.core.servlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.OverrideOnDemand;
import com.helger.commons.collection.ext.ICommonsList;
import com.helger.commons.lang.ServiceLoaderHelper;
import com.helger.photon.basic.app.request.RequestParameterManager;
import com.helger.photon.core.app.html.IHTMLProvider;
import com.helger.photon.core.app.html.PhotonHTMLHelper;
import com.helger.photon.core.spi.IApplicationRequestListenerSPI;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * Base servlet for the main application.
 *
 * @author Philip Helger
 */
public abstract class AbstractApplicationServlet extends AbstractUnifiedResponseServlet
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (AbstractApplicationServlet.class);

  private final ICommonsList <IApplicationRequestListenerSPI> m_aListeners;

  protected AbstractApplicationServlet ()
  {
    m_aListeners = ServiceLoaderHelper.getAllSPIImplementations (IApplicationRequestListenerSPI.class);
    if (!m_aListeners.isEmpty ())
      s_aLogger.info ("Loaded " + m_aListeners.size () + " IApplicationRequestListenerSPI implementations");
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onRequestBegin (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope)
  {
    // Run default request initialization (menu item and locale)
    RequestParameterManager.getInstance ().onRequestBegin (aRequestScope, getApplicationID ());

    // Invoke all "request begin" listener
    for (final IApplicationRequestListenerSPI aListener : m_aListeners)
      try
      {
        aListener.onRequestBegin (aRequestScope);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRequestBegin on " + aListener + " with request " + aRequestScope, t);
      }
  }

  @Override
  @OverrideOnDemand
  @OverridingMethodsMustInvokeSuper
  protected void onRequestEnd (final boolean bExceptionOccurred)
  {
    // Invoke all "request end" listener
    for (final IApplicationRequestListenerSPI aListener : m_aListeners)
      try
      {
        aListener.onRequestEnd (bExceptionOccurred);
      }
      catch (final Throwable t)
      {
        s_aLogger.error ("Failed to invoke onRequestEnd on " +
                         aListener +
                         "; exceptionOccured=" +
                         bExceptionOccurred,
                         t);
      }
  }

  /**
   * @param aRequestScope
   *        The request scope
   * @return The HTML provider that creates the content. May not be
   *         <code>null</code>.
   */
  @Nonnull
  protected abstract IHTMLProvider createHTMLProvider (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws IOException, ServletException
  {
    // Who is responsible for creating the HTML?
    final IHTMLProvider aHTMLProvider = createHTMLProvider (aRequestScope);

    // Create the HTML and put it into the response
    PhotonHTMLHelper.createHTMLResponse (aRequestScope, aUnifiedResponse, aHTMLProvider);
  }
}
