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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.annotations.DevelopersNote;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.annotations.ReturnsMutableCopy;
import com.helger.commons.collections.CollectionHelper;
import com.helger.commons.equals.EqualsUtils;
import com.helger.commons.string.ToStringGenerator;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.property.CSSPropertyFree;
import com.helger.css.property.ECSSProperty;
import com.helger.css.propertyvalue.ICSSValue;
import com.helger.html.hc.IHCHasCSSStyles;

/**
 * Stand alone implementation of {@link IHCHasCSSStyles}
 *
 * @author Philip Helger
 * @since 4.5.2
 */
public class HCHasCSSStyles implements IHCHasCSSStyles <HCHasCSSStyles>
{
  // Must be a LinkedHashMap:
  private Map <ECSSProperty, ICSSValue> m_aStyles;

  @Nonnull
  @ReturnsMutableCopy
  public final Map <ECSSProperty, ICSSValue> getAllStyles ()
  {
    return CollectionHelper.newMap (m_aStyles);
  }

  @Nonnull
  @ReturnsMutableCopy
  public final Collection <ICSSValue> getAllStyleValues ()
  {
    return m_aStyles == null ? new ArrayList <ICSSValue> () : CollectionHelper.newList (m_aStyles.values ());
  }

  @Nullable
  public final ICSSValue getStyleValue (@Nullable final ECSSProperty eProperty)
  {
    return eProperty == null || m_aStyles == null ? null : m_aStyles.get (eProperty);
  }

  public final boolean containsStyle (@Nullable final ECSSProperty eProperty)
  {
    return m_aStyles != null && m_aStyles.containsKey (eProperty);
  }

  public final boolean hasStyle (@Nullable final ICSSValue aValue)
  {
    if (aValue == null || m_aStyles == null)
      return false;

    // Contained styles can never have a null value!
    final ECSSProperty eProp = aValue.getProp ();
    return EqualsUtils.equals (m_aStyles.get (eProp), aValue);
  }

  public final boolean hasAnyStyle ()
  {
    return m_aStyles != null && !m_aStyles.isEmpty ();
  }

  @Nonnull
  public final HCHasCSSStyles addStyle (@Nonnull final ECSSProperty eProperty,
                                        @Nonnull @Nonempty final String sPropertyValue)
  {
    return addStyle (new CSSPropertyFree (eProperty).newValue (sPropertyValue));
  }

  @Nonnull
  public final HCHasCSSStyles addStyle (@Nullable final ICSSValue aValue)
  {
    if (aValue != null)
    {
      if (m_aStyles == null)
        m_aStyles = new LinkedHashMap <ECSSProperty, ICSSValue> ();
      m_aStyles.put (aValue.getProp (), aValue);
    }
    return this;
  }

  @Nonnull
  @DevelopersNote ("Use addStyle - singular")
  @Deprecated
  public final HCHasCSSStyles addStyles (@Nullable final ICSSValue aValue)
  {
    return addStyle (aValue);
  }

  @Nonnull
  public final HCHasCSSStyles addStyles (@Nullable final ICSSValue... aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return this;
  }

  @Nonnull
  public final HCHasCSSStyles addStyles (@Nullable final Iterable <? extends ICSSValue> aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return this;
  }

  @Nonnull
  public final HCHasCSSStyles removeStyle (@Nonnull final ECSSProperty eProperty)
  {
    if (m_aStyles != null)
      m_aStyles.remove (eProperty);
    return this;
  }

  @Nonnull
  public final HCHasCSSStyles removeAllStyles ()
  {
    m_aStyles.clear ();
    return this;
  }

  @Nullable
  public final String getAllStylesAsString (@Nonnull final ICSSWriterSettings aCSSSettings)
  {
    if (m_aStyles == null || m_aStyles.isEmpty ())
      return null;
    final StringBuilder aSB = new StringBuilder ();
    for (final ICSSValue aValue : m_aStyles.values ())
      aSB.append (aValue.getAsCSSString (aCSSSettings, 0));
    return aSB.toString ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).appendIfNotEmpty ("Styles", m_aStyles).toString ();
  }
}
