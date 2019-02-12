package com.helger.photon.core.api;

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
