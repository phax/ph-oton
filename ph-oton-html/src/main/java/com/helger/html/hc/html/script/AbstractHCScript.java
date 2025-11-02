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

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.OverridingMethodsMustInvokeSuper;
import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.IHCHasChildrenMutable;
import com.helger.html.hc.IHCNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.html.hc.html.embedded.EHCCORSSettings;
import com.helger.mime.CMimeType;
import com.helger.mime.IMimeType;
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

  @NonNull
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

  @NonNull
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

  @NonNull
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

  @NonNull
  public final IMPLTYPE setIntegrity (@Nullable final String sIntegrity)
  {
    m_sIntegrity = sIntegrity;
    return thisAsT ();
  }

  @Override
  @OverridingMethodsMustInvokeSuper
  protected void onFinalizeNodeState (@NonNull final IHCConversionSettingsToNode aConversionSettings,
                                      @NonNull final IHCHasChildrenMutable <?, ? super IHCNode> aTargetNode)
  {
    super.onFinalizeNodeState (aConversionSettings, aTargetNode);

    if (!hasNonce ())
      setNonce (aConversionSettings.getNonceScript ());
  }

  @Override
  protected void fillMicroElement (@NonNull final IMicroElement aElement,
                                   @NonNull final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.isNotEmpty (m_sType))
      aElement.setAttribute (CHTMLAttributes.TYPE, m_sType);
    if (StringHelper.isNotEmpty (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
    if (m_eCrossOrigin != null)
      aElement.setAttribute (CHTMLAttributes.CROSSORIGIN, m_eCrossOrigin);
    if (StringHelper.isNotEmpty (m_sIntegrity))
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
