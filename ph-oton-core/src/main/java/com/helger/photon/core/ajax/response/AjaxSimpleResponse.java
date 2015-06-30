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

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.json.IJson;

/**
 * A simple AJAX response, based on an {@link IJson} value.
 *
 * @author Philip Helger
 */
@Immutable
public class AjaxSimpleResponse implements IAjaxResponse
{
  private final boolean m_bSuccess;
  private final IJson m_aValue;

  public AjaxSimpleResponse (final boolean bSuccess, @Nullable final IJson aValue)
  {
    m_bSuccess = bSuccess;
    m_aValue = aValue;
  }

  public boolean isSuccess ()
  {
    return m_bSuccess;
  }

  public boolean isFailure ()
  {
    return !m_bSuccess;
  }

  @Nullable
  public IJson getJson ()
  {
    return m_aValue;
  }

  @Nonnull
  public String getSerializedAsJSON (final boolean bIndentAndAlign)
  {
    return m_aValue == null ? "" : m_aValue.getAsString ();
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AjaxSimpleResponse rhs = (AjaxSimpleResponse) o;
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
    return new ToStringGenerator (this).append ("value", m_aValue).toString ();
  }
}
