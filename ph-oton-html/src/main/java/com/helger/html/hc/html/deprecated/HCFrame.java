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
package com.helger.html.hc.html.deprecated;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.CGlobal;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.EHCScrolling;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;frame&gt; element
 *
 * @author Philip Helger
 */
@Deprecated (forRemoval = false)
public class HCFrame extends AbstractHCElement <HCFrame>
{
  /** By default a frame border is visible */
  @Deprecated
  public static final boolean DEFAULT_FRAME_BORDER = true;
  /** By default resize is allowed */
  @Deprecated
  public static final boolean DEFAULT_NO_RESIZE = false;

  private boolean m_bFrameBorder = DEFAULT_FRAME_BORDER;
  private String m_sLongDesc;
  private int m_nMarginWidth = CGlobal.ILLEGAL_UINT;
  private int m_nMarginHeight = CGlobal.ILLEGAL_UINT;
  private String m_sName;
  private boolean m_bNoResize = DEFAULT_NO_RESIZE;
  private EHCScrolling m_eScrolling;
  private String m_sSrc;

  @Deprecated
  public HCFrame ()
  {
    super (EHTMLElement.FRAME);
  }

  @Deprecated
  public HCFrame (@Nullable final String sName)
  {
    this ();
    setName (sName);
  }

  @Deprecated
  public final boolean isFrameBorder ()
  {
    return m_bFrameBorder;
  }

  @Deprecated
  @NonNull
  public final HCFrame setFrameBorder (final boolean bFrameBorder)
  {
    m_bFrameBorder = bFrameBorder;
    return this;
  }

  @Deprecated
  @Nullable
  public final String getLongDesc ()
  {
    return m_sLongDesc;
  }

  @Deprecated
  @NonNull
  public final HCFrame setLongDesc (@Nullable final String sLongDesc)
  {
    m_sLongDesc = sLongDesc;
    return this;
  }

  @Deprecated
  public final int getMarginWidth ()
  {
    return m_nMarginWidth;
  }

  @Deprecated
  @NonNull
  public final HCFrame setMarginWidth (final int nMarginWidth)
  {
    m_nMarginWidth = nMarginWidth;
    return this;
  }

  @Deprecated
  public final int getMarginHeight ()
  {
    return m_nMarginHeight;
  }

  @Deprecated
  @NonNull
  public final HCFrame setMarginHeight (final int nMarginHeight)
  {
    m_nMarginHeight = nMarginHeight;
    return this;
  }

  @Deprecated
  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @Deprecated
  @NonNull
  public final HCFrame setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Deprecated
  public final boolean isNoResize ()
  {
    return m_bNoResize;
  }

  @Deprecated
  @NonNull
  public final HCFrame setNoResize (final boolean bNoResize)
  {
    m_bNoResize = bNoResize;
    return this;
  }

  @Deprecated
  @Nullable
  public final EHCScrolling getScrolling ()
  {
    return m_eScrolling;
  }

  @Deprecated
  @NonNull
  public final HCFrame setScrolling (@Nullable final EHCScrolling eScrolling)
  {
    m_eScrolling = eScrolling;
    return this;
  }

  @Deprecated
  @Nullable
  public final String getSrc ()
  {
    return m_sSrc;
  }

  @Deprecated
  @NonNull
  public final HCFrame setSrc (@Nullable final String sSrc)
  {
    m_sSrc = sSrc;
    return this;
  }

  @Deprecated
  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.FRAMEBORDER, m_bFrameBorder ? "1" : "0");
    if (StringHelper.isNotEmpty (m_sLongDesc))
      aElement.setAttribute (CHTMLAttributes.LONGDESC, m_sLongDesc);
    if (m_nMarginWidth >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINWIDTH, m_nMarginWidth);
    if (m_nMarginHeight >= 0)
      aElement.setAttribute (CHTMLAttributes.MARGINHEIGHT, m_nMarginHeight);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_bNoResize)
      aElement.setAttribute (CHTMLAttributes.NORESIZE, CHTMLAttributeValues.NORESIZE);
    if (m_eScrolling != null)
      aElement.setAttribute (CHTMLAttributes.SCROLLING, m_eScrolling);
    if (StringHelper.isNotEmpty (m_sSrc))
      aElement.setAttribute (CHTMLAttributes.SRC, m_sSrc);
  }

  @Deprecated
  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("frameBorder", m_bFrameBorder)
                            .appendIfNotNull ("longDesc", m_sLongDesc)
                            .append ("marginWidth", m_nMarginWidth)
                            .append ("marginHeight", m_nMarginHeight)
                            .appendIfNotNull ("name", m_sName)
                            .append ("noResize", m_bNoResize)
                            .appendIfNotNull ("scrolling", m_eScrolling)
                            .appendIfNotNull ("src", m_sSrc)
                            .getToString ();
  }
}
