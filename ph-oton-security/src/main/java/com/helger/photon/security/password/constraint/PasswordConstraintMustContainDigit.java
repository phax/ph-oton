/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
import com.helger.commons.string.ToStringGenerator;
import com.helger.xml.microdom.IMicroElement;

/**
 * Password constraint defining that at least a certain amount of digits must be
 * contained
 *
 * @author Philip Helger
 * @since 2.7.4
 */
public class PasswordConstraintMustContainDigit implements IPasswordConstraint
{
  private static final String ATTR_MIN_DIGITS = "mindigits";

  private final int m_nMinDigits;

  /**
   * Ctor
   *
   * @param nMinDigits
   *        The minimum number of digits that must occur in a password. Must be
   *        &gt; 0.
   */
  public PasswordConstraintMustContainDigit (@Nonnegative final int nMinDigits)
  {
    m_nMinDigits = ValueEnforcer.isGT0 (nMinDigits, "MinNumbers");
  }

  @Nonnegative
  public int getMinDigits ()
  {
    return m_nMinDigits;
  }

  public boolean isPasswordValid (@Nullable final String sPlainTextPassword)
  {
    int nDigits = 0;
    if (sPlainTextPassword != null)
      for (final char c : sPlainTextPassword.toCharArray ())
        if (Character.isDigit (c))
          ++nDigits;
    return nDigits >= m_nMinDigits;
  }

  @Nullable
  public String getDescription (@Nonnull final Locale aContentLocale)
  {
    return EPasswordConstraintText.DESC_MUST_CONTAIN_DIGITS.getDisplayTextWithArgs (aContentLocale,
                                                                                    Integer.valueOf (m_nMinDigits));
  }

  public void fillMicroElement (@Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_MIN_DIGITS, m_nMinDigits);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PasswordConstraintMustContainDigit rhs = (PasswordConstraintMustContainDigit) o;
    return m_nMinDigits == rhs.m_nMinDigits;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nMinDigits).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("minDigits", m_nMinDigits).toString ();
  }
}
