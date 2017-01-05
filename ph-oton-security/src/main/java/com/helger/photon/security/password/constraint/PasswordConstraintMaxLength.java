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
package com.helger.photon.security.password.constraint;

import java.util.Locale;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.xml.microdom.IMicroElement;

/**
 * Password constraint defining the maximum length (incl.)
 *
 * @author Philip Helger
 */
public class PasswordConstraintMaxLength implements IPasswordConstraint
{
  private static final String ATTR_MAX_LENGTH = "maxlength";

  private final int m_nMaxLength;

  public PasswordConstraintMaxLength (@Nonnegative final int nMaxLength)
  {
    m_nMaxLength = ValueEnforcer.isGT0 (nMaxLength, "MaxLength");
  }

  @Nonnegative
  public int getMaxLength ()
  {
    return m_nMaxLength;
  }

  public boolean isPasswordValid (@Nullable final String sPlainTextPassword)
  {
    return StringHelper.getLength (sPlainTextPassword) <= m_nMaxLength;
  }

  @Nullable
  public String getDescription (@Nonnull final Locale aContentLocale)
  {
    return EPasswordConstraintText.DESC_MAX_LENGTH.getDisplayTextWithArgs (aContentLocale,
                                                                           Integer.valueOf (m_nMaxLength));
  }

  public void fillMicroElement (@Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_MAX_LENGTH, m_nMaxLength);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PasswordConstraintMaxLength rhs = (PasswordConstraintMaxLength) o;
    return m_nMaxLength == rhs.m_nMaxLength;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nMaxLength).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("maxLength", m_nMaxLength).toString ();
  }
}
