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

import com.helger.base.CGlobal;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributeValues;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.mime.IMimeType;
import com.helger.url.ISimpleURL;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;object&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCObject <IMPLTYPE extends AbstractHCObject <IMPLTYPE>> extends
                                       AbstractHCElementWithChildren <IMPLTYPE> implements
                                       IHCObject <IMPLTYPE>
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

  public AbstractHCObject ()
  {
    super (EHTMLElement.OBJECT);
  }

  public final int getWidth ()
  {
    return m_nWidth;
  }

  @NonNull
  public final IMPLTYPE setWidth (final int nWidth)
  {
    m_nWidth = nWidth;
    return thisAsT ();
  }

  public final int getHeight ()
  {
    return m_nHeight;
  }

  @NonNull
  public final IMPLTYPE setHeight (final int nHeight)
  {
    m_nHeight = nHeight;
    return thisAsT ();
  }

  @Nullable
  public final String getHSpace ()
  {
    return m_sHSpace;
  }

  @NonNull
  public final IMPLTYPE setHSpace (@Nullable final String sHSpace)
  {
    m_sHSpace = sHSpace;
    return thisAsT ();
  }

  @Nullable
  public final String getVSpace ()
  {
    return m_sVSpace;
  }

  @NonNull
  public final IMPLTYPE setVSpace (@Nullable final String sVSpace)
  {
    m_sVSpace = sVSpace;
    return thisAsT ();
  }

  @Nullable
  public final EHCObjectAlign getAlign ()
  {
    return m_eAlign;
  }

  @NonNull
  public final IMPLTYPE setAlign (@Nullable final EHCObjectAlign eAlign)
  {
    m_eAlign = eAlign;
    return thisAsT ();
  }

  @Nullable
  public final String getArchive ()
  {
    return m_sArchive;
  }

  @NonNull
  public final IMPLTYPE setArchive (@Nullable final String sArchive)
  {
    m_sArchive = sArchive;
    return thisAsT ();
  }

  @Nullable
  public final String getBorder ()
  {
    return m_sBorder;
  }

  @NonNull
  public final IMPLTYPE setBorder (@Nullable final String sBorder)
  {
    m_sBorder = sBorder;
    return thisAsT ();
  }

  @Nullable
  public final String getClassID ()
  {
    return m_sClassID;
  }

  @NonNull
  public final IMPLTYPE setClassID (@Nullable final String sClassID)
  {
    m_sClassID = sClassID;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getCodeBase ()
  {
    return m_aCodeBase;
  }

  @NonNull
  public final IMPLTYPE setCodeBase (@Nullable final ISimpleURL aCodeBase)
  {
    m_aCodeBase = aCodeBase;
    return thisAsT ();
  }

  @Nullable
  public final IMimeType getCodeType ()
  {
    return m_aCodeType;
  }

  @NonNull
  public final IMPLTYPE setCodeType (@Nullable final IMimeType aCodeType)
  {
    m_aCodeType = aCodeType;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getData ()
  {
    return m_aData;
  }

  @NonNull
  public final IMPLTYPE setData (@Nullable final ISimpleURL aData)
  {
    m_aData = aData;
    return thisAsT ();
  }

  public final boolean isDeclare ()
  {
    return m_bDeclare;
  }

  @NonNull
  public final IMPLTYPE setDeclare (final boolean bDeclare)
  {
    m_bDeclare = bDeclare;
    return thisAsT ();
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @NonNull
  public final IMPLTYPE setName (@Nullable final String sName)
  {
    m_sName = sName;
    return thisAsT ();
  }

  @Nullable
  public final String getStandBy ()
  {
    return m_sStandBy;
  }

  @NonNull
  public final IMPLTYPE setStandBy (@Nullable final String sStandBy)
  {
    m_sStandBy = sStandBy;
    return thisAsT ();
  }

  @Nullable
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @NonNull
  public final IMPLTYPE setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
    return thisAsT ();
  }

  @Nullable
  public final String getUseMap ()
  {
    return m_sUseMap;
  }

  @NonNull
  public final IMPLTYPE setUseMap (@Nullable final String sUseMap)
  {
    m_sUseMap = sUseMap;
    return thisAsT ();
  }

  @Override
  protected void onConsistencyCheck (@NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (m_aData == null && m_aType == null)
      HCConsistencyChecker.consistencyError ("OBJECT contains neither type nor data");
  }

  @Override
  protected void fillMicroElement (@NonNull final IMicroElement aElement,
                                   @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (m_nWidth > 0)
      aElement.setAttribute (CHTMLAttributes.WIDTH, m_nWidth);
    if (m_nHeight > 0)
      aElement.setAttribute (CHTMLAttributes.HEIGHT, m_nHeight);
    if (StringHelper.isNotEmpty (m_sHSpace))
      aElement.setAttribute (CHTMLAttributes.HSPACE, m_sHSpace);
    if (StringHelper.isNotEmpty (m_sVSpace))
      aElement.setAttribute (CHTMLAttributes.VSPACE, m_sVSpace);
    if (m_eAlign != null)
      aElement.setAttribute (CHTMLAttributes.ALIGN, m_eAlign);
    if (StringHelper.isNotEmpty (m_sArchive))
      aElement.setAttribute (CHTMLAttributes.ARCHIVE, m_sArchive);
    if (StringHelper.isNotEmpty (m_sBorder))
      aElement.setAttribute (CHTMLAttributes.BORDER, m_sBorder);
    if (StringHelper.isNotEmpty (m_sClassID))
      aElement.setAttribute (CHTMLAttributes.CLASSID, m_sClassID);
    if (m_aCodeBase != null)
      aElement.setAttribute (CHTMLAttributes.CODEBASE,
                             m_aCodeBase.getWithCharset (aConversionSettings.getCharset ()).getAsString ());
    if (m_aCodeType != null)
      aElement.setAttribute (CHTMLAttributes.CODETYPE, m_aCodeType.getAsString ());
    if (m_aData != null)
      aElement.setAttribute (CHTMLAttributes.DATA,
                             m_aData.getWithCharset (aConversionSettings.getCharset ()).getAsString ());
    if (m_bDeclare)
      aElement.setAttribute (CHTMLAttributes.DECLARE, CHTMLAttributeValues.DECLARE);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (StringHelper.isNotEmpty (m_sStandBy))
      aElement.setAttribute (CHTMLAttributes.STANDBY, m_sStandBy);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (StringHelper.isNotEmpty (m_sUseMap))
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
                            .getToString ();
  }
}
