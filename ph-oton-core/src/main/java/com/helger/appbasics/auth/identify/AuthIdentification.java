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
package com.helger.appbasics.auth.identify;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import org.joda.time.DateTime;

import com.helger.appbasics.auth.subject.IAuthSubject;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;
import com.helger.datetime.PDTFactory;

/**
 * Default implementation of the {@link IAuthIdentification} interface.
 *
 * @author Philip Helger
 */
@Immutable
public final class AuthIdentification implements IAuthIdentification
{
  private final IAuthSubject m_aSubject;
  private final DateTime m_aIdentificationDT;

  /**
   * @param aSubject
   *        May be <code>null</code>.
   */
  public AuthIdentification (@Nullable final IAuthSubject aSubject)
  {
    m_aSubject = aSubject;
    m_aIdentificationDT = PDTFactory.getCurrentDateTime ();
  }

  @Nullable
  public IAuthSubject getSubject ()
  {
    return m_aSubject;
  }

  @Nonnull
  public DateTime getIdentificationDate ()
  {
    return m_aIdentificationDT;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final AuthIdentification rhs = (AuthIdentification) o;
    return EqualsUtils.equals (m_aSubject, rhs.m_aSubject) && m_aIdentificationDT.equals (rhs.m_aIdentificationDT);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aSubject).append (m_aIdentificationDT).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotNull ("subject", m_aSubject)
                                       .append ("identificationDT", m_aIdentificationDT)
                                       .toString ();
  }
}
