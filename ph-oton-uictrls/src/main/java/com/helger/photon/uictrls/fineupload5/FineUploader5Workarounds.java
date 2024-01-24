/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.photon.uictrls.fineupload5;

import javax.annotation.Nonnull;

import com.helger.html.jscode.JSAssocArray;

/**
 * Wrapper for Fine Uploader 5.x cors part
 *
 * @author Philip Helger
 */
public class FineUploader5Workarounds implements IFineUploader5Part
{
  public static final boolean DEFAULT_WORKAROUNDS = true;

  private boolean m_bWorkaroundsIosEmptyVideo = DEFAULT_WORKAROUNDS;
  private boolean m_bWorkaroundsIos8BrowserCrash = DEFAULT_WORKAROUNDS;
  private boolean m_bWorkaroundsIos8SafariUploads = DEFAULT_WORKAROUNDS;

  public FineUploader5Workarounds ()
  {}

  public boolean isIosEmptyVideo ()
  {
    return m_bWorkaroundsIosEmptyVideo;
  }

  /**
   * Ensures all &lt;input type='file'&gt; elements tracked by Fine Uploader do
   * NOT contain a multiple attribute to work around an issue present in iOS7
   * &amp; 8 that otherwise results in 0-sized uploaded videos.
   *
   * @param bIosEmptyVideo
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Workarounds setIosEmptyVideo (final boolean bIosEmptyVideo)
  {
    m_bWorkaroundsIosEmptyVideo = bIosEmptyVideo;
    return this;
  }

  public boolean isIos8BrowserCrash ()
  {
    return m_bWorkaroundsIos8BrowserCrash;
  }

  /**
   * Ensures all &lt;input type='file'&gt; elements tracked by Fine Uploader
   * always have a multiple attribute present. This only applies to iOS8 Chrome
   * and iOS8 UIWebView, and is put in place to work around an issue that causes
   * the browser to crash when a file input element does not contain a multiple
   * attribute inside of a UIWebView container created by an iOS8 app compiled
   * with and iOS7 SDK.
   *
   * @param bIos8BrowserCrash
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Workarounds setIos8BrowserCrash (final boolean bIos8BrowserCrash)
  {
    m_bWorkaroundsIos8BrowserCrash = bIos8BrowserCrash;
    return this;
  }

  public boolean isIos8SafariUploads ()
  {
    return m_bWorkaroundsIos8SafariUploads;
  }

  /**
   * Disables Fine Uploader and displays a message to the user in iOS 8.0.0
   * Safari. Due to serious bugs in iOS 8.0.0 Safari, uploading is not possible.
   * This was apparently fixed in subsequent builds of iOS8, so this workaround
   * only targets 8.0.0.
   *
   * @param bIos8SafariUploads
   *        New value
   * @return this for chaining
   */
  @Nonnull
  public FineUploader5Workarounds setIos8SafariUploads (final boolean bIos8SafariUploads)
  {
    m_bWorkaroundsIos8SafariUploads = bIos8SafariUploads;
    return this;
  }

  @Nonnull
  public JSAssocArray getJSCode ()
  {
    final JSAssocArray aSub = new JSAssocArray ();

    if (m_bWorkaroundsIosEmptyVideo != DEFAULT_WORKAROUNDS)
      aSub.add ("iosEmptyVideos", m_bWorkaroundsIosEmptyVideo);
    if (m_bWorkaroundsIos8BrowserCrash != DEFAULT_WORKAROUNDS)
      aSub.add ("ios8BrowserCrash", m_bWorkaroundsIos8BrowserCrash);
    if (m_bWorkaroundsIos8SafariUploads != DEFAULT_WORKAROUNDS)
      aSub.add ("ios8SafariUploads", m_bWorkaroundsIos8SafariUploads);

    return aSub;
  }
}
