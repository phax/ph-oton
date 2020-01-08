/**
 * Copyright (C) 2014-2020 Philip Helger (www.helger.com)
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

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.collection.impl.CommonsHashMap;
import com.helger.commons.collection.impl.ICommonsMap;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.hashcode.IHashCodeGenerator;
import com.helger.commons.id.factory.GlobalIDFactory;
import com.helger.commons.regex.RegExHelper;
import com.helger.commons.string.ToStringGenerator;

/**
 * Default implementation of the {@link ICSSClassProvider} interface. Uses an
 * internal cache to reuse existing objects. Use
 * {@link DefaultCSSClassProvider#create(String)} to create objects!
 *
 * @author Philip Helger
 */
@Immutable
public class DefaultCSSClassProvider implements ICSSClassProvider, Serializable
{
  private static final Logger LOGGER = LoggerFactory.getLogger (DefaultCSSClassProvider.class);
  private static final ICommonsMap <String, DefaultCSSClassProvider> s_aAll = new CommonsHashMap <> ();

  private final String m_sCSSClass;
  // Status vars
  private transient int m_nHashCode = IHashCodeGenerator.ILLEGAL_HASHCODE;

  public static void validateCSSClassName (@Nonnull @Nonempty final String sCSSClass) throws IllegalArgumentException
  {
    ValueEnforcer.notEmpty (sCSSClass, "CSSClass");
    if (sCSSClass.indexOf (' ') >= 0)
      throw new IllegalArgumentException ("CSS class may not contain spaces '" + sCSSClass + "'");

    // Happens more frequently because people are reusing existing attributes
    // for configuration purposes.
    if (!RegExHelper.stringMatchesPattern ("-?[_a-zA-Z]+[_a-zA-Z0-9-]*", sCSSClass))
      LOGGER.warn ("The CSS class '" + sCSSClass + "' does not match the naming requirements!");

    if (sCSSClass.startsWith ("_"))
      throw new IllegalArgumentException ("The CSS class name '" + sCSSClass + "' may rise problems with IE6!");
  }

  private DefaultCSSClassProvider (@Nonnull @Nonempty final String sCSSClass)
  {
    validateCSSClassName (sCSSClass);
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
    int ret = m_nHashCode;
    if (ret == IHashCodeGenerator.ILLEGAL_HASHCODE)
      ret = m_nHashCode = new HashCodeGenerator (this).append (m_sCSSClass).getHashCode ();
    return ret;
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("cssClass", m_sCSSClass).getToString ();
  }

  @Nonnull
  public static DefaultCSSClassProvider create (@Nonnull @Nonempty final String sCSSClass)
  {
    return s_aAll.computeIfAbsent (sCSSClass, k -> new DefaultCSSClassProvider (sCSSClass));
  }

  /**
   * Create a dummy CSS class with an arbitrary, but unique name.
   *
   * @return A new CSS class with a unique, non-persistent name.
   * @since 8.0.1
   */
  @Nonnull
  public static DefaultCSSClassProvider createUnique ()
  {
    return create ("cssclass" + GlobalIDFactory.getNewIntID ());
  }
}
