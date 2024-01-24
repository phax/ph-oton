/*
 * Copyright (C) 2014-2024 Philip Helger (www.helger.com)
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
package com.helger.html.css;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * A special implementation of {@link ICSSClassProvider} using no checks at all.
 * Handle with care. Prefer using {@link DefaultCSSClassProvider} instead.
 *
 * @author Philip Helger
 */
@Immutable
public class UncheckedCSSClassProvider implements ICSSClassProvider
{
  private final String m_sCSSClass;

  public UncheckedCSSClassProvider (@Nullable final String sCSSClass)
  {
    m_sCSSClass = sCSSClass;
  }

  @Nullable
  public String getCSSClass ()
  {
    return m_sCSSClass;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final UncheckedCSSClassProvider rhs = (UncheckedCSSClassProvider) o;
    return EqualsHelper.equals (m_sCSSClass, rhs.m_sCSSClass);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sCSSClass).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("cssClass", m_sCSSClass).getToString ();
  }
}
