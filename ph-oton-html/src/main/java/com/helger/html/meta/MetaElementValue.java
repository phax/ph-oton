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
package com.helger.html.meta;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.string.ToStringGenerator;

/**
 * This class encapsulates a single MetaTag. It consists of a name, a locale and
 * a value. If the meta tag is locale independent, the constant
 * {@link com.helger.commons.CGlobal#LOCALE_INDEPENDENT} is used.
 *
 * @author Philip Helger
 */
@Immutable
public class MetaElementValue implements IMetaElementValue
{
  private final String m_sName;
  private final Locale m_aContentLocale;
  private final String m_sContent;
  private final boolean m_bIsHttpEquiv;

  public MetaElementValue (@Nonnull @Nonempty final String sName,
                           @Nonnull final Locale aContentLocale,
                           @Nonnull final String sContent,
                           final boolean bIsHttpEquiv)
  {
    ValueEnforcer.notEmpty (sName, "Name");
    ValueEnforcer.notNull (aContentLocale, "ContentLocale");
    ValueEnforcer.notNull (sContent, "Content");

    m_sName = sName;
    m_aContentLocale = aContentLocale;
    m_sContent = sContent;
    m_bIsHttpEquiv = bIsHttpEquiv;
  }

  @Nonnull
  @Nonempty
  public String getName ()
  {
    return m_sName;
  }

  @Nonnull
  public Locale getContentLocale ()
  {
    return m_aContentLocale;
  }

  @Nonnull
  public String getContent ()
  {
    return m_sContent;
  }

  public boolean isHttpEquiv ()
  {
    return m_bIsHttpEquiv;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final MetaElementValue rhs = (MetaElementValue) o;
    return m_sName.equals (rhs.m_sName) &&
           m_aContentLocale.equals (rhs.m_aContentLocale) &&
           m_sContent.equals (rhs.m_sContent) &&
           m_bIsHttpEquiv == rhs.m_bIsHttpEquiv;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_sName)
                                       .append (m_aContentLocale)
                                       .append (m_sContent)
                                       .append (m_bIsHttpEquiv)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("name", m_sName)
                                       .append ("contentLocale", m_aContentLocale)
                                       .append ("content", m_sContent)
                                       .append ("isHttpEquiv", m_bIsHttpEquiv)
                                       .getToString ();
  }
}
