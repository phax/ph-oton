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
package com.helger.photon.uicore.html.google;

import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.ESuccess;
import com.helger.commons.string.StringHelper;
import com.helger.commons.url.SimpleURL;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.HttpClientSettings;
import com.helger.httpclient.response.ResponseHandlerJson;
import com.helger.json.IJson;

public final class ReCaptchaServerSideValidator
{
  private static final Logger LOGGER = LoggerFactory.getLogger (ReCaptchaServerSideValidator.class);

  private ReCaptchaServerSideValidator ()
  {}

  /**
   * Check if the response of a ReCaptcha is valid or not.
   *
   * @param sServerSideKey
   *        Server side ReCaptcha key for verification
   * @param sReCaptchaResponse
   *        The value of the <code>g-recaptcha-response</code> request
   *        parameter.
   * @return {@link ESuccess}
   */
  @Nonnull
  public static ESuccess check (@Nonnull @Nonempty final String sServerSideKey, @Nullable final String sReCaptchaResponse)
  {
    final HttpClientSettings aSettings = new HttpClientSettings ();
    // For proxy etc
    aSettings.setUseSystemProperties (true);
    return check (sServerSideKey, sReCaptchaResponse, aSettings);
  }

  /**
   * Check if the response of a ReCaptcha is valid or not.
   *
   * @param sServerSideKey
   *        Server side ReCaptcha key for verification
   * @param sReCaptchaResponse
   *        The value of the <code>g-recaptcha-response</code> request
   *        parameter.
   * @param aHCS
   *        The HTTP client settings. May not be <code>null</code>.
   * @return {@link ESuccess}
   */
  @Nonnull
  public static ESuccess check (@Nonnull @Nonempty final String sServerSideKey,
                                @Nullable final String sReCaptchaResponse,
                                @Nonnull final HttpClientSettings aHCS)
  {
    ValueEnforcer.notEmpty (sServerSideKey, "ServerSideKey");
    ValueEnforcer.notNull (aHCS, "HttpClientSettings");

    if (StringHelper.hasNoText (sReCaptchaResponse))
      return ESuccess.SUCCESS;

    try (HttpClientManager aMgr = HttpClientManager.create (aHCS))
    {
      final HttpPost aPost = new HttpPost (new SimpleURL ("https://www.google.com/recaptcha/api/siteverify").add ("secret", sServerSideKey)
                                                                                                            .add ("response",
                                                                                                                  sReCaptchaResponse)
                                                                                                            .getAsURI ());
      final ResponseHandlerJson aRH = new ResponseHandlerJson ();
      final IJson aJson = aMgr.execute (aPost, aRH);
      if (aJson != null && aJson.isObject ())
      {
        final boolean bSuccess = aJson.getAsObject ().getAsBoolean ("success", false);

        if (GlobalDebug.isDebugMode ())
          LOGGER.info ("ReCpatcha Response for '" + sReCaptchaResponse + "': " + aJson.getAsJsonString ());

        return ESuccess.valueOf (bSuccess);
      }
    }
    catch (final IOException ex)
    {
      LOGGER.warn ("Error checking ReCaptcha response", ex);
    }
    return ESuccess.FAILURE;
  }
}
