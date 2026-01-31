/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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
package com.helger.html.hc.html.metadata;

import java.nio.charset.Charset;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.base.string.StringHelper;
import com.helger.base.tostring.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCConversionSettingsToNode;
import com.helger.html.hc.html.AbstractHCElement;
import com.helger.xml.microdom.IMicroElement;

/**
 * Represents an HTML &lt;meta&gt; element
 *
 * @author Philip Helger
 */
public class HCMeta extends AbstractHCElement <HCMeta>
{
  private String m_sName;
  private String m_sHttpEquiv;
  private String m_sContent;
  private String m_sCharset;

  public HCMeta ()
  {
    super (EHTMLElement.META);
  }

  @Nullable
  public final String getName ()
  {
    return m_sName;
  }

  @NonNull
  public final HCMeta setName (@Nullable final String sName)
  {
    m_sName = sName;
    return this;
  }

  @Nullable
  public final String getHttpEquiv ()
  {
    return m_sHttpEquiv;
  }

  @NonNull
  public final HCMeta setHttpEquiv (@Nullable final String sHttpEquiv)
  {
    m_sHttpEquiv = sHttpEquiv;
    return this;
  }

  @Nullable
  public final String getContent ()
  {
    return m_sContent;
  }

  @NonNull
  public final HCMeta setContent (@Nullable final String sContent)
  {
    m_sContent = sContent;
    return this;
  }

  @Nullable
  public final String getCharset ()
  {
    return m_sCharset;
  }

  @NonNull
  public final HCMeta setCharset (@Nullable final String sCharset)
  {
    m_sCharset = sCharset;
    return this;
  }

  @NonNull
  public final HCMeta setCharset (@Nullable final Charset aCharset)
  {
    return setCharset (aCharset == null ? null : aCharset.name ());
  }

  @Override
  protected void fillMicroElement (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.fillMicroElement (aElement, aConversionSettings);
    if (StringHelper.isNotEmpty (m_sName))
      aElement.setAttribute (CHTMLAttributes.NAME, m_sName);
    if (StringHelper.isNotEmpty (m_sHttpEquiv))
      aElement.setAttribute (CHTMLAttributes.HTTP_EQUIV, m_sHttpEquiv);
    if (StringHelper.isNotEmpty (m_sContent))
      aElement.setAttribute (CHTMLAttributes.CONTENT, m_sContent);
    if (StringHelper.isNotEmpty (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("name", m_sName)
                            .append ("httpEquiv", m_sHttpEquiv)
                            .append ("content", m_sContent)
                            .append ("charset", m_sCharset)
                            .getToString ();
  }
}
