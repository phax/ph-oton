/*
 * Copyright (C) 2014-2026 Philip Helger (www.helger.com)
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

import org.jspecify.annotations.NonNull;

import com.helger.annotation.concurrent.Immutable;
import com.helger.base.enforce.ValueEnforcer;
import com.helger.base.hashcode.HashCodeGenerator;
import com.helger.base.tostring.ToStringGenerator;

/**
 * This class encapsulates a single MetaTag. It consists of a name, a locale and a value. If the
 * meta tag is locale independent, the constant
 * {@link com.helger.text.locale.LocaleHelper#LOCALE_INDEPENDENT} is used.
 *
 * @author Philip Helger
 */
@Immutable
public class MetaElementValue implements IMetaElementValue
{
  private final EMetaElementType m_eType;
  private final String m_sName;
  private final Locale m_aContentLocale;
  private final String m_sContent;

  public MetaElementValue (@NonNull final EMetaElementType eType,
                           @NonNull final String sName,
                           @NonNull final Locale aContentLocale,
                           @NonNull final String sContent)
  {
    ValueEnforcer.notNull (eType, "Type");
    ValueEnforcer.notNull (sName, "Name");
    ValueEnforcer.notNull (aContentLocale, "ContentLocale");
    ValueEnforcer.notNull (sContent, "Content");

    m_eType = eType;
    m_sName = sName;
    m_aContentLocale = aContentLocale;
    m_sContent = sContent;
  }

  @NonNull
  public EMetaElementType getType ()
  {
    return m_eType;
  }

  @NonNull
  public String getName ()
  {
    return m_sName;
  }

  @NonNull
  public Locale getContentLocale ()
  {
    return m_aContentLocale;
  }

  @NonNull
  public String getContent ()
  {
    return m_sContent;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final MetaElementValue rhs = (MetaElementValue) o;
    return m_eType.equals (rhs.m_eType) &&
           m_sName.equals (rhs.m_sName) &&
           m_aContentLocale.equals (rhs.m_aContentLocale) &&
           m_sContent.equals (rhs.m_sContent);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eType)
                                       .append (m_sName)
                                       .append (m_aContentLocale)
                                       .append (m_sContent)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("type", m_eType)
                                       .append ("name", m_sName)
                                       .append ("contentLocale", m_aContentLocale)
                                       .append ("content", m_sContent)
                                       .getToString ();
  }
}
