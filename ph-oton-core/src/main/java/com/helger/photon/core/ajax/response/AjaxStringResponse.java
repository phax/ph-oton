/**
 * Copyright (C) 2014-2017 Philip Helger (www.helger.com)
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
package com.helger.photon.core.ajax.response;

import java.nio.charset.Charset;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.hc.config.HCSettings;
import com.helger.photon.core.app.html.PhotonHTMLHelper;
import com.helger.servlet.response.UnifiedResponse;
import com.helger.xml.serialize.write.XMLWriterSettings;

/**
 * A simple AJAX response, based on an {@link String} value.
 *
 * @author Philip Helger
 */
@Immutable
@Deprecated
public class AjaxStringResponse implements IAjaxResponse
{
  private final String m_sValue;
  private final Charset m_aCharset;
  private final IMimeType m_aMimeType;

  public AjaxStringResponse (@Nullable final String sValue,
                             @Nonnull final Charset aCharset,
                             @Nonnull final IMimeType aMimeType)
  {
    m_sValue = StringHelper.getNotNull (sValue);
    m_aCharset = ValueEnforcer.notNull (aCharset, "Charset");
    m_aMimeType = ValueEnforcer.notNull (aMimeType, "MimeType");
  }

  @Nonnull
  public String getResponseString ()
  {
    return m_sValue;
  }

  @Nonnull
  public Charset getCharset ()
  {
    return m_aCharset;
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  @OverridingMethodsMustInvokeSuper
  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    aUnifiedResponse.setContentAndCharset (m_sValue, m_aCharset).setMimeType (m_aMimeType);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxStringResponse rhs = (AjaxStringResponse) o;
    return m_sValue.equals (rhs.m_sValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Value", m_sValue)
                            .append ("MimeType", m_aMimeType)
                            .getToString ();
  }

  @Nonnull
  public static AjaxStringResponse createForHTML (@Nullable final String sValue)
  {
    return new AjaxStringResponse (sValue, HCSettings.getHTMLCharset (), PhotonHTMLHelper.getMimeType (null));
  }

  @Nonnull
  public static AjaxStringResponse createForText (@Nullable final String sValue)
  {
    return new AjaxStringResponse (sValue, XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ, CMimeType.TEXT_PLAIN);
  }
}
