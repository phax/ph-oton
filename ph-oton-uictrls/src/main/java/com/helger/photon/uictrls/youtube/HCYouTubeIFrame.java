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
package com.helger.photon.uictrls.youtube;

import java.util.Locale;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.Nonempty;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.embedded.AbstractHCIFrame;
import com.helger.url.ISimpleURL;
import com.helger.url.URLBuilder;

/**
 * Embeds YouTube videos as an IFrame<br>
 * Example code: <code>new HCYouTubeIFrame (640, 385, "93RkWNK3BZc")</code>
 *
 * @author Philip Helger
 */
public class HCYouTubeIFrame extends AbstractHCIFrame <HCYouTubeIFrame>
{
  private static final String PREFIX = "http://www.youtube.com/embed/";

  private final URLBuilder m_aVideoURL;

  public HCYouTubeIFrame (@NonNull @Nonempty final String sVideoID)
  {
    m_aVideoURL = URLBuilder.of (PREFIX + sVideoID);
  }

  public HCYouTubeIFrame (final int nWidth, final int nHeight, @NonNull @Nonempty final String sVideoID)
  {
    this (sVideoID);
    setWidth (nWidth);
    setHeight (nHeight);
  }

  @NonNull
  public ISimpleURL getVideoURL ()
  {
    return m_aVideoURL.build ();
  }

  @NonNull
  public HCYouTubeIFrame setAutoPlay (final boolean bAutoplay)
  {
    m_aVideoURL.param ("autoplay", bAutoplay ? "1" : "0");
    return this;
  }

  @NonNull
  public HCYouTubeIFrame setAllowFullscreen (final boolean bAllowFullscreen)
  {
    m_aVideoURL.param ("fs", bAllowFullscreen ? "1" : "0");
    return this;
  }

  @NonNull
  public HCYouTubeIFrame setShowRelated (final boolean bShowRelated)
  {
    m_aVideoURL.param ("rel", bShowRelated ? "1" : "0");
    return this;
  }

  @NonNull
  public HCYouTubeIFrame setLanguage (@NonNull final Locale aLocale)
  {
    ValueEnforcer.notNull (aLocale, "Locale");
    ValueEnforcer.notEmpty (aLocale.getLanguage (), "Locale.getLanguage");

    m_aVideoURL.param ("hl", aLocale.getLanguage ());
    return this;
  }

  @Override
  protected void onFinalizeNodeState (final IHCConversionSettingsToNode aConversionSettings,
                                      final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    setSrc (m_aVideoURL.build ());
  }
}
