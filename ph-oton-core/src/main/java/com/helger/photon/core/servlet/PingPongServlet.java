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

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;

import com.helger.commons.charset.CCharset;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.statistics.IMutableStatisticsHandlerCounter;
import com.helger.commons.statistics.StatisticsManager;
import com.helger.photon.core.servletstatus.ServletStatusManager;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;

/**
 * A simple availability-check servlet that responds with a "pong" text message.
 * Usually this servlet should be called "ping".
 *
 * @author Philip Helger
 */
public final class PingPongServlet extends AbstractUnifiedResponseServlet
{
  public static final String SERVLET_DEFAULT_NAME = "ping";
  public static final String SERVLET_DEFAULT_PATH = "/" + SERVLET_DEFAULT_NAME;

  /** The response string to send. */
  public static final String RESPONSE_TEXT = "pong";

  /** The response charset */
  public static final Charset RESPONSE_CHARSET = CCharset.CHARSET_ISO_8859_1_OBJ;

  /** The response MIME type */
  public static final IMimeType RESPONSE_MIMETYPE = CMimeType.TEXT_PLAIN;

  private static final IMutableStatisticsHandlerCounter s_aStatsPingPong = StatisticsManager.getCounterHandler (PingPongServlet.class);

  private static final boolean s_bIsRegistered = ServletStatusManager.isServletRegistered (PingPongServlet.class);

  public PingPongServlet ()
  {}

  public static boolean isServletRegisteredInServletContext ()
  {
    return s_bIsRegistered;
  }

  @Override
  protected void handleRequest (@Nonnull final IRequestWebScopeWithoutResponse aRequestScope,
                                @Nonnull final UnifiedResponse aUnifiedResponse) throws ServletException
  {
    aUnifiedResponse.setContentAndCharset (RESPONSE_TEXT, RESPONSE_CHARSET)
                    .setMimeType (RESPONSE_MIMETYPE)
                    .disableCaching ();
    s_aStatsPingPong.increment ();
  }
}
