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
package com.helger.photon.uictrls.youtube;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.embedded.AbstractHCObject;
import com.helger.html.hc.html.embedded.HCEmbed;
import com.helger.html.hc.html.embedded.HCParam;

/**
 * Embeds YouTube videos!<br>
 * Example code: <code>new YouTubeEmbed (640, 385, "93RkWNK3BZc")</code>
 *
 * @author Philip Helger
 */
public class HCYouTubeEmbed extends AbstractHCObject <HCYouTubeEmbed>
{
  private static final String PREFIX = "http://www.youtube.com/v/";

  public HCYouTubeEmbed (final int nWidth,
                         final int nHeight,
                         @Nonnull @Nonempty final String sVideoID,
                         final boolean bAllowFullScreen)
  {
    final ISimpleURL aBaseURL = new SimpleURL (PREFIX + sVideoID);
    setWidth (nWidth);
    setHeight (nHeight);
    final HCParam aParamMovie = addAndReturnChild (new HCParam ("movie"));
    final HCParam aParamAllowFullScreen = addAndReturnChild (new HCParam ("allowFullScreen"));
    aParamAllowFullScreen.setValue (Boolean.toString (bAllowFullScreen));
    addChild (new HCParam ("allowscriptaccess").setValue ("always"));
    final HCEmbed aEmbed = addAndReturnChild (new HCEmbed ());
    aEmbed.setType (CMimeType.APPLICATION_SHOCKWAVE_FLASH);
    aEmbed.setCustomAttr ("allowscriptaccess", "always");
    aEmbed.setWidth (nWidth);
    aEmbed.setHeight (nHeight);
    aEmbed.setCustomAttr ("allowfullscreen", Boolean.toString (bAllowFullScreen));

    // Build the correct URL based on the passed settings
    final ISimpleURL aURL = new SimpleURL (aBaseURL).add ("hl", "en_US").add ("fs", bAllowFullScreen ? "1" : "0");
    aParamMovie.setValue (aURL.getAsStringWithEncodedParameters ());
    aEmbed.setSrc (aURL);
  }
}
