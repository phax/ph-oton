/*
 * Copyright (C) 2014-2023 Philip Helger (www.helger.com)
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
package com.helger.photon.uicore.html.twitter;

import javax.annotation.Nonnull;

import com.helger.commons.debug.GlobalDebug;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.css.DefaultCSSClassProvider;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.textlevel.AbstractHCA;
import com.helger.html.resource.js.ConstantJSPathProvider;
import com.helger.photon.app.html.PhotonJS;

/**
 * <pre>
 * &lt;a href="https://twitter.com/share" class="twitter-share-button" data-show-count="false"&gt;Tweet&lt;/a&gt;
 * &lt;script async src="//platform.twitter.com/widgets.js" charset="utf-8"&gt;&lt;/script&gt;
 * </pre>
 *
 * @author Philip Helger
 */
public class HCTweet extends AbstractHCA <HCTweet>
{
  public static final ICSSClassProvider CSS_TWITTER_SHARE_BUTTON = DefaultCSSClassProvider.create ("twitter-share-button");
  public static final ICSSClassProvider CSS_TWITTER_HASHTAG_BUTTON = DefaultCSSClassProvider.create ("twitter-hashtag-button");

  public static final ISimpleURL URL_SHARE = new SimpleURL ("https://twitter.com/share");
  public static final ISimpleURL URL_TWEET = new SimpleURL ("https://twitter.com/intent/tweet");

  public HCTweet ()
  {}

  @Override
  public boolean canConvertToMicroNode (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    // Render "tweet" nodes only in production mode
    return GlobalDebug.isProductionMode ();
  }

  @Override
  protected void onRegisterExternalResources (@Nonnull final IHCConversionSettingsToNode aConversionSettings,
                                              final boolean bForceRegistration)
  {
    super.onRegisterExternalResources (aConversionSettings, bForceRegistration);
    PhotonJS.registerJSIncludeForThisRequest (ConstantJSPathProvider.createExternal ("https://platform.twitter.com/widgets.js"));
  }

  @Nonnull
  public static HCTweet createShareButton ()
  {
    final HCTweet ret = new HCTweet ().addClass (CSS_TWITTER_SHARE_BUTTON).setHref (URL_SHARE).addChild ("Tweet");
    ret.customAttrs ().setDataAttr ("show-count", "false");
    return ret;
  }
}
