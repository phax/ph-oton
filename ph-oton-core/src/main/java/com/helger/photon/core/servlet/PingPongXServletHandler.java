/*
 * Copyright (C) 2014-2025 Philip Helger (www.helger.com)
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
import java.nio.charset.StandardCharsets;

import org.jspecify.annotations.NonNull;

import com.helger.mime.CMimeType;
import com.helger.mime.IMimeType;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.statistics.api.IMutableStatisticsHandlerCounter;
import com.helger.statistics.impl.StatisticsManager;
import com.helger.web.scope.IRequestWebScopeWithoutResponse;
import com.helger.xservlet.handler.simple.IXServletSimpleHandler;

import jakarta.servlet.ServletException;

/**
 * A simple availability-check servlet that responds with a "pong" text message.
 * Usually this servlet should be called "ping".
 *
 * @author Philip Helger
 */
public class PingPongXServletHandler implements IXServletSimpleHandler
{
  /** The response string to send. */
  public static final String RESPONSE_TEXT = "pong";

  /** The response charset */
  public static final Charset RESPONSE_CHARSET = StandardCharsets.ISO_8859_1;

  /** The response MIME type */
  public static final IMimeType RESPONSE_MIMETYPE = CMimeType.TEXT_PLAIN;

  private static final IMutableStatisticsHandlerCounter STATS_PING_PONG = StatisticsManager.getCounterHandler (PingPongXServletHandler.class);

  public PingPongXServletHandler ()
  {}

  @Override
  public void handleRequest (@NonNull final IRequestWebScopeWithoutResponse aRequestScope,
                             @NonNull final UnifiedResponse aUnifiedResponse) throws ServletException
  {
    aUnifiedResponse.setContentAndCharset (RESPONSE_TEXT, RESPONSE_CHARSET).setMimeType (RESPONSE_MIMETYPE).disableCaching ();
    STATS_PING_PONG.increment ();
  }
}
