package com.helger.photon.uicore.html.google;

import java.io.IOException;
import java.io.UncheckedIOException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.attr.StringMap;
import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.state.ESuccess;
import com.helger.httpclient.HttpClientFactory;
import com.helger.httpclient.HttpClientHelper;
import com.helger.httpclient.HttpClientManager;
import com.helger.httpclient.response.ResponseHandlerJson;
import com.helger.json.IJson;

public class ReCaptchaServerSideValidator
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ReCaptchaServerSideValidator.class);

  private ReCaptchaServerSideValidator ()
  {}

  /**
   * Check if the response of a RecCaptcha is valid or not.
   *
   * @param sServerSideKey
   *        Server side ReCaptcha key for verification
   * @param sReCaptchaResponse
   *        The value of the <code>g-recaptcha-response</code> request
   *        parameter.
   * @return {@link ESuccess}
   */
  @Nonnull
  public static ESuccess check (@Nonnull @Nonempty final String sServerSideKey,
                                @Nullable final String sReCaptchaResponse)
  {
    ValueEnforcer.notEmpty (sServerSideKey, "ServerSideKey");

    final HttpClientFactory aHCFactory = new HttpClientFactory ();
    // For proxy etc

    try (HttpClientManager aMgr = new HttpClientManager (aHCFactory))
    {
      final HttpPost aPost = new HttpPost ("https://www.google.com/recaptcha/api/siteverify");
      aPost.setEntity (HttpClientHelper.createParameterEntity (new StringMap ().add ("secret", sServerSideKey)
                                                                               .add ("response", sReCaptchaResponse)));
      final ResponseHandlerJson aRH = new ResponseHandlerJson ();
      final IJson aJson = aMgr.execute (aPost, aRH);
      if (aJson != null && aJson.isObject ())
      {
        final boolean bSuccess = aJson.getAsObject ().getAsBoolean ("success", false);

        if (GlobalDebug.isDebugMode ())
          s_aLogger.info ("ReCpatcha Response: " + aJson.getAsJsonString ());

        return ESuccess.valueOf (bSuccess);
      }
      return ESuccess.FAILURE;
    }
    catch (final IOException ex)
    {
      throw new UncheckedIOException (ex);
    }
  }
}
