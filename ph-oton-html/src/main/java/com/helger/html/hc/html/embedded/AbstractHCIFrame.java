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

import java.util.Collections;
import java.util.EnumSet;

import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.CGlobal;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.css.ECSSUnit;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.EHCScrolling;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;iframe&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCIFrame <IMPLTYPE extends AbstractHCIFrame <IMPLTYPE>> extends
                                       AbstractHCElementWithChildren <IMPLTYPE> implements
                                       IHCIFrame <IMPLTYPE>
{
  /** Default scrolling is auto */
  public static final EHCScrolling DEFAULT_SCROLLING = EHCScrolling.AUTO;
  /** By default a frame border is visible */
  public static final boolean DEFAULT_FRAME_BORDER = true;
  /** By default sandbox is disabled */
  public static final boolean DEFAULT_SANDBOX = false;

  private ISimpleURL m_aSrc;
  private String m_sName;
  private String m_sLongDesc;
  private EHCScrolling m_eScrolling = DEFAULT_SCROLLING;
  private EHCIFrameAlign m_eAlign;
  private boolean m_bFrameBorder = DEFAULT_FRAME_BORDER;
  private String m_sWidth;
  private String m_sHeight;
  private int m_nMarginWidth = CGlobal.ILLEGAL_UINT;
  private int m_nMarginHeight = CGlobal.ILLEGAL_UINT;
  private boolean m_bSandbox = DEFAULT_SANDBOX;
  private final EnumSet <EHCSandboxAllow> m_aSandboxAllows = EnumSet.noneOf (EHCSandboxAllow.class);
  private EHCLoadingType m_eLoading;

  public AbstractHCIFrame ()
  {
    super (EHTMLElement.IFRAME);
  }

  @Nullable
  public final ISimpleURL getSrc ()
  {
    return m_aSrc;
  }

  @Nonnull
  public final IMPLTYPE setSrc (@Nullable final ISimpleURL aSrc)
  {
    m_aSrc = aSrc;
    return thisAsT ();
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public final IMPLTYPE setName (@Nullable final String sName)
  {
    m_sName = sName;
    return thisAsT ();
  }

  @Nullable
  public final String getLongDesc ()
  {
    return m_sLongDesc;
  }

  @Nonnull
  public final IMPLTYPE setLongDesc (@Nullable final String sLongDesc)
  {
    m_sLongDesc = sLongDesc;
    return thisAsT ();
  }

  @Nullable
  public final EHCScrolling getScrolling ()
  {
    return m_eScrolling;
  }

  @Nonnull
  public final IMPLTYPE setScrolling (@Nullable final EHCScrolling eScrolling)
  {
    m_eScrolling = eScrolling;
    return thisAsT ();
  }

  @Nullable
  public final EHCIFrameAlign getAlign ()
  {
    return m_eAlign;
  }

  @Nonnull
  public final IMPLTYPE setAlign (@Nullable final EHCIFrameAlign eAlign)
  {
    m_eAlign = eAlign;
    return thisAsT ();
  }

  public final boolean isFrameBorder ()
  {
    return m_bFrameBorder;
  }

  @Nonnull
  public final IMPLTYPE setFrameBorder (final boolean bFrameBorder)
  {
    m_bFrameBorder = bFrameBorder;
    return thisAsT ();
  }

  @Nullable
  public final String getWidth ()
  {
    return m_sWidth;
  }

  /**
   * Set the width in pixel
   *
   * @param nWidth
   *        the width in pixel
   * @return this
   */
  @Nonnull
  public final IMPLTYPE setWidth (final int nWidth)
  {
    if (nWidth >= 0)
      m_sWidth = Integer.toString (nWidth);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setWidthPercentage (final double dPercentage)
  {
    m_sWidth = ECSSUnit.perc (dPercentage);
    return thisAsT ();
  }

  @Nullable
  public final String getHeight ()
  {
    return m_sHeight;
  }

  @Nonnull
  public final IMPLTYPE setHeight (final int nHeight)
  {
    if (nHeight >= 0)
      m_sHeight = Integer.toString (nHeight);
    return thisAsT ();
  }

  @Nonnull
  public final IMPLTYPE setHeightPercentage (final double dPercentage)
  {
    m_sHeight = ECSSUnit.perc (dPercentage);
    return thisAsT ();
  }

  public final int getMarginWidth ()
  {
    return m_nMarginWidth;
  }

  @Nonnull
  public final IMPLTYPE setMarginWidth (final int nMarginWidth)
  {
    m_nMarginWidth = nMarginWidth;
    return thisAsT ();
  }

  public final int getMarginHeight ()
  {
    return m_nMarginHeight;
  }

  @Nonnull
  public final IMPLTYPE setMarginHeight (final int nMarginHeight)
  {
    m_nMarginHeight = nMarginHeight;
    return thisAsT ();
  }

  public final boolean isSandbox ()
  {
    return m_bSandbox;
  }

  @Nonnull
  @ReturnsMutableCopy
  public final EnumSet <EHCSandboxAllow> getSandboxAllow ()
  {
    return EnumSet.copyOf (m_aSandboxAllows);
  }

  @Nonnull
  public final IMPLTYPE setSandbox (final boolean bSandbox, @Nullable final EHCSandboxAllow... aSandboxAllows)
  {
    m_bSandbox = bSandbox;
    m_aSandboxAllows.clear ();
    if (aSandboxAllows != null)
      Collections.addAll (m_aSandboxAllows, aSandboxAllows);
    return thisAsT ();
  }

  @Nullable
  public final EHCLoadingType getLoading ()
  {
    return m_eLoading;
  }

  @Nonnull
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
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (StringHelper.isNotEmpty (m_sLongDesc))
      aElement.setAttribute (CHTMLAttributes.LONGDESC, m_sLongDesc);
    if (m_eScrolling != null)
      aElement.setAttribute (CHTMLAttributes.SCROLLING, m_eScrolling);
    if (m_eAlign != null)
      aElement.setAttribute (CHTMLAttributes.ALIGN, m_eAlign);
    aElement.setAttribute (CHTMLAttributes.FRAMEBORDER, m_bFrameBorder ? "1" : "0");
    if (StringHelper.isNotEmpty (m_sWidth))
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_sWidth);
    if (StringHelper.isNotEmpty (m_sHeight))
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_sHeight);
    if (m_nMarginWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINWIDTH, m_nMarginWidth);
    if (m_nMarginHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINHEIGHT, m_nMarginHeight);
    if (m_bSandbox)
    {
      final StringBuilder aValue = new StringBuilder ();
      for (final EHCSandboxAllow eAllow : m_aSandboxAllows)
      {
        if (aValue.length () > 0)
          aValue.append (' ');
        aValue.append (eAllow.getAttrValue ());
      }
      aElement.setAttribute (CHTMLAttributes.SANDBOX, aValue.toString ());
    }
    if (m_eLoading != null)
      aElement.setAttribute (CHTMLAttributes.LOADING, m_eLoading);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Src", m_aSrc)
                            .appendIfNotNull ("Name", m_sName)
                            .appendIfNotNull ("LongDesc", m_sLongDesc)
                            .appendIfNotNull ("Scrolling", m_eScrolling)
                            .appendIfNotNull ("Align", m_eAlign)
                            .append ("FrameBorder", m_bFrameBorder)
                            .appendIfNotNull ("Width", m_sWidth)
                            .appendIfNotNull ("Height", m_sHeight)
                            .append ("MarginWidth", m_nMarginWidth)
                            .append ("MarginHeight", m_nMarginHeight)
                            .appendIfNotNull ("Loading", m_eLoading)
                            .getToString ();
  }
}
