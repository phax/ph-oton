/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.EContinue;
import com.helger.photon.app.html.IHTMLProvider;
import com.helger.photon.app.html.PhotonHTMLHelper;
import com.helger.photon.core.interror.InternalErrorBuilder;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
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
  protected void invokeInternalErrorHandler (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope, @Nonnull final Throwable t)
  {
    // Send internal error mail if needed
    new InternalErrorBuilder ().setThrowable (t)
                               .setRequestScope (aRequestScope)
                               .addErrorMessage ("Error running application servlet " + getClass ().getSimpleName ())
                               .handle ();
  }

  @Nonnull
  @Override
  public EContinue onException (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse,
                                @Nonnull final Throwable t)
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
   * @return The HTML provider that creates the content. May not be
   *         <code>null</code>.
   */
  @Nonnull
  protected abstract IHTMLProvider createHTMLProvider (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope);

  @OverridingMethodsMustInvokeSuper
  public void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                             @Nonnull final UnifiedResponse aUnifiedResponse) throws IOException, ServletException
  {
    if (LOGGER.isDebugEnabled ())
      LOGGER.debug ("Start handleRequest");

    try
    {
      // Who is responsible for creating the HTML?
      final IHTMLProvider aHTMLProvider = createHTMLProvider (aRequestScope);

      // Create the HTML and put it into the response
      PhotonHTMLHelper.createHTMLResponse (aRequestScope, aUnifiedResponse, aHTMLProvider);
    }
    finally
    {
      if (LOGGER.isDebugEnabled ())
        LOGGER.debug ("End handleRequest");
    }
  }
}
