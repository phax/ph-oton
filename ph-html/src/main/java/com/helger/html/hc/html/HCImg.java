/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.gfx.ScalableSize;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.conversion.HCConsistencyChecker;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;img&gt; element
 *
 * @author Philip Helger
 */
public class HCImg extends AbstractHCElement <HCImg>
{
  // Must be a String to allow for inline images
  private String m_sSrc;
  private ScalableSize m_aExtent;
  private String m_sAlt;

  public HCImg ()
  {
    super (EHTMLElement.IMG);
  }

  @Nullable
  public final String getSrc ()
  {
    return m_sSrc;
  }

  @Nonnull
  public HCImg setSrc (@Nullable final ISimpleURL aSrc)
  {
    setSrc (aSrc == null ? null : aSrc.getAsString ());
    return this;
  }

  @Nonnull
  public HCImg setSrc (@Nullable final String sSrc)
  {
    HCConsistencyChecker.checkIfLinkIsMasked (sSrc);
    m_sSrc = sSrc;
    return this;
  }

  public final boolean hasExtent ()
  {
    return m_aExtent != null;
  }

  public final int getWidth (final int nDefaultValue)
  {
    return m_aExtent != null ? m_aExtent.getWidth () : nDefaultValue;
  }

  public final int getHeight (final int nDefaultValue)
  {
    return m_aExtent != null ? m_aExtent.getHeight () : nDefaultValue;
  }

  @Nullable
  public final ScalableSize getExtent ()
  {
    return m_aExtent;
  }

  @Nonnull
  public final HCImg setExtent (@Nullable final ScalableSize aImageData)
  {
    m_aExtent = aImageData;
    return this;
  }

  @Nonnull
  public final HCImg setExtent (@Nonnegative final int nWidth, @Nonnegative final int nHeight)
  {
    return setExtent (new ScalableSize (nWidth, nHeight));
  }

  @Nonnull
  public final HCImg scaleToWidth (@Nonnegative final int nNewWidth)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getScaledToWidth (nNewWidth);
    return this;
  }

  @Nonnull
  public final HCImg scaleToHeight (@Nonnegative final int nNewHeight)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getScaledToHeight (nNewHeight);
    return this;
  }

  /**
   * Scales the image so that neither with nor height are exceeded, keeping the
   * aspect ratio.
   *
   * @param nMaxWidth
   *        Maximum with
   * @param nMaxHeight
   *        Maximum height
   * @return the correctly resized image tag
   */
  @Nonnull
  public final HCImg scaleBestMatching (@Nonnegative final int nMaxWidth, @Nonnegative final int nMaxHeight)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getBestMatchingSize (nMaxWidth, nMaxHeight);
    return this;
  }

  @Nullable
  public final String getAlt ()
  {
    return m_sAlt;
  }

  @Nonnull
  public final HCImg setAlt (@Nullable final String sAlt)
  {
    m_sAlt = sAlt;
    return this;
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sSrc))
      aElement.setAttribute (CHTMLAttributes.SRC, m_sSrc);
    if (m_aExtent != null)
    {
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_aExtent.getWidth ());
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_aExtent.getHeight ());
    }

    // Ensure that the alt attribute is present
    final String sTitle = getTitle ();
    final String sRealAlt = StringHelper.hasText (m_sAlt) ? m_sAlt : sTitle;
    aElement.setAttribute (CHTMLAttributes.ALT, sRealAlt);

    // If the title is empty, but the alternative text is present, use the
    // alternative text as title
    // The default "title" attribute is set in a base class!
    if (StringHelper.hasNoText (sTitle) && StringHelper.hasText (m_sAlt))
      aElement.setAttribute (CHTMLAttributes.TITLE, m_sAlt);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("src", m_sSrc)
                            .appendIfNotNull ("extent", m_aExtent)
                            .appendIfNotNull ("alt", m_sAlt)
                            .toString ();
  }

  @Nonnull
  public static HCImg create (@Nullable final ISimpleURL aSrc)
  {
    return new HCImg ().setSrc (aSrc);
  }

  @Nonnull
  public static HCImg create (@Nullable final String sSrc)
  {
    return new HCImg ().setSrc (sSrc);
  }
}
