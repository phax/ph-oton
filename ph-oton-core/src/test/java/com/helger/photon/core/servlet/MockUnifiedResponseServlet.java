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
package com.helger.photon.core.servlet;

import java.nio.charset.StandardCharsets;

import com.helger.commons.http.EHttpMethod;
import com.helger.commons.mime.CMimeType;
import com.helger.xservlet.AbstractXServlet;

public final class MockUnifiedResponseServlet extends AbstractXServlet
{
  public static final String RESPONSE_TEXT = "mock";

  public MockUnifiedResponseServlet ()
  {
    handlerRegistry ().registerHandler (EHttpMethod.GET,
                                        (aRequestScope,
                                         aUnifiedResponse) -> aUnifiedResponse.setContentAndCharset (RESPONSE_TEXT,
                                                                                                     StandardCharsets.UTF_8)
                                                                              .setMimeType (CMimeType.TEXT_PLAIN)
                                                                              .disableCaching ());
    handlerRegistry ().copyHandlerToAll (EHttpMethod.GET);
  }
}
