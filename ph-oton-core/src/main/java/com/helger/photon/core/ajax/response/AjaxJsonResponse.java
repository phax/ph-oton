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
import java.nio.charset.StandardCharsets;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.mime.MimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IJson;
import com.helger.json.JsonValue;
import com.helger.servlet.response.UnifiedResponse;

/**
 * A simple AJAX response, based on an {@link IJson} value.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxJsonResponse implements IAjaxResponse
{
  private final IJson m_aValue;

  public AjaxJsonResponse (@Nullable final IJson aValue)
  {
    m_aValue = aValue;
  }

  @Nullable
  public IJson getJson ()
  {
    return m_aValue;
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    // Ensure it is valid JSON
    final String sResponse = m_aValue != null ? m_aValue.getAsJsonString () : "{}";
    final Charset aCharset = StandardCharsets.UTF_8;
    aUnifiedResponse.setContentAndCharset (sResponse, aCharset)
                    .setMimeType (new MimeType (CMimeType.APPLICATION_JSON).addParameter (CMimeType.PARAMETER_NAME_CHARSET,
                                                                                          aCharset.name ()));
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final AjaxJsonResponse rhs = (AjaxJsonResponse) o;
    return EqualsHelper.equals (m_aValue, rhs.m_aValue);
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_aValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("value", m_aValue).getToString ();
  }

  @Nonnull
  public static AjaxJsonResponse createSuccess ()
  {
    return createSuccess ((IJson) null);
  }

  @Nonnull
  public static AjaxJsonResponse createSuccess (@Nullable final IJson aValue)
  {
    return new AjaxJsonResponse (aValue);
  }

  @Nonnull
  public static AjaxJsonResponse createError (@Nonnull final String sErrorMsg)
  {
    return new AjaxJsonResponse (JsonValue.create (sErrorMsg));
  }
}
