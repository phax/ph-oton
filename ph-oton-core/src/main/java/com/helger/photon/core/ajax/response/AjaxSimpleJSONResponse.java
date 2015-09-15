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

import com.helger.commons.charset.CCharset;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.mime.CMimeType;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IJson;
import com.helger.web.servlet.response.UnifiedResponse;

/**
 * A simple AJAX response, based on an {@link IJson} value.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxSimpleJSONResponse extends AbstractAjaxResponse
{
  private final IJson m_aValue;

  public AjaxSimpleJSONResponse (final boolean bSuccess, @Nullable final IJson aValue)
  {
    super (bSuccess);
    m_aValue = aValue;
  }

  @Nullable
  public IJson getJson ()
  {
    return m_aValue;
  }

  public void applyToResponse (@Nonnull final UnifiedResponse aUnifiedResponse)
  {
    final String sResponse = m_aValue != null ? m_aValue.getAsString () : "";
    aUnifiedResponse.setContentAndCharset (sResponse, CCharset.CHARSET_UTF_8_OBJ)
                    .setMimeType (CMimeType.APPLICATION_JSON);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AjaxSimpleJSONResponse rhs = (AjaxSimpleJSONResponse) o;
    return EqualsHelper.equals (m_aValue, rhs.m_aValue);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("value", m_aValue).toString ();
  }
}
