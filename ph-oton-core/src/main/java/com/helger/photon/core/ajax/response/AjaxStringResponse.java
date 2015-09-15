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
package com.helger.photon.core.ajax.response;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.IMimeType;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.commons.xml.serialize.write.XMLWriterSettings;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * A simple AJAX response, based on an {@link String} value.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxStringResponse implements IAjaxResponse
{
  private final boolean m_bSuccess;
  private final String m_sValue;
  private final IMimeType m_aMimeType;

  public AjaxStringResponse (final boolean bSuccess, @Nullable final String sValue, @Nonnull final IMimeType aMimeType)
  {
    m_bSuccess = bSuccess;
    m_sValue = StringHelper.getNotNull (sValue);
    m_aMimeType = ValueEnforcer.notNull (aMimeType, "MimeType");
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public boolean isFailure ()
  {
    return !m_bSuccess;
  }

  @Nonnull
  public IMimeType getMimeType ()
  {
    return m_aMimeType;
  }

  @Nonnull
  public String getResponseString ()
  {
    return m_sValue;
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    aUnifiedResponse.setContentAndCharset (m_sValue, XMLWriterSettings.DEFAULT_XML_CHARSET_OBJ)
                    .setMimeType (m_aMimeType);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AjaxStringResponse rhs = (AjaxStringResponse) o;
    return m_sValue.equals (rhs.m_sValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("value", m_sValue).toString ();
  }
}
