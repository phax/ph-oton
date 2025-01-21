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
package com.helger.photon.core.form;

import javax.annotation.Nonnull;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.request.IHCRequestFieldBooleanMultiValue;

/**
 * Special request field specially for check boxes where the same field name can
 * be used with different values.
 *
 * @author Philip Helger
 */
public class RequestFieldBooleanMultiValue extends RequestField implements IHCRequestFieldBooleanMultiValue
{
  private final String m_sValue;
  private final boolean m_bDefaultValue;

  /**
   * Constructor.
   *
   * @param sFieldName
   *        Name of the field.
   * @param sValue
   *        The value to be used for the check box.
   * @param bDefaultValue
   *        The status to be returned, if no request value is present.
   */
  public RequestFieldBooleanMultiValue (@Nonnull @Nonempty final String sFieldName,
                                        @Nonnull final String sValue,
                                        final boolean bDefaultValue)
  {
    super (sFieldName, bDefaultValue ? sValue : null);

    m_sValue = ValueEnforcer.notNull (sValue, "Value");
    // The default value is immutable
    m_bDefaultValue = bDefaultValue;
  }

  @Nonnull
  public String getValue ()
  {
    return m_sValue;
  }

  public boolean isChecked ()
  {
    return getParams ().hasCheckBoxValue (getFieldName (), m_sValue, m_bDefaultValue);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (!super.equals (o))
      return false;
    final RequestFieldBooleanMultiValue rhs = (RequestFieldBooleanMultiValue) o;
    return m_sValue.equals (rhs.m_sValue) && m_bDefaultValue == rhs.m_bDefaultValue;
  }

  @Override
  public int hashCode ()
  {
    return HashCodeGenerator.getDerived (super.hashCode ()).append (m_sValue).append (m_bDefaultValue).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return ToStringGenerator.getDerived (super.toString ())
                            .append ("Value", m_sValue)
                            .append ("DefaultValue", m_bDefaultValue)
                            .getToString ();
  }
}
