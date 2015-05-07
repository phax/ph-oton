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

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.CHTMLAttributes;
import com.helger.html.EHTMLElement;
import com.helger.html.hc.IHCJSNode;
import com.helger.html.hc.conversion.IHCConversionSettingsToNode;
import com.helger.html.hc.impl.AbstractHCElement;

/**
 * Represents an HTML &lt;script&gt; element
 *
 * @author Philip Helger
 * @see HCScript
 * @see HCScriptFile
 * @param <IMPLTYPE>
 *        Implementation type
 */
public abstract class AbstractHCScript <IMPLTYPE extends AbstractHCScript <IMPLTYPE>> extends AbstractHCElement <IMPLTYPE> implements IHCJSNode
{
  /** Default MIME type: text/javascript */
  public static final IMimeType DEFAULT_TYPE = CMimeType.TEXT_JAVASCRIPT;

  private IMimeType m_aType = DEFAULT_TYPE;
  private String m_sCharset;

  public AbstractHCScript ()
  {
    super (EHTMLElement.SCRIPT);
  }

  @Nonnull
  public IMimeType getType ()
  {
    return m_aType;
  }

  @Nonnull
  public IMPLTYPE setType (@Nonnull final IMimeType aType)
  {
    m_aType = ValueEnforcer.notNull (aType, "Type");
    return thisAsT ();
  }

  @Nullable
  public String getCharset ()
  {
    return m_sCharset;
  }

  @Nonnull
  public IMPLTYPE setCharset (@Nullable final Charset aCharset)
  {
    return setCharset (aCharset == null ? null : aCharset.name ());
  }

  @Nonnull
  public IMPLTYPE setCharset (@Nullable final String sCharset)
  {
    m_sCharset = sCharset;
    return thisAsT ();
  }

  @Override
  protected void applyProperties (final IMicroElement aElement, final IHCConversionSettingsToNode aConversionSettings)
  {
    super.applyProperties (aElement, aConversionSettings);
    aElement.setAttribute (CHTMLAttributes.TYPE, m_aType.getAsString ());
    if (StringHelper.hasText (m_sCharset))
      aElement.setAttribute (CHTMLAttributes.CHARSET, m_sCharset);
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .appendIfNotNull ("type", m_aType)
                            .appendIfNotNull ("charset", m_sCharset)
                            .toString ();
  }
}
