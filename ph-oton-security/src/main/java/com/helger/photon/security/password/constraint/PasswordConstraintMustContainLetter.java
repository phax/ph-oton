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
import com.helger.commons.microdom.IMicroElement;
import com.helger.commons.string.ToStringGenerator;

/**
 * Password constraint defining that at least a certain amount of letters must
 * be contained
 *
 * @author Philip Helger
 * @since 2.7.4
 */
public class PasswordConstraintMustContainLetter implements IPasswordConstraint
{
  private static final String ATTR_MIN_LETTERS = "minletters";

  private final int m_nMinLetters;

  /**
   * Ctor
   *
   * @param nMinLetters
   *        The minimum number of letters that must occur in a password. Must be
   *        &gt; 0.
   */
  public PasswordConstraintMustContainLetter (@Nonnegative final int nMinLetters)
  {
    m_nMinLetters = ValueEnforcer.isGT0 (nMinLetters, "MinLetters");
  }

  @Nonnegative
  public int getMinLetters ()
  {
    return m_nMinLetters;
  }

  public boolean isPasswordValid (@Nullable final String sPlainTextPassword)
  {
    int nLetters = 0;
    if (sPlainTextPassword != null)
      for (final char c : sPlainTextPassword.toCharArray ())
        if (Character.isLetter (c))
          ++nLetters;
    return nLetters >= m_nMinLetters;
  }

  @Nullable
  public String getDescription (@Nonnull final Locale aContentLocale)
  {
    return EPasswordConstraintText.DESC_MUST_CONTAIN_LETTERS.getDisplayTextWithArgs (aContentLocale,
                                                                                     Integer.valueOf (m_nMinLetters));
  }

  public void fillMicroElement (@Nonnull final IMicroElement aElement)
  {
    aElement.setAttribute (ATTR_MIN_LETTERS, m_nMinLetters);
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final PasswordConstraintMustContainLetter rhs = (PasswordConstraintMustContainLetter) o;
    return m_nMinLetters == rhs.m_nMinLetters;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_nMinLetters).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("minLetters", m_nMinLetters).toString ();
  }
}
