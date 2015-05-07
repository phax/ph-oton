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
package com.helger.html.css;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.annotations.Nonempty;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link ICSSClassProvider} interface. Uses an
 * internal cache to reuse existing objects. Use
 * {@link DefaultCSSClassProvider#create(String)} to create objects!
 *
 * @author Philip Helger
 */
@Immutable
public final class DefaultCSSClassProvider implements ICSSClassProvider, Serializable
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (DefaultCSSClassProvider.class);
  private static final Map <String, DefaultCSSClassProvider> s_aAll = new HashMap <String, DefaultCSSClassProvider> ();

  private final String m_sCSSClass;
  private Integer m_aHashCode;

  private DefaultCSSClassProvider (@Nonnull @Nonempty final String sCSSClass)
  {
    if (StringHelper.hasNoText (sCSSClass))
      throw new IllegalArgumentException ("Empty CSS class provided");
    if (sCSSClass.indexOf (' ') >= 0)
      throw new IllegalArgumentException ("CSS class may not contain spaces '" + sCSSClass + "'");
    {
      // Happens more frequently because people are reusing existing attributes
      // for configuration purposes.
      if (!RegExHelper.stringMatchesPattern ("-?[_a-zA-Z]+[_a-zA-Z0-9-]*", sCSSClass))
        s_aLogger.warn ("The CSS class '" + sCSSClass + "' does not match the naming requirements!");
    }
    if (sCSSClass.startsWith ("_"))
      throw new IllegalArgumentException ("The CSS class name '" + sCSSClass + "' may rise problems with IE6!");

    m_sCSSClass = sCSSClass;
  }

  @Nonnull
  @Nonempty
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
    final DefaultCSSClassProvider rhs = (DefaultCSSClassProvider) o;
    return m_sCSSClass.equals (rhs.m_sCSSClass);
  }

  @Override
  public int hashCode ()
  {
    if (m_aHashCode == null)
      m_aHashCode = new HashCodeGenerator (this).append (m_sCSSClass).getHashCodeObj ();
    return m_aHashCode.intValue ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("cssClass", m_sCSSClass).toString ();
  }

  @Nonnull
  public static DefaultCSSClassProvider create (@Nonnull @Nonempty final String sCSSClass)
  {
    DefaultCSSClassProvider aProvider = s_aAll.get (sCSSClass);
    if (aProvider != null)
      return aProvider;

    aProvider = new DefaultCSSClassProvider (sCSSClass);
    s_aAll.put (sCSSClass, aProvider);
    return aProvider;
  }
}
