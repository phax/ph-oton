/*
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
package com.helger.photon.api;

import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.lang.StackTraceHelper;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.servlet.response.UnifiedResponse;

/**
 * Abstract implementation of {@link IAPIExceptionMapper} with some helper
 * methods.
 *
 * @author Philip Helger
 * @since 8.1.3
 */
public abstract class AbstractAPIExceptionMapper implements IAPIExceptionMapper
{
  @Nonnull
  public static String getResponseEntityWithoutStackTrace (@Nonnull final Throwable ex)
  {
    // The class name does not really matter
    return ex.getMessage ();
  }

  @Nonnull
  public static String getResponseEntityWithStackTrace (@Nonnull final Throwable ex)
  {
    // Includes class name and message
    return StackTraceHelper.getStackAsString (ex);
  }

  protected static void setSimpleTextResponse (@Nonnull final UnifiedResponse aUnifiedResponse,
                                               final int nStatusCode,
                                               @Nullable final String sContent)
  {
    aUnifiedResponse.setStatus (nStatusCode);
    if (StringHelper.hasText (sContent))
    {
      aUnifiedResponse.setAllowContentOnStatusCode (true)
                      .setContentAndCharset (sContent, StandardCharsets.UTF_8)
                      .setMimeType (CMimeType.TEXT_PLAIN);
    }
  }
}
