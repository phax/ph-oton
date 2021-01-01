/**
 * Copyright (C) 2014-2021 Philip Helger (www.helger.com)
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
 * Special request field specially for check boxes with a fixed value.
 *
 * @author Philip Helger
 */
public class RequestFieldBoolean extends RequestField implements IHCRequestFieldBoolean
{
  // predefined values
  // Don't change them - it is expected that they are "true" and "false"!
  public static final String VALUE_CHECKED = Boolean.TRUE.toString ();
  public static final String VALUE_UNCHECKED = Boolean.FALSE.toString ();

  private final boolean m_bDefaultValue;

  /**
   * Constructor using either {@link #VALUE_CHECKED} or
   * {@link #VALUE_UNCHECKED}.
   *
   * @param sFieldName
   *        Name of the field.
   * @param bDefaultValue
   *        The status to be returned, if no request value is present.
   */
  public RequestFieldBoolean (@Nonnull @Nonempty final String sFieldName, final boolean bDefaultValue)
  {
    super (sFieldName, getStringValue (bDefaultValue));

    // The default value is immutable
    m_bDefaultValue = bDefaultValue;
  }

  /**
   * @param bValue
   *        the boolean value
   * @return The string parameter value to be used for the passed parameter.
   *         Neither <code>null</code> nor empty.
   */
  @Nonnull
  @Nonempty
  public static String getStringValue (final boolean bValue)
  {
    return bValue ? VALUE_CHECKED : VALUE_UNCHECKED;
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
    final RequestFieldBoolean rhs = (RequestFieldBoolean) o;
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
