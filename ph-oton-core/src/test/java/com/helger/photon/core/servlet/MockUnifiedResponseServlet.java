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
package com.helger.photon.core.servlet;

import java.util.EnumSet;
import java.util.Set;

import javax.annotation.Nonnull;

import com.helger.commons.charset.CCharset;
import com.helger.commons.mime.CMimeType;
import com.helger.web.http.EHTTPMethod;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.web.scope.mock.MockServletRequestListenerScopeAware;
import com.helger.web.servlet.response.UnifiedResponse;

public final class MockUnifiedResponseServlet extends AbstractUnifiedResponseServlet
{
  public static final String RESPONSE_TEXT = "mock";

  @Override
  protected String getApplicationID ()
  {
    return MockServletRequestListenerScopeAware.MOCK_APPLICATION_ID;
  }

  @Override
  @Nonnull
  protected Set <EHTTPMethod> getAllowedHTTPMethods ()
  {
    return EnumSet.allOf (EHTTPMethod.class);
  }

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws Exception
  {
    aUnifiedResponse.setContentAndCharset (RESPONSE_TEXT, CCharset.CHARSET_UTF_8_OBJ)
                    .setMimeType (CMimeType.TEXT_PLAIN)
                    .disableCaching ();
  }
}
