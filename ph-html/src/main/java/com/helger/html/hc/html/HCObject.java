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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.CGlobal;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.api.EHCObjectAlign;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElementWithChildren;

/**
 * Represents an HTML &lt;object&gt; element
 *
 * @author Philip Helger
 */
public class HCObject extends AbstractHCElementWithChildren <HCObject>
{
  /** By default declare is disabled */
  public static final boolean DEFAULT_DECLARE = false;

  private int m_nWidth = CGlobal.ILLEGAL_UINT;
  private int m_nHeight = CGlobal.ILLEGAL_UINT;
  private String m_sHSpace;
  private String m_sVSpace;
  private EHCObjectAlign m_eAlign;
  private String m_sArchive;
  private String m_sBorder;
  private String m_sClassID;
  private ISimpleURL m_aCodeBase;
  private IMimeType m_aCodeType;
  private ISimpleURL m_aData;
  private boolean m_bDeclare = DEFAULT_DECLARE;
  private String m_sName;
  private String m_sStandBy;
  private IMimeType m_aType;
  private String m_sUseMap;

  public HCObject ()
  {
    super (EHTMLElement.OBJECT);
  }

  public final int getWidth ()
  {
    return m_nWidth;
  }

  @Nonnull
  public final HCObject setWidth (final int nWidth)
  {
    m_nWidth = nWidth;
    return this;
  }

  public final int getHeight ()
  {
    return m_nHeight;
  }

  @Nonnull
  public final HCObject setHeight (final int nHeight)
  {
    m_nHeight = nHeight;
    return this;
  }

  @Nullable
  public final String getHSpace ()
  {
    return m_sHSpace;
  }

  @Nonnull
  public final HCObject setHSpace (@Nullable final String sHSpace)
  {
    m_sHSpace = sHSpace;
    return this;
  }

  @Nullable
  public final String getVSpace ()
  {
    return m_sVSpace;
  }

  @Nonnull
  public final HCObject setVSpace (@Nullable final String sVSpace)
  {
    m_sVSpace = sVSpace;
    return this;
  }

  @Nullable
  public final EHCObjectAlign getAlign ()
  {
    return m_eAlign;
  }

  @Nonnull
  public final HCObject setAlign (@Nullable final EHCObjectAlign eAlign)
  {
    m_eAlign = eAlign;
    return this;
  }

  @Nullable
  public final String getArchive ()
  {
    return m_sArchive;
  }

  @Nonnull
  public final HCObject setArchive (@Nullable final String sArchive)
  {
    m_sArchive = sArchive;
    return this;
  }

  @Nullable
  public final String getBorder ()
  {
    return m_sBorder;
  }

  @Nonnull
  public final HCObject setBorder (@Nullable final String sBorder)
  {
    m_sBorder = sBorder;
    return this;
  }

  @Nullable
  public final String getClassID ()
  {
    return m_sClassID;
  }

  @Nonnull
  public final HCObject setClassID (@Nullable final String sClassID)
  {
    m_sClassID = sClassID;
    return this;
  }

  @Nullable
  public final ISimpleURL getCodeBase ()
  {
    return m_aCodeBase;
  }

  @Nonnull
  public final HCObject setCodeBase (@Nullable final ISimpleURL aCodeBase)
  {
    m_aCodeBase = aCodeBase;
    return this;
  }

  @Nullable
  public final IMimeType getCodeType ()
  {
    return m_aCodeType;
  }

  @Nonnull
  public final HCObject setCodeType (@Nullable final IMimeType aCodeType)
  {
    m_aCodeType = aCodeType;
    return this;
  }

  @Nullable
  public final ISimpleURL getData ()
  {
    return m_aData;
  }

  @Nonnull
  public final HCObject setData (@Nullable final ISimpleURL aData)
  {
    m_aData = aData;
    return this;
  }

  public final boolean isDeclare ()
  {
    return m_bDeclare;
  }

  @Nonnull
  public final HCObject setDeclare (final boolean bDeclare)
  {
    m_bDeclare = bDeclare;
    return this;
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public final HCObject setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  public final String getStandBy ()
  {
    return m_sStandBy;
  }

  @Nonnull
  public final HCObject setStandBy (@Nullable final String sStandBy)
  {
    m_sStandBy = sStandBy;
    return this;
  }

  @Nullable
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public final HCObject setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return this;
  }

  @Nullable
  public final String getUseMap ()
  {
    return m_sUseMap;
  }

  @Nonnull
  public final HCObject setUseMap (@Nullable final String sUseMap)
  {
    m_sUseMap = sUseMap;
    return this;
  }

  @Override
  protected void applyProperties (@Nonnull final IMicroElement aElement,
                                  @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    if (m_nWidth > 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
    if (m_nHeight > 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (StringHelper.hasText (m_sHSpace))
      aElement.setAttribute (CHTMLAttributes.HSPACE, m_sHSpace);
    if (StringHelper.hasText (m_sVSpace))
      aElement.setAttribute (CHTMLAttributes.VSPACE, m_sVSpace);
    if (m_eAlign != null)
      aElement.setAttribute (CHTMLAttributes.ALIGN, m_eAlign);
    if (StringHelper.hasText (m_sArchive))
      aElement.setAttribute (CHTMLAttributes.ARCHIVE, m_sArchive);
    if (StringHelper.hasText (m_sBorder))
      aElement.setAttribute (CHTMLAttributes.BORDER, m_sBorder);
    if (StringHelper.hasText (m_sClassID))
      aElement.setAttribute (CHTMLAttributes.CLASSID, m_sClassID);
    if (m_aCodeBase != null)
      aElement.setAttribute (CHTMLAttributes.CODEBASE, m_aCodeBase.getAsString ());
    if (m_aCodeType != null)
      aElement.setAttribute (CHTMLAttributes.CODETYPE, m_aCodeType.getAsString ());
    if (m_aData != null)
      aElement.setAttribute (CHTMLAttributes.DATA, m_aData.getAsString ());
    if (m_bDeclare)
      aElement.setAttribute (CHTMLAttributes.DECLARE, CHTMLAttributeValues.DECLARE);
    if (StringHelper.hasText (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (StringHelper.hasText (m_sStandBy))
      aElement.setAttribute (CHTMLAttributes.STANDBY, m_sStandBy);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (StringHelper.hasText (m_sUseMap))
      aElement.setAttribute (CHTMLAttributes.USEMAP, m_sUseMap);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("width", m_nWidth)
                            .append ("height", m_nHeight)
                            .appendIfNotNull ("hspace", m_sHSpace)
                            .appendIfNotNull ("vspace", m_sVSpace)
                            .appendIfNotNull ("align", m_eAlign)
                            .appendIfNotNull ("archive", m_sArchive)
                            .appendIfNotNull ("border", m_sBorder)
                            .appendIfNotNull ("classID", m_sClassID)
                            .appendIfNotNull ("codeBase", m_aCodeBase)
                            .appendIfNotNull ("codeType", m_aCodeType)
                            .appendIfNotNull ("data", m_aData)
                            .append ("declare", m_bDeclare)
                            .appendIfNotNull ("name", m_sName)
                            .appendIfNotNull ("standBy", m_sStandBy)
                            .appendIfNotNull ("type", m_aType)
                            .appendIfNotNull ("useMap", m_sUseMap)
                            .toString ();
  }
}
