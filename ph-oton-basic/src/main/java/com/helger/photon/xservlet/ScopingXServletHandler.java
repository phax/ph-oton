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
package com.helger.photon.xservlet;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.string.ToStringGenerator;
import com.helger.http.EHTTPMethod;
import com.helger.http.EHTTPVersion;
import com.helger.web.scope.IRequestWebScope;
import com.helger.web.scope.request.RequestScopeInitializer;

public final class ScopingXServletHandler implements IXServletHandler
{
  private final String m_sApplicationID;
  private final IScopedXServletHandler m_aNestedHandler;

  public ScopingXServletHandler (@Nonnull @Nonempty final String sApplicationID,
                                 @Nonnull final IScopedXServletHandler aNestedHandler)
  {
    m_sApplicationID = ValueEnforcer.notEmpty (sApplicationID, "ApplicationID");
    m_aNestedHandler = ValueEnforcer.notNull (aNestedHandler, "NestedHandler");
  }

  public void handle (@Nonnull final HttpServletRequest aHttpRequest,
                      @Nonnull final HttpServletResponse aHttpResponse,
                      @Nonnull final EHTTPVersion eHttpVersion,
                      @Nonnull final EHTTPMethod eHttpMethod) throws ServletException, IOException
  {
    final RequestScopeInitializer aRequestScopeInitializer = RequestScopeInitializer.create (m_sApplicationID,
                                                                                             aHttpRequest,
                                                                                             aHttpResponse);
    try
    {
      // Pass to original handler
      final IRequestWebScope aRequestScope = aRequestScopeInitializer.getRequestScope ();
      m_aNestedHandler.handle (aHttpRequest, aHttpResponse, eHttpVersion, eHttpMethod, aRequestScope);
    }
    finally
    {
      aRequestScopeInitializer.destroyScope ();
    }
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ApplicationID", m_sApplicationID)
                                       .append ("NestedHandler", m_aNestedHandler)
                                       .getToString ();
  }
}
