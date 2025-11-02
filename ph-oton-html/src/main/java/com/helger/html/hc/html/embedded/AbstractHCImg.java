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
package com.helger.html.hc.html.embedded;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonnegative;
import com.helger.base.dimension.SizeInt;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCMediaElementChild;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;img&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        The implementation type.
 */
public abstract class AbstractHCImg <IMPLTYPE extends AbstractHCImg <IMPLTYPE>> extends
                                    AbstractHCMediaElementChild <IMPLTYPE> implements
                                    IHCImg <IMPLTYPE>
{
  // Inline images can be SimpleURLs as well!
  private ISimpleURL m_aSrc;
  private String m_sSrcSet;
  private String m_sSizes;
  private SizeInt m_aExtent;
  private String m_sAlt;
  private EHCCORSSettings m_eCrossOrigin;
  private EHCLoadingType m_eLoading;

  public AbstractHCImg ()
  {
    super (EHTMLElement.IMG);
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @NonNull
  public final IMPLTYPE setSrc (@NonNull final ISimpleURL aSrc)
  {
    ValueEnforcer.notNull (aSrc, "src");

    m_aSrc = aSrc;
    return thisAsT ();
  }

  @Nullable
  public final String getSrcSet ()
  {
    return m_sSrcSet;
  }

  @NonNull
  public final IMPLTYPE setSrcSet (@Nullable final String sSrcSet)
  {
    m_sSrcSet = sSrcSet;
    return thisAsT ();
  }

  @Nullable
  public final String getSizes ()
  {
    return m_sSizes;
  }

  @NonNull
  public final IMPLTYPE setSizes (@Nullable final String sSizes)
  {
    m_sSizes = sSizes;
    return thisAsT ();
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
  public final SizeInt getExtent ()
  {
    return m_aExtent;
  }

  @NonNull
  public final IMPLTYPE setExtent (@Nullable final SizeInt aExtent)
  {
    m_aExtent = aExtent;
    return thisAsT ();
  }

  @NonNull
  public final IMPLTYPE setExtent (@Nonnegative final int nWidth, @Nonnegative final int nHeight)
  {
    return setExtent (new SizeInt (nWidth, nHeight));
  }

  @NonNull
  public final IMPLTYPE scaleToWidth (@Nonnegative final int nNewWidth)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getScaledToWidth (nNewWidth);
    return thisAsT ();
  }

  @NonNull
  public final IMPLTYPE scaleToHeight (@Nonnegative final int nNewHeight)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getScaledToHeight (nNewHeight);
    return thisAsT ();
  }

  /**
   * Scales the image so that neither with nor height are exceeded, keeping the aspect ratio.
   *
   * @param nMaxWidth
   *        Maximum with
   * @param nMaxHeight
   *        Maximum height
   * @return the correctly resized image tag
   */
  @NonNull
  public final IMPLTYPE scaleBestMatching (@Nonnegative final int nMaxWidth, @Nonnegative final int nMaxHeight)
  {
    if (m_aExtent != null)
      m_aExtent = m_aExtent.getBestMatchingSize (nMaxWidth, nMaxHeight);
    return thisAsT ();
  }

  @Nullable
  public final String getAlt ()
  {
    return m_sAlt;
  }

  @NonNull
  public final IMPLTYPE setAlt (@Nullable final String sAlt)
  {
    m_sAlt = sAlt;
    return thisAsT ();
  }

  @Nullable
  public final EHCCORSSettings getCrossOrigin ()
  {
    return m_eCrossOrigin;
  }

  @NonNull
  public final IMPLTYPE setCrossOrigin (@Nullable final EHCCORSSettings eCrossOrigin)
  {
    m_eCrossOrigin = eCrossOrigin;
    return thisAsT ();
  }

  @Nullable
  public final EHCLoadingType getLoading ()
  {
    return m_eLoading;
  }

  @NonNull
  public final IMPLTYPE setLoading (@Nullable final EHCLoadingType eLoading)
  {
    m_eLoading = eLoading;
    return thisAsT ();
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_aSrc != null)
      aElement.setAttribute (CHTMLAttributes.SRC,
                             m_aSrc.getWithCharset (aConversionSettings.getCharset ()).getAsString ());
    if (StringHelper.isNotEmpty (m_sSrcSet))
      aElement.setAttribute (CHTMLAttributes.SRCSET, m_sSrcSet);
    if (StringHelper.isNotEmpty (m_sSizes))
      aElement.setAttribute (CHTMLAttributes.SIZES, m_sSizes);
    if (m_aExtent != null)
    {
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_aExtent.getWidth ());
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_aExtent.getHeight ());
    }

    // Ensure that the alt attribute is present
    // For WAI conformity, only alt but not title should be present
    if (StringHelper.isNotEmpty (m_sAlt))
    {
      aElement.setAttribute (CHTMLAttributes.ALT, m_sAlt);
      aElement.removeAttribute (CHTMLAttributes.TITLE);
    }
    else
    {
      final String sTitle = getTitle ();
      if (sTitle != null)
      {
        aElement.setAttribute (CHTMLAttributes.ALT, sTitle);
        aElement.removeAttribute (CHTMLAttributes.TITLE);
      }
      else
        aElement.setAttribute (CHTMLAttributes.ALT, "");
    }

    if (m_eCrossOrigin != null)
      aElement.setAttribute (CHTMLAttributes.CROSSORIGIN, m_eCrossOrigin);
    if (m_eLoading != null)
      aElement.setAttribute (CHTMLAttributes.LOADING, m_eLoading);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Src", m_aSrc)
                            .appendIfNotNull ("SrcSet", m_sSrcSet)
                            .appendIfNotNull ("Sizes", m_sSizes)
                            .appendIfNotNull ("Extent", m_aExtent)
                            .appendIfNotNull ("Alt", m_sAlt)
                            .appendIfNotNull ("CrossOrigin", m_eCrossOrigin)
                            .appendIfNotNull ("Loading", m_eLoading)
                            .getToString ();
  }
}
