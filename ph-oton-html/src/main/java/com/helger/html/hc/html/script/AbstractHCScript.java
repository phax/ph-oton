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
package com.helger.html.hc.html.script;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.embedded.EHCCORSSettings;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;script&gt; element. This is the base class for inline
 * script and externally referenced script
 *
 * @author Philip Helger
 * @see HCScriptInline
 * @see HCScriptFile
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCScript <IMPLTYPE extends AbstractHCScript <IMPLTYPE>> extends
                                       AbstractHCElement <IMPLTYPE> implements
                                       IHCScript <IMPLTYPE>
{
  /** Default MIME type: text/javascript */
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_JAVASCRIPT;

  private String m_sType = DEFAULT_TYPE.getAsString ();
  private String m_sCharset;
  private EHCCORSSettings m_eCrossOrigin;
  private String m_sIntegrity;

  protected AbstractHCScript ()
  {
    super (EHTMLElement.SCRIPT);
  }

  @Nullable
  public final String getType ()
  {
    return m_sType;
  }

  @Nonnull
  public final IMPLTYPE setType (@Nullable final String sType)
  {
    m_sType = sType;
    return thisAsT ();
  }

  @Nullable
  public final String getCharset ()
  {
    return m_sCharset;
  }

  @Nonnull
  public final IMPLTYPE setCharset (@Nullable final String sCharset)
  {
    m_sCharset = sCharset;
    return thisAsT ();
  }

  @Nullable
  public final EHCCORSSettings getCrossOrigin ()
  {
    return m_eCrossOrigin;
  }

  @Nonnull
  public final IMPLTYPE setCrossOrigin (@Nullable final EHCCORSSettings eCrossOrigin)
  {
    m_eCrossOrigin = eCrossOrigin;
    return thisAsT ();
  }

  @Nullable
  public final String getIntegrity ()
  {
    return m_sIntegrity;
  }

  @Nonnull
  public final IMPLTYPE setIntegrity (@Nullable final String sIntegrity)
  {
    m_sIntegrity = sIntegrity;
    return thisAsT ();
  }

  @Override
  protected void fillMicroElement (@Nonnull final IMicroElement aElement,
                                   @Nonnull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.hasText (m_sType))
      aElement.setAttribute (CHTMLAttributes.TYPE, m_sType);
    if (StringHelper.hasText (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
    if (m_eCrossOrigin != null)
      aElement.setAttribute (CHTMLAttributes.CROSSORIGIN, m_eCrossOrigin);
    if (StringHelper.hasText (m_sIntegrity))
      aElement.setAttribute (CHTMLAttributes.INTEGRITY, m_sIntegrity);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("Type", m_sType)
                            .appendIfNotNull ("Charset", m_sCharset)
                            .appendIfNotNull ("CrossOrigin", m_eCrossOrigin)
                            .appendIfNotNull ("Integrity", m_sIntegrity)
                            .getToString ();
  }
}
