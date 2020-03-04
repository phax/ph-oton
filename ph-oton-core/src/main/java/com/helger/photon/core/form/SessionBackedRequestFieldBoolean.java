/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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
package com.helger.photon.core.form;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.request.IHCRequestFieldBoolean;
import com.helger.web.scope.IRequestParamContainer;

/**
 * Special session backed request field specially for check boxes with a fixed
 * value.
 *
 * @author Philip Helger
 * @since 8.2.4
 */
public class SessionBackedRequestFieldBoolean extends SessionBackedRequestField implements IHCRequestFieldBoolean
{
  private final boolean m_bDefaultValue;

  /**
   * Constructor using either {@link RequestFieldBoolean#VALUE_CHECKED} or
   * {@link RequestFieldBoolean#VALUE_UNCHECKED}.
   *
   * @param sFieldName
   *        Name of the field.
   * @param bDefaultValue
   *        The status to be returned, if no request value is present.
   */
  public SessionBackedRequestFieldBoolean (@Nonnull @Nonempty final String sFieldName, final boolean bDefaultValue)
  {
    super (sFieldName, RequestFieldBoolean.getStringValue (bDefaultValue));

    // The default value is immutable
    m_bDefaultValue = bDefaultValue;
  }

  public boolean isChecked (@Nonnull final IRequestParamContainer aParams)
  {
    return aParams.isCheckBoxChecked (getFieldName (), m_bDefaultValue);
  }

  public boolean isChecked ()
  {
    return isChecked (getParams ());
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final SessionBackedRequestFieldBoolean rhs = (SessionBackedRequestFieldBoolean) o;
    return m_bDefaultValue == rhs.m_bDefaultValue;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_bDefaultValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ()).append ("DefaultValue", m_bDefaultValue).getToString ();
  }
}
