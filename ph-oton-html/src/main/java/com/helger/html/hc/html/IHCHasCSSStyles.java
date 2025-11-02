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
package com.helger.html.hc.html;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import com.helger.annotation.Nonempty;
import com.helger.annotation.misc.DevelopersNote;
import com.helger.annotation.style.ReturnsMutableCopy;
import com.helger.base.trait.IGenericImplTrait;
import com.helger.collection.commons.ICommonsList;
import com.helger.collection.commons.ICommonsOrderedMap;
import com.helger.css.ICSSWriterSettings;
import com.helger.css.property.CSSPropertyFree;
import com.helger.css.property.ECSSProperty;
import com.helger.css.propertyvalue.ICSSValue;

/**
 * Base interface for objects having CSS styles
 *
 * @author Philip Helger
 * @param <IMPLTYPE>
 *        Implementation type
 */
public interface IHCHasCSSStyles <IMPLTYPE extends IHCHasCSSStyles <IMPLTYPE>> extends IGenericImplTrait <IMPLTYPE>
{
  /**
   * Add an element specific style (that is not consistency checked).
   *
   * @param eProperty
   *        The CSS property to be added. May not be <code>null</code>.
   * @param sPropertyValue
   *        The property value to be used. May not be <code>null</code>.
   * @return this
   */
  @NonNull
  default IMPLTYPE addStyle (@NonNull final ECSSProperty eProperty, @NonNull @Nonempty final String sPropertyValue)
  {
    return addStyle (new CSSPropertyFree (eProperty).newValue (sPropertyValue));
  }

  /**
   * Add an element specific style.
   *
   * @param aValue
   *        The value to be added. May be <code>null</code>.
   * @return this
   */
  @NonNull
  IMPLTYPE addStyle (@Nullable ICSSValue aValue);

  @NonNull
  @Deprecated (forRemoval = false)
  @DevelopersNote ("Use addStyle instead!")
  default IMPLTYPE addStyles (@Nullable final ICSSValue aValue)
  {
    return addStyle (aValue);
  }

  /**
   * Add element specific styles.
   *
   * @param aValues
   *        The values to be added. May be <code>null</code>.
   * @return this
   */
  @NonNull
  default IMPLTYPE addStyles (@Nullable final ICSSValue... aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return thisAsT ();
  }

  /**
   * Add element specific styles.
   *
   * @param aValues
   *        The values to be added. May be <code>null</code>.
   * @return this
   */
  @NonNull
  default IMPLTYPE addStyles (@Nullable final Iterable <? extends ICSSValue> aValues)
  {
    if (aValues != null)
      for (final ICSSValue aValue : aValues)
        addStyle (aValue);
    return thisAsT ();
  }

  /**
   * Remove the specified style from the element
   *
   * @param eProperty
   *        The style property to remove
   * @return this
   */
  @NonNull
  IMPLTYPE removeStyle (@NonNull ECSSProperty eProperty);

  /**
   * Remove all styles from the element
   *
   * @return this
   */
  @NonNull
  IMPLTYPE removeAllStyles ();

  /**
   * @return A copy of all contained styles. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsOrderedMap <ECSSProperty, ICSSValue> getAllStyles ();

  /**
   * @return All style values. Never <code>null</code>.
   */
  @NonNull
  @ReturnsMutableCopy
  ICommonsList <ICSSValue> getAllStyleValues ();

  /**
   * Find the style value associated to a single property.
   *
   * @param eProperty
   *        The property to search. May be <code>null</code>.
   * @return <code>null</code> if no such style is contained.
   */
  @Nullable
  ICSSValue getStyleValue (@Nullable ECSSProperty eProperty);

  /**
   * Check if a style value is associated to a single property.
   *
   * @param eProperty
   *        The property to search. May be <code>null</code>.
   * @return <code>true</code> if a respective style is present,
   *         <code>false</code> otherwise.
   */
  boolean containsStyle (@Nullable ECSSProperty eProperty);

  /**
   * Check if the style property is contained, and the value matches.
   *
   * @param aValue
   *        The value to be checked
   * @return <code>true</code> if such a CSS value is contained,
   *         <code>false</code> otherwise
   */
  boolean hasStyle (@Nullable ICSSValue aValue);

  /**
   * @return <code>true</code> if at least one CSS style is assigned,
   *         <code>false</code> otherwise.
   */
  boolean hasAnyStyle ();

  /**
   * The value to be set to the HTML style attribute
   *
   * @param aCSSSettings
   *        The CSS settings to use
   * @return <code>null</code> if no styles are present
   */
  @Nullable
  String getAllStylesAsString (@NonNull ICSSWriterSettings aCSSSettings);
}
