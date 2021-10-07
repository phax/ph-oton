/*
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
 * Password constraint defining that at least a certain amount of special
 * characters (= not letter and not digit) must be contained
 *
 * @author Philip Helger
 * @since 2.7.4
 */
public class PasswordConstraintMustContainSpecialChar implements IPasswordConstraint
{
  private static final String ATTR_MIN_SPECIAL = "minspecials";

  private final int m_nMinSpecials;

  /**
   * Ctor
   *
   * @param nMinSpecials
   *        The minimum number of letters that must occur in a password. Must be
   *        &gt; 0.
   */
  public PasswordConstraintMustContainSpecialChar (@Nonnegative final int nMinSpecials)
  {
    m_nMinSpecials = ValueEnforcer.isGT0 (nMinSpecials, "MinSpecials");
  }

  @Nonnegative
  public int getMinSpecials ()
  {
    return m_nMinSpecials;
  }

  public boolean isPasswordValid (@Nullable final String sPlainTextPassword)
  {
    int nSpecials = 0;
    if (sPlainTextPassword != null)
      for (final char c : sPlainTextPassword.toCharArray ())
        if (!Character.isLetterOrDigit (c))
          ++nSpecials;
    return nSpecials >= m_nMinSpecials;
  }

  @Nullable
  public String getDescription (@Nonnull final Locale aContentLocale)
  {
    return EPasswordConstraintText.DESC_MUST_CONTAIN_SPECIALS.getDisplayTextWithArgs (aContentLocale, Integer.valueOf (m_nMinSpecials));
  }

  public void fillMicroElement (@Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_MIN_SPECIAL, m_nMinSpecials);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PasswordConstraintMustContainSpecialChar rhs = (PasswordConstraintMustContainSpecialChar) o;
    return m_nMinSpecials == rhs.m_nMinSpecials;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nMinSpecials).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("minSpecials", m_nMinSpecials).getToString ();
  }
}
