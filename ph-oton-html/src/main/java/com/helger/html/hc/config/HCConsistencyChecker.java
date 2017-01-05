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
package com.helger.html.hc.config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotation.PresentForCodeCoverage;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.URLHelper;
import com.helger.commons.url.URLParameterDecoder;

/**
 * This class performs some consistency checks on HCNodes
 *
 * @author Philip Helger
 */
@Immutable
public final class HCConsistencyChecker
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (HCConsistencyChecker.class);

  private static final int MAX_CSS_IE = 31;

  @PresentForCodeCoverage
  private static final HCConsistencyChecker s_aInstance = new HCConsistencyChecker ();

  private HCConsistencyChecker ()
  {}

  public static void consistencyError (@Nonnull final String sMsg)
  {
    if (GlobalDebug.isDebugMode ())
      throw new IllegalStateException ("HC Consistency check error: " + sMsg);
    // In production emit only an error
    s_aLogger.error ("HC Consistency check error: " + sMsg);
  }

  public static void checkIfStringURLIsEscaped (@Nullable final String sHref)
  {
    if (StringHelper.hasText (sHref))
    {
      // This is potential vulnerability. If the passed href is passed
      // from a user input, which cannot be told at this point, it might as well
      // contain a'&amp;' followed by some malicious code that should be
      // escaped.
      // Note PH: this is not a vulnerability. This is a programming error!
      if (sHref.contains ("&amp;"))
        consistencyError ("The URL '" + sHref + "' seems to be already XML escaped - please use an unescaped URL!!");

      // Check if string is URL escaped
      final URLParameterDecoder aDecoder = new URLParameterDecoder (URLHelper.CHARSET_URL_OBJ);
      final String sDecoded = aDecoder.getDecoded (sHref);
      if (!sHref.equals (sDecoded))
        consistencyError ("The URL '" + sHref + "' seems to be already URL encoded - please use a decoded URL!!");
    }
  }

  public static void checkForMaximumCSSResources (final int nCSSExternals)
  {
    // Sources:
    // http://acidmartin.wordpress.com/2008/11/25/the-32-external-css-files-limitation-of-internet-explorer-and-more/
    // http://social.msdn.microsoft.com/Forums/en-US/iewebdevelopment/thread/ad1b6e88-bbfa-4cc4-9e95-3889b82a7c1d
    if (nCSSExternals > MAX_CSS_IE)
      consistencyError ("You are including more than 31 CSS files (" +
                        nCSSExternals +
                        ") in your request, which will be ignored by Internet Explorer (at least up to version 8)!");
  }
}
