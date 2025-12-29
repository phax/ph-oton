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
package com.helger.html.js;

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.annotation.style.UnsafeAPI;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

/**
 * Implementation of {@link IHasJSCode} that takes an arbitrary string from any sources
 *
 * @author Philip Helger
 */
@Immutable
public class UnparsedJSCodeProvider implements IHasJSCode
{
  private final String m_sJSCode;

  @UnsafeAPI
  public UnparsedJSCodeProvider (@NonNull final String sJSCode)
  {
    m_sJSCode = ValueEnforcer.notNull (sJSCode, "JSCode");
  }

  /**
   * @return The plain JS code passed in the constructor
   */
  @NonNull
  public String getJSCode ()
  {
    return m_sJSCode;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final UnparsedJSCodeProvider rhs = (UnparsedJSCodeProvider) o;
    return m_sJSCode.equals (rhs.m_sJSCode);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sJSCode).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("JSCode", m_sJSCode).getToString ();
  }
}
