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
package com.helger.html.hc.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.string.StringHelper;
import com.helger.commons.string.ToStringGenerator;
import com.helger.html.css.ICSSClassProvider;
import com.helger.html.hc.IHCHasCSSClasses;

/**
 * Stand alone implementation of {@link IHCHasCSSClasses}
 *
 * @author Philip Helger
 * @since 4.5.2
 */
public class HCHasCSSClasses implements IHCHasCSSClasses <HCHasCSSClasses>
{
  // Must be a LinkedHashSet:
  private Set <ICSSClassProvider> m_aCSSClassProviders;

  public boolean containsClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return aCSSClassProvider != null &&
           m_aCSSClassProviders != null &&
           m_aCSSClassProviders.contains (aCSSClassProvider);
  }

  @Nonnull
  public HCHasCSSClasses addClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    if (aCSSClassProvider != null)
    {
      if (m_aCSSClassProviders == null)
        m_aCSSClassProviders = new LinkedHashSet <ICSSClassProvider> ();
      m_aCSSClassProviders.add (aCSSClassProvider);
    }
    return this;
  }

  @Deprecated
  @Nonnull
  public HCHasCSSClasses addClasses (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    return addClass (aCSSClassProvider);
  }

  @Nonnull
  public HCHasCSSClasses addClasses (@Nullable final ICSSClassProvider... aCSSClassProviders)
  {
    if (aCSSClassProviders != null)
      for (final ICSSClassProvider aProvider : aCSSClassProviders)
        addClass (aProvider);
    return this;
  }

  @Nonnull
  public HCHasCSSClasses addClasses (@Nullable final Iterable <? extends ICSSClassProvider> aCSSClassProviders)
  {
    if (aCSSClassProviders != null)
      for (final ICSSClassProvider aProvider : aCSSClassProviders)
        addClass (aProvider);
    return this;
  }

  @Nonnull
  public HCHasCSSClasses removeClass (@Nullable final ICSSClassProvider aCSSClassProvider)
  {
    if (m_aCSSClassProviders != null && aCSSClassProvider != null)
      m_aCSSClassProviders.remove (aCSSClassProvider);
    return this;
  }

  @Nonnull
  public HCHasCSSClasses removeAllClasses ()
  {
    if (m_aCSSClassProviders != null)
      m_aCSSClassProviders.clear ();
    return this;
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <ICSSClassProvider> getAllClasses ()
  {
    return CollectionHelper.newOrderedSet (m_aCSSClassProviders);
  }

  @Nonnull
  @ReturnsMutableCopy
  public Set <String> getAllClassNames ()
  {
    final Set <String> ret = new LinkedHashSet <String> ();
    if (m_aCSSClassProviders != null)
      for (final ICSSClassProvider aCSSClassProvider : m_aCSSClassProviders)
      {
        final String sCSSClass = aCSSClassProvider.getCSSClass ();
        if (StringHelper.hasText (sCSSClass))
          ret.add (sCSSClass);
      }
    return ret;
  }

  @Nullable
  public String getAllClassesAsString ()
  {
    if (m_aCSSClassProviders == null || m_aCSSClassProviders.isEmpty ())
      return null;

    final StringBuilder aSB = new StringBuilder ();
    for (final ICSSClassProvider aCSSClassProvider : m_aCSSClassProviders)
    {
      final String sCSSClass = aCSSClassProvider.getCSSClass ();
      if (StringHelper.hasText (sCSSClass))
      {
        if (aSB.length () > 0)
          aSB.append (' ');
        aSB.append (sCSSClass);
      }
    }
    return aSB.toString ();
  }

  public boolean hasAnyClass ()
  {
    return m_aCSSClassProviders != null && !m_aCSSClassProviders.isEmpty ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotEmpty ("CSSClassProviders", m_aCSSClassProviders).toString ();
  }
}
