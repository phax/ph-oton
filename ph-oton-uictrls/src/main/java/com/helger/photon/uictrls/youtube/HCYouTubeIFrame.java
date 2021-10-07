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
package com.helger.photon.uictrls.youtube;

import java.util.Locale;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.url.ISimpleURL;
import com.helger.commons.url.SimpleURL;
import com.helger.html.hc.html.embedded.AbstractHCIFrame;

/**
 * Embeds YouTube videos as an IFrame<br>
 * Example code: <code>new HCYouTubeIFrame (640, 385, "93RkWNK3BZc")</code>
 *
 * @author Philip Helger
 */
public class HCYouTubeIFrame extends AbstractHCIFrame <HCYouTubeIFrame>
{
  private static final String PREFIX = "http://www.youtube.com/embed/";

  private final SimpleURL m_aVideoURL;

  public HCYouTubeIFrame (@Nonnull @Nonempty final String sVideoID)
  {
    m_aVideoURL = new SimpleURL (PREFIX + sVideoID);
    setSrc (m_aVideoURL);
  }

  public HCYouTubeIFrame (final int nWidth, final int nHeight, @Nonnull @Nonempty final String sVideoID)
  {
    this (sVideoID);
    setWidth (nWidth);
    setHeight (nHeight);
  }

  @Nonnull
  public ISimpleURL getVideoURL ()
  {
    return m_aVideoURL;
  }

  @Nonnull
  public HCYouTubeIFrame setAutoPlay (final boolean bAutoplay)
  {
    m_aVideoURL.params ().remove ("autoplay");
    m_aVideoURL.params ().add ("autoplay", bAutoplay ? "1" : "0");
    setSrc (m_aVideoURL);
    return this;
  }

  @Nonnull
  public HCYouTubeIFrame setAllowFullscreen (final boolean bAllowFullscreen)
  {
    m_aVideoURL.params ().remove ("fs");
    m_aVideoURL.params ().add ("fs", bAllowFullscreen ? "1" : "0");
    setSrc (m_aVideoURL);
    return this;
  }

  @Nonnull
  public HCYouTubeIFrame setShowRelated (final boolean bShowRelated)
  {
    m_aVideoURL.params ().remove ("rel");
    m_aVideoURL.params ().add ("rel", bShowRelated ? "1" : "0");
    setSrc (m_aVideoURL);
    return this;
  }

  @Nonnull
  public HCYouTubeIFrame setLanguage (@Nonnull final Locale aLocale)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    ValueEnforcer.notEmpty (aLocale.getLanguage (), "Locale.getLanguage");

    m_aVideoURL.params ().remove ("hl");
    m_aVideoURL.add ("hl", aLocale.getLanguage ());
    setSrc (m_aVideoURL);
    return this;
  }
}
