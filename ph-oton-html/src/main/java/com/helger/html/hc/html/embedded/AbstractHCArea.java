/*
 * Copyright (C) 2014-2022 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.url.ISimpleURL;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.links.EHCReferrerPolicy;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;area&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCArea <IMPLTYPE extends AbstractHCArea <IMPLTYPE>> extends AbstractHCElement <IMPLTYPE> implements
                                     IHCArea <IMPLTYPE>
{
  private String m_sAlt;
  private String m_sCoords;
  private String m_sShape;
  private ISimpleURL m_aHref;
  private HC_Target m_aTarget;
  private String m_sDownload;
  private ISimpleURL m_aPing;
  private String m_sRel;
  private EHCReferrerPolicy m_eReferrerPolicy;

  public AbstractHCArea ()
  {
    super (EHTMLElement.AREA);
  }

  public AbstractHCArea (@Nonnull final ISimpleURL aHref)
  {
    this ();
    setHref (aHref);
  }

  @Nullable
  public final String getAlt ()
  {
    return m_sAlt;
  }

  @Nonnull
  public final IMPLTYPE setAlt (@Nullable final String sAlt)
  {
    m_sAlt = sAlt;
    return thisAsT ();
  }

  @Nullable
  public final String getCoords ()
  {
    return m_sCoords;
  }

  @Nonnull
  public final IMPLTYPE setCoords (@Nullable final String sCoords)
  {
    m_sCoords = sCoords;
    return thisAsT ();
  }

  @Nullable
  public final String getShape ()
  {
    return m_sShape;
  }

  @Nonnull
  public final IMPLTYPE setShape (@Nullable final String sShape)
  {
    m_sShape = sShape;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getHref ()
  {
    return m_aHref;
  }

  @Nonnull
  public final IMPLTYPE setHref (@Nonnull final ISimpleURL aHref)
  {
    ValueEnforcer.notNull (aHref, "href");

    m_aHref = aHref;
    return thisAsT ();
  }

  @Nullable
  public final HC_Target getTarget ()
  {
    return m_aTarget;
  }

  @Nonnull
  public final IMPLTYPE setTarget (@Nullable final HC_Target aTarget)
  {
    m_aTarget = aTarget;
    return thisAsT ();
  }

  @Nullable
  public final String getDownload ()
  {
    return m_sDownload;
  }

  @Nonnull
  public final IMPLTYPE setDownload (@Nullable final String sDownload)
  {
    m_sDownload = sDownload;
    return thisAsT ();
  }

  @Nullable
  public final ISimpleURL getPing ()
  {
    return m_aPing;
  }

  @Nonnull
  public final IMPLTYPE setPing (@Nullable final ISimpleURL aPing)
  {
    m_aPing = aPing;
    return thisAsT ();
  }

  @Nullable
  public final String getRel ()
  {
    return m_sRel;
  }

  @Nonnull
  public final IMPLTYPE setRel (@Nullable final String sRel)
  {
    m_sRel = sRel;
    return thisAsT ();
  }

  @Nullable
  public final EHCReferrerPolicy getReferrerPolicy ()
  {
    return m_eReferrerPolicy;
  }

  @Nonnull
  public final IMPLTYPE setReferrerPolicy (@Nullable final EHCReferrerPolicy eReferrerPolicy)
  {
    m_eReferrerPolicy = eReferrerPolicy;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sAlt))
      aElement.setAttribute (CHTMLAttributes.ALT, m_sAlt);
    if (StringHelper.hasText (m_sCoords))
      aElement.setAttribute (CHTMLAttributes.COORDS, m_sCoords);
    if (StringHelper.hasText (m_sShape))
      aElement.setAttribute (CHTMLAttributes.SHAPE, m_sShape);
    if (m_aHref != null)
    {
      final String sHref = m_aHref.getAsStringWithEncodedParameters (aConversionSettings.getCharset ());
      aElement.setAttribute (CHTMLAttributes.HREF, sHref);
    }
    if (m_aTarget != null)
    {
      // Note: attribute "target" is not allowed in XHTML 1.0 strict (but in
      // 1.1)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget);
    }
    if (StringHelper.hasText (m_sDownload))
      aElement.setAttribute (CHTMLAttributes.DOWNLOAD, m_sDownload);
    if (m_aPing != null)
    {
      final String sPing = m_aPing.getAsStringWithEncodedParameters (aConversionSettings.getCharset ());
      aElement.setAttribute (CHTMLAttributes.PING, sPing);
    }
    if (StringHelper.hasText (m_sRel))
      aElement.setAttribute (CHTMLAttributes.REL, m_sRel);
    if (m_eReferrerPolicy != null)
      aElement.setAttribute (CHTMLAttributes.REFERRERPOLICY, m_eReferrerPolicy);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Href", m_sAlt)
                            .appendIfNotNull ("Coords", m_sCoords)
                            .appendIfNotNull ("Shape", m_sShape)
                            .appendIfNotNull ("Href", m_aHref)
                            .appendIfNotNull ("Target", m_aTarget)
                            .appendIfNotNull ("Download", m_sDownload)
                            .appendIfNotNull ("Ping", m_aPing)
                            .appendIfNotNull ("Rel", m_sRel)
                            .appendIfNotNull ("ReferrerPolicy", m_eReferrerPolicy)
                            .getToString ();
  }
}
