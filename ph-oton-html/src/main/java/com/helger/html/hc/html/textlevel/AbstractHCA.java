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
package com.helger.html.hc.html.textlevel;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.config.HCConsistencyChecker;
import com.helger.html.hc.html.AbstractHCElementWithChildren;
import com.helger.html.hc.html.HCHTMLHelper;
import com.helger.html.hc.html.HC_Target;
import com.helger.html.hc.html.links.EHCReferrerPolicy;
import com.helger.http.url.ISimpleURL;
import com.helger.mime.IMimeType;
import com.helger.xml.microdom.IMicroElement;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Represents an HTML &lt;a&gt; element
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCA <IMPLTYPE extends AbstractHCA <IMPLTYPE>> extends AbstractHCElementWithChildren <IMPLTYPE> implements
                                  IHCA <IMPLTYPE>
{
  private String m_sName;
  private ISimpleURL m_aHref;
  private HC_Target m_aTarget;
  private String m_sDownload;
  private ISimpleURL m_aPing;
  private String m_sRel;
  private IMimeType m_aType;
  private EHCReferrerPolicy m_eReferrerPolicy;
  private String m_sMediaQuery;

  public AbstractHCA ()
  {
    super (EHTMLElement.A);
  }

  public AbstractHCA (@Nonnull final ISimpleURL aHref)
  {
    this ();
    setHref (aHref);
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
  public final IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nullable final IMimeType aType)
  {
    m_aType = aType;
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

  @Nullable
  public final String getMedia ()
  {
    return m_sMediaQuery;
  }

  @Nonnull
  public final IMPLTYPE setMedia (@Nullable final String sMediaQuery)
  {
    m_sMediaQuery = sMediaQuery;
    return thisAsT ();
  }

  @Override
  protected void onConsistencyCheck (@Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.onConsistencyCheck (aConversionSettings);
    if (HCHTMLHelper.recursiveContainsChildWithTagName (this, EHTMLElement.A))
      HCConsistencyChecker.consistencyError ("<A> may never contain other links!");
    if (HCHTMLHelper.recursiveContainsChildWithTagName (this, EHTMLElement.SELECT))
      HCConsistencyChecker.consistencyError ("<A> contains invalid child element!");
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (m_aHref != null)
    {
      final String sHref = m_aHref.getAsStringWithEncodedParameters (aConversionSettings.getCharset ());
      aElement.setAttribute (CHTMLAttributes.HREF, sHref);
    }

    // The target, download, ping, rel, hreflang, type, and referrerpolicy
    // attributes must be omitted if the href attribute is not present.
    if (m_aTarget != null)
    {
      // Note: attribute "target" is not allowed in XHTML 1.0 strict (but in
      // 1.1)
      aElement.setAttribute (CHTMLAttributes.TARGET, m_aTarget);
    }
    if (StringHelper.isNotEmpty (m_sDownload))
      aElement.setAttribute (CHTMLAttributes.DOWNLOAD, m_sDownload);
    if (m_aPing != null)
    {
      final String sPing = m_aPing.getAsStringWithEncodedParameters (aConversionSettings.getCharset ());
      aElement.setAttribute (CHTMLAttributes.PING, sPing);
    }
    if (StringHelper.isNotEmpty (m_sRel))
      aElement.setAttribute (CHTMLAttributes.REL, m_sRel);
    if (m_aType != null)
      aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (m_eReferrerPolicy != null)
      aElement.setAttribute (CHTMLAttributes.REFERRERPOLICY, m_eReferrerPolicy);
    // HTML5 only:
    if (StringHelper.isNotEmpty (m_sMediaQuery))
      aElement.setAttribute (CHTMLAttributes.MEDIA, m_sMediaQuery);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Name", m_sName)
                            .appendIfNotNull ("Href", m_aHref)
                            .appendIfNotNull ("Target", m_aTarget)
                            .appendIfNotNull ("Download", m_sDownload)
                            .appendIfNotNull ("Ping", m_aPing)
                            .appendIfNotNull ("Rel", m_sRel)
                            .appendIfNotNull ("Type", m_aType)
                            .appendIfNotNull ("ReferrerPolicy", m_eReferrerPolicy)
                            .appendIfNotNull ("MediaQuery", m_sMediaQuery)
                            .getToString ();
  }
}
